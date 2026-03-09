const express = require('express');
const router = express.Router();
const { Commande } = require('../services/mongodb');
const redis = require('../services/redis');
const { publierCommande } = require('../services/rabbitmq');

const INSTANCE = process.env.INSTANCE || 'Instance-?';
const TTL = 30;

//POST unecommandes
router.post('/', async (req, res) => {
  try {
    const { produit, quantite, client } = req.body;

    if (!produit || !quantite || !client)
      return res.status(400).json({ erreur: 'Champs manquants' });

    // 1. Sauvegarder dans MongoDB
    const commande = new Commande({ produit, quantite, client });
    await commande.save();

    // 2. Invalider le cache Redis
    await redis.del('toutes_commandes');

    // 3. Publier dans RabbitMQ
    publierCommande({ id: commande._id, produit, client });

    res.status(201).json({
      message: 'Commande créée',
      commande,
      traitee_par: INSTANCE
    });

  } catch (err) {
    res.status(500).json({ erreur: err.message });
  }
});

// GET une commandes 
router.get('/', async (req, res) => {
  try {
    // 1. Vérifier le cache Redis
    const cached = await redis.get('toutes_commandes');
    if (cached) {
      return res.json({
        source: ' CACHE Redis',
        traitee_par: INSTANCE,
        commandes: JSON.parse(cached)
      });
    }

    // 2. Sinon aller dans MongoDB
    const commandes = await Commande.find().sort({ date: -1 });

    // 3. Stocker en cache 30 secondes
    await redis.setex('toutes_commandes', TTL, JSON.stringify(commandes));

    res.json({
      source: ' MongoDB',
      traitee_par: INSTANCE,
      commandes
    });

  } catch (err) {
    res.status(500).json({ erreur: err.message });
  }
});

module.exports = router;