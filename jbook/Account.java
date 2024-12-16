package jbook;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Account {
    private String id;
    private String pwd;
    private Utente utente;
    private LinkedList<Utente> richieste;
    private LinkedList<Utente> amici;
    private LinkedList<Messaggio> messaggi;

    public Account(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
        this.utente = null;
        this.richieste = new LinkedList<>();
        this.amici = new LinkedList<>();
        this.messaggi = new LinkedList<>();
    }

    public String getId(){
        return this.id;
    }
    
    public String getNome(){
        return this.utente.getNome();
    }

    public String getPwd() {
        return this.pwd;
    }

    public void setNome(String nuovoNome){
        this.utente = new Utente(nuovoNome, this);
        Book.aggiungiUtente(this.utente);
    }
    
    public Utente mioUtente(){
        return this.utente;
    }
    
    public void aggiungiRichiesta(Utente u) {
        this.richieste.add(u);
    } 

    public Collection<Utente> richieste(){
        return this.richieste;
    }

    public Collection<Utente> amici(){
        return this.amici;
    }
    
    public void accetto(Utente nuovoAmico) throws RichiestaInesistenteException {
        if (!this.richieste.contains(nuovoAmico)) {
            throw new RichiestaInesistenteException();
        }
        this.amici.add(nuovoAmico);
        this.richieste.remove(nuovoAmico);
    }
    
    public Messaggio post(String testo){
        Messaggio m = new Messaggio(this.utente,testo,System.currentTimeMillis());
        messaggi.add(m);
        return m;
    }

    public LinkedList<Messaggio> messaggi() {
        return this.messaggi;
    }
    
    public List<Messaggio> aggiornamenti(){
        LinkedList<Messaggio> messAmici = new LinkedList<>(); 
        for (Utente u : amici) {
            messAmici.addAll(u.bacheca());
        }
        messAmici.addAll(this.utente.bacheca());

        return messAmici.stream()
                        .sorted(Comparator.comparing(Messaggio::getTime).reversed())
                        .toList();
    }

}
