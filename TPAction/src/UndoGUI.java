/**
 * @author <a href="mailto:gery.casiez@lifl.fr">Gery Casiez</a>
 */

import java.awt.*;
import java.awt.event.*;
import java.util.EventListener;

import javax.swing.*;
import javax.swing.event.*;

public class UndoGUI {
	protected static DefaultListModel listModel;
	private JTextField text;
	private JList list;

	protected static boolean activeUndo = false;
	protected static boolean activeRedo = false;

	UndoGUI() {
		//les actions
		ActionListener add = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (text.getText().length() > 0) {
					listModel.addElement(text.getText());
					text.setText("");
				}
			}
		};

		ActionListener sup = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = list.getSelectedIndex();
				if (index != -1) listModel.remove(index);
			}
		};

		// JFrame
		JFrame fen = new JFrame("Undo/Redo");
		fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Liste
		listModel = new DefaultListModel();
		listModel.addElement("Universite Lille 1");
		listModel.addElement("Master informatique");
		listModel.addElement("IHM");
		list = new JList(listModel);
		JScrollPane listScroller = new JScrollPane(list);
		text = new JTextField();
		text.addActionListener(add);
		JButton boutonAjouter = new JButton("Ajouter");
		boutonAjouter.addActionListener(add);


		// Menu
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Edition");
		menuBar.add(menu);
		JMenuItem menuItem = new JMenuItem("Supprimer");
		menu.add(menuItem);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		menuItem.addActionListener(sup);
		menuItem = new JMenuItem("Annuler");
		menu.add(menuItem);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Event.CTRL_MASK));
		menuItem.setEnabled(activeUndo);
		menuItem = new JMenuItem("Refaire");
		menu.add(menuItem);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, Event.CTRL_MASK));
		menuItem.setEnabled(activeRedo);


		// ToolBar
		JToolBar toolBar = new JToolBar("Barre d'outils");
		JButton boutonSupprimer = new JButton("Supprimer");
		boutonSupprimer.addActionListener(sup);
		
		JButton boutonAnnuler = new JButton("Annuler");
		JButton boutonRefaire = new JButton("Refaire");
		toolBar.add(boutonSupprimer);
		toolBar.add(boutonAnnuler);
		boutonAnnuler.setEnabled(activeUndo);
		toolBar.add(boutonRefaire);
		boutonRefaire.setEnabled(activeRedo);
		fen.setJMenuBar(menuBar);
		fen.add(toolBar);
		fen.getContentPane().setLayout(new BoxLayout(fen.getContentPane(),BoxLayout.Y_AXIS));
		fen.getContentPane().add(listScroller);
		fen.getContentPane().add(text);
		fen.getContentPane().add(boutonAjouter);
		fen.pack();
		fen.setVisible(true);

	}

	public static final DefaultListModel getListModel(){
		return listModel;
	}
	public static final void setListModel(DefaultListModel l){
		listModel = l;
	}
	public static final void setActiveUndo(boolean b){
		activeUndo = b;
	}


	public static void main(String[] args) {
	    //Schedule a job for the event-dispatching thread:
	    //creating and showing this application's GUI.
	    javax.swing.SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
			new UndoGUI();
		    }
		});
	}

}
