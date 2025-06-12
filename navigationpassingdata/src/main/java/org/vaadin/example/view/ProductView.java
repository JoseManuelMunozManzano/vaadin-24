package org.vaadin.example.view;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.vaadin.example.util.NotificationUtil;

@Route("product")
public class ProductView extends VerticalLayout implements HasUrlParameter<String> {

    public ProductView() {
        add(new H3("Product View"));
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String productId) {
        if (productId != null) {
            NotificationUtil.showSuccessNotification(productId);
        } else {
            NotificationUtil.showErrorNotification("Product ID is null");
        }
    }
}
