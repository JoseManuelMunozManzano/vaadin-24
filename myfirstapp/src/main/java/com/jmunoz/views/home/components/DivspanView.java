package com.jmunoz.views.home.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("divspan")
public class DivspanView extends VerticalLayout {

    public DivspanView() {

        Button btn1 = new Button("Button 1");
        Button btn2 = new Button("Button 2");
        Button btn3 = new Button("Button 3");
        Button btn4 = new Button("Button 4");
        Button btn5 = new Button("Button 5");
        Button btn6 = new Button("Button 6");

        // Div y componentes añadidos al mismo.
        // Se le añade un título (se ve al inspeccionar el elemento), un texto y tres botones.
        Div divPrimary = new Div();
        divPrimary.setTitle("Div Primary");
        divPrimary.setText("This is div");
        divPrimary.add(btn1, btn2);
        divPrimary.setEnabled(true);
        divPrimary.setVisible(true);

        // Eliminar componentes de un div.
//        divPrimary.remove(btn2);

        // Eliminar todos los componentes de un div.
//        divPrimary.removeAll();

        // Sustituir un componente. Se indica el componente antiguo y el nuevo.
        divPrimary.replace(btn1, btn3);

        // Dando estilos a un div
//        divPrimary.addClassName("divStyle");
        divPrimary.getStyle().set("background", "red");

        // Span
        // A su constructor podemos pasarle un componente o texto.
        // Div tiene el méto-do replace(), que no tiene Span, pero más o menos Span tiene lo mismo que un Div.
        Span spanPrimary = new Span(btn4, btn5, btn6);

        add(divPrimary, spanPrimary);
    }
}
