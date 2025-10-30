# Lista de Pasos para Completar el Proyecto

Basado en el análisis del código existente, el proyecto ya tiene una estructura básica, pero hay deficiencias clave para cumplir con los requisitos de ejecutar procesos del sistema operativo (Linux), capturar stdout/stderr en tiempo real, y persistir el historial de ejecuciones en ficheros .txt, usando una arquitectura por interfaces con @Component, @Service y @Repository.

## Análisis del Código Existente

- **Arquitectura actual**: Ya sigue parcialmente la arquitectura por interfaces (@Component, @Service, @Repository), pero las interfaces de servicios están vacías y no definen contratos. El repositorio persiste en un archivo único (`mis_procesos.txt`), pero no maneja múltiples ejecuciones ni archivos separados por comando.
- **Ejecución de procesos**: El `ComandoServiceAbstract` usa `ProcessBuilder`, pero no captura stdout/stderr en tiempo real ni persiste correctamente. Solo ejecuta y espera, sin mostrar salida en vivo ni prefijos [OUT]/[ERR].
- **Persistencia**: `FileJobRepository` intenta guardar, pero `add()` siempre retorna `false` y no maneja archivos separados (.txt por ejecución o comando). `getpath()` no está implementado.
- **Comandos soportados**: `lsof -i`, `top`, `ps aux | head` están definidos, pero no ejecutan correctamente ni capturan salida.
- **Captura en tiempo real**: Falta lógica para leer stdout/stderr en hilos separados y mostrar con prefijos.
- **Historial**: No persiste el historial de ejecuciones en archivos .txt separados; solo intenta append a uno.

El proyecto no cumple actualmente con la captura en tiempo real ni la persistencia adecuada. Necesita modificaciones para usar hilos para captura, persistir en archivos separados (e.g., `lsof_output.txt`, `top_output.txt`), y definir interfaces completas.

## Pasos Secuenciales

1. **Definir interfaces de servicios completas**  
   - **Archivos a tocar**:  
     - `procesos-servicios-consola/src/main/java/com/comandos/services/interfaces/CommandService.java` (vacío actualmente).  
     - `procesos-servicios-consola/src/main/java/com/comandos/services/interfaces/LsofService.java` (vacío).  
     - `procesos-servicios-consola/src/main/java/com/comandos/services/interfaces/PsHeadService.java` (vacío).  
     - `procesos-servicios-consola/src/main/java/com/comandos/services/interfaces/TopService.java` (vacío).  
   - **Cambios**: Convertir estas clases vacías en interfaces con métodos como `executeCommand(String[] args)` que retornen void o un resultado. Ejemplo: `void executeCommand(String[] args);`. Esto define contratos para ejecutar comandos y manejar captura/persistencia.

2. **Actualizar ComandoServiceAbstract para captura en tiempo real y persistencia**  
   - **Archivos a tocar**:  
     - `procesos-servicios-consola/src/main/java/com/comandos/services/abstracts/ComandoServiceAbstract.java`.  
   - **Cambios**:  
     - Modificar `procesarLinea()` y `ejecutarProceso()` para usar hilos separados (e.g., con `Thread` o `ExecutorService`) para leer stdout/stderr en tiempo real desde `Process.getInputStream()` y `getErrorStream()`.  
     - Mostrar salida en consola con prefijos `[OUT]` para stdout y `[ERR]` para stderr.  
     - Integrar persistencia: Después de captura, llamar al repositorio para guardar la salida completa en un archivo .txt separado por comando (e.g., `lsof_output.txt`).  
     - Remover redirección a archivo en `ProcessBuilder` (actualmente `> mis_procesos.txt`); manejar manualmente.  
     - Agregar manejo de excepciones y cierre de streams.

3. **Implementar interfaces en las clases de servicio concretas**  
   - **Archivos a tocar**:  
     - `procesos-servicios-consola/src/main/java/com/comandos/services/impl/LsofServiceImpl.java`.  
     - `procesos-servicios-consola/src/main/java/com/comandos/services/impl/PsHeadServiceImpl.java`.  
     - `procesos-servicios-consola/src/main/java/com/comandos/services/impl/TopServiceImpl.java`.  
   - **Cambios**: Hacer que extiendan `ComandoServiceAbstract` e implementen las interfaces (e.g., `implements LsofService`). Sobrescribir métodos si es necesario para comandos específicos (e.g., `lsof -i` no necesita validación extra). Asegurar que llamen a `super.procesarLinea()` para reutilizar lógica.

4. **Mejorar el repositorio para persistencia en archivos .txt separados**  
   - **Archivos a tocar**:  
     - `procesos-servicios-consola/src/main/java/com/comandos/repositories/interfaces/IJobRepository.java`.  
     - `procesos-servicios-consola/src/main/java/com/comandos/repositories/file/FileJobRepository.java`.  
   - **Cambios**:  
     - En `IJobRepository`: Agregar métodos como `boolean saveOutput(Job job, String output)` para guardar salida por comando.  
     - En `FileJobRepository`: Implementar `getpath()` para retornar el path del archivo. Cambiar `add()` para crear archivos separados (e.g., `Paths.get("lsof_output.txt")`) y escribir la salida completa. Usar `Files.write()` con `CREATE`/`APPEND` si es necesario. Retornar `true` en éxito.  
     - Mover archivos a `src/main/resources/` o un directorio dedicado para historial.

5. **Actualizar el controlador CLI para inyección correcta**  
   - **Archivos a tocar**:  
     - `procesos-servicios-consola/src/main/java/com/comandos/controllers/CliControllers.java`.  
   - **Cambios**: Cambiar inyección directa de impls a interfaces (e.g., `@Autowired LsofService lsofService` en lugar de `LsofServiceImpl`). Asegurar que el menú llame a `executeCommand()` en lugar de `procesarLinea()`. Agregar manejo de bucles para múltiples comandos si es necesario.

6. **Ajustar la clase principal y configuración**  
   - **Archivos a tocar**:  
     - `procesos-servicios-consola/src/main/java/com/comandos/ProcesosServiciosApplication.java` (opcional, si necesita ajustes en CommandLineRunner).  
     - `procesos-servicios-consola/src/main/resources/application.properties` (agregar propiedades si es necesario, e.g., paths de archivos).  
   - **Cambios**: Asegurar que el CommandLineRunner inicie el menú correctamente. Agregar propiedades para configurar paths de salida.

7. **Eliminar archivos innecesarios y limpiar**  
   - **Archivos a tocar**:  
     - `procesos-servicios-consola/src/main/java/com/comandos/ProcCliApplication.java` (vacío, eliminar).  
   - **Cambios**: Remover clases no usadas para mantener limpieza.

8. **Probar y validar**  
   - Ejecutar `mvn clean compile` y `mvn spring-boot:run`.  
   - Probar comandos en el menú: Verificar captura en tiempo real con [OUT]/[ERR], y que se creen archivos .txt con historial.  
   - Verificar que stdout/stderr se muestren en consola y se persistan.

Esta lista asegura cumplimiento total. Los archivos principales a cambiar son las interfaces, el abstract, impls, repositorio y controlador. Si procedes, se pueden implementar estos cambios paso a paso.
