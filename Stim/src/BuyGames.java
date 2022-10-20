import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;

public class BuyGames extends JFrame implements ActionListener{

	private JLabel idLabel, nameLabel, priceLabel, genreLabel, countLabel, agreeLabel;
    private JButton insertBtn, backBtn;
    private JTextField idField, nameField, priceField, genreField;
    private JSpinner countSpinner;
    private JCheckBox agreeCheck;
    private JTable table;
    private DefaultTableModel dtm;
    private JScrollPane scroll;
    private Vector<Games> gamedata;
    private int row = -1;
    private User user;
    private Vector<Transaction> transactions;

        public BuyGames(User user){
        	this.user = user;
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridLayout(2, 1, 0, 0));
            setBackground(Color.DARK_GRAY);
            
            try {
				gamedata = DatabaseStim.getAllGames();
				transactions = DatabaseStim.getAllTransaction(user.getUserId());
			} catch (SQLException e) {
				e.printStackTrace();
			}
          
            init();
            
            setSize(870, 530);
            setResizable(false);
            setLocationRelativeTo(null);
            setVisible(true);
        }
        
        private void newTable() {
        	 try {
 				gamedata = DatabaseStim.getAllGames();
 				transactions = DatabaseStim.getAllTransaction(user.getUserId());
 			} catch (SQLException e) {
 				e.printStackTrace();
 			}
        	 dtm.setRowCount(0);
         	for (Games g : gamedata) {
         		String[]game = new String[5];
         		String genre;
				try {
					game[0] = g.getId();
	         		game[1] = g.getName();
	         		game[2] = Integer.toString(g.getPrice());
					genre = DatabaseStim.getGenreWithID(g.getGenre());
					game[3] = genre;
	         		game[4] = Integer.toString(g.getQuantity());
	         		dtm.addRow(game);
				} catch (SQLException e) {
					e.printStackTrace();
				}
         		
         	}
        	 
        }

        private void init() {
        	String[] header = {"Game ID", "Game Name", "Game Price", "Genre", "Quantity"};
        	String[][] content = new String[gamedata.size()][5];
        	
        	int i = 0;
        	for (Games g : gamedata) {
        		
        		String genre;
				try {
					content[i][0] = g.getId();
	        		content[i][1] = g.getName();
	        		content[i][2] = Integer.toString(g.getPrice());
					genre = DatabaseStim.getGenreWithID(gamedata.get(i).getGenre());
					content[i][3] = genre;
	        		content[i++][4] = Integer.toString(g.getQuantity());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
        	}
        	
        	dtm = new DefaultTableModel(content, header) {
				private static final long serialVersionUID = 1L;
        		
				public boolean isCellEditable(int row, int column) {return false;};
        	};

            table = new JTable(dtm);
            table.setBackground(Color.darkGray);
            table.setForeground(Color.white);
            table.getTableHeader().setBackground(Color.DARK_GRAY);
            table.getTableHeader().setForeground(Color.white);
            table.getTableHeader().setReorderingAllowed(false);
            table.setOpaque(true);
            table.addMouseListener(new MouseListener() {
            	@Override
            	public void mouseClicked(MouseEvent e) {}
            	@Override
            	public void mousePressed(MouseEvent e) {
            		if (e.getSource().equals(table)) {
            	        row = table.getSelectedRow();
            	        idField.setText(gamedata.get(row).getId());
            	        nameField.setText(gamedata.get(row).getName());
            	        priceField.setText(Integer.toString(gamedata.get(row).getPrice()));
            	        String genre;
        				try {
        					genre = DatabaseStim.getGenreWithID(gamedata.get(row).getGenre());
        					genreField.setText(genre);
        				} catch (SQLException e1) {
        					e1.printStackTrace();
        				}	
            		}
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

            JPanel northPanel = new JPanel(new GridLayout());
            northPanel.setBackground(Color.DARK_GRAY);
            northPanel.add(scroll);

            JPanel southPanelWrap = new JPanel(new BorderLayout());
            JPanel southPanel = new JPanel(new GridLayout(1, 2));
            JPanel leftPanel = new JPanel();
            JPanel rightPanel = new JPanel();
            GridBagLayout gbl = new GridBagLayout();
            gbl.columnWidths = new int[] {0};
            gbl.columnWeights = new double[] {1};
            gbl.rowHeights = new int[] {10, 10, 10, 10, 10, 10, 10};
            gbl.rowWeights = new double[] {1, 1, 1, 1, 1, 1, 1};
            leftPanel.setLayout(gbl);
            leftPanel.setBackground(Color.DARK_GRAY);
            rightPanel.setLayout(gbl);
            rightPanel.setBackground(Color.darkGray);
            
            southPanel.add(leftPanel);
            southPanel.add(rightPanel);
            southPanel.setBackground(Color.DARK_GRAY);
            
            southPanelWrap.add(southPanel, BorderLayout.CENTER);
            
            JPanel d1 = new JPanel();
            d1.setPreferredSize(new Dimension(80, 0));
            d1.setBackground(Color.DARK_GRAY);
            JPanel d2 = new JPanel();
            d2.setPreferredSize(new Dimension(80, 0));
            d2.setBackground(Color.DARK_GRAY);
            JPanel d3 = new JPanel();
            d3.setPreferredSize(new Dimension(0, 20));
            d3.setBackground(Color.DARK_GRAY);
            JPanel d4 = new JPanel();
            d4.setPreferredSize(new Dimension(0, 20));
            d4.setBackground(Color.DARK_GRAY);
            southPanelWrap.add(d1, BorderLayout.EAST);
            southPanelWrap.add(d2, BorderLayout.WEST);
            southPanelWrap.add(d3, BorderLayout.NORTH);
            southPanelWrap.add(d4, BorderLayout.SOUTH);
            
            idLabel = new JLabel("Game ID");
            idLabel.setForeground(Color.white);
            nameLabel = new JLabel("Game Name");
            nameLabel.setForeground(Color.white);
            priceLabel = new JLabel("Game Price");
            priceLabel.setForeground(Color.white);
            genreLabel = new JLabel("Game Genre");
            genreLabel.setForeground(Color.white);
            countLabel = new JLabel("How many do you want to buy?");
            countLabel.setForeground(Color.white);
            agreeLabel = new JLabel("Once bought game cannot be returned!");
            agreeLabel.setForeground(Color.white);
            
            idField = new JTextField();
            idField.setEditable(false);
            idField.setOpaque(false);
            idField.setForeground(Color.WHITE);
            nameField = new JTextField();
            nameField.setOpaque(false);
            nameField.setEditable(false);
            nameField.setForeground(Color.WHITE);
            priceField = new JTextField();
            priceField.setOpaque(false);
            priceField.setEditable(false);
            priceField.setForeground(Color.WHITE);
            genreField = new JTextField();
            genreField.setOpaque(false);
            genreField.setEditable(false);
            genreField.setForeground(Color.WHITE);
            countSpinner = new JSpinner();
            agreeCheck = new JCheckBox();
            agreeCheck.setOpaque(false);
            
            backBtn = new JButton("Back");
            backBtn.setBackground(Color.darkGray);;
            backBtn.setForeground(Color.white);
            insertBtn = new JButton("Buy Game");
            insertBtn.setBackground(Color.DARK_GRAY);
            insertBtn.setForeground(Color.white);;
            
            GridBagConstraints c = new GridBagConstraints();
            
            c.fill = GridBagConstraints.HORIZONTAL;
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
            leftPanel.add(countLabel, c);
            c.gridy++;
            leftPanel.add(agreeLabel, c);
            
            c.fill = GridBagConstraints.CENTER;
            c.anchor = GridBagConstraints.CENTER;
            c.gridy++;
            leftPanel.add(backBtn, c);
            
            c.fill = GridBagConstraints.HORIZONTAL;
            c.anchor = GridBagConstraints.WEST;
            c.gridx = 0; c.gridy = 0;
            rightPanel.add(idField, c);
            c.gridy++;
            rightPanel.add(nameField, c);
            c.gridy++;
            rightPanel.add(priceField, c);
            c.gridy++;
            rightPanel.add(genreField, c);
            c.gridy++;
            rightPanel.add(countSpinner, c);
            c.gridy++;
            rightPanel.add(agreeCheck, c);
            
            c.fill = GridBagConstraints.CENTER;
            c.anchor = GridBagConstraints.CENTER;
            c.gridy++;
            rightPanel.add(insertBtn, c);
            
            insertBtn.addActionListener(this);
            backBtn.addActionListener(this);
            add(northPanel);
            add(southPanelWrap);
        }
    @Override
	public void actionPerformed(ActionEvent e) {
    	if (e.getSource().equals(insertBtn)) {
    		if (row == -1) {
    			JOptionPane.showMessageDialog(this, "Please select a game!", "Warning",
						JOptionPane.WARNING_MESSAGE);
    		}else if (!agreeCheck.isSelected()) {
    			JOptionPane.showMessageDialog(this, "Checkbox must be checked!", "Warning",
						JOptionPane.WARNING_MESSAGE);
    		}else {
    			int quantity = (int) countSpinner.getValue();
    			if (gamedata.get(row).getQuantity() < quantity || quantity < 1) {
    				JOptionPane.showMessageDialog(this, "Game quantity cannot be less than 0 or more than stock", "Warning",
    						JOptionPane.WARNING_MESSAGE);
    			}else {
    				Games theGame = new Games(gamedata.get(row).getId(), gamedata.get(row).getName(), 
        					gamedata.get(row).getPrice(), gamedata.get(row).getGenre(), gamedata.get(row).getQuantity() - quantity);
        			boolean found = false;
        			
        			for (int i = 0; i < transactions.size(); i++) {
        				if (transactions.get(i).getGameId().equals(theGame.getId())) {
        					found = true;
        					Transaction transaction = transactions.get(i);
        					transaction.setGameQuantity(transaction.getGameQuantity() + quantity);
        					try {
    							DatabaseStim.updateTransaction(transaction);
    						} catch (SQLException e1) {
    							e1.printStackTrace();
    						}
        				}
        			}
        			
        			if (!found) {
        				Transaction transaction = new Transaction(theGame.getId(), 0, user.getUserId(), quantity);
        				try {
        					DatabaseStim.addTransaction(transaction);
    					} catch (SQLException e1) {
    						e1.printStackTrace();
    					}
        			}
        			
        			try {
    					DatabaseStim.updateGame(theGame);
    					JOptionPane.showMessageDialog(this, "Game Bought", "Success",
    							JOptionPane.WARNING_MESSAGE);
    					 newTable() ;
    				} catch (SQLException e1) {
    					e1.printStackTrace();
    				}
    			}	
    		}
    	}else if (e.getSource().equals(backBtn)) {
    		new MenuPlayer(user);
    		dispose();
    	}
    }
}