
import java.util.Objects;

import javax.swing.JLabel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Maxence
 */
public class Lien {
    int distance;
    char type;
    Noeud depart;
    Noeud arrivee;
    JLabel label;

    public Lien(int newDistance, char newType, Noeud newDepart, Noeud newArrivee)
    {
        distance = newDistance;
        type = newType;
        depart = newDepart;
        arrivee = newArrivee;
        label = new JLabel(type+","+distance);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.distance;
        hash = 71 * hash + this.type;
        hash = 71 * hash + Objects.hashCode(this.depart);
        hash = 71 * hash + Objects.hashCode(this.arrivee);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Lien other = (Lien) obj;
        if (this.distance != other.distance) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (!Objects.equals(this.depart, other.depart)) {
            return false;
        }
        return Objects.equals(this.arrivee, other.arrivee);
    }
    
    /**
     * Renvoie la distance du lien
     * @return Un entier valant la distance du lien. 
     */
    public int getDistance()
    {
        return this.distance;
    }
    
    /**
     * Renvoie le type du lien
     * @return Un char valant le type du lien. 
     */
    public char getType()
    {
        return this.type;
    }
    
    /**
     * Renvoie le NoeudDepart du lien
     * @return Un objet de type noeud. 
     */
    public Noeud getDepart()
    {
        return this.depart;
    }
    
    /**
     * Renvoie le NoeudArrivee du lien
     * @return Un objet de type noeud. 
     */
    public Noeud getArrivee()
    {
        return this.arrivee;
    }

    public JLabel getLabel()
    {
        return this.label;
    }
}
