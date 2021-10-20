// liefert die Anzahl an Koernern, die sich auf dem Feld vor dem 
// Hamster befinden; produziert dabei keine Seiteneffekte
import de.hamster.debugger.model.Territorium;import de.hamster.model.HamsterException;import de.hamster.model.HamsterInitialisierungsException;import de.hamster.model.HamsterNichtInitialisiertException;import de.hamster.model.KachelLeerException;import de.hamster.model.MauerDaException;import de.hamster.model.MaulLeerException;public class hamsterprogramm extends de.hamster.debugger.model.IHamster implements de.hamster.model.HamsterProgram {int koernerVorne() {  // Definition einer int-Funktion
    if (!vornFrei()) {
      return 0;
    }
    vor();
    int anzahl = 0;  // lokale Variable
    while (kornDa()) {
      nimm();
      anzahl = anzahl + 1;
    }
    // um Seiteneffekte zu vermeiden, muessen nun noch die Koerner
    // wieder abgelegt werden und der Hamster muss auf
    // seine alte Kachel zurueckkehren
    int zurueck = anzahl;
    while (zurueck > 0) {
      gib();
      zurueck = zurueck - 1;
    }
    kehrt();
    vor();
    kehrt();
    // nun kann die Anzahl an Koernern zurueckgeliefert werden
    return anzahl;
}

void kehrt() {
    linksUm(); linksUm();
}

public void main() {
    // ermittelt die Anzahl an Koernern vor sich
    int nachbarKoerner = koernerVorne(); // Aufruf einer int-Funktion
    int drehungen = 0;
    // dreht sich in die anderen Richtungen und addiert die
    // entsprechenden Koerneranzahlen
    while (drehungen < 3) {
      linksUm();
      nachbarKoerner = nachbarKoerner + 
                       koernerVorne();  // Aufruf einer int-Funktion
      drehungen = drehungen + 1;
    }
    // legt entsprechend viele Koerner ab, solange er noch
    // welche im Maul hat
    while (nachbarKoerner > 0 && !maulLeer()) {
      gib();
      nachbarKoerner = nachbarKoerner - 1;
    }
}
}