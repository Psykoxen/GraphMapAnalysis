
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
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
/**
 *
 * @author Antoine
 * 
 */
public class PanelInterface extends JPanel{
    private Node SelectedNode = null;

    public int widthJFrame;
    public int heightJFrame;

    private final ArrayList<Node> list_node;
    private final ArrayList<Link> list_link;
    private ArrayList<Node> list_node_affiches;
    private ArrayList<Link> list_link_affiches;
    private ArrayList<Node> checkNode;
    private ArrayList<Link> checkLink;
    
    public Map<String, Color> mapPanelColor; 

    Random random = new Random();

    JPopupMenu jMenu;
    JMenuItem btnOneNeighbour;
    JMenuItem btnTwoNeighbour;
    JMenuItem btnReset;
    Boolean oneNeighbourDisplay = false;
    Boolean twoNeighbourDisplay = false;
    ArrayList<JLabel> labelList;

    
    PanelInterface(int width,int height,ArrayList<Node> list_node,ArrayList<Link> list_link)
    {
        this.widthJFrame = width;
        this.heightJFrame = height;
        this.list_link = list_link;
        this.list_node = list_node;

        this.list_link_affiches = (ArrayList<Link>) list_link.clone();
        this.list_node_affiches = new ArrayList<>();
       

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
                    if (e.getButton() == MouseEvent.BUTTON3)
                    {
                        for (Node noeud:list_node_affiches)
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
                        for (Link link : list_link_affiches)
                        {
                            if(checkOnTheLine(link, e.getX(), e.getY()))
                            {
                                list_link_affiches.clear();
                                list_link_affiches.add(link);
                                list_node_affiches.clear();
                                list_node_affiches.add(link.getDepart());
                                list_node_affiches.add(link.getArrivee());
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
    
    /*
    * Méthode générant les coordonées aléatoire d'un noeud.
    */
    public void generatingNode()
    {
        for (Node noeud:list_node) 
        {
            generatingCoord(noeud);
            list_node_affiches.add(noeud);
        }
    }

    public void generatingCoord(Node noeud)
    {
        int x = random.nextInt(20,this.widthJFrame - 70);
            int y = random.nextInt(20,this.heightJFrame - 170);
            noeud.setX(x);
            noeud.setY(y);
            for (int i = 0;i<500;i++)
            {
                if (checkNode(noeud) && !noeud.equals(list_node.get(0)))
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

    private boolean checkNode(Node noeud)
    {
        for (Node noeudCheck : list_node)
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

    /*
    * Méthode ajoutant un action performed pour le boutton "reset"
    * @param variable d'évenement evt
    */
    private void btnResetActionPerformed(ActionEvent evt) 
    {
        reset();
    }
    
    public boolean checkOnTheLine(Link link,int x, int y)
    {

        if 
        (
            (link.getLabel().getX() < x && (link.getLabel().getX()+(int)link.getLabel().getSize().getWidth()) > x)
            &&
            (link.getLabel().getY() < y && (link.getLabel().getY()+(int)link.getLabel().getSize().getHeight()) > y)
        )
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /*
    * Méthode ajoutant un action performed pour le boutton "1-voisins"
    * @param variable d'évenement evt
    */
    private void btnOneNeigbourActionPerformed(ActionEvent evt) 
    {
        HideOrDisplayOneNeighbour();
    }

    private void HideOrDisplayOneNeighbour()
    {
        twoNeighbourDisplay = false;
        oneNeighbourDisplay = true;
        list_link_affiches.clear();
        list_node_affiches.clear();
        list_node_affiches.add(SelectedNode);
        for (Link link : list_link)
        {

            if (link.getDepart().equals(SelectedNode))
            {
                list_link_affiches.add(link);
                list_node_affiches.add(link.getArrivee());

            }
            else if (link.getArrivee().equals(SelectedNode))
            {
                list_link_affiches.add(link);
                list_node_affiches.add(link.getDepart());

            }
        }
        repaint();
    }

    /*
    * Méthode ajoutant un action performed pour le boutton "2-voisins"
    * @param variable d'évenement evt
    */
    private void btnTwoNeigbourActionPerformed(ActionEvent evt) 
    {
        HideOrDisplayTwoNeighbour();
    }

    private void HideOrDisplayTwoNeighbour() 
    {
        twoNeighbourDisplay = true;
        oneNeighbourDisplay = false;
        list_link_affiches.clear();
        list_node_affiches.clear();
        list_node_affiches.add(SelectedNode);
        for (Link link : list_link)
        {
            if (link.getDepart().equals(SelectedNode))
            {
                list_link_affiches.add(link);
                list_node_affiches.add(link.getArrivee());
                for (Link linkSecond : list_link)
                {
                    if 
                    (
                        (link.getArrivee().equals(linkSecond.getDepart()))
                        &&
                        (!SelectedNode.equals(linkSecond.getArrivee())) 
                    )
                    {
                        list_link_affiches.add(linkSecond);
                        list_node_affiches.add(linkSecond.getArrivee());
                    }
                    else if 
                    (
                        (link.getArrivee().equals(linkSecond.getArrivee()))
                        &&
                        (!SelectedNode.equals(linkSecond.getDepart())) 
                    )
                    {
                        list_link_affiches.add(linkSecond);
                        list_node_affiches.add(linkSecond.getDepart());
                    }
                }
            }
            else if (link.getArrivee().equals(SelectedNode))
            {
                list_link_affiches.add(link);
                list_node_affiches.add(link.getDepart());
                for (Link linkSecond : list_link)
                {
                    if 
                    (
                        (link.getDepart().equals(linkSecond.getDepart()))
                        &&
                        (!SelectedNode.equals(linkSecond.getArrivee())) 
                    )
                    {
                        list_link_affiches.add(linkSecond);
                        list_node_affiches.add(linkSecond.getArrivee());
                    }
                    else if 
                    (
                        (link.getDepart().equals(linkSecond.getArrivee()))
                        &&
                        (!SelectedNode.equals(linkSecond.getDepart())) 
                    )
                    {
                        list_link_affiches.add(linkSecond);
                        list_node_affiches.add(linkSecond.getDepart());
                    }
                }
            }
        }
        repaint();
    }

    private void moveNode(int x,int y)
    {
        for (Node noeud:list_node_affiches)
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
     
    public void hideOrDisplayLinkByType(Boolean selected,char type)
    {
        boolean check;
            
                if (!selected)
                {
                    for (Link link : list_link)
                    {
                        if (link.getType() == type)
                        {
                            list_link_affiches.remove(link);
                        }

                    }
                }
                else
                {
                    for (Link link : list_link)
                    {
                        if (link.getType() == type)
                        {
                            check = false;
                            for (Node noeudA : list_node_affiches)
                            {
                                if (noeudA.equals(link.getArrivee()) || noeudA.equals(link.getDepart()))
                                {
                                    for (Node noeudB : list_node_affiches)
                                    {
                                        if ((noeudB.equals(link.getArrivee()) || noeudB.equals(link.getDepart())) && !noeudA.equals(noeudB) )
                                        {
                                            check = true;
                                            break;
                                        }
                                    }
                                }
                            }
                            if (check)
                            {
                                list_link_affiches.add(link);
                            }
                    }
                }
            

        }
        this.repaint();    
    }    
    
    public void hideOrDisplayNodeByType(Boolean selected,char type,GrapheInterface JFrame)
    {
        boolean check = false;
        if (SelectedNode == null)
        {
            
            for (int i = 0; i <  this.getListNode().size(); i++) {
                if (!selected) {
                    if ( this.getListNode().get(i).getType() == type) {
                        this.getListNodeAffiches().remove(this.getListNode().get(i));
                        deleteLink(this.getListNode().get(i));
                    }
                } else {
                    if ( this.getListNode().get(i).getType() == type) {
                        for (int y = 0; y <  this.getListNodeAffiches().size(); y++) {
                            if ( this.getListNode().get(i).getNom().equals( this.getListNodeAffiches().get(y).getNom())) {
                                check = true;
                                break;
                            }
                        }
                        if (!check) {
                            this.getListNodeAffiches().add( this.getListNode().get(i));
                            addLink( this.getListNode().get(i),JFrame);
                        }
                    }
                }
            }
        } else {
            if (!selected)
            {
                checkNode = (ArrayList<Node>) list_node_affiches.clone();
                for (Node noeud : checkNode)
                {
                    if 
                    (
                        (!noeud.equals(SelectedNode))
                        &&
                        (noeud.getType() == type)
                    )
                    {
                        deleteLink(noeud);
                        list_node_affiches.remove(noeud);
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

    private void deleteUnlinkedNode()
    {
        Boolean check;
        checkNode = (ArrayList<Node>) list_node_affiches.clone();
        for (Node noeud : checkNode)
                        {
                            check = false; 
                            for (Link link : list_link_affiches)
                            {
                                if 
                                (
                                    (link.getDepart().equals(noeud))
                                    ||
                                    (link.getArrivee().equals(noeud))
                                )
                                {
                                    check = true;
                                }
                            }
                            if (!check)
                            {
                                list_node_affiches.remove(noeud);
                            }
                        }
    }
   
    private void addLink(Node addNode, GrapheInterface JFrame) 
        {
        for (int y = 0; y <  this.getListNodeAffiches().size(); y++) 
        {
            for (int z = 0; z < this.getListLink().size(); z++) 
            {
                if (
                    (
                        ( this.getListNodeAffiches().get(y).equals(this.getListLink().get(z).getDepart()))
                        &&
                        (addNode.equals(this.getListLink().get(z).getArrivee()))
                        ||
                        ( this.getListNodeAffiches().get(y).equals(this.getListLink().get(z).getArrivee()))
                        &&
                        (addNode.equals(this.getListLink().get(z).getDepart()))
                    )
                   ) 
                {
                    if 
                    (
                        (JFrame.btnNational.isSelected() && this.getListLink().get(z).getType() == 'N')
                        ||
                        (JFrame.btnDepartemental.isSelected() && this.getListLink().get(z).getType() == 'D')
                        ||
                        (JFrame.btnHighway.isSelected() && this.getListLink().get(z).getType() == 'A')
                    )
                    {
                        this.getListLinkAffiches().add(this.getListLink().get(z));
                    }
                    
                }
            }
        }
        }

    private void deleteLink(Node delNode) 
    {
        for (int i = 0; i < this.getListLink().size(); i++) {
                
            if (
                (this.getListLink().get(i).getDepart().equals(delNode))
                ||
                (this.getListLink().get(i).getArrivee().equals(delNode))
                ) 
                {
                this.getListLinkAffiches().remove(this.getListLink().get(i));
                }
            }
        }    
    
    public void reset()
    {
        list_link_affiches.clear();
        list_node_affiches.clear();

        for (Node noeud : list_node)
        {
            list_node_affiches.add(noeud);
        }

        for (Link link : list_link)
        {
            list_link_affiches.add(link);
        }

        this.repaint();
        SelectedNode = null;
        twoNeighbourDisplay = false;
        oneNeighbourDisplay = false;
    }
   
    private void updateStat()
    {
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

    public int countLinkByType(char type) 
    {
        int count = 0;
        for (Link link : list_link_affiches)
        {
            if (link.getType() == type)
            {
                count++;
            }
        }
        return count;
    }

    public int countNodeByType(char type) 
    {
        int count = 0;
        for (Node noeud : list_node_affiches)
        {
            if (noeud.getType() == type)
            {
                count++;
            }
        }
        return count;
    }
 
    public void paintComponent(Graphics g) 
    {    
        this.removeAll();
        super.paintComponent(g);
        for (Link link:this.list_link_affiches)
        {
            switch (link.getType())
            {
                case 'A':
                {
                    g.setColor(mapPanelColor.get("H"));
                    link.getLabel().setForeground(mapPanelColor.get("H"));
                    break;
                }
                case 'N':
                {
                    g.setColor(mapPanelColor.get("N"));
                    link.getLabel().setForeground(mapPanelColor.get("N"));
                    break;
                }
                case 'D':
                {
                    g.setColor(mapPanelColor.get("D"));
                    link.getLabel().setForeground(mapPanelColor.get("D"));
                    break;
                }
            }
            g.drawLine(
                link.getDepart().getX()+25, 
                link.getDepart().getY()+25,
                link.getArrivee().getX()+25,
                link.getArrivee().getY()+25
            );
            /* */
            link.getLabel().setLocation(
                (link.getDepart().getX() + link.getArrivee().getX()+ 25) / 2,
                (link.getDepart().getY() + link.getArrivee().getY()+ 25) / 2
            );
            
            this.add(link.getLabel());
            /*g.drawString(
                link.getType()+","+link.getDistance(),
                (link.getDepart().getX() + link.getArrivee().getX()+ 25) / 2,
                (link.getDepart().getY() + link.getArrivee().getY()+ 25) / 2
            );*/
        }
        for (Node noeud:list_node_affiches)
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

    /*
    * Méthode renvoyant la liste des node
    * @return Un objet de type Arraylist<Link> contenant la liste des node.
    */
    public ArrayList<Node> getListNode()
    {
        return this.list_node;
    }

    /*
    * Méthode renvoyant la liste des link.
    * @return Un objet de type Arraylist<Link> contenant la liste des link.
    */
    public ArrayList<Link> getListLink()
    {
        return this.list_link;
    }

    /*
    * Méthode renvoyant la liste des node affiches
    * @return Un objet de type Arraylist<Node> contenant la liste des node affiches.
    */
    public ArrayList<Node> getListNodeAffiches()
    {
        return list_node_affiches;
    }
    
    /*
    * Méthode renvoyant la liste des link affiches
    * @return Un objet de type Arraylist<Link> contenant la liste des link affiches.
    */
    public ArrayList<Link> getListLinkAffiches()
    {
        return list_link_affiches;
    }
    
    /*
    * Méthode modifiant le contenu de la liste des labels de la JFrame (Stats des node)
    * @param Un objet de type ArrayList<JLabel> contenant une liste de JLabel.
    */
    public void setLabelList(ArrayList<JLabel> labelList)
    {
        this.labelList = labelList;
    }
}
