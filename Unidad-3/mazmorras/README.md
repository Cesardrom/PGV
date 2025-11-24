# Resumen y Mejoras: Servidor de Mazmorras, Calculadora de Daño Crítico y Mundo Abierto con Spawns Programados

---

## Servidor de Mazmorras

### Descripción del Código

El servidor simula la entrada de jugadores a distintas mazmorras. Para procesar estas solicitudes, utiliza un pool fijo de 3 hilos, denominados "GM bots". Cada solicitud de entrada es representada por una tarea `Runnable` llamada `PeticionMazmorra` que:
- Valida al jugador.
- Prepara la instancia de la mazmorra.
- Carga enemigos, loot, etc.

El pool limita la cantidad de hilos que se ejecutan simultáneamente a 3, mientras que las solicitudes restantes esperan en cola.

### Preguntas Resueltas

- ¿Solo se usan 3 hilos para atender a todos los jugadores?
  Sí, se usa un pool fijo de 3 hilos para procesar concurrentemente las solicitudes, limitando la cantidad de hilos activos y mejorando la eficiencia con la reutilización.

- ¿Los mismos hilos procesan varias peticiones?
  Sí, los hilos se reutilizan para múltiples tareas evitando crear y destruir hilos repetidamente, lo que reduce la sobrecarga del sistema.

- ¿Qué pasa si cambias el tamaño del pool a 1 o a 10?
  Con 1 hilo, las tareas se procesan serialmente, aumentando el tiempo total. Con 10 hilos, se incrementa concurrencia, pero también el consumo de recursos y posible sobrecarga administrativa.

---

## Calculadora de Daño Crítico

### Descripción del Código

Simula una raid donde múltiples jugadores lanzan ataques simultáneamente. Cada ataque tiene:
- Nombre del atacante.
- Daño base.
- Probabilidad de crítico.
- Multiplicador crítico.

Cada cálculo de daño es una tarea `Callable<Integer>` que devuelve el daño total, considerando si el ataque fue crítico o no. Se usa un pool fijo de 4 hilos para ejecutar estos cálculos en paralelo.

### Preguntas Resueltas

- ¿Diferencia entre `execute(Runnable)` y `submit(Callable<V>)`?
  `execute(Runnable)` ejecuta sin devolver resultado. `submit(Callable<V>)` devuelve un `Future` de donde se puede obtener el resultado.

- ¿Cómo lanzar muchos cálculos de daño en paralelo y recogerlos?
  Usando un pool de hilos fijo y enviando tareas `Callable` con `submit`, luego recogiendo resultados con `Future.get()`.

- ¿Cómo afecta cambiar la probabilidad de crítico al daño total?
  Al aumentar la probabilidad, incrementa la cantidad de ataques críticos y el daño total esperado de la raid.

---

## Mundo Abierto con Spawns Programados

### Descripción del Código

Simula un mundo abierto donde enemigos aparecen periódicamente en zonas aleatorias usando `ScheduledExecutorService` con un pool de 2 hilos.

### Preguntas Resueltas

- ¿Qué significa `schedule(...)` y `scheduleAtFixedRate(...)`?
  `schedule(...)` ejecuta la tarea una sola vez después del retraso programado.  
  `scheduleAtFixedRate(...)` ejecuta la tarea repetidamente a intervalos fijos, intentando mantener la frecuencia aunque la tarea tome más tiempo.

- ¿Cómo se comporta el sistema si la tarea dura más que el período?
  El scheduler espera a que la tarea actual termine antes de empezar la siguiente, evitando solapamientos pero retrasando ejecuciones.

- ¿Qué pasa si se cambia el período o la duración del sleep del main?
  Cambiar el período varía la frecuencia de spawns y puede afectar el rendimiento. Modificar la duración del sleep afecta cuánto tiempo corre el scheduler antes de apagarse.

---

## Conclusión General

Estos tres módulos demuestran el uso de diferentes tipos de ejecutores para optimizar el procesamiento concurrente en un sistema de juego:

- FixedThreadPool para limitar y reutilizar hilos eficientemente.
- Callable y Future para ejecutar y recoger resultados de tareas que retornan valores.
- ScheduledExecutorService para programar tareas repetitivas en intervalos regulares.

La configuración adecuada de tamaños de pool y períodos de ejecución impacta directamente en el rendimiento y eficiencia del sistema. Las pruebas y ajustes deben considerar la carga esperada y recursos disponibles.
