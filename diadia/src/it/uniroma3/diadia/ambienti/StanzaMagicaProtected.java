package it.uniroma3.diadia.ambienti;

public class StanzaMagicaProtected extends StanzaMagica {

	
	final static protected int SOGLIA_MAGICA_DEFAULT = 3; // NOTA. Soglia magica default è presente anche in stanza magica come private, quindi non vi ho accesso. O la metto come protected o la ricopio dentro  qua come protected. 
	protected int contatoreAttrezziPosati;					// pero se fosse stato protected in stanza magica -> avrei potuto omettere la inizializzazione degli attributi -> avrei avuto accesso semplicemente con super 
	protected int sogliaMagica;

	public StanzaMagicaProtected(String nome) {
		super(nome, SOGLIA_MAGICA_DEFAULT); // con super invoco i parametri della super clase.
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = SOGLIA_MAGICA_DEFAULT;
	}
	
	/*
	 . Quando usi super in un costruttore, stai sempre invocando il costruttore della superclasse e passando i parametri che la superclasse si aspetta.
	 super in un costruttore -> oggetto; 
	  il costruttore della superclasse non costruisce l'intero oggetto: costruisce solo la parte della superclasse e permette alla sottoclasse di gestire la sua parte.
	  
	 */
	
//	Quando usi super in un costruttore di una sottoclasse, 
//	stai invocando il costruttore della superclasse per costruire la parte dell'oggetto che appartiene alla superclasse.
//	Passi come parametri i valori che il costruttore della superclasse si aspetta.

	public StanzaMagicaProtected(String nome, int soglia) {
		
		// con super sto costruendo un oggetto e passo come parametri nome e soglia che vengono "costruiti" dal costurttore della super classe
		super(nome, soglia);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}
	
}