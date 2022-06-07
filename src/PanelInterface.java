
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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
    Noeud SelectedNode = null;

    private int widthJFrame;
    private int heightJFrame;

    private final ArrayList<Noeud> list_noeuds;
    private final ArrayList<Lien> list_liens;
    private ArrayList<Noeud> list_noeuds_affiches;
    private ArrayList<Lien> list_liens_affiches;
    private ArrayList<Noeud> checkNode;
    private ArrayList<Lien> checkLink;


    Random random = new Random();

    JPopupMenu jMenu;
    JMenuItem OneNeighbour;
    JMenuItem TwoNeighbour;
    JMenuItem Reset;
    Boolean OneNeighbourDisplay = false;
    Boolean TwoNeighbourDisplay = false;
    ArrayList<JLabel> labelList;

    
    PanelInterface(int width,int height,ArrayList<Noeud> list_noeuds,ArrayList<Lien> list_liens)
    {
        this.widthJFrame = width;
        this.heightJFrame = height;
        this.list_liens = list_liens;
        this.list_noeuds = list_noeuds;

        this.list_liens_affiches = (ArrayList<Lien>) list_liens.clone();
        this.list_noeuds_affiches = (ArrayList<Noeud>) list_noeuds.clone();

        for (Noeud noeud:list_noeuds) 
        {
            int x = random.nextInt(this.widthJFrame - 120);
            int y = random.nextInt(this.heightJFrame - 120);
            noeud.setX(x);
            noeud.setY(y);
        }


        jMenu = new JPopupMenu();
        OneNeighbour = new JMenuItem();
        OneNeighbour.setText("1-voisins");
        OneNeighbour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OneNeigbourActionPerformed(evt);
            }
        });
        TwoNeighbour = new JMenuItem();
        TwoNeighbour.setText("2-voisins");
        TwoNeighbour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TwoNeigbourActionPerformed(evt);
            }
        });
        Reset = new JMenuItem();
        Reset.setText("Réinitialiser");
        Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetActionPerformed(evt);
            }
        }); 
        jMenu.add(Reset);
        jMenu.add(OneNeighbour);
        jMenu.add(TwoNeighbour);
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
                            System.out.println(checkOnTheLine(lien.getDepart(), lien.getArrivee(), e.getX(), e.getY()));
                        }
                    }
            }
        });
        addMouseMotionListener(new MouseAdapter() 
        {
            @Override
            public void mouseDragged(MouseEvent e) {
                System.out.println("X : "+(int)e.getPoint().getX()+" Y : "+(int)e.getPoint().getY());
                moveNode((int)e.getPoint().getX(),(int)e.getPoint().getY());   
            }
        });
    }
    protected void ResetActionPerformed(ActionEvent evt) 
    {
        reset();
    }
    
    public boolean checkOnTheLine(Noeud noeudA, Noeud noeudB,int x, int y)
    {
        if(noeudA.getX()<=noeudB.getX())
        {
            if(x>noeudA.getX() && x<noeudB.getX())
            {
                if (y>noeudA.getY() && y<noeudB.getY())
            }
        }
        else
        {

        }
    }
    
    protected void OneNeigbourActionPerformed(ActionEvent evt) 
    {
        HideOrDisplayOneNeighbour();
    }

    private void HideOrDisplayOneNeighbour()
    {
        TwoNeighbourDisplay = false;
        OneNeighbourDisplay = true;
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

    protected void TwoNeigbourActionPerformed(ActionEvent evt) 
    {
        HideOrDisplayTwoNeighbour();
    }

    private void HideOrDisplayTwoNeighbour() 
    {
        TwoNeighbourDisplay = true;
        OneNeighbourDisplay = false;
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

    private void moveNode(int x,int y)
    {
        for (Noeud noeud:list_noeuds_affiches)
                {
                    if (
                        (noeud.getX() <= x && x <= noeud.getX()+50)
                        &&
                        (noeud.getY() <= y && y <= noeud.getY()+50)
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
                if (OneNeighbourDisplay)
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
                        (JFrame.National.isSelected() && this.getListLiens().get(z).getType() == 'N')
                        ||
                        (JFrame.Departemental.isSelected() && this.getListLiens().get(z).getType() == 'D')
                        ||
                        (JFrame.Highway.isSelected() && this.getListLiens().get(z).getType() == 'A')
                    )
                    {
                        this.getListLiensAffiches().add(this.getListLiens().get(z));
                    }
                    
                }
            }
        }
        }

    private void deleteLink(Noeud delNoeud) {
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
        TwoNeighbourDisplay = false;
        OneNeighbourDisplay = false;
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

    public int countLinkByType(char type) {
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

    public int countNodeByType(char type) {
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
 
    public void updateLabelList(ArrayList<JLabel> labelList)
    {
        this.labelList = labelList;
    }

    public void paintComponent(Graphics g) 
    {
        System.out.println(" -- REPAINT -- ");
        super.paintComponent(g);
        for (Lien lien:this.list_liens_affiches)
        {
            g.drawLine(
                lien.getDepart().getX()+25, 
                lien.getDepart().getY()+25,
                lien.getArrivee().getX()+25,
                lien.getArrivee().getY()+25
            );
            g.drawString(
                lien.getType()+","+lien.getDistance(),
                (lien.getDepart().getX() + lien.getArrivee().getX()+ 25) / 2,
                (lien.getDepart().getY() + lien.getArrivee().getY()+ 25) / 2
            );
        }
        for (Noeud noeud:list_noeuds_affiches)
        {
            switch (noeud.getType())
            {
                case 'V':
                {
                    g.setColor(Color.GREEN);
                    break;
                }
                case 'R':
                {
                    g.setColor(Color.RED);
                    break;
                }
                case 'L':
                {
                    g.setColor(Color.BLUE);
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

    public ArrayList<Noeud> getListNoeud()
    {
        return this.list_noeuds;
    }

    public ArrayList<Lien> getListLiens()
    {
        return this.list_liens;
    }

    public ArrayList<Noeud> getListNoeudAffiches()
    {
        return list_noeuds_affiches;
    }
    
    public ArrayList<Lien> getListLiensAffiches()
    {
        return list_liens_affiches;
    }
}
