package com.example.application.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

// Permite a cualquier usuario autenticado navegar a esta view.
@Route("dashboard")
@PermitAll
public class DashboardView extends VerticalLayout {

    public DashboardView() {
        add(new H1("Welcome to the dashboard!"));
    }
}
