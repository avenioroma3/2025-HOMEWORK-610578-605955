package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.*;
import it.uniroma3.diadia.attrezzi.*;

public class ComandoRegala extends AbstractComando{
	
	private final String CHI  = "A chi dovrei regalare l'oggetto?";
	
	public ComandoRegala(IO io) {
		super(io);
	}

	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		Attrezzo attrezzo = partita.getGiocatore().getBorsa().getAttrezzo(this.parametro);
		if(personaggio!=null && attrezzo!=null) {
			io.mostraMessaggio(personaggio.riceviRegalo(attrezzo, partita));    
		}
		else
		if(attrezzo==null&&personaggio!=null) {
			io.mostraMessaggio("Non hai questo oggetto nella borsa");
		}
		else 
			io.mostraMessaggio(CHI);
	}
}
