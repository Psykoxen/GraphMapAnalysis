
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagConstraints;  
import java.awt.GridBagLayout; 

public class SearchDialog extends JDialog{
    protected ArrayList<Noeud> list_noeuds;
    protected ArrayList<Lien> list_liens;
    private JPanel panConnectedDialog;
    private Boolean find;
    
    JButton check;
    JComboBox noeudA;
    JComboBox noeudB;
    JComboBox selector;
    JLabel labelselector;

    

    public SearchDialog (JFrame parent, String title,ArrayList<Noeud> list_noeuds, ArrayList<Lien> list_liens)
    {
        super(parent,title,true);
        this.setSize(220,150);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        this.list_noeuds = list_noeuds;
        this.list_liens = list_liens;
        
        panConnectedDialog  = new JPanel();
        panConnectedDialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel labelNoeudA = new JLabel("Lieux 1 ");
        panConnectedDialog.add(labelNoeudA,gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        noeudA = new JComboBox();
        for (Noeud noeud : list_noeuds)
        {
            noeudA.addItem(noeud);
        }
        panConnectedDialog.add(noeudA,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel labelNoeudB = new JLabel("Lieux 2 ");
        panConnectedDialog.add(labelNoeudB,gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        noeudB = new JComboBox();
        for (Noeud noeud : list_noeuds)
        {
            noeudB.addItem(noeud);
        }
        panConnectedDialog.add(noeudB,gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        labelselector = new JLabel();
        panConnectedDialog.add(labelselector,gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        selector = new JComboBox();
        panConnectedDialog.add(selector,gbc);




        check = new JButton("Vérifier");
        check.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                action();
            }});
        gbc.gridwidth = 2;    
        gbc.gridx = 0;
        gbc.gridy = 3;
        panConnectedDialog.add(check,gbc);
        this.add(panConnectedDialog);
    }

    protected void action() {

    }
}
