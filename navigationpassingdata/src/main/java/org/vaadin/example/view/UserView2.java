package org.vaadin.example.view;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.vaadin.example.util.NotificationUtil;

import java.util.Optional;

// En este ejemplo lo hacemos opcional pasando ? y pasamos también un regex.
// En este caso probamos Route Templates usando BeforeEnterObserver
@Route("user2/:userID?([0-9]{1,9})/edit/:phone?")
public class UserView2 extends VerticalLayout implements BeforeEnterObserver {

    public UserView2() {
        add(new H3("User View 2"));
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        Optional<String> optionalUserID = beforeEnterEvent.getRouteParameters().get("userID");
        Optional<String> optionalPhone = beforeEnterEvent.getRouteParameters().get("phone");

        NotificationUtil.showSuccessNotification(optionalUserID.orElse(""));
        NotificationUtil.showSuccessNotification(optionalPhone.orElse(""));
    }
}
