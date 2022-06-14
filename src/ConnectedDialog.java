
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ConnectedDialog extends SearchDialog
{
    ConnectedDialog (JFrame parent, String title,ArrayList<Noeud> list_noeuds, ArrayList<Lien> list_liens)
    {
        super(parent,title,list_noeuds,list_liens);
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
        if (noeudA.getSelectedItem().equals(noeudB.getSelectedItem()))
        {
            JOptionPane.showMessageDialog
            (
                                            null,
                                            "Les noeuds sélectionnés sont identiques",
                                            "Vérification de connexion",
                                            JOptionPane.WARNING_MESSAGE
            );
        }
        else
        {
                    if (Integer.valueOf(selector.getSelectedItem().toString()) == 1)
                    {
                        for (Lien lien : list_liens)
                        {
                           
                                if 
                                (
                                    (
                                        lien.getDepart().getType() == noeudA.getSelectedItem().toString().split(",")[0].charAt(0)
                                        &&
                                        lien.getDepart().getNom().equals(noeudA.getSelectedItem().toString().split(",")[1])
                                        &&
                                        lien.getArrivee().getType() == noeudB.getSelectedItem().toString().split(",")[0].charAt(0)
                                        &&
                                        lien.getArrivee().getNom().equals(noeudB.getSelectedItem().toString().split(",")[1])
                                    )
                                    ||
                                    (
                                        lien.getDepart().getType() == noeudB.getSelectedItem().toString().split(",")[0].charAt(0)
                                        &&
                                        lien.getDepart().getNom().equals(noeudB.getSelectedItem().toString().split(",")[1])
                                        &&
                                        lien.getArrivee().getType() == noeudA.getSelectedItem().toString().split(",")[0].charAt(0)
                                        &&
                                        lien.getArrivee().getNom().equals(noeudA.getSelectedItem().toString().split(",")[1])
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
                                for (Lien lienA : list_liens){
                                    for (Lien lienB : list_liens)
                                    {
                                        if
                                        (
                                            (
                                                lienA.getDepart().getType() == noeudA.getSelectedItem().toString().split(",")[0].charAt(0)
                                                &&
                                                lienA.getDepart().getNom().equals(noeudA.getSelectedItem().toString().split(",")[1])
                                                &&
                                                (
                                                    (
                                                        lienA.getArrivee().equals(lienB.getDepart())
                                                        &&
                                                        lienB.getArrivee().getType() == noeudB.getSelectedItem().toString().split(",")[0].charAt(0)
                                                        &&
                                                        lienB.getArrivee().getNom().equals(noeudB.getSelectedItem().toString().split(",")[1])
                                                    )
                                                    ||
                                                    (
                                                        lienA.getArrivee().equals(lienB.getArrivee())
                                                        &&
                                                        lienB.getDepart().getType() == noeudB.getSelectedItem().toString().split(",")[0].charAt(0)
                                                        &&
                                                        lienB.getDepart().getNom().equals(noeudB.getSelectedItem().toString().split(",")[1])
                                                    )
                                                )
                                            )
                                            ||
                                            (
                                                lienA.getDepart().getType() == noeudB.getSelectedItem().toString().split(",")[0].charAt(0)
                                                &&
                                                lienA.getDepart().getNom().equals(noeudB.getSelectedItem().toString().split(",")[1])
                                                &&
                                                (
                                                    (
                                                        lienA.getArrivee().equals(lienB.getDepart())
                                                        &&
                                                        lienB.getArrivee().getType() == noeudA.getSelectedItem().toString().split(",")[0].charAt(0)
                                                        &&
                                                        lienB.getArrivee().getNom().equals(noeudA.getSelectedItem().toString().split(",")[1])
                                                    )
                                                    ||
                                                    (
                                                        lienA.getArrivee().equals(lienB.getArrivee())
                                                        &&
                                                        lienB.getDepart().getType() == noeudA.getSelectedItem().toString().split(",")[0].charAt(0)
                                                        &&
                                                        lienB.getDepart().getNom().equals(noeudA.getSelectedItem().toString().split(",")[1])
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
                                                            "Les noeuds "+noeudA.getSelectedItem()+" et "+noeudB.getSelectedItem()+" sont à "+selector.getSelectedItem()+" de liaison.",
                                                            "Vérification de connexion",
                                                            JOptionPane.INFORMATION_MESSAGE
                                                            );
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(
                                                            null,
                                                            "Les noeuds "+noeudA.getSelectedItem()+" et "+noeudB.getSelectedItem()+" ne sont pas à "+selector.getSelectedItem()+" de liaison.",
                                                            "Vérification de connexion",
                                                            JOptionPane.ERROR_MESSAGE
                                                            );
                        }   
                    }
                }
    }
        

