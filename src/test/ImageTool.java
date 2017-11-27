package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
public class ImageTool {
	public static void addRec(int x,int y,int h,int w){
		try {
			BufferedImage bufImage = ImageIO.read(new File("F:/11.jpg"));
	        Graphics g = bufImage.getGraphics();
	        Graphics2D g2d = (Graphics2D) g.create();
	        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g2d.setColor(Color.RED);
	        Polygon p=new Polygon();
	        Rectangle rect=new Rectangle(x, y, w, h);
	        g2d.draw(rect);
	        g.dispose();  
	        ByteArrayOutputStream output = new ByteArrayOutputStream();  
	        ImageOutputStream imageOut = ImageIO.createImageOutputStream(output);  
	        //ImageIO.write(bufImage, "JPEG", imageOut);
	        ImageIO.write(bufImage, "JPG", new File("F:/22.jpg"));
	        imageOut.close();  
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		/**
		 * "h":151,
        "w":151,
        "x":1420,
        "y":237
		 */
		ImageTool.addRec(1420,237,151,151);
	}

}
