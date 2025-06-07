package com.jmunoz.views.home.components;

import com.jmunoz.entity.Employee;
import com.jmunoz.util.DataGenerator;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;

@Route("select")
public class SelectView extends VerticalLayout {

    public SelectView() {
        Select<String> selectNames = new Select<>();
        setSelectProperties(selectNames);
        setSelectListeners(selectNames);

        Select<Employee> selectEmployee = new Select<>();
        setSelectEmployeesProperties(selectEmployee);

        // USANDO CSS
        // Ver el fuente main/frontend/themes/my-app/components/selectStyle.css
        // Ese css se importa en main/frontend/themes/my-app/styles.css
        selectNames.addClassName("name-select");
        selectNames.setOverlayClassName("name-select-overlay");

        add(selectNames, selectEmployee);
    }

    private void setSelectProperties(Select<String> selectNames) {
        selectNames.setLabel("Select Employee Name");
        selectNames.setItems(DataGenerator.getNames());

        selectNames.setPlaceholder("Employee Name");
        selectNames.setTooltipText("Select Valid Employee Name");
        selectNames.setErrorMessage("Employee Name should be valid");

        // Para que se vea un icono en el campo donde aparece el elemento seleccionado (no en cada elemento)
        Icon icon = new Icon(VaadinIcon.USER);
        selectNames.setPrefixComponent(icon);

        // Habilitar/Deshabilitar elementos del desplegable.
        // Funciona para elemento vacío con valor null.
        selectNames.setItemEnabledProvider(item -> item == null || !item.equalsIgnoreCase("Mike"));

        // Seleccionamos programáticamente un valor.
        selectNames.setValue("John");

        // Creamos un elemento vacío, para poder deseleccionar.
        selectNames.setEmptySelectionAllowed(true);

        // En vez de que el elemento vacío valga null, se puede indicar un valor
        selectNames.setEmptySelectionCaption("Empty Item");

        // Añadimos un nuevo componente.
        selectNames.addComponents("Mike", new Hr());
    }

    private void setSelectListeners(Select<String> selectNames) {
        selectNames.addValueChangeListener(event -> {
            Notification.show(event.getValue());
        });
    }

    private void setSelectEmployeesProperties(Select<Employee> selectEmployee) {
        selectEmployee.setLabel("Select Employee");
        selectEmployee.setItems(DataGenerator.getEmployees());

        // Cambiamos el label de cada uno de los elementos seleccionables.
        selectEmployee.setItemLabelGenerator(item -> item.getName() + " - " + item.getPhone());

        // Renderizamos los elementos utilizando otros componentes.
        selectEmployee.setRenderer(createEmployeeRenderer());
    }

    private ComponentRenderer<HorizontalLayout, Employee> createEmployeeRenderer() {
        return new ComponentRenderer<>(employee -> {
            HorizontalLayout wrapper = new HorizontalLayout();
            wrapper.setAlignItems(Alignment.CENTER);

            Image image = new Image();
            image.setSrc("images/"+employee.getId()+".png");
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
