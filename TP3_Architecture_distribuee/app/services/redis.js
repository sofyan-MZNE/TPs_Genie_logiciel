const Redis = require('ioredis');

const redis = new Redis({ host: 'redis', port: 6379 });

redis.on('connect', () => console.log(' Redis connecté'));

module.exports = redis;