//file scritto da Cappellotto Lorenzo, matricola 1188257
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class MapAdapterTest {
    private HMap miaMappa;

    @org.junit.Before
    public void setUp() {
        miaMappa = new MapAdapter();
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
    public void	metodoHashCode(){
        assertEquals(0, miaMappa.hashCode());

        assertNull(miaMappa.put("primo elemento", 1));
        assertNull(miaMappa.put("secondo elemento", 2));
        assertNull(miaMappa.put("quarto elemento", 3));
        assertEquals(3,miaMappa.put("quarto elemento",4));
        assertNotEquals(0, miaMappa.hashCode());

        int hashcodePrima = miaMappa.hashCode();

        miaMappa.remove("primo elemento");
        assertNotEquals(hashcodePrima,miaMappa.hashCode());

        miaMappa.put("primo elemento",1);
        assertEquals(hashcodePrima,miaMappa.hashCode());
    }

    @org.junit.Test
    public void metodoEqualsCasoNormale(){
        HMap altraMappa = new MapAdapter();

        assertFalse(miaMappa.equals(null));
        assertFalse(miaMappa.equals(new CollectionAdapter()));
        assertTrue(miaMappa.equals(altraMappa));

        assertNull(miaMappa.put("primo elemento", 1));
        assertNull(miaMappa.put("secondo elemento", 2));
        assertNull(miaMappa.put("quarto elemento", 3));
        assertEquals(3,miaMappa.put("quarto elemento",4));

        assertFalse(miaMappa.equals(altraMappa));
        assertEquals(altraMappa.equals(miaMappa),miaMappa.equals(altraMappa));

        assertNull(altraMappa.put("secondo elemento", 2));
        assertNull(altraMappa.put("quarto elemento", 3));
        assertEquals(3,altraMappa.put("quarto elemento",4));
        assertNull(altraMappa.put("primo elemento", 1));

        assertTrue(miaMappa.equals(altraMappa));
        assertEquals(altraMappa.equals(miaMappa),miaMappa.equals(altraMappa));

        altraMappa.remove("primo elemento");
        assertFalse(miaMappa.equals(altraMappa));
        assertNull(altraMappa.put("primo elemento", 1));
        assertTrue(miaMappa.equals(altraMappa));//la posizione non e' importante
    }

    @org.junit.Test
    public void metodoPutCasiLimite(){
        assertTrue(miaMappa.isEmpty());

        assertThrows(NullPointerException.class, () -> miaMappa.put(null,null));
        assertThrows(NullPointerException.class, () -> miaMappa.put("chiave",null));
        assertThrows(NullPointerException.class, () -> miaMappa.put(null,"valore"));
        assertTrue(miaMappa.isEmpty());
    }
    @org.junit.Test
    public void metodoPutCasoNormale(){
        assertTrue(miaMappa.isEmpty());

        assertNull(miaMappa.put("primo elemento", 1));
        assertNull(miaMappa.put("secondo elemento", 2));
        assertNull(miaMappa.put("quarto elemento", 3));
        assertEquals(3,miaMappa.put("quarto elemento",4));

        assertEquals(3,miaMappa.size());
        miaMappa.clear();
        assertEquals(0,miaMappa.size());
    }

    @org.junit.Test
    public void metodoPutAllCasoLimite() {
        assertEquals(0,miaMappa.size());
        assertThrows(NullPointerException.class, () -> miaMappa.putAll(null));
        assertEquals(0,miaMappa.size());
    }
    @org.junit.Test
    public void metodoPutAllCasoNormale(){
        HMap secondaMappa = new MapAdapter();

        assertEquals(0,miaMappa.size());
        miaMappa.putAll(secondaMappa);
        assertEquals(0,miaMappa.size());

        assertNull(secondaMappa.put("primo elemento", 1));
        assertNull(secondaMappa.put("secondo elemento", 2));
        assertNull(secondaMappa.put("quarto elemento", 3));
        assertEquals(3,secondaMappa.put("quarto elemento",4));

        miaMappa.putAll(secondaMappa);
        assertEquals(3,miaMappa.size());
        assertTrue(miaMappa.equals(secondaMappa));

        assertNull(secondaMappa.put("terzo elemento", 3));

        miaMappa.putAll(secondaMappa);
        assertEquals(4,miaMappa.size());
        assertTrue(miaMappa.equals(secondaMappa));
    }

    @org.junit.Test
    public void metodoContainsKeyCasoLimite(){
        assertEquals(0,miaMappa.size());
        assertThrows(NullPointerException.class, () -> miaMappa.containsKey(null));
        assertEquals(0,miaMappa.size());
    }
    @org.junit.Test
    public void metodoContainsKeyCasoNormale(){
        assertEquals(0,miaMappa.size());

        assertNull(miaMappa.put("primo elemento", 1));
        assertNull(miaMappa.put("secondo elemento", 2));
        assertNull(miaMappa.put("terzo elemento", 3));

        assertTrue(miaMappa.containsKey("primo elemento"));
        assertTrue(miaMappa.containsKey("secondo elemento"));

        assertFalse(miaMappa.containsKey("quarto elemento"));
    }

    @org.junit.Test
    public void metodoContainsValueCasoLimite() {
        assertEquals(0,miaMappa.size());
        assertThrows(NullPointerException.class, () -> miaMappa.containsValue(null));
        assertEquals(0,miaMappa.size());
    }
    @org.junit.Test
    public void metodoContainsValueCasoNormale(){
        assertEquals(0,miaMappa.size());

        assertNull(miaMappa.put("primo elemento", 1));
        assertNull(miaMappa.put("secondo elemento", 2));
        assertNull(miaMappa.put("terzo elemento", 3));

        assertTrue(miaMappa.containsValue(1));
        assertTrue(miaMappa.containsValue(2));

        assertFalse(miaMappa.containsKey(4));
    }

    @org.junit.Test
    public void metodoGetCasoLimite() {
        assertEquals(0,miaMappa.size());
        assertThrows(NullPointerException.class, () -> miaMappa.get(null));
        assertEquals(0,miaMappa.size());
    }
    @org.junit.Test
    public void metodoGetCasoNormale(){
        assertEquals(0,miaMappa.size());

        assertNull(miaMappa.put("primo elemento", 1));
        assertNull(miaMappa.put("secondo elemento", 2));
        assertNull(miaMappa.put("terzo elemento", 3));

        assertEquals(1,miaMappa.get("primo elemento"));
        assertEquals(2,miaMappa.get("secondo elemento"));

        assertNull(miaMappa.get("quarto elemento"));
    }

    @org.junit.Test
    public void metodoRemoveCasoLimite(){
        assertEquals(0,miaMappa.size());
        assertThrows(NullPointerException.class, () -> miaMappa.remove(null));
        assertEquals(0,miaMappa.size());
    }
    @org.junit.Test
    public void metodoRemoveCasoNormale(){
        assertEquals(0,miaMappa.size());

        assertNull(miaMappa.put("primo elemento", 1));
        assertNull(miaMappa.put("secondo elemento", 2));
        assertNull(miaMappa.put("terzo elemento", 3));

        assertEquals(1,miaMappa.remove("primo elemento"));
        assertEquals(2,miaMappa.remove("secondo elemento"));
        assertNull(miaMappa.remove("quarto elemento"));

        assertEquals(1,miaMappa.size());
    }

    @org.junit.Test
    public void metodoEntrySet(){
        HSet setDiEntry = miaMappa.entrySet();

        //defect test
        assertFalse(miaMappa.entrySet().contains(null));
        assertThrows(NullPointerException.class, () -> miaMappa.entrySet().containsAll(null));

        assertFalse(miaMappa.entrySet().remove(null));
        assertThrows(NullPointerException.class, () -> miaMappa.entrySet().removeAll(null));
        assertThrows(NullPointerException.class, () -> miaMappa.entrySet().retainAll(null));

        assertFalse(setDiEntry.iterator().hasNext());
        assertThrows(NoSuchElementException.class, () -> miaMappa.entrySet().iterator().next());

        assertNull(miaMappa.put("primo elemento", 1));
        assertNull(miaMappa.put("secondo elemento", 2));
        assertNull(miaMappa.put("quarto elemento", 3));
        assertEquals(3,miaMappa.put("quarto elemento",4));

        assertThrows(IllegalStateException.class, () -> {
            HIterator itth = miaMappa.entrySet().iterator();
            itth.remove();
        });
        assertThrows(IllegalStateException.class, () -> {
            HIterator itth = miaMappa.entrySet().iterator();
            itth.next();
            itth.remove();
            itth.remove();
        });

        //validation
        assertFalse(setDiEntry.isEmpty());
        assertFalse(miaMappa.isEmpty());

        assertFalse(setDiEntry.add(null));
        assertFalse(setDiEntry.addAll(null));

        assertTrue(setDiEntry.contains(new EntryAdapter("primo elemento",1)));
        assertEquals(1,miaMappa.get("primo elemento"));


        assertNull(miaMappa.put("terzo elemento", 3));
        HCollection collezione = new CollectionAdapter();
        collezione.add(new EntryAdapter("primo elemento",1));
        assertTrue(setDiEntry.containsAll(collezione));
        collezione.add(new EntryAdapter("quinto elemento",5));
        assertFalse(setDiEntry.containsAll(collezione));
        collezione.add(new EntryAdapter("terzo elemento",3));


        assertTrue(setDiEntry.retainAll(collezione));
        assertEquals(2,setDiEntry.size());
        assertEquals(miaMappa.size(),setDiEntry.size());
        assertFalse(setDiEntry.retainAll(collezione));
        assertEquals(2,setDiEntry.size());
        assertEquals(miaMappa.size(),setDiEntry.size());

        assertTrue(setDiEntry.remove(new EntryAdapter("primo elemento",1)));
        assertArrayEquals(new Object[]{new EntryAdapter("terzo elemento",3)},setDiEntry.toArray());
        assertArrayEquals(new Object[]{new EntryAdapter("terzo elemento",3)},setDiEntry.toArray(new Object[1]));
        assertTrue(setDiEntry.removeAll(collezione));
        assertEquals(0,setDiEntry.size());
        assertEquals(miaMappa.size(),setDiEntry.size());
        assertFalse(setDiEntry.removeAll(collezione));

        assertNull(miaMappa.put("primo elemento", 1));
        assertNull(miaMappa.put("secondo elemento", 2));
        assertNull(miaMappa.put("quarto elemento", 4));

        assertEquals(3,setDiEntry.size());//dato che durante il test ho rimosso 1 elemento devo avere 3-1=2 el
        setDiEntry.clear();
        assertTrue(setDiEntry.isEmpty());
        assertTrue(miaMappa.isEmpty());
    }

    @org.junit.Test
    public void metodoKeySet(){
        HSet setDiKey = miaMappa.keySet();

        //defect test
        assertFalse(miaMappa.keySet().contains(null));
        assertFalse(miaMappa.keySet().contains(new MapAdapter()));
        assertThrows(NullPointerException.class, () -> miaMappa.keySet().containsAll(null));

        assertFalse(miaMappa.keySet().remove(null));
        assertFalse(miaMappa.keySet().remove(new MapAdapter()));
        assertThrows(NullPointerException.class, () -> miaMappa.keySet().removeAll(null));
        assertThrows(NullPointerException.class, () -> miaMappa.keySet().retainAll(null));

        assertFalse(setDiKey.iterator().hasNext());
        assertThrows(NoSuchElementException.class, () -> miaMappa.keySet().iterator().next());

        assertNull(miaMappa.put("primo elemento", 1));
        assertNull(miaMappa.put("secondo elemento", 2));
        assertNull(miaMappa.put("quarto elemento", 3));
        assertEquals(3,miaMappa.put("quarto elemento",4));

        assertThrows(IllegalStateException.class, () -> {
            HIterator itth = miaMappa.keySet().iterator();
            itth.remove();
        });
        assertThrows(IllegalStateException.class, () -> {
            HIterator itth = miaMappa.keySet().iterator();
            itth.next();
            itth.remove();
            itth.remove();
        });

        //validation
        assertFalse(setDiKey.isEmpty());
        assertFalse(miaMappa.isEmpty());

        assertFalse(setDiKey.add(null));
        assertFalse(setDiKey.addAll(null));

        assertTrue(setDiKey.contains(("primo elemento")));
        assertEquals(1,miaMappa.get("primo elemento"));

        assertNull(miaMappa.put("terzo elemento", 3));
        HCollection collezione = new CollectionAdapter();
        collezione.add("primo elemento");
        assertTrue(setDiKey.containsAll(collezione));
        collezione.add("quinto elemento");
        assertFalse(setDiKey.containsAll(collezione));
        collezione.add("terzo elemento");

        assertTrue(setDiKey.retainAll(collezione));
        assertEquals(2,setDiKey.size());
        assertEquals(miaMappa.size(),setDiKey.size());
        assertFalse(setDiKey.retainAll(collezione));
        assertEquals(2,setDiKey.size());
        assertEquals(miaMappa.size(),setDiKey.size());

        assertTrue(setDiKey.remove("primo elemento"));
        assertArrayEquals(new Object[]{"terzo elemento"},setDiKey.toArray());
        assertArrayEquals(new Object[]{"terzo elemento"},setDiKey.toArray(new Object[1]));
        assertTrue(setDiKey.removeAll(collezione));
        assertEquals(0,setDiKey.size());
        assertEquals(miaMappa.size(),setDiKey.size());
        assertFalse(setDiKey.removeAll(collezione));

        assertNull(miaMappa.put("primo elemento", 1));
        assertNull(miaMappa.put("secondo elemento", 2));
        assertNull(miaMappa.put("quarto elemento", 4));
        assertEquals(3,setDiKey.size());//dato che durante il test ho rimosso 1 elemento devo avere 3-1=2 el
        setDiKey.clear();
        assertTrue(setDiKey.isEmpty());
        assertTrue(miaMappa.isEmpty());
    }

    @org.junit.Test
    public void metodoValues(){
        HCollection collDiValues = miaMappa.values();

        //defect test
        assertFalse(miaMappa.values().contains(null));
        assertFalse(miaMappa.values().contains(new MapAdapter()));
        assertThrows(NullPointerException.class, () -> miaMappa.values().containsAll(null));

        assertFalse(miaMappa.values().remove(null));
        assertFalse(miaMappa.values().remove(new MapAdapter()));
        assertThrows(NullPointerException.class, () -> miaMappa.values().removeAll(null));
        assertThrows(NullPointerException.class, () -> miaMappa.values().retainAll(null));

        assertFalse(collDiValues.iterator().hasNext());
        assertThrows(NoSuchElementException.class, () -> miaMappa.values().iterator().next());

        assertNull(miaMappa.put("primo elemento", 1));
        assertNull(miaMappa.put("secondo elemento", 2));
        assertNull(miaMappa.put("quarto elemento", 3));
        assertEquals(3,miaMappa.put("quarto elemento",4));
        assertNull(miaMappa.put("quinto elemento", 4));
        assertNull(miaMappa.put("sesto elemento", 4));

        assertThrows(IllegalStateException.class, () -> {
            HIterator itth = miaMappa.values().iterator();
            itth.remove();
        });
        assertThrows(IllegalStateException.class, () -> {
            HIterator itth = miaMappa.values().iterator();
            itth.next();
            itth.remove();
            itth.remove();
        });

        //validation
        assertFalse(collDiValues.isEmpty());
        assertFalse(miaMappa.isEmpty());

        assertFalse(collDiValues.add(null));
        assertFalse(collDiValues.addAll(null));

        assertTrue(collDiValues.contains(1));
        assertTrue(miaMappa.containsValue(1));
        assertTrue(collDiValues.contains(4));
        assertTrue(miaMappa.containsValue(4));

        HCollection collezione = new CollectionAdapter();
        collezione.add(1);
        collezione.add(4);
        assertTrue(collDiValues.containsAll(collezione));
        collezione.add(3);
        assertFalse(collDiValues.containsAll(collezione));

        assertTrue(collDiValues.retainAll(collezione));
        assertEquals(3,collDiValues.size());
        assertEquals(miaMappa.size(),collDiValues.size());
        assertFalse(collDiValues.retainAll(collezione));
        assertEquals(3,collDiValues.size());
        assertEquals(miaMappa.size(),collDiValues.size());

        assertTrue(collDiValues.remove(1));
        assertArrayEquals(new Object[]{4,4},collDiValues.toArray());
        assertArrayEquals(new Object[]{4,4},collDiValues.toArray(new Object[2]));
        assertTrue(collDiValues.removeAll(collezione));
        assertEquals(1,collDiValues.size());
        assertEquals(miaMappa.size(),collDiValues.size());
        assertTrue(collDiValues.removeAll(collezione));
        assertEquals(0,collDiValues.size());
        assertEquals(miaMappa.size(),collDiValues.size());
        assertFalse(collDiValues.removeAll(collezione));

        assertNull(miaMappa.put("primo elemento", 1));
        assertNull(miaMappa.put("secondo elemento", 2));
        assertNull(miaMappa.put("quarto elemento", 4));
        assertEquals(3,collDiValues.size());//dato che durante il test ho rimosso 1 elemento devo avere 3-1=2 el
        collDiValues.clear();
        assertTrue(collDiValues.isEmpty());
        assertTrue(miaMappa.isEmpty());
    }
}
