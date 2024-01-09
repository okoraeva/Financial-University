package cw.fitnessclub.ControllerClasses.AddControllerClasses;

import cw.fitnessclub.ControllerClasses.AbstractController;
import cw.fitnessclub.FunctionalClasses.Const;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Класс AddClientController представляет контроллер для добавления нового клиента.
 * Отвечает взаимодействие с пользователем на сцене добавления нового клиента, а также за обработку событий на данной сцене.
 */
public class AddClientController extends AbstractController {

    @FXML
    private Label loginLabel;

    @FXML
    private TextField clientNameField;
    @FXML
    private TextField clientEmailField;
    @FXML
    private TextField clientPhoneField;

    /**
     * Обработчик события перехода на главное меню.
     * Осуществляет переход на главное меню при нажатии кнопки "Выход".
     *
     * @param event событие нажатия кнопки
     * @throws IOException если возникла ошибка при загрузке главного меню
     */
    @FXML
    void toMainMenu(ActionEvent event) throws IOException {
        Const.ReplaceScene(event, "MainMenuView.fxml", getDataBase());
    }

    /**
     * Обработчик события добавления нового клиента.
     * Осуществляет добавление нового клиента в базу данных при нажатии кнопки "Добавить" если все соответсвующеие стобцы заполненны верно.
     *
     * @param event событие нажатия кнопки
     * @throws IOException если возникла ошибка при добавлении нового клиента
     */
    @FXML
    void addNewClient(ActionEvent event) throws IOException {
        String clientName = clientNameField.getText().trim();

        if (clientName.isEmpty()) {
            System.out.println("Имя не указано");
            return;
        }

        String clientEmail = clientEmailField.getText().trim();
        if (clientEmail.isEmpty()) {
            System.out.println("Почта не указана");
            return;
        }

        String clientPhone = clientPhoneField.getText().trim();
        if (clientPhone.isEmpty()) {
            System.out.println("Телефон не указан");
            return;
        }

        String update = "INSERT INTO clients (client_name, client_email, client_phone) VALUES (?, ?, ?)";

        try {
            PreparedStatement statement = getDataBase().getPrepareStatement(update);
            statement.setString(1, clientName);
            statement.setString(2, clientEmail);
            statement.setString(3, clientPhone);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении нового клиента");
        }

        toMainMenu(event);
    }

    /**
     * Инициализация контроллера при запуске.
     * Отображает имя пользователя.
     */
    @Override
    public void start() {
        loginLabel.setText(getDataBase().USER_LOGIN);
    }

}