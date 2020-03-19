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
public class RingTutorial extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        RingProgressIndicator ringIndicator = new RingProgressIndicator();
        ringIndicator.setRingWidth(150);
        ringIndicator.makeIndeterminate();
        
        StackPane root = new StackPane();
        root.getChildren().add(ringIndicator);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Ring Progress Indicator");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //execute progress thread.
        new WorkerThread(ringIndicator).start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    //Thread thats adds a progress status to the ring indicator.
    class WorkerThread extends Thread{
        
        RingProgressIndicator rpi;
        int progress = 0;

        public WorkerThread(RingProgressIndicator rpi) {
            this.rpi = rpi;
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
                    rpi.setProgress(progress);
                });
                
                progress+=1;

                if(progress > 100){
                    break;
                }
            }
        }
    }
}
