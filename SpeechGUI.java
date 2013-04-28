package VoiceAuthentication;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpeechGUI extends DecryptOrNew {
    static JLabel speakNow;

    protected JFrame frame;
    protected JTextField Username;
    protected JTable table;
    protected JFormattedTextField dtrpnWelcomeusername;
    protected JTextField textField_1;
    protected JTable table_1;
    protected int x = 0;
    protected Vector<String> columnNames;
    protected boolean fileChanged = false;
    @SuppressWarnings("unused")
    private JTable table_2;
    private boolean match = false;
    protected JTextArea Print;
    protected String Text;
  
 

   

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    SpeechGUI window = new SpeechGUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
    private JTextArea Print1;
    

    /**
     * Create the application.
     */
    public SpeechGUI() {
        initialize();
    }

    /**
     * getUsername
     */
    public String getUsername() {
        return Username.getText();
    }
    
   /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        


        frame = new JFrame();
    	frame.setTitle("Password Vault");
        frame.setBounds(100, 100, 997, 678);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JButton ADD = new JButton("ADD");
        ADD.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			((DefaultTableModel) table.getModel())
    				.addRow(new java.util.Vector<String>(java.util.Arrays
    					.asList(new String[] { String.valueOf(x++), "",
    						"" })));
    			}
    		});
// first pane
        final JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 979, 607);
        frame.getContentPane().add(layeredPane);
        layeredPane.setVisible(true);

        final JTextPane txtpnYouShallNot = new JTextPane();
        txtpnYouShallNot.setBounds(451, 272, 323, 64);
        layeredPane.add(txtpnYouShallNot);
        txtpnYouShallNot.setVisible(false);
        txtpnYouShallNot.setText("Please Try Again");

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(107, 156, 72, 16);
        layeredPane.add(lblUsername);

        final JTextField Username_1 = new JTextField();
        Username_1.setBounds(64, 195, 155, 22);
        layeredPane.add(Username_1);
        Username_1.setColumns(8);

        final JLabel speakNow = new JLabel("Speak Now");
	speakNow.setBounds(105, 135, 150, 16);
	layeredPane.add(speakNow);
	speakNow.setVisible(false);

        JButton btnSpeak = new JButton("SPEAK");
        btnSpeak.setBounds(96, 242, 97, 25);
        layeredPane.add(btnSpeak);

        dtrpnWelcomeusername = new JFormattedTextField();
        dtrpnWelcomeusername.setText("Welcome");
        dtrpnWelcomeusername.setBounds(396, 55, 407, 57);
        layeredPane.add(dtrpnWelcomeusername);
        dtrpnWelcomeusername.setVisible(true);
        dtrpnWelcomeusername.setFont(dtrpnWelcomeusername.getFont().deriveFont(
                dtrpnWelcomeusername.getFont().getSize() + 20f));
        dtrpnWelcomeusername.setHorizontalAlignment(SwingConstants.CENTER);
        dtrpnWelcomeusername.setEditable(false);
        dtrpnWelcomeusername.setAlignmentX(Component.RIGHT_ALIGNMENT);

        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(354, 115, 536, 265);
        scrollPane.setVisible(false);
        layeredPane.add(scrollPane);
        table = new JTable();
        scrollPane.setViewportView(table);

        final Vector<String> columnNames = new Vector<String>(3);
        columnNames.addElement("Website");
        columnNames.addElement("Username");
        columnNames.addElement("Password");
        
        final JScrollPane TextScroll = new JScrollPane();
        TextScroll.setBounds(72,100,150,45);
        //TextScroll.setVisible(false);
        layeredPane.add(TextScroll);
        
        Print = new JTextArea();
        TextScroll.setViewportView(Print);
        Print.setEditable(false);
        TextAreaOutputStream textOut = new TextAreaOutputStream(Print);
        PrintStream outStream = new PrintStream(textOut, true);
        System.setOut(outStream);
        
        
        btnSpeak.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scrollPane.setVisible(false);
                txtpnYouShallNot.setVisible(false);
                TextScroll.setVisible(true);
                dtrpnWelcomeusername.setText(Username_1.getText());
                VoiceFunctions Authenticator = new VoiceFunctions(Username_1.getText());
                try {
                                           
                    if (Authenticator.compareSample() == true) { 
                        
                        match = true;
                        ReturnDecrypted(match, Username_1.getText());
                        InsertFileDataToJTable FileData = new InsertFileDataToJTable("decrypted.txt");
                        Vector<Vector<String>> data = FileData.getData();
                        DefaultTableModel model = new DefaultTableModel(data, columnNames);
                        table.setModel(model);
                        table.setVisible(true);
                        scrollPane.setVisible(true);
                        dtrpnWelcomeusername.setText("Welcome "
                                + Username_1.getText());
                        dtrpnWelcomeusername.setVisible(true);
                        speakNow.setVisible(false);
                        TextScroll.setVisible(false);
                    } else if (match == false) {
                        txtpnYouShallNot.setVisible(true);

                    }
                } catch (Exception ex) {
                    Logger.getLogger(SpeechGUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });


        JButton btnAdd = new JButton("ADD");
        btnAdd.setBounds(513, 431, 97, 25);
        layeredPane.add(btnAdd);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((DefaultTableModel) table.getModel())
                        .addRow(new java.util.Vector<String>(java.util.Arrays
                        .asList(new String[]{String.valueOf(x++), "",
                            ""})));

            }
        });

        final JButton btnSave = new JButton("Save");
        btnSave.setBounds(649, 431, 97, 25);
        layeredPane.add(btnSave);
        btnSave.setEnabled(true);
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }
                try {
                    StringBuffer fileContent = new StringBuffer();
                    TableModel tModel = table.getModel();
                    for (int i = 0; i < tModel.getRowCount(); i++) {

                        Object cellValue = tModel.getValueAt(i, 0);
                        Object cellValue1 = tModel.getValueAt(i, 1);
                        Object cellValue2 = tModel.getValueAt(i, 2);
                        fileContent.append(cellValue + " ");
                        fileContent.append(cellValue1 + " ");
                        fileContent.append(cellValue2 + "\r\n");

                    }

                    FileWriter fileWriter = new FileWriter(new File(Username_1
                            .getText() + ".txt"));
                    fileWriter.write(fileContent.toString());
                    fileWriter.flush();
                    fileWriter.close();
                    
                } catch (IOException e1) {
// TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                File newUserFile = new File(Username_1.getText() + ".txt");
                    try {
                        encryptNew(Username_1.getText(), newUserFile);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(SpeechGUI.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Throwable ex) {
                        Logger.getLogger(SpeechGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

        });
//add new user pane
        final JLayeredPane layeredPane_1 = new JLayeredPane();
        layeredPane_1.setBounds(0, 0, 979, 607);
        frame.getContentPane().add(layeredPane_1);
        layeredPane_1.setVisible(false);

        textField_1 = new JTextField();
        textField_1.setBounds(115, 214, 116, 22);
        layeredPane_1.add(textField_1);
        textField_1.setColumns(10);

        JLabel lblAddUsername = new JLabel("Add Username");
        lblAddUsername.setBounds(127, 187, 84, 16);
        layeredPane_1.add(lblAddUsername);
        
        final JLabel RecordSample = new JLabel("Record Sample");
      	RecordSample.setBounds(129, 167, 150, 16);
      	layeredPane_1.add(RecordSample);
      	RecordSample.setVisible(true);
      		
      	final JLabel RecordNow = new JLabel("Record Now");
      	RecordNow.setBounds(129, 167, 150, 16);
      	layeredPane_1.add(RecordNow);
      	RecordNow.setVisible(false);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(354, 115, 536, 265);
        layeredPane_1.add(scrollPane_1);
        String columnName[] = {"Website", "Username", "Password"};
        String[][] data = {{"ADD", "DATA", "HERE"}};
        final DefaultTableModel table1model = new DefaultTableModel(data,
                columnName);
        table_1 = new JTable(table1model);
        scrollPane_1.setViewportView(table_1);
        /*final JTextArea Print1 = new JTextArea();
        Print1.setBounds(100,125,150,15);
        Print1.setEditable(false);
        Print1.setVisible(false);
        layeredPane_1.add(Print1);
         */
       
        
       
        //TextAreaOutputStream textOut1 = new TextAreaOutputStream(Print1);
        //PrintStream outStream1 = new PrintStream(textOut1, true);
        //System.setOut(outStream1);
        
        
        final JButton btnAddNewUser = new JButton("Add New User");
        btnAddNewUser.setVisible(false);
        btnAddNewUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 TextScroll.setVisible(true);
                 
                if (table_1.isEditing()) {
                    table_1.getCellEditor().stopCellEditing();
                }
                try {
                    StringBuffer fileContent = new StringBuffer();
                    TableModel tModel = table_1.getModel();
                    for (int i = 0; i < tModel.getRowCount(); i++) {

                        Object cellValue = tModel.getValueAt(i, 0);
                        Object cellValue1 = tModel.getValueAt(i, 1);
                        Object cellValue2 = tModel.getValueAt(i, 2);
                        fileContent.append(cellValue + " ");
                        fileContent.append(cellValue1 + " ");
                        fileContent.append(cellValue2 + "\r\n");

                    }

                    FileWriter fileWriter = new FileWriter(new File(textField_1
                            .getText() + ".txt"));
                    fileWriter.write(fileContent.toString());
                    fileWriter.flush();
                    fileWriter.close();
                    //TextScroll.setVisible(false);
                    File newUserFile = new File(textField_1.getText() + ".txt");
                    try {
                        encryptNew(textField_1.getText(), newUserFile);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(SpeechGUI.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Throwable ex) {
                        Logger.getLogger(SpeechGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });
        btnAddNewUser.setBounds(115, 278, 116, 25);
        layeredPane_1.add(btnAddNewUser);

        final JButton button = new JButton("Save");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table_1.isEditing()) {
                    table_1.getCellEditor().stopCellEditing();
                }
                try {
                    StringBuilder fileContent = new StringBuilder();
                    TableModel tModel = table_1.getModel();
                    for (int i = 0; i < tModel.getRowCount(); i++) {

                        Object cellValue = tModel.getValueAt(i, 0);
                        Object cellValue1 = tModel.getValueAt(i, 1);
                        Object cellValue2 = tModel.getValueAt(i, 2);
                        fileContent.append(cellValue).append(" ");
                        fileContent.append(cellValue1).append(" ");
                        fileContent.append(cellValue2).append("\r\n");

                    }

                    FileWriter fileWriter = new FileWriter(new File(textField_1
                            .getText() + ".txt"));
                    fileWriter.write(fileContent.toString());
                    fileWriter.flush();
                    fileWriter.close();
                    String userName = new String();
                    if (layeredPane.isVisible() == true) {
                        userName = Username_1.getText();
                    } else {
                        userName = textField_1.getText();
                    }
                    try {
                        File appendedPassFile = new File(userName + ".txt");
                        encryptNew(userName, appendedPassFile);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(SpeechGUI.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Throwable ex) {
                        Logger.getLogger(SpeechGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException e1) {
                	// TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        button.setBounds(649, 431, 97, 25);
        layeredPane_1.add(button);

        JButton button_1 = new JButton("ADD");
        button_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ((DefaultTableModel) table_1.getModel())
                        .addRow(new java.util.Vector<String>(java.util.Arrays
                        .asList(new String[]{String.valueOf(x++), "",
                            ""})));

            }
        });
        button_1.setBounds(513, 431, 97, 25);
        layeredPane_1.add(button_1);
        
        final JButton btnRecordSample = new JButton("Record Sample");
        btnRecordSample.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             
                //TextAreaOutputStream taos = TextAreaOutputStream.getInstance(Print1);
                //call to record speechsample
                VoiceFunctions recordNew = new VoiceFunctions(textField_1.getText());
                try {
                    recordNew.recordNewUser();
                } catch (Exception ex) {
                    Logger.getLogger(SpeechGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                RecordSample.setVisible(false);
                btnRecordSample.setVisible(false);
                btnAddNewUser.setVisible(true);
                

            }
        });
        btnRecordSample.setBounds(98, 278, 150, 25);
        layeredPane_1.add(btnRecordSample);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenuItem mntmAddnewuser = new JMenuItem("AddNewUser");
		mntmAddnewuser.setMaximumSize(new Dimension(20, 32767));
		mntmAddnewuser.setBounds(new Rectangle(0, 0, 20, 0));
		mntmAddnewuser.addActionListener(new ActionListener() {
                        @Override
			public void actionPerformed(ActionEvent e) {
				layeredPane.setVisible(false);
				layeredPane_1.setVisible(true);
                                layeredPane_1.add(TextScroll);
			}
		});
		menuBar.add(mntmAddnewuser);

        JMenuItem mntmExit = new JMenuItem("Exit");
        mntmExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //reencypt
               
                String userName;
                if (layeredPane.isVisible() == true) {
                    userName = Username_1.getText();
                } else {
                    userName = textField_1.getText();
                }
              
                System.exit(0);
                
            }
        });
        menuBar.add(mntmExit);

        
    }

}
