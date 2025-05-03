package it.uniroma3.diadia.giocatore;

public class Giocatore {
	
	static final private int CFU_INIZIALI = 20;
	private int cfu;
	public Borsa borsa;
	
	public Giocatore() {
		this.cfu=CFU_INIZIALI;
		this.borsa=borsa;
//		System.out.println("sto cvreando un giuocatore con cfu "+cfu);
	}
	
	public void setCfu(int cfu) {
		this.cfu=cfu;
	}

	public void setBorsa(Borsa borsa) {
		this.borsa=borsa;
	}
	
	public int getCfu() {
		return this.cfu;
	}
	
	public Borsa getBorsa() {
		return this.borsa;
	}
	

	
}

