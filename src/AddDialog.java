import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;  
import java.awt.GridBagLayout;  

public class AddDialog extends JDialog{
    JPanel panDialog;
    JTextField noeud;
    JComboBox type;
    JButton btnAdd;
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