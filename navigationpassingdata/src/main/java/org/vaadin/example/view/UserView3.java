package org.vaadin.example.view;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;
import org.vaadin.example.util.NotificationUtil;

import java.util.List;
import java.util.Map;

// Ejemplo de Query Parameters
@Route("user3")
public class UserView3 extends VerticalLayout implements BeforeEnterObserver {

    public UserView3() {
        add(new H3("User View"));
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEvent) {
        QueryParameters queryParameters = beforeEvent.getLocation().getQueryParameters();
        Map<String, List<String>> parametersMap = queryParameters.getParameters();

        List<String> names = parametersMap.get("name");

        List<String> phone = parametersMap.get("phone");

        List<String> email = parametersMap.get("email");

        if (null != names && !names.isEmpty()) {
            NotificationUtil.showSuccessNotification(names.get(0));
        }

        if (null != phone && !phone.isEmpty()) {
            NotificationUtil.showSuccessNotification(phone.get(0));
        }

        if (null != email && !email.isEmpty()) {
            NotificationUtil.showSuccessNotification(email.get(0));
        }
    }
}
