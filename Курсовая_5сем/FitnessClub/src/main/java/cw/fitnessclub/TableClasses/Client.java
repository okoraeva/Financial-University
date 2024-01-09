package cw.fitnessclub.TableClasses;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс Client представляет информацию о клиенте фитнес-клуба.
 * Содержит поля для идентификатора, имени, электронной почты и телефона клиента.
 */
public class Client {
    private final int id;
    private final String name;
    private final String email;
    private final String phone;

    /**
     * Конструктор класса Client.
     * Инициализирует параметры объектов Client, которые получает из SQL запроса.
     * Необходим для заполнения таблиц.
     *
     * @param resultSet результат запроса к базе данных
     * @throws SQLException если возникла ошибка при работе с базой данных
     */
    public Client(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt("client_id");
        name = resultSet.getString("client_name");
        email = resultSet.getString("client_email");
        phone = resultSet.getString("client_phone");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
