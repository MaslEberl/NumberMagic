package rechnungen;

import java.util.ArrayList;
import java.util.Collection;

/** In dieser Klasse werden die Zahlen mit Operationen gespeichert.<br>
 * Es gibt eine Member-Variable für den ersten Wert. Alle anderen
 * sind als Term (Paar von einer Operation mit dem darauf folgenden Wert)
 * in einer ArrayList gespeichert.<br>
 *
 * Weil diese Klasse von ArrayList erbt, können alle Methoden einer
 * ArrayList verwendet werden, z.B. terme.add(term1) */
public class Terme extends ArrayList<Term>
{
    private double startwert;

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the list
     * @throws IllegalArgumentException if the specified initial capacity
     *                                  is negative
     */
    public Terme(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Constructs an empty list with an initial capacity of ten.
     */
    public Terme(double startwert) {
        this.startwert = startwert;
    }

    /**
     * Constructs an empty list with an initial capacity of ten.
     */
    public Terme() {
    }

    /**
     * Constructs a list containing the elements of the specified
     * collection, in the order they are returned by the collection's
     * iterator.
     *
     * @param c the collection whose elements are to be placed into this list
     * @throws NullPointerException if the specified collection is null
     */
    public Terme(Collection<? extends Term> c, double startwert) {
        super(c);
        this.startwert = startwert;
    }






    public double getStartwert() {
        return startwert;
    }

    public void setStartwert(double startwert) {
        this.startwert = startwert;
    }
}