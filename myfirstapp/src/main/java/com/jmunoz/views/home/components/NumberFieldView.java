package com.jmunoz.views.home.components;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("numberfield")
public class NumberFieldView extends VerticalLayout {

    public NumberFieldView() {
        NumberField nbPrice = new NumberField("Price");
        setNumberFieldProperties(nbPrice);
        setNumberFieldListeners(nbPrice);
        stylingNumberField(nbPrice);

        add(nbPrice);
    }

    private void stylingNumberField(NumberField nbPrice) {
        // // THEMES VARIANTS
        // El texto de ayuda se sitúa en la parte superior
//        nbPrice.addThemeVariants(TextFieldVariant.LUMO_HELPER_ABOVE_FIELD, TextFieldVariant.LUMO_SMALL, TextFieldVariant.LUMO_ALIGN_RIGHT);

        // ESTILOS INLINE
//        nbPrice.getStyle().setBackgroundColor("beige");
//        nbPrice.getStyle().setColor("green");
//        nbPrice.getStyle().set("border-radius", "8px");

        // USANDO CSS
        // Ver el fuente main/frontend/themes/my-app/components/numberfieldStyle.css
        // Ese css se importa en main/frontend/themes/my-app/styles.css
        nbPrice.addClassName("price");
    }

    private void setNumberFieldListeners(NumberField nbPrice) {
        // Mostramos el valor antiguo. Para ello hace falta indicar EAGER.
        // Cuidado porque partiendo de nada, el valor no es numérico y da error.
        nbPrice.setValueChangeMode(ValueChangeMode.EAGER);
        nbPrice.addValueChangeListener(event -> {
            Notification.show(event.getOldValue().toString());
        });

        nbPrice.addFocusListener(event -> {
            Notification.show("Focus listener called");
        });

        // Cuando se pierde el foco.
        nbPrice.addBlurListener(event -> {
            Notification.show("Blur listener called");
        });
    }

    private void setNumberFieldProperties(NumberField nbPrice) {
        // Para que aparezca el mensaje de error.
        nbPrice.setErrorMessage("Price should be in double only");

        // Para que aparezcan botones para incrementar/decrementar valores.
        nbPrice.setStepButtonsVisible(true);

        // Se indica el valor máximo y mínimo posibles y el step size.
        nbPrice.setMax(20);
        nbPrice.setMin(-4);
        nbPrice.setStep(0.2);

        // Habilitamos el campo para poder informar valores.
//        nbPrice.setEnabled(true);

        // Visibilizar/Invisibilizar el campo.
//        nbPrice.setVisible(true);

        // Indicar el prefijo del campo.
        Div divSmiley = new Div(":)");
        divSmiley.getStyle().set("color", "orange");

        nbPrice.setPrefixComponent(divSmiley);

        // Indicar el sufijo del campo.
        Icon dollarIcon = new Icon(VaadinIcon.DOLLAR);
        dollarIcon.setColor("red");

        nbPrice.setSuffixComponent(dollarIcon);

        // Añadir componente con ayuda (setHelperText() sería para texto)
        H6 h6HelperMessage = new H6("It will accept only two decimals i.e. 123.56");
        nbPrice.setHelperComponent(h6HelperMessage);
    }
}
