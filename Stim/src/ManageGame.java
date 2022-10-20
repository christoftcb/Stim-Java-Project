import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.*;

public class ManageGame extends JFrame implements ActionListener{
	JLabel idLabel, nameLabel, priceLabel, genreLabel, quantityLabel, newNameLabel, newPriceLabel, newGenreLabel, newQuantityLabel;
	JTable table;
	JScrollPane scroll;
	JTextField idField, nameField, priceField, newNameField, newPriceField;
	JComboBox<String> genreCombo, newGenreCombo;
	JSpinner quantitySpinner, newQuantitySpinner;
	JButton updateBtn, deleteBtn, insertBtn, backBtn;
	private int row = -1;
	private Vector<Games> gamedata;
	private Vector<Genre> allgenre;
	private DefaultTableModel dtm;
	
	public ManageGame() {
		setLayout(new GridLayout(2, 1, 0, 0));
		setBackground(Color.DARK_GRAY);
		
		try {
			gamedata = DatabaseStim.getAllGames();
			allgenre = DatabaseStim.getAllGenre();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		setTable();
		setDesc();
		
		setVisible(true);
		setSize(900, 500);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void newTable() {
		try {
			gamedata = DatabaseStim.getAllGames();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dtm.setRowCount(0);
		String content[][] = new String [gamedata.size()][5];
		
		for (int i = 0; i < gamedata.size(); i++) {
			String genre;
			try {
				content[i][0] = gamedata.get(i).getId();
				content[i][1] = gamedata.get(i).getName();
				content[i][2] = Integer.toString(gamedata.get(i).getPrice());
				genre = DatabaseStim.getGenreWithID(gamedata.get(i).getGenre());
				content[i][3] = genre;
				content[i][4] = Integer.toString(gamedata.get(i).getQuantity());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			dtm.addRow(content[i]);
		}
	}
	
	void setTable() {
		String header[] = {"Game ID", "Game Name", "Game Price", "Genre", "Quantity"} ;
		String content[][] = new String [gamedata.size()][5];
		
		for (int i = 0; i < gamedata.size(); i++) {
			String genre;
			try {
				content[i][0] = gamedata.get(i).getId();
				content[i][1] = gamedata.get(i).getName();
				content[i][2] = Integer.toString(gamedata.get(i).getPrice());
				genre = DatabaseStim.getGenreWithID(gamedata.get(i).getGenre());
				content[i][3] = genre;
				content[i][4] = Integer.toString(gamedata.get(i).getQuantity());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		dtm = new DefaultTableModel(content, header) {
			private static final long serialVersionUID = 1L;
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		table = new JTable(dtm);
		table.setBackground(Color.DARK_GRAY);
		table.getTableHeader().setBackground(Color.DARK_GRAY);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setForeground(Color.white);
		table.setForeground(Color.white);
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {
				row = table.getSelectedRow();
				idField.setText(gamedata.get(row).getId());
				nameField.setText(gamedata.get(row).getName());
				priceField.setText(Integer.toString(gamedata.get(row).getPrice()));
				for (int i = 0; i < allgenre.size(); i++) {
					if (gamedata.get(row).getGenre().equals(allgenre.get(i).getGenreId())) {
						genreCombo.setSelectedItem(allgenre.get(i).getGenreName());
					}
				}
				quantitySpinner.setValue(gamedata.get(row).getQuantity());
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
		JPanel descPanel = new JPanel(new GridLayout(1, 2, 100, 0));
		descPanel.setBackground(Color.DARK_GRAY);
		
		JPanel d1 = new JPanel();
		d1.setBackground(Color.DARK_GRAY);
		d1.setPreferredSize(new Dimension(80, 0));
		JPanel d2 = new JPanel();
		d2.setBackground(Color.DARK_GRAY);
		d2.setPreferredSize(new Dimension(80, 0));
		JPanel d3 = new JPanel();
		d3.setBackground(Color.DARK_GRAY);
		d3.setPreferredSize(new Dimension(0, 20));
		JPanel d4 = new JPanel();
		d4.setBackground(Color.DARK_GRAY);
		d4.setPreferredSize(new Dimension(0, 20));
		
		setLeft(descPanel);
		setRight(descPanel);
		
		descPanelWrap.add(d1, BorderLayout.WEST);
		descPanelWrap.add(d2, BorderLayout.EAST);
		descPanelWrap.add(d3, BorderLayout.NORTH);
		descPanelWrap.add(d4, BorderLayout.SOUTH);
		descPanelWrap.add(descPanel, BorderLayout.CENTER);
		
		add(descPanelWrap);
	}
	
	private void setLeft(JPanel descPanel) {
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(Color.DARK_GRAY);
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[] {0, 0, 0};
		gbl.columnWeights = new double[] {1, 1, 1};
		gbl.rowHeights = new int[] {0, 0, 0, 0, 0, 0};
		gbl.rowWeights = new double[] {1, 1, 1, 1, 1, 1};
		leftPanel.setLayout(gbl);
		
		idLabel = new JLabel("Game id");
		idLabel.setForeground(Color.white);
		nameLabel = new JLabel("Game Name");
		nameLabel.setForeground(Color.white);
		priceLabel = new JLabel("Game Price");
		priceLabel.setForeground(Color.white);
		genreLabel = new JLabel("Game Genre");
		genreLabel.setForeground(Color.white);
		quantityLabel = new JLabel("Game Quantity");
		quantityLabel.setForeground(Color.white);
		
		idField = new JTextField();
		idField.setEditable(false);
		idField.setBackground(Color.DARK_GRAY);
		idField.setForeground(Color.white);
		nameField = new JTextField();
		nameField.setCaretColor(Color.white);
		nameField.setBackground(Color.DARK_GRAY);
		nameField.setForeground(Color.white);
		priceField = new JTextField();
		priceField.setCaretColor(Color.white);
		priceField.setBackground(Color.DARK_GRAY);
		priceField.setForeground(Color.white);
		
		String[] str = new String [allgenre.size() + 1];
		str[0] =  "Select a Genre";
		for (int i = 1; i <= allgenre.size() ; i++) {
			str[i] = allgenre.get(i - 1).getGenreName();
		}
		
		genreCombo = new JComboBox<String>(str);
		genreCombo.setBackground(Color.DARK_GRAY);
		genreCombo.setForeground(Color.white);
		quantitySpinner = new JSpinner();
		
		backBtn = new JButton("Back");
		backBtn.addActionListener(this);
		backBtn.setBackground(Color.DARK_GRAY);
		backBtn.setForeground(Color.white);
		deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(this);
		deleteBtn.setBackground(Color.DARK_GRAY);
		deleteBtn.setForeground(Color.white);
		updateBtn = new JButton("Update");
		updateBtn.addActionListener(this);
		updateBtn.setBackground(Color.DARK_GRAY);
		updateBtn.setForeground(Color.white);
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = 1;
		c.fill = GridBagConstraints.CENTER;
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0; c.gridy = 0;
		leftPanel.add(idLabel, c);
		c.gridy++;
		leftPanel.add(nameLabel, c);
		c.gridy++;
		leftPanel.add(priceLabel, c);
		c.gridy++;
		leftPanel.add(genreLabel, c);
		c.gridy++;
		leftPanel.add(quantityLabel, c);
		c.anchor = GridBagConstraints.CENTER;
		c.gridy++;
		leftPanel.add(backBtn, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx++; c.gridy = 0;
		leftPanel.add(idField, c);
		c.gridy++;
		leftPanel.add(nameField, c);
		c.gridy++;
		leftPanel.add(priceField, c);
		c.gridy++;
		leftPanel.add(genreCombo, c);
		c.gridy++;
		leftPanel.add(quantitySpinner, c);
		c.gridwidth = 1;
		c.fill = GridBagConstraints.CENTER;
		c.anchor = GridBagConstraints.CENTER;
		c.gridy++;
		leftPanel.add(deleteBtn, c);
		c.gridx++;
		leftPanel.add(updateBtn, c);
		descPanel.add(leftPanel);
	}
	
	private void setRight(JPanel descPanel) {
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(Color.DARK_GRAY);
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[] {0, 0, 0};
		gbl.columnWeights = new double[] {0.3, 1, 1};
		gbl.rowHeights = new int[] {0, 0, 0, 0, 0, 0};
		gbl.rowWeights = new double[] {1, 1, 1, 1, 1, 1};
		rightPanel.setLayout(gbl);
		
		newNameLabel = new JLabel("New Game Name");
		newNameLabel.setForeground(Color.white);
		newPriceLabel = new JLabel("New Game Price");
		newPriceLabel.setForeground(Color.white);
		newGenreLabel = new JLabel("New Game Genre");
		newGenreLabel.setForeground(Color.white);
		newQuantityLabel = new JLabel("New Game Quantity");
		newQuantityLabel.setForeground(Color.white);
		
		newNameField = new JTextField();
		newNameField.setBackground(Color.darkGray);
		newNameField.setForeground(Color.white);
		newNameField.setCaretColor(Color.white);
		newPriceField = new JTextField();
		newPriceField.setBackground(Color.DARK_GRAY);
		newPriceField.setForeground(Color.white);
		newPriceField.setCaretColor(Color.white);
		

		String[] str = new String [allgenre.size() + 1];
		str[0] =  "Select a Genre";
		for (int i = 1; i <= allgenre.size() ; i++) {
			str[i] = allgenre.get(i - 1).getGenreName();
		}
		newGenreCombo = new JComboBox<String>(str);
		newGenreCombo.setForeground(Color.white);
		newGenreCombo.setBackground(Color.DARK_GRAY);
		newQuantitySpinner = new JSpinner();
		insertBtn = new JButton("Insert");
		insertBtn.addActionListener(this);
		insertBtn.setBackground(Color.DARK_GRAY);
		insertBtn.setForeground(Color.white);
	
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0; c.gridy = 0;
		rightPanel.add(newNameLabel, c);
		c.gridy++;
		rightPanel.add(newPriceLabel, c);
		c.gridy++;
		rightPanel.add(newGenreLabel, c);
		c.gridy++;
		rightPanel.add(newQuantityLabel, c);
		c.gridwidth = 2;
		c.gridx++; c.gridy = 0;
		rightPanel.add(newNameField, c);
		c.gridy++;
		rightPanel.add(newPriceField, c);
		c.gridy++;
		rightPanel.add(newGenreCombo, c);
		c.gridy++;
		rightPanel.add(newQuantitySpinner, c);
		c.gridwidth = 1;
		c.fill = GridBagConstraints.CENTER;
		c.anchor = GridBagConstraints.EAST;
		c.gridx++; c.gridy++; c.gridy++;
		rightPanel.add(insertBtn, c);
		
		descPanel.add(rightPanel);
	}
	private boolean mustBeNumeric(String string) {
		for (int i = 0; i < string.length(); i++) {
			if (!Character.isDigit(string.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(insertBtn)) {
			String name = newNameField.getText().toString();
			String price = newPriceField.getText().toString();
			int index = newGenreCombo.getSelectedIndex();
			int quantity = (int) newQuantitySpinner.getValue();
			
			boolean validation = false;
			if (name.length() < 5 || name.length() > 35) {
				JOptionPane.showMessageDialog(this, "Name must be between 5-30 Characters!", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}else if (price.isEmpty()){
				JOptionPane.showMessageDialog(this, "Please fill the price!", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}else if (!mustBeNumeric(price)) {
				JOptionPane.showMessageDialog(this, "Price must be numeric", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}else if (quantity < 1) {
				JOptionPane.showMessageDialog(this, "Quantity must be > 0", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}else if (index == 0) {
				JOptionPane.showMessageDialog(this, "Please select a genre!", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}else if (Integer.parseInt(price) <= 0) {
				JOptionPane.showMessageDialog(this, "Price must be more than 0", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}else {
				validation = true;
			}
			
			if (validation) {
				
				boolean found = false;	
				for (int i = 0; i < gamedata.size() ; i++) {
					if (gamedata.get(i).getName().equals(name)) {
						found = true;
						JOptionPane.showMessageDialog(this, "Game name already exist!", "Warning",
								JOptionPane.WARNING_MESSAGE);
						break;
					}
				}
			
				if (!found) {
					try {
						String lastGameID = gamedata.get(gamedata.size() - 1).getId();
						int lastIndex = Integer.parseInt(lastGameID.substring(4, 7));
						lastIndex++;
						lastGameID = "GAME";
						
						if (lastIndex < 10) {
							lastGameID += "00" + Integer.toString(lastIndex);  
						}else if (lastIndex < 100) {
							lastGameID += "0" + Integer.toString(lastIndex);  
						}

						Games games = new Games(lastGameID, name, Integer.parseInt(price),
								allgenre.get(index - 1).getGenreId(), quantity);
						
						DatabaseStim.addGames(games);
						JOptionPane.showMessageDialog(this, "Insert Success", "Success",
								JOptionPane.WARNING_MESSAGE);
						newTable();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
			
		}else if (e.getSource().equals(backBtn)) {
			new MenuDev();
			dispose();
		}else if (e.getSource().equals(deleteBtn)) {
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Select a game first!", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}else {
				Games games = gamedata.get(row);
				try {
					DatabaseStim.deleteGame(games);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				newTable();
				JOptionPane.showMessageDialog(this, "Delete Success", "Success",
						JOptionPane.WARNING_MESSAGE);
			}
		}else if (e.getSource().equals(updateBtn)) {
			String name = nameField.getText().toString();
			String price = priceField.getText().toString();
			int index = genreCombo.getSelectedIndex();
			int quantity = (int) quantitySpinner.getValue();
			
			boolean validation = false;
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Select a game first!", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}else if (name.length() < 5 || name.length() > 35) {
				JOptionPane.showMessageDialog(this, "Name must be between 5-30 Characters!", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}else if (price.isEmpty()){
				JOptionPane.showMessageDialog(this, "Please fill the price!", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}else if (!mustBeNumeric(price)) {
				JOptionPane.showMessageDialog(this, "Price must be numeric", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}else if (quantity < 1) {
				JOptionPane.showMessageDialog(this, "Quantity must be > 0", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}else if (index == 0) {
				JOptionPane.showMessageDialog(this, "Please select a genre!", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}else if (Integer.parseInt(price) <= 0) {
				JOptionPane.showMessageDialog(this, "Price must be more than 0", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}else {
				validation = true;
			}
			
			if (validation) {
				boolean change = true;
				boolean found = false;
				if (gamedata.get(row).getName().equals(name)) {
					change = false;
				}
					
				if (change) {
					for (int i = 0; i < gamedata.size() ; i++) {
						if (gamedata.get(i).getName().equals(name)) {
							found = true;
							JOptionPane.showMessageDialog(this, "Game name already exist!", "Warning",
									JOptionPane.WARNING_MESSAGE);
							break;
						}
					}
				}
				
				if (!found) {
					try {
						Games games = gamedata.get(row);
						games.setName(name);
						games.setGenre(allgenre.get(index - 1).getGenreId());
						games.setPrice(Integer.parseInt(price));
						games.setQuantity(quantity);
						DatabaseStim.updateGame(games);
						JOptionPane.showMessageDialog(this, "Update Success", "Success",
								JOptionPane.WARNING_MESSAGE);
						newTable();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		
	}
}
