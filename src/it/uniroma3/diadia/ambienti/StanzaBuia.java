package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuia extends StanzaProtected{
	
	String specialtool;

	public StanzaBuia(String nome,String specialtool) {
		super(nome);
		this.specialtool=specialtool;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getDescrizione() {
		
    	   if(this.hasAttrezzo(specialtool)) {
    	       return this.toString();
    	   
       }
       return "Qui c'è un buio pesto...";
    }
	
	public String getspecialtool() {
		return specialtool;
	}
}
