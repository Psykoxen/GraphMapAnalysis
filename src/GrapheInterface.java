
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

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

    Graphe mainGraphe = null;
    
    BorderLayout borderLayout;
    PanelInterface panelInterface;
    JPanel bottomBar;
    
    JMenuBar menubar;
    JMenu menuFile;
    JMenu menuDisplay;
    JMenu menuFind;
    JMenu menuPreferences;
    JMenu menuColor;
    JMenu menuColorNode;
    JMenu menuColorLink;
    JMenu menuPlaces;
    JMenu menuLink;
    JMenu menuAdd;

    JMenuItem btnAddNode;
    JMenuItem btnAddLink;
    JMenuItem btnColorNodeCity;
    JMenuItem btnColorNodeFun;
    JMenuItem btnColorNodeFood;
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

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                panelInterface.widthJFrame = getWidth();
                panelInterface.heightJFrame = getHeight();
                if (!new Dimension(getWidth(),getHeight()).equals(getPreferredSize()))
                {
                    for (Node node : panelInterface.getListNodeAffiches())
                    {
                        panelInterface.generatingCoord(node);
                    }
                }
            }
        });

        this.mainGraphe = new Graphe("graphData.csv");
        labelList = new ArrayList<>();
        panelInterface = new PanelInterface(getWidth(), getHeight(),mainGraphe.getNode(),mainGraphe.getLink());
        getContentPane().add(panelInterface, BorderLayout.CENTER);
        
        menuFile = new JMenu("Menu");
        btnOpen = new JMenuItem("Ouvrir");
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });
        menuFile.add(btnOpen);


        menuAdd = new JMenu("Ajout");
        btnAddNode = new JMenuItem("Noeud");
        btnAddNode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNodeActionPerformed(evt);
            }
        });

        menuAdd.add(btnAddNode);
        btnAddLink = new JMenuItem("Lien");
        btnAddLink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddLinkActionPerformed(evt);
            }
        });
        menuAdd.add(btnAddLink);

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
        menuColorNode = new JMenu("Noeuds");
        menuColorLink = new JMenu("Liens");

        btnColorNodeCity = new JMenuItem("Villes");
        btnColorNodeCity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColorNodeCityActionPerformed(evt);
            }
        });
        btnColorNodeFood = new JMenuItem("Restaurants");
        btnColorNodeFood.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColorNodeFoodActionPerformed(evt);
            }
        });
        btnColorNodeFun = new JMenuItem("Loisirs");
        btnColorNodeFun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColorNodeFunActionPerformed(evt);
            }
        });
        menuColorNode.add(btnColorNodeCity);
        menuColorNode.add(btnColorNodeFood);
        menuColorNode.add(btnColorNodeFun);
        menuColor.add(menuColorNode);

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
        menubar.add(menuAdd);
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

        setJMenuBar(menubar);
        pack();
        setVisible(true);

    }

    /**
     * Crée une action qui ouvre une une fenêtre afin d'ajouter un link entre 2 nodes
     * @param evt correspond à un objet de type ActionEvent
     */
    private void btnAddLinkActionPerformed(ActionEvent evt) 
    {
        new LinkDialog(this, this.getTitle(),panelInterface.getListNode(), panelInterface.getListNodeAffiches(),panelInterface.getListLink(),panelInterface.getListLinkAffiches());
        this.repaint();
    }

    /**
     * Crée une action qui ouvre une une fenêtre afin d'ajouter un node entre 2 nodes
     * @param evt correspond à un objet de type ActionEvent
     */
    private void btnAddNodeActionPerformed(ActionEvent evt) 
    {
        new NodeDialog(this, this.getTitle(),panelInterface.getListNode(), panelInterface.getListNodeAffiches());
        this.repaint();
    }
    
    /**
     * Crée une action qui ouvre une fenêtre permettant de chercher un fichier dans l'ordinateur et de charger le ficher
     * dans le graphe. 
     * @param evt correspond à un objet de type ActionEvent
     */
    private void btnOpenActionPerformed(ActionEvent evt) 
    {
        JFileChooser choice = new JFileChooser();
        int select=choice.showOpenDialog(this);
        if(select==JFileChooser.APPROVE_OPTION)
        {
            getContentPane().remove(panelInterface);
            mainGraphe = new Graphe(choice.getSelectedFile().getAbsolutePath());
            panelInterface = new PanelInterface(getWidth(), getHeight(),mainGraphe.getNode(),mainGraphe.getLink());
            panelInterface.setLabelList(this.labelList);     
            getContentPane().add(panelInterface, BorderLayout.CENTER);
            this.setVisible(false);
            this.setVisible(true);
        }
 
    }

    /**
     * Crée une action qui ouvre une fenêtre d'un panel de couleurs afin de remplacer la couleurs des link de type "D" par la couleur souhaitée
     * @param evt correspond à un objet ActionEvent
     */
    private void btnColorLinkDepartementaleActionPerformed(ActionEvent evt) 
    {
        panelInterface.mapPanelColor.replace("D",JColorChooser.showDialog(this, "Sélecteur de couleur", panelInterface.mapPanelColor.get("D")));
        panelInterface.repaint();
    }

    /**
     * Crée une action qui ouvre une fenêtre d'un panel de couleurs afin de remplacer la couleurs des link de type "N" par la couleur souhaitée
     * @param evt correspond à un objet ActionEvent
     */
    private void btnColorLinkNationaleActionPerformed(ActionEvent evt) 
    {
        panelInterface.mapPanelColor.replace("N",JColorChooser.showDialog(this, "Sélecteur de couleur", panelInterface.mapPanelColor.get("N")));
        panelInterface.repaint();
    }

    /**
     * Crée une action qui ouvre une fenêtre d'un panel de couleurs afin de remplacer la couleurs des link de type "H" par la couleur souhaitée
     * @param evt correspond à un objet ActionEvent
     */
    private void btnColorLinkHighwayActionPerformed(ActionEvent evt) 
    {
        panelInterface.mapPanelColor.replace("H",JColorChooser.showDialog(this, "Sélecteur de couleur", panelInterface.mapPanelColor.get("H")));
        panelInterface.repaint();
    }

    /**
     * Crée une action qui ouvre une fenêtre d'un panel de couleurs afin de remplacer la couleurs des nodes de type "FU" par la couleur souhaitée
     * @param evt correspond à un objet ActionEvent
     */
    private void btnColorNodeFunActionPerformed(ActionEvent evt) 
    {
        panelInterface.mapPanelColor.replace("FU",JColorChooser.showDialog(this, "Sélecteur de couleur", panelInterface.mapPanelColor.get("FU")));
        panelInterface.repaint();
    }

    /**
     * Crée une action qui ouvre une fenêtre d'un panel de couleurs afin de remplacer la couleurs des nodes de type "FO" par la couleur souhaitée
     * @param evt correspond à un objet ActionEvent
     */
    private void btnColorNodeFoodActionPerformed(ActionEvent evt) 
    {
        panelInterface.mapPanelColor.replace("FO",JColorChooser.showDialog(this, "Sélecteur de couleur", panelInterface.mapPanelColor.get("FO")));
        panelInterface.repaint();
    }

    /**
     * Crée une action qui ouvre une fenêtre d'un panel de couleurs afin de remplacer la couleurs des nodes de type "C" par la couleur souhaitée
     * @param evt correspond à un objet ActionEvent
     */
    private void btnColorNodeCityActionPerformed(ActionEvent evt) 
    {
        panelInterface.mapPanelColor.replace("C",JColorChooser.showDialog(this, "Sélecteur de couleur", panelInterface.mapPanelColor.get("C")));
        panelInterface.repaint();
    }

	/**
     * Ajoute un évènement au bouton "btnCompare" qui permet de comparer 2 nodes afin de savoir s'ils sont plus ou moins
     * grastronomiques, ouverts, culturels.
     * @param evt correspond à un ActionEvent
     */
    private void btnCompareActionPerformed(ActionEvent evt) {
        new CompareDialog(this, this.getTitle(),panelInterface.getListNodeAffiches(),panelInterface.getListLinkAffiches(),mainGraphe);
    }

    /**
     *Ajoute un évènement au bouton "Connexion" qui permet de savoir si deux nodes sont reliés entre eux.
     * @param evt correspond à un ActionEvent
     */
    private void btnConnectedActionPerformed(ActionEvent evt) {
        new ConnectedDialog(this, this.getTitle(),panelInterface.getListNodeAffiches(),panelInterface.getListLinkAffiches());
    }

    /**
     * Ajoute un évènement au bouton "Rafraîchir" qui permet de revenir au stade initial du graphe
     * @param evt correcpond à un ActionEvent
     */
    private void btnRefreshActionPerformed(ActionEvent evt) {
        panelInterface.reset();
    }

    /**
     * Ajoute un évènement à la checkbox "Départementales" qui efface de l'interface tous les link de type "D".
     * @param evt correcpond à un ActionEvent
     */
    private void btnDepartementalActionPerformed(ActionEvent evt) {
        panelInterface.hideOrDisplayLinkByType(btnDepartemental.isSelected(), 'D');  
    }
    
    /**
     * Ajoute un évènement à la checkbox "Nationales" qui efface de l'interface tous les link de type "N".
     * @param evt correcpond à un ActionEvent
     */
    private void btnNationalActionPerformed(ActionEvent evt) {
        panelInterface.hideOrDisplayLinkByType(btnNational.isSelected(), 'N');
    }
    
    /**
     * Ajoute un évènement à la checkbox "Autoroutes" qui efface de l'interface tous les link de type "A".
     * @param evt correcpond à un ActionEvent
     */
    private void btnHighwayActionPerformed(ActionEvent evt) {
        panelInterface.hideOrDisplayLinkByType(btnHighway.isSelected(), 'A');  
    }
    
    /**
     * Ajoute un évènement à la checkbox "Restaurants" qui efface de l'interface tous les link de type "R".
     * @param evt correcpond à un ActionEvent
     */
    private void btnFoodActionPerformed(ActionEvent evt) {
        panelInterface.hideOrDisplayNodeByType(btnFood.isSelected(), 'R',this);     
    }

    /**
     * Ajoute un évènement à la checkbox "Loisirs" qui efface de l'interface tous les link de type "L".
     * @param evt correcpond à un ActionEvent
     */
    private void btnFunActionPerformed(ActionEvent evt) {
        panelInterface.hideOrDisplayNodeByType(btnFun.isSelected(), 'L',this);  
    }

    /**
     * Ajoute un évènement à la checkbox "Villes" qui efface de l'interface tous les link de type "V".
     * @param evt correcpond à un ActionEvent
     */
    private void btnCityActionPerformed(ActionEvent evt) {
        panelInterface.hideOrDisplayNodeByType(btnCity.isSelected(), 'V',this);     
    }

    
    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
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
