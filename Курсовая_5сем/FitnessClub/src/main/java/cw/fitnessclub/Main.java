package cw.fitnessclub;

import cw.fitnessclub.ControllerClasses.AbstractController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Загрузка FXML-файла с графическим интерфейсом
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AuthorizationView.fxml"));
        Parent root = loader.load();

        // Создание сцены и установка размеров окна
        Scene scene = new Scene(root, 900, 600);

        // Установка заголовка окна
        stage.setTitle("FITNESS CLUB");

        // Установка сцены в окне и запрет изменения размеров окна
        stage.setScene(scene);
        stage.setResizable(false);

        // Получение экземпляра контроллера и вызов метода start()
        AbstractController controller = loader.getController();
        controller.start();

        // Отображение окна
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}