import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;  

public class NodeDialog extends AddDialog{
    JTextField noeud;
    JComboBox type;
    JButton btnAdd;
    NodeDialog(JFrame parent, String title,ArrayList<Noeud> list_noeuds,ArrayList<Noeud> list_noeuds_affiche)
    {
        super(parent,title);

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel labelNoeud = new JLabel("Nom ");
        

        panDialog.add(labelNoeud,gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        noeud = new JTextField(13);
        panDialog.add(noeud,gbc);


        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel labelType = new JLabel("Type ");
        panDialog.add(labelType,gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        type = new JComboBox();
        type.addItem("V");
        type.addItem("L");
        type.addItem("R");
        panDialog.add(type,gbc);

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 2;
        btnAdd = new JButton("Ajouter");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(list_noeuds_affiche,list_noeuds);
            }});
        panDialog.add(btnAdd,gbc);

        this.add(panDialog);
        this.setVisible(true);
    }

    private void btnAddActionPerformed(ArrayList<Noeud> list_noeuds_affiche, ArrayList<Noeud> list_noeuds) {
        Noeud newNoeud = null;
        Boolean find = false;
        for (Noeud noeudCheck : list_noeuds)
        {
            if 
                (
                    type.getSelectedItem().toString().charAt(0) == noeudCheck.getType()
                    &&
                    noeud.getText().equals(noeudCheck.getNom())
                    )
                    {
                        find = true;
                        break;
                    }
        }
        if (!find)
        {
            newNoeud = new Noeud(type.getSelectedItem().toString().charAt(0),noeud.getText());
            list_noeuds.add(newNoeud);
            list_noeuds_affiche.add(newNoeud);
            dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(
                                                        null,
                                                        "Le noeud existe déjà !",
                                                        "Vérification",
                                                        JOptionPane.WARNING_MESSAGE
                                                        );
        }
        
            
        }
    }
