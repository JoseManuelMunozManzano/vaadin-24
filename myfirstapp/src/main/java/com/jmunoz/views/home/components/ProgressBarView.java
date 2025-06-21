package com.jmunoz.views.home.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.progressbar.ProgressBarVariant;
import com.vaadin.flow.router.Route;

@Route("progressbar")
public class ProgressBarView extends VerticalLayout {

    private Button btnAdd;
    private Button btnSubstract;
    private HorizontalLayout horizontalLayout;

    public ProgressBarView() {

        ProgressBar progressBar = new ProgressBar();
        setProgressBardProperties(progressBar);
        stylingProgressBar(progressBar);

        add(horizontalLayout, progressBar, new HorizontalLayout(btnAdd, btnSubstract));
    }

    private void stylingProgressBar(ProgressBar progressBar) {
        // THEMES VARIANTS
//        progressBar.addThemeVariants(ProgressBarVariant.LUMO_ERROR);

        // USANDO CSS
        // Ver el fuente main/frontend/themes/my-app/components/progressBarStyle.css
        // Ese css se importa en main/frontend/themes/my-app/styles.css
    }

    private void setProgressBardProperties(ProgressBar progressBar) {
        // Cuando no sabemos el tiempo que lleva completar una tarea.
//        progressBar.setIndeterminate(true);

        // Establecemos un valor de completado (de 0.0 a 1.0)
        progressBar.setValue(0.3);

        // Indicamos rangos.
//        progressBar.setMax(0.7);
//        progressBar.setMin(0.2);

        // Indicamos los valores.
        NativeLabel progressBarLabel = new NativeLabel("Progress = ");
        NativeLabel progressBarVal = new NativeLabel(String.valueOf(progressBar.getValue()));

        horizontalLayout = new HorizontalLayout(progressBarLabel, progressBarVal);

        btnAdd = new Button(VaadinIcon.PLUS.create());
        btnAdd.addClickListener(e -> {
            if ((progressBar.getValue() + 0.1) < 1.0) {
                progressBar.setValue(progressBar.getValue() + 0.1);
                progressBarVal.setText(String.valueOf(progressBar.getValue()));
            }
        });

        btnSubstract = new Button(VaadinIcon.MINUS.create());
        btnSubstract.addClickListener(e -> {
            if ((progressBar.getValue() - 0.1) > 0.0) {
                progressBar.setValue(progressBar.getValue() - 0.1);
                progressBarVal.setText(String.valueOf(progressBar.getValue()));
            }
        });
    }
}
