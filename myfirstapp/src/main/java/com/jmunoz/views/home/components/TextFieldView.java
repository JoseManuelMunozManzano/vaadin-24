package com.jmunoz.views.home.components;

import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("textfield")
public class TextFieldView extends VerticalLayout {

    public TextFieldView() {
        TextField txtName = new TextField("Name : ");
        setTextFieldProperties(txtName);
        setTextFieldListeners(txtName);
        stylingTextField(txtName);

        TextField txtCurrency = new TextField("Currency");
        setTxtCurrencyProperties(txtCurrency);

        add(txtName, txtCurrency);
    }

    private void stylingTextField(TextField txtName) {
        // THEMES VARIANTS
        // El texto de ayuda se sitúa en la parte superior
//        txtName.addThemeVariants(TextFieldVariant.LUMO_HELPER_ABOVE_FIELD, TextFieldVariant.LUMO_SMALL, TextFieldVariant.LUMO_ALIGN_RIGHT);

        // ESTILOS INLINE
//        txtName.getStyle().set("background", "violet");
//        txtName.getStyle().set("font-size", "12px");
//        txtName.getStyle().set("font-weight", "bold");
//        txtName.getStyle().set("font-style", "italic");
//        txtName.getStyle().set("border-radius", "8px");

        // USANDO CSS
        // Ver el fuente main/frontend/themes/my-app/components/textfieldStyle.css
        // Ese css se importa en main/frontend/themes/my-app/styles.css
        txtName.addClassName("my-custom-textfield");
    }

    private void setTextFieldListeners(TextField txtName) {

        // ValueChangeMode.EAGER: El listener se disparará tan pronto como tecleamos
        // ValueChangeMode.ON_CHANGE: El listener se disparará tan pronto como perdamos el foco
//        txtName.setValueChangeMode(ValueChangeMode.ON_CHANGE);

        // Cuando cambia el valor se dispara este listener
        txtName.addValueChangeListener(event -> {
//            Notification.show(event.getValue());
        });

        // Cuando se presiona una tecla se dispara este listener
        txtName.addKeyDownListener(event -> {
//            Notification.show(event.getKey().toString());
        });
    }

    private void setTextFieldProperties(TextField txtName) {
        // Otra forma de indicar el label de un TextField
        txtName.setLabel("Name");

        // Indicar un valor programáticamente
//        txtName.setValue("José");

        // Indicar que es requerido
        txtName.setRequired(true);

        // Indicamos un valor placeholder
        txtName.setPlaceholder("Please insert your name");

        // Indicar altura y anchura
        txtName.setWidth("400px");
//        txtName.setHeight("200px");

        // Indicar texto de ayuda
        txtName.setHelperText("Name should be between 4 to 50 in length");

        // Indicar longitud mínima y máxima
        txtName.setMinLength(4);
        txtName.setMaxLength(50);

        // Habilitar/Inhabilitar el campo
        txtName.setEnabled(true);

        // Indicar mensaje de error
        txtName.setErrorMessage("Name should be between 4 to 50 in length and should be alphanumeric");

        // Indicar un tooltip
        txtName.setTooltipText("Please insert your first name");

        // Indicar un patrón
        // Se puede indicar el carácter no permitido, pero aparecerá el mensaje de error
//        txtName.setPattern("^[A-Za-z0-9]*$");

        // Patrón de caracteres permitido
        // No se puede indicar el carácter no permitido
//        txtName.setAllowedCharPattern("^[A-Za-z0-9]*$");

        // Aparece la X para limpiar la información escrita
        txtName.setClearButtonVisible(true);
    }

    private void setTxtCurrencyProperties(TextField txtCurrency) {
        // Uso de iconos
        Icon icon = new Icon(VaadinIcon.PLUS);
        icon.setColor("red");

        Icon icon2 = new Icon(VaadinIcon.DOLLAR);
        icon2.setColor("green");

        txtCurrency.setPrefixComponent(icon);
        txtCurrency.setSuffixComponent(icon2);

        txtCurrency.setClearButtonVisible(true);

        txtCurrency.setWidth("300px");
    }
}
