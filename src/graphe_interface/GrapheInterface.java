package graphe_interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import graphe_backend.*;
import java.util.*;

/**
 *
 * @author Antoine
 */
public class GrapheInterface extends javax.swing.JFrame {

    Graphe mainGraphe = new Graphe();

    public class drawpanel extends JPanel {
        private int width;
        private int height;
        // private final ArrayList<Noeud> list_noeuds;
        // private final ArrayList<Lien> list_liens;
        private final ArrayList<Noeud> list_noeuds = mainGraphe.getNoeuds();
        private final ArrayList<Lien> list_liens = mainGraphe.getLiens();
        private ArrayList<Lien> list_liens_affiches = (ArrayList<Lien>) list_liens.clone();
        private ArrayList<Noeud> list_noeuds_affiches = (ArrayList<Noeud>) list_noeuds.clone();
        Random random = new Random();

        drawpanel(int width, int height) {
            this.width = width;
            this.height = height;
            /*
             * list_noeuds = new ArrayList<>();
             * Noeud A = new Noeud('V', "A");
             * list_noeuds.add(A);
             * Noeud B = new Noeud('R', "B");
             * list_noeuds.add(B);
             * Noeud C = new Noeud('L', "C");
             * list_noeuds.add(C);
             * 
             * list_liens = new ArrayList<>();
             * Lien AB = new Lien(0,'L',A,B);
             * list_liens.add(AB);
             * Lien BC = new Lien(0,'L',B,C);
             * list_liens.add(BC);
             * Lien CA = new Lien(0,'L',C,A);
             * list_liens.add(CA);
             */

        }

        public void paintComponent(Graphics g) {
            for (int i = 0; i < list_noeuds_affiches.size(); i++) {
                int x = random.nextInt(this.width - 120);
                int y = random.nextInt(this.height - 120);
                list_noeuds.get(i).setX(x);
                list_noeuds.get(i).setY(y);
            }

            for (int i = 0; i < list_liens_affiches.size(); i++) {
                g.drawLine(list_liens_affiches.get(i).getDepart().getX() + 25,
                        list_liens_affiches.get(i).getDepart().getY() + 25,
                        list_liens_affiches.get(i).getArrivee().getX() + 25,
                        list_liens_affiches.get(i).getArrivee().getY() + 25);
                g.drawString(
                        list_liens_affiches.get(i).getDepart().getNom() + " -> " + list_liens_affiches.get(i).getType()
                                + "," + list_liens_affiches.get(i).getDistance() + " -> "
                                + list_liens_affiches.get(i).getArrivee().getNom(),
                        (list_liens_affiches.get(i).getDepart().getX() + list_liens_affiches.get(i).getArrivee().getX()
                                + 25) / 2,
                        (list_liens_affiches.get(i).getDepart().getY() + list_liens_affiches.get(i).getArrivee().getY()
                                + 25) / 2);
            }

            for (int i = 0; i < list_noeuds_affiches.size(); i++) {
                switch (list_noeuds_affiches.get(i).getType()) {
                    case 'V': {
                        g.setColor(Color.GREEN);
                        break;
                    }
                    case 'R': {
                        g.setColor(Color.RED);
                        break;
                    }
                    case 'L': {
                        g.setColor(Color.BLUE);
                        break;
                    }
                }
                g.fillOval(list_noeuds_affiches.get(i).getX(), list_noeuds_affiches.get(i).getY(), 50, 50);
                g.drawString(list_noeuds_affiches.get(i).getNom(), list_noeuds_affiches.get(i).getX() + 25,
                        list_noeuds_affiches.get(i).getY() + 60);
            }
            lblDepartemental.setText("Départementales : "+countLinkByType('D'));
            lblNational.setText("Nationales : "+countLinkByType('N'));
            lblHighway.setText("Autoroutes : "+countLinkByType('A'));
            lblFood.setText("Restaurants : "+countNodeByType('R')); 
            lblFun.setText("Loisirs : "+countNodeByType('L'));
            lblCity.setText("Villes : "+countNodeByType('V'));
            lblNational.setText("Nationales : "+countLinkByType('N'));
        }

    }

    drawpanel a;
    JMenuBar menubar;
    JMenu File;
    JMenu Display;
    JMenuItem Open;
    BorderLayout borderLayout;
    JPanel bottomBar;
    JMenu Places;
    JMenu Link;
    JCheckBox City;
    JCheckBox Fun;
    JCheckBox Food;
    JCheckBox Highway;
    JCheckBox National;
    JCheckBox Departmental;
    JLabel lblCity;
    JLabel lblFood;
    JLabel lblFun;
    JLabel lblDepartemental;
    JLabel lblNational;
    JLabel lblHighway;

    public GrapheInterface() {

        initComponents();

    }

    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        setTitle("Graph-Map");
        setPreferredSize(new Dimension(800, 800));
        setMinimumSize(new Dimension(800, 800));
        // setResizable(false);
        borderLayout = new BorderLayout();
        setLayout(borderLayout);

        a = new drawpanel(getWidth(), getHeight());
        getContentPane().add(a, borderLayout.CENTER);
        File = new JMenu("Menu");
        Open = new JMenuItem("Ouvrir");
        File.add(Open);

        Display = new JMenu("Affichage");
        Places = new JMenu("Lieux");
        Link = new JMenu("Liaisons");

        City = new JCheckBox("Villes");
        City.setSelected(true);
        City.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCityActionPerformed(evt);
            }
        });

        Fun = new JCheckBox("Loisirs");
        Fun.setSelected(true);
        Fun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFunActionPerformed(evt);
            }
        });

        Food = new JCheckBox("Restaurants");
        Food.setSelected(true);
        Food.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFoodActionPerformed(evt);
            }
        });

        Highway = new JCheckBox("Autoroutes");
        Highway.setSelected(true);
        Highway.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHighwayActionPerformed(evt);
            }
        });

        National = new JCheckBox("Nationales");
        National.setSelected(true);
        National.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNationalActionPerformed(evt);
            }
        });

        Departmental = new JCheckBox("Départementales");
        Departmental.setSelected(true);
        Departmental.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepartmentalActionPerformed(evt);
            }
        });

        Places.add(City);
        Places.add(Fun);
        Places.add(Food);
        Link.add(Highway);
        Link.add(National);
        Link.add(Departmental);
        Display.add(Places);
        Display.add(Link);

        menubar = new JMenuBar();
        menubar.add(File);
        menubar.add(Display);

        bottomBar = new JPanel();
        bottomBar.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 5));
        lblCity = new JLabel("Villes : "+countNodeByType('V'));
        lblFood = new JLabel("Restaurants : "+countNodeByType('R')); 
        lblFun = new JLabel("Loisirs : "+countNodeByType('L'));
        lblDepartemental = new JLabel("Departementales : "+countLinkByType('D'));
        lblNational = new JLabel("Nationales : "+countLinkByType('N'));
        lblHighway = new JLabel("Autoroutes : "+countLinkByType('A'));
        bottomBar.add(lblCity);
        bottomBar.add(lblFood);
        bottomBar.add(lblFun);
        bottomBar.add(lblDepartemental);
        bottomBar.add(lblNational);
        bottomBar.add(lblHighway);
        bottomBar.setBackground(Color.WHITE);

        getContentPane().add(bottomBar, borderLayout.SOUTH);
        bottomBar.setVisible(true);

        setJMenuBar(menubar);
        pack();
        setVisible(true);

    }

    protected void btnDepartmentalActionPerformed(ActionEvent evt) {
        hideOrDisplayLink(Departmental.isSelected(), 'D');    
    }

    protected void btnNationalActionPerformed(ActionEvent evt) {
        hideOrDisplayLink(National.isSelected(), 'N');  
    }

    protected void btnHighwayActionPerformed(ActionEvent evt) {
        hideOrDisplayLink(Highway.isSelected(), 'A');  
    }

    protected void btnFoodActionPerformed(ActionEvent evt) {
        hideOrDisplayNode(Food.isSelected(), 'R');       
    }

    protected void btnFunActionPerformed(ActionEvent evt) {
        hideOrDisplayNode(Fun.isSelected(), 'L');    
    }

    protected void btnCityActionPerformed(ActionEvent evt) {
        hideOrDisplayNode(City.isSelected(), 'V');      
    }
    private void refreshInfoBottomBar()
    {
        
    }
    private void hideOrDisplayLink(Boolean selected,char type)
    {
        boolean check = false;
        for (int i = 0; i < a.list_liens.size(); i++) 
        {
            if (!selected)
            {
                if (a.list_liens.get(i).getType() == type)
                {
                    a.list_liens_affiches.remove(a.list_liens.get(i));
                }
            }
            else
            {
                if (a.list_liens.get(i).getType() == type)
                {
                    for (int j=0;j<a.list_noeuds_affiches.size();j++)
                    {
                        if (a.list_liens.get(i).getDepart().equals(a.list_noeuds_affiches.get(j))) 
                        {
                            for (int k=0;k<a.list_noeuds_affiches.size();k++)
                            {
                                if (a.list_liens.get(i).getArrivee().equals(a.list_noeuds_affiches.get(k))) 
                                {
                                    a.list_liens_affiches.add(a.list_liens.get(i));
                                }
                            }
                        }
                    }
                }
            }
        }
        repaint();    
    }
    
    private void hideOrDisplayNode(Boolean selected,char type)
    {
        boolean check = false;
        for (int i = 0; i < a.list_noeuds.size(); i++) {
            if (!selected) {
                if (a.list_noeuds.get(i).getType() == type) {
                    a.list_noeuds_affiches.remove(a.list_noeuds.get(i));
                    deleteLink(a.list_noeuds.get(i));
                }
            } else {
                if (a.list_noeuds.get(i).getType() == type) {
                    for (int y = 0; y < a.list_noeuds_affiches.size(); y++) {
                        if (a.list_noeuds.get(i).getNom().equals(a.list_noeuds_affiches.get(y).getNom())) {
                            check = true;
                            break;
                        }
                    }
                    if (!check) {
                        a.list_noeuds_affiches.add(a.list_noeuds.get(i));
                        addLink(a.list_noeuds.get(i));
                    }
                }
            }
        }
        repaint();
    }
    
    private void addLink(Noeud addNoeud) 
    {
        for (int y = 0; y < a.list_noeuds_affiches.size(); y++) 
        {
            for (int z = 0; z < a.list_liens.size(); z++) 
            {
                if (
                    (
                        (a.list_noeuds_affiches.get(y).equals(a.list_liens.get(z).getDepart()))
                        &&
                        (addNoeud.equals(a.list_liens.get(z).getArrivee()))
                        ||
                        (a.list_noeuds_affiches.get(y).equals(a.list_liens.get(z).getArrivee()))
                        &&
                        (addNoeud.equals(a.list_liens.get(z).getDepart()))
                    )
                   ) 
                {
                    a.list_liens_affiches.add(a.list_liens.get(z));
                }
            }
        }
    }

    private void deleteLink(Noeud delNoeud) {
        for (int i = 0; i < a.list_liens.size(); i++) {
            
            if (
                (a.list_liens.get(i).getDepart().equals(delNoeud))
                ||
                (a.list_liens.get(i).getArrivee().equals(delNoeud))
               ) 
            {
                a.list_liens_affiches.remove(a.list_liens.get(i));
            }
        }
    }

    private boolean checkSuperpose() {
        boolean check = true;
        for (int i = 0; i < a.list_noeuds_affiches.size(); i++) {
            for (int j = i + 1; j < a.list_noeuds_affiches.size(); j++) {
                if ((a.list_noeuds_affiches.get(i).getX() < a.list_noeuds_affiches.get(j).getX())
                        &&
                        (a.list_noeuds_affiches.get(i).getX() + 20 > a.list_noeuds_affiches.get(j).getX())
                        ||
                        (a.list_noeuds_affiches.get(i).getY() < a.list_noeuds_affiches.get(j).getY())
                                &&
                                (a.list_noeuds_affiches.get(i).getY() + 20 > a.list_noeuds_affiches.get(j).getY())) {
                    check = false;
                }
            }
        }
        return check;
    }

    private int countLinkByType(char type) {
        int count = 0;
        for (Lien link : a.list_liens_affiches)
        {
            if (link.getType() == type)
            {
                count++;
            }
        }
        return count;
    }

    private int countNodeByType(char type) {
        int count = 0;
        for (Noeud noeud : a.list_noeuds_affiches)
        {
            if (noeud.getType() == type)
            {
                count++;
            }
        }
        return count;
    }

    private void go() {
        while (!checkSuperpose()) {

            for (int i = 0; i < a.list_noeuds_affiches.size(); i++) {
                for (int j = i + 1; j < a.list_noeuds_affiches.size(); j++) {

                    if ((a.list_noeuds_affiches.get(i).getX() < a.list_noeuds_affiches.get(j).getX())
                            &&
                            (a.list_noeuds_affiches.get(i).getX() + 20 > a.list_noeuds_affiches.get(j).getX())
                            ||
                            (a.list_noeuds_affiches.get(i).getY() < a.list_noeuds_affiches.get(j).getY())
                                    &&
                                    (a.list_noeuds_affiches.get(i).getY() + 20 > a.list_noeuds_affiches.get(j)
                                            .getY())) {
                        if ((a.list_noeuds_affiches.get(i).getX() < a.list_noeuds_affiches.get(j).getX())
                                &&
                                (a.list_noeuds_affiches.get(i).getX() + 20 > a.list_noeuds_affiches.get(j).getX())
                                &&
                                (a.list_noeuds_affiches.get(i).getY() < a.list_noeuds_affiches.get(j).getY())
                                &&
                                (a.list_noeuds_affiches.get(i).getY() + 20 > a.list_noeuds_affiches.get(j).getY())) {
                            if (a.list_noeuds_affiches.get(i).getX() < 200) {
                                a.list_noeuds_affiches.get(i).setX(a.list_noeuds_affiches.get(i).getX() + 10);
                            } else {
                                a.list_noeuds_affiches.get(i).setX(a.list_noeuds_affiches.get(i).getX() - 10);
                            }

                            if (a.list_noeuds_affiches.get(i).getY() < 200) {
                                a.list_noeuds_affiches.get(i).setY(a.list_noeuds_affiches.get(i).getY() + 10);
                            } else {
                                a.list_noeuds_affiches.get(i).setY(a.list_noeuds_affiches.get(i).getY() - 10);
                            }

                        } else if ((a.list_noeuds_affiches.get(i).getX() < a.list_noeuds_affiches.get(j).getX())
                                &&
                                (a.list_noeuds_affiches.get(i).getX() + 20 > a.list_noeuds_affiches.get(j).getX())) {
                            if (a.list_noeuds_affiches.get(i).getX() < 200) {
                                a.list_noeuds_affiches.get(i).setX(a.list_noeuds_affiches.get(i).getX() + 10);
                            } else {
                                a.list_noeuds_affiches.get(i).setX(a.list_noeuds_affiches.get(i).getX() - 10);
                            }
                        } else {
                            if (a.list_noeuds_affiches.get(i).getY() < 200) {
                                a.list_noeuds_affiches.get(i).setY(a.list_noeuds_affiches.get(i).getY() + 10);
                            } else {
                                a.list_noeuds_affiches.get(i).setY(a.list_noeuds_affiches.get(i).getY() - 10);
                            }
                        }
                    }
                }

            }

        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GrapheInterface.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GrapheInterface().setVisible(true);
            }
        });
    }
}
