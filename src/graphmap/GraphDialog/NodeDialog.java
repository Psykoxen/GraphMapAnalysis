package graphmap.GraphDialog;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;  

import graphmap.GraphBackEnd.Node;
public class NodeDialog extends AddDialog{
    JTextField node;
    JComboBox type;
    JButton btnAdd;
    public NodeDialog(JFrame parent, String title,ArrayList<Node> list_node,ArrayList<Node> list_node_affiche)
    {
        super(parent,title);

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel labelNode = new JLabel("Nom ");
        

        panDialog.add(labelNode,gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        node = new JTextField(13);
        panDialog.add(node,gbc);


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
                btnAddActionPerformed(list_node_affiche,list_node);
            }});
        panDialog.add(btnAdd,gbc);

        this.add(panDialog);
        this.setVisible(true);
    }

    private void btnAddActionPerformed(ArrayList<Node> list_node_affiche, ArrayList<Node> list_node) {
        Node newNode = null;
        Boolean find = false;
        for (Node nodeCheck : list_node)
        {
            if 
                (
                    type.getSelectedItem().toString().charAt(0) == nodeCheck.getType()
                    &&
                    node.getText().equals(nodeCheck.getNom())
                    )
                    {
                        find = true;
                        break;
                    }
        }
        if (!find)
        {
            newNode = new Node(type.getSelectedItem().toString().charAt(0),node.getText());
            list_node.add(newNode);
            list_node_affiche.add(newNode);
            dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(
                                                        null,
                                                        "Le node existe déjà !",
                                                        "Vérification",
                                                        JOptionPane.WARNING_MESSAGE
                                                        );
        }
        
            
        }
    }
