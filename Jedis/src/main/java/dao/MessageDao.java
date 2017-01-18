package dao;

import entity.Message;
import util.jdbc.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * <p>
 * <p>
 * Created by liuchenwei on 2016/7/7.
 */
public class MessageDao {

    private static final String SQL_INSERT =
            "INSERT INTO msg (id, userid, username, toid, toname, body, type, timestamp) " +
            " VALUES (?,?,?,?,?,?,?,?)";

    public void insert(List<Message> messages) {
        if(messages == null || messages.isEmpty()) {
            return;
        }

        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.create();

            connection.setAutoCommit(false);

            ps = connection.prepareStatement(SQL_INSERT);

            for (Message message : messages) {
                if (message.getMid() != null) {
                    ps.setString(1, message.getMid());
                    ps.setString(2, message.getFrom() == null ? "" : message.getFrom().getUid());
                    ps.setString(3, message.getFrom() == null ? "" : message.getFrom().getTitle());
                    ps.setString(4, message.getTo());
                    ps.setString(5, message.getToName());
                    ps.setString(6, message.getBody());
                    ps.setString(7, message.getType());
                    ps.setString(8, message.getTimestamp().substring(0,19).replace('T',' '));
                    ps.addBatch();
                }
            }

            int[] ints = ps.executeBatch();
            System.out.println("data inserted：" + ints.length);
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
