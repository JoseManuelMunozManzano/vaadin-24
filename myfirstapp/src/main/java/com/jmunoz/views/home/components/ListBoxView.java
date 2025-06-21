package com.jmunoz.views.home.components;

import com.jmunoz.entity.Employee;
import com.jmunoz.util.DataGenerator;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route("listbox")
public class ListBoxView extends VerticalLayout {

    public ListBoxView() {

        ListBox<String> lstbxName = new ListBox<>();
        setListBoxProperties(lstbxName);
        setListBoxListeners(lstbxName);
        stylingListBox(lstbxName);

        MultiSelectListBox<Employee> lstbxEmployee = new MultiSelectListBox<>();
        setMultiSelectListBoxProperties(lstbxEmployee);
        setMultiSelectListBoxListeners(lstbxEmployee);

        add(lstbxName, lstbxEmployee);
    }

    private void stylingListBox(ListBox<String> lstbxName) {
        // USANDO CSS
        // Ver el fuente main/frontend/themes/my-app/components/listBoxStyle.css
        // Ese css se importa en main/frontend/themes/my-app/styles.css
        lstbxName.addClassName("listbox-name");
    }

    private void setMultiSelectListBoxListeners(MultiSelectListBox<Employee> lstbxEmployee) {
        lstbxEmployee.addValueChangeListener(event -> {
            Notification.show(event.getValue().toString());
        });
    }

    private void setMultiSelectListBoxProperties(MultiSelectListBox<Employee> lstbxEmployee) {
        // MultiSelectListBox a partir de empleados.
        // Pulsando en cualquier empleado se selecciona, sin deseleccionar el que ya estuviera seleccionado.
        List<Employee> employeeList = DataGenerator.getEmployees();
        lstbxEmployee.setItems(employeeList);

        // Indicamos los elementos que queremos que aparezcan seleccionados por defecto.
        lstbxEmployee.select(employeeList.getFirst(), employeeList.get(2));

        // Indicamos lo que queremos ver en la lista.
        // Esto es solo la etiqueta que se ve. Realmente, por debajo está to-do el objeto.
        //
//        lstbxEmployee.setItemLabelGenerator(item -> item.getName() + " - " + item.getProfession());

        // Diseño de la etiqueta utilizando distintos componentes.
        lstbxEmployee.setRenderer(createEmployeeRenderer());

        // Habilitar/Deshabilitar elementos. En este caso deshabilitamos Mike.
        lstbxEmployee.setItemEnabledProvider(item -> !item.getName().equalsIgnoreCase("Mike"));

        // Añadiendo un nuevo componente (línea horizontal) tras el primer employee.
        lstbxEmployee.addComponents(employeeList.getFirst(), new Hr());
    }

    private void setListBoxListeners(ListBox<String> lstbxName) {
        lstbxName.addValueChangeListener(event -> {
            Notification.show(event.getValue());
        });
    }

    private void setListBoxProperties(ListBox<String> lstbxName) {
        // ListBox a partir de nombres y aparece como seleccionado el que tiene el valor John.
        // Pulsando en cualquier elemento se selecciona ese y se deselecciona el que estuviera seleccionado.
        lstbxName.setItems(DataGenerator.getNames());
        lstbxName.setValue("John");

        // Se puede deshabilitar un elemento concreto de la lista, en este caso Mike.
        lstbxName.setItemEnabledProvider(item -> !item.equalsIgnoreCase("Mike"));

        // Se pueden añadir componentes.
        // Se indica tras que elemento se añade y qué se añade.
        // En el ejemplo indicamos que el componente Línea horizontal se añade después de Jenna.
        lstbxName.addComponents("Jenna", new Hr());
    }

    private ComponentRenderer<HorizontalLayout, Employee> createEmployeeRenderer() {
        return new ComponentRenderer<>(employee -> {
           HorizontalLayout wrapper = new HorizontalLayout();
           wrapper.setAlignItems(Alignment.CENTER);

            Image image = new Image();
            image.setSrc("images/" + employee.getId() + ".png");
            image.setAlt("Portrait of " + employee.getName());

            Div info = new Div();
            info.setText(employee.getName());

            Div profession = new Div();
            profession.setText(employee.getProfession());
            profession.getStyle()
                    .set("color", "var(--lumo-secondary-text-color)")
                    .set("font-size", "var(--lumo-font-size-s)");
            info.add(profession);

            wrapper.add(image, info);
            return wrapper;
        });
    }
}
