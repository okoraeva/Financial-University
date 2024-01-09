package cw.fitnessclub.FunctionalClasses;

import java.sql.*;

/**
 * Класс DataBase предоставляет методы для работы с базой данных.
 * Наследуется от класса Const, чтобы использовать его константы для настройки подключения к базе данных.
 */
public class DataBase extends Const {
    private Connection connection;

    public String USER_LOGIN;

    private final String URL = "jdbc:mysql://" + dbHost + dbPort + dbName;

    /**
     * Устанавливает соединение с базой данных.
     */
    public void setConnection() {
        try {
            connection = DriverManager.getConnection(URL, dbUser, dbPass);
        } catch (SQLException e) {
            System.out.println("Ошибка подключения к серверу");
        }
    }

    /**
     * Проверяет, есть ли пользователь с заданным логином и хэшем пароля в базе данных.
     *
     * @param login        логин пользователя
     * @param passwordHash хэш пароля пользователя
     * @return true, если пользователь найден в базе данных, иначе false
     */
    public boolean isUserInBase(String login, int passwordHash) {
        String select = "SELECT * FROM users WHERE user_name = '" + login + "';";
        try {
            ResultSet resultSet = getResultSet(select);

            if (!resultSet.next()) {
                return false;
            }

            int userSalt = resultSet.getInt("user_salt");
            int userHash = resultSet.getInt("user_hash");
            int passwordSaltHash = Integer.toHexString(passwordHash + userSalt).hashCode();

            return (passwordSaltHash == userHash);

        } catch (SQLException e) {
            System.out.println("Ошибка получения результатов из базы данных при попытке войти");
            return false;
        }
    }

    /**
     * Выполняет SQL-запрос и возвращает результат в виде ResultSet.
     *
     * @param select SQL-запрос
     * @return ResultSet с результатами запроса
     */
    public ResultSet getResultSet(String select) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(select);
        } catch (SQLException e) {
            System.out.println("Ошибка при получении данных");
            return null;
        }
    }

    /**
     * Обновляет данные в базе данных с помощью SQL-запроса.
     *
     * @param update SQL-запрос для обновления данных
     */
    public void updateData(String update) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(update);
        } catch (SQLException e) {
            System.out.println("Ошибка при обновлении данных");
        }
    }

    /**
     * Создает подготовленное выражение для выполнения SQL-запроса.
     *
     * @param select SQL-запрос
     * @return подготовленное выражение
     */
    public PreparedStatement getPrepareStatement(String select) {
        try {
            return connection.prepareStatement(select);
        } catch (SQLException e) {
            System.out.println("Ошибка получения результатов из базы данных");
            return null;
        }
    }
}