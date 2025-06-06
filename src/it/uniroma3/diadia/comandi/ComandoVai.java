package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.*;


public class ComandoVai extends AbstractComando {
  
	public ComandoVai(IO io) {
    	super(io);
    }
    
    @Override
    public void esegui(Partita partita) {
        Stanza stanzaCorrente = partita.getStanzaCorrente();
        Stanza prossimaStanza = null;
        if (this.parametro == null) {
            io.mostraMessaggio("Dove vuoi andare? Devi specificare una direzione");
            return;
        }
        prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.parametro);
        if (prossimaStanza == null) {
            io.mostraMessaggio("Direzione inesistente.");
            return;
        }
        partita.setStanzaCorrente(prossimaStanza);
        io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
        partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
        io.mostraMessaggio("CFU: "+partita.getGiocatore().getCfu());
    }
}