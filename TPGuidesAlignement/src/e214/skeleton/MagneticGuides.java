package e214.skeleton ;

import fr.lri.swingstates.canvas.Canvas ;
import fr.lri.swingstates.canvas.CShape ;
import fr.lri.swingstates.canvas.CRectangle ;
import fr.lri.swingstates.canvas.CSegment ;
import fr.lri.swingstates.canvas.CTag ;
import fr.lri.swingstates.canvas.CExtensionalTag ;
import fr.lri.swingstates.canvas.CStateMachine ;
import fr.lri.swingstates.canvas.transitions.PressOnShape;
import fr.lri.swingstates.canvas.transitions.PressOnTag ;
import fr.lri.swingstates.sm.State;
import fr.lri.swingstates.sm.Transition;
import fr.lri.swingstates.sm.transitions.Click;
import fr.lri.swingstates.sm.transitions.Drag ;
import fr.lri.swingstates.sm.transitions.MouseOnPosition;
import fr.lri.swingstates.sm.transitions.Release ;

import java.awt.Color ;
import java.awt.BasicStroke ;
import java.awt.geom.Point2D ;
import javax.swing.JFrame ;
import java.util.LinkedList ;

/**
 * @author Nicolas Roussel (roussel@lri.fr)
 *
 */

public class MagneticGuides extends JFrame {

    private Canvas canvas ;
    private CExtensionalTag oTag ;

    public MagneticGuides(String title, int width, int height) {
	   super(title) ;
	   canvas = new Canvas(width, height) ;
	   canvas.setAntialiased(true) ;
	   getContentPane().add(canvas) ;

	   
	   oTag = new CExtensionalTag(canvas) {} ;
	   
	   
	   CStateMachine sm = new CStateMachine() {

			 private Point2D p ;
			 private CShape draggedShape ;
			 private MagneticGuide mg;

			 public State start = new State() {
				 Transition pressOnObject = new PressOnTag(oTag, BUTTON1, ">> oDrag") {
					  public void action() {
						 p = getPoint() ;
						 draggedShape = getShape() ;
					  }
				};
				MouseOnPosition Hguide = new Click(BUTTON1){
					public void action(){
						mg = new MagneticGuide(getPoint().getY(),true,canvas);
						mg.getSeg().addTag(oTag);
					}
				};
				MouseOnPosition Vguide = new Click(BUTTON3){
					public void action(){
						mg = new MagneticGuide(getPoint().getX(),false,canvas);
						mg.getSeg().addTag(oTag);
					}
				};
					 
			 };
			 public State oDrag = new State() {
				    Transition drag = new Drag(BUTTON1) {
						  public void action() {
							 Point2D q = getPoint() ;
							 draggedShape.translateBy(q.getX() - p.getX(), q.getY() - p.getY()) ;
							 p = q ;
						  }
					   } ;
				    Transition release = new Release(BUTTON1, ">> start") {} ;
				} ;
		  } ;
	   sm.attachTo(canvas);

	   pack() ;
	   setVisible(true) ;
	   canvas.requestFocusInWindow() ;
    }

    public void populate() {
	   int width = canvas.getWidth() ;
	   int height = canvas.getHeight() ;

	   double s = (Math.random()/2.0+0.5)*30.0 ;
	   double x = s + Math.random()*(width-2*s) ;
	   double y = s + Math.random()*(height-2*s) ;

	   int red = (int)((0.8+Math.random()*0.2)*255) ;
	   int green = (int)((0.8+Math.random()*0.2)*255) ;
	   int blue = (int)((0.8+Math.random()*0.2)*255) ;

	   CRectangle r = canvas.newRectangle(x,y,s,s) ;
	   r.setFillPaint(new Color(red, green, blue)) ;
	   r.addTag(oTag) ;
    }

    public static void main(String[] args) {
	   MagneticGuides guides = new MagneticGuides("Magnetic guides",600,600) ;
	   for (int i=0; i<20; ++i) guides.populate() ;
	   guides.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
    }

}
