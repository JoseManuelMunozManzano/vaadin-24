package com.jmunoz.views.home.components;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.*;
import com.vaadin.flow.router.Route;

@Route("tabview")
public class TabView extends VerticalLayout {

    public TabView() {
        VerticalLayout content = new VerticalLayout();

        // En un componente Tabs añadimos tres Tab.
        Tab tabHome = new Tab(VaadinIcon.HOME.create(), new Span("Home"));
        Tab tabPortfolio = new Tab(new Span("Portfolio"), new Span("34"));
        Tab tabContactUs = new Tab("Contact Us");

        // Habilitamos/Deshabilitamos pestañas.
        tabPortfolio.setEnabled(true);

        // Añadimos el primer argumento del Tab en la parte de arriba.
//        for (Tab tab : new Tab[] {tabHome, tabPortfolio, tabContactUs}) {
//            tab.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
//        }

        Tabs tabs = new Tabs(tabHome, tabPortfolio, tabContactUs);

        // La orientación por defecto es horizontal, pero podemos cambiarlo a vertical.
        // tabs.setOrientation(Tabs.Orientation.VERTICAL);

        // Indicamos el tamaño.
        tabs.setWidthFull();
        // Centramos las pestañas de forma distribuida por to-do el ancho.
        tabs.addThemeVariants(TabsVariant.LUMO_CENTERED, TabsVariant.LUMO_EQUAL_WIDTH_TABS);

        // Mostramos un Vertical Layout personalizado en función del tab seleccionado.
        tabs.addSelectedChangeListener(event -> {

            // Eliminamos el contenido actual.
            content.removeAll();

           if (event.getSelectedTab() == tabHome) {
               content.add(new Span("Home"));;
           } else if (event.getSelectedTab() == tabPortfolio) {
               content.add(new Span("Portfolio"));
           } else if (event.getSelectedTab() == tabContactUs) {
               content.add(new Span("Contact Us"));
           }
        });

        // Cambiamos programáticamente el tab seleccionado, que por defecto es el primero.
        tabs.setSelectedTab(tabContactUs);

        // TABSHEET
        // Tienen su propio contenido, no como los Tabs, donde hemos creado el contenido manualmente.
        // Es decir, no hay que hacer nada del addSelectedChangeListener.
        TabSheet tabSheet = new TabSheet();
        tabSheet.add("Home Tab", new Div("This is home tab"));
        Tab tabPortfolio2 = tabSheet.add("Portfolio Tab", new Div("This is portfolio tab"));
        tabSheet.add("Contact Us Tab", new Div("This is contact us tab"));

        // Habilitamos/Deshabilitamos un tab del tabsheet
        tabPortfolio2.setEnabled(false);

        // USANDO CSS
        // Ver el fuente main/frontend/themes/my-app/components/tabStyle.css
        // Ese css se importa en main/frontend/themes/my-app/styles.css
        tabSheet.addClassName("my-tabsheet");

        // El vertical layout se añade, pero su contenido es dinámico.
        add(tabs, content, tabSheet);
    }
}
