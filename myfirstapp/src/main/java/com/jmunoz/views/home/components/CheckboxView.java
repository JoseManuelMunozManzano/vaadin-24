package com.jmunoz.views.home.components;

import com.jmunoz.entity.Employee;
import com.jmunoz.util.DataGenerator;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.HashSet;
import java.util.List;

@Route("checkbox")
public class CheckboxView extends VerticalLayout {

    private final CheckboxGroup<String> chkbxWeekdays;
    private final Checkbox chkSelectAll;

    public CheckboxView() {
        Checkbox chkBxAgree = new Checkbox();
        // Indicamos la etiqueta.
        chkBxAgree.setLabel("I agree to terms and conditions");
        // Indicamos valor. Por defecto es false.
        chkBxAgree.setValue(false);

        // Creamos un grupo checkbox.
        CheckboxGroup<Employee> chkbxEmployee = new CheckboxGroup<>();
        setCheckboxGroupEmployeeProperties(chkbxEmployee);

        // Creamos otro checkbox.
        chkSelectAll = new Checkbox();
        chkSelectAll.setLabel("Select All");
        // Activado parcialmente. Esto solo puede conseguirse programáticamente.
        chkSelectAll.setIndeterminate(true);
        setChkSelectAllListeners(chkSelectAll);

        // Creamos otro grupo checkbox.
        chkbxWeekdays = new CheckboxGroup<>();
        setCheckboxGroupWeekDaysProperties();

        // ESTILIZANDO CHECKBOX USANDO CSS Y SHADOW DOM
        // Ver el fuente main/frontend/themes/my-app/components/checkboxStyle.css
        // Ese css se importa en main/frontend/themes/my-app/styles.css
        chkBxAgree.addClassName("checkbox-agree");
        chkbxEmployee.addClassName("checkbox-employee");

        add(chkBxAgree, chkbxEmployee, chkSelectAll ,chkbxWeekdays);
    }

    private void setChkSelectAllListeners(Checkbox chkSelectAll) {
        // Si activamos este checkbox, se activarán todos los valores de chbxWeekdays.
        // Y si desactivamos este checkbox, se desactivarán todos los valores de chbxWeekdays.
        chkSelectAll.addValueChangeListener(event -> {
            if (event.getValue()) {
                chkbxWeekdays.select(DataGenerator.getWeekdaysMap().keySet());
            } else {
                chkbxWeekdays.deselectAll();
            }
        });
    }

    private void setCheckboxGroupWeekDaysProperties() {
        // Indicamos su etiqueta.
        chkbxWeekdays.setLabel("Weekdays");

        // Añadimos los items que conforman el grupo de checkbox.
        // El item es el formato ISO de los días de la semana, pero como etiqueta
        // se muestra el nombre del día de la semana.
        chkbxWeekdays.setItems(DataGenerator.getWeekdaysMap().keySet());
        chkbxWeekdays.setItemLabelGenerator(weekday -> DataGenerator.getWeekdaysMap().get(weekday));

        // Activamos por defecto algún valor usando setValue.
        HashSet<String> selectedWeekdays = new HashSet<>();
        selectedWeekdays.add("SUN");
        selectedWeekdays.add("MON");
//        chkbxWeekdays.setValue(selectedWeekdays);

        // También podemos seleccionar en vez de usar setValue()
        chkbxWeekdays.select(selectedWeekdays);

        // Aquí mostramos el valor real del item.
        chkbxWeekdays.addValueChangeListener(event -> {
//            Notification.show(event.getValue().toString());

            // Si activamos manualmente alguno de los valores de chbxWeekdays, chkSelectAll quedará
            // parcialmente activado.
            // Si los desactivamos todos, chkSelectAll quedará desactivado.
            // Si los activamos todos, chkSelectAll quedará activado.
            if (event.getValue().size() == DataGenerator.getWeekdaysMap().size()) {
                chkSelectAll.setValue(true);
                chkSelectAll.setIndeterminate(false);
            } else if (event.getValue().isEmpty()) {
                chkSelectAll.setValue(false);
                chkSelectAll.setIndeterminate(false);
            } else {
                chkSelectAll.setIndeterminate(true);
            }
        });
    }

    private void setCheckboxGroupEmployeeProperties(CheckboxGroup<Employee> chkbxEmployee) {
        // Indicamos la etiqueta.
        chkbxEmployee.setLabel("Employee");

        // Añadimos los items que conforman el grupo de checkbox.
        List<Employee> items = DataGenerator.getEmployees();
        chkbxEmployee.setItems(items);

        // Cambiamos el label por defecto de cada checkbox por el que deseamos.
        // También podríamos haber creado en la clase Employee, el méto-do toString() con el texto deseado y ya estaría,
        // pero en ese caso ese sería el label para toda la aplicación.
        chkbxEmployee.setItemLabelGenerator(emp -> emp.getName() + " " + emp.getProfession());

        // Marcamos los elementos que deseamos.
        chkbxEmployee.select(items.get(1), items.get(3));

        // Si queremos deseleccionar todos
//        chkbxEmployee.deselectAll();

        // Cambiar la orientación.
        // Por defecto, los distintos valores de un checkbox group se ven horizontalmente.
        // Con este theme variant, se pueden visualizar verticalmente.
        chkbxEmployee.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);

        // Hacer que los items sean de solo lectura.
//        chkbxEmployee.setReadOnly(true);

        // Habilitar/Inhabilitar funciona algo parecido a setReadOnly.
//        chkbxEmployee.setEnabled(false);
    }
}
