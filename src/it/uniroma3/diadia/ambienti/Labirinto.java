package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.util.*;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.*;

/**
 * Rappresenta un labirinto i cui nodi sono stanze (Stanza) collegate tra loro,
 * con una stanza iniziale (entrata) e una stanza vincente (uscita).
 * Tutta la costruzione “fisica” del grafo di stanze viene demandata a CaricatoreLabirinto.
 */
public class Labirinto {
    
    private Stanza entrata;
    private Stanza uscita;

    // Costruttore reso privato: si potrà istanziare Labirinto solo da dentro la classe (es. dal Builder)
    private Labirinto() {
        // Non costruisce nulla; getLabirinto() del Caricatore o il Builder imposteranno entrata e uscita.
    }

    public void setEntrata(Stanza entrata) {
        this.entrata = entrata;
    }

    public void setUscita(Stanza uscita) {
        this.uscita = uscita;
    }

    public Stanza getStanzaIniziale() {
        return this.entrata;
    }

    public Stanza getStanzaVincente() {
        return this.uscita;
    }
    
    public static Labirinto caricaDaFile(String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException {
        CaricatoreLabirinto caricatore = new CaricatoreLabirinto(nomeFile);
        caricatore.carica();
        return caricatore.getLabirinto();
    }
    
    /**
     * Factory method per ottenere un nuovo Builder.
     */
    public static LabirintoBuilder newBuilder() {
        return new LabirintoBuilder();
    }

    /**
     * Classe statica nidificata che svolge il ruolo di Builder per Labirinto.
     * Può accedere al costruttore privato di Labirinto.
     */
    public static class LabirintoBuilder {
        
        private static final String DESCRIZIONE_MAGO = 
            " Benvenuto, avventuriero, guarda bene la mia stanza,\n"
            + "dove ogni oggetto ha una sua importanza.\n"
            + "Ne piazzo a bizzeffe, non per distrazione,\n"
            + "ma per creare... un'illusione!";
        private static final String DESCRIZIONE_STREGA = 
            " Non aver paura di me hihih. \nEHI! Però non guardarmi "
            + "non mi piace essere disturbata... \nHai qualcosa per me un dono forse? \nTranquillo di me ti puoi fidare"
            + "hih";
        private static final String DESCRIZIONE_CANE   = 
            " L’abbaiare rompe il silenzio con ferocia. Il cane, \nun randagio sporco e dagli occhi febbrili, "
            + "mostra le zanne in segno di sfida.\n"
            + "Ma c’è qualcosa che non torna. Un riflesso, un luccichio tra le fauci.\n"
            + "C’è qualcosa... tra i suoi denti.";
        
        private Map<String, Stanza> stanze = new HashMap<>();
        private Stanza ultimaStanza;
        private Stanza stanzaIniziale;
        private Stanza stanzaVincente;
        
        /**
         * Costruttore pubblico del Builder (non tocca affatto il costruttore di Labirinto).
         */
        public LabirintoBuilder() {
            // lasciare vuoto
        }

        /**
         * Crea una nuova stanza in successione alla precedente e la marca come ultima
         * @param nome stanza da creare 
         * @return this
         */
        public LabirintoBuilder addStanza(String nome) {
            if(!stanze.containsKey(nome)) {
                stanze.put(nome, new Stanza(nome));
            }
            this.ultimaStanza = stanze.get(nome);
            return this;
        }
        
        /**
         * Crea la stanza iniziale del labirinto
         * @param nome stanza iniziale
         * @return this
         */
        public LabirintoBuilder addStanzaIniziale(String nome) {
            this.addStanza(nome);
            this.stanzaIniziale = this.ultimaStanza;
            return this;
        }
        
        /**
         * Crea la stanza vincente
         * @param nome stanza vincente
         * @return this
         */
        public LabirintoBuilder addStanzaVincente(String nome) {
            this.addStanza(nome);
            this.stanzaVincente = this.ultimaStanza;
            return this;
        }
        
        /**
         * Aggiunge un attrezzo all'ultima stanza aggiunta
         * @param nome attrezzo
         * @param peso attrezzo
         * @return this
         */
        public LabirintoBuilder addAttrezzo(String nome, int peso) {
            if(this.ultimaStanza == null)
                return this;
            this.ultimaStanza.addAttrezzo(new Attrezzo(nome, peso));
            return this;
        }
        
        /**
         * Aggiunge una stanza adiacente nella direzione specificata
         * @param from stanza corrente
         * @param to stanza in cui voglio andare 
         * @param dir direzione per andare nella stanza in cui voglio andare 
         * @return this
         */
        public LabirintoBuilder addAdiacenza(String from, String to, String dir) {
            this.addStanza(from);
            this.addStanza(to);
            this.stanze.get(from).impostaStanzaAdiacente(dir, stanze.get(to));
            return this;
        }
        
        public Map<String, Stanza> getListaStanze() {
            return this.stanze;
        }
        
        /**
         * Restituisce il Labirinto configurato con stanze adiacenti, attrezzi, ecc.
         * @return labirinto
         */
        public Labirinto getLabirinto() {
            Labirinto lab = new Labirinto();  // può chiamare il costruttore privato perché è classe interna
            lab.setEntrata(this.stanzaIniziale);
            lab.setUscita(this.stanzaVincente);
            return lab;
        }

        /* Metodi stanze particolari per far funzionare tutti i test case */
        
        /**
         * Crea una nuova stanza Magica in successione alla precedente e la marca come ultima
         * @param nomeStanzaMagica stanza da creare 
         * @param sogliaMagica soglia per la stanza magica
         * @return this
         */
        public LabirintoBuilder addStanzaMagica(String nomeStanzaMagica, int sogliaMagica) {
            if(!stanze.containsKey(nomeStanzaMagica)) {
                stanze.put(nomeStanzaMagica, new it.uniroma3.diadia.ambienti.StanzaMagica(nomeStanzaMagica, sogliaMagica));
            }
            this.ultimaStanza = stanze.get(nomeStanzaMagica);
            return this;
        }

        /**
         * Crea una nuova stanza Bloccata in successione alla precedente e la marca come ultima
         * @param nome stanza bloccata 
         * @param direzione direzione bloccata
         * @param attrezzoSbloccante nome dell'attrezzo che sblocca
         * @return this
         */
        public LabirintoBuilder addStanzaBloccata(String nome, String direzione, String attrezzoSbloccante) {
            if(!this.stanze.containsKey(nome)) {
                stanze.put(nome, new it.uniroma3.diadia.ambienti.StanzaBloccata(nome, direzione, attrezzoSbloccante));
            }
            this.ultimaStanza = stanze.get(nome);
            return this;
        }

        /**
         * Crea una nuova stanza Buia in successione alla precedente e la marca come ultima
         * @param nome nome della stanza buia 
         * @param attrezzoIlluminante nome dell'attrezzo che illumina
         * @return this
         */
        public LabirintoBuilder addStanzaBuia(String nome, String attrezzoIlluminante) {
            if(!this.stanze.containsKey(nome)) {
                this.stanze.put(nome, new it.uniroma3.diadia.ambienti.StanzaBuia(nome, attrezzoIlluminante));
            }
            this.ultimaStanza = this.stanze.get(nome);
            return this;
        }
        
        /**
         * Aggiunge un Magio nella stanza desiderata
         * @param nomePersonaggio nome del mago 
         * @param nomeStanza stanza in cui inserirlo
         * @param attrezzo attrezzo che ti dona il Mago
         * @return this
         */
        public LabirintoBuilder addMago(String nomePersonaggio, String nomeStanza, Attrezzo attrezzo) {
            Stanza s = this.stanze.get(nomeStanza);
            Mago m = new Mago(nomePersonaggio, DESCRIZIONE_MAGO, attrezzo);
            s.setPersonaggio(m);
            return this;
        }

        /**
         * Aggiunge un Cane nella stanza desiderata
         * @param nomePersonaggio nome del cane 
         * @param nomeStanza stanza in cui inserirlo
         * @param attrezzo attrezzo che ti dona il Cane
         * @return this
         */
        public LabirintoBuilder addCane(String nomePersonaggio, String nomeStanza, Attrezzo attrezzo) {
            Stanza s = this.stanze.get(nomeStanza);
            Cane c = new Cane(nomePersonaggio, DESCRIZIONE_CANE, attrezzo);
            s.setPersonaggio(c);
            return this;
        }

        /**
         * Aggiunge una Strega nella stanza desiderata
         * @param nomePersonaggio nome della strega 
         * @param nomeStanza stanza in cui inserirla
         * @return this
         */
        public LabirintoBuilder addStrega(String nomePersonaggio, String nomeStanza) {
            Stanza s = this.stanze.get(nomeStanza);
            Strega st = new Strega(nomePersonaggio, DESCRIZIONE_STREGA);
            s.setPersonaggio(st);
            return this;
        }
    }
}

