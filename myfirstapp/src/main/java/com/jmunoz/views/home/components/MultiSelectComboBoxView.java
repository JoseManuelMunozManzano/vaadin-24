package com.jmunoz.views.home.components;

import com.jmunoz.entity.Employee;
import com.jmunoz.util.DataGenerator;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.router.Route;

import java.util.List;
import java.util.stream.Collectors;

@Route("multiselectcombobox")
public class MultiSelectComboBoxView extends VerticalLayout {

    public MultiSelectComboBoxView() {
        // Creando un MultiSelectComboBoxView de un String.
        MultiSelectComboBox<String> cboNames = new MultiSelectComboBox<>("Names");
        setMultiSelectComboBoxViewProperties(cboNames);
        setMultiSelectComboBoxViewListeners(cboNames);
        // USANDO CSS
        // Ver el fuente main/frontend/themes/my-app/components/multiSelectComboBoxStyle.css
        // Ese css se importa en main/frontend/themes/my-app/styles.css

        // Creando un MultiSelectComboBoxView de un objeto personalizado.
        MultiSelectComboBox<Employee> cboEmployees = new MultiSelectComboBox<>("Employees");
        setMultiSelectComboBoxViewEmployeesProperties(cboEmployees);
        setMultiSelectComboBoxViewEmployeesListeners(cboEmployees);

        add(cboNames, cboEmployees);
    }

    private void setMultiSelectComboBoxViewEmployeesListeners(MultiSelectComboBox<Employee> cboEmployees) {
        cboEmployees.addValueChangeListener(event -> {
            String employeeNames = event.getValue().stream().map(Employee::getName).collect(Collectors.joining(", "));
            Notification.show(employeeNames);
        });
    }

    private void setMultiSelectComboBoxViewEmployeesProperties(MultiSelectComboBox<Employee> cboEmployees) {
        List<Employee> employees = DataGenerator.getEmployees();
        cboEmployees.setItems(employees);
        cboEmployees.setClearButtonVisible(true);
//        cboEmployees.setWidth("600px");

        // Generamos las etiquetas para cada elemento seleccionado.
        cboEmployees.setItemLabelGenerator(employee -> employee.getName() + " - " + employee.getProfession());

        // Renderizamos usando HTML el contenido del desplegable.
        cboEmployees.setRenderer(createEmployeeRenderer());

        // Cada elemento seleccionado, si su etiqueta no cabe en la horizontal, genera verticalmente una nueva línea.
//        cboEmployees.setAutoExpand(MultiSelectComboBox.AutoExpandMode.VERTICAL);

        // Cada elemento seleccionado, si su etiqueta no cabe en la horizontal, genera más espacio horizontal.
        cboEmployees.setAutoExpand(MultiSelectComboBox.AutoExpandMode.HORIZONTAL);

        // Los elementos seleccionados del desplegable aparecen al principio.
        cboEmployees.setSelectedItemsOnTop(true);
    }

    private void setMultiSelectComboBoxViewListeners(MultiSelectComboBox<String> cboNames) {
        cboNames.addValueChangeListener(event -> {
            String names = String.join(", ", event.getValue());
            Notification.show(names);
        });
    }

    private void setMultiSelectComboBoxViewProperties(MultiSelectComboBox<String> cboNames) {
        cboNames.setItems(DataGenerator.getNames());
        cboNames.setClearButtonVisible(true);
    }

    private Renderer<Employee> createEmployeeRenderer() {
        StringBuilder tpl = new StringBuilder();
        tpl.append("<div style=\"display: flex;\">");
        tpl.append("  <div>");
        tpl.append("    ${item.id} ${item.name}");
        tpl.append("    <div style=\"font-size: var(--lumo-font-size-s); color: red;\">${item.address}</div>");
        tpl.append("  </div>");
        tpl.append("</div>");

        return LitRenderer.<Employee>of(tpl.toString())
                .withProperty("id", Employee::getId)
                .withProperty("name", Employee::getName)
                .withProperty("address", Employee::getAddress);
    }
}
