package graphmap.models;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Maxence
 */
public class TestNoeud {
    @Test
    public void testNoeud()
    {
        Node noeudTest1 = new Node('V', "Test ville 1");
        Node noeudTest2 = new Node('R', "Test restaurant 2");
        Node noeudTest3 = new Node('L', "Test loisir 3");
        Node noeudTest4 = new Node('V', "Test ville 4");
        Node noeudTest5 = new Node('V', "Test ville 1");
        boolean test1 = asserequals(noeudTest1.getType(),noeudTest4.getType());
        boolean test2 = assertequals(noeudTest1.getNom(),noeudTest5.getNom());
        boolean test3 = assertfalse(assertequals(noeudTest3.getType(),noeudTest4.getType()));
        System.out.println("Les tests renvoient un résultat positif");
        return;
    }
}
