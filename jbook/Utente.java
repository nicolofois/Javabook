package jbook;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Utente {
    private String nome;
    private Account account;

    public Utente(String nome, Account account) {
        this.nome = nome;
        this.account = account;
    }

    public String getNome(){
        return this.nome;
    }

    public Account getAccount() {
        return this.account;
    }

    public void richiestaAmicizia(Utente richiedente){
        this.account.aggiungiRichiesta(richiedente);
    }
    
    public Collection<Utente> amici(){
        return this.account.amici();
    }
    
    public List<Messaggio> bacheca(){
        return this.account.messaggi().stream()
                                      .sorted(Comparator.comparing(Messaggio::getTime).reversed())
                                      .toList();
    }
    
    public double livelloSociale(){
        LinkedList<Integer> values = new LinkedList<>();
        int count;
        for (Utente a : this.account.amici()) {
            count = 0;
            for (Utente u : a.getAccount().amici()) {
                for (Utente x : this.account.amici()) {
                    if (x.equals(u)) {
                        count++;
                    }
                }
            }
            values.add(count);
        }
        count = 0;
        int total = 0;
        for (Integer i : values) {
            total += i;
            count++;
        }
        return (double)total/(double)count;
    }

    public long livelloAttivita(){
        return this.account.messaggi().stream()
                                      .filter(m -> System.currentTimeMillis() - m.getTime() < 3600000)
                                      .count();
    }
}
