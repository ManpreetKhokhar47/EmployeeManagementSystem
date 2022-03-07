import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;


class dltuser extends db{

	JPanel p,p1,p2;
	JLabel lblnm;
	JTextField txtid;
	JButton dlt,back,srch;
	Statement st;
	ResultSet rs;
	
	JLabel nm,lblidnm,lblid,lblfnm,lblcity,lbladd,lblrgstndt,lblrgdt,lblsal,lblpost,lblcnno;
	JTextField txtnm,txtfnm,txtcity,txtsal,txtpost,txtcnno;
	JTextArea txtadd;
	
	int id;
	String name="";
	
	dltuser()
	{
		int width=180;
		p = new JPanel();
		p.setLayout(null);
		
		p1 = new JPanel(null);
		p1.setBorder(BorderFactory.createTitledBorder(" Enter User ID "));
		p1.setBounds(100,80,800,100);
		
		p2 = new JPanel(null);
		p2.setBorder(BorderFactory.createTitledBorder(" User Information "));
		p2.setBounds(100,200,800,400);
		p2.setVisible(false);
		
		lblnm = new JLabel("Enter Employee ID : ");
		lblnm.setBounds(120, 40, 150, 25);
		
		txtid = new JTextField(20);
		txtid.setBounds(270, 40, 150, 25);
		
		srch = new JButton("Search Record");
		srch.setBounds(470, 40, 200, 25);
		
		dlt = new JButton("Delete");
		dlt.setBounds(150, 325, 200, 35);
		
		back = new JButton("Back");
		back.setBounds(450, 325, 200, 35);
		
		// p2 components starts
		
		lblidnm = new JLabel("Employee ID : ");
		lblidnm.setBounds(50, 50, 150, 25);
		
		lblid = new JLabel();
		lblid.setBounds(180, 50, width, 25);
		
		nm = new JLabel("Employee Name : ");
		nm.setBounds(50, 100, 150, 25);
		
		txtnm = new JTextField(20);
		txtnm.setBounds(180, 100, width, 25);
		
		lblfnm = new JLabel("Father Name : ");
		lblfnm.setBounds(50, 150, 150, 25);
		
		txtfnm = new JTextField(20);
		txtfnm.setBounds(180, 150, width, 25);
		
		lblcity = new JLabel("City : ");
		lblcity.setBounds(50, 200, 150, 25);
		
		txtcity = new JTextField(20);
		txtcity.setBounds(180, 200, width, 25);
		
		lbladd = new JLabel("Address : ");
		lbladd.setBounds(430, 200, 150, 25);
		
		txtadd = new JTextArea();
		txtadd.setBounds(580, 200, width, 75);
		
		lblrgstndt = new JLabel("Registration Date : ");
		lblrgstndt.setBounds(430, 50, 150, 25);
		
		lblrgdt = new JLabel();
		lblrgdt.setBounds(580, 50, width, 25);
		lblrgdt.setText(new SimpleDateFormat("dd-MM-yy").format(new Date()));
		
		lblsal = new JLabel("Salary : ");
		lblsal.setBounds(430, 100, 150, 25);
		
		txtsal = new JTextField(20);
		txtsal.setBounds(580, 100, width, 25);
		
		lblpost = new JLabel("Post : ");
		lblpost.setBounds(430, 150, 150, 25);
		
		txtpost = new JTextField(20);
		txtpost.setBounds(580, 150, width, 25);
		
		lblcnno = new JLabel("Contact No : ");
		lblcnno.setBounds(50, 250, 150, 25);
		
		txtcnno = new JTextField(20);
		txtcnno.setBounds(180, 250, width, 25);
		
		
		// p2 components ends
		
		//-------------------
		p2.add(lblidnm);
		p2.add(lblid);
		p2.add(nm);
		p2.add(txtnm);
		p2.add(txtfnm);
		p2.add(lblfnm);
		p2.add(lblcity);
		p2.add(txtcity);
		p2.add(lbladd);
		p2.add(txtadd);
		p2.add(lblrgstndt);
		p2.add(lblrgdt);
		p2.add(lblsal);
		p2.add(txtsal);
		p2.add(lblpost);
		p2.add(txtpost);
		p2.add(lblcnno);
		p2.add(txtcnno);
		p2.add(dlt);
		p2.add(back);
		//----------------
		
		p1.add(lblnm);
		p1.add(txtid);
		p1.add(srch);
		p.add(p1);
		p.add(p2);
		
		
		
		add(p);
			
		
		srch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String uid = txtid.getText();
				
				
				if(uid.isEmpty())
				{
					JOptionPane.showMessageDialog(null,"Please Enter User ID");
				}
				else
				{
					id = Integer.parseInt(uid);
					
				}
				
				try {
					int r=0;
					st = con.createStatement();
					rs = st.executeQuery("select * from emp_info where id ="+id);
					
					while(rs.next())
					{
						r++;
					}
					
					if(r>0)
					{
						p2.setVisible(true);
					rs = st.executeQuery("select * from emp_info where id ="+id);
				
					while(rs.next())
					{
						lblid.setText(Integer.toString(rs.getInt(1)));
						txtnm.setText(rs.getString("name"));
						name = rs.getString("name");
						txtfnm.setText(rs.getString("fname"));
						txtcity.setText(rs.getString("city"));
						txtcnno.setText(rs.getString("contactno"));
						lblrgdt.setText(rs.getString("reg_date"));
						txtsal.setText(rs.getString("salary"));
						txtpost.setText(rs.getString("post"));
						txtadd.setText(rs.getString("address"));
					}
					}
					else
					{
						p2.setVisible(false);
						JOptionPane.showMessageDialog(null,"No Record Found");
					}
					
								
					
				}
				catch (Exception e) 
				{
					System.out.println("Error : " + e);
				}
				
				
			}
		});
		
		dlt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String cnm="";
				try
				{
				PreparedStatement myst = con.prepareStatement("delete from emp_info where id=?");
				myst.setInt(1,id);
				cnm = name.replaceAll("\\s","")+"_"+id;
				int r = st.executeUpdate("alter table emp_att drop column "+cnm);
				
				if(myst.executeQuery() != null)
				{
					if(r==0)
					{
					JOptionPane.showMessageDialog(null, "Record Deleted Successfully");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Something Wrong...!");
				}

				}
				catch(Exception e)
				{
					System.out.println("Error : "+e);
				}
				
			}
		});
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				
			}
		});
		
		
		
		
		setVisible(true);
		setSize(1000, 700);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Employee Management System (Delete User Information)");
		
	}
	
}



