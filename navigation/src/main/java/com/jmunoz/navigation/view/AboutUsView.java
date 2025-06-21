package com.jmunoz.navigation.view;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

// Se ha indicado implements AfterNavigationObserver porque se ha añadido el ciclo de vida
// de la navegación AfterNavigationEvent.
@Route("aboutUs")
public class AboutUsView extends VerticalLayout implements AfterNavigationObserver {

    private final TextField txtName;

    public AboutUsView() {
        add(new H3("About Us"));
        RouterLink routerLinkContactUs = new RouterLink("Contact Us", ContactUsView.class);

        txtName = new TextField("Name");

        add(routerLinkContactUs, txtName);
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        // Para probar ir a http://localhost:8080/contactUs y pulsar el routerLink Contact Us
        txtName.setValue("John");
    }
}
