package it.uniroma3.diadia;


import java.lang.management.GarbageCollectorMXBean;
import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;
import it.uniroma3.diadia.ambienti.*;


/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.\n";
	
	// static final private String[] elencoComandi = {"1)vai ", "2)aiuto", "3)fine","4)prendi *nome_oggetto*","5)posa *nome_oggetto*","6)inventario"};

	private Partita partita;
	public IO IOConsole;
//	public Labirinto Labirinto;

	public DiaDia(IO io) {
		this.IOConsole= io;
		this.partita = new Partita();
		this.partita.setIOConsole(io); // IMPORTANTE PER FARE IN MODO CHE POSSA ESSERE USATO DAGLI ALTRI METODI.
	}

	public void gioca() {
		String istruzione; 
		Scanner scannerDiLinee;

		IOConsole.mostraMessaggio(MESSAGGIO_BENVENUTO);
		

		
		do		
		istruzione =  IOConsole.leggiRiga();
	while (!processaIstruzione(istruzione));
		
	
		
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
//	private boolean processaIstruzione(String istruzione) {
//		Comando comandoDaEseguire = new Comando(istruzione);
//
//		if (comandoDaEseguire.getNome().equals("fine")) {
//			this.fine(); 
//			return true;
//		} else if (comandoDaEseguire.getNome().equals("vai"))
//			this.vai(comandoDaEseguire.getParametro());
//		else if (comandoDaEseguire.getNome().equals("aiuto"))
//			this.aiuto();
//		else
////			
//			IOConsole.mostraMessaggio("Comando Sconosciuto");
//		if (this.partita.isFinita()) { // is vinta non era la condizione giusta
//			IOConsole.mostraMessaggio("Hai vinto!");
//			return true;
//		} else
//			return false;
//	}   
//	
//	private boolean processaIstruzione(String istruzione) {
//		Comando comandoDaEseguire = new Comando(istruzione);
//
//		if (comandoDaEseguire.getNome().equals("fine")) {
//			this.fine(); 
//			return true;
//		} else if (comandoDaEseguire.getNome().equals("vai"))
//			this.vai(comandoDaEseguire.getParametro());
//	
//	else if(comandoDaEseguire.getNome().equals("prendi")) {
//		this.prendiOggetto(comandoDaEseguire.getParametro());
//	}
//	else if(comandoDaEseguire.getNome().equals("posa")) {
//		this.posaOggetto(comandoDaEseguire.getParametro());
//		
//	}
//	else if(comandoDaEseguire.getNome().equals("inventario")) {
//		IOConsole.mostraMessaggio(partita.giocatore.borsa.toString());
//		
//	}
//		else if (comandoDaEseguire.getNome().equals("aiuto"))
//			this.aiuto();
//		else
////			
//			IOConsole.mostraMessaggio("Comando Sconosciuto");
//		if (this.partita.isFinita()) { // is vinta non era la condizione giusta
//			IOConsole.mostraMessaggio("Hai vinto!");
//			return true;
//		} else
//			return false;
//	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
//	private void aiuto() {
//		for(int i=0; i< elencoComandi.length; i++) 
////			System.out.print(elencoComandi[i]+" ");
//			IOConsole.mostraMessaggio(elencoComandi[i]+" ");
//
//		IOConsole.mostraMessaggio("");
//		
//	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
//	private void vai(String direzione) {
//		if(direzione==null)
////			System.out.println("Dove vuoi andare ?");
//			IOConsole.mostraMessaggio("Dove vuoi andare ?");
//
//		Stanza prossimaStanza = null;
//		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
//		if (prossimaStanza == null)
//			IOConsole.mostraMessaggio("Direzione inesistente");
//
//		else {
//			this.partita.setStanzaCorrente(prossimaStanza);
//			int cfu = this.partita.giocatore.getCfu();
//			this.partita.giocatore.setCfu(cfu-1); // -- non funziona perche il valore attuale di cfu viene prima passato alla funzione setCfu(), e solo dopo cfu viene decrementato.
//		}
////		System.out.println(partita.getStanzaCorrente().getDescrizione())
//		IOConsole.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
//		IOConsole.mostraMessaggio("CFU attuali:"+ String.valueOf(partita.giocatore.getCfu())+"\n");
//	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
//		System.out.println("Grazie di aver giocato!");  // si desidera smettere
		IOConsole.mostraMessaggio(("Grazie di aver giocato!"));
		
	}

	public static void main(String[] argc) {
//		IOConsole IoConsole = new IOConsole();
		IO io= new IOConsole(); // sto creando un'oggetto di tipo IO -> lo tratto come se fosse IOConsole (tempo dinamico)
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
	}
	
	

	
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire;
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica(); // prima era FabbricadiComandiFisarmonica, ho riaggiornato tutto il codice
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita);
		IOConsole.mostraMessaggio("\n");
		if (this.partita.vinta())

		IOConsole.mostraMessaggio("Hai vinto!");
		if (!this.partita.giocatoreIsVivo())

		IOConsole.mostraMessaggio("Hai esaurito i CFU...");

		return this.partita.isFinita();
	
	
}
	
	public Partita getPartita()  {
		return this.partita;
	}
	
	public String getMessaggioBenvenuto() {
		return this.MESSAGGIO_BENVENUTO;
	}
	
}