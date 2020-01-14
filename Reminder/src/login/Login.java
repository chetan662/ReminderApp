package login;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javax.swing.JTextField;

public class Login extends Application {
    public void start(Stage primaryStage) {
        int counter = 0;
        int[][] remindersArray = new int[2][100];
        primaryStage.setTitle("Home");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid, 500, 500);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(5);
        Button addButton = new Button("+");
        TextField reminderIn = new TextField("reminder");
        TextField hour = new TextField();
        TextField minute = new TextField();
        Text reminders = new Text("");
        grid.add(reminderIn, 0, 0);
        grid.add(hour, 1, 0);
        grid.add(minute, 2, 0);
        grid.add(addButton, 2, 0);
        grid.add(reminders, 0, 4);
        primaryStage.setScene(scene);
        primaryStage.show();
        addButton.setOnAction((ActionEvent event) -> {
            System.out.println(remindersArray.length);
            reminders.setText(reminders.getText() + "\n" + reminderIn.getText() + " at " + hour.getText() + minute.getText());
            primaryStage.setScene(scene);
            primaryStage.show();
            counter++;
        });
        
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
