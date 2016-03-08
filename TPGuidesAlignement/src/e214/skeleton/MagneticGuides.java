package e214.skeleton ;

import fr.lri.swingstates.canvas.Canvas ;
import fr.lri.swingstates.canvas.CShape ;
import fr.lri.swingstates.canvas.CRectangle ;
import fr.lri.swingstates.canvas.CSegment ;
import fr.lri.swingstates.canvas.CTag ;
import fr.lri.swingstates.canvas.CExtensionalTag ;
import fr.lri.swingstates.canvas.CStateMachine ;
import fr.lri.swingstates.canvas.transitions.LeaveOnTag;
import fr.lri.swingstates.canvas.transitions.PressOnTag ;
import fr.lri.swingstates.sm.State;
import fr.lri.swingstates.sm.Transition;
import fr.lri.swingstates.canvas.transitions.EnterOnTag;
import fr.lri.swingstates.sm.transitions.Drag ;
import fr.lri.swingstates.sm.transitions.Press;
import fr.lri.swingstates.sm.transitions.Release ;

import java.awt.Color ;
import java.awt.BasicStroke ;
import java.awt.Paint;
import java.awt.geom.Point2D ;

import javax.swing.JFrame ;

import java.rmi.dgc.Lease;
import java.util.LinkedList ;

/**
 * @author Nicolas Roussel (roussel@lri.fr)
 *
 */
public class MagneticGuides extends JFrame {

    private Canvas canvas ;
    private CExtensionalTag oTag ;
    private CExtensionalTag magneticGuide;

    public MagneticGuides(String title, int width, int height) {
	   super(title) ;
	   canvas = new Canvas(width, height) ;
	   canvas.setAntialiased(true) ;
	   getContentPane().add(canvas) ;

	   oTag = new CExtensionalTag(canvas) {} ;
	   
	   magneticGuide = new MagneticGuide(canvas);

	   CStateMachine sm = new CStateMachine() {

			 private Point2D p ;
			 private CShape draggedShape;
			 private CShape guide;
			 private Paint guideDefaultColor;

			 public State start = new State() {
				    Transition pressOnObject = new PressOnTag(oTag, BUTTON1, ">> oDrag") {
						  public void action() {
							 p = getPoint() ;
							 draggedShape = getShape() ;
						  }
					   } ;
					   
					Transition pressOnCanvas1 = new Press(BUTTON1,CONTROL){
				    	public void action(){
				    		p = getPoint() ;
				    		CSegment segment = canvas.newSegment(0, p.getY(), canvas.getWidth(), p.getY());
				    		segment.belowAll();
				    		segment.addTag(magneticGuide);
				    	}
					};
					
					Transition pressOnCanvas2 = new Press(BUTTON3,CONTROL){
				    	public void action(){
				    		p = getPoint() ;
				    		CSegment segment = canvas.newSegment( p.getX(),0, p.getX(), canvas.getHeight());
				    		segment.belowAll();
				    		segment.addTag(magneticGuide);
				    	}
					};
					
					Transition pressOnMagneticGuide = new PressOnTag(magneticGuide,BUTTON1, ">>magneticguideDrag") {
						public void action(){
							p = getPoint() ;
							draggedShape = getShape() ;
						}
					};
				} ;

			public State oDrag = new State() {
				Transition drag = new Drag(BUTTON1) {
					public void action() {
						Point2D q = getPoint();
						draggedShape.translateBy(q.getX() - p.getX(), q.getY()
								- p.getY());
						p = q;
					}
				};
				Transition release = new Release(BUTTON1, ">> start") {
					public void action(){
					}
				};
				
				Transition onGuide = new EnterOnTag(magneticGuide, ">> objectOnGuide"){					
					public void action(){
						guide = getShape();
						guideDefaultColor = guide.getFillPaint();
						guide.setFillPaint(Color.YELLOW);
					}
				};
			};
			
			public State magneticguideDrag = new State() {
				Transition drag = new Drag(BUTTON1) {
					public void action() {
						Point2D q = getPoint();
						draggedShape.translateBy(0, q.getY() - p.getY());
						p = q;
					}
				};
				Transition release = new Release(BUTTON1, ">> start") {
				};
			};
			
			public State objectOnGuide = new State() {
				Transition releaseObjectOnGuide = new Release(">>start"){
					public void action(){
						draggedShape.addTag(magneticGuide);
					}
				};
				
				Transition objectLeaveGuide = new LeaveOnTag(magneticGuide){
					public void action(){
						
					}
				};
			};

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