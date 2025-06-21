package org.vaadin.example.view;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("contactus")
public class ContactUsView extends VerticalLayout {

    public ContactUsView() {
        add(new H3("Contact Us"));

        // RouterLink sería como el HTML tag <a>
        // Se indica el texto y el path, obligatoriamente un view, no vale un string.
        RouterLink routerLinkAboutUs = new RouterLink("About Us", AboutUsView.class);

        add(routerLinkAboutUs);
    }
}
