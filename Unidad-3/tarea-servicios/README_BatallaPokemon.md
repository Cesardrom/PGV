# BatallaPokemon - Simulación de Batalla de Pokémon con Hilos en Java

## Introducción

La clase `BatallaPokemon` es una simulación de una batalla entre dos Pokémon, Pikachu y Charmander, utilizando programación concurrente en Java. Esta implementación demuestra el uso de hilos (threads) para representar acciones simultáneas, sincronización con locks y condiciones para gestionar turnos alternos, y variables atómicas para manejar estados compartidos de manera segura. El objetivo es simular una batalla por turnos donde cada Pokémon ataca al otro hasta que uno de ellos pierda toda su vida (HP).

Esta clase forma parte de un conjunto de ejemplos de concurrencia en Java, inspirados en temas populares como Pokémon, para ilustrar conceptos avanzados de programación multihilo.

## Descripción General del Código

### Estructura de la Clase

La clase `BatallaPokemon` contiene:

- **Variables de instancia**:
  - `juegoTerminado`: Un `AtomicBoolean` que indica si la batalla ha terminado. Se usa `AtomicBoolean` porque es thread-safe y evita problemas de concurrencia al verificar y modificar el estado desde múltiples hilos.
  - `hpPikachu` y `hpCharmander`: Enteros que representan los puntos de vida (HP) de cada Pokémon. Inicialmente 100.
  - `turno`: Un `String` que indica de quién es el turno actual ("Pikachu" o "Charmander").
  - `m`: Un `ReentrantLock` para sincronizar el acceso a secciones críticas del código.
  - `turnoCambio`: Una `Condition` asociada al lock, utilizada para hacer que los hilos esperen su turno.

- **Método `atacar`**:
  - Parámetros: `atacante` (String), `esPikachu` (boolean).
  - Genera un daño aleatorio entre 5 y 20.
  - Reduce el HP del rival.
  - Imprime el daño y el HP restante.
  - Verifica si el HP del rival llega a 0 o menos; si es así, marca `juegoTerminado` como true y declara al atacante como ganador.
  - Incluye un sleep aleatorio (200-600 ms) para simular el tiempo de ataque.

- **Clases Internas**:
  - `HiloPikachu` y `HiloCharmander`: Implementan `Runnable`. Cada una representa el hilo de un Pokémon.
    - En `run()`: Mientras el juego no haya terminado, adquiere el lock, espera su turno usando `turnoCambio.await()`, ataca si es su turno, cambia el turno y notifica al otro hilo con `turnoCambio.signal()`.
    - Maneja interrupciones con `Thread.currentThread().interrupt()`.

- **Método `iniciar`**:
  - Crea instancias de `HiloPikachu` y `HiloCharmander`.
  - Crea y inicia los hilos correspondientes.
  - Espera a que ambos hilos terminen con `join()`.

- **Método `main`**:
  - Instancia `BatallaPokemon` y llama a `iniciar()`.

### Por Qué se Hace Así

#### Uso de Hilos (Threads)
- **Razones**: En una batalla real, las acciones ocurren de manera secuencial por turnos, pero en programación, podemos simular concurrencia para representar entidades independientes (Pokémon) que "actúan" al mismo tiempo. Los hilos permiten que Pikachu y Charmander ejecuten su lógica en paralelo, aunque sincronizados para turnos.
- **Alternativas**: Sin hilos, sería un bucle secuencial, pero esto no demostraría concurrencia. Usar `ExecutorService` podría ser más moderno, pero aquí se usa `Thread` directamente para simplicidad y enseñanza.

#### Variables Atómicas (AtomicBoolean)
- **Razones**: `AtomicBoolean` asegura que las operaciones de lectura/escritura sean atómicas, evitando race conditions donde un hilo lee un valor obsoleto mientras otro lo modifica. Es más eficiente que sincronizar todo con locks.
- **Por qué no `boolean` normal**: Un `boolean` compartido entre hilos puede causar inconsistencias debido a la caché de CPU y la falta de atomicidad.

#### Locks y Condiciones (ReentrantLock y Condition)
- **Razones**: El `ReentrantLock` protege secciones críticas (como verificar y cambiar el turno) para evitar que ambos hilos accedan simultáneamente. La `Condition` permite que un hilo espere hasta que sea su turno, liberando el lock mientras espera, lo que es más eficiente que busy-waiting.
- **Por qué no `synchronized`**: `synchronized` es más simple, pero `ReentrantLock` ofrece más control (e.g., `Condition` para waits específicos). Aquí se usa para demostrar patrones avanzados de sincronización.
- **Turnos alternos**: Sin sincronización, los hilos podrían atacar fuera de turno o simultáneamente, rompiendo la lógica de batalla.

#### Sleeps Aleatorios
- **Razones**: Simulan variabilidad en el tiempo de ataque, haciendo la simulación más realista. Los sleeps en `atacar` y en los hilos previenen que el programa termine instantáneamente y permiten observar la concurrencia.
- **Por qué aleatorios**: Representan la imprevisibilidad de una batalla real.

#### Manejo de Interrupciones
- **Razones**: Los hilos pueden ser interrumpidos (e.g., por el sistema). Llamar a `interrupt()` en el hilo actual permite manejar esto correctamente, evitando bucles infinitos.

### Cómo Funciona en el Ordenador

Cuando ejecutas `java BatallaPokemon`:

1. **Inicio**: Se crea una instancia de `BatallaPokemon`. Las variables se inicializan: `juegoTerminado = false`, `hpPikachu = 100`, `hpCharmander = 100`, `turno = "Pikachu"`.

2. **Creación de Hilos**: En `iniciar()`, se crean dos objetos `Thread`: uno para `HiloPikachu` y otro para `HiloCharmander`. Cada hilo ejecuta su método `run()` en paralelo.

3. **Ejecución Concurrente**:
   - Ambos hilos entran en un bucle `while (!juegoTerminado.get())`.
   - El hilo de Pikachu (inicia primero) adquiere el lock `m`.
   - Como `turno == "Pikachu"`, no espera y llama a `atacar("Pikachu", true)`.
   - Dentro de `atacar`: Genera daño aleatorio, reduce `hpCharmander`, imprime, duerme 200-600 ms.
   - Cambia `turno` a "Charmander" y llama a `turnoCambio.signal()` para despertar al otro hilo.
   - Libera el lock.
   - Ahora, el hilo de Charmander despierta, adquiere el lock, verifica que es su turno, ataca, etc.
   - Esto continúa alternadamente hasta que un HP llegue a 0.

4. **Sincronización**:
   - El lock asegura que solo un hilo modifique variables compartidas a la vez.
   - La condition hace que el hilo no activo espere eficientemente, sin consumir CPU.

5. **Fin**: Cuando un Pokémon gana, `juegoTerminado` se setea a true. Ambos hilos salen del bucle. `join()` espera a que terminen, y el programa imprime el resultado final.

6. **Aspectos del Sistema Operativo**:
   - Los hilos son manejados por el scheduler del SO (e.g., Linux con kernel threads). El `sleep` cede el control al SO, permitiendo que otros procesos/hilos ejecuten.
   - La JVM traduce los hilos Java a threads nativos del SO.
   - En un procesador multi-core, los hilos pueden ejecutarse en núcleos diferentes, pero la sincronización asegura orden.

### Ejemplo de Salida
```
Pikachu ataca con 12 de daño. HP rival: 88
Charmander ataca con 8 de daño. HP rival: 92
Pikachu ataca con 15 de daño. HP rival: 77
...
Pikachu ataca con 20 de daño. HP rival: 0
Pikachu ha ganado la batalla!
```

### Consideraciones Finales
- **Ventajas**: Demuestra concurrencia controlada, útil para juegos o simulaciones.
- **Limitaciones**: En producción, usar frameworks como Akka para actores sería mejor. Aquí es educativo.
- **Mejoras Posibles**: Agregar más Pokémon, interfaz gráfica, o usar `CompletableFuture` para asincronía.

Este README explica el código paso a paso, justificando las decisiones de diseño y describiendo la ejecución en el ordenador. Para ejecutar, compila con `javac BatallaPokemon.java` y ejecuta con `java BatallaPokemon`.
