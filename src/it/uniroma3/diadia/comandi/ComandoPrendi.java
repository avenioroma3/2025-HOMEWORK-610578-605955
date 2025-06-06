package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando{
	
	public ComandoPrendi(IO io) {
		super(io);
	}
	@Override
	public void esegui(Partita partita) {
		if (parametro == null) {
	        io.mostraMessaggio("Devi specificare il nome dell'oggetto da prendere.");
	        return;
	    }
		Attrezzo pickuptool = partita.getStanzaCorrente().getAttrezzo(parametro);
		if(!partita.getStanzaCorrente().hasAttrezzo(parametro)) {
			io.mostraMessaggio("L'oggetto: "+parametro+" non è in questa stanza");
			return;
		}
		else {
			partita.getStanzaCorrente().removeAttrezzo(pickuptool); 
			partita.getGiocatore().getBorsa().addAttrezzo(pickuptool);
			io.mostraMessaggio(parametro+" raccolto e aggiunto nell'inventario");
		}
	}
}
