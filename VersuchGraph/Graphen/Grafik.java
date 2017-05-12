import java.awt.*;
import java.awt.event.*;
import java.util.*;
/************************************************
**  Zeigt Fenster mit gezeichneten Kurven
*/
public class Grafik extends Frame implements ActionListener {
  private MenuBar menuBar;
  private Bild pc;
  private Darstellbar fun;
  TextField tf1,tf2,tf5,tf6,tf8;
  Button rc=new Button("Neu zeichnen");

  /************************************************
  **  Parameter:                                                <br>&nbsp;&nbsp;
  **    fr - Liste der Kurven
  */
  public Grafik(Liste fr) {
    int funktionenAnzahl=fr.getAnzahl();
    String beschriftung=" ";
    beschriftung=fr.getDarstellbar(0).getName();
    for (int i=1; i<funktionenAnzahl; i++) 
      beschriftung = beschriftung + "    &    " + fr.getDarstellbar(i).getName();
    
    setTitle(beschriftung);
    setLayout(new BorderLayout());
      
    // Zeichnen 
    pc=new Bild(fr,this);
    add("Center",pc);

    //Menu
    MenuBar menuBar  = new MenuBar();
    Menu programmMenu= new Menu("Programm", true);
    Menu graphikMenu = new Menu("Grafik", true);
  
    programmMenu.add("Beenden");
    //graphikMenu.add("Drucken");
    graphikMenu.add("Speichern");
    menuBar.add(programmMenu);
    menuBar.add(graphikMenu);
    setMenuBar(menuBar);
    
    // Textfelder 
    Panel np1=new Panel();
    Panel np=new Panel();
    np.setLayout(new GridLayout(2,6));
    np1.setLayout(new GridLayout(1,1));
    np.setBackground(Color.cyan);
    double h;
    MinMax mm=new MinMax();
    mm=pc.getMinMaxX();
    h=Math.round(mm.min*10000);
    h /=10000;
    Label l1=new Label("min X: "+h);
    h=Math.round(mm.max*10000);
    h /=10000;
    Label l2=new Label("max X: "+h);
    mm=pc.getMinMaxY();
    h=Math.round(mm.min*10000);
    h /=10000;
    Label l3=new Label("min Y: "+h);
    h=Math.round(mm.max*10000);
    h /=10000;
    Label l4=new Label("max Y: "+h);
    Label l8=new Label("Auflösung:");
    tf8=new TextField(5);
    tf8.setText(""+pc.getAufloesung());
    tf8.setBackground(Color.white);      

    Label l5=new Label("Maus X:");
    tf5=new TextField(5);
    Label l6=new Label("Maus Y:");
    tf6=new TextField(5);

    //Buttons
    rc.addActionListener(this);
    rc.setBackground(Color.lightGray);
     
    np.add(l1);
    np.add(l3);
    np.add(l5);
    np.add(l6);
    np.add(l8);

    np.add(l2);
    np.add(l4);
    np.add(tf5);
    np.add(tf6);
    np.add(tf8);

    np1.add(rc);

    add("North",np);
    add("South",np1);

    pack();
    setVisible(true);
  }


  /************************************************
  **  Parameter:                                                <br>&nbsp;&nbsp;
  **    f - Funktion
  */
  public Grafik(Funktion f) {
    setTitle(f.getName());
    setLayout(new BorderLayout());
      
    // Zeichnen 
    pc=new Bild(f,this);
    fun=f;
    add("Center",pc);
    double h;

    //Menu
    MenuBar menuBar  = new MenuBar();
    Menu programmMenu= new Menu("Programm", true);
    Menu graphikMenu = new Menu("Grafik", true);
  
    programmMenu.add("Beenden");
    graphikMenu.add("Drucken");
    graphikMenu.add("Speichern");
    menuBar.add(programmMenu);
    menuBar.add(graphikMenu);
    setMenuBar(menuBar);

    // Textfelder 
    Panel np=new Panel();
    Panel np1=new Panel();
    np1.setLayout(new GridLayout(1,1));
    
    np.setLayout(new GridLayout(2,5));
    np.setBackground(Color.cyan);
      
    MinMax mm=new MinMax();
    mm=f.getMinMaxX();
    h=Math.round(mm.min*10000);
    h /=10000;
    Label l1;
    l1=new Label("  min X: ");
    tf1=new TextField(5);
    tf1.setText(""+h);
    tf1.setBackground(Color.white);      
    
    h=Math.round(mm.max*10000);
    h /=10000;
    Label l2;
    l2=new Label("  max X: ");
    tf2=new TextField(5);
    tf2.setText(""+h);
    tf2.setBackground(Color.white);      
    
    Label l8=new Label("Auflösung:");
    tf8=new TextField(5);
    tf8.setText(""+pc.getAufloesung());
    tf8.setBackground(Color.white);      

    Label l5=new Label("Maus X:");
    tf5=new TextField(5);
    
    Label l6=new Label("Maus Y:");
    tf6=new TextField(5);

    //Buttons
    rc.addActionListener(this);
    rc.setBackground(Color.lightGray);
     
    Label l7=new Label();
      
    np.add(l1);
    np.add(l2);
    np.add(l5);
    np.add(l6);
    np.add(l8);

    np.add(tf1);
    np.add(tf2);
    np.add(tf5);
    np.add(tf6);
    np.add(tf8);

    np1.add(rc);

    add("North",np);
    add("South",np1);

    pack();
    setVisible(true);
  }

  /************************************************
  **  Parameter:                                                <br>&nbsp;&nbsp;
  **    f - ParamKurve
  */
  public Grafik(ParamKurve f) {
    setTitle(f.getName());
    setLayout(new BorderLayout());
      
    // Zeichnen 
    pc=new Bild(f,this);
    fun=f;
    add("Center",pc);
    double h;

    //Menu
    MenuBar menuBar= new MenuBar();
    Menu programmMenu= new Menu("Programm", true);
    Menu graphikMenu= new Menu("Grafik", true);
  
    programmMenu.add("Beenden");
    graphikMenu.add("Drucken");
    graphikMenu.add("Speichern");
    menuBar.add(programmMenu);
    menuBar.add(graphikMenu);
    setMenuBar(menuBar);

    // Textfelder 
    Panel np=new Panel();
    Panel np1=new Panel();
    np1.setLayout(new GridLayout(1,1));
    
    np.setLayout(new GridLayout(2,5));
    np.setBackground(Color.cyan);
      
    MinMax mm=new MinMax();
    mm=f.getMinMaxT();
    h=Math.round(mm.min*10000);
    h /=10000;
    Label l1;
    l1=new Label("  min t: ");
    tf1=new TextField(5);
    tf1.setText(""+h);
    tf1.setBackground(Color.white);      
    
    h=Math.round(mm.max*10000);
    h /=10000;
    Label l2;
    l2=new Label("  max t: ");
    tf2=new TextField(5);
    tf2.setText(""+h);
    tf2.setBackground(Color.white);      
    
    Label l8=new Label("Auflösung:");
    tf8=new TextField(5);
    tf8.setText(""+pc.getAufloesung());
    tf8.setBackground(Color.white);      

    Label l5=new Label("Maus X:");
    tf5=new TextField(5);
    
    Label l6=new Label("Maus Y:");
    tf6=new TextField(5);

    //Buttons
    rc.addActionListener(this);
    rc.setBackground(Color.lightGray);
     
    Label l7=new Label();
      
    np.add(l1);
    np.add(l2);
    np.add(l5);
    np.add(l6);
    np.add(l8);

    np.add(tf1);
    np.add(tf2);
    np.add(tf5);
    np.add(tf6);
    np.add(tf8);

    np1.add(rc);

    add("North",np);
    add("South",np1);

    pack();
    setVisible(true);
  }

  /************************************************
  **  Ereignissbehandlung
  */
  public void actionPerformed(ActionEvent e) {
    String cmd;
    cmd=e.getActionCommand();
      
    // Neuzeichnen
    if (cmd.equals("Neu zeichnen")) {
      
      //Änderung der Auflösung
      int al=(int)(new Double(tf8.getText())).doubleValue();
      if (fun==null) pc.setAufloesung(al);
      
      //Änderung der Intervallgrenzen bei einer Kurve
      else {
        double minXNeu, maxXNeu;
        minXNeu=(new Double(tf1.getText())).doubleValue();
        maxXNeu=(new Double(tf2.getText())).doubleValue();
        if(minXNeu>maxXNeu) {
          double tausche=minXNeu;
          minXNeu=maxXNeu;
          maxXNeu=tausche;
        }
        pc.setIA(minXNeu,maxXNeu,al);
      }
      pc.repaint();
    }
  }
  
  public boolean handleEvent(Event event) {
    if(event.id==Event.WINDOW_DESTROY) System.exit(0);
    
    if(event.target instanceof MenuItem) {
      MenuItem item=(MenuItem)event.target;
      
      if(item.getLabel().equals("Beenden")) {
        setVisible(false);
        dispose();
        System.exit(0);
      }

      if(item.getLabel().equals("Drucken")) {
        Toolkit tk= Toolkit.getDefaultToolkit();
        Properties pp= new Properties();
        PrintJob pj;
        pj= tk.getPrintJob(this, "Print", pp);
        if(pj!= null) {
          Graphics pg= pj.getGraphics();
          if(pg!= null) {
            pc.printAll(pg);
            pg.dispose();
            pj.end();
          } 
        }
      }

      if(item.getLabel().equals("Speichern")) {
        Image im= createImage(700,500);
        Graphics g= im.getGraphics();
        pc.paint(g);
        SpeichernDialog sd= new SpeichernDialog(this);
        sd.show();
        GIFEncoder.saveGIF(im,sd.getDirectory()+sd.getFile());
      }
    }
    return false;
  }
}
