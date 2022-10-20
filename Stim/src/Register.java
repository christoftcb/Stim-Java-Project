//import javax.swing.*;
//import java.awt.*;
//
//public class Register {
//
//	public Register() {
//		Font f1 = new Font(Font.DIALOG, Font.BOLD, 20);
//		
//		JPanel Panel = new JPanel();
//		JFrame registerFrame = new JFrame();
//		registerFrame.setSize(600,400);
//		registerFrame.setDefaultCloseOperation(registerFrame.EXIT_ON_CLOSE);
//		registerFrame.add(Panel);
//		registerFrame.setResizable(false);
//		Panel.setBackground(Color.darkGray);
//		Panel.setLayout(null);
//		
//		JLabel titleLabel2 = new JLabel("Create An Account");
//		titleLabel2.setBounds(20, 30, 250, 30);
//		titleLabel2.setFont(f1);
//		titleLabel2.setForeground(Color.WHITE);
//		Panel.add(titleLabel2);
//		
//		JLabel usernameLabel = new JLabel("Username");
//		usernameLabel.setBounds(20, 70, 100, 25);
//		usernameLabel.setForeground(Color.WHITE);
//		Panel.add(usernameLabel);
//		
//		JTextField usernameField = new JTextField(15);
//		usernameField.setBounds(150, 70, 300, 25);
//		usernameField.setOpaque(false);
//		usernameField.setForeground(Color.WHITE);
//		Panel.add(usernameField);
//		
//		JLabel passwordLabel = new JLabel("Password");
//		passwordLabel.setBounds(20, 110, 100, 25);
//		passwordLabel.setForeground(Color.WHITE);
//		Panel.add(passwordLabel);
//	
//		JTextField passwordField = new JTextField(10);
//		passwordField.setBounds(150, 110, 300, 25);
//		passwordField.setOpaque(false);
//		passwordField.setForeground(Color.WHITE);
//		Panel.add(passwordField);
//		
//		JLabel genderLabel = new JLabel("Gender");
//		genderLabel.setBounds(20, 150, 100, 25);
//		genderLabel.setForeground(Color.WHITE);
//		Panel.add(genderLabel);
//		
//		JRadioButton male = new JRadioButton();
//		
//		
//		registerFrame.setVisible(true);
//	}
//
//	public static void main(String[] args) {
//		new Register();
//	}
//
//}

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;

public class Register extends JFrame implements ActionListener {

	private JLabel title, username, password, gender, country, role;
	private JTextField user;
	private JPasswordField passwd;
	private JRadioButton ml, fml, pl, dpl;
	private JComboBox<String> countryName;
	private JButton back, register;
	private Vector<User> allUser;

	public Register() {
		Font f1 = new Font(Font.DIALOG, Font.BOLD, 25);
		
		try {
			allUser = DatabaseStim.getAllUsers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		title = new JLabel("Create an account");
		title.setFont(f1);
		title.setForeground(Color.white);
		username = new JLabel("Username");
		username.setForeground(Color.white);
		password = new JLabel("Password");
		password.setForeground(Color.white);
		gender = new JLabel("Gender");
		gender.setForeground(Color.white);
		country = new JLabel("Country");
		country.setForeground(Color.white);
		role = new JLabel("Choose a role:");
		role.setForeground(Color.white);

		user = new JTextField();
		user.setCaretColor(Color.WHITE);
		passwd = new JPasswordField("");
		passwd.setCaretColor(Color.WHITE);

		ml = new JRadioButton("Male");
		fml = new JRadioButton("Female");
		pl = new JRadioButton("Player");
		dpl = new JRadioButton("Developer");

		ButtonGroup bg = new ButtonGroup();
		bg.add(ml);
		bg.add(fml);
		ButtonGroup bg1 = new ButtonGroup();
		bg1.add(pl);
		bg1.add(dpl);

		String str[] = { "Select a country", "Indonesia", "America", "England", "Malaysia", "Singapore", "South Korea", "German"};
		countryName = new JComboBox<String>(str);

		back = new JButton("Back");
		back.addActionListener(this);
		register = new JButton("Register");
		register.addActionListener(this);
		
		JPanel northPanelWrap = new JPanel(new BorderLayout());
		northPanelWrap.add(title, BorderLayout.CENTER);
		northPanelWrap.setBackground(Color.DARK_GRAY);
		
		JPanel d1 = new JPanel();
		d1.setPreferredSize(new Dimension(0, 20));
		d1.setBackground(Color.darkGray);
		northPanelWrap.add(d1, BorderLayout.NORTH);
		JPanel d2 = new JPanel();
		d2.setPreferredSize(new Dimension(0, 20));
		d2.setBackground(Color.DARK_GRAY);
		northPanelWrap.add(d2, BorderLayout.SOUTH);
		JPanel d3 = new JPanel();
		d3.setPreferredSize(new Dimension(20, 0));
		d3.setBackground(Color.DARK_GRAY);
		northPanelWrap.add(d3, BorderLayout.EAST);
		JPanel d4 = new JPanel();
		d4.setPreferredSize(new Dimension(20, 0));
		d4.setBackground(Color.darkGray);
		northPanelWrap.add(d4, BorderLayout.WEST);
		
		setLayout(new BorderLayout());
		add(northPanelWrap, BorderLayout.NORTH);
		
		JPanel buttonPanelWrap = new JPanel(new BorderLayout());
		buttonPanelWrap.setBackground(Color.darkGray);
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 0));
		buttonPanel.setBackground(Color.DARK_GRAY);
		JPanel d5 = new JPanel();
		d5.setPreferredSize(new Dimension(0, 20));
		d5.setBackground(Color.DARK_GRAY);
		buttonPanelWrap.add(d5, BorderLayout.NORTH);
		JPanel d6 = new JPanel();
		d6.setPreferredSize(new Dimension(0, 20));
		d6.setBackground(Color.darkGray);
		buttonPanelWrap.add(d6, BorderLayout.SOUTH);
		JPanel d7 = new JPanel();
		d7.setPreferredSize(new Dimension(60, 0));
		d7.setBackground(Color.DARK_GRAY);
		buttonPanelWrap.add(d7, BorderLayout.EAST);
		JPanel d8 = new JPanel();
		d8.setPreferredSize(new Dimension(60, 0));
		d8.setBackground(Color.DARK_GRAY);
		buttonPanelWrap.add(d8, BorderLayout.WEST);
		
		back.setForeground(Color.white);
		back.setBackground(Color.darkGray);
		buttonPanel.add(back);
		register.setForeground(Color.white);
		register.setBackground(Color.DARK_GRAY);
		buttonPanel.add(register);
		buttonPanelWrap.add(buttonPanel, BorderLayout.CENTER);
		add(buttonPanelWrap, BorderLayout.SOUTH);
		
		JPanel mainPanelWrap = new JPanel(new BorderLayout());
		JPanel d9 = new JPanel();
		d9.setBackground(Color.DARK_GRAY);
		d9.setPreferredSize(new Dimension(20, 0));
		JPanel d10 = new JPanel();
		d10.setBackground(Color.DARK_GRAY);
		d10.setPreferredSize(new Dimension(20, 0));
		mainPanelWrap.add(d9, BorderLayout.EAST);
		mainPanelWrap.add(d10, BorderLayout.WEST);
		
		JPanel mainPanel = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[] {0, 0, 0};
		gbl.rowHeights = new int[] {0, 0, 0, 0, 0};
		gbl.columnWeights = new double[] {1, 1, 1};
		gbl.rowWeights = new double[] {1, 1, 1, 1, 1};
		mainPanel.setLayout(gbl);
		mainPanel.setBackground(Color.DARK_GRAY);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;
		//left part of the mainPanel
		c.gridwidth = 2;
		c.gridx = 0; c.gridy = 0;
		mainPanel.add(username, c);
		c.gridy++;
		mainPanel.add(password, c);
		c.gridy++;
		mainPanel.add(gender, c);
		c.gridy++;
		mainPanel.add(country, c);
		c.gridy++;
		mainPanel.add(role, c);
		
		//right part of the mainPanel
		c.gridwidth = 2;
		c.gridx = 1; c.gridy = 0;
		user.setBackground(Color.DARK_GRAY);
		user.setForeground(Color.white);
		mainPanel.add(user, c);
		c.gridy++;
		passwd.setBackground(Color.DARK_GRAY);
		passwd.setForeground(Color.white);
		mainPanel.add(passwd, c);
		
		JPanel genderPanel = new JPanel(new GridLayout(1, 2));
		genderPanel.setBackground(Color.DARK_GRAY);
		ml.setForeground(Color.white);
		ml.setBackground(Color.DARK_GRAY);
		genderPanel.add(ml);
		fml.setForeground(Color.white);
		fml.setBackground(Color.DARK_GRAY);
		genderPanel.add(fml);
		c.gridwidth = 1;
		c.gridy++;
		mainPanel.add(genderPanel, c);

		c.fill = GridBagConstraints.WEST;
		c.gridy++;
		countryName.setBackground(Color.DARK_GRAY);
		countryName.setForeground(Color.white);
		mainPanel.add(countryName, c);
		
		JPanel rolePanel = new JPanel(new GridLayout());
		rolePanel.setBackground(Color.DARK_GRAY);
		rolePanel.add(pl);
		pl.setBackground(Color.DARK_GRAY);
		pl.setForeground(Color.white);
		rolePanel.add(dpl);
		dpl.setForeground(Color.white);
		dpl.setBackground(Color.DARK_GRAY);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy++;
		mainPanel.add(rolePanel, c);
		
		mainPanelWrap.add(mainPanel, BorderLayout.CENTER);
		add(mainPanelWrap, BorderLayout.CENTER);
		
		setSize(500, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}

//creating the object of the class
	public static void main(String[] args) {
		new Register();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Register")) {
			
			int len = user.getText().length();
			if (len < 5 || len > 15 || user.getText().isBlank()) {
				JOptionPane.showMessageDialog(this, "Username length must be atleast 5-15 characters", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}else {
				int passlen = passwd.getText().length();
				if (passlen < 3 || passlen > 10 || passwd.getText().isBlank()) {
					JOptionPane.showMessageDialog(this, "Password length must be atleast 3-10 characters", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}else {
					if (!(ml.isSelected() || fml.isSelected())) {
						JOptionPane.showMessageDialog(this, "Please select a gender", "Warning",
								JOptionPane.WARNING_MESSAGE);
					}else {
						if (countryName.getSelectedIndex() == 0) {
							JOptionPane.showMessageDialog(this, "Please select a country", "Warning",
									JOptionPane.WARNING_MESSAGE);
						}else {
							if (!(pl.isSelected() || dpl.isSelected())) {
							JOptionPane.showMessageDialog(this, "Please select a role", "Warning",
									JOptionPane.WARNING_MESSAGE);
							
							} else {
								String username = user.getText().toString();
								String password = passwd.getText().toString();
								String userGender = "";
								String userRole ="";
								String userCountry = "";
								if (ml.isSelected()) {
									userGender = "Male";
								}else {
									userGender = "Female";
								}
								
								if (pl.isSelected()) {
									userRole = "Player";
								}else {
									userRole = "Developer";
								}
								
								if (countryName.getSelectedIndex() == 1) {
									userCountry ="Indonesia";
								}else if (countryName.getSelectedIndex() == 2) {
									userCountry ="America";
								}else if (countryName.getSelectedIndex() == 3) {
									userCountry ="England";
								}else if (countryName.getSelectedIndex() == 4) {
									userCountry ="Malaysia";
								}else if (countryName.getSelectedIndex() == 5) {
									userCountry ="Singapore";
								}else if (countryName.getSelectedIndex() == 6) {
									userCountry ="South Korea";
								}else if (countryName.getSelectedIndex() == 7) {
									userCountry ="German";
								}
								boolean found = false;
								for (int i = 0; i < allUser.size(); i++) {
									if (allUser.get(i).getUsername().equals(username)) {
										found = true;
									}
										
								}
								
								if (found) {
									JOptionPane.showMessageDialog(this, "Username already taken!", "Warning",
											JOptionPane.WARNING_MESSAGE);
								}else {
									User newUser = new User(username,password, userGender,userCountry, userRole, 0 );
									try {
										DatabaseStim.addUser(newUser);
									} catch (SQLException e1) {
										e1.printStackTrace();
									}
									JOptionPane.showMessageDialog(this, "Successfully registered user!", "Success",
											JOptionPane.WARNING_MESSAGE);
									new Login();
									dispose();
								}
							}
						}
					}
				}
			}	
		}else {
			new Login();
			dispose();
		}
	}
}