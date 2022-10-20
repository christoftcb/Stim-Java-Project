import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MenuPlayer{
	
	private User user;

	public MenuPlayer(User user) {
		 this.user = user;
		 Font f1 = new Font(Font.DIALOG, Font.ITALIC, 80);
		 
		 JMenu menu1, menu2;  
         JMenuItem i1, e1, e2; 
         JFrame PlayerFrame= new JFrame("Stim");  
         JMenuBar mb = new JMenuBar();  
         mb.setBackground(Color.darkGray);
         
         menu1=new JMenu("Account");
         i1=new JMenuItem("Logout");
         i1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Login();
				PlayerFrame.dispose();
			}
         });
         menu1.setForeground(Color.WHITE);
         
         menu2 = new JMenu("Games");
         e1=new JMenuItem("Buy Games");
         e1.addActionListener(new ActionListener() {
 			@Override
 			public void actionPerformed(ActionEvent e) {
 				new BuyGames(user);
 				PlayerFrame.dispose();
 			}
          });
         e2=new JMenuItem("Owned Games");
         e2.addActionListener(new ActionListener() {
 			@Override
 			public void actionPerformed(ActionEvent e) {
 				new OwnedGame(user);
 				PlayerFrame.dispose();
 			}
          });
         menu2.setForeground(Color.WHITE);
         
         menu1.add(i1); 
         menu2.add(e1);
         menu2.add(e2);
         
         mb.add(menu1);
         mb.add(menu2);
         PlayerFrame.setJMenuBar(mb);
         
        JLabel logo = new JLabel("Stim");
 		logo.setBounds(300, 200, 200, 80);
 		logo.setForeground(Color.BLACK);
 		logo.setFont(f1);
 		PlayerFrame.add(logo);
         
 		PlayerFrame.setSize(800,600);  
 		PlayerFrame.setLayout(null);  
 		PlayerFrame.setVisible(true);
 		
 		PlayerFrame.setLocationRelativeTo(null);
	}
}
