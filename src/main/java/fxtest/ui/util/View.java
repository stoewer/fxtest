package fxtest.ui.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import com.google.inject.Injector;
import javax.inject.Inject;
import java.io.IOException;
import java.net.URL;

public class View {

    private static final String CSS_EXT = ".css";
    private static final String FXML_EXT = ".fxml";

    private URL styleURL;

    private FXMLLoader loader;
    private Injector injector;

    @Inject
    public View(Injector injector) {
        this.injector = injector;

        styleURL = generateStyleURL();

        loader = new FXMLLoader(generateViewURL());
        loader.setControllerFactory(cls -> injector.getInstance(cls));
    }

    public FXMLLoader getLoader() {
        return loader;
    }

    public Parent getParent() throws IOException {
        Parent p = loader.load();
        if (styleURL != null) {
            p.getStylesheets().add(styleURL.toExternalForm());
        }
        return p;
    }

    protected Injector getInjector() {
        return injector;
    }

    private URL generateViewURL() {
        StringBuilder name = new StringBuilder(100)
                .append('/')
                .append(getClass().getCanonicalName().replace('.', '/'))
                .append(FXML_EXT);
        return getClass().getResource(name.toString());
    }

    private URL generateStyleURL() {
        StringBuilder name = new StringBuilder(100)
                .append('/')
                .append(getClass().getCanonicalName().replace('.', '/'))
                .append(CSS_EXT);
        return getClass().getResource(name.toString());
    }

}
