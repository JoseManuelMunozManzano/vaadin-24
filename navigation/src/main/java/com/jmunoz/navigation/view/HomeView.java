package com.jmunoz.navigation.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeLeaveEvent;
import com.vaadin.flow.router.BeforeLeaveObserver;
import com.vaadin.flow.router.Route;

// Como root path se puede usar @Route("/") o @Route("")
// Si se indica solo @Route, entonces el path URL por defecto es /home
//
// Se ha indicado implements BeforeLeaveObserver porque se ha añadido el ciclo de vida
// de la navegación BeforeLeaveEvent.
@Route("/")
public class HomeView extends VerticalLayout implements BeforeLeaveObserver {

    public HomeView() {
        add(new H3("Home"));

        // Ejemplo de navegación usando un botón.
        // Navegamos al pulsar el botón usando navigate.
        Button btnContactUs = new Button("Contact us");

        btnContactUs.addClickListener(e -> {
            // Toma el path de la view.
            // Se le llama server side navigation.
            UI.getCurrent().navigate(ContactUsView.class);
        });

        Button btnAboutUs = new Button("About us");

        btnAboutUs.addClickListener(e -> {
            // Usa un string con el nombre del path url.
            // Este string debe estar definido en alguna anotación @Route de alguna view.
            UI.getCurrent().navigate("aboutUs");
        });

        // Ejemplo de navegación usando un elemento href
        Anchor anchorAboutUs = new Anchor("aboutUs","About Us");
        anchorAboutUs.getElement().setAttribute("target", "_blank");

        add(btnContactUs,btnAboutUs,anchorAboutUs);
    }

    @Override
    public void beforeLeave(BeforeLeaveEvent beforeLeaveEvent) {
        // Vamos a mostrar un Dialog al usuario para que confirme que quiere salir de la view Home.
        BeforeLeaveEvent.ContinueNavigationAction action = beforeLeaveEvent.postpone();

        ConfirmDialog confirmDialog = new ConfirmDialog();
        confirmDialog.setText("Your form has changes! Are you sure you want to leave?");
        confirmDialog.setCancelable(true);
        confirmDialog.addConfirmListener(event -> action.proceed());
        confirmDialog.addCancelListener(event -> action.cancel());
        confirmDialog.open();
    }
}
