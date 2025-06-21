package com.jmunoz.views.home.components;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Locale;

@Route("datepicker")
public class DatePickerView extends HorizontalLayout {

    DatePickerView() {
        setPadding(true);

        DatePicker dfDOB = new DatePicker("Date of birth");
        setDatePickerdProperties(dfDOB);
        setDatePikerListeners(dfDOB);
        stylingDatePicker(dfDOB);

        add(dfDOB);
    }

    private void stylingDatePicker(DatePicker dfDOB) {
        // USANDO CSS
        // Ver el fuente main/frontend/themes/my-app/components/datePickerStyle.css
        // Ese css se importa en main/frontend/themes/my-app/styles.css
        dfDOB.addClassName("dob-datepicker");

        // La clase del desplegable.
        dfDOB.setOverlayClassName("dob-datepicker-overlay");
    }

    private void setDatePikerListeners(DatePicker dfDOB) {
        dfDOB.addValueChangeListener(event -> {
            Notification.show(event.getValue().toString());
        });
    }

    private void setDatePickerdProperties(DatePicker dfDOB) {
        // Rango de fechas mínima y máxima seleccionable.
//        dfDOB.setMin(LocalDate.now());
//        dfDOB.setMax(LocalDate.now().plusDays(6));

        // Indicamos que el número de la semana se vea.
        // Pero para que funcione hay que indicar la siguiente línea indicando
        // que el primer día de la semana es lunes (si no es lunes no funciona)
        dfDOB.setWeekNumbersVisible(true);
        dfDOB.setI18n(new DatePicker.DatePickerI18n().setFirstDayOfWeek(1));

        // Indicar posición inicial.
        // Se indica el mes y el año que se mostrarán al abrir el desplegable, sin seleccionar una fecha aún.
        dfDOB.setInitialPosition(LocalDate.now(ZoneId.systemDefault()).with(TemporalAdjusters.lastDayOfMonth()));

        // Si hago click en cualquier parte del input, no solo el icono del desplegable, se abre el calendario.
        // El valor por defecto es true.
        // Se se indica false, solo se abrirá el calendario si se pulsa el icono.
        dfDOB.setAutoOpen(false);

        // Internacionalization (I18n)
//        DatePicker.DatePickerI18n germanI18n = new DatePicker.DatePickerI18n();
//        germanI18n.setMonthNames(List.of("Januar", "Februar", "Marz", "April", "Mai", "Juni",
//                "Juli", "August", "September", "Oktober", "November", "Dezember"));
//        germanI18n.setWeekdays(List.of("Soontag", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag"));
//        germanI18n.setWeekdaysShort(List.of("So", "Mo", "Di", "Mi", "Do", "Fr", "Sa"));
//        germanI18n.setToday("Heute");
//        germanI18n.setCancel("Abbrechen");
//
//        dfDOB.setI18n(germanI18n);

        // Indicamos la localización.
        // Es el formato en el que se verá la fecha seleccionada en el calendario.
        dfDOB.setLocale(new Locale("fi", "FI"));
    }
}
