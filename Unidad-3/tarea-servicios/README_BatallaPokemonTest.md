# BatallaPokemonTest - Pruebas Unitarias para BatallaPokemon

## Introducción

La clase `BatallaPokemonTest` es una suite de pruebas unitarias escrita con JUnit 5 para verificar el comportamiento correcto de la clase `BatallaPokemon`. Esta prueba se enfoca en asegurar que la simulación de batalla concluya con un ganador, demostrando el uso de pruebas en aplicaciones concurrentes. Las pruebas unitarias son esenciales para validar que el código funcione como esperado, especialmente en escenarios multihilo donde los resultados pueden variar debido a la aleatoriedad y la concurrencia.

Esta explicación detalla el código de la prueba, explica por qué se estructura así, y describe cómo funciona en el ordenador, incluyendo aspectos de ejecución de pruebas en Java.

## Descripción General del Código

### Estructura de la Clase

La clase `BatallaPokemonTest` extiende implícitamente `Object` y utiliza anotaciones de JUnit 5 para definir el ciclo de vida de las pruebas.

- **Variables de instancia**:
  - `originalOut`: Almacena el `PrintStream` original de `System.out` para restaurarlo después de cada prueba.
  - `outputStream`: Un `ByteArrayOutputStream` que captura la salida impresa durante la prueba.

- **Método `setUp`** (anotado con `@BeforeEach`):
  - Se ejecuta antes de cada prueba.
  - Redirige `System.out` a `outputStream` para capturar la salida de consola generada por `BatallaPokemon`.

- **Método `restoreSystemOut`** (anotado con `@AfterEach`):
  - Se ejecuta después de cada prueba.
  - Restaura `System.out` al flujo original para evitar interferencias entre pruebas.

- **Método `testBatallaPokemon_debeHaberGanador`** (anotado con `@Test`):
  - **Arrange**: Crea una instancia de `BatallaPokemon`.
  - **Act**: Llama a `iniciar()` para ejecutar la batalla.
  - **Assert**: Verifica que la salida capturada contenga la frase "ha ganado la batalla!", asegurando que la batalla termine con un ganador.

### Por Qué se Hace Así

#### Uso de JUnit 5
- **Razones**: JUnit es el framework estándar para pruebas unitarias en Java. Permite estructurar pruebas con anotaciones claras (@Test, @BeforeEach, @AfterEach), facilitando la ejecución automática y la detección de fallos. Es ideal para probar lógica concurrente como en `BatallaPokemon`.
- **Por qué JUnit 5**: Ofrece mejoras sobre versiones anteriores, como mejor soporte para lambdas y extensiones. Aquí se usa para simplicidad y compatibilidad.

#### Captura de Salida de Consola
- **Razones**: `BatallaPokemon` imprime resultados a `System.out`, pero las pruebas unitarias no deben depender de la consola. Redirigir la salida permite verificar el comportamiento sin efectos secundarios visibles.
- **Alternativas**: Podría modificarse `BatallaPokemon` para retornar un resultado en lugar de imprimir, pero eso alteraría el código original. La captura es no invasiva.
- **Por qué `ByteArrayOutputStream`**: Es un flujo en memoria que permite leer la salida como string fácilmente.

#### Patrón Arrange-Act-Assert (AAA)
- **Razones**: Estructura estándar en pruebas unitarias para claridad: preparar datos, ejecutar acción, verificar resultados. Hace las pruebas legibles y mantenibles.
- **Por qué no más aserciones**: Esta prueba se enfoca en el resultado principal (haber un ganador). Pruebas adicionales podrían verificar detalles como HP finales, pero aquí es suficiente para validar la concurrencia básica.

#### @BeforeEach y @AfterEach
- **Razones**: Aseguran que cada prueba sea independiente. `setUp` prepara el entorno (captura salida), `restoreSystemOut` lo limpia. Evita que fallos en una prueba afecten otras.
- **Por qué no @BeforeAll/@AfterAll**: La salida es por prueba, no global. Usar @BeforeEach permite aislamiento.

#### Nombre del Método de Prueba
- **Razones**: `testBatallaPokemon_debeHaberGanador` sigue la convención de nombres descriptivos en español/inglés, indicando qué se prueba y el resultado esperado. Mejora la legibilidad en reportes de pruebas.

### Cómo Funciona en el Ordenador

Cuando ejecutas las pruebas con Maven (`mvn test`) o un IDE:

1. **Carga de JUnit**: La JVM carga JUnit 5 y la clase `BatallaPokemonTest`.

2. **Ejecución del Ciclo de Vida**:
   - Para cada método @Test:
     - Se ejecuta `setUp()`: `System.out` se redirige a `outputStream`.
     - Se ejecuta el método de prueba: Crea `BatallaPokemon`, llama `iniciar()`, que inicia hilos concurrentes.
     - Los hilos de Pikachu y Charmander ejecutan en paralelo, imprimiendo a `System.out` (ahora capturado en `outputStream`).
     - La batalla termina cuando un HP llega a 0, imprimiendo "ha ganado la batalla!".
     - Se ejecuta `restoreSystemOut()`: Restaura la consola original.

3. **Aserción**: `assertTrue(salida.contains("ha ganado la batalla!"))` verifica que la cadena capturada incluya el mensaje de victoria. Si falla, la prueba falla.

4. **Aspectos Concurrentes**:
   - Los hilos se ejecutan en el scheduler del SO, similar a la ejecución normal.
   - La prueba espera a que `iniciar()` termine (join implícito), asegurando que la batalla complete antes de asertar.

5. **Ejecución en el SO**:
   - Maven ejecuta las pruebas en un proceso separado.
   - La salida se captura en memoria, no se imprime a consola durante la prueba.
   - Si la prueba pasa, confirma que la lógica concurrente funciona correctamente.

### Ejemplo de Ejecución
```
[Test worker] INFO - Running servicios.BatallaPokemonTest
Pikachu ataca con 12 de daño. HP rival: 88
Charmander ataca con 8 de daño. HP rival: 92
...
Pikachu ha ganado la batalla!
[Test worker] INFO - Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
```

### Consideraciones Finales
- **Ventajas**: Verifica que la concurrencia no cause deadlocks o resultados inválidos.
- **Limitaciones**: No prueba escenarios específicos (e.g., empate, que no ocurre aquí). Para pruebas más robustas, podría usarse `ThreadLocalRandom` mockeado.
- **Mejoras Posibles**: Agregar timeouts para evitar pruebas infinitas, o probar con HP iniciales fijos para resultados determinísticos.

Este README explica la prueba paso a paso, justificando el diseño y describiendo la ejecución. Para ejecutar, usa `mvn test` en el directorio del proyecto.
