# Componentes Básicos

Veremos:

- Button
- Text Field
- Number Field
- Email
- Password Field
- Vertical Layout
- Horizontal Layout
- Form Layout
- Split Layout
- Divspan
- Text Area
- Check Box
- Combo Box
- Dialog
- Accordion
- Avatar
- Date Picker
- Time Picker
- Icon
- Image
- Badge
- List Box
- Message Input
- Message List
- Multi Select Combo Box
- Notification
- Progress Bar
- Radio Button
- Select
- Upload Field
- Menu Bar
- Tabs
- Grid
- Lazy Loading Grid

NOTA: En el CSS `practicaInstructorStyle.css` puede haber algún CSS que choque con los otros. Esto hay que tenerlo en cuenta por si se revisa el código y pareciera que algún estilo no se aplica como debe.

## Button

El button Vaadin es un componente UI fundamental del framework Vaadin, usado para disparar acciones cuando un usuario interactúa con la interfaz.

Es un elemento clickable que puede personalizarse para ajustarse a las necesidades de nuestra aplicación.

Se ha creado en el package `views/home/components` un ejemplo `ButtonView.java`.

Usos:

- Disparar acciones: Realizar tareas como enviar formularios, navegación entre views o ejecución de lógica de negocio al interactuar el usuario
- Apariencia personalizable: Estilizar el botón con themes, icons o CSS personalizado para adecuarse al diseño de la aplicación
- Manejo de eventos: Responder a clicks del usuario usando listeners para implementar funcionalidad específica de la aplicación
- Integración con otros componentes: Uso de botones en dialogs, forms, o layouts para proveer opciones como Save, Cancel o Delete

Estilizar Botón:

- Usando Theme Variants de Vaadin
- Usando estilos en linea
- Configurando Variables CSS personalizadas
- Shadow DOM y selectores `::part`

## Text Field

Text Field es un componente UI que permite a los usuarios informar y editar texto en un formulario u otro interfaz de usuario.

Es un componente fundamental en el set de elementos de formulario de Vaadin y es comunmente usado en aplicaciones web para obtener entradas del usuario.

Las características clave de TextField son:

- Text Input: El componente de TextField provee una línea sencilla de texto de entrada donde los usuarios pueden teclear
- Labelling: Podemos asociar una etiqueta con el TextField para describir su propósito, haciendo más fácil de entender a los usuarios la información que necesitamos que introduzcan
- Placeholders: Podemos informar un texto placeholder, que se muestra dentro del campo cuando esté vacío. Este texto da una pista de la información esperada
- Events: Se disparan eventos, como ValueChangeEvent, permitiendo reaccionar a cambios en la entrada. Por ejemplo, se pueden realizar validaciones en tiempo real o actualizar otros componentes UI basados en dicha entrada

Se ha creado en el package `views/home/components` un ejemplo `TextFieldView.java`.

Usos:

- Entrada de texto de una sola línea: Permite a los usuarios introducir valores cortos de texto, como nombres, direcciones de email o queries de búsqueda
- Campos de formulario: Usado en formularios para obtener información esencial como nombres, números de teléfono, o nombres de producto
- Funcionalidades de búsqueda: Implementa campos de búsqueda donde los usuarios pueden teclear palabras clave a filtrar o buscar contenido específico
- Data binding: Enlaza el campo de texto al modelo de datos, habilitando una integración perfecta con los sistemas backend para leer o actualizar valores
- Validaciones de entrada: Hace cumplir reglas de validación como longitud de caracteres, pattern matching (por ejemplo para formato de email), o validaciones personalizadas para asegurar la entrada correcta de la data

Estilizar TextField:

- Usando Theme Variants de Vaadin
- Usando estilos inline
- Usando CSS
- Configurando Variables CSS personalizadas
- Usando estilos personalizados con Shadow DOM

## Number Field

Number Field es un componente UI diseñado para la entrada numérica de valores. Permite a los usuarios informar y editar números con restricciones opcionales como valores mínimos y máximos, saltos de incremento/decremento y la habilidad de formatear la entrada.

Las características clave de NumberField son:

- Numeric Input: El campo restringe la entrada a valores numéricos válidos, pudiendo incluir enteros o números de punto flotante
- Min/Max Values: Puede establecerse el valor mínimo y máximo que puede introducir el usuario
- Ste Size: Determina el valor de incremento o decremento cuando los usuarios usan las teclas de flecha de arriba y abajo, o los controles del spinner
- Placeholder and Prefix/Suffix: Pueden añadirse placeholder, prefijos o sufijos para dar contexto adicional de la entrada esperada al usuario
- Value Change Event: Dispara eventos cuando cambia el valor, permitiendo manejar cambios de entrada programáticamente

Se ha creado en el package `views/home/components` un ejemplo `NumberFieldView.java`.

Usos:

- Entrada numérica: Acepta y valida entradas numéricas de los usuarios, como números enteros o decimales, para campos como edad, cantidad o precio
- Validación de formulario: Asegura que el valor introducido es un número válido, con la posibilidad de establecer restricciones como min, max y step values
- Cálculos y computaciones: Usado en formularios o aplicaciones donde los cálculos o computaciones numéricas son requeridas, como apps financieras o herramientas científicas
- Enlazado de datos dinámico: Enlazar data numérica a modelos backend, permitiendo una integración perfecta con BD o APIs
- Control de rangos: Controlar un rango específico de valores, como seleccionar una cantidad entre el valor mínimo y máximo (por ejemplo, montos de préstamos)

Estilizar NumberField:

- Usando Theme Variants de Vaadin
- Usando estilos inline
- Usando CSS
- Configurando Variables CSS personalizadas
- Usando estilos personalizados con Shadow DOM

## Email

EmailField es un componente de Vaadin que permite a los usuarios introducir una dirección email. Este campo está especificamente diseñado para validar la entrada de acuerdo al formato email estándar, asegurando que la data introducida es una dirección de email válida.

Las características clave de EmailField son:

- Validation: El componente valida automáticamente la entrada para que coincida con un formato de dirección email válido (como user@example.com). Si la entrada no coincide con este formato, el campo puede mostrar un mensaje de error
- Placeholder Text: Puede indicarse un texto placeholder que aparezca en el campo cuando este esté vacío, guiando al usuario en lo que tiene que introducir
- Label: El componente puede tener una etiqueta que indique el propósito del campo
- Required Indicator: Puede marcarse el campo como requerido, asegurando que el usuario provee un valor antes de enviar el formulario

Se ha creado en el package `views/home/components` un ejemplo `EmailFieldView.java`.

Usos:

- Obtener direcciones email: Aceptar entradas de usuario formateadas específicamente como una dirección email
- Validación: Validar automáticamente las entradas que aseguren que coincidan con el formato email (como user@example.com)
- Integración con formularios: Usado en registro, login, o envios de formularios para capturar emails de usuarios
- Mejorando la experiencia de usuario: Provee funciones integradas como texto placeholder y botones de borrado para mejor usabilidad

## Password Field

PasswordField es un componente de Vaadin que es usado para recibir información sensitiva del usuario, como passwords. Enmascara la entrada de texto con asteriscos (u otros caracteres) para asegurar la privacidad mientras el usuario teclea.

Las características clave de PasswordField son:

- Input Masking: Oculta los caracteres introducidos por el usuario, mostrándolos como puntos o asteriscos
- Validation: Se pueden añadir validadores para asegurar que el password introducido cumple ciertos criterios

Se ha creado en el package `views/home/components` un ejemplo `PasswordFieldView.java`.

Usos:

- Entradas de passwords: Obtener passwords de usuario de forma segura enmascarando caracteres mientras se teclea, evitando que otros miren el password
- Login en formularios: Usado en autenticación de usuarios en login de formularios, donde los usuarios tienen que informar un password para acceder a su cuenta
- Creación de cuenta: Obtener entradas de password durante el registro de una cuenta, asegurando que el usuario informa un password seguro
- Configuración de seguridad: Permite a los usuarios cambiar o actualizar sus passwords en una sección de gestión de configuración o de perfil
- Validación de password: Integra comprobaciones de fuerza de password o reglas de validación (como longitud mínima, caracteres especiales) durante la creación de una cuenta o el cambio de un password

Estilizar PasswordField:

- Usando Theme Variants de Vaadin
- Usando estilos inline
- Usando CSS
- Configurando Variables CSS personalizadas
- Usando estilos personalizados con Shadow DOM

## Layout

- Los layouts son componentes que controlan la disposición de los componentes UI en una vista
- Definen como son organizados los componentes hijos, son alineados y redimensionados dentro del contenedor
- Vaadin provee varios componentes layout que nos ayudan a estructurar la UI

Usos:

- Organizing UI Components: Organizar y posicionar componentes UI (como buttons, forms, text fields) de una forma estructurada
- Responsive Design: Crear layouts adaptables que se ajustan automáticamente a diferentes tamaños de pantallas y dispositivos
- Managing Spacing and Alignment: Control de espaciado, padding y alineación de componentes hijos para una UI limpia y consistente
- Component Grouping: Agrupar componentes relacionados, como situar campos en un formulario o botones en un toolbar


### Vertical Layout

- Disponen a sus hijos en una columna
- Apilan los componentes verticalmente

Se ha creado en el package `views/home/components` un ejemplo `VerticalLayoutView.java`.

Estilizar VerticalLayout:

- Usando estilos inline
- Usando CSS

### Horizontal Layout

- Disponen a sus hijos en una fila
- Coloca componentes horizontalmente uno al lado del otro

Se ha creado en el package `views/home/components` un ejemplo `HorizontalLayoutView.java`.

Estilizar HorizontalLayout:

- Usando estilos inline
- Usando CSS

### Form Layout

Permite crear formularios responsivos con varias columnas, permitiendo posicionar etiquetas de inputs encima o a la izquierda de los campos input

Se ha creado en el package `views/home/components` un ejemplo `FormLayoutView.java`.

Estilizar FormLayout:

- Usando estilos inline
- Usando CSS

### Split Layout

- Divide el espacio disponible en dos partes redimensionables con un divisor arrastrable
- Ideal para layouts con una barra lateral y un area de contenido central

Se ha creado en el package `views/home/components` un ejemplo `SplitLayoutView.java`.

## Div & Span

Se exploran los componentes Div y Span de Vaadin.

Se ha creado en el package `views/home/components` un ejemplo `DivspanView.java`.

## Text Area

TextArea es un campo de entrada multilinea que permite a los usuarios introducir una gran cantidad de data, muy útil cuando una sola línea de texto no es suficiente.

Las características clave de TextArea son:

- Multi-line Input: La característica principal es que soporta múltiples líneas de texto, a diferencia de TextField que solo permite una
- Automatic Height Adjustment: La altura de TextArea puede ajustarse automáticamente, mientras el usuario va introduciendo más texto. Alternativamente, se puede configurar una altura fija manualmente
- Placeholders: Soporta placeholders que aparecen cuando no hay ningún texto, ayudando a los usuarios a entender que tipo de entrada se espera
- Character Limits: Pueden definirse longitudes mínimas y máximas, haciendo fácil hacer cumplir las restricciones de entrada

Se ha creado en el package `views/home/components` un ejemplo `TextAreaView.java`.

Usos:

- Entrada de texto multilinea: Permite a los usuarios introducir textos largos, como descripciones, comentarios, o retroalimentación, que requieren muchas líneas
- Campos de formulario: Usado en formularios donde los usuarios necesitan introducir información más detallada, como tickets de soporte o formularios de contacto
- Edición de texto: Permite a los usuarios editar contenido largo, como notas o mensajes, con la posibilidad de desplazarse por el texto
- Visualización de contenido: Presenta o muestra texto multilinea, como términos y condiciones, políticas de privacidad, o bloques de texto largo
- Actualizaciones en tiempo real: Muestra actualizaciones de contenido en tiempo real, útil en entornos colaborativos como chat o herramientas de edición de dcoumentos

Estilizar TextArea:

- Usando Theme Variants de Vaadin
- Usando estilos inline
- Usando CSS
- Configurando Variables CSS personalizadas
- Usando estilos personalizados con Shadow DOM

## Check Box

El Checkbox de Vaadin es un componente UI del framework Vaadin que permite a los usuarios elegir entre "marcado" (verdadero) o "desmarcado" (falso). Este componente forma parte de los elementos de formulario de Vaadin y puede utilizarse en diversas interfaces de usuario donde se requiere una opción simple de activar/desactivar o de sí/no.

Las características clave de Checkbox son:

- Two-State Input: La casilla de verificación tiene dos estados: marcada y desmarcada, que pueden representar verdadero/falso, sí/no o cualquier otra opción binaria
- Label Support: Puede agregar una etiqueta junto al checkbox para aclarar qué controla o representa
- Event Handling: La casilla de verificación de Vaadin proporciona detectores de eventos como ValueChangeListener para gestionar los cambios en el estado de la casilla. Esto le permite activar acciones en su aplicación cuando el usuario activa o desactiva la casilla

Se ha creado en el package `views/home/components` un ejemplo `CheckboxView.java`.

Usos:

- Selección de múltiples opciones: Permite a los usuarios elegir una o más opciones de un conjunto
- Alternancia de estados booleanos: Representa y alterna entre estados marcados (verdadero) y no marcados (falso) para configuraciones o preferencias
- Filtrado de datos: Actúa como filtro en cuadrículas (grids) o listas, por ejemplo, filtrando elementos por categoría o disponibilidad
- Entradas de formulario: Recopila las respuestas de los usuarios para acuerdos, preferencias u otros valores booleanos en formularios

Estilizar Check Box:

- Usando Theme Variants de Vaadin
- Usando estilos inline
- Usando CSS
- Configurando Variables CSS personalizadas
- Usando estilos personalizados con Shadow DOM

Solo se ve el uso de Shadow DOM ya que es la forma más eficiente.

## Combo Box

Combo Box permite al usuario seleccionar un valor de una lista filtrable de opciones superpuestas. Admite carga diferida y se puede configurar para aceptar valores personalizados.

Es una combinación de un campo de entrada de texto y una lista de opciones desplegables, permitiendo a los usuarios seleccionar items de una lista o escribir un valor.

Suelen usarse para seleccionar elementos de una lista, proveyendo a los usuarios con una forma conveniente y eficiente de informar data.

Se ha creado en el package `views/home/components` un ejemplo `ComboBoxView.java`. Vemos un ejemplo de `LitRenderer` en este código.

Buenas prácticas y consejos:

- Mantener la lista desplegable concisa y relevante a las necesidades del usuario para evitar sobrecargarlo con muchas opciones. Usar el componente `Select` en vez de Combo Box si el número de elementos es muy pequeño y no se requiere carga perezosa ni filtrado
- Usar texto placeholder y texto de ayuda para dar pistas o instrucciones a los usuarios para que sepan usar el Combo Box
- Probar a fondo los Combo Boxes para asegurarnos de que funcionan como esperamos en los diferentes dispositivos y navegadores

Usos:

- Seleccionar un único valor de una List: Permite a los usuarios elegir una opción de un menú desplegable
- Filtrado dinámico: Filtra opciones dinámicamente mientras el usuario va tecleando, haciéndolo ideal para sets de datos muy largos
- Enlazando a fuentes de datos: Se enlaza fácilmente a data backend o listas para una integración perfecta
- Opciones personalizables: Muestra contenido personalizado como iconos o texto formateado junto a los items en el desplegable

Estilizar Combo Box:

- Usando Theme Variants de Vaadin
  - Se puede cambiar el color, estilo y otros visuales del componente
- Configurando Variables CSS personalizadas
- Usando estilos personalizados con Shadow DOM, en las partes internas de los componentes

## Dialog y ConfirmDialog

Un Dialog es un componente UI que representa una ventana modal que puede visualizar contenido como texto, formularios y otros componentes interactivos.

Normalmente, se usa en interacciones de usuario que requieren atención, como indicaciones de confirmación, formularios o notificaciones.

Usos:

- ConfirmDialog está especializado para confirmaciones, haciendo más fácil de implementar escenarios de confirmar/cancelar
- Dialog es más de propósito general, y puede usarse para cualquier interacción modal
  Personalización:

- Dialog proporciona más flexibilidad en contenido y disposición, ya que se puede añadir cualquier componente
- ConfirmDialog ya viene con una estructura predefinida, pero puede personalizarse en términos de texto y etiquetas de botones

Facilidad de uso:

- ConfirmDialog es más fácil de usar en dialogs de confirmación estándar, ya que reduce el código repetitivo
- Dialog requiere más configuración manual pero ofrece más versatilidad

Se ha creado en el package `views/home/components` un ejemplo `DialogView.java`.

Usos de Dialog y Confirm Dialog:

- Visualización de contenido modal: Mostrar información importante o acciones que requieren atención del usuario sin tener que dejar la página actual
- Confirmación del usuario: Solicitar a los usuarios que confirmen acciones como borrados o envío de formularios
- Incrustación de formularios: Incrustar formularios o entradas para tareas como login, registro o recopilación de datos
- Mostrar contenido dinámico: Presentar contenido generado dinámicamente, como mensajes de error o información detallada de un elemento

Estilizar Dialog:

- Usando Theme Variants de Vaadin
- Usando estilos inline
- Usando CSS
- Configurando Variables CSS personalizadas
- Usando estilos personalizados con Shadow DOM

Solo se ve el uso de Shadow DOM.

## Accordion

Accordion es un componente UI usado para visualizar secciones o paneles articulados, útiles para organizar contenido de una forma estructurada y eficiente.

Cada sección de un accordion puede expandirse o colapsarse al clickar su cabecera, revelando y escondiendo el contenido asociado.

Las características clave de Accordion son:

- Collapsible Panels: Cada sección puede expandirse o colapsarse individualmente
- Compact Design: Ahorra espacio en pantalla al mostrar solo el contenido relevante de la sección cada vez
- Customizable: Cabeceras y contenido pueden estilizarse o personalizarse

Se ha creado en el package `views/home/components` un ejemplo `AccordionView.java`.

Usos:

- Agrupación de contenido: Organizar contenido en secciones que el usuario puede expandir o colapsar, que es útil al tratar con sets largos de información relacionada
- Formularios y entrada de datos: Usado para descomponer largos formularios en secciones manejables. El usuario pueden enfocarse en una sección cada vez, mejorando la experiencia de usuario
- Frequently Asked Questions (FAQ): Mostrar preguntas como cabeceras y respuestas como el contenido de cada sección, proveyendo una página FAQ clara y organizada
- Configurando paneles: Mostrar opciones de configuración o configuraciones de usuario en paneles plegables, permitiendo a los usuarios enfocarse en una categoría a la vez
- Instrucciones paso a paso: Usar cada sección del accordion para mostrar un paso de un proceso, haciendo más fácil que lo sigan los usuarios

Estilizar Accordion:

- Usando Theme Variants de Vaadin
- Usando estilos inline
- Usando CSS
- Configurando Variables CSS personalizadas
- Usando estilos personalizados con Shadow DOM

Solo se ve el uso de Shadow DOM.

## Avatar

Un Avatar es una representación gráfica de un usuario, normalmente usado en UI para mostrar una imagen de perfil, iniciales u otros visuales identificativos.

Usos:

- Perfiles de usuario: Muestran perfiles de usuario o iniciales en varias partes de la aplicación, como en listas de usuarios, comentarios o interfaces de mensajes
- Personalización: Mejoran la experiencia de usuario al mostrar elementos personalizados, como iconos o imágenes específicas del usuario, haciendo la interfaz más atractiva
- Identificación visual: Permite a los usuarios una rápida identificación de otros usuarios gracias a su representación visual, lo que es útil es aplicaciones sociales, herramientas colaborativas y plataformas comunitarias
- Marca: Usar avatares para representar diferentes roles o status con imágenes o símbolos personalizados, ayudando a reforzar la marca o proveer una rápida pista visual sobre el rol o status del usuario

Se ha creado en el package `views/home/components` un ejemplo `AvatarView.java`.

Estilizar Avatar:

- Usando Theme Variants de Vaadin
- Usando estilos inline
- Usando CSS
- Configurando Variables CSS personalizadas
- Usando estilos personalizados con Shadow DOM

## DatePicker

DatePicker es un componente Vaadin que permite a los usuarios seleccionar una fecha de una interface de calendario, o teclear una fecha manualmente.

Es un componente muy personalizable que puede manejar varios formatos de fecha, locales y reglas de validación.

Las características clave de DatePicker son:

- Calendar UI: Los usuarios pueden elegir una fecha usando un calendario emergente
- Manual Input: Los usuarios pueden teclear la fecha directamente en el campo
- Localization: El componente soporta Localization, permitiendo mostrar las fechas en diferentes formatos basado en la localización del usuario
- Date Format: Se puede personalizar el formato de fecha para que coincida con el formato requerido (dd/MM/yyyy, MM/dd/yyyy)
- Range Validation: Se pueden indicar unas fechas mínimas y máximas para restringir el rango de fechas seleccionable

Se ha creado en el package `views/home/components` un ejemplo `DatePickerView.java`.

Usos:

- Selección de fechas: Permite a los usuarios elegir una fecha específica de una interfaz de calendario
- Selección de rango de fechas: Facilita seleccionar fechas de comienzo y fin para tareas como reservas y planificaciones
- Validación de entrada de fecha: Asegura que los usuarios indican una fecha válida y formateada, reduciendo errores
- Enlace con modelos de datos: Integración fácil con sistemas backend para almacenar o recuperar valores de fecha

Estilizar DatePicker:

- Usando Theme Variants de Vaadin
- Usando estilos inline
- Usando CSS
- Configurando Variables CSS personalizadas
- Usando estilos personalizados con Shadow DOM

Solo se ve el uso de Shadow DOM.

## TimePicker

TimePicker es un componente Vaadin usado para seleccionar valores de hora.

Provee una interfaz amigable donde el usuario puede seleccionar una hora, en horas o minutos de un desplegable, o introduciéndolas manualmente en el campo de entrada.

Este componente simplifica el proceso de entrada de hora, haciéndolo fácil para el usuario, quien puede seleccionar una hora válida de un desplegable sin tener que formatearla manualmente.

Las características clave de TimePicker son:

- Time Selection Interface: Un desplegable o ventana emergente permite al usuario elegir una hora (horas y minutos)
- Customizable Time Format: Se puede configurar el formato de hora, por ejemplo formato de 12 horas am/pm o formato de 24 horas
- Locale-Sensitive: El componente puede ajustarse para seguir los formatos de hora local, basado en la localización del usuario
- Validation: Incluye validaciones incorporadas para asegurar que la hora introducida es válida
- Keyboard and Mouse Input: Los usuarios pueden elegir usar el desplegable o teclear la hora en el campo de entrada

Se ha creado en el package `views/home/components` un ejemplo `TimePickerView.java`.

Usos:

- Planificador de eventos: Usado en formularios para que los usuarios puedan planificar eventos, reuniones o citas
- Sistemas de reserva: Permite a los usuarios elegir franjas horarias en sistemas de reservas, como restaurantes, citas
- Gestión de tareas: Útil en aplicaciones donde hay que configurar el seguimiento del tiempo o recordatorios basados en tiempo
- Alarmas y notificaciones: Puede usarse en aplicaciones que requieren configurar horas específicas para recordatorios o alarmas

## Icon

Iconos que podemos usar en nuestras aplicaciones:

- Iconos incorporados
  - Iconos Vaadin
  - Iconos Lumo
- Iconos de terceros
  - Iconos SVG
  - Iconos personalizados
  - Iconos Font Awesome

Se ha creado en el package `views/home/components` un ejemplo `IconView.java` donde veremos distintas formas de crear iconos.

Usos:

- Representaciones visuales: Mejora la UI al representar acciones o status visualmente, como un icono de papelera para eliminar o una marca de verificación para éxito
- Mejora de la navegación: Usar iconos en menús, botones o pestañas para hacer que la navegación sea más intuitiva
- Retroalimentación y alertas: Mostrar iconos para transmitir retroalimentación, como errores, advertencias o notificaciones de éxito
- Diseño personalizable: Combinar iconos con texto u otros componentes para crear diseños visualmente atractivas y funcionales

**SVG**

Los iconos SVG se guardan en `src/main/resources/META-INF/resources/icons`.

Es necesario usar un `StreamResource` que consume un `SvgIcon`.

**Iconos personalizados**

En la carpeta `src/main/resources/META-INF/resources/icons` también puede verse el archivo `customIcons.svg`, que contiene el código de varios iconos SVG.

Es necesario usar un `StreamResource` que consume un `SvgIcon`.

**Font Awesome**

En la carpeta `src/main/frontend/themes/my-app/` he creado la carpeta `fontawesome` y he copiado los archivos:

- fontawesome.css
- solid.css
- fa-solid-900.ttf
- fa-solid-900.woff2

Estos archivos los he descargado de `https://fontawesome.com/download`, la versión para Web.

También he importado `fontawesome.css` y `solid.css` en el archivo `src/main/frontend/themes/my-app/styles.css` para poder usarlos.

Con esto ya puede usarse en `IconView.java`, usando `FontIcon`.

## Image

Se ha creado en el package `views/home/components` un ejemplo `ImageView.java`.

Las imágenes se encuentran en el directorio `src/main/resources/META-INF/resources/images`.

## Badge

Los Badges son componentes UI del framework Vaadin que proveen una forma sencilla de visualizar un pequeño trozo de información, como una etiqueta de status o una cuenta de notificación.

Se usan típicamente para destacar o mover la atención a información específica en el interfaz de usuario.

Se ha creado en el package `views/home/components` un ejemplo `BadgeView.java`.

Usos:

- Recuento de notificaciones: Muestra el número de mensajes sin leer, tareas u otras notificaciones en un icono o botón
- Indicadores de status: Indican el status de un elemento, como "activo", "inactivo", "completado", etc.
- Etiquetas o rótulos: Etiquetar elementos con etiquetas o categorías, como "Nuevo", "En Venta" o "Prioridad"
- Destacar información: Lleva la atención a información o actualizaciones importantes

## ListBox y MultiSelectListBox

Son componentes usados para renderizar una lista de elementos donde el usuario puede seleccionar una o varias opciones respectivamente.

- List Box
  - Permite al usuario seleccionar un elemento de una lista de opciones disponibles
- Multi Select List Box
  - Permite al usuario seleccionar varios elementos de una lista de opciones. Similar a List Box pero soportando multiselección

Las características clave son:

- Single-selection (ListBox): Útil en formularios donde el usuario necesitar elegir una opción
- Multi-selection (MultiSelectListBox): Útil cuando el usuario necesita seleccionar varios elementos, como filtrar operaciones
- Customization: Se pueden personalizar los elementos, añadir iconos o aplicar estilos a los componentes

Se ha creado en el package `views/home/components` un ejemplo `ListBoxView.java`.

Usos:

- Selección Sencilla: Permite a los usuarios seleccionar un único elemento de una lista, ideal para formularios o menús desplegables
- Mostrar data: Mostrar una lista de opciones, como categorías, países o nombres de producto en un formulario compacto
- Filtrado y Búsqueda: Habilita la búsqueda o el filtrado en una lista para ayudar a los usuarios a encontrar opciones rápidamente
- Contenido personalizado: Muestra contenido personalizado como iconos, texto formateado o tipos de data complejos junto a los elementos de la lista
- Selección Múltiple: Permite a los usuarios seleccionar varios elementos de una lista, adecuado para formularios multi-opción o preferencias
- Acciones masivas: Habilita acciones masivas en los elementos seleccionados, como eliminar, actualizar o exportar varias opciones a la vez

Estilizar ListBox y MultiSelectListBox:

- Usando Theme Variants de Vaadin
- Usando estilos inline
- Usando CSS
- Configurando Variables CSS personalizadas
- Usando estilos personalizados con Shadow DOM

Solo se ve el uso de Shadow DOM.

## MessageInput

MessageInput es un componente Vaadin usado para crear aplicaciones de tipo chat donde el usuario puede introducir un mensaje y este se añadirá a la lista de mensajes.

Típicamente, se usa en aplicaciones que involucran mensajes, chats en vivo, o cualquier interface donde el usuario necesita enviar un mensaje basado en texto.

El componente provee un campo de entrada de texto junto con un botón de envío, haciendo ideal para UIs de chat.

Las características clave son:

- Text Input Area
- Send Button
- Event Listener for Message Sending

Se ha creado en el package `views/home/components` un ejemplo `MessageInputView.java`.

Usos:

- Aplicaciones de chat: Permite a los usuarios escribir y enviar mensajes, típicamente en chat o aplicaciones de mensajes
- Soporte a clientes o soportes de tickets
- Secciones de comentarios: Usado como un text area por usuarios para escribir información detallada en formularios, como descripciones o notas
- Interacciones en tiempo real: Habilita comunicación en tiempo real, como en soporte de clientes, sistemas de chat o forums.
- Bots interactivos

## MessageList

Este componente Vaadin es un elemento UI diseñado para mostrar una lista de mensajes en una aplicación.

Suele usarse en interfaces de chat, sistemas de mensajería o características similares.

Este componente simplifica el proceso de presentar mensajes de una forma límpia, estructurada y responsiva.

Las características clave son:

- Representación de mensajes:
  - Los mensajes pueden representar texto, contenido HTML, u otro contenido personalizado
  - Cada mensaje puede incluir metadata como el nombre del remitente, marca de tiempo y avatar
- Formateo
  - Los mensajes pueden formatearse de manera diferente basados en si son enviados o recibidos, permitiendo una distinción clara entre mensajes de usuario y respuestas
  - Soporta formateo personalizado en los mensajes para la fecha y la hora
- Soporte de Avatar
  - Cada mensaje puede asociarse con un avatar, típicamente usado para mostrar una imagen de perfil o un icono representando al remitente

Se ha creado en el package `views/home/components` un ejemplo `MessageListView.java`.

Usos:

- Mostrar notificaciones: Mostrar una serie de mensajes o notificaciones al usuario, como success, warning o mensajes de error
- Interfaz de chat o mensajería: Mostrar una lista de mensajes en una interfaz de tipo chat donde los usuarios pueden ver conversaciones o notificaciones
- Seguimiento de eventos: Mostrar eventos o logs que rastrean acciones o estados del sistema, como actualizaciones del sistema o actividades del usuario
- Comentarios de los usuarios: Presentar una retroalimentación continua, alertas o actualizaciones en un formato desplazable
- Actualizaciones en tiempo real: Actualizar dinámicamente la lista con nuevos mensajes o alertas sin refrescar la página, útil en notificaciones en vivo

Estilizar MessageList:

- Usando Theme Variants de Vaadin
- Usando estilos inline
- Usando CSS
- Configurando Variables CSS personalizadas
- Usando estilos personalizados con Shadow DOM

Solo se ve el uso de Shadow DOM.

## MultiSelectComboBox

Es un componente UI de Vaadin que combina características de un ComboBox con la habilidad de seleccionar múltiples opciones del desplegable, haciéndola útil en situaciones donde los usuarios necesitan elegir más de una opción de una lista.

Las características clave son:

- Selección múltiple: A diferencia de un ComboBox estándar donde los usuarios solo pueden seleccionar un elemento, el multi-select combo box permite seleccionar varios elementos a la vez
- Opciones personalizables: La lista desplegable puede contener varios tipos de data, desde valores sencillos de texto a objetos más complejos. Soporta plantillas de elementos para presentación personalizada de cada elemento
- Menú desplegable de búsqueda: Provee un campo de búsqueda donde los usuarios pueden filtrar opciones escribiendo, lo que es especialmente útil cuando tratamos con conjuntos de datos muy grandes

Se ha creado en el package `views/home/components` un ejemplo `MultiSelectComboBoxView.java`.

Usos:

- Selección de múltiples opciones: Permite a los usuarios seleccionar múltiples valores de una lista desplegable, adecuado para filtrar o categorizar data
- Tagging y Etiquetado: Habilita a los usuarios asignar varios tags o etiquetas a un elemento, como categorización de productos o posteos
- Preferencias o configuraciones: Permite a los usuarios elegir múltiples preferencias o configuraciones, como seleccionar intereses o tipos de notificaciones
- Filtrado dinámico: Usado en búsquedas o filtros para permitir la selección de varios criterios de filtro a la vez, como filtrar productos por múltiples categorías
- Enlace de datos: Enlazar a una lista de opciones y mostrar valores seleccionados dinámicamente, con autocompletado o características de búsqueda para conjuntos de data grandes

Estilizar MultiSelectComboBox:

- Usando Theme Variants de Vaadin
- Usando estilos inline
- Usando CSS
- Configurando Variables CSS personalizadas
- Usando estilos personalizados con Shadow DOM

Solo se ve el uso de Shadow DOM.

## Notification

Se ha creado en el package `views/home/components` un ejemplo `NotificationView.java`.

## ProgressBar

Es un componente Vaadin que se usa para indicar visualmente el progreso de una operación, como la carga de data o el completado de una tarea, en una aplicación web responsiva e interactiva.

Las características clave son:

- Indicador visual de progresión: La barra de progreso muestra una representación visual de progreso, normalmente con una barra horizontal que se va rellenando conforme progresa la tarea
- Modo Indeterminado: La barra de progreso de Vaadin puede operar en modo indeterminado, es decir, no muestra una cantidad específica de progreso, sino que indica que la tarea está en curso (por ejemplo, cuando el tiempo exacto para terminar es desconocido)
- Valores personalizables: Se puede configurar el valor de progreso programáticamente, desde un rango 0.0 (0%) a 1.0 (100%). La barra de progreso se irá rellenando en consecuencia
- Apariencia personalizable: La apariencia de la barra de progreso puede personalizarse fácilmente con CSS. Se puede modificar el color, tamaño y estilo para que coincida con el diseño de la aplicación

Se ha creado en el package `views/home/components` un ejemplo `ProgressBarView.java`.

Usos:

- Indicar progreso de tarea: Mostrar el progreso de tareas u operaciones de larga duración, como subidas de ficheros, procesamiento de data o generación de informes
- Retroalimentación sobre acciones de usuario: Proveer a los usuarios con retroalimentación visual durante procesos en segundo plano como guardados, descargas o sincronizaciones de data
- Indicadores de carga: Mostrar que un proceso sigue en ejecución cuando el sistema está trabajando en algo, mejorando la experiencia de usuario y evitando la confusión
- Finalización de tarea: Representar el porcentaje de finalización de un proceso, como un formulario multi-paso o un proceso de instalación
- Actualizaciones en tiempo real: Actualizar dinámicamente la barra de progreso a la vez que una tarea se ejecuta, manteniendo a los usuarios informados del progreso sin necesidad de refrescar la página

Estilizar ProgressBar:

- Usando Theme Variants de Vaadin
- Usando estilos inline
- Usando CSS
- Configurando Variables CSS personalizadas
- Usando estilos personalizados con Shadow DOM

## RadioButton y RadioButtonGroup

RadioButton no es un componente independiente, sino que son usados junto a RadioButtonGroup.

RadioButtonGroup es un componente Vaadin que permite a los usuarios seleccionar una opción de un grupo de opciones, funcionando parecido al HTML radio button tradicional.

Las características clave son:

- Exclusividad mútua: Solo puede seleccionarse una opción dentro de un RadioButtonGroup. Seleccionar una nueva opción deselecciona automáticamente la anterior, similar a como funcionar radiobutton en un formulario HTML
- Asociación de etiquetas: Cada elemento del RadioButtonGroup está asociado con una etiqueta que describe la opción, dando contexto al usuario
- Enlace de datos: RadioButtonGroup puede vincularse fácilmente a un modelo de datos o usarse en formularios para una integración perfecta con la lógica backend

Se ha creado en el package `views/home/components` un ejemplo `RadioButtonView.java`.

Usos:

- Encuestas y formularios: Usar RadioButton en preguntas donde solo una opción es posible, como género, niveles de satisfacción o preguntas si/no
- Preferencias de configuración: Al configurar preferencias, tales como selección de tema, donde el usuario solo puede seleccionar una opción
- Filtros: En aplicaciones e-commerce o dashboard, puede usarse en filtros, como seleccionar una categoría de producto o un método de ordenación

Estilizar RadioButton:

- Usando Theme Variants de Vaadin
- Usando estilos inline
- Usando CSS
- Configurando Variables CSS personalizadas
- Usando estilos personalizados con Shadow DOM

## Select

Es un componente Vaadin diseñado para proveer una lista desplegable en la que se puede seleccionar un solo valor de una lista de opciones.

Las características clave son:

- Selección única: Permite a los usuarios seleccionar solo un elemento de una lista de opciones
- Apariencia personalizable: La apariencia puede ser fácilmente personalizable para que coincida con el diseño de la aplicación. Las opciones incluyen iconos, plantillas personalizadas y diferentes layouts para cada elemento
- Responsivo y accesible: Select está diseñado para ser totalmente responsivo y se adhiere a los estándares de accesibilidad, asegurando que funciona bien en dispositivos móviles y es utilizable por personas con discapacidad
- Navegación por teclado: Soporta navegación total usando el teclado, haciéndolo fácil de usar sin ratón. Los usuarios pueden usar el teclado para navegar por las opciones y seleccionar el valor deseado

Se ha creado en el package `views/home/components` un ejemplo `SelectView.java`.

Usos:

- Seleccionar de un desplegable: Permitir seleccionar a los usuarios una opción de una lista predefinida, como un país, categoría o tipo de producto
- Entradas de formulario: Usado en formularios donde los usuarios necesitan elegir de un conjunto de opciones, como títulos de trabajo, departamentos o métodos de venta
- Contenido dinámico: Poblar el campo de selección dinámicamente basado en data de un servidor o base de datos, haciéndolo adaptable a cambios de contenido
- Filtrado o búsqueda: Ayudar a los usuarios a filtrar o buscar contenido ofreciendo una lista de opciones seleccionables, como opciones de ordenación o filtros de estátus
- Soporte multi-idioma: Proveer a los usuarios con la posibilidad de seleccionar su idioma predilecto, mejorando la experiencia de usuario en aplicaciones internacionalizadas

Estilizar Select:

- Usando Theme Variants de Vaadin
- Usando estilos inline
- Usando CSS
- Configurando Variables CSS personalizadas
- Usando estilos personalizados con Shadow DOM

Solo se ve el uso de Shadow DOM.

## UploadField

Este componente Vaadin permite a los usuarios subir un fichero desde su sistema local a un servidor.

Provee una interfaz amigable con un area para arrastrar y soltar o un botón para navegar y seleccionar ficheros.

El componente Upload maneja el proceso de subida del fichero, y se puede configurar para gestionar varios aspectos como el límite del tamaño del fichero, tipos de ficheros aceptados, y como se procesan en el servidor los ficheros subidos.

Las características clave son:

- Drag and Drop: Los usuarios pueden arrastrar ficheros directamente al area del componente
- Selección de ficheros: Los usuarios pueden navegar por sus ficheros usando un selector de archivos estándar
- Varios ficheros: El componente puede configurarse para permitir subir varios ficheros de una vez
- Gestión de eventos: Los desarrolladores pueden manejar eventos como cuando un fichero comienza a subir, progresa o finaliza

Se ha creado en el package `views/home/components` un ejemplo `UploadView.java`.

Usos:

- Subida de ficheros: Permitir a los usuarios subir ficheros desde su sistema local a la aplicación, como documentos, imágenes o videos
- Soporte para arrastrar y soltar: Habilitar a los usuarios arrastrar y soltar ficheros en el campo de upload para un envío de archivos fácil
- Envío de formularios: Usado como parte de un formulario para enviar adjuntos, como imágenes de perfil, currículums o imágenes de productos
- Seguimiento de progreso: Mostrar el progreso de subida de un fichero, proveyendo retroalimentación al usuario sobre el estátus del proceso de subida

Estilizar Upload:

- Usando estilos inline
- Usando CSS
- Configurando Variables CSS personalizadas
- Usando estilos personalizados con Shadow DOM

Solo se ve el uso de Shadow DOM.

## MenuBar

MenuBar es un componente de interfaz de usuario usado para navegación.

Las características clave son:

- Jerarquías de menús: Permite crear menús multi-nivel con submenus anidados
- Gestión de eventos: Soporta event listeners para interacción del usuario como clics sobre elementos del menú
- Personalizable: Puede estilizarse y personalizarse según los requerimientos del diseño de la aplicación
- Responsivo: Se adapta bien a diferentes tamaños de pantalla y dispositivos

Se ha creado en el package `views/home/components` un ejemplo `MenuBarView.java`.

Usos:

- Navegación: Puede usarse para navegar entre diferentes vistas o páginas en una aplicación web
- Acciones contextuales: Mostrar acciones específicas del contexto dependiendo de la página actual o selección del usuario
- Menús de ficheros: Al igual que en aplicaciones de escritorio, puede usarse para implementar menús fichero, edición y vista, con acciones asociadas
- Agrupación de acciones: Los menús ayudan a agrupar acciones o funcionalidades relacionadas bajo categorías, haciendo más fácil a los usuarios encontrar acciones específicas
- Menús desplegables: Crear desplegables para varias opciones, como configuraciones de cuenta, notificaciones u otros elementos interactivos

Estilizar MenuBar:

- Usando Theme Variants de Vaadin
- Usando estilos inline
- Usando CSS
- Configurando Variables CSS personalizadas
- Usando estilos personalizados con Shadow DOM

Solo se ve el uso de Shadow DOM.

## Tabs

Los tabs (pestañas) son patrones UI comunes que permiten que los usuarios cambien entre secciones o contenido diferentes dentro de una página.

Las características clave son:

- Creación de pestaña simple: Se pueden crear pestañas fácilmente usando el componente Tab de Vaadin, lo que permite una navegación más limpia y organizada en aplicaciones web
- Horizontal o Vertical Layout: Los Tabs de Vaadin pueden mostrarse en layouts horizontales o verticales, dependiendo de como se quiera organizar el contenido de la pestaña
- Cambio de contenido dinámico: Usando Tabs en combinación con elementos Tab, los desarrolladores pueden mostrar u ocultar contenido dinámicamente, basado en que tab está seleccionado
- Estilo personalizable: Vaadin proporciona la posibilidad de personalizar el look and feel de las pestañas con CSS, permitiendo a las pestañas alinearse con el diseño de la aplicación

Se ha creado en el package `views/home/components` un ejemplo `TabView.java`.

Usos:

- Contenido organizado: Dividir el contenido en secciones o vistas separadas, permitiendo a los usuarios cambiar entre ellos sin dejar la página
- Navegación: Proveer a los usuarios de una forma clara y fácil de usar, para navegar entre secciones diferentes de la aplicación, como ajustes, perfiles o reportes
- Eficiencia de espacio: Ahorra espacio en pantalla al agrupar información relacionada o herramientas en pestañas, reduciendo el desorden y mejorando la disposición de la UI
- Agrupación contextual: Organizar funcionalidades relacionadas (por ejemplo pasos de formularios, categorías de productos o tipos de contenido) bajo diferentes pestañas para una mejor compresión para el usuario
- Cambio de pestañas dinámico: Habilitar comportamiento dinámico donde el contenido de las pestañas se actualiza basado en la interacción de usuario, tal como mostrar diferentes detalles u opciones en el mismo layout

Estilizar Tabs:

- Usando Theme Variants de Vaadin
- Usando estilos inline
- Usando CSS
- Configurando Variables CSS personalizadas
- Usando estilos personalizados con Shadow DOM

Solo se ve el uso de Shadow DOM.

## Grid

El componente Grid de Vaadin permite a los desarrolladores mostrar e interactuar con grandes conjuntos de datos en forma tabular, similar a las tablas HTML, pero con características más poderosas para gestionar grandes cantidades de datos de forma eficiente.

Se usa comúnmente en aplicaciones empresariales donde las tablas y las listas son los elementos UI centrales.

Las características clave son:

- Carga perezosa (Scroll virtual): Grid de Vaadin soporta scroll virtual, lo que significa ue solo las filas visibles son renderizadas y cargadas en el navegador. Conforme el usuario va haciendo scroll, más data se carga bajo demanda, lo que optimiza el rendimiento cuando se trabajan con grandes conjuntos de datos
- Ordenación y filtrado: Las columnas del Grid pueden ordenarse y filtrarse fácilmente, tanto en la parte cliente como servidor. Esto mejora la experiencia de usuario, especialmente cuando navegamos entre grandes conjuntos de datos
- Gestión de columnas: Los desarrolladores pueden definir múltiples columnas con diferentes tipos de datos (texto, números, fechas, etc.). Las columnas pueden reordenarse, ocultarse, o fijarse basado en las preferencias de usuario o configuración programática
- Enlace de datos: El Grid Vaadin puede vincularse fácilmente a varias fuentes de datos, incluyendo APIs backend, bases de datos, o incluso estructuras de datos en memoria. Soporta enlazado de datos a través de proveedores de datos Vaadin
- Diseño personalizable: El Grid es altamente configurable. Se pueden añadir renderizados personalizados para celdas, presentación de data formateada (por ejemplo, monedas, fechas), o añadir estilos HTML/CSS personalizados que se adapten a nuestras necesidades

Se ha creado en el package `views/home/components` un ejemplo `GridView.java`.
Se ha creado en el package `views/home/components` un ejemplo `GridFilterView.java`.

Usos:

- Tablas de datos empresariales: El Grid Vaadin es ideal para aplicaciones empresariales donde se muestran grandes conjuntos de datos, como CRM, sistemas ERP o herramientas de gestión de inventario
- Dashboards e informes: En dashboards, los grids se suele usar para mostrar data tabulada para informes, resúmenes, o resultados analíticos, donde los usuarios pueden necesitar filtrar, ordenar y analizar la data
- Sistemas de entrada de data: Adecuado para aplicaciones donde los usuarios necesitan introducir, editar o revisar largos conjuntos de data estructurada, como envíos de formularios o procesamiento masivo de registros
- Sistemas financieros: Los grids se usan mucho en aplicaciones financieras o de contabilidad para mostrar y manipular transacciones y auditoría de logs
- Aplicaciones Web con mucha data: El Grid Vaadin es usado en aplicaciones web donde el rendimiento es crítico en el trato de grandes conjuntos de datos, como monitoreo en tiempo real o paneles de control en vivo

## Grid Carga Perezosa

La carga perezosa de un Grid es una técnica muy útil para mejorar el rendimiento cuando tratamos con conjuntos de datos muy grandes.

La idea consiste en cargar solo la porción visible de data en pantalla, y mientras el usuario va haciendo scroll, la data se va cargando.

El Grid de Vaadin provee un soporte integrado para la carga perezosa (lazy loading) a través de su mecanismo data provider.

Se ha creado en el package `views/home/components` un ejemplo `GridLazyView.java`.

## Práctica

Se ha realizado en el package `views/home/components` una práctica `PracticaView.java`.

Y a la manera del instructor, se ha creado en el package `views/home/components` su resolución de la práctica `PracticaInstructorView.java`.

