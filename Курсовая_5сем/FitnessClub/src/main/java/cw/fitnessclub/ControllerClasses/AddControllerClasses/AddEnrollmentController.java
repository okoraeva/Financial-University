package cw.fitnessclub.ControllerClasses.AddControllerClasses;

import cw.fitnessclub.ControllerClasses.AbstractController;
import cw.fitnessclub.FunctionalClasses.Const;
import cw.fitnessclub.TableClasses.Class;
import cw.fitnessclub.TableClasses.Client;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.*;
import java.time.format.DateTimeFormatter;

/**
 * Класс AddEnrollmentController представляет контроллер для сцены добавления записи на существующее групповое занятие.
 * Отвечает за взаимодействие с пользователем на сцене добавления новоой записи на занятия в фитннес клубе, а также за обработку событий на данной сцене.
 */
public class AddEnrollmentController extends AbstractController {

    boolean allNodesIsVisible = false;

    String enrollmentDate;

    @FXML
    private Label loginLabel;

    @FXML
    private DatePicker enrollmentDatePicker;

    @FXML
    private TextField classNameSearchField;
    @FXML
    private TextField clientNameSearchField;

    @FXML
    private TableView<Class> classTable;

    @FXML
    private TableColumn<Class, String> columnClassName;
    @FXML
    private TableColumn<Class, Time> columnClassTime;

    @FXML
    private TableView<Client> clientTable;

    @FXML
    private TableColumn<Client, Integer> columnClientID;
    @FXML
    private TableColumn<Client, String> columnClientName;

    @FXML
    private Button addNewClassButton;
    @FXML
    private Button addNewClientButton;
    @FXML
    private Button addNewEnrollmentButton;

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
     * Метод toAddNewClient переходит на сцену добавления нового клиента при нажатии кнопки.
     *
     * @param event событие нажатия кнопки
     * @throws IOException если возникла ошибка ввода-вывода
     */
    @FXML
    void toAddNewClient(ActionEvent event) throws IOException {
        Const.ReplaceScene(event, "AddViews/AddClientView.fxml", getDataBase());
    }

    /**
     * Метод toAddNewClass переходит на сцену добавления нового занятия при нажатии кнопки.
     *
     * @param event событие нажатия кнопки
     * @throws IOException если возникла ошибка ввода-вывода
     */
    @FXML
    void toAddNewClass(ActionEvent event) throws IOException {
        Const.ReplaceScene(event, "AddViews/AddClassView.fxml", getDataBase());
    }

    /**
     * Метод refreshAllData обновляет Таблицы на сцене при нажатии кнопки.
     *
     * При первом запуске, если выбранна дата занятия, Затем анимирует появление всех таблиц и кнопкок и полей для поиска по таблицам.
     * Запоминает выбранную дату и запиысвает в класс, которая в дальнейшем будет использоватся в других методах.
     * Обновляет данные о клиентах и занятиях в таблицах с учетам фильтров.
     *
     * @param event событие нажатия кнопки
     */
    @FXML
    void refreshAllData(ActionEvent event) {
        if (!allNodesIsVisible) {
            if (enrollmentDatePicker.getValue() == null) {
                System.out.println("Дата не выбрана");
                return;
            }
            animateFadeIn(classNameSearchField, clientNameSearchField, classTable, clientTable,
                    addNewClassButton, addNewClientButton, addNewEnrollmentButton);
        }
        enrollmentDate = enrollmentDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        refreshClientData();
        refreshClassData();
    }

    /**
     * Обновляет данные о клиентах на сцене добавления записи на занятия.
     * Получает и отображает данные о клиентах, соответствующих заданному критерию поиска.
     */
    void refreshClientData() {

        ObservableList<Client> clientList = FXCollections.observableArrayList();

        String select = "SELECT * FROM clients WHERE client_name LIKE ?;";

        try {
            PreparedStatement statement = getDataBase().getPrepareStatement(select);
            statement.setString(1, "%" + clientNameSearchField.getText().trim() + "%");

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
     * Обновляет данные о занятиях на сцене добавления записи на занятия.
     * Получает и отображает данные о занятиях, соответствующих заданным критериям поиска.
     */
    void refreshClassData() {
        ObservableList<Class> classList = FXCollections.observableArrayList();


        String select = "SELECT * FROM classes c JOIN employees e on c.employee_id=e.employee_id " +
                "WHERE class_name LIKE ? AND class_date = ?";

        try {
            PreparedStatement statement = getDataBase().getPrepareStatement(select);
            statement.setString(1, "%" + classNameSearchField.getText().trim() + "%");
            statement.setDate(2, Date.valueOf(enrollmentDate));

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                classList.add(new Class(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при получении данных");
        }

        classTable.setItems(classList);
    }

    /**
     * Обработчик события добавления новой записи на занятие.
     * Выполняет добавление новой записи на занятие в базу данных при нажатии кнопки "Добавить".
     *
     * @param event событие нажатия кнопки
     * @throws IOException если возникла ошибка ввода-вывода
     */
    @FXML
    void addNewEnrollment(ActionEvent event) throws IOException {

        int clientID;
        try {
            Client selectedEmployee = clientTable.getSelectionModel().getSelectedItem();
            clientID = selectedEmployee.getId();
        } catch (Exception e) {
            System.out.println("Клиент не выбран");
            return;
        }

        int classID;
        try {
            Class selectedEmployee = classTable.getSelectionModel().getSelectedItem();
            classID = selectedEmployee.getId();
        } catch (Exception e) {
            System.out.println("Занятие не выбрано");
            return;
        }

        String update = "INSERT INTO enrollments (class_id, client_id) VALUES (?, ?)";
        System.out.println(clientID + "" + classID);
        try {
            PreparedStatement statement = getDataBase().getPrepareStatement(update);
            statement.setInt(1, classID);
            statement.setInt(2, clientID);

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении новой записи");
        }

        toMainMenu(event);
    }

    /**
     * Метод для анимации появления элементов интерфейса.
     * Проигрывает анимацию появления элементов интерфейса при первом нажатии кнопки "Обновить".
     *
     * @param nodes элементы интерфейса, которые должны появиться
     */
    void animateFadeIn(Node... nodes) {

        for (Node node : nodes) {
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), node);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();
        }

        allNodesIsVisible = true;
    }

    /**
     * Метод, вызываемый при запуске контроллера.
     * Устанавливает текст для метки с логином пользователя.
     * Устанавливает значения для столбцов таблицы сотрудников.
     */
    @Override
    public void start() {
        loginLabel.setText(getDataBase().USER_LOGIN);

        columnClientID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnClientName.setCellValueFactory(new PropertyValueFactory<>("name"));

        columnClassName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnClassTime.setCellValueFactory(new PropertyValueFactory<>("time"));
    }
}