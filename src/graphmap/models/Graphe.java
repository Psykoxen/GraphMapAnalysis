package graphmap.models;
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
 * Class Graphe contenant des noeuds reliés par des liens ainsi qu'une fonction de chargement. 
 *
 * */
public class Graphe {
    private int nbNode;
    private int nbLink;
    private final ArrayList<Node> list_node;
    private final ArrayList<Link> list_link;


    /**
     * Constructeur de la classe Graphe qui prend en argument une chaîne de caractères et instancie une liste de node, 
     * une liste de link, met le nombre de link et de node à 0 et essaye de récupérer les données d'un fichier CSV 
     * dont le chemin est donné par la chaîne de caractères en paramètres 
     * @param path Le chemin du fichier à charger
     */
    public Graphe(String path) 
    {
        list_node = new ArrayList<>();
        list_link = new ArrayList<>();
        nbNode = 0;
        nbLink = 0;
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
        return nbNode == graphe.nbNode && nbLink == graphe.nbLink && Objects.equals(list_node, graphe.list_node) && Objects.equals(list_link, graphe.list_link);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hash(nbNode, nbLink, list_node, list_link);
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
        String[] links;
        String[] details;
        String[] data;
        Node start = null;
        Node voisin = null;
        Boolean check;
        int i = 0;
        File file = new File(path);
        Scanner scan = new Scanner(file);
        scan.useDelimiter(";;");
        while (scan.hasNext()) {
            line = scan.next();
            if (!line.equals(null))
            {
                lieux = line.split(":", 2);                                //On sépare en 2 pour ne pas se concentrer sur les autres , du CSV.  lieux[0] contient le noeud & lieux[1] les link
                data = lieux[0].split(",");
                if (i != 0) {                                                           //Pour passer l'erreur du premier neud à créer
                    if (existNode(data[0].charAt(2), data[1]) == null) {        //Vérifie si le noeud existe déjà pour le créer ou juste le sélectionner
                        start = this.add_noeud(data[0].charAt(2), data[1]);
                    } else {
                        start = existNode(data[0].charAt(2), data[1]);
                    }
                    links = lieux[1].split(";");                                 //Séparation des link dans la liste de link
                    for (String link : links) {
                        details = link.split("::");
                        data = details[1].split(",");
                        if (existNode(data[0].charAt(0), data[1]) == null) {    //Vérifie si le noeud existe déjà pour le créer ou juste le sélectionner
                            voisin = this.add_noeud(data[0].charAt(0), data[1]);
                        } else {
                            voisin = existNode(data[0].charAt(0), data[1]);
                        }
                        data = details[0].split(",");               
                        check =true;
                        for (Link linktoCheck : list_link)
                        {
                            if 
                            (
                                (linktoCheck.getDepart().equals(voisin))
                                &&
                                (linktoCheck.getArrivee().equals(start))
                                &&                                                      //Vérifier si un link identique existe déjà
                                (linktoCheck.getType() == data[0].charAt(0))
                                &&
                                (linktoCheck.getDistance() == Integer.parseInt(data[1]))
                            )
                            {
                                check = false;
                            }
                        }
                        if (check)
                        {   
                            this.add_link(Integer.parseInt(data[1]), data[0].charAt(0), start, voisin);
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
    public Node existNode(char type,String nom)
    {
        for (Node noeud : list_node)
        {
            if (noeud.getNom().equals(nom) && noeud.getType()==type)
            {
                return noeud;
            }
        }
        return null;
    }

    /**
     * Renvoie true si les node donnés en paramètres sont à une 2-distance et false sinon
     * @param noeudInitial correspond à un object de type Node
     * @param noeudArrivee correspond à un object de type Node
     * @return un booleen
     */
    public boolean deuxVoisins(Node noeudInitial, Node noeudArrivee)
    {
        boolean success = false;
        for (Link link : list_link) {
            if (link.getDepart().equals(noeudInitial)) {
                for (Link second : list_link) {
                    if (second.getDepart().equals(link.getArrivee()) && second.getArrivee().equals(noeudArrivee)) {
                        success = true;
                    }
                }
            }
        }
        return success;
    }

    /**
     * Renvoie true si les node donnés en paramètres sont voisins et false sinon
     * @param noeudInitial correspond à un objet de type Node
     * @param noeudVoisin correspond à un objet de type Node
     * @return un booleen
     */
    public boolean estVoisin(Node noeudInitial, Node noeudVoisin)
    {
        boolean success = false;
        for (Link link : list_link)
        {
            if (link.getDepart().equals(noeudInitial) && link.getArrivee().equals(noeudVoisin))
            {
                success = true;
            }
        }
        return success;
    }

    /**
     * Renvoie la liste les voisins d'un noeud donné en paramètre
     * @param noeud correspond à un objet Node
     * @return une ArrayList d'objets de type Node
     */
    public ArrayList<Node> listeVoisins(Node noeud)
    {
        ArrayList<Node> listVoisins = new ArrayList<>();
        for (Node noeud_test : list_node)
        {
            if (estVoisin(noeud,noeud_test) && noeud != noeud_test)
            {
                listVoisins.add(noeud_test);
            }
        }
        return listVoisins;
    }

    /**
     * Renvoie la liste des node à une 2-distance d'un noeud donné en paramètre
     * @param noeud correspond à un objet de type noeud
     * @return une ArrayList d'objets de type Node
     */
    public ArrayList<Node> liste2Voisins(Node noeud)
    {
        ArrayList<Node> list2Voisins = new ArrayList<>();
        for (Node noeud_test : list_node)
        {
            if (deuxVoisins(noeud,noeud_test) && noeud != noeud_test)
            {
                list2Voisins.add(noeud_test);
            }
        }
        return list2Voisins;
    }

    /**
     * Renvoie le nombre de node dans le graphe
     * @return un entier
     */
    public int getNbNode() 
    {
        return nbNode;
    }

    /**
     * Renvoie le nombre de link dans le graphe
     * @return un entier
     */
    public int getNbLink() 
    {
        return nbLink;
    }

    /**
     * Renvoie la liste des node du graphe
     * @return une ArrayList d'objets de type Node
     */
    public ArrayList<Node> getNode()
    {
        return list_node;
    }

    /**
     * Renvoie la liste des link du graphe
     * @return une ArrayList d'objets de type Link
     */
    public ArrayList<Link> getLink()
    {
        return list_link;
    }

    /**
     * Crée un objet de type Node et l'ajoute au graphe
     * @param type correspond à un char
     * @param nom correspond à un String
     * @return un objet de type Node
     */
    public Node add_noeud(char type, String nom)
    {
        Node create;
        create = new Node(type, nom);
        list_node.add(create);
        nbNode++;
        return create;
    }

    /**
     * Crée un objet de type Link et l'ajoute au graphe 
     * @param distance correspond à un entier
     * @param type correspond à un char
     * @param depart correspond à un objet de type Node
     * @param arrivee correspond à un objet de type Node
     */
    public void add_link(int distance, char type, Node depart, Node arrivee) 
    {
        list_link.add(nbLink, new Link(distance, type, depart, arrivee));
        nbLink++;
    }

    /**
     * Renvoie une chaine de caractères indiquant la gastronomie d'un Node par rapport à un autre.
     * Plus le Node possède de voisins restaurants, plus il est gastronomique
     * @param noeud1 correspond à un objet de type Node
     * @param noeud2 correspond à un objet de type Node
     * @return un objet String
     */
    public String plusGastro(Node noeud1, Node noeud2)
    {
        int compteur1 = 0; int compteur2 = 0;
        for (Node noeud1test : listeVoisins(noeud1))
        {
            if (noeud1test.getType() == 'R') {
                compteur1++;
            }
        }
        for (Node noeud2test : listeVoisins(noeud2))
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
            return noeud1.getNom() + " est plus gastronomique que " + noeud2.getNom();
        }
        else {
            return noeud1.getNom() + " est moins gastronomique que " + noeud2.getNom();
        }
    }

    /**
     * Renvoie une chaine de caractères indiquant l'ouverture d'un Node par rapport à un autre.
     * Plus le Node possède de voisins qui sont des villes, plus il est ouvert
     * @param noeud1 correspond à un objet de type Node
     * @param noeud2 correspond à un objet de type Node
     * @return un objet String
     */
    public String plusOuverte(Node noeud1, Node noeud2)
    {
        int compteur1 = 0; int compteur2 = 0;
        for (Node noeud1test : listeVoisins(noeud1))
        {
            if (noeud1test.getType() == 'V') {
                compteur1++;
            }
        }
        for (Node noeud2test : listeVoisins(noeud2))
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
     * Renvoie une chaine de caractères indiquant la culture d'un Node par rapport à un autre.
     * Plus le Node possède de voisins qui sont des centres de loisir, plus il est gastronomique
     * @param noeud1 correspond à un objet de type Node
     * @param noeud2 correspond à un objet de type Node
     * @return un objet String
     */
    public String plusCulturelle(Node noeud1, Node noeud2)
    {
        int compteur1 = 0; int compteur2 = 0;
        for (Node noeud1test : listeVoisins(noeud1))
        {
            if (noeud1test.getType() == 'L') {
                compteur1++;
            }
        }
        for (Node noeud2test : listeVoisins(noeud2))
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
