package diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

class LabirintoTest {
	
	Labirinto labirinto = new Labirinto();
	Stanza stanza1 = new Stanza("aula1");
	Stanza stanza2 = new Stanza("aula2");

//
//	@BeforeEach
//	void setUpLabirinto() {
//		labirinto.setEntrata(stanza1);
//	}
//	
	@Test
	public void verificaSetEntrata() {
		labirinto.setEntrata(stanza1);
		assertEquals(stanza1,labirinto.getEntrata());
	}
	
	@Test
	public void verificaSetUscita() {
		labirinto.setUscita(stanza2);
		assertEquals(stanza2,labirinto.getUscita());
	}
	
	

}
