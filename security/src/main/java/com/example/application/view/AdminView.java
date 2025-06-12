package com.example.application.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

// Concede acceso a usuarios que tengan los roles especificados en la property `value` de la anotación.
@Route("admin")
@RolesAllowed(value = "ADMIN")
public class AdminView extends VerticalLayout {

    public AdminView() {
        add(new H1("Welcome to the admin panel!"));
    }
}
