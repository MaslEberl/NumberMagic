import java.awt.*;
import java.awt.event.*;
import java.util.*;
/************************************************
**  Zeichnet die Kurven und Gitterlinien und
**  zeigt Mauskoordinaten
*/
public class Bild
extends Canvas
implements MouseMotionListener {
  private static final int fensterGroesseX = 700;
  private static final int fensterGroesseY = 500;
  private static final int margines = 20;
  private double minY, maxY;
  private double minX, maxX;
  private int aufloesung=500;
  private int funktionenAnzahl;
  private Darstellbar[] db;
  private Grafik fsr;
  private Liste fr;
  private String beschriftung=" ";
  private Image im= createImage(fensterGroesseX,fensterGroesseY);

  /************************************************
  **  Parameter:                                               <br>&nbsp;&nbsp;
  **  fr - Liste der zu zeichnenden Kurven bzw. Punkten        <br>&nbsp;&nbsp;
  **  fsr - Grafik in der das Bild gezeigt wird
  */
  public Bild(Liste fr, Grafik fsr) {
    super();
    this.fsr=fsr;
    this.fr=fr;
    funktionenAnzahl=fr.getAnzahl();
    
    //Berechnung der Punkte der Kurven und Bestimmung minX, maxX, minY, maxY  
    db= new Darstellbar[funktionenAnzahl];
    MinMax mm=new MinMax();
    minY=100000; maxY=-100000;
    minX=100000; maxX=-100000;
    for(int i=0; i<funktionenAnzahl; i++) {
      db[i]=fr.getDarstellbar(i);
      mm=db[i].getMinMaxX();
      if (mm.min<minX) minX=mm.min;
      if (mm.max>maxX) maxX=mm.max;
      mm=db[i].getMinMaxY();
      if (mm.min<minY) minY=mm.min;
      if (mm.max>maxY) maxY=mm.max;
    } 

    beschriftung=fr.getDarstellbar(0).getName();
    for (int i=1; i<funktionenAnzahl; i++) 
      beschriftung = beschriftung + "    &    " + fr.getDarstellbar(i).getName();
      
    addMouseMotionListener(this);
    setVisible(true);
  }

  /************************************************
  **  Parameter:                                                <br>&nbsp;&nbsp;
  **  f - zu zeichnende Kurve                                   <br>&nbsp;&nbsp;
  **  fsr - Grafik in der das Bild gezeigt wird
  */
  public Bild(Darstellbar f, Grafik fsr) {
    super();
    this.fsr=fsr;
    funktionenAnzahl=1;
    db= new Darstellbar[funktionenAnzahl];
    db[0]=f;

    //Berechnung der Punkte der Kurve und Bestimmung minX, maxX, minY, maxY   
    MinMax mm=new MinMax();
    mm=f.getMinMaxY();
    minY=mm.min; maxY=mm.max;
    mm=f.getMinMaxX();
    minX=mm.min; maxX=mm.max;

    beschriftung =""+f.getName();
    
    addMouseMotionListener(this);
    setVisible(true);
  }

  /************************************************
  **  Zeichnet Kurven bzw. Punkte und Gitterlinien
  */
  public void paint(Graphics g) {
    setBackground(Color.white);
    int nogx=7, nogy=5, size_x, size_y, gdx, gdy, zerox, zeroy;
    size_x = fensterGroesseX - 2*margines;
    size_y = fensterGroesseY - 2*margines;
    gdx = size_x/(nogx-1);
    gdy = size_y/(nogy-1);
    zerox = margines + (int)Math.rint(-minX/(maxX-minX)*size_x);
    zeroy = size_y + margines + (int)Math.rint(minY/(maxY-minY)*size_y);

    g.setColor(new Color(255,200,200));
    for (int i=0; i<nogx; i++)
      g.drawLine(margines + i*gdx, margines, 
                 margines + i*gdx, margines+size_y);

    for (int i=0; i<nogy; i++)
      g.drawLine(margines, margines + i*gdy, 
                 margines+size_x, margines + i*gdy);
    
    g.setColor(Color.blue);
    g.drawLine(zerox, margines, 
               zerox, margines+size_y);

    g.drawLine(margines, zeroy, 
               margines+size_x, zeroy);
      
    //Zeichnen der Kurven 
    g.setColor(Color.black);
    for (int i=0; i<funktionenAnzahl; i++)
      db[i].draw(g,minX,maxX,minY,maxY,this);
    
    
    double h1=Math.round(minX*10000);
    h1/=10000;
    double h2=Math.round(maxX*10000);
    h2/=10000;
    double h3=Math.round(minY*10000);
    h3/=10000;
    double h4=Math.round(maxY*10000);
    h4/=10000;
    String fminmax =""+h1+" < x < "+h2+"     "+h3+" < y < "+h4;
    g.drawString(fminmax, margines, margines-5);
    g.drawString(beschriftung, margines, fensterGroesseY-5);

  }

  /************************************************
  **  Gibt Image<br>
  **  Parameter: -<br>
  **  Ausgabe: Image
  */
  public Image getBildImage() { return im; }

  /************************************************
  **  Setzt die Auflösung<br>
  **  Parameter:                                                <br>&nbsp;&nbsp;
  **  aufloesung - neue Auflösung                               <br>
  **  Ausgabe: -
  */
  public void setAufloesung(int aufloesung) {
    this.aufloesung=aufloesung;
    for(int i=0; i<funktionenAnzahl; i++) 
      db[i].setAufloesung(aufloesung);
      
    //Berechnung der Punkte der Kurven und Bestimmung minX, maxX, minY, maxY  
    MinMax mm=new MinMax();
    minY=100000; maxY=-100000;
    minX=100000; maxX=-100000;
    for(int i=0; i<funktionenAnzahl; i++)
    {
      db[i]=fr.getDarstellbar(i);
      mm=db[i].getMinMaxX();
      if (mm.min<minX) minX=mm.min;
      if (mm.max>maxX) maxX=mm.max;
      mm=db[i].getMinMaxY();
      if (mm.min<minY) minY=mm.min;
      if (mm.max>maxY) maxY=mm.max;
    } 
  }

  /************************************************
  **  Setzt die Intervallgrenzen und Auflösung<br>
  **  Parameter:                                                <br>&nbsp;&nbsp;
  **  min - untere Intervallgrenze                              <br>&nbsp;&nbsp;
  **  max - obere Intervallgrenze                               <br>&nbsp;&nbsp;
  **  aufloesung - neue Auflösung                               <br>
  **  Ausgabe: -
  */
  public void setIA(double min, double max, int aufloesung) {
    db[0].setIntervall(min,max);
    db[0].setAufloesung(aufloesung);
      
    //Berechnung der Punkte der Kurve und Bestimmung minX, maxX, minY, maxY   
    MinMax mm=new MinMax();
    mm=db[0].getMinMaxY();
    minY=mm.min; maxY=mm.max;
    mm=db[0].getMinMaxX();
    minX=mm.min; maxX=mm.max;
  }
  
  /************************************************
  **  Berechnet Mauskoordinaten
  */
  public void mouseMoved(MouseEvent m) {
    double x=(m.getX()-margines)*(maxX-minX)/(fensterGroesseX-2*margines)+minX;
    double y=(fensterGroesseY-m.getY()-margines)*(maxY-minY)/(fensterGroesseY-2*margines)+minY;
    String xs=""+(Math.rint(x*10000)/10000);
    String ys=""+(Math.rint(y*10000)/10000);
    fsr.tf5.setText(xs);
    fsr.tf6.setText(ys);
  }
  
  public Dimension getPreferredSize() {return new Dimension(fensterGroesseX,fensterGroesseY);}
  public Dimension getMinimumSize()   {return new Dimension(fensterGroesseX,fensterGroesseY);}
  public void mouseDragged(MouseEvent m) {}

  /************************************************
  **  Gibt Breite des Fensters <br>
  **  Parameter: - <br>
  **  Ausgabe: Breite des Fensters
  */
  public int getFensterGroesseX() { return fensterGroesseX; }

  /************************************************
  **  Gibt Höhe des Fensters <br>
  **  Parameter: - <br>
  **  Ausgabe: Höhe des Fensters
  */
  public int getFensterGroesseY() { return fensterGroesseY; }

  /************************************************
  **  Gibt Margines <br>
  **  Parameter: - <br>
  **  Ausgabe: Margines
  */
  public int getMargines() { return margines; }

  /************************************************
  **  Gibt Aufloesung <br>
  **  Parameter: - <br>
  **  Ausgabe: Aufloesung
  */
  public int getAufloesung() { return aufloesung; }

  /************************************************
  **  Gibt minimale und maximale Koordinate X <br>
  **  Parameter: - <br>
  **  Ausgabe: minimale und maximale Koordinate X
  */
  public MinMax getMinMaxX() {
    MinMax mmX=new MinMax();
    mmX.min=minX;
    mmX.max=maxX;
    return mmX;
  }
  
  /************************************************
  **  Gibt minimale und maximale Koordinate Y<br>
  **  Parameter: - <br>
  **  Ausgabe: minimale und maximale Koordinate Y
  */
  public MinMax getMinMaxY() {
    MinMax mmY=new MinMax();
    mmY.min=minY;
    mmY.max=maxY;
    return mmY;
  }
}
