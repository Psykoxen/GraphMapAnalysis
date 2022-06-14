package graphmap.GraphDialog;


import graphmap.GraphBackEnd.Graphe;
import graphmap.GraphBackEnd.Node;
import graphmap.GraphBackEnd.Link;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class CompareDialog extends SearchDialog{
    Graphe graphe;
    public CompareDialog (JFrame parent, String title,ArrayList<Node> list_node, ArrayList<Link> list_link, Graphe graphe)
    {
        super(parent,title,list_node,list_link);
        this.graphe = graphe;
        labelselector.setText("Critère ");;
        selector.addItem("Gastronomique");
        selector.addItem("Ouverte");
        selector.addItem("Culturelle");
        this.setVisible(true);
    }
    @Override
    protected void action() {
        super.action();
        String text = null;
        Node nodeAToCompare = null;
        Node nodeBToCompare = null;
        if (nodeA.getSelectedItem().equals(nodeB.getSelectedItem()))
                {
                    JOptionPane.showMessageDialog(
                                                        null,
                                                        "Les node sélectionnés sont identiques",
                                                        "Vérification de connexion",
                                                        JOptionPane.WARNING_MESSAGE
                                                        );
                }
                else
                {
                    for (Node node : list_node)
                    {
                        if 
                        (
                            nodeA.getSelectedItem().toString().split(",")[0].charAt(0)==node.getType()
                            &&
                            nodeA.getSelectedItem().toString().split(",")[1].equals(node.getNom())
                        )
                        {
                            nodeAToCompare = node;
                        }
                        else if
                        (
                            nodeB.getSelectedItem().toString().split(",")[0].charAt(0)==node.getType()
                            &&
                            nodeB.getSelectedItem().toString().split(",")[1].equals(node.getNom())
                        )
                        {
                            nodeBToCompare = node;
                        }
                    }
                    switch(selector.getSelectedItem().toString())
                    {
                            case "Gastronomique":text = graphe.plusGastro(nodeAToCompare, nodeBToCompare);break;
                            case "Ouverte": text = graphe.plusOuverte(nodeAToCompare, nodeBToCompare);break;
                            case "Culturelle": text = graphe.plusCulturelle(nodeAToCompare, nodeBToCompare);break;
                    }
                    JOptionPane.showMessageDialog(
                                                                            null,
                                                                            text,
                                                                            "Vérification de connexion",
                                                                            JOptionPane.INFORMATION_MESSAGE
                                                                            );
            }
        }
}
