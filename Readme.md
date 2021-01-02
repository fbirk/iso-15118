# Implementierung des ISO-15118 für Devolo_dLan_GreenPHY eval Boards

## Inhalt

1. [Setup](#setup)  
 1.1 [Eval-Boards](#eval-boards)  
 1.2 [Host-System](#host-system)  
    1.3 [RISE V2G Open Source
    Implementierung](#rise-v2g-open-source-implementierung)  
2. [Kommunikation zwischen Host und Eval
   Boards](#kommunikation-zwischen-host-und-eval-boards)  
   2.1 [Erste Verbindungs und
   Statusabfrage](#erste-verbindungs-und-statusabfrage)  
   2.2 [NMK neu setzen](#nmk-neu-setzen)
3. [Websocket Interface Wallbox-SECC](#websocket-interface-wallbox-secc)

## Setup

Anbei sind die Schritte beschrieben, um die Testumgebung mit den beiden GreenPHY
Eval Boards zum Laufen zu bekommen.

### Eval-Boards

Anschluss der Kabel folgendem Bild bzw. der Anleitung entnehmen (links PEV,
rechts EVSE):
![image](setup_evalboards_3.jpg)

* Stromversorgung über Micro-USB Anschluss (hier über USB-Hub betrieben)
* es gibt 3 Möglichkeiten, wie zwei eval boards untereinander kommunizieren
  können, abhängig ist dies davon, wie die Jumper JC6, JC7 und JC8 belegt sind:
  * (*JC8 geschlossen, JC7 offen*) Powerline-Anschluss (AC-Line) beider Boards an **gleicher** Mehrfachsteckdosenleiste
    * (*JC7 geschlossen, JC8 offen, JC6 offen für 100W impendanz*) beide Boards über Twisted-Pair-Anschluss verbunden (grüner Stecker), Belegung jeweils:
      * links blaues Kabel
        * rechts braunes Kabel
    * (*JC7 geschlossen, JC8 offen, JC6 geschlossen für 50W impendanz*) Koax-Kabel
* Kommunikation zum Host (Entwicklungsrechner) über Ethernet

### Host-System

* open-plc-utils installieren
  * für Arch-Linux als AUR-Package
  * andernfalls suchen oder von [GitHub](https://github.com/qca/open-plc-utils)
    beziehen

### RISE V2G Open Source Implementierung

* Download des [GitHub Repos](https://github.com/V2GClarity/RISE-V2G)
* RISE-V2G-SHARED/pom.xml anpassen:
  * folgendes unter `<dependencies>` einfügen:

 ```XML
 <dependency>
   <groupId>javax.xml.bind</groupId>
   <artifactId>jaxb-api</artifactId>
   <version>2.3.1</version>
  </dependency>
 <dependency>
   <groupId>org.glassfish.jaxb</groupId>
   <artifactId>jaxb-runtime</artifactId>
   <version>2.3.1</version>
   <scope>runtime</scope>
  </dependency>
 ```

* die javax.xml.bind dependencies wurden im JDK Version > 8 entfernt und
      müssen so nachträglich installiert werden
* maven-dependancies laden und das Projekt bauen
* zum ersten Testen:
  * StartEVCC ausführen (RISE-V2G-EVCC > src > main >
      com.v2gclarity.risev2g.evcc > main)
    * StartSECC ausführen (RISE-V2G-SECC > analog zu oben )

## Kommunikation zwischen Host und Eval Boards

Die Eval-Boards haben eine Atheros QCA7000 Chip und können über die, durch
open-plc-utils mitgelierferten, Programme und Skripte angesprochen und gesteuert
werden.
> Hinweis: Statt wie in der open-plc Doku verwendeten int6k.. Programme die
> pcltool.. Programme für den neueren QCA7000 Chip verwenden

Die Boards sind bereits vorkonfiguriert und entsprechend geflasht.
Vorbelegten MACs und entsprechende Ethernet-Interfaces (auf dem Host-System):

* EVSE: BC:F2:AF:F1:4F:C4 (Ethernet-Interface: enp0s20f0u2u4)
* PEV:  BC:F2:AF:F1:CC:03 (Ethernet-Interface: enp0s20f0u3u1)

### Erste Verbindungs und Statusabfrage

* **plctool -m**: Netzwerk-Config abfragen
  * `network->STATIONS = 1` zeigt, dass das Board über Powerline mit einem
    Netzwerk Verbindung aufgebaut hat, was auch aus der Übertragungsrate > 0
    deutlich wird
* **plctool -r**: Firmware und Hardware Infos abfragen
* **plctool -I**: Den PBI-Header auslesen
  * *DAK*: Device Access Key (für jedes Board individuell)
  * *NMK*: Network membership key (muss für beide gleich sein, um einem Netzwerk
    zugeordnet zu sein)

```shell
❯ plctool -m -i enp0s20f0u2u4
enp0s20f0u2u4 00:B0:52:00:00:01 Fetch Network Information
enp0s20f0u2u4 BC:F2:AF:F1:4F:C4 Found 1 Network(s)

source address = BC:F2:AF:F1:4F:C4

 network->NID = B0:F2:E6:95:66:6B:03
 network->SNID = 11
 network->TEI = 1
 network->ROLE = 0x02 (CCO)
 network->CCO_DA = BC:F2:AF:F1:4F:C4
 network->CCO_TEI = 1
 network->STATIONS = 1

  station->MAC = BC:F2:AF:F1:CC:03
  station->TEI = 2
  station->BDA = 00:50:B6:B8:46:D5
  station->AvgPHYDR_TX = 009 mbps Primary
  station->AvgPHYDR_RX = 009 mbps Primary

> plctool -r -i enp0s20f0u2u4
enp0s20f0u2u4 00:B0:52:00:00:01 Request Version Information
enp0s20f0u2u4 BC:F2:AF:F1:4F:C4 QCA7000 MAC-QCA7000-1.1.0.727-02-20130826-FINAL
> plctool -I -i enp0s20f0u2u4
 PIB 0-0 8080 bytes
 MAC BC:F2:AF:F1:4F:C4
 DAK D7:EA:8D:81:1A:95:44:53:B3:EA:62:4E:69:C1:7C:17
 NMK 50:D3:E4:93:3F:85:5B:70:40:78:4D:F8:15:AA:8D:B7 (HomePlugAV)
 NID B0:F2:E6:95:66:6B:03
 Security level 0
 NET Qualcomm Atheros Enabled Network
 MFG Devolo_dLan_GreenPHY
 USR SLAC-EVSE
 CCo Always
 MDU N/A
```

### NMK neu setzen

Sollte der Network Membership Key (NMK) eines der Boards verändert worden sein,
kann dieser mittels folgendem Befehl neu gesetzt werden:
(anzupassen sind jeweils Ethernet-Interface, MAC-Adresse und NMK des anderen
Boards, zu welchem Netzwerk man sich verbinden möchte)

```shell
plctool -i eth1 -p settings.pib BC:F2:AF:F1:CC:03 && \
      modpib -N FA:6C:AC:B4:BB:66:42:95:EC:E6:9F:20:E5:7F:87:23 -v settings.pib && \
      plctool -i eth1 -P settings.pib BC:F2:AF:F1:CC:03
```

## Websocket Interface Wallbox-SECC

Um die Kommunikation mit dem PEV über ISO-15118 steuern zu können, wird eine Websocket-API eingeführt, über welche die benötigten Daten abegerufen bzw. übertragen werden können. Der Server ist unter folgender URL zu erreichen: [ws://localhost:8080/v2gServer/](ws://localhost:8080/v2gServer/).

### Websocket Nachrichten

Die Nachrichten werden als JSON übertragen. Anbei eine Beispiel-Nachricht nach erfolgreichem Verbindungsaufbau:

```json
{
 "messageType":"sessionStartReq",
 "title":"Connected",
 "value":""
}

{
 "messageType":"discoveryReq",
 "title":"SECCDiscoveryReq received.",
 "value":"Security: 16"
}

{
 "messageType":"discoveryReq",
 "title":"Initializing new session.",
 "value":"fe80:0:0:0:2e0:4cff:fe68:241e%7"
}
```
