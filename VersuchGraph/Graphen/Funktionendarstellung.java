/************************************************
**  Graphische Darstellung mehrerer Funktionen
*/
public class Funktionendarstellung
{
  public static void main(String[] args)  
  {
    //Funktionen zur Darstellung
    Liste fr=new Liste(); 
    
    //Funktion 
    Quadrat f1=new Quadrat(0,3);  
    //Die Funktion wird zur Darstellung bereitgestellt
    fr.addDarstellbar(f1);        
    
    Wurzel f2=new Wurzel(0,10);
    fr.addDarstellbar(f2);
    
    Sinus f3=new Sinus(-3.14,3.14);
    fr.addDarstellbar(f3);
    
    Gerade f4=new Gerade(-2,2);
    fr.addDarstellbar(f4);
    
    Ellipse f5=new Ellipse(0,6.28);
    fr.addDarstellbar(f5);
    
    double kX[]={1,2,3,4,5};
    double kY[]={2,4,6,8,10};
    InterpolPunkte f6=new InterpolPunkte(kX,kY);;
    fr.addDarstellbar(f6);
    
    //Darstellung der Funktionen
    //Grafik g1=new Grafik(f3);
    Grafik g2=new Grafik(fr);
  }
}
