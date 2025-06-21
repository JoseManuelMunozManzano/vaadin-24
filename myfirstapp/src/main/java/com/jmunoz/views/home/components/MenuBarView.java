package com.jmunoz.views.home.components;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("menubar")
public class MenuBarView extends VerticalLayout {

    private MenuBar menuBar;
    private MenuItem itemHome, itemPortfolio, itemAboutUs, itemShare,
                     itemInstagram, itemFacebook, itemWhatsApp;

    private SubMenu subMenuAboutUs;
    private ComponentEventListener<ClickEvent<MenuItem>> listener;

    public MenuBarView() {
        menuBar = new MenuBar();
        // El listener lo tengo que crear antes, ya que se asigna a cada elemento del menú.
        setMenuItemListeners();
        setMenuBarProperties();

        // USANDO CSS
        // Ver el fuente main/frontend/themes/my-app/components/menuBarStyle.css
        // Ese css se importa en main/frontend/themes/my-app/styles.css
        menuBar.addClassName("my-menu");
        menuBar.setOverlayClassName("my-menu-overlay");

        add(menuBar);
    }

    private void setMenuItemListeners() {

        // Event Listener sobre un elemento de nuestro MenuBar.
//        itemHome.addClickListener(menuItemClickEvent -> {
//            Notification.show("Home item clicked");
//        });

        // Crear un listener para cada elemento es muy tedioso, así que podemos usar un ComponentEventListener.
        // Este listener se pasa a cada elemento.
        listener = menuItemClickEvent ->
                Notification.show(menuItemClickEvent.getSource().getText());
    }

    private void setMenuBarProperties() {
        // Añadimos elementos a nuestro MenuBar.
        // Notar que pasamos un ComponentEventListener como argumento a cada elemento.
        itemHome = menuBar.addItem("Home", listener);
        itemPortfolio = menuBar.addItem("Portfolio", listener);
        itemAboutUs = menuBar.addItem("About Us", listener);
        itemShare = menuBar.addItem("Share", listener);

        // Creamos un submenú y algunos elementos dentro de él.
        // Añadimos iconos a un elemento del subMenú.
        // Para submenús se puede indicar una línea horizontal para separar los distintos elementos.
        subMenuAboutUs = itemAboutUs.getSubMenu();
        itemInstagram = subMenuAboutUs.addItem("Instagram", listener);
        itemFacebook = subMenuAboutUs.addItem(getDivFacebook(), listener);
        subMenuAboutUs.addSeparator();
        itemWhatsApp = subMenuAboutUs.addItem("WhatsApp", listener);

        // Indicamos si el elemento es chequeable (por defecto a false)
        // Da un error si indicamos true y el elemento es un menú de nivel root,
        // es decir, solo funciona con elementos en submenús.
        itemInstagram.setCheckable(true);
        itemFacebook.setCheckable(true);

        // Indicamos elemento chequeados por defecto.
        itemFacebook.setChecked(true);

        // Podemos habilitar/deshabilitar elementos.
        itemShare.setEnabled(false);
        itemFacebook.setEnabled(false);
    }

    private Component getDivFacebook() {
        Icon iconFacebook = VaadinIcon.FACEBOOK.create();
        iconFacebook.setColor("blue");
        Span spanTxtFB = new Span("Facebook");

        return new Div(iconFacebook, spanTxtFB);
    }
}
