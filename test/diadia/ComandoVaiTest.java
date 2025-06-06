package diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comandi.ComandoVai;

class ComandoVaiTest {
	
	private ComandoVai comando;
	private Partita partita;
	private Stanza stanzaIniziale;
	private Stanza stanzaAdiacente;
	private IOSimulator io;
	private Labirinto.LabirintoBuilder builder;
	
	
	@BeforeEach
	void setUp() throws Exception {
		partita = new Partita();
		io = new IOSimulator();
		comando = new ComandoVai(io);
		stanzaIniziale = new Stanza("atrio");
		stanzaAdiacente = new Stanza("biblioteca");
		stanzaIniziale.impostaStanzaAdiacente("nord", stanzaAdiacente);
		partita.setStanzaCorrente(stanzaIniziale);
		this.setBuilder(Labirinto.newBuilder());
	}
	
	@Test
	void test_SenzaParametro() {
		assertEquals(partita.getStanzaCorrente(), stanzaIniziale);
		comando.esegui(partita);
		assertEquals(partita.getStanzaCorrente(), stanzaIniziale);
	}
	
	@Test
	void test_DirezioneInesistente() {
		assertEquals(partita.getStanzaCorrente(), stanzaIniziale);
		comando.setParametro("sud");
		comando.esegui(partita);
		assertEquals(partita.getStanzaCorrente(), stanzaIniziale);
	}
	
	@Test
	void test_PassaStanzaSuccessiva() {
		assertEquals(partita.getStanzaCorrente(), stanzaIniziale);
		comando.setParametro("nord");
		comando.esegui(partita);
		assertEquals(partita.getStanzaCorrente(), stanzaAdiacente);
	}
	
	@Test
	void test_PassaStanzeSuccessive() {
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("atrio")
				.addStanza("campus")
				.addStanza("N11")
				.addStanzaVincente("biblioteca")
				.addAdiacenza("atrio", "campus", "est")
				.addAdiacenza("campus", "N11", "sud")
				.addAdiacenza("N11", "biblioteca", "ovest")
				.getLabirinto();
		
		partita = new Partita(labirinto);
		assertEquals(partita.getStanzaCorrente().getNome(), "atrio");
		comando.setParametro("est");
		comando.esegui(partita);
		assertEquals(partita.getStanzaCorrente().getNome(), "campus");
		comando.setParametro("sud");
		comando.esegui(partita);
		assertEquals(partita.getStanzaCorrente().getNome(), "N11");
		comando.setParametro("ovest");
		comando.esegui(partita);
		assertEquals(partita.getStanzaCorrente().getNome(), "biblioteca");
	}

	public Labirinto.LabirintoBuilder getBuilder() {
		return builder;
	}

	public void setBuilder(Labirinto.LabirintoBuilder builder) {
		this.builder = builder;
	}

}
