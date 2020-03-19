import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Bassey Oddy
 */
public class FillTutorial extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        FillProgressIndicator fillIndicator = new FillProgressIndicator();
       
        StackPane root = new StackPane();
        root.getChildren().add(fillIndicator);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        new WorkerThread(fillIndicator).start();
    }
    public static void main(String[] args) {
        launch(args);
    }
    //Thread thats adds a progress status to the ring indicator.
    class WorkerThread extends Thread{
        
        FillProgressIndicator fpi;
        int progress = 0;

        public WorkerThread(FillProgressIndicator fpi) {
            this.fpi = fpi;
        }

        @Override
        public void run() {
            while(true){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(RingTutorial.class.getName()).log(Level.SEVERE, null, ex);
                }
                Platform.runLater(()->{
                    fpi.setProgress(progress);
                });
                
                progress+=1;

                if(progress > 100){
                    break;
                }
            }
        }
    }
}
