# SWEP - 3D VisApp Beispieldaten

## Disclaimer
Alle in diesem Dokument sowie in den bereitgestellten Testdaten und Dokumentationen enthaltenen Information sind vertraulich und dürfen nicht an Dritte weitergegeben werden.

## G&S SatConnect

### Einleitung zur API
G&S SatConnect bietet eine REST-API an. Die Authentifizierung erfolgt via BasicAuth, Session-Cookie oder einem Token. Die Integration mit der echten API kann durch G&S übernommen werden. Im Folgenden werden Beispiel GET-Requests dargestellt. Die entsprechenden Outputs werden als gesonderte JSON abgelegt.

Der generelle API-URL-Aufbau ist wie folgt:
```
{base-url}/api/{version}/{endpoint}
```

Als Version gibt es derzeit nur `v1`.
Im Folgenden wird eine interne Demo-Instanz genutzt, die folgende base-url hat: `https://dev-web.int.gsits.de/satconnect_demo_develop_backend`

Eine normale Response hat folgenden Wrapper:
```json
{
    "meta": {
        "listing": false,
        "status": 200
    },
    "data": {

    },
    "errors": []
}
```
Das Feld `meta.listing` gibt an, ob im Attribut `data` ein einzelner Datensatz oder ein Array enthalten ist. In dem oberen Fall handelt es sich um ein einzelnen Datensatz.
Das Feld `meta.status` gibt den HTTP-Status-Code an.
Das Feld `data` enthält den eigentlichen Payload und ist abhängig vom Endpunkt.
Das Feld `errors` enthält in einem Fehlerfall eine Auflistung der Fehler.

Eine Response auf ein Listing (das heißt, wenn mehrere Objekte, z.B. Satelliten abgerufen werden sollen) hat folgenden Wrapper:
```json
{
    "meta": {
        "listing": true,
        "count": 5,
        "countTotal": 5,
        "start": 0,
        "pageSize": 20,
        "page": 1,
        "sort": [],
        "status": 200
    },
    "data": [

    ],
    "errors": []
}
```
Im Gegensatz zu der normalen response ist das Feld `data` nun ein Array (`[]`) anstatt eines Objekts (`{}`).
Die weiteren meta-Angaben beschreiben, wie viele Ergebnisse im Listing sind, und wie viele davon angezeigt werden (pagination).


### Satellites

Die Entität der Satelliten heißt intern `ScSatellite`. Es wurden einige Beispiel-Satelliten angelegt, die alle mit "G&S" im Namen beginnen.
Zum Abruf aller Satelliten, die `G&S` im Namen enthalten, wird folgender API-Request ausgeführt:
```http
GET https://dev-web.int.gsits.de/satconnect_demo_develop_backend/api/v1/obj/ScSatellite?name__contains=G%26S
```
Die Response liegt in der Datei: `Listing-ScSatellite.json`

Es handelt sich derzeit ausschließlich um GEO-stationöre Satelliten.

Relevante Felder sind:
* `ID`
* `name`
* `longitude`

### Overlays

Bei den Overlays handelt es sich intern um die Entität `MapOverlay`. Hierbei ist es so, dass derzeit die geodaten nicht mit im Listing sind, da es sich hierbei um sehr große Datensätze handeln kann. Zunächst wird wiederum das Listing abgerufen, um alle Map-Overlays abzurufen.

Hinweise zu den verwendeten Get-Parametern:
* `__pageSize` wird auf 40 gesetzt, um alle Objekte zu erhalten (Standardwert ist sonst 20)
* `__start` wird auf 2 gesetzt, um die ersten zwei Objekte zu überspringen, da diese nicht zu den erzeugten Testdaten gehören

```http
GET https://dev-web.int.gsits.de/satconnect_demo_develop_backend/api/v1/obj/MapOverlay?__pageSize=40&__start=2
```
Die Response liegt in der Datei: `Listing-MapOverlay.json`

Relevante Felder sind:
* `ID`
* `name`
* `MapOverlayGroupID` (FK zu einer Gruppierung. Hier kommen ID 1 für Service Areas und ID 3 für Beams vor.)
* `type` (Es kommt nur der type `footprint` vor. Dies besagt, dass die geodaten ein 2D Polygon auf der Karte sind und getrennt abzurufen sind)
* `color` (kann optional genutzt werden, um die Fläche zu färben)

Anschließend müssten für jedes Overlay, das angezeigt werden soll (wir machen das in unserer 2D Karte erst dann, wenn ein Mapoverlay angeklickt wird), die geodaten geladen werden.
Die entsprechenden API-Requests sehen wie folgt aus (Platzhalter `{ID}` wird durch die jeweilige `ID` des MapOverlay-Objekts ersetzt):
```http
GET https://dev-web.int.gsits.de/satconnect_demo_develop_backend/api/v1/obj/MapOverlay/{ID}/geodata
```

Die Daten der ServiceAreas liegen im Ordner: `/MapOverlay-ServiceAreas`
Die Daten der Beams liegen aufgeteilt nach Satelliten in dem Ordner: `/MapOverlay-Beam`

Leider gibt es derzeit zwischen den MapOverlays und den Satelliten noch keinen Bezug in G&S SatConnect. Daher gibt es keine Referenzen in den Payloads. Derzeit können die Overlays also nur in die Overlay-Gruppen eigeteilt und dann als Liste angezeigt werden.
Die hier abgelegten Beam-Daten sind keine Beams, sondern auch nur Regionen bzw. Länder. Die Daten sind daher alle beispielahft zu verstehen.


### Terminals mit Standort

Für Terminals gibt es einen Endpunkt, der immer die wichtigsten Live-Daten enthält. Im folgenden Request werden für 2 Terminals (IDs 4 & 14), welche GPS-Daten enthalten, diese Live-daten abgerufen:
```http
GET https://dev-web.int.gsits.de/satconnect_demo_develop_backend/api/v1/obj/TerminalLiveData?ID__in=4,14
```
Die Response liegt in der Datei: `Listing-TerminalLiveData.json`

Die zugehörigen Terminal-Stammdaten werden über folgenden Request abgerufen:
```http
GET https://dev-web.int.gsits.de/satconnect_demo_develop_backend/api/v1/obj/Terminal?ID__in=4,14
```
Die Response liegt in der Datei: `Listing-Terminal.json`

Insgesamt sind folgende Daten interessant:
* Aus dem Terminal selbst
  * `ID`
  * `name`
  * `mobilityType` (Möglichkeiten: `Aero`, `fixed`, `maritime`, `Vehicle`, `none`)
* Aus TerminalLiveData
  * `aggregatedStatus` (Möglichkeiten: `unknown`, `alarm`, `online`, `offline`, `suspended`, `provisioned`, `none`)
  * `longitude`
  * `latitude`

## IBM Wetterdaten
In dem Ordner `/IBM-Wetter` liegt eine JSON-Datei sowie eine Anleitung als PDF. Diese JSON-Datei enthält alle Wetterphänomene zu einem bestimmten Zeitpunkt.

## Open Weather Maps
Hier haben wir keine Beispieldaten. In der 2D-Karte nutzen wir nur direkte Tile-Layer und keine strukturierten Daten. OWM bietet aber nach Angabe auf der Homepage auch strukturierte Daten (JSON / XML) an.
