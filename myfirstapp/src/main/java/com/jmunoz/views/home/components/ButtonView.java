package com.jmunoz.views.home.components;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.SvgIcon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

@Route("button")
public class ButtonView extends VerticalLayout {

    public ButtonView() {

        final Button btnShowMessage = new Button("Show");
        addListeners(btnShowMessage);
        stylingButton(btnShowMessage);

        // Sobre este botón no aplicamos los estilos CSS
        final Button btnClick = new Button("Click");
        btnClick.setDisableOnClick(true);

        add(btnShowMessage, btnClick);
    }

    private void addListeners(Button btn) {
        // Aquí se puede indicar el texto también
        btn.setText("Show");
        btn.setTooltipText("It will display hello world message");
        // Cuando se haga click (o se pulse la tecla ENTER si no hay AddClickListener), se deshabilita el botón.
        btn.setDisableOnClick(true);
        // Si se pulsa Intro, se está disparando el Listener activo.
        btn.addClickShortcut(Key.ENTER);


        // Para un clic genérico.
        btn.addClickListener(buttonClickEvent -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Notification.show("Hello World");
            btn.setEnabled(true);
        });

        // Para un clic.
//         btn.addSingleClickListener(buttonClickEvent -> {
//            Notification.show("Single Click");
//         });

        // Para doble clic.
//        btn.addDoubleClickListener(buttonClickEvent -> {
//            Notification.show("Double Clicked");
//        });

        // Pulsar la tecla Tab para que el botón acabe obteniendo el foco.
        btn.addFocusListener(buttonFocusEvent -> {
            Notification.show("Focused");
        });
    }

    private void stylingButton(Button btn) {
        // // THEMES VARIANTS
//        btn.addThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_PRIMARY);
//        btn.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR);
        btn.addThemeVariants(ButtonVariant.LUMO_ERROR);

        // Iconos
        btn.setIcon(VaadinIcon.ADOBE_FLASH.create());

        // Creando un icono a partir de un archivo .svg
        StreamResource streamResourceYouTube = new StreamResource("youtube.svg",
                () -> getClass().getResourceAsStream("/META-INF/resources/icons/youtube.svg"));
        SvgIcon youTubeIcon = new SvgIcon(streamResourceYouTube);
        btn.setIcon(youTubeIcon);

        // Situar el icono detrás del texto
        btn.setIconAfterText(true);

        // Dar estilos usando el méto-do getStyle().set
        // Clases inline que se aplican con prioridad sobre addClassName()
//        btn.getStyle().set("background", "green");
//        btn.getStyle().set("color", "white");

        // Dar estilos usando el méto-do addClassName()
        // Ver el fuente main/frontend/themes/my-app/components/buttonStyle.css
        // Ese css se importa en main/frontend/themes/my-app/styles.css
        btn.addClassName("primary");
    }
}
