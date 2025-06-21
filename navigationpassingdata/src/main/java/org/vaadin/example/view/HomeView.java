package org.vaadin.example.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// La ruta es /home
@Route
public class HomeView extends VerticalLayout {

    public HomeView() {
        add(new H3("Home"));

        // Para Query Params.
        Map<String, List<String>> parametersMap = new HashMap<>();
        parametersMap.put("name", Arrays.asList("John"));
        parametersMap.put("phone", Arrays.asList("123456789"));
        parametersMap.put("email", Arrays.asList("a@a.com"));

        QueryParameters queryParameters = new QueryParameters(parametersMap);

        Button btnUser = new Button("User");
        btnUser.addClickListener(e -> {
            // Server Side Navigation usando clase.
            UI.getCurrent().navigate(UserView.class, "123");
        });

        Button btnProduct = new Button("Product");
        btnProduct.addClickListener(e -> {
           UI.getCurrent().navigate(ProductView.class);
        });

        Button btnProduct2 = new Button("Product 2");
        btnProduct2.addClickListener(e -> {
            UI.getCurrent().navigate(ProductView2.class, "166563/334/334");
        });

        Button btnUser2 = new Button("User 2");
        btnUser2.addClickListener(e -> {
            // Server Side Navigation usando path.
            UI.getCurrent().navigate("user2/123/edit/11234334");
        });

        Button btnUser3 = new Button("User 3");
        btnUser3.addClickListener(e -> {
            UI.getCurrent().navigate(UserView3.class, queryParameters);
        });

        Button btnContactUs = new Button("Contact Us");
        btnContactUs.addClickListener(e -> {
            UI.getCurrent().navigate(ContactUsView.class);
        });

        // Ejemplo de Standard Links (Anchor)
        // Por defecto navega dentro de la misma pestaña del navegador.
        Anchor anchorAboutUs = new Anchor("aboutus", "About Us");
        // Añadiendo este atributo, abre una pestaña nueva en el navegador.
        anchorAboutUs.getElement().setAttribute("target", "_blank");


        add(btnUser, btnProduct, btnProduct2, btnUser2, btnUser3, btnContactUs, anchorAboutUs);
    }
}
