# Navigation And Routing - Passing data - Server Side Navigation - Route Exception Handling

Para no mezclar views con routes se crea otro proyecto distinto de navigation and routing.

## Passing Data

En este ejemplo veremos como se pasa data al navegar a otra ruta.

- En Vaadin, el usuario puede pasar data de formas diferentes a través de la ruta
  - Route Param
  - Query Param
  - Route Templates

**Route Params**

En Vaadin pueden usarse parámetros de enrutamiento para pasar valores dentro de URLs, permitiendo navegar entre views con data específica al contexto. Vaadin Flow, el framework de Java para construir aplicaciones Vaadin, provee una manera de definir estos parámetros directamente en la ruta.

```java
@Route("product/:productId")
public class ProductView extends VerticalLayout implements HasUrlParameter<String> {
    
    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String productId) {
        if (productId != null) {
            add(new Label("Product ID: " + productId));
        } else {
            add (new Label("Product ID not provided"));
        }
    }
}
```

Como ejemplo, se ha creado en el package `view` los views `HomeView`, `ProductView`, `ProductView2`, `UserView`, `UserView2`, `UserView3`, `ContactUs` y `AboutUs`.

Casos:

- Optional Route Parameters: son parámetros de la URL que pueden o no estar presentes. Suelen usarse cuando queremos añadir flexibilidad al permitir a los usuarios omitir ciertos segmentos del path URL.
- Uso: Se define un parámetro opcional usando la anotación `@OptionalParameter` en el método `setParameter` de la clase que implementa `HasUrlParameter`.
- Comportamiento: Si el parámetro opcional no viene en la URL, Vaadin seguirá enrutando a la view pero pasará el valor null o un parámetro vacío.
- Ejemplos de URL:
  - /product/123  - pasa 123 como productId
  - /product      - No se pasa ningún productId, y es manejado como null

Se puede ver un ejemplo de Optional Route en `ProductView`

- Wildcard Route Parameters: Se usan para capturar múltiples segmentos de path tras una ruta específica. Son útiles para manejar profundidad de URL flexibles o desconocidas, donde podemos no saber cuántos segmentos vienen tras la ruta base.
- Uso: Implementado para usar en `HasUrlParameter<String>` que captura todos los segmentos tras el path base.
- Comportamiento: Permiten cualquier número adicional de segmentos del path, capturándolos en una lista para que puedan ser procesados dinámicamente.
- Ejemplos de URL:
  - /files/documents/reports/2024     - pathSegments contendrá ["documents", "reports", "2024]
  - /files/reports                    - pathSegments contendrá ["reports"]
  - /files                            - pathSegments estará vacío

Se puede ver un ejemplo de Wildcard Route en `ProductView2`

**Query Parameters**

Los Query Parameters son pares clave-valor que se añaden al final del URL tras el símbolo `?`. Se usan para pasar data al server o a la aplicación sin tener que cambiar el path URL primario. En Vaadin, los query parameters son usados comúnmente para filtrar, ordenar o proveer contexto adicional a la view o componente que se está cargando.

- Estructura de Query Parameters: `http://example.com/view?param1=value1&param2=value2`
- Cuándo usar Query Parameters:
  - Filtrar u ordenar data, por ejemplo `/products?sort=price&category=electronics`
  - Paginación, por ejemplo `/articles?page=2&size=20`
  - Buscador, por ejemplo `/search?query=vaadin`
  - Pasar información adicional: Cuando es necesario contexto adicional sin tener que cambiar la ruta.

Se puede ver un ejemplo de Query Parameters en `UserView3` y `HomeView`.

**Route Templates**

En Vaadin, Route Templates permiten crear rutas flexibles usando parámetros y placeholders en los paths URL. Proveen una forma de definir rutas que pueden aceptar partes variables, habilitando navegación dinámica en la aplicación Vaadin.

Los parámetros Route Template deben seguir la siguiente sintaxis: `:parameter_name[modifier][(regex)]`

- parameter_name es el nombre del parámetro cuyo valor a recuperar cuando la URL coincide con la plantilla se resuelve en el server. Debe ser precedido por dos puntos :
- modifier es opcional y puede ser uno de los siguientes:
  - ? denota un parámetro opcional que puede no venir en la URL que se tiene que resolver.
  - * denota un parámetro wildcard que puede usarse solo como el último segmento en la plantilla, resolviendo todos los valores de segmento al final del URL.
- Ejemplo: `user/:userID?([0-9]{1-9}*)/edit`

Se puede ver un ejemplo en `UserView` y `UserView2`.

## Server Side Navigation

Vamos a explorar diferentes formas de realizar la navegación en una aplicación hecha en Vaadin.

- Vía links, cambiando la URL directamente en el navegador.
- Server Side Navigation
  - Puede hacerse usando el método `UI.navigate()`, al que se le puede pasar la clase o el path como un String
  - Se pueden ver ejemplos en `HomeView`.
  - Se suele navegar usando la ayuda de `clickListener`, disponible en diferentes componentes como iconos, menús, botones o accordions.
- Router Links
  - Es un componente basado en el tag HTML `<a>` usado para navegación. La página se actualiza en su lugar sin tener que recargarla por completo, y actualiza la URL de la página en el navegador.
  - En este caso no se necesita la ayuda de `clickListener`.
  - Se puede ver un ejemplo en `ContactUsView`.
- Standard Links (Anchor)
  - En Vaadin, con standard links nos referimos generalmente al elemento HTML anchor `<a href="/aboutUs">` en vez de al RouterLink de Vaadin. Estos links se usan para navegar a sitios webs externos, fuentes fuera del sistema de enrutado de Vaadin, o simples links que no necesitan el routing de Vaadin ni su soporte para navegación. 
  - Se puede ver un ejemplo en `HomeView`.

## Route Exception Handling

Creamos en el proyecto el package `exception` y dentro el fuente `RouteNotFound`.

Ejemplo:

```java
public class RouteNotFound extends VerticalLayout implements HasErrorParameter<NotFoundException> {
    
    @Override
    public int setErrorParameter(BeforeEnterEvent beforeEnterEvent, ErrorParameter<NotFoundException> errorParameter) {
        NotificationUtil.showErrorNotification("Could not navigate to " + beforeEnterEvent.getLocation().getPath());
        return 404;
    }
}
```

## Testing

Acceder a `http://localhost:8080/` y `http://localhost:8080/home` para distintas pruebas.

Para probar el manejo de excepciones, solo hay que indicar una ruta inexistente, por ejemplo `http://localhost:8080/noexiste`.