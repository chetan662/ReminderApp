package login;

import com.sun.javafx.tk.Toolkit;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
public class Login extends Application {
    
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Home");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid, 500, 500);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(5);
        Button gotoLogin = new Button("Set reminder for 5 seconds");
        grid.add(gotoLogin, 0, 0);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        
        gotoLogin.setOnAction((ActionEvent event) -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("5 secs done");
            primaryStage.setTitle("Login");
            grid.getChildren().remove(gotoLogin);
        });
    }
    
    public static void main(String[] args) {
        
//        try{
//            //Obtain only one instance of the SystemTray object
//            SystemTray tray = SystemTray.getSystemTray();
//
//            // If you want to create an icon in the system tray to preview 
//            try {
//                Image retValue = Toolkit.getToolkit().getImage(ClassLoader.getSystemResource("icon.png"));
//                
//            }
//            catch (Exception e) {
//                
//            }
////            Image image = Toolkit.getDefaultToolkit().createImage("some-icon.png");
//            //Alternative (if the icon is on the classpath):
//            //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));
//
//            TrayIcon trayIcon = new TrayIcon(image, "Java AWT Tray Demo");
//            //Let the system resize the image if needed
//            trayIcon.setImageAutoSize(true);
//            //Set tooltip text for the tray icon
//            trayIcon.setToolTip("System tray icon demo");
//            tray.add(trayIcon);
//
//            // Display info notification:
//            trayIcon.displayMessage("Hello, World", "Java Notification Demo", MessageType.INFO);
//            // Error:
//            // trayIcon.displayMessage("Hello, World", "Java Notification Demo", MessageType.ERROR);
//            // Warning:
//            // trayIcon.displayMessage("Hello, World", "Java Notification Demo", MessageType.WARNING);
//        }catch(Exception ex){
//            System.err.print(ex);
//        }
        launch(args);
    }
    
}
