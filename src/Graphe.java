import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author Antoine
 * @author Maxence
 *
 * */

public class Graphe {
    private int nbNoeuds;
    private int nbLiens;
    private final ArrayList<Noeud> list_noeuds;
    private final ArrayList<Lien> list_liens;


    /**
     * Constructeur de la classe Graphe qui prend en argument une chaîne de caractères et instancie une liste de noeuds, 
     * une liste de liens, met le nombre de liens et de noeuds à 0 et essaye de récupérer les données d'un fichier CSV 
     * dont le chemin est donné par la chaîne de caractères en paramètres 
     * @param path correspond à une chaîne de caractère
     */
    public Graphe(String path) 
    {
        list_noeuds = new ArrayList<>();
        list_liens = new ArrayList<>();
        nbNoeuds = 0;
        nbLiens = 0;
        try {
            loading(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object o) 
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Graphe graphe = (Graphe) o;
        return nbNoeuds == graphe.nbNoeuds && nbLiens == graphe.nbLiens && Objects.equals(list_noeuds, graphe.list_noeuds) && Objects.equals(list_liens, graphe.list_liens);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hash(nbNoeuds, nbLiens, list_noeuds, list_liens);
    }
    

    /**
     * Charge la classe en données en lisant et récupérant les données contenues dans un fichier CSV dont le chemin
     * est donné en paramètres
     * @param path correspond à une chaîne de caractères
     */
    public void loading(String path) throws FileNotFoundException
    {
        String line;
        String[] lieux;
        String[] liens;
        String[] details;
        String[] data;
        Noeud start = null;
        Noeud voisin = null;
        Boolean check;
        int i = 0;
        File file = new File(path);
        Scanner scan = new Scanner(file);
        scan.useDelimiter(";;");
        while (scan.hasNext()) {
            line = scan.next();
            if (!line.equals(null))
            {
                lieux = line.split(":", 2);                                //On sépare en 2 pour ne pas se concentrer sur les autres , du CSV.  lieux[0] contient le noeud & lieux[1] les liens
                data = lieux[0].split(",");
                if (i != 0) {                                                           //Pour passer l'erreur du premier neud à créer
                    if (existNoeud(data[0].charAt(2), data[1]) == null) {        //Vérifie si le noeud existe déjà pour le créer ou juste le sélectionner
                        start = this.add_noeud(data[0].charAt(2), data[1]);
                    } else {
                        start = existNoeud(data[0].charAt(2), data[1]);
                    }
                    liens = lieux[1].split(";");                                 //Séparation des liens dans la liste de lien
                    for (String lien : liens) {
                        details = lien.split("::");
                        data = details[1].split(",");
                        if (existNoeud(data[0].charAt(0), data[1]) == null) {    //Vérifie si le noeud existe déjà pour le créer ou juste le sélectionner
                            voisin = this.add_noeud(data[0].charAt(0), data[1]);
                        } else {
                            voisin = existNoeud(data[0].charAt(0), data[1]);
                        }
                        data = details[0].split(",");               
                        check =true;
                        for (Lien lientoCheck : list_liens)
                        {
                            if 
                            (
                                (lientoCheck.getDepart().equals(voisin))
                                &&
                                (lientoCheck.getArrivee().equals(start))
                                &&                                                      //Vérifier si un lien identique existe déjà
                                (lientoCheck.getType() == data[0].charAt(0))
                                &&
                                (lientoCheck.getDistance() == Integer.parseInt(data[1]))
                            )
                            {
                                check = false;
                            }
                        }
                        if (check)
                        {   
                            this.add_lien(Integer.parseInt(data[1]), data[0].charAt(0), start, voisin);
                        }
                        
                        
                       
                    }
                }i++;
            }
    }
        scan.close();
    }

    /**
     * Cherche si un noeud existe à partir d'un type de noeud et d'un nom de noeud
     * @param type correspond à un char
     * @param nom correspond à un String
     */
    public Noeud existNoeud(char type,String nom)
    {
        for (Noeud noeud : list_noeuds)
        {
            if (noeud.getNom().equals(nom) && noeud.getType()==type)
            {
                return noeud;
            }
        }
        return null;
    }

    /**
     * Renvoie true si les noeuds donnés en paramètres sont à une 2-distance et false sinon
     * @param noeudInitial correspond à un object de type Noeud
     * @param noeudArrivee correspond à un object de type Noeud
     * @return un booleen
     */
    public boolean deuxVoisins(Noeud noeudInitial, Noeud noeudArrivee)
    {
        boolean success = false;
        for (Lien lien : list_liens) {
            if (lien.getDepart().equals(noeudInitial)) {
                for (Lien second : list_liens) {
                    if (second.getDepart().equals(lien.getArrivee()) && second.getArrivee().equals(noeudArrivee)) {
                        success = true;
                    }
                }
            }
        }
        return success;
    }

    /**
     * Renvoie true si les noeuds donnés en paramètres sont voisins et false sinon
     * @param noeudInitial correspond à un objet de type Noeud
     * @param noeudVoisin correspond à un objet de type Noeud
     * @return un booleen
     */
    public boolean estVoisin(Noeud noeudInitial, Noeud noeudVoisin)
    {
        boolean success = false;
        for (Lien lien : list_liens)
        {
            if (lien.getDepart().equals(noeudInitial) && lien.getArrivee().equals(noeudVoisin))
            {
                success = true;
            }
        }
        return success;
    }

    /**
     * Renvoie la liste les voisins d'un noeud donné en paramètre
     * @param noeud correspond à un objet Noeud
     * @return une ArrayList d'objets de type Noeud
     */
    public ArrayList<Noeud> listeVoisins(Noeud noeud)
    {
        ArrayList<Noeud> listVoisins = new ArrayList<>();
        for (Noeud noeud_test : list_noeuds)
        {
            if (estVoisin(noeud,noeud_test) && noeud != noeud_test)
            {
                listVoisins.add(noeud_test);
            }
        }
        return listVoisins;
    }

    /**
     * Renvoie la liste des noeuds à une 2-distance d'un noeud donné en paramètre
     * @param noeud correspond à un objet de type noeud
     * @return une ArrayList d'objets de type Noeud
     */
    public ArrayList<Noeud> liste2Voisins(Noeud noeud)
    {
        ArrayList<Noeud> list2Voisins = new ArrayList<>();
        for (Noeud noeud_test : list_noeuds)
        {
            if (deuxVoisins(noeud,noeud_test) && noeud != noeud_test)
            {
                list2Voisins.add(noeud_test);
            }
        }
        return list2Voisins;
    }

    /**
     * Renvoie le nombre de noeuds dans le graphe
     * @return un entier
     */
    public int getNbNoeuds() 
    {
        return nbNoeuds;
    }


    /**
     * Renvoie le nombre de liens dans le graphe
     * @return un entier
     */
    public int getNbLiens() 
    {
        return nbLiens;
    }

    /**
     * Renvoie la liste des noeuds du graphe
     * @return une ArrayList d'objets de type Noeud
     */
    public ArrayList<Noeud> getNoeuds()
    {
        return list_noeuds;
    }

    /**
     * Renvoie la liste des liens du graphe
     * @return une ArrayList d'objets de type Lien
     */
    public ArrayList<Lien> getLiens()
    {
        return list_liens;
    }

    /**
     * Crée un objet de type Noeud et l'ajoute au graphe
     * @param type correspond à un char
     * @param nom correspond à un String
     * @return un objet de type Noeud
     */
    public Noeud add_noeud(char type, String nom)
    {
        Noeud create;
        create = new Noeud(type, nom);
        list_noeuds.add(create);
        nbNoeuds++;
        return create;
    }

    /**
     * Crée un objet de type Lien et l'ajoute au graphe 
     * @param distance correspond à un entier
     * @param type correspond à un char
     * @param depart correspond à un objet de type Noeud
     * @param arrivee correspond à un objet de type Noeud
     */
    public void add_lien(int distance, char type, Noeud depart, Noeud arrivee) 
    {
        list_liens.add(nbLiens, new Lien(distance, type, depart, arrivee));
        nbLiens++;
    }

    /**
     * Renvoie une chaine de caractères indiquant la gastronomie d'un Noeud par rapport à un autre.
     * Plus le Noeud possède de voisins restaurants, plus il est gastronomique
     * @param noeud1 correspond à un objet de type Noeud
     * @param noeud2 correspond à un objet de type Noeud
     * @return un objet String
     */
    public String plusGastro(Noeud noeud1, Noeud noeud2)
    {
        int compteur1 = 0; int compteur2 = 0;
        for (Noeud noeud1test : listeVoisins(noeud1))
        {
            if (noeud1test.getType() == 'R') {
                compteur1++;
            }
        }
        for (Noeud noeud2test : listeVoisins(noeud2))
        {
            if (noeud2test.getType() == 'R')
            {
                compteur2++;
            }
        }
        if (compteur1 == compteur2)
        {
            return noeud1.getNom() + " est tout aussi gastronomique que " + noeud2.getNom();
        }
        else if (compteur1 > compteur2)
        {
            return noeud1.getNom() + "est plus gastronomique que " + noeud2.getNom();
        }
        else {
            return noeud1.getNom() + " est moins gastronomique que " + noeud2.getNom();
        }
    }

    /**
     * Renvoie une chaine de caractères indiquant l'ouverture d'un Noeud par rapport à un autre.
     * Plus le Noeud possède de voisins qui sont des villes, plus il est ouvert
     * @param noeud1 correspond à un objet de type Noeud
     * @param noeud2 correspond à un objet de type Noeud
     * @return un objet String
     */
    public String plusOuverte(Noeud noeud1, Noeud noeud2)
    {
        int compteur1 = 0; int compteur2 = 0;
        for (Noeud noeud1test : listeVoisins(noeud1))
        {
            if (noeud1test.getType() == 'V') {
                compteur1++;
            }
        }
        for (Noeud noeud2test : listeVoisins(noeud2))
        {
            if (noeud2test.getType() == 'V')
            {
                compteur2++;
            }
        }
        if (compteur1 == compteur2)
        {
            return noeud1.getNom() + " est tout aussi ouverte que " + noeud2.getNom();
        }
        else if (compteur1 > compteur2)
        {
            return noeud1.getNom() + "est plus ouverte que " + noeud2.getNom();
        }
        else {
            return noeud1.getNom() + " est moins ouverte que " + noeud2.getNom();
        }
    }

    /**
     * Renvoie une chaine de caractères indiquant la culture d'un Noeud par rapport à un autre.
     * Plus le Noeud possède de voisins qui sont des centres de loisir, plus il est gastronomique
     * @param noeud1 correspond à un objet de type Noeud
     * @param noeud2 correspond à un objet de type Noeud
     * @return un objet String
     */
    public String plusCulturelle(Noeud noeud1, Noeud noeud2)
    {
        int compteur1 = 0; int compteur2 = 0;
        for (Noeud noeud1test : listeVoisins(noeud1))
        {
            if (noeud1test.getType() == 'L') {
                compteur1++;
            }
        }
        for (Noeud noeud2test : listeVoisins(noeud2))
        {
            if (noeud2test.getType() == 'L')
            {
                compteur2++;
            }
        }
        if (compteur1 == compteur2)
        {
            return noeud1.getNom() + " est tout aussi culturel que " + noeud2.getNom();
        }
        else if (compteur1 > compteur2)
        {
            return noeud1.getNom() + "est plus culturel que " + noeud2.getNom();
        }
        else {
            return noeud1.getNom() + " est moins gastronomique que " + noeud2.getNom();
        }
    }
}
