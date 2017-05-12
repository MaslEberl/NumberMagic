import java.awt.*;
/************************************************
**  Abstrakte Klasse.<br>
**  Die grafisch dargestellbaren Interpolationspunkte sind
**  Subklassen von Klasse InterpolPunkte.
*/
public class InterpolPunkte implements Darstellbar {
  protected double koordX[];
  protected double koordY[];
  protected int anzahl;
  public String Name="Interpolationspunkte";
  
  public InterpolPunkte(double[] koordX, double[] koordY) {
    this.koordX=koordX;
    this.koordY=koordY;
    if (koordX.length<=koordY.length) anzahl=koordX.length;
    else anzahl=koordY.length;
  }

  /************************************************
  **  Gibt minimale und maximale Koordinate x der Interpolationspunkte<br>
  **  Parameter: - <br>
  **  Ausgabe: minimale und maximale Koordinate x der Interpolationspunkte
  */
  public MinMax getMinMaxX() {
    MinMax mmX=new MinMax();
    double minX=1000000;
    double maxX=-1000000;
    for(int i=0; i<anzahl; i++) {
      if(koordX[i]<minX) minX=koordX[i];
      if(koordX[i]>maxX) maxX=koordX[i];
    }
    mmX.min=minX;
    mmX.max=maxX;
    return mmX;
  }

  /************************************************
  **  Gibt minimale und maximale Koordinate Y der Interpolationspunkte<br>
  **  Parameter: - <br>
  **  Ausgabe: minimale und maximale Koordinate Y der Interpolationspunkte
  */
  public MinMax getMinMaxY() {
    MinMax mmY=new MinMax();
    double minY=1000000;
    double maxY=-1000000;
    for (int i=0; i<anzahl; i++) {
      if (koordY[i]<minY) minY=koordY[i]; 
      if (koordY[i]>maxY) maxY=koordY[i];
    }   
    mmY.min=minY;
    mmY.max=maxY;
    return mmY;
  }
  
  /************************************************
  **  Gibt Name der Punkten<br>
  **  Parameter: - <br>
  **  Ausgabe: Name der Punkten
  */
  public String getName(){ return Name; }
  public void setName(String name){ Name=name; }

  /************************************************
  **  Zeichnet die Punkte<br>
  **  Parameter:                                                <br>&nbsp;&nbsp;
  **    gminX - minimale Wert der Koordinate X                  <br>&nbsp;&nbsp;
  **    gmaxX - maximale Wert der Koordinate X                  <br>&nbsp;&nbsp;
  **    gminX - minimale Wert der Koordinate Y                  <br>&nbsp;&nbsp;
  **    gmaxX - maximale Wert der Koordinate Y                  <br>&nbsp;&nbsp;
  **    fst - Bild in dem die Punkte gezeichnet werden          <br>
  **  Ausgabe: -
  */
  public void draw(
        Graphics g, double gminX, double gmaxX, double gminY, double gmaxY, Bild fst) {
    int size_x, size_y, xold, yold, x, y, margines;
    double scale_x, scale_y;

    //Berechnung der Skalierung der "echten" Koordinaten in die "bild" koordinaten
    margines = fst.getMargines();
    size_x = fst.getFensterGroesseX()-2*margines;
    size_y = fst.getFensterGroesseY()-2*margines;
    
    scale_x = size_x/(gmaxX-gminX);
    scale_y = size_y/(gmaxY-gminY);  

    //Zeichnen der Kurve
    for (int i=0; i<anzahl; i++) {
      x = margines+(int)Math.rint((koordX[i]-gminX)*scale_x);
      y = size_y+margines-(int)Math.rint((koordY[i]-gminY)*scale_y);
      g.setColor(new Color(50,150,50));     
      g.fillOval(x-4,y-4,8,8);
      g.setColor(Color.black);      
    }
  }
  public void setIntervall(double min, double max) {}
  public void setAufloesung(int aufloesung)        {}
}
