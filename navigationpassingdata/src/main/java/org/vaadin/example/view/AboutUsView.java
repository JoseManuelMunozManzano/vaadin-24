package org.vaadin.example.view;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("aboutus")
public class AboutUsView extends VerticalLayout {

    public AboutUsView() {
        add(new H3("About Us"));

        // RouterLink sería como el HTML tag <a>
        // Se indica el texto y el path, obligatoriamente un view, no vale un string.
        RouterLink routerLinkContactUs = new RouterLink("Contact Us", ContactUsView.class);

        add(routerLinkContactUs);
    }
}
