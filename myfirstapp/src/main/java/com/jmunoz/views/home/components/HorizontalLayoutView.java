package com.jmunoz.views.home.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

@Route("horizontalLayout")
public class HorizontalLayoutView extends Div {

    private final Button btnLeft;
    private final Button btnCenter;
    private final Button btnRight;

    public HorizontalLayoutView() {
        btnLeft = new Button("Left Button");
        btnCenter = new Button("Center Button");
        btnRight = new Button("Right Button");

        // HorizontalLayout toma por defecto el 100% del ancho, pero no tiene una altura por defecto.
        // También toma por defecto valores para el theme spacing (espacio entre componentes).
        HorizontalLayout hLayoutPrimary = new HorizontalLayout();
        setHorizontalLayoutProperties(hLayoutPrimary);
        setHorizontalLayoutListeners(hLayoutPrimary);
        stylingHorizontalLayout(hLayoutPrimary);

        hLayoutPrimary.add(btnLeft, btnCenter, btnRight);

        add(hLayoutPrimary);
    }

    private void stylingHorizontalLayout(HorizontalLayout hLayoutPrimary) {
        // ESTILOS INLINE
        hLayoutPrimary.getStyle().set("background", "lightgray");

        // USANDO CSS
        // Ver el fuente main/frontend/themes/my-app/components/horizontalLayoutStyle.css
        // Ese css se importa en main/frontend/themes/my-app/styles.css
//        hLayoutPrimary.addClassName("hlPrimary");
    }

    private void setHorizontalLayoutListeners(HorizontalLayout hLayoutPrimary) {
        // Tenemos distintas formas de disparar eventos al hacer click.
        hLayoutPrimary.addClickListener(event -> {

        });
    }

    private void setHorizontalLayoutProperties(HorizontalLayout hLayoutPrimary) {
        // Indicamos la altura y la anchura del layout.
        hLayoutPrimary.setHeight("500px");
        hLayoutPrimary.setWidth("750px");

        // Eliminamos el spacing.
//        hLayoutPrimary.setSpacing(false);

        // Modificamos el padding.
        hLayoutPrimary.setPadding(true);

        // Añadimos margin.
        hLayoutPrimary.setMargin(true);

        // Alinear verticalmente todos los items dentro de un Horizontal Layout.
        hLayoutPrimary.setAlignItems(FlexComponent.Alignment.CENTER);

        // Otra forma de alinear verticalmente todos los items de un Horizontal Layout.
//        hLayoutPrimary.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.END);

        // Alinear verticalmente uno de los items de un Vertical Layout.
//        hLayoutPrimary.setAlignSelf(FlexComponent.Alignment.START, btnLeft);
//        hLayoutPrimary.setAlignSelf(FlexComponent.Alignment.CENTER, btnCenter);
//        hLayoutPrimary.setAlignSelf(FlexComponent.Alignment.END, btnRight);

        // Otra forma de alinear verticalmente uno de los items de un Vertical Layout.
//        hLayoutPrimary.setVerticalComponentAlignment(FlexComponent.Alignment.START, btnLeft);
//        hLayoutPrimary.setVerticalComponentAlignment(FlexComponent.Alignment.CENTER, btnCenter);
//        hLayoutPrimary.setVerticalComponentAlignment(FlexComponent.Alignment.END, btnRight);

        // Alinear horizontalmente todos los items dentro de un Horizontal Layout.
        hLayoutPrimary.setJustifyContentMode(FlexComponent.JustifyContentMode.EVENLY);

        // Hacer que los componentes crezcan horizontalmente
        // Toma to-do el espacio horizontal disponible y lo reparte como le digamos entre los componentes especificados.
        hLayoutPrimary.setFlexGrow(1, btnCenter);

        // Visibilizamos/invisibilizamos todos los componentes del layout (y el layout)
//        hLayoutPrimary.setVisible(false);

        // Habilitamos/Inhabilitamos todos los componentes de un Layout.
//        hLayoutPrimary.setEnabled(false);
    }
}
