# Jorge Alonso López

# GREENTECH BIOFUELS

El proyecto se centra en la gestión de una fábrica de biodiesel de segunda generación.
Habrá dos tipos de usuarios distintos: clientes y administradores.

El cliente podrá realizar pedidos (comprar productos y vender materias primas), listar, ordenar y cancelar sus pedidos y modificar sus datos. El administrador podrá listar a los clientes, acceder a su información, eliminar clientes y cambiar los precios.

Al inicio se encuentra una página de información donde ambos tipos de usuario van a poder ver la información. En esta se encuentran las opciones de iniciar sesión (se requiere nombre de usuario y contraseña, para clientes o administradores) y la opción de registro solo para clientes. Una vez hayan iniciado sesión, podrán continuar viendo la página informativa, acceder a su área o cerrar sesión.

### Cliente

Si desea registrarse le pedirá su nombre y apellidos, DNI, fecha de nacimiento, correo, teléfono, país, dirección, nombre de usuario y contraseña. Una vez registrado, tendrá que esperar a que el administrador le permita el acceso.
Estos datos (excepto el DNI y el nombre de usuario) se podrán modificar posteriormente, junto con la opción de darse de baja o de alta.

Como cliente, tendrá acceso a un listado de todos los pedidos, donde se informa sobre el ID del mismo, fecha de entrega/recogida, fecha de salida del transporte y precio final. También se podrá cancelar algún pedido según condiciones que se citarán más adelante. La lista de pedidos se puede ordenar y filtrar según los siguientes criterios:
 - Precio final
 - Rango de precio
 - Si la operación está pendiente, con respecto a la fecha de entrega o recepción
 
Para cancelar un pedido, la fecha de salida del transporte debe ser posterior a la fecha actual.
Junto a esta información, se muestra el ID del pedido y la cantidad y precio del producto comprado o vendido.

A la hora de comprar un producto o vender una materia prima se requerirán las cantidades de los distintos productos y la fecha de entrega/recogida. El precio final y la fecha de salida las proporciona el sistema.

Para la selección de fecha de entrega/recogida se tendrá en cuenta la duración temporal del envío. Si la fecha estimada de salida del transporte es anterior a la fecha en la que se ha realizado el pedido, la operación se cancelará.

### Administrador

El administrador tendrá la lista de los clientes registrados. Si selecciona un cliente, tendrá acceso, en forma de lista, a sus pedidos realizados con las opciones de ordenado y filtrado ya mencionadas anteriormente.
Podrá aceptar o rechazar el acceso de nuevos clientes.

Podrá visualizar los clientes, ordenados y filtrados por:
 - Buscador de nombre y apellidos
 - Dado de alta o baja
 
Podrá cambiar el precio de los productos, materias primas y transporte. También podrá añadir o eliminar nuevos productos y materias primas.

## Opciones a futuro

### Administrador

El administrador podrá gestionar la producción. Podrá retrasar los pedidos de un cliente, por los motivos oportunos, siempre y cuando el envío no haya salido. Si esto ocurriese, se devolvería, a dicho cliente, un porcentaje del precio final. La cuantía porcentual se calculará en función de los días de retraso.

El administrador podrá filtrar a los clientes por:
 - Número de pedidos totales
 - Número de pedidos restantes
 - Dinero total invertido
 
Gestionará la producción seleccionando qué materia prima se va a consumir. Tendrá un historial de la producción y consumo, y una pequeña lista a 60 días vista con estimaciones del consumo de las materias primas y la llegada y retirada de material.

### Cliente

El cliente podrá ordenar los pedidos en función de:
 - Fecha de entrega o recepción
 - Tipo de pedido, ya sea compra o venta
 - Los productos que contenga el pedido
 
### Administrador y cliente

Ambos tendrán una lista con mensajes en los que se mostrará: fecha, remitente y primeras palabras del mensaje. Al seleccionar uno, mostrará el remitente, fecha y contenido íntegro.
Podrán enviar un nuevo mensaje, indicando el destinatario y el contenido.
