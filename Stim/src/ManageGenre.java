import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ManageGenre extends JFrame implements ActionListener{
	JLabel idLabel, nameLabel, newNameLabel;
	JTextField idField, nameField, newNameField;
	JButton insertButton, updateButton, deleteButton, backButton;
	JTable table;
	JScrollPane scroll;
	private DefaultTableModel dtm;
	private int row = -1;
	private Vector<Genre> allgenre;
	
	public ManageGenre() {
		setLayout(new GridLayout(2, 1));
		getContentPane().setBackground(Color.DARK_GRAY);
		
		try {
			allgenre = DatabaseStim.getAllGenre();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		setTable();
		setDesc();
		setListener();
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(900, 500);
		setLocationRelativeTo(null);
		setResizable(true);
	}
	
	private void newtable() {
		try {
			allgenre = DatabaseStim.getAllGenre();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dtm.setRowCount(0);
		String content[][] = new String[allgenre.size()][2];
		for (int i = 0; i < allgenre.size(); i++) {
			content[i][0] = allgenre.get(i).getGenreId();
			content[i][1] = allgenre.get(i).getGenreName();
			dtm.addRow(content[i]);
		}
	}
	
	void setTable(){
		String header[] = {"Genre ID", "Genre Name"};
		
		String content[][] = new String[allgenre.size()][2];
		for (int i = 0; i < allgenre.size(); i++) {
			content[i][0] = allgenre.get(i).getGenreId();
			content[i][1] = allgenre.get(i).getGenreName();
		}
		dtm = new DefaultTableModel(content, header) {
			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable(int row, int column) {return false;};
		};
		
		table = new JTable(dtm);
		table.getTableHeader().setBackground(Color.DARK_GRAY);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setForeground(Color.white);
		table.setBackground(Color.DARK_GRAY);
		table.setForeground(Color.white);
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {
				row = table.getSelectedRow();
				idField.setText(allgenre.get(row).getGenreId());
				nameField.setText(allgenre.get(row).getGenreName());
			}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
			
		});
		scroll = new JScrollPane(table);
		scroll.getViewport().setBackground(Color.DARK_GRAY);
		
		add(scroll);
	}
	
	void setDesc() {
		JPanel descPanelWrap = new JPanel(new BorderLayout());
		descPanelWrap.setBackground(Color.DARK_GRAY);
		JPanel descPanel = new JPanel(new GridLayout(1, 2, 50, 0));
		descPanel.setBackground(Color.DARK_GRAY);
		
		JPanel d1 = new JPanel();
		d1.setBackground(Color.darkGray);
		d1.setPreferredSize(new Dimension(30, 0));
		JPanel d2 = new JPanel();
		d2.setBackground(Color.darkGray);
		d2.setPreferredSize(new Dimension(30, 0));
		JPanel d3 = new JPanel();
		d3.setBackground(Color.darkGray);
		d3.setPreferredSize(new Dimension(0, 60));
		JPanel d4 = new JPanel();
		d4.setBackground(Color.darkGray);
		d4.setPreferredSize(new Dimension(0, 60));
		
		setLeft(descPanel);
		setRight(descPanel);
		
		descPanelWrap.add(d1, BorderLayout.EAST);
		descPanelWrap.add(d2, BorderLayout.WEST);
		descPanelWrap.add(d3, BorderLayout.NORTH);
		descPanelWrap.add(d4, BorderLayout.SOUTH);
		descPanelWrap.add(descPanel, BorderLayout.CENTER);
		
		add(descPanelWrap);
	}
	
	private void setLeft(JPanel descPanel) {
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(Color.DARK_GRAY);
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWeights = new double[] {0.5, 1, 1};
		gbl.columnWidths = new int[] {0, 0, 0};
		gbl.rowHeights = new int[] {0, 0, 0};
		gbl.rowWeights = new double[] {1, 1, 1};
		leftPanel.setLayout(gbl);
		
		idLabel = new JLabel("Genre ID");
		idLabel.setForeground(Color.white);
		nameLabel = new JLabel("Genre Name");
		nameLabel.setForeground(Color.white);
		backButton = new JButton("Back");
		backButton.setForeground(Color.white);
		backButton.setBackground(Color.DARK_GRAY);
		deleteButton = new JButton("Delete");
		deleteButton.setForeground(Color.white);
		deleteButton.setBackground(Color.DARK_GRAY);
		updateButton = new JButton("Update");
		updateButton.setForeground(Color.white);
		updateButton.setBackground(Color.DARK_GRAY);
		idField = new JTextField();
		idField.setEditable(false);
		idField.setForeground(Color.white);
		idField.setBackground(Color.DARK_GRAY);
		idField.setCaretColor(Color.white);
		nameField = new JTextField();
		nameField.setForeground(Color.white);
		nameField.setBackground(Color.DARK_GRAY);
		nameField.setCaretColor(Color.white);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;
		c.gridwidth = 1;
		c.gridx = 0; c.gridy = 0;
		leftPanel.add(idLabel, c);
		c.gridy++;
		leftPanel.add(nameLabel, c);
		c.fill = GridBagConstraints.CENTER;
		c.gridy++;
		leftPanel.add(backButton, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = 2;
		c.gridx++; c.gridy = 0;
		leftPanel.add(idField, c);
		c.gridy++;
		leftPanel.add(nameField, c);
		c.gridwidth = 1;
		c.fill = GridBagConstraints.CENTER;
		c.gridy++;
		leftPanel.add(deleteButton, c);
		c.gridx++;
		leftPanel.add(updateButton, c);
		
		descPanel.add(leftPanel);
	}
	
	private void setRight(JPanel descPanel) {
		JPanel rightPanelWrap = new JPanel(new BorderLayout());
		rightPanelWrap.setBackground(Color.DARK_GRAY);
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(Color.DARK_GRAY);
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWeights = new double[] {0.3, 1, 1};
		gbl.columnWidths = new int[] {0, 0, 0};
		gbl.rowWeights = new double[] {0.1, 0.1};
		gbl.rowHeights = new int[] {0, 0};
		rightPanel.setLayout(gbl);
		
		newNameLabel = new JLabel("New Genre Name:");
		newNameLabel.setForeground(Color.white);
		newNameField = new JTextField();
		newNameField.setForeground(Color.white);
		newNameField.setBackground(Color.DARK_GRAY);
		newNameField.setCaretColor(Color.white);
		insertButton = new JButton("Insert");
		insertButton.setForeground(Color.white);
		insertButton.setBackground(Color.DARK_GRAY);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;
		c.gridwidth = 1;
		c.gridx = 0; c.gridy = 0;
		rightPanel.add(newNameLabel, c);
		c.gridwidth = 2;
		c.gridx++;
		rightPanel.add(newNameField, c);
		c.gridwidth = 1;
		c.fill = GridBagConstraints.CENTER;
		c.anchor = GridBagConstraints.EAST;
		c.gridx++; c.gridy++;
		rightPanel.add(insertButton, c);
		
		JPanel d1 = new JPanel();
		d1.setPreferredSize(new Dimension(0, 20));
		d1.setBackground(Color.DARK_GRAY);
		JPanel d2 = new JPanel();
		d2.setPreferredSize(new Dimension(0, 20));
		d2.setBackground(Color.DARK_GRAY);
		
		rightPanelWrap.add(d1, BorderLayout.NORTH);
		rightPanelWrap.add(d2, BorderLayout.SOUTH);
		rightPanelWrap.add(rightPanel, BorderLayout.CENTER);
		
		descPanel.add(rightPanelWrap);
	}
	
	void setListener() {
		backButton.addActionListener(this);
		deleteButton.addActionListener(this);
		updateButton.addActionListener(this);
		insertButton.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(backButton)) {
			new MenuDev();
			dispose();
		}else if (e.getSource().equals(deleteButton)) {
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Please Select a Genre", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}else {
				Genre genre = allgenre.get(row);
				try {
					DatabaseStim.deleteGenre(genre);
					JOptionPane.showMessageDialog(this, "Delete success", "Success",
							JOptionPane.WARNING_MESSAGE);
					newtable();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}else if (e.getSource().equals(insertButton)) {
			String name = newNameField.getText().toString();
			if (name.length() < 1){
				JOptionPane.showMessageDialog(this, "New Genre Name must consist of more than 0 character", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}else {

				String lastGenreID = allgenre.get(allgenre.size()- 1).getGenreId();
				int lastIndex = Integer.parseInt(lastGenreID.substring(3, 6));
				lastIndex++;
				lastGenreID = "GEN";
				
				if (lastIndex < 10) {
					lastGenreID += "00" + Integer.toString(lastIndex);  
				}else if (lastIndex < 100) {
					lastGenreID += "0" + Integer.toString(lastIndex);  
				}
				
				boolean found = false;
				for (int i = 0; i < allgenre.size(); i++) {
					if (allgenre.get(i).getGenreName().equals(name)) {
						found = true;
						JOptionPane.showMessageDialog(this, "New Genre Name must not be duplicate", "Warning",
								JOptionPane.WARNING_MESSAGE);
					}
				}

				if (!found) {
					Genre genre = new Genre(lastGenreID, name);
					try {
						DatabaseStim.addGenre(genre);
						JOptionPane.showMessageDialog(this, "Insert success", "Success",
								JOptionPane.WARNING_MESSAGE);
						newtable();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
			}
		}else if (e.getSource().equals(updateButton)) {;
			String name = nameField.getText().toString();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Please Select a Genre", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}else if (name.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please enter a name!", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}else {
				boolean found = false;
				for (int i = 0; i < allgenre.size(); i++) {
					if (allgenre.get(i).getGenreName().equals(name)) {
						found = true;
						JOptionPane.showMessageDialog(this, "Genre already exists!", "Warning",
								JOptionPane.WARNING_MESSAGE);
					}
				}
				if (!found) {
					Genre genre = allgenre.get(row);
					genre.setGenreName(name);
					try {
						DatabaseStim.updateGenre(genre);
						JOptionPane.showMessageDialog(this, "Update success", "Success",
								JOptionPane.WARNING_MESSAGE);
						newtable();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
			}
		}
	}
}
