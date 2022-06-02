package graphe_interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;

import graphe_backend.Graphe;
import graphe_backend.Lien;
import graphe_backend.Noeud;

/**
 *
 * @author Antoine
 * 
 */
public class GrapheInterface extends javax.swing.JFrame {

    Graphe mainGraphe = new Graphe();

    PanelInterface PanelInterface;
    JMenuBar menubar;
    JMenu File;
    JMenu Display;
    JMenu Find;
    JMenuItem Connected;
    JMenuItem Open;
    JMenuItem Refresh;
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

        /*////////////////////////////////////////////////////////////
        ArrayList<Noeud> list_noeuds;
        ArrayList<Lien> list_liens;
        Noeud A = new Noeud('V', "A");
        Noeud B = new Noeud('R', "B");
        Noeud C = new Noeud('L', "C");
        Noeud D = new Noeud('L', "D");
        list_noeuds = new ArrayList<>();
        list_noeuds.add(A);
        list_noeuds.add(B);
        list_noeuds.add(C);
        list_noeuds.add(D);
        Lien AB = new Lien(0,'N',A,B);
        Lien BC = new Lien(0,'D',B,C);
        Lien CA = new Lien(0,'A',C,A);
        Lien AD = new Lien(0,'A',A,D);
        list_liens = new ArrayList<>();
        list_liens.add(AB);
        list_liens.add(BC);
        list_liens.add(CA);
        list_liens.add(AD);
        PanelInterface = new PanelInterface(getWidth(), getHeight(),list_noeuds,list_liens);
        ////////////////////////////////////////////////////////////*/

        PanelInterface = new PanelInterface(getWidth(), getHeight(),mainGraphe.getNoeuds(),mainGraphe.getLiens());
        getContentPane().add(PanelInterface, BorderLayout.CENTER);
        
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


        Refresh = new JMenuItem("Réinitialiser");
        Refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        Display.add(Refresh);

        Find = new JMenu("Recherche");
        Connected = new JMenuItem("Connexion..");
        Connected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectedActionPerformed(evt);
            }
        });
        Find.add(Connected);



        menubar = new JMenuBar();
        menubar.add(File);
        menubar.add(Display);
        menubar.add(Find);

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

    protected void btnConnectedActionPerformed(ActionEvent evt) {
        ConnectedDialog dialog = new ConnectedDialog(this, this.getTitle(),PanelInterface.getListNoeudAffiches(),PanelInterface.getListLiensAffiches());
    }

    protected void btnRefreshActionPerformed(ActionEvent evt) {
        PanelInterface.reset();
        updateCountLabel();
    }

    protected void btnDepartmentalActionPerformed(ActionEvent evt) {
        PanelInterface.hideOrDisplayLinkByType(Departmental.isSelected(), 'D');  
        updateCountLabel();  
    }

    protected void btnNationalActionPerformed(ActionEvent evt) {
        PanelInterface.hideOrDisplayLinkByType(National.isSelected(), 'N');
        updateCountLabel();  
    }

    protected void btnHighwayActionPerformed(ActionEvent evt) {
        PanelInterface.hideOrDisplayLinkByType(Highway.isSelected(), 'A');  
        updateCountLabel();
    }

    protected void btnFoodActionPerformed(ActionEvent evt) {
        PanelInterface.hideOrDisplayNodeByType(Food.isSelected(), 'R',this);     
        updateCountLabel();  
    }

    protected void btnFunActionPerformed(ActionEvent evt) {
        PanelInterface.hideOrDisplayNodeByType(Fun.isSelected(), 'L',this);  
        updateCountLabel();  
    }

    protected void btnCityActionPerformed(ActionEvent evt) {
        PanelInterface.hideOrDisplayNodeByType(City.isSelected(), 'V',this);
        updateCountLabel();      
    }

    private void updateCountLabel()
    {
        lblCity.setText("Villes : "+countNodeByType('V'));
        lblFood.setText("Restaurants : "+countNodeByType('R')); 
        lblFun.setText("Loisirs : "+countNodeByType('L'));
        lblDepartemental.setText("Departementales : "+countLinkByType('D'));
        lblNational.setText("Nationales : "+countLinkByType('N'));
        lblHighway.setText("Autoroutes : "+countLinkByType('A'));   
    }
    

    private int countLinkByType(char type) {
        int count = 0;
        for (Lien link : PanelInterface.getListLiensAffiches())
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
        for (Noeud noeud : PanelInterface.getListNoeudAffiches())
        {
            if (noeud.getType() == type)
            {
                count++;
            }
        }
        return count;
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
