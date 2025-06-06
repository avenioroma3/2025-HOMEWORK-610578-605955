package diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.ambienti.*;

public class IOSimulatorTest {

	private IOSimulator io;
    private Labirinto.LabirintoBuilder builder;

	
	@BeforeEach
    public void setUp() {
        io = new IOSimulator();
        this.builder = Labirinto.newBuilder();
        }
	
    @Test
    public void testSimulatorBasic() {
        IOSimulator io = new IOSimulator();
        io.mostraMessaggio("Benvenuto");
        io.addInput("comando1");
        io.addInput("comando2");

        assertEquals(1, io.getMessaggiCount());
        assertEquals("Benvenuto", io.getMessaggioAt(0));

        assertEquals("comando1", io.leggiRiga());
        assertEquals("comando2", io.leggiRiga());
        assertEquals("", io.leggiRiga());
    }

    @Test
    public void testAcceptanceScenario() {
        IOSimulator io = new IOSimulator();
        io.addInput("fine");

        DiaDia gioco = new DiaDia(io);
        gioco.gioca();

        // Cerco un messaggio di fine partita
        boolean trovato = false;
        for (int i = 0; i < io.getMessaggiCount(); i++) {
            String m = io.getMessaggioAt(i).toLowerCase();
            if (m.contains("arrivederci") || m.contains("grazie") || m.contains("fine partita")) {
                trovato = true;
                break;
            }
        }
        assertTrue(trovato, "Non trovato un messaggio di fine partita");
    }
    
    /*Nuovi test con labirintoBuilder che testano intere partite anche complesse*/
    @Test
    public void test_partitaVintaSubito() {
    	Labirinto labirinto = builder
    		.addStanzaIniziale("atrio")
    		.addStanzaVincente("atrio")
    		.getLabirinto();
    	
    	DiaDia gioco = new DiaDia(labirinto, io);
    	
    	gioco.gioca();
    	String ultimo = io.getMessaggioAt(io.getMessaggiCount() - 1);
    	assertTrue(ultimo.contains("Hai Vinto!"));
    }
    
    @Test
    public void test_partitaVinta() {
    	Labirinto labirinto = builder
    			.addStanzaIniziale("atrio")
    			.addStanzaVincente("biblioteca")
    			.addAdiacenza("atrio", "biblioteca", "nord")
    			.getLabirinto();
    	
    	io.addInput("vai nord");
    	
    	DiaDia gioco = new DiaDia(labirinto, io);
    	
    	gioco.gioca();
    	String ultimo = io.getMessaggioAt(io.getMessaggiCount()-1);
    	assertTrue(ultimo.contains("Hai Vinto!"));
    }
    
    @Test
    public void test_partitaFinita() {
    	Labirinto labirinto = builder
    			.addStanzaIniziale("atrio")
    			.addStanzaVincente("biblioteca")
    			.addAdiacenza("atrio", "biblioteca", "nord")
    			.getLabirinto();
    	
    	io.addInput("fine");
    	
    	DiaDia gioco = new DiaDia(labirinto, io);
    	
    	gioco.gioca();
    	String ultimo = io.getMessaggioAt(io.getMessaggiCount()-1);
    	assertTrue(ultimo.contains("Grazie per aver giocato!"));
    }
    
    @Test
    public void test_Direzioni() {
    	Labirinto labirinto = builder
    			.addStanzaIniziale("atrio")
    			.addStanzaVincente("biblioteca")
    			.addAdiacenza("atrio", "biblioteca", "nord")
    			.getLabirinto();
    	
    	io.addInput("vai");
    	io.addInput("vai sud");
    	io.addInput("vai nord");
    	
    	DiaDia gioco = new DiaDia(labirinto, io);
    	gioco.gioca();
    	assertEquals(io.getMessaggioAt(1), "Dove vuoi andare? Devi specificare una direzione");
    	assertEquals(io.getMessaggioAt(2), "Direzione inesistente.");
    	assertTrue(io.getMessaggioAt(io.getMessaggiCount()-1).contains("Hai Vinto!"));
    }
    
    @Test
    public void test_piuStanzePrimaDellaVittoria() {
    	Labirinto labirinto = builder
    			.addStanzaIniziale("atrio")
    			.addAdiacenza("atrio", "N11", "nord")
    			.addStanza("N11")
    			.addAdiacenza("N11", "Campus", "est")
    			.addStanza("Campus")
    			.addAdiacenza("Campus", "biblioteca", "sud")
    			.addStanzaVincente("biblioteca")
    			.getLabirinto();
    	
    	io.addInput("vai nord");
    	io.addInput("vai est");
    	io.addInput("vai sud");
    	
    	DiaDia gioco = new DiaDia(labirinto, io);
    	gioco.gioca();
    	assertTrue(io.getMessaggioAt(1).contains("N11"));
    	assertTrue(io.getMessaggioAt(3).contains("Campus"));
    	assertEquals(io.getMessaggioAt(io.getMessaggiCount()-1), "Hai Vinto!");
    }
    
    @Test
    public void test_Sconfitta() {
    	Labirinto labirinto = builder
    			.addStanzaIniziale("atrio")
    			.addAdiacenza("atrio", "N11", "nord")
    			.addStanza("N11")
    			.addAdiacenza("N11", "atrio", "nord") //anche se non è realistico mi consente di scrivere il test più velocemente mantenendo il funzionamento corretto
    			.addStanza("Campus")
    			.addAdiacenza("Campus", "biblioteca", "sud")
    			.addStanzaVincente("biblioteca")
    			.getLabirinto();
    	
    	/*dopo 20 chiamate vai il giocatore finisce i CFU*/
    	io.addInput("vai nord");
    	io.addInput("vai nord");
    	io.addInput("vai nord");
    	io.addInput("vai nord");
    	io.addInput("vai nord");
    	io.addInput("vai nord");
    	io.addInput("vai nord");
    	io.addInput("vai nord");
    	io.addInput("vai nord");
    	io.addInput("vai nord");
    	io.addInput("vai nord");
    	io.addInput("vai nord");
    	io.addInput("vai nord");
    	io.addInput("vai nord");
    	io.addInput("vai nord");
    	io.addInput("vai nord");
    	io.addInput("vai nord");
    	io.addInput("vai nord");
    	io.addInput("vai nord");
    	io.addInput("vai nord");
    	
    	DiaDia gioco = new DiaDia(labirinto, io);
    	gioco.gioca();
    	assertEquals(io.getMessaggioAt(io.getMessaggiCount()-1), "Hai esaurito i CFU...");
    }
    
    
    @Test
    public void test_RaccoltaERilascioOggetti() {
    	Labirinto labirinto = builder
    			.addStanzaIniziale("atrio")
    			.addAttrezzo("chiave", 2)
    			.addStanzaVincente("biblioteca")
    			.getLabirinto();
    	
    	io.addInput("prendi");
    	io.addInput("prendi osso");
    	io.addInput("prendi chiave");
    	io.addInput("guarda");
    	io.addInput("posa");
    	io.addInput("posa osso");
    	io.addInput("posa chiave");
    	io.addInput("guarda");
    	io.addInput("fine");
    	
    	DiaDia gioco = new DiaDia(labirinto, io);
    	gioco.gioca();
    	assertTrue(io.getMessaggioAt(1).contains("Devi specificare il nome dell'oggetto da prendere."));
    	assertEquals(io.getMessaggioAt(2), "L'oggetto: osso non è in questa stanza");
    	assertEquals(io.getMessaggioAt(3), "chiave raccolto e aggiunto nell'inventario");
    	assertEquals(io.getMessaggioAt(6).contains("chiave (2kg)"), true);
    	assertEquals(io.getMessaggioAt(7), "Devi specificare quale oggetto posare.");
    	assertEquals(io.getMessaggioAt(8), "L'oggetto: osso non è nell'inventario");
    	assertEquals(io.getMessaggioAt(9), "chiave posato e rimosso dall'inventario");
    	assertEquals(io.getMessaggioAt(12), "Borsa vuota");
    	}
    
    @Test
    public void test_stanzaMagica() {
    	Labirinto labirinto = builder
    			.addStanzaIniziale("atrio")
    			.addAttrezzo("libro", 2)
    			.addAttrezzo("chiave", 2)
    			.addAttrezzo("arco", 2)
    			.addStanzaMagica("campus", 2)
    			.addStanzaVincente("biblioteca")
    			.addAdiacenza("atrio", "campus", "est")
    			.getLabirinto();
    	
    	io.addInput("prendi libro");
    	io.addInput("prendi arco");
    	io.addInput("prendi chiave");
    	io.addInput("vai est");
    	io.addInput("posa chiave");
    	io.addInput("posa libro");
    	io.addInput("posa arco");
    	io.addInput("guarda");
    	io.addInput("prendi ocra");
    	io.addInput("guarda");
    	io.addInput("fine");
    	
    	DiaDia gioco = new DiaDia(labirinto, io);
    	gioco.gioca();
    	assertTrue(io.getMessaggioAt(9).contains("chiave (2kg)"));
    	assertTrue(io.getMessaggioAt(9).contains("libro (2kg)"));
    	assertTrue(io.getMessaggioAt(9).contains("ocra (4kg)")); //viene invertito il nome del 3 oggetto aggiunto e raddoppiato il peso
    	assertEquals(io.getMessaggioAt(12), "ocra raccolto e aggiunto nell'inventario");
    	assertTrue(io.getMessaggioAt(15).contains("ocra (4kg)"));
    }
    
    @Test 
    public void test_stanzaBuia() {
    	Labirinto labirinto = builder
    			.addStanzaIniziale("atrio")
    			.addAttrezzo("lanterna", 2)
    			.addAttrezzo("chiave", 2)
    			.addStanzaBuia("campus", "lanterna")
    			.addStanzaVincente("biblioteca")
    			.addAdiacenza("atrio", "campus", "est")
    			.getLabirinto();
    	
    	io.addInput("prendi chiave");
    	io.addInput("prendi lanterna");
    	io.addInput("vai est");
    	io.addInput("posa chiave");
    	io.addInput("guarda");
    	io.addInput("posa lanterna");
    	io.addInput("guarda");
    	io.addInput("fine");
    	
    	DiaDia gioco = new DiaDia(labirinto, io);
    	gioco.gioca();
    	
    	assertEquals(io.getMessaggioAt(3), "qui c'è buio pesto");
    	assertEquals(io.getMessaggioAt(5), "chiave posato e rimosso dall'inventario");
    	assertEquals(io.getMessaggioAt(6), "qui c'è buio pesto");
    	assertFalse(io.getMessaggioAt(6).contains("campus"));
    	assertEquals(io.getMessaggioAt(9), "lanterna posato e rimosso dall'inventario");
    	assertTrue(io.getMessaggioAt(10).contains("campus"));
    }
    
    @Test 
    public void test_stanzaBloccata() {
    	Labirinto labirinto = builder
    			.addStanzaIniziale("atrio")
    			.addAttrezzo("lanterna", 2)
    			.addAttrezzo("chiave", 2)
    			.addStanzaBloccata("campus", "nord", "chiave")
    			.addStanzaVincente("biblioteca")
    			.addAdiacenza("atrio", "campus", "est")
    			.addAdiacenza("campus", "biblioteca", "nord")
    			.getLabirinto();
    	
    	io.addInput("prendi chiave");
    	io.addInput("prendi lanterna");
    	io.addInput("vai est");
    	io.addInput("posa lanterna");
    	io.addInput("vai nord");
    	io.addInput("posa chiave");
    	io.addInput("vai nord");
    	
    	DiaDia gioco = new DiaDia(labirinto, io);
    	gioco.gioca();
    	
    	assertEquals(io.getMessaggioAt(3), "campus\n"+ "Uscite: nord\n"+ "Attrezzi nella stanza: Nessun oggetto nella stanza\nPersonaggi nella stanza: Nessun personaggio\nAttenzione, la porta a nord è bloccata, per sbloccarla ti serve: chiave");
    	assertEquals(io.getMessaggioAt(5), "lanterna posato e rimosso dall'inventario");
    	assertEquals(io.getMessaggioAt(6), "campus\n"+ "Uscite: nord\n"+ "Attrezzi nella stanza: lanterna (2kg) \nPersonaggi nella stanza: Nessun personaggio\nAttenzione, la porta a nord è bloccata, per sbloccarla ti serve: chiave");
    	assertEquals(io.getMessaggioAt(8), "chiave posato e rimosso dall'inventario");
    	assertEquals(io.getMessaggioAt(9), "biblioteca\n"+ "Uscite:\n"+ "Attrezzi nella stanza: Nessun oggetto nella stanza\nPersonaggi nella stanza: Nessun personaggio");
    	assertEquals(io.getMessaggioAt(11), "Hai Vinto!");
    }
    
}
