import java.awt.*;
/************************************************
**  Zeigt Dialogfenster zum Speichern der Grafik
*/
class SpeichernDialog extends FileDialog {
  public SpeichernDialog(Frame fr) {
    super(fr, "Grafik speichern (als *.gif)", SAVE);
  }
}
