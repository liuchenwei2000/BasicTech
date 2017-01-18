package jedis;

import redis.clients.jedis.Jedis;

/**
 * RedisClient
 * <p>
 * <p>
 * Created by liuchenwei on 2016/7/7.
 */
public class RedisClient {

    private static final String HOST = "10.24.11.232";
    private static final int PORT = 6379;
    private static final String PASSWORD = "inspur-Solr";

    private Jedis jedis;

    public RedisClient() {
        // 连接 redis 服务器
        jedis = new Jedis(HOST, PORT);
        // 权限认证
        jedis.auth(PASSWORD);
    }

    public Jedis getConnection(){
        return jedis;
    }
}
