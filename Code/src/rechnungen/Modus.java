package rechnungen;


import java.util.TreeMap;

public class Modus{
      TreeMap<Operation,Grenzen> map = new TreeMap<>();

      //Liest aus der Map herraus und fixiert Grenzen

      Grenzen getForOperationen(Operation op){
            //Gibt die Grenzen, für den Key in der Map gespeichert sind zurück
          return map.get(op);
      }

}