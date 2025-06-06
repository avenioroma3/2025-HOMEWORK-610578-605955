package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.ConfigurazioneDiadia;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

import java.util.*;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
*/

public class Stanza {
	
	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	
	private String nome;
	private AbstractPersonaggio personaggio;
	
    private List<Attrezzo> attrezzi;
    private Map<String, Stanza> stanzeAdiacenti;
    private List<String> direzioni;
    private int numeroMassimoAttrezzi;
	
    
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public Stanza(String nome) {
        this.nome = nome;
        this.direzioni = new ArrayList<>();
        this.attrezzi = new ArrayList<Attrezzo>();
        this.stanzeAdiacenti = new HashMap<String, Stanza>();
        this.numeroMassimoAttrezzi = ConfigurazioneDiadia.getNumMassimoAttrezziStanza();
    	}

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(String direzione, Stanza stanza) { // *****!!IMPORTANTE!!*****
    	// se la direzione non era già presente, la aggiungo all'array (finché non supero il massimo)
    	 if (this.stanzeAdiacenti.size() >= 4)
    	        return; // o lancia eccezione
    	if(!this.stanzeAdiacenti.containsKey(direzione)) {
    		if(this.stanzeAdiacenti.size()<NUMERO_MASSIMO_DIREZIONI) {
    			this.direzioni.add(direzione);
    		}
    	}
    	this.stanzeAdiacenti.put(direzione, stanza);
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
	public Stanza getStanzaAdiacente(String direzione) {
        return this.stanzeAdiacenti.get(direzione);
	}

	/**
	 * Restituisce la mappa delle stanze adiacenti
	 * @return stanze adiacenti
	 */
	public Map<String, Stanza> getMapStanzeAdiacenti() {
		return this.stanzeAdiacenti;
	}
    /**
     * Restituisce il nome della stanza.
     * @return il nome della stanza
     */
    public String getNome() {
        return this.nome;
    }

    public AbstractPersonaggio getPersonaggio() {
		return personaggio;
	}

	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}

	/**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    public String getDescrizione() {
        return this.toString();
    }

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
    public List<Attrezzo> getAttrezzi() {
        return this.attrezzi;
    }

    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {  // *****!!IMPORTANTE!!*****
        if (this.attrezzi.size() < this.numeroMassimoAttrezzi&&!this.hasAttrezzo(attrezzo.getNome())) {
        	this.attrezzi.add(attrezzo);
        	return true;
        }
        else {
        	return false;
        }
    }

    /**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		for(Attrezzo a : this.attrezzi) {
			if(a.equals(attrezzo)) {
				this.attrezzi.remove(a);
				return true;
			}
		}
		return false;
	}
	
   /**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
    
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.nome);
    	risultato.append("\nUscite:");
    	for (String direzione : this.direzioni)
    		if (direzione!=null)
    			risultato.append(" " + direzione);
    	risultato.append("\nAttrezzi nella stanza: ");
    	if(this.attrezzi.isEmpty())
    		risultato.append("Nessun oggetto nella stanza");
    	for (Attrezzo attrezzo : this.attrezzi) {
    		//if(attrezzo!=null)
    			risultato.append(attrezzo.toString()+" ");
    	}
    		risultato.append("\nPersonaggi nella stanza: ");
    		if(this.getPersonaggio()==null)
    			risultato.append("Nessun personaggio");
    		else
    			risultato.append(this.getPersonaggio());
    	return risultato.toString();
    }
  
    
    
    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
   
    public boolean hasAttrezzo(String nomeAttrezzo) {
    	return this.getAttrezzo(nomeAttrezzo)!=null;
  	}

	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) { 
		for(Attrezzo a : this.attrezzi) {
			if(nomeAttrezzo!=null && a.getNome().equals(nomeAttrezzo)) return a;
		}
		return null;
	}

	public List<String> getDirezioni() {
	    return new ArrayList<>(this.direzioni);
    }
	
	@Override 
	public boolean equals(Object o) {
		if(this==o) return true;
		if(o==null) return false;
		Stanza that = (Stanza) o;
		return this.nome.equals(that.getNome());
	}
	
	@Override
	public int hashCode() {
		return this.nome.hashCode();
	}
}
	
	

