package com.jmunoz.views.home.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("notification")
public class NotificationView extends VerticalLayout {

    Notification notification;

    public NotificationView() {

        // Inicializando una notificación.
        // Podemos incrementar la duración de la notificación (por defecto unos 5000 ms) y la posición
        // de aparición de la notificación (por defecto abajo a la izquierda)
        //
        // Indicando argumentos en el constructor
        // notification = new Notification("Hello World!", 4000, Notification.Position.BOTTOM_CENTER);

        // Indicando configuración usando métodos set.
         notification = new Notification();
         notification.setText("Welcome to Spain!!");
         notification.setDuration(4000);
         notification.setPosition(Notification.Position.TOP_STRETCH);

        // Añadiendo a la notificación un VerticalLayout con componentes.
        // notification.add(displayMessage());

        // THEMES VARIANTS
        // notification.addThemeVariants(NotificationVariant.LUMO_WARNING);

        // USANDO CSS
        // Ver el fuente main/frontend/themes/my-app/components/notificationStyle.css
        // Ese css se importa en main/frontend/themes/my-app/styles.css
        notification.addClassName("primary");

        Button btnShowNotification = new Button("Show Notification");

        btnShowNotification.addClickListener(event -> {
            // Formas de usar un notification:

            // Creación y apertura usando show()
            // Notification.show("Hello World!", 2000, Notification.Position.MIDDLE);

            // Abriendo una notificación existente usando open()
            notification.open();
        });

        Button btnClose = new Button("Close Notification");
        btnClose.addClickListener(buttonClickEvent -> {
            // Cerrando una notificación existente manualmente usando close()
            notification.close();
        });

        add(btnShowNotification, btnClose);
        // add(btnShowNotification);
    }

    private VerticalLayout displayMessage() {
        VerticalLayout verticalLayout = new VerticalLayout();

        H6 message = new H6("Your session has been expired");
        H6 message2 = new H6("Please login again");

        Button btnClose = new Button(VaadinIcon.CLOSE.create());
        btnClose.addClickListener(event -> {
            notification.close();
        });

        verticalLayout.add(btnClose, message, new Hr(), message2);

        return verticalLayout;
    }
}
