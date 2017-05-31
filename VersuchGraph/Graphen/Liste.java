/************************************************
** Bildet Liste, in der die zu zeichnenden Kurven gespeichert sind
*/
public class Liste {
  private int anzahl;
  private Darstellbar f;
  private Liste rest;

  /************************************************
  **  Parameter:<br>
  */
  public Liste() { anzahl=0; }

  /************************************************
  **  Gibt Kurve mit Index n<br>
  **  Parameter:                                                <br>&nbsp;&nbsp;
  **    n - Index der Kurve                                     <br>
  **  Ausgabe: Kurve mit Index n
  */
  public Darstellbar getDarstellbar(int n) {
    if   (n>(anzahl-1)) {throw new Error("Die Liste ist Kürzer!");}
    else {if (n>0) return rest.getDarstellbar(n-1); else return f;}
  }

  /************************************************
  **  Fügt Kurve f der Liste hinzu<br>
  **  Parameter:                                                <br>&nbsp;&nbsp;
  **    f - zu speichernde Kurve <br>
  **  Ausgabe: -
  */
  public void addDarstellbar(Darstellbar f) {
    Liste neuesElement=new Liste();
    neuesElement.rest=this.rest;
    neuesElement.f=this.f;
    neuesElement.anzahl=anzahl;
    this.rest=neuesElement;
    this.f=f;
    anzahl=neuesElement.anzahl+1;
  }

  /************************************************
  **  Gibt Anzahl der gespeicherten Kurven<br>
  **  Parameter: - <br>
  **  Ausgabe: Anzahl der gespeicherten Kurven
  */
  public int getAnzahl() { return anzahl; }
}
