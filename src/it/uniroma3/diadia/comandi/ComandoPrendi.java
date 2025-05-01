package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {
	
	private String oggetto;

	public ComandoPrendi(String oggetto) {
		this.oggetto = oggetto;
	}

	@Override
	public void esegui(Partita partita) {
		IO ioconsole = partita.getIOConsole();
		Attrezzo pickuptool = partita.getStanzaCorrente().getAttrezzo(oggetto);
		if(pickuptool==null) {
			ioconsole.mostraMessaggio("L'attrezzo che hai inserito non è presente...");

			return; // esco dal metodo -> se continuo con le istruzioni successive mi da errore
		}
		partita.getStanzaCorrente().removeAttrezzo(pickuptool); 
		partita.giocatore.getBorsa().addAttrezzo(pickuptool);
		ioconsole.mostraMessaggio("Hai raccolto: "+pickuptool.getNome());

	}

	@Override
	public void setParametro(String parametro) {
		this.oggetto=parametro;

	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return "prendi";
	}

	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return oggetto;
	}

}
