
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ConnectedDialog extends SearchDialog
{
    ConnectedDialog (JFrame parent, String title,ArrayList<Node> list_nodes, ArrayList<Link> list_link)
    {
        super(parent,title,list_nodes,list_link);
        labelselector.setText("Liaison ");;
        selector.addItem(1);
        selector.addItem(2);
        this.setVisible(true);
    }
    @Override
    protected void action() 
    {
        super.action();
        Boolean find = false;
        if (nodeA.getSelectedItem().equals(nodeB.getSelectedItem()))
        {
            JOptionPane.showMessageDialog
            (
                                            null,
                                            "Les nodes sélectionnés sont identiques",
                                            "Vérification de connexion",
                                            JOptionPane.WARNING_MESSAGE
            );
        }
        else
        {
                    if (Integer.valueOf(selector.getSelectedItem().toString()) == 1)
                    {
                        for (Link link : list_link)
                        {
                           
                                if 
                                (
                                    (
                                        link.getDepart().getType() == nodeA.getSelectedItem().toString().split(",")[0].charAt(0)
                                        &&
                                        link.getDepart().getNom().equals(nodeA.getSelectedItem().toString().split(",")[1])
                                        &&
                                        link.getArrivee().getType() == nodeB.getSelectedItem().toString().split(",")[0].charAt(0)
                                        &&
                                        link.getArrivee().getNom().equals(nodeB.getSelectedItem().toString().split(",")[1])
                                    )
                                    ||
                                    (
                                        link.getDepart().getType() == nodeB.getSelectedItem().toString().split(",")[0].charAt(0)
                                        &&
                                        link.getDepart().getNom().equals(nodeB.getSelectedItem().toString().split(",")[1])
                                        &&
                                        link.getArrivee().getType() == nodeA.getSelectedItem().toString().split(",")[0].charAt(0)
                                        &&
                                        link.getArrivee().getNom().equals(nodeA.getSelectedItem().toString().split(",")[1])
                                    )       
                                )
                                {
                                    find = true;
                                    break;
                                }
                        }
                    }
                            else
                            {
                                for (Link linkA : list_link){
                                    for (Link linkB : list_link)
                                    {
                                        if
                                        (
                                            (
                                                linkA.getDepart().getType() == nodeA.getSelectedItem().toString().split(",")[0].charAt(0)
                                                &&
                                                linkA.getDepart().getNom().equals(nodeA.getSelectedItem().toString().split(",")[1])
                                                &&
                                                (
                                                    (
                                                        linkA.getArrivee().equals(linkB.getDepart())
                                                        &&
                                                        linkB.getArrivee().getType() == nodeB.getSelectedItem().toString().split(",")[0].charAt(0)
                                                        &&
                                                        linkB.getArrivee().getNom().equals(nodeB.getSelectedItem().toString().split(",")[1])
                                                    )
                                                    ||
                                                    (
                                                        linkA.getArrivee().equals(linkB.getArrivee())
                                                        &&
                                                        linkB.getDepart().getType() == nodeB.getSelectedItem().toString().split(",")[0].charAt(0)
                                                        &&
                                                        linkB.getDepart().getNom().equals(nodeB.getSelectedItem().toString().split(",")[1])
                                                    )
                                                )
                                            )
                                            ||
                                            (
                                                linkA.getDepart().getType() == nodeB.getSelectedItem().toString().split(",")[0].charAt(0)
                                                &&
                                                linkA.getDepart().getNom().equals(nodeB.getSelectedItem().toString().split(",")[1])
                                                &&
                                                (
                                                    (
                                                        linkA.getArrivee().equals(linkB.getDepart())
                                                        &&
                                                        linkB.getArrivee().getType() == nodeA.getSelectedItem().toString().split(",")[0].charAt(0)
                                                        &&
                                                        linkB.getArrivee().getNom().equals(nodeA.getSelectedItem().toString().split(",")[1])
                                                    )
                                                    ||
                                                    (
                                                        linkA.getArrivee().equals(linkB.getArrivee())
                                                        &&
                                                        linkB.getDepart().getType() == nodeA.getSelectedItem().toString().split(",")[0].charAt(0)
                                                        &&
                                                        linkB.getDepart().getNom().equals(nodeA.getSelectedItem().toString().split(",")[1])
                                                    )
                                                )
                                            )
                                        )
                                        {
                                            find = true;
                                            break;
                                        }
                                }
                            }
                        }
                    
                
                        if (find)
                        {
                            JOptionPane.showMessageDialog(
                                                            null,
                                                            "Les nodes "+nodeA.getSelectedItem()+" et "+nodeB.getSelectedItem()+" sont à "+selector.getSelectedItem()+" de liaison.",
                                                            "Vérification de connexion",
                                                            JOptionPane.INFORMATION_MESSAGE
                                                            );
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(
                                                            null,
                                                            "Les nodes "+nodeA.getSelectedItem()+" et "+nodeB.getSelectedItem()+" ne sont pas à "+selector.getSelectedItem()+" de liaison.",
                                                            "Vérification de connexion",
                                                            JOptionPane.ERROR_MESSAGE
                                                            );
                        }   
                    }
                }
    }
        

