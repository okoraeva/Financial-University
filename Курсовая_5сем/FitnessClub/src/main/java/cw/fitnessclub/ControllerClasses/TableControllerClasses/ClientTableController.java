package cw.fitnessclub.ControllerClasses.TableControllerClasses;

import cw.fitnessclub.ControllerClasses.AbstractController;
import cw.fitnessclub.TableClasses.Client;
import cw.fitnessclub.FunctionalClasses.Const;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Класс ClientTableController представляет контроллер для отображения и управления таблицей клиентов.
 * Отвечает за обработку событий на сцене отображения таблиц клиентов фитнес клуба и взаимодействие с пользователем.
 */
public class ClientTableController extends AbstractController {

    @FXML
    private Label loginLabel;

    @FXML
    private TextField nameSearchField;

    @FXML
    private TableView<Client> clientTable;

    @FXML
    private TableColumn<Client, Integer> columnClientID;
    @FXML
    private TableColumn<Client, String> columnClientName;
    @FXML
    private TableColumn<Client, String> columnClientEmail;
    @FXML
    private TableColumn<Client, String> columnClientPhone;

    /**
     * Обработчик события перехода на главное меню.
     * Осуществляет переход на главное меню при нажатии кнопки "Назад".
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
     * Осуществляет переход на окно добавления нового клиента при нажатии кнопки.
     *
     * @param event событие нажатия кнопки
     * @throws IOException если возникла ошибка при загрузке окна добавления клиента
     */
    @FXML
    void toAddNewClient(ActionEvent event) throws IOException {
        Const.ReplaceScene(event, "AddViews/AddClientView.fxml", getDataBase());
    }

    /**
     * Обновляет данные в таблице клиентов.
     * Извлекает данные из базы данных и обновляет таблицу клиентов с учетом фильтра поиска по имени.
     *
     * @param event событие обновления данных
     */
    @FXML
    void refreshData(ActionEvent event) {
        ObservableList<Client> clientList = FXCollections.observableArrayList();

        String select = "SELECT * FROM clients WHERE client_name LIKE ?;";

        try {
            PreparedStatement statement = getDataBase().getPrepareStatement(select);
            statement.setString(1, "%" + nameSearchField.getText().trim() + "%");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                clientList.add(new Client(resultSet));
            }
        } catch (Exception e) {
            System.out.println("Ошибка при получении данных");
        }

        clientTable.setItems(clientList);
    }

    /**
     * Метод, вызываемый при запуске контроллера.
     * Устанавливает соединение с базой данных, отображает имя пользователя и настраивает столбцы таблицы клиентов.
     */
    @Override
    public void start() {
        loginLabel.setText(getDataBase().USER_LOGIN);

        columnClientID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnClientName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnClientEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnClientPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        refreshData(null);
    }

}