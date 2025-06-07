package com.jmunoz.views.home.components;

import com.jmunoz.entity.Person;
import com.jmunoz.util.DataGenerator;
import com.vaadin.flow.component.grid.*;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.router.Route;

import java.util.List;
import java.util.stream.Collectors;

@Route("gridview")
public class GridView extends VerticalLayout {

    private List<Person> people = DataGenerator.getPersons();

    public GridView() {
        // Hay varias formas de crear un grid.
        // Pero si queremos crear las columnas automáticamente hay que pasar el tipo.
        // Por defecto autoCreateColumns es true, lo que indica que se verá la data en el grid.
        // Indicando autoCreateColumns a false, no se ve la data en el grid, y tendremos que generar las columnas
        // manualmente.
        Grid<Person> gridPerson = new Grid<>(Person.class, false);
        // Forma EAGER (no LAZY) de cargar data en el grid.
        gridPerson.setItems(people);

        setGridColumns(gridPerson);
        seGridListeners(gridPerson);
        gridPerson.addClassName("person-grid");

        add(gridPerson);
    }

    private void setGridColumns(Grid<Person> gridPerson) {
        // Damos to-do el ancho.
        // gridPerson.setWidthFull();

        // Hacemos que se vean los bordes de las columnas y las filas de distinto color para diferenciarlas.
        gridPerson.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS, GridVariant.LUMO_ROW_STRIPES);

        // setPageSize
        // Este méto-do NO limita el número de filas visibles en la pantalla, sino que configura el tamaño de "page"
        // en grids con "lazy loading" (modo de datos asincrónico)
        // Funciona en combinación con la altura del grid (para que quepan las filas) y la altura del layout
        // que lo contiene.
        //
        // gridPerson.setHeight("800px");
        // gridPerson.setPageSize(10);

        // Si autoCreateColumns está a true, por defecto cada columna tiene un nombre y es ordenable.
        // Podemos indicar qué columnas no queremos que se puedan ordenar.
        // Podemos indicar el nombre de cada columna.
        // Podemos indicar muchas más cosas, como un tooltip, un comparator...
        //
//        gridPerson.getColumnByKey("profession").setSortable(false);
//        gridPerson.getColumnByKey("gender").setHeader("Person Gender").setSortable(false);

        // Añadiendo columnas del grid manualmente (autoCreateColumns a false)
        // Tenemos el control de qué columnas crear, y en qué orden.
        // Notar que se ha añadido la ordenación (si no se indica, por defecto está a false)
        // Notar que se ha añadido un footer para saber el número de personas.
        // Notar que la columna Email se puede redimensionar.
        Grid.Column<Person> columnFirstName = gridPerson.addColumn(Person::getFirstName).setFooter(createPersonCount(people));
        Grid.Column<Person> columnLastName = gridPerson.addColumn(Person::getLastName);
        gridPerson.addColumn(Person::getAge).setHeader("Age").setSortable(true);
        gridPerson.addColumn(Person::getEmail).setHeader("Email").setSortable(false).setResizable(true);
        // gridPerson.addColumn(Person::getGender).setHeader("Gender").setSortable(true);

        // Creación de una columna usando ComponentRenderer y que es ordenable. También le damos un tamaño y un tooltip.
        // Se usa ComponentRenderer para modelar una columna, basado en alguna decisión.
        // Se pueden añadir también botones o cualquier tipo de diseño.
        gridPerson.addColumn(new ComponentRenderer<>(person -> {
            if (person.getGender().equalsIgnoreCase("Male")) {
                return VaadinIcon.MALE.create();
            } else {
                return VaadinIcon.FEMALE.create();
            }
        })).setHeader("Gender").setComparator(Person::getGender).setWidth("5px").setTooltipGenerator(Person::getGender);

        // Alineaciones en columnas.
        // Además, se muestra en el pie el total.
        gridPerson.addColumn(Person::getSalary)
                .setHeader("Salary")
                .setSortable(true)
                .setTextAlign(ColumnTextAlign.END)
                .setWidth("20px")
                .setFooter(createSalaryFooterText(people));

        // Creación de una columna usando un Renderer, y un Comparator para poder ordenarlo.
        // También se congela, es decir se fija para que no se desplace horizontalmente al hacer scroll lateral.
        // Las columnas congeladas deben ir al principio o al final, según el caso.
        //
        // gridPerson.addColumn(createPersonRenderer()).setHeader("Person").setComparator(Person::getFirstName).setFrozen(true);

        // Agrupamos firstName y lastName
        HeaderRow headerRow = gridPerson.prependHeaderRow();
        headerRow.join(columnFirstName, columnLastName).setText("Full Name");
    }

    private void seGridListeners(Grid<Person> gridPerson) {

        // Indicamos si solo se puede seleccionar una fila, ninguna, o más de una fila.
        gridPerson.setSelectionMode(Grid.SelectionMode.MULTI);

        // Selección de filas usando el arrastre del ratón por el check de selección del grid.
        // Se tiene que indicar obligatoriamente Grid.SelectionMode.MULTI
        GridMultiSelectionModel<Person> selectionModel = (GridMultiSelectionModel<Person>) gridPerson.getSelectionModel();
        selectionModel.setDragSelect(true);

        gridPerson.addSelectionListener(selectionEvent -> {
            // Obtenemos el set de items seleccionados para poder hacer acciones.

            // Para Grid.SelectionMode.SINGLE
            // Notification.show(selectionEvent.getAllSelectedItems().iterator().next().getFirstName());

            // Para Grid.SelectionMode.MULTI
            Notification.show(selectionEvent.getAllSelectedItems().stream().map(Person::getFirstName).collect(Collectors.joining(", ")));
        });

        gridPerson.addItemDoubleClickListener(personItemDoubleClickEvent -> {
            Notification.show(personItemDoubleClickEvent.getItem().getProfession());
        });
    }

    private static Renderer<Person> createPersonRenderer() {
        return LitRenderer.<Person>of(
                        "<vaadin-horizontal-layout style=\"align-items: center;\" theme=\"spacing\">"
                                + "  <vaadin-vertical-layout style=\"line-height: var(--lumo-line-height-m);\">"
                                + "    <span> ${item.firstname}  ${item.lastname}</span>"
                                + "    <span style=\"font-size: var(--lumo-font-size-s); color: var(--lumo-secondary-text-color);\">"
                                + "      ${item.profession}" + "    </span>"
                                + "  </vaadin-vertical-layout>"
                                + "</vaadin-horizontal-layout>")
                .withProperty("firstname", Person::getFirstName)
                .withProperty("lastname", Person::getLastName)
                .withProperty("profession", Person::getProfession);
    }

    private static String createSalaryFooterText(List<Person> people) {
        double sumSalary = people.stream().mapToDouble(Person::getSalary).sum();

        return String.format("%s sum", sumSalary);
    }

    private static String createPersonCount(List<Person> people) {
        long totalRecords = people.stream().count();

        return String.format("%s total people", totalRecords);
    }
}
