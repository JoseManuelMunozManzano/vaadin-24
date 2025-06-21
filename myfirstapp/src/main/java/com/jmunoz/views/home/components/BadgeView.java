package com.jmunoz.views.home.components;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("badge")
public class BadgeView extends VerticalLayout {

    public BadgeView() {

        // Podemos añadir iconos en nuestro badge.
        Icon iconPending = VaadinIcon.CLOCK.create();
        iconPending.getStyle().set("padding", "4px");

        // Notar que sobre un Span añadimos el theme badge.
        // Este es el tema incorporado proporcionado por Vaadin.
        // Podemos añadir más themes, como en el ejemplo.
        Span badgePending = new Span(iconPending, new Span("Pending"));
        badgePending.getElement().getThemeList().add("badge contrast primary");

        // Creamos otro badge sobre un Div.
        Div badgeError = new Div("Error");
        badgeError.getElement().getThemeList().add("badge error primary");

        // Creamos dos badges que añadimos a un Span.
        Span badgeCompleted = new Span("completed!");
        badgeCompleted.getElement().getThemeList().add("badge success");

        Span badgeNotifications = new Span("5 pending notifications");
        badgeNotifications.getElement().getThemeList().add("badge error");

        Span spanNotification = new Span(new Span("Your order has been successfully"),
                badgeCompleted, new Span("Thank you for your purchase. Please note that you have"),
                badgeNotifications, new Span("in your account. These might include updates, promotional offers, or important information related to your recent activity. Be sure to check them out to stay informed and make the most of your experience."));
//        Span spanNotification = new Span("Your order has been successfully completed! Thank you for your purchase. Please note that you have 5 pending notifications in your account. These might include updates, promotional offers, or important information related to your recent activity. Be sure to check them out to stay informed and make the most of your experience.");

        add(badgePending, badgeError, spanNotification);

        setPadding(true);
        setMargin(true);
    }
}
