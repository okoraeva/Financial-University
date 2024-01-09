package cw.fitnessclub.ControllerClasses.TableControllerClasses;

import cw.fitnessclub.ControllerClasses.AbstractController;
import cw.fitnessclub.FunctionalClasses.Const;
import cw.fitnessclub.TableClasses.Class;
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
 * Класс ClassTableController представляет контроллер для отображения и управления таблицей групповых занятий.
 * Отвечает за обработку событий на сцене отображения таблиц групповых занятий фитнес клуба и взаимодействие с пользователем.
 */
public class ClassTableController extends AbstractController {

    @FXML
    private Label loginLabel;

    @FXML
    private TextField nameSearchField;
    @FXML
    private TextField employeeSearchField;
    @FXML
    private DatePicker searchDatePicker;

    @FXML
    private TableView<Class> classTable;

    @FXML
    private TableColumn<Class, Integer> columnClassID;
    @FXML
    private TableColumn<Class, String> columnClassName;
    @FXML
    private TableColumn<Class, String> columnEmployeeName;
    @FXML
    private TableColumn<Class, Date> columnClassDate;
    @FXML
    private TableColumn<Class, Time> columnClassTime;

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
     * Обработчик события добавления нового группового занятия.
     * Осуществляет переход на окно добавления нового группового занятия при нажатии кнопки.
     *
     * @param event событие нажатия кнопки
     * @throws IOException если возникла ошибка при загрузке окна добавления класса
     */
    @FXML
    void toAddNewClass(ActionEvent event) throws IOException {
        Const.ReplaceScene(event, "AddViews/AddClassView.fxml", getDataBase());
    }

    /**
     * Обновляет данные в таблице групповых занятий.
     * Извлекает данные из базы данных и обновляет таблицу групповых занятий с учетом фильтров поиска.
     *
     * @param event событие обновления данных
     */
    @FXML
    void refreshData(ActionEvent event) {
        ObservableList<Class> classList = FXCollections.observableArrayList();

        String select = "SELECT * FROM classes c " +
                "JOIN employees e ON c.employee_id = e.employee_id " +
                "WHERE c.class_name LIKE ? " +
                "AND e.employee_name LIKE ? " +
                "AND c.class_date LIKE ?;";

        try {
            PreparedStatement statement = getDataBase().getPrepareStatement(select);
            statement.setString(1, "%" + nameSearchField.getText().trim() + "%");
            statement.setString(2, "%" + employeeSearchField.getText().trim() + "%");

            String searchDate = "";
            if (searchDatePicker.getValue() != null) {
                searchDate = searchDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
            statement.setString(3, "%" + searchDate + "%");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                classList.add(new Class(resultSet));
            }
        } catch (Exception e) {
            System.out.println("Ошибка при получении данных");
        }

        classTable.setItems(classList);
    }

    /**
     * Метод, вызываемый при запуске контроллера.
     * Устанавливает соединение с базой данных, отображает имя пользователя и настраивает столбцы таблицы классов.
     */
    @Override
    public void start() {
        loginLabel.setText(getDataBase().USER_LOGIN);

        columnClassID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnClassName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnEmployeeName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        columnClassDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnClassTime.setCellValueFactory(new PropertyValueFactory<>("time"));

        refreshData(null);
    }

}