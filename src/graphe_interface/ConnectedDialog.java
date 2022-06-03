

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ConnectedDialog extends JDialog 
{
    private ArrayList<Noeud> list_noeuds;
    private ArrayList<Lien> list_liens;
    private JPanel panConnectedDialog;
    private Boolean find;

    public ConnectedDialog (JFrame parent, String title,ArrayList<Noeud> list_noeuds, ArrayList<Lien> list_liens)
    {
        super(parent,title,true);
        this.setSize(400,100);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        this.list_noeuds = list_noeuds;
        this.list_liens = list_liens;
        
        panConnectedDialog  = new JPanel();
        panConnectedDialog.setPreferredSize(new Dimension(400,400));
        JComboBox noeudA = new JComboBox();

        for (Noeud noeud : list_noeuds)
        {
            noeudA.addItem(noeud);
        }
        panConnectedDialog.add(noeudA);
        JComboBox noeudB = new JComboBox();
        for (Noeud noeud : list_noeuds)
        {
            noeudB.addItem(noeud);
        }
        panConnectedDialog.add(noeudB);
        JButton check = new JButton("Vérifier");
        check.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println(noeudA.getSelectedItem().toString());
                System.out.println(noeudB.getSelectedItem().toString());
                find = false;
                if (noeudA.getSelectedItem().equals(noeudB.getSelectedItem()))
                {
                    JOptionPane.showMessageDialog(
                                                        null,
                                                        "Les noeuds sélectionnés sont identiques",
                                                        "Vérification de connexion",
                                                        JOptionPane.WARNING_MESSAGE
                                                        );
                }
                else
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
                        if (find)
                        {
                            JOptionPane.showMessageDialog(
                                                            null,
                                                            "Les noeuds "+noeudA.getSelectedItem()+" et "+noeudB.getSelectedItem()+" sont connecté !",
                                                            "Vérification de connexion",
                                                            JOptionPane.INFORMATION_MESSAGE
                                                            );
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(
                                                            null,
                                                            "Les noeuds "+noeudA.getSelectedItem()+" et "+noeudB.getSelectedItem()+" ne sont pas connecté !",
                                                            "Vérification de connexion",
                                                            JOptionPane.ERROR_MESSAGE
                                                            );
                        }   
                    }
                }
        });
        panConnectedDialog.add(check);
        this.add(panConnectedDialog, BorderLayout.CENTER);
        this.setVisible(true);
    }
    
}
