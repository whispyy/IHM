import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Created by durand on 08/03/16.
 */

public class Main {
    private static int count;

    /**
     * Question 1 à 3
     * @param args
     */
    public static void oldmain1(String[] args) {
        JFrame w = new JFrame("Incrémenter");
        JLabel l = new JLabel();
        JButton b = new JButton("Incrémenter");

        //configuration du label
        count = 0;
        l.setText(String.valueOf(count));

        //configuration du bouton
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        /*b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                count++;
                l.setText(String.valueOf(count));
            }
        });*/
        b.addActionListener(new ReponseAuClic(l));

        //configuration de la fenêtre
        w.getContentPane().setLayout(new BoxLayout(w.getContentPane(), BoxLayout.PAGE_AXIS));
        w.setLocationRelativeTo(null);
        w.getContentPane().add(l);
        w.getContentPane().add(b);
        w.pack();
        w.setVisible(true);
        System.out.println("Fenêtre initialisé");
    }

    /**
     * Question 4
     * @param args
     */
    public static void oldmain2(String[] args){
        new FenetreIncrementer(new JLabel());
    }

    /**
     * Question 5 et 6
     * (voir FenetreIncrementer2 pour la question 6
     * @param args
     */
    public static void oldmain3(String[] args){
        new FenetreIncrementer2(new JLabel());
    }

    /**
     * Question 7
     * @param args
     */

    public static void main(String[] args){
        JFrame w = new JFrame("Incrémenter");
        JLabel l = new JLabel();
        w.getContentPane().add(l);

        for (int i=1; i <4; i++) {
            JButton bouton = new JButton("Bouton " + i);
            bouton.setAlignmentX(Component.CENTER_ALIGNMENT);
            bouton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    l.setText(bouton.getText());
                }
            });
            w.getContentPane().add(bouton);
        }

        //configuration de la fenêtre
        w.getContentPane().setLayout(new BoxLayout(w.getContentPane(), BoxLayout.PAGE_AXIS));
        w.setLocationRelativeTo(null);
        w.pack();
        w.setVisible(true);
        System.out.println("Fenêtre initialisé");
        w.addWindowListener(new FermetureFenetre());
    }

}