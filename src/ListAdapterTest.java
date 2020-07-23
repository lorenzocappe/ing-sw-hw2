//file scritto da Cappellotto Lorenzo, matricola 1188257
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Vector;

import static org.junit.Assert.*;

public class ListAdapterTest{
    private HList miaLista;
    private Vector vettoreObj;
    private Object obj = new Object();

    @org.junit.Before
    public void setUp() {
        miaLista = new ListAdapter();
        vettoreObj = new Vector();

        vettoreObj.addElement("primo elemento");
        vettoreObj.addElement(obj);
        vettoreObj.addElement(null);
        vettoreObj.addElement("elemento duplicato");
        vettoreObj.addElement(2);
        vettoreObj.addElement(4.5);
        vettoreObj.addElement("elemento duplicato");
    }

    /*
    @org.junit.Test
    public void metodoClear(){}
    @org.junit.Test
    public void metodoIsEmpty(){}
    @org.junit.Test
    public void metodoSize(){}
    */
    @org.junit.Test
    public void metodoHashCode() {
        HList altraLista = new ListAdapter();

         miaLista.hashCode();
        assertEquals(1, miaLista.hashCode());
        assertEquals(altraLista.hashCode(), miaLista.hashCode());

        Object temp;
        Enumeration enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            temp = enu.nextElement();
            miaLista.add(temp);
            altraLista.add(temp);
        }
        assertEquals(altraLista.hashCode(), miaLista.hashCode());
        altraLista.remove("primo elemento");
        assertNotEquals(altraLista.hashCode(), miaLista.hashCode());
        altraLista.add("primo elemento");
        assertNotEquals(altraLista.hashCode(), miaLista.hashCode());//la posizione e' importante
    }

    @org.junit.Test
    public void metodoEquals() {
        HList altraLista = new ListAdapter();

        assertFalse(miaLista.equals(null));
        assertFalse(miaLista.equals(new SetAdapter()));

        assertTrue(miaLista.equals(altraLista));
        assertTrue(miaLista.equals(miaLista));//riflessivo, deve funzionare
        assertEquals(altraLista.equals(miaLista),miaLista.equals(altraLista));//controllo simmetria

        Enumeration enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            miaLista.add(enu.nextElement());
        }
        assertFalse(miaLista.equals(altraLista));
        assertFalse(altraLista.equals(miaLista));

        enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            altraLista.add(enu.nextElement());
        }
        assertTrue(miaLista.equals(altraLista));
        assertEquals(altraLista.equals(miaLista),miaLista.equals(altraLista));//controllo simmetria

        altraLista.remove("primo elemento");
        assertFalse(miaLista.equals(altraLista));
        altraLista.add("primo elemento");
        assertFalse(miaLista.equals(altraLista));//la posizione e' importante
    }

    @org.junit.Test
    public void metodoToArray1() {
        assertEquals(0,miaLista.toArray().length);

        Object[] mioVettore = new Object[4];
        for(int i=0; i < mioVettore.length; i++){
            mioVettore[i] = "A"+i;
            miaLista.add(mioVettore[i]);
        }
        assertEquals(mioVettore.length,miaLista.size());
        assertArrayEquals(mioVettore,miaLista.toArray());
    }

    @org.junit.Test
    public void metodoToArray2CasiLimite() {
        assertEquals(0, miaLista.toArray(new Object[0]).length);
        assertThrows(NullPointerException.class, () -> miaLista.toArray(null));

        miaLista.add(1.2);
        miaLista.add(2);
        miaLista.add(3);
        assertThrows(ArrayStoreException.class, () -> miaLista.toArray(new String[3]));
    }
    @org.junit.Test
    public void metodoToArray2CasoNormale() {
        Object[] mioVettore = new Object[4];
        Object[] vettoreToArray1 = new Object[4];
        Object[] vettoreToArray2 = new Object[6];
        Object[] vettoreToArray3 = new Object[2];

        for(int i=0; i < mioVettore.length; i++){
            mioVettore[i] = "A"+i;
            miaLista.add(mioVettore[i]);
        }
        miaLista.toArray(vettoreToArray1);
        miaLista.toArray(vettoreToArray2);
        Object[] vettoreToArray4 = miaLista.toArray(vettoreToArray3);

        assertEquals(mioVettore.length,vettoreToArray1.length);
        assertArrayEquals(mioVettore,vettoreToArray1);

        assertNotEquals(mioVettore.length,vettoreToArray3.length);
        assertEquals(mioVettore.length,vettoreToArray4.length);
        assertArrayEquals(mioVettore,vettoreToArray4);

        assertNotEquals(mioVettore.length,vettoreToArray2.length);
        assertEquals(null,vettoreToArray2[miaLista.size()]);
    }

    @org.junit.Test
    public void metodoContains() {
        Enumeration enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            miaLista.add(enu.nextElement());
        }

        assertTrue(miaLista.contains("primo elemento"));
        assertTrue(miaLista.contains(null));

        assertFalse(miaLista.contains(new Object()));//e' giusto perche due nuovi oggetti vuoti sono oggetti diversi
        assertFalse(miaLista.contains("secondo elemento"));
    }

    @org.junit.Test
    public void metodoContainsAllCasiLimite() {
        assertEquals(0,miaLista.size());
        assertThrows(NullPointerException.class, () -> miaLista.containsAll(null));
        assertEquals(0,miaLista.size());
    }
    @org.junit.Test
    public void metodoContainsAllCasoNormale() {
        HCollection coll = new CollectionAdapter();

        assertTrue(miaLista.containsAll(coll));

        Enumeration enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            miaLista.add(enu.nextElement());
        }

        vettoreObj.remove("primo elemento");
        vettoreObj.remove(obj);
        enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            coll.add(enu.nextElement());
        }
        assertTrue(miaLista.containsAll(coll));

        coll.add("secondo elemento");
        assertFalse(miaLista.containsAll(coll));
    }

    @org.junit.Test
    public void metodoAdd() {
        assertNotEquals(vettoreObj.size(),miaLista.size());

        Enumeration enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            assertTrue(miaLista.add(enu.nextElement()));
        }
        assertArrayEquals(vettoreObj.toArray(),miaLista.toArray());
        assertEquals(vettoreObj.size(),miaLista.size());
    }

    @org.junit.Test
    public void metodoAddAllCasiLimite() {
        assertEquals(0,miaLista.size());
        assertThrows(NullPointerException.class, () -> miaLista.addAll(null));
        assertEquals(0,miaLista.size());
    }
    @org.junit.Test
    public void metodoAddAllCasoNormale() {
        HCollection coll = new CollectionAdapter();

        assertFalse(miaLista.addAll(coll));

        Enumeration enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            coll.add(enu.nextElement());
        }

        assertTrue(miaLista.addAll(coll));
        assertArrayEquals(coll.toArray(),miaLista.toArray());
    }

    @org.junit.Test
    public void metodoAddAtCasiLimite() {
        assertEquals(0,miaLista.size());
        assertThrows(IndexOutOfBoundsException.class, () -> miaLista.add(-1,"elemento"));
        assertThrows(IndexOutOfBoundsException.class, () -> miaLista.add(1,"elemento"));
        assertEquals(0,miaLista.size());

        miaLista.add(2);
        miaLista.add(2);
        miaLista.add(2);
        miaLista.add(2);
        assertEquals(4,miaLista.size());
        assertThrows(IndexOutOfBoundsException.class, () -> miaLista.add(6,null));
        assertEquals(4,miaLista.size());
    }
    @org.junit.Test
    public void metodoAddAtCasoNormale() {
        miaLista.add(0,1);//1
        miaLista.add(0,2);//2 1
        miaLista.add(1,3);//2 3 1
        miaLista.add(4);               //2 3 1 4
        miaLista.add(4,5);//2 3 1 4 5
        assertArrayEquals(new Object[]{2,3,1,4,5},miaLista.toArray());
    }

    @org.junit.Test
    public void metodoAddAllAtCasiLimite() {
        assertEquals(0,miaLista.size());
        assertThrows(NullPointerException.class, () -> miaLista.addAll(0,null));
        assertThrows(IndexOutOfBoundsException.class, () -> miaLista.addAll(-1,new CollectionAdapter()));
        assertThrows(IndexOutOfBoundsException.class, () -> miaLista.addAll(1,new CollectionAdapter()));
        assertEquals(0,miaLista.size());
    }
    @org.junit.Test
    public void metodoAddAllAtCasoNormale() {
        miaLista.add('a');
        miaLista.add('b');
        miaLista.add('c');
        miaLista.add('d');
        //lista: a b c d

        HCollection coll = new CollectionAdapter();
        assertFalse(miaLista.addAll(0, coll));

        Enumeration enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            coll.add(enu.nextElement());
        }
        miaLista.addAll(2,coll);
        //lista: a b -coll- c d

        vettoreObj.insertElementAt('b',0);
        vettoreObj.insertElementAt('a',0);
        vettoreObj.addElement('c');
        vettoreObj.addElement('d');

        assertEquals(vettoreObj.size(),miaLista.size());
        for(int i=0; i<miaLista.size(); i++){
            assertTrue(miaLista.contains(vettoreObj.get(i)));
        }
    }

    @org.junit.Test
    public void metodoIndexOf(){
        assertEquals(-1,miaLista.indexOf(null));
        assertEquals(-1,miaLista.indexOf("primo elemento"));

        Enumeration enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            miaLista.add(enu.nextElement());
        }

        assertEquals(0,miaLista.indexOf("primo elemento"));
        assertEquals(2,miaLista.indexOf(null));
        assertEquals(3,miaLista.indexOf("elemento duplicato"));

        assertEquals(-1,miaLista.indexOf("secondo elemento"));

        assertEquals(2,miaLista.indexOf(null));
        miaLista.remove(null);
        assertNotEquals(2,miaLista.indexOf(null));
    }

    @org.junit.Test
    public void metodoLastIndexOf(){
        assertEquals(-1,miaLista.lastIndexOf(null));
        assertEquals(-1,miaLista.lastIndexOf("primo elemento"));

        Enumeration enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            miaLista.add(enu.nextElement());
        }

        assertEquals(miaLista.size()-1,miaLista.lastIndexOf("elemento duplicato"));
        assertNotEquals(3, miaLista.lastIndexOf("elemento duplicato"));

        assertEquals(-1, miaLista.lastIndexOf("secondo elemento"));
    }

    @org.junit.Test
    public void metodoGetCasiLimite() {
        assertEquals(0, miaLista.size());
        assertThrows(IndexOutOfBoundsException.class, () -> miaLista.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> miaLista.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> miaLista.get(1));
        assertEquals(0, miaLista.size());
    }
    @org.junit.Test
    public void metodoGetCasoNormale(){
        Enumeration enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            miaLista.add(enu.nextElement());
        }

        assertEquals(vettoreObj.elementAt(4),miaLista.get(4));
        assertEquals("primo elemento",miaLista.get(0));
    }

    @org.junit.Test
    public void metodoSetCasiAnomali() {
        assertEquals(0,miaLista.size());
        assertThrows(IndexOutOfBoundsException.class, () -> miaLista.set(-1,null));
        assertThrows(IndexOutOfBoundsException.class, () -> miaLista.set(0,null));
        assertThrows(IndexOutOfBoundsException.class, () -> miaLista.set(1,null));
        assertEquals(0,miaLista.size());
    }
    @org.junit.Test
    public void metodoSetCasoNormale() {
        Enumeration enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            miaLista.add(enu.nextElement());
        }
        assertEquals("primo elemento", miaLista.get(0));
        assertEquals("primo elemento",miaLista.set(0,"primo elemento nuovo"));
        assertEquals("primo elemento nuovo", miaLista.get(0));

        assertEquals(obj, miaLista.get(1));
        assertEquals(obj,miaLista.set(1,null));
        assertEquals(null, miaLista.get(1));

    }

    @org.junit.Test
    public void metodoRemove() {
        assertFalse(miaLista.remove(null));
        assertFalse(miaLista.remove("primo elemento"));

        Enumeration enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            miaLista.add(enu.nextElement());
        }
        assertTrue(miaLista.remove("elemento duplicato"));
        assertTrue(miaLista.remove("elemento duplicato"));
        assertFalse(miaLista.remove("elemento duplicato"));

        assertFalse(miaLista.remove("secondo elemento"));
    }

    @org.junit.Test
    public void metodoRemoveAtCasiLimite(){
        assertTrue(miaLista.isEmpty());
        assertThrows(IndexOutOfBoundsException.class, () -> miaLista.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> miaLista.remove(0));
        assertThrows(IndexOutOfBoundsException.class, () -> miaLista.remove(1));
        assertTrue(miaLista.isEmpty());
    }
    @org.junit.Test
    public void metodoRemoveAtCasoNormale(){
        assertTrue(miaLista.isEmpty());

        Enumeration enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            miaLista.add(enu.nextElement());
        }

        assertEquals(null,miaLista.remove(2));
        assertEquals("primo elemento",miaLista.remove(0));
    }

    @org.junit.Test
    public void metodoRemoveAllCasoLimite() {
        assertTrue(miaLista.isEmpty());
        assertThrows(NullPointerException.class, () -> miaLista.removeAll(null));
        assertTrue(miaLista.isEmpty());
    }
    @org.junit.Test
    public void metodoRemoveAllCasoNormale() {
        HCollection coll = new CollectionAdapter();
        assertFalse(miaLista.removeAll(coll));
        assertTrue(miaLista.isEmpty());

        Enumeration enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            miaLista.add(enu.nextElement());
        }

        vettoreObj.remove("elemento duplicato");
        vettoreObj.remove(null);
        enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            coll.add(enu.nextElement());
        }

        assertTrue(miaLista.removeAll(coll));
        assertEquals(2,miaLista.size());
        assertTrue(miaLista.removeAll(coll));
        assertEquals(1,miaLista.size());

        assertFalse(miaLista.removeAll(coll));
        assertEquals(1,miaLista.size());
    }

    @org.junit.Test
    public void metodoRetainAllCasoLimite() {
        assertTrue(miaLista.isEmpty());
        assertThrows(NullPointerException.class, () -> miaLista.retainAll(null));
        assertTrue(miaLista.isEmpty());
    }
    @org.junit.Test
    public void metodoRetainAllCasoNormale() {
        HCollection coll = new CollectionAdapter();
        assertFalse(miaLista.retainAll(coll));

        assertTrue(miaLista.isEmpty());
        miaLista.add(1);
        assertFalse(miaLista.isEmpty());
        assertTrue(miaLista.retainAll(coll));
        assertTrue(miaLista.isEmpty());

        Enumeration enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            miaLista.add(enu.nextElement());
        }

        coll.add("primo elemento");
        coll.add("elemento duplicato");
        coll.add(null);

        assertTrue(miaLista.retainAll(coll));
        assertEquals(4,miaLista.size());
        assertFalse(miaLista.retainAll(coll));
        assertEquals(4,miaLista.size());
    }

    @org.junit.Test
    public void metodoIterator() {
        //controlla prima che add funzioni
        Object[] miovettore = new Object[4];

        //defect test
        HIterator it = miaLista.iterator();
        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, () -> miaLista.iterator().next());
        assertThrows(IllegalStateException.class, () -> miaLista.iterator().remove());

        //validation test
        for(int i=0; i < miovettore.length; i++){
            miovettore[i] = "A"+i;
            miaLista.add(miovettore[i]);
        }

        it = miaLista.iterator();
        int i = 0;
        while(it.hasNext()){//verifico che hasNext e next funzionino correttamente
            assertEquals(miovettore[i++],it.next());
        }
        //controllo che remove funzioni correttamente
        assertThrows(IllegalStateException.class, () -> miaLista.iterator().remove());
        assertThrows(IllegalStateException.class, () -> {
            HIterator itth = miaLista.iterator();
            itth.next();
            itth.remove();
            itth.remove();
        });
        it = miaLista.iterator();
        while(it.hasNext()){
            it.next();
            it.remove();
        }
        assertEquals(0,miaLista.size());
    }

    @org.junit.Test
    public void metodoListIterator(){
        HListIterator it = miaLista.listIterator();

        assertFalse(it.hasNext());
        assertFalse(it.hasPrevious());
        assertEquals(-1, it.previousIndex());
        assertEquals(0, it.nextIndex());

        assertThrows(NoSuchElementException.class, () -> miaLista.listIterator().next());
        assertThrows(NoSuchElementException.class, () -> miaLista.listIterator().previous());
        assertThrows(IllegalStateException.class, () -> miaLista.listIterator().remove());
        assertThrows(IllegalStateException.class, () -> miaLista.listIterator().set(null));

        it.add(1);
        assertThrows(IllegalStateException.class, () -> miaLista.listIterator().remove());
        assertThrows(IllegalStateException.class, () -> miaLista.listIterator().set(0));

        miaLista.clear();
        it = miaLista.listIterator();

        //test di add e set da lista vuota
        it.add(-1);// |
        it.add(-2);//-1 |
                   //-1 -2 |
        assertEquals(2, miaLista.size());

        assertFalse(it.hasNext());
        assertTrue(it.hasPrevious());
        it.previous();//-1 | -2

        assertTrue(miaLista.contains(-1));
        assertTrue(miaLista.contains(-2));
        assertFalse(miaLista.contains(0));
        it.set(0);//-1 | 0
        assertTrue(miaLista.contains(0));
        assertFalse(miaLista.contains(-2));
        assertEquals(2, miaLista.size());

        assertTrue(miaLista.contains(0));
        assertTrue(miaLista.contains(-1));
        assertFalse(miaLista.contains(1));
        it.previous();// | -1 0
        it.next();    // -1 | 0
        //test di set dopo next
        it.set(1);    //  1 | 0
        assertTrue(miaLista.contains(1));
        assertFalse(miaLista.contains(-1));
        assertEquals(2, miaLista.size());

        //popolo lista
        miaLista.clear();//se uso la mappa il comportamento dell'iteratore non e' garantito, devo ri-inizializzarlo
        it = miaLista.listIterator();
        Object[] vettoreObj = new Object[6];
        for(int i=0; i < vettoreObj.length; i++) {
            vettoreObj[i] = "A" + i;
            miaLista.add(vettoreObj[i]);
        }

        //test hasPrevious previousIndex e previous in contemporanea da fine a inizio lista
        while(it.hasPrevious()){
            assertEquals(vettoreObj[it.previousIndex()],it.previous());
        }

        //test hasNext nextIndex e next in contemporanea da inizio a fine lista
        while(it.hasNext()){
            assertEquals(vettoreObj[it.nextIndex()],it.next());
        }

        //test add piu' accurato di add con lista gia' popolata
        // | A0 A1 A2 A3 A4 A5
        it = miaLista.listIterator();
        it.next();it.next();
        // A0 A1 | A2 A3 A4 A5
        it.add(0);
        // A0 A1 0 | A2 A3 A4 A5

        assertEquals(0,it.previous());
        assertEquals(2,miaLista.indexOf(0));
        assertEquals(0,it.next());

        it.next();it.next();it.previous();it.previous();
        // A0 A1 0 | A2 A3 A4 A5
        it.add(1);
        // A0 A1 0 1 | A2 A3 A4 A5

        assertNotEquals(1,it.next());
        assertNotEquals(1,it.previous());
        assertEquals(1,it.previous());

        it = miaLista.listIterator();
        while(it.hasNext()){
            it.next();
            it.remove();
        }
        assertTrue(miaLista.isEmpty());
    }
    @org.junit.Test
    public void metodoListIteratorAt(){
        assertThrows(IndexOutOfBoundsException.class, () -> miaLista.listIterator(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> miaLista.listIterator(10));

        HListIterator it = miaLista.listIterator(0);
        assertFalse(it.hasNext());
        assertFalse(it.hasPrevious());
        assertEquals(-1, it.previousIndex());
        assertEquals(0, it.nextIndex());

        assertThrows(NoSuchElementException.class, () -> miaLista.listIterator().next());
        assertThrows(NoSuchElementException.class, () -> miaLista.listIterator().previous());
        assertThrows(IllegalStateException.class, () -> miaLista.listIterator().remove());
        assertThrows(IllegalStateException.class, () -> miaLista.listIterator().set(null));

        Object[] miovettore = new Object[6];
        for(int i=0; i < miovettore.length; i++) {
            miovettore[i] = "A" + i;
            miaLista.add(miovettore[i]);
        }

        it = miaLista.listIterator(3);
        assertTrue(it.hasNext());
        assertTrue(it.hasPrevious());
        assertEquals(2, it.previousIndex());
        assertEquals(3, it.nextIndex());

        //tutto il resto funziona allo stesso modo di listIterator()
    }

    @org.junit.Test
    public void metodoSubListCasiLimite() {
        assertThrows(IndexOutOfBoundsException.class, () -> miaLista.subList(-1,2));
        assertThrows(IndexOutOfBoundsException.class, () -> miaLista.subList(1,122));

        miaLista.add(1);
        miaLista.add(2);
        miaLista.add(3);
        assertThrows(IndexOutOfBoundsException.class, () -> miaLista.subList(1,0));
    }

}