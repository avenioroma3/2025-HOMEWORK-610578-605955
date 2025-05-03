package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccata extends StanzaProtected{
	
	String direzioneBloccata;
	String specialtool;
	

	public StanzaBloccata(String nome,String direzioneBloccata,String specialtool) {
		super(nome);
		this.direzioneBloccata=direzioneBloccata;
		this.specialtool=specialtool;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
//		System.out.println("la stanza ha n direzioni possibili: "+this.getDirezioni().length); // funziona, rileva le direzioni.
        Stanza stanza = null;
       if(this.hasAttrezzo(specialtool)) {
//    	   System.out.println("ho attrezzo->ritorno riferimento a stanza richiesta");
//    	   System.out.println("numeroStanzeAdiacenti: " + this.numeroStanzeAdiacenti);
		for(int i=0; i<this.numeroStanzeAdiacenti; i++) {
			
//			System.out.println("sono dentro il ciclo");
			
        	if (this.direzioni[i] != null && this.direzioni[i].equals(direzione)) { // mi consente di non ottenere un riferimento nullo (this.direzioni[i] != null)
//        		System.out.println("ho trovato la direzione: "+direzione);
        		stanza = this.stanzeAdiacenti[i];
        		break;
        	}
		}
        return stanza;
       }
       else {
//    	   System.out.println("sto ritornando la stanza corrente");
    	   return  this;
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
    	risultato.append("\n\nDirezione bloccata: "+direzioneBloccata);
    	risultato.append("\nNella stanza ci deve essere l'oggetto: "+specialtool);
    	return risultato.toString(); 
    }
	

	@Override
	public String getDescrizione() {
        return this.toString();
    }
      
	}
	


