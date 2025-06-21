package com.jmunoz.navigation.parentnestedroutesview;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.router.Layout;
import com.vaadin.flow.router.RouterLayout;

// Aquí uso la anotación @Layout como contraejemplo de MainLayout
@Layout("mainlayout2")
public class MainLayout2 extends Div implements RouterLayout {

    public MainLayout2() {
        add(new H6("Main View 2"));
    }
}
