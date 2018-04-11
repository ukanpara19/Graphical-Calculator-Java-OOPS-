package graph.cal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

// Drawing the outline of polygon on the graph in order to plot it
public class Draw_shape {
	Polygon d;
	Color c;

	public Draw_shape(int[] a, int[] b, Color c) {
		d = new Polygon();
		d.xpoints = a;
		d.ypoints = b;
		this.c = c;
		d.npoints = a.length;
	}

	public void drawPolygon(Graphics g) {
		g.setColor(c);
		g.drawPolygon(d);
	}
}
