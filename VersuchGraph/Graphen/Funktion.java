import java.awt.*;
/******************************************************************************
** Jolanta Bajorska, mit kleine Aenderungen von B.W., 23.10.99<br>
** Abstrakte Klasse.<br>
** Die grafisch darzustellenden Funktionen sind Subklassen dieser Klasse.
** Benutzt die Klassen Bild, MinMax und das Interface Darstellbar.
*/
public abstract class Funktion implements Darstellbar {
  protected double minX, maxX;
  protected double xMin, xMax; //B.W. wegen Funktion1
  protected String Name="NoName"; //Gross B.W.
  private double[] koordX;
  private double[] koordY;
  private int aufloesung=100;
  
  /************************************************
  **  Parameter:                                                <br>&nbsp;&nbsp;
  **  min - untere Grenze des Intervalls auf dem die Funktion berechnet wird<br>&nbsp;&nbsp;
  **  max - obere Grenze des Intervalls auf dem die Funktion berechnet wird<br>
  */
  public Funktion(double min, double max) {
    this.minX=min;
    this.maxX=max;
    xMin=min; xMax=max; //B.W.
  }

  public Funktion(){//B.W., zum Zeichnen ungeeignet
    xMax=Double.MAX_VALUE;
    xMin=-xMax;
    minX=xMin; maxX=xMax;
  }

  public abstract double getY(double x);
  
  public MinMax getMinMaxX() {//liefert minX, maxX
    MinMax mmX=new MinMax();
    mmX.min=minX;
    mmX.max=maxX;
    return mmX;
  }

  public MinMax getMinMaxY() {//Liefert die Grenzen von f([minX.maxX])
    MinMax mmY=new MinMax();
    koordX= new double[aufloesung];
    koordY= new double[aufloesung];
                double minY=Double.MAX_VALUE;
                double maxY=-minY;
                double dx=(maxX-minX)/(aufloesung-1);

    for (int i=0; i<aufloesung; i++) {
      koordX[i]=minX+i*dx;
      koordY[i]=getY(koordX[i]);
      if (koordY[i]<minY) minY=koordY[i];
      if (koordY[i]>maxY) maxY=koordY[i];
    }
    mmY.min=minY;
    mmY.max=maxY;
    return mmY;
  }
  
  /************************************************
  **  Parameter:                                                <br>&nbsp;&nbsp;
  **    minX - untere Intervallgrenze                           <br>&nbsp;&nbsp;
  **    maxX - obere Intervallgrenze                            <br>
  **  Ausgabe: -
  */
  public void setIntervall(double minX, double maxX) {
    this.minX=minX;
    this.maxX=maxX;
  }
  
  public void setAufloesung(int aufloesung) { this.aufloesung=aufloesung; }
  public String getName(){ return Name; }

  public void setName(String Name){ this.Name=Name; }

  /************************************************
  ** Zeichnet die Funktion<br>
  **  Parameter:                                                <br>&nbsp;&nbsp;
  **    gminX - minimale Wert der Koordinate X                  <br>&nbsp;&nbsp;
  **    gmaxX - maximale Wert der Koordinate X                  <br>&nbsp;&nbsp;
  **    gminX - minimale Wert der Koordinate Y                  <br>&nbsp;&nbsp;
  **    gmaxX - maximale Wert der Koordinate Y                  <br>&nbsp;&nbsp;
  **    fst   - ein Objekt vom Typ Bild                         <br>
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
  }//Ende draw()

  public double[] getWertetabelle(double a, double b, int N){ //B.W.
    double h=(b-a)/N;
    double[] y=new double[N+1];
    for(int k=0;k<=N;k++)
      y[k]=getY(a+k*h);
    return y;
  }//Ende getWertetabelle

}//Ende class Funktion
