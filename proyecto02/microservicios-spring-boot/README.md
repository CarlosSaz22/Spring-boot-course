Descripción del Proyecto 2
Este proyecto implementa una arquitectura modular de microservicios para la gestión integral de productos, pedidos, inventarios, notificaciones y mensajería, utilizando tecnologías modernas para alto rendimiento y escalabilidad.

Características Principales
1. Modularidad
El proyecto está diseñado de forma modular, donde cada servicio cubre una responsabilidad específica (producto, orden, inventario, notificación, mensajería, autenticación, etc.).

Esta modularidad permite que cada microservicio se desarrolle, despliegue, escale y mantenga de manera independiente, facilitando la evolución y mejora continua del sistema.

2. Tecnologías Utilizadas
Lenguaje: Java 21
Framework: Spring Boot 3.5.4

Contenerización y Orquestación: Docker, con Docker Compose (versión 3.7)

3. Componentes Principales
API Gateway: Punto único de entrada, distribuye las solicitudes de los usuarios según el servicio.

Auth Server: Implementado con Keycloak (respaldado por MySQL), gestiona seguridad, usuarios y roles usando OAuth/JWT.

Product Service: Administra productos y catálogo. Base de datos: MongoDB.

Order Service: Procesa pedidos y los almacena en MySQL. Cuando se crea una orden, envía eventos por Apache Kafka y por RabbitMQ.

Inventario Service: Administra y actualiza el inventario de productos (MySQL).

Notification Service: Recibe eventos a través de la cola de Apache Kafka para enviar notificaciones relacionadas con las órdenes.

Message-Service: Utiliza RabbitMQ para distribuir mensajes cuando se crea una orden, por ejemplo, emitir alertas o interactuar con otros sistemas.

4. Integración y Comunicación
Apache Kafka: Broker de mensajería asíncrona, útil para desacoplar y escalar la comunicación entre servicios, especialmente entre Order y Notification Service.

RabbitMQ: Broker adicional de mensajería, para el servicio de mensajes dedicado (Message-Service).

Eureka (Service Discovery): Localización dinámica y automática de microservicios.

Config Server: Centraliza la configuración compartida entre servicios.

5. Observabilidad y Monitorización
Zipkin: Seguimiento distribuido y trazabilidad de peticiones.

Prometheus: Métricas automáticas de estado y rendimiento.

Grafana: Visualización avanzada de métricas y análisis.

6. Contenerización y Despliegue
Todos los servicios se despliegan con Docker Compose (versión 3.7), integrando bases de datos, mensajería (Kafka y RabbitMQ), autenticación (Keycloak), monitorización (Zipkin, Prometheus, Grafana) y orquestación (Zookeeper).
