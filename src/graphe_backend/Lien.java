import java.util.Objects;

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
    
    public Lien(int newDistance, char newType, Noeud newDepart, Noeud newArrivee)
    {
        distance = newDistance;
        type = newType;
        depart = newDepart;
        arrivee = newArrivee;
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
    
    
    
    public int getDistance()
    {
        return this.distance;
    }
    
    public char getType()
    {
        return this.type;
    }
    
    public Noeud getDepart()
    {
        return this.depart;
    }
    
    public Noeud getArrivee()
    {
        return this.arrivee;
    }

    public String toString(){ return "ville de départ : " + depart + ", ville d'arrivée : "
            + arrivee + ", type de route : " + type + ", distance : " + distance;}
}
