import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created by durand on 15/03/16.
 */
public class Question2font {
    public static void main(String[] args){
        JFrame fen = new JFrame("Drag Police");
        fen.getContentPane().setLayout(new BoxLayout(fen.getContentPane(), BoxLayout.PAGE_AXIS));
        fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //label
        JLabel titre = new JLabel("IHM");


        //formatted 1
        JFormattedTextField ftf = new JFormattedTextField("Master 1 info");
        ftf.setFont(new Font("Courier",Font.ITALIC,12));
        ftf.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("JFormattedTextField"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
        ftf.setDragEnabled(true);

        //formatted2
        JFormattedTextField ftf1 = new JFormattedTextField("Universite de Lille 1");
        ftf1.setFont(new Font("Verdana",Font.BOLD,12));
        ftf1.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("JFormattedTextField"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
        ftf1.setDragEnabled(true);

        //Mouse Motion & TransferHandler

        TransferHandler th1 = new TransferHandler("font");
        titre.setTransferHandler(th1);
        ftf1.setTransferHandler(th1);
        ftf.setTransferHandler(th1);

        MouseMotionListener m = new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                titre.getTransferHandler().exportAsDrag(titre,e,th1.COPY);
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }

        };
        titre.addMouseMotionListener(m);

        fen.getContentPane().add(titre);
        fen.getContentPane().add(ftf);
        fen.getContentPane().add(ftf1);
        fen.pack();
        fen.setVisible(true);
    }

}
