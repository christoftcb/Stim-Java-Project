import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Vector;

public class Login extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel titleLabel1 = new JLabel("Login");
	JLabel usernameLabel = new JLabel("Username : ");
	JTextField usernameField = new JTextField();
	JLabel passwordLabel = new JLabel("Password : ");
	JTextField passwordField = new JPasswordField();
	JButton loginButton = new JButton("Login");
	JButton registerButton = new JButton("Register");
	private Vector<User> allUser;

	public Login() {
		Font f1 = new Font(Font.DIALOG, Font.BOLD, 25);
		
		setLayout(new BorderLayout());
		
		try {
			allUser = DatabaseStim.getAllUsers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		titleLabel1.setFont(f1);
		titleLabel1.setForeground(Color.white);
		titleLabel1.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel northPanel = new JPanel(new BorderLayout());
		northPanel.setBackground(Color.darkGray);
		JPanel buttonPanelWrap = new JPanel(new BorderLayout());
		buttonPanelWrap.setBackground(Color.DARK_GRAY);
		JPanel mainPanel = new JPanel(new GridBagLayout());
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[] {0, 0};
		gbl.rowHeights = new int[] {0, 0, 0};
		gbl.columnWeights = new double[] {0, 1};
		gbl.rowWeights = new double[] {0, 0, 0};
		mainPanel.setLayout(gbl);
		
		mainPanel.setBackground(Color.DARK_GRAY);
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 0));
		buttonPanel.setBackground(Color.DARK_GRAY);
		
		northPanel.add(titleLabel1, BorderLayout.CENTER);
		
		JPanel d1 = new JPanel();
		d1.setBackground(Color.DARK_GRAY);
		d1.setPreferredSize(new Dimension(80, 0));
		JPanel d2 = new JPanel();
		d2.setBackground(Color.DARK_GRAY);
		d2.setPreferredSize(new Dimension(80, 0));
		buttonPanelWrap.add(d1, BorderLayout.EAST);
		buttonPanelWrap.add(d2, BorderLayout.WEST);
		
		JPanel d3 = new JPanel();
		d3.setBackground(Color.DARK_GRAY);
		d3.setPreferredSize(new Dimension(40, 0));
		JPanel d4 = new JPanel();
		d4.setBackground(Color.DARK_GRAY);
		d4.setPreferredSize(new Dimension(40, 0));
		add(d3, BorderLayout.EAST);
		add(d4, BorderLayout.WEST);
		
		loginButton.setBackground(Color.DARK_GRAY);
		loginButton.setForeground(Color.white);
		loginButton.addActionListener(this);
		buttonPanel.add(loginButton);
		registerButton.setBackground(Color.DARK_GRAY);
		registerButton.setForeground(Color.white);
		registerButton.addActionListener(this);
		buttonPanel.add(registerButton);
		buttonPanelWrap.add(buttonPanel, BorderLayout.CENTER);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(5, 0, 0, 0);
		c.gridx = 0; c.gridy = 1;
		usernameLabel.setForeground(Color.white);
		mainPanel.add(usernameLabel, c);
		c.gridy++;
		passwordLabel.setForeground(Color.white);
		mainPanel.add(passwordLabel, c);
		c.gridx++; c.gridy--;
		usernameField.setOpaque(false);
		usernameField.setForeground(Color.white);
		usernameField.setCaretColor(Color.white);
		mainPanel.add(usernameField, c);
		c.gridy++;
		passwordField.setForeground(Color.white);
		passwordField.setOpaque(false);
		passwordField.setCaretColor(Color.white);
		mainPanel.add(passwordField, c);
		c.insets = new Insets(10, 0, 0, 0);
		c.gridwidth = 2;
		c.gridx--; c.gridy++;
		mainPanel.add(buttonPanelWrap, c);
		c.insets = new Insets(0, 0, 5, 0);
		c.anchor = GridBagConstraints.CENTER;
		c.gridy = 0;
		mainPanel.add(northPanel, c);
		
		add(mainPanel, BorderLayout.CENTER);
		
		setVisible(true);
		setSize(450, 300);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new Login();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(loginButton)) {
			String username = usernameField.getText().toString();
			String password = passwordField.getText().toString();
			if (username.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Username cannot be empty", "Warning", JOptionPane.WARNING_MESSAGE);
			}else if (password.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Password cannot be empty", "Warning", JOptionPane.WARNING_MESSAGE);
			}else {
				boolean found = false;
				for (int i = 0; i < allUser.size(); i++) {
					if (allUser.get(i).getUsername().equals(username)) {
						found = true;
						if (allUser.get(i).getPassword().equals(password)) {
							
							if (allUser.get(i).getRole().equals("Developer")) {
								new MenuDev();
								dispose();
							}else {
								new MenuPlayer(allUser.get(i));
								dispose();
							}
						}else {
							JOptionPane.showMessageDialog(this, "Username/Password is Wrong!", "Warning", JOptionPane.WARNING_MESSAGE);
						}
					}		
				}
				
				if (!found) {
					JOptionPane.showMessageDialog(this, "Username/Password is Wrong!", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
		if (e.getSource().equals(registerButton)) {
			new Register();
			dispose();
		}
	}
}
