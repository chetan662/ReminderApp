package reminder;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
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
import java.util.concurrent.TimeUnit;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class reminder extends Application {
    // whether or not the alarm is disabled
    public boolean disabled = false;
    public void start(Stage primaryStage) {
        // arrays for reminders and their respective times
        ArrayList<String> remindersArr = new ArrayList<>();
        ArrayList<Integer> hoursArr = new ArrayList<>();
        ArrayList<Integer> minutesArr = new ArrayList<>();
        // sets title and icon for window
        primaryStage.setTitle("Home");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        // sets up window
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid, 888, 500);
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        // creates all components
        Button addButton = new Button("+");
        Button disableButton = new Button("DND");
        TextField reminderIn = new TextField("");
        Label errors = new Label("");
        TextField hour = new TextField();
        TextField minute = new TextField();
        Text reminders = new Text("");
        Text colon = new Text(":");
        Text clock = new Text("");
        Text alert = new Text("");
        reminderIn.setPromptText("Reminder");
        hour.setPromptText("HR");
        minute.setPromptText("MN");
        // sets styles for some components
        reminderIn.setPrefWidth(400);
        hour.setPrefWidth(45);
        minute.setPrefWidth(45);
        addButton.setPrefWidth(60);
        disableButton.setPrefWidth(60);
        grid.setStyle("-fx-background-color: #444;");
        alert.setStyle("-fx-font: 32 arial;");
        clock.setStyle("-fx-font: 32 arial;");
        // adds all components to the screen
        grid.add(clock, 0, 0);
        grid.add(disableButton, 4, 0);
        grid.add(errors, 0, 2);
        grid.add(reminderIn, 0, 1);
        grid.add(hour, 1, 1);
        grid.add(colon, 2, 1);
        grid.add(minute, 3, 1);
        grid.add(addButton, 4, 1);
        grid.add(reminders, 0, 4);
        grid.add(alert, 0, 3);
        // new timer, basically runs all of this every second
        Timer timer = new Timer();
        TimerTask repet = new TimerTask() {
            public void run() {
                String text = "";
                long x = System.currentTimeMillis();
                // for every reminder that exists
                for (int i = 0; i < remindersArr.size(); i++) {
                    // if there is a reminder
                    if (remindersArr.size() > 0) {
                        // append the reminder and time to text
                        text = String.format("%s%s at %02d:%02d%n", text, remindersArr.get(i), hoursArr.get(i), minutesArr.get(i));
                        // if its the time
                        if ((int) (x/(1000*60*60)) % 24 + 19 == hoursArr.get(i) && (int) (x/(1000*60)) % 60 == minutesArr.get(i)) {
                            if (!disabled) {
                                // play the sound and set the alert text
                                String musicFile = "sound.mp3";
                                Media sound = new Media(new File(musicFile).toURI().toString());
                                MediaPlayer mediaPlayer = new MediaPlayer(sound);
                                mediaPlayer.play();
                                try {
                                    alert.setText(remindersArr.get(i));
                                    TimeUnit.SECONDS.sleep(5);
                                    alert.setText("");
                                }
                                catch (Exception e) {
                                    System.out.println(e);
                                }
                            }
                            // remove the reminder
                            remindersArr.remove(i);
                            hoursArr.remove(i);
                            minutesArr.remove(i);
                        }
                    }
                }
                reminders.setText(text);
                text = "";
                // prints out the time
                clock.setText(String.format("%02d:%02d:%02d", (int) (x/(1000*60*60)) % 24 + 19, (int) (x/(1000*60)) % 60, (int) (x/1000) % 60));
            }
        };
        timer.schedule(repet, 1000, 1000);
        primaryStage.setScene(scene);
        primaryStage.show();
        addButton.setOnAction((ActionEvent event) -> {
            // checks for errors
            if (reminderIn.getText().equals("") || hour.getText().equals("") || minute.getText().equals("") ) {
                errors.setText("Fill out all the boxes please");
            }
            else if (Integer.parseInt(hour.getText()) > 23) {
                errors.setText("Hour out of range");
            }
            else if (Integer.parseInt(minute.getText()) > 59) {
                errors.setText("Minute out of range");
            }
            // if no errors, adds the reminder to the reminder array
            else {
                remindersArr.add(reminderIn.getText());
                hoursArr.add(Integer.parseInt(hour.getText()));
                minutesArr.add(Integer.parseInt(minute.getText()));
                errors.setText("");
            }
            primaryStage.setScene(scene);
            primaryStage.show();
        });
        disableButton.setOnAction((ActionEvent event) -> {
            
            disabled = !disabled;
            if (disabled) {
                disableButton.setStyle("-fx-background-color: red");
            }
            else {
                disableButton.setStyle("-fx-background-color: white");
            }
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
}