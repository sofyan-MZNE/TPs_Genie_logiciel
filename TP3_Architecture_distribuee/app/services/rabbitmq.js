const amqp = require('amqplib');

let channel;
const QUEUE = 'commandes';

async function connectRabbitMQ(retries = 10) {
  for (let i = 0; i < retries; i++) {
    try {
      const conn = await amqp.connect('amqp://admin:admin@rabbitmq');
      channel = await conn.createChannel();
      await channel.assertQueue(QUEUE, { durable: true });
      console.log('✅ RabbitMQ connecté');
      return;
    } catch (err) {
      console.log(`⏳ RabbitMQ pas prêt, tentative ${i + 1}/${retries}...`);
      await new Promise(res => setTimeout(res, 3000));
    }
  }
  throw new Error('❌ Impossible de se connecter à RabbitMQ');
}

function publierCommande(commande) {
  const msg = JSON.stringify(commande);
  channel.sendToQueue(QUEUE, Buffer.from(msg), { persistent: true });
  console.log('📤 Message publié dans RabbitMQ');
}

module.exports = { connectRabbitMQ, publierCommande };