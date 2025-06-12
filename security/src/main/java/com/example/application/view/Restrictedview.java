package com.example.application.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.DenyAll;

// No permite a nadie navegar a la view. Es el comportamiento por defecto, es decir, si no se anota una view, se aplica esta anotación.
@Route("restricted")
@DenyAll
public class Restrictedview extends VerticalLayout {

    public Restrictedview() {
        add(new H1("Access to this page is denied for everyone."));
    }
}
