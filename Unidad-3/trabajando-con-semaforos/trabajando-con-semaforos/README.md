# Proyecto: Trabajando con Semáforos

Este proyecto es una colección de ejemplos en Java que ilustran el uso de semáforos para la sincronización de hilos (threads) en programación concurrente. Los semáforos son herramientas fundamentales en sistemas operativos y programación multihilo para controlar el acceso a recursos compartidos, evitando condiciones de carrera y garantizando la exclusión mutua.

El proyecto está estructurado como un proyecto Maven y contiene varias clases que demuestran diferentes escenarios de uso de semáforos. A continuación, se explica cada parte del código, cómo funciona en el ordenador y por qué se implementó de esa manera.

## Estructura del Proyecto

- `ColorSemaforo.java`: Simulación básica de un semáforo de tráfico sin sincronización avanzada.
- `ColorSemaforoMejorado.java`: Versión mejorada del semáforo usando semáforos para sincronización.
- `Estudiante.java`: Simulación de estudiantes accediendo a equipos compartidos con control de concurrencia.
- `EstudianteMejorado.java`: Versión mejorada que identifica el equipo específico en uso.
- `SaiyanRaceSemaphoreOne.java`: Simulación de una carrera entre personajes (Goku y Vegeta) con turnos alternos usando semáforos.

## Requisitos

- Java 8 o superior.
- Maven para compilar y ejecutar el proyecto.

## Compilación y Ejecución

Para compilar el proyecto:

```bash
mvn clean compile
```

Para ejecutar una clase específica (reemplaza `NombreClase` con la clase deseada):

```bash
mvn exec:java -Dexec.mainClass="com.docencia.semaforo.NombreClase"
```

O directamente con Java:

```bash
java -cp target/classes com.docencia.semaforo.NombreClase
```

## Explicación Detallada de Cada Clase

### 1. ColorSemaforo.java

**¿Qué hace el código?**
Esta clase simula el funcionamiento de un semáforo de tráfico básico. Imprime los colores "ROJO", "VERDE" y "ÁMBAR" en un ciclo infinito, con tiempos de espera específicos: 3 segundos para ROJO y VERDE, y 1 segundo para ÁMBAR.

**Fragmentos de Código Relevantes:**

```java
@Override
public void run() {
    String[] colors = { "ROJO", "VERDE", "ÁMBAR" };
    int[] times = { 3000, 3000, 1000 };
    int index = 0;
    while (running) {
        System.out.println(colors[index]);
        try {
            Thread.sleep(times[index]);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            break;
        }
        index = (index + 1) % 3;
    }
}
```

```java
public static void main(String[] args) throws InterruptedException {
    ColorSemaforo semaforo = new ColorSemaforo();
    Thread thread = new Thread(semaforo);
    thread.start();
    Thread.sleep(20000);
    semaforo.stop();
    thread.interrupt();
    thread.join();
}
```

**Cómo funciona en el ordenador:**

- Se crea un hilo (thread) que ejecuta el método `run()`.
- El hilo imprime el color actual y duerme durante el tiempo correspondiente usando `Thread.sleep()`.
- El ciclo continúa hasta que se llama al método `stop()`, que establece `running = false` y interrumpe el hilo.
- En el método `main()`, se inicia el hilo, se espera 20 segundos y luego se detiene.

**Por qué se implementó de esa forma:**

- Es una implementación simple sin sincronización, usando solo variables volátiles para control básico.
- No utiliza semáforos porque no hay recursos compartidos entre múltiples hilos; es un solo hilo manejando el ciclo.
- Sirve como base para comparar con versiones más avanzadas que requieren sincronización.

### 2. ColorSemaforoMejorado.java

**¿Qué hace el código?**
Similar a `ColorSemaforo.java`, pero incorpora un semáforo para controlar el acceso al recurso compartido (la impresión de colores). El semáforo permite un solo hilo a la vez, aunque en este caso solo hay un hilo.

**Fragmentos de Código Relevantes:**

```java
private volatile boolean running = true;
private Semaphore semaforo = new Semaphore(1);

@Override
public void run() {
    String[] colors = { "ROJO", "VERDE", "ÁMBAR" };
    int[] times = { 3000, 3000, 1000 };
    int index = 0;
    while (running) {
        try {
            semaforo.acquire();
            System.out.println(colors[index]);
            semaforo.release();
            try {
                Thread.sleep(times[index]);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            index = (index + 1) % 3;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            break;
        }
    }
}
```

**Cómo funciona en el ordenador:**

- Se inicializa un semáforo con 1 permiso (`new Semaphore(1)`).
- En el método `run()`, el hilo adquiere el semáforo antes de imprimir y liberar después.
- El resto del comportamiento es idéntico al anterior: ciclo de colores con tiempos de espera.
- El semáforo asegura que, si hubiera múltiples hilos, solo uno podría imprimir a la vez.

**Por qué se implementó de esa forma:**

- Introduce el concepto de semáforos en un escenario simple.
- Prepara el terreno para escenarios más complejos donde múltiples hilos compiten por recursos.
- Aunque innecesario aquí (solo un hilo), demuestra el patrón de adquirir y liberar semáforos, que es esencial para evitar race conditions en entornos concurrentes.

### 3. Estudiante.java

**¿Qué hace el código?**
Simula 6 estudiantes intentando usar 4 equipos compartidos. Cada estudiante es un hilo que intenta adquirir un permiso del semáforo para "usar" un equipo, duerme un tiempo aleatorio (3-5 segundos) simulando el uso, y luego libera el permiso.

**Fragmentos de Código Relevantes:**

```java
@Override
public void run() {
    try {
        semaforo.acquire();
        System.out.println("El estudiante " + nombre + " ha comenzado a utilizar el equipo");
        Thread.sleep(ThreadLocalRandom.current().nextInt(3000, 5001));
        System.out.println("El estudiante " + nombre + " ha finalizado con el equipo");
        semaforo.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}
```

```java
public static void main(String[] args) throws InterruptedException {
    Semaphore semaforo = new Semaphore(4);
    Estudiante[] estudiantes = new Estudiante[6];
    for (int i = 0; i < 6; i++) {
        estudiantes[i] = new Estudiante(String.valueOf(i + 1), semaforo);
        estudiantes[i].start();
    }
    for (Estudiante estudiante : estudiantes) {
        estudiante.join();
    }
}
```

**Cómo funciona en el ordenador:**

- Se crea un semáforo con 4 permisos (`new Semaphore(4)`), representando los 4 equipos disponibles.
- Cada estudiante (hilo) adquiere un permiso, imprime un mensaje de inicio, duerme, imprime fin y libera el permiso.
- Los hilos se ejecutan concurrentemente, pero solo 4 pueden acceder simultáneamente; los demás esperan en cola.

**Por qué se implementó de esa forma:**

- Demuestra el uso de semáforos para controlar el acceso limitado a recursos (equipos).
- Evita que más de 4 estudiantes usen equipos al mismo tiempo, simulando un escenario real de concurrencia.
- Usa `ThreadLocalRandom` para tiempos aleatorios, haciendo la simulación más realista y probando la robustez del semáforo.

### 4. EstudianteMejorado.java

**¿Qué hace el código?**
Versión mejorada de `Estudiante.java`. Además de controlar el acceso, identifica cuál equipo específico está usando cada estudiante (basado en los permisos disponibles).

**Fragmentos de Código Relevantes:**

```java
@Override
public void run() {
    try {
        semaforo.acquire();
        int equipo = semaforo.availablePermits() + 1;
        System.out.println("El estudiante " + nombre + " ha comenzado a utilizar el equipo " + equipo);
        Thread.sleep(ThreadLocalRandom.current().nextInt(3000, 5001));
        System.out.println("El estudiante " + nombre + " ha finalizado con el equipo " + equipo);
        semaforo.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}
```

**Cómo funciona en el ordenador:**

- Similar a la anterior, pero al adquirir el permiso, calcula el número de equipo como `semaforo.availablePermits() + 1`.
- Imprime mensajes indicando el equipo en uso.
- El resto es idéntico: adquirir, usar, liberar.

**Por qué se implementó de esa forma:**

- Mejora la trazabilidad al mostrar qué recurso específico se está usando.
- Ilustra cómo usar métodos del semáforo (como `availablePermits()`) para obtener información adicional.
- Refuerza el concepto de semáforos como contadores de permisos, no solo como bloqueos binarios.

### 5. SaiyanRaceSemaphoreOne.java

**¿Qué hace el código?**
Simula una carrera entre Goku y Vegeta, donde avanzan alternadamente. Cada uno da pasos aleatorios (1-10 metros) hasta llegar a 100 metros. El primero en llegar gana.

**Fragmentos de Código Relevantes:**

```java
private static final Semaphore turnGoku = new Semaphore(1);
private static final Semaphore turnVegeta = new Semaphore(0);
private static final AtomicBoolean winnerDeclared = new AtomicBoolean(false);

@Override
public void run() {
    final boolean isGoku = "Goku".equals(name);
    final Semaphore myTurn = isGoku ? turnGoku : turnVegeta;
    final Semaphore otherTurn = isGoku ? turnVegeta : turnGoku;

    try {
        while (!winnerDeclared.get() && distance < GOAL) {
            myTurn.acquire();
            if (winnerDeclared.get()) {
                otherTurn.release();
                break;
            }
            int step = ThreadLocalRandom.current().nextInt(1, 11);
            distance += step;
            System.out.println(name + " avanzó " + step + " metros. Distancia total: " + distance + " metros.");

            if (distance >= GOAL) {
                if (winnerDeclared.compareAndSet(false, true)) {
                    System.out.println(name + " ha ganado la carrera!");
                }
            }
            otherTurn.release();

            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(200, 401));
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                otherTurn.release();
                break;
            }
        }
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        (isGoku ? turnVegeta : turnGoku).release();
    }
}
```

**Cómo funciona en el ordenador:**

- Usa dos semáforos: uno para el turno de Goku (inicialmente disponible) y otro para Vegeta (inicialmente bloqueado).
- Cada hilo (Goku o Vegeta) adquiere su semáforo de turno, avanza, verifica si ganó, y libera el turno del otro.
- Si uno gana, se declara el ganador usando `AtomicBoolean` para evitar múltiples declaraciones.
- Incluye manejo de interrupciones para liberar semáforos si es necesario.

**Por qué se implementó de esa forma:**

- Demuestra semáforos para sincronización de turnos en un juego o simulación secuencial.
- Evita que ambos avancen simultáneamente, forzando alternancia.
- Usa `AtomicBoolean` para una bandera compartida segura en entornos multihilo.
- Maneja interrupciones correctamente para liberar recursos, una buena práctica en concurrencia.

## Conceptos Clave: Semáforos en Java

Los semáforos (`java.util.concurrent.Semaphore`) permiten controlar el acceso a recursos compartidos:

- `acquire()`: Adquiere un permiso; bloquea si no hay disponibles.
- `release()`: Libera un permiso.
- Se inicializan con un número de permisos (e.g., 1 para exclusión mutua, >1 para acceso limitado).

En el ordenador, los semáforos operan a nivel de JVM, usando primitivas del SO para sincronización eficiente. Evitan race conditions y deadlocks cuando se usan correctamente.

## Conclusión

Este proyecto ilustra progresivamente el uso de semáforos desde escenarios simples hasta complejos, enfatizando su rol en la programación concurrente. Cada clase construye sobre la anterior, mostrando por qué la sincronización es crucial en sistemas multihilo para prevenir errores y asegurar comportamiento predecible.
