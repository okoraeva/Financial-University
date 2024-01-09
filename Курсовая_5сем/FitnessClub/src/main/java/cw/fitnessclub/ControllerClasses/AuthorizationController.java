package cw.fitnessclub.ControllerClasses;

import cw.fitnessclub.FunctionalClasses.Const;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.io.IOException;

/**
 * Класс AuthorizationController представляет контроллер для окна авторизации.
 * Отвечает за обработку событий и взаимодействие с пользователем.
 */
public class AuthorizationController extends AbstractController {

    @FXML
    private TextField LoginField;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Label logInErrMessage;

    /**
     * Обработчик события нажатия кнопки "Войти".
     * Проверяет введенные данные пользователя и осуществляет переход на главное меню в случае успешной авторизации.
     * Выводит сообщение об ошибке в случае неверного логина или пароля.
     *
     * @param event событие нажатия кнопки "Войти"
     * @throws IOException если возникла ошибка при загрузке главного меню
     */
    @FXML
    void logIn(ActionEvent event) throws IOException {

        String login = LoginField.getText().trim();

        getDataBase().USER_LOGIN = login;

        int passwordHash = PasswordField.getText().trim().hashCode();

        if (getDataBase().isUserInBase(login, passwordHash))
            Const.ReplaceScene(event, "MainMenuView.fxml", getDataBase());
        else {
            System.out.println("Неверный логин или пароль");
            LoginField.clear();
            PasswordField.clear();
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), logInErrMessage);
            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(0);
            fadeTransition.play();
        }
    }

    /**
     * Метод, вызываемый при запуске контроллера.
     * Устанавливает соединение с базой данных.
     */
    @Override
    public void start() {
        getDataBase().setConnection();
    }


}