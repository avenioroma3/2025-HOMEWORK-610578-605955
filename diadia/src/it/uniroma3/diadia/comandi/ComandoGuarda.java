package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {

	@Override
	public void esegui(Partita partita) {
		
		IO ioConsole = partita.getIOConsole();
		ioConsole.mostraMessaggio("CFU Rimanenti: "+partita.getGiocatore().getCfu());
		ioConsole.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
		ioConsole.mostraMessaggio(partita.getStanzaCorrente().toString());

	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return "guarda";
	}

	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return null;
	}

}
