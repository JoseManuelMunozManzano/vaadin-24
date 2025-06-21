package com.jmunoz.views.home.components;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

@Route("image")
public class ImageView extends VerticalLayout {

    public ImageView() {

        // La imagen se encuentra en src/main/resources/META-INF/resources/images
        // Si no se encuentra la imagen se muestra el texto alternativo.
        Image carImage = new Image();
        carImage.setSrc("images/redcar.png");
        carImage.setAlt("Image is not available");
        // USANDO CSS
        // Ver el fuente main/frontend/themes/my-app/components/imageStyle.css
        // Ese css se importa en main/frontend/themes/my-app/styles.css
        carImage.addClassName("red-car");


        // Podemos indicar un StreamResource y su texto alternativo en el constructor.
        StreamResource resourceUserImage = new StreamResource("1.png",
                () -> getClass().getResourceAsStream("/META-INF/resources/images/1.png"));
        Image userImage = new Image(resourceUserImage, "The image is not available");

        // Añadiendo estilos inline a las imágenes.
        userImage.getStyle().setBackground("red");
        userImage.getStyle().set("padding", "6px");

        add(carImage, userImage);
    }
}
