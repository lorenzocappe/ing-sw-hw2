//file scritto da Cappellotto Lorenzo, matricola 1188257

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.NoSuchElementException;

/**
 * La Classe MapAdapter e' la classe che implementa l'iterfaccia {@link HMap} richiesta.
 *
 * Per implementarla l'ho costruita come adattatore di {@link Hashtable}.
 *
 * Questo adapter e' di tipo Object Adapter per un motivo:
 * 1. L'interfaccia di {@link HMap} non corrispondeva con i metodi proposti da hashtable.
 *
 * Gli oggetti di tipo MapAdapter non possono contenere oggetti nulli.
 * Tutti i metodi sono stati implementati usando come supporto il tipo di struttura dati fornita da HashTable e diverse classi di supporto tra cui:
 * 1. EntryAdapter che implementa l'interfaccia {@link HMap.Entry} in modo da poter memorizzare e gestire entry di tipo {chiave,valore}
 * 2. EntrySetView
 * 3. KeySetView
 * 3. ValueCollectionView
 * Ovvero delle viste proposte da questa struttura dati, le quali devono interfacciarsi con l'oggetto da cui sono state create
 * e fornire un modo diverso di vedere ed elaborare i dati contenuti nella struttura.
 *
 * In questo caso ho fornito i test per tutti i metodi della struttura e  per tutte le classi associate ad essa.
 */
public class MapAdapter implements HMap{
    private Hashtable<Object,Object> mappaHT;
    public MapAdapter() {
        mappaHT = new Hashtable();
    }

    /**
     * Il metodo clear() si occupera' di ripulire la struttura dati da tutti i dati in essa contenuti.
     */
    public void clear(){
        mappaHT.clear();
    }

    /**
     * Il metodo isEmpty() ritornera' true se la struttura dati e' vuota, false altrimenti.
      * @return boolean
     */
    public boolean isEmpty(){
        return mappaHT.isEmpty();
    }

    /**
     * Il metodo size() ritornera' il numero di elementi in essa contenuti.
     * @return int
     */
    public int size(){
        return mappaHT.size();
    }

    /**
     * Il metodo containsKey() si occupera' di ritornare true se nella mappa e' presente la chiave inserita come parametro,
     * false altrimenti.
     * Nel caso il parametro passato sia uguale a null il metodo lancera' l'eccezione NullPointerException
     *
     * @param key
     * @return boolean
     * @throws NullPointerException
     */
    public boolean containsKey(Object key) throws NullPointerException{
        if(null == key){
            throw new NullPointerException();
            //return false no, l'interfaccia java 1.4.2 dice che in caso di parametro null e' necessario lanciare un eccezione
        }
        return mappaHT.containsKey(key);
    }

    /**
     * Il metodo containsValue() si occupera' di ritornare true se nella mappa e' presente il valore inserito come parametro,
     * false altrimenti.
     * Nel caso il parametro passato sia uguale a null il metodo lancera' l'eccezione NullPointerException
     *
     * @param value
     * @return boolean
     * @throws NullPointerException
     */
    public boolean containsValue(Object value) throws NullPointerException{
        if(null == value){
            throw new NullPointerException();
        }
        return mappaHT.contains(value);
    }

    /**
     * Il metodo get() dovra' restituire il valore associato alla chiave inserita come parametro se la chiave e' contenuta nella mappa,
     * null altrimenti.
     * Se viene passata come parametro una chiave null il metodo lancera' l'eccezione NullPointerException
     *
     * @param key
     * @return Object
     * @throws NullPointerException
     */
    public Object get(Object key) throws NullPointerException{
        if(null == key){
            throw new NullPointerException();
        }
        return mappaHT.get(key);
    }

    /**
     * Il metodo put() si occupa di associare all'interno della mappa un valore a una chiave.
     * Se la chiave e' gia' presente all'interno della mappa, il valore ad essa associato verra' sostituito con quello passato come parametro,
     * altrimenti verra' creata una nuova entry avente quella coppia chiave-valore.
     * Verra' inoltre restituito il valore precedentemente associato alla chiave come parametro di ritorno se ve ne era uno,
     * altrimenti verra' restituito null.
     * Se la chiave o il valore passato per parametro sono uguali a null verra' lanciata l'eccezione NullPointerException.
     *
     * @param key
     * @param value
     * @return Object
     * @throws NullPointerException
     */
    public Object put(Object key, Object value) throws NullPointerException{
        if(null == key || null == value){
            throw new NullPointerException();
        }
        return mappaHT.put(key,value);
    }
    /**Il metodo putAll() si occupa di inserire all'interno della mappa tutti le entry contenute in una mappa passata come parametro.
     * Per ogni entry, se la chiave e' gia' presente all'interno della mappa, il valore ad essa associato verra' sostituito con quello passato come parametro,
     * altrimenti verra' creata una nuova entry avente quella coppia chiave-valore.
     * Per ogni entry, verra' inoltre restituito il valore precedentemente associato alla chiave come parametro di ritorno se ve ne era uno,
     * altrimenti verra' restituito null.
     * Se la mappa passata come parametro e' uguale a null verra' lanciata l'eccezione NullPointerException.
     *
     * @param m
     * @throws NullPointerException
     */
    public void putAll(HMap m) throws NullPointerException{
        if(m == null){
            throw new NullPointerException();
        }

        HSet setDiChiavi = m.keySet();
        HIterator it = setDiChiavi.iterator();

        while(it.hasNext()){
            Object tempKey = it.next();
            this.put(tempKey,m.get(tempKey));//Throw NullPointerException
        }
    }

    /**
     * Il metodo remove() si occupa di cancellare una entry {chiave, valore} contenuta nella mappa a partire dalla sua chiave.
     * Essendo le chiavi univoche all'interno della mappa viene sempre cancellata al piu' una entry.
     * Se viene passata come parametro una chiave null il metodo lancera' l'eccezione NullPointerException
     *
     * @param key
     * @return Object
     * @throws NullPointerException
     */
    public Object remove(Object key) throws NullPointerException{
        if(null == key){
            throw new NullPointerException();
        }
        return mappaHT.remove(key);
    }
    /**
     * Il metodo removeValue() non fa parte dell'interfaccia di HMap.
     * E' un helper method che permette di cancellare una entry {chiave, valore} contenuta nella mappa a partire dal suo valore.
     * Essendo i valori non univoci all'interno della mappa, ho deciso che venga cancellata al piu' una sola entry, la prima trovata.
     * Se viene passata come parametro un valore null il metodo lancera' l'eccezione NullPointerException
     *
     * @param value
     * @return boolean
     * @throws NullPointerException
     */
    private boolean removeValue(Object value) throws NullPointerException{
        if(null == value){
            throw new NullPointerException();
        }

        // ritorna la chiave
        Enumeration enu = mappaHT.keys();//rimuove una singola istanza avente quel valore
        Object temp;
        while (enu.hasMoreElements()){
            temp = enu.nextElement();
            if(this.get(temp) == value){
                this.remove(temp);
                return true;
            }
        }
        return false;
    }

    /**
     * Il metodo hashCode() non si deve interessare dell'ordine con cui sono state inserite le entry.
     * L'hashCode di una mappa vuota e' 0, altrimenti e' uguale alla somma degli hashCode delle entry in essa contenuti.
     *
     * @return int
     */
    public int	hashCode(){
        int hashcode = 0;
        Object temp;

        HSet setView = this.entrySet();
        HIterator it = setView.iterator();

        while(it.hasNext()){
            temp = it.next();
            if(null != temp){
                hashcode += temp.hashCode();
            }
        }
        return hashcode;
    }

    /**
     * Il metodo equals() non si deve interessare dell'ordine con cui sono state inserite le entry per verificare l'uguaglianza.
     * Se il parametro o e' null o di tipo diverso da HCollection il risultato sara' uguale a false,
     * altrimenti e' true se le due collezioni hanno la stessa dimensione e lo stesso hashcode.
     * (che equivale ad avere le stesse entry)
     *
     * @param o
     * @return boolean
     */
    public boolean equals(Object o){
        if(null == o || !(o instanceof HMap)){
            return false;
        }
        else{
            return (this.entrySet().equals(((HMap) o).entrySet()));
        }
    }

    /**
     * Il metodo entrySet() ritorna un set rappresentante un vista delle entry contenute nella mappa.
     * Offre tutti i metodi dell'interfaccia {@link HSet} a meno dei metodi add e addAll.
     *
     * @return HSet
     */
    public HSet entrySet(){
        return new EntrySetView();
    }
    /**
     * Il metodo keySet() ritorna un set rappresentante un vista delle chiavi contenute nella mappa.
     * Offre tutti i metodi dell'interfaccia {@link HSet} a meno dei metodi add e addAll.
     *
     * @return HSet
     */
    public HSet keySet() {
        return new KeySetView();
    }
    /**
     * Il metodo values() ritorna una collection rappresentante un vista dei valori contenuti nella mappa.
     * Offre tutti i metodi dell'interfaccia {@link HCollection} a meno dei metodi add e addAll.
     *
     * @return HCollection
     */
    public HCollection values(){
        return new ValueCollectionView();
    }



    /**
     * La classe EntrySetView e' stata creata solamente per soddisfare l'esigenza del metodo entrySet()
     * Permette di ottenere un set delle entry della mappa a cui e' associata, su cui e' possibile lavorare usando
     * tutti i metodi dell'interfaccia {@link HSet} a meno dei metodi add e addAll.
     *
     * Tutte le operazioni svolte sulla vista si riflettono sulla mappa a cui essa e' associata.
     * In caso la mappa venga modificata dopo la creazione della vista, non ne e' piu' garantito il funzionamento.
     *
     * Questa classe e' stata creata tenendo a mente anche che sarebbe stato necessario costruire le classi
     * KeySetView e ValueCollectionView, per questo motivo solamente i metodi contains, remove, iterator e equals sono
     * singolari a questa vista, tutti gli altri metodi si basano sui precedenti per eseguire tutte le operazioni.
     *
     * Questa classe contiene inoltre la classe EntrySetIterator.
     */
    protected class EntrySetView implements HSet{
        public EntrySetView(){}

        public boolean contains(Object o) {
            if(null == o || !(o instanceof HMap.Entry)){
                return false;
            }
            else{
                HMap.Entry ea = (HMap.Entry) o;
                return MapAdapter.this.containsKey(ea.getKey());
            }
        }
        public boolean remove(Object o) {
            if(null == o || !(o instanceof HMap.Entry)){
                return false;
            }
            else{
                HMap.Entry ea = (HMap.Entry) o;
                return (MapAdapter.this.remove(ea.getKey()) != null);
            }
        }
        public HIterator iterator() {
            return new EntrySetIterator();
        }

        public boolean add(Object o) {
            return false;
        }
        public boolean addAll(HCollection c) {
            return false;
        }

        public void clear() {
            MapAdapter.this.clear();
        }
        public boolean isEmpty() {
            return MapAdapter.this.isEmpty();
        }
        public int size() {
            return MapAdapter.this.size();
        }

        public boolean containsAll(HCollection c) throws NullPointerException {
            if(null == c){
                throw new NullPointerException();
            }

            HIterator it = c.iterator();
            while(it.hasNext()){
                if(!this.contains(it.next())){
                    return false;
                }
            }
            return true;
        }
        public boolean removeAll(HCollection c) throws NullPointerException{
            if(null == c){
                throw new NullPointerException();
            }

            HIterator it = c.iterator();
            boolean risultato = false;

            while(it.hasNext()){
                Object temp = it.next();
                if(this.contains(temp)){
                    this.remove(temp);
                    risultato = true;
                }
            }
            return risultato;
        }
        public boolean retainAll(HCollection c) throws NullPointerException{
            if(null == c){
                throw new NullPointerException();
            }
            boolean risultato = false;

            if(c.size() == 0){
                risultato = !(this.isEmpty());
                this.clear();
                return risultato;
            }

            HIterator it = this.iterator();
            while(it.hasNext()){
                Object temp = it.next();
                if(!c.contains(temp)){
                    it.remove();//cancella l'elemento appena controllato
                    risultato = true;
                }
            }
            return risultato;
        }

        public Object[] toArray() {
            Object[] risultato = new Object[this.size()];

            HIterator it = this.iterator();
            int i=0;
            while(it.hasNext()){
                risultato[i++] = it.next();
            }
            return risultato;
        }
        public Object[] toArray(Object[] a) throws NullPointerException{
            if(null == a){
                throw new NullPointerException();
            }
            if(a.length < this.size()){
                a = new Object[this.size()];
            }

            int i=0;
            Object temp;
            HIterator it = this.iterator();
            while(it.hasNext()){
                temp = it.next();
                a[i++] = temp;
            }
            if (a.length > this.size())
                a[this.size()] = null;

            return a;
        }

        public int hashCode(){
            int hashcode = 0;
            Object temp;
            HIterator it = this.iterator();
            while(it.hasNext()){
                temp = it.next();
                if(null != temp){
                    hashcode += temp.hashCode();
                }
            }
            return hashcode;
        }
        public boolean equals(Object o) {
            if (null == o || !(o instanceof HSet)) {
                return false;
            } else {

                return (this.size() == ((HSet) o).size() && this.hashCode() == o.hashCode());
            }
        }

        /**
         * La classe EntrySetIterator implementa l'interfaccia {@link HIterator} e permette di ritornare iteratori funzionanti
         * per la vista EntrySetView.
         * Questa classe implementando l'iterfaccia HIterator implementa di conseguenza anche il patter iterator di GOF.
         * Il motivo per cui questa classe implementa il pattern iterator e' principalmente per soddisfare le richieste della consegna.
         *
         * Tutte le operazioni svolte attraverso si riflettono sulla vista e di conseguenza sulla mappa associata.
         * In caso la mappa o la vista venga modificata dopo la creazione dell'iteratore, non ne e' piu' garantito il funzionamento.
         *
         * Questa classe e' stata costruita tenendo a mente di dover costruire gli iteratori anche per le altre due viste,
         * per questo motivo solamente i metodi next e remove sono specifici.
         */
        protected class EntrySetIterator implements HIterator{
            protected Enumeration enumerationI;
            protected Object lastElement;

            public EntrySetIterator(){
                enumerationI = MapAdapter.this.mappaHT.keys();
                lastElement = null;
            }

            public boolean hasNext() {
                return enumerationI.hasMoreElements();
            }
            public Object next() throws NoSuchElementException {
                lastElement = enumerationI.nextElement();
                return new EntryAdapter(lastElement,MapAdapter.this.mappaHT.get(lastElement));
            }
            public void remove() throws IllegalStateException{
                if(null == lastElement){
                    throw new IllegalStateException();
                }
                else{
                    MapAdapter.this.remove(lastElement);
                    lastElement = null;//questo impedisce di usare due volte consecutivamente remove
                }
            }
        }
    }

    /**
     * La classe KeySetView e' stata creata estendendo la classe EntrySetView ed e' stata creata solamente per il metodo keySet().
     * Permette di ottenere un set delle chiavi della mappa a cui e' associata, su cui e' possibile lavorare usando
     * tutti i metodi dell'interfaccia {@link HSet} a meno dei metodi add e addAll.
     *
     * Tutte le operazioni svolte sulla vista si riflettono sulla mappa a cui essa e' associata.
     * In caso la mappa venga modificata dopo la creazione della vista, non ne e' piu' garantito il funzionamento.
     *
     * Solamente i metodi contains, remove, iterator e equals sono singolari a questa vista,
     * tutti gli altri metodi si basano sui precedenti per eseguire tutte le operazioni.
     *
     * Questa classe contiene inoltre la classe KeySetIterator.
     */
    private class KeySetView extends EntrySetView implements HSet{
        public KeySetView() {super();}

        public boolean contains(Object o) {
            if(null == o){
                return false;
            }
            else{
                return MapAdapter.this.containsKey(o);
            }
        }
        public boolean remove(Object o) {
            if(null == o){
                return false;
            }
            else{
                return (MapAdapter.this.remove(o) != null);
            }
        }
        public HIterator iterator() {
            return new KeySetIterator();
        }

        public boolean equals(Object o) {
            if (null == o || !(o instanceof KeySetView)) {
                return false;
            } else {
                return (this.hashCode() == o.hashCode());
            }
        }

        /**
         * La classe KeySetIterator implementa l'interfaccia {@link HIterator} ed e' stata creata estendendo EntrySetIterator.
         * Permette di ritornare iteratori funzionanti per questa vista. Solamente i metodi next e remove sono specifici.
         * Questa classe implementando l'iterfaccia HIterator implementa di conseguenza anche il patter iterator di GOF.
         * Il motivo per cui questa classe implementa il pattern iterator e' principalmente per soddisfare le richieste della consegna.
         *
         * Tutte le operazioni svolte attraverso si riflettono sulla vista e di conseguenza sulla mappa associata.
         * In caso la mappa o la vista venga modificata dopo la creazione dell'iteratore, non ne e' piu' garantito il funzionamento.
         */
        private class KeySetIterator extends EntrySetIterator implements HIterator{
            public KeySetIterator(){
                super();
            }
            //va bene un enum di keys

            public Object next() throws NoSuchElementException{
                lastElement = enumerationI.nextElement();
                return lastElement;
            }
            public void remove() throws IllegalStateException{
                if(null == lastElement){
                    throw new IllegalStateException();
                }
                else{
                    MapAdapter.this.remove(lastElement);
                    lastElement = null;//questo impedisce di usare due volte consecutivamente remove
                }
            }
        }
    }

    /**
     * La classe ValueCollectionView e' stata creata estendendo la classe EntrySetView ed e' stata creata solamente per il metodo values().
     * Permette di ottenere una collection dei valori contenuti nella mappa associata e su cui e' possibile lavorare usando
     * tutti i metodi dell'interfaccia {@link HCollection} a meno dei metodi add e addAll.
     *
     * Tutte le operazioni svolte sulla vista si riflettono sulla mappa a cui essa e' associata.
     * In caso la mappa venga modificata dopo la creazione della vista, non ne e' piu' garantito il funzionamento.
     *
     * Solamente i metodi contains, remove, iterator e equals sono singolari a questa vista,
     * tutti gli altri metodi si basano sui precedenti per eseguire tutte le operazioni.
     *
     * Questa classe contiene inoltre la classe CollectionValueIterator.
     */
    private class ValueCollectionView extends EntrySetView implements HCollection{
        public ValueCollectionView(){super();}
        public boolean contains(Object o) {
            if(null == o){
                return false;
            }
            else{
                return MapAdapter.this.containsValue(o);//in caso ve ne siano piu' di una funziona ugualmente
            }
        }
        public boolean remove(Object o) {
            if(null == o){
                return false;
            }
            else{
                return MapAdapter.this.removeValue(o);//elimina una sola istanza avente quel valore
            }
        }
        public HIterator iterator() {
            return new ValueCollectionIterator();
        }

        public boolean equals(Object o) {
            if (null == o || !(o instanceof ValueCollectionView)) {
                return false;
            } else {
                return (this.hashCode() == o.hashCode());
            }
        }

        /**
         * La classe CollectionValueIterator implementa l'interfaccia {@link HIterator} ed e' stata creata estendendo EntrySetIterator.
         * Permette di ritornare iteratori funzionanti per questa vista. Solamente i metodi next e remove sono specifici.
         * Questa classe implementando l'iterfaccia HIterator implementa di conseguenza anche il patter iterator di GOF.
         * Il motivo per cui questa classe implementa il pattern iterator e' principalmente per soddisfare le richieste della consegna.
         *
         * Tutte le operazioni svolte attraverso si riflettono sulla vista e di conseguenza sulla mappa associata.
         * In caso la mappa o la vista venga modificata dopo la creazione dell'iteratore, non ne e' piu' garantito il funzionamento.
         */
        private class ValueCollectionIterator extends EntrySetIterator implements HIterator{
            public ValueCollectionIterator(){
                enumerationI = MapAdapter.this.mappaHT.elements();
                lastElement = null;
            }

            public Object next() throws NoSuchElementException{
                lastElement = enumerationI.nextElement();
                return lastElement;
            }
            public void remove() throws IllegalStateException{
                if(null == lastElement){
                    throw new IllegalStateException();
                }
                else{
                    MapAdapter.this.removeValue(lastElement);
                    lastElement = null;//questo impedisce di usare due volte consecutivamente remove
                }
            }
        }
    }

    
}
