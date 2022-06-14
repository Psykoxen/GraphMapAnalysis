
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.Point;
/**
 *
 * @author Antoine
 * 
 */
public class PanelInterface extends JPanel{
    private Noeud SelectedNode = null;

    public int widthJFrame;
    public int heightJFrame;

    private final ArrayList<Noeud> list_noeuds;
    private final ArrayList<Lien> list_liens;
    private ArrayList<Noeud> list_noeuds_affiches;
    private ArrayList<Lien> list_liens_affiches;
    private ArrayList<Noeud> checkNode;
    private ArrayList<Lien> checkLink;
    
    public Map<String, Color> mapPanelColor; 

    Random random = new Random();

    JPopupMenu jMenu;
    JMenuItem btnOneNeighbour;
    JMenuItem btnTwoNeighbour;
    JMenuItem btnReset;
    Boolean oneNeighbourDisplay = false;
    Boolean twoNeighbourDisplay = false;
    ArrayList<JLabel> labelList;

    
    PanelInterface(int width,int height,ArrayList<Noeud> list_noeuds,ArrayList<Lien> list_liens)
    {
        this.widthJFrame = width;
        this.heightJFrame = height;
        this.list_liens = list_liens;
        this.list_noeuds = list_noeuds;

        this.list_liens_affiches = (ArrayList<Lien>) list_liens.clone();
        this.list_noeuds_affiches = new ArrayList<>();
       

        generatingNode();

        this.mapPanelColor = new HashMap<String, Color>();
        this.mapPanelColor.put("C",Color.GREEN);
        this.mapPanelColor.put("FU",Color.BLUE);
        this.mapPanelColor.put("FO",Color.RED);
        this.mapPanelColor.put("H",Color.BLACK);
        this.mapPanelColor.put("N",Color.BLACK);
        this.mapPanelColor.put("D",Color.BLACK);

        jMenu = new JPopupMenu();
        btnOneNeighbour = new JMenuItem();
        btnOneNeighbour.setText("1-voisins");
        btnOneNeighbour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOneNeigbourActionPerformed(evt);
            }
        });
        btnTwoNeighbour = new JMenuItem();
        btnTwoNeighbour.setText("2-voisins");
        btnTwoNeighbour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTwoNeigbourActionPerformed(evt);
            }
        });
        btnReset = new JMenuItem();
        btnReset.setText("Réinitialiser");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        }); 
        jMenu.add(btnReset);
        jMenu.add(btnOneNeighbour);
        jMenu.add(btnTwoNeighbour);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                    //selectNoeud(e.getX(),e.getY());
                    if (e.getButton() == MouseEvent.BUTTON3)
                    {
                        for (Noeud noeud:list_noeuds_affiches)
                        {
                        if (
                            (noeud.getX() <= e.getX() && e.getX() <= noeud.getX()+50)
                            &&
                            (noeud.getY() <= e.getY() && e.getY() <= noeud.getY()+50)
                        )
                        {
                            SelectedNode = noeud;
                            jMenu.show(e.getComponent(), e.getX(), e.getY());
                        }
                        }
                    }
                    else
                    {
                        for (Lien lien : list_liens_affiches)
                        {
                            if(checkOnTheLine(lien, e.getX(), e.getY()))
                            {
                                list_liens_affiches.clear();
                                list_liens_affiches.add(lien);
                                list_noeuds_affiches.clear();
                                list_noeuds_affiches.add(lien.getDepart());
                                list_noeuds_affiches.add(lien.getArrivee());
                                repaint();
                                break;
                            }
                        }
                        
                    }
            }
        });
        addMouseMotionListener(new MouseAdapter() 
        {
            @Override
            public void mouseDragged(MouseEvent e) {
                moveNode((int)e.getPoint().getX(),(int)e.getPoint().getY());   
            }
        });
        
    }
    
    /**
     * Donne des coordonnées à chaque noeud de la liste list_noeuds et les ajoute
     */
    public void generatingNode()
    {
        for (Noeud noeud:list_noeuds) 
        {
            generatingCoord(noeud);
            list_noeuds_affiches.add(noeud);
        }
    }

    /**
     * Génère des coordonnées aléatoires pour un noeud donné en paramètre
     * @param noeud correspond à un objet de type Noeud
     */
    public void generatingCoord(Noeud noeud)
    {
        int x = random.nextInt(20,this.widthJFrame - 70);
        int y = random.nextInt(20,this.heightJFrame - 170);
        noeud.setX(x);
        noeud.setY(y);
        for (int i = 0;i<500;i++)
        {
            if (checkNode(noeud) && !noeud.equals(list_noeuds.get(0)))
            {
                x = random.nextInt(20,this.widthJFrame - 70);
                y = random.nextInt(20,this.heightJFrame - 170);
                noeud.setX(x);
                noeud.setY(y);
            }
            else
            {
                break;
            }
        }
    }

    /**
     * Renvoie true si les noeud donné en paramètre est superposé à un autre noeud et false sinon
     * @param noeud correspond à un objet de type Noeud
     * @return un booleen
     */
    private boolean checkNode(Noeud noeud)
    {
        for (Noeud noeudCheck : list_noeuds)
        {
            if 
            (
                (
                (Point.distance((double)noeud.getX()+25,(double)noeud.getY()+25,(double)noeudCheck.getX()+25,(double)noeudCheck.getY()+25) <= 50)
                )
                &&
                (!noeud.equals(noeudCheck))
                &&
                (noeudCheck.getX() != 0 && noeudCheck.getY() != 0)
            )
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Méthode ajoutant un action performed pour le boutton "reset"
     * @param variable d'évenement evt
     */
    private void btnResetActionPerformed(ActionEvent evt) 
    {
        reset();
    }
    
    /**
     * Renvoie true si les coordonnées x,y sont sur le label du lien donné en paramètre
     * @param lien correspond à un objet de type Lien
     * @param x correspond à un entier
     * @param y correspond à un entier
     * @return un booleen
     */
    public boolean checkOnTheLine(Lien lien,int x, int y)
    {
        System.out.println("Xclick : "+x+" Yclick : "+y);
        System.out.println("X : "+lien.getLabel().getX()+" X++ : "+(lien.getLabel().getX()+(int)lien.getLabel().getSize().getWidth()));
        System.out.println("Y :"+lien.getLabel().getY());
        System.out.println("Y++ :"+(lien.getLabel().getY()+(int)lien.getLabel().getSize().getHeight()));
        if 
        (
            (lien.getLabel().getX() < x && (lien.getLabel().getX()+(int)lien.getLabel().getSize().getWidth()) > x)
            &&
            (lien.getLabel().getY() < y && (lien.getLabel().getY()+(int)lien.getLabel().getSize().getHeight()) > y)
        )
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Méthode ajoutant un action performed pour le boutton "1-voisins"
     * @param variable d'évenement evt
     */
    private void btnOneNeigbourActionPerformed(ActionEvent evt) 
    {
        HideOrDisplayOneNeighbour();
    }

    /**
     * Affiche ou camoufle les 1-voisins d'un noeud
     */
    private void HideOrDisplayOneNeighbour()
    {
        twoNeighbourDisplay = false;
        oneNeighbourDisplay = true;
        list_liens_affiches.clear();
        list_noeuds_affiches.clear();
        list_noeuds_affiches.add(SelectedNode);
        for (Lien lien : list_liens)
        {
            System.out.println(lien);
            if (lien.getDepart().equals(SelectedNode))
            {
                list_liens_affiches.add(lien);
                list_noeuds_affiches.add(lien.getArrivee());
                System.out.println("Ajout de arrivée "+lien.getArrivee());
            }
            else if (lien.getArrivee().equals(SelectedNode))
            {
                list_liens_affiches.add(lien);
                list_noeuds_affiches.add(lien.getDepart());
                System.out.println("Ajout de départ "+lien.getDepart());
            }
        }
        repaint();
    }

    /**
     * Méthode ajoutant un action performed pour le boutton "2-voisins"
     * @param variable d'évenement evt
     */
    private void btnTwoNeigbourActionPerformed(ActionEvent evt) 
    {
        HideOrDisplayTwoNeighbour();
    }

    /**
     * Affiche ou camoufle les 2-voisins d'un noeud
     */
    private void HideOrDisplayTwoNeighbour() 
    {
        twoNeighbourDisplay = true;
        oneNeighbourDisplay = false;
        list_liens_affiches.clear();
        list_noeuds_affiches.clear();
        list_noeuds_affiches.add(SelectedNode);
        for (Lien lien : list_liens)
        {
            if (lien.getDepart().equals(SelectedNode))
            {
                list_liens_affiches.add(lien);
                list_noeuds_affiches.add(lien.getArrivee());
                for (Lien lienSecond : list_liens)
                {
                    if 
                    (
                        (lien.getArrivee().equals(lienSecond.getDepart()))
                        &&
                        (!SelectedNode.equals(lienSecond.getArrivee())) 
                    )
                    {
                        list_liens_affiches.add(lienSecond);
                        list_noeuds_affiches.add(lienSecond.getArrivee());
                    }
                    else if 
                    (
                        (lien.getArrivee().equals(lienSecond.getArrivee()))
                        &&
                        (!SelectedNode.equals(lienSecond.getDepart())) 
                    )
                    {
                        list_liens_affiches.add(lienSecond);
                        list_noeuds_affiches.add(lienSecond.getDepart());
                    }
                }
            }
            else if (lien.getArrivee().equals(SelectedNode))
            {
                list_liens_affiches.add(lien);
                list_noeuds_affiches.add(lien.getDepart());
                for (Lien lienSecond : list_liens)
                {
                    if 
                    (
                        (lien.getDepart().equals(lienSecond.getDepart()))
                        &&
                        (!SelectedNode.equals(lienSecond.getArrivee())) 
                    )
                    {
                        list_liens_affiches.add(lienSecond);
                        list_noeuds_affiches.add(lienSecond.getArrivee());
                    }
                    else if 
                    (
                        (lien.getDepart().equals(lienSecond.getArrivee()))
                        &&
                        (!SelectedNode.equals(lienSecond.getDepart())) 
                    )
                    {
                        list_liens_affiches.add(lienSecond);
                        list_noeuds_affiches.add(lienSecond.getDepart());
                    }
                }
            }
        }
        repaint();
    }

    /**
     * Permet de bouger les noeuds indépendamment
     * @param x correspond à un entier
     * @param y correspond à un entier
     */
    private void moveNode(int x,int y)
    {
        for (Noeud noeud:list_noeuds_affiches)
                {
                    if (
                        (noeud.getX() <= x && x <= noeud.getX()+50)
                        &&
                        (noeud.getY() <= y && y <= noeud.getY()+50)
                        &&
                        (x>=20 && x<=this.widthJFrame-40 && y<=this.heightJFrame-125 && y>=20)
                        
                    )
                    {
                            noeud.setX(x-25);
                            noeud.setY(y-25);
                        break;
                    }
                }
                this.repaint();
    }
     
    /**
     * Affiche ou camoufle les liens selon leur type. le booléen "selected" indiquera s'ils doivent être affichés ou non, le char "type" indiquera quel type de lien doit l'être
     * @param selected correspond à un objet Boooleen
     * @param type correspond à un char
     */
    public void hideOrDisplayLinkByType(Boolean selected,char type)
    {
        boolean check;
            
                if (!selected)
                {
                    for (Lien lien : list_liens)
                    {
                        if (lien.getType() == type)
                        {
                            System.out.println("Type : "+lien.getType()+" || From "+lien.getDepart().getNom()+" to "+lien.getArrivee().getNom()+" --> TRUE");
                        
                            list_liens_affiches.remove(lien);
                        }
                        else
                        {
                            System.out.println("Type : "+lien.getType()+" || From "+lien.getDepart().getNom()+" to "+lien.getArrivee().getNom()+" --> FALSE");
                        
                        }
                    }
                }
                else
                {
                    for (Lien lien : list_liens)
                    {
                        if (lien.getType() == type)
                        {
                            check = false;
                            for (Noeud noeudA : list_noeuds_affiches)
                            {
                                if (noeudA.equals(lien.getArrivee()) || noeudA.equals(lien.getDepart()))
                                {
                                    for (Noeud noeudB : list_noeuds_affiches)
                                    {
                                        if ((noeudB.equals(lien.getArrivee()) || noeudB.equals(lien.getDepart())) && !noeudA.equals(noeudB) )
                                        {
                                            check = true;
                                            break;
                                        }
                                    }
                                }
                            }
                            if (check)
                            {
                                list_liens_affiches.add(lien);
                            }
                    }
                }
            

        }
        for (Lien lien : list_liens_affiches)
        {
            System.out.println("Type : "+lien.getType()+" || From "+lien.getDepart().getNom()+" to "+lien.getArrivee().getNom());
        }
        this.repaint();    
    }    
    
    /**
     * Affiche ou camoufle les noeuds selon leur type. le booléen "selected" indiquera s'ils doivent être affichés ou non, le char "type" indiquera quel type de noeud doit l'être
     * @param selected correspond à un objet Boolean
     * @param type correspond à un char
     * @param JFrame correspond à un objet GrapheInterface
     */
    public void hideOrDisplayNodeByType(Boolean selected,char type,GrapheInterface JFrame)
    {
        boolean check = false;
        if (SelectedNode == null)
        {
            
            for (int i = 0; i <  this.getListNoeud().size(); i++) {
                if (!selected) {
                    if ( this.getListNoeud().get(i).getType() == type) {
                        this.getListNoeudAffiches().remove(this.getListNoeud().get(i));
                        deleteLink(this.getListNoeud().get(i));
                    }
                } else {
                    if ( this.getListNoeud().get(i).getType() == type) {
                        for (int y = 0; y <  this.getListNoeudAffiches().size(); y++) {
                            if ( this.getListNoeud().get(i).getNom().equals( this.getListNoeudAffiches().get(y).getNom())) {
                                check = true;
                                break;
                            }
                        }
                        if (!check) {
                            this.getListNoeudAffiches().add( this.getListNoeud().get(i));
                            addLink( this.getListNoeud().get(i),JFrame);
                        }
                    }
                }
            }
        } else {
            if (!selected)
            {
                checkNode = (ArrayList<Noeud>) list_noeuds_affiches.clone();
                for (Noeud noeud : checkNode)
                {
                    if 
                    (
                        (!noeud.equals(SelectedNode))
                        &&
                        (noeud.getType() == type)
                    )
                    {
                        deleteLink(noeud);
                        list_noeuds_affiches.remove(noeud);
                    }
                    
                }
                deleteUnlinkedNode();
            }
            else
            {
                if (oneNeighbourDisplay)
                {
                    HideOrDisplayOneNeighbour(); 
                }
                else
                {
                    HideOrDisplayTwoNeighbour();
                }
            }
        }
        this.repaint();
    }

    /**
     * Supprime un noeud s'il n'a aucun voisin
     */
    private void deleteUnlinkedNode()
    {
        Boolean check;
        checkNode = (ArrayList<Noeud>) list_noeuds_affiches.clone();
        for (Noeud noeud : checkNode)
                        {
                            check = false; 
                            for (Lien lien : list_liens_affiches)
                            {
                                if 
                                (
                                    (lien.getDepart().equals(noeud))
                                    ||
                                    (lien.getArrivee().equals(noeud))
                                )
                                {
                                    check = true;
                                }
                            }
                            if (!check)
                            {
                                list_noeuds_affiches.remove(noeud);
                            }
                        }
    }
   
    /**
     * Ajoute un lien
     * @param addNoeud correspond à un objet Noeud
     * @param JFrame coorespond à un objet GrapheInterface
     */
    private void addLink(Noeud addNoeud, GrapheInterface JFrame) 
        {
        for (int y = 0; y <  this.getListNoeudAffiches().size(); y++) 
        {
            for (int z = 0; z < this.getListLiens().size(); z++) 
            {
                if (
                    (
                        ( this.getListNoeudAffiches().get(y).equals(this.getListLiens().get(z).getDepart()))
                        &&
                        (addNoeud.equals(this.getListLiens().get(z).getArrivee()))
                        ||
                        ( this.getListNoeudAffiches().get(y).equals(this.getListLiens().get(z).getArrivee()))
                        &&
                        (addNoeud.equals(this.getListLiens().get(z).getDepart()))
                    )
                   ) 
                {
                    if 
                    (
                        (JFrame.btnNational.isSelected() && this.getListLiens().get(z).getType() == 'N')
                        ||
                        (JFrame.btnDepartemental.isSelected() && this.getListLiens().get(z).getType() == 'D')
                        ||
                        (JFrame.btnHighway.isSelected() && this.getListLiens().get(z).getType() == 'A')
                    )
                    {
                        this.getListLiensAffiches().add(this.getListLiens().get(z));
                    }
                    
                }
            }
        }
    }

    /**
     * Supprime un lien s'il n'a comme voisin que lui-même
     * @param delNoeud correspond à un objet Noeud
     */
    private void deleteLink(Noeud delNoeud) 
    {
        for (int i = 0; i < this.getListLiens().size(); i++) {
                
            if (
                (this.getListLiens().get(i).getDepart().equals(delNoeud))
                ||
                (this.getListLiens().get(i).getArrivee().equals(delNoeud))
                ) 
                {
                this.getListLiensAffiches().remove(this.getListLiens().get(i));
                }
        }
    }    
    
    /**
     * Réinitialise le graphe
     */
    public void reset()
    {
        list_liens_affiches.clear();
        list_noeuds_affiches.clear();

        for (Noeud noeud : list_noeuds)
        {
            list_noeuds_affiches.add(noeud);
        }

        for (Lien lien : list_liens)
        {
            list_liens_affiches.add(lien);
        }

        this.repaint();
        SelectedNode = null;
        twoNeighbourDisplay = false;
        oneNeighbourDisplay = false;
    }
   
    /**
     * Affiche toutes les statistiques de noeuds et de liens du graphique
     */
    private void updateStat()
    {
        System.out.println("-- UPDATE --");
        for (JLabel label : labelList)
        {
            switch(label.getText().split(":")[0])
            {
                case "Ville ": label.setText("Ville : "+countNodeByType('V'));break;
                case "Restaurant ": label.setText("Restaurant : "+countNodeByType('R'));break;
                case "Loisir ": label.setText("Loisir : "+countNodeByType('L'));break;
                case "Départementale ": label.setText("Départementale : "+countLinkByType('D'));break;
                case "Nationale ": label.setText("Nationale : "+countLinkByType('N'));break;
                case "Autoroute ": label.setText("Autoroute : "+countLinkByType('A'));break;
            }
        }
    }

    /**
     *  Renvoie le nombre de liens d'un type donné en paramètre
     * @param type correspond à un char
     * @return un int
     */
    public int countLinkByType(char type) 
    {
        int count = 0;
        for (Lien link : list_liens_affiches)
        {
            if (link.getType() == type)
            {
                count++;
            }
        }
        return count;
    }

    /**
     *  Renvoie le nombre de noeuds d'un type donné en paramètre
     * @param type correspond à un char
     * @return un int
     */
    public int countNodeByType(char type) 
    {
        int count = 0;
        for (Noeud noeud : list_noeuds_affiches)
        {
            if (noeud.getType() == type)
            {
                count++;
            }
        }
        return count;
    }
 
    /**
     * Redéfinition de la méthode paintComponent
     */
    public void paintComponent(Graphics g) 
    {    
        this.removeAll();
        System.out.println(" -- REPAINT -- ");
        super.paintComponent(g);
        for (Lien lien:this.list_liens_affiches)
        {
            switch (lien.getType())
            {
                case 'A':
                {
                    g.setColor(mapPanelColor.get("H"));
                    lien.getLabel().setForeground(mapPanelColor.get("H"));
                    break;
                }
                case 'N':
                {
                    g.setColor(mapPanelColor.get("N"));
                    lien.getLabel().setForeground(mapPanelColor.get("N"));
                    break;
                }
                case 'D':
                {
                    g.setColor(mapPanelColor.get("D"));
                    lien.getLabel().setForeground(mapPanelColor.get("D"));
                    break;
                }
            }
            g.drawLine(
                lien.getDepart().getX()+25, 
                lien.getDepart().getY()+25,
                lien.getArrivee().getX()+25,
                lien.getArrivee().getY()+25
            );
            /* */
            lien.getLabel().setLocation(
                (lien.getDepart().getX() + lien.getArrivee().getX()+ 25) / 2,
                (lien.getDepart().getY() + lien.getArrivee().getY()+ 25) / 2
            );
            
            this.add(lien.getLabel());
        }
        for (Noeud noeud:list_noeuds_affiches)
        {
            switch (noeud.getType())
            {
                case 'V':
                {
                    g.setColor(mapPanelColor.get("C"));
                    break;
                }
                case 'R':
                {
                    g.setColor(mapPanelColor.get("FO"));
                    break;
                }
                case 'L':
                {
                    g.setColor(mapPanelColor.get("FU"));
                    break;
                }
            }
            g.fillOval(
                noeud.getX(),
                noeud.getY(),
                50,
                50
            );
            g.drawString(
                noeud.getNom(),
                noeud.getX() + 24,
                noeud.getY() + 60
            );
            g.setColor(Color.WHITE);
            Font f = new Font ("Sanserif",Font.PLAIN, 12);
                g.setFont (f);
            g.drawString(
                String.valueOf(noeud.getType()),
                noeud.getX() + 23,
                noeud.getY() + 28
            );
        }
        if (labelList != null)
        {
            updateStat();
        }
    }

    /**
     * Méthode renvoyant la liste des noeuds
     * @return Un objet de type Arraylist<Lien> contenant la liste des noeuds.
     */
    public ArrayList<Noeud> getListNoeud()
    {
        return this.list_noeuds;
    }

    /**
     * Méthode renvoyant la liste des liens.
     * @return Un objet de type Arraylist<Lien> contenant la liste des liens.
     */
    public ArrayList<Lien> getListLiens()
    {
        return this.list_liens;
    }

    /**
     * Méthode renvoyant la liste des noeuds affiches
     * @return Un objet de type Arraylist<Noeud> contenant la liste des noeuds affiches.
     */
    public ArrayList<Noeud> getListNoeudAffiches()
    {
        return list_noeuds_affiches;
    }
    
    /**
     * Méthode renvoyant la liste des liens affiches
     * @return Un objet de type Arraylist<Lien> contenant la liste des liens affiches.
     */
    public ArrayList<Lien> getListLiensAffiches()
    {
        return list_liens_affiches;
    }
    
    /**
     * Méthode modifiant le contenu de la liste des labels de la JFrame (Stats des noeuds)
     * @param Un objet de type ArrayList<JLabel> contenant une liste de JLabel.
     */
    public void setLabelList(ArrayList<JLabel> labelList)
    {
        this.labelList = labelList;
    }
}
