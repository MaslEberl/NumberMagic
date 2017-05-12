/************************************************
**  Klasse berechnet Werte der Funktion f(x)=sin(x)
*/
public class Sinus extends Funktion {
  public Sinus(double min, double max) {
    super(min, max);
    Name="sin(x)";
  }
  
  public double getY(double x) { return Math.sin(x); }
}
