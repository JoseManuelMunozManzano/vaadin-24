package com.jmunoz.navigation.view;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

// Se ha indicado implements BeforeEnterObserver porque se ha añadido el ciclo de vida
// de la navegación BeforeEnterEvent.
@Route("contactUs")
public class ContactUsView extends VerticalLayout implements BeforeEnterObserver {

    public ContactUsView() {
        add(new H3("Contact Us"));

        RouterLink routerLinkAboutUs = new RouterLink("About US", AboutUsView.class);

        add(routerLinkAboutUs);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
//        Notification.show("Welcome to Contact Us!");

        boolean isAuthenticated = getAuthentication();

        if (!isAuthenticated) {
            // forwardTo detiene la navegación y reenvía la solicitud a otra vista actualizando la URL del navegador.
            // Usarlo cuando quieres redirigir y reflejarlo en la barra de direcciones, como después de un login o redirección explícita.
            // Para probar ir a http://localhost:8080/aboutUs y pulsar el routerLink Contact Us
            // Accederemos a HomeView y en la URL aparecerá http://localhost:8080
            //
//            beforeEnterEvent.forwardTo(HomeView.class);

            // rerouteTo detiene la navegación actual y cambia internamente el destino a otra vista, sin cambiar la URL del navegador.
            // Usarlo cuando quieres que el usuario no vea el cambio de URL, como en validaciones, roles, errores, etc.
            // Para probar ir a http://localhost:8080/aboutUs y pulsar el routerLink Contact Us.
            // Accederemos a HomeView pero en la URL aparecerá http://localhost:8080/contactUs
            //
//            beforeEnterEvent.rerouteTo(HomeView.class);
        }
    }

    private boolean getAuthentication() {
        return false;
    }
}
