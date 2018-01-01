package com.noname.service.impl;

import com.noname.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.Slowlog;

import java.util.List;

@Service
public class RedisServiceImpl implements RedisService{

    @Autowired
    JedisPool jedisPool;

    @Override
    public String getRdeisInfo() {
        Jedis jedis = null;

        try {
            jedis =  jedisPool.getResource();
            Client client = jedis.getClient();
            client.info();
            ;
            String info  = client.getBulkReply();
            return info;
        } finally {

            //连接返回到连接池
          //  jedis.close();;
        }
    }

    @Override
    public List<Slowlog> getLogs(long entries) {
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            List<Slowlog> logList = jedis.slowlogGet(entries);
            return logList;
        } finally {
           jedisPool.returnResource(jedis);
        }
    }

    @Override
    public Long getLogsLESN() {
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            Long logLen = jedis.slowlogLen();
            return logLen;
        } finally {
            jedis.close();;
        }
    }

    @Override
    public String logEnpty() {

        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            return jedis.slowlogReset();
        } finally {
            jedis.close();;
        }
    }

    @Override
    public Long dbSize() {
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            //TODO 配日志redis服务器信息
            Client client = jedis.getClient();
            client.dbSize();;
            return client.getIntegerReply();
        } finally {
            jedis.close();;
        }
    }
}
