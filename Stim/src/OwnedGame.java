import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.*;

public class OwnedGame extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	JLabel idLabel, nameLabel, priceLabel, genreLabel, quantityLabel, totalLabel;
	JTextField idField, nameField, priceField, genreField, quantityField, totalField;
	JTable table;
	JScrollPane scroll;
	JButton backBtn;
	private DefaultTableModel dtm;
	private User user;
	private Vector<Transaction> transactions;
	private Vector<Games> userGames;
	
	public OwnedGame(User user) {
		this.user = user;
		setLayout(new GridLayout(2, 1));
		getContentPane().setBackground(Color.DARK_GRAY);;
		userGames = new Vector<Games>();
		try {
			transactions = DatabaseStim.getAllTransaction(user.getUserId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		setTable();
		setDesc();
		setListener();
		
		setVisible(true);
		setSize(860, 570);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	void setTable() {
		String header[] = {"Game ID", "Game Name", "Genre", "Quantity", "Price"};
		String[][] content = new String[transactions.size()][5];
    	
    	int i = 0;
    	for (Transaction t : transactions) {
    		Games g;
			try {
				g = DatabaseStim.getGameWithId(t.getGameId());
				content[i][0] = g.getId();
	    		content[i][1] = g.getName();
	    		String genre = DatabaseStim.getGenreWithID(g.getGenre());
	    		content[i][2] = genre;
	    		content[i][3] = Integer.toString(t.getGameQuantity());
	    		content[i++][4] = Integer.toString(g.getPrice());
	    		userGames.add(g);
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
		table.setForeground(Color.white);
		table.getTableHeader().setForeground(Color.white);
		table.getTableHeader().setReorderingAllowed(false);
		
		scroll = new JScrollPane(table);
		scroll.getViewport().setBackground(Color.DARK_GRAY);
		
		add(scroll);
	}
	
	void setDesc() {
		JPanel descPanelWrap = new JPanel(new BorderLayout());
		descPanelWrap.setBackground(Color.darkGray);
		JPanel d1 = new JPanel();
		d1.setBackground(Color.darkGray);
		d1.setPreferredSize(new Dimension(240, 0));
		JPanel d2 = new JPanel();
		d2.setBackground(Color.darkGray);
		d2.setPreferredSize(new Dimension(240, 0));
		JPanel d3 = new JPanel();
		d3.setBackground(Color.DARK_GRAY);
		d3.setPreferredSize(new Dimension(0, 20));
		JPanel d4 = new JPanel();
		d4.setBackground(Color.DARK_GRAY);
		d4.setPreferredSize(new Dimension(0, 20));
		
		descPanelWrap.add(d1, BorderLayout.WEST);
		descPanelWrap.add(d2, BorderLayout.EAST);
		descPanelWrap.add(d3, BorderLayout.NORTH);
		descPanelWrap.add(d4, BorderLayout.SOUTH);
		
		JPanel descPanel = new JPanel();
		descPanel.setBackground(Color.DARK_GRAY);
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[] {0, 0, 0};
		gbl.columnWeights = new double[] {0.2, 1, 1};
		gbl.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
		gbl.rowWeights = new double[] {1, 1, 1, 1, 1, 1, 1};
		descPanel.setLayout(gbl);
		
		idLabel = new JLabel("Game ID");
		idLabel.setForeground(Color.white);
		nameLabel = new JLabel("Game Name");
		nameLabel.setForeground(Color.white);
		priceLabel = new JLabel("Game Price");
		priceLabel.setForeground(Color.white);
		genreLabel = new JLabel("Game Genre");
		genreLabel.setForeground(Color.white);
		quantityLabel = new JLabel("Owned Quantity");
		quantityLabel.setForeground(Color.white);
		totalLabel = new JLabel("Total Spent on Games");
		totalLabel.setForeground(Color.white);
		
		backBtn = new JButton("Back");
		backBtn.setBackground(Color.DARK_GRAY);
		backBtn.setForeground(Color.white);
		
		idField = new JTextField();
		idField.setBackground(Color.DARK_GRAY);;
		idField.setEditable(false);
		idField.setForeground(Color.white);
		nameField = new JTextField();
		nameField.setOpaque(false);
		nameField.setEditable(false);
		nameField.setForeground(Color.white);
		priceField = new JTextField();
		priceField.setOpaque(false);
		priceField.setEditable(false);
		priceField.setForeground(Color.white);
		genreField = new JTextField();
		genreField.setOpaque(false);
		genreField.setEditable(false);
		genreField.setForeground(Color.white);
		quantityField = new JTextField();
		quantityField.setOpaque(false);
		quantityField.setEditable(false);
		quantityField.setForeground(Color.white);
		totalField = new JTextField();
		totalField.setOpaque(false);
		totalField.setEditable(false);
		totalField.setForeground(Color.white);
		
		//TODO
		totalField.setText("");
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 0; c.gridy = 0;
		descPanel.add(idLabel, c);
		c.gridy++;
		descPanel.add(nameLabel, c);
		c.gridy++;
		descPanel.add(priceLabel, c);
		c.gridy++;
		descPanel.add(genreLabel, c);
		c.gridy++;
		descPanel.add(quantityLabel, c);
		c.gridy++;
		descPanel.add(totalLabel, c);
		c.gridy++;
		descPanel.add(backBtn, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx++; c.gridy = 0;
		descPanel.add(idField, c);
		c.gridy++;
		descPanel.add(nameField, c);
		c.gridy++;
		descPanel.add(priceField, c);
		c.gridy++;
		descPanel.add(genreField, c);
		c.gridy++;
		descPanel.add(quantityField, c);
		c.gridy++;
		descPanel.add(totalField, c);
		
		descPanelWrap.add(descPanel, BorderLayout.CENTER);
		add(descPanelWrap);
	}
	
	void setListener() {
		backBtn.addActionListener(this);
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {
				int row = table.getSelectedRow();
				idField.setText(userGames.get(row).getId());
				nameField.setText(userGames.get(row).getName());
				priceField.setText(Integer.toString(userGames.get(row).getPrice()));
				String genre;
				try {
					genre = DatabaseStim.getGenreWithID(userGames.get(row).getGenre());
					genreField.setText(genre);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}	
				quantityField.setText(Integer.toString(transactions.get(row).getGameQuantity()));
				int total = transactions.get(row).getGameQuantity() * userGames.get(row).getPrice();
				totalField.setText(Integer.toString(total));
				
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
			
		});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		new MenuPlayer(user);
		dispose();
	}

}
