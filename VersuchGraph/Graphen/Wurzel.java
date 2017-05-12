/************************************************
**  Klasse berechnet Werte der Funktion f(x)=sqrt(x)
*/
public class Wurzel extends Funktion {
  public Wurzel(double xMin, double xMax){
    super(xMin,xMax);
    Name="Sqrt(x)";
  }
  //Die einfachere Konstruktor-Variante:
  public Wurzel(){
    super();
    xMin=0;
    Name="Sqrt(x)";
  }

  public double getY(double x){
    double y=Double.NaN;
    if ((xMin<=x) & (x<=xMax)) y=Math.sqrt(x);
    else System.out.println(x+" ist nicht im Def.Bereich");
    return y;
  }
}//Ende Wurzel
