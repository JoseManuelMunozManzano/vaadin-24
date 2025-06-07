package com.jmunoz.views.home.components;

import com.jmunoz.entity.Employee;
import com.jmunoz.util.DataGenerator;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.router.Route;

@Route("radiobutton")
public class RadioButtonView extends VerticalLayout {

    public RadioButtonView() {
        RadioButtonGroup<String> rdbtnPermission = new RadioButtonGroup<>();
        setRadioButtonProperties(rdbtnPermission);
        setRadioButtonListeners(rdbtnPermission);

        RadioButtonGroup<Employee> rdbtnEmployee = new RadioButtonGroup<>();
        setRadioButtonEmployeeProperties(rdbtnEmployee);
        setRadioButtonEmployeeListeners(rdbtnEmployee);
        stylingRadioButton(rdbtnEmployee);

        add(rdbtnPermission, rdbtnEmployee);
    }

    private void stylingRadioButton(RadioButtonGroup<Employee> rdbtnEmployee) {
        // THEMES VARIANTS
        rdbtnEmployee.addThemeVariants(RadioGroupVariant.LUMO_HELPER_ABOVE_FIELD, RadioGroupVariant.LUMO_VERTICAL);

        // USANDO CSS
        // Ver el fuente main/frontend/themes/my-app/components/radioButtonStyle.css
        // Ese css se importa en main/frontend/themes/my-app/styles.css
        rdbtnEmployee.addClassName("radio-employee");
    }

    private void setRadioButtonEmployeeListeners(RadioButtonGroup<Employee> rdbtnEmployee) {
        rdbtnEmployee.addValueChangeListener(event -> {
            Notification.show(event.getValue().getName() + " - " + event.getValue().getProfession());
        });
    }

    private void setRadioButtonEmployeeProperties(RadioButtonGroup<Employee> rdbtnEmployee) {
        rdbtnEmployee.setLabel("Employee");
        rdbtnEmployee.setItems(DataGenerator.getEmployees());
        rdbtnEmployee.setHelperText("Select Employee");

        // Personalizamos las etiquetas de cada radiobutton.
        rdbtnEmployee.setItemLabelGenerator(employee -> employee.getName() + " - " + employee.getProfession());

        // Solo lectura.
        rdbtnEmployee.setReadOnly(false);

    }

    private void setRadioButtonListeners(RadioButtonGroup<String> rdbtnPermission) {
        rdbtnPermission.addValueChangeListener(event -> {
            Notification.show(event.getValue());
        });
    }

    private void setRadioButtonProperties(RadioButtonGroup<String> rdbtnPermission) {
        // Indicamos etiqueta
        rdbtnPermission.setLabel("Permission");

        // Enlazado de datos.
        rdbtnPermission.setItems(DataGenerator.getPermissions());

        // Habilita los elementos de radio button que no cumplan la condición.
        rdbtnPermission.setItemEnabledProvider(item -> {
            return !item.equalsIgnoreCase("Edit");
        });

        // Indicamos el elemento que estará seleccionado.
        rdbtnPermission.setValue("Read");

        // Indicamos texto de ayuda.
        rdbtnPermission.setHelperText("Select Permission");

        // Habilitar / Deshabilitar
        rdbtnPermission.setEnabled(true);
    }
}
