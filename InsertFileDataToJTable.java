package VoiceAuthentication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class InsertFileDataToJTable{

private Vector<Vector<String>> myVector = new Vector<Vector<String>>();

public InsertFileDataToJTable(String Username) {
    
String fileName = (Username);


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
inFile.close();
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