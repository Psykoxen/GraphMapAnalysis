import java.util.Objects;

/**
 *
 * @author Antoine
 */

public class Noeud {
    private char type;
    private String nom;
    private int x;
    private int y;
    
    public Noeud(char newType, String newNom)
    {
        type = newType;
        nom = newNom;
    }

    @Override
    public boolean equals(Object o) 
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Noeud noeud = (Noeud) o;
        return type == noeud.type && Objects.equals(nom, noeud.nom);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hash(type, nom);
    }

   /*
    * Renvoie le nom du noeud
    * @return String contenant le nom du noeud
    */
    public String getNom()
    {
        return this.nom;
    }

    
    /*
     * Renvoie le type du noeud
     * @return char contenant le type du noeud
     */
    public char getType()
    {
        return this.type;
    }

    
    /* 
     * Renvoie la coordonnée de l'abscisse du noeud sur la fenêtre
     * @return int contenant la position X du noeud
     */
    public int getX()
    {
        return this.x;
    }

    
    /*
     * Renvoie la coordonnée de l'ordonnée du noeud sur la fenêtre
     * @return int contenant la position Y du noeud
     */
    public int getY()
    {
        return this.y;
    }

    
    /* 
     * Change le nom du noeud par celui donné en paramètres
     * @param newNom correspond à un String
     */
    public void setNom(String newNom)
    {
        this.nom = newNom;
    }

    
    /* 
     * Change le type du noeud par celui donné en paramètres
     * @param newType correspond à un char
     */
    public void setType(char newType)
    {
        this.type = newType;
    }
    
    
    /* 
     * Change la coordonnée de l'abscisse du noeud par celle donné en paramètres
     * @param x correspond à un int
     */
    public void setX(int x)
    {
        this.x = x;
    }
    
    
    /* 
     * Change la coordonnée de l'ordonnée du noeud par celle donné en paramètres
     * @param y correspond à un int
     */
    public void setY(int y)
    {
        this.y = y;
    }
    
    
    /* 
     * Convertit le noeud en chaîne de caractères
     * @return String contenant une chaine de caractères personnalisée du noeud
     */
    @Override
    public String toString()
    {
        return String.valueOf(this.getType())+','+this.getNom();
    }
        
}
