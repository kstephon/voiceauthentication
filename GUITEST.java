	import java.awt.*;
	import java.awt.event.*;

	import javax.swing.*;
	import javax.swing.event.TableModelEvent;
	import javax.swing.table.DefaultTableModel;
	import javax.swing.table.TableModel;
	import java.io.*;
import java.util.Vector;

	public class GUITEST {

		protected JFrame frame;
		protected JTextField Username;
		protected JTable table;
		protected JFormattedTextField dtrpnWelcomeusername;
		protected JTextField textField_1;
		protected JTable table_1;
		protected int x = 0;
		protected Vector<String> columnNames;
		@SuppressWarnings("unused")
		private JTable table_2;

		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						GUITEST window = new GUITEST();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

		/**
		 * Create the application.
		 */
		public GUITEST() {
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
			frame.setBounds(100, 100, 997, 678);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);

			JButton ADD = new JButton("ADD");
			ADD.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					((InsertFileDataToJTable) table.getModel())
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
			txtpnYouShallNot.setText("YOU SHALL NOT PASS");

			JLabel lblUsername = new JLabel("Username");
			lblUsername.setBounds(107, 156, 72, 16);
			layeredPane.add(lblUsername);

			final JTextField Username_1 = new JTextField();
			Username_1.setBounds(64, 195, 155, 22);
			layeredPane.add(Username_1);
			Username_1.setColumns(8);

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
			
			btnSpeak.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					InsertDataFinal FileData = new InsertDataFinal(Username_1.getText());
					Vector<Vector<String>> data = FileData.getData();
					DefaultTableModel model = new DefaultTableModel(data, columnNames);
					dtrpnWelcomeusername.setText(Username_1.getText());
					Authentication Authenticator = new Authentication(true);
					Authenticator.setUsername(Username_1.getText());

					if (Authenticator.isResult() == true) {
						table.setModel(model);
						table.setVisible(true);
						scrollPane.setVisible(true);
						dtrpnWelcomeusername.setText("Welcome "
								+ Username_1.getText());
						dtrpnWelcomeusername.setVisible(true);
					}
					if (Authenticator.isResult() == false) {
						txtpnYouShallNot.setVisible(true);
					}
				}
			});
			
			
			JButton btnAdd = new JButton("ADD");
			btnAdd.setBounds(513, 431, 97, 25);
			layeredPane.add(btnAdd);
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					((DefaultTableModel) table.getModel())
					.addRow(new java.util.Vector<String>(java.util.Arrays
							.asList(new String[] { String.valueOf(x++), "",
									"" })));
				
				}
			});

			final JButton btnSave = new JButton("Save");
			btnSave.setBounds(649, 431, 97, 25);
			layeredPane.add(btnSave);
			btnSave.setEnabled(true);
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
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
						//SEND TO ENCRIPTION
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
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

			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(354, 115, 536, 265);
			layeredPane_1.add(scrollPane_1);
			String columnName[] = { "Website", "Username", "Password" };
			String[][] data = { { "ADD", "DATA", "HERE" } };
			final DefaultTableModel table1model = new DefaultTableModel(data,
					columnName);
			table_1 = new JTable(table1model);
			scrollPane_1.setViewportView(table_1);
			
			
			JButton btnAddNewUser = new JButton("Add New User");
			btnAddNewUser.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
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
						//SEND TO ENCRIPTION
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			});
			btnAddNewUser.setBounds(115, 278, 116, 25);
			layeredPane_1.add(btnAddNewUser);

			final JButton button = new JButton("Save");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
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
						//SEND TO ENCRIPTION
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
				public void actionPerformed(ActionEvent e) {

					((DefaultTableModel) table_1.getModel())
							.addRow(new java.util.Vector<String>(java.util.Arrays
									.asList(new String[] { String.valueOf(x++), "",
											"" })));

				}
			});
			button_1.setBounds(513, 431, 97, 25);
			layeredPane_1.add(button_1);

			JMenuBar menuBar = new JMenuBar();
			frame.setJMenuBar(menuBar);

			JMenu mnFile = new JMenu("File");
			menuBar.add(mnFile);

			JMenuItem mntmNewMenuItem = new JMenuItem("Add New User");
			mntmNewMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					layeredPane.setVisible(false);
					layeredPane_1.setVisible(true);
				}
			});
			mnFile.add(mntmNewMenuItem);

			JMenuItem mntmExit = new JMenuItem("Exit");
			mntmExit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
				
						});
			mnFile.add(mntmExit);

			JMenu mnEdit = new JMenu("Edit");
			menuBar.add(mnEdit);

			JMenuItem mntmEditPasswords = new JMenuItem("Edit Passwords");
			mnEdit.add(mntmEditPasswords);
		}

		public void tableChanged(TableModelEvent e)
	    {
	        if (e.getType() == TableModelEvent.UPDATE)
	        {
	          
			
	        }}

}
