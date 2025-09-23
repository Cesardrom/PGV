## Code, Learn & Practice(Procesos y Servicios (modo alumno, sin root) — Trabajo en $HOME/dam con user units de systemd)

#### Comprobar versión de systemd y que el *user manager* está activo:

```bash
systemctl --user --version | head -n1
systemctl --user status --no-pager | head -n5
```
**Pega salida aquí:**

```text
systemd 255 (255.4-1ubuntu8.6)

a108pc13
    State: running
    Units: 262 loaded (incl. loaded aliases)
     Jobs: 0 queued
   Failed: 0 units

```

**Reflexiona la salida:**

```text
La version del systemmd que tengo en mi pc es la 255.4-1 de ubuntu.

Y mi pc tiene de nombre a108pc13 y ahora mismo no tiene ni un trabajo en cola y ninguno a fallado.
```

---

## Bloque 1 — Conceptos (breve + fuentes)

1) ¿Qué es **systemd** y en qué se diferencia de SysV init?  

**Respuesta:**  Systemmd es un sistema de gestion de servicios que esta presente en las distribuciones de Linux y se encarga de arrancar lo que esta debajo del kernel, otra de las funciones es implementar un sistema de control de dependencias. 
Systemd es un sistema moderno para gestionar la inicialización del sistema y los servicios en Linux, diseñado para superar las limitaciones de SysV init mediante un enfoque paralelo, modular y una gestión centralizada de la configuración y el registro 

_Fuentes:_
https://keepcoding.io/blog/que-es-systemd/
https://www.tumblr.com/sololinuxes/611046081453785088/diferencias-entre-systemd-y-sysvinit-sysv

2) **Servicio** vs **proceso** (ejemplos).  

**Respuesta:**  Un servicio es un programa que se ejecuta al inicio del sistema operativo como el antivirus. Cuando ejecutamos una aplicación, se ejecuta uno o varios procesos para que funcione correctamente, éstos son los que consume recursos de nuestra máquina, como puede ser el uso de la CPU, la memoria, la memoria virtual (swap)…etc como xeyes.

_Fuentes:_
https://medium.com/enredando-con-linux/linux-procesos-y-administraci%C3%B3n-de-servicios-9147c27f7f9d

3) ¿Qué son los **cgroups** y para qué sirven?  

**Respuesta:**  Los cgroups (control groups) son una funcionalidad del kernel Linux que permite agrupar procesos y controlar de manera granular los recursos del sistema que consumen, como CPU, memoria, I/O, y otros.

_Fuentes:_
https://sergiobelkin.com/posts/que-son-los-cgroups-y-para-que-sirven/

4) ¿Qué es un **unit file** y tipos (`service`, `timer`, `socket`, `target`)?  

**Respuesta:** es un archivo de configuración utilizado por el sistema y el gestor de servicios de sistema en Linux. Define cómo debe iniciarse, detenerse y gestionarse un servicio. Este contiene varias directivas que especifican el comportamiento, dependencias y límites de recursos del servicio.

_Fuentes:_
https://labex.io/questions/what-is-a-service-unit-file-995886

5) ¿Qué hace `journalctl` y cómo ver logs **de usuario**?  

**Respuesta:**  journalctl es una eficiente herramienta para consultar y mostrar registros de eventos o ficheros log en Linux. journalctl se constituye como un componente clave de systemd, administrador de sistemas y servicios. Para ver los logs de un usuario se debe acceder a /var/logs.

_Fuentes:_
https://www.ionos.es/digitalguide/servidores/herramientas/que-es-journalctl/

---

## Bloque 2 — Práctica guiada (todo en tu `$DAM`)

> Si un comando pide permisos que no tienes, usa la **versión `--user`** o consulta el **ayuda** con `--help` para alternativas.

### 2.1 — PIDs básicos

**11.** PID de tu shell y su PPID.

```bash
echo "PID=$$  PPID=$PPID"
```
**Salida:**

```text
PID=68802  PPID=68794
```

**Pregunta:** ¿Qué proceso es el padre (PPID) de tu shell ahora?  

**Respuesta:** /usr/libexec/gnome-terminal-server

---

**12.** PID del `systemd --user` (manager de usuario) y explicación.

```bash
pidof systemd --user || pgrep -u "$USER" -x systemd
```

**Salida:**

```text
3323
```
**Pregunta:** ¿Qué hace el *user manager* de systemd para tu sesión?  

**Respuesta:** El user manager de systemd (systemd --user) gestiona servicios, sesiones y recursos específicos del usuario en la sesión actual.

---

### 2.2 — Servicios **de usuario** con systemd

Vamos a crear un servicio sencillo y un timer **en tu carpeta** `~/.config/systemd/user` o en `$DAM/units` (usaremos la primera para que `systemctl --user` lo encuentre).

**13.** Prepara directorios y script de práctica.

```bash
mkdir -p ~/.config/systemd/user "$DAM"/{bin,logs}
cat << 'EOF' > "$DAM/bin/fecha_log.sh"
#!/usr/bin/env bash
mkdir -p "$HOME/dam/logs"
echo "$(date --iso-8601=seconds) :: hello from user timer" >> "$HOME/dam/logs/fecha.log"
EOF
chmod +x "$DAM/bin/fecha_log.sh"
```

**14.** Crea el servicio **de usuario** `fecha-log.service` (**Type=simple**, ejecuta tu script).

```bash
cat << 'EOF' > ~/.config/systemd/user/fecha-log.service
[Unit]
Description=Escribe fecha en $HOME/dam/logs/fecha.log

[Service]
Type=simple
ExecStart=%h/dam/bin/fecha_log.sh
EOF

systemctl --user daemon-reload
systemctl --user start fecha-log.service
systemctl --user status fecha-log.service --no-pager -l | sed -n '1,10p'
```
**Salida (pega un extracto):**

```text
fecha-log.service - Escribe fecha en $HOME/dam/logs/fecha.log
     Loaded: loaded (/home/dam/.config/systemd/user/fecha-log.service; static)
     Active: inactive (dead)

sep 23 18:46:16 a108pc13 systemd[3323]: Started fecha-log.service - Escribe fecha en $HOME/dam/logs/fecha.log.
```
**Pregunta:** ¿Se creó/actualizó `~/dam/logs/fecha.log`? Muestra las últimas líneas:

```bash
tail -n 5 "$DAM/logs/fecha.log"
```

**Salida:**

```text
2025-09-23T18:46:16+01:00 :: hello from user timer
```

**Reflexiona la salida:**

```text
Se almaceno una creacion de timer por un usuario
```

---

**15.** Diferencia **enable** vs **start** (modo usuario). Habilita el **timer**.

```bash
cat << 'EOF' > ~/.config/systemd/user/fecha-log.timer
[Unit]
Description=Timer (usuario): ejecuta fecha-log.service cada minuto

[Timer]
OnCalendar=*:0/1
Unit=fecha-log.service
Persistent=true

[Install]
WantedBy=timers.target
EOF

systemctl --user daemon-reload
systemctl --user enable --now fecha-log.timer
systemctl --user list-timers --all | grep fecha-log || true
```

**Salida (recorta):**

```text
Created symlink /home/dam/.config/systemd/user/timers.target.wants/fecha-log.timer → /home/dam/.config/systemd/user/fecha-log.timer.
Tue 2025-09-23 18:48:00 WEST  21s -                                       - fecha-log.timer                fecha-log.service
```
**Pregunta:** ¿Qué diferencia hay entre `enable` y `start` cuando usas `systemctl --user`?  

**Respuesta:** Enable es para que el servicio se inicia al arrancar y start es para arrancarlo manualmente.

---

**16.** Logs recientes **del servicio de usuario** con `journalctl --user`.

```bash
journalctl --user -u fecha-log.service -n 10 --no-pager
```

**Salida:**

```text
sep 23 18:46:16 a108pc13 systemd[3323]: Started fecha-log.service - Escribe fecha en $HOME/dam/logs/fecha.log.
sep 23 18:48:30 a108pc13 systemd[3323]: Started fecha-log.service - Escribe fecha en $HOME/dam/logs/fecha.log.
sep 23 18:49:30 a108pc13 systemd[3323]: Started fecha-log.service - Escribe fecha en $HOME/dam/logs/fecha.log.
```
**Pregunta:** ¿Ves ejecuciones activadas por el timer? ¿Cuándo fue la última?  

**Respuesta:** Si 3 ejecuciones y la ultima fue el 23 de septiembre a las 18:49

---

### 2.3 — Observación de procesos sin root

**17.** Puertos en escucha (lo que puedas ver como usuario).

```bash
lsof -i -P -n | grep LISTEN || ss -lntp
```
**Salida:**

```text
adb       42047  dam   12u  IPv4 185838      0t0  TCP 127.0.0.1:5037 (LISTEN)
code      46703  dam   86u  IPv4 221955      0t0  TCP 127.0.0.1:46565 (LISTEN)
java      70554  dam   22u  IPv6 279350      0t0  TCP [::1]:42629 (LISTEN)
java      70554  dam  322u  IPv6 285388      0t0  TCP 127.0.0.1:34451 (LISTEN)
java      70554  dam  325u  IPv6 285389      0t0  TCP 127.0.0.1:40145 (LISTEN)
```
**Pregunta:** ¿Qué procesos *tuyos* están escuchando? (si no hay, explica por qué)  

**Respuesta:** Todos estan escuchando

---

**18.** Ejecuta un proceso bajo **cgroup de usuario** con límite de memoria.

```bash
systemd-run --user --scope -p MemoryMax=50M sleep 200
ps -eo pid,ppid,cmd,stat | grep "[s]leep 200"
```

**Salida:**

```text
Running as unit: run-r13f19b8118bc42889cdafff006b11bc4.scope; invocation ID: ecd83a59e1954c49a56bd2a6c8eace8c
```
**Pregunta:** ¿Qué ventaja tiene lanzar con `systemd-run --user` respecto a ejecutarlo “a pelo”?  

**Respuesta:** Lanzar procesos con systemd-run --user permite gestionar y mantener esos procesos como servicios del usuario, aprovechando el control, la supervisión y la automatización que ofrece systemd

---

**19.** Observa CPU en tiempo real con `top` (si tienes `htop`, también vale).

```bash
top -b -n 1 | head -n 15
```
**Salida (resumen):**

```text
MiB Mem :  31453,3 total,  12760,2 libre,   8322,8 usado,  10965,4 búf/caché    
MiB Intercambio:   2048,0 total,   2048,0 libre,      0,0 usado.  23130,5 dispon

    PID USUARIO   PR  NI    VIRT    RES    SHR S  %CPU  %MEM     HORA+ ORDEN
      1 root      20   0   23240  14024   9288 S   0,0   0,0   0:01.77 systemd
      2 root      20   0       0      0      0 S   0,0   0,0   0:00.03 kthreadd
      3 root      20   0       0      0      0 S   0,0   0,0   0:00.00 pool_wo+
      4 root       0 -20       0      0      0 I   0,0   0,0   0:00.00 kworker+
```
**Pregunta:** ¿Cuál es tu proceso con mayor `%CPU` en ese momento?  

**Respuesta:** 133996 dam       20   0   17228   5760   3584 R   8,3   0,0   0:00.02 top

---

**20.** Traza syscalls de **tu propio proceso** (p. ej., el `sleep` anterior).
> Nota: Sin root, no podrás adjuntarte a procesos de otros usuarios ni a algunos del sistema.

```bash
pid=$(pgrep -u "$USER" -x sleep | head -n1)
strace -p "$pid" -e trace=nanosleep -tt -c -f 2>&1 | sed -n '1,10p'
```

**Salida (fragmento):**

```text

```
**Pregunta:** Explica brevemente la syscall que observaste.  

**Respuesta:**

---

### 2.4 — Estados y jerarquía (sin root)

**21.** Árbol de procesos con PIDs.

```bash
pstree -p | head -n 50
```

**Salida (recorta):**

```text
<<<<<<< HEAD
systemd(1)-+-ModemManager(1851)-+-{ModemManager}(1861)
           |                    |-{ModemManager}(1864)
           |                    `-{ModemManager}(1866)
           |-NetworkManager(1819)-+-{NetworkManager}(1856)
           |                      |-{NetworkManager}(1857)
           |                      `-{NetworkManager}(1858)
           |-accounts-daemon(1156)-+-{accounts-daemon}(1190)
           |                       |-{accounts-daemon}(1191)
           |                       `-{accounts-daemon}(1820)
           |-agetty(2231)
           |-apache2(2283)-+-apache2(2295)
           |               |-apache2(2296)
           |               |-apache2(2298)
           |               |-apache2(2299)
           |               `-apache2(2300)
           |-at-spi2-registr(3660)-+-{at-spi2-registr}(3666)
           |                       |-{at-spi2-registr}(3667)
           |                       `-{at-spi2-registr}(3668)
           |-avahi-daemon(1158)---avahi-daemon(1251)
           |-blkmapd(1105)
           |-colord(2015)-+-{colord}(2021)
           |              |-{colord}(2022)
           |              `-{colord}(2025)
           |-containerd(2000)-+-{containerd}(2017)
           |                  |-{containerd}(2018)
           |                  |-{containerd}(2019)
           |                  |-{containerd}(2020)
           |                  |-{containerd}(2024)
           |                  |-{containerd}(2034)
           |                  |-{containerd}(2035)
           |                  |-{containerd}(2042)
           |                  |-{containerd}(2043)
           |                  |-{containerd}(2044)
           |                  |-{containerd}(2052)
           |                  |-{containerd}(2056)
           |                  |-{containerd}(2057)
           |                  |-{containerd}(2058)
           |                  `-{containerd}(2526)
=======
>>>>>>> 3ca8a9b (Subimos parte de la tarea)

```
**Pregunta:** ¿Bajo qué proceso aparece tu `systemd --user`?  

<<<<<<< HEAD
**Respuesta:** Aparece bajo la shell de el usuario.
=======
**Respuesta:**
>>>>>>> 3ca8a9b (Subimos parte de la tarea)

---

**22.** Estados y relación PID/PPID.

```bash
ps -eo pid,ppid,stat,cmd | head -n 20
```
**Salida:**

```text
<<<<<<< HEAD
PID    PPID STAT CMD
      1       0 Ss   /sbin/init splash
      2       0 S    [kthreadd]
      3       2 S    [pool_workqueue_release]
      4       2 I<   [kworker/R-rcu_g]
      5       2 I<   [kworker/R-rcu_p]
      6       2 I<   [kworker/R-slub_]
      7       2 I<   [kworker/R-netns]
     10       2 I<   [kworker/0:0H-events_highpri]
     12       2 I<   [kworker/R-mm_pe]
     13       2 I    [rcu_tasks_kthread]
     14       2 I    [rcu_tasks_rude_kthread]
     15       2 I    [rcu_tasks_trace_kthread]
     16       2 S    [ksoftirqd/0]
     17       2 I    [rcu_preempt]
     18       2 S    [migration/0]
     19       2 S    [idle_inject/0]
     20       2 S    [cpuhp/0]
     21       2 S    [cpuhp/1]
     22       2 S    [idle_inject/1]
```
**Pregunta:** Explica 3 flags de `STAT` que veas (ej.: `R`, `S`, `T`, `Z`, `+`).  

**Respuesta:** R= Running  es un proceso que esta en ejecucion
S= Sleeping  Es un proceso dormido, está esperando a un evento para activarse
Z= Zombie  Es un proceso que ha terminado pero su proceso padre no ha recolectado la informacion sobre su finalizacion.
=======

```
**Pregunta:** Explica 3 flags de `STAT` que veas (ej.: `R`, `S`, `T`, `Z`, `+`).  

**Respuesta:**
>>>>>>> 3ca8a9b (Subimos parte de la tarea)

---

**23.** Suspende y reanuda **uno de tus procesos** (no crítico).

```bash
# Crea un proceso propio en segundo plano
sleep 120 &
pid=$!
# Suspende
kill -STOP "$pid"
# Estado
ps -o pid,stat,cmd -p "$pid"
# Reanuda
kill -CONT "$pid"
# Estado
ps -o pid,stat,cmd -p "$pid"
```
**Pega los dos estados (antes/después):**

```text
<<<<<<< HEAD
 dam  ~  sleep 120 &
pid=$!
[1] 98961
 dam  ~  1  kill -STOP 98961

[1]+  Detenido                sleep 120

ps -o pid,stat,cmd -p 98961
    PID STAT CMD
  98961 T    sleep 120

 ps -o pid,stat,cmd -p 98961
    PID STAT CMD
  98961 S    sleep 120


=======
>>>>>>> 3ca8a9b (Subimos parte de la tarea)

```
**Pregunta:** ¿Qué flag indicó la suspensión?  

<<<<<<< HEAD
**Respuesta:** S = Sleeping
=======
**Respuesta:**
>>>>>>> 3ca8a9b (Subimos parte de la tarea)

---

**24. (Opcional)** Genera un **zombie** controlado (no requiere root).

```bash
cat << 'EOF' > "$DAM/bin/zombie.c"
#include <stdlib.h>
#include <unistd.h>
int main() {
  if (fork() == 0) { exit(0); } // hijo termina
  sleep(60); // padre no hace wait(), hijo queda zombie hasta que padre termine
  return 0;
}
EOF
gcc "$DAM/bin/zombie.c" -o "$DAM/bin/zombie" && "$DAM/bin/zombie" &
ps -el | grep ' Z '
```
**Salida (recorta):**

```text

```
**Pregunta:** ¿Por qué el estado `Z` y qué lo limpia finalmente?  

**Respuesta:**

---

### 2.5 — Limpieza (solo tu usuario)

Detén y deshabilita tu **timer/servicio de usuario** y borra artefactos si quieres.

```bash
systemctl --user disable --now fecha-log.timer
systemctl --user stop fecha-log.service
rm -f ~/.config/systemd/user/fecha-log.{service,timer}
systemctl --user daemon-reload
rm -rf "$DAM/bin" "$DAM/logs" "$DAM/units"
```

---

## ¿Qué estás prácticando?
<<<<<<< HEAD
- [X] Pegaste **salidas reales**.  
- [X] Explicaste **qué significan**.  
- [X] Usaste **systemd --user** y **journalctl --user**.  
- [X] Probaste `systemd-run --user` con límites de memoria.  
- [X] Practicaste señales (`STOP`/`CONT`), `pstree`, `ps` y `strace` **sobre tus procesos**.

## Creado Por César Domínguez Romero
=======
- [ ] Pegaste **salidas reales**.  
- [ ] Explicaste **qué significan**.  
- [ ] Usaste **systemd --user** y **journalctl --user**.  
- [ ] Probaste `systemd-run --user` con límites de memoria.  
- [ ] Practicaste señales (`STOP`/`CONT`), `pstree`, `ps` y `strace` **sobre tus procesos**.

---

## Licencia 📄
Licencia **Apache 2.0** — ver [LICENSE.md](https://github.com/jpexposito/code-learn-practice/blob/main/LICENSE).
>>>>>>> 3ca8a9b (Subimos parte de la tarea)
