//file scritto da Cappellotto Lorenzo, matricola 1188257
/**
 * La Classe SetAdapter e' la classe che implementa l'iterfaccia {@link HSet} richiesta.
 *
 * Dato che e' stato necessario scrivere una classe che implementi l'interfaccia {@link HCollection},
 * ho preferito riusare il codice scritto in quella classe per quanto possibile anche in questa.
 *
 * Per farlo ho costruito SetAdapter come un adattatore di {@link CollectionAdapter} che,
 * implementando tutti i metodi richiesti da HCollection, conteneva gia' tutta la logica necessaria a implementare questa
 * a meno del concetto di unicita' degli elementi in esso contenuti.
 * Un vantaggio di usare un adattatore di ListAdapater e' proprio il riutilizzo del codice.
 *
 * Questo adapter e' di tipo Class Adapter per diversi motivi:
 * 1. Le iterfacce {@link HSet} e {@link HCollection} propongono gli stessi metodi con le stesse firme.
 * 2. Richiede la stessa logica di {@link CollectionAdapter} a meno di un paio di metodi (di inserimento e verifica uguaglianza).
 *
 * Gli oggetti di tipo SetAdapter possono contenere oggetti nulli.
 *
 * E' stato necessario estendere e implementare i metodi add, addAll ed Equals dato che hanno un comportamenti diversi da quelli di Collection,
 * ho eseguito i test solamente di questi.
 */
public class SetAdapter extends CollectionAdapter implements HSet {
    public SetAdapter(){ super(); }

    /**
     * In Set un elemento viene inserito solamente se non e' gia presente.
     * Il metodo add() ritornera' true solamente se l'oggetto da inserire non e' gia' contenuto nella struttura dati.
     *
     * @param o
     * @return boolean
     */
    public boolean add(Object o){
        if(!this.contains(o)){
            super.add(o);
            return true;
        }
        return false;
    }
    /**
     * In Set un elemento viene inserito solamente se non e' gia presente.
     * Il metodo addAll() ritornera' true solamente se almeno un oggetto da inserire non e' gia' contenuto nella struttura dati.
     *
     * In caso il parametro sia uguale a null viene lanciata un eccezione di tipo NullPointerException
     *
     * @param c
     * @return boolean
     * @throws NullPointerException
     */

    public boolean addAll(HCollection c)throws NullPointerException {
        if(null == c){
            throw new NullPointerException();
        }

        HIterator it = c.iterator();
        boolean flag = false;

        while(it.hasNext()){//se flag e' false e avviene l'inserimento, flag diventa true, altrimenti rimane false
            flag = flag | this.add(it.next());
        }

        return flag;
    }
    /**
     * Il metodo equals() di Set e' equivale al metodo CollectionAdapter.equals, l'unica differenza sta nel fatto che
     * questo ritorna true se l'oggetto o e' di tipo HSet e non HCollection
     *
     * @param o
     * @return boolean
     */
    public boolean equals(Object o) {
        if (null == o || !(o instanceof HSet)) {
            return false;
        } else {
            return ((this.size() == ((HSet) o).size()) && (this.hashCode() == o.hashCode()));
        }
    }
}