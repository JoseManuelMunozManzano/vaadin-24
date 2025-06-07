package com.jmunoz.views.home.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("verticallayout")
public class VerticalLayoutView extends Div {

    private final Button btnTop;
    private final Button btnCenter;
    private final Button btnBottom;

    public VerticalLayoutView() {
        btnTop = new Button("Top Button");
        btnCenter = new Button("Center Button");
        btnBottom = new Button("Bottom Button");

        // VerticalLayout toma por defecto el 100% del ancho, pero no tiene una altura por defecto.
        // También toma por defecto valores para los themes padding y spacing (espacio entre componentes).
        VerticalLayout vLayoutPrimary = new VerticalLayout();
        setVerticalLayoutProperties(vLayoutPrimary);
        setVerticalLayoutListeners(vLayoutPrimary);
        stylingVerticalLayout(vLayoutPrimary);
        vLayoutPrimary.add(btnTop, btnCenter, btnBottom);

        VerticalLayout vLayoutSecondary = new VerticalLayout();
        TextField txtName = new TextField("Name");
        vLayoutSecondary.add(txtName);

        add(vLayoutPrimary, vLayoutSecondary);
    }

    private void stylingVerticalLayout(VerticalLayout vLayoutPrimary) {
        // ESTILOS INLINE
        vLayoutPrimary.getStyle().set("background", "lightgray");

        // USANDO CSS
        // Ver el fuente main/frontend/themes/my-app/components/verticalLayoutStyle.css
        // Ese css se importa en main/frontend/themes/my-app/styles.css
//        vLayoutPrimary.addClassName("vlPrimary");
    }

    private void setVerticalLayoutListeners(VerticalLayout vLayoutPrimary) {

        // Tenemos distintas formas de disparar eventos al hacer click.
        vLayoutPrimary.addClickListener(event -> {

        });
    }

    private void setVerticalLayoutProperties(VerticalLayout vLayoutPrimary) {
        // Establecemos altura.
        vLayoutPrimary.setHeight("500px");

        // Eliminamos el spacing.
//        vLayoutPrimary.setSpacing(false);

        // Modificamos el padding.
//        vLayoutPrimary.setPadding(false);

        // Añadimos margin.
//        vLayoutPrimary.setMargin(true);

        // Alinear horizontalmente todos los items dentro de un Vertical Layout.
        vLayoutPrimary.setAlignItems(FlexComponent.Alignment.CENTER);

        // Otra forma de alinear horizontalmente todos los items de un Vertical Layout.
//        vLayoutPrimary.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);

        // Alinear horizontalmente uno de los items de un Vertical Layout.
//        vLayoutPrimary.setHorizontalComponentAlignment(FlexComponent.Alignment.START, btnTop);
//        vLayoutPrimary.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, btnCenter);
//        vLayoutPrimary.setHorizontalComponentAlignment(FlexComponent.Alignment.END, btnBottom);

        // Hacer que los componentes crezcan verticalmente (tenemos que haberle dado una altura fija).
        // Toma to-do el espacio vertical disponible y lo reparte como le digamos entre los componentes especificados.
//        vLayoutPrimary.setFlexGrow(0.5, btnTop);
//        vLayoutPrimary.setFlexGrow(1, btnCenter);
//        vLayoutPrimary.setFlexGrow(0.5, btnBottom);

        // Expandir expande verticalmente un item de un Vertical Layout to-do lo posible.
        // Si se inspecciona el elemento veremos que aplica flex-grow: 1
//        vLayoutPrimary.expand(btnCenter);

        // Alinear verticalmente todos los items dentro de un Vertical Layout.
        vLayoutPrimary.setJustifyContentMode(FlexComponent.JustifyContentMode.AROUND);

        // Visibilizamos/invisibilizamos todos los componentes del layout (y el layout)
//        vLayoutPrimary.setVisible(false);

        // Habilitamos/Inhabilitamos todos los componentes de un Layout.
//        vLayoutPrimary.setEnabled(false);
    }
}
