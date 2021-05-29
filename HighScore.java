import java.io.*;
public class HighScore{
	int[] highScores = new int[3];
	String[] highScoreName = new String[3];

	BufferedWriter writer;
	BufferedReader reader;
	public void writeScores(){
		try{
			    File file = new File("scores.txt");

			    writer = new BufferedWriter(new FileWriter(file));
			    for (int i = 0; i < highScores.length ; i++ ) {
			    	writer.write(highScores[i] + " - " + highScoreName[i]);
			    }
			    
			    writer.flush();

		   	}catch(IOException e){
						e.printStackTrace();
			}finally {
			    try {
					writer.close();
				}catch(IOException e){
					e.printStackTrace();
				}
				
		}
	}
	public void readScores(){
		int count = 0;
		try {
		    File file = new File("scores.txt");
		    reader = new BufferedReader(new FileReader(file));

		    String line;
		    while ((line = reader.readLine()) != null) {
		    	highScores[count] = Integer.parseInt(line.substring(0, line.indexOf(' ')));
		    	highScoreName[count] = line.substring(line.lastIndexOf('-') + 1, line.length());
		        count++;
		    }
		    reader.close();

		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        reader.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
	}
	public int[] getScores(){
		return highScores;
	}
	public String[] getNames(){
		return highScoreName;
	}
}