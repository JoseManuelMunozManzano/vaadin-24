package com.jmunoz.views.home.components;

import com.jmunoz.entity.Person;
import com.jmunoz.util.PersonDataProvider;
import com.jmunoz.util.PersonFilterForLazyLoading;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridMultiSelectionModel;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.Route;

@Route("practica")
public class PracticaView extends VerticalLayout {

    private Div divMenuBar;
    private SplitLayout slMain;
    private Span spFooter;

    public PracticaView() {
        setHeight("100%");

        setMenuBarProperties();
        setMainProperties();
        setFooter();

        add(divMenuBar, slMain, spFooter);
    }

    private void setMainProperties() {
        slMain = new SplitLayout(setAccordionGrid(), setForm());
        slMain.setWidthFull();
        slMain.setHeight("85%");
        slMain.setSplitterPosition(70);
    }

    private Component setAccordionGrid() {
        return new HorizontalLayout(setAccordionPanel(), setGridPeople());
    }

    private Component setGridPeople() {
        VerticalLayout vl = new VerticalLayout();
        vl.setPadding(false);

        PersonFilterForLazyLoading personFilterForLazyLoading = new PersonFilterForLazyLoading();
        PersonDataProvider personDataProvider = new PersonDataProvider();
        ConfigurableFilterDataProvider<Person, Void, PersonFilterForLazyLoading> configurableFilterDataProvider = personDataProvider.withConfigurableFilter();

        Grid<Person> gridPerson = new Grid<>(Person.class, false);
        gridPerson.setHeight("100%");
        gridPerson.setPageSize(20);
        gridPerson.setSelectionMode(Grid.SelectionMode.MULTI);
        gridPerson.setItems(configurableFilterDataProvider);
        gridPerson.addClassName("person-grid-practica");

        GridMultiSelectionModel<Person> selectionModel = (GridMultiSelectionModel<Person>) gridPerson.getSelectionModel();
        selectionModel.setDragSelect(true);

        gridPerson.addColumn(createPersonRenderer()).setHeader("Person").setResizable(true);
        gridPerson.addColumn(Person::getAge, "age").setHeader("Age").setSortable(true).setWidth("5px");
        gridPerson.addColumn(new ComponentRenderer<>(person -> {
                    if (person.getGender().equalsIgnoreCase("Male")) {
                        Icon male = VaadinIcon.MALE.create();
                        male.setColor("#453E8A");
                        return male;
                    } else {
                        Icon female = VaadinIcon.FEMALE.create();
                        female.setColor("#F8178D");
                        return female;
                    }
                })).setHeader("Gender")
                .setComparator(Person::getGender)
                .setSortProperty("gender")
                .setTooltipGenerator(person -> {
                    if (person.getGender().equalsIgnoreCase("Male")) {
                        return "Male";
                    } else {
                        return "Female";
                    }
                })
                .setWidth("5px")
                .setTooltipGenerator(Person::getGender);
        gridPerson.addColumn(Person::getSalary).setHeader("Salary ($)");
        gridPerson.addColumn(Person::getProfession, "profession").setHeader("Profession").setSortable(true);

        TextField searchField = new TextField();
        searchField.setWidth("50%");
        searchField.setPlaceholder("Search");
        searchField.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
        searchField.setValueChangeMode(ValueChangeMode.EAGER);

        searchField.addValueChangeListener(event -> {
            personFilterForLazyLoading.setSearchTerm(event.getValue());
            configurableFilterDataProvider.setFilter(personFilterForLazyLoading);
        });

        vl.add(searchField, gridPerson);

        return vl;
    }

    private Component setForm() {
        FormLayout fl = new FormLayout();
        fl.addClassName("fl-practica");
        fl.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("20em", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP)
        );

        TextField txtFirstName = new TextField();
        txtFirstName.setWidthFull();
        TextField txtLastName = new TextField();
        txtLastName.setWidthFull();
        NumberField numberAge = new NumberField();
        numberAge.setWidthFull();
        numberAge.setStepButtonsVisible(true);
        numberAge.setValue(25.0);
        EmailField email = new EmailField();
        email.setWidthFull();
        email.setErrorMessage("Email is invalid");
        ComboBox<String> cboGender = new ComboBox<>();
        cboGender.setOverlayClassName("cbo-gender-practica");
        cboGender.setWidthFull();
        cboGender.setItems("Male", "Female");
        cboGender.setValue("Male");
        NumberField numberSalary = new NumberField();
        numberSalary.setWidthFull();
        Icon dollarIcon = new Icon(VaadinIcon.DOLLAR);
        dollarIcon.setColor("yellow");
        numberSalary.setSuffixComponent(dollarIcon);
        TextField txtProfession = new TextField();
        txtProfession.setWidthFull();

        FormLayout.FormItem itemFirstName = fl.addFormItem(txtFirstName, "First Name");
        FormLayout.FormItem itemLastName = fl.addFormItem(txtLastName, "Last Name");
        FormLayout.FormItem itemAge = fl.addFormItem(numberAge, "Age");
        FormLayout.FormItem itemEmail = fl.addFormItem(email, "Email");
        FormLayout.FormItem itemGender = fl.addFormItem(cboGender, "Gender");
        FormLayout.FormItem itemSalary = fl.addFormItem(numberSalary, "Salary");
        FormLayout.FormItem itemProfession = fl.addFormItem(txtProfession, "Profession");

        fl.setColspan(itemFirstName, 1);
        fl.setColspan(itemLastName, 1);
        fl.setColspan(itemAge, 1);
        fl.setColspan(itemEmail, 2);
        fl.setColspan(itemGender, 1);
        fl.setColspan(itemSalary, 1);
        fl.setColspan(itemProfession, 2);

        HorizontalLayout hLayout = new HorizontalLayout();
        hLayout.setWidthFull();
        Button btnReset = new Button("Reset");
        btnReset.getStyle().set("color", "white");
        btnReset.getStyle().set("background", "red");
        Button btnSave = new Button("Save");
        hLayout.add(btnReset, btnSave);
        btnSave.getStyle().set("color", "white");
        btnSave.getStyle().set("background", "green");

        hLayout.setFlexGrow(1, btnReset);
        hLayout.setFlexGrow(1, btnSave);

        fl.add(hLayout);
        fl.setColspan(hLayout, 2);

        return fl;
    }

    private Accordion setAccordionPanel() {
        Accordion accordionLinks = new Accordion();
        accordionLinks.getStyle().setPaddingRight("20px");

        AccordionPanel panelServices = new AccordionPanel("Services");
        panelServices.add(createContentAccordion(createStyledAnchor("#", "Consultation"), createStyledAnchor("#", "Product"), createStyledAnchor("#", "Development")));

        AccordionPanel panelAbout = new AccordionPanel("About");
        panelAbout.add(createContentAccordion(createStyledAnchor("#", "About Us"), createStyledAnchor("#", "Contact Us")));

        AccordionPanel panelPortfolio = new AccordionPanel("Portfolio");
        panelPortfolio.add(createContentAccordion(createStyledAnchor("#", "Content"), createStyledAnchor("#", "Management"), createStyledAnchor("#", "System"), createStyledAnchor("#", "Document"), createStyledAnchor("#", "Learning")));

        accordionLinks.add(panelServices);
        accordionLinks.add(panelAbout);
        accordionLinks.add(panelPortfolio);

        accordionLinks.open(1);

        accordionLinks.addClassName("accordion-practica");

        return accordionLinks;
    }

    private VerticalLayout createContentAccordion(Anchor... anchors) {
        VerticalLayout content = new VerticalLayout();
        content.setPadding(false);
        content.setSpacing(false);
        content.add(anchors);

        return content;
    }

    private Anchor createStyledAnchor(String href, String text) {
        Anchor anchor = new Anchor(href, text);
        anchor.getStyle().set("color", "black");
        anchor.getStyle().set("text-decoration", "none");

        return anchor;
    }

    private void setMenuBarProperties() {
        divMenuBar = new Div();
        divMenuBar.setWidthFull();
        divMenuBar.getStyle().setPaddingTop("10px");
        divMenuBar.getStyle().setPaddingBottom("20px");
        divMenuBar.getStyle().setDisplay(Style.Display.FLEX);
        divMenuBar.getStyle().setJustifyContent(Style.JustifyContent.CENTER);

        MenuBar menuBar = new MenuBar();

        menuBar.addItem("Home");
        menuBar.addItem("Portfolio");
        menuBar.addItem("Services");
        menuBar.addItem("About Us");
        menuBar.addItem("Contact Us");
        menuBar.addClassName("menu-bar-practica");

        divMenuBar.add(menuBar);
    }

    private ComponentRenderer<HorizontalLayout, Person> createPersonRenderer() {
        return new ComponentRenderer<>(person -> {
            HorizontalLayout wrapper = new HorizontalLayout();
            wrapper.setAlignItems(Alignment.CENTER);

            Avatar avatar = new Avatar();
            avatar.setImage(person.getPictureUrl());

            Div info = new Div();
            info.setText(person.getFirstName() + " " + person.getLastName());

            Div email = new Div();
            email.setText(person.getEmail());
            email.getStyle()
                    .set("color", "var(--lumo-secondary-text-color)")
                    .set("font-size", "var(--lumo-font-size-s)");
            info.add(email);

            wrapper.add(avatar, info);
            return wrapper;
        });
    }

    private void setFooter() {
        spFooter = new Span("© 2025 JMMM. ALL RIGHTS RESERVED. EMPOWERING LEARNERS WITH CUTTING-EDGE TECH SKILLS. FOLLOW US AND CONTACT US IF YOU NEED. THIS TEXT IS A FOOTER EXAMPLE");
        spFooter.getStyle().setColor("#DEB75F");
    }
}