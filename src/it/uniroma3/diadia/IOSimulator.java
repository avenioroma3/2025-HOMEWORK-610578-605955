package it.uniroma3.diadia;

public class IOSimulator implements IO {
	
	private String messaggiMostrati[];
	private int writeindex=0;
	
	
	private String messaggiDaLeggere[];
	private int readindex=0;

	public IOSimulator(String[] messaggiDaLeggere, int numeroMassimoMessaggi) {
	    this.messaggiDaLeggere = messaggiDaLeggere;  // array di comandi in input
	    this.messaggiMostrati = new String[numeroMassimoMessaggi]; // numero massimo di messaggi che possono essere salvati
	}
	
	// lettura e scrittura su array funzionano

	@Override
	public void mostraMessaggio(String messaggio) {		
		if(messaggio!=null && !messaggio.trim().isEmpty()) {
		if(writeindex<messaggiMostrati.length) {
			if(messaggio.equals("Grazie di aver giocato!")) {
//				System.out.println("correttamente non ho salvato la stringa di fine");
				return;
			}
			else if(messaggio.equals(""+
					"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
					"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
					"I locali sono popolati da strani personaggi, " +
					"alcuni amici, altri... chissa!\n"+
					"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
					"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
					"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
					"Per conoscere le istruzioni usa il comando 'aiuto'.\n")) {
				return;
			}
			messaggiMostrati[writeindex] = messaggio;
			System.out.println("\nmessaggio salvato:\n"+messaggiMostrati[writeindex]);
			writeindex = writeindex+1;
		}
		}

		
	}

	@Override
	public String leggiRiga() {
		String stringtoreturn=null;
		if(readindex<messaggiDaLeggere.length) {
			stringtoreturn = messaggiDaLeggere[readindex];
			System.out.println("\nmessaggio letto da array: "+stringtoreturn);
			readindex = readindex +1;
			return stringtoreturn;
		}
		return "fine";
	}
	
	// metodi per interagire con gli array
	
	
	// visualizza gli elementi dell'array
	public void leggiarray() {
		for(int i=0;i<writeindex;i++) {
			if(messaggiMostrati[i]==null) {
				System.out.println("ho trovato nulla");
				return;
			}
			System.out.println(i+")"+messaggiMostrati[i]+"\n");
		}
	}
	
	// verifica se nell'array è presente una stringa data in input
	
	public boolean VerificaPresenzaStringa(String stringa) {
		for(int i=0;i<writeindex;i++) {
			if(messaggiMostrati[i].equals(stringa)) {
				return true;
			}
		}
		return false;
	}

}
