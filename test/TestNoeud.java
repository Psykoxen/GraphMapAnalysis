/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Maxence
 */
public class TestNoeud {
    public void testNoeud()
    {
        Noeud noeudTest1 = new Noeud('V', "Test ville 1");
        Noeud noeudTest2 = new Noeud('R', "Test restaurant 2");
        Noeud noeudTest3 = new Noeud('L', "Test loisir 3");
        Noeud noeudTest4 = new Noeud('V', "Test ville 4");
        Noeud noeudTest5 = new Noeud('V', "Test ville 1");
        boolean test1 = assertequals(noeudTest1.getType(),noeudTest4.getType());
        boolean test2 = assertequals(noeudTest1.getNom(),noeudTest5.getNom());
        boolean test3 = assertfalse(assertequals(noeudTest3.getType(),noeudTest4.getType()));
        if(test1 && test2 && test3)
        {
            System.out.println("Les tests renvoient un résultat positif");
            return;
        }
        System.out.println("Problèmes lors des tests");
        return;
    }
}
