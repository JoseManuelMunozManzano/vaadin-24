package com.example.application.view;

import com.example.application.util.NotificationUtil;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.RouteConfiguration;

// Vamos a eliminar esta route.
public class ServicesView extends VerticalLayout implements BeforeEnterObserver {

    public ServicesView() {
        add(new H6("Services View"));
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        RouteConfiguration configuration = RouteConfiguration.forSessionScope();

//        configuration.removeRoute(RemoveView.class);
        // Si queremos eliminar un path, también se puede
         configuration.removeRoute("remove");

        NotificationUtil.showErrorNotification("remove has been removed");
    }
}
