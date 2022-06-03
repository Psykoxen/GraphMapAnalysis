/*
    public class drawpanel extends JPanel implements MouseListener{
        
        private int width;
        private int height;
        private final ArrayList<Noeud> list_noeuds;
        private final ArrayList<Lien> list_liens;
         
       // private final ArrayList<Noeud> list_noeuds = mainGraphe.getNoeuds();
        //private final ArrayList<Lien> list_liens = mainGraphe.getLiens();
        private ArrayList<Lien> list_liens_affiches;
        private ArrayList<Noeud> list_noeuds_affiches;
        Random random = new Random();
        
        drawpanel(int width, int height) {
            addMouseListener(this);
            this.width = width;
            this.height = height;
            list_noeuds = new ArrayList<>();
            Noeud A = new Noeud('V', "A");
            list_noeuds.add(A);
            Noeud B = new Noeud('R', "B");
            list_noeuds.add(B);
            Noeud C = new Noeud('L', "C");
            list_noeuds.add(C);
              
            list_liens = new ArrayList<>();
            Lien AB = new Lien(0,'N',A,B);
            list_liens.add(AB);
            Lien BC = new Lien(0,'D',B,C);
            list_liens.add(BC);
            Lien CA = new Lien(0,'A',C,A);
            list_liens.add(CA);
            list_liens_affiches = (ArrayList<Lien>) list_liens.clone();
            list_noeuds_affiches = (ArrayList<Noeud>) list_noeuds.clone();
              
            for (int i = 0; i < list_noeuds_affiches.size(); i++) {
                int x = random.nextInt(this.width - 120);
                int y = random.nextInt(this.height - 120);
                if (i>0)
                {
                    while (
                        (list_noeuds.get(i-1).getX()<x && x<list_noeuds.get(i-1).getX()+50)
                    )
                    {
                        if (x<=200)
                        {
                            x -= 5;
                        }
                        else
                        {
                            x +=5;
                        }
                            
                    }
                    while (
                        (list_noeuds.get(i-1).getY()<x && x<list_noeuds.get(i-1).getY()+50)
                    )
                    {
                            if (y<=200)
                            {
                                y -= 5;
                            }
                            else
                            {
                                y +=5;
                            }
                    }
                }
                list_noeuds.get(i).setX(x);
                list_noeuds.get(i).setY(y);
            }
        }
        public void paintComponent(Graphics g) {
            System.out.println("-- REPAINT --");

            for (int i = 0; i < list_liens_affiches.size(); i++) {
                g.drawLine(list_liens_affiches.get(i).getDepart().getX() + 25,
                        list_liens_affiches.get(i).getDepart().getY() + 25,
                        list_liens_affiches.get(i).getArrivee().getX() + 25,
                        list_liens_affiches.get(i).getArrivee().getY() + 25);
                g.drawString(
                                  list_liens_affiches.get(i).getType()
                                + "," + list_liens_affiches.get(i).getDistance(),
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
                g.setColor(Color.WHITE);
                Font f = new Font ("Sanserif",Font.PLAIN, 12);
                 g.setFont (f);
                g.drawString(String.valueOf(list_noeuds_affiches.get(i).getType()), list_noeuds_affiches.get(i).getX() + 23,
                        list_noeuds_affiches.get(i).getY() + 28);
            }
            lblDepartemental.setText("Départementales : "+countLinkByType('D'));
            lblNational.setText("Nationales : "+countLinkByType('N'));
            lblHighway.setText("Autoroutes : "+countLinkByType('A'));
            lblFood.setText("Restaurants : "+countNodeByType('R')); 
            lblFun.setText("Loisirs : "+countNodeByType('L'));
            lblCity.setText("Villes : "+countNodeByType('V'));
            lblNational.setText("Nationales : "+countLinkByType('N'));
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("CLICK ### X : "+e.getX()+" Y : "+e.getY());
            for (int i=0; i<list_noeuds_affiches.size();i++)
            {
                System.out.println(list_noeuds_affiches.get(i).getNom()+" XSTART : "+list_noeuds_affiches.get(i).getX()+" YSTART : "+list_noeuds_affiches.get(i).getY()+"\n      XEND : "+list_noeuds_affiches.get(i).getX()+50+" YEND : "+list_noeuds_affiches.get(i).getY()+50);
                if 
                (
                    (list_noeuds_affiches.get(i).getX() < e.getX() && e.getX() < list_noeuds_affiches.get(i).getX()+50)
                    &&
                    (list_noeuds_affiches.get(i).getY() < e.getY() && e.getY() < list_noeuds_affiches.get(i).getY()+50)
                )
                {
                    list_noeuds_affiches.remove(list_noeuds_affiches.get(i));
                        
                }
            }
            this.repaint(0,0,400,400);
        }
        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("PRESS ### X : "+e.getX()+" Y : "+e.getY());
        }
        @Override
        public void mouseReleased(MouseEvent e) {
            System.out.println("RLESE ### X : "+e.getX()+" Y : "+e.getY());
            
        }
        @Override
        public void mouseEntered(MouseEvent e) {
            System.out.println("ENTER ### X : "+e.getX()+" Y : "+e.getY());
            
        }
        @Override
        public void mouseExited(MouseEvent e) {
            System.out.println("EXITE ### X : "+e.getX()+" Y : "+e.getY());
            
        }



    }
    */