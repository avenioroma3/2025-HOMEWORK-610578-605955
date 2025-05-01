package it.uniroma3.diadia;

public class ComandoNonValido implements Comando {

	@Override
	public void esegui(Partita partita) {
		IOConsole ioconsole = partita.getIOConsole();
		
		ioconsole.mostraMessaggio("Comando non valido.");

	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub

	}

}
