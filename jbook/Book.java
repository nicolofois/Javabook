package jbook;

import java.util.Collection;
import java.util.LinkedList;
import java.util.TreeMap;

public class Book {
    private TreeMap<String,Account> accounts = new TreeMap<>();
    private static LinkedList<Utente> utenti = new LinkedList<>();
    
    public void nuovoAccount(String id, String pwd){
        Account a = new Account(id,pwd);
        accounts.put(id, a);
    }
    
    public Account accedi(String id, String pwd) throws AccessoFallitoException {
        if (!accounts.containsKey(id)) {
            throw new AccessoFallitoException();
        }
        else if (!accounts.get(id).getPwd().equals(pwd)) {
            throw new AccessoFallitoException();
        }
        return accounts.get(id);
    }

    public static void aggiungiUtente(Utente u) {
        utenti.add(u);
    }

    public Collection<Utente> cercaUtenti(String search){
        LinkedList<Utente> l = new LinkedList<>();
        for (Utente u : utenti) {
            if (u.getNome().contains(search)) {
                l.add(u);
            }
        }
        return l;
    }
}
