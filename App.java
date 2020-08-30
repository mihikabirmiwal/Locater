import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application
{
    private static View myView;
    private static MainScene myMainScene;
    
    public App()
    {
   	 myMainScene = new MainScene();
   	 myView = new View();   	 
    }
    
    public static View getView()
    {
   	 return myView;  	 
    }
    public static MainScene getMainScene()
    {
   	 return myMainScene;
    }
    

    public static void main(String[] args)
    {
   	 launch(args);
    }

    public void start(Stage stage) throws Exception
    {
   	 System.out.println("Initializing app window...");
   	 myView.setStage(stage);
   	 System.out.println("Initialization has completed");
    }    
}
