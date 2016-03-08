import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by durand on 08/03/16.
 */
public class FermetureFenetre extends WindowAdapter {

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("Fenêtre en cours de fermeture");
        super.windowClosing(e);
        System.exit(0);
    }
}
