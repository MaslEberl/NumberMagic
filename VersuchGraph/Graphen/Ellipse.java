/************************************************
**  Berechnet die Koordinaten der Punkte auf der
**  Ellipse: x(t)=2*cos(t), y(t)=4*sin(t)
*/
public class Ellipse extends ParamKurve {
  public Ellipse(double min, double max){
    super(min, max);
    Name="x=2*cos(t), y=4*sin(t)";
  }
  
  public double getX(double t) {return 2*Math.cos(t);}
  public double getY(double t) {return 4*Math.sin(t);}
}

































