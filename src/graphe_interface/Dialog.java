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

public class Dialog extends JDialog{
    protected ArrayList<Noeud> list_noeuds;
    protected ArrayList<Lien> list_liens;
    private JPanel panConnectedDialog;
    private Boolean find;
    
    JButton check;
    JComboBox noeudA;
    JComboBox noeudB;
    JComboBox selector;
    JLabel labelselector;

    public Dialog (JFrame parent, String title,ArrayList<Noeud> list_noeuds, ArrayList<Lien> list_liens)
    {
        super(parent,title,true);
        this.setSize(350,200);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        this.list_noeuds = list_noeuds;
        this.list_liens = list_liens;
        
        panConnectedDialog  = new JPanel();

        JPanel p1 = new JPanel();
        p1.setBorder(new EmptyBorder(new Insets(10, 20, 10, 20)));
        p1.setLayout(new BoxLayout(p1, BoxLayout.LINE_AXIS));
        JLabel labelNoeudA = new JLabel("Lieux 1 ");
        noeudA = new JComboBox();

        for (Noeud noeud : list_noeuds)
        {
            noeudA.addItem(noeud);
        }
        p1.add(labelNoeudA);
        p1.add(noeudA);

        JPanel p2 = new JPanel();
        p2.setBorder(new EmptyBorder(new Insets(10, 20, 10, 20)));
        p2.setLayout(new BoxLayout(p2, BoxLayout.LINE_AXIS));
        JLabel labelNoeudB = new JLabel("Lieux 2 ");
        noeudB = new JComboBox();
        for (Noeud noeud : list_noeuds)
        {
            noeudB.addItem(noeud);
        }
        p2.add(labelNoeudB);
        p2.add(noeudB);

        JPanel p3 = new JPanel();
        p3.setBorder(new EmptyBorder(new Insets(10, 20, 10, 20)));
        p3.setLayout(new BoxLayout(p3, BoxLayout.LINE_AXIS));
        labelselector = new JLabel();
        selector = new JComboBox();
        


        p3.add(labelselector);
        p3.add(selector);

        check = new JButton("Vérifier");
        check.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                action();
            }});

        p3.add(check);
        panConnectedDialog.setLayout(new BoxLayout(panConnectedDialog, BoxLayout.PAGE_AXIS));
        panConnectedDialog.add(p1);
        panConnectedDialog.add(p2);
        panConnectedDialog.add(p3);
        this.add(panConnectedDialog, BorderLayout.CENTER);
        
    }

    protected void action() {

    }
}
