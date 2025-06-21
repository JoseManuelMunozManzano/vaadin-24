package com.jmunoz.navigation.parentnestedroutesview;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.router.RouterLayout;

// De forma inversa a como hemos visto, indicaremos en MenuBarView que su parentLayout es esta clase.
// Es decir, siempre se verá esta clase cuando se cargue el path por defecto, ya que en MenuBarView
// @Layout apunta al root.
public class MainLayout3 extends Div implements RouterLayout {

    public MainLayout3() {
        add(new H6("Main View 3"));
    }
}
