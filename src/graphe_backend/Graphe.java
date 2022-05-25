package graphe_backend;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.Reader;
import java.util.*;
import java.io.File;

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

    public Graphe() {
        list_noeuds = new ArrayList<>();
        list_liens = new ArrayList<>();
        nbNoeuds = 0;
        nbLiens = 0;
        try {
            loading();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void afficherVoisins()
    {
        ArrayList<Noeud> voisins;
        for(Noeud noeud:list_noeuds)
        {
            System.out.print("Le noeud "+noeud.getNom() + " possède ");
            voisins = this.listeVoisins(noeud);
            for (Noeud voisin : voisins)
            {
                System.out.print(voisin.getNom()+", ");
            }
            System.out.println(" comme voisin(s).");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Graphe graphe = (Graphe) o;
        return nbNoeuds == graphe.nbNoeuds && nbLiens == graphe.nbLiens && Objects.equals(list_noeuds, graphe.list_noeuds) && Objects.equals(list_liens, graphe.list_liens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nbNoeuds, nbLiens, list_noeuds, list_liens);
    }
    public void loading() throws FileNotFoundException
    {
        String line;
        String[] lieux;
        String[] liens;
        String[] details;
        String[] data;
        Noeud start = null;
        Noeud voisin = null;
        int i = 0;
        File file = new File("src\\graphe_data\\Graphe.csv");
        Scanner scan = new Scanner(file);
        scan.useDelimiter(";;");
        while (scan.hasNext()) {
            line = scan.next();
            if (!line.equals(null))
            {
                lieux = line.split(":", 2);
                data = lieux[0].split(",");
                if (i != 0) {
                    if (existNoeud(data[0].charAt(2), data[1]) == null) {
                        start = this.add_noeud(data[0].charAt(2), data[1]);
                    } else {
                        start = existNoeud(data[0].charAt(2), data[1]);

                    }
                    liens = lieux[1].split(";");
                    for (String lien : liens) {
                        details = lien.split("::");

                        data = details[1].split(",");
                        if (existNoeud(data[0].charAt(0), data[1]) == null) {
                            voisin = this.add_noeud(data[0].charAt(0), data[1]);
                        } else {
                            voisin = existNoeud(data[0].charAt(0), data[1]);
                        }
                        data = details[0].split(",");
                        this.add_lien(Integer.parseInt(data[1]), data[0].charAt(0), start, voisin);
                    }
                }i++;
            }
    }
        scan.close();
    }

    /*
    public void loading() throws FileNotFoundException {
        CSVReader reader = new CSVReader(new FileReader("./noeud.csv"));
        CSVParser parser = new CSVParserBuilder().withSeparator(";;").build();
    }
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

    public int getNbNoeuds() {
        return nbNoeuds;
    }

    public int getNbLiens() {
        return nbLiens;
    }

    public ArrayList getNoeuds()
    {
        return list_noeuds;
    }

    public ArrayList getLiens()
    {
        return list_liens;
    }

    public Noeud add_noeud(char type, String nom)
    {
        Noeud create;
        create = new Noeud(type, nom);
        list_noeuds.add(create);
        nbNoeuds++;
        return create;
    }

    public String trouverLien(Lien lien)
    {
        return "Noeud de départ: " + lien.depart.getType() + lien.depart.getNom() +
                "Noeud d'arrivée : " + lien.arrivee.getType() + lien.arrivee.getNom();
    }

    public void add_lien(int distance, char type, Noeud depart, Noeud arrivee) {
        list_liens.add(nbLiens, new Lien(distance, type, depart, arrivee));
        nbLiens++;
    }

    public void afficherLieux()
    {
        for (Noeud noeud : list_noeuds) {
            System.out.println(noeud.toString());
        }
    }

    public void afficherVilles()
    {
        for (Noeud ville : list_noeuds) {
            if (ville.getType() == 'V') {
                System.out.println(ville.getNom());
            }
        }
    }

    public void afficherLoisirs()
    {
        for (Noeud loisir : list_noeuds) {
            if (loisir.getType() == 'L') {
                System.out.println(loisir.getNom());
            }
        }
    }

    public void afficherRestaurants()
    {
        for (Noeud loisir : list_noeuds) {
            if (loisir.getType() == 'R') {
                System.out.println(loisir.getNom());
            }
        }
    }

    public void afficherLiens()
    {
        for (Lien lien : list_liens) {
            System.out.println(lien.toString());
            }
    }

    public void afficherAutoroutes()
    {
        for (Lien autoroute : list_liens) {
            if (autoroute.getType() == 'A') {
                System.out.println(autoroute.toString());
            }
        }
    }

    public void afficherNationales()
    {
        for (Lien nationale : list_liens) {
            if (nationale.getType() == 'N') {
                System.out.println(nationale.toString());
            }
        }
    }

    public void afficherDepartementales()
    {
        for (Lien departementale : list_liens) {
            if (departementale.getType() == 'D') {
                System.out.println(departementale.toString());
            }
        }
    }

    public int nbrLoisirs()
    {
        int res = 0;
        for (Noeud loisir : list_noeuds) {
            if (loisir.getType() == 'L') {
                res++;
            }
        }
        return res;
    }

    public int nbrVilles()
    {
        int res = 0;
        for (Noeud ville : list_noeuds) {
            if (ville.getType() == 'V') {
                res++;
            }
        }
        return res;
    }

    public int nbrRestaurants()
    {
        int res = 0;
        for (Noeud restaurant : list_noeuds) {
            if (restaurant.getType() == 'R') {
                res++;
            }
        }
        return res;
    }

    public int nbrAutoroutes()
    {
        int res = 0;
        for (Lien autoroute : list_liens) {
            if (autoroute.getType() == 'A') {
                res++;
            }
        }
        return res;
    }

    public int nbrNationales()
    {
        int res = 0;
        for (Lien nationale : list_liens) {
            if (nationale.getType() == 'N') {
                res++;
            }
        }
        return res;
    }

    public int nbrDepartementales()
    {
        int res = 0;
        for (Lien departementale : list_liens) {
            if (departementale.getType() == 'D') {
                res++;
            }
        }
        return res;
    }

    public String plusGastro(Noeud noeud1, Noeud noeud2)
    {
        int compteur1 = 0; int compteur2 = 0;
        for (Noeud noeud1test : liste2Voisins(noeud1))
        {
            if (noeud1test.getType() == 'R') {
                compteur1++;
            }
        }
        for (Noeud noeud2test : liste2Voisins(noeud2))
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




    public String plusOuverte(Noeud noeud1, Noeud noeud2)
    {
        int compteur1 = 0; int compteur2 = 0;
        for (Noeud noeud1test : liste2Voisins(noeud1))
        {
            if (noeud1test.getType() == 'V') {
                compteur1++;
            }
        }
        for (Noeud noeud2test : liste2Voisins(noeud2))
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


    public String plusCulturelle(Noeud noeud1, Noeud noeud2)
    {
        int compteur1 = 0; int compteur2 = 0;
        for (Noeud noeud1test : liste2Voisins(noeud1))
        {
            if (noeud1test.getType() == 'L') {
                compteur1++;
            }
        }
        for (Noeud noeud2test : liste2Voisins(noeud2))
        {
            if (noeud2test.getType() == 'L')
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

    public void Affichage()
    {
        Scanner scanner = new Scanner(System.in);
        boolean check = true;
        while (check)
        {
            System.out.println("Veuillez entrer la valeur se trouvant devant ce que vous souhaitez afficher :");
            System.out.println("1. Seulement les villes");
            System.out.println("2. Seulement les centres de loisirs");
            System.out.println("3. Seulement les restaurants");
            System.out.println("4. Seulement les autoroutes");
            System.out.println("5. Seulement les nationales");
            System.out.println("6. Seulement les départementales");
            System.out.println("8. Tous les lieux");
            System.out.println("9. Toutes les routes");
            System.out.println("10. Les villes et les restaurants");
            System.out.println("11. Les villes et les centres de loisir");
            System.out.println("12. Les restaurants et les centres de loisirs");
            System.out.println("13. les autoroutes et nationales");
            System.out.println("14. les autoroutes et départementales");
            System.out.println("15. les nationales et départementales");
            int choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    afficherVilles();
                    break;
                case 2:
                    afficherLoisirs();
                    break;
                case 3:
                    afficherRestaurants();
                    break;
                case 4:
                    afficherAutoroutes();
                    break;
                case 5:
                    afficherNationales();
                    break;
                case 6:
                    afficherDepartementales();
                    break;
                case 7:
                    afficherLieux();
                    break;
                case 8:
                    afficherLiens();
                    break;
            }
        }
    }
/*
    public void FloydMarshall()
    {
        int[][] distances = new int[list_noeuds.size()][list_noeuds.size()];
        int inf = 999999999;
        Arrays.fill(distances,-1);
        for (int i = 0;i<list_noeuds.size())
        {
            for (int j = 0; j<list_noeuds.size())
            {
                if (i == j)
                {
                    distances[i][j] = inf;
                }
                else
                {
                    for (Lien lien : list_liens)
                    {
                        if (lien.getDepart().equals(list_noeuds.get(j)))
                        {
                            distances[i][j] = lien.getDistance();
                        }
                    }
                    if (distances[i][j] == 0)
                    {
                        distances[i][j] = inf;
                    }
                }
            }
        }
        for(Noeud k : list_noeuds)
        {
            for (Noeud i : list_noeuds)
            {
                for (Noeud j : list_noeuds)
                {
                    if (distances[i][j] > distances[i][k] + distances[k][j])
                    {
                        distances[i][j] = distances[i][k] + distances[k][j];

                    }
                }
            }
        }
    }*/
}
