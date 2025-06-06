package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio{

	public final String MESSAGGIO_MALEDUCATO = "Sei un maleducato, VATTENE VIA! (ti ritrovi in un altra stanza)";
	public final String MESSAGGIO = "Mi vergogno, VATTENE VIA! (ti ritrovi in un altra stanza)";
	public final String MESSAGGIO_REGALO = "HIHIAH, STOLTO, grazie del regalo, adesso questo oggetto è mio HIHIHI";
			
	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		Stanza stanzaCorrente = partita.getStanzaCorrente();
        Stanza stanzaScelta = null;
		int nAttrezzi;
		if(this.haSalutato()) {
			nAttrezzi=-1;
			for(String direzione : stanzaCorrente.getDirezioni()) {
				if(stanzaCorrente.getStanzaAdiacente(direzione)!=null) {
					if(stanzaCorrente.getStanzaAdiacente(direzione).getAttrezzi().size()>nAttrezzi) {
						stanzaScelta = stanzaCorrente.getStanzaAdiacente(direzione);
						nAttrezzi = stanzaScelta.getAttrezzi().size();
					}
				}
			}
			if(stanzaScelta!=null)
				partita.setStanzaCorrente(stanzaScelta);
			msg = MESSAGGIO;
		}
		else {
			nAttrezzi = Integer.MAX_VALUE;
			for(String direzione : stanzaCorrente.getDirezioni()) {
				if(stanzaCorrente.getStanzaAdiacente(direzione)!=null) {
					if(stanzaCorrente.getStanzaAdiacente(direzione).getAttrezzi().size()<nAttrezzi) {
					stanzaScelta = stanzaCorrente.getStanzaAdiacente(direzione);
					nAttrezzi = stanzaScelta.getAttrezzi().size();
					}
				}
			}
			if(stanzaScelta!=null)
				partita.setStanzaCorrente(stanzaScelta);
			msg = MESSAGGIO_MALEDUCATO;
		}
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
		return MESSAGGIO_REGALO;
	}
	
}
