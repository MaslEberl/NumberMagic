/************************************************
**  Berechnet die Koordinaten der Punkte der Geraden x(t)=2*t y(t)=4*t
*/
public class Gerade extends ParamKurve {
  public Gerade(double min, double max){
    super(min, max);
    Name="x=2*t, y=4*t";
  }
  public double getX(double t) { return 2*t; }
  public double getY(double t) { return 4*t; }
}
