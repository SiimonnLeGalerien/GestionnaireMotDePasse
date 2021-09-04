import java.util.*;
public class CliElement {
    enum TypeElement {commande, option, argument, nonReconnu};
    TypeElement elem;
    public CliElement () {
        elem = TypeElement.nonReconnu;
    }
    
    public CliElement (TypeElement element) {
        elem = element;
    }

    /**
     * Ceci est une fonction de parsing. C'est une fonction qui permet
     * de déterminer si les entrées de l'utilisateur sont des arguments,
     * des commandes ou des options.
     */
    
     public TypeElement EntryParse (String entry) {
        TypeElement element = TypeElement.nonReconnu;
        
        if (entry.charAt(0) == '-') {
            element = TypeElement.option;
            return element;
        }
        


        return element;
    }
}