package cw.fitnessclub.ControllerClasses.TableControllerClasses;

import cw.fitnessclub.ControllerClasses.AbstractController;
import cw.fitnessclub.FunctionalClasses.Const;
import cw.fitnessclub.TableClasses.Enrollment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.time.format.DateTimeFormatter;

/**
 * Класс EnrollmentTableController представляет контроллер для отображения и управления таблицей записей на занятия.
 * Отвечает за обработку событий на сцене отображения таблицы записей на занятия фитнес клуба и взаимодействие с пользователем.
 */
public class EnrollmentTableController extends AbstractController {

    @FXML
    private Label loginLabel;

    @FXML
    private TextField classSearchField;
    @FXML
    private TextField clientSearchField;
    @FXML
    private DatePicker datePickerSearch;

    @FXML
    private TableView<Enrollment> enrollmentTable;

    @FXML
    private TableColumn<Enrollment, Integer> columnEnrollmentID;
    @FXML
    private TableColumn<Enrollment, String> columnClassName;
    @FXML
    private TableColumn<Enrollment, String> columnClientName;
    @FXML
    private TableColumn<Enrollment, Date> columnClassDate;
    @FXML
    private TableColumn<Enrollment, Time> columnClassTime;

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
     * Обработчик события добавления новой записи на занятие.
     * Осуществляет переход на окно добавления новой записи на занятие при нажатии кнопки.
     *
     * @param event событие нажатия кнопки
     * @throws IOException если возникла ошибка при загрузке окна добавления записи на занятие
     */
    @FXML
    void toAddNewEnrollment(ActionEvent event) throws IOException {
        Const.ReplaceScene(event, "AddViews/AddEnrollmentView.fxml", getDataBase());
    }

    /**
     * Обновляет данные в таблице записей на занятия.
     * Извлекает данные из базы данных и обновляет таблицу записей на занятия с учетом фильтров поиска по названию занятия, имени клиента и дате.
     *
     * @param event событие обновления данных
     */
    @FXML
    void refreshData(ActionEvent event) {
        ObservableList<Enrollment> enrollmentList = FXCollections.observableArrayList();

        String select = "SELECT * FROM enrollments e " +
                "JOIN clients c ON c.client_id = e.client_id " +
                "JOIN classes cl ON e.class_id = cl.class_id " +
                "WHERE cl.class_name LIKE ? " +
                "AND c.client_name LIKE ? " +
                "AND cl.class_date LIKE ?";

        try {
            PreparedStatement statement = getDataBase().getPrepareStatement(select);
            statement.setString(1, "%" + classSearchField.getText().trim() + "%");
            statement.setString(2, "%" + clientSearchField.getText().trim() + "%");

            String datePickSearch = "";
            if (datePickerSearch.getValue() != null) {
                datePickSearch = datePickerSearch.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
            statement.setString(3, "%" + datePickSearch + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                enrollmentList.add(new Enrollment(resultSet));
            }

        } catch (Exception e) {
            System.out.println("Ошибка при получении данныхиз базы данных");
        }

        enrollmentTable.setItems(enrollmentList);
    }

    /**
     * Метод, вызываемый при запуске контроллера.
     * Устанавливает соединение с базой данных, отображает имя пользователя и настраивает столбцы таблицы записей на групповые занятия.
     */
    @Override
    public void start() {
        loginLabel.setText(getDataBase().USER_LOGIN);

        columnEnrollmentID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnClassName.setCellValueFactory(new PropertyValueFactory<>("className"));
        columnClientName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        columnClassDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnClassTime.setCellValueFactory(new PropertyValueFactory<>("time"));

        refreshData(null);
    }

}