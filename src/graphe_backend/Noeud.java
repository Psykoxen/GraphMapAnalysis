/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Noeud noeud = (Noeud) o;
        return type == noeud.type && Objects.equals(nom, noeud.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, nom);
    }

    public String getNom()
    {
        return this.nom;
    }

    public char getType()
    {
        return this.type;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public void setNom(String newNom)
    {
        this.nom = newNom;
    }

    public void setType(char newType)
    {
        this.type = newType;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }
    
    
    
}
