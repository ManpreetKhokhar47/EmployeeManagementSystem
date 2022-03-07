
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.jws.soap.SOAPBinding.Style;
import javax.swing.*;


class login extends JFrame
{
	JPanel p,p1;
	JLabel lblnm,lblpwd,name,error;
	JTextField txtnm, txtpwd;
	JButton btnlgn,btnexit;
	Font f;
	
	login()
	{
		f = new Font("Arial",Font.BOLD, 50);
		p = new JPanel(null);
		name = new JLabel("Employee Management System");
		name.setFont(f);
		name.setBounds(126, 20, 748, 100);
		
		p1 = new JPanel(null);
		p1.setBounds(300, 200,350,225);
		p1.setBorder(BorderFactory.createTitledBorder("  LOGIN  "));
		
		
		lblnm = new JLabel("User Name : ");
		lblnm.setBounds(50, 50, 100, 25);
		
		txtnm = new JTextField(20);
		txtnm.setBounds(150, 50, 150, 25);
		
		
		lblpwd = new JLabel("Password : ");
		lblpwd.setBounds(50, 100, 100, 25);
		
		txtpwd = new JTextField(20);
		txtpwd.setBounds(150, 100, 150, 25);
		
		btnlgn = new JButton("Login");
		btnlgn.setBounds(50, 150, 120, 25);
		
		btnexit= new JButton("Exit");
		btnexit.setBounds(180, 150, 120, 25);
		
		error = new JLabel("Invalid Username or Password");
		error.setForeground(Color.red);
		error.setBounds(90, 190, 180, 25);
		error.setVisible(false);
		
		p1.add(lblnm);
		p1.add(txtnm);
		p1.add(lblpwd);
		p1.add(txtpwd);
		p1.add(btnlgn);
		p1.add(btnexit);
		p1.add(error);
		p.add(p1);
		p.add(name);
		add(p);
		
		setSize(1000, 700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Employee Management System ( Login )");
		
		
		btnlgn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				if(txtnm.getText().equals("manpreet") && txtpwd.getText().equals("1234"))
				{
					setVisible(false);
					new welcome();
				}
				else
				{
					error.setVisible(true);
				}
				
			
		}
		});
		
		btnexit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(0);
			}
		});
		
		
	}
}

class Homepage {
	public static void main(String args[])
	{
		//new login();
		new welcome();
		//new newuser();
		//new dltuser();
		//new att();
		//new examples();
		//new viewsal();
	}
}
