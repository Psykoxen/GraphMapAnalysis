package graphe_interface;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import graphe_backend.*;

/**
 *
 * @author Antoine
 * 
 */
public class PanelInterface extends JPanel{
    int xPress = 0;
    int xRelease = 0;
    int yPress = 0;
    int yRelease = 0;

    private int widthJFrame;
    private int heightJFrame;

    private final ArrayList<Noeud> list_noeuds;
    private final ArrayList<Lien> list_liens;
    private ArrayList<Noeud> list_noeuds_affiches;
    private ArrayList<Lien> list_liens_affiches;

    Random random = new Random();

    
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

        addMouseListener(new MouseListener() 
        {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (xPress==xRelease && yPress==yRelease)
                {
                    selectNoeud(e.getX(),e.getY());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                xPress = e.getX();
                yPress = e.getY();
                System.out.println(e.getX()+" - "+e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                xRelease = e.getX();
                yRelease = e.getY();
                if (xPress!=xRelease || yPress!=yRelease)
                {
                    moveNode();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

        });
    }
    private void moveNode()
    {
        for (Noeud noeud:list_noeuds_affiches)
                {
                    if (
                        (noeud.getX() <= xPress && xPress <= noeud.getX()+50)
                        &&
                        (noeud.getY() <= yPress && yPress <= noeud.getY()+50)
                    )
                    {
                        noeud.setX(xRelease);
                        noeud.setY(yRelease);
                        break;
                    }
                }
                this.repaint();
    }
     
    private void selectNoeud(int x,int y)
    {
        Noeud noeudToKeep = null;
        for (Noeud noeud:list_noeuds_affiches)
                {
                    if (
                        (noeud.getX() <= x && x <= noeud.getX()+50)
                        &&
                        (noeud.getY() <= y && y <= noeud.getY()+50)
                    )
                    {
                        noeudToKeep = noeud;
                    }
                }
        if (noeudToKeep != null)
        {
            System.out.println("TO KEEP : "+noeudToKeep.getNom());
            list_noeuds_affiches.clear();
            list_noeuds_affiches.add(noeudToKeep);
            list_liens_affiches.clear();
            for (Lien lien:list_liens)
            {
                if 
                (
                    (lien.getArrivee().equals(noeudToKeep))
                    ||
                    (lien.getDepart().equals(noeudToKeep))
                )
                {
                    if (lien.getDepart().equals(noeudToKeep))
                    {
                        list_noeuds_affiches.add(lien.getArrivee());  
                    }
                    else
                    {
                        list_noeuds_affiches.add(lien.getDepart());
                    }
                    list_liens_affiches.add(lien);
                    
                }
             }
        for (Lien lien :list_liens_affiches)
        {System.out.println("De "+lien.getDepart().getNom()+" -> "+lien.getArrivee().getNom());}
        repaint();
    }
}
    
    public void hideOrDisplayLinkByType(Boolean selected,char type)
    {
        for (int i = 0; i < this.getListLiens().size(); i++) 
        {
            if (!selected)
            {
                if (this.getListLiens().get(i).getType() == type)
                {
                    this.getListLiensAffiches().remove(this.getListLiens().get(i));
                }
            }
            else
            {
                if (this.getListLiens().get(i).getType() == type)
                {
                    for (int j=0;j< this.getListNoeudAffiches().size();j++)
                    {
                        if (this.getListLiens().get(i).getDepart().equals( this.getListNoeudAffiches().get(j))) 
                        {
                            for (int k=0;k< this.getListNoeudAffiches().size();k++)
                            {
                                if (this.getListLiens().get(i).getArrivee().equals( this.getListNoeudAffiches().get(k))) 
                                {
                                    this.getListLiensAffiches().add(this.getListLiens().get(i));
                                }
                            }
                        }
                    }
                }
            }
        }
        this.repaint();    
    }    
    
    public void hideOrDisplayNodeByType(Boolean selected,char type,GrapheInterface JFrame)
    {
        boolean check = false;
        for (int i = 0; i <  this.getListNoeud().size(); i++) {
            if (!selected) {
                if ( this.getListNoeud().get(i).getType() == type) {
                    System.out.println(this.getListNoeud().size());
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
        this.repaint();
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
                        (JFrame.Departmental.isSelected() && this.getListLiens().get(z).getType() == 'D')
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
        for (Noeud noeud:list_noeuds_affiches)
        {
            System.out.println(noeud.getNom());
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
