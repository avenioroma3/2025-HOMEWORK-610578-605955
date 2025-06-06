package it.uniroma3.diadia;

import java.util.Scanner;

public class IOConsole implements IO {
    
    private Scanner scannerDiLinee;
    
    public IOConsole(Scanner scanner) {
        this.scannerDiLinee = scanner;
    }
    
    @Override
    public void mostraMessaggio(String msg) {
        System.out.println(msg);
    }
    
    @Override
    public String leggiRiga() {
        // Rimuovo la creazione di un nuovo Scanner: uso quello passato in input
        return scannerDiLinee.nextLine();
    }
}
