import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by durand on 08/03/16.
 */
public class FenetreIncrementer {
    private static JLabel l;
    private static int count;

    private static class ReponseAuClic implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Clic sur le bouton");
            String t = l.getText();
            int val = Integer.parseInt(t);
            val++;
            l.setText(""+val);
        }
    }

    public FenetreIncrementer(JLabel l) {
        this.l = l;
        this.count = 0;
        JFrame w = new JFrame("Incrémenter");
        JButton b = new JButton("Incrémenter");

        //configuration du label
        count = 0;
        l.setText(String.valueOf(count));

        //configuration du bouton
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.addActionListener(new ReponseAuClic());

        //configuration de la fenêtre
        w.getContentPane().setLayout(new BoxLayout(w.getContentPane(), BoxLayout.PAGE_AXIS));
        w.setLocationRelativeTo(null);
        w.getContentPane().add(l);
        w.getContentPane().add(b);
        w.pack();
        w.setVisible(true);
        System.out.println("Fenêtre initialisé");
    }

}
