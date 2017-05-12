package rechnungen;

import java.util.ArrayList;

/** In dieser Klasse werden die Zahlen mit Operationen gespeichert.<br>
 * Es gibt eine Member-Variable für den ersten Wert. Alle anderen
 * sind als Term (Paar von einer Operation mit dem darauf folgenden Wert)
 * in einer ArrayList gespeichert.<br>
 *
 * Weil diese Klasse von ArrayList erbt, können alle Methoden einer
 * ArrayList verwendet werden, z.B. terme.add(term1) */
public class Terme extends ArrayList<Term>
{
    double startwert;

}