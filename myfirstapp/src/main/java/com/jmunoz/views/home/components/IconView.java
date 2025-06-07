package com.jmunoz.views.home.components;

import com.vaadin.flow.component.icon.FontIcon;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.SvgIcon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.theme.lumo.LumoIcon;

@Route("icon")
public class IconView extends VerticalLayout {

    IconView() {
        // Icono Vaadin
        Icon icoTaxi = VaadinIcon.TAXI.create();
        setIconProperties(icoTaxi);

        // Icono Lumo
        Icon icoAngleDown = LumoIcon.ANGLE_DOWN.create();
        stylingIcon(icoAngleDown);

        // Iconos de terceros: SVG
        // Se guardan en /META-INF/resources/icons
        // Se pueden manipular sus estilos como en un icono Vaadin o Lumo.
        // También se pueden gestionar eventos y añadir properties como con los iconos normales.
        StreamResource resourceFacebook = new StreamResource("facebook.svg",
                () -> getClass().getResourceAsStream("/META-INF/resources/icons/facebook.svg"));
        SvgIcon iconFacebook = new SvgIcon(resourceFacebook);
        iconFacebook.setColor("blue");

        StreamResource resourceYoutube = new StreamResource("youtube.svg",
                () -> getClass().getResourceAsStream("/META-INF/resources/icons/youtube.svg"));
        SvgIcon iconYoutube = new SvgIcon(resourceYoutube);

        // Uso de varios iconos SVG agrupados en un archivo SVG.
        // El archivo es /META-INF/resources/icons/customIcons.svg
        // Tenemos que indicar en el parámetro symbol el id, para saber cuál de los SVG mostrar en la app.
        StreamResource resourceCustomIcons = new StreamResource("customIcons.svg",
                () -> getClass().getResourceAsStream("/META-INF/resources/icons/customIcons.svg"));
        SvgIcon iconYoutube2 = new SvgIcon(resourceCustomIcons, "icon-youtube");
        SvgIcon iconFacebook2 = new SvgIcon(resourceCustomIcons, "icon-facebook");

        // Uso de iconos de terceros: FontAwesome
        // Se pueden manipular sus estilos como en un icono Vaadin o Lumo.
        // También se pueden gestionar eventos y añadir properties como con los iconos normales.
        FontIcon fontIconGlass = new FontIcon("fa", "fa-glasses");
        fontIconGlass.setColor("orange");
        FontIcon fontIconUser = new FontIcon("fa", "fa-user-md");
        fontIconUser.addClassName("red-text");

        add(icoTaxi, icoAngleDown, iconFacebook, iconYoutube, iconYoutube2, iconFacebook2, fontIconGlass, fontIconUser);
    }

    private void stylingIcon(Icon icon) {
        // USO DE CSS
        // Ver el fuente main/frontend/themes/my-app/components/iconStyle.css
        // Ese css se importa en main/frontend/themes/my-app/styles.css
        //
        // También he importado fontawesome.css y solid.css en styles.css para poder usar iconos de terceros.
        // Ambos los coge de la carpeta main/frontend/themes/my-app/fontawesome
        icon.addClassName("red-text");
    }

    private void setIconProperties(Icon icon) {
        // Podemos cambiar el tamaño del icono.
        icon.setSize("64px");

        // Cambiando el color del icono.
        icon.setColor("red");
    }
}
