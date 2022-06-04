import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CompareDialog extends Dialog{
    Graphe graphe;
    CompareDialog (JFrame parent, String title,ArrayList<Noeud> list_noeuds, ArrayList<Lien> list_liens, Graphe graphe)
    {
        super(parent,title,list_noeuds,list_liens);
        this.graphe = graphe;
        labelselector.setText("Critère ");;
        selector.addItem("Gastronomique");
        selector.addItem("Ouverte");
        selector.addItem("Culturelle");
        this.setVisible(true);
    }
    @Override
    protected void action() {
        super.action();
        String text = null;
        Noeud noeudAToCompare = null, noeudBToCompare = null;
        if (noeudA.getSelectedItem().equals(noeudB.getSelectedItem()))
                {
                    JOptionPane.showMessageDialog(
                                                        null,
                                                        "Les noeuds sélectionnés sont identiques",
                                                        "Vérification de connexion",
                                                        JOptionPane.WARNING_MESSAGE
                                                        );
                }
                else
                {
                    for (Noeud noeud : list_noeuds)
                    {
                        if 
                        (
                            noeudA.getSelectedItem().toString().split(",")[0].charAt(0)==noeud.getType()
                            &&
                            noeudA.getSelectedItem().toString().split(",")[1].equals(noeud.getNom())
                        )
                        {
                            noeudAToCompare = noeud;
                        }
                        else if
                        (
                            noeudB.getSelectedItem().toString().split(",")[0].charAt(0)==noeud.getType()
                            &&
                            noeudB.getSelectedItem().toString().split(",")[1].equals(noeud.getNom())
                        )
                        {
                            noeudBToCompare = noeud;
                        }
                    }
                    switch(selector.getSelectedItem().toString())
                    {
                            case "Gastronomique":text = graphe.plusGastro(noeudAToCompare, noeudBToCompare);break;
                            case "Ouverte": text = graphe.plusOuverte(noeudAToCompare, noeudBToCompare);break;
                            case "Culturelle": text = graphe.plusCulturelle(noeudAToCompare, noeudBToCompare);break;
                    }
                    JOptionPane.showMessageDialog(
                                                                            null,
                                                                            text,
                                                                            "Vérification de connexion",
                                                                            JOptionPane.INFORMATION_MESSAGE
                                                                            );
            }
        }
}
