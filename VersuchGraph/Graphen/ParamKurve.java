import java.awt.*;
/************************************************
**  Abstrakte Klasse.<br>
**  Die grafisch darzustellenden parametriesierten Kurven sind
**  Subklassen dieser Klasse ParamKurve.
*/
public abstract class ParamKurve implements Darstellbar {
  protected double min, max;
  protected String Name="";//gross B.W.
  private double koordX[];
  private double koordY[];
  private int aufloesung=100;

  /************************************************
  **  Parameter:                                                <br>&nbsp;&nbsp;
  **    min - untere Grenze des Intervals des Parameters auf
  **          dem die Kurve berechnet wird                      <br>&nbsp;&nbsp;
  **    max - obere Grenze des Intervals des Parameters auf
  **          dem die Kurve berechnet wird                      <br>
  */
  public ParamKurve(double min, double max) {
    this.min=min;
    this.max=max;
  }

  /************************************************
  **  Gibt Koordinate x des Punktes der Kurve<br>
  **  Parameter:                                                <br>&nbsp;&nbsp;
  **    argument - Argument für den die Koordinate x berechnet wird<br>
  **  Ausgabe: Koordinate x des Punktes der Kurve
  */
  public abstract double getX(double argument);

  /************************************************
  **  Gibt Koordinate y des Punktes der Kurve<br>
  **  Parameter:                                                <br>&nbsp;&nbsp;
  **    argument - Argument für den die Koordinate y berechnet wird<br>
  **  Ausgabe: Koordinate y des Punktes der Kurve
  */
  public abstract double getY(double argument);

  /************************************************
  **  Gibt minimale und maximale Wert des Parameters t<br>
  **  Parameter: - <br>
  **  Ausgabe: minimale und maximale Wert des Parameters t
  */
  public MinMax getMinMaxT() {
    MinMax mmT=new MinMax();
    mmT.min=min;
    mmT.max=max;
    return mmT;
  }

  /************************************************
  **  Berechnet Koordinaten X der Punkten der Kurve und
  **  gibt minimale und maximale Koordinate X der Kurve<br>
  **  Parameter: - <br>
  **  Ausgabe: minimale und maximale Koordinate X der Kurve
  */
  public MinMax getMinMaxX()
  { 
    MinMax mmX=new MinMax();
    koordX= new double[aufloesung];
    double minX=1000000;
    double maxX=-1000000;
    double dt=(max-min)/(aufloesung-1);
    for (int i=0; i<aufloesung; i++)
    {
      koordX[i]=getX(min+i*dt);
      if (koordX[i]<minX) minX=koordX[i]; 
      if (koordX[i]>maxX) maxX=koordX[i];
    }   
    mmX.min=minX;
    mmX.max=maxX;
    return mmX;
  }
  
  /************************************************
  **  Berechnet Koordinaten Y der Punkten der Kurve und
  **  gibt minimale und maximale Koordinate Y der Kurve<br>
  **  Parameter: - <br>
  **  Ausgabe: minimale und maximale Koordinate Y der Kurve
  */
  public MinMax getMinMaxY()
  { 
    MinMax mmY=new MinMax();
    koordY= new double[aufloesung];
    double minY=1000000;
    double maxY=-1000000;
    double dt=(max-min)/(aufloesung-1);
    for (int i=0; i<aufloesung; i++)
    {
      koordY[i]=getY(min+i*dt);
      if (koordY[i]<minY) minY=koordY[i]; 
      if (koordY[i]>maxY) maxY=koordY[i];
    }   
    mmY.min=minY;
    mmY.max=maxY;
    return mmY;
  }
  
  
  /************************************************
  **  Setzt neue Intervallgrenzen<br>
  **  Parameter:                                                <br>&nbsp;&nbsp;
  **    minX - untere Intervallgrenze                           <br>&nbsp;&nbsp;
  **    maxX - obere Intervallgrenze<br>
  **  Ausgabe: -
  */
  public void setIntervall(double minX, double maxX) {
    min=minX;
    max=maxX;
  }
  
  /************************************************
  **  Gibt Name der Funktion<br>
  **  Parameter: - <br>
  **  Ausgabe: Name der Funktion
  */
  public String getName() { return Name; }
  public void setName(String name){ Name=name; }

  /************************************************
  **  Setzt die Aufloesung
  **  Parameter:                                                <br>&nbsp;&nbsp;
  **     aufloesung - neue Aufloesung<br>
  **  Ausgabe: -
  */
  public void setAufloesung(int aufloesung) { this.aufloesung=aufloesung; }
  
  /************************************************
  **  Zeichnet die Kurve<br>
  **  Parameter:                                                <br>&nbsp;&nbsp;
  **     gminX - minimale Wert der Koordinate X                 <br>&nbsp;&nbsp;
  **     gmaxX - maximale Wert der Koordinate X                 <br>&nbsp;&nbsp;
  **     gminX - minimale Wert der Koordinate Y                 <br>&nbsp;&nbsp;
  **     gmaxX - maximale Wert der Koordinate Y                 <br>&nbsp;&nbsp;
  **     fst - Bild in dem die Kurve gezeichnet wird<br>
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
    xold = margines+(int)Math.rint((koordX[0]-gminX)*scale_x);
    yold = size_y+margines-(int)Math.rint((koordY[0]-gminY)*scale_y);
    for (int i=1; i<aufloesung; i++) {
      x = margines+(int)Math.rint((koordX[i]-gminX)*scale_x);
      y = size_y+margines-(int)Math.rint((koordY[i]-gminY)*scale_y);
      g.drawLine(xold,yold,x,y);
      xold = x;
      yold = y;  
    }
  }
}
