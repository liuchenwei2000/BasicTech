package test;

import dao.MessageDao;
import entity.Message;
import jedis.msg.MessageFetcher;
import util.TimeCounter;

import java.util.List;

/**
 * <p>
 * <p>
 * Created by liuchenwei on 2016/7/6.
 */
public class JedisTest {

    public static void main(String[] args) {
        MessageFetcher messageFetcher = new MessageFetcher();
        List<Message> messages = messageFetcher.extractMessages();
        messageFetcher.close();

        TimeCounter tc = new TimeCounter();
        tc.start();

        new MessageDao().insert(messages);

        tc.stop();
        System.out.println("insertMessages：" + tc.consumeBySecond()+ "s");
    }
}
