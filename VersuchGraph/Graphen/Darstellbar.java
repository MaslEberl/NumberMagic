import java.awt.*;
/************************************************
**  Interface mit Methoden zum Zeichnen
*/
interface Darstellbar
{
  public MinMax getMinMaxX();
  public MinMax getMinMaxY();
  public String getName();
  public void setName(String name);
  public void draw(Graphics g, double gminX, double gmaxX, double gminY, double gmaxY, Bild fst);
  public void setAufloesung(int aufloesung);
  public void setIntervall(double min, double max);
}
