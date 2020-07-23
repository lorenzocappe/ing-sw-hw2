//file scritto da Cappellotto Lorenzo, matricola 1188257
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    private static int[] totaleTest = new int[3];//[0] eseguiti [1] ignorati [2] falliti
    private static boolean flagSuccesso = true;

    private static void stampaTotaleRisultati(){
        System.out.println("Totale");
        System.out.println("Numero di test eseguiti: "+totaleTest[0]);
        System.out.println("Numero di test ignorati: "+totaleTest[1]);
        System.out.println("Numero di test falliti: "+totaleTest[2]);
        System.out.println("Esecuzione Avvenuta con successo: "+flagSuccesso);
    }

    private static void stampaRisultatoTest(Class classeTestDaEseguire,String nomeClasse){
        Result result;

        System.out.println("Test "+nomeClasse);
        result = JUnitCore.runClasses(classeTestDaEseguire);
        System.out.println("Numero di test eseguiti: "+result.getRunCount());
        System.out.println("Numero di test ignorati: "+result.getIgnoreCount());
        System.out.println("Numero di test falliti: "+result.getFailureCount());
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println("Esecuzione Avvenuta con successo: "+result.wasSuccessful()+"\n");

        totaleTest[0]+=result.getRunCount();
        totaleTest[1]+=result.getIgnoreCount();
        totaleTest[2]+=result.getFailureCount();
        flagSuccesso = flagSuccesso & result.wasSuccessful();
    }

    public static void main(String[] args) {
        stampaRisultatoTest(MapAdapterTest.class,"MapAdapter");
        stampaRisultatoTest(CollectionAdapterTest.class,"CollectionAdapter");
        stampaRisultatoTest(SetAdapterTest.class,"SetAdapter");
        stampaRisultatoTest(ListAdapterTest.class,"ListAdapter");
        stampaRisultatoTest(SubListViewTest.class,"SubListView");

        stampaTotaleRisultati();
    }
}
