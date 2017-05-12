/************************************************
**  Berechnet die Werte der Funktion f(x)=x*x
*/
public class Quadrat extends Funktion {
  public Quadrat(double min, double max) {
    super(min, max);
    Name="x^2";
  }

  public double getY(double x) { return x*x; }
}
