package com.example.application.view;

import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Layout;
import com.vaadin.flow.router.RouterLayout;

@Layout
public class ParentLayout extends VerticalLayout implements RouterLayout {

    public ParentLayout() {
        add(new H6("Parent Layout"));
    }
}
