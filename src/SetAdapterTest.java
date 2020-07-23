//file scritto da Cappellotto Lorenzo, matricola 1188257
import java.util.Enumeration;
import java.util.Vector;

import static org.junit.Assert.*;

public class SetAdapterTest {
    private HSet mioSet;
    private Vector vettoreObj;

    @org.junit.Before
    public void setUp() {
        mioSet = new SetAdapter();

        vettoreObj = new Vector();
        vettoreObj.addElement("primo elemento");
        vettoreObj.addElement(null);
        vettoreObj.addElement("elemento duplicato");
        vettoreObj.addElement(2);
        vettoreObj.addElement(4.5);
    }

    @org.junit.Test
    public void metodoAdd() {
        Enumeration enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            assertTrue(mioSet.add(enu.nextElement()));
        }
        assertEquals(vettoreObj.size(),mioSet.size());

        assertFalse(mioSet.add("elemento duplicato"));
        assertEquals(vettoreObj.size(),mioSet.size());
    }

    @org.junit.Test
    public void metodoAddAllCasoLimite() {
        assertThrows(NullPointerException.class, () -> mioSet.addAll(null));
        assertEquals(0,mioSet.size());
    }
    @org.junit.Test
    public void metodoAddAllCasoNormale() {
        HCollection coll = new CollectionAdapter();

        assertFalse(mioSet.addAll(coll));
        assertEquals(0,mioSet.size());

        vettoreObj.addElement("elemento duplicato");
        Enumeration enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            coll.add(enu.nextElement());
        }
        assertTrue(mioSet.addAll(coll));

        assertNotEquals(coll.toArray(),mioSet.toArray());
        assertNotEquals(coll.size(),mioSet.size());

        coll.remove("elemento duplicato");
        assertEquals(coll.size(),mioSet.size());
    }

    @org.junit.Test
    public void metodoEquals() {
        assertFalse(mioSet.equals(null));
        assertFalse(mioSet.equals(new ListAdapter()));

        HSet altroSet = new SetAdapter();

        assertTrue(mioSet.equals(altroSet));
        assertTrue(mioSet.equals(mioSet));//riflessivo, deve funzionare
        assertEquals(altroSet.equals(mioSet),mioSet.equals(altroSet));//controllo simmetria

        Enumeration enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            mioSet.add(enu.nextElement());
        }
        assertFalse(mioSet.equals(altroSet));
        assertFalse(altroSet.equals(mioSet));

        enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            altroSet.add(enu.nextElement());
        }
        assertTrue(mioSet.equals(altroSet));
        assertEquals(altroSet.equals(mioSet),mioSet.equals(altroSet));//controllo simmetria

        altroSet.remove("primo elemento");
        assertFalse(mioSet.equals(altroSet));
        altroSet.add("primo elemento");
        assertTrue(mioSet.equals(altroSet));//la posizione non e' importante
    }
}