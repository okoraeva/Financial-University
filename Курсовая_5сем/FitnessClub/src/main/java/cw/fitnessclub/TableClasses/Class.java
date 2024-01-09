package cw.fitnessclub.TableClasses;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

/**
 * Класс Class представляет информацию о занятии в фитнес-клубе.
 * Содержит поля для идентификатора, названия занятия, имени сотрудника, даты и времени проведения.
 */
public class Class {
    private final int id;
    private final String name;
    private final String employeeName;
    private final Date date;
    private final Time time;

    /**
     * Конструктор класса Class.
     * Инициализирует параметры объектов Class которые получает из SQL запроса
     * Необходим для заполнения таблиц
     *
     * @param resultSet результат запроса к базе данных
     * @throws SQLException если возникла ошибка при работе с базой данных
     */
    public Class(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt("class_id");
        name = resultSet.getString("class_name");
        employeeName = resultSet.getString("employee_name");
        date = resultSet.getDate("class_date");
        time = resultSet.getTime("class_time");
    }

    public int getId() {
        return id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }
}
