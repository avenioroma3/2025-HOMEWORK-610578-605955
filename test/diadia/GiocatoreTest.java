package diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;

class GiocatoreTest {
	
	Giocatore player= new Giocatore();
	

	@Test
	public void VerificaCFU() {
		 player.setCfu(5);
		assertEquals(5,player.getCfu());
	}
	
	@Test
	public void VerficaBorsa() {
		Borsa inventario = new Borsa(5);
		player.setBorsa(inventario);
		assertEquals(inventario,player.getBorsa());
	}

}
