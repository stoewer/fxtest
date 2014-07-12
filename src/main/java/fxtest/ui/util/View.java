package fxtest.ui.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import com.google.inject.Injector;
import javax.inject.Inject;
import java.io.IOException;
import java.net.URL;

public class View {

    private URL styleURL;
    private FXMLLoader loader;

    @Inject
    private Injector injector;

    public View() {
        styleURL = generateStyleURL();

        loader = new FXMLLoader(generateViewURL());
        loader.setControllerFactory(cls -> injector.getInstance(cls));
    }

    public Parent getScene() throws IOException {
        Parent p = loader.load();
        if (styleURL != null) {
            p.getStylesheets().add(styleURL.toExternalForm());
        }
        return p;
    }

    private URL generateViewURL() {
        StringBuilder name = new StringBuilder(100)
                .append('/')
                .append(getClass().getCanonicalName().replace('.', '/'))
                .append(".fxml");
        return getClass().getResource(name.toString());
    }

    private URL generateStyleURL() {
        StringBuilder name = new StringBuilder(100)
                .append('/')
                .append(getClass().getCanonicalName().replace('.', '/'))
                .append(".css");
        return getClass().getResource(name.toString());
    }

}
