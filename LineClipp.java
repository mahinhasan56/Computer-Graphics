

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

public class LineClipp implements GLEventListener{
	
	static GLProfile profile = GLProfile.get(GLProfile.GL2);
    static GLCapabilities capabilities = new GLCapabilities(profile);
    // The canvas 
    static GLCanvas glcanvas = new GLCanvas(capabilities);
    
//   static double x0=-.2;
//    static double x1=.2;
//  static double  y0=-.1;
//   static double y1=.1;
    
  static double x0=randomNum(-1, 1);
  static double x1=randomNum(-1, 1);;
static double  y0=randomNum(-1, 1);;
 static double y1=randomNum(-1, 1);;
    
//  static double x0=-.5;
//  static double x1=.5;
//static double  y0=-.3;
// static double y1=-.3;
 	  
	static  double xMax=.3;
	 static double xMin=-.3;
	 static double yMax=.2;
	 static double yMin=-.2;
	 static int LEFT=1;
	 static int RIGHT=2;
	 static int BOTTOM=4;
	 static int TOP=8;
	 
    
   public static void main(String[] args) {
	      //getting the capabilities object of GL2 profile
	   	  
	      
	      LineClipp l = new LineClipp();
	      //creating frame
	      glcanvas.addGLEventListener(l);
	      glcanvas.setSize(600, 400);
	      
	      final JFrame frame = new JFrame ("straight Line");
	      //adding canvas to frame
	      frame.getContentPane().add(glcanvas);
	      frame.setSize(frame.getContentPane().getPreferredSize());
	      frame.setVisible(true);
	      
	   }
   public void display(GLAutoDrawable drawable) {
      final GL2 gl = drawable.getGL().getGL2();
       	  gl.glBegin (GL2.GL_LINES);//static field
//          gl.glVertex2d(0.5f,0.5f);
//          gl.glVertex2d(0.5f,-0.5f);
//          gl.glVertex2d(-0.5f,0.5f);
//          gl.glVertex2d(-0.5f,-0.5f);
       	  
    
       
    	  gl.glColor3f(1f,1f,1f);
    	  gl.glVertex2d(xMax,yMin);
          gl.glVertex2d(xMax,yMax);
         
          gl.glVertex2d(xMax,yMax);
        gl.glVertex2d(xMin,yMax);
       
        gl.glVertex2d(xMin,yMax);
        gl.glVertex2d(xMin,yMin);
       
        gl.glVertex2d(xMin,yMin);
        gl.glVertex2d(xMax,yMin);
          
          gl.glEnd();
          
         int outCode_0,outCode_1,outCode;
         
         for(int n=0;n<10000;n++){
         double x0=randomNum(-1, 1);
         double x1=randomNum(-1, 1);
         double  y0=randomNum(-1, 1);
          double y1=randomNum(-1, 1);
         
          outCode_0=makeCode(x0,y0);
          outCode_1=makeCode(x1,y1);
          
         double x,y; 
         gl.glBegin (GL2.GL_LINES);
			
			gl.glColor3f(1f,0f,1f);
			gl.glVertex2d(x0,y0);
			gl.glVertex2d(x1,y1);
			gl.glEnd();
//          
          while(true){
	    		if((outCode_0 | outCode_1) ==0){
	    			gl.glBegin (GL2.GL_LINES);
	    			gl.glColor3f(0f,1f,0f);
	    			gl.glVertex2d(x0,y0);
	    			gl.glVertex2d(x1,y1);
	    			System.out.println("completely excepted");
	    			gl.glEnd();
	    			break;
	    		}
	    		else if((outCode_0 & outCode_1)>0){
	    			
//	    			System.out.println("completely rejected");
	    			
	    			break;
	    		}
	    		else{
	    			
	    			
	    			outCode = (outCode_0 != 0) ? outCode_0 : outCode_1;
	    			
	    			 if ((outCode & TOP) != 0) {
	                     x = x0 + (x1 - x0) * (yMax - y0) / (y1 - y0);
	                     y =yMax;
	                 } else if ((outCode & BOTTOM) != 0) {
	                     x = x0 + (x1 - x0) * (yMin - y0) / (y1 - y0);
	                     y = yMin;
	                 } else if ((outCode & RIGHT) != 0) {
	                     y = y0 + (y1 - y0) * (xMax - x0) / (x1 - x0);
	                     x = xMax;
	                 } else {
	                     y = y0 + (y1 - y0) * (xMin - x0) / (x1 - x0);
	                     x = xMin;
	                 }

	                 // Now we move outside point to intersection point to clip
	                 if (outCode == outCode_0) {
	                     x0 = x;
	                     y0 = y;
	                     outCode_0 = makeCode(x0, y0);
	                 } else {
	                     x1 = x;
	                     y1 = y;
	                     outCode_1 = makeCode(x1, y1);
	                 }
	    			
	                
	    			System.out.println("Partialy accepted");
	    			
	    			
	    		}
	    		 
    	}
//          gl.glBegin (GL2.GL_LINES);
//          gl.glColor3f(1f,1f,1f);
// 			gl.glVertex2d(x0,y0);
// 			gl.glVertex2d(x1,y1);
// 			gl.glEnd();
         }
   }
   
   int makeCode(double x,double y){
	   int outCode=0;

    	  

	   
	   if(y>yMax){
		   outCode+=8;
	   }
	   else if(y<yMin){
		   outCode+=4;
	   }
	   if(x>xMax){
		   outCode+=2;
	   }
	   else if(x<xMin){
		   outCode+=1;
	   }
	   return outCode;
   }
   
   
   static double randomNum(int min, int max)
   {
       double range = (max - min);    
      return (Math.random() * range) + min;
   }
   
   public void dispose(GLAutoDrawable arg0) {
      //method body
   }

   
   public void init(GLAutoDrawable drawable) {
      // method body
	   //4. drive the display() in a loop
	    }
   
   public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
      // method body
   }
   //end of main
}//end of classimport javax.media.opengl.GL2;