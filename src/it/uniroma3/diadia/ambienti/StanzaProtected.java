package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaProtected extends Stanza {
	
	static final protected int NUMERO_MASSIMO_DIREZIONI = 4;
	static final protected int NUMERO_MASSIMO_ATTREZZI = 10;
	
	protected String nome;
	
    protected Attrezzo[] attrezzi;
    protected int numeroAttrezzi;
    
    protected Stanza[] stanzeAdiacenti;
    protected int numeroStanzeAdiacenti;
    
	protected String[] direzioni;

	public StanzaProtected(String nome) {
		super(nome);
		// TODO Auto-generated constructor stub
	}

}
