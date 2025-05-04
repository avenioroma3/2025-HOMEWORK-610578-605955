package diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {

	Partita partita = new Partita();
	
	Attrezzo tool1 = new Attrezzo("chiavi",1);
	Attrezzo tool2 = new Attrezzo("banana",1);
	
	
	StanzaBloccata blockedroom = new StanzaBloccata("Parcheggio","nord",tool1.getNome());
	Stanza room = new Stanza("Corridoio");
	
	@BeforeEach 
	public void setup() {
		
		blockedroom.impostaStanzaAdiacente("nord", room);
		blockedroom.addAttrezzo(tool1);

		

		partita.setStanzaCorrente(blockedroom);
		
	}
	
	@Test
	// verifica toString
	public void VerificaToStringModificata() {
		System.out.println(blockedroom.getDescrizione());
	}
	
	
	 
	 @Test
	 // verifico che la direzione in teoria bloccata sia accessibile siccome l'oggetto chiave  è nella stanza
	 public void VerificaDirezioneSbloccataOggettoPresente() {
		 assertEquals(room,partita.getStanzaCorrente().getStanzaAdiacente("nord"));
	 }
	 
	 // verifico che la direzione in teoria bloccata sia NON accessibile siccome l'oggetto chiave NON è nella stanza
	 @Test 
	 public void VerificaDirezioneBloccata()  {
		 
		 blockedroom.removeAttrezzo(tool1); // rimuovo l'attrezzo chiave temporaneamente per il fine del test
		 blockedroom.addAttrezzo(tool2);
		 assertEquals(blockedroom,partita.getStanzaCorrente().getStanzaAdiacente("nord"));
		 
		 // se non ce l'ha mi rida riferimento ala stanza corrente
	 }
	 
	

}
