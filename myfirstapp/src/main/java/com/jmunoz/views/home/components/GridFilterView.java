package com.jmunoz.views.home.components;

import com.jmunoz.entity.Person;
import com.jmunoz.util.DataGenerator;
import com.jmunoz.util.PersonFilter;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.*;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Route("gridfilterview")
public class GridFilterView extends VerticalLayout {

    private final GridListDataView<Person> dataView;
    private List<Person> people = DataGenerator.getPersons();
    private TextField searchField;
    private NumberField ageFilter;
    private HeaderRow headerRow;
    private Grid.Column<Person> columnFirstName;
    private Grid.Column<Person> columnLastName;
    private Grid.Column<Person> columnGender;
    private Grid.Column<Person> columnSalary;

    public GridFilterView() {
        Grid<Person> gridPerson = new Grid<>(Person.class, false);
        dataView = gridPerson.setItems(people);

        setGridColumns(gridPerson);
        seGridListeners(gridPerson);
        setGridFilter();
        setGridPersonFilter(gridPerson);
        gridPerson.addClassName("person-grid");

        HorizontalLayout hl = new HorizontalLayout();
        hl.setWidthFull();
        hl.add(searchField, ageFilter);

        add(hl, gridPerson);
    }

    private void setGridColumns(Grid<Person> gridPerson) {
        gridPerson.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);

        columnFirstName = gridPerson.addColumn(Person::getFirstName).setFooter(createPersonCount(people));
        columnLastName = gridPerson.addColumn(Person::getLastName);
        gridPerson.addColumn(Person::getAge).setHeader("Age").setSortable(true);
        gridPerson.addColumn(Person::getEmail).setHeader("Email").setSortable(false).setResizable(true);

        columnGender = gridPerson.addColumn(new ComponentRenderer<>(person -> {
            if (person.getGender().equalsIgnoreCase("Male")) {
                return VaadinIcon.MALE.create();
            } else {
                return VaadinIcon.FEMALE.create();
            }
        })).setHeader("Gender").setComparator(Person::getGender).setWidth("5px").setTooltipGenerator(Person::getGender);

        columnSalary = gridPerson.addColumn(Person::getSalary)
                .setHeader("Salary")
                .setSortable(true)
                .setTextAlign(ColumnTextAlign.END)
                .setWidth("20px")
                .setFooter(createSalaryFooterText(people));

        headerRow = gridPerson.prependHeaderRow();
        headerRow.join(columnFirstName, columnLastName).setText("Full Name");
    }

    private void seGridListeners(Grid<Person> gridPerson) {
        gridPerson.setSelectionMode(Grid.SelectionMode.MULTI);

        GridMultiSelectionModel<Person> selectionModel = (GridMultiSelectionModel<Person>) gridPerson.getSelectionModel();
        selectionModel.setDragSelect(true);

        gridPerson.addSelectionListener(selectionEvent -> {
            Notification.show(selectionEvent.getAllSelectedItems().stream().map(Person::getFirstName).collect(Collectors.joining(", ")));
        });

        gridPerson.addItemDoubleClickListener(personItemDoubleClickEvent -> {
            Notification.show(personItemDoubleClickEvent.getItem().getProfession());
        });
    }

    private void setGridFilter() {
        // Añadir filtro a la data view usando un TextField y filtrando lo introducido.
        searchField = new TextField("Search");
        searchField.setWidth("50%");
        searchField.setPlaceholder("Search");
        searchField.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
        searchField.setValueChangeMode(ValueChangeMode.EAGER);
        searchField.addValueChangeListener(event -> {
           dataView.refreshAll();
        });

        // Añadir filtro a la data view usando un NumberField y filtrando lo introducido.
        ageFilter = new NumberField("Search on Age");
        ageFilter.setWidth("50%");
        ageFilter.setStepButtonsVisible(false);
        ageFilter.setPlaceholder("Search on age");
        ageFilter.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
        ageFilter.setValueChangeMode(ValueChangeMode.EAGER);
        ageFilter.addValueChangeListener(event -> {
            dataView.refreshAll();
        });

        dataView.addFilter(person -> {
            String searchTerm = searchField.getValue();
            Double searchAge = ageFilter.getValue();

            if (searchTerm.isEmpty() && (searchAge == null || searchAge == 0.0)) {
                return true;
            }

            boolean matchFirstName = true;
            boolean matchLastName = true;
            boolean matchGender = true;
            if (!searchTerm.isEmpty()) {
                matchFirstName = matchesTerm(person.getFirstName(), searchTerm);
                matchLastName = matchesTerm(person.getLastName(), searchTerm);
                matchGender = matchesTerm(String.valueOf(person.getGender()), searchTerm);
            }

            boolean matchAge = true;
            if (searchAge != null && searchAge != 0.0) {
                matchAge = compareAge(person.getAge(), searchAge);
            }

            return (matchFirstName || matchLastName || matchGender) && matchAge;
        });
    }

    private void setGridPersonFilter(Grid<Person> gridPerson) {
        PersonFilter personFilter = new PersonFilter(dataView);

        gridPerson.getHeaderRows().clear();
        headerRow = gridPerson.appendHeaderRow();

        headerRow.getCell(columnFirstName).setComponent(
                createFilterHeader("Name", personFilter::setFirstName));
        headerRow.getCell(columnGender).setComponent(
                createFilterHeader("Gender", personFilter::setGender));
        headerRow.getCell(columnSalary).setComponent(
                createFilterHeaderForSalary("Salary", personFilter::setSalary));
    }

    private boolean compareAge(int age, Double searchTerm) {
        return searchTerm <=  age;
    }

    private boolean matchesTerm(String value, String searchTerm) {
        return value.toLowerCase().contains(searchTerm.toLowerCase());
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

    private static Component createFilterHeader(String labelText,
                                                Consumer<String> filterChangeConsumer) {
        NativeLabel label = new NativeLabel(labelText);
        label.getStyle().set("padding-top", "var(--lumo-space-m)")
                .set("font-size", "var(--lumo-font-size-xs)");
        TextField textField = new TextField();
        textField.setValueChangeMode(ValueChangeMode.EAGER);
        textField.setClearButtonVisible(true);
        textField.addThemeVariants(TextFieldVariant.LUMO_SMALL);
        textField.setWidthFull();
        textField.getStyle().set("max-width", "100%");
        textField.addValueChangeListener(
                e -> filterChangeConsumer.accept(e.getValue()));
        VerticalLayout layout = new VerticalLayout(label, textField);
        layout.getThemeList().clear();
        layout.getThemeList().add("spacing-xs");

        return layout;
    }

    private static Component createFilterHeaderForSalary(String labelText,
                                                         Consumer<Integer> filterChangeConsumer) {
        NativeLabel label = new NativeLabel(labelText);
        label.getStyle().set("padding-top", "var(--lumo-space-m)")
                .set("font-size", "var(--lumo-font-size-xs)");
        TextField textField = new TextField();
        textField.setValueChangeMode(ValueChangeMode.EAGER);
        textField.setClearButtonVisible(true);
        textField.addThemeVariants(TextFieldVariant.LUMO_SMALL);
        textField.setWidthFull();
        textField.getStyle().set("max-width", "100%");
        textField.addValueChangeListener(
                e -> filterChangeConsumer.accept(Integer.valueOf(e.getValue())));
        VerticalLayout layout = new VerticalLayout(label, textField);
        layout.getThemeList().clear();
        layout.getThemeList().add("spacing-xs");

        return layout;
    }
}
