package com.example.application.view;

import com.example.application.util.NotificationUtil;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.router.RouteConfiguration;

// Indicamos @RouteAlias para indicar distintos alias con los que acceder al root.
// Es obligatorio indicar @Route si indicamos @RouteAlias.
@Route(value = "")
@RouteAlias("home")
@RouteAlias("main")
@RouteAlias("xyz")
@PageTitle("Home")
public class MenuView extends VerticalLayout {

    public MenuView() {

        add(new H6("Menu View"));

        Anchor linkUser = new Anchor("user", "User");

        // Registering Routes
        // Para configurar un route dinámicamente usamos la clase RouteConfiguration
        // y su méto-do forSessionScope, donde podemos establecer un route, indicando
        // el path y la view donde debe navegar.
        // El path indicado tiene que ser el mismo que se indica en el href del Anchor.
        //
        // Es importante indicar que registramos UserView2 para un ámbito de session, es decir
        // si la intentamos registrar más de una vez para la misma sesión, lanza una excepción.
        // Y se registra más de una vez si volvemos a este root (http://localhost:8080/)
        // La session se guarda como cookie.
        // Más adelante en el curso veremos como tratar este escenario de error.
        //
//        RouteConfiguration.forSessionScope().setRoute("user2", UserView2.class);

        // Route Alias y Parent Layout
        // Notar que se indica el alias como primer argumento y el último es su Parent Layout.
        // Acceder primero a http://localhost:8080/ para registrar los alias.
        // Luego puedo acceder a http://localhost:8080/john o http://localhost:8080/kevin o http://localhost:8080/adriana
        // Y accederemos a UserView2. Solo se mostrará el Parent Layout si accedemos a http://localhost:8080/user2 o
        // http://localhost:8080/adriana
        RouteConfiguration configuration = RouteConfiguration.forSessionScope();
//        configuration.setRoute("user2", UserView2.class, ParentLayout.class);       // parent layout
//        configuration.setRoute("john", UserView2.class);                            // Este es un alias
//        configuration.setRoute("kevin", UserView2.class);                           // Este es otro alias
//        configuration.setRoute("adriana", UserView2.class, ParentLayout.class);     // Este es otro alias y parent layout
//
//        Anchor linkUser2 = new Anchor("user2", "User2");
//        add(linkUser, linkUser2);

        // Registramos 3 nuevas views dinámicamente.
        configuration.setRoute("service", ServicesView.class);
        configuration.setRoute("contactus", ContactUsView.class);
        configuration.setRoute("remove", RemoveView.class);

        // Get Registered Routes
        configuration.getAvailableRoutes().forEach(route -> {
            NotificationUtil.showSuccessNotification(route.getTemplate());
        });

        add(linkUser);
    }
}
