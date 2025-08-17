Proyecto 01: Este proyecto implementa un sistema de reservas utilizando microservicios desarrollados con Java 21 y Spring Boot 3.0. Los servicios gestionan usuarios, hoteles y calificaciones, cada uno con su propia base de datos (MySQL, PostgreSQL y MongoDB respectivamente). Se integran componentes como Eureka para el registro de servicios, API Gateway para el acceso centralizado, un servidor de configuración, comunicación REST entre microservicios (RestTemplate y OpenFeign), tolerancia a fallos con Resilience4j y seguridad basada en JWT. Es un ejemplo robusto y escalable de arquitectura moderna basada en microservicios.

proyecto 02: Es una plataforma modular basada en microservicios, desarrollada en Java 21 y Spring Boot 3.5.4, donde cada servicio cumple una función específica y puede escalarse o desplegarse de forma independiente. La autenticación centralizada se realiza con Keycloak (respaldado por MySQL). El sistema utiliza Apache Kafka y RabbitMQ como brokers de mensajería para gestionar eventos y notificaciones asíncronas entre los servicios (órdenes, inventario, mensajería, notificaciones). La integración, monitorización y trazabilidad están soportadas por herramientas como Eureka, Config Server, Zipkin, Prometheus y Grafana. Todo el entorno se implementa y orquesta mediante Docker Compose (versión 3.7), lo que facilita el despliegue y la administración de los servicios y sus dependencias.

proyecto 03: La aplicación de ejemplo con Axon Framework trata sobre la gestión de productos utilizando el patrón CQRS y Event Sourcing:
Guardar productos (Command):
Para agregar un producto, se manda un Command, por ejemplo CreateProductCommand, que contiene la información del nuevo producto (ID, nombre, descripción, precio, etc.). El CommandHandler recibe esta orden, valida y aplica la lógica de negocio (por ejemplo, evitar duplicados o validar campos). Luego, Axon registra un evento, como ProductCreatedEvent, y lo persiste. Así, cada modificación queda registrada como un evento histórico.
Listar productos (Query):
Para consultar los productos, se envía una Query, por ejemplo ListProductsQuery. El QueryHandler accede a las proyecciones o modelo de lectura, y devuelve el listado de productos. Las proyecciones se mantienen actualizadas reaccionando a los eventos (ejemplo: ProductCreatedEvent), asegurando que las consultas siempre reflejen la última información disponible.
Esta estructuración permite separar claramente la lógica de modificación (guardar productos) de la lógica de consulta (listar productos), mostrando el valor del patrón CQRS apoyado por el almacenamiento de eventos de Axon Framework. Así, el sistema es más escalable, flexible y auditado, y el flujo del ejemplo resulta claro y sencillo para aprendizaje en sistemas modernos.

proyecto 04: El proyecto implementa Axon Framework aplicando los patrones CQRS y Event Sourcing para simular una aplicación de cajero automático, siguiendo los mismos principios del ejemplo anterior con productos, pero ahora adaptado a la gestión de cuentas bancarias.
Commands para modificar:
Crear una cuenta: Se envía un CreateAccountCommand con los datos necesarios para crear una nueva cuenta.
Sumar fondos: Se usa un CreditAccountCommand para agregar saldo a la cuenta mediante un crédito.
Restar fondos: Se envía un DebitAccountCommand para descontar saldo mediante un débito.
Cada uno de estos comandos es manejado por un CommandHandler que valida la operación, ejecuta la lógica de negocio y registra un evento representativo (por ejemplo, AccountCreatedEvent, AccountCreditedEvent, AccountDebitedEvent) en el event store. Esto permite mantener un historial completo y auditable de todas las operaciones sobre cada cuenta.
Queries para consultar:
Listar cuentas: Mediante un ListAccountsQuery se obtiene un listado de todas las cuentas existentes.
Buscar cuenta específica: Con un FindAccountByIdQuery se consulta la información detallada de una cuenta en particular.
Los QueryHandlers consultan las proyecciones actualizadas que reflejan el estado actual de las cuentas, las cuales se mantienen sincronizadas al escuchar los eventos generados por los comandos.
Con esta estructura, la aplicación simula un cajero automático completo donde las modificaciones de estado son estrictamente por comandos y las consultas mediante queries, demostrando la separación clara de responsabilidades y la potencia de Event Sourcing para preservar todo el historial de operaciones en Axon Framework. Esto hace que el sistema sea robusto, escalable y fácil de auditar.


Proyecto 5: Saga por Orquestación con Axon, CQRS y Event Sourcing
CQRS y Event Sourcing:
El sistema sigue separando las operaciones de modificación (Commands) y consulta (Queries) como en los proyectos previos, apoyándose en el almacenamiento de eventos para registrar todo el historial de pedidos, pagos y envíos.
Implementación de la Saga por Orquestación:
El flujo de negocio se coordina a través de la Saga, que actúa como orquestador central. Esto permite manejar procesos distribuidos y garantizar la consistencia entre servicios independientes como el de pedidos, pagos y envíos.
Flujo típico del proceso:
El Order Management Saga recibe un comando para crear una orden e interactúa con el Order Service y actualiza los datos de la orden.
La saga envía un Create Invoice Command al Payment Service, que procesa el pago y emite un Invoice Created Event de vuelta a la saga.
La saga, tras recibir el evento de factura creada, envía un Create Shipping Command al Shipping Service.
El Shipping Service maneja el envío y retorna un Order Shipped Event a la saga, finalizando el flujo.
CQRS en cada servicio:
Los comandos se usan para modificar el estado (ejemplo: crear orden, pagar, enviar), y las queries para consultar el estado del pedido, factura y envío en cualquier punto, asegurando la separación de responsabilidades.
Event Sourcing:
Cada transición significativa (orden creada, factura emitida, pedido enviado) se registra como evento, permitiendo reconstruir el flujo completo de la orden y facilitar auditoría y recuperación del estado.
Axon:
Facilita el manejo de los handlers de comandos, queries, eventos y la definición e ejecución de la saga, automatizando la orquestación de flujos distribuidos y manteniendo la integridad consistente del sistema.
Esta arquitectura permite construir un sistema robusto y flexible para la gestión de órdenes distribuidas, donde cada paso del proceso es auditable, escalable y fácilmente extensible gracias a CQRS, Event Sourcing y la Saga por orquestación soportados por Axon Framework.
