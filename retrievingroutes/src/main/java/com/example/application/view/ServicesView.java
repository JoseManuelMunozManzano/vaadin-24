package com.example.application.view;

import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "service")
@PageTitle("Services")
public class ServicesView extends VerticalLayout {

    public ServicesView() {
        add(new H6("Services View"));
    }
}
