package e214.skeleton;

import fr.lri.swingstates.canvas.CExtensionalTag;
import fr.lri.swingstates.canvas.CSegment;
import fr.lri.swingstates.canvas.Canvas;

public class MagneticGuide extends CExtensionalTag {
	private CSegment seg;
	
	public MagneticGuide(double val, boolean horizontal, Canvas canvas){
		super();
		if (horizontal)
			this.seg = canvas.newSegment(-3000, val, 3000, val);
		else
			this.seg = canvas.newSegment(val, -3000, val, 3000);

		
	}

	public CSegment getSeg(){
		return this.seg;
	}
}
