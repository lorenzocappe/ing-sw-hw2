//file scritto da Cappellotto Lorenzo, matricola 1188257

/**
 * La Classe EntryAdapter e' una classe che implementa l'iterfaccia {@link HMap.Entry} ed e' stata costruita solamente per soddisfare
 * le esigenze di MapAdapter.
 *
 * Questa classe non e' propriamente un adattatore, ma l'ho chiamata ugualmente EntryAdapter perche' e' di supporto a MapAdapter
 * ed e' usata nel contesto di creazione di classi adattatori.
 *
 * La classe non supporta chiave o valore nulli.
 */
public class EntryAdapter implements HMap.Entry{
    private Object key;
    private Object value;

    public EntryAdapter(Object k, Object v) throws NullPointerException{
        if(null == v || null == k){
            throw new NullPointerException();
        }
        key = k;
        value = v;
    }

    public Object getKey() {
        return key;
    }
    public Object getValue(){
        return value;
    }
    public Object setValue(Object v) throws NullPointerException{
        if(null == v){
            throw new NullPointerException();
        }
        Object res = value;
        value = v;
        return res;
    }
    public int hashCode(){
        //la formula e' presa dalla documentazione 1.4.2
        return (this.getKey().hashCode() ^ this.getValue().hashCode());
    }
    public boolean equals(Object o){
        //la formula e' presa dalla documentazione 1.4.2
        if(null == o || !(o instanceof HMap.Entry)){
            return false;
        }
        else{
            HMap.Entry second = (HMap.Entry) o;
            return (this.getKey().equals(second.getKey()) && this.getValue().equals(second.getValue()));
        }
    }
}