import java.awt.*;
import java.util.*;
public class Food{
	public int x;
	public int y;
	boolean inCharacter;
	int scl = 20;
	Color red = new Color(255, 87, 0);
	public Food(){
		inCharacter = false;
		x = (int)(Math.random()* 40);
		y = (int)(Math.random()* 40);
	}
	public void drawMe(Graphics g){
		g.setColor(red);
		g.fillRect(x*scl + 1, y*scl + 1, 18, 18);
		//System.out.println("Food Position: (" + x + ", " + y + ")");
	}
	public void changePosition(ArrayList<Integer> xi, ArrayList<Integer> yi){
		x = (int)(Math.random()* 40);
		y = (int)(Math.random()* 40);
		for(int i = 0; i < xi.size(); i ++){
			if(xi.get(i) == x && yi.get(i) == y){
				inCharacter = true;
			}
		}
		System.out.println("");
		while (inCharacter){
			System.out.println("inCharacter");
			x = (int)(Math.random()* 40);
			y = (int)(Math.random()* 40);
			for(int i = 0; i < xi.size(); i ++){
				if(xi.get(i) != x || yi.get(i) != y){
					inCharacter = false;
				}
			}
		}
	}
}