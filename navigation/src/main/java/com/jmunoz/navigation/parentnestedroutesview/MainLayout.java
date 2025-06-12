package com.jmunoz.navigation.parentnestedroutesview;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.router.Layout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;

// Que una view implemente RouterLayout hace que se convierta en un Parent Layout.
// La ruta como tal no hace falta si queremos que sea la root, pero como tengo el package view,
// para diferenciarlo, indico /main.
//
// Si esta app no fuera un ejemplo donde tengo distintos packages de views, comentaría @Route("main")
// porque no querría que fuese una ruta, y descomentaría @Layout, cuya URL por defecto es /
// Usando @Layout no hace falta que en las Nested Routes defina layout = MainLayout.class dento de la anotación @Route,
// salvo que tenga más de un @Layout en la app y quiera que la view sea hija del layout con ruta distinta de / (por defecto)
// Si no quiero que una view sea hija de ningún parent layout, indicaré autoLayout = false en la view correspondiente.
// Puedo indicar más de un @Layout en la app, siempre que tengan diferentes paths.

//@Layout
@Route("main")
public class MainLayout extends Div implements RouterLayout {

    public MainLayout() {
        add(new H6("Main View"));
    }
}
