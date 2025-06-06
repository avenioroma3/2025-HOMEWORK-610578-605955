package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio{
	private static final String MESSAGGIO = "Sei un vero simpaticone, " +
			"con una mia magica azione, troverai un nuovo oggetto " +
			"per il tuo borsone!";
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";
	private static final String MESSAGGIO_REGALO = "Sei un vero simpaticone, con una mia magica azione, alleggerisco il tuo oggetto , nel tuo borsone";
	private static final String MESSAGGIO_BORSA = "Oh che spasso, che allegrone!\n"
			+ "Beccati un dono per il borsone!\n"
			+ "Ma ops… la tua borsa fa il botto,\n"
			+ "torna da me con meno ingombro, di sotto!";
	
	private Attrezzo attrezzo;
	
	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}
	
	@Override
	public String agisci(Partita partita) {
		String msg;
		int peso = partita.getGiocatore().getBorsa().getPeso();
		if(this.attrezzo!=null) {
			if(peso+attrezzo.getPeso()>=partita.getGiocatore().getBorsa().getPesoMax()) {
				msg = MESSAGGIO_BORSA;
			}
			else {
				msg = MESSAGGIO;
				partita.getGiocatore().getBorsa().addAttrezzo(this.attrezzo);
				this.attrezzo=null;
			}
		} 
		else
			msg = MESSAGGIO_SCUSE;
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		Attrezzo dono = partita.getGiocatore().getBorsa().getAttrezzo(attrezzo.getNome());
		int peso = dono.getPeso();
		dono.setPeso(peso/2);
		return MESSAGGIO_REGALO;
	}
	
	public Attrezzo getAttrezzo() {
		return this.attrezzo;
	}
}
