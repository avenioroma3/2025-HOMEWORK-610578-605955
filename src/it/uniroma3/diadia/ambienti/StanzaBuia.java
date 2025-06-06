package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {
	private final String attrezzoIlluminante;
	
	/**
	 * Stanza di cui si posso vedere le informazioni solo con un determinato oggetto
	 * @param nome della stanza
	 * @param attrezzo che consente di vedere la descrizione della stanza
	 */
	public StanzaBuia(String nome, String attrezzoIlluminante) {
		super(nome);
		this.attrezzoIlluminante = attrezzoIlluminante;
	}
	
	/**
     * Se nella stanza NON c'è l'attrezzo illuminante, restituisce
     * "qui c'è buio pesto", altrimenti delega alla descrizione standard.
     */
	@Override
	public String getDescrizione() {
		if(this.hasAttrezzo(attrezzoIlluminante))
			return super.getDescrizione();
		else
			return "qui c'è buio pesto";
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
