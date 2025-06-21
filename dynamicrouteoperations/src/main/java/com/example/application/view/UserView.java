package com.example.application.view;

import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

// Page Title
// Para establecer el nombre del título de la page (se ve en la pestaña del navegador)
// Estáticamente, podemos indicar la anotación @PageTitle("User")
// Dinámicamente, tenemos que implementar HasDynamicTitle y su méto-do getPageTitle()
@Route(value = "user")
//@PageTitle("User")
public class UserView extends VerticalLayout implements HasDynamicTitle, HasUrlParameter<String> {

    private String title;

    public UserView() {
        add(new H6("User View"));
    }

    @Override
    public String getPageTitle() {
        return title;
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String parameter) {
        title = parameter;
    }
}
