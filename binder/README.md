# Enlazado de datos y validaciones

## Introducción

Un `Binder` en Vaadin es un mecanismo de enlazado de datos muy poderoso usado para conectar componentes UI (como formularios) a modelos de datos backend de una manera que es type-safe y declarativa.

Simplifica manejar entradas de usuario, validaciones de data, y conversiones, haciendo que los desarrolladores puedan construir formularios eficientes y robustos.

**¿Por qué necesitamos un binder?**

En muchas aplicaciones web, las entradas de usuarios de los formularios tienen que ser procesadas, validadas, y guardadas en objetos del backend. El Binder provee una abstracción para manejar este proceso de una manera estructurada:

- Two-Way Binding: Sincroniza automáticamente la data entre los componentes UI (como TextField) y los modelos de data backend.
- Validation: Asegura que la data introducida por el usuario es válida antes de comprometerla en el objeto backend.
- Type Conversion: Convierte data entre la representación UI (por ejemplo cadenas) y el tipo del modelo backend (por ejemplo Integer, Date).
- Error Handling: Muestra errores de validaciones y conversiones directamente en los componentes UI.

**Características Clave de un Binder**

- Two-Way Binding (Enlazado bidireccional): Sincroniza automáticamente la data entre un campo de un formulario y una propiedad del objeto modelo.

```java
Binder<Person> binder = new Binder<>(Person.class);
TextField nameField = new TextField("Name");
binder.forField(txtName).bind(Person::getName, Person::setName);
```

- Bean Binding: Enlaza un objeto completo (un bean) a un formulario.

```java
Person person = new Person();
binder.setBean(person);
```

- Validation: Añadir validadores a campos para asegurar que la entrada del usuario cumple criterios específicos.
- Type Conversion: Convierte entre tipos (por ejempo de String a Integer) automáticamente.

## Binder Read Only

Podemos hacer que nuestro TextField esté en modo de solo lectura pasando al método bind, en la parte setter del POJO, un null, de esta forma:

```java
binder.forField(txtName).bind(Person::getName, null);
```

## Validation

Ejemplo de validación:

```java
binder.forField(txtName)
    .withValidator(name -> name.length() > 6, "Name should be greater than 6 characters")
    .bind(Person::getName, Person::setName);
```

Vaadin tiene algunos validadores, como `EmailValidator`, por ejemplo:

```java
binder.forField(txtEmail)
        .withValidator(new EmailValidator("Email is invalid"))
        .bind(Person::getEmail, Person::setEmail);
```

También podemos indicar que es obligatorio informar un campo, por ejemplo:

```java
binder.forField(txtAddress)
        .asRequired("Address is required")
        .bind(Person::getAddress, Person::setAddress);
```

Podemos validar un binder antes de escribirlo en un bean, por ejemplo:

```java
if (binder.validate().isOk()) {
    binder.writeBean(person);
    NotificationUtil.showSuccessNotification(person.getName());
    NotificationUtil.showSuccessNotification(person.getMobile());
}
```

## Type Conversion

Vaadin ya viene con varios conversores de tipos por defecto. Un ejemplo de conversión entre tipos es el siguiente:

```java
binder.forField(ageField)
    .withConverter(new StringToIntegerConverter("Must enter a number"))
    .bind(Person::getAge, Person::setAge);
```

También podemos crear nuestros propios conversores. Para ello, ver `util/MyConverter.java` y `util/CustomStringToDoubleConverter.java`.

## Usos de Binder

- Gestión de formularios: Ideal para construir formularios donde se necesitan enlazar varios campos a un único objeto de datos.
- Validación de data: Asegura que la entrada de usuario es válida antes de guardarla en base de datos.
- Formularios dinámicos: Crear formularios dinámicamente y enlazarlos a diferentes modelos de datos en tiempo de ejecución.
- Mejorar la lectura de código: Separa responsabilidades al manejar lógica UI, enlace de datos y validación dentro de un framework estructurado.
- Simplificar el mantenimiento: Centraliza la lógica de Vaadin, reduciendo la redundancia y mejorando la mantenibilidad.