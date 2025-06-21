# Navigation And Routing - Retrieving Routes

Este es otro proyecto distinto de navigation and routing.

## Retrieving Routes

Podemos recuperar las rutas de nuestras view registradas y usarlas para realizar la navegación de esta forma:

```java
// Usando RouteConfiguration y su méto-do forSessionScope podemos
// recuperar la ruta de una clase.
// Si necesita parámetros, los indicamos usando RouteParameters donde
// indicamos la key y su value.
String userRoute = RouteConfiguration.forSessionScope()
        .getUrl(UserView.class, new RouteParameters("id", "567"));

// Creamos unos Anchor donde indicamos la ruta recuperada.
Anchor linkUser = new Anchor(userRoute, "User");
```

También podrían usarse en un `clickListener` de un menuBar o de un button.