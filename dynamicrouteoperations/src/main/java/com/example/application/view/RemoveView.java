package com.example.application.view;

import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

// Notar que no hemos indicado la route.
public class RemoveView extends VerticalLayout {

    public RemoveView() {
        add(new H6("Remove View"));
    }
}
