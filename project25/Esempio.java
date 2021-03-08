import dieta.Alimenti;
import dieta.ElementoNutritivo;
import dieta.Menu;
import dieta.Ricetta;


public class Esempio {

    public static void main(String[] args)  {
        Alimenti alimenti = new Alimenti();
        alimenti.definisciMateriaPrima("Zucchero", 400, 0, 100, 0);
        alimenti.definisciMateriaPrima("Mais", 70, 2.7, 12, 1.3);
        alimenti.definisciMateriaPrima("Pasta", 350, 12, 72.2, 1.5);
        alimenti.definisciMateriaPrima("Olio", 900, 0, 0, 100);
        alimenti.definisciMateriaPrima("Nutella", 530, 6.8, 56, 31);
        
        for(ElementoNutritivo e : alimenti.materiePrime()){
            System.out.println(e.getNome() + " cal: " + e.getCalorie());
        }
       
        alimenti.definisciProdotto("Cracker", 111, 2.6, 17.2, 3.5);
        alimenti.definisciProdotto("Gelato", 121, 22.6, 13.2, 13.5);
        
        for(ElementoNutritivo e : alimenti.prodotti()){
            System.out.println(e.getNome() + " cal: " + e.getCalorie());
        }
        
        Ricetta r = new Ricetta("Pasta e Nutella", alimenti); 

        r.aggiungiIngrediente("Pasta", 70);
        r.aggiungiIngrediente("Nutella", 30);
        
        for(ElementoNutritivo e : alimenti.ricette()){
            System.out.println(e.getNome() + " cal: " + e.getCalorie());
        } 
      
        Menu menu = new Menu("M1", alimenti);
        menu.aggiungiRicetta("Pasta e Nutella", 50);
        menu.aggiungiProdotto("Cracker");
        
        System.out.println("Per il menu " + menu.getNome() + 
                "\n\tCalorie     : " + menu.getCalorie() +
                "\n\tCarboidrati : " + menu.getCarboidrati() +
                "\n\tGrassi      : " + menu.getGrassi() +
                "\n\tProteine    : " + menu.getProteine() +
                "."
                );

    }

}
