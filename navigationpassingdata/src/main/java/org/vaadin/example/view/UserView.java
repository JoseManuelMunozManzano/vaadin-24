package org.vaadin.example.view;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;
import org.vaadin.example.util.NotificationUtil;

// Para hacerlo opcional pasamos ?
@Route("user/:userID?")
public class UserView extends VerticalLayout implements HasUrlParameter<String> {

    public UserView() {
        add(new H3("User View"));
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String parameter) {

        // Para Router Param
//        NotificationUtil.showSuccessNotification(parameter);

        // Para Route Templates
        String userID = beforeEvent.getRouteParameters().get("userID").toString();
        NotificationUtil.showSuccessNotification(userID);

        // Para Query Param

    }
}
