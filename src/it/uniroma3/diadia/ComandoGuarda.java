package it.uniroma3.diadia;

public class ComandoGuarda implements Comando {

	@Override
	public void esegui(Partita partita) {
		
		IOConsole ioConsole = partita.getIOConsole();
		ioConsole.mostraMessaggio("CFU Rimanenti: "+partita.getGiocatore().getCfu());
		ioConsole.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
		ioConsole.mostraMessaggio(partita.getStanzaCorrente().toString());

	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub
	}

}
