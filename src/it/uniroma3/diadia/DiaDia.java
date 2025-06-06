package it.uniroma3.diadia;


import java.io.FileNotFoundException;
import java.util.Scanner;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.comandi.*;


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

	public static final String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	public enum elencoComandi {
		VAI("1)vai *direzione*"),
	    GUARDA("2)guarda"),
	    FINE("3)fine"),
	    PRENDI("4)prendi *nome_oggetto*"),
	    POSA("5)posa *nome_oggetto*"),
	    AIUTO("6)aiuto"),
	    REGALA("7)regala *nome_oggetto*"),
	    INTERAGISCI("8)interagisci"),
	    SALUTA("9)saluta");

		private final String descrizione;
		elencoComandi(String descrizione) {
			this.descrizione = descrizione;
		}
		public String getDescrizione() {
			return this.descrizione;
		}
	}
	
	private Partita partita;
	public IO io;


	public DiaDia(IO io) {
		this.partita = new Partita();
		this.io = io;
	}
	public DiaDia(Labirinto labirinto, IO io) {
		this.partita = new Partita(labirinto);
		this.io = io;
	}

	public void gioca() {
		String istruzione; 
		io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do {
			istruzione = io.leggiRiga();
		} while(!processaIstruzione(istruzione));
	}   


	private boolean processaIstruzione(String istruzione) {
		AbstractComando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva(this.io);
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.vinta())
			io.mostraMessaggio("Hai Vinto!");
		if (!this.partita.getGiocatore().isVivo())
			io.mostraMessaggio("Hai esaurito i CFU...");

		return this.partita.isFinita();
	}
	
	
	public static void main(String[] argc) {
        // Qui apriamo lo Scanner in try-with-resources, così lo chiudiamo solo a fine programma
        try (Scanner scanner = new Scanner(System.in)) {
            IO io = new IOConsole(scanner);
            Labirinto labirinto = null;

            try {
                String nomeFileLabirinto = ConfigurazioneDiadia.getLabirintoFile();
                CaricatoreLabirinto caricatore = new CaricatoreLabirinto(nomeFileLabirinto);
                caricatore.carica();
                labirinto = caricatore.getLabirinto();
            } catch (FileNotFoundException e) {
                io.mostraMessaggio("Errore: file di configurazione 'labirinto.txt' non trovato.");
                return;
            } catch (FormatoFileNonValidoException e) {
                io.mostraMessaggio("Errore: formato del file labirinto.txt non valido → " + e.getMessage());
                return;
            }

            // Se sono arrivato qui, il labirinto è stato caricato correttamente:
            DiaDia gioco = new DiaDia(labirinto, io);
            gioco.gioca();

            // Al termine di `gioca()`, usciamo dal try-with-resources: scanner.close() verrà invocato qui
        }
	}

	
	/*public static void main(String[] argc) {
		/* N.B. unica istanza di IOConsole
		di cui sia ammessa la creazione 
		IO io = new IOConsole();
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
	}*/
	
	/* public static void main(String[] argc) {
		/* N.B. unica istanza di IOConsole
		di cui sia ammessa la creazione 
		IO io = new IOConsole();
		Labirinto labirinto = new LabirintoBuilder().addStanzaIniziale(“LabCampusOne”).addStanzaVincente(“Biblioteca”).addAdiacenza(“LabCampusOne”,“Biblioteca”,”ovest”).getLabirinto();
		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();
	}*/
}