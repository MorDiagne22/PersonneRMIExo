package cours;

import cours.utils.LoadView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application
{

    @Override
    public void start(Stage primaryStage) {
        try {
            LoadView.showView("Test","FormPersonne.fxml",1);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
