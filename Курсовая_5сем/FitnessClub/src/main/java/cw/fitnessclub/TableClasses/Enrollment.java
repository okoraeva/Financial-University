package cw.fitnessclub.TableClasses;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

/**
 * Класс Enrollment представляет информацию о записи на занятие в фитнес-клубе.
 * Содержит поля для идентификатора, названия занятия, имени клиента, даты и времени проведения.
 */
public class Enrollment {
    private final int id;
    private final String className;
    private final String clientName;
    private final Date date;
    private final Time time;

    /**
     * Конструктор класса Enrollment.
     * Инициализирует параметры объектов Enrollment, которые получает из SQL запроса.
     * Необходим для заполнения таблиц.
     *
     * @param resultSet результат запроса к базе данных
     * @throws SQLException если возникла ошибка при работе с базой данных
     */
    public Enrollment(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt("enrollment_id");
        className = resultSet.getString("class_name");
        clientName = resultSet.getString("client_name");
        date = resultSet.getDate("class_date");
        time = resultSet.getTime("class_time");
    }

    public int getId() {
        return id;
    }


    public String getClassName() {
        return className;
    }

    public String getClientName() {
        return clientName;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }
}
