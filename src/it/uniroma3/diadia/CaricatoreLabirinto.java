package it.uniroma3.diadia;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Labirinto;    
import it.uniroma3.diadia.attrezzi.Attrezzo;


public class CaricatoreLabirinto {

    private static final String STANZE_MARKER       = "Stanze:";
    private static final String STANZE_MAGICHE      = "Magiche:";
    private static final String STANZE_BLOCCATE     = "Bloccate:";
    private static final String STANZE_BUIE         = "Buie:";
    private static final String PERSONAGGI_MARKER   = "Personaggi:";
    private static final String STANZA_INIZIALE_MARKER = "Inizio:";
    private static final String STANZA_VINCENTE_MARKER = "Vincente:";
    private static final String ATTREZZI_MARKER     = "Attrezzi:";
    private static final String USCITE_MARKER       = "Uscite:";

    private LineNumberReader lineReader;
    private Labirinto.LabirintoBuilder builder;   

    public CaricatoreLabirinto(Reader reader) {
        this.lineReader = new LineNumberReader(new BufferedReader(reader));
        this.builder    = Labirinto.newBuilder();    
    }
    
    public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
        this.lineReader = new LineNumberReader(new FileReader(nomeFile));
        this.builder    = Labirinto.newBuilder();    
    }

    public void carica() throws FormatoFileNonValidoException {
        try {
            this.leggiECreaStanze();
            this.leggiECollocaStanzeMagiche();
            this.leggiECreaStanzeBloccate();
            this.leggiECreaStanzeBuie();
            this.leggiECreaPersonaggi();
            this.leggiInizialeEvincente();
            this.leggiECollocaAttrezzi();
            this.leggiEImpostaUscite();
        } 
        finally {
            try {
                lineReader.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    public Labirinto getLabirinto() {
        return this.builder.getLabirinto();
    }

    private void leggiECreaStanze() throws FormatoFileNonValidoException {
        String nomiStanze = leggiRigaCheCominciaPer(STANZE_MARKER);
        for (String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
            nomeStanza = nomeStanza.trim();
            this.builder.addStanza(nomeStanza);
        }
    }
    
    /****************
     * 
     * STANZA MAGICA
     * 
     ****************/
    private void leggiECollocaStanzeMagiche() throws FormatoFileNonValidoException {
        String nomiStanze = leggiRigaCheCominciaPer(STANZE_MAGICHE);
        for (String specificaStanzaMagica : separaStringheAlleVirgole(nomiStanze)) {
            specificaStanzaMagica = specificaStanzaMagica.trim();
            try (Scanner scannerLinea = new Scanner(specificaStanzaMagica)) {
                check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome di una stanza."));
                String nomeStanza = scannerLinea.next().trim();

                check(scannerLinea.hasNext(), msgTerminazionePrecoce("la soglia di attivazione."));
                String sogliaAttivazione = scannerLinea.next().trim();
                    
                check(this.builder.getListaStanze().containsKey(nomeStanza), 
                      "Stanza magica " + nomeStanza + " non definita (non presente nel blocco Stanze:)");
                
                this.builder.getListaStanze().remove(nomeStanza);
                creaStanzaMagica(nomeStanza, sogliaAttivazione);
            } 
        }
    }
    
    private void creaStanzaMagica(String nomeStanza, String sogliaAttivazione) 
            throws FormatoFileNonValidoException {
        int soglia;
        try {
            soglia = Integer.parseInt(sogliaAttivazione);
        } catch (NumberFormatException e) {
            check(false, "Soglia attivazione " + sogliaAttivazione + " non valida");
            return; 
        }   
        
        this.builder.addStanzaMagica(nomeStanza, soglia);
    }
    
    /****************
     * 
     * STANZA BLOCCATA
     * 
     ****************/
    private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException {
        String nomiStanze = leggiRigaCheCominciaPer(STANZE_BLOCCATE);
        for (String specificaStanzaBloccata: separaStringheAlleVirgole(nomiStanze)) {
            specificaStanzaBloccata = specificaStanzaBloccata.trim();
            try (Scanner scannerLinea = new Scanner(specificaStanzaBloccata)) {
                
                check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome di una stanza."));
                String nomeStanza = scannerLinea.next().trim();

                check(scannerLinea.hasNext(), msgTerminazionePrecoce("la direzione bloccata"));
                String direzioneBloccata = scannerLinea.next().trim();
                
                check(scannerLinea.hasNext(), msgTerminazionePrecoce("l'attrezzo sbloccante"));
                String attrezzoSbloccante = scannerLinea.next().trim();

                check(this.builder.getListaStanze().containsKey(nomeStanza), 
                      "Stanza bloccata " + nomeStanza + " non definita");
                
                this.builder.getListaStanze().remove(nomeStanza);
                this.builder.addStanzaBloccata(nomeStanza, direzioneBloccata, attrezzoSbloccante);
            } 
        }
    }
    
    /****************
     * 
     * STANZA BUIA
     * 
     ****************/
    private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException {
        String nomiStanze = leggiRigaCheCominciaPer(STANZE_BUIE);
        for (String specificaStanzaBuia: separaStringheAlleVirgole(nomiStanze)) {
            specificaStanzaBuia = specificaStanzaBuia.trim();
            try (Scanner scannerLinea = new Scanner(specificaStanzaBuia)) {
                
                check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome di una stanza."));
                String nomeStanza = scannerLinea.next().trim();
                
                check(scannerLinea.hasNext(), msgTerminazionePrecoce("l'attrezzo illuminante"));
                String attrezzoIlluminante = scannerLinea.next().trim();

                check(this.builder.getListaStanze().containsKey(nomeStanza), 
                      "Stanza buia " + nomeStanza + " non definita");
                
                this.builder.getListaStanze().remove(nomeStanza);
                this.builder.addStanzaBuia(nomeStanza, attrezzoIlluminante);
            } 
        }
    }

    /****************
     * 
     * PERSONAGGI
     * 
     ****************/
    private void leggiECreaPersonaggi() throws FormatoFileNonValidoException {
        String nomiPersonaggi = leggiRigaCheCominciaPer(PERSONAGGI_MARKER);
        for (String specificaPersonaggio: separaStringheAlleVirgole(nomiPersonaggi)) {
            specificaPersonaggio = specificaPersonaggio.trim();
            try (Scanner scannerLinea = new Scanner(specificaPersonaggio)) {
                
                check(scannerLinea.hasNext(), msgTerminazionePrecoce("il tipo del personaggio"));
                String tipo = scannerLinea.next().trim();
                
                check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome del personaggio."));
                String nomePersonaggio = scannerLinea.next().trim();

                check(scannerLinea.hasNext(), msgTerminazionePrecoce("la stanza in cui è situato il personaggio"));
                String nomeStanza = scannerLinea.next().trim();
                
                check(this.builder.getListaStanze().containsKey(nomeStanza), 
                      "Stanza " + nomeStanza + " non definita (per il personaggio " + nomePersonaggio + ")");
                
                switch (tipo) {
                    case "Mago": {
                        check(scannerLinea.hasNext(), msgTerminazionePrecoce("l’attrezzo del Mago"));
                        String nomeAttrezzo = scannerLinea.next().trim();

                        check(scannerLinea.hasNext(), msgTerminazionePrecoce("il peso dell’attrezzo"));
                        String pesoAttrezzoStr = scannerLinea.next().trim();

                        int pesoAttrezzo;
                        try {
                            pesoAttrezzo = Integer.parseInt(pesoAttrezzoStr);
                        } catch (NumberFormatException e) {
                            check(false, "Peso attrezzo \"" + pesoAttrezzoStr 
                                  + "\" non valido per il Mago “" + nomePersonaggio + "\"");
                            return; 
                        }

                        Attrezzo attrezzoPerMago = new Attrezzo(nomeAttrezzo, pesoAttrezzo);
                        this.builder.addMago(nomePersonaggio, nomeStanza, attrezzoPerMago);
                        break;
                    }

                    case "Cane": {
                        check(scannerLinea.hasNext(), msgTerminazionePrecoce("l’attrezzo del Cane"));
                        String nomeAttrezzo = scannerLinea.next().trim();

                        check(scannerLinea.hasNext(), msgTerminazionePrecoce("il peso dell’attrezzo"));
                        String pesoAttrezzoStr = scannerLinea.next().trim();

                        int pesoAttrezzo;
                        try {
                            pesoAttrezzo = Integer.parseInt(pesoAttrezzoStr);
                        } catch (NumberFormatException e) {
                            check(false, "Peso attrezzo \"" + pesoAttrezzoStr 
                                  + "\" non valido per il Cane “" + nomePersonaggio + "\"");
                            return;
                        }

                        Attrezzo attrezzoPerCane = new Attrezzo(nomeAttrezzo, pesoAttrezzo);
                        this.builder.addCane(nomePersonaggio, nomeStanza, attrezzoPerCane);
                        break;
                    }

                    case "Strega": {
                        this.builder.addStrega(nomePersonaggio, nomeStanza);
                        break;
                    }

                    default:
                        check(false, "Tipo di personaggio \"" + tipo + "\" non riconosciuto");
                } 
            }
        }
    }
    
    private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
        String nomeStanzaIniziale = leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER).trim();
        check(this.builder.getListaStanze().containsKey(nomeStanzaIniziale), 
              nomeStanzaIniziale + " non definita");
        this.builder.addStanzaIniziale(nomeStanzaIniziale);

        String nomeStanzaVincente = leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER).trim();
        check(this.builder.getListaStanze().containsKey(nomeStanzaVincente), 
              nomeStanzaVincente + " non definita");
        this.builder.addStanzaVincente(nomeStanzaVincente);
    }

    private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
        String specificheAttrezzi = leggiRigaCheCominciaPer(ATTREZZI_MARKER);

        for (String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
            specificaAttrezzo = specificaAttrezzo.trim();
            try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
                
                check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome di un attrezzo."));
                String nomeAttrezzo = scannerLinea.next().trim();

                check(scannerLinea.hasNext(), msgTerminazionePrecoce("il peso dell'attrezzo " + nomeAttrezzo + "."));
                String pesoAttrezzoStr = scannerLinea.next().trim();

                check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo " + nomeAttrezzo + "."));
                String nomeStanzaDest = scannerLinea.next().trim();

                posaAttrezzo(nomeAttrezzo, pesoAttrezzoStr, nomeStanzaDest);
            } 
        }
    }

    private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzoStr, String nomeStanzaDest)
            throws FormatoFileNonValidoException {
        int peso;
        try {
            peso = Integer.parseInt(pesoAttrezzoStr);
        } catch (NumberFormatException e) {
            check(false, "Peso attrezzo " + nomeAttrezzo + " non valido");
            return; 
        }
        
        check(this.builder.getListaStanze().containsKey(nomeStanzaDest),
              "Attrezzo " + nomeAttrezzo + " non collocabile: stanza " + nomeStanzaDest + " inesistente");

        this.builder.addStanza(nomeStanzaDest);   
        this.builder.addAttrezzo(nomeAttrezzo, peso);
    }

    private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
        String specificheUscite = leggiRigaCheCominciaPer(USCITE_MARKER);
       
        for (String triplaUscita : separaStringheAlleVirgole(specificheUscite)) {
            triplaUscita = triplaUscita.trim();
            try (Scanner scannerLinea = new Scanner(triplaUscita)) {
                
                check(scannerLinea.hasNext(), msgTerminazionePrecoce("le uscite di una stanza."));
                String stanzaPartenza = scannerLinea.next().trim();

                check(scannerLinea.hasNext(), msgTerminazionePrecoce(
                          "la direzione di un'uscita della stanza " + stanzaPartenza));
                String dir = scannerLinea.next().trim();

                check(scannerLinea.hasNext(), msgTerminazionePrecoce(
                          "la destinazione di una uscita della stanza " + stanzaPartenza + " nella direzione " + dir));
                String stanzaDestinazione = scannerLinea.next().trim();

                check(this.builder.getListaStanze().containsKey(stanzaPartenza),
                      "Stanza di partenza sconosciuta " + stanzaPartenza);
                check(this.builder.getListaStanze().containsKey(stanzaDestinazione),
                      "Stanza di destinazione sconosciuta " + stanzaDestinazione);

                this.builder.addAdiacenza(stanzaPartenza, stanzaDestinazione, dir);
            } 
        }
    }

    private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
        try {
            String riga = this.lineReader.readLine();
            check(riga != null && riga.startsWith(marker),
                  "Riga " + this.lineReader.getLineNumber()
                  + ": era attesa una riga che cominciasse per \"" + marker + "\"");
            return riga.substring(marker.length());
        } catch (IOException e) {
            throw new FormatoFileNonValidoException(e.getMessage());
        }
    }

    private List<String> separaStringheAlleVirgole(String string) {
        List<String> result = new LinkedList<>();
        Scanner scanner = new Scanner(string);
        scanner.useDelimiter(",");   
        while (scanner.hasNext()) {
            String token = scanner.next().trim();
            if (!token.isEmpty())
                result.add(token);
        }
        scanner.close();
        return result;
    }

    private String msgTerminazionePrecoce(String cosa) {
        return "Terminazione  precoce del file prima di leggere " + cosa;
    }

    private void check(boolean condizione, String messaggioErrore)
            throws FormatoFileNonValidoException {
        if (!condizione) {
            int linea = this.lineReader.getLineNumber();
            throw new FormatoFileNonValidoException(
                "Formato file non valido [" + linea + "] " + messaggioErrore);
        }
    }
}


