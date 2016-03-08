package e214.skeleton;

import java.awt.BasicStroke;

import fr.lri.swingstates.canvas.CExtensionalTag;
import fr.lri.swingstates.canvas.CSegment;
import fr.lri.swingstates.canvas.CShape;
import fr.lri.swingstates.canvas.Canvas;

public class MagneticGuide extends CExtensionalTag {
	private CSegment seg;
	
	public MagneticGuide(Canvas canvas){
		super(canvas);
		/*if (horizontal)
			this.seg = canvas.newSegment(-3000, val, 3000, val);
		else
			this.seg = canvas.newSegment(val, -3000, val, 3000);

		*/
	}

	public CSegment getSeg(){
		return this.seg;
	}
	@Override
	public void added(CShape s){
		s.setStroke(new BasicStroke(1,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,0,new float[]{9},0));
		//super.added(s);
	}
}
