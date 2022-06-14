
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
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

    Graphe mainGraphe;
    PanelInterface PanelInterface;
    JMenuBar menubar;
    JMenu File;
    JMenu Display;
    JMenu Find;
    JMenu Preferences;
    JMenu color;
    JMenu colorNoeud;
    JMenu colorLink;
    JMenuItem colorNoeudCity;
    JMenuItem colorNoeudFun;
    JMenuItem colorNoeudFood;
    JMenuItem colorLinkHighway;
    JMenuItem colorLinkNationale;
    JMenuItem colorLinkDepartementale;
    JMenuItem Connected;
    JMenuItem Compare;
    JMenuItem Finder;
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
    JCheckBox Departemental;
    JLabel lblCity;
    JLabel lblFood;
    JLabel lblFun;
    JLabel lblDepartemental;
    JLabel lblNational;
    JLabel lblHighway;
    ArrayList<JLabel>  labelList;
    

    public GrapheInterface() {

        initComponents();
    }

    private void initComponents() {

        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        this.setTitle("GRAph-Map-Analysis");
        this.setPreferredSize(new Dimension(800, 800));
        this.setMinimumSize(new Dimension(800, 800));
        this.setIconImage(new ImageIcon(getClass().getResource("logo.png")).getImage());
        this.setLocationRelativeTo(null);
        borderLayout = new BorderLayout();
        this.setLayout(borderLayout);

       this.mainGraphe = new Graphe("graphData.csv");

        /////////////////////////////////////////////////////////////
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
        PanelInterface = new PanelInterface(getWidth(), getHeight(),list_noeuds,list_liens);
        ////////////////////////////////////////////////////////////*/
       labelList = new ArrayList<>();
        PanelInterface = new PanelInterface(getWidth(), getHeight(),mainGraphe.getNoeuds(),mainGraphe.getLiens());
        getContentPane().add(PanelInterface, BorderLayout.CENTER);
        
        File = new JMenu("Menu");
        Open = new JMenuItem("Ouvrir");
        Open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });
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

        Departemental = new JCheckBox("Départementales");
        Departemental.setSelected(true);
        Departemental.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepartementalActionPerformed(evt);
            }
        });

        Places.add(City);
        Places.add(Fun);
        Places.add(Food);
        Link.add(Highway);
        Link.add(National);
        Link.add(Departemental);
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
        Finder = new JMenuItem("Recherche");
        Finder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinderActionPerformed(evt);
            }
        });

        Preferences = new JMenu("Préférences");
        color = new JMenu("Couleurs");
        colorNoeud = new JMenu("Noeuds");
        colorLink = new JMenu("Liens");

        colorNoeudCity = new JMenuItem("Villes");
        colorNoeudCity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColorNoeudCityActionPerformed(evt);
            }
        });
        colorNoeudFood = new JMenuItem("Restaurants");
        colorNoeudFood.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColorNoeudFoodActionPerformed(evt);
            }
        });
        colorNoeudFun = new JMenuItem("Loisirs");
        colorNoeudFun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColorNoeudFunActionPerformed(evt);
            }
        });
        colorNoeud.add(colorNoeudCity);
        colorNoeud.add(colorNoeudFood);
        colorNoeud.add(colorNoeudFun);
        color.add(colorNoeud);

        colorLinkHighway = new JMenuItem("Autoroutes");
        colorLinkHighway.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColorLinkHighwayActionPerformed(evt);
            }
        });
        colorLinkNationale = new JMenuItem("Nationales");
        colorLinkNationale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColorLinkNationaleActionPerformed(evt);
            }
        });
        colorLinkDepartementale = new JMenuItem("Départementales");
        colorLinkDepartementale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColorLinkDepartementaleActionPerformed(evt);
            }
        }); 
        colorLink.add(colorLinkHighway);
        colorLink.add(colorLinkNationale);
        colorLink.add(colorLinkDepartementale);    
        color.add(colorLink);
 
        Preferences.add(color);

        menubar = new JMenuBar();
        menubar.add(File);
        menubar.add(Display);
        menubar.add(Find);
        menubar.add(Preferences);

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

        getContentPane().add(bottomBar, BorderLayout.SOUTH);
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

    protected void btnOpenActionPerformed(ActionEvent evt) 
    {
        JFileChooser choice = new JFileChooser();
        int select=choice.showOpenDialog(this);
        if(select==JFileChooser.APPROVE_OPTION)
        {
            getContentPane().remove(PanelInterface);
            mainGraphe = new Graphe(choice.getSelectedFile().getAbsolutePath());
            PanelInterface = new PanelInterface(getWidth(), getHeight(),mainGraphe.getNoeuds(),mainGraphe.getLiens());
            PanelInterface.updateLabelList(this.labelList);     
            getContentPane().add(PanelInterface, BorderLayout.CENTER);
            this.setVisible(false);
            this.setVisible(true);
        }
 
    }

    protected void btnFinderActionPerformed(ActionEvent evt) 
    {

    }

    protected void btnColorLinkDepartementaleActionPerformed(ActionEvent evt) 
    {
        PanelInterface.mapPanelColor.replace("D",JColorChooser.showDialog(this, "Sélecteur de couleur", PanelInterface.mapPanelColor.get("D")));
        PanelInterface.repaint();
    }

    protected void btnColorLinkNationaleActionPerformed(ActionEvent evt) 
    {
        PanelInterface.mapPanelColor.replace("N",JColorChooser.showDialog(this, "Sélecteur de couleur", PanelInterface.mapPanelColor.get("N")));
        PanelInterface.repaint();
    }

    protected void btnColorLinkHighwayActionPerformed(ActionEvent evt) 
    {
        PanelInterface.mapPanelColor.replace("H",JColorChooser.showDialog(this, "Sélecteur de couleur", PanelInterface.mapPanelColor.get("H")));
        PanelInterface.repaint();
    }

    protected void btnColorNoeudFunActionPerformed(ActionEvent evt) 
    {
        PanelInterface.mapPanelColor.replace("FU",JColorChooser.showDialog(this, "Sélecteur de couleur", PanelInterface.mapPanelColor.get("FU")));
        PanelInterface.repaint();
    }

    protected void btnColorNoeudFoodActionPerformed(ActionEvent evt) 
    {
        PanelInterface.mapPanelColor.replace("FO",JColorChooser.showDialog(this, "Sélecteur de couleur", PanelInterface.mapPanelColor.get("FO")));
        PanelInterface.repaint();
    }

    protected void btnColorNoeudCityActionPerformed(ActionEvent evt) 
    {
        PanelInterface.mapPanelColor.replace("C",JColorChooser.showDialog(this, "Sélecteur de couleur", PanelInterface.mapPanelColor.get("C")));
        PanelInterface.repaint();
    }

	/*
    * Ajoute un évènement au bouton "Compare" qui permet de comparer 2 noeuds afin de savoir s'ils sont plus ou moins
    * grastronomiques, ouverts, culturels.
    * @param evt correspond à un ActionEvent
    */
    protected void btnCompareActionPerformed(ActionEvent evt) {
        new CompareDialog(this, this.getTitle(),PanelInterface.getListNoeudAffiches(),PanelInterface.getListLiensAffiches(),mainGraphe);
    }

    /*
    *Ajoute un évènement au bouton "Connexion" qui permet de savoir si deux noeuds sont reliés entre eux.
    * @param evt correspond à un ActionEvent
    */
    protected void btnConnectedActionPerformed(ActionEvent evt) {
        new ConnectedDialog(this, this.getTitle(),PanelInterface.getListNoeudAffiches(),PanelInterface.getListLiensAffiches());
    }

    /*
    * Ajoute un évènement au bouton "Rafraîchir" qui permet de revenir au stade initial du graphe
    * @param evt correcpond à un ActionEvent
    */
    protected void btnRefreshActionPerformed(ActionEvent evt) {
        PanelInterface.reset();
    }

    /*
    * Ajoute un évènement à la checkbox "Départementales" qui efface de l'interface tous les liens de type "D".
    * @param evt correcpond à un ActionEvent
    */
    protected void btnDepartementalActionPerformed(ActionEvent evt) {
        PanelInterface.hideOrDisplayLinkByType(Departemental.isSelected(), 'D');  


    }
    
    /*
    * Ajoute un évènement à la checkbox "Nationales" qui efface de l'interface tous les liens de type "N".
    * @param evt correcpond à un ActionEvent
    */
    protected void btnNationalActionPerformed(ActionEvent evt) {
        PanelInterface.hideOrDisplayLinkByType(National.isSelected(), 'N');
    }
    
    /*
    * Ajoute un évènement à la checkbox "Autoroutes" qui efface de l'interface tous les liens de type "A".
    * @param evt correcpond à un ActionEvent
    */
    protected void btnHighwayActionPerformed(ActionEvent evt) {
        PanelInterface.hideOrDisplayLinkByType(Highway.isSelected(), 'A');  
    }
    
    /*
    * Ajoute un évènement à la checkbox "Restaurants" qui efface de l'interface tous les liens de type "R".
    * @param evt correcpond à un ActionEvent
    */
    protected void btnFoodActionPerformed(ActionEvent evt) {
        PanelInterface.hideOrDisplayNodeByType(Food.isSelected(), 'R',this);     
    }

    /*
    * Ajoute un évènement à la checkbox "Loisirs" qui efface de l'interface tous les liens de type "L".
    * @param evt correcpond à un ActionEvent
    */
    protected void btnFunActionPerformed(ActionEvent evt) {
        PanelInterface.hideOrDisplayNodeByType(Fun.isSelected(), 'L',this);  
    }

    /*
    * Ajoute un évènement à la checkbox "Villes" qui efface de l'interface tous les liens de type "V".
    * @param evt correcpond à un ActionEvent
    */
    protected void btnCityActionPerformed(ActionEvent evt) {
        PanelInterface.hideOrDisplayNodeByType(City.isSelected(), 'V',this);     
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
