# Navigation And Routing - Dynamic Route Operations - Route Alias

Este es otro proyecto distinto de navigation and routing.

## Dynamic Operation on Routes

Vamos a ver:

- Page Title: Vamos a ver como podemos configurar el título de una page a la route dinámicamente.
- Registering Routes: Vamos a ver como registrar routes dinámicamente.
  - Routes Alias
  - Setting Parent Layout
- Get Registered Routes: Vamos a ver como obtener routes disponibles y registradas en nuestro sistema.
- Removing Routes: Vamos a ver como podemos eliminar routes dinámicamente.

### Page Title

Ver `UserView`.

Acceder directamente a esta ruta: `http://localhost:8080/user/john` o cualquier otro nombre.

```java
// Para establecer el nombre del título de la page (se ve en la pestaña del navegador)
// Estáticamente, podemos indicar la anotación @PageTitle("User")
// Dinámicamente, tenemos que implementar HasDynamicTitle y su méto-do getPageTitle()
@Route(value = "user")
//@PageTitle("User")
public class UserView extends VerticalLayout implements HasDynamicTitle, HasUrlParameter<String> {

  private String title;

  public UserView() {
    add(new H6("User View"));
  }

  @Override
  public String getPageTitle() {
    return title;
  }

  @Override
  public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String parameter) {
    title = parameter;
  }
}
```

### Registering Routes

Ver `MenuView` y  `UserView2`.

### Route Alias

Ver `MenuView`.

### Setting Parent Layout

Ver `ParentLayout` y `MenuView`.

### Get Registered Routes

Para este ejemplo se han creado las views `ContactUsView` y `ServicesView` sin indicar `@Route`.

Ver `MenuView`.

```java
// Get Registered Routes
configuration.getAvailableRoutes().forEach(route -> {
    NotificationUtil.showSuccessNotification(route.getTemplate());
});
```

### Removing Routes

Desde la view `ServicesView` vamos a eliminar la route a `RemoveView`, recien creada para este ejemplo.

Para el ejemplo:

- Ir a `http://localhost:8080/` (eliminar la cookie de JSESSIONID si hace falta) para registrar las routes (aparecerá remove)
- Ir a `http://localhost:8080/service` para eliminar la view `RemoveView`
- Ir a `http://localhost:8080/contactus` para ver que ya no aparece la route remove

## Route Alias

- @RouteAlias se usa para crear varios paths a la misma view.
- Si indicamos @RouteAlias es obligatorio indicar también @Route.

Ejemplo:

```java
@Route(value = "")
@RouteAlias("home")
@RouteAlias("main")
@RouteAlias("xyz")
@PageTitle("Home")
public class MenuView extends VerticalLayout {
}
```