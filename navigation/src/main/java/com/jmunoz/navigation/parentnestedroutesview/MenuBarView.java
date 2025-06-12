package com.jmunoz.navigation.parentnestedroutesview;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Layout;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.RouterLayout;

// Indicamos que el parent layout de MenuBar es MainLayout, es decir, al acceder a cualquier ruta que contenga /
// y no tenga otro parent layout definido en la view, se verá esta menubar.
// Tampoco se verá si la view tiene definido autoLayout a false.
@Layout
@ParentLayout(MainLayout3.class)
public class MenuBarView extends VerticalLayout implements RouterLayout {

    public MenuBarView() {
        MenuBar menuBar = new MenuBar();

        ComponentEventListener<ClickEvent<MenuItem>> listener = e ->
        {
            if (e.getSource().getText().equalsIgnoreCase("Home")) {
                e.getSource().getUI().ifPresent(ui -> {
                    ui.navigate(HomeView.class);
                });
            } else if (e.getSource().getText().equalsIgnoreCase("Contact Us")) {
                e.getSource().getUI().ifPresent(ui -> {
                    ui.navigate(ContactUsView.class);
                });
            } else if (e.getSource().getText().equalsIgnoreCase("Services")) {
                e.getSource().getUI().ifPresent(ui -> {
                    ui.navigate("service");
                });
            }
        };

        menuBar.addItem("Home", listener);
        menuBar.addItem("Contact Us", listener);
        menuBar.addItem("Services", listener);

        add(menuBar);
    }
}
