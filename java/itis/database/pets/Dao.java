package itis.database.pets;

import itis.database.pets.generator.Dataset;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static itis.database.pets.generator.Generator.*;
import static itis.database.pets.generator.Generator.getString;

public class Dao {
    
    private final Connection connection;

    public Dao(Connection connection) {
        this.connection = connection;
    }

    public void saveUsers() throws IOException, SQLException {
        String sql = "insert into usr(role, name, age, balance, city, rating, contacts) values (?,?,?,?,?,?,?)";
        for (int i = 0; i < 10_000; i++) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            String role = "выгульщик";
            if (getInt(2) == 0) role = "хозяин";
            String name = new Dataset("storage\\name").getNext();
            int age = getInt(100);
            BigInteger balance = getBigInt(BigInteger.valueOf(100_000_000));
            String city = new Dataset("storage\\city").getNext();
            int rating = getInt(101);
            String contacts = getPhone();

            preparedStatement.setString(1, role);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, age);
            preparedStatement.setBigDecimal(4, new BigDecimal(balance));
            preparedStatement.setString(5, city);
            preparedStatement.setInt(6, rating);
            preparedStatement.setString(7, contacts);

            preparedStatement.executeUpdate();
        }
    }

    public void saveTechSupportMessages() throws SQLException, IOException {
        String sql = "insert into techsupportmessage(user_id, employee_id, message_time, text) values (?,?,?,?)";
        for (int i = 0; i < 10000; i++) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(sql);

            int max = 14;
            max -= 12;
            int employee_id = (int) (Math.random() * ++max) + 12;
            preparedStatement.setInt(1, getInt(10_000) + 1);
            preparedStatement.setInt(2, employee_id);
            preparedStatement.setTimestamp(3, getTimestamp("2022-01-01 01:01:01", "2023-11-03 14:40:59"));
            preparedStatement.setString(4, getString("storage\\techsup"));

            preparedStatement.executeUpdate();
        }
    }

    public void savePets() throws SQLException, IOException {
        String sql = "insert into pet(name, species, age, photo, owner_id) values(?,?,?,?,?);";
        for (int i = 0; i < 10000; i++) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(sql);
            String new_sql = "select user_id from usr where role = 'хозяин';";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(new_sql);
            List<Integer> employer = new ArrayList<>();
            while (resultSet.next()) {
                employer.add(resultSet.getInt("user_id"));
            }
            int index = getInt(employer.size());
            int employer_id = employer.get(index);

            preparedStatement.setString(1, getString("storage\\petnames"));
            preparedStatement.setString(2, getString("storage\\species"));
            preparedStatement.setInt(3, getInt(25));
            preparedStatement.setString(4, getString("storage\\dogphoto"));
            preparedStatement.setInt(5, employer_id);

            preparedStatement.executeUpdate();
        }
    }

    public void saveContracts() throws SQLException, IOException {
        String sql = "insert into contract(conclusion_date, employee_id, employer_id, conditions) values (?,?,?,?)";
        for (int i = 0; i < 10000; i++) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(sql);

            String new_sql = "select user_id from usr where role = 'хозяин';";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(new_sql);
            List<Integer> employer = new ArrayList<>();
            while (resultSet.next()) {
                employer.add(resultSet.getInt("user_id"));
            }
            int index = getInt(employer.size());
            int employer_id = employer.get(index);

            new_sql = "select user_id from usr where role = 'выгульщик';";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(new_sql);
            List<Integer> employee = new ArrayList<>();
            while (resultSet.next()) {
                employee.add(resultSet.getInt("user_id"));
            }
            index = getInt(employee.size());
            int employee_id = employee.get(index);

            Timestamp conclusion_date = getTimestamp("2022-01-01 01:01:01", "2023-11-03 14:40:59");
            String conditions = getString("storage\\contract");

            preparedStatement.setTimestamp(1, conclusion_date);
            preparedStatement.setInt(2, employee_id);
            preparedStatement.setInt(3, employer_id);
            preparedStatement.setString(4, conditions);

            preparedStatement.executeUpdate();
        }
    }

    public void saveVacancies() throws SQLException, IOException {
        String extraSql = "select pet_id, owner_id from pet;";
        Statement extraStatement = connection.createStatement();
        ResultSet extraResultSet = extraStatement.executeQuery(extraSql);
        List<Integer> users = new ArrayList<>();
        List<Integer> pets = new ArrayList<>();
        while (extraResultSet.next()) {
            users.add(extraResultSet.getInt("owner_id"));
            pets.add(extraResultSet.getInt("pet_id"));
        }

        String sql = "insert into vacancies(description, duration, user_id, pet_id, place_id, publication_date, begin_date) values (?,?,?,?,?,?,?)";

        for (int i = 0; i < 10000; i++) {
            int index = getInt(users.size());

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, getString("storage\\vacDesc"));
            preparedStatement.setTimestamp(2, getTimestamp("2022-01-01 01:01:01", "2023-11-03 14:40:59"));
            preparedStatement.setInt(3, users.get(index));
            preparedStatement.setInt(4, pets.get(index));
            preparedStatement.setInt(5, getInt(23) + 1);
            preparedStatement.setTimestamp(6, getTimestamp("2022-01-01 01:01:01", "2023-11-03 14:40:59"));
            preparedStatement.setTimestamp(7, getTimestamp("2022-01-01 01:01:01", "2023-11-03 14:40:59"));

            preparedStatement.executeUpdate();
        }
    }

    public void savePosts() throws SQLException, IOException {
        String sql = "insert into post(title, post_text, publication_date) values (?,?,?)";

        for (int i = 0; i < 10000; i++) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(sql);

            preparedStatement.setString(1, getString("storage\\title"));
            preparedStatement.setString(2, getString("storage\\post"));
            preparedStatement.setTimestamp(3, getTimestamp("2022-01-01 01:01:01", "2023-11-03 14:40:59"));

            preparedStatement.executeUpdate();
        }
    }

    public void saveMessages() throws SQLException, IOException {
        String sql = "insert into messenger(message, sender_id, reciever_id, publication_date) values (?,?,?,?)";

        for (int i = 0; i < 10000; i++) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(sql);

            preparedStatement.setString(1, getString("storage\\message"));
            preparedStatement.setInt(2, getInt(10_000) + 1);
            preparedStatement.setInt(3, getInt(10_000) + 1);
            preparedStatement.setTimestamp(4, getTimestamp("2022-01-01 01:01:01", "2023-11-03 14:40:59"));

            preparedStatement.executeUpdate();
        }
    }

    public void saveComments() throws SQLException, IOException {
        String sql = "insert into comment(post_id, user_id, comment_text, publication_date) values (?,?,?,?)";

        for (int i = 0; i < 10000; i++) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(sql);

            preparedStatement.setInt(1, getInt(10_000) + 1);
            preparedStatement.setInt(2, getInt(10_000) + 1);
            preparedStatement.setString(3, getString("storage\\comment"));
            preparedStatement.setTimestamp(4, getTimestamp("2022-01-01 01:01:01", "2023-11-03 14:40:59"));

            preparedStatement.executeUpdate();
        }
    }

    public void saveResumes() throws SQLException, IOException {
        String sql = "insert into resume(description, photo, user_id, place_id, publication_date, salary) values(?,?,?,?,?,?);";
        for (int i = 0; i < 10000; i++) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(sql);
            String new_sql = "select user_id from usr where role = 'выгульщик';";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(new_sql);
            List<Integer> employer = new ArrayList<>();
            while (resultSet.next()) {
                employer.add(resultSet.getInt("user_id"));
            }
            int index = getInt(employer.size());
            int user_id = employer.get(index);

            preparedStatement.setString(1, getString("storage\\description"));
            preparedStatement.setString(2, getString("storage\\peoplephoto"));
            preparedStatement.setInt(3, user_id);
            preparedStatement.setInt(4, getInt(24));
            preparedStatement.setTimestamp(5, getTimestamp("2022-01-01 01:01:01", "2023-11-03 14:40:59"));
            preparedStatement.setInt(6, getInt(16_242, 30_000));
            preparedStatement.executeUpdate();
        }
    }

    public void saveReviews() throws SQLException, IOException {
        String sql = "insert into review(user_id, publication_date, massage_text) values(?,?,?);";
        for (int i = 0; i < 10000; i++) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(sql);
            String new_sql = "select user_id from usr where role = 'хозяин';";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(new_sql);
            List<Integer> employer = new ArrayList<>();
            while (resultSet.next()) {
                employer.add(resultSet.getInt("user_id"));
            }
            int index = getInt(employer.size());
            int user_id = employer.get(index);

            preparedStatement.setInt(1, user_id);
            preparedStatement.setTimestamp(2, getTimestamp("2022-01-01 01:01:01", "2023-11-03 14:40:59"));
            preparedStatement.setString(3, getString("storage\\review"));
            preparedStatement.executeUpdate();
        }
    }


}
