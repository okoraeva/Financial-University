package cw.fitnessclub.ControllerClasses.AddControllerClasses;

import cw.fitnessclub.ControllerClasses.AbstractController;
import cw.fitnessclub.FunctionalClasses.Const;
import cw.fitnessclub.TableClasses.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс AddClassController представляет контроллер для добавления нового занятия.
 * Отвечает за взаимодействие с пользователем на сцене добавления нового занятия, а также за обработку событий на данной сцене.
 */
public class AddClassController extends AbstractController {

    @FXML
    private Label loginLabel;

    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<Employee, String> columnEmployeeID;
    @FXML
    private TableColumn<Employee, String> columnEmployeeName;
    @FXML
    private TableColumn<Employee, String> columnEmployeePosition;

    @FXML
    private TextField employeeNameSearchField;
    @FXML
    private TextField employeePositionSearchField;

    @FXML
    private TextField classNameField;
    @FXML
    private DatePicker classDatePicker;
    @FXML
    private TextField classTimeField;

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
     * Обработчик события перехода к добавлению нового сотрудника.
     * Осуществляет переход к сцене добавления нового сотрудника при нажатии кнопки "Добавить сотрудника".
     *
     * @param event событие нажатия кнопки
     * @throws IOException если возникла ошибка при загрузке сцены добавления нового сотрудника
     */
    @FXML
    void toAddNewEmployee(ActionEvent event) throws IOException {
        Const.ReplaceScene(event, "AddViews/AddEmployeeView.fxml", getDataBase());
    }

    /**
     * Обработчик события обновления данных.
     * Осуществляет обновление данных в таблице сотрудников при нажатии кнопки "Обновить".
     *
     * @param event событие нажатия кнопки
     */
    @FXML
    void refreshData(ActionEvent event) {
        ObservableList<Employee> employeeList = FXCollections.observableArrayList();

        String select = "SELECT * FROM employees WHERE employee_name LIKE ? AND employee_position LIKE ?";

        try {
            PreparedStatement statement = getDataBase().getPrepareStatement(select);
            statement.setString(1, "%" + employeeNameSearchField.getText().trim() + "%");
            statement.setString(2, "%" + employeePositionSearchField.getText().trim() + "%");

            ResultSet reSet = statement.executeQuery();
            while (reSet.next()) {
                employeeList.add(new Employee(reSet));
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при получении данных");
        }

        employeeTable.setItems(employeeList);
    }

    /**
     * Обработчик события добавления нового занятия.
     * Осуществляет добавление нового занятия в базу данных при нажатии кнопки "Добавить", если все соответствующие поля заполнены верно.
     *
     * @param event событие нажатия кнопки
     * @throws IOException если возникла ошибка при загрузке главного меню
     */
    @FXML
    void addNewClass(ActionEvent event) throws IOException {

        int employeeID;
        try {
            Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
            employeeID = selectedEmployee.getId();
        } catch (Exception e) {
            System.out.println("Сотрудник не выбран");
            return;
        }

        String className = classNameField.getText().trim();
        if (className.isEmpty()) {
            System.out.println("Название не указано");
            return;
        }

        String classTime = classTimeField.getText().trim();
        try {
            LocalTime.parse(classTime, DateTimeFormatter.ofPattern("HH:mm:ss"));
        } catch (Exception e) {
            classTimeField.clear();
            System.out.println("Неверный формат времени. Введите время в формате ЧЧ:ММ:СС");
            return;
        }

        if (classDatePicker.getValue() == null) {
            System.out.println("Дата не указана");
            return;
        }
        String classDate = classDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        String update = "INSERT INTO classes (employee_id, class_name, class_date, class_time) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement statement = getDataBase().getPrepareStatement(update);
            statement.setInt(1, employeeID);
            statement.setString(2, className);
            statement.setDate(3, Date.valueOf(classDate));
            statement.setTime(4, Time.valueOf(classTime));

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении нового занятия");
        }

        toMainMenu(event);
    }

    /**
     * Метод, вызываемый при запуске контроллера.
     * Устанавливает текст для метки с логином пользователя.
     * Устанавливает значения для столбцов таблицы сотрудников.
     * Обновляет данные в таблице сотрудников.
     */
    @Override
    public void start() {
        loginLabel.setText(getDataBase().USER_LOGIN);

        columnEmployeeID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnEmployeeName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnEmployeePosition.setCellValueFactory(new PropertyValueFactory<>("position"));

        refreshData(null);
    }
}