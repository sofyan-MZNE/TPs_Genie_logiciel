const amqp = require('amqplib');

async function demarrerConsommateur() {
  const conn = await amqp.connect('amqp://admin:admin@rabbitmq');
  const channel = await conn.createChannel();
  const QUEUE = 'commandes';

  await channel.assertQueue(QUEUE, { durable: true });
  console.log(' Consommateur en attente de messages...');

  channel.consume(QUEUE, (msg) => {
    if (msg) {
      const commande = JSON.parse(msg.content.toString());
      console.log('\n NOUVELLE COMMANDE REÇUE !');
      console.log('  Client  :', commande.client);
      console.log('  Produit :', commande.produit);
      console.log('  ID      :', commande.id);
      console.log('Email de confirmation envoyé (simulé)\n');
      channel.ack(msg);
    }
  });
}

// Attendre 5s que RabbitMQ soit prêt
setTimeout(demarrerConsommateur, 5000);