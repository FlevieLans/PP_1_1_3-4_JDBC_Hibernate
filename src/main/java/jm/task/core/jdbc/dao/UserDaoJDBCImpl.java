package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Connection connection = getConnection();
        PreparedStatement ps = null;
        String sql = "CREATE TABLE users (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), lastName VARCHAR(20), age INT)";
        try {
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (ps != null) { try { ps.close(); } catch (SQLException e) { e.printStackTrace(); } }
            if (connection != null) { try { connection.close(); } catch (SQLException e) { e.printStackTrace(); } }
        }

    }

    public void dropUsersTable() {
        Connection connection = getConnection();
        PreparedStatement ps = null;
        String sql = "DROP TABLE IF EXISTS users";
        try {
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (ps != null) { try { ps.close(); } catch (SQLException e) { e.printStackTrace(); } }
            if (connection != null) { try { connection.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = getConnection();
        PreparedStatement ps = null;
        String sql = "insert into users (name, lastName, age) values (?, ?, ?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
            System.out.println("Пользователь добавлен");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (ps != null) { try { ps.close(); } catch (SQLException e) { e.printStackTrace(); } }
            if (connection != null) { try { connection.close(); } catch (SQLException e) { e.printStackTrace(); } }
        }

    }

    public void removeUserById(long id) {
        Connection connection = getConnection();
        PreparedStatement ps = null;
        String sql = "delete from users where id = ?";
        User user = new User();
        try {
            ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
            System.out.println("Пользователь удален");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (ps != null) { try { ps.close(); } catch (SQLException e) { e.printStackTrace(); } }
            if (connection != null) { try { connection.close(); } catch (SQLException e) { e.printStackTrace(); } }
        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<User>();
        Connection connection = getConnection();
        Statement st = null;
        ResultSet rs = null;
        String sql = "select * from users";
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                usersList.add(user);
            }
            System.out.println("Получены все пользователи");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (st != null) { try { st.close(); } catch (SQLException e) { e.printStackTrace(); } }
            if (connection != null) { try { connection.close(); } catch (SQLException e) { e.printStackTrace(); } }
        }
        return usersList;
    }

    public void cleanUsersTable() {
        Connection connection = getConnection();
        PreparedStatement ps = null;
        String sql = "delete from users";
        try {
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (ps != null) { try { ps.close(); } catch (SQLException e) { e.printStackTrace(); } }
            if (connection != null) { try { connection.close(); } catch (SQLException e) { e.printStackTrace(); } }
        }
    }
}
