package com.jmunoz.views.home.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.ElementFactory;
import com.vaadin.flow.router.Route;

@Route("formlayout")
public class FormLayoutView extends VerticalLayout {

    private final TextField txtFirstName;
    private final TextField txtLastName;
    private final TextField txtAddress;
    private final TextField txtPhone;
    private final HorizontalLayout hLayout;
    private final Button btnSubmit;
    private final Button btnReset;

    public FormLayoutView() {
        txtFirstName = new TextField();
        txtLastName = new TextField();
        txtAddress = new TextField();
        txtPhone = new TextField();

        // Los botones van a estar en una nueva fila, centrados.
        hLayout = new HorizontalLayout();
        hLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        btnSubmit = new Button("Submit");
        btnReset = new Button("Reset");
        hLayout.add(btnSubmit, btnReset);

        // FormLayout es responsivo al tamaño de la pantalla.
        // Si se reduce mucho coloca todos los items en una sola columna.
        // Si se agranda, muestra los items por defecto en dos columnas.
        // No podemos añadir padding o spacing.
        FormLayout formLayout = new FormLayout();
        setFormLayoutProperties(formLayout);
        stylingFormLayout(formLayout);
        formLayout.add(hLayout);
        formLayout.setColspan(hLayout, 6);

        add(formLayout);
    }

    private void stylingFormLayout(FormLayout formLayout) {
        // ESTILOS INLINE
//        formLayout.getStyle().set("background", "lightgray");

        // USANDO CSS
        // Ver el fuente main/frontend/themes/my-app/components/formLayoutStyle.css
        // Ese css se importa en main/frontend/themes/my-app/styles.css
        formLayout.addClassName("flPrimary");
    }

    private void setFormLayoutProperties(FormLayout formLayout) {
        // Indicamos el número de columnas por fila (por defecto 2)
        // Indicamos el minWidth y el número de columnas.
        // Esto lo que quiere decir es que si el tamaño mínimo de la pantalla es de 200px, el
        // número de columnas por fila será 2.
        // Pero si el tamaño mínimo de la pantalla es de 500px, entonces el número de columnas
        // por fila será 4.
        // Y, si el tamaño mínimo de la pantalla es de 1000px, entonces el número de columnas
        // por fila será 6.
        //
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("200px", 2),
                new FormLayout.ResponsiveStep("500px", 4),
                new FormLayout.ResponsiveStep("1000px", 6)
        );

        // Para que el label salga arriba.
        // Usando addFormItem por defecto el label sale a la izquierda.
        //
//        formLayout.setResponsiveSteps(
//                new FormLayout.ResponsiveStep("0", 1, LabelsPosition.TOP),
//                new FormLayout.ResponsiveStep("20em", 2, LabelsPosition.TOP)
//        );

        // Aseguramos que todos los campos ocupen el 100% del ancho de su celda (si queremos).
        txtFirstName.setWidthFull();
        txtLastName.setWidthFull();
        txtAddress.setWidthFull();
//        txtPhone.setWidthFull();

        // Otra forma de añadir items a nuestro FormLayout.
        // Se indica el item y su label. De esta forma el label aparece a la izquierda del input.
        // Usando el méto-do add() el label aparece encima del input.
        // CUIDADO: Si se mezcla con el méto-do add(), puede dar lugar a inconsistencias.
        // Mejor hacerlo solo de una forma, con add() o con addFormItem().
        // O jugar con los distintos layouts.
        FormLayout.FormItem itemFirstName = formLayout.addFormItem(txtFirstName, "First Name");
        FormLayout.FormItem itemLastName = formLayout.addFormItem(txtLastName, "Last Name");
        FormLayout.FormItem itemAddress = formLayout.addFormItem(txtAddress, "Address");
        FormLayout.FormItem itemPhone = formLayout.addFormItem(txtPhone, "Phone");

        // Cambiamos el número de columnas por fila por defecto.
        // Siempre será 5 columnas por fila, independientemente del tamaño de la pantalla.
        //
//        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0px", 5));

        // Configuramos cuantas columnas ocupa cada item.
        // Importante indicarlas todas para evitar problemas de solapamiento de labels con otros inputs.
        formLayout.setColspan(itemFirstName, 2); // media fila
        formLayout.setColspan(itemLastName, 2);  // media fila
        formLayout.setColspan(itemAddress, 4);   // fila entera (en 4 columnas)
        formLayout.setColspan(itemPhone, 2);     // media fila

        // Visibilizamos/invisibilizamos todos los componentes del layout (y el layout)
//        formLayout.setVisible(false);

        // Habilitamos/Inhabilitamos todos los componentes de un Layout.
//        formLayout.setEnabled(false);

        // Para los botones, forzamos una nueva fila.
        formLayout.getElement().appendChild(ElementFactory.createBr());
    }
}
