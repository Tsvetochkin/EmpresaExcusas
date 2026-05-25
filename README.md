# Excusas S.A. - Sistema de Gestión de Demoras

Este proyecto es una solución integral para la automatización del procesamiento de excusas de empleados, desarrollada para la cátedra de Programación Orientada a Objetos. El sistema utiliza una arquitectura desacoplada basada en múltiples patrones de diseño para garantizar la extensibilidad y el mantenimiento.

## 🛠 Arquitectura y Patrones de Diseño

El sistema se basa en los siguientes patrones de diseño, aplicados para resolver los problemas de acoplamiento y repetición de lógica:

### 1. Chain of Responsibility (Cadena de Responsabilidad)
Se implementó para manejar la jerarquía de evaluación de excusas. Cada responsable (`Recepcionista`, `Supervisora`, `GerenteRRHH`, `CEO`) decide si procesa la excusa o la deriva al siguiente eslabón.
*   **Beneficio:** Permite configurar el orden de evaluación dinámicamente sin modificar las clases de dominio.

### 2. State (Estado) + Delivery de Estados
Cada responsable posee un `DeliveryModo` que gestiona su comportamiento laboral: **Productivo**, **Normal** o **Vago**.
*   **Lógica de transición:** El estado cambia automáticamente según la cantidad de excusas procesadas (0-5: Productivo, 6-10: Normal, >10: Vago).
*   **Beneficio:** Cumple con el principio de Abierto/Cerrado (SOLID), permitiendo agregar nuevos modos de desempeño sin alterar la lógica del responsable.

### 3. Template Method (Método Plantilla)
La clase base `Encargado` define el esqueleto del algoritmo de revisión en el método `revisarExcusa()`.
*   **Hooks:** Los métodos `puedeManejar()` y `procesar()` son implementados por cada subclase.
*   **Beneficio:** Evita la duplicación de código en la lógica de derivación y control de estados.

### 4. Builder (Constructor)
Se utiliza `CadenaEncargadosBuilder` para la configuración de la línea de mando.
*   **Beneficio:** Facilita la creación de cadenas complejas y garantiza que los objetos estén correctamente vinculados.

### 5. Observer (Observador)
Implementado para la gestión de **Prontuarios**. Cuando el CEO acepta una excusa inverosímil, el `AdministradorProntuarios` (Observable) notifica automáticamente al `EquipoDireccion` y al propio `CEO` (Observers).
*   **Beneficio:** Desacopla la lógica de persistencia de la lógica de notificación.

### 6. Singleton (Instancia Única)
El `AdministradorProntuarios` utiliza este patrón para centralizar la gestión de registros.
*   **Beneficio:** Evita inconsistencias en los datos al garantizar un único punto de acceso a la persistencia de prontuarios.

### 7. Polimorfismo en Excusas
Se eliminó el uso de atributos planos (como Strings para tipos de excusa) y se implementó una jerarquía (`ExcusaTrivial`, `ExcusaModerada`, etc.).
*   **Beneficio:** El comportamiento varía según el tipo de objeto, eliminando condicionales complejos (`if/else`) y delegando la responsabilidad al tipo de excusa.

## 🚀 Ejecución y Pruebas

### Requisitos
*   Java 17 o superior.
*   Maven 3.x.

### Ejecución del Demo
Para ver el sistema en funcionamiento con diferentes casos de prueba:
```bash
mvn compile exec:java -Dexec.mainClass="com.empresa.Demo"
```

### Ejecución de Pruebas (TDD)
El proyecto fue desarrollado siguiendo metodologías de TDD para garantizar la robustez de los cambios de estado y la integridad de la cadena.
Para correr los tests unitarios e integrales:
```bash
mvn test
```

## 📂 Estructura del Proyecto
*   `com.empresa.domain`: Clases de dominio (`Empleado`, `Excusa`, `Prontuario`).
*   `com.empresa.domain.chain`: Implementación de la Cadena de Responsabilidad.
*   `com.empresa.domain.state`: Implementación del patrón State.
*   `com.empresa.domain.observer`: Lógica de notificaciones y Singleton.
*   `com.empresa.controller`: Punto de entrada del sistema.
*   `com.empresa.builder`: Configuración dinámica de la cadena.

---
**Desarrollado para el TP de Patrones de Diseño.**
