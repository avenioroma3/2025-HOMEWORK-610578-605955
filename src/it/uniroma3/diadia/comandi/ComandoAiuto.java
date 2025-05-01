package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando {

static final private String[] elencoComandi = {"1)vai ", "2)aiuto", "3)fine","4)prendi *nome_oggetto*","5)posa *nome_oggetto*","6)inventario"};


	@Override
	public void esegui(Partita partita) {
	IO ioconsole = partita.getIOConsole();
			for(int i=0; i< elencoComandi.length; i++) 
				ioconsole.mostraMessaggio(elencoComandi[i]+" ");

			ioconsole.mostraMessaggio("");
			
		

	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return "aiuto";
	}

	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return null;
	}
	


}
