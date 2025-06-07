package com.jmunoz.views.home.components;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("password")
public class PasswordFieldView extends VerticalLayout {

    private final int MAX_CHAR = 8;
    private Span passwordStrengthText = null;

    public PasswordFieldView() {
        PasswordField passwordField = new PasswordField("Password");
        setPasswordFieldProperties(passwordField);
        setPasswordFieldListeners(passwordField);
        stylingPasswordField(passwordField);

        add(passwordField);
    }

    private void stylingPasswordField(PasswordField passwordField) {
        // THEMES VARIANTS
//        passwordField.addThemeVariants(TextFieldVariant.LUMO_SMALL, TextFieldVariant.LUMO_HELPER_ABOVE_FIELD, TextFieldVariant.LUMO_ALIGN_RIGHT);

        // ESTILOS INLINE
//        passwordField.getStyle().set("background", "beige");
//        passwordField.getStyle().set("color", "green");

        // USANDO CSS
        // Ver el fuente main/frontend/themes/my-app/components/passwordfieldStyle.css
        // Ese css se importa en main/frontend/themes/my-app/styles.css
        passwordField.addClassName("custom-password");
    }

    private void setPasswordFieldListeners(PasswordField passwordField) {
        // Con EAGER se disparan los eventos en el momento de teclear, no al perder el foco.
        passwordField.setValueChangeMode(ValueChangeMode.EAGER);

        passwordField.addValueChangeListener(event -> {
            // event.getSource() nos devuelve passwordField.
//            event.getSource().setHelperText("char: " + event.getValue().length() + " / " + MAX_CHAR);
            updatePasswordStrength(event.getValue().length());
        });
    }

    private void setPasswordFieldProperties(PasswordField passwordField) {
        passwordField.setMaxLength(MAX_CHAR);
        passwordField.setMinLength(0);

        // Establece el patrón de caracteres permitidos, pero permite teclear caracteres inválidos.
//        passwordField.setPattern("^[A-Za-z0-9]*$");

        // Establece el patrón de caracteres permitidos, pero NO permite teclear caracteres inválidos.
        passwordField.setAllowedCharPattern("^[A-Za-z0-9]*$");

        // Para mostrar un botón y poder limpiar el campo.
        passwordField.setClearButtonVisible(true);

        // Por defecto ya muestra un botón para poder visualizar el password.
        passwordField.setRevealButtonVisible(true);

        // Indicamos el mensaje de error.
        passwordField.setErrorMessage("Password can contain only alphanumeric characters");

        // Habilitamos/Deshabilitamos el campo.
        passwordField.setEnabled(true);

        // Mostramos/Ocultamos el campo.
        passwordField.setVisible(true);

        passwordStrength(passwordField);
    }

    // Muestra la fortaleza de un password.
    // Añadiendo un texto helper usando un Div con un H6 y un Span.
    private void passwordStrength(PasswordField passwordField) {
        Div passwordStrength = new Div();
        passwordStrengthText = new Span("Weak");
        passwordStrengthText.getStyle().set("color", "red");
        passwordStrengthText.getStyle().set("font-weight", "bolder");
        passwordStrength.add(new H6("Password Strength: "), passwordStrengthText);

        passwordField.setHelperComponent(passwordStrength);
    }

    private void updatePasswordStrength(int length) {
        if (length >= 6) {
            passwordStrengthText.setText("High");
            passwordStrengthText.getStyle().set("color", "green");
        } else if (length >= 4) {
            passwordStrengthText.setText("Medium");
            passwordStrengthText.getStyle().set("color", "orange");
        } else {
            passwordStrengthText.setText("Weak");
            passwordStrengthText.getStyle().set("color", "red");
        }
    }

}
