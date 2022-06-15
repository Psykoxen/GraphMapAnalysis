package graphmap.UI.Dialog;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import graphmap.models.Node;
import graphmap.models.Link;

/**
 * 
 * @author Antoine
 * 
 * JDialog étendant une JDialog générale utilisé pour les Dialog d'affichage, cette JDialog sert à l'ajout de lien.
 * 
 */
public class LinkDialog extends AddDialog{
    JComboBox nodeA;
    JComboBox nodeB;
    JSpinner spinnerDist;
    JComboBox type;
    JButton btnVerify;
    public LinkDialog(JFrame parent, String title,ArrayList<Node> list_node,ArrayList<Node> list_node_affiche,ArrayList<Link> list_link,ArrayList<Link> list_link_affiche)
    {
        super(parent,title);
        DefaultComboBoxModel modelA = new DefaultComboBoxModel<>();
        DefaultComboBoxModel modelB = new DefaultComboBoxModel<>();
        for (Node node : list_node_affiche)
        {
            modelA.addElement(node);
            modelB.addElement(node);
        }
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel labelNodeA = new JLabel("Lieux 1 ");
        panDialog.add(labelNodeA,gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        nodeA = new JComboBox<Node>();
        nodeA.setModel(modelA);
        panDialog.add(nodeA,gbc);


        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel labelNodeB = new JLabel("Lieux 2 ");
        panDialog.add(labelNodeB,gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        nodeB = new JComboBox<Node>();
        nodeB.setModel(modelB);
        panDialog.add(nodeB,gbc);

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
                btnVerifyActionPerformed(list_node_affiche,list_link,list_link_affiche);
            }});
        panDialog.add(btnVerify,gbc);

        this.add(panDialog);
        this.setVisible(true);
    }

    private void btnVerifyActionPerformed(ArrayList<Node> list_node_affiche, ArrayList<Link> list_link,ArrayList<Link> list_link_affiche) {
        Node nodeAToLink = null;
        Node nodeBToLink = null;
        if (!nodeA.getSelectedItem().toString().equals(nodeB.getSelectedItem().toString()))
        {
            for (Node node : list_node_affiche)
                    {
                        if 
                        (
                            nodeA.getSelectedItem().toString().split(",")[0].charAt(0)==node.getType()
                            &&
                            nodeA.getSelectedItem().toString().split(",")[1].equals(node.getNom())
                        )
                        {
                            nodeAToLink = node;
                        }
                        else if
                        (
                            nodeB.getSelectedItem().toString().split(",")[0].charAt(0)==node.getType()
                            &&
                            nodeB.getSelectedItem().toString().split(",")[1].equals(node.getNom())
                        )
                        {
                            nodeBToLink = node;
                        }
                    }
            Link link = new Link(Integer.valueOf((int) spinnerDist.getValue()), type.getSelectedItem().toString().charAt(0), nodeAToLink, nodeBToLink);
            list_link.add(link);
            list_link_affiche.add(link);
            dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(
                                                        null,
                                                        "Les node sélectionnés sont identique",
                                                        "Vérification",
                                                        JOptionPane.WARNING_MESSAGE
                                                        );
        }
    }
}
