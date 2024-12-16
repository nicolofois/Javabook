import java.util.Collection;
import java.util.List;

import jbook.*;

public class Esempio {

    public static void main(String[] args) throws RichiestaInesistenteException, InterruptedException, AccessoFallitoException {
        
        Book jb = new Book();
        
        jb.nuovoAccount("jim@poli.to", "J");
        jb.nuovoAccount("jane@poli.to", "J");
        jb.nuovoAccount("john@poli.to", "J");
        
        
        Account jim = jb.accedi("jim@poli.to", "J");
        jim.setNome("Jim");
        Account jane = jb.accedi("jane@poli.to", "J");
        jane.setNome("Jane");
        Account john = jb.accedi("john@poli.to", "J");
        john.setNome("John");

        
        // Jim cerca e chiede amicizia a Jane
        Collection<Utente> risultato = jb.cercaUtenti("Jane");
        System.out.println("Ci sono " + risultato.size() + " utenti 'Jane'");
        Utente UJane = risultato.iterator().next();
        UJane.richiestaAmicizia(jim.mioUtente());
        
        // Jane trova la richiesta di Jim e l'accetta
        Collection<Utente> richieste = jane.richieste();
        System.out.println("Ci sono " + richieste.size() + " richieste di amicizia per " + jane.getNome());
        for(Utente richiedente : richieste){ // accetta tutte le richieste
            jane.accetto(richiedente);
        }
        
        // Jim e Jane scrivono sulla propria bacheca
        jim.post("Oggi esame di Java...");
        Thread.sleep(1000);
        jane.post("Nevica!");
        Thread.sleep(1000);
        jim.post("Molta neve");
        
        // bacheca di Jim
        List<Messaggio> bacheca = jim.mioUtente().bacheca();
        System.out.println("Ci sono " + bacheca.size() + " messaggi nella bacheca di " + jim.getNome());
        
        // aggiornamenti di Jane
        List<Messaggio> news = jane.aggiornamenti();
        System.out.println("Aggiornamenti per " + jane.getNome());
        for(Messaggio msg : news){
            System.out.println("\tPostato da: " + msg.getAutore().getNome());
            System.out.println("\t<< " + msg.getTesto() + " >>");
            System.out.println("\t" + Messaggio.quantoTempoFa(msg.getTime()));
        }
          
}

}
