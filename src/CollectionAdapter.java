//file scritto da Cappellotto Lorenzo, matricola 1188257
/**
 * La Classe CollectionAdapter e' la classe che implementa l'iterfaccia {@link HCollection} richiesta.
 *
 * Dato che e' stato richiesto anche di scrivere una classe che implementi l'interfaccia {@link HList},
 * ho preferito riusare il codice scritto in quella classe per quanto possibile in questa.
 *
 * Per farlo ho costruito CollectionAdapter come un adattatore di {@link ListAdapter} che,
 * implementando tutti i metodi richiesti da HCollection, conteneva gia' tutta la logica necessaria a implementare questa.
 * Un vantaggio di usare un adattatore di ListAdapater e' proprio il riutilizzo del codice.
 * Un possibile svantaggio e' che in caso debba modificare ListAdapter, CollectionAdapter risentirebbe dei cambiamenti,
 * questo solamente se la semantica dei metodi usati viene modificata, non se vengono inseriti nuovi metodi.
 * (Bisogna comunque tenere in conto che ListAdapter implementa l'interfaccia HList chee propone dei metodi precisi)
 *
 * Questo adapter e' di tipo Object Adapter principalmente per due motivi:
 * 1. Garantire una maggiore pulizia all'interno del codice, infatti se avessi deciso di costruire un Class Adapter
 * avrei dovuto estendere molti metodi di ListAdapter non proposti dall'interfaccia di HCollection e inserire UnsupportedOperationException.
 * 2. Le interfacce di adapter e adaptee non sono uguali, nonostante siano una sottointerfaccia dell'altra.
 *
 * Gli oggetti di tipo CollectionAdapter possono contenere oggetti nulli.
 * E' stato comunque necessario reimplementare i metodi hashCode ed Equals dato che hanno un comportamento diverso da quello di lista.
 *
 * Dato che ho sovrascritto solo i due metodi hashCode ed Equals ho eseguito i test solamente di quelli.
 */
public class CollectionAdapter implements HCollection{
    private ListAdapter lista;
    public CollectionAdapter(){ lista = new ListAdapter(); }

    /**
     * In Collection per il metodo equals() non e' importante l'ordine con cui sono stati inseriti gli elementi per verificare l'uguaglianza.
     * Se il parametro e' null o di tipo diverso da HCollection il risultato sara' uguale a false,
     * altrimenti e' true se le due collezioni hanno la stessa dimensione e lo stesso hashcode.
     * (che equivale ad avere gli stessi elementi)
     * (controllo anche la dimensione per verificare che il numero di elementi nulli nelle due collezioni sia uguale)
     *
     * @param o
     * @return boolean
     */
    public boolean equals(Object o) {
        if (null == o || !(o instanceof HCollection)) {
            return false;
        } else {
            return ((this.size() == ((HCollection) o).size()) && (this.hashCode() == o.hashCode()));
        }
    }
    /**
     * In Collection per il metodo hashCode() non e' importante l'ordine con cui sono stati inseriti gli elementi.
     * L'hashCode di una collezione vuota e' 0, altrimenti e' uguale alla somma degli hashCode degli elementi in essa contenuti.
     * (se contiene un elemento nullo il valore di hashCode non cambia)
     *
     * @return int
     */
    public int hashCode(){
        int hashcode = 0;
        Object temp;
        HIterator it = this.iterator();
        while(it.hasNext()){
            temp = it.next();
            if(null != temp){//se null += 0 non serve inserirlo
                hashcode += temp.hashCode();
            }
        }
        return hashcode;
    }

    public void clear(){ lista.clear(); }
    public int size(){ return lista.size(); }
    public boolean isEmpty(){ return lista.isEmpty(); }
    public HIterator iterator(){ return lista.iterator(); }

    public boolean add(Object o){ return lista.add(o); }
    public boolean addAll(HCollection c) throws NullPointerException{ return lista.addAll(c); }

    public boolean contains(Object o){ return lista.contains(o); }
    public boolean containsAll(HCollection c) throws NullPointerException{ return lista.containsAll(c); }

    public boolean remove(Object o){ return lista.remove(o); }
    public boolean removeAll(HCollection c) throws NullPointerException{ return lista.removeAll(c); }
    public boolean retainAll(HCollection c) throws NullPointerException{ return lista.retainAll(c); }

    public Object[] toArray(){ return lista.toArray(); }
    public Object[] toArray(Object[] a){ return lista.toArray(a); }

    public String toString(){ return lista.toString(); }
}
