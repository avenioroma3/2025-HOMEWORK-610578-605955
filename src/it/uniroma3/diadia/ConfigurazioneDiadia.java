package it.uniroma3.diadia;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Legge il file diadia.properties da classpath
 * e fornisce getter statici per le varie proprietà.
 */
public class ConfigurazioneDiadia {
    private static final String FILE_PROPERTIES = "/diadia.properties";

    // Oggetto Properties che conterrà tutte le coppie chiave=valore
    private static final Properties props = new Properties();

    static {
        // Blocco statico: avviene una sola volta, quando la classe viene caricata
        try (InputStream in = ConfigurazioneDiadia.class.getResourceAsStream(FILE_PROPERTIES)) {
            if (in == null) {
                throw new RuntimeException("Impossibile trovare " + FILE_PROPERTIES + " nel classpath.");
            }
            props.load(in);
        } catch (IOException e) {
            throw new RuntimeException("Errore durante il caricamento del file diadia.properties", e);
        }
    }

    // Getter “forti” (con conversione a interi, float, boolean, ecc.)
    public static int getCfuIniziali() {
        String val = props.getProperty("iniziali.cfu");
        if (val == null) throw new RuntimeException("Proprietà iniziali.cfu non trovata!");
        try {
            return Integer.parseInt(val.trim());
        } catch (NumberFormatException e) {
            throw new RuntimeException("Valore non intero per iniziali.cfu: " + val);
        }
    }

    public static int getPesoMaxBorsa() {
        String val = props.getProperty("borsa.pesoMax");
        if (val == null) throw new RuntimeException("Proprietà borsa.pesoMax non trovata!");
        try {
            return Integer.parseInt(val.trim());
        } catch (NumberFormatException e) {
            throw new RuntimeException("Valore non intero per borsa.pesoMax: " + val);
        }
    }

    public static String getLabirintoFile() {
        String val = props.getProperty("labirinto.file");
        if (val == null) throw new RuntimeException("Proprietà labirinto.file non trovata!");
        return val.trim();
    }
   
    public static int getNumMassimoAttrezziStanza() {
    	String val = props.getProperty("stanza.attrezziMax");
    	if(val==null) throw new RuntimeException("Propietà stanza.attrezziMax non trovata!");
    	try {
    		return Integer.parseInt(val.trim());
    	} catch (NumberFormatException e) {
    		throw new RuntimeException("Valore non intero per stanza.attrezziMax: "+val);
    	}
    }
}
