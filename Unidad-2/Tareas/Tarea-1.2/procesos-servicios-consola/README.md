# Procesos y Servicios Consola

## Descripción General

Esta es una aplicación Spring Boot desarrollada en Java que permite la gestión y ejecución de comandos de sistema Linux a través de una interfaz de consola. La aplicación está diseñada para ejecutar comandos específicos relacionados con procesos y servicios del sistema operativo, como `lsof -i`, `top -bn 1` y `ps aux | head`. Los resultados de estos comandos se muestran en la consola y se guardan automáticamente en archivos de texto para su posterior consulta.

La aplicación sigue una arquitectura modular basada en Spring Framework, utilizando inyección de dependencias, servicios, repositorios y validaciones para asegurar la ejecución segura y controlada de los comandos.

## Arquitectura de la Aplicación

La aplicación está estructurada en capas siguiendo principios de diseño orientado a objetos y patrones de arquitectura limpia:

- **Capa de Presentación**: Controladores de línea de comandos (`CliControllers`) que manejan la interacción con el usuario.
- **Capa de Servicios**: Interfaces y implementaciones que encapsulan la lógica de negocio para cada comando.
- **Capa de Dominio**: Entidades y enumeraciones que representan los conceptos del dominio (tipos de trabajos).
- **Capa de Repositorios**: Manejo de la persistencia de datos (salidas de comandos en archivos).
- **Capa de Infraestructura**: Clases abstractas que proporcionan funcionalidad común para la ejecución de procesos.

## Componentes Principales

### 1. Clase Principal: `ProcesosServiciosApplication.java`

- **Ubicación**: `src/main/java/com/comandos/ProcesosServiciosApplication.java`
- **Función**: Punto de entrada de la aplicación Spring Boot.
- **Características**:
  - Anotada con `@SpringBootApplication` para habilitar la configuración automática de Spring.
  - Define un `CommandLineRunner` que ejecuta el menú de consola al iniciar la aplicación.
  - Inyecta el controlador `CliControllers` para manejar la lógica de la interfaz de usuario.

### 2. Controlador de Consola: `CliControllers.java`

- **Ubicación**: `src/main/java/com/comandos/controllers/CliControllers.java`
- **Función**: Maneja la interacción con el usuario a través de la consola.
- **Características**:
  - Anotado con `@Service` para ser gestionado por Spring.
  - Utiliza inyección de dependencias para acceder a los servicios de comandos.
  - Implementa un menú simple que lee la entrada del usuario y ejecuta el comando correspondiente.
  - Soporta tres comandos principales: LSOF, TOP y PS.

### 3. Servicios de Comandos

#### Interfaz Base: `CommandService.java`
- **Ubicación**: `src/main/java/com/comandos/services/interfaces/CommandService.java`
- **Función**: Define el contrato base para todos los servicios de comandos.
- **Método**: `void executeCommand(String[] args)` - Ejecuta un comando con los argumentos proporcionados.

#### Servicios Específicos:

##### `LsofService.java` e `LsofServiceImpl.java`
- **Función**: Maneja la ejecución del comando `lsof -i` (lista archivos abiertos de red).
- **Validación**: Requiere el parámetro `-i`.
- **Implementación**: Extiende `ComandoServiceAbstract` y valida que el comando comience con "LSOF".

##### `TopService.java` e `TopServiceImpl.java`
- **Función**: Maneja la ejecución del comando `top -bn 1` (muestra procesos del sistema en modo batch).
- **Validación**: Acepta parámetros como `-bn` seguido de un número opcional.
- **Implementación**: Extiende `ComandoServiceAbstract` y valida que el comando comience con "TOP".

##### `PsHeadService.java` e `PsHeadServiceImpl.java`
- **Función**: Maneja la ejecución del comando `ps aux | head` (muestra procesos del sistema con detalles).
- **Validación**: Acepta parámetros `aux` o `ef`.
- **Implementación**: Extiende `ComandoServiceAbstract` y valida que el comando comience con "PS".

### 4. Clase Abstracta: `ComandoServiceAbstract.java`

- **Ubicación**: `src/main/java/com/comandos/services/abstracts/ComandoServiceAbstract.java`
- **Función**: Proporciona funcionalidad común para la ejecución de comandos del sistema.
- **Características Principales**:
  - **Ejecución de Procesos**: Utiliza `ProcessBuilder` para ejecutar comandos en el shell.
  - **Manejo de Salidas**: Captura tanto la salida estándar como los errores, imprimiéndolos en consola con prefijos `[OUT]` y `[ERR]`.
  - **Persistencia**: Guarda la salida completa en archivos de texto a través del repositorio.
  - **Validación**: Verifica que el comando coincida con el tipo esperado y valida parámetros usando expresiones regulares.
  - **Multihilo**: Utiliza `ExecutorService` para manejar la lectura de streams de entrada y error concurrentemente.
  - **Timeout**: Espera hasta 5 segundos para la finalización del proceso.

### 5. Dominio: `Job.java`

- **Ubicación**: `src/main/java/com/comandos/domain/Job.java`
- **Función**: Enumeración que define los tipos de trabajos (comandos) soportados.
- **Valores**: `LSOF`, `TOP`, `PS`.

### 6. Repositorios

#### Interfaz: `IJobRepository.java`
- **Ubicación**: `src/main/java/com/comandos/repositories/interfaces/IJobRepository.java`
- **Función**: Define el contrato para la persistencia de salidas de comandos.
- **Métodos**:
  - `Path getpath()`: Obtiene la ruta del archivo (actualmente no implementado).
  - `boolean saveOutput(Job job, String output)`: Guarda la salida de un comando en un archivo.

#### Implementación: `FileJobRepository.java`
- **Ubicación**: `src/main/java/com/comandos/repositories/file/FileJobRepository.java`
- **Función**: Implementa la persistencia basada en archivos.
- **Características**:
  - Guarda las salidas en archivos con nombres como `lsof_output.txt`, `top_output.txt`, `ps_output.txt`.
  - Ubicación: `src/main/resources/`.
  - Utiliza `Files.write()` con opciones de creación y escritura.
  - Maneja errores de E/S con logging usando SLF4J.

## Flujo de Ejecución

1. **Inicio de la Aplicación**: Spring Boot carga el contexto y ejecuta el `CommandLineRunner`.
2. **Menú de Consola**: Se muestra un menú con los comandos disponibles.
3. **Entrada del Usuario**: El usuario ingresa un comando (ej: "lsof -i").
4. **Enrutamiento**: `CliControllers` identifica el tipo de comando y llama al servicio correspondiente.
5. **Validación**: El servicio valida el comando y parámetros.
6. **Ejecución**: `ComandoServiceAbstract` ejecuta el proceso usando `ProcessBuilder`.
7. **Captura de Salida**: Se capturan y muestran las salidas en tiempo real.
8. **Persistencia**: La salida completa se guarda en un archivo de texto.
9. **Repetición**: El proceso puede repetirse para otros comandos.

## Requisitos del Sistema

- **Java**: Versión 8 o superior.
- **Spring Boot**: Framework utilizado para la gestión de dependencias y configuración.
- **Sistema Operativo**: Linux (los comandos están diseñados para entornos Unix-like).
- **Permisos**: El usuario debe tener permisos para ejecutar los comandos del sistema (`lsof`, `top`, `ps`).

## Cómo Ejecutar la Aplicación

1. **Compilación**: Asegúrate de tener Maven instalado y ejecuta:
   ```
   mvn clean compile
   ```

2. **Ejecución**: Ejecuta la aplicación con Maven:
   ```
   mvn spring-boot:run
   ```
   O directamente con Java:
   ```
   java -jar target/procesos-servicios-consola-1.0.jar
   ```

3. **Interacción**: Una vez ejecutada, la aplicación mostrará un menú en la consola. Ingresa uno de los comandos soportados.

## Ejemplos de Uso

- **LSOF**: Ingresa `lsof -i` para listar conexiones de red abiertas.
- **TOP**: Ingresa `top -bn 1` para obtener una instantánea de los procesos del sistema.
- **PS**: Ingresa `ps aux` o `ps ef` para listar procesos con detalles.

Los resultados se mostrarán en la consola y se guardarán en archivos en `src/main/resources/`.

## Manejo de Errores

- **Comandos Inválidos**: Si se ingresa un comando no reconocido, se mostrará "Comando invalido".
- **Parámetros Incorrectos**: Los servicios validan los parámetros; comandos malformados serán rechazados.
- **Errores de Ejecución**: Excepciones durante la ejecución de procesos se imprimen en la consola.
- **Errores de Archivo**: Problemas al guardar salidas se registran en los logs.

## Pruebas

La aplicación incluye pruebas unitarias en `src/test/java/com/comandos/controllers/CliControllersTest.java`, que verifican la funcionalidad del controlador de consola.

## Extensibilidad

La arquitectura modular permite agregar fácilmente nuevos comandos:
1. Agregar un nuevo valor al enum `Job`.
2. Crear una interfaz y implementación de servicio que extienda `CommandService`.
3. Actualizar `CliControllers` para enrutar el nuevo comando.
4. Configurar validaciones en la implementación del servicio.

## Autor

- **Nombre**: Cesardrom
- **Versión**: 1.0
- **Fecha**: 1.0

## Licencia

Este proyecto es de uso educativo y no tiene una licencia específica asignada.
