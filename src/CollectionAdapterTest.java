//file scritto da Cappellotto Lorenzo, matricola 1188257
import java.util.Enumeration;
import java.util.Vector;

import static org.junit.Assert.*;

public class CollectionAdapterTest {
    private HCollection miaCollezione;
    private HCollection altraCollezione;
    private Vector vettoreObj;

    @org.junit.Before
    public void setUp() {
        miaCollezione = new CollectionAdapter();
        altraCollezione = new CollectionAdapter();
        vettoreObj = new Vector();

        vettoreObj.addElement("primo elemento");
        vettoreObj.addElement(null);
        vettoreObj.addElement("elemento duplicato");
        vettoreObj.addElement(2);
        vettoreObj.addElement(4.5);
        vettoreObj.addElement("elemento duplicato");
    }

    @org.junit.Test
    public void metodoHashCode() {
        assertEquals(0, miaCollezione.hashCode());
        assertEquals(altraCollezione.hashCode(), miaCollezione.hashCode());

        Object temp;
        Enumeration enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            temp = enu.nextElement();
            miaCollezione.add(temp);
            altraCollezione.add(temp);
        }

        assertNotEquals(0, miaCollezione.hashCode());
        assertEquals(altraCollezione.hashCode(), miaCollezione.hashCode());

        altraCollezione.remove("primo elemento");
        assertNotEquals(altraCollezione.hashCode(), miaCollezione.hashCode());
        altraCollezione.add("primo elemento");
        assertEquals(altraCollezione.hashCode(), miaCollezione.hashCode());//la posizione non e' importante

        miaCollezione.clear();
        assertNotEquals(altraCollezione.hashCode(),miaCollezione.hashCode());
    }

    @org.junit.Test
    public void metodoEquals() {
        assertFalse(miaCollezione.equals(null));
        assertFalse(miaCollezione.equals(new ListAdapter()));

        assertTrue(miaCollezione.equals(altraCollezione));
        assertTrue(miaCollezione.equals(miaCollezione));//riflessivo, deve funzionare
        assertEquals(altraCollezione.equals(miaCollezione),miaCollezione.equals(altraCollezione));//controllo simmetria

        Enumeration enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            miaCollezione.add(enu.nextElement());
        }
        assertFalse(miaCollezione.equals(altraCollezione));
        assertFalse(altraCollezione.equals(miaCollezione));

        enu = vettoreObj.elements();
        while (enu.hasMoreElements()){
            altraCollezione.add(enu.nextElement());
        }
        assertTrue(miaCollezione.equals(altraCollezione));
        assertEquals(altraCollezione.equals(miaCollezione),miaCollezione.equals(altraCollezione));//controllo simmetria

        altraCollezione.remove("primo elemento");
        assertFalse(miaCollezione.equals(altraCollezione));
        altraCollezione.add("primo elemento");
        assertTrue(miaCollezione.equals(altraCollezione));//la posizione non e' importante
    }
}
