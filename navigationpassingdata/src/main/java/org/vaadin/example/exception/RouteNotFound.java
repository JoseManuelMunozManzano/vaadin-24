package org.vaadin.example.exception;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.ErrorParameter;
import com.vaadin.flow.router.HasErrorParameter;
import com.vaadin.flow.router.NotFoundException;
import org.vaadin.example.util.NotificationUtil;
import org.vaadin.example.view.ContactUsView;

public class RouteNotFound extends VerticalLayout implements HasErrorParameter<NotFoundException> {

    @Override
    public int setErrorParameter(BeforeEnterEvent beforeEnterEvent, ErrorParameter<NotFoundException> errorParameter) {
        NotificationUtil.showErrorNotification("Could not navigate to " + beforeEnterEvent.getLocation().getPath());
        beforeEnterEvent.forwardTo(ContactUsView.class);
        return 404;
    }
}
