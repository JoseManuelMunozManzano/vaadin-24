package com.jmunoz.views.home.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("splitlayout")
public class SplitLayoutView extends VerticalLayout {

    public SplitLayoutView() {

        // Importante indicar altura tanto del contenedor como del SplitLayout.
        setHeightFull();

        // SplitLayout toma dos argumentos de tipo Component.
        // Puede tomar un tercer argumento donde se indica la orientación de los componentes, por defecto HORIZONTAL.
//        SplitLayout splitLayout = new SplitLayout(primaryLayout(), secondaryLayout(), SplitLayout.Orientation.VERTICAL);
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setWidthFull();
        splitLayout.setHeightFull();

        // En vez de añadir los componentes en el constructor de SplitLayout, se pueden añadir de uno en uno.
        // Igual con la orientación.
        splitLayout.addToPrimary(primaryLayout());
//        splitLayout.addToSecondary(secondaryLayout());
        splitLayout.setOrientation(SplitLayout.Orientation.HORIZONTAL);

        // Se indica, en porcentaje, donde aparece el divisor.
        splitLayout.setSplitterPosition(40);

        // Podemos añadir un splitter como componente de otro splitter.
        SplitLayout splitLayoutSecondary = new SplitLayout(new H1("Hello World on Secondary Splitter"), secondaryLayout());
        splitLayoutSecondary.setSizeFull();
        splitLayoutSecondary.setOrientation(SplitLayout.Orientation.VERTICAL);

        splitLayout.addToSecondary(splitLayoutSecondary);

        add(splitLayout);
    }

    private Component primaryLayout() {
        FormLayout primaryLayout = new FormLayout();

        TextField txtFirstName = new TextField("First Name");
        TextField txtLastName = new TextField("Last Name");
        TextField txtAddress = new TextField("Address");
        TextField txtPhone = new TextField("Phone");

        primaryLayout.add(txtFirstName, txtLastName, txtAddress, txtPhone);
        return primaryLayout;
    }

    // También podría devolver un Component.
    private VerticalLayout secondaryLayout() {
        VerticalLayout verticalLayout = new VerticalLayout();

        TextArea txtDescription = new TextArea();
        txtDescription.setWidthFull();

        txtDescription.setValue("Lorem ipsum dolor, sit amet consectetur adipisicing elit. Eius ex impedit, obcaecati magni quidem necessitatibus vero iusto dolorem accusantium porro officia fugit beatae, a quo consequatur atque praesentium repellendus quisquam.");
        verticalLayout.add(txtDescription);

        return verticalLayout;
    }
}
