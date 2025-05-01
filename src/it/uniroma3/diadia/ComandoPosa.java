package it.uniroma3.diadia;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando {
	
	private String oggetto;

	public ComandoPosa(String oggetto) {
		this.oggetto=oggetto;
	}

	@Override
	public void esegui(Partita partita) {
		IOConsole ioconsole = partita.getIOConsole();

		Attrezzo droppedtool = partita.giocatore.getBorsa().getAttrezzo(oggetto);
		if(droppedtool==null) {
			ioconsole.mostraMessaggio("L'oggetto da posare non è presente nella borsa...");
			return; 
		}
		partita.giocatore.getBorsa().removeAttrezzo(oggetto);
		partita.getStanzaCorrente().addAttrezzo(droppedtool);
		
		ioconsole.mostraMessaggio("Hai posato nella stanza: "+droppedtool.getNome());
		
	}

	@Override
	public void setParametro(String parametro) {
		this.oggetto=parametro;

	}

}
