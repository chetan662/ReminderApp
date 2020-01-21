package reminder;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import java.util.ArrayList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class reminder extends Application {
    public int counter = 0;
    public void start(Stage primaryStage) {
        ArrayList<String> remindersArr = new ArrayList<>();
        ArrayList<Integer> hoursArr = new ArrayList<>();
        ArrayList<Integer> minutesArr = new ArrayList<>();
        primaryStage.setTitle("Home");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid, 888, 500);
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        Button addButton = new Button("+");
        Button confirmButton = new Button("OK");
        TextField reminderIn = new TextField("reminder");
        Label errors = new Label("");
        TextField hour = new TextField();
        TextField minute = new TextField();
        Text reminders = new Text("");
        Text colon = new Text(":");
        Text clock = new Text("");
        reminderIn.setPrefWidth(400);
        hour.setPrefWidth(40);
        minute.setPrefWidth(40);
        grid.add(clock, 0, 0);
        grid.add(errors, 0, 2);
        grid.add(reminderIn, 0, 1);
        grid.add(hour, 1, 1);
        grid.add(colon, 2, 1);
        grid.add(minute, 3, 1);
        grid.add(addButton, 4, 1);
        grid.add(reminders, 0, 3);
        Timer timer = new Timer();
        TimerTask repet = new TimerTask() {
            
            public void run() {
                String text = "";
                long x = System.currentTimeMillis();
                for (int i = 0; i < counter; i++) {
                    if (remindersArr.size() > 0) {
                        text = String.format("%s%s at %02d:%02d%n", text, remindersArr.get(i), hoursArr.get(i), minutesArr.get(i));
                        if ((int) (x/(1000*60*60)) % 24 + 7 == hoursArr.get(i) && (int) (x/(1000*60)) % 60 == minutesArr.get(i)) {
                            String musicFile = "sound.mp3";
                            Media sound = new Media(new File(musicFile).toURI().toString());
                            MediaPlayer mediaPlayer = new MediaPlayer(sound);
                            mediaPlayer.play();
                            remindersArr.remove(i);
                            hoursArr.remove(i);
                            minutesArr.remove(i);
                            System.out.println("REMINDER");
                        }
                        else {
                            text = "";
                        }
                    }
                }
                reminders.setText(text);
                clock.setText(String.format("%02d:%02d:%02d", (int) (x/(1000*60*60)) % 24 + 7, (int) (x/(1000*60)) % 60, (int) (x/1000) % 60));
            }
        };
        timer.schedule(repet, 1000, 1000);
        primaryStage.setScene(scene);
        primaryStage.show();
        addButton.setOnAction((ActionEvent event) -> {
            if (reminderIn.getText().equals("") || hour.getText().equals("") || minute.getText().equals("") ) {
                errors.setText("Fill out all the boxes please");
            }
            else if (Integer.parseInt(hour.getText()) > 23) {
                errors.setText("Hour out of range");
            }
            else if (Integer.parseInt(minute.getText()) > 59) {
                errors.setText("Minute out of range");
            }
            else {
                remindersArr.add(reminderIn.getText());
                hoursArr.add(Integer.parseInt(hour.getText()));
                minutesArr.add(Integer.parseInt(minute.getText()));
                errors.setText("");
                counter++;
            }
            primaryStage.setScene(scene);
            primaryStage.show();
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
}