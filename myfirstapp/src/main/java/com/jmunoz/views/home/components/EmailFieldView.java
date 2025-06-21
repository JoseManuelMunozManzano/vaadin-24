package com.jmunoz.views.home.components;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.router.Route;

@Route("email")
public class EmailFieldView extends VerticalLayout {

    public EmailFieldView() {
        EmailField email = new EmailField("Email");
        setEmailFieldProperties(email);

        add(email);
    }

    private void setEmailFieldProperties(EmailField email) {
        // Indicamos el tamaño del ancho del campo
        email.setWidth("100%");

        // Habilitamos el campo
        email.setEnabled(true);

        // Cambiar el valor del campo programáticamente
        email.setValue("jmunoz@mail.com");

        // Indicar un tooltip
        email.setTooltipText("It should contain correct email");

        // Indicar mensaje de error
        email.setErrorMessage("Email is invalid");

        // Hacemos obligatorio informar el campo
        email.setRequired(true);
    }
}
