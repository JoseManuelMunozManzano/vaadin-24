package com.jmunoz.navigation.parentnestedroutesview;

import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

// Esta view tiene como path contactUs2 y en la pestaña del navegador aparecerá el nombre Contact Us.
// Indicamos su parent layout.
// Cuando accedamos a /contactUs2, veremos cargado también su parent layout.
@Route(value = "contactUs2", layout = MainLayout2.class)
@PageTitle("Contact Us")
public class ContactUsView extends VerticalLayout {

    public ContactUsView() {
        add(new H6("Contact Us View"));
    }
}
