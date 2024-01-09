module cw.fitnessclub {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    exports cw.fitnessclub;
    opens cw.fitnessclub to javafx.fxml;
    exports cw.fitnessclub.ControllerClasses;
    opens cw.fitnessclub.ControllerClasses to javafx.fxml;
    exports cw.fitnessclub.TableClasses;
    opens cw.fitnessclub.TableClasses to javafx.fxml;
    exports cw.fitnessclub.ControllerClasses.TableControllerClasses;
    opens cw.fitnessclub.ControllerClasses.TableControllerClasses to javafx.fxml;
    exports cw.fitnessclub.ControllerClasses.AddControllerClasses;
    opens cw.fitnessclub.ControllerClasses.AddControllerClasses to javafx.fxml;

}