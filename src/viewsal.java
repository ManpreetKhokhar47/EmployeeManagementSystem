import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;


public class viewsal extends db{


	JPanel p,p1,p2,p3;
	JLabel lblnm,lbldt;
	JTextField txtid;
	JButton back,srch;
	Statement st;
	ResultSet rs;
	
	JLabel nm,lblidnm,lblid,lblrgstndt,lblrgdt,lblsal,lblpost,lblname,lblpostval,lblsalval,pday,pdayval,sal,salval;

	JComboBox mnth,year;
	
	
	
	viewsal()
	{
		int width=180;
		p = new JPanel();
		p.setLayout(null);
		
		p1 = new JPanel(null);
		p1.setBorder(BorderFactory.createTitledBorder(" Enter User ID "));
		p1.setBounds(100,80,270,100);
		
		p2 = new JPanel(null);
		p2.setBorder(BorderFactory.createTitledBorder(" User Information "));
		p2.setBounds(100,200,800,400);
		p2.setVisible(false);
		
		p3 = new JPanel(null);
		p3.setBorder(BorderFactory.createTitledBorder(" Select Date "));
		p3.setBounds(390,80,510,100);
		
		lblnm = new JLabel("User ID : ");
		lblnm.setBounds(50, 40,60, 25);
		
		txtid = new JTextField(20);
		txtid.setBounds(120, 40, 100, 25);
		
		srch = new JButton("Search Record");
		srch.setBounds(470, 40, 200, 25);
		
		
		back = new JButton("Back");
		back.setBounds(300, 325, 200, 35);
		
		// p2 components starts
		
		lblidnm = new JLabel("Employee ID : ");
		lblidnm.setBounds(50, 50, 150, 25);
		
		lblid = new JLabel("125");
		lblid.setBounds(180, 50, width, 25);
		
		nm = new JLabel("Employee Name : ");
		nm.setBounds(50, 100, 150, 25);
		
		lblname = new JLabel("Manpreet");
		lblname.setBounds(180, 100, width, 25);
		
	
	
		
		lblrgstndt = new JLabel("Registration Date : ");
		lblrgstndt.setBounds(430, 50, 150, 25);
		
		lblrgdt = new JLabel();
		lblrgdt.setBounds(580, 50, width, 25);
		
		
		lblsal = new JLabel("Salary (Per Month) : ");
		lblsal.setBounds(430, 100, 150, 25);
		
		lblsalval = new JLabel("15000");
		lblsalval.setBounds(580, 100, width, 25);
		
		lblpost = new JLabel("Post : ");
		lblpost.setBounds(50, 150, 150, 25);
		
		lblpostval = new JLabel("Lecturer");
		lblpostval.setBounds(180, 150, width, 25);
		
		pday = new JLabel("Toatl Present Day's : ");
		pday.setBounds(430, 150, width, 25);
		
		pdayval = new JLabel("28");
		pdayval.setBounds(580, 150, width, 25);
		
		sal = new JLabel("Balanced Salary :");
		sal.setBounds(430, 200, width, 25);
		
		salval = new JLabel("12500");
		salval.setBounds(580, 200, width, 25);
		
		lbldt = new JLabel("Select Date : ");
		lbldt.setBounds(40, 40, width, 25);
		
		String arr[] = {"Select Month","01","02","03","04","05","06","07","08","09","10","11","12"};
		mnth = new JComboBox (arr);
		mnth.setBounds(140, 40, 100, 25);
		
		String yr[] = {"Select Year"};
		year = new JComboBox (yr);
		year.setBounds(250, 40, 100, 25);
		
		srch = new JButton("Search Record");
		srch.setBounds(360, 40, 120, 25);
		
		// p2 components ends
		
		//-------------------
		p2.add(lblidnm);
		p2.add(lblid);
		p2.add(nm);
		p2.add(lblname);
	
		p2.add(lblrgstndt);
		p2.add(lblrgdt);
		p2.add(lblsal);
		p2.add(lblsalval);
		p2.add(lblpost);
		p2.add(lblpostval);
		p2.add(pday);
		p2.add(pdayval);
	
		p2.add(back);
		p2.add(sal);
		p2.add(salval);
		//----------------
		
		p3.add(lbldt);
		p3.add(srch);
		p3.add(mnth);
		p3.add(year);
		
		p1.add(lblnm);
		p1.add(txtid);
		p.add(p1);
		p.add(p2);
		p.add(p3);
		add(p);
		
		getyears();
		
		back.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent arg0) {
			
				setVisible(false);
				
			}
		});
		
		srch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			
				String mn,yr,id,cnm,sal ="";
				String name="";
				int total=0;
				int present=0,flag=0;
				
				id = txtid.getText();
				mn = (String) mnth.getSelectedItem();
				yr = (String) year.getSelectedItem();
				
				if(id==""||mn=="Select Month" || yr =="Select Year")
				{
					JOptionPane.showMessageDialog(null, "Please Enter ID or Choose Date");
				}
				else
				{
					try
					{
						st = con.createStatement();
						rs = st.executeQuery("Select * from emp_info where id="+id);
						
						while(rs.next())
						{
							flag=1;
						}
						
						if(flag == 1)
						{
							st = con.createStatement();
							rs = st.executeQuery("Select name,salary,post,reg_date from emp_info where id="+id);
						
							while(rs.next())
							{
								name = rs.getString(1).replaceAll("\\s+","");
								sal = rs.getString(2);
								lblid.setText(id);
								lblname.setText(name);
								lblsalval.setText(rs.getString(2));
								lblpostval.setText(rs.getString(3));
								lblrgdt.setText(rs.getString(4));
								
							}
							
							cnm = name+"_"+id;
							rs = st.executeQuery("select att_date from emp_att where att_date like '___"+mn+"_"+yr+"'");
							while(rs.next())
							{
								total++;
							}
							if(total<28)
							{
								total=30;
							}
							
						
							rs = st.executeQuery("select att_date from emp_att where att_date like '___"+mn+"_"+yr+"' and "+cnm+"='Present'");
							while(rs.next())
							{
								present++;
							}
							
							if(present>0)
							{
						
							int ttlsal = (Integer.parseInt(sal)/total)*present;
						
							System.out.println("Total :"+ total +"Present :"+present+ "Sal : "+ttlsal );
						
							pdayval.setText(Integer.toString(present));
							salval.setText(Integer.toString(ttlsal));
							p2.setVisible(true);
							}
							else
							{
								p2.setVisible(false);
								JOptionPane.showMessageDialog(null, "No Record Found");
							}
						
						}
						else
						{
							p2.setVisible(false);
							JOptionPane.showMessageDialog(null, "No record Found");
						}
					}
					catch(Exception e)
					{
						System.out.println("Error "+e);
					}
				}
				
				
			}
		});
		
		
		setVisible(true);
		setSize(1000, 700);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Employee Management System ( Update Information )");
			
}

	private void getyears() 
	{
		try
		{
			st = con.createStatement();
			rs = st.executeQuery("select distinct SUBSTR(att_date,7,10) from emp_att");
			while(rs.next())
			{
				year.addItem(rs.getString(1));
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Error : "+e);
		}
	}
}