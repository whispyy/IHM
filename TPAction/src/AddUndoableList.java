import javax.swing.*;
import javax.swing.undo.AbstractUndoableEdit;

/**
 * Created by durand on 22/03/16.
 */
public class AddUndoableList extends AbstractUndoableEdit {
    private DefaultListModel save;
    private int position;

    public AddUndoableList(){
        this.save = UndoGUI.getListModel();
        this.position = 0;
    }

    public void addElement(){
        this.save = UndoGUI.getListModel();
        this.position++;
    }

    public void undo(){
        if(this.position >0){
            UndoGUI.setActiveUndo(false);
            UndoGUI.setListModel(save);
            this.position--;
        }
        else
            UndoGUI.setActiveUndo(false);
    }
}
