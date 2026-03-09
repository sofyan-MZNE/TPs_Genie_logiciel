const mongoose = require('mongoose');

const CommandeSchema = new mongoose.Schema({
  produit:  { type: String, required: true },
  quantite: { type: Number, required: true },
  client:   { type: String, required: true },
  statut:   { type: String, default: 'en_attente' },
  date:     { type: Date, default: Date.now }
});

const Commande = mongoose.model('Commande', CommandeSchema);

async function connectMongo() {
  await mongoose.connect('mongodb://mongodb:27017/boutique');
  console.log(' MongoDB connecté');
}

module.exports = { connectMongo, Commande };