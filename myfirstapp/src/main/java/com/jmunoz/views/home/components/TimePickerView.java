package com.jmunoz.views.home.components;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.Route;

import java.time.Duration;
import java.time.LocalTime;

@Route("timepicker")
public class TimePickerView extends VerticalLayout {

    TimePickerView() {
        TimePicker currentTime = new TimePicker("Current Time");
        setTimePickerProperties(currentTime);

        add(currentTime);
    }

    private void setTimePickerProperties(TimePicker currentTime) {
        // El salto que aparece entre dos intervalos de tiempo.
        // Por defecto es una hora.
        // Si hay muchos intervalos, es posible que no se muestre el desplegable de horas.
        currentTime.setStep(Duration.ofMinutes(30));

        // Mínima-Máxima hora seleccionable.
        currentTime.setMin(LocalTime.of(4, 0));
        currentTime.setMax(LocalTime.of(5, 0));

        // Configuramos texto de error.
        currentTime.setErrorMessage("Invalid Time");

        // Indicamos un valor programáticamente.
        currentTime.setValue(LocalTime.of(4, 35));

        // Mostrar el botón de borrado del campo de entrada.
        currentTime.setClearButtonVisible(true);
    }
}
