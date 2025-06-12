# Navigation and Routing

## Introducción

Vamos a ver como se usa la navegación en el framework Vaadin. En Vaadin, a la `navigation` también se le llama `routing`.

- La navegación Vaadin es una parte clave del framework Vaadin, responsable de controlar como los usuarios se mueven entre views (pages o components) dentro de una aplicación Vaadin.
- En Vaadin, la navegación normalmente se basa en el URL y permite a los usuarios acceder a diferentes views directamente por el URL, haciendolo útil para `single-page application (SPA)` y `multi-page application`.

**Routing and Views**

- Routes: Vaadin mapea URLs con views específicas a través de routes. Se pueden definir routes anotando views con la anotación `@Route`, especificando el path URL.
- Views: Cada view es típicamente una clase Java que extiende `Component` o `Composite`, representando una `simple plage` o sección UI dentro de la app.

Para este ejemplo se han creado en el package `view` los views `AboutUsView`, `ContactUsView` y `HomeView`.

## Navigation Life Cycle

En Vaadin, el ciclo de vida de la navegación es el proceso por el cual un usuario se mueve entre views o pages en una aplicación Vaadin.

El router de Vaadin controla esta navegación determinando que componente mostrar basado en el path URL.

En Vaadin tenemos diferentes ciclos de vida de navegación.

- BeforeLeaveEvent
- BeforeEnterEvent
- AfterNavigationEvent

**BeforeLeaveEvent**

- Es el primer evento en el ciclo de vida de la navegación.
- Es un evento del ciclo de vida de navegación que se dispara justo antes de que el usuario deje la view. Este evento da la posibilidad de controlar o validar el proceso de la navegación, tal como confirmar cambios no grabados o realizar cualquier operación de limpieza antes de que el usuario deje la view actual.

Ejemplo:

```java
@Route("/")
public class HomeView extends VerticalLayout implements BeforeLeaveObserver {
    @Override
    public void beforeLeave(BeforeLeaveEvent beforeLeaveEvent) {
        BeforeLeaveEvent.ContinueNavigationAction action = beforeLeaveEvent.postpone();

        ConfirmDialog confirmDialog = new ConfirmDialog();
        confirmDialog.setText("Your form has changes! Are you sure you want to leave?");
        confirmDialog.setCancelable(true);
        confirmDialog.addConfirmListener(event -> action.proceed());
        confirmDialog.addCancelListener(event -> action.cancel());
        confirmDialog.open();
    }    
}
```

**BeforeEnterEvent**

- Es el segundo evento en el ciclo de vida de la navegación.
- Se dispara justo antes de que el usuario navegue a una view, permitiendo gestionar accesos, validaciones, o preparar la data antes de que se muestre la view.

Ejemplo:

```java
@Route("contactUs")
public class ContactUsView extends VerticalLayout implements BeforeEnterObserver {
    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        boolean isAuthenticated = true;

        if (!isAuthenticated) {
            beforeEnterEvent.forwardTo(HomeView.class);
        }
    }
}
```

**AfterNavigationEvent**

- Es el tercer evento en el ciclo de vida de la navegación.
- Se dispara inmediatamente después de que el usuario haya navegado exitosamente a la nueva view. Este evento permite realizar acciones tras cargarse la view y ser totalmente visible, como actualizar elementos UI, establecer el foco, o registrar efectos de navegación.

Ejemplo:

```java
@Route("aboutUs")
public class AboutUsView extends VerticalLayout implements AfterNavigationObserver {
    
    private final TextField txtName;
    
    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        txtName.setValue("John");
    }
}
```

## Parent and Nested Routes

- Parent Routes:
  - Son rutas de nivel superior que sirven como punto de entrada o categorías principales de la aplicación.
  - Típicamente representan views o layouts principales.
  - Ejemplo: Un dashboard, o panel administrativo.
- Nested Routes:
  - Son rutas hijas que dependen de un parent route y normalmente representan subsecciones o views detalladas dentro del parent.
  - Las rutas anidadas heredan el layout de sus padres, habilitando componentes compartidos como headers, sidebars o footers.
  - Ejemplo: Una página de informes o página de configuraciones bajo un dashboard.

**¿Por qué necesitamos Parent y Nested Routes?**

- Aplicaciones E-commerce:
  - Parent route: /products
  - Nested routes: /products/details, /products/reviews
- Dashboard administrativo:
  - Parent route: /dashboard
  - Nested routes: /dashboard/users, /dashboard/settings
- Plataforma educacional
  - Parent route: /courses
  - Nested routes: /courses/list, /courses/details

Para este ejemplo se ha creado el package `parentnestedroutesview` el layout `MainLayout` y los views `HomeView`, `ContactUsView`, `ServicesView` y `MenuBarView`.

La ruta del parent layout es `http://localhost:8080/main`.

Hay otro parent layout en `http://localhost:8080/mainlayout2`.

Y otro parent layout, esta vez en el root `http://localhost:8080`.