Proyecto 01: Sistema de Reservas con Microservicios en Java 21 y Spring Boot 3.0
📌 Descripción
Este repositorio presenta el desarrollo de un sistema distribuido basado en microservicios, construido con Java 21 y Spring Boot 3.0, orientado a la gestión integral de usuarios, hoteles y calificaciones. El sistema destaca por su arquitectura moderna, integración de tecnologías clave y patrones de diseño para aplicaciones empresariales robustas y escalables.

🏗️ Arquitectura de Microservicios
El sistema se compone de tres microservicios principales:

usuario-service
Encargado de la gestión de usuarios. Se conecta a una base de datos MySQL para almacenar y administrar la información de los usuarios.

hotel-service
Administra la información de los hoteles. Utiliza una base de datos PostgreSQL como almacenamiento principal.

calificacion-service
Gestiona las calificaciones que los usuarios otorgan a los hoteles. Toda la información se guarda en una colección de MongoDB para mayor flexibilidad.

🔌 Integraciones y Componentes
Eureka Server:
Implementado como Discovery Server para el registro, descubrimiento y balanceo dinámico de los microservicios.

Comunicación entre Microservicios:
Se utiliza tanto RestTemplate como OpenFeign Client para realizar llamadas RESTful y abstraer la comunicación interna.

API Gateway:
Implementado para centralizar el ingreso a los microservicios, facilitar el enrutamiento, aplicar filtros de seguridad, logging y control de acceso.

Servidor de Configuración (Config Server):
Permite tener la configuración centralizada y compartida por todos los microservicios, evitando duplicidad y facilitando cambios globales.

Resilience4j (Circuit Breaker y Retry):
Incorporado para dotar de tolerancia a fallos a la arquitectura, gestionando circuitos abiertos y reintentos ante errores en la comunicación.

Seguridad con JWT:
Todos los endpoints protegidos mediante autenticación y autorización basada en JSON Web Tokens (JWT), asegurando que solo usuarios autenticados puedan acceder a los recursos.

🚀 Tecnologías
Java 21

Spring Boot 3.0

Spring Cloud (Eureka, Config Server, Gateway)

Resilience4j

Spring Security + JWT

RestTemplate y OpenFeign

MySQL, PostgreSQL y MongoDB

Maven o Gradle

💡 Aspectos Destacados
Arquitectura basada en microservicios, altamente desacoplada y escalable.

Separación clara de responsabilidades: usuario, hotel y calificación.

Integración multiplataforma con tres motores de bases de datos.

Registro, descubrimiento y comunicación robusta entre microservicios.

Seguridad avanzada para APIs con JWT.

Centralización de la configuración.

Resiliencia y tolerancia a fallos implementada.
