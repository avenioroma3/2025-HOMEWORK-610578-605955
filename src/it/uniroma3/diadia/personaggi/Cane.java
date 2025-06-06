package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio{

	public final String CANE_ARRABBIATO = "BAU BAU GRRR! CRUNCH!";
	public final String CANE_ADDOMESTICATO = "BAU BAU GNAM! (Il cane afferra l'osso lasciando cadere qualcosa dalla sua bocca)";
	private Attrezzo attrezzo;
	
	/**
	 * @param nome
	 * @param presentazione
	 * @param attrezzo che ha nella bocca
	 */
	public Cane(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}

	@Override
	public String agisci(Partita partita) {
		int cfu = partita.getGiocatore().getCfu();
		partita.getGiocatore().setCfu(cfu-1);
		cfu = cfu-1;
		return CANE_ARRABBIATO + "\nHai perso un CFU = " + cfu ;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(!attrezzo.getNome().equals("osso")) {
			return this.agisci(partita);
		}
		else {
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			return CANE_ADDOMESTICATO;
		}
	}

	public Attrezzo getAttrezzo() {
		return this.attrezzo;
	}
}
