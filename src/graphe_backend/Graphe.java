package graphe_backend;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import java.io.FileNotFoundException;

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
            e.printStackTrace();
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
        Boolean check;
        int i = 0;
        File file = new File("graphe_data/Graphe.csv");
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
                        check =true;
                        for (Lien lientoCheck : list_liens)
                        {
                            if 
                            (
                                (lientoCheck.getDepart().equals(voisin))
                                &&
                                (lientoCheck.getArrivee().equals(start))
                                &&
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

    public ArrayList<Noeud> getNoeuds()
    {
        return list_noeuds;
    }

    public ArrayList<Lien> getLiens()
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



    public void add_lien(int distance, char type, Noeud depart, Noeud arrivee) {
        list_liens.add(nbLiens, new Lien(distance, type, depart, arrivee));
        nbLiens++;
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
