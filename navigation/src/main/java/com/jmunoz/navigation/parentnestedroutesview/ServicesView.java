package com.jmunoz.navigation.parentnestedroutesview;

import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

// Esta view tiene como path service y en la pestaña del navegador aparecerá el nombre Services.
// Indicamos autoLayout = false (por defecto true) para indicar que esta view no tiene un parent layout.
// Esto es obligatorio si en MainLayout.java usamos la anotación @Layout y no queremos que esta view sea su hija.
@Route(value = "service", autoLayout = false)
@PageTitle("Services")
public class ServicesView extends VerticalLayout {

    public ServicesView() {
        add(new H6("Services View"));
    }
}
