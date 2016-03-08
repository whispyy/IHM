import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by durand on 08/03/16.
 */
public class ReponseAuClic implements ActionListener{

    private JLabel l;

    public ReponseAuClic(JLabel l){
        super();
        this.l = l;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Clic sur le bouton");
        String t = l.getText();
        int val = Integer.parseInt(t);
        val++;
        l.setText(""+val);
    }
}
