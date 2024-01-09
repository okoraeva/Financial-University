package cw.fitnessclub.FunctionalClasses;

import cw.fitnessclub.ControllerClasses.AbstractController;
import cw.fitnessclub.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Класс Const содержит константы и методы, используемые в различных частях приложения.
 * Создан для перехода между сценами и использования констант при подключении к базае данных.
 */
public class Const {
    protected String dbHost = "localhost"; // Адрес хоста
    protected String dbPort = ":3306"; // Порт (двоеточие не убирать)
    protected String dbUser = "root"; // Имя пользователя для подключения к БД
    protected String dbPass = "root"; // Пароль
    protected String dbName = "/FitnessClubDB"; // Название базы данных

    /**
     * Заменяет текущую сцену на новую сцену, загруженную из указанного FXML-файла.
     *
     * @param event    событие, вызвавшее замену сцены
     * @param view     путь к FXML-файлу новой сцены
     * @param dataBase экземпляр класса DataBase
     * @throws IOException если возникла ошибка при загрузке FXML-файла
     */
    public static void ReplaceScene(ActionEvent event, String view, DataBase dataBase) throws IOException {
        // Переход на новую сцену
        FXMLLoader newLoader = new FXMLLoader(Main.class.getResource(view));
        Stage stage = getStage(event);
        Parent newRoot = newLoader.load();
        Scene newScene = new Scene(newRoot);
        stage.setScene(newScene);

        // Передача базы данных контроллеру новой сцены
        setDB(newLoader, dataBase);
        stage.show();
    }

    /**
     * Возвращает текущий Stage, связанный с событием.
     *
     * @param event событие, от которого нужно получить Stage
     * @return текущий Stage
     */
    public static Stage getStage(ActionEvent event) {
        return (Stage) ((Node) event.getSource()).getScene().getWindow();
    }

    /**
     * Устанавливает экземпляр класса DataBase в контроллер и вызывает его метод start().
     * Создан для передачи подключения к базе данных между сценами, а также вывода имени пользователя на главный экран
     *
     * @param loader   экземпляр FXMLLoader, связанный с новой сценой
     * @param dataBase экземпляр класса DataBase
     */
    public static void setDB(FXMLLoader loader, DataBase dataBase) {
        AbstractController controller = loader.getController();
        controller.setDataBase(dataBase);
        controller.start();
    }
}