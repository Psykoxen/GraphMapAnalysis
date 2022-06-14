package graphmap.GraphDialog;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;  
import java.awt.GridBagLayout;  

public class AddDialog extends JDialog{
    JPanel panDialog;
    GridBagConstraints gbc;
    AddDialog(JFrame parent, String title)
    {
        super(parent,title,true);
        this.setLocationRelativeTo(null);
        this.setSize(220,150);
        this.setResizable(false);
        panDialog  = new JPanel();
        panDialog.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

    }
}