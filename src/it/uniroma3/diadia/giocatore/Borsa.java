package it.uniroma3.diadia.giocatore;

import java.util.*;

import it.uniroma3.diadia.ConfigurazioneDiadia;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa{
	
	private List<Attrezzo> attrezzi;
	private int pesoMax;

	public Borsa() {
		this(ConfigurazioneDiadia.getPesoMaxBorsa());
		this.attrezzi = new ArrayList<Attrezzo>();
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new ArrayList<Attrezzo>();
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		this.attrezzi.add(attrezzo);
		return true; 
	}

	public int getPesoMax() {
		return pesoMax;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {	
		for(Attrezzo a : this.attrezzi) {
			if (nomeAttrezzo!=null && a.getNome().equals(nomeAttrezzo))
				return a;
			}
		return null;
	}

	public int getPeso() {
		int peso = 0;
		for (Attrezzo a : this.attrezzi) {
			peso += a.getPeso();
		}
		return peso;
	}

	public boolean isEmpty() {
		return  this.attrezzi.isEmpty();
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {  
		return this.getAttrezzo(nomeAttrezzo) != null;
	}

	public boolean removeAttrezzo(String nomeAttrezzo) {  
		for(Attrezzo a : this.attrezzi) {
			if(a.getNome().equals(nomeAttrezzo)) {
					this.attrezzi.remove(a);	
					return true;
			}			
		}
		return false;
	}
	
	/**
	 * estituisce la lista degli attrezzi nella borsa ordinati per peso e quindi,
	a parità di peso, per nome
	 * @return lista ordinata per peso e poi in caso nome
	 */
	public List<Attrezzo> getContenutoOrdinatoPerPeso() {
		class PesoENomeComparator implements Comparator<Attrezzo>{

			@Override
			public int compare(Attrezzo a1, Attrezzo a2) {
				int diff = a1.getPeso() - a2.getPeso();
				if(diff!=0) return diff;
				return a1.getNome().compareTo(a2.getNome());
			}
		}
		
		Collections.sort(this.attrezzi, new PesoENomeComparator());
		return this.attrezzi;
	}
	
	/**restituisce l'insieme degli attrezzi nella borsa ordinati per nome
	 * 
	 * @return lista ordinata per nome
	 */
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
		class NomeComparator implements Comparator<Attrezzo>{

			@Override
			public int compare(Attrezzo a1, Attrezzo a2) {
				return a1.getNome().compareTo(a2.getNome());
			}
		}
		
		SortedSet<Attrezzo> set = new TreeSet<>(new NomeComparator());
		set.addAll(this.attrezzi);
		return set;
	}
	
	/**
	 * restituisce una mappa che associa un intero (rappresentante un
peso) con l’insieme (comunque non vuoto) degli attrezzi di tale peso:
tutti gli attrezzi dell'insieme che figura come valore hanno lo stesso
peso pari all'intero che figura come chiave
	 * @return mappa che raggruppa oggetti con stesso peso
	 */
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		class NomeComparator implements Comparator<Attrezzo>{

			@Override
			public int compare(Attrezzo a1, Attrezzo a2) {
				return a1.getNome().compareTo(a2.getNome());
			}
		}
		
		Map<Integer, Set<Attrezzo>> map = new HashMap<>();
		for(Attrezzo a : this.attrezzi) {
			int peso = a.getPeso();
			if(!map.containsKey(peso))
				map.put(peso, new TreeSet<>(new NomeComparator()));
			map.get(peso).add(a);
		}
		return map;
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
		class PesoENomeComparator implements Comparator<Attrezzo>{

			@Override
			public int compare(Attrezzo a1, Attrezzo a2) {
				int diff = a1.getPeso() - a2.getPeso();
				if(diff!=0) return diff;
				return a1.getNome().compareTo(a2.getNome());
			}
		}
		
		SortedSet<Attrezzo> set = new TreeSet<>(new PesoENomeComparator());
		/*Ho ordinato, basta che aggiungo tutti gli elementi (già ordinati) alla lista*/
		set.addAll(this.attrezzi);
		return set;
	}
	public String toString() {
		StringBuilder s = new StringBuilder();
		int i = 0;
		if (!this.isEmpty()) {
			s.append("Contenuto borsa (" + this.getPeso() + "kg/" + this.getPesoMax() + "kg): "+"\n");
			for (Attrezzo a : this.attrezzi)
				s.append(i+1+") "+a.toString() + "\n");
		} else
			s.append("Borsa vuota");
		return s.toString();
	}
	
}



