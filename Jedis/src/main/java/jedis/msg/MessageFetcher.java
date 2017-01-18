package jedis.msg;

import com.alibaba.fastjson.JSON;
import entity.Message;
import jedis.RedisClient;
import redis.clients.jedis.Jedis;
import util.TimeCounter;

import java.util.*;

/**
 * <p>
 * <p>
 * Created by liuchenwei on 2016/7/7.
 */
public class MessageFetcher {

    private Jedis jedis = new RedisClient().getConnection();

    public List<Message> extractMessages() {
        TimeCounter tc = new TimeCounter();
        tc.start();

        Set<String> keys = jedis.keys("ecm:msg:*");
        System.out.println("Messages=" + keys.size());
        Map<String, String> id_type_channels = extractChannels();
        List<Message> messages = new LinkedList<>();
        if (keys != null) {
            for (String table : keys) {
                Message message = JSON.parseObject(jedis.get(table), Message.class);
                message.setToName(id_type_channels.get(message.getTo()));
                messages.add(message);
            }
        }

        tc.stop();
        System.out.println("extractMessages：" + tc.consumeBySecond()+ "s");

        return new ArrayList<>(messages);
    }

    public Map<String, String> extractChannels() {
        Set<String> keys = jedis.keys("ecm:idx:cu:*");
        System.out.println("Channels=" + keys.size());
        Map<String, String> id_type_channels = new HashMap<>();
        if (keys != null) {
            for (String table : keys) {
                Set<String> hkeys = jedis.hkeys(table);
                for (String hkey : hkeys) {
                    if (id_type_channels.get(hkey) == null) {
                        id_type_channels.put(hkey, jedis.hmget(table, hkey).get(0));
                    }
                }
            }
        }
        return id_type_channels;
    }

    public void close() {
        if (jedis != null) {
            jedis.close();
        }
    }
}
