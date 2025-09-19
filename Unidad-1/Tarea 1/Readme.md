## Code, Learn & Practice(Programación de Servicios y Procesos: "Procesos")

### Bloque 1: Conceptos básicos (teoría)


- #### Define qué es un proceso y en qué se diferencia de un programa.
Un proceso es una instancia en ejecución de un programa, con recursos asignados; un programa es código estático.

- #### Explica qué es el kernel y su papel en la gestión de procesos.
Núcleo del SO que gestiona procesos, memoria, hardware, etc.
- #### ¿Qué son PID y PPID? Explica con un ejemplo.
PID es ID único del proceso; PPID es ID del padre (ej. proceso hijo tiene PPID del padre).
- #### Describe qué es un cambio de contexto y por qué es costoso.
Interrupción y guardado de estado de un proceso para ejecutar otro; costoso por overhead.
- #### Explica qué es un PCB (Process Control Block) y qué información almacena.
Bloque de control que almacena estado, PID, registros, memoria, etc.
- #### Diferencia entre proceso padre y proceso hijo.
Padre crea hijo; hijo hereda atributos del padre.
- #### Explica qué ocurre cuando un proceso queda huérfano en Linux.
Padre termina antes que hijo; init adopta al hijo.
- #### ¿Qué es un proceso zombie? Da un ejemplo de cómo puede ocurrir.
Proceso terminado pero no recolectado; ocurre si padre no lee estado de salida.
- #### Diferencia entre concurrencia y paralelismo.
Concurrencia: múltiples tareas alternadas; Paralelismo: simultáneas en múltiples núcleos.
- #### Explica qué es un hilo (thread) y en qué se diferencia de un proceso.
Unidad de ejecución dentro de un proceso; comparte memoria con otros hilos del mismo proceso, a diferencia de procesos que son independientes.


### Bloque 2: Práctica con comandos en Linux

- #### Usa echo $$ para mostrar el PID del proceso actual.
`echo $$`

```bash
32540
```

- #### Usa echo $PPID para mostrar el PID del proceso padre.
`echo $PPID`

```bash
32531
```

- #### Ejecuta pidof systemd y explica el resultado.
`pidof systemd`  Muestra el PID del proceso systemd (gestor de servicios).

```bash
3325
```

- #### Abre un programa gráfico (ejemplo: gedit) y usa pidof para obtener sus PID.
`gedit &`

```bash
[1] 34553
``` 

`pidof gedit`  

```bash
34553
```

- #### Ejecuta ps -e y explica qué significan sus columnas.
`ps -e`  Lista todos los procesos; columnas: PID, TTY, TIME, CMD.

```bash
 PID TTY          TIME CMD
      1 ?        00:00:01 systemd
      2 ?        00:00:00 kthreadd
      3 ?        00:00:00 pool_workqueue_release
      4 ?        00:00:00 kworker/R-rcu_g
      5 ?        00:00:00 kworker/R-rcu_p
      6 ?        00:00:00 kworker/R-slub_
      7 ?        00:00:00 kworker/R-netns
      8 ?        00:00:00 kworker/0:0-rcu_par_gp
     10 ?        00:00:00 kworker/0:0H-events_highpri
     12 ?        00:00:00 kworker/R-mm_pe
     13 ?        00:00:00 rcu_tasks_kthread
     14 ?        00:00:00 rcu_tasks_rude_kthread
     15 ?        00:00:00 rcu_tasks_trace_kthread
     16 ?        00:00:00 ksoftirqd/0
     17 ?        00:00:02 rcu_preempt
     18 ?        00:00:00 migration/0
     19 ?        00:00:00 idle_inject/0
     20 ?        00:00:00 cpuhp/0
     21 ?        00:00:00 cpuhp/1
     22 ?        00:00:00 idle_inject/1
     23 ?        00:00:00 migration/1
     24 ?        00:00:00 ksoftirqd/1
     25 ?        00:00:00 kworker/1:0-events
     26 ?        00:00:00 kworker/1:0H-events_highpri
     27 ?        00:00:00 cpuhp/2
     28 ?        00:00:00 idle_inject/2
     29 ?        00:00:00 migration/2
     30 ?        00:00:00 ksoftirqd/2
     32 ?        00:00:00 kworker/2:0H-events_highpri
     33 ?        00:00:00 cpuhp/3
     34 ?        00:00:00 idle_inject/3
     35 ?        00:00:00 migration/3
     36 ?        00:00:00 ksoftirqd/3
     38 ?        00:00:00 kworker/3:0H-events_highpri
     39 ?        00:00:00 cpuhp/4
     40 ?        00:00:00 idle_inject/4
     41 ?        00:00:00 migration/4
     42 ?        00:00:00 ksoftirqd/4
     44 ?        00:00:00 kworker/4:0H-events_highpri
     45 ?        00:00:00 cpuhp/5
     46 ?        00:00:00 idle_inject/5
     47 ?        00:00:00 migration/5
     48 ?        00:00:00 ksoftirqd/5
     50 ?        00:00:00 kworker/5:0H-events_highpri
     51 ?        00:00:00 cpuhp/6
     52 ?        00:00:00 idle_inject/6
     53 ?        00:00:00 migration/6
     54 ?        00:00:00 ksoftirqd/6
     55 ?        00:00:00 kworker/6:0-rcu_par_gp
     56 ?        00:00:00 kworker/6:0H-events_highpri
     57 ?        00:00:00 cpuhp/7
     58 ?        00:00:00 idle_inject/7
     59 ?        00:00:00 migration/7
     60 ?        00:00:00 ksoftirqd/7
     62 ?        00:00:00 kworker/7:0H-kblockd
     63 ?        00:00:00 cpuhp/8
     64 ?        00:00:00 idle_inject/8
     65 ?        00:00:00 migration/8
     66 ?        00:00:00 ksoftirqd/8
     68 ?        00:00:00 kworker/8:0H-events_highpri
     69 ?        00:00:00 cpuhp/9
     70 ?        00:00:00 idle_inject/9
     71 ?        00:00:00 migration/9
     72 ?        00:00:00 ksoftirqd/9
     73 ?        00:00:00 kworker/9:0-events
     74 ?        00:00:00 kworker/9:0H-events_highpri
     75 ?        00:00:00 cpuhp/10
     76 ?        00:00:00 idle_inject/10
     77 ?        00:00:00 migration/10
     78 ?        00:00:00 ksoftirqd/10
     80 ?        00:00:00 kworker/10:0H-events_highpri
     81 ?        00:00:00 cpuhp/11
     82 ?        00:00:00 idle_inject/11
     83 ?        00:00:00 migration/11
     84 ?        00:00:00 ksoftirqd/11
     86 ?        00:00:00 kworker/11:0H-events_highpri
     89 ?        00:00:00 kdevtmpfs
     90 ?        00:00:00 kworker/R-inet_
     92 ?        00:00:00 kauditd
     96 ?        00:00:00 khungtaskd
     98 ?        00:00:00 oom_reaper
     99 ?        00:00:00 kworker/R-write
    100 ?        00:00:00 kcompactd0
    101 ?        00:00:00 ksmd
    102 ?        00:00:00 khugepaged
    103 ?        00:00:00 kworker/R-kinte
    104 ?        00:00:00 kworker/R-kbloc
    105 ?        00:00:00 kworker/R-blkcg
    106 ?        00:00:00 irq/9-acpi
    109 ?        00:00:00 kworker/R-tpm_d
    110 ?        00:00:00 kworker/R-ata_s
    111 ?        00:00:00 kworker/R-md
    112 ?        00:00:00 kworker/R-md_bi
    113 ?        00:00:00 kworker/R-edac-
    115 ?        00:00:00 kworker/R-devfr
    116 ?        00:00:00 watchdogd
    117 ?        00:00:00 kworker/2:1H-kblockd
    119 ?        00:00:00 kswapd0
    120 ?        00:00:00 ecryptfs-kthread
    121 ?        00:00:00 kworker/R-kthro
    126 ?        00:00:00 kworker/9:1-events
    127 ?        00:00:00 kworker/10:1-events
    129 ?        00:00:00 kworker/R-acpi_
    132 ?        00:00:00 kworker/R-mld
    133 ?        00:00:00 kworker/R-ipv6_
    135 ?        00:00:00 kworker/3:1H-kblockd
    143 ?        00:00:00 kworker/R-kstrp
    145 ?        00:00:00 kworker/u67:0
    152 ?        00:00:00 kworker/R-crypt
    162 ?        00:00:00 kworker/R-charg
    191 ?        00:00:00 kworker/9:1H-kblockd
    194 ?        00:00:00 kworker/5:1H-kblockd
    195 ?        00:00:00 kworker/6:1H-kblockd
    206 ?        00:00:00 kworker/8:1H-kblockd
    208 ?        00:00:00 kworker/4:1H-kblockd
    209 ?        00:00:00 kworker/10:1H-kblockd
    213 ?        00:00:00 kworker/11:1H-kblockd
    221 ?        00:00:00 kworker/1:1H-kblockd
    227 ?        00:00:00 kworker/0:1H-kblockd
    228 ?        00:00:00 kworker/7:1H
    230 ?        00:00:00 scsi_eh_0
    231 ?        00:00:00 kworker/R-scsi_
    232 ?        00:00:00 scsi_eh_1
    233 ?        00:00:00 kworker/R-scsi_
    234 ?        00:00:00 scsi_eh_2
    235 ?        00:00:00 kworker/R-scsi_
    237 ?        00:00:00 scsi_eh_3
    238 ?        00:00:00 kworker/R-scsi_
    239 ?        00:00:00 scsi_eh_4
    240 ?        00:00:00 kworker/R-scsi_
    241 ?        00:00:00 scsi_eh_5
    242 ?        00:00:00 kworker/R-scsi_
    243 ?        00:00:00 scsi_eh_6
    244 ?        00:00:00 kworker/R-scsi_
    245 ?        00:00:00 scsi_eh_7
    246 ?        00:00:00 kworker/R-scsi_
    253 ?        00:00:00 scsi_eh_8
    255 ?        00:00:00 kworker/R-scsi_
    256 ?        00:00:01 kworker/u66:9+events_unbound
    257 ?        00:00:00 scsi_eh_9
    258 ?        00:00:00 kworker/R-scsi_
    262 ?        00:00:00 scsi_eh_10
    263 ?        00:00:00 kworker/R-scsi_
    298 ?        00:00:00 scsi_eh_11
    299 ?        00:00:00 kworker/R-scsi_
    300 ?        00:00:00 usb-storage
    302 ?        00:00:00 kworker/R-uas
    322 ?        00:00:00 kworker/11:2-events
    333 ?        00:00:00 kworker/R-raid5
    383 ?        00:00:00 jbd2/sdb3-8
    384 ?        00:00:00 kworker/R-ext4-
    445 ?        00:00:00 systemd-journal
    476 ?        00:00:00 kworker/R-rpcio
    477 ?        00:00:00 kworker/R-xprti
    480 ?        00:00:00 kworker/R-nvme-
    481 ?        00:00:00 kworker/R-nvme-
    482 ?        00:00:00 kworker/R-nvme-
    483 ?        00:00:00 kworker/R-nvme-
    509 ?        00:00:00 kworker/7:2-events
    530 ?        00:00:00 systemd-udevd
    533 ?        00:00:00 psimon
    899 ?        00:00:00 kworker/u65:4-events_power_efficient
    902 ?        00:00:00 jbd2/sda1-8
    903 ?        00:00:00 kworker/R-ext4-
    908 ?        00:00:00 kworker/R-amdgp
    910 ?        00:00:00 jbd2/sdb4-8
    911 ?        00:00:00 kworker/R-ext4-
    915 ?        00:00:00 kworker/R-ttm
    916 ?        00:00:00 kworker/R-amdgp
    917 ?        00:00:00 kworker/R-amdgp
    918 ?        00:00:00 kworker/R-amdgp
    919 ?        00:00:00 kworker/R-dm_vb
    920 ?        00:00:00 card1-crtc0
    921 ?        00:00:00 card1-crtc1
    922 ?        00:00:00 card1-crtc2
    923 ?        00:00:00 card1-crtc3
    935 ?        00:00:00 spl_system_task
    936 ?        00:00:00 spl_delay_taskq
    937 ?        00:00:00 spl_dynamic_tas
    938 ?        00:00:00 spl_kmem_cache
    940 ?        00:00:00 zvol
    941 ?        00:00:00 arc_prune
    942 ?        00:00:00 arc_evict
    943 ?        00:00:00 arc_reap
    944 ?        00:00:00 dbu_evict
    945 ?        00:00:00 dbuf_evict
    946 ?        00:00:00 z_vdev_file
    947 ?        00:00:00 l2arc_feed
   1053 ?        00:00:00 rpcbind
   1063 ?        00:00:00 systemd-resolve
   1064 ?        00:00:00 systemd-timesyn
   1078 ?        00:00:00 blkmapd
   1110 ?        00:00:00 nfsdcld
   1146 ?        00:00:00 accounts-daemon
   1151 ?        00:00:00 avahi-daemon
   1154 ?        00:00:00 dbus-daemon
   1157 ?        00:00:00 fsidd
   1161 ?        00:00:00 irqbalance
   1197 ?        00:00:00 polkitd
   1198 ?        00:00:00 power-profiles-
   1204 ?        00:00:00 prometheus-node
   1213 ?        00:00:00 smartd
   1220 ?        00:00:00 switcheroo-cont
   1221 ?        00:00:00 systemd-logind
   1222 ?        00:00:00 systemd-machine
   1225 ?        00:00:01 touchegg
   1231 ?        00:00:00 udisksd
   1236 ?        00:00:00 virtlockd
   1237 ?        00:00:00 virtlogd
   1239 ?        00:00:00 zed
   1248 ?        00:00:00 avahi-daemon
   1252 ?        00:00:00 rsyslogd
   1818 ?        00:00:00 NetworkManager
   1819 ?        00:00:00 wpa_supplicant
   1845 ?        00:00:00 ModemManager
   1968 ?        00:00:00 psimon
   1977 ?        00:00:00 cupsd
   1994 ?        00:00:00 containerd
   2010 ?        00:00:00 colord
   2022 ?        00:00:00 winbindd
   2033 ?        00:00:00 wb[A108PC13]
   2092 ?        00:00:00 cups-browsed
   2093 ?        00:00:01 dockerd
   2094 ?        00:00:00 socat
   2104 ?        00:00:00 cron
   2108 ?        00:00:00 rpc.idmapd
   2119 ?        00:00:00 rpc.statd
   2125 ?        00:00:00 rpc.mountd
   2161 ?        00:00:00 kerneloops
   2165 ?        00:00:00 lightdm
   2166 ?        00:00:00 kerneloops
   2171 ?        00:00:00 kworker/R-ipmi-
   2176 ?        00:00:00 lockd
   2195 ?        00:00:00 kworker/R-iprt-
   2199 ?        00:00:00 iprt-VBoxTscThread
   2216 tty7     00:01:03 Xorg
   2219 tty1     00:00:00 agetty
   2223 ?        00:00:00 nfsd
   2224 ?        00:00:00 nfsd
   2225 ?        00:00:00 nfsd
   2226 ?        00:00:00 nfsd
   2227 ?        00:00:00 nfsd
   2228 ?        00:00:00 nfsd
   2229 ?        00:00:00 nfsd
   2230 ?        00:00:00 nfsd
   2276 ?        00:00:00 apache2
   2293 ?        00:00:00 apache2
   2294 ?        00:00:00 apache2
   2295 ?        00:00:00 apache2
   2297 ?        00:00:00 apache2
   2298 ?        00:00:00 apache2
   2333 ?        00:00:00 dnsmasq
   2334 ?        00:00:00 dnsmasq
   2421 ?        00:00:00 rtkit-daemon
   2620 ?        00:00:00 upowerd
   3128 ?        00:00:00 containerd-shim
   3150 ?        00:00:00 apache2
   3225 ?        00:00:00 docker-proxy
   3231 ?        00:00:00 docker-proxy
   3282 ?        00:00:00 apache2
   3283 ?        00:00:00 apache2
   3284 ?        00:00:00 apache2
   3285 ?        00:00:00 apache2
   3286 ?        00:00:00 apache2
   3311 ?        00:00:00 lightdm
   3325 ?        00:00:00 systemd
   3326 ?        00:00:00 (sd-pam)
   3335 ?        00:00:00 pipewire
   3336 ?        00:00:00 pipewire
   3338 ?        00:00:00 powerline-daemo
   3339 ?        00:00:00 wireplumber
   3340 ?        00:00:00 pipewire-pulse
   3341 ?        00:00:00 gnome-keyring-d
   3342 ?        00:00:00 dbus-daemon
   3367 ?        00:00:00 cinnamon-sessio
   3580 ?        00:00:00 csd-automount
   3581 ?        00:00:00 csd-settings-re
   3582 ?        00:00:00 csd-power
   3584 ?        00:00:00 csd-housekeepin
   3585 ?        00:00:00 csd-wacom
   3586 ?        00:00:00 csd-color
   3590 ?        00:00:00 csd-background
   3591 ?        00:00:00 at-spi-bus-laun
   3594 ?        00:00:00 csd-screensaver
   3596 ?        00:00:00 csd-xsettings
   3602 ?        00:00:00 csd-keyboard
   3603 ?        00:00:00 dbus-daemon
   3604 ?        00:00:00 csd-media-keys
   3605 ?        00:00:00 csd-clipboard
   3606 ?        00:00:00 csd-print-notif
   3607 ?        00:00:00 csd-a11y-settin
   3610 ?        00:00:00 dconf-service
   3651 ?        00:00:00 gvfsd
   3664 ?        00:00:00 gvfsd-fuse
   3677 ?        00:00:00 csd-printer
   3686 ?        00:00:00 at-spi2-registr
   3704 ?        00:00:00 gvfs-udisks2-vo
   3714 ?        00:00:00 gvfs-gphoto2-vo
   3719 ?        00:00:00 gvfs-goa-volume
   3724 ?        00:00:00 goa-daemon
   3732 ?        00:00:00 goa-identity-se
   3738 ?        00:00:00 gvfs-mtp-volume
   3744 ?        00:00:00 gvfs-afc-volume
   3751 ?        00:00:00 cinnamon-launch
   3755 ?        00:02:05 cinnamon
   3787 ?        00:00:00 xapp-sn-watcher
   3810 ?        00:00:00 agent
   3819 ?        00:00:00 socat
   3824 ?        00:00:00 nemo-desktop
   3832 ?        00:00:00 evolution-alarm
   3835 ?        00:00:00 cinnamon-killer
   3836 ?        00:00:00 blueman-applet
   3927 ?        00:00:00 obexd
   3934 ?        00:00:00 evolution-sourc
   3945 ?        00:00:00 evolution-calen
   3960 ?        00:00:00 evolution-addre
   3989 ?        00:00:00 gvfsd-trash
   4002 ?        00:00:00 gvfsd-metadata
   4058 ?        00:00:00 mintUpdate
   4149 ?        00:00:00 applet.py
   4258 ?        00:00:10 mintreport-tray
   4289 ?        00:00:00 xdg-desktop-por
   4294 ?        00:00:00 xdg-document-po
   4298 ?        00:00:00 xdg-permission-
   4305 ?        00:00:00 fusermount3
   4310 ?        00:00:00 xdg-desktop-por
   4329 ?        00:00:00 xdg-desktop-por
   4544 ?        00:02:32 firefox-bin
   4550 ?        00:00:00 crashhelper
   4638 ?        00:00:00 Socket Process
   4665 ?        00:00:08 Privileged Cont
   4671 ?        00:00:00 RDD Process
   4734 ?        00:00:07 WebExtensions
   4783 ?        00:00:00 Utility Process
   4791 ?        00:00:16 Isolated Web Co
   4795 ?        00:00:02 Isolated Web Co
   4799 ?        00:00:05 Isolated Web Co
   4920 ?        00:00:07 Isolated Web Co
   5114 ?        00:00:03 Isolated Web Co
   5217 ?        00:00:05 Isolated Web Co
   5273 ?        00:00:00 kworker/11:0-inet_frag_wq
   5354 ?        00:00:29 code
   5358 ?        00:00:00 code
   5359 ?        00:00:00 code
   5361 ?        00:00:00 code
   5380 ?        00:00:00 chrome_crashpad
   5396 ?        00:01:46 code
   5401 ?        00:00:01 code
   5424 ?        00:03:06 code
   5442 ?        00:00:41 code
   5464 ?        00:00:00 kworker/u68:3-ttm
   5694 ?        00:00:35 code
   5695 ?        00:01:29 code
   5730 ?        00:00:01 code
   5731 ?        00:00:00 code
   5738 ?        00:00:26 code
   5739 ?        00:00:00 code
   5741 ?        00:00:00 code
   5793 ?        00:00:01 code
   5795 ?        00:00:03 code
   5806 ?        00:00:07 code
   5856 ?        00:00:00 kworker/4:1-events
   5870 pts/0    00:00:00 bash
   6530 ?        00:00:00 code
   6538 ?        00:00:00 code
   6564 ?        00:00:00 bash
   6577 ?        00:00:02 intelliphp.ls
   6606 ?        00:00:00 php
   6687 ?        00:00:00 sh
   6688 ?        00:00:00 code
   6689 ?        00:00:01 php8.3
   6748 ?        00:00:00 code
   6784 ?        00:00:39 java
   6848 ?        00:00:07 devsense.php.ls
   6993 ?        00:00:52 code
   7144 ?        00:00:00 code
   7151 ?        00:00:00 code
   7167 ?        00:00:00 code
   7545 ?        00:00:00 Isolated Web Co
  13043 ?        00:00:00 kworker/6:2-events
  13049 ?        00:00:00 fwupd
  13050 ?        00:00:00 kworker/5:0-events
  13082 ?        00:00:00 kworker/2:2-cgwb_release
  14769 ?        00:00:00 kworker/u65:1-events_power_efficient
  15317 ?        00:00:00 gvfsd-http
  16282 pts/0    00:00:00 docker
  16305 pts/0    00:00:00 docker-compose
  16364 ?        00:00:00 kworker/3:0-events
  16414 ?        00:00:00 containerd-shim
  16438 ?        00:00:00 apache2
  16513 ?        00:00:00 docker-proxy
  16520 ?        00:00:00 docker-proxy
  16551 ?        00:00:00 apache2
  16552 ?        00:00:00 apache2
  16553 ?        00:00:00 apache2
  16554 ?        00:00:00 apache2
  16555 ?        00:00:00 apache2
  16775 ?        00:00:00 apache2
  17106 ?        00:00:24 Isolated Web Co
  17921 ?        00:00:00 kworker/5:3-events
  19095 ?        00:00:00 kworker/u65:0-events_power_efficient
  19210 ?        00:00:00 kworker/0:1-events
  19448 ?        00:00:00 kworker/u66:2-events_power_efficient
  19537 ?        00:00:00 kworker/u68:4-ttm
  19838 ?        00:00:00 kworker/8:2-events_freezable
  20828 ?        00:00:00 kworker/7:1-events
  21140 ?        00:00:00 kworker/2:0-inet_frag_wq
  21154 ?        00:00:00 Web Content
  21200 ?        00:00:00 Web Content
  21267 ?        00:00:00 kworker/10:2-rcu_par_gp
  21280 ?        00:00:00 Web Content
  21442 ?        00:00:00 kworker/4:0
  21443 ?        00:00:00 kworker/3:2-rcu_par_gp
  21609 ?        00:00:00 kworker/1:2-mm_percpu_wq
  26038 ?        00:00:01 kworker/u64:0-sdma0
  26162 ?        00:00:00 kworker/u66:0-events_freezable_power_
  26276 ?        00:00:00 kworker/8:1-cgroup_destroy
  29587 ?        00:00:00 kworker/u69:0
  29588 ?        00:00:00 kworker/u69:1-ttm
  30308 ?        00:00:00 kworker/u64:2-gfx_low
  31139 ?        00:00:00 kworker/u65:2-flush-8:16
  31165 ?        00:00:00 kworker/10:0-rcu_par_gp
  31238 ?        00:00:00 kworker/0:2
  31243 ?        00:00:00 kworker/2:1-events
  31523 ?        00:00:00 kworker/u65:3-flush-8:16
  32264 ?        00:00:00 kworker/u68:0-ttm
  32531 ?        00:00:00 gnome-terminal-
  32540 pts/1    00:00:00 bash
  34479 ?        00:00:00 kworker/u64:1-sdma0
  34553 pts/1    00:00:00 xeyes
  34975 ?        00:00:00 kworker/u66:1
  35027 pts/1    00:00:00 ps

```

- #### Ejecuta ps -f y observa la relación entre procesos padre e hijo.
`ps -f`

```bash
UID          PID    PPID  C STIME TTY          TIME CMD
dam        32540   32531  0 15:10 pts/1    00:00:00 bash
dam        34553   32540  0 15:13 pts/1    00:00:00 xeyes
dam        35546   32540  0 15:14 pts/1    00:00:00 ps -f
```

- #### Usa ps -axf o pstree para mostrar el árbol de procesos y dibújalo.
`ps -axf`

```bash
PID TTY      STAT   TIME COMMAND
      2 ?        S      0:00 [kthreadd]
      3 ?        S      0:00  \_ [pool_workqueue_release]
      4 ?        I<     0:00  \_ [kworker/R-rcu_g]
      5 ?        I<     0:00  \_ [kworker/R-rcu_p]
      6 ?        I<     0:00  \_ [kworker/R-slub_]
      7 ?        I<     0:00  \_ [kworker/R-netns]
     10 ?        I<     0:00  \_ [kworker/0:0H-events_highpri]
     12 ?        I<     0:00  \_ [kworker/R-mm_pe]
     13 ?        I      0:00  \_ [rcu_tasks_kthread]
     14 ?        I      0:00  \_ [rcu_tasks_rude_kthread]
     15 ?        I      0:00  \_ [rcu_tasks_trace_kthread]
     16 ?        S      0:00  \_ [ksoftirqd/0]
     17 ?        I      0:02  \_ [rcu_preempt]
     18 ?        S      0:00  \_ [migration/0]
     19 ?        S      0:00  \_ [idle_inject/0]
     20 ?        S      0:00  \_ [cpuhp/0]
     21 ?        S      0:00  \_ [cpuhp/1]
     22 ?        S      0:00  \_ [idle_inject/1]
     23 ?        S      0:00  \_ [migration/1]
     24 ?        S      0:00  \_ [ksoftirqd/1]
     25 ?        I      0:00  \_ [kworker/1:0-events]
     26 ?        I<     0:00  \_ [kworker/1:0H-events_highpri]
     27 ?        S      0:00  \_ [cpuhp/2]
     28 ?        S      0:00  \_ [idle_inject/2]
     29 ?        S      0:00  \_ [migration/2]
     30 ?        S      0:00  \_ [ksoftirqd/2]
     32 ?        I<     0:00  \_ [kworker/2:0H-events_highpri]
     33 ?        S      0:00  \_ [cpuhp/3]
     34 ?        S      0:00  \_ [idle_inject/3]
     35 ?        S      0:00  \_ [migration/3]
     36 ?        S      0:00  \_ [ksoftirqd/3]
     38 ?        I<     0:00  \_ [kworker/3:0H-events_highpri]
     39 ?        S      0:00  \_ [cpuhp/4]
     40 ?        S      0:00  \_ [idle_inject/4]
     41 ?        S      0:00  \_ [migration/4]
     42 ?        S      0:00  \_ [ksoftirqd/4]
     44 ?        I<     0:00  \_ [kworker/4:0H-events_highpri]
     45 ?        S      0:00  \_ [cpuhp/5]
     46 ?        S      0:00  \_ [idle_inject/5]
     47 ?        S      0:00  \_ [migration/5]
     48 ?        S      0:00  \_ [ksoftirqd/5]
     50 ?        I<     0:00  \_ [kworker/5:0H-events_highpri]
     51 ?        S      0:00  \_ [cpuhp/6]
     52 ?        S      0:00  \_ [idle_inject/6]
     53 ?        S      0:00  \_ [migration/6]
     54 ?        S      0:00  \_ [ksoftirqd/6]
     55 ?        I      0:00  \_ [kworker/6:0-rcu_par_gp]
     56 ?        I<     0:00  \_ [kworker/6:0H-events_highpri]
     57 ?        S      0:00  \_ [cpuhp/7]
     58 ?        S      0:00  \_ [idle_inject/7]
     59 ?        S      0:00  \_ [migration/7]
     60 ?        S      0:00  \_ [ksoftirqd/7]
     62 ?        I<     0:00  \_ [kworker/7:0H-kblockd]
     63 ?        S      0:00  \_ [cpuhp/8]
     64 ?        S      0:00  \_ [idle_inject/8]
     65 ?        S      0:00  \_ [migration/8]
     66 ?        S      0:00  \_ [ksoftirqd/8]
     68 ?        I<     0:00  \_ [kworker/8:0H-events_highpri]
     69 ?        S      0:00  \_ [cpuhp/9]
     70 ?        S      0:00  \_ [idle_inject/9]
     71 ?        S      0:00  \_ [migration/9]
     72 ?        S      0:00  \_ [ksoftirqd/9]
     73 ?        I      0:00  \_ [kworker/9:0-events]
     74 ?        I<     0:00  \_ [kworker/9:0H-events_highpri]
     75 ?        S      0:00  \_ [cpuhp/10]
     76 ?        S      0:00  \_ [idle_inject/10]
     77 ?        S      0:00  \_ [migration/10]
     78 ?        S      0:00  \_ [ksoftirqd/10]
     80 ?        I<     0:00  \_ [kworker/10:0H-events_highpri]
     81 ?        S      0:00  \_ [cpuhp/11]
     82 ?        S      0:00  \_ [idle_inject/11]
     83 ?        S      0:00  \_ [migration/11]
     84 ?        S      0:00  \_ [ksoftirqd/11]
     86 ?        I<     0:00  \_ [kworker/11:0H-events_highpri]
     89 ?        S      0:00  \_ [kdevtmpfs]
     90 ?        I<     0:00  \_ [kworker/R-inet_]
     92 ?        S      0:00  \_ [kauditd]
     96 ?        S      0:00  \_ [khungtaskd]
     98 ?        S      0:00  \_ [oom_reaper]
     99 ?        I<     0:00  \_ [kworker/R-write]
    100 ?        S      0:00  \_ [kcompactd0]
    101 ?        SN     0:00  \_ [ksmd]
    102 ?        SN     0:00  \_ [khugepaged]
    103 ?        I<     0:00  \_ [kworker/R-kinte]
    104 ?        I<     0:00  \_ [kworker/R-kbloc]
    105 ?        I<     0:00  \_ [kworker/R-blkcg]
    106 ?        S      0:00  \_ [irq/9-acpi]
    109 ?        I<     0:00  \_ [kworker/R-tpm_d]
    110 ?        I<     0:00  \_ [kworker/R-ata_s]
    111 ?        I<     0:00  \_ [kworker/R-md]
    112 ?        I<     0:00  \_ [kworker/R-md_bi]
    113 ?        I<     0:00  \_ [kworker/R-edac-]
    115 ?        I<     0:00  \_ [kworker/R-devfr]
    116 ?        S      0:00  \_ [watchdogd]
    117 ?        I<     0:00  \_ [kworker/2:1H-kblockd]
    119 ?        S      0:00  \_ [kswapd0]
    120 ?        S      0:00  \_ [ecryptfs-kthread]
    121 ?        I<     0:00  \_ [kworker/R-kthro]
    126 ?        I      0:00  \_ [kworker/9:1-events]
    127 ?        I      0:00  \_ [kworker/10:1-mm_percpu_wq]
    129 ?        I<     0:00  \_ [kworker/R-acpi_]
    132 ?        I<     0:00  \_ [kworker/R-mld]
    133 ?        I<     0:00  \_ [kworker/R-ipv6_]
    135 ?        I<     0:00  \_ [kworker/3:1H-kblockd]
    143 ?        I<     0:00  \_ [kworker/R-kstrp]
    145 ?        I<     0:00  \_ [kworker/u67:0]
    152 ?        I<     0:00  \_ [kworker/R-crypt]
    162 ?        I<     0:00  \_ [kworker/R-charg]
    191 ?        I<     0:00  \_ [kworker/9:1H-kblockd]
    194 ?        I<     0:00  \_ [kworker/5:1H-kblockd]
    195 ?        I<     0:00  \_ [kworker/6:1H-kblockd]
    206 ?        I<     0:00  \_ [kworker/8:1H-kblockd]
    208 ?        I<     0:00  \_ [kworker/4:1H-kblockd]
    209 ?        I<     0:00  \_ [kworker/10:1H-kblockd]
    213 ?        I<     0:00  \_ [kworker/11:1H-kblockd]
    221 ?        I<     0:00  \_ [kworker/1:1H-kblockd]
    227 ?        I<     0:00  \_ [kworker/0:1H-kblockd]
    228 ?        I<     0:00  \_ [kworker/7:1H]
    230 ?        S      0:00  \_ [scsi_eh_0]
    231 ?        I<     0:00  \_ [kworker/R-scsi_]
    232 ?        S      0:00  \_ [scsi_eh_1]
    233 ?        I<     0:00  \_ [kworker/R-scsi_]
    234 ?        S      0:00  \_ [scsi_eh_2]
    235 ?        I<     0:00  \_ [kworker/R-scsi_]
    237 ?        S      0:00  \_ [scsi_eh_3]
    238 ?        I<     0:00  \_ [kworker/R-scsi_]
    239 ?        S      0:00  \_ [scsi_eh_4]
    240 ?        I<     0:00  \_ [kworker/R-scsi_]
    241 ?        S      0:00  \_ [scsi_eh_5]
    242 ?        I<     0:00  \_ [kworker/R-scsi_]
    243 ?        S      0:00  \_ [scsi_eh_6]
    244 ?        I<     0:00  \_ [kworker/R-scsi_]
    245 ?        S      0:00  \_ [scsi_eh_7]
    246 ?        I<     0:00  \_ [kworker/R-scsi_]
    253 ?        S      0:00  \_ [scsi_eh_8]
    255 ?        I<     0:00  \_ [kworker/R-scsi_]
    256 ?        I      0:01  \_ [kworker/u66:9-events_freezable_power_]
    257 ?        S      0:00  \_ [scsi_eh_9]
    258 ?        I<     0:00  \_ [kworker/R-scsi_]
    262 ?        S      0:00  \_ [scsi_eh_10]
    263 ?        I<     0:00  \_ [kworker/R-scsi_]
    298 ?        S      0:00  \_ [scsi_eh_11]
    299 ?        I<     0:00  \_ [kworker/R-scsi_]
    300 ?        S      0:00  \_ [usb-storage]
    302 ?        I<     0:00  \_ [kworker/R-uas]
    322 ?        I      0:00  \_ [kworker/11:2-events]
    333 ?        I<     0:00  \_ [kworker/R-raid5]
    383 ?        S      0:00  \_ [jbd2/sdb3-8]
    384 ?        I<     0:00  \_ [kworker/R-ext4-]
    476 ?        I<     0:00  \_ [kworker/R-rpcio]
    477 ?        I<     0:00  \_ [kworker/R-xprti]
    480 ?        I<     0:00  \_ [kworker/R-nvme-]
    481 ?        I<     0:00  \_ [kworker/R-nvme-]
    482 ?        I<     0:00  \_ [kworker/R-nvme-]
    483 ?        I<     0:00  \_ [kworker/R-nvme-]
    509 ?        I      0:00  \_ [kworker/7:2-events]
    533 ?        S      0:00  \_ [psimon]
    902 ?        S      0:00  \_ [jbd2/sda1-8]
    903 ?        I<     0:00  \_ [kworker/R-ext4-]
    908 ?        I<     0:00  \_ [kworker/R-amdgp]
    910 ?        S      0:00  \_ [jbd2/sdb4-8]
    911 ?        I<     0:00  \_ [kworker/R-ext4-]
    915 ?        I<     0:00  \_ [kworker/R-ttm]
    916 ?        I<     0:00  \_ [kworker/R-amdgp]
    917 ?        I<     0:00  \_ [kworker/R-amdgp]
    918 ?        I<     0:00  \_ [kworker/R-amdgp]
    919 ?        I<     0:00  \_ [kworker/R-dm_vb]
    920 ?        S      0:00  \_ [card1-crtc0]
    921 ?        S      0:00  \_ [card1-crtc1]
    922 ?        S      0:00  \_ [card1-crtc2]
    923 ?        S      0:00  \_ [card1-crtc3]
    935 ?        S<     0:00  \_ [spl_system_task]
    936 ?        S<     0:00  \_ [spl_delay_taskq]
    937 ?        S<     0:00  \_ [spl_dynamic_tas]
    938 ?        S<     0:00  \_ [spl_kmem_cache]
    940 ?        S<     0:00  \_ [zvol]
    941 ?        S      0:00  \_ [arc_prune]
    942 ?        S      0:00  \_ [arc_evict]
    943 ?        SN     0:00  \_ [arc_reap]
    944 ?        S      0:00  \_ [dbu_evict]
    945 ?        SN     0:00  \_ [dbuf_evict]
    946 ?        SN     0:00  \_ [z_vdev_file]
    947 ?        S      0:00  \_ [l2arc_feed]
   1968 ?        S      0:00  \_ [psimon]
   2171 ?        I<     0:00  \_ [kworker/R-ipmi-]
   2176 ?        I      0:00  \_ [lockd]
   2195 ?        I<     0:00  \_ [kworker/R-iprt-]
   2199 ?        S      0:00  \_ [iprt-VBoxTscThread]
   2223 ?        I      0:00  \_ [nfsd]
   2224 ?        I      0:00  \_ [nfsd]
   2225 ?        I      0:00  \_ [nfsd]
   2226 ?        I      0:00  \_ [nfsd]
   2227 ?        I      0:00  \_ [nfsd]
   2228 ?        I      0:00  \_ [nfsd]
   2229 ?        I      0:00  \_ [nfsd]
   2230 ?        I      0:00  \_ [nfsd]
   5273 ?        I      0:00  \_ [kworker/11:0-inet_frag_wq]
   5464 ?        I<     0:00  \_ [kworker/u68:3-ttm]
   5856 ?        I      0:00  \_ [kworker/4:1-mm_percpu_wq]
  13043 ?        I      0:00  \_ [kworker/6:2-mm_percpu_wq]
  13050 ?        I      0:00  \_ [kworker/5:0-events]
  14769 ?        I      0:00  \_ [kworker/u65:1-events_power_efficient]
  16364 ?        I      0:00  \_ [kworker/3:0-mm_percpu_wq]
  17921 ?        I      0:00  \_ [kworker/5:3-events]
  19095 ?        I      0:00  \_ [kworker/u65:0-events_unbound]
  19210 ?        I      0:00  \_ [kworker/0:1-events]
  19448 ?        I      0:00  \_ [kworker/u66:2-events_unbound]
  19537 ?        I<     0:00  \_ [kworker/u68:4-ttm]
  19838 ?        I      0:00  \_ [kworker/8:2-mm_percpu_wq]
  20828 ?        I      0:00  \_ [kworker/7:1-events]
  21140 ?        I      0:00  \_ [kworker/2:0-rcu_par_gp]
  21442 ?        I      0:00  \_ [kworker/4:0]
  21443 ?        I      0:00  \_ [kworker/3:2-rcu_par_gp]
  21609 ?        I      0:00  \_ [kworker/1:2-events]
  26038 ?        I      0:01  \_ [kworker/u64:0-sdma0]
  26162 ?        I      0:00  \_ [kworker/u66:0-writeback]
  26276 ?        I      0:00  \_ [kworker/8:1-cgroup_destroy]
  29587 ?        I<     0:00  \_ [kworker/u69:0]
  29588 ?        I<     0:00  \_ [kworker/u69:1-ttm]
  30308 ?        I      0:00  \_ [kworker/u64:2-gfx_low]
  31139 ?        I      0:00  \_ [kworker/u65:2-writeback]
  31165 ?        I      0:00  \_ [kworker/10:0-rcu_par_gp]
  31238 ?        I      0:00  \_ [kworker/0:2-rcu_par_gp]
  31243 ?        I      0:00  \_ [kworker/2:1-events]
  31523 ?        I      0:00  \_ [kworker/u65:3-events_unbound]
  32264 ?        I<     0:00  \_ [kworker/u68:0-ttm]
  34479 ?        I      0:00  \_ [kworker/u64:1-sdma0]
  34975 ?        D      0:00  \_ [kworker/u66:1+events_unbound]
  35328 ?        I      0:00  \_ [kworker/0:0-rcu_par_gp]
  35334 ?        I      0:00  \_ [kworker/2:2-cgroup_destroy]
  35622 ?        I<     0:00  \_ [kworker/u69:2-ttm]
      1 ?        Ss     0:01 /sbin/init splash
    445 ?        S<s    0:00 /usr/lib/systemd/systemd-journald
    530 ?        Ss     0:00 /usr/lib/systemd/systemd-udevd
   1053 ?        Ss     0:00 /sbin/rpcbind -f -w
   1063 ?        Ss     0:00 /usr/lib/systemd/systemd-resolved
   1064 ?        Ssl    0:00 /usr/lib/systemd/systemd-timesyncd
   1078 ?        Ss     0:00 /usr/sbin/blkmapd
   1110 ?        Ss     0:00 /usr/sbin/nfsdcld
   1146 ?        Ssl    0:00 /usr/libexec/accounts-daemon
   1151 ?        Ss     0:00 avahi-daemon: running [a108pc13.local]
   1248 ?        S      0:00  \_ avahi-daemon: chroot helper
   1154 ?        Ss     0:00 @dbus-daemon --system --address=systemd: --nofork -
   1157 ?        Ss     0:00 /usr/sbin/fsidd
   1161 ?        Ssl    0:00 /usr/sbin/irqbalance
   1197 ?        Ssl    0:00 /usr/lib/polkit-1/polkitd --no-debug
   1198 ?        Ssl    0:00 /usr/libexec/power-profiles-daemon
   1204 ?        Ssl    0:00 /usr/bin/prometheus-node-exporter
   1213 ?        Ss     0:00 /usr/sbin/smartd -n
   1220 ?        Ssl    0:00 /usr/libexec/switcheroo-control
   1221 ?        Ss     0:00 /usr/lib/systemd/systemd-logind
   1222 ?        Ss     0:00 /usr/lib/systemd/systemd-machined
   1225 ?        Ssl    0:01 /usr/bin/touchegg --daemon
   1231 ?        Ssl    0:00 /usr/libexec/udisks2/udisksd
   1236 ?        Ss     0:00 /usr/sbin/virtlockd
   1237 ?        Ss     0:00 /usr/sbin/virtlogd
   1239 ?        Ssl    0:00 zed -F
   1252 ?        Ssl    0:00 /usr/sbin/rsyslogd -n -iNONE
   1818 ?        Ssl    0:00 /usr/sbin/NetworkManager --no-daemon
   1819 ?        Ss     0:00 /usr/sbin/wpa_supplicant -u -s -O DIR=/run/wpa_supp
   1845 ?        Ssl    0:00 /usr/sbin/ModemManager
   1977 ?        Ss     0:00 /usr/sbin/cupsd -l
   1994 ?        Ssl    0:00 /usr/bin/containerd
   2010 ?        Ssl    0:00 /usr/libexec/colord
   2022 ?        Ss     0:00 /usr/sbin/winbindd --foreground --no-process-group
   2033 ?        S      0:00  \_ winbindd: domain child [A108PC13]
   2092 ?        Ssl    0:00 /usr/sbin/cups-browsed
   2093 ?        Ssl    0:01 /usr/bin/dockerd -H fd:// --containerd=/run/contain
   3225 ?        Sl     0:00  \_ /usr/bin/docker-proxy -proto tcp -host-ip 0.0.0
   3231 ?        Sl     0:00  \_ /usr/bin/docker-proxy -proto tcp -host-ip :: -h
  16513 ?        Sl     0:00  \_ /usr/bin/docker-proxy -proto tcp -host-ip 0.0.0
  16520 ?        Sl     0:00  \_ /usr/bin/docker-proxy -proto tcp -host-ip :: -h
   2094 ?        Ss     0:00 socat -T 60 openssl-connect:server:789,cafile=/etc/
   2104 ?        Ss     0:00 /usr/sbin/cron -f -P
   2108 ?        Ss     0:00 /usr/sbin/rpc.idmapd
   2119 ?        Ss     0:00 /usr/sbin/rpc.statd
   2125 ?        Ss     0:00 /usr/sbin/rpc.mountd
   2161 ?        Ss     0:00 /usr/sbin/kerneloops --test
   2165 ?        SLsl   0:00 /usr/sbin/lightdm
   2216 tty7     Rsl+   1:05  \_ /usr/lib/xorg/Xorg -core :0 -seat seat0 -auth /
   3311 ?        Sl     0:00  \_ lightdm --session-child 13 24
   3367 ?        Ssl    0:00      \_ /usr/lib/x86_64-linux-gnu/cinnamon-session-
   3580 ?        Sl     0:00          \_ /usr/bin/csd-automount
   3581 ?        Sl     0:00          \_ /usr/bin/csd-settings-remap
   3582 ?        Sl     0:00          \_ /usr/bin/csd-power
   3584 ?        Sl     0:00          \_ /usr/bin/csd-housekeeping
   3585 ?        Sl     0:00          \_ /usr/bin/csd-wacom
   3586 ?        Sl     0:00          \_ /usr/bin/csd-color
   3590 ?        Sl     0:00          \_ /usr/bin/csd-background
   3591 ?        Sl     0:00          \_ /usr/libexec/at-spi-bus-launcher --laun
   3603 ?        S      0:00          |   \_ /usr/bin/dbus-daemon --config-file=
   3594 ?        Sl     0:00          \_ /usr/bin/csd-screensaver-proxy
   3596 ?        Sl     0:00          \_ /usr/bin/csd-xsettings
   3602 ?        Sl     0:00          \_ /usr/bin/csd-keyboard
   3604 ?        Sl     0:00          \_ /usr/bin/csd-media-keys
   3605 ?        Sl     0:00          \_ /usr/bin/csd-clipboard
   3606 ?        Sl     0:00          \_ /usr/bin/csd-print-notifications
   3607 ?        Sl     0:00          \_ /usr/bin/csd-a11y-settings
   3751 ?        Sl     0:00          \_ cinnamon-launcher
   3755 ?        Rl     2:07          |   \_ cinnamon --replace
   4544 ?        Sl     2:32          |       \_ /usr/lib/firefox/firefox --priv
   4550 ?        Sl     0:00          |       |   \_ /usr/lib/firefox/crashhelpe
   4638 ?        Sl     0:00          |       |   \_ /usr/lib/firefox/firefox-bi
   4665 ?        Sl     0:08          |       |   \_ /usr/lib/firefox/firefox-bi
   4671 ?        Sl     0:00          |       |   \_ /usr/lib/firefox/firefox-bi
   4734 ?        Sl     0:07          |       |   \_ /usr/lib/firefox/firefox-bi
   4783 ?        Sl     0:00          |       |   \_ /usr/lib/firefox/firefox-bi
   4791 ?        Sl     0:16          |       |   \_ /usr/lib/firefox/firefox-bi
   4795 ?        Sl     0:02          |       |   \_ /usr/lib/firefox/firefox-bi
   4799 ?        Sl     0:05          |       |   \_ /usr/lib/firefox/firefox-bi
   4920 ?        Sl     0:07          |       |   \_ /usr/lib/firefox/firefox-bi
   5114 ?        Sl     0:03          |       |   \_ /usr/lib/firefox/firefox-bi
   5217 ?        Sl     0:05          |       |   \_ /usr/lib/firefox/firefox-bi
   7545 ?        Sl     0:00          |       |   \_ /usr/lib/firefox/firefox-bi
  17106 ?        Sl     0:24          |       |   \_ /usr/lib/firefox/firefox-bi
  21154 ?        Sl     0:00          |       |   \_ /usr/lib/firefox/firefox-bi
  21200 ?        Sl     0:00          |       |   \_ /usr/lib/firefox/firefox-bi
  21280 ?        Sl     0:00          |       |   \_ /usr/lib/firefox/firefox-bi
   5354 ?        SLl    0:30          |       \_ /usr/share/code/code
   5358 ?        S      0:00          |           \_ /usr/share/code/code --type
   5396 ?        Sl     1:48          |           |   \_ /usr/share/code/code --
   5359 ?        S      0:00          |           \_ /usr/share/code/code --type
   5361 ?        S      0:00          |           |   \_ /usr/share/code/code --
   5424 ?        Sl     3:11          |           |       \_ /usr/share/code/cod
   5442 ?        Sl     0:41          |           |       \_ /usr/share/code/cod
   5806 ?        Sl     0:07          |           |       \_ /usr/share/code/cod
   6993 ?        Sl     0:52          |           |       \_ /usr/share/code/cod
   7151 ?        Sl     0:00          |           |       \_ /usr/share/code/cod
   7167 ?        Sl     0:00          |           |       \_ /usr/share/code/cod
   5401 ?        Sl     0:01          |           \_ /usr/share/code/code --type
   5694 ?        Sl     0:35          |           \_ /usr/share/code/code --type
   5731 ?        Sl     0:00          |           |   \_ /usr/share/code/code /h
   5741 ?        Sl     0:00          |           |   \_ /usr/share/code/code /h
   6538 ?        Sl     0:00          |           |   \_ /usr/share/code/code /h
   6577 ?        Sl     0:02          |           |   \_ /home/dam/.vscode/exten
   6606 ?        S      0:00          |           |   \_ PHP Language Server
   6687 ?        S      0:00          |           |   |   \_ sh -c '/usr/bin/php
   6689 ?        S      0:01          |           |   |       \_ PHP Language Se
   6748 ?        Sl     0:00          |           |   \_ /usr/share/code/code /u
   6848 ?        Sl     0:07          |           |   \_ /home/dam/.vscode/exten
   7144 ?        Sl     0:00          |           |   \_ /usr/share/code/code /u
   5695 ?        Sl     1:31          |           \_ /usr/share/code/code --type
   5730 ?        Sl     0:01          |           |   \_ /usr/share/code/code /u
   6530 ?        Sl     0:00          |           |   \_ /usr/share/code/code /h
   6564 ?        S      0:00          |           |   \_ /bin/bash /home/dam/.vs
   6784 ?        Sl     0:39          |           |   |   \_ /usr/lib/jvm/java-2
   6688 ?        Sl     0:00          |           |   \_ /usr/share/code/code /u
   5738 ?        Sl     0:26          |           \_ /usr/share/code/code --type
   5739 ?        Sl     0:00          |           \_ /usr/share/code/code --type
   5793 ?        Sl     0:01          |           \_ /usr/share/code/code --type
   5795 ?        Sl     0:03          |           \_ /usr/share/code/code --type
   5870 pts/0    Ss     0:00          |               \_ /usr/bin/bash --init-fi
  16282 pts/0    Sl+    0:00          |                   \_ docker compose up -
  16305 pts/0    Sl+    0:00          |                       \_ /usr/libexec/do
   3787 ?        Sl     0:00          \_ /usr/lib/x86_64-linux-gnu/xapps/xapp-sn
   3810 ?        Sl     0:00          \_ /usr/libexec/geoclue-2.0/demos/agent
   3819 ?        S      0:00          \_ socat -T 60 openssl-connect:server:789,
   3824 ?        Sl     0:00          \_ /usr/bin/nemo-desktop
   3832 ?        Sl     0:00          \_ /usr/libexec/evolution-data-server/evol
   3835 ?        Sl     0:00          \_ cinnamon-killer-daemon
   3836 ?        Sl     0:00          \_ /usr/bin/python3 /usr/bin/blueman-apple
   4149 ?        S      0:00          \_ /usr/bin/python3 /usr/share/system-conf
   2166 ?        Ss     0:00 /usr/sbin/kerneloops
   2219 tty1     Ss+    0:00 /sbin/agetty -o -p -- \u --noclear - linux
   2276 ?        Ss     0:00 /usr/sbin/apache2 -k start
   2293 ?        S      0:00  \_ /usr/sbin/apache2 -k start
   2294 ?        S      0:00  \_ /usr/sbin/apache2 -k start
   2295 ?        S      0:00  \_ /usr/sbin/apache2 -k start
   2297 ?        S      0:00  \_ /usr/sbin/apache2 -k start
   2298 ?        S      0:00  \_ /usr/sbin/apache2 -k start
   2333 ?        S      0:00 /usr/sbin/dnsmasq --conf-file=/var/lib/libvirt/dnsm
   2334 ?        S      0:00  \_ /usr/sbin/dnsmasq --conf-file=/var/lib/libvirt/
   2421 ?        SNsl   0:00 /usr/libexec/rtkit-daemon
   2620 ?        Ssl    0:00 /usr/libexec/upowerd
   3128 ?        Sl     0:00 /usr/bin/containerd-shim-runc-v2 -namespace moby -i
   3150 ?        Ss     0:00  \_ apache2 -DFOREGROUND
   3282 ?        S      0:00      \_ apache2 -DFOREGROUND
   3283 ?        S      0:00      \_ apache2 -DFOREGROUND
   3284 ?        S      0:00      \_ apache2 -DFOREGROUND
   3285 ?        S      0:00      \_ apache2 -DFOREGROUND
   3286 ?        S      0:00      \_ apache2 -DFOREGROUND
   3325 ?        Ss     0:00 /usr/lib/systemd/systemd --user
   3326 ?        S      0:00  \_ (sd-pam)
   3335 ?        S<sl   0:00  \_ /usr/bin/pipewire
   3336 ?        Ssl    0:00  \_ /usr/bin/pipewire -c filter-chain.conf
   3338 ?        Ss     0:00  \_ /usr/bin/python3 /usr/bin/powerline-daemon --fo
   3339 ?        S<sl   0:00  \_ /usr/bin/wireplumber
   3340 ?        S<sl   0:00  \_ /usr/bin/pipewire-pulse
   3341 ?        SLsl   0:00  \_ /usr/bin/gnome-keyring-daemon --foreground --co
   3342 ?        Ss     0:00  \_ /usr/bin/dbus-daemon --session --address=system
   3610 ?        Ssl    0:00  \_ /usr/libexec/dconf-service
   3651 ?        Ssl    0:00  \_ /usr/libexec/gvfsd
   3989 ?        Sl     0:00  |   \_ /usr/libexec/gvfsd-trash --spawner :1.32 /o
  15317 ?        Sl     0:00  |   \_ /usr/libexec/gvfsd-http --spawner :1.32 /or
   3664 ?        Sl     0:00  \_ /usr/libexec/gvfsd-fuse /run/user/1001/gvfs -f
   3704 ?        Ssl    0:00  \_ /usr/libexec/gvfs-udisks2-volume-monitor
   3714 ?        Ssl    0:00  \_ /usr/libexec/gvfs-gphoto2-volume-monitor
   3719 ?        Ssl    0:00  \_ /usr/libexec/gvfs-goa-volume-monitor
   3724 ?        Sl     0:00  \_ /usr/libexec/goa-daemon
   3732 ?        Sl     0:00  \_ /usr/libexec/goa-identity-service
   3738 ?        Ssl    0:00  \_ /usr/libexec/gvfs-mtp-volume-monitor
   3744 ?        Ssl    0:00  \_ /usr/libexec/gvfs-afc-volume-monitor
   3927 ?        Ss     0:00  \_ /usr/libexec/bluetooth/obexd
   3934 ?        Ssl    0:00  \_ /usr/libexec/evolution-source-registry
   3945 ?        Ssl    0:00  \_ /usr/libexec/evolution-calendar-factory
   3960 ?        Ssl    0:00  \_ /usr/libexec/evolution-addressbook-factory
   4002 ?        Ssl    0:00  \_ /usr/libexec/gvfsd-metadata
   4289 ?        Ssl    0:00  \_ /usr/libexec/xdg-desktop-portal
   4294 ?        Ssl    0:00  \_ /usr/libexec/xdg-document-portal
   4305 ?        Ss     0:00  |   \_ fusermount3 -o rw,nosuid,nodev,fsname=porta
   4298 ?        Ssl    0:00  \_ /usr/libexec/xdg-permission-store
   4310 ?        Ssl    0:00  \_ /usr/libexec/xdg-desktop-portal-xapp
   4329 ?        Ssl    0:00  \_ /usr/libexec/xdg-desktop-portal-gtk
  32531 ?        Ssl    0:01  \_ /usr/libexec/gnome-terminal-server
  32540 pts/1    Ss     0:00      \_ bash
  34553 pts/1    S      0:00          \_ xeyes
  35720 pts/1    R+     0:00          \_ ps -axf
   3677 ?        Sl     0:00 /usr/libexec/csd-printer
   3686 ?        Sl     0:00 /usr/libexec/at-spi2-registryd --use-gnome-session
   4058 ?        Sl     0:00 mintUpdate
   4258 ?        Sl     0:11 mintreport-tray
   5380 ?        Sl     0:00 /usr/share/code/chrome_crashpad_handler --monitor-s
  13049 ?        Ssl    0:00 /usr/libexec/fwupd/fwupd
  16414 ?        Sl     0:00 /usr/bin/containerd-shim-runc-v2 -namespace moby -i
  16438 ?        Ss     0:00  \_ apache2 -DFOREGROUND
  16551 ?        S      0:00      \_ apache2 -DFOREGROUND
  16552 ?        S      0:00      \_ apache2 -DFOREGROUND
  16553 ?        S      0:00      \_ apache2 -DFOREGROUND
  16554 ?        S      0:00      \_ apache2 -DFOREGROUND
  16555 ?        S      0:00      \_ apache2 -DFOREGROUND
  16775 ?        S      0:00      \_ apache2 -DFOREGROUND

```

`pstree`  

```bash
systemd─┬─ModemManager───3*[{ModemManager}]
        ├─NetworkManager───3*[{NetworkManager}]
        ├─accounts-daemon───3*[{accounts-daemon}]
        ├─agetty
        ├─apache2───5*[apache2]
        ├─at-spi2-registr───3*[{at-spi2-registr}]
        ├─avahi-daemon───avahi-daemon
        ├─blkmapd
        ├─chrome_crashpad───2*[{chrome_crashpad}]
        ├─colord───3*[{colord}]
        ├─containerd───16*[{containerd}]
        ├─containerd-shim─┬─apache2───5*[apache2]
        │                 └─11*[{containerd-shim}]
        ├─containerd-shim─┬─apache2───6*[apache2]
        │                 └─11*[{containerd-shim}]
        ├─cron
        ├─csd-printer───3*[{csd-printer}]
        ├─cups-browsed───3*[{cups-browsed}]
        ├─cupsd
        ├─dbus-daemon
        ├─dnsmasq───dnsmasq
        ├─dockerd─┬─2*[docker-proxy───7*[{docker-proxy}]]
        │         ├─docker-proxy───6*[{docker-proxy}]
        │         ├─docker-proxy───5*[{docker-proxy}]
        │         └─31*[{dockerd}]
        ├─fsidd
        ├─fwupd───5*[{fwupd}]
        ├─irqbalance───{irqbalance}
        ├─2*[kerneloops]
        ├─lightdm─┬─Xorg───11*[{Xorg}]
        │         ├─lightdm─┬─cinnamon-sessio─┬─agent───3*[{agent}]
        │         │         │                 ├─applet.py
        │         │         │                 ├─at-spi-bus-laun─┬─dbus-daemon
        │         │         │                 │                 └─4*[{at-spi-bu+
        │         │         │                 ├─blueman-applet───4*[{blueman-ap+
        │         │         │                 ├─cinnamon-killer───4*[{cinnamon-+
        │         │         │                 ├─cinnamon-launch─┬─cinnamon─┬─co+
        │         │         │                 │                 │          ├─fi+
        │         │         │                 │                 │          └─25+
        │         │         │                 │                 └─6*[{cinnamon-+
        │         │         │                 ├─csd-a11y-settin───4*[{csd-a11y-+
        │         │         │                 ├─csd-automount───4*[{csd-automou+
        │         │         │                 ├─csd-background───4*[{csd-backgr+
        │         │         │                 ├─csd-clipboard───3*[{csd-clipboa+
        │         │         │                 ├─csd-color───4*[{csd-color}]
        │         │         │                 ├─csd-housekeepin───4*[{csd-house+
        │         │         │                 ├─csd-keyboard───4*[{csd-keyboard+
        │         │         │                 ├─csd-media-keys───4*[{csd-media-+
        │         │         │                 ├─csd-power───4*[{csd-power}]
        │         │         │                 ├─csd-print-notif───3*[{csd-print+
        │         │         │                 ├─csd-screensaver───3*[{csd-scree+
        │         │         │                 ├─csd-settings-re───4*[{csd-setti+
        │         │         │                 ├─csd-wacom───3*[{csd-wacom}]
        │         │         │                 ├─csd-xsettings───4*[{csd-xsettin+
        │         │         │                 ├─evolution-alarm───7*[{evolution+
        │         │         │                 ├─nemo-desktop───5*[{nemo-desktop+
        │         │         │                 ├─socat
        │         │         │                 ├─xapp-sn-watcher───4*[{xapp-sn-w+
        │         │         │                 └─4*[{cinnamon-sessio}]
        │         │         └─3*[{lightdm}]
        │         └─3*[{lightdm}]
        ├─mintUpdate───8*[{mintUpdate}]
        ├─mintreport-tray───4*[{mintreport-tray}]
        ├─nfsdcld
        ├─polkitd───3*[{polkitd}]
        ├─power-profiles-───3*[{power-profiles-}]
        ├─prometheus-node───5*[{prometheus-node}]
        ├─rpc.idmapd
        ├─rpc.mountd
        ├─rpc.statd
        ├─rpcbind
        ├─rsyslogd───3*[{rsyslogd}]
        ├─rtkit-daemon───2*[{rtkit-daemon}]
        ├─smartd
        ├─socat
        ├─switcheroo-cont───3*[{switcheroo-cont}]
        ├─systemd─┬─(sd-pam)
        │         ├─dbus-daemon
        │         ├─dconf-service───3*[{dconf-service}]
        │         ├─evolution-addre───6*[{evolution-addre}]
        │         ├─evolution-calen───9*[{evolution-calen}]
        │         ├─evolution-sourc───4*[{evolution-sourc}]
        │         ├─gnome-keyring-d───4*[{gnome-keyring-d}]
        │         ├─gnome-terminal-─┬─bash─┬─pstree
        │         │                 │      └─xeyes
        │         │                 └─5*[{gnome-terminal-}]
        │         ├─goa-daemon───4*[{goa-daemon}]
        │         ├─goa-identity-se───3*[{goa-identity-se}]
        │         ├─gvfs-afc-volume───4*[{gvfs-afc-volume}]
        │         ├─gvfs-goa-volume───3*[{gvfs-goa-volume}]
        │         ├─gvfs-gphoto2-vo───3*[{gvfs-gphoto2-vo}]
        │         ├─gvfs-mtp-volume───3*[{gvfs-mtp-volume}]
        │         ├─gvfs-udisks2-vo───4*[{gvfs-udisks2-vo}]
        │         ├─gvfsd─┬─gvfsd-http───3*[{gvfsd-http}]
        │         │       ├─gvfsd-trash───4*[{gvfsd-trash}]
        │         │       └─3*[{gvfsd}]
        │         ├─gvfsd-fuse───6*[{gvfsd-fuse}]
        │         ├─gvfsd-metadata───3*[{gvfsd-metadata}]
        │         ├─obexd
        │         ├─2*[pipewire───2*[{pipewire}]]
        │         ├─pipewire-pulse───2*[{pipewire-pulse}]
        │         ├─powerline-daemo
        │         ├─wireplumber───5*[{wireplumber}]
        │         ├─xdg-desktop-por───6*[{xdg-desktop-por}]
        │         ├─2*[xdg-desktop-por───4*[{xdg-desktop-por}]]
        │         ├─xdg-document-po─┬─fusermount3
        │         │                 └─6*[{xdg-document-po}]
        │         └─xdg-permission-───3*[{xdg-permission-}]
        ├─systemd-journal
        ├─systemd-logind
        ├─systemd-machine
        ├─systemd-resolve
        ├─systemd-timesyn───{systemd-timesyn}
        ├─systemd-udevd
        ├─touchegg───3*[{touchegg}]
        ├─udisksd───5*[{udisksd}]
        ├─upowerd───3*[{upowerd}]
        ├─virtlockd
        ├─virtlogd
        ├─winbindd───wb[A108PC13]
        ├─wpa_supplicant
        └─zed───2*[{zed}]

```

- #### Ejecuta top o htop y localiza el proceso con mayor uso de CPU.
`top`  

```bash
   2216 root      20   0 1188492 158220  89376 S   3,0   0,5   1:11.17 Xorg                                                                                
```

- #### Ejecuta sleep 100 en segundo plano y busca su PID con ps.
`sleep 100 &` 

```bash
[2] 38554
```

`ps`

```bash
38554 pts/1    00:00:00 sleep
```

- #### Finaliza un proceso con kill y comprueba con ps que ya no está.
`kill <PID>`  

```bash
[1]-  Terminado               xeyes
```

`ps`

```bash
PID TTY          TIME CMD
  32540 pts/1    00:00:00 bash
  38554 pts/1    00:00:00 sleep
  39343 pts/1    00:00:00 ps

```

### Bloque 3: Procesos y jerarquía


- #### Identifica el PID del proceso init/systemd y explica su función.

El PID del proceso init/systemd es generalmente 1; es el primer proceso que arranca el sistema y gestiona otros procesos.

- #### Explica qué ocurre con el PPID de un proceso hijo si su padre termina antes.

Si el padre termina antes que el hijo, el PPID del hijo cambia a 1 (init/systemd lo adopta).

- #### Ejecuta un programa que genere varios procesos hijos y observa sus PIDs con ps.

`ps`.

```bash

```

- #### Haz que un proceso quede en estado suspendido con Ctrl+Z y réanúdalo con fg.
`fg`.

```bash
xeyes
```
- #### Lanza un proceso en segundo plano con & y obsérvalo con jobs.
`xeyes &`

```bash
[4] 41876
```

`jobs`

```bash
[4]+  Ejecutando              xeyes &
```

- #### Explica la diferencia entre los estados de un proceso: Running, Sleeping, Zombie, Stopped.

Estados de proceso:
  - Running: en ejecución.
  - Sleeping: esperando evento.
  - Zombie: terminado pero no recogido.
  - Stopped: detenido.

- #### Usa ps -eo pid,ppid,stat,cmd para mostrar los estados de varios procesos.
`ps -eo pid,ppid,stat,cmd`  

```bash
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
     23       2 S    [migration/1]
     24       2 S    [ksoftirqd/1]
     25       2 I    [kworker/1:0-events]
     26       2 I<   [kworker/1:0H-events_highpri]
     27       2 S    [cpuhp/2]
     28       2 S    [idle_inject/2]
     29       2 S    [migration/2]
     30       2 S    [ksoftirqd/2]
     32       2 I<   [kworker/2:0H-events_highpri]
     33       2 S    [cpuhp/3]
     34       2 S    [idle_inject/3]
     35       2 S    [migration/3]
     36       2 S    [ksoftirqd/3]
     38       2 I<   [kworker/3:0H-events_highpri]
     39       2 S    [cpuhp/4]
     40       2 S    [idle_inject/4]
     41       2 S    [migration/4]
     42       2 S    [ksoftirqd/4]
     44       2 I<   [kworker/4:0H-events_highpri]
     45       2 S    [cpuhp/5]
     46       2 S    [idle_inject/5]
     47       2 S    [migration/5]
     48       2 S    [ksoftirqd/5]
     50       2 I<   [kworker/5:0H-events_highpri]
     51       2 S    [cpuhp/6]
     52       2 S    [idle_inject/6]
     53       2 S    [migration/6]
     54       2 S    [ksoftirqd/6]
     55       2 I    [kworker/6:0-rcu_par_gp]
     56       2 I<   [kworker/6:0H-events_highpri]
     57       2 S    [cpuhp/7]
     58       2 S    [idle_inject/7]
     59       2 S    [migration/7]
     60       2 S    [ksoftirqd/7]
     62       2 I<   [kworker/7:0H-kblockd]
     63       2 S    [cpuhp/8]
     64       2 S    [idle_inject/8]
     65       2 S    [migration/8]
     66       2 S    [ksoftirqd/8]
     68       2 I<   [kworker/8:0H-events_highpri]
     69       2 S    [cpuhp/9]
     70       2 S    [idle_inject/9]
     71       2 S    [migration/9]
     72       2 S    [ksoftirqd/9]
     73       2 I    [kworker/9:0-events]
     74       2 I<   [kworker/9:0H-events_highpri]
     75       2 S    [cpuhp/10]
     76       2 S    [idle_inject/10]
     77       2 S    [migration/10]
     78       2 S    [ksoftirqd/10]
     80       2 I<   [kworker/10:0H-events_highpri]
     81       2 S    [cpuhp/11]
     82       2 S    [idle_inject/11]
     83       2 S    [migration/11]
     84       2 S    [ksoftirqd/11]
     86       2 I<   [kworker/11:0H-events_highpri]
     89       2 S    [kdevtmpfs]
     90       2 I<   [kworker/R-inet_]
     92       2 S    [kauditd]
     96       2 S    [khungtaskd]
     98       2 S    [oom_reaper]
     99       2 I<   [kworker/R-write]
    100       2 S    [kcompactd0]
    101       2 SN   [ksmd]
    102       2 SN   [khugepaged]
    103       2 I<   [kworker/R-kinte]
    104       2 I<   [kworker/R-kbloc]
    105       2 I<   [kworker/R-blkcg]
    106       2 S    [irq/9-acpi]
    109       2 I<   [kworker/R-tpm_d]
    110       2 I<   [kworker/R-ata_s]
    111       2 I<   [kworker/R-md]
    112       2 I<   [kworker/R-md_bi]
    113       2 I<   [kworker/R-edac-]
    115       2 I<   [kworker/R-devfr]
    116       2 S    [watchdogd]
    117       2 I<   [kworker/2:1H-kblockd]
    119       2 S    [kswapd0]
    120       2 S    [ecryptfs-kthread]
    121       2 I<   [kworker/R-kthro]
    126       2 I    [kworker/9:1-events]
    127       2 I    [kworker/10:1-mm_percpu_wq]
    129       2 I<   [kworker/R-acpi_]
    132       2 I<   [kworker/R-mld]
    133       2 I<   [kworker/R-ipv6_]
    135       2 I<   [kworker/3:1H-kblockd]
    143       2 I<   [kworker/R-kstrp]
    145       2 I<   [kworker/u67:0]
    152       2 I<   [kworker/R-crypt]
    162       2 I<   [kworker/R-charg]
    191       2 I<   [kworker/9:1H-kblockd]
    194       2 I<   [kworker/5:1H-kblockd]
    195       2 I<   [kworker/6:1H-kblockd]
    206       2 I<   [kworker/8:1H-kblockd]
    208       2 I<   [kworker/4:1H-kblockd]
    209       2 I<   [kworker/10:1H-kblockd]
    213       2 I<   [kworker/11:1H-kblockd]
    221       2 I<   [kworker/1:1H-kblockd]
    227       2 I<   [kworker/0:1H-kblockd]
    228       2 I<   [kworker/7:1H]
    230       2 S    [scsi_eh_0]
    231       2 I<   [kworker/R-scsi_]
    232       2 S    [scsi_eh_1]
    233       2 I<   [kworker/R-scsi_]
    234       2 S    [scsi_eh_2]
    235       2 I<   [kworker/R-scsi_]
    237       2 S    [scsi_eh_3]
    238       2 I<   [kworker/R-scsi_]
    239       2 S    [scsi_eh_4]
    240       2 I<   [kworker/R-scsi_]
    241       2 S    [scsi_eh_5]
    242       2 I<   [kworker/R-scsi_]
    243       2 S    [scsi_eh_6]
    244       2 I<   [kworker/R-scsi_]
    245       2 S    [scsi_eh_7]
    246       2 I<   [kworker/R-scsi_]
    253       2 S    [scsi_eh_8]
    255       2 I<   [kworker/R-scsi_]
    256       2 I    [kworker/u66:9-flush-8:16]
    257       2 S    [scsi_eh_9]
    258       2 I<   [kworker/R-scsi_]
    262       2 S    [scsi_eh_10]
    263       2 I<   [kworker/R-scsi_]
    298       2 S    [scsi_eh_11]
    299       2 I<   [kworker/R-scsi_]
    300       2 S    [usb-storage]
    302       2 I<   [kworker/R-uas]
    322       2 I    [kworker/11:2-events]
    333       2 I<   [kworker/R-raid5]
    383       2 S    [jbd2/sdb3-8]
    384       2 I<   [kworker/R-ext4-]
    445       1 S<s  /usr/lib/systemd/systemd-journald
    476       2 I<   [kworker/R-rpcio]
    477       2 I<   [kworker/R-xprti]
    480       2 I<   [kworker/R-nvme-]
    481       2 I<   [kworker/R-nvme-]
    482       2 I<   [kworker/R-nvme-]
    483       2 I<   [kworker/R-nvme-]
    530       1 Ss   /usr/lib/systemd/systemd-udevd
    533       2 S    [psimon]
    902       2 S    [jbd2/sda1-8]
    903       2 I<   [kworker/R-ext4-]
    908       2 I<   [kworker/R-amdgp]
    910       2 S    [jbd2/sdb4-8]
    911       2 I<   [kworker/R-ext4-]
    915       2 I<   [kworker/R-ttm]
    916       2 I<   [kworker/R-amdgp]
    917       2 I<   [kworker/R-amdgp]
    918       2 I<   [kworker/R-amdgp]
    919       2 I<   [kworker/R-dm_vb]
    920       2 S    [card1-crtc0]
    921       2 S    [card1-crtc1]
    922       2 S    [card1-crtc2]
    923       2 S    [card1-crtc3]
    935       2 S<   [spl_system_task]
    936       2 S<   [spl_delay_taskq]
    937       2 S<   [spl_dynamic_tas]
    938       2 S<   [spl_kmem_cache]
    940       2 S<   [zvol]
    941       2 S    [arc_prune]
    942       2 S    [arc_evict]
    943       2 SN   [arc_reap]
    944       2 S    [dbu_evict]
    945       2 SN   [dbuf_evict]
    946       2 SN   [z_vdev_file]
    947       2 S    [l2arc_feed]
   1053       1 Ss   /sbin/rpcbind -f -w
   1063       1 Ss   /usr/lib/systemd/systemd-resolved
   1064       1 Ssl  /usr/lib/systemd/systemd-timesyncd
   1078       1 Ss   /usr/sbin/blkmapd
   1110       1 Ss   /usr/sbin/nfsdcld
   1146       1 Ssl  /usr/libexec/accounts-daemon
   1151       1 Ss   avahi-daemon: running [a108pc13.local]
   1154       1 Ss   @dbus-daemon --system --address=systemd: --nofork --nopidfile --systemd-activation --syslog-only
   1157       1 Ss   /usr/sbin/fsidd
   1161       1 Ssl  /usr/sbin/irqbalance
   1197       1 Ssl  /usr/lib/polkit-1/polkitd --no-debug
   1198       1 Ssl  /usr/libexec/power-profiles-daemon
   1204       1 Ssl  /usr/bin/prometheus-node-exporter
   1213       1 Ss   /usr/sbin/smartd -n
   1220       1 Ssl  /usr/libexec/switcheroo-control
   1221       1 Ss   /usr/lib/systemd/systemd-logind
   1222       1 Ss   /usr/lib/systemd/systemd-machined
   1225       1 Ssl  /usr/bin/touchegg --daemon
   1231       1 Ssl  /usr/libexec/udisks2/udisksd
   1236       1 Ss   /usr/sbin/virtlockd
   1237       1 Ss   /usr/sbin/virtlogd
   1239       1 Ssl  zed -F
   1248    1151 S    avahi-daemon: chroot helper
   1252       1 Ssl  /usr/sbin/rsyslogd -n -iNONE
   1818       1 Ssl  /usr/sbin/NetworkManager --no-daemon
   1819       1 Ss   /usr/sbin/wpa_supplicant -u -s -O DIR=/run/wpa_supplicant GROUP=netdev
   1845       1 Ssl  /usr/sbin/ModemManager
   1968       2 S    [psimon]
   1977       1 Ss   /usr/sbin/cupsd -l
   1994       1 Ssl  /usr/bin/containerd
   2010       1 Ssl  /usr/libexec/colord
   2022       1 Ss   /usr/sbin/winbindd --foreground --no-process-group
   2033    2022 S    winbindd: domain child [A108PC13]
   2092       1 Ssl  /usr/sbin/cups-browsed
   2093       1 Ssl  /usr/bin/dockerd -H fd:// --containerd=/run/containerd/containerd.sock
   2094       1 Ss   socat -T 60 openssl-connect:server:789,cafile=/etc/epoptes/server.crt,openssl-no-sni,openssl-commonname="",interval=60,forever EXEC:bash -c \"exec -a epoptes-client sh\"
   2104       1 Ss   /usr/sbin/cron -f -P
   2108       1 Ss   /usr/sbin/rpc.idmapd
   2119       1 Ss   /usr/sbin/rpc.statd
   2125       1 Ss   /usr/sbin/rpc.mountd
   2161       1 Ss   /usr/sbin/kerneloops --test
   2165       1 SLsl /usr/sbin/lightdm
   2166       1 Ss   /usr/sbin/kerneloops
   2171       2 I<   [kworker/R-ipmi-]
   2176       2 I    [lockd]
   2195       2 I<   [kworker/R-iprt-]
   2199       2 S    [iprt-VBoxTscThread]
   2216    2165 Ssl+ /usr/lib/xorg/Xorg -core :0 -seat seat0 -auth /var/run/lightdm/root/:0 -nolisten tcp vt7 -novtswitch
   2219       1 Ss+  /sbin/agetty -o -p -- \u --noclear - linux
   2223       2 I    [nfsd]
   2224       2 I    [nfsd]
   2225       2 I    [nfsd]
   2226       2 I    [nfsd]
   2227       2 I    [nfsd]
   2228       2 I    [nfsd]
   2229       2 I    [nfsd]
   2230       2 I    [nfsd]
   2276       1 Ss   /usr/sbin/apache2 -k start
   2293    2276 S    /usr/sbin/apache2 -k start
   2294    2276 S    /usr/sbin/apache2 -k start
   2295    2276 S    /usr/sbin/apache2 -k start
   2297    2276 S    /usr/sbin/apache2 -k start
   2298    2276 S    /usr/sbin/apache2 -k start
   2333       1 S    /usr/sbin/dnsmasq --conf-file=/var/lib/libvirt/dnsmasq/default.conf --leasefile-ro --dhcp-script=/usr/lib/libvirt/libvirt_leaseshelper
   2334    2333 S    /usr/sbin/dnsmasq --conf-file=/var/lib/libvirt/dnsmasq/default.conf --leasefile-ro --dhcp-script=/usr/lib/libvirt/libvirt_leaseshelper
   2421       1 SNsl /usr/libexec/rtkit-daemon
   2620       1 Ssl  /usr/libexec/upowerd
   3128       1 Sl   /usr/bin/containerd-shim-runc-v2 -namespace moby -id 5df934926b0f1fcae370ac4d81a091d31014ed4fc6bbd199b02e9a4020afa491 -address /run/containerd/containerd.sock
   3150    3128 Ss   apache2 -DFOREGROUND
   3225    2093 Sl   /usr/bin/docker-proxy -proto tcp -host-ip 0.0.0.0 -host-port 8000 -container-ip 172.24.0.2 -container-port 80 -use-listen-fd
   3231    2093 Sl   /usr/bin/docker-proxy -proto tcp -host-ip :: -host-port 8000 -container-ip 172.24.0.2 -container-port 80 -use-listen-fd
   3282    3150 S    apache2 -DFOREGROUND
   3283    3150 S    apache2 -DFOREGROUND
   3284    3150 S    apache2 -DFOREGROUND
   3285    3150 S    apache2 -DFOREGROUND
   3286    3150 S    apache2 -DFOREGROUND
   3311    2165 Sl   lightdm --session-child 13 24
   3325       1 Ss   /usr/lib/systemd/systemd --user
   3326    3325 S    (sd-pam)
   3335    3325 S<sl /usr/bin/pipewire
   3336    3325 Ssl  /usr/bin/pipewire -c filter-chain.conf
   3338    3325 Ss   /usr/bin/python3 /usr/bin/powerline-daemon --foreground
   3339    3325 S<sl /usr/bin/wireplumber
   3340    3325 S<sl /usr/bin/pipewire-pulse
   3341    3325 SLsl /usr/bin/gnome-keyring-daemon --foreground --components=pkcs11,secrets --control-directory=/run/user/1001/keyring
   3342    3325 Ss   /usr/bin/dbus-daemon --session --address=systemd: --nofork --nopidfile --systemd-activation --syslog-only
   3367    3311 Ssl  /usr/lib/x86_64-linux-gnu/cinnamon-session-binary --session cinnamon
   3580    3367 Sl   /usr/bin/csd-automount
   3581    3367 Sl   /usr/bin/csd-settings-remap
   3582    3367 Sl   /usr/bin/csd-power
   3584    3367 Sl   /usr/bin/csd-housekeeping
   3585    3367 Sl   /usr/bin/csd-wacom
   3586    3367 Sl   /usr/bin/csd-color
   3590    3367 Sl   /usr/bin/csd-background
   3591    3367 Sl   /usr/libexec/at-spi-bus-launcher --launch-immediately
   3594    3367 Sl   /usr/bin/csd-screensaver-proxy
   3596    3367 Sl   /usr/bin/csd-xsettings
   3602    3367 Sl   /usr/bin/csd-keyboard
   3603    3591 S    /usr/bin/dbus-daemon --config-file=/usr/share/defaults/at-spi2/accessibility.conf --nofork --print-address 11 --address=unix:path=/run/user/1001/at-spi/bus_0
   3604    3367 Sl   /usr/bin/csd-media-keys
   3605    3367 Sl   /usr/bin/csd-clipboard
   3606    3367 Sl   /usr/bin/csd-print-notifications
   3607    3367 Sl   /usr/bin/csd-a11y-settings
   3610    3325 Ssl  /usr/libexec/dconf-service
   3651    3325 Ssl  /usr/libexec/gvfsd
   3664    3325 Sl   /usr/libexec/gvfsd-fuse /run/user/1001/gvfs -f
   3677       1 Sl   /usr/libexec/csd-printer
   3686       1 Sl   /usr/libexec/at-spi2-registryd --use-gnome-session
   3704    3325 Ssl  /usr/libexec/gvfs-udisks2-volume-monitor
   3714    3325 Ssl  /usr/libexec/gvfs-gphoto2-volume-monitor
   3719    3325 Ssl  /usr/libexec/gvfs-goa-volume-monitor
   3724    3325 Sl   /usr/libexec/goa-daemon
   3732    3325 Sl   /usr/libexec/goa-identity-service
   3738    3325 Ssl  /usr/libexec/gvfs-mtp-volume-monitor
   3744    3325 Ssl  /usr/libexec/gvfs-afc-volume-monitor
   3751    3367 Sl   cinnamon-launcher
   3755    3751 Sl   cinnamon --replace
   3787    3367 Sl   /usr/lib/x86_64-linux-gnu/xapps/xapp-sn-watcher
   3810    3367 Sl   /usr/libexec/geoclue-2.0/demos/agent
   3819    3367 S    socat -T 60 openssl-connect:server:789,cafile=/etc/epoptes/server.crt,openssl-no-sni,openssl-commonname="",interval=60,forever EXEC:bash -c \"exec -a epoptes-client sh\"
   3824    3367 Sl   /usr/bin/nemo-desktop
   3832    3367 Sl   /usr/libexec/evolution-data-server/evolution-alarm-notify
   3835    3367 Sl   cinnamon-killer-daemon
   3836    3367 Sl   /usr/bin/python3 /usr/bin/blueman-applet
   3927    3325 Ss   /usr/libexec/bluetooth/obexd
   3934    3325 Ssl  /usr/libexec/evolution-source-registry
   3945    3325 Ssl  /usr/libexec/evolution-calendar-factory
   3960    3325 Ssl  /usr/libexec/evolution-addressbook-factory
   3989    3651 Sl   /usr/libexec/gvfsd-trash --spawner :1.32 /org/gtk/gvfs/exec_spaw/0
   4002    3325 Ssl  /usr/libexec/gvfsd-metadata
   4058       1 Sl   mintUpdate
   4149    3367 S    /usr/bin/python3 /usr/share/system-config-printer/applet.py
   4258       1 Sl   mintreport-tray
   4289    3325 Ssl  /usr/libexec/xdg-desktop-portal
   4294    3325 Ssl  /usr/libexec/xdg-document-portal
   4298    3325 Ssl  /usr/libexec/xdg-permission-store
   4305    4294 Ss   fusermount3 -o rw,nosuid,nodev,fsname=portal,auto_unmount,subtype=portal -- /run/user/1001/doc
   4310    3325 Ssl  /usr/libexec/xdg-desktop-portal-xapp
   4329    3325 Ssl  /usr/libexec/xdg-desktop-portal-gtk
   4544    3755 Sl   /usr/lib/firefox/firefox --private-window
   4550    4544 Sl   /usr/lib/firefox/crashhelper 4544 9 /tmp/ 10 12
   4638    4544 Sl   /usr/lib/firefox/firefox-bin -contentproc -parentBuildID 20250522210034 -prefsHandle 0:41684 -prefMapHandle 1:272655 -sandboxReporter 2 -chrootClient 3 -ipcHandle 4 -initialChannelId {35b2ccac-8eb7-41c2-a865-1ad83451e
   4665    4544 Sl   /usr/lib/firefox/firefox-bin -contentproc -isForBrowser -prefsHandle 0:41733 -prefMapHandle 1:272655 -jsInitHandle 2:245828 -parentBuildID 20250522210034 -sandboxReporter 3 -chrootClient 4 -ipcHandle 5 -initialChannel
   4671    4544 Sl   /usr/lib/firefox/firefox-bin -contentproc -parentBuildID 20250522210034 -prefsHandle 0:41733 -prefMapHandle 1:272655 -sandboxReporter 2 -chrootClient 3 -ipcHandle 4 -initialChannelId {e0eda99c-b6df-437f-8a89-39aa9c567
   4734    4544 Sl   /usr/lib/firefox/firefox-bin -contentproc -isForBrowser -prefsHandle 0:50622 -prefMapHandle 1:272655 -jsInitHandle 2:245828 -parentBuildID 20250522210034 -sandboxReporter 3 -chrootClient 4 -ipcHandle 5 -initialChannel
   4783    4544 Sl   /usr/lib/firefox/firefox-bin -contentproc -parentBuildID 20250522210034 -sandboxingKind 0 -prefsHandle 0:50663 -prefMapHandle 1:272655 -sandboxReporter 2 -chrootClient 3 -ipcHandle 4 -initialChannelId {0c8d566f-9592-4
   4791    4544 Sl   /usr/lib/firefox/firefox-bin -contentproc -isForBrowser -prefsHandle 0:40873 -prefMapHandle 1:272655 -jsInitHandle 2:245828 -parentBuildID 20250522210034 -sandboxReporter 3 -chrootClient 4 -ipcHandle 5 -initialChannel
   4795    4544 Sl   /usr/lib/firefox/firefox-bin -contentproc -isForBrowser -prefsHandle 0:40873 -prefMapHandle 1:272655 -jsInitHandle 2:245828 -parentBuildID 20250522210034 -sandboxReporter 3 -chrootClient 4 -ipcHandle 5 -initialChannel
   4920    4544 Sl   /usr/lib/firefox/firefox-bin -contentproc -isForBrowser -prefsHandle 0:41247 -prefMapHandle 1:272655 -jsInitHandle 2:245828 -parentBuildID 20250522210034 -sandboxReporter 3 -chrootClient 4 -ipcHandle 5 -initialChannel
   5114    4544 Sl   /usr/lib/firefox/firefox-bin -contentproc -isForBrowser -prefsHandle 0:41247 -prefMapHandle 1:272655 -jsInitHandle 2:245828 -parentBuildID 20250522210034 -sandboxReporter 3 -chrootClient 4 -ipcHandle 5 -initialChannel
   5217    4544 Sl   /usr/lib/firefox/firefox-bin -contentproc -isForBrowser -prefsHandle 0:41288 -prefMapHandle 1:272655 -jsInitHandle 2:245828 -parentBuildID 20250522210034 -sandboxReporter 3 -chrootClient 4 -ipcHandle 5 -initialChannel
   5273       2 I    [kworker/11:0-inet_frag_wq]
   5354    3755 SLl  /usr/share/code/code
   5358    5354 S    /usr/share/code/code --type=zygote --no-zygote-sandbox
   5359    5354 S    /usr/share/code/code --type=zygote
   5361    5359 S    /usr/share/code/code --type=zygote
   5380       1 Sl   /usr/share/code/chrome_crashpad_handler --monitor-self-annotation=ptype=crashpad-handler --no-rate-limit --database=/home/dam/.config/Code/Crashpad --url=appcenter://code?aid=fba07a4d-84bd-4fc8-a125-9640fc8ce171&uid=f
   5396    5358 Sl   /usr/share/code/code --type=gpu-process --crashpad-handler-pid=5380 --enable-crash-reporter=5cc31211-7a24-4340-a84b-f5c0e9b9b4e5,no_channel --user-data-dir=/home/dam/.config/Code --gpu-preferences=UAAAAAAAAAAgAAAEAAAA
   5401    5354 Sl   /usr/share/code/code --type=utility --utility-sub-type=network.mojom.NetworkService --lang=es --service-sandbox-type=none --crashpad-handler-pid=5380 --enable-crash-reporter=5cc31211-7a24-4340-a84b-f5c0e9b9b4e5,no_cha
   5424    5361 Sl   /usr/share/code/code --type=renderer --crashpad-handler-pid=5380 --enable-crash-reporter=5cc31211-7a24-4340-a84b-f5c0e9b9b4e5,no_channel --user-data-dir=/home/dam/.config/Code --standard-schemes=vscode-webview,vscode-
   5442    5361 Sl   /usr/share/code/code --type=renderer --crashpad-handler-pid=5380 --enable-crash-reporter=5cc31211-7a24-4340-a84b-f5c0e9b9b4e5,no_channel --user-data-dir=/home/dam/.config/Code --standard-schemes=vscode-webview,vscode-
   5694    5354 Sl   /usr/share/code/code --type=utility --utility-sub-type=node.mojom.NodeService --lang=es --service-sandbox-type=none --dns-result-order=ipv4first --inspect-port=0 --crashpad-handler-pid=5380 --enable-crash-reporter=5cc
   5695    5354 Sl   /usr/share/code/code --type=utility --utility-sub-type=node.mojom.NodeService --lang=es --service-sandbox-type=none --dns-result-order=ipv4first --inspect-port=0 --crashpad-handler-pid=5380 --enable-crash-reporter=5cc
   5730    5695 Sl   /usr/share/code/code /usr/share/code/resources/app/extensions/markdown-language-features/dist/serverWorkerMain --node-ipc --clientProcessId=5695
   5731    5694 Sl   /usr/share/code/code /home/dam/.vscode/extensions/ms-azuretools.vscode-containers-2.1.0/dist/dockerfile-language-server-nodejs/lib/server.js --node-ipc --node-ipc --clientProcessId=5694
   5738    5354 Sl   /usr/share/code/code --type=utility --utility-sub-type=node.mojom.NodeService --lang=es --service-sandbox-type=none --crashpad-handler-pid=5380 --enable-crash-reporter=5cc31211-7a24-4340-a84b-f5c0e9b9b4e5,no_channel -
   5739    5354 Sl   /usr/share/code/code --type=utility --utility-sub-type=node.mojom.NodeService --lang=es --service-sandbox-type=none --crashpad-handler-pid=5380 --enable-crash-reporter=5cc31211-7a24-4340-a84b-f5c0e9b9b4e5,no_channel -
   5741    5694 Sl   /usr/share/code/code /home/dam/.vscode/extensions/ms-azuretools.vscode-containers-2.1.0/dist/compose-language-service/lib/server.js --node-ipc --node-ipc --clientProcessId=5694
   5793    5354 Sl   /usr/share/code/code --type=utility --utility-sub-type=node.mojom.NodeService --lang=es --service-sandbox-type=none --crashpad-handler-pid=5380 --enable-crash-reporter=5cc31211-7a24-4340-a84b-f5c0e9b9b4e5,no_channel -
   5795    5354 Sl   /usr/share/code/code --type=utility --utility-sub-type=node.mojom.NodeService --lang=es --service-sandbox-type=none --crashpad-handler-pid=5380 --enable-crash-reporter=5cc31211-7a24-4340-a84b-f5c0e9b9b4e5,no_channel -
   5806    5361 Sl   /usr/share/code/code --type=renderer --crashpad-handler-pid=5380 --enable-crash-reporter=5cc31211-7a24-4340-a84b-f5c0e9b9b4e5,no_channel --user-data-dir=/home/dam/.config/Code --standard-schemes=vscode-webview,vscode-
   5856       2 I    [kworker/4:1-mm_percpu_wq]
   5870    5795 Ss   /usr/bin/bash --init-file /usr/share/code/resources/app/out/vs/workbench/contrib/terminal/common/scripts/shellIntegration-bash.sh
   6530    5695 Sl   /usr/share/code/code /home/dam/.vscode/extensions/mtxr.sqltools-0.28.5/dist/languageserver.js --node-ipc --clientProcessId=5695
   6538    5694 Sl   /usr/share/code/code /home/dam/.vscode/extensions/mtxr.sqltools-0.28.5/dist/languageserver.js --node-ipc --clientProcessId=5694
   6564    5695 S    /bin/bash /home/dam/.vscode/extensions/oracle.oracle-java-24.1.0/nbcode/bin/../platform/lib/nbexec.sh --jdkhome  --clusters /home/dam/.vscode/extensions/oracle.oracle-java-24.1.0/nbcode/platform:/home/dam/.vscode/exte
   6577    5694 Sl   /home/dam/.vscode/extensions/devsense.intelli-php-vscode-0.12.17700-linux-x64/out/server/intelliphp.ls
   6606    5694 S    PHP Language Server
   6687    6606 S    sh -c '/usr/bin/php8.3' '-n' '-c' '/tmp/qEknx4' '/home/dam/.vscode/extensions/zobo.php-intellisense-1.3.3/vendor/felixfbecker/language-server/bin/php-language-server.php' '--tcp=127.0.0.1:45553' '--memory-limit=4095M'
   6688    5695 Sl   /usr/share/code/code /usr/share/code/resources/app/extensions/json-language-features/server/dist/node/jsonServerMain --node-ipc --clientProcessId=5695
   6689    6687 S    PHP Language Server
   6748    5694 Sl   /usr/share/code/code /usr/share/code/resources/app/extensions/json-language-features/server/dist/node/jsonServerMain --node-ipc --clientProcessId=5694
   6784    6564 Sl   /usr/lib/jvm/java-21-openjdk-amd64/bin/java -Djdk.home=/usr/lib/jvm/java-21-openjdk-amd64 -classpath /home/dam/.vscode/extensions/oracle.oracle-java-24.1.0/nbcode/platform/lib/boot.jar:/home/dam/.vscode/extensions/ora
   6848    5694 Sl   /home/dam/.vscode/extensions/devsense.phptools-vscode-1.60.17873-linux-x64/out/server/devsense.php.ls --vscode
   6993    5361 Sl   /usr/share/code/code --type=renderer --crashpad-handler-pid=5380 --enable-crash-reporter=5cc31211-7a24-4340-a84b-f5c0e9b9b4e5,no_channel --user-data-dir=/home/dam/.config/Code --standard-schemes=vscode-webview,vscode-
   7144    5694 Sl   /usr/share/code/code /usr/share/code/resources/app/extensions/html-language-features/server/dist/node/htmlServerMain --node-ipc --clientProcessId=5694
   7151    5361 Sl   /usr/share/code/code --type=renderer --crashpad-handler-pid=5380 --enable-crash-reporter=5cc31211-7a24-4340-a84b-f5c0e9b9b4e5,no_channel --user-data-dir=/home/dam/.config/Code --standard-schemes=vscode-webview,vscode-
   7167    5361 Sl   /usr/share/code/code --type=renderer --crashpad-handler-pid=5380 --enable-crash-reporter=5cc31211-7a24-4340-a84b-f5c0e9b9b4e5,no_channel --user-data-dir=/home/dam/.config/Code --standard-schemes=vscode-webview,vscode-
   7545    4544 Sl   /usr/lib/firefox/firefox-bin -contentproc -isForBrowser -prefsHandle 0:41288 -prefMapHandle 1:272655 -jsInitHandle 2:245828 -parentBuildID 20250522210034 -sandboxReporter 3 -chrootClient 4 -ipcHandle 5 -initialChannel
  13043       2 I    [kworker/6:2-mm_percpu_wq]
  13049       1 Ssl  /usr/libexec/fwupd/fwupd
  13050       2 I    [kworker/5:0-events]
  15317    3651 Sl   /usr/libexec/gvfsd-http --spawner :1.32 /org/gtk/gvfs/exec_spaw/1
  16282    5870 Sl+  docker compose up --build
  16305   16282 Sl+  /usr/libexec/docker/cli-plugins/docker-compose compose up --build
  16364       2 I    [kworker/3:0-mm_percpu_wq]
  16414       1 Sl   /usr/bin/containerd-shim-runc-v2 -namespace moby -id 107b8c3bd2e852dff4919ce77d6b7fea316e0f3709af5428729ed2d9ec5abb78 -address /run/containerd/containerd.sock
  16438   16414 Ss   apache2 -DFOREGROUND
  16513    2093 Sl   /usr/bin/docker-proxy -proto tcp -host-ip 0.0.0.0 -host-port 8080 -container-ip 172.25.0.2 -container-port 80 -use-listen-fd
  16520    2093 Sl   /usr/bin/docker-proxy -proto tcp -host-ip :: -host-port 8080 -container-ip 172.25.0.2 -container-port 80 -use-listen-fd
  16551   16438 S    apache2 -DFOREGROUND
  16552   16438 S    apache2 -DFOREGROUND
  16553   16438 S    apache2 -DFOREGROUND
  16554   16438 S    apache2 -DFOREGROUND
  16555   16438 S    apache2 -DFOREGROUND
  16775   16438 S    apache2 -DFOREGROUND
  17106    4544 Sl   /usr/lib/firefox/firefox-bin -contentproc -isForBrowser -prefsHandle 0:41288 -prefMapHandle 1:272655 -jsInitHandle 2:245828 -parentBuildID 20250522210034 -sandboxReporter 3 -chrootClient 4 -ipcHandle 5 -initialChannel
  17921       2 I    [kworker/5:3-mm_percpu_wq]
  19095       2 I    [kworker/u65:0-events_power_efficient]
  19210       2 I    [kworker/0:1-mm_percpu_wq]
  19537       2 I<   [kworker/u68:4-ttm]
  19838       2 I    [kworker/8:2-mm_percpu_wq]
  20828       2 I    [kworker/7:1-mm_percpu_wq]
  21154    4544 Sl   /usr/lib/firefox/firefox-bin -contentproc -isForBrowser -prefsHandle 0:41288 -prefMapHandle 1:272655 -jsInitHandle 2:245828 -parentBuildID 20250522210034 -sandboxReporter 3 -chrootClient 4 -ipcHandle 5 -initialChannel
  21200    4544 Sl   /usr/lib/firefox/firefox-bin -contentproc -isForBrowser -prefsHandle 0:41288 -prefMapHandle 1:272655 -jsInitHandle 2:245828 -parentBuildID 20250522210034 -sandboxReporter 3 -chrootClient 4 -ipcHandle 5 -initialChannel
  21280    4544 Sl   /usr/lib/firefox/firefox-bin -contentproc -isForBrowser -prefsHandle 0:41288 -prefMapHandle 1:272655 -jsInitHandle 2:245828 -parentBuildID 20250522210034 -sandboxReporter 3 -chrootClient 4 -ipcHandle 5 -initialChannel
  21442       2 I    [kworker/4:0]
  21609       2 I    [kworker/1:2-mm_percpu_wq]
  26162       2 D    [kworker/u66:0+events_unbound]
  26276       2 I    [kworker/8:1-cgroup_destroy]
  29587       2 I<   [kworker/u69:0]
  30308       2 I    [kworker/u64:2-gfx_low]
  31139       2 I    [kworker/u65:2-events_unbound]
  31165       2 I    [kworker/10:0-rcu_par_gp]
  31243       2 I    [kworker/2:1-events]
  31523       2 I    [kworker/u65:3-events_unbound]
  32264       2 I<   [kworker/u68:0-ttm]
  32531    3325 Ssl  /usr/libexec/gnome-terminal-server
  32540   32531 Ss   bash
  34479       2 I    [kworker/u64:1-sdma0]
  34975       2 I    [kworker/u66:1-events_unbound]
  35328       2 I    [kworker/0:0-rcu_par_gp]
  35334       2 I    [kworker/2:2-cgroup_destroy]
  35622       2 I<   [kworker/u69:2-ttm]
  37861       2 I    [kworker/7:0-events]
  38143       2 I    [kworker/3:1]
  38772       2 I    [kworker/u64:0-gfx_low]
  39190       2 I    [kworker/u66:2-events_power_efficient]
  39983   32540 Sl   gnome-calculator
  41311       2 I    [kworker/5:1]
  41312       2 I    [kworker/10:2-inet_frag_wq]
  41876   32540 S    xeyes
  42690       2 I    [kworker/u65:1]
  42784   32540 R+   ps -eo pid,ppid,stat,cmd

```

- #### Ejecuta watch -n 1 ps -e y observa cómo cambian los procesos en tiempo real.
`watch -n 1 ps -e`

```bash
PID TTY          TIME CMD
      1 ?        00:00:01 systemd
      2 ?        00:00:00 kthreadd
      3 ?        00:00:00 pool_workqueue_release
      4 ?        00:00:00 kworker/R-rcu_g
      5 ?        00:00:00 kworker/R-rcu_p
      6 ?        00:00:00 kworker/R-slub_
      7 ?        00:00:00 kworker/R-netns
     10 ?        00:00:00 kworker/0:0H-events_highpri
     12 ?        00:00:00 kworker/R-mm_pe
     13 ?        00:00:00 rcu_tasks_kthread
     14 ?        00:00:00 rcu_tasks_rude_kthread
     15 ?        00:00:00 rcu_tasks_trace_kthread
     16 ?        00:00:00 ksoftirqd/0
     17 ?        00:00:03 rcu_preempt
     18 ?        00:00:00 migration/0
     19 ?        00:00:00 idle_inject/0
     20 ?        00:00:00 cpuhp/0
     21 ?        00:00:00 cpuhp/1
     22 ?        00:00:00 idle_inject/1
     23 ?        00:00:00 migration/1
     24 ?        00:00:00 ksoftirqd/1
     25 ?        00:00:00 kworker/1:0-events
     26 ?        00:00:00 kworker/1:0H-events_highpri
     27 ?        00:00:00 cpuhp/2
     28 ?        00:00:00 idle_inject/2
     29 ?        00:00:00 migration/2
     30 ?        00:00:00 ksoftirqd/2
     32 ?        00:00:00 kworker/2:0H-events_highpri
     33 ?        00:00:00 cpuhp/3
     34 ?        00:00:00 idle_inject/3
     35 ?        00:00:00 migration/3
     36 ?        00:00:00 ksoftirqd/3
     38 ?        00:00:00 kworker/3:0H-events_highpri
     39 ?        00:00:00 cpuhp/4
     40 ?        00:00:00 idle_inject/4
     41 ?        00:00:00 migration/4
     42 ?        00:00:00 ksoftirqd/4
     44 ?        00:00:00 kworker/4:0H-events_highpri
     45 ?        00:00:00 cpuhp/5
     46 ?        00:00:00 idle_inject/5
     47 ?        00:00:00 migration/5
     48 ?        00:00:00 ksoftirqd/5
     50 ?        00:00:00 kworker/5:0H-events_highpri
     51 ?        00:00:00 cpuhp/6
     52 ?        00:00:00 idle_inject/6
     53 ?        00:00:00 migration/6
     54 ?        00:00:00 ksoftirqd/6
     55 ?        00:00:00 kworker/6:0-rcu_par_gp
     56 ?        00:00:00 kworker/6:0H-events_highpri
     57 ?        00:00:00 cpuhp/7
     58 ?        00:00:00 idle_inject/7
     59 ?        00:00:00 migration/7
     60 ?        00:00:00 ksoftirqd/7
     62 ?        00:00:00 kworker/7:0H-kblockd
     63 ?        00:00:00 cpuhp/8
     64 ?        00:00:00 idle_inject/8
```

- #### Explica la diferencia entre ejecutar un proceso con & y con nohup.
Ejecutar con `&` lanza proceso en segundo plano; `nohup` permite que siga tras cerrar sesión.

- #### Usa ulimit -a para ver los límites de recursos de procesos en tu sistema.
`ulimit -a`

```bash
real-time non-blocking time  (microseconds, -R) unlimited
core file size              (blocks, -c) 0
data seg size               (kbytes, -d) unlimited
scheduling priority                 (-e) 0
file size                   (blocks, -f) unlimited
pending signals                     (-i) 125404
max locked memory           (kbytes, -l) 4026020
max memory size             (kbytes, -m) unlimited
open files                          (-n) 1024
pipe size                (512 bytes, -p) 8
POSIX message queues         (bytes, -q) 819200
real-time priority                  (-r) 0
stack size                  (kbytes, -s) 8192
cpu time                   (seconds, -t) unlimited
max user processes                  (-u) 125404
virtual memory              (kbytes, -v) unlimited
file locks                          (-x) unlimited
```

---
#### Ejercicio realizado por César Domínguez Romero 
