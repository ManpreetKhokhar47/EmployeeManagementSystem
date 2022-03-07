import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;


class newuser extends db{

	JPanel p,p1;
	JLabel nm,lblidnm,lblid,lblfnm,lblcity,lbladd,lblrgstndt,lblrgdt,lblsal,lblpost,lblcnno;
	JTextField txtnm,txtfnm,txtcity,txtsal,txtpost,txtcnno;
	JTextArea txtadd;
	JButton ad,back;
	Statement st;
	ResultSet rs;
	JRadioButton male,femail;
	ButtonGroup btn;
	
	int id;
	
	newuser()
	{
		int x,y,width=200,height=25;
		getid();
		p = new JPanel();
		p.setLayout(null);
		
		p1 = new JPanel(null);
		p1.setBounds(50,100,900,500);
		p1.setBorder(BorderFactory.createTitledBorder("New User Registration"));
		
		lblidnm = new JLabel("Employee ID : ");
		lblidnm.setBounds(50, 50, 150, 25);
		
		lblid = new JLabel();
		lblid.setText(Integer.toString(id));
		lblid.setBounds(150, 50, width, 25);
		
		nm = new JLabel("Enter Employee Name : ");
		nm.setBounds(50, 100, 150, 25);
		
		txtnm = new JTextField(20);
		txtnm.setBounds(200, 100, width, 25);
		
		lblfnm = new JLabel("Father Name : ");
		lblfnm.setBounds(50, 150, 150, 25);
		
		txtfnm = new JTextField(20);
		txtfnm.setBounds(200, 150, width, 25);
		
		lblcity = new JLabel("City : ");
		lblcity.setBounds(50, 200, 150, 25);
		
		txtcity = new JTextField(20);
		txtcity.setBounds(200, 200, width, 25);
		
		lbladd = new JLabel("Address : ");
		lbladd.setBounds(450, 200, 150, 25);
		
		txtadd = new JTextArea();
		txtadd.setBounds(600, 200, width, 75);
		
		lblrgstndt = new JLabel("Registration Date : ");
		lblrgstndt.setBounds(450, 50, 150, 25);
		
		lblrgdt = new JLabel();
		lblrgdt.setBounds(600, 50, width, 25);
		lblrgdt.setText(new SimpleDateFormat("dd-MM-yy").format(new Date()));
		
		lblsal = new JLabel("Salary : ");
		lblsal.setBounds(450, 100, 150, 25);
		
		txtsal = new JTextField(20);
		txtsal.setBounds(600, 100, width, 25);
		
		lblpost = new JLabel("Post : ");
		lblpost.setBounds(450, 150, 150, 25);
		
		txtpost = new JTextField(20);
		txtpost.setBounds(600, 150, width, 25);
		
		lblcnno = new JLabel("Contact No : ");
		lblcnno.setBounds(50, 250, 150, 25);
		
		txtcnno = new JTextField(20);
		txtcnno.setBounds(200, 250, width, 25);
		
		
		ad = new JButton("Add");
		ad.setBounds(250, 350, width, height+10);
		
		back = new JButton("Back");
		back.setBounds(500, 350, width, height+10);
		
		
		p1.add(lblidnm);
		p1.add(lblid);
		p1.add(nm);
		p1.add(txtnm);
		p1.add(txtfnm);
		p1.add(lblfnm);
		p1.add(lblcity);
		p1.add(txtcity);
		p1.add(lbladd);
		p1.add(txtadd);
		p1.add(lblrgstndt);
		p1.add(lblrgdt);
		p1.add(lblsal);
		p1.add(txtsal);
		p1.add(lblpost);
		p1.add(txtpost);
		p1.add(lblcnno);
		p1.add(txtcnno);
		p1.add(ad);
		p1.add(back);
		
		
		p.add(p1);
		add(p);
		
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				setVisible(false);
			}
		});
		
		
		
		ad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name,fnm,cty,cnno,rdt,sal,post,add;
				name = txtnm.getText();
				fnm = txtfnm.getText();
				cty = txtcity.getText();
				cnno = txtcnno.getText();
				rdt = lblrgdt.getText();
				sal = txtsal.getText();
				post = txtpost.getText();
				add = txtadd.getText();
				
				
				String cnm = name.replaceAll("\\s+","")+"_"+Integer.toString(id);
				try {
					st = con.createStatement();
					PreparedStatement myst = con.prepareStatement("insert into emp_info values(?,?,?,?,?,?,?,?,?)");
					
					int result = st.executeUpdate("alter table emp_att add "+cnm+" varchar(30)");
					myst.setInt(1,id);
					myst.setString(2,name);
					myst.setString(3,fnm);
					myst.setString(4,cty);
					myst.setString(5,cnno);
					myst.setString(6,rdt);
					myst.setString(7,sal);
					myst.setString(8,post);
					myst.setString(9,add);
					
					
					if(myst.executeQuery() != null)
					{
						if(result == 0)
						{
							JOptionPane.showMessageDialog(null, "Record Inserted Successfully");
							getid();
							lblid.setText(Integer.toString(id));
							clearcontrols();
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "bai nai gal");
					}
					
				}
				catch (Exception e) 
				{
					System.out.println("Error : " + e);
				}
				
				
			}

			
		});
		
		
		
		
		
		setVisible(true);
		setSize(1000, 700);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Employee Management System (New User Registration)");
		
	}
	private void clearcontrols() {
		
		txtnm.setText("");
		txtfnm.setText("");
		txtcity.setText("");
		txtcnno.setText("");
		txtsal.setText("");
		txtpost.setText("");
		txtadd.setText("");
		
		
	}
	
	private void getid() {
		try {
			st  = con.createStatement();
			rs = st.executeQuery("select max(id) from emp_info");
			
			//int id = rs.getInt("id");
			while(rs.next())
			{
				if(rs.getInt("max(id)")==0)
				{
					id=101;
				}
				else
				{
					id= rs.getInt("max(id)")+1;
				}
				
			}
			
		} 
		catch (Exception e) 
		{
			System.out.print("Error "+ e);
			
		}
	}
	
}
