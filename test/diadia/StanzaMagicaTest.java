package diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {

	Partita partita = new Partita();
	
	
	StanzaMagica s1 = new StanzaMagica("Aula0");
	StanzaMagica s2 = new StanzaMagica("Aula1",1);
	StanzaMagica s3 = new StanzaMagica("Aula2",2);

	
	Attrezzo tool1 = new Attrezzo("Arco",3);
	Attrezzo tool2 = new Attrezzo("Coltello",2);
	Attrezzo tool3 = new Attrezzo("Stivali",3);
	Attrezzo tool4 = new Attrezzo("Moneta",1);



	
	@BeforeEach
	public void setUpStanzaMagica() {
		
		IOConsole io = new IOConsole();
		
		partita.setIOConsole(io);
		
		
		s1.addAttrezzo(tool1);
		s1.addAttrezzo(tool2);
		s1.addAttrezzo(tool3);
		s1.addAttrezzo(tool4);
		
		
		// S1 ha soglia default -> 3
		// al 4 oggetto il nome cambia

		
		s2.addAttrezzo(tool1);
		s2.addAttrezzo(tool2);
		
		// S2 ha soglai default ->1
		// il secondo oggetto(tool2) cambia nome
		
		
		
		s3.addAttrezzo(tool1);
		s3.addAttrezzo(tool2);
		
		
	}
	
	@Test
	// verifico la soglia default -> 3 
	// non è presente nella stanza l'oggetto da verificare -> bensi il suo nome al contrario

	public void VerificaSogliaDefault() {
		assertFalse(s1.hasAttrezzo(tool4.getNome()));		

	}
	
	@Test
	// verifico una soglia non default -> 1
	// non è presente nella stanza l'oggetto da verificare -> bensi il suo nome al contrario

	public void VerificaSogliaNonDefault() {
		assertFalse(s2.hasAttrezzo(tool2.getNome()));		
	}
	
	@Test
	// verifico il comportamento se la soglia non viene raggiunta -> i nomi non vengono invertiti.
	public void VerificaSogliaNonRaggiunta() { 
		assertTrue(s3.hasAttrezzo(tool1.getNome()) && s3.hasAttrezzo(tool2.getNome())); 
	}
	

}
