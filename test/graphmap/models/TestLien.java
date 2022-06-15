package graphmap.models;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Maxence
 */

public class TestLien {
    @Test
    public void testLink()
    {
        Link testLink1 = new Link(50, 'A', new Node('V', "Villeurbanne"), new Node('R', "Ain El Fouara"));
        Link testLink2 = new Link(50, 'N', new Node('V', "Villeurbanne"), new Node('R', "Burger King Part Dieu"));
        boolean test1 = assertequals(testLink1.getDistance(),testLink2.getDistance());;
        boolean test2 = assertequals(testLink1.getDepart(),testLink1.getDepart());
        boolean test3 = assertfalse(assertequals(testLink1.getArrivee().getNom(),testLink2.getArrivee().getNom()));
        System.out.println("Les tests se sont déroulés sans problème");
        return;
    }
}
