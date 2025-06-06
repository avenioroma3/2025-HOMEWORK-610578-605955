package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza{

	private String attrezzoSbloccante;
	private String direzioneBloccata;
	/**
	 * Stanza che ha una direzione che può essere aperta solo con determinato oggetto
	 * @param nome stanza bloccata
	 * @param direzione bloccata
	 * @param Attrezzo che serve a sbloccare quella direzione
	 */
	public StanzaBloccata(String nome, String direzioneBloccata, String attrezzoSbloccante) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoSbloccante = attrezzoSbloccante;
	}
	
	/**
	 * Entri nella stanza adiacente solo se hai l'oggetto sbloccante altrimenti rimani 
	 * nella stanza corrente
	 */
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if(direzione.equals(direzioneBloccata)&&!hasAttrezzo(attrezzoSbloccante)) {
			return this;
		}
		return super.getStanzaAdiacente(direzione);
	}
	
	@Override
	public String getDescrizione() {
		String descrizione = super.getDescrizione();
		if(!this.hasAttrezzo(attrezzoSbloccante)) {
			descrizione = descrizione + "\nAttenzione, la porta a "+this.direzioneBloccata+" è bloccata, per sbloccarla ti serve: "+this.attrezzoSbloccante;
		}
		return descrizione;
	}
	
	@Override 
	public boolean equals(Object o) {
		return super.equals(o);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
	


