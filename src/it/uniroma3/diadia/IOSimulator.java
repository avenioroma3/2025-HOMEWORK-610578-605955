package it.uniroma3.diadia;

import java.util.*;

public class IOSimulator implements IO {
    private static final int MAX = 100;
    private List<String> messaggi = new ArrayList<>(MAX);
    private List<String> righeIniettate = new ArrayList<>(MAX);
    private int indiceLettura = 0;

    /**
     *stampa un messaggio output
     */
    @Override
    public void mostraMessaggio(String msg) {
        if (this.messaggi.size() < MAX) {
            this.messaggi.add(msg);
        }
    }

    /**
     *legge una riga input da tastiera
     */
    @Override
    public String leggiRiga() {
        if (this.indiceLettura < this.righeIniettate.size()) {
            return this.righeIniettate.get(indiceLettura++);
        }
        return "";
    }

    /**
     * Aggiunge una riga come input simulato
     */
    public void addInput(String riga) {
        if (this.righeIniettate.size() < MAX) {
            this.righeIniettate.add(riga);
        }
    }
  
    /*restituisce il numero di messaggi*/
    public int getMessaggiCount() {
    	return this.messaggi.size();
    }
    
    /**
     * Restituisce il messaggio in posizione i
     * @param indice del messaggio che si vuole ottenere
     */
    public String getMessaggioAt(int i) {
        if (i >= 0 && i < this.messaggi.size()) {
            return this.messaggi.get(i);
        }
        return null;
    }
}