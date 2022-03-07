
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.jws.soap.SOAPBinding.Style;
import javax.swing.*;


class welcome extends JFrame
{
	JPanel p,p1;
	JLabel lblnm,lblpwd,name,error;
	JTextField txtnm, txtpwd;
	JButton newusr,dltusr,viewsal,att,exit,updtinfo;
	Font f;
	int w=250,h=60;
	
	welcome()
	{
		f = new Font("Arial",Font.BOLD, 50);
	
		p = new JPanel(null);
	
		name = new JLabel("Employee Management System");
		name.setFont(f);
		name.setBounds(126, 20, 748, 100);
		
		newusr = new JButton("New User Registration");
		newusr.setBounds(75, 150, w, h);
	
		
		dltusr = new JButton("Delete User");
		dltusr.setBounds(375, 150, w, h);
		
		updtinfo = new JButton("Update User Info");
		updtinfo.setBounds(675, 150, w, h);
		
		viewsal = new JButton("View Employee Salary");
		viewsal.setBounds(75, 250, w, h);
		
		att = new JButton("Attendance");
		att.setBounds(375, 250, w, h);
		
		exit = new JButton("Exit");
		exit.setBounds(675, 250, w, h);
		
		
		
		p.add(newusr);
		p.add(dltusr);
		p.add(updtinfo);
		p.add(viewsal);
		p.add(att);
		p.add(exit);
		p.add(name);
		
		add(p);
		
		dltusr.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
		
				new dltuser();
			}
		});
		exit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
			}
		});
		
		att.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new att();
				
			}
		});
		
		updtinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new updateinfo();
				
			}
		});
		newusr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new newuser();
				
			}
		});
		
		viewsal.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent arg0) {
			
				new viewsal();
				
			}
		});
		
		setSize(1000, 700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Employee Management System ( Welcome )");
		
	}
}


