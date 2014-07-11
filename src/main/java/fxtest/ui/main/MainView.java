package fxtest.ui.main;

import com.google.inject.Injector;
import fxtest.ui.util.View;
import sun.applet.Main;

import javax.inject.Inject;

public class MainView extends View {

    @Inject
    public MainView(Injector injector) {
        super(injector);
    }

}