package com.jmunoz.views.home.components;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextAreaVariant;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("textarea")
public class TextAreaView extends VerticalLayout {

    public TextAreaView() {
        TextArea txtDescription = new TextArea("Description");
        setTextAreaProperties(txtDescription);
        setTextAreaListeners(txtDescription);
        stylingTextArea(txtDescription);

        add(txtDescription);
    }

    private void stylingTextArea(TextArea txtDescription) {
        // THEMES VARIANTS
        txtDescription.addThemeVariants(TextAreaVariant.LUMO_ALIGN_RIGHT, TextAreaVariant.LUMO_HELPER_ABOVE_FIELD);

        // ESTILOS INLINE
//        txtDescription.getStyle().set("color", "red");
//        txtDescription.getStyle().setBackgroundColor("beige");

        // USANDO CSS
        // Ver el fuente main/frontend/themes/my-app/components/textAreaStyle.css
        // Ese css se importa en main/frontend/themes/my-app/styles.css
        txtDescription.addClassName("my-custom-textarea");
    }

    private void setTextAreaListeners(TextArea txtDescription) {

        // Indicamos que tras cada tecla pulsada se ejecute el Change Listener.
        txtDescription.setValueChangeMode(ValueChangeMode.EAGER);

        // Indicamos la longitud del texto introducido como un texto de ayuda.
        txtDescription.addValueChangeListener(event -> {
            event.getSource().setHelperText(event.getValue().length() + " / " + 1000);
        });
    }

    private void setTextAreaProperties(TextArea txtDescription) {
        // Establecemos longitud mínima y máxima permitidas, y su mensaje de error.
        txtDescription.setMinLength(4);
        txtDescription.setMaxLength(1000);
        txtDescription.setErrorMessage("Description should be between 4 to 1000 in length");

        // Indicamos un placeholder.
        txtDescription.setPlaceholder("Description");

        // Indicamos un valor y que no se puede informar en el TextArea, solo verlo.
//        txtDescription.setValue("Lorem, ipsum dolor sit amet consectetur adipisicing elit. Esse earum quas nobis placeat, molestiae odio libero praesentium provident labore id quo similique corporis facere quisquam eaque necessitatibus impedit molestias blanditiis.");
//        txtDescription.setReadOnly(true);

        // Visualizar botón de eliminación de texto.
        txtDescription.setClearButtonVisible(true);
    }
}
