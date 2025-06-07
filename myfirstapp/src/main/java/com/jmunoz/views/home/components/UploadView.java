package com.jmunoz.views.home.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.Route;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Route("upload")
public class UploadView extends VerticalLayout {

    private Upload fileUpload;
    private Upload filesUpload;

    public UploadView() {

        uploadOneFile();
        uploadMoreThanOneFile();

        // USANDO CSS
        // Ver el fuente main/frontend/themes/my-app/components/uploadStyle.css
        // Ese css se importa en main/frontend/themes/my-app/styles.css
        filesUpload.addClassName("image-upload");

        add(fileUpload, filesUpload);
    }

    private void uploadMoreThanOneFile() {
        // MultiFileMemoryBuffer permite seleccionar más de un fichero.
        MultiFileMemoryBuffer multiFileMemoryBuffer = new MultiFileMemoryBuffer();

        filesUpload = new Upload(multiFileMemoryBuffer);
        filesUpload.setAcceptedFileTypes(".png");
        // Se indica el máximo número de ficheros posibles a subir.
        filesUpload.setMaxFiles(3);
        // Se indica el máximo tamaño posible de un fichero. En el ejemplo 100Kb.
        filesUpload.setMaxFileSize(100000);
        // Se indica el texto (como componente, no String) que aparece. Por defecto es Drop files here
        filesUpload.setDropLabel(new Span("Upload files by dropping here"));
        // Se indica en el botón de subida un icono u otro botón (y se estiliza tanto aquí como en su archivo CSS)
        // filesUpload.setUploadButton(VaadinIcon.UPLOAD_ALT.create());
        Button uploadButton = new Button();
        uploadButton.setText("Upload images");
        uploadButton.getStyle().set("background", "red");
        filesUpload.setUploadButton(uploadButton);
        // Cambiar el icono que aparece en la parte de drag and drop.
        filesUpload.setDropLabelIcon(VaadinIcon.UPLOAD_ALT.create());
        // Se indica si se permite usar la parte de arrastrar y soltar. Por defecto está a true.
         filesUpload.setDropAllowed(true);

        filesUpload.addSucceededListener(succeededEvent -> {
            Notification.show(succeededEvent.getFileName() + " is uploaded successfully");

            // Para guardar cada uno de los ficheros en un path físico.
            InputStream inputStream = multiFileMemoryBuffer.getInputStream(succeededEvent.getFileName());

            File targetFile = new File("src/main/resources/" + succeededEvent.getFileName());

            try {
                // Convertimos el inputStream a un fichero.
                FileUtils.copyInputStreamToFile(inputStream, targetFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // Este listener es llamado cuando se rechaza la subida del fichero por la configuración indicada.
        filesUpload.addFileRejectedListener(fileRejectedEvent -> {
            Notification.show(fileRejectedEvent.getFileName() + " - " + fileRejectedEvent.getErrorMessage());
        });

        // Este listener es llamado cuando la subida del fichero da error.
        filesUpload.addFailedListener(failedEvent -> {
            Notification.show(failedEvent.getFileName() + " is failed to upload. " + failedEvent.getReason().getMessage());
        });
    }

    private void uploadOneFile() {
        // MemoryBuffer solo permite seleccionar un fichero.
         MemoryBuffer buffer = new MemoryBuffer();

        // El componente Upload requiere de un buffer de tipo MemoryBuffer.
        fileUpload = new Upload(buffer);
        // Solo vamos a aceptar ficheros de tipo .png. Permite una lista de Strings.
        fileUpload.setAcceptedFileTypes(".png");

        // Este listener es llamado cuando el fichero sube correctamente.
        fileUpload.addSucceededListener(succeededEvent -> {
            Notification.show(succeededEvent.getFileName() + " is uploaded successfully");

            // Para guardar el fichero en un path físico.
            InputStream inputStream = buffer.getInputStream();

            File targetFile = new File("src/main/resources/" + succeededEvent.getFileName());

            try {
                // Convertimos el inputStream a un fichero.
                FileUtils.copyInputStreamToFile(inputStream, targetFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
