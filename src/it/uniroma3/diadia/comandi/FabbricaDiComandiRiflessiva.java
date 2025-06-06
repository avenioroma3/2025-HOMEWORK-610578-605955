package it.uniroma3.diadia.comandi;

import java.lang.reflect.Constructor;
import java.util.*;

import it.uniroma3.diadia.IO;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {
	
	private IO io;
    public FabbricaDiComandiRiflessiva(IO io) {
        this.io = io;
    }
    @Override
    public AbstractComando costruisciComando(String istruzione) {
        Scanner scannerDiParole = new Scanner(istruzione);
        String nomeComando = null;
        String parametro = null;
        AbstractComando comando = null;

        if (scannerDiParole.hasNext())
            nomeComando = scannerDiParole.next();

        if (scannerDiParole.hasNext())
            parametro = scannerDiParole.next();

        if (nomeComando == null) {
            return new ComandoNonValido(io);
        }
        String nomeClasse = "it.uniroma3.diadia.comandi.Comando";

        try {
            nomeClasse += Character.toUpperCase(nomeComando.charAt(0)) + nomeComando.substring(1);
            Class<?> classeComando = Class.forName(nomeClasse);
            Constructor<?> costruttore = classeComando.getConstructor(IO.class);
            comando = (AbstractComando) costruttore.newInstance(this.io);
            comando.setParametro(parametro);
            return comando;
        } catch (Exception e) {
        	AbstractComando cmd = new ComandoNonValido(io);
            cmd.setParametro(parametro);
            return cmd;
        }
    }
}
