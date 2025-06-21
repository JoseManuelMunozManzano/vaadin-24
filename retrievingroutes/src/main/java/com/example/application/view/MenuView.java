package com.example.application.view;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

@Route(value = "")
public class MenuView extends VerticalLayout {

    public MenuView() {
        // Usando RouteConfiguration y su méto-do forSessionScope podemos
        // recuperar la ruta de una clase.
        // Si necesita parámetros, los indicamos usando RouteParameters donde
        // indicamos la key y su value.
        String userRoute = RouteConfiguration.forSessionScope()
                .getUrl(UserView.class, new RouteParameters("id", "567"));

        String ServiceRoute = RouteConfiguration.forSessionScope()
                .getUrl(ServicesView.class);

        // Creamos unos Anchor donde indicamos la ruta recuperada.
        Anchor linkUser = new Anchor(userRoute, "User");
        Anchor linkServices = new Anchor(ServiceRoute, "Services");

        add(linkUser, linkServices);
    }
}
