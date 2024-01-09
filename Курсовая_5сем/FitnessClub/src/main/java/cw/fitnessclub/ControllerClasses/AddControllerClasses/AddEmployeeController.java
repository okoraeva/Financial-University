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
 * Класс AddEmployeeController представляет контроллер для добавления нового сотрудника.
 * Отвечает за взаимодействие с пользователем на сцене добавления нового сотрудника, а также за обработку событий на данной сцене.
 */
public class AddEmployeeController extends AbstractController {

    @FXML
    private Label loginLabel;

    @FXML
    private TextField employeeNameField;
    @FXML
    private TextField employeePositionField;
    @FXML
    private TextField employeeEmailField;
    @FXML
    private TextField employeePhoneField;
    @FXML
    private TextField employeeAddressField;
    @FXML
    private TextField employeeSalaryField;

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
     * Обработчик события добавления нового сотрудника.
     * Осуществляет добавление нового сотрудника в базу данных при нажатии кнопки "Добавить", если все соответствующие поля заполнены верно.
     *
     * @param event событие нажатия кнопки
     * @throws IOException если возникла ошибка при загрузке главного меню
     */
    @FXML
    void addNewEmployee(ActionEvent event) throws IOException {
        String employeeName = employeeNameField.getText().trim();

        if (employeeName.isEmpty()) {
            System.out.println("Имя не указано");
            return;
        }

        String employeePosition = employeePositionField.getText().trim();

        if (employeePosition.isEmpty()) {
            System.out.println("Должность не указана");
            return;
        }

        String employeeEmail = employeeEmailField.getText().trim();
        if (employeeEmail.isEmpty()) {
            System.out.println("Почта не указана");
            return;
        }

        String employeePhone = employeePhoneField.getText().trim();
        if (employeePhone.isEmpty()) {
            System.out.println("Телефон не указан");
            return;
        }

        String employeeAddress = employeeAddressField.getText().trim();
        if (employeeAddress.isEmpty()) {
            System.out.println("Адрес не указан");
            return;
        }

        int employeeSalary;
        try {
            employeeSalary = Integer.parseInt(employeeSalaryField.getText().trim());
        } catch (Exception e) {
            System.out.println("Неверно указана зарплата");
            employeeSalaryField.clear();
            return;
        }

        String update = "INSERT INTO employees " +
                "(employee_name, employee_position, employee_email, employee_phone, employee_address, employee_salary) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = getDataBase().getPrepareStatement(update);
            statement.setString(1, employeeName);
            statement.setString(2, employeePosition);
            statement.setString(3, employeeEmail);
            statement.setString(4, employeePhone);
            statement.setString(5, employeeAddress);
            statement.setInt(6, employeeSalary);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлениинового сотрудника");
        }

        toMainMenu(event);
    }

    /**
     * Метод, который будет вызван при запуске контроллера.
     * Устанавливает логин пользователя на сцене добавления нового сотрудника.
     */
    @Override
    public void start() {
        loginLabel.setText(getDataBase().USER_LOGIN);
    }

}