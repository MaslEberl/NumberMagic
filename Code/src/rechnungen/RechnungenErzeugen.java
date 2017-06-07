package rechnungen;

/**
 * Created by julian on 31.05.17.
 */
public class RechnungenErzeugen {

    /*
    * Wie es funktionieren soll:
    *
    * Es wird unterteilt in einzelne Terme erzeugen.
    *
    * erzeugeTerm() wählt zuerst eine Operation und versucht, Zufallszahlen zu wählen,
    * die die übergebenen Grenzen für diese Operation einhalten. Nach ein paar
    * Versuchen soll es fehlschlagen.
    * Das soll so sein, damit man beim Erzeugen nicht in eine Sackgasse gerät.
    *
    * Wenn das erzeugen eines Terms fehlschlägt, sollen ein paar Terme davor
    * auch gelöscht werden. Rekursiv?, Jedes mal 5 Versuche?
    *
    *
    * */

    /* Der Modus ist eine Member-Variable,
    * Somit muss für jede Runde eine Instanz dieser Klasse erzeugt werden. */
    private Modus modus;
    private Terme terme;

    public RechnungenErzeugen(Modus modus) {
        this.modus = modus;
    }

    public Terme erzeuge()
    {

    }

    /** @return Gibt null zurück, wenn es fehlschlägt. */
    private Term erzeugeTerm(int position)
    {

    }
}
