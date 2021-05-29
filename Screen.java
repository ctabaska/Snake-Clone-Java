import java.awt.*;
import javax.swing.JPanel;
import java.util.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class Screen extends JPanel implements KeyListener
{	

	public int scl = 20;
	Player pl = new Player();
	Food fd = new Food();
	HighScore hs = new HighScore();
	Color graphite;
	boolean verticalM = false;
	boolean horizontalM = false;
	int rateOfRefresh;
	boolean scoreChange;
	boolean pause;
	Font[] hsFonts;

	public Screen(){
		setLayout(null);
		hsFonts = new Font[3];
		hsFonts[0] = new Font("Helvetica", Font.BOLD, 50);
		hsFonts[1] = new Font("Helvetica", Font.BOLD, 25);
		hsFonts[2] = new Font("Helvetica", Font.BOLD, 18);
		pause = false;
		scoreChange = false;
		rateOfRefresh = 140;
		graphite = new Color(53, 53, 53);
		hs.readScores();
		addKeyListener(this);
        setFocusable(true);
	}
	
	public Dimension getPreferredSize(){
		//Sets the size of the panel
        return new Dimension(800,800);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(graphite);
		g.fillRect(0,0, 800, 800);
		
		fd.drawMe(g);
		pl.drawMe(g);
		g.drawString("Score: " + pl.xi.size(), 0, 20);
		if(pl.movement == true){
			scoreChange = false;
		}
		if(pl.movement == false){
			g.drawString("You Died", 100, 400);
			for (int i = 0; i < 3 ; i++ ) {
				if(pl.xi.size() > hs.highScores[i] && scoreChange == false){
					for (int ii = 2; ii > i ; ii-- ) {
						hs.highScores[ii] = hs.highScores[ii - 1];
						hs.highScoreName[ii] = hs.highScoreName[ii - 1];
					}
					hs.highScoreName[i] = " ";
					hs.highScores[i] = pl.xi.size();
					scoreChange = true;
					break;
				}
			}
			for (int i = 0; i < 3 ; i++) {
				g.setFont(hsFonts[i]);
				g.drawString(hs.highScores[i] + " - " + hs.highScoreName[i], 200, 300 + (50*i));
			}
		}
	}
	public void keyTyped(KeyEvent e){}
	public void keyPressed(KeyEvent e){
		//System.out.println(e.getKeyCode());
		if(e.getKeyCode() == 39){
			if((!horizontalM || pl.getArraySize() <= 2) && !pl.keyAction ){
				pl.keyAction = true;
				pl.changeDirection(1, 0);
				horizontalM = true;
				verticalM = false;
			}
		} //right
		if(e.getKeyCode() == 40){
			if((!verticalM || pl.getArraySize() <= 2) && !pl.keyAction ){
				pl.keyAction = true;
				pl.changeDirection(0, 1);
				horizontalM = false;
				verticalM = true;
			}
		} //down
		if(e.getKeyCode() == 37){
			if((!horizontalM || pl.getArraySize() <= 2) && !pl.keyAction ){
				pl.keyAction = true;
				pl.changeDirection(-1, 0);
				horizontalM = true;
				verticalM = false;
			}
		} //left
		if(e.getKeyCode() == 38){
			if((!verticalM || pl.getArraySize() <= 2 ) && !pl.keyAction){
				pl.keyAction = true;
				pl.changeDirection(0, -1);
				horizontalM = false;
				verticalM = true;
			}
		} //up
		if(e.getKeyCode() == 32){
			if(pl.movement == false){
				rateOfRefresh = 140;
				pl = new Player();
				fd = new Food();
			}
		}
		if(e.getKeyCode() == 27){
			pause = !pause;
		}
	}
	public void keyReleased(KeyEvent e){}

	public void animate(){

		while( true ){
			try {
			    Thread.sleep(rateOfRefresh);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			if(pl.checkFood(fd.x, fd.y)){

				fd.changePosition(pl.xi, pl.yi );
			}
			//rateOfRefresh = (int) -Math.sqrt(20*pl.getArraySize()) + 140;
			rateOfRefresh = (int) ( 1000.0 / (pl.getArraySize() + 10) ) + 60;
			//System.out.println(rateOfRefresh);
			
			pl.move();
			
			repaint();
		}
	}
}
