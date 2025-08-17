Proyecto 01: Este proyecto implementa un sistema de reservas utilizando microservicios desarrollados con Java 21 y Spring Boot 3.0. Los servicios gestionan usuarios, hoteles y calificaciones, cada uno con su propia base de datos (MySQL, PostgreSQL y MongoDB respectivamente). Se integran componentes como Eureka para el registro de servicios, API Gateway para el acceso centralizado, un servidor de configuración, comunicación REST entre microservicios (RestTemplate y OpenFeign), tolerancia a fallos con Resilience4j y seguridad basada en JWT. Es un ejemplo robusto y escalable de arquitectura moderna basada en microservicios.

proyecto 02: Es una plataforma modular basada en microservicios, desarrollada en Java 21 y Spring Boot 3.5.4, donde cada servicio cumple una función específica y puede escalarse o desplegarse de forma independiente. La autenticación centralizada se realiza con Keycloak (respaldado por MySQL). El sistema utiliza Apache Kafka y RabbitMQ como brokers de mensajería para gestionar eventos y notificaciones asíncronas entre los servicios (órdenes, inventario, mensajería, notificaciones). La integración, monitorización y trazabilidad están soportadas por herramientas como Eureka, Config Server, Zipkin, Prometheus y Grafana. Todo el entorno se implementa y orquesta mediante Docker Compose (versión 3.7), lo que facilita el despliegue y la administración de los servicios y sus dependencias.

Proyecto 03:
La aplicación gestiona productos con Axon Framework usando CQRS y Event Sourcing.

Para guardar productos, se envía un Command (CreateProductCommand) y Axon registra el evento (ProductCreatedEvent), asegurando todas las modificaciones quedan históricamente registradas.

Para listar productos, se usa una Query (ListProductsQuery) y el sistema devuelve la información actualizada gracias a proyecciones reactivas a los eventos.

Esta separación permite un sistema escalable, flexible y fácil de auditar.



Proyecto 04:
La aplicación simula un cajero automático utilizando Axon Framework, CQRS y Event Sourcing para gestionar cuentas bancarias.

Commands: Crear cuenta (CreateAccountCommand), sumar fondos (CreditAccountCommand), restar fondos (DebitAccountCommand). Cada acción genera eventos históricos y auditables.

Queries: Listar cuentas (ListAccountsQuery) y buscar una cuenta específica (FindAccountByIdQuery) utilizando proyecciones actualizadas por los eventos.

Así, el sistema separa claramente la lógica de modificación y consulta, asegurando transparencia y facilidad para escalar y auditar.


Proyecto 05:
La aplicación implementa Axon Framework con CQRS, Event Sourcing y Saga por orquestación para orquestar el proceso de gestión de pedidos distribuidos.

CQRS y Event Sourcing:
Se separan los commands (modificaciones como crear orden, pagar, enviar) y queries (consultas de pedidos, facturas, envíos). Cada acción significativa se registra como evento, garantizando trazabilidad y recuperación histórica.

Saga por orquestación:
La Saga coordina todo el flujo: recibe un comando de creación de orden, envía comandos para factura y envío, y maneja los eventos generados por cada servicio, asegurando la consistencia y comunicación entre procesos distribuidos.

Axon Framework:
Gestiona los handlers, los eventos y la definición de la saga, facilitando automatización, integridad y escalabilidad.

Este diseño permite procesos distribuidos auditable y flexible, donde cada etapa del pedido (creación, pago, envío) está coordinada y registrada usando Axon, CQRS y Event Sourcing.
