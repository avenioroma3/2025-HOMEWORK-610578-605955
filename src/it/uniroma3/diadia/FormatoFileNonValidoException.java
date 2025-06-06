package it.uniroma3.diadia;  // Assicurati che sia lo stesso package di CaricatoreLabirinto

public class FormatoFileNonValidoException extends Exception {
    private static final long serialVersionUID = 1L; // consigliato per le eccezioni serializzabili

    // Costruttore di default
    public FormatoFileNonValidoException() {
        super();
    }

    // Costruttore con messaggio di errore
    public FormatoFileNonValidoException(String message) {
        super(message);
    }

    // Se vuoi, puoi anche aggiungere altri costruttori (ad esempio con Throwable cause):
    public FormatoFileNonValidoException(String message, Throwable cause) {
        super(message, cause);
    }
}
