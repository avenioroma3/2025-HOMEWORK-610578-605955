package diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {
	
	Partita partita = new Partita();
	
	Attrezzo tool1 = new Attrezzo ("Torcia",2);
	Attrezzo tool2 = new Attrezzo ("Fiammifero",1);


	StanzaBuia s1 = new StanzaBuia("Cortile",tool1.getNome());
	StanzaBuia s2 = new StanzaBuia ("Portineria",tool2.getNome());
	
	@BeforeEach
	public void setup() {
		s1.impostaStanzaAdiacente("nord", s2);
		s1.addAttrezzo(tool1);
	}
	
	
	// nb: i test servono solamente per verificare se effettivamente i testi vengono visualizzati nella situazione apposita.

	@Test
	// è presente l'oggetto "speciale"
	public void ControlloTestoVisibile() {
		s1.addAttrezzo(tool1);
		System.out.println("\n4)TEST  VISIBILE (c'è oggetto) \n"+s1.getDescrizione());
	}
	
	@Test
	// NON è presente l'oggetto "speciale"

	public void ControlloTestoNonVisibile() {
		System.out.println("\n3)TEST NON VISIBILE (non c'è oggetto) \n"+s2.getDescrizione());

	}
	
	@Test
	// è presente l'oggetto "speciale" nella stanza -> mi sposto in un altra stanza che non ce l'ha
	public void ControlloPrimaVisibilePoiNo(){
		
		System.out.println("\n2)TEST CONCATENATO: visibile -> non visibile");
		System.out.println("TEST  VISIBILE (c'è oggetto)\n"+s1.getDescrizione());
		System.out.println("\nmi sposto di stanza\n");
		partita.setStanzaCorrente(s2);
		System.out.println("TEST NON VISIBILE (non c'è oggetto)\n"+s2.getDescrizione());


	}
	

	@Test
	// NON è presente l'oggetto "speciale" nella stanza -> mi sposto in un altra stanza che ce l'ha
	public void ControlloPrimaNonVisibilePoiSi(){
		
		System.out.println("\n1)TEST CONCATENATO : non visibile->visibile");
		System.out.println("TEST NON VISIBILE (non c'è oggetto)\n"+s2.getDescrizione());
		System.out.println("\nmi sposto di stanza\n");
		partita.setStanzaCorrente(s1);
		System.out.println("TEST  VISIBILE (c'è oggetto)\n"+s1.getDescrizione());

	}
	


}
