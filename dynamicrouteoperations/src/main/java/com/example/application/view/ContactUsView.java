package com.example.application.view;

import com.example.application.util.NotificationUtil;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.RouteConfiguration;

// Notar que no hemos indicado la route.
// Pero aquí también obtenemos las rutas registradas.
public class ContactUsView extends VerticalLayout implements BeforeEnterObserver {

    public ContactUsView() {
        add(new H6("Contact Us View"));
    }

    // Como las rutas se registraron en MenuView, aquí se muestran sin problema esas rutas registradas.
    // Importante pasar primero por http://localhost:8080/ que es donde se registran estas rutas.
    // Importante entender que esto va por sesión!!!
    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        RouteConfiguration configuration = RouteConfiguration.forSessionScope();

        configuration.getAvailableRoutes().forEach(route -> {
            NotificationUtil.showSuccessNotification(route.getTemplate());
        });
    }
}
