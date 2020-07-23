//file scritto da Cappellotto Lorenzo, matricola 1188257
import java.util.NoSuchElementException;
import java.util.Vector;

/**
 * La Classe ListAdapter e' la classe che implementa l'iterfaccia {@link HList} richiesta.
 *
 * Dato che e' stato richiesto anche di scrivere delle classi che implementino le interfacce {@link HCollection} e {@link HSet}
 * ho scritto una classe che si adatti ad essere usata anche in quelle classi.
 *
 * Per farlo ho costruito ListAdapter come un adattatore di {@link Vector}
 * Questo adapter e' di tipo Object Adapter per un motivo:
 * 1. L'interfaccia di {@link HList} non corrispondeva con i metodi proposti da Vector.
 *
 * Tra i punti piu' importanti c'e il metodo subList e la possibilita' di creare sottoliste che rappresentino una porzione di quella di partenza
 * e che offrano tutti i metodi dell'iterfaccia {@link HList} a meno del metodo subList stesso.
 * Non e' possibile creare sottoliste di sottoliste perche' questo metodo e' stato creato principalmente per non implementare metodi
 * che consentano di fare operazioni su intervalli di questa lista.
 *
 * Gli oggetti di tipo ListAdapter possono contenere oggetti nulli.
 * Tutti i metodi sono stati implementati usando come supporto il tipo di struttura dati fornita da Vector e diverse classi di supporto tra cui:
 * 1. ListIteratorAdapter la queale implementa l'interfaccia {@link HListIterator} e permette da costruzione sia di HIterator che HListIterator.
 * 2. SubListView che permette di creare sottoliste a partire dalla lista principale e di svolgere su di esse tutte le operazioni proposte nell'interfaccia {@link HList}.
 *
 * In questo caso ho fornito i test per tutti i metodi della struttura e  per tutte le classi associate ad essa.
 */
public class ListAdapter implements HList {
    private Vector vettore;

    public ListAdapter(){
        vettore = new Vector();
    }

    /**
     * Il metodo add() si occupera' di inserire alla fine della lista l'elemento passato come parametro.
     * Inoltre ritornera' true.
     *
     * @param o
     * @return boolean
     */
    public boolean add(Object o){
        vettore.addElement(o);
        return true;
    }
    /**
     * Il metodo addAll() si occupera' di inserire alla fine della lista tutti gli elementi all'interno della collezione passata come parametro.
     * Inoltre ritornera' true.
     * Se il parametro passato e' uguale a null verra' lanciata l'eccezione NullPointerException.
     *
     * @param c
     * @return boolean
     * @throws NullPointerException
     */
    public boolean addAll(HCollection c) throws NullPointerException {
        if(null == c){
            throw new NullPointerException();
        }

        HIterator it = c.iterator();
        boolean flag = it.hasNext();

        while(it.hasNext()){
            this.add(it.next());
        }

        return flag;
    }

    /**
     * Il metodo add(index) si occupera' di inserire in posizione specificata l'elemento passato come parametro.
     * Se l'indice passato si trova al di fuori dei limiti della lista verra' lanciata l'eccezione IndexOutOfBoundException.
     *
     * @param index
     * @param element
     * @throws IndexOutOfBoundsException
     */
    public void add(int index, Object element) throws IndexOutOfBoundsException {
        if(index < 0 || index > this.size()){
            throw new IndexOutOfBoundsException();
        }
        else{
            vettore.insertElementAt(element, index);
        }
    }

    /**
     * Il metodo addAll() si occupera' di inserire a partire dalla posizione specificata tutti gli elementi all'interno della collezione passata come parametro.
     * Inoltre ritornera' true.
     * Se la collezione passata e' uguale a null verra' lanciata l'eccezione NullPointerException.
     * Se l'indice passato si trova al di fuori dei limiti della lista verra' lanciata l'eccezione IndexOutOfBoundException.
     *
     * @param index
     * @param c
     * @return boolean
     * @throws IndexOutOfBoundsException
     * @throws NullPointerException
     */
    public boolean addAll(int index, HCollection c) throws IndexOutOfBoundsException, NullPointerException{
        if(null == c){
            throw new NullPointerException();
        }
        if(index < 0 || index > this.size()){//e' necessario fare il test anche qui perche' in caso di collection vuota non si entra mai nel ciclo
            throw new IndexOutOfBoundsException();
        }
        HIterator it = c.iterator();
        boolean flag = it.hasNext();

        while(it.hasNext()){
            this.add(index++,it.next());
            //this.add(index,it.next());
        }

        return flag;
    }

    /**
     * Il metodo contains() ritornera' true se l'elemento passato come parametro e' contenuto all'interno della lista, false altrimenti.
     *
     * @param o
     * @return boolean
     */
    public boolean contains(Object o){
        int indiceOggetto = this.indexOf(o);
        return (indiceOggetto != -1);
    }

    /**
     * Il metodo containsAll() ritornera' true se tutti gli elementi contenuti nella collezione passata come parametro
     * sono' contenuti all'interno della lista, false altrimenti.
     * Se la collezione passata e' uguale a null verra' lanciata l'eccezione NullPointerException.
     *
     * @param c
     * @return boolean
     * @throws NullPointerException
     */
    public boolean containsAll(HCollection c) throws NullPointerException{
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

    /**
     * Il metodo clear() si occupera' di svuotare la lista da qualsiasi elemento in essa contenuto.
     */
    public void clear(){
        vettore.removeAllElements();
    }

    /**
     * Il metodo equals() si deve interessare dell'ordine con cui sono stati inseriti gli elementi per verificare l'uguaglianza.
     * Se il parametro e' null o di tipo diverso da HList il risultato sara' uguale a false,
     * altrimenti e' true se le due lista hanno la stessa dimensione e lo stesso hashcode.
     * (che equivale ad avere gli stessi elementi nello stesso ordine)
     * (controllo anche la dimensione per verificare che il numero di elementi nulli nelle due liste sia uguale)
     *
     * @param o
     * @return boolean
     */
    public boolean equals(Object o){
        if(null == o || !(o instanceof HList)){
            return false;
        }
        else{
            return ((this.size() == ((HList) o) .size()) && (this.hashCode() == ((HList) o).hashCode()));
        }
    }

    /**
     * Il metodo hashCode() si deve interessare dell'ordine con cui sono stati inseriti gli elementi.
     * L'hashCode di una lista vuota e' 1, altrimenti e' uguale a
         HIterator i = this.iterator();
         while (i.hasNext()) {
         Object obj = i.next();
         hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
         }
     * (se contiene un elemento nullo il valore di hashCode non cambia)
     *
     * @return int
     */
    /**
     *
     * @return int
     */
    public int hashCode(){
        int hashCode = 1;
        HIterator i = this.iterator();
        while (i.hasNext()) {
            Object obj = i.next();
            hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
        }
        return hashCode;
    }

    /**
     * Il metodo isEmpty() ritorna true se la lista non contiene elementi, false altrimenti.
     *
     * @return boolean
     */
    public boolean isEmpty(){
        return vettore.isEmpty();
    }

    /**
     * Il metodo size() ritorna il numero di elementi contenuti nella lista.
     *
     * @return int
     */
    public int size(){
        return vettore.size();
    }

    /**
     * Il metodo indexOf() ritorna l'indice del primo elemento, in ordine, avente valore uguale a quello passato come parametro,
     * -1 se l'elemento non e' presente nella lista.
     *
     * @param o
     * @return int
     */
    public int indexOf(Object o){
        int indiceOggetto = vettore.indexOf(o);//eccezioni gia tirate da vector
        if(indiceOggetto < 0 || indiceOggetto >= this.size()){
            return -1;
        }
        else{
            return indiceOggetto;
        }
    }
    /**
     * Il metodo lastIndexOf() ritorna l'indice dell'ultimo elemento, in ordine, avente valore uguale a quello passato come parametro,
     * -1 se l'elemento non e' presente nella lista.
     *
     * @param o
     * @return int
     */
    public int lastIndexOf(Object o){
        int indiceOggetto = vettore.lastIndexOf(o);//eccezioni gia tirate da vector
        if(indiceOggetto < 0 || indiceOggetto >= size()){
            return -1;
        }
        else{
            return indiceOggetto;
        }
    }

    /**
     * Il metodo get() ritorna l'elemento contenuto nella lista avente indice uguale al parametro passato.
     * Se l'indice passato si trova al di fuori dei limiti della lista verra' lanciata l'eccezione IndexOutOfBoundException.
     *
     * @param index
     * @return Object
     * @throws IndexOutOfBoundsException
     */
    public Object get(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index >= this.size()){
            throw new IndexOutOfBoundsException();
        }
        else{
            return vettore.elementAt(index);
        }
    }

    /**
     * Il metodo set() modifica l'elemento contenuto nella lista avente indice uguale al parametro passato.
     * Inoltre ritorna il valore che aveva l'elemento prima di essere modificato.
     * Se l'indice passato si trova al di fuori dei limiti della lista verra' lanciata l'eccezione IndexOutOfBoundException.
     *
     * @param index
     * @param element
     * @return Object
     * @throws IndexOutOfBoundsException
     */
    public Object set(int index, Object element) throws IndexOutOfBoundsException{
        if(index < 0 || index >= this.size()){
            throw new IndexOutOfBoundsException();
        }
        Object risultato = vettore.elementAt(index);
        vettore.setElementAt(element, index);
        return risultato;
    }

    /**
     * Il metodo remove(index) rimuove l'elemento contenuto nella lista avente indice uguale al parametro passato.
     * Inoltre ritorna il valore dell'elemento eliminato.
     * Se l'indice passato si trova al di fuori dei limiti della lista verra' lanciata l'eccezione IndexOutOfBoundException.
     *
     * @param index
     * @return Object
     * @throws IndexOutOfBoundsException
     */
    public Object remove(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index >= this.size()){
            throw new IndexOutOfBoundsException();
        }
        Object risultato = vettore.elementAt(index);
        vettore.removeElementAt(index);
        return risultato;
    }

    /**
     * Il metodo remove(Object) rimuove, se presente, un solo elemento contenuto nella lista avente valore uguale al parametro passato.
     * Inoltre ritorna true se effettivamente un elemento e' stato rimosso, false altrimenti.
     * Se l'indice passato si trova al di fuori dei limiti della lista verra' lanciata l'eccezione IndexOutOfBoundException.
     *
     * @param o
     * @return boolean
     */
    public boolean remove(Object o){
        if(this.indexOf(o) == -1){
            return false;
        }
        else{
            vettore.removeElement(o);
            return true;
        }

    }
    /**
     * Il metodo removeAll() rimuove, se presenti, un solo elemento contenuto nella lista per ogni elemento contenuto nella collezione
     * passata come parametro.
     * Inoltre ritorna true se effettivamente almeno un elemento e' stato rimosso, false altrimenti.
     * Se la collezione passata e' uguale a null verra' lanciata l'eccezione NullPointerException.
     *
     * @param c
     * @return boolean
     */
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

    /**
     * Il metodo retainAll() rimuove tutti gli elementi contenuto nella lista che non appartengono anche alla collezione passata come parametro.
     * Inoltre ritorna true se effettivamente almeno un elemento e' stato rimosso, false altrimenti.
     * Se la collezione passata e' uguale a null verra' lanciata l'eccezione NullPointerException.
     *
     * @param c
     * @return boolean
     */
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

    /**
     * Il metodo iterator() ritorna un oggetto che implementa l'interfaccia {@link HIterator} che permette di scorrere
     * la lista, sapere se vi sono altri elementi da scorrere, leggerne il valore e rimuoverli.
     * L'iteratore inizialmente si trovera' in posizione 0.
     *
     * @return HIterator
     */
    public HIterator iterator(){
        return new ListAdapterIterator(this,0);
    }
    /**
     * Il metodo listIterator() ritorna un oggetto che implementa l'interfaccia {@link HListIterator} che permette di scorrere
     * la lista in entrambi i sensi.
     * L'iteratore inizialmente si trovera' in posizione 0.
     *
     * @return HListIterator
     */
    public HListIterator listIterator(){
        return listIterator(0);
    }
    /**
     * Il metodo listIterator(index) ritorna un oggetto che implementa l'interfaccia {@link HListIterator} che permette di scorrere
     * la lista in entrambi i sensi.
     * L'iteratore inizialmente si trovera' in posizione pari all'indice passato come parametro.
     * Se l'indice passato si trova al di fuori dei limiti della lista verra' lanciata l'eccezione IndexOutOfBoundException.
     *
     * @param index
     * @return HListIterator
     */
    public HListIterator listIterator(int index) throws IndexOutOfBoundsException{
        return new ListAdapterIterator(this, index);
    }

    /**
     * Il metodo subList() ritorna un oggetto di tipo sottolista, la quale inizia(inclusivo) e termina(esclusivo) negli indici passati come parametri.
     * Questo oggetto sara' solo una vista della lista originale, le modifiche apportate saranno visibili anche dalla lista di partenza.
     * Se la lista viene modificata dopo la creazione della sottolista, non e' garantita l'integrita' di quest'ultima.
     * Se almeno uno dei due indici passati si trovano al di fuori dei limiti della lista verra' lanciata l'eccezione IndexOutOfBoundException.
     *
     * @param fromIndex
     * @param toIndex
     * @return HList
     */
    public HList subList(int fromIndex, int toIndex) throws IndexOutOfBoundsException{
        return new SubListView(fromIndex,toIndex);
    }

    /**
     * il metodo toArray() ritorna un array di oggetti corrispondenti a tutti gli elementi contenuti nella lista.
     *
     * @return Object[]
     */
    public Object[] toArray(){
        Object[] risultato = new Object[this.size()];

        int i=0;
        HIterator it = this.iterator();
        while(it.hasNext()){
            risultato[i++] = it.next();
        }
        return risultato;
    }
    /**
     * Il metodo toArray(Object[]) ritorna un array di oggetti corrispondenti a tutti gli elementi contenuti nella lista.
     * Il metodo ritorna, se di dimensione sufficiente e tipo adatto, l'array passato come parametro, altrimenti ne creera' uno nuovo.
     * Se l'array passato come parametro e' di dimensione maggiore rispetto alla lista, l'elemento in posizione successiva
     * all'ultimo della lista viene impostato uguale a null.
     * Se il parametro passato e' uguale a null verra' lanciata l'eccezione NullPointerException.
     *
     * @param a
     * @return Object[]
     */
    public Object[] toArray(Object[] a) throws NullPointerException, ArrayStoreException{
        if(null == a){
            throw new NullPointerException();
        }
        if(a.length < this.size()){
            a = new Object[this.size()];
        }

        int i = 0;
        Object temp;
        HIterator it = this.iterator();
        while (it.hasNext()) {
            temp = it.next();
            a[i++] = temp;
        }
        if (a.length > this.size())
            a[this.size()] = null;

        return a;
    }

    public String toString(){
        HIterator it = this.iterator();
        Object temp;

        if(!it.hasNext()){return "{}";}

        String risultato = "{";
        while (it.hasNext()){
            temp = it.next();
            if(null != temp){
                risultato += temp.toString()+" ";
            }
            else{
                risultato += "null ";
            }
        }
        return risultato.substring(0, risultato.length()-1)+"}";
    }

    /**
     * La classe ListAdapterIterator implementa l'interfaccia {@link HListIterator} per ListAdapter e per SubListView.
     * Inoltre, viene usata anche per implementare l'interfaccia {@link HIterator} in quanto e' sovrainterfaccia della
     * precedente e non richiede particolari implementativi diversi.
     *
     * Questa classe implementando l'iterfaccia HIterator implementa di conseguenza anche il patter iterator di GOF.
     * Il motivo per cui questa classe implementa il pattern iterator e' principalmente per soddisfare le richieste della consegna.
     *
     * Questa classe e' stata costruita tenendo a mente di dover costruire degli iteratori sia per ListAdapter che per SubListView.
     * Questo e' stato possibile tenendo traccia a quale oggetto fa riferimento l'iteratore attraverso un attributo privato
     * e richiamando su di quello i metodi che renderanno trasparenti alla classe le modifiche fatte attraverso l'iteratore.
     *
     * In caso la lista o la sottolista venga modificata dopo la creazione dell'iteratore, non ne e' piu' garantito il funzionamento.
     *
     * Inoltre, la classe contiene altri due interi per capire quando possono essere richiamati i metodi set e remove dell'iteratore.
     */
    private class ListAdapterIterator implements HListIterator {
        private int posizione;
        private int posizionePrecedente;

        private HList oggettoSuCuiIterare;

        public ListAdapterIterator(HList oggetto) throws NullPointerException{
            this(oggetto,0);
        }
        public ListAdapterIterator(HList oggetto, int posizioneIniziale) throws IndexOutOfBoundsException,NullPointerException{
            if(null == oggetto){
                throw new NullPointerException();
            }
            oggettoSuCuiIterare = oggetto;
            if(posizioneIniziale < 0 || posizioneIniziale > oggettoSuCuiIterare.size()){
                throw new IndexOutOfBoundsException();
            }
            else{
                posizione = posizioneIniziale;
                posizionePrecedente = posizione;
            }
        }

        public boolean hasNext(){//posizione sara' sempre nei limiti consentiti
            return (posizione < oggettoSuCuiIterare.size());
        }
        public Object next() throws NoSuchElementException {
            if(!this.hasNext()){
                throw new NoSuchElementException();
            }
            else{
                posizionePrecedente = posizione;
                posizione++;
                return oggettoSuCuiIterare.get(posizionePrecedente);
            }
        }
        public int nextIndex(){
            return posizione;
        }

        public boolean hasPrevious(){
            return (posizione > 0);
        }
        public Object previous(){
            if(!this.hasPrevious()){
                throw new NoSuchElementException();
            }
            else{
                posizionePrecedente = posizione;
                posizione--;
                return oggettoSuCuiIterare.get(posizione);
            }
        }
        public int previousIndex(){
            return posizione-1;
        }

        public void add(Object o){
            //prec, inserisco dopo elemento
            //next, inserisco prima dell'elemento
            oggettoSuCuiIterare.add(posizione++,o);
            posizionePrecedente = posizione;
        }
        public void remove() throws IllegalStateException{
            //uso posizionePrecedente in modo da non poter rimuovere senza aver fatto next o previous
            //o dopo add o remove
            if(posizionePrecedente < posizione){
                oggettoSuCuiIterare.remove(posizionePrecedente);
                posizione = posizionePrecedente;
            }
            else if(posizionePrecedente > posizione){
                oggettoSuCuiIterare.remove(posizione);
                posizionePrecedente = posizione;
            }
            else{
                throw new IllegalStateException();
            }
        }
        public void set(Object o) throws IllegalStateException{
            //uso posizionePrecedente in modo da non poter usare set senza aver fatto next o previous
            //o dopo add o remove
            if(posizionePrecedente < posizione){
                oggettoSuCuiIterare.set(posizionePrecedente,o);
            }
            else if(posizionePrecedente > posizione){
                oggettoSuCuiIterare.set(posizione,o);
            }
            else{
                throw new IllegalStateException();
            }
        }
    }

    /**
     * La classe SubListView mi permette di implementare il metodo subList() della classe ListAdapter e gestire tutte le sue
     * implicazioni.
     * Questa classe implementa l'interfaccia {@link HList}.
     *
     * Questa classe come ListAdapter usa ListAdapaterIterator per funzionare.
     *
     * Ho deciso di non supportare il metodo subList all'interno delle sottoliste per due motivi:
     * 1. La documentazione java 2 collection framework List.java spiega come la creazione di sottoliste sia utile ad evitare
     * di creare metodi che operino con intervalli di indici.
     * 2. Per semplificare la struttura della vista e non creare una struttura ricorsiva, alla fine questa classe deve essere
     * una vista sulla lista di partenza e non una struttura a se stante.
     *
     * In caso la lista venga modificata dopo la creazione della sottolista, non ne e' piu' garantito il funzionamento,
     * infatti gli indici potrebbero disallinearsi dagli elementi previsti.
     *
     * Gli oggetti di tipo SubListVIew possono contenere oggetti nulli(questo deriva dalle scelte fatte su ListAdapter).
     */
    private class SubListView implements HList{
        private int fromIndex, toIndex;

        private SubListView(int posIniziale, int posFinale) throws IndexOutOfBoundsException{
            if(posIniziale < 0 || posFinale > ListAdapter.this.size() || posIniziale > posFinale) {
                throw new IndexOutOfBoundsException();
            }
            fromIndex = posIniziale;
            toIndex = posFinale;
        }

        public boolean add(Object o) {
            ListAdapter.this.add(toIndex,o);
            toIndex++;
            return true;// da documentazione true (as specified by Collection.add(E))
        }
        public boolean addAll(HCollection c) throws NullPointerException {
            if(null == c){
                throw new NullPointerException();
            }

            HIterator it = c.iterator();
            boolean flag = it.hasNext();
            while(it.hasNext()){
                this.add(it.next());
            }

            return flag;
        }
        public void add(int index, Object element) throws IndexOutOfBoundsException {
            if(index < 0 || index > toIndex - fromIndex){
                throw new IndexOutOfBoundsException();
            }
            else{
                ListAdapter.this.add(fromIndex + index,element);
                toIndex++;
            }
        }
        public boolean addAll(int index, HCollection c) throws NullPointerException, IndexOutOfBoundsException {
            if(null == c){
                throw new NullPointerException();
            }
            if(index < 0 || index > toIndex - fromIndex){//e' necessario fare il test anche qui perche' in caso di collection vuota non si entra mai nel ciclo
                throw new IndexOutOfBoundsException();
            }

            HIterator it = c.iterator();
            boolean flag = it.hasNext();
            while(it.hasNext()){
                this.add(index++,it.next());
            }
            return flag;
        }
        public boolean contains(Object o) {
            int indiceOggetto = this.indexOf(o);
            return (indiceOggetto != -1);
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
        public void clear() {
            //in questo modo posso eliminare tutti gli elementi appertenenti solo alla sottolista
            HIterator it = this.iterator();
            while (it.hasNext()){
                it.next();
                it.remove();
            }
        }
        public boolean equals(Object o){
            if(null == o || !(o instanceof HList)){
                return false;
            }
            else{
                return ((this.size() == ((HList) o) .size()) && (this.hashCode() == ((HList) o).hashCode()));
            }
        }
        public int hashCode(){
            int hashCode = 1;
            HIterator i = this.iterator();
            while (i.hasNext()) {
                Object obj = i.next();
                hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
            }
            return hashCode;
        }
        public boolean isEmpty() {
            return (fromIndex == toIndex);
        }
        public int size() {
            return toIndex-fromIndex;
        }
        public int indexOf(Object o) {
            int indiceOggetto = ListAdapter.this.indexOf(o);//eccezioni gia tirate da vector
            if(indiceOggetto == -1 || indiceOggetto < fromIndex || indiceOggetto >= toIndex){
                return -1;
            }
            else{
                return indiceOggetto - fromIndex;
            }
        }
        public int lastIndexOf(Object o) {
            int indiceOggetto = ListAdapter.this.lastIndexOf(o);//eccezioni gia tirate da vector
            if(indiceOggetto == -1 || indiceOggetto < fromIndex || indiceOggetto >= toIndex){
                return -1;
            }
            else{
                return indiceOggetto - fromIndex;
            }
        }
        public Object get(int index) throws IndexOutOfBoundsException {
            if(index < 0 || index >= toIndex-fromIndex){
                throw new IndexOutOfBoundsException();
            }
            else{
                return ListAdapter.this.get(fromIndex + index);
            }
        }
        public Object set(int index, Object element) throws IndexOutOfBoundsException {
            if(index < 0 || index >= toIndex-fromIndex){
                throw new IndexOutOfBoundsException();
            }
            Object risultato = vettore.elementAt(fromIndex + index);
            vettore.setElementAt(element,fromIndex + index);
            return risultato;
        }
        public Object remove(int index) throws IndexOutOfBoundsException {
            if(index < 0 || index >= toIndex-fromIndex){
                throw new IndexOutOfBoundsException();
            }
            Object risultato = vettore.elementAt(fromIndex + index);
            vettore.removeElementAt(fromIndex + index);
            toIndex--;
            return risultato;
        }
        public boolean remove(Object o) {
            if(this.indexOf(o) == -1){
                return false;
            }
            else{
                ListAdapter.this.remove(o);
                toIndex--;
                return true;
            }
        }
        public boolean removeAll(HCollection c) throws NullPointerException {
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
        public boolean retainAll(HCollection c) throws NullPointerException {
            if(null == c){
                throw new NullPointerException();
            }

            HIterator it = this.iterator();
            boolean risultato = false;
            while(it.hasNext()){
                Object temp = it.next();
                if(!c.contains(temp)){
                    it.remove();//cancella l'elemento appena controllato
                    risultato = true;
                }
            }
            return risultato;
        }
        public HIterator iterator() {
            return new ListAdapterIterator(this,0);/*SubListIterator(0);*/
        }
        public HListIterator listIterator() {
            return this.listIterator(0);
        }
        public HListIterator listIterator(int index) throws IndexOutOfBoundsException {
            return new ListAdapterIterator(this,index);/*SubListIterator(index);*/
        }
        public HList subList(int fromIndex, int toIndex){//non supportata
            return null;
        }
        public Object[] toArray() {
            Object[] risultato = new Object[this.size()];

            int i=0;
            HIterator it = this.iterator();
            while(it.hasNext()){
                risultato[i++] = it.next();
            }
            return risultato;
        }
        public Object[] toArray(Object[] a) throws NullPointerException, ArrayStoreException{
            if(null == a){
                throw new NullPointerException();
            }
            if(a.length < this.size()){
                a = new Object[this.size()];
            }

            int i = 0;
            Object temp;
            HIterator it = this.iterator();
            while (it.hasNext()) {
                temp = it.next();
                a[i++] = temp;
            }
            if (a.length > this.size())
                a[this.size()] = null;

            return a;
        }
        public String toString(){
            HIterator it = this.iterator();
            Object temp;
            if(!it.hasNext()){return "{}";}

            String risultato = "{";
            while (it.hasNext()){
                temp = it.next();
                if(null != temp){
                    risultato += temp.toString()+" ";
                }
                else{
                    risultato += "null ";
                }
            }
            return risultato.substring(0, risultato.length()-1)+"}";
        }
    }
 }
