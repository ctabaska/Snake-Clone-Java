import java.awt.*;
import java.util.*;
public class Player{
	ArrayList<Integer> xi = new ArrayList<Integer>();
	ArrayList<Integer> yi = new ArrayList<Integer>();
	
	int size;
	Color player = new Color(250, 255, 255);
	int scl = 20;
	int xMove;
	int yMove;

	boolean movement;

	boolean keyAction;

	public Player(){
		keyAction = false;
		movement = true;
		xi.add(0);
		yi.add(0);
		xMove = 1;
		yMove = 0;
	}
	public void drawMe(Graphics g){
		
		for (int i = 0 ; i < xi.size() ; i++ ) {
			
			g.setColor(player);
			g.fillRect(xi.get(i) * scl + 1, yi.get(i) * scl + 1, 18, 18);
		}
		
		//System.out.println(xi.size() + " " + yi.size());
		//System.out.println("Player Position: (" + xi.get(xi.size() - 1) + ", " + yi.get(yi.size() - 1) + ")");
	}
	public void move(){
		if(movement == true){
			for (int i = 0; i < xi.size() - 1; i++) {
				xi.set(i, xi.get(i + 1));
				yi.set(i, yi.get(i + 1));
			}
			xi.set(xi.size() -1, (int)xi.get(xi.size() - 1) + xMove);
			yi.set(yi.size() -1, (int)yi.get(yi.size() - 1) + yMove);
			checkCollision();
			keyAction = false;
		}
		
	}
	public void changeDirection(int xMove, int yMove){
		this.xMove = xMove;
		this.yMove = yMove;
	}
	public void addBlock(){
		xi.add(0, (int)xi.get(xi.size() - 1) - xMove);
		yi.add(0, (int)yi.get(yi.size() - 1) - yMove);
	}
	public boolean checkFood(int foodX, int foodY){
		if(xi.get(xi.size() - 1) == foodX && yi.get(xi.size() - 1) == foodY){
			addBlock();
			//System.out.println("BOY");
			return true;
		}
		return false;
	}

	public void checkCollision(){
		if(xi.get(xi.size() - 1) < 0 || xi.get(xi.size() - 1) > 39 || yi.get(yi.size() - 1) < 0 || yi.get(yi.size() - 1) > 39){
			movement = false;
		}
		for (int i = 0; i < xi.size() - 1 ; i++ ) {
			if( xi.get(xi.size() - 1) == xi.get(i) && yi.get(yi.size() - 1) == yi.get(i))
				movement = false;
		}
	}

	public int getArraySize(){
		return xi.size();
	}
}