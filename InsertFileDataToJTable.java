import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;


@SuppressWarnings("serial")
public class InsertFileDataToJTable extends AbstractTableModel {
   
	protected String line;
	protected Vector<String> data;
	protected Vector<String> columns;



	/**
	 * @return file
	 */
    public InsertFileDataToJTable(String Username) {
    	
    		String fileName = (Username + ".txt");
    		
            data = new Vector<String>();
           columns = new Vector<String>();
            
            try {            		
            		FileInputStream fis = new FileInputStream(fileName);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    columns.addElement("Website");
                    columns.addElement("Username");
                    columns.addElement("Password");
           
                    while ((line = br.readLine()) != null) {
                            StringTokenizer st2 = new StringTokenizer(line, " ");
                            while (st2.hasMoreTokens())
                                    data.addElement(st2.nextToken());
                    }
                    br.close();
            } catch (Exception e) {
                    e.printStackTrace();
            }
			
    }

    public int getRowCount() {
            return data.size() / getColumnCount();
    }
    public String getColumnName(int i){
       return (String)columns.get(i);
    }    
    public int getColumnCount() {
            return columns.size();
    }
    public Object getValueAt(int rowIndex, int columnIndex) {
            return (String) data.elementAt((rowIndex * getColumnCount())
                            + columnIndex);
    }
    public void addRow(Vector<String> vector) {
		// TODO Auto-generated method stub
		int rowIndex = data.size();
		    data.add(line);
		     newRowsAdded(new TableModelEvent(
		       this, rowIndex, rowIndex, -1, TableModelEvent.INSERT)
		     );

	}
    private void newRowsAdded(TableModelEvent tableModelEvent) {
    	// TODO Auto-generated method stub
    	 fireTableChanged(tableModelEvent);

    }
    /* public static void main(String s[]) {    	        
        
	   InsertFileDataToJTable model = new InsertFileDataToJTable("Password");
       JTable table = new JTable();
       table.setModel(model);
       JScrollPane scrollpane = new JScrollPane(table);
       JPanel panel = new JPanel();
       panel.add(scrollpane);
       JFrame frame = new JFrame();
       frame.add(panel, "Center");
       frame.pack();
       frame.setVisible(true);
       
}
*/

	


	
} 
