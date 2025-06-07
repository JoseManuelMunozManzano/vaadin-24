package com.jmunoz.views.home.components;

import com.jmunoz.entity.Employee;
import com.jmunoz.util.DataGenerator;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.ComboBoxVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.router.Route;

@Route("combobox")
public class ComboBoxView extends VerticalLayout {

    public ComboBoxView() {
        // Paso 1
        // Creamos nuestro combobox.
        ComboBox<String> cboNames = new ComboBox<>("Name");

        // Paso 2
        // Establecemos los items del combobox.
        cboNames.setItems(DataGenerator.getNames());
        setComboBoxProperties(cboNames);
        setComboBoxListeners(cboNames);
        stylingComboBox(cboNames);

        // Creamos otro ComboBox.
        ComboBox<Boolean> cboYesNo = new ComboBox<>();
        cboYesNo.setItems(DataGenerator.getBooleans());

        // Creamos otro ComboBox.
        ComboBox<Integer> cboAge = new ComboBox<>("Age");
        cboAge.setItems(DataGenerator.getIntegers());
        // USANDO CSS
        // Ver el fuente main/frontend/themes/my-app/components/comboBoxStyle.css
        // Ese css se importa en main/frontend/themes/my-app/styles.css
        // Notar que hay que indicar el nombre de una clave para overlay.
        cboAge.setClassName("cboage");
        cboAge.setOverlayClassName("cboage");

        // Creamos otro ComboBox de un tipo personalizado.
        ComboBox<Employee> cboEmployee = new ComboBox<>("Employee");
        cboEmployee.setItems(DataGenerator.getEmployees());
        cboEmployee.setItemLabelGenerator(employee -> employee.getId() + " - " + employee.getName());
        cboEmployee.addValueChangeListener(event -> {
            Notification.show(event.getValue().getPhone() + " - " + event.getValue().getAddress());
        });
        // Podemos usar HTML y CSS para que los items se vean más bonitos.
        cboEmployee.setRenderer(createEmployeeRenderer());

        // Paso 3
        // Añadimos los combobox al layout.
        add(cboNames, cboYesNo, cboAge, cboEmployee);
    }

    private void stylingComboBox(ComboBox<String> cboNames) {
        // THEMES VARIANTS
        cboNames.addThemeVariants(ComboBoxVariant.LUMO_ALIGN_RIGHT, ComboBoxVariant.LUMO_SMALL);
    }

    private Renderer<Employee> createEmployeeRenderer() {
        StringBuilder tpl = new StringBuilder();
        tpl.append("<div style=\"display: flex;\">");
        tpl.append("  <div>");
        tpl.append("    ${item.keyid} ${item.keyname}");
        tpl.append("     <div style=\"font-size: var(--lumo-font-size-s); color: blue;\">${item.keyaddress}</div>");
        tpl.append("  </div");
        tpl.append("</div");

        return LitRenderer.<Employee> of(tpl.toString())
                .withProperty("keyid", Employee::getId)
                .withProperty("keyname", Employee::getName)
                .withProperty("keyaddress", Employee::getAddress);
    }

    private void setComboBoxListeners(ComboBox<String> cboNames) {
        cboNames.addValueChangeListener(event -> {
            Notification.show(event.getValue());
            Notification.show(event.getOldValue());
        });
    }

    private void setComboBoxProperties(ComboBox<String> cboNames) {
        // Otra forma de indicar el label.
        cboNames.setLabel("Name");

        // Indicamos un valor por defecto en el input del combobox.
        cboNames.setValue("Kyle");

        // Habilitar/Deshabilitar
//        cboNames.setEnabled(false);

        // Visualizar/Invisibilizar
//        cboNames.setVisible(false);

        // Mostrar el botón para eliminar el texto
//        cboNames.setClearButtonVisible(true);

        // Campo de solo lectura
//        cboNames.setReadOnly(true);

        // Hacer el campo obligatorio
//        cboNames.setRequired(true);
    }
}
