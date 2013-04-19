import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class InsertDataFinal{

private Vector<Vector<String>> myVector = new Vector<Vector<String>>();

public InsertDataFinal(String Username) {
    	
		String fileName = (Username + ".txt");

		
		try {
		    FileReader fReader = new FileReader(fileName);
		    BufferedReader inFile = new BufferedReader(fReader);
		    String input;
		    while ((input = inFile.readLine()) != null) {
		        String[] temp = input.split(" ");
		        Vector<String> v = new Vector<String>(3);
		        for (int i = 0; i < temp.length; i++) {
		            v.add(temp[i]);
		        }
		        myVector.add(v);
		    }
		} catch (IOException e) {
		    e.printStackTrace(System.err);
		}
}					
		
		
		

public Vector<Vector<String>> getData() {
	// TODO Auto-generated method stub
	return myVector;
}

}
		//http://www.serkey.com/data-from-text-file-into-jtable-bftsqk.html
