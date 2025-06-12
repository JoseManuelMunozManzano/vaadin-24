package com.jmunoz.navigation.parentnestedroutesview;

import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

// Esta view tiene como path /home y en la pestaña del navegador aparecerá el nombre Home.
// Indicamos su parent layout.
// Si en MainLayout.java usara la anotación @Layout no haría falta, salvo que en este ejemplo hay dos
// parent layout distintos y entonces hay que indicar cuál es su padre.
// Si no indicamos cuál es su padre, por defecto intentará que sea el que tiene como path el root, que es /.
// Cuando accedamos a /home, veremos cargado también su parent layout.
@Route(value = "home", layout = MainLayout.class)
@PageTitle("Home")
public class HomeView extends VerticalLayout {

    public HomeView() {
        add(new H6("Home View"));
    }
}
