package com.jmunoz.view;

import com.jmunoz.model.Person;
import com.jmunoz.util.CustomStringToDoubleConverter;
import com.jmunoz.util.MyConverter;
import com.jmunoz.util.NotificationUtil;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.router.Route;

@Route("/")
public class PersonView extends VerticalLayout {

    public PersonView() {
        setSizeFull();
        createForm();
    }

    private void createForm() {
        FormLayout formLayout = new FormLayout();
        formLayout.setSizeFull();

        TextField txtName = new TextField("Name");
        TextField txtMobile = new TextField("Mobile");
        TextField txtAddress = new TextField("Address");
        TextField txtEmail = new TextField("Email");
        TextField txtAge = new TextField("Age");
        TextField txtAmount = new TextField("Amount");

        // Inicializamos el Binder.
        Binder<Person> binder = new Binder<>(Person.class);
        // Enlazamos el Binder con nuestros TextField y añadimos validadores.
        // Notar que indicamos el getter y el setter del property del Bean.
        // Sin el setter indicamos null, el TextField es de solo lectura.
        binder.forField(txtName)
                .withValidator(name -> name.length() > 6, "Name should be greater than 6 characters")
                .bind(Person::getName, Person::setName);

        binder.forField(txtMobile).bind(Person::getMobile, null);

        binder.forField(txtAddress)
                .asRequired("Address is required")
                .bind(Person::getAddress, Person::setAddress);

        binder.forField(txtEmail)
                .withValidator(new EmailValidator("Email is invalid"))
                .bind(Person::getEmail, Person::setEmail);

        // un TextField siempre es un String, pero age en nuestro POJO es un int.
        // Tenemos que usar la conversión de tipos.
        binder.forField(txtAge)
//                .withConverter(new StringToIntegerConverter("Age must be number"))
                .withConverter(new MyConverter())
                .bind(Person::getAge, Person::setAge);

        binder.forField(txtAmount)
                .withConverter(new CustomStringToDoubleConverter())
                .bind(Person::getAmount, Person::setAmount);

        HorizontalLayout horizontalLayout = new HorizontalLayout();

        Button btnSave = new Button("Save");
        Button btnCancel = new Button("Cancel");

        horizontalLayout.add(btnSave, btnCancel);

        formLayout.add(txtName, txtMobile, txtAge, txtAddress, txtEmail, txtAmount, horizontalLayout);

        add(formLayout);

        Person person = new Person();
        // Ejemplo de uso de Binder para lectura.
        ejemploUsoBinderLectura(binder, person);

        // Ejemplo de uso de Binder para escritura.
        ejemploUsoBinderEscritura(btnSave, binder, person);
    }

    private void ejemploUsoBinderLectura(Binder<Person> binder, Person person) {
        // Creamos un objeto Person dummy para probar el enlazado de datos.
        // Notar que le pongo los datos al objeto, no al TextField.
        person.setName("John James");
        person.setMobile("123456789");
        person.setAge(25);
        person.setAddress("123 Main St");
        person.setEmail("johnj@a.com");
        // Ahora enlazamos el objeto person a nuestro Binder.
        // Notar que leemos la data de nuestro objeto Person y se escribe automáticamente en el TextField.
        binder.readBean(person);
    }

    private void ejemploUsoBinderEscritura(Button btnSave, Binder<Person> binder, Person person) {
        // Notar que escribimos el valor en el TextField, pero a la notificación le estamos
        // pasando el campo de Person, y funciona porque lo enlazamos con nuestro binder.
        btnSave.addClickListener(e -> {
            try {
                // Podemos validar el binder antes de escribirlo en el bean.
                // Usaremos binder.isValid() cuando no queramos mostrar mensajes en pantalla, es decir, validación silenciosa.
                // Es útil para comprobaciones internas.
                // Usaremos binder.validate().isOk() cuando queramos mostrar mensajes en pantalla, por ejemplo cuando un
                // usuario hace submit de un formulario.
                if (binder.validate().isOk()) {
                    binder.writeBean(person);
                    NotificationUtil.showSuccessNotification(person.getName());
                    NotificationUtil.showSuccessNotification(person.getMobile());
                    NotificationUtil.showSuccessNotification(String.valueOf(person.getAge()));
                    NotificationUtil.showSuccessNotification(String.valueOf(person.getAmount()));
                }
            } catch (ValidationException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
