import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Scrollbar;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;


class att extends db{
	
	JPanel p ,p1,p2,patt,psts,pin,pbout;
	JLabel lbl[],lblid,lblnm, lblsid,lblsnm,lblspost,lblsts,lblsdt,idval,nmval,pstval,stsval,dtval;
	JButton btn[],done;
	JScrollPane jsp;
	
	ResultSet rs,rs1;
	Statement st;
	int r;
	int uid;
	att()
	{
		
	
		int i=1;
		p = new JPanel();
		p.setLayout(null);
		
		p1 = new JPanel();
		p1.setLayout(new GridLayout(0,2,10,10));
		
		patt = new JPanel(null);
		patt.setBounds(80, 50, 390, 550);
		patt.setBorder(BorderFactory.createTitledBorder(" Attendance "));
		
		psts = new JPanel(null);
		psts.setBounds(550, 50, 350, 300);
		psts.setBorder(BorderFactory.createTitledBorder(" Status "));
		
		pin = new JPanel(null);
		pin.setBounds(20, 30, 310, 250);
		pin.setBorder(BorderFactory.createLineBorder(Color.black));
		
		pbout = new JPanel(null);
		pbout.setBounds(550, 400, 350, 75);
		pbout.setBorder(BorderFactory.createTitledBorder(""));
		
		
		p2 = new JPanel();
		
		lbl = new JLabel[50];
		btn = new JButton[50];
		
		lblid = new JLabel("ID              Name");
		
		lblnm = new JLabel("");
			
		lblsid = new JLabel("ID : ");
		lblsid.setBounds(40,30,50,25);
		
		lblsnm = new JLabel("Name : ");
		lblsnm.setBounds(40,70,50,25);
		
		lblspost = new JLabel("Post : ");
		lblspost.setBounds(40,110,50,25);
		
		lblsdt = new JLabel("Date : ");
		lblsdt.setBounds(40,150,50,25);
		
		lblsts = new JLabel("Status : ");
		lblsts.setBounds(40,190,50,25);
		
		
		idval = new JLabel();
		idval.setBounds(120,30,150,25);
		
		nmval = new JLabel();
		nmval.setBounds(120,70,150,25);
		
		pstval = new JLabel();
		pstval.setBounds(120,110,150,25);
		
		dtval = new JLabel();
		dtval.setBounds(120,150,150,25);
		
		stsval = new JLabel();
		stsval.setBounds(120,190,150,25);
		
		done = new JButton("Done");
		done.setBounds(20,20, 310, 35);
		
		pin.add(lblsid);
		pin.add(lblsnm);
		pin.add(lblspost);
		pin.add(lblsdt);
		pin.add(lblsts);
		pin.add(nmval);
		pin.add(idval);
		pin.add(pstval);
		pin.add(stsval);
		pin.add(dtval);
		
		int r=0;
		int flag=0;
		
		
		try
		{
			String dt = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			st = con.createStatement();
			rs = st.executeQuery("select att_date from emp_att");

			while(rs.next())
			{
				if(rs.getString(1).equals(dt))
				{
					flag=1;
				}
			}
			
			if(flag==0)
			{
				adminatt();
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Error : "+e);
		}
		
		
		
		try {
		
			st = con.createStatement();
			rs = st.executeQuery("select * from emp_info order by id asc");
			int lx=30,ly=50,bx=170,by=50;
			p1.add(lblid);
			p1.add(lblnm);
			
			while (rs.next())
			{
				uid = rs.getInt("id");
				lbl[i] = new JLabel(rs.getInt("id") +"        "+ rs.getString("name"));
				//lbl[i].setBounds(lx, ly, 200, 25);
				btn[i] = new JButton("Present");
				//btn[i].setBounds(bx, by, 150, 25);
				
				btn[i].setName(rs.getString("name"));
				final String s = btn[i].getName().replaceAll("\\s", "") +"_"+ rs.getInt("id");
				
				p1.add(lbl[i]);
				p1.add(btn[i]);
				
				btn[i].addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent arg0) 
					{
						String dt1 = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
						try
						{
							System.out.println(s);
							PreparedStatement mystmt = con.prepareStatement("update emp_att set "+ s +" = 'Present' where att_date = ?");
							mystmt.setString(1, dt1);
							
							if(mystmt.executeQuery() != null)
							{
								System.out.println("Ok");
								getstatus();
							}
						}
						catch(Exception e)
						{
							System.out.println("Error : "+ e);
						}
					}

					
				});	
				ly = ly+40;
				by = by+40;
				i++;
			}
		}
		catch(Exception e)
		{
			System.out.print("Error :" + e );
		}
		
		
		
		
		p2.add(p1);
		
		jsp = new JScrollPane(p2);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setBounds(100, 80, 350, 500);
		
		p.setPreferredSize(new Dimension(1000, 700));
		p.add(jsp);
		
		p.add(patt);
		psts.add(pin);
		pbout.add(done);
		p.add(psts);
		p.add(pbout);
		setContentPane(p);
		
		pack();
	
		done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				
			}
		});
		
		
		
		setVisible(true);
		setSize(1000, 700);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Employee Management System ( Daily Attendance )");
	}
	private void adminatt() {
		try{
			String s = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			PreparedStatement stmt = con.prepareStatement("insert into emp_att (att_date,admin) values (?,?)");
			stmt.setString(1, s);
			stmt.setString(2,"Present");
			if(stmt.executeQuery() == null)
			{
				JOptionPane.showMessageDialog(null, "Something went Wrong...");
			}
		
		}
		catch(Exception e)
		{
			System.out.println("Error" + e);
		}
	}
	private void getstatus() {
		
		try{
			
			
			st = con.createStatement();
			rs = st.executeQuery("select * from emp_info where id="+uid);
			
			while(rs.next())
			{
				idval.setText(Integer.toString(rs.getInt("id")));
				nmval.setText(rs.getString("name"));
				pstval.setText(rs.getString("post"));
				stsval.setText("Present");
				dtval.setText(new SimpleDateFormat("dd-MMM-yyyy").format(new Date()));
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Gal nai Bandi inj "+ e);
		}
		
		
	}
	
	
}

