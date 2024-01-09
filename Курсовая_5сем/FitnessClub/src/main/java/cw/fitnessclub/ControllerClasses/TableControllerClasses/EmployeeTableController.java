package cw.fitnessclub.ControllerClasses.TableControllerClasses;

import cw.fitnessclub.ControllerClasses.AbstractController;
import cw.fitnessclub.FunctionalClasses.Const;
import cw.fitnessclub.TableClasses.Employee;
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
 * Класс EmployeeTableController представляет контроллер для отображения и управления таблицей сотрудников.
 * Отвечает за обработку событий на сцене отображения таблицы сотрудников фитнес клуба и взаимодействие с пользователем.
 */
public class EmployeeTableController extends AbstractController {

    @FXML
    private Label loginLabel;

    @FXML
    private TextField employeeNameSearchField;

    @FXML
    private TableView<Employee> employeeTable;

    @FXML
    private TableColumn<Employee, Integer> columnEmployeeID;
    @FXML
    private TableColumn<Employee, String> columnEmployeePosition;
    @FXML
    private TableColumn<Employee, String> columnEmployeeName;
    @FXML
    private TableColumn<Employee, String> columnEmployeeEmail;
    @FXML
    private TableColumn<Employee, String> columnEmployeePhone;
    @FXML
    private TableColumn<Employee, String> columnEmployeeAddress;
    @FXML
    private TableColumn<Employee, Integer> columnEmployeeSalary;

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
     * Обработчик события добавления нового сотрудника.
     * Осуществляет переход на окно добавления нового сотрудника при нажатии кнопки.
     *
     * @param event событие нажатия кнопки
     * @throws IOException если возникла ошибка при загрузке окна добавления сотрудника
     */
    @FXML
    void toAddNewEmployee(ActionEvent event) throws IOException {
        Const.ReplaceScene(event, "AddViews/AddEmployeeView.fxml", getDataBase());
    }

    /**
     * Обновляет данные в таблице сотрудников.
     * Извлекает данные из базы данных и обновляет таблицу сотрудников с учетом фильтра поиска по имени.
     *
     * @param event событие обновления данных
     */
    @FXML
    void refreshData(ActionEvent event) {
        ObservableList<Employee> employeeList = FXCollections.observableArrayList();

        String select = "SELECT * FROM employees WHERE employee_name LIKE ?;";

        try {
            PreparedStatement statement = getDataBase().getPrepareStatement(select);
            statement.setString(1, "%" + employeeNameSearchField.getText().trim() + "%");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employeeList.add(new Employee(resultSet));
            }

        } catch (Exception e) {
            System.out.println("Ошибка при получении данных");
        }

        employeeTable.setItems(employeeList);
    }

    /**
     * Метод, вызываемый при запуске контроллера.
     * Устанавливает соединение с базой данных, отображает имя пользователя и настраивает столбцы таблицы сотрудников.
     */
    @Override
    public void start() {
        loginLabel.setText(getDataBase().USER_LOGIN);

        columnEmployeeID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnEmployeeName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnEmployeePosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        columnEmployeeEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnEmployeePhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        columnEmployeeAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        columnEmployeeSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        refreshData(null);
    }
}