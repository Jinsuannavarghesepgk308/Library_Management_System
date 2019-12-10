package LMS;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;


public class LMS_Admin {

dbcon con=new dbcon();

public void adminlogin() throws ClassNotFoundException, SQLException, IOException {

Scanner s = new Scanner(System.in);
Statement s1 = (Statement) con.getConnection().createStatement();
ResultSet rs = s1.executeQuery("select * from admin");
System.out.println("Enter Username:");
String name = s.next();
System.out.println("Enter Password");
String pwd = s.next();
int f = 0;
while (rs.next()) {
String name1 = rs.getString(1);
String pass1 = rs.getString(2);
if (name1.equals(name) && pass1.equals(pwd)) 
	{
	System.out.println("Sucessfully Verified");
	LMS_Admin adl = new LMS_Admin();
	adl.choice();
	f=1;
	}
}
if(f==0)
{
	System.out.println("Unauthorized Login Attempt");
LMS_Main m=new LMS_Main();
m.choices();


}
}

// if (f == 1) {




public void choice() throws ClassNotFoundException, SQLException, IOException {
// TODO Auto-generated method stub

Scanner s = new Scanner(System.in);
System.out.println("1.Add book \n2.Display books\n3.Remove book\n4.Update book\n5.Add student\n6.Remove student\n7.Logout");
System.out.println("Enter the choice:");
int ch = s.nextInt();
switch (ch) {
case 1://add book

System.out.println("Enter Book Id");
int id = s.nextInt();
int idd=0,flag=0;
int status=0;
Statement ss1=(Statement)con.getConnection().createStatement();
ResultSet sr=ss1.executeQuery("Select * from books");
while(sr.next())
{
	 idd=sr.getInt(1);
	 if(idd==id)
		{
			System.out.println("Book Id is already Exist");
			flag=1;
			choice();
			break;
		}
}
	
	if(flag==0)
	{

System.out.println("Enter Book Name");
String bname = s.next();
System.out.println("Enter the author");
String author = s.next();
System.out.println("Enter edition");
int edition = s.nextInt();
PreparedStatement st = (PreparedStatement) con.getConnection().prepareStatement("insert into  books (book_id,book_name,author,edition,status)values(?,?,?,?,?);");
st.setInt(1, id);
st.setString(2, bname);
st.setString(3, author);
st.setInt(4, edition);
st.setInt(5,status);

System.out.println("Book Added Sucessfully");
st.executeUpdate();
choice();
	}
break;

case 2://display book

Statement s1 = (Statement) con.getConnection().createStatement();
ResultSet r = s1.executeQuery("Select * from books");
while (r.next()) {

System.out.println("Book Id:\t" + r.getInt(1) + " \n" + "Book Name:\t" + r.getString(2) + " \n"
+ "Book author:\t" + r.getString(3) + "\nEdition:\t" + r.getInt(4));
System.out.println("#####################################");
}
choice();
break;

case 3://remove book
System.out.println("Enter Id");
int iddd = s.nextInt();
PreparedStatement st1 = (PreparedStatement) con.getConnection()
.prepareStatement("delete  from books  where book_id=?;");
st1.setInt(1, iddd);
st1.executeUpdate();
con.getConnection().close();
System.out.println("Data Deleted");

choice();
break;

case 4://update book

						System.out.println("Choose Option");
						System.out.println("1.Update Id\n2.Update Book Name\n3.Update Author Name\n4.Update Edition\n5.Exit Updation");
						int  op=s.nextInt();
						System.out.println("Enter Id");
						int update_id=s.nextInt();
						
						switch(op)
						{
						case 1:
							Statement sup1=(Statement)con.getConnection().createStatement();
							ResultSet rup1=sup1.executeQuery("Select * from books ");
							//s4.setInt(1,update_id);
							while(rup1.next())
							{
								if(rup1.getInt(1)==update_id)
								{
									System.out.println("Enter New Id");
									int newid=s.nextInt();
								//int upid1=rup1.getInt(1);
								PreparedStatement stup1=(PreparedStatement) con.getConnection().prepareStatement("update books set book_id=? where book_id=?");
								stup1.setInt(1, newid);
								stup1.setInt(2, update_id);
								stup1.executeUpdate();
								}
							}
							
							break;
						
						case 2:
							Statement sup2=(Statement)con.getConnection().createStatement();
							ResultSet rup2=sup2.executeQuery("Select * from books ");
							//s4.setInt(1,update_id);
							while(rup2.next())
							{
								if(rup2.getInt(1)==update_id)
								{
									System.out.println("Enter Book Name");
									String p_name=s.next();
								//int upid1=rup1.getInt(1);
								PreparedStatement stup2=(PreparedStatement) con.getConnection().prepareStatement("update books set book_name=? where book_id=?");
								stup2.setString(1,p_name);
								stup2.setInt(2, update_id);
								stup2.executeUpdate();
								}
							}
							
							break;
							
						case 3:
						
						int iddq=0;
						String author="";
						Statement ss4=(Statement)con.getConnection().createStatement();
						ResultSet rr4=ss4.executeQuery("Select * from books ");
						//s4.setInt(1,update_id);
						while(rr4.next())
						{
				
							if(rr4.getInt(1)==update_id)
							{
							iddq=rr4.getInt(1);
							author=rr4.getString(3);
							}
						}
						System.out.println("Enter New Author Name");
						String new_authorname=s.next();
						
						PreparedStatement st2=(PreparedStatement) con.getConnection().prepareStatement("update books set author=? where book_id=?;");
						st2.setInt(1,iddq);
						st2.setString(2,new_authorname);	
						st2.executeUpdate();
						break;
						
						case 4:
							
							Statement sup3=(Statement)con.getConnection().createStatement();
							ResultSet rup3=sup3.executeQuery("Select * from books ");
							//s4.setInt(1,update_id);
							while(rup3.next())
							{
								if(rup3.getInt(1)==update_id)
								{
									System.out.println("Enter Edition");
									int edition=s.nextInt();
								//int upid1=rup1.getInt(1);
								PreparedStatement stup3=(PreparedStatement) con.getConnection().prepareStatement("update books set edition=? where book_id=?");
								stup3.setInt(1,edition);
								stup3.setInt(2, update_id);
								stup3.executeUpdate();
								}
							}
							
							break;
							
						case 5:
							choice();
							break;
						
					
						//break;
							default:
								break;
								
								
						}
						System.out.println(" Updated Successfully");
						choice();
					break;
					
case 5://add student

System.out.println("Enter Student Id");
int sid = s.nextInt();
System.out.println("Enter Student Name");
String sname = s.next();
PreparedStatement st3 = (PreparedStatement) con.getConnection().prepareStatement("insert into  student(id,name)values(?,?);");
st3.setInt(1, sid);
st3.setString(2, sname);

System.out.println("Student Added Sucessfully");
st3.executeUpdate();
choice();
break;

case 6://remove student

System.out.println("Enter Id");
String name1 = s.next();
PreparedStatement st4 = (PreparedStatement) con.getConnection().prepareStatement("delete  from books  where book_name=?;");
st4.setString(1, name1);
st4.executeUpdate();
con.getConnection().close();
System.out.println("Student Deleted");
choice();
break;
case 7:
	LMS_Main lm=new LMS_Main();
	lm.choices();
default:
break;
}
}
}