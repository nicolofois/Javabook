package jbook;

public class Messaggio {
    private Utente autore;
    private String testo;
    private long tempo;

    public Messaggio(Utente autore, String testo, long tempo) {
        this.autore = autore;
        this.testo = testo;
        this.tempo = tempo;
    }

    public Utente getAutore(){
        return this.autore;
    }
    
    public String getTesto(){
        return this.testo;
    }
    
    public long getTime(){
        return this.tempo;
    }
    
    public static String quantoTempoFa(long t){
        if (t < 60000) {
            return t/1000 + " secondi fa";
        }
        else if (t >= 60000 && t < 3600000) {
            return t/60000 + " minuti fa";
        }
        else {
            return t/3600000 + " ore fa";
        }
    }
}
