package fxtest;

import com.google.inject.Guice;
import com.google.inject.Injector;
import fxtest.ui.main.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception
    {
        setUserAgentStylesheet(STYLESHEET_CASPIAN);

        Injector injector = Guice.createInjector(new AppModule());

        MainView main = injector.getInstance(MainView.class);
        Scene scene = new Scene(main.getParent());

        stage.setTitle("Address Book");
        stage.setScene(scene);
        stage.show();
    }

    public static void main( String[] args )
    {
        launch(args);
    }

}
