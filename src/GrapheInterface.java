
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
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

    Graphe mainGraphe;
    
    BorderLayout borderLayout;
    PanelInterface panelInterface;
    JPanel bottomBar;
    
    JMenuBar menubar;
    JMenu menuFile;
    JMenu menuDisplay;
    JMenu menuFind;
    JMenu menuPreferences;
    JMenu menuColor;
    JMenu menuColorNoeud;
    JMenu menuColorLink;
    JMenu menuPlaces;
    JMenu menuLink;
    
    JMenuItem btnColorNoeudCity;
    JMenuItem btnColorNoeudFun;
    JMenuItem btnColorNoeudFood;
    JMenuItem btnColorLinkHighway;
    JMenuItem btnColorLinkNationale;
    JMenuItem btnColorLinkDepartementale;
    JMenuItem btnConnected;
    JMenuItem btnCompare;
    JMenuItem btnFinder;
    JMenuItem btnOpen;
    JMenuItem btnRefresh;
    JCheckBox btnCity;
    JCheckBox btnFun;
    JCheckBox btnFood;
    JCheckBox btnHighway;
    JCheckBox btnNational;
    JCheckBox btnDepartemental;
    
    JLabel lblCity;
    JLabel lblFood;
    JLabel lblFun;
    JLabel lblDepartemental;
    JLabel lblNational;
    JLabel lblHighway;
    ArrayList<JLabel>  labelList;

    public GrapheInterface() 
    {

        initComponents();
    }

    private void initComponents() 
    {

        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        this.setTitle("GRAph-Map-Analysis");
        this.setPreferredSize(new Dimension(800, 800));
        this.setMinimumSize(new Dimension(800, 800));
        this.setIconImage(new ImageIcon(getClass().getResource("logo.png")).getImage());
        this.setLocationRelativeTo(null);
        borderLayout = new BorderLayout();
        this.setLayout(borderLayout);

        this.mainGraphe = new Graphe("graphData.csv");

        /*////////////////////////////////////////////////////////////
        ArrayList<Noeud> list_noeuds;;
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
        Lien AB = new Lien(3,'N',A,B);
        Lien BC = new Lien(6,'D',B,C);
        Lien CA = new Lien(2,'A',C,A);
        Lien AD = new Lien(11,'A',A,D);
        list_liens = new ArrayList<>();
        list_liens.add(AB);
        list_liens.add(BC);
        list_liens.add(CA);
        list_liens.add(AD);
        panelInterface = new panelInterface(getWidth(), getHeight(),list_noeuds,list_liens);
        ////////////////////////////////////////////////////////////*/
        labelList = new ArrayList<>();
        panelInterface = new PanelInterface(getWidth(), getHeight(),mainGraphe.getNoeuds(),mainGraphe.getLiens());
        getContentPane().add(panelInterface, BorderLayout.CENTER);
        
        menuFile = new JMenu("Menu");
        btnOpen = new JMenuItem("Ouvrir");
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });
        menuFile.add(btnOpen);

        menuDisplay = new JMenu("Affichage");
        menuPlaces = new JMenu("Lieux");
        menuLink = new JMenu("Liaisons");

        btnCity = new JCheckBox("Villes");
        btnCity.setSelected(true);
        btnCity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCityActionPerformed(evt);
            }
        });

        btnFun = new JCheckBox("Loisirs");
        btnFun.setSelected(true);
        btnFun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFunActionPerformed(evt);
            }
        });

        btnFood = new JCheckBox("Restaurants");
        btnFood.setSelected(true);
        btnFood.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFoodActionPerformed(evt);
            }
        });

        btnHighway = new JCheckBox("Autoroutes");
        btnHighway.setSelected(true);
        btnHighway.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHighwayActionPerformed(evt);
            }
        });

        btnNational = new JCheckBox("Nationales");
        btnNational.setSelected(true);
        btnNational.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNationalActionPerformed(evt);
            }
        });

        btnDepartemental = new JCheckBox("Départementales");
        btnDepartemental.setSelected(true);
        btnDepartemental.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepartementalActionPerformed(evt);
            }
        });

        menuPlaces.add(btnCity);
        menuPlaces.add(btnFun);
        menuPlaces.add(btnFood);
        menuLink.add(btnHighway);
        menuLink.add(btnNational);
        menuLink.add(btnDepartemental);
        menuDisplay.add(menuPlaces);
        menuDisplay.add(menuLink);


        btnRefresh = new JMenuItem("Réinitialiser");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        menuDisplay.add(btnRefresh);

        menuFind = new JMenu("Recherche");
        btnConnected = new JMenuItem("Connexion");
        btnConnected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectedActionPerformed(evt);
            }
        });
        menuFind.add(btnConnected);
        btnCompare = new JMenuItem("Comparer");
        btnCompare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompareActionPerformed(evt);
            }
        });
        menuFind.add(btnCompare);

        menuPreferences = new JMenu("Préférences");
        menuColor = new JMenu("Couleurs");
        menuColorNoeud = new JMenu("Noeuds");
        menuColorLink = new JMenu("Liens");

        btnColorNoeudCity = new JMenuItem("Villes");
        btnColorNoeudCity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColorNoeudCityActionPerformed(evt);
            }
        });
        btnColorNoeudFood = new JMenuItem("Restaurants");
        btnColorNoeudFood.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColorNoeudFoodActionPerformed(evt);
            }
        });
        btnColorNoeudFun = new JMenuItem("Loisirs");
        btnColorNoeudFun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColorNoeudFunActionPerformed(evt);
            }
        });
        menuColorNoeud.add(btnColorNoeudCity);
        menuColorNoeud.add(btnColorNoeudFood);
        menuColorNoeud.add(btnColorNoeudFun);
        menuColor.add(menuColorNoeud);

        btnColorLinkHighway = new JMenuItem("Autoroutes");
        btnColorLinkHighway.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColorLinkHighwayActionPerformed(evt);
            }
        });
        btnColorLinkNationale = new JMenuItem("Nationales");
        btnColorLinkNationale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColorLinkNationaleActionPerformed(evt);
            }
        });
        btnColorLinkDepartementale = new JMenuItem("Départementales");
        btnColorLinkDepartementale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColorLinkDepartementaleActionPerformed(evt);
            }
        }); 
        menuColorLink.add(btnColorLinkHighway);
        menuColorLink.add(btnColorLinkNationale);
        menuColorLink.add(btnColorLinkDepartementale);    
        menuColor.add(menuColorLink);
 
        menuPreferences.add(menuColor);

        menubar = new JMenuBar();
        menubar.add(menuFile);
        menubar.add(menuDisplay);
        menubar.add(menuFind);
        menubar.add(menuPreferences);

        bottomBar = new JPanel();
        bottomBar.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 5));
        lblCity = new JLabel("Ville : "+panelInterface.countNodeByType('V'));
        lblFood = new JLabel("Restaurant : "+panelInterface.countNodeByType('L'));
        lblFun = new JLabel("Loisir : "+panelInterface.countNodeByType('R'));
        lblDepartemental = new JLabel("Départementale : "+panelInterface.countLinkByType('D'));
        lblNational =   new JLabel("Nationale : "+panelInterface.countLinkByType('N'));
        lblHighway = new JLabel("Autoroute : "+panelInterface.countLinkByType('A'));
        
        
        bottomBar.add(lblCity);
        bottomBar.add(lblFood);
        bottomBar.add(lblFun);       
        bottomBar.add(lblDepartemental);    
        bottomBar.add(lblNational);       
        bottomBar.add(lblHighway);
        bottomBar.setBackground(Color.WHITE);

        getContentPane().add(bottomBar, BorderLayout.SOUTH);
        bottomBar.setVisible(true);
        
        labelList.add(lblCity);
        labelList.add(lblFood);
        labelList.add(lblFun);
        labelList.add(lblDepartemental);
        labelList.add(lblNational);
        labelList.add(lblHighway);
        panelInterface.setLabelList(labelList);

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                panelInterface.widthJFrame = getWidth();
                panelInterface.heightJFrame = getHeight();
                System.out.println("Resize");
            }
        });
        setJMenuBar(menubar);
        pack();
        setVisible(true);
        
    }

    private void btnOpenActionPerformed(ActionEvent evt) 
    {
        JFileChooser choice = new JFileChooser();
        int select=choice.showOpenDialog(this);
        if(select==JFileChooser.APPROVE_OPTION)
        {
            getContentPane().remove(panelInterface);
            mainGraphe = new Graphe(choice.getSelectedFile().getAbsolutePath());
            panelInterface = new PanelInterface(getWidth(), getHeight(),mainGraphe.getNoeuds(),mainGraphe.getLiens());
            panelInterface.setLabelList(this.labelList);     
            getContentPane().add(panelInterface, BorderLayout.CENTER);
            this.setVisible(false);
            this.setVisible(true);
        }
 
    }

    private void btnColorLinkDepartementaleActionPerformed(ActionEvent evt) 
    {
        panelInterface.mapPanelColor.replace("D",JColorChooser.showDialog(this, "Sélecteur de couleur", panelInterface.mapPanelColor.get("D")));
        panelInterface.repaint();
    }

    private void btnColorLinkNationaleActionPerformed(ActionEvent evt) 
    {
        panelInterface.mapPanelColor.replace("N",JColorChooser.showDialog(this, "Sélecteur de couleur", panelInterface.mapPanelColor.get("N")));
        panelInterface.repaint();
    }

    private void btnColorLinkHighwayActionPerformed(ActionEvent evt) 
    {
        panelInterface.mapPanelColor.replace("H",JColorChooser.showDialog(this, "Sélecteur de couleur", panelInterface.mapPanelColor.get("H")));
        panelInterface.repaint();
    }

    private void btnColorNoeudFunActionPerformed(ActionEvent evt) 
    {
        panelInterface.mapPanelColor.replace("FU",JColorChooser.showDialog(this, "Sélecteur de couleur", panelInterface.mapPanelColor.get("FU")));
        panelInterface.repaint();
    }

    private void btnColorNoeudFoodActionPerformed(ActionEvent evt) 
    {
        panelInterface.mapPanelColor.replace("FO",JColorChooser.showDialog(this, "Sélecteur de couleur", panelInterface.mapPanelColor.get("FO")));
        panelInterface.repaint();
    }

    private void btnColorNoeudCityActionPerformed(ActionEvent evt) 
    {
        panelInterface.mapPanelColor.replace("C",JColorChooser.showDialog(this, "Sélecteur de couleur", panelInterface.mapPanelColor.get("C")));
        panelInterface.repaint();
    }

	/*
    * Ajoute un évènement au bouton "btnCompare" qui permet de comparer 2 noeuds afin de savoir s'ils sont plus ou moins
    * grastronomiques, ouverts, culturels.
    * @param evt correspond à un ActionEvent
    */
    private void btnCompareActionPerformed(ActionEvent evt) {
        new CompareDialog(this, this.getTitle(),panelInterface.getListNoeudAffiches(),panelInterface.getListLiensAffiches(),mainGraphe);
    }

    /*
    *Ajoute un évènement au bouton "Connexion" qui permet de savoir si deux noeuds sont reliés entre eux.
    * @param evt correspond à un ActionEvent
    */
    private void btnConnectedActionPerformed(ActionEvent evt) {
        new ConnectedDialog(this, this.getTitle(),panelInterface.getListNoeudAffiches(),panelInterface.getListLiensAffiches());
    }

    /*
    * Ajoute un évènement au bouton "Rafraîchir" qui permet de revenir au stade initial du graphe
    * @param evt correcpond à un ActionEvent
    */
    private void btnRefreshActionPerformed(ActionEvent evt) {
        panelInterface.reset();
    }

    /*
    * Ajoute un évènement à la checkbox "Départementales" qui efface de l'interface tous les liens de type "D".
    * @param evt correcpond à un ActionEvent
    */
    private void btnDepartementalActionPerformed(ActionEvent evt) {
        panelInterface.hideOrDisplayLinkByType(btnDepartemental.isSelected(), 'D');  
    }
    
    /*
    * Ajoute un évènement à la checkbox "Nationales" qui efface de l'interface tous les liens de type "N".
    * @param evt correcpond à un ActionEvent
    */
    private void btnNationalActionPerformed(ActionEvent evt) {
        panelInterface.hideOrDisplayLinkByType(btnNational.isSelected(), 'N');
    }
    
    /*
    * Ajoute un évènement à la checkbox "Autoroutes" qui efface de l'interface tous les liens de type "A".
    * @param evt correcpond à un ActionEvent
    */
    private void btnHighwayActionPerformed(ActionEvent evt) {
        panelInterface.hideOrDisplayLinkByType(btnHighway.isSelected(), 'A');  
    }
    
    /*
    * Ajoute un évènement à la checkbox "Restaurants" qui efface de l'interface tous les liens de type "R".
    * @param evt correcpond à un ActionEvent
    */
    private void btnFoodActionPerformed(ActionEvent evt) {
        panelInterface.hideOrDisplayNodeByType(btnFood.isSelected(), 'R',this);     
    }

    /*
    * Ajoute un évènement à la checkbox "Loisirs" qui efface de l'interface tous les liens de type "L".
    * @param evt correcpond à un ActionEvent
    */
    private void btnFunActionPerformed(ActionEvent evt) {
        panelInterface.hideOrDisplayNodeByType(btnFun.isSelected(), 'L',this);  
    }

    /*
    * Ajoute un évènement à la checkbox "Villes" qui efface de l'interface tous les liens de type "V".
    * @param evt correcpond à un ActionEvent
    */
    private void btnCityActionPerformed(ActionEvent evt) {
        panelInterface.hideOrDisplayNodeByType(btnCity.isSelected(), 'V',this);     
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                System.out.println(info.getName());
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
