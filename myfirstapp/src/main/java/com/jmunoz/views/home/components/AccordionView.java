package com.jmunoz.views.home.components;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("accordion")
public class AccordionView extends VerticalLayout {

    public AccordionView() {
        Accordion accordionForm = new Accordion();
        // Hay que indicar el resumen y un componente.
        accordionForm.add("Personal Form", createPersonalForm());

        add(accordionForm);

        Accordion accordionDetails = new Accordion();
        accordionDetails.add("Personal Details", createPersonalDetails());

        add(accordionDetails);

        // AccordionPanel
        // Se agrupan accordion, separados por una línea divisoria
        // Cuando se visualiza uno (el primero por defecto), se oculta el otro.
        // En un Accordion Panel se indica el summary y los componentes que lo componen por separado.
        AccordionPanel accordionPanelForm = new AccordionPanel();
        accordionPanelForm.setSummaryText("Personal Form");
        accordionPanelForm.add(createPersonalForm());

        AccordionPanel accordionPanelDetails = new AccordionPanel();
        accordionPanelDetails.setSummary(new Span("Personal Details"));
        accordionPanelDetails.add(createPersonalDetails());
        setAccordionPanelProperties(accordionPanelDetails);
        setAccordionPanelListeners(accordionPanelDetails);

        Accordion accordionPersonal = new Accordion();
        accordionPersonal.add(accordionPanelForm);
        accordionPersonal.add(accordionPanelDetails);
        stylingAccordion(accordionPersonal);

        add(accordionPersonal);

        // Otros tres paneles de acordeón que se añaden a un nuevo acordeón.
        Accordion accordionLinks = new Accordion();

        AccordionPanel panelServices = new AccordionPanel();
        panelServices.setSummaryText("Services");
        panelServices.add(createContent(createStyledAnchor("#", "Consultation"),
                createStyledAnchor("#", "Product Development"),
                createStyledAnchor("#", "Cloud Services")));

        AccordionPanel panelAbout = new AccordionPanel();
        panelAbout.setSummaryText("About");
        panelAbout.add(createContent(createStyledAnchor("#", "About Us"),
                createStyledAnchor("#", "Contact Us")));

        AccordionPanel panelPortfolio = new AccordionPanel();
        panelPortfolio.setSummaryText("Portfolio");
        panelPortfolio.add(createContent(createStyledAnchor("#", "Content Management System"),
                createStyledAnchor("#", "Document Management System"),
                createStyledAnchor("#", "Learning Management System")));

        accordionLinks.add(panelServices);
        accordionLinks.add(panelAbout);
        accordionLinks.add(panelPortfolio);
        // Cambiar el accordion que se abre por defecto
        accordionLinks.open(panelAbout);

        add(accordionLinks);
    }

    private void stylingAccordion(Accordion accordionPersonal) {
        // ESTILIZANDO USANDO CSS
        // Ver el fuente main/frontend/themes/my-app/components/accordionStyle.css
        // Ese css se importa en main/frontend/themes/my-app/styles.css
        accordionPersonal.addClassName("personal-accordion");
    }

    private void setAccordionPanelListeners(AccordionPanel accordionPanelDetails) {
        accordionPanelDetails.addOpenedChangeListener(openedChangeEvent -> {
            if (openedChangeEvent.isOpened()) {
                Notification.show("Personal Details");
            }
        });
    }

    private void setAccordionPanelProperties(AccordionPanel accordionPanelDetails) {
        // Visualizar u ocultar el accordion o accordion panel.
        accordionPanelDetails.setVisible(true);

        // Habilitar o deshabilitar el accordion o accordion panel.
        accordionPanelDetails.setEnabled(true);

        // Indicamos un tooltip de ayuda, solo para accordion panel.
        accordionPanelDetails.setTooltipText("This panel contains personal details");
    }

    private FormLayout createPersonalForm() {
        TextField txtFirstName = new TextField("First Name");
        TextField txtLastName = new TextField("Last Name");
        TextField txtMobile = new TextField("Mobile");
        TextArea txtAddress = new TextArea("Address");
        Button btnSave = new Button("Save");
        Button btnReset = new Button("Reset");

        FormLayout layoutPersonalForm = new FormLayout();
        layoutPersonalForm.add(txtFirstName, txtLastName, txtMobile, txtAddress, btnSave, btnReset);

        layoutPersonalForm.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 2));

        return layoutPersonalForm;
    }

    private FormLayout createPersonalDetails() {
        H3 lblName = new H3("John");
        H3 lblMobile = new H3("+01102932839");
        H3 lblAddress = new H3("Street Thalia, King Road, Mars");

        FormLayout layoutDetailsForm = new FormLayout();
        layoutDetailsForm.add(lblName, lblMobile, lblAddress);

        layoutDetailsForm.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));

        return layoutDetailsForm;
    }

    private VerticalLayout createContent(Anchor... anchors) {
        VerticalLayout content = new VerticalLayout();
        content.setPadding(false);
        content.setSpacing(false);
        content.add(anchors);

        return content;
    }

    private Anchor createStyledAnchor(String href, String text) {
        Anchor anchor = new Anchor(href, text);
        anchor.getStyle().set("color", "var(--lumo-primary-text-color)");
        anchor.getStyle().set("text-decoration", "none");

        return anchor;
    }
}
