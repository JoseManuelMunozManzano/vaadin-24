# Authentication and Authorization

Vaadin Security consiste en el conjunto de herramientas, prácticas y funciones integradas en el Framework de Vaadin que aseguran la seguridad en aplicaciones web basadas en Vaadin.

Incluye soporte para autenticación de usuarios, control de acceso basado en roles, comunicación segura y protección contra vulnerabilidades web típicas, como Cross-Site Scripting (XSS) y Cross-Site Request Forgery (CSRF).

**Habilitar seguridad en Apps Vaadin**

Hay que seguir los siguientes pasos:

- Añadir la dependencia Spring Security al POM.
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```
- Crear una view Login.
  - Ver `LoginView`
- Crear una clase de configuración de la seguridad.
  - Ver `SecurityConfiguration`
- Crear views securizadas.
  - Ver `PublicView`, `DashboardView`, `AdminView` `UserView` y `Restrictedview`.

**Anotaciones a usar para securizar views**

- `@AnonymousAllowed`: Permite a cualquiera navegar a una view sin autenticación ni autorización.
- `@PermitAll`: Permite a cualquier usuario autenticado navegar a la view.
- `@RolesAllowed`: Concede acceso a usuarios que tengan los roles especificados en la property `value` de la anotación.
- `@DenyAll`: No permite a nadie navegar a la view. Es el comportamiento por defecto, es decir, si no se anota una view, se aplica esta anotación.

## Testing

Acceder a `http://localhost:8080` y veremos que automáticamente redirige a `http://localhost:8080/login`.

Podemos acceder sin ningún tipo de autorización/autenticación, es decir, siempre, a `http://localhost:8080/public`.

Si nos autenticamos en el formulario de login con usuario `user` y password `user` podremos acceder a `http://localhost:8080/dashboard` y `http://localhost:8080/user`.

Si nos autenticamos en el formulario de login con usuario `admin` y password `admin` podremos acceder a `http://localhost:8080/dashboard` y `http://localhost:8080/admin`.

En ningún caso vamos a poder acceder a `http://localhost:8080/restricted`.