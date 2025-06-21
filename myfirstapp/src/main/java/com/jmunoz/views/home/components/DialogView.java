package com.jmunoz.views.home.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.dialog.DialogVariant;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("dialog")
public class DialogView extends VerticalLayout {

    private final Dialog dialog;
    private final Button btnShow;
    private final ConfirmDialog confirmDialog;
    private final Button btnShowConfirmDialog;

    public DialogView() {

        // Dialog - De propósito general.
        dialog = new Dialog();
        setDialogProperties();
        stylingDialog();

        btnShow = new Button("Show Dialog");
        setButtonListeners();

        // ConfirmDialog - Especializado en confirmaciones.
        confirmDialog = new ConfirmDialog();
        setConfirmDialogProperties();

        btnShowConfirmDialog = new Button("Show Confirm Dialog");
        setButtonConfirmDialogListeners();


        // Los dialogs no se añaden al layout, sino que se abren y se cierran, generalmente
        // en click listener de botones.
        add(btnShow, btnShowConfirmDialog);
    }

    private void setButtonConfirmDialogListeners() {
        btnShowConfirmDialog.addClickListener(event -> {
           confirmDialog.open();
        });
    }

    private void setConfirmDialogProperties() {
        // Indicamos título del dialog.
        confirmDialog.setHeader("About Us");

        // Hacer que el confirm dialog se cierre al pulsar Escape. Por defecto es true.
        confirmDialog.setCloseOnEsc(true);

        // En un confirm dialog no existe setModal(), setResizable() ni setDraggable()

        // Indicamos el texto del dialog.
        confirmDialog.setText("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sint, iste tempora. Vitae odit aspernatur quasi tempora minus? Eveniet qui, totam inventore nostrum, similique, tempore vel corporis placeat adipisci nesciunt error.");

        // Por defecto aparece un botón de Confirmación, cuyo texto podemos cambiar.
        // Vemos su Event Listener.
        confirmDialog.setConfirmText("Are you sure to confirm?");
        confirmDialog.addConfirmListener(event -> {
            Notification.show("Confirmed");
        });

        // Añadimos botón de Reject. Por defecto a false.
        // Se le puede cambiar el texto por defecto.
        // Vemos su Event Listener.
        confirmDialog.setRejectable(true);
        confirmDialog.setRejectText("Reject This");
        confirmDialog.addRejectListener(event -> {
            Notification.show("Rejected");
        });

        // Se le puede cambiar el texto por defecto.
        // Añadimos botón de Cancelar. Por defecto a false.
        // Vemos su Event Listener.
//        confirmDialog.setCancelable(true);
//        confirmDialog.setCancelText("Cancel This");
//        confirmDialog.addCancelListener(event -> {
//            Notification.show("Cancelled");
//        });

        // Versión alternativa para crear un botón de Cancelar (en este caso), añadiendo el texto y su Event Listener.
        confirmDialog.setCancelButton("Cancel By Button", event -> {
            Notification.show("Cancel by Button");
        });
    }

    private void setButtonListeners() {
        btnShow.addClickListener(event -> {
            dialog.open();
        });
    }

    private void stylingDialog() {
        // THEMES VARIANTS
        // LUMO_NO_PADDING reduce el tamaño del dialog para eliminar el padding extra.
        dialog.addThemeVariants(DialogVariant.LUMO_NO_PADDING);

        // USANDO CSS
        // Ver el fuente main/frontend/themes/my-app/components/dialogStyle.css
        // Ese css se importa en main/frontend/themes/my-app/styles.css
    }

    private void setDialogProperties() {
        // Indicamos título del dialog.
        dialog.setHeaderTitle("About Us");

        // Por defecto modal es true.
        // Indicar false cuando el usuario necesite acceder al contenido debajo del cuadro de diálogo
        // y para tareas menos críticas, opcionales o de soporte.
        // Además, si se pulsa click fuera del dialog, con setModal(false) no se cierra, mientras que
        // con setModal(true) si se cierra.
        dialog.setModal(false);

        // Hacer que se puede mover el dialog.
        // Suele combinarse con setModal(false)
        dialog.setDraggable(true);

        // Hacer el dialog redimensionable.
        dialog.setResizable(true);

        // Hacer que el dialog se cierre al pulsar Escape. Por defecto es true.
        dialog.setCloseOnEsc(false);

        // Contenido del dialog.
        dialog.add(dialogContent());

        // Añadimos botones al footer de nuestro dialog.
        Button btnSave = new Button("Save");
        Button btnReset = new Button("Reset");
        dialog.getFooter().add(btnSave, btnReset);
    }

    private VerticalLayout dialogContent() {
        VerticalLayout verticalLayout = new VerticalLayout();

        H6 txtIpsum = new H6("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sint, iste tempora. Vitae odit aspernatur quasi tempora minus? Eveniet qui, totam inventore nostrum, similique, tempore vel corporis placeat adipisci nesciunt error.");

        Button btnClose = new Button(VaadinIcon.CLOSE.create());

        btnClose.addClickListener(x -> {
            dialog.close();
        });

        verticalLayout.add(btnClose, txtIpsum);
        verticalLayout.setHorizontalComponentAlignment(Alignment.END, btnClose);

        return verticalLayout;
    }
}
