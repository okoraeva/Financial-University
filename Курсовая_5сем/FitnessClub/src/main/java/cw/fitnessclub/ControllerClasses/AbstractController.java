package cw.fitnessclub.ControllerClasses;

import cw.fitnessclub.FunctionalClasses.DataBase;

/**
 * Базовый абстрактный класс контроллера.
 * Содержит общие методы и свойства для всех контроллеров.
 */
public abstract class AbstractController {

    private DataBase database = new DataBase();

    /**
     * Получает экземпляр класса DataBase.
     *
     * @return экземпляр класса DataBase
     */
    public DataBase getDataBase() {
        return database;
    }

    /**
     * Устанавливает экземпляр класса DataBase.
     *
     * @param database экземпляр класса DataBase
     */
    public void setDataBase(DataBase database) {
        this.database = database;
    }

    /**
     * Метод, который будет вызван при запуске контроллера.
     * Может быть переопределен в дочерних классах.
     */
    public void start() {
        // Пустая реализация
    }
}