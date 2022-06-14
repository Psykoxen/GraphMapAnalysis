import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;

import java.awt.GridBagConstraints;  
import java.awt.GridBagLayout;  

public class LinkDialog extends AddDialog{
    JComboBox noeudA;
    JComboBox noeudB;
    JSpinner spinnerDist;
    JComboBox type;
    JButton btnVerify;
    LinkDialog(JFrame parent, String title,ArrayList<Noeud> list_noeuds,ArrayList<Noeud> list_noeuds_affiche,ArrayList<Lien> list_liens,ArrayList<Lien> list_liens_affiche)
    {
        super(parent,title);

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel labelNoeudA = new JLabel("Lieux 1 ");
        panDialog.add(labelNoeudA,gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        noeudA = new JComboBox();
        for (Noeud noeud : list_noeuds_affiche)
        {
            noeudA.addItem(noeud);
        }
        panDialog.add(noeudA,gbc);


        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel labelNoeudB = new JLabel("Lieux 2 ");
        panDialog.add(labelNoeudB,gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        noeudB = new JComboBox();
        for (Noeud noeud : list_noeuds_affiche)
        {
            noeudB.addItem(noeud);
        }
        panDialog.add(noeudB,gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel labelType = new JLabel("Type ");
        panDialog.add(labelType,gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        type = new JComboBox();
        type.addItem("A");
        type.addItem("N");
        type.addItem("D");
        panDialog.add(type,gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel labelDist = new JLabel("Distance ");
        panDialog.add(labelDist,gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        SpinnerModel value = new SpinnerNumberModel(5, //initial value  
                0, //minimum value  
                100000, //maximum value  
                1); //step  
        spinnerDist = new JSpinner(value);
        panDialog.add(spinnerDist,gbc);

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 4;
        btnVerify = new JButton("Ajouter");
        btnVerify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerifyActionPerformed(list_noeuds_affiche,list_liens,list_liens_affiche);
            }});
        panDialog.add(btnVerify,gbc);

        this.add(panDialog);
        this.setVisible(true);
    }

    private void btnVerifyActionPerformed(ArrayList<Noeud> list_noeuds_affiche, ArrayList<Lien> list_liens,ArrayList<Lien> list_liens_affiche) {
        Noeud noeudAToLink = null;
        Noeud noeudBToLink = null;
        System.out.println(noeudA);
        System.out.println(noeudB);
        if (!noeudA.getSelectedItem().toString().equals(noeudB.getSelectedItem().toString()))
        {
            for (Noeud noeud : list_noeuds_affiche)
                    {
                        if 
                        (
                            noeudA.getSelectedItem().toString().split(",")[0].charAt(0)==noeud.getType()
                            &&
                            noeudA.getSelectedItem().toString().split(",")[1].equals(noeud.getNom())
                        )
                        {
                            noeudAToLink = noeud;
                        }
                        else if
                        (
                            noeudB.getSelectedItem().toString().split(",")[0].charAt(0)==noeud.getType()
                            &&
                            noeudB.getSelectedItem().toString().split(",")[1].equals(noeud.getNom())
                        )
                        {
                            noeudBToLink = noeud;
                        }
                    }
            Lien link = new Lien(Integer.valueOf((int) spinnerDist.getValue()), type.getSelectedItem().toString().charAt(0), noeudAToLink, noeudBToLink);
            list_liens.add(link);
            list_liens_affiche.add(link);
            dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(
                                                        null,
                                                        "Les noeuds sélectionnés sont identique",
                                                        "Vérification",
                                                        JOptionPane.WARNING_MESSAGE
                                                        );
        }
    }
}
