package net.jc_mouse.app;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
/**
 * @web http://wwww.jc-mouse.net/
 * @author mouse
 */
public class MazePanel extends JPanel{
        
    private int level=0;//para controlar el nivel en donde se encuentra el jugador
    private GeneralPath mainRoute;//el poligono que forma el camino 
    private Polygon target;//rectangulo que forma la meta del juego
    private final Image face = new ImageIcon(getClass().getResource("/net/jc_mouse/app/linda_blair.jpg")).getImage();     
    //para el sonido
    private Clip clip;
    private boolean play = false;
    /**
     * Constructor de clase
     */
    public MazePanel(){
        MazePanel.this.setPreferredSize(new Dimension(1000,600));
        //listener
        MazePanel.this.addMouseListener(new MouseHandler());
        MazePanel.this.addMouseMotionListener(new MouseMotionHandler());
        //se cambia cursor
        Image mano = new ImageIcon(getClass().getResource("/net/jc_mouse/app/custom_cursor.png")).getImage();        
        Cursor miCursor= MazePanel.this.getToolkit().createCustomCursor(mano, new Point(2,2), "custom cursor");        
        MazePanel.this.setCursor(miCursor);
    }
    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 =(Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);            
        g2.setColor( new Color(0,0,0) );
        g2.fill(new Rectangle2D.Double(0,0,getWidth(),getHeight()));
        //pinta segun el nivel donde se encuentre
        switch(level) {
            case 0: drawMenu(g2);break; //menu del juego
            case 1: drawLevel1(g2);break; //nivel 1 - facil
            case 2: drawLevel2(g2);break; //nivel 2 - medio
            case 3: drawLevel3(g2);break; //nivel 3 - dificil
            case 4: drawFace(g2); break;//imagen de susto :)
            case 5: drawGameOver(g2);break; //imagen Game Over
        }
    }
    
    /**
     * Menu del juego
    */
    private void drawMenu(Graphics2D g2){
        g2.setColor( new Color(255,255,255) );
        g2.fill(new Rectangle2D.Double(0,0,getWidth(),getHeight()));
        
        g2.setFont(new Font("Tahoma", Font.BOLD, 32));    
        g2.setColor( new Color(0,0,0) );        
        g2.drawString("CLIC PARA JUGAR", getWidth()/2-130, getHeight()/2);
    }
    
    /**
     * Pantalla Game Over
     */
    private void drawGameOver(Graphics2D g2){
        g2.setColor( new Color(255,255,255) );
        g2.fill(new Rectangle2D.Double(0,0,getWidth(),getHeight()));
        g2.setColor( new Color(0,0,0) );
        g2.setFont(new Font("Tahoma", Font.BOLD, 32));    
        g2.drawString("GAME OVER", getWidth()/2-100, getHeight()/2);
    }
    
   /**
    * Nivel 1
    */
    private void drawLevel1(Graphics2D g2){        
        g2.setColor( new Color(0,255,255) );                          
        int x1Points[] = {600,600,700,700,300,300,550,550};  
        int y1Points[] = {100,200,200,550,550,200,200,100};
        mainRoute = new GeneralPath(GeneralPath.WIND_EVEN_ODD,x1Points.length);                 
        mainRoute.moveTo(x1Points[0], y1Points[0]); 
        for(int i=0;i<x1Points.length;i++){
            mainRoute.lineTo(x1Points[i], y1Points[i]);     
        }
        mainRoute.closePath();
        g2.fill(mainRoute ); 
        //objetivo de color rojo
        g2.setColor( new Color(255,0,0) );   
        target = new Polygon();
        target.addPoint( 545,38 );
        target.addPoint( 545+60,38 );
        target.addPoint( 545+60,38+66 );
        target.addPoint( 545,38+66 );
        g2.fillPolygon(target );
    }
    
    /**
     * Nivel 2
     */
    private void drawLevel2(Graphics2D g2){        
        g2.setColor( new Color(0,255,255) );                          
        int x1Points[] = {951,51,51,899,899,51,51,951,951,199,199,951,951,82,82,951};  
        int y1Points[] = {80,80,219,219,331,331,547,547,449,449,400,400,185,185,100,100};
        mainRoute = new GeneralPath(GeneralPath.WIND_EVEN_ODD,x1Points.length);                 
        mainRoute.moveTo(x1Points[0], y1Points[0]); 
        for(int i=0;i<x1Points.length;i++){
            mainRoute.lineTo(x1Points[i], y1Points[i]);     
        }
        mainRoute.closePath();
        g2.fill(mainRoute ); 
        //objetivo de color rojo
        g2.setColor( new Color(255,0,0) );   
        target = new Polygon();
        target.addPoint( 870,20 );
        target.addPoint( 951,20 );
        target.addPoint( 951,80 );
        target.addPoint( 870,80 );
        g2.fillPolygon(target );
    }
    
    /**
     * Nivel 3
     */
    private void drawLevel3(Graphics2D g2){        
        g2.setColor( new Color(0,255,255) );                          
        int x1Points[] = {500,500,446,446,504,504,421,421,232,232,800,800,200,200,765,765,200,200,496,495,421,421,495,495};  
        int y1Points[] = {92,114,114,141,141,201,201,220,220,331,331,549,549,504,504,400,400,185,185,147,147,109,109,92};
        mainRoute = new GeneralPath(GeneralPath.WIND_EVEN_ODD,x1Points.length);                 
        mainRoute.moveTo(x1Points[0], y1Points[0]); 
        for(int i=0;i<x1Points.length;i++){
            mainRoute.lineTo(x1Points[i], y1Points[i]);     
        }
        mainRoute.closePath();
        g2.fill(mainRoute ); 
        //objetivo de color rojo
        g2.setColor( new Color(255,0,0) );   
        target = new Polygon();
        target.addPoint( 467,40 );
        target.addPoint( 467+60,40 );
        target.addPoint( 467+60,100 );
        target.addPoint( 467,100 );
        g2.fillPolygon(target );
    }
       
    /*
     * pinta la imagen que debe dar miedo
     */
    private void drawFace(Graphics2D g2){
        g2.drawImage(face, 0, 0, null);
    }
    
    /**
     * reproduce sonido
     */
    private void playSound(){    
        if(!play)
        try {
            clip = AudioSystem.getClip();
            InputStream audioSrc = getClass().getResourceAsStream("/net/jc_mouse/app/grito.wav");
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);            
            clip.open(audioStream);
            clip.start(); 
            play = true;
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog (null, "" + e.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
        }             
    }
    /**
     * mueve el cursor a las coordenas X Y pasados por parametros
     * ajustando las coordenas con relación a la posicion del JPanel en pantalla
     * @param x
     * @param y
     */
    private void mouseXY(int x, int y){
        Point pt = new Point(MazePanel.this.getLocation()); 
        SwingUtilities.convertPointToScreen(pt, MazePanel.this);         
        Robot r;
        try {
            r = new Robot();            
            r.mouseMove(pt.x+ x,pt.y + y);
        } catch (AWTException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * Clase privada para el manejo de los eventos del raton
     */
    private class MouseHandler extends MouseAdapter{
                
        @Override
        public void mouseClicked(MouseEvent e){         
            if(level==0){//comenzar juego            
                level = 1;//nivel 1                
                mouseXY(550,500);
                repaint();
            }else if(level==5 || level==4){//Game Over
                level = 0;//menu principal
                if(clip!=null)clip.stop();
                play = false;                
                repaint();
            }            
        }
        
    }
    
    /**
     * Clase provada para el manejo de los eventos del raton
     */
    private class MouseMotionHandler extends MouseMotionAdapter{        
        
        @Override
        public void mouseMoved(MouseEvent e){   
            if(level!=5 && level !=4 && level!= 0)
                if(mainRoute!=null)
                        if(target.contains(e.getPoint()) ){
                           switch(level){
                               case 1: level = 2; mouseXY(925,504);break;
                               case 2: level = 3; mouseXY(220,528);break;
                               case 3: level = 4; playSound(); break;
                           }                    
                           repaint();
                       }else if(!mainRoute.contains(e.getPoint())){
                            level = 5;
                            repaint();
                        }       
        }
        
    }
}//MazePanel:end
