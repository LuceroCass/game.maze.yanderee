package net.jc_mouse.app;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
/**
 * @web http://www.jc-mouse.net/
 * @author mouse
 */
public class MazeFrame extends JFrame{
 
    /**
     * Constructor de clase
     */
    public MazeFrame(){      
        initComponents();        
        MazeFrame.this.setLocationRelativeTo(null);            
        MazeFrame.this.setResizable(false);
    }
    
    private void initComponents() {        
        setTitle("Maze of Fear - [http://www.jc-mouse.net/]");
        MazePanel panel = new MazePanel();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);        
        getContentPane().add(panel);
        pack();
    }
    
    public static void main(String args[]){
        EventQueue.invokeLater(() -> {
            new MazeFrame().setVisible(true);
        });
    }    
}
