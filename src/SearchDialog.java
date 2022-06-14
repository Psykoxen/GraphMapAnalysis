
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel; 

public class SearchDialog extends JDialog{
    protected ArrayList<Node> list_node;
    protected ArrayList<Link> list_link;
    private JPanel panConnectedDialog;
    private Boolean find;
    
    JButton check;
    JComboBox nodeA;
    JComboBox nodeB;
    JComboBox selector;
    JLabel labelselector;

    

    public SearchDialog (JFrame parent, String title,ArrayList<Node> list_node, ArrayList<Link> list_link)
    {
        super(parent,title,true);
        this.setSize(220,150);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        this.list_node = list_node;
        this.list_link = list_link;
        
        DefaultComboBoxModel modelA = new DefaultComboBoxModel<>();
        DefaultComboBoxModel modelB = new DefaultComboBoxModel<>();
        for (Node node : list_node)
        {
            modelA.addElement(node);
            modelB.addElement(node);
        }
        
        panConnectedDialog  = new JPanel();
        panConnectedDialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel labelNodeA = new JLabel("Lieux 1 ");
        panConnectedDialog.add(labelNodeA,gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        nodeA = new JComboBox<Node>();
        nodeA.setModel(modelA);
        panConnectedDialog.add(nodeA,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel labelNodeB = new JLabel("Lieux 2 ");
        panConnectedDialog.add(labelNodeB,gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        nodeB = new JComboBox();
        nodeB.setModel(modelB);
        panConnectedDialog.add(nodeB,gbc);

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
