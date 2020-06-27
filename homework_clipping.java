


import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

public class homework_clipping implements GLEventListener{
	
	static GLProfile profile = GLProfile.get(GLProfile.GL2);
    static GLCapabilities capabilities = new GLCapabilities(profile);
    // The canvas 
    static GLCanvas glcanvas = new GLCanvas(capabilities);
    
    
    
    
    GL2 gl; 
    
    
   public static void main(String[] args) {
	      //getting the capabilities object of GL2 profile
	   	  
	      
	   homework_clipping l = new homework_clipping();
	      //creating frame
	      glcanvas.addGLEventListener(l);
	      glcanvas.setSize(1000, 600);
	      
	      final JFrame frame = new JFrame ("Line Clipping");
	      //adding canvas to frame
	      frame.getContentPane().add(glcanvas);
	      frame.setSize(frame.getContentPane().getPreferredSize());
	      frame.setVisible(true);
	      
	   }
   public void display(GLAutoDrawable drawable) {
	   gl = drawable.getGL().getGL2();
	   
	   //final GL2 gl = drawable.getGL().getGL2();
       	  //gl.glBegin (GL2.GL_POINTS);//static field
       	 
	   
	   for(int i=0;i<10;i++){

         float x0 = (float) ((Math.random() * 2)-1);
         float y0 = (float) ((Math.random() * 2)-1);
         
         float x1 = (float) ((Math.random() * 2)-1);
         float y1 = (float) ((Math.random() * 2)-1);
         
         
//         float x0 = -0.75f;
//         float y0 =0.80f ;
//         
//         float x1 = 0.70f;
//         float y1 = -0.50f;

        // float x1 = (float) ((Math.random() * 2)-1);
         //float y1 = (float) ((Math.random() * 2)-1);

         
         
         int top=8;
         int  bottom=4;
         int  right=2;
         int  left=1;
         
         float xmin=-0.45f;
         float ymin=-0.45f;
         float xmax=0.45f;
         float ymax=0.45f;
         
         
         gl.glBegin (GL2.GL_LINES);
        
         gl.glVertex2d(xmax,ymax);
         gl.glVertex2d(xmin,ymax);
         
         gl.glVertex2d(xmin,ymax);
         gl.glVertex2d(xmin,ymin);
         
         gl.glVertex2d(xmin,ymin);
         gl.glVertex2d(xmax,ymin);
         
         gl.glVertex2d(xmax,ymin);
         gl.glVertex2d(xmax,ymax);
         
         gl.glEnd();
         
         int Outcode=0;
         

         
         int Outcode_0=Makecode(x0,y0);
         int Outcode_1=Makecode(x1,y1);
         
         
         boolean accept=false;
         
         
         
         while(true){
        	 
        	 
        	 
             int and=Outcode_0 & Outcode_1;
             int or=Outcode_0 | Outcode_1;
             
             
         if (or==0){
        	 System.out.println("fully accepted");
        	 //accept=true;
        	 gl.glColor3f(0,1,0);
        	 DrawLine(x0,y0,x1,y1);
        	 break;
         }
         
             else if(and>0){
        	 System.out.println("fully rejected");
        	 gl.glColor3f(1,0,0);
        	 DrawLine(x0,y0,x1,y1);
        	 break;
         }
         
         
         else{
        	 float x,y;
        	 
        	 if (Outcode_0>0){
        		 Outcode=Outcode_0;
        	 }
        	 
        	 else{
        		 Outcode=Outcode_1;
        	 }
        	 
        	 int OandT=Outcode & top;
        	 int OandB=Outcode & bottom;
        	 int OandR=Outcode & right;
        	 int OandL=Outcode & left;
        	 if(OandT>0){
        		 y=ymax;
        		 x=x0+((ymax-y0)/(y1-y0))*(x1-x0);
        	 }
        	 
        	 else if(OandB>0){
        		 y=ymin;
        		 x=x0+((ymin-y0)/(y1-y0))*(x1-x0);
        		 
        	 }else if(OandR>0){
        		 x=xmax;
        		 y=y0+((xmax-x0)/(x1-x0))*(y1-y0);
        		 
        	 }else {
        		 x=xmin;
        		 y=y0+((xmin-x0)/(x1-x0))*(y1-y0);
        	 }
        	 
        	 if(Outcode == Outcode_0){
        		 x0=x;
        		 y0=y;
        		 Outcode_0=Makecode(x0,y0);
        	 }else{
        		 x1=x;
        		 y1=y;
        		 Outcode_1=Makecode(x1,y1);
        	 }
        	// System.out.println("partially accepted");
        	 //DrawLine(x0,y0,x1,y1);
        	 
         }
         
       
         
         } 
         
   }
   }
   
   public int Makecode(float x0,float y0){
	   
	   
	   float top=8;
       float bottom=4;
       float right=2;
       float left=1;
       
       float xmin=-0.45f;
       float ymin=-0.45f;
       float xmax=0.45f;
       float ymax=0.45f;
       
       
       
       
       
       int code=0;
       
       
       if(y0>ymax){
      	 code+=top;
      	 
       }else if(y0<ymin){
      	 code+=bottom;
      	 
       }
       
       if(x0>xmax){
      	 code+=right;
      	 
       }else if(x0<xmin){
      	 code+=left;
      	 
       }

	   
	   
	   
	   return code;
   }
   
   
   
   public void DrawLine(float x1,float y1,float x2,float y2){
	  
	   
	   int zone=9;
      	
      	float dx=x2-x1;
     //System.out.println(dx);
       float dy=y2-y1;
     //System.out.println(dy);
      	
       
       
       if(dx>0 && dy>0){
      		if(Math.abs(dy)>Math.abs(dx)){
      			zone=1;
      		}else{
      			zone=0;
      		}
      	}else if(dx<0 && dy>0){
      		if(Math.abs(dy)>Math.abs(dx)){
      			zone=2;
      		}else{
      			zone=3;
      		}
      	}else if(dx<0 && dy<0){
      		if(Math.abs(dy)>Math.abs(dx)){
      			zone=5;
      		}else{
      			zone=4;
      		}
      	}else if(dx>0 && dy<0){
      		if(Math.abs(dy)>Math.abs(dx)){
      			zone=6;
      		}else{
      			zone=7;
      		}
      	}
       
       if(zone==0){
       	
       	gl.glBegin (GL2.GL_POINTS);//static field
        	 // gl.glColor3f(0,1,0);
        	 gl.glVertex2d(x1,y1);
       	
       	 float dint=dy-(dx/2);
      	  System.out.println("dint:"+dint);
      	  float de=dy;
            float dne=dy-dx;
      	  
      	  while(x1<x2){
      		  dx=x2-x1;
                dy=y2-y1;
                
      		  if (dint<0){
      			  x1+=0.001;
      			  dint=dint+de;
      			  gl.glVertex2d(x1,y1);
      		  }else{
      			  x1+=0.001;
      			  y1+=0.001;
      			  dint=dint+dne;
      			  gl.glVertex2d(x1,y1);
      		  }
      	  }
      	gl.glVertex2d(x2,y2);
      	gl.glEnd();}
       
       
      else if(zone==1){
       	gl.glBegin (GL2.GL_POINTS);//static field
      	 // gl.glColor3f(1,0,0);
      	 gl.glVertex2d(x1,y1);
       	
       	
       	float dint=(dy/2)-dx;
     	  System.out.println("dint:"+dint);
     	  float dn=-dx;
           float dne=dy-dx;
     	  
     	  while(y1<y2){
     		  dx=x2-x1;
               dy=y2-y1;
               
     		  if (dint>0){
     			  y1+=0.001;
     			  dint=dint+dn;
     			  gl.glVertex2d(x1,y1);
     		  }else{
     			  x1+=0.001;
     			  y1+=0.001;
     			  dint=dint+dne;
     			  gl.glVertex2d(x1,y1);
     		  }
     	  }
     	gl.glVertex2d(x2,y2);
     	gl.glEnd();
       }else if(zone==2){
       	gl.glBegin (GL2.GL_POINTS);//static field
      	 // gl.glColor3f(0,0,1);
      	 gl.glVertex2d(x1,y1);
       	
       	
       	 float dint=-dx-(dy/2);
      	  System.out.println("dint:"+dint);
      	  float dn=-dx;
            float dnw=-dy-dx;
      	  
      	  while(y1<y2){
      		  dx=x2-x1;
                dy=y2-y1;
                
      		  if (dint<0){
      			  y1+=0.001;
      			  dint=dint+dn;
      			  gl.glVertex2d(x1,y1);
      		  }else{
      			  x1-=0.001;
      			  y1+=0.001;
      			  dint=dint+dnw;
      			  gl.glVertex2d(x1,y1);
      		  }
      	  }
      	gl.glVertex2d(x2,y2);
      	gl.glEnd();
       }else if(zone==3){
       	
       	gl.glBegin (GL2.GL_POINTS);//static field
      	 // gl.glColor3f(1,1,1);
      	 gl.glVertex2d(x1,y1);
       	
       	
       	 float dint=-dy-(dx/2);
         	  System.out.println("zone:"+zone);
         	  float dw=-dy;
               float dnw=-dy-dx;
         	  
         	  while(x1>x2){
         		  dx=x2-x1;
                   dy=y2-y1;
                   
         		  if (dint>0){
         			  x1-=0.001;
         			  dint=dint+dw;
         			  gl.glVertex2d(x1,y1);
         		  }else{
         			  x1-=0.001;
         			  y1+=0.001;
         			  dint=dint+dnw;
         			  gl.glVertex2d(x1,y1);
         		  }
         	  }
         	gl.glVertex2d(x2,y2);
         	gl.glEnd();
       }else if(zone==4){
       	gl.glBegin (GL2.GL_POINTS);//static field
      	 // gl.glColor3f(1,1,0);
      	 gl.glVertex2d(x1,y1);
       	
       	
       	float dint=-dy-(dx/2);
       	  System.out.println("zone:"+zone);
       	  float dw=-dy;
             float dsw=-dy+dx;
       	  
       	  while(x1>x2){
       		  dx=x2-x1;
                 dy=y2-y1;
                 
       		  if (dint<0){
       			  x1-=0.001;
       			  dint=dint+dw;
       			  gl.glVertex2d(x1,y1);
       		  }else{
       			  x1-=0.001;
       			  y1-=0.001;
       			  dint=dint+dsw;
       			  gl.glVertex2d(x1,y1);
       		  }
       	  }
       	  gl.glVertex2d(x2,y2);
       	  gl.glEnd();
       }else if(zone==5){
       	
       	gl.glBegin (GL2.GL_POINTS);//static field
      	//  gl.glColor3f(1,0,1);
      	 gl.glVertex2d(x1,y1);
       	
       	float dint=-(dy/2)+dx;
     	  System.out.println("zone:"+zone);
     	  float ds=dx;
           float dsw=-dy+dx;
     	  
     	  while(y1>y2){
     		  dx=x2-x1;
               dy=y2-y1;
               
     		  if (dint>0){
     			  y1-=0.001;
     			  dint=dint+ds;
     			  gl.glVertex2d(x1,y1);
     		  }else{
     			  x1-=0.001;
     			  y1-=0.001;
     			  dint=dint+dsw;
     			  gl.glVertex2d(x1,y1);
     		  }
     	  }
     	gl.glVertex2d(x2,y2);
     	gl.glEnd();
       	
       }else if(zone==6){
       	
       	gl.glBegin (GL2.GL_POINTS);//static field
      	//  gl.glColor3f(0,1,1);
      	 gl.glVertex2d(x1,y1);
       	
       	
       	float dint=(dy/2)+dx;
       	  System.out.println("zone:"+zone);
       	  float ds=dx;
             float dse=dy+dx;
       	  
       	  while(y1>y2){
       		  dx=x2-x1;
                 dy=y2-y1;
                 
                 
       		  if (dint<0){
       			  y1-=0.001;
       			  dint=dint+ds;
       			  gl.glVertex2d(x1,y1);
       		  }else{
       			  x1+=0.001;
       			  y1-=0.001;
       			  dint=dint+dse;
       			  gl.glVertex2d(x1,y1);
       		  }
       	  }
       	  gl.glVertex2d(x2,y2);
       	  gl.glEnd();
       }else if(zone==7){
    	  // System.out.println("zone:"+zone);
       	gl.glBegin (GL2.GL_POINTS);//static field
      //	  gl.glColor3f(0.2f,0.3f,0.5f);
      	 gl.glVertex2d(x1,y1);
       	
       	
       	float dint=(dx/2)+dy;
     	  System.out.println("zone:"+zone);
     	  float de=dy;
           float dse=dy+dx;
     	  
     	  while(x1<x2){
     		  dx=x2-x1;
               dy=y2-y1;
               
     		  if (dint>0){
     			  x1+=0.001;
     			  dint=dint+de;
     			  gl.glVertex2d(x1,y1);
     		  }else{
     			  x1+=0.001;
     			  y1-=0.001;
     			  dint=dint+dse;
     			  gl.glVertex2d(x1,y1);
     		  }
     	  }
     	
       }
      	
       gl.glVertex2d(x2,y2);
   
       gl.glEnd();
      		
	   
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
}//end of  import javax.media.opengl.GL2;
