package org.vaadin.example.view;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.vaadin.example.util.NotificationUtil;

@Route("product2")
public class ProductView2 extends VerticalLayout implements HasUrlParameter<String> {

    public ProductView2() {
        add(new H3("Product View 2"));
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @WildcardParameter String productId) {

        // Con WidlCard no entra nunca por el null, ya que si no hay segmentos de path
        // lo que tengo es un String vacío, no un null.
        if (productId != null) {
            NotificationUtil.showSuccessNotification(productId);
        } else {
            NotificationUtil.showErrorNotification("Product ID is null");
        }
    }
}
