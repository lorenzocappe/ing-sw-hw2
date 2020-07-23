//file scritto da Cappellotto Lorenzo, matricola 1188257
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Vector;

import static org.junit.Assert.*;

public class SubListViewTest{
    private HList miaLista,miaSublist;
    private Vector vettoreObj;
    private Object obj;

    @org.junit.Before
    public void setUp() {
        miaLista = new ListAdapter();
        vettoreObj = new Vector();

        miaLista.add("primo elemento");
        miaLista.add("elemento duplicato");
        miaLista.add("elemento duplicato");
        miaLista.add(null);
        miaLista.add(1);
        miaLista.add(2);
        miaLista.add(4.5);

        //miaSublist = miaLista.subList(2, 4);

        vettoreObj.addElement("1");
        vettoreObj.addElement("2");
        vettoreObj.addElement("3");
        vettoreObj.addElement("4");
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
        miaSublist = miaLista.subList(1,1);
        HList altraLista = new ListAdapter();
        assertEquals(1, miaSublist.hashCode());
        assertEquals(altraLista.hashCode(), miaSublist.hashCode());

        Object temp;
        Enumeration enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            temp = enu.nextElement();
            miaSublist.add(temp);
            altraLista.add(temp);
        }
        assertEquals(altraLista.hashCode(), miaSublist.hashCode());
        altraLista.remove("1");
        assertNotEquals(altraLista.hashCode(), miaSublist.hashCode());
        altraLista.add("1");
        assertNotEquals(altraLista.hashCode(), miaSublist.hashCode());//la posizione e' importante
    }

    @org.junit.Test
    public void metodoEquals() {
        miaSublist = miaLista.subList(1,1);
        HList altraLista = new ListAdapter();

        assertFalse(miaSublist.equals(null));
        assertFalse(miaSublist.equals(new SetAdapter()));

        assertTrue(miaSublist.equals(altraLista));
        assertTrue(miaSublist.equals(miaSublist));//riflessivo, deve funzionare
        assertEquals(altraLista.equals(miaSublist),miaSublist.equals(altraLista));//controllo simmetria

        Enumeration enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            miaSublist.add(enu.nextElement());
        }
        assertFalse(miaSublist.equals(altraLista));
        assertFalse(altraLista.equals(miaSublist));

        enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            altraLista.add(enu.nextElement());
        }
        assertTrue(miaSublist.equals(altraLista));
        assertEquals(altraLista.equals(miaSublist),miaSublist.equals(altraLista));//controllo simmetria

        altraLista.remove("1");
        assertFalse(miaSublist.equals(altraLista));
        altraLista.add("1");
        assertFalse(miaSublist.equals(altraLista));//la posizione e' importante
    }

    @org.junit.Test
    public void metodoToArray1() {
        miaSublist = miaLista.subList(1,1);
        assertEquals(0,miaSublist.toArray().length);

        Object[] mioVettore = new Object[4];
        for(int i=0; i < mioVettore.length; i++){
            mioVettore[i] = "A"+i;
            miaSublist.add(mioVettore[i]);
        }
        assertEquals(mioVettore.length,miaSublist.size());
        assertArrayEquals(mioVettore,miaSublist.toArray());
    }

    @org.junit.Test
    public void metodoToArray2CasiLimite() {
        miaSublist = miaLista.subList(1,1);
        assertEquals(0, miaSublist.toArray(new Object[0]).length);
        assertThrows(NullPointerException.class, () -> miaSublist.toArray(null));

        miaSublist.add(1.2);
        miaSublist.add(2);
        miaSublist.add(3);
        assertThrows(ArrayStoreException.class, () -> miaSublist.toArray(new String[3]));
    }
    @org.junit.Test
    public void metodoToArray2CasoNormale() {
        miaSublist = miaLista.subList(1,1);
        Object[] mioVettore = new Object[4];
        Object[] vettoreToArray1 = new Object[4];
        Object[] vettoreToArray2 = new Object[6];
        Object[] vettoreToArray3 = new Object[2];

        for(int i=0; i < mioVettore.length; i++){
            mioVettore[i] = "A"+i;
            miaSublist.add(mioVettore[i]);
        }
        miaSublist.toArray(vettoreToArray1);
        miaSublist.toArray(vettoreToArray2);
        Object[] vettoreToArray4 = miaSublist.toArray(vettoreToArray3);

        assertEquals(mioVettore.length,vettoreToArray1.length);
        assertArrayEquals(mioVettore,vettoreToArray1);

        assertNotEquals(mioVettore.length,vettoreToArray3.length);
        assertEquals(mioVettore.length,vettoreToArray4.length);
        assertArrayEquals(mioVettore,vettoreToArray4);

        assertNotEquals(mioVettore.length,vettoreToArray2.length);
        assertEquals(null,vettoreToArray2[miaSublist.size()]);
    }

    @org.junit.Test
    public void metodoContains() {
        miaSublist = miaLista.subList(1,1);
        Enumeration enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            miaLista.add(enu.nextElement());
        }
        assertFalse(miaSublist.contains("1"));
        assertFalse(miaSublist.contains("2"));

        miaSublist = miaLista.subList(1,1);
        enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            miaSublist.add(enu.nextElement());
        }
        assertTrue(miaSublist.contains("1"));
        assertTrue(miaSublist.contains("2"));

        assertFalse(miaSublist.contains("secondo elemento"));
        assertFalse(miaLista.contains("secondo elemento"));
    }

    @org.junit.Test
    public void metodoContainsAllCasiLimite() {
        miaSublist = miaLista.subList(1,1);
        assertEquals(0,miaSublist.size());
        assertThrows(NullPointerException.class, () -> miaSublist.containsAll(null));
        assertEquals(0,miaSublist.size());
    }
    @org.junit.Test
    public void metodoContainsAllCasoNormale() {
        miaSublist = miaLista.subList(1,1);
        HCollection coll = new CollectionAdapter();
        assertTrue(miaLista.containsAll(coll));
        assertTrue(miaSublist.containsAll(coll));

        Enumeration enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            miaLista.add(enu.nextElement());
        }
        vettoreObj.remove("1");
        enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            coll.add(enu.nextElement());
        }
        assertTrue(miaLista.containsAll(coll));
        assertFalse(miaSublist.containsAll(coll));

        enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            miaSublist.add(enu.nextElement());
        }
        assertTrue(miaLista.containsAll(coll));
        assertTrue(miaSublist.containsAll(coll));

        coll.add("secondo elemento");
        assertFalse(miaLista.containsAll(coll));
        assertFalse(miaSublist.containsAll(coll));
    }

    @org.junit.Test
    public void metodoAdd() {
        miaSublist = miaLista.subList(1,1);
        assertNotEquals(vettoreObj.size(),miaSublist.size());

        Object temp;
        Enumeration enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            temp = enu.nextElement();
            assertTrue(miaSublist.add(temp));
            assertTrue(miaLista.contains(temp));
        }

        assertArrayEquals(vettoreObj.toArray(),miaSublist.toArray());
        assertEquals(vettoreObj.size(),miaSublist.size());
    }

    @org.junit.Test
    public void metodoAddAllCasiLimite() {
        miaSublist = miaLista.subList(1,1);
        assertTrue(miaSublist.isEmpty());
        assertThrows(NullPointerException.class, () -> miaSublist.addAll(null));
        assertTrue(miaSublist.isEmpty());
    }
    @org.junit.Test
    public void metodoAddAllCasoNormale() {
        miaSublist = miaLista.subList(1,1);
        HCollection coll = new CollectionAdapter();
        assertFalse(miaSublist.addAll(coll));

        Enumeration enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            coll.add(enu.nextElement());
        }

        assertTrue(miaLista.addAll(coll));
        assertFalse(miaSublist.contains("1"));

        assertTrue(miaSublist.addAll(coll));
        assertTrue(miaSublist.contains("1"));
        assertArrayEquals(coll.toArray(),miaSublist.toArray());
    }

    @org.junit.Test
    public void metodoAddAtCasiLimite() {
        miaSublist = miaLista.subList(1,1);
        assertEquals(0,miaSublist.size());
        assertThrows(IndexOutOfBoundsException.class, () -> miaSublist.add(-1,"elemento"));
        assertThrows(IndexOutOfBoundsException.class, () -> miaSublist.add(1,"elemento"));
        assertEquals(0,miaSublist.size());

        miaSublist.add(2);
        miaSublist.add(2);
        miaSublist.add(2);
        miaSublist.add(2);
        assertEquals(4,miaSublist.size());
        assertThrows(IndexOutOfBoundsException.class, () -> miaSublist.add(6,null));
        assertEquals(4,miaSublist.size());
    }
    @org.junit.Test
    public void metodoAddAtCasoNormale() {
        miaSublist = miaLista.subList(1,1);

        miaSublist.add(0,1);//1
        miaSublist.add(0,2);//2 1
        miaSublist.add(1,3);//2 3 1
        miaSublist.add(4);               //2 3 1 4
        miaSublist.add(4,5);//2 3 1 4 5
        assertArrayEquals(new Object[]{2,3,1,4,5},miaSublist.toArray());

        for(int i=0;i<miaSublist.size();i++){
            assertEquals(miaSublist.get(i),miaLista.get(1+i));
        }
    }

    @org.junit.Test
    public void metodoAddAllAtCasiLimite() {
        miaSublist = miaLista.subList(1,1);

        assertEquals(0,miaSublist.size());
        assertThrows(NullPointerException.class, () -> miaSublist.addAll(0,null));
        assertThrows(IndexOutOfBoundsException.class, () -> miaSublist.addAll(-1,new CollectionAdapter()));
        assertThrows(IndexOutOfBoundsException.class, () -> miaSublist.addAll(1,new CollectionAdapter()));
        assertEquals(0,miaSublist.size());
    }
    @org.junit.Test
    public void metodoAddAllAtCasoNormale() {
        miaSublist = miaLista.subList(1,1);

        miaSublist.add('a');
        miaSublist.add('b');
        miaSublist.add('c');
        miaSublist.add('d');
        //lista: a b c d

        HCollection coll = new CollectionAdapter();
        assertFalse(miaSublist.addAll(0, coll));

        Enumeration enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            coll.add(enu.nextElement());
        }
        miaSublist.addAll(2,coll);
        //lista: a b -coll- c d

        vettoreObj.insertElementAt('b',0);
        vettoreObj.insertElementAt('a',0);
        vettoreObj.addElement('c');
        vettoreObj.addElement('d');

        assertEquals(vettoreObj.size(),miaSublist.size());
        for(int i=0; i<miaSublist.size(); i++){
            assertTrue(miaSublist.contains(vettoreObj.get(i)));
            assertTrue(miaLista.contains(vettoreObj.get(i)));
            assertEquals(miaLista.get(1+i),miaSublist.get(i));
        }
    }

    @org.junit.Test
    public void metodoIndexOf(){
        miaSublist = miaLista.subList(1,1);
        assertEquals(-1,miaSublist.indexOf("1"));
        assertEquals(-1,miaSublist.indexOf("primo elemento"));

        Enumeration enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            miaSublist.add(enu.nextElement());
        }

        assertEquals(0,miaSublist.indexOf("1"));
        assertEquals(1+0,miaLista.indexOf("1"));
        assertEquals(3,miaSublist.indexOf("4"));
        assertEquals(1+3,miaLista.indexOf("4"));

        assertEquals(-1,miaLista.indexOf("secondo elemento"));
        assertEquals(-1,miaSublist.indexOf("secondo elemento"));

        assertEquals(1+1,miaLista.indexOf("2"));
        assertEquals(1,miaSublist.indexOf("2"));
        miaSublist.remove("2");
        assertNotEquals(1+1,miaLista.indexOf("2"));
        assertNotEquals(1,miaSublist.indexOf("2"));
    }

    @org.junit.Test
    public void metodoLastIndexOf(){
        miaSublist = miaLista.subList(1,1);
        assertEquals(-1,miaSublist.lastIndexOf("1"));
        assertEquals(-1,miaSublist.lastIndexOf("primo elemento"));

        Enumeration enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            miaSublist.add(enu.nextElement());
        }
        enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            miaSublist.add(enu.nextElement());
        }

        assertEquals(4,miaSublist.lastIndexOf("1"));
        assertEquals(1+4,miaLista.lastIndexOf("1"));
        assertEquals(7,miaSublist.lastIndexOf("4"));
        assertEquals(1+7,miaLista.lastIndexOf("4"));

        assertEquals(-1,miaLista.lastIndexOf("secondo elemento"));
        assertEquals(-1,miaSublist.lastIndexOf("secondo elemento"));

        assertEquals(1+5,miaLista.lastIndexOf("2"));
        assertEquals(5,miaSublist.lastIndexOf("2"));
        miaSublist.remove("2");
        assertNotEquals(1+5,miaLista.lastIndexOf("2"));
        assertNotEquals(5,miaSublist.lastIndexOf("2"));
    }

    @org.junit.Test
    public void metodoGetCasiLimite() {
        miaSublist = miaLista.subList(1,1);

        assertEquals(0, miaSublist.size());
        assertThrows(IndexOutOfBoundsException.class, () -> miaSublist.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> miaSublist.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> miaSublist.get(1));
        assertEquals(0, miaSublist.size());
    }
    @org.junit.Test
    public void metodoGetCasoNormale(){
        miaSublist = miaLista.subList(1,1);

        Enumeration enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            miaSublist.add(enu.nextElement());
        }

        assertEquals(vettoreObj.elementAt(3),miaSublist.get(3));
        assertEquals(vettoreObj.elementAt(3),miaLista.get(1+3));
        assertEquals("1",miaSublist.get(0));
        assertEquals("1",miaLista.get(1));
    }

    @org.junit.Test
    public void metodoSetCasiAnomali() {
        miaSublist = miaLista.subList(1,1);

        assertEquals(0,miaSublist.size());
        assertThrows(IndexOutOfBoundsException.class, () -> miaSublist.set(-1,null));
        assertThrows(IndexOutOfBoundsException.class, () -> miaSublist.set(0,null));
        assertThrows(IndexOutOfBoundsException.class, () -> miaSublist.set(1,null));
        assertEquals(0,miaSublist.size());
    }
    @org.junit.Test
    public void metodoSetCasoNormale() {
        miaSublist = miaLista.subList(1,1);

        Enumeration enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            miaSublist.add(enu.nextElement());
        }
        assertEquals("1", miaSublist.get(0));
        assertEquals("1",miaSublist.set(0,"5"));
        assertEquals("5", miaSublist.get(0));
        assertEquals("5", miaLista.get(1));
    }

    @org.junit.Test
    public void metodoRemove() {
        miaLista.add("1");
        miaSublist = miaLista.subList(1,1);

        assertFalse(miaSublist.remove("1"));
        assertFalse(miaSublist.remove("2"));

        Enumeration enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            miaSublist.add(enu.nextElement());
        }
        miaSublist.add("1");

        assertTrue(miaSublist.remove("1"));
        assertTrue(miaSublist.remove("1"));
        assertFalse(miaSublist.remove("1"));

        assertFalse(miaSublist.remove("5"));
    }

    @org.junit.Test
    public void metodoRemoveAtCasiLimite(){
        miaSublist = miaLista.subList(1,1);

        assertTrue(miaSublist.isEmpty());
        assertThrows(IndexOutOfBoundsException.class, () -> miaSublist.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> miaSublist.remove(0));
        assertThrows(IndexOutOfBoundsException.class, () -> miaSublist.remove(1));
        assertTrue(miaSublist.isEmpty());
    }
    @org.junit.Test
    public void metodoRemoveAtCasoNormale(){
        miaSublist = miaLista.subList(1,1);
        assertTrue(miaSublist.isEmpty());

        Enumeration enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            miaSublist.add(enu.nextElement());
        }

        assertEquals("3",miaSublist.remove(2));
        assertEquals("1",miaSublist.remove(0));

        assertEquals(2,miaSublist.size());
        assertFalse(miaLista.contains("1"));
        assertFalse(miaSublist.contains("1"));
    }

    @org.junit.Test
    public void metodoRemoveAllCasoLimite() {
        miaSublist = miaLista.subList(1,1);

        assertTrue(miaSublist.isEmpty());
        assertThrows(NullPointerException.class, () -> miaSublist.removeAll(null));
        assertTrue(miaSublist.isEmpty());
    }
    @org.junit.Test
    public void metodoRemoveAllCasoNormale() {
        miaLista.add("1");
        miaSublist = miaLista.subList(1,1);

        HCollection coll = new CollectionAdapter();
        assertFalse(miaSublist.removeAll(coll));
        assertTrue(miaSublist.isEmpty());

        Enumeration enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            miaSublist.add(enu.nextElement());
        }
        miaSublist.add("1");
        miaSublist.add("5");

        enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            coll.add(enu.nextElement());
        }

        assertTrue(miaSublist.removeAll(coll));
        assertEquals(2,miaSublist.size());
        assertTrue(miaSublist.removeAll(coll));
        assertEquals(1,miaSublist.size());

        assertFalse(miaSublist.removeAll(coll));
        assertEquals(1,miaSublist.size());
    }

    @org.junit.Test
    public void metodoRetainAllCasoLimite() {
        miaSublist = miaLista.subList(1,1);

        assertTrue(miaSublist.isEmpty());
        assertThrows(NullPointerException.class, () -> miaSublist.retainAll(null));
        assertTrue(miaSublist.isEmpty());
    }
    @org.junit.Test
    public void metodoRetainAllCasoNormale() {
        miaLista.add("1");
        miaSublist = miaLista.subList(1,1);

        HCollection coll = new CollectionAdapter();
        assertFalse(miaSublist.retainAll(coll));

        assertTrue(miaSublist.isEmpty());
        miaSublist.add("1");
        assertFalse(miaSublist.isEmpty());
        assertTrue(miaSublist.retainAll(coll));
        assertTrue(miaSublist.isEmpty());

        Enumeration enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            miaSublist.add(enu.nextElement());
        }
        miaSublist.add("1");
        miaSublist.add("5");

        coll.add("1");
        coll.add("2");

        assertTrue(miaSublist.retainAll(coll));
        assertEquals(3,miaSublist.size());
        assertFalse(miaSublist.retainAll(coll));
        assertEquals(3,miaSublist.size());
    }

    @org.junit.Test
    public void metodoIterator() {
        miaSublist = miaLista.subList(1,1);

        //controlla prima che add funzioni
        Object[] miovettore = new Object[4];

        //defect test
        HIterator it = miaSublist.iterator();
        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, () -> miaSublist.iterator().next());
        assertThrows(IllegalStateException.class, () -> miaSublist.iterator().remove());

        //validation test
        for(int i=0; i < miovettore.length; i++){
            miovettore[i] = "A"+i;
            miaSublist.add(miovettore[i]);
        }

        it = miaSublist.iterator();
        int i = 0;
        while(it.hasNext()){//verifico che hasNext e next funzionino correttamente
            assertEquals(miovettore[i++],it.next());
        }
        //controllo che remove funzioni correttamente
        assertThrows(IllegalStateException.class, () -> miaSublist.iterator().remove());
        assertThrows(IllegalStateException.class, () -> {
            HIterator itth = miaSublist.iterator();
            itth.next();
            itth.remove();
            itth.remove();
        });
        it = miaSublist.iterator();
        while(it.hasNext()){
            it.next();
            it.remove();
        }
        assertEquals(0,miaSublist.size());
    }

    @org.junit.Test
    public void metodoListIterator(){
        miaSublist = miaLista.subList(1,1);

        HListIterator it = miaSublist.listIterator();

        assertFalse(it.hasNext());
        assertFalse(it.hasPrevious());
        assertEquals(-1, it.previousIndex());
        assertEquals(0, it.nextIndex());

        assertThrows(NoSuchElementException.class, () -> miaSublist.listIterator().next());
        assertThrows(NoSuchElementException.class, () -> miaSublist.listIterator().previous());
        assertThrows(IllegalStateException.class, () -> miaSublist.listIterator().remove());
        assertThrows(IllegalStateException.class, () -> miaSublist.listIterator().set(null));

        it.add(1);
        assertThrows(IllegalStateException.class, () -> miaSublist.listIterator().remove());
        assertThrows(IllegalStateException.class, () -> miaSublist.listIterator().set(0));

        miaSublist.clear();
        it = miaSublist.listIterator();

        //test di add e set da lista vuota
        it.add(-1);// |
        it.add(-2);//-1 |
        //-1 -2 |
        assertEquals(2, miaSublist.size());

        assertFalse(it.hasNext());
        assertTrue(it.hasPrevious());
        it.previous();//-1 | -2

        assertTrue(miaSublist.contains(-1));
        assertTrue(miaSublist.contains(-2));
        assertFalse(miaSublist.contains(0));
        it.set(0);//-1 | 0
        assertTrue(miaSublist.contains(0));
        assertFalse(miaSublist.contains(-2));
        assertEquals(2, miaSublist.size());

        assertTrue(miaSublist.contains(0));
        assertTrue(miaSublist.contains(-1));
        assertFalse(miaSublist.contains(1));
        it.previous();// | -1 0
        it.next();    // -1 | 0
        //test di set dopo next
        it.set(1);    //  1 | 0
        assertTrue(miaSublist.contains(1));
        assertFalse(miaSublist.contains(-1));
        assertEquals(2, miaSublist.size());

        //popolo lista
        miaSublist.clear();//se uso la mappa il comportamento dell'iteratore non e' garantito, devo ri-inizializzarlo
        it = miaSublist.listIterator();
        Object[] vettoreObj = new Object[6];
        for(int i=0; i < vettoreObj.length; i++) {
            vettoreObj[i] = "A" + i;
            miaSublist.add(vettoreObj[i]);
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
        it = miaSublist.listIterator();
        it.next();it.next();
        // A0 A1 | A2 A3 A4 A5
        it.add(0);
        // A0 A1 0 | A2 A3 A4 A5

        assertEquals(0,it.previous());
        assertEquals(2,miaSublist.indexOf(0));
        assertEquals(0,it.next());

        it.next();it.next();it.previous();it.previous();
        // A0 A1 0 | A2 A3 A4 A5
        it.add(1);
        // A0 A1 0 1 | A2 A3 A4 A5

        assertNotEquals(1,it.next());
        assertNotEquals(1,it.previous());
        assertEquals(1,it.previous());

        it = miaSublist.listIterator();
        while(it.hasNext()){
            it.next();
            it.remove();
        }
        assertTrue(miaSublist.isEmpty());
    }
    @org.junit.Test
    public void metodoListIteratorAt(){
        miaSublist = miaLista.subList(1,1);

        assertThrows(IndexOutOfBoundsException.class, () -> miaSublist.listIterator(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> miaSublist.listIterator(10));

        HListIterator it = miaSublist.listIterator(0);
        assertFalse(it.hasNext());
        assertFalse(it.hasPrevious());
        assertEquals(-1, it.previousIndex());
        assertEquals(0, it.nextIndex());

        assertThrows(NoSuchElementException.class, () -> miaSublist.listIterator().next());
        assertThrows(NoSuchElementException.class, () -> miaSublist.listIterator().previous());
        assertThrows(IllegalStateException.class, () -> miaSublist.listIterator().remove());
        assertThrows(IllegalStateException.class, () -> miaSublist.listIterator().set(null));

        Object[] miovettore = new Object[6];
        for(int i=0; i < miovettore.length; i++) {
            miovettore[i] = "A" + i;
            miaSublist.add(miovettore[i]);
        }

        it = miaSublist.listIterator(3);
        assertTrue(it.hasNext());
        assertTrue(it.hasPrevious());
        assertEquals(2, it.previousIndex());
        assertEquals(3, it.nextIndex());

        //tutto il resto funziona allo stesso modo di listIterator()
    }

    @org.junit.Test
    public void metodoSubList() {
        miaSublist = miaLista.subList(1,1);
        assertEquals(null, miaSublist.subList(-1,2));
    }

}