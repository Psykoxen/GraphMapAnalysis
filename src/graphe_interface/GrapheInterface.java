import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;

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
    JMenuItem Compare;
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
    JCheckBox btnDepartemental;
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
        this.setIconImage(new ImageIcon(getClass().getResource("logo.png")).getImage());
        borderLayout = new BorderLayout();
        setLayout(borderLayout);

       

        /*////////////////////////////////////////////////////////////
        ArrayList<Noeud> list_noeuds;
        ArrayList<Lien> list_liens;
        Noeud A = new Noeud('V', "A");
        Noeud B = new Noeud('R', "B");
        //Noeud C = new Noeud('L', "C");
        //Noeud D = new Noeud('L', "D");
        list_noeuds = new ArrayList<>();
        list_noeuds.add(A);
        list_noeuds.add(B);
        //list_noeuds.add(C);
        //list_noeuds.add(D);
        Lien AB = new Lien(0,'N',A,B);
        //Lien BC = new Lien(0,'D',B,C);
        //Lien CA = new Lien(0,'A',C,A);
        //Lien AD = new Lien(0,'A',A,D);
        list_liens = new ArrayList<>();
        list_liens.add(AB);
        //list_liens.add(BC);
        //list_liens.add(CA);
        //list_liens.add(AD);
        PanelInterface = new PanelInterface(getWidth(), getHeight(),list_noeuds,list_liens);
        ////////////////////////////////////////////////////////////*/
        ArrayList<JLabel> labelList = new ArrayList<>();
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

        btnDepartemental = new JCheckBox("Départementales");
        btnDepartemental.setSelected(true);
        btnDepartemental.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbtnDepartementalActionPerformed(evt);
            }
        });

        Places.add(City);
        Places.add(Fun);
        Places.add(Food);
        Link.add(Highway);
        Link.add(National);
        Link.add(btnDepartemental);
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
        Connected = new JMenuItem("Connexion");
        Connected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectedActionPerformed(evt);
            }
        });
        Find.add(Connected);
        Compare = new JMenuItem("Comparer");
        Compare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompareActionPerformed(evt);
            }
        });
        Find.add(Compare);


        menubar = new JMenuBar();
        menubar.add(File);
        menubar.add(Display);
        menubar.add(Find);

        bottomBar = new JPanel();
        bottomBar.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 5));
        lblCity = new JLabel("Ville : "+PanelInterface.countNodeByType('V'));
        lblFood = new JLabel("Restaurant : "+PanelInterface.countNodeByType('L'));
        lblFun = new JLabel("Loisir : "+PanelInterface.countNodeByType('R'));
        lblDepartemental = new JLabel("Départementale : "+PanelInterface.countLinkByType('D'));
        lblNational =   new JLabel("Nationale : "+PanelInterface.countLinkByType('N'));
        lblHighway = new JLabel("Autoroute : "+PanelInterface.countLinkByType('A'));
        
        
        bottomBar.add(lblCity);
        
        bottomBar.add(lblFood);
        
        bottomBar.add(lblFun);
        
        bottomBar.add(lblDepartemental);
        
        bottomBar.add(lblNational);
        
        bottomBar.add(lblHighway);
        bottomBar.setBackground(Color.WHITE);

        getContentPane().add(bottomBar, borderLayout.SOUTH);
        bottomBar.setVisible(true);
        
        labelList.add(lblCity);
        labelList.add(lblFood);
        labelList.add(lblFun);
        labelList.add(lblDepartemental);
        labelList.add(lblNational);
        labelList.add(lblHighway);
        PanelInterface.updateLabelList(labelList);

        setJMenuBar(menubar);
        pack();
        setVisible(true);
        
    }

    protected void btnCompareActionPerformed(ActionEvent evt) {
        new CompareDialog(this, this.getTitle(),PanelInterface.getListNoeudAffiches(),PanelInterface.getListLiensAffiches(),mainGraphe);
    }

    protected void btnConnectedActionPerformed(ActionEvent evt) {
        new ConnectedDialog(this, this.getTitle(),PanelInterface.getListNoeudAffiches(),PanelInterface.getListLiensAffiches());
    }

    protected void btnRefreshActionPerformed(ActionEvent evt) {
        PanelInterface.reset();
    }

    protected void btnbtnDepartementalActionPerformed(ActionEvent evt) {
        PanelInterface.hideOrDisplayLinkByType(btnDepartemental.isSelected(), 'D');  

    }

    protected void btnNationalActionPerformed(ActionEvent evt) {
        PanelInterface.hideOrDisplayLinkByType(National.isSelected(), 'N');
    }

    protected void btnHighwayActionPerformed(ActionEvent evt) {
        PanelInterface.hideOrDisplayLinkByType(Highway.isSelected(), 'A');  
    }

    protected void btnFoodActionPerformed(ActionEvent evt) {
        PanelInterface.hideOrDisplayNodeByType(Food.isSelected(), 'R',this);     
    }

    protected void btnFunActionPerformed(ActionEvent evt) {
        PanelInterface.hideOrDisplayNodeByType(Fun.isSelected(), 'L',this);  
    }

    protected void btnCityActionPerformed(ActionEvent evt) {
        PanelInterface.hideOrDisplayNodeByType(City.isSelected(), 'V',this);     
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
