package com.example.application.views;

import com.example.application.service.OpenAIService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.progressbar.ProgressBarVariant;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@PageTitle("Chat")
@Route("")
@Menu(order = 0, icon = LineAwesomeIconUrl.COMMENTS, title = "AI Chat Bot")
public class ChatView extends VerticalLayout {

    private final OpenAIService openAIService;
    private String uploadedFilePath;

    public ChatView(OpenAIService openAIService) {
        this.openAIService = openAIService;
        setSizeFull();
        createChatForm();
    }

    private void createChatForm() {
        addClassNames("chat-view", LumoUtility.Width.FULL, LumoUtility.Display.FLEX, LumoUtility.Flex.AUTO);
        setSpacing(false);
        setSizeFull();

        MessageList list = new MessageList();
        list.setWidthFull();

        MessageInput input = new MessageInput();
        input.setWidthFull();

        ProgressBar progressBar = new ProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setVisible(false);
        progressBar.addThemeVariants(ProgressBarVariant.LUMO_SUCCESS);  // Initially hidden

        // File Upload Component
        MemoryBuffer buffer = new MemoryBuffer();
        Upload upload = new Upload(buffer);
        upload.setAcceptedFileTypes("image/png", "image/jpeg", "image/jpg");
        upload.addSucceededListener(event -> {
           try {
               String filename = event.getFileName();
               InputStream inputStream = buffer.getInputStream();
               File targetFile = new File("src/main/resources/" + filename);
               targetFile.getParentFile().mkdirs(); // Ensure directory exists
               Files.copy(inputStream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
               uploadedFilePath = filename;
           } catch (Exception e) {
               e.printStackTrace();
           }
        });

        input.addSubmitListener(submitEvent -> {
           String userMessageText = submitEvent.getValue();

            MessageListItem userMessage = new MessageListItem(userMessageText, Instant.now(), "ME");
            userMessage.setUserColorIndex(6);
            userMessage.setUserAbbreviation("ME");

            List<MessageListItem> items = new ArrayList<>(list.getItems());
            items.add(userMessage);
            list.setItems(items);

            // Show the progress bar when the task starts
            progressBar.setVisible(true);

            // Capture the current UI instance
            UI currentUI = UI.getCurrent();

            // Con esto evitamos que, durante el tiempo que la IA tarde en devolvernos la respuesta, se bloquee la pantalla.
            // Sin esto, ni siquiera aparecerá nuestro mensaje, ya que la pantalla queda bloqueada esperando la respuesta de ChatGpt.
            CompletableFuture.supplyAsync(() -> openAIService.sendMessage(userMessageText, uploadedFilePath))
                    .thenAccept(aiResponse -> {
                       // Use the captured UI instance to safely update the UI
                       currentUI.access(() -> {
                           MessageListItem aiMessage = new MessageListItem(aiResponse, Instant.now(), "AI");
                           aiMessage.setUserColorIndex(4);
                           aiMessage.setUserAbbreviation("AI");

                           List<MessageListItem> updatedItems = new ArrayList<>(list.getItems());
                           updatedItems.add(aiMessage);
                           list.setItems(updatedItems);

                           // Hide the progress bar when the task is complete
                           progressBar.setVisible(false);
                       });
                    })
                    .exceptionally(ex -> {
                       ex.printStackTrace();
                       currentUI.access(() -> progressBar.setVisible(false));
                       return null;
                    });
        });

        VerticalLayout chatLayout = new VerticalLayout(progressBar, upload, list, input);
        chatLayout.setSizeFull();
        chatLayout.expand(list);
        add(chatLayout);
    }
}
