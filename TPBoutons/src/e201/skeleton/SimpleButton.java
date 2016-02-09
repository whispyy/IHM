package e201.skeleton ;


import fr.lri.swingstates.canvas.Canvas ;
import fr.lri.swingstates.canvas.CShape ;
import fr.lri.swingstates.canvas.CText ;
import fr.lri.swingstates.canvas.CExtensionalTag;
import fr.lri.swingstates.canvas.CRectangle;
import fr.lri.swingstates.canvas.CStateMachine;
import fr.lri.swingstates.sm.State;
import fr.lri.swingstates.sm.StateMachineListener;
import fr.lri.swingstates.sm.Transition;
import fr.lri.swingstates.canvas.transitions.EnterOnShape;
import fr.lri.swingstates.canvas.transitions.LeaveOnShape;
import fr.lri.swingstates.canvas.transitions.ReleaseOnShape;
import fr.lri.swingstates.canvas.transitions.PressOnShape;

import javax.swing.JFrame ;
import java.awt.Font;
import java.awt.Paint;
import java.awt.BasicStroke;
import java.awt.Color;


/**
 * @author Nicolas Roussel (roussel@lri.fr)
 *
 */
public class SimpleButton {

    private CText label ;

    SimpleButton(Canvas canvas, String text) {
	   label = canvas.newText(0, 0, text, new Font("verdana", Font.PLAIN, 12)) ;
	   CRectangle rect = canvas.newRectangle(-2, -2, label.getWidth()+2, label.getHeight()+2);
	   canvas.addShape(rect);
	   label.aboveAll();
	   rect.setParent(label);
	   CExtensionalTag selected = new CExtensionalTag(canvas) { };
	   rect.addTag(selected);
	   CStateMachine sm = new CStateMachine() {
		    Paint initColor;
		    public State out = new State() {
		        Transition clickBox = new PressOnShape(">> hover") {
		            public void action() {
		            	initColor = rect.getFillPaint();
		                rect.setFillPaint(Color.YELLOW);
		            	
		            }
		        };
		        Transition releaseBox = new ReleaseOnShape(">> hover") {
		            public void action() {
		            	rect.setFillPaint(initColor);
		            }
		        };
		    };
		    public State hover = new State(){
		    	Transition enterBox = new EnterOnShape(">> in") {
		            public void action() {
		            	initColor = rect.getFillPaint();
		                rect.setOutlined(true).setStroke(new BasicStroke(2));
		            }
		        };
		        Transition leaveBox = new LeaveOnShape(">> in") {
		            public void action() {
		            	initColor = rect.getFillPaint();
		                rect.setOutlined(true).setStroke(new BasicStroke(1));
		            }
		        };
		        
		    };
		    public State in = new State() {
		        Transition leaveBox = new LeaveOnShape(">> hover") {
		            public void action() {
		            	rect.setOutlined(true).setStroke(new BasicStroke(1));
		                
		            }
		        };
		    };
		};
		
	sm.attachTo(canvas);
    }
    
    public void action() {
	   System.out.println("ACTION!") ;
    }

    public CShape getShape() {
	   return label ;
    }

    static public void main(String[] args) {
	   JFrame frame = new JFrame() ;
	   Canvas canvas = new Canvas(400,400) ;
	   frame.getContentPane().add(canvas) ;
	   frame.pack() ;
	   frame.setVisible(true) ;	   
	   SimpleButton simple = new SimpleButton(canvas, "simple") ;
	   simple.getShape().translateBy(100,100) ;
    }

}
