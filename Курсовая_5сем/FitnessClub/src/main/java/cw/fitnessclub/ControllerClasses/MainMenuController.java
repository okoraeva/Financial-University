package cw.fitnessclub.ControllerClasses;

import cw.fitnessclub.FunctionalClasses.Const;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

/**
 * Класс MainMenuController является контроллером для главного меню приложения.
 * Он наследуется от абстрактного класса AbstractController и содержит методы для перехода на различные сцены.
 */
public class MainMenuController extends AbstractController {

    @FXML
    private Label loginLabel;

    /**
     * Метод, вызываемый при нажатии кнопки "Выход".
     * Он вызывает метод ReplaceScene из класса Const для перехода на сцену авторизации.
     *
     * @param event событие, вызвавшее нажатие кнопки
     * @throws IOException если возникла ошибка при загрузке FXML-файла
     */
    @FXML
    void toAuthorization(ActionEvent event) throws IOException {
        Const.ReplaceScene(event, "AuthorizationView.fxml", getDataBase());
    }

    /**
     * Метод, вызываемый при нажатии кнопки "Список клиентов".
     * Он вызывает метод ReplaceScene из класса Const для перехода на сцену с таблицей клиентов.
     *
     * @param event событие, вызвавшее нажатие кнопки
     * @throws IOException если возникла ошибка при загрузке FXML-файла
     */
    @FXML
    void toClients(ActionEvent event) throws IOException {
        Const.ReplaceScene(event, "TableViews/ClientTableView.fxml", getDataBase());
    }

    /**
     * Метод, вызываемый при нажатии кнопки "Список сотрудников".
     * Он вызывает метод ReplaceScene из класса Const для перехода на сцену с таблицей сотрудников.
     *
     * @param event событие, вызвавшее нажатие кнопки
     * @throws IOException если возникла ошибка при загрузке FXML-файла
     */
    @FXML
    void toEmployees(ActionEvent event) throws IOException {
        Const.ReplaceScene(event, "TableViews/EmployeeTableView.fxml", getDataBase());
    }

    /**
     * Метод, вызываемый при нажатии кнопки "Групповые занятия".
     * Он вызывает метод ReplaceScene из класса Const для перехода на сцену с таблицей занятий.
     *
     * @param event событие, вызвавшее нажатие кнопки
     * @throws IOException если возникла ошибка при загрузке FXML-файла
     */
    @FXML
    void toClasses(ActionEvent event) throws IOException {
        Const.ReplaceScene(event, "TableViews/ClassTableView.fxml", getDataBase());
    }

    /**
     * Метод, вызываемый при нажатии кнопки "Записи клиентов".
     * Он вызывает метод ReplaceScene из класса Const для перехода на сцену с таблицей записей.
     *
     * @param event событие, вызвавшее нажатие кнопки
     * @throws IOException если возникла ошибка при загрузке FXML-файла
     */
    @FXML
    void toEnrollments(ActionEvent event) throws IOException {
        Const.ReplaceScene(event, "TableViews/EnrollmentTableView.fxml", getDataBase());
    }

    /**
     * Переопределенный метод start() из абстрактного класса AbstractController.
     * Он устанавливает текст для метки loginLabel, отображающей имя пользователя.
     */
    @Override
    public void start() {
        loginLabel.setText(getDataBase().USER_LOGIN);
    }

}