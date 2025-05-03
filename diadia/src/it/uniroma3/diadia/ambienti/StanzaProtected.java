package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaProtected extends Stanza {
	
	static final protected int NUMERO_MASSIMO_DIREZIONI = 4;
	static final protected int NUMERO_MASSIMO_ATTREZZI = 10;
	
	
    protected Attrezzo[] attrezzi;
    protected int numeroAttrezzi;
    
    protected Stanza[] stanzeAdiacenti;
    protected int numeroStanzeAdiacenti;
    
	protected String[] direzioni;

	public StanzaProtected(String nome) {
		super(nome);
		this.numeroStanzeAdiacenti = 0;
	    this.numeroAttrezzi = 0;
	    this.direzioni = new String[NUMERO_MASSIMO_DIREZIONI];
	    this.stanzeAdiacenti = new Stanza[NUMERO_MASSIMO_DIREZIONI];
	    this.attrezzi = new Attrezzo[NUMERO_MASSIMO_ATTREZZI];
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
	    boolean aggiornato = false;
	    for (int i = 0; i < this.numeroStanzeAdiacenti; i++) {
	        if (this.direzioni[i].equals(direzione)) {
	            this.stanzeAdiacenti[i] = stanza;
	            aggiornato = true;
	        }
	    }

	    if (!aggiornato && this.numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) {
	        this.direzioni[this.numeroStanzeAdiacenti] = direzione;
	        this.stanzeAdiacenti[this.numeroStanzeAdiacenti] = stanza;
	        this.numeroStanzeAdiacenti++;
	    }
	}
	
	@Override
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append("Posizione: "+this.getNome());
    	risultato.append("\nUscite: ");
    	for (String direzione : this.direzioni)
    		if (direzione!=null)
    			risultato.append(" " + direzione);
    	risultato.append("\nAttrezzi nella stanza: ");
    	for (Attrezzo attrezzo : this.getAttrezzi()) {
    		if(attrezzo!=null)
    		risultato.append(attrezzo.toString()+" ");
    		else if(attrezzo==null){
    		}
    	}
    	return risultato.toString();
    }

}
