package cw.fitnessclub.TableClasses;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс Employee представляет информацию о сотруднике фитнес-клуба.
 * Содержит поля для идентификатора, имени, должности, телефона, электронной почты, адреса и зарплаты сотрудника.
 */
public class Employee {
    private final int id;
    private final String name;
    private final String position;
    private final String phone;
    private final String email;
    private final String address;
    private final int salary;

    /**
     * Конструктор класса Employee.
     * Инициализирует параметры объектов Employee, которые получает из SQL запроса.
     * Необходим для заполнения таблиц.
     *
     * @param resultSet результат запроса к базе данных
     * @throws SQLException если возникла ошибка при работе с базой данных
     */
    public Employee(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt("employee_id");
        name = resultSet.getString("employee_name");
        position = resultSet.getString("employee_position");
        email = resultSet.getString("employee_email");
        phone = resultSet.getString("employee_phone");
        address = resultSet.getString("employee_address");
        salary = resultSet.getInt("employee_salary");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {

        return phone;
    }

    public String getAddress() {
        return address;
    }

    public int getSalary() {
        return salary;
    }
}
