package com.example.application.view;

import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

// Registering Routes
// Desde MenuView hay un Anchor a esta view, pero aquí no tenemos un @Route registrado.
public class UserView2 extends VerticalLayout {

    public UserView2() {
        add(new H6("User View 2"));
    }
}
