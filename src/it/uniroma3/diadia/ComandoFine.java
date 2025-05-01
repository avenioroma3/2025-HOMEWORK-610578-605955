package it.uniroma3.diadia;

public class ComandoFine implements Comando {

	@Override
	public void esegui(Partita partita) {
		IOConsole ioconsole = partita.getIOConsole();
		ioconsole.mostraMessaggio(("Grazie di aver giocato!"));
		partita.setFinita();
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub

	}

}
