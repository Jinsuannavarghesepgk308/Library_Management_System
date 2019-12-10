package LMS;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class LMS_Student {
dbcon con=new dbcon();
		public int stid;

		public void studentLogin() throws ClassNotFoundException, SQLException, IOException
	{
		// TODO Auto-generated method stub
		
		//Create Scanner object
		Scanner s = new Scanner(System.in);
		//Create object of student class
		LMS_Student std = new LMS_Student();
		//create statement object
		Statement s1 = (Statement) con.getConnection().createStatement();
		//Login operation of the user by entering userid and password
		System.out.println("Enter Your id:");
		int stid = s.nextInt();
		//Select all details from student database where the id is matching 
		PreparedStatement stmt = (PreparedStatement) con.getConnection().prepareStatement("select * from student where id = ?");    
		stmt.setInt(1, stid);    
		ResultSet rs = stmt.executeQuery();
		int f = 0;
		while (rs.next())
		{
		int stid1 = rs.getInt(1);
		
		if (stid==stid1 ) {
			System.out.println("Sucessfully Verified");
			std.choice(stid);
		}
		else
		{
			System.out.println("Unauthorized User");
			std.choice(stid);
		}
		}
		if(rs.next()==false)
		{
	
			System.out.println("You  are  not registered yet!! Contact Admin ");
			std.choice(stid);
		}
		
	}
	

public  void choice(int stid) throws ClassNotFoundException, SQLException, IOException {
// TODO Auto-generated method stub
//Student operation choices  are displaying
Scanner s=new Scanner(System.in);
System.out.println();
System.out.println("1.View Books\n2.Order Book\n3.View Order\n4.Check Book Status\n5.Logout\n");
System.out.println("Choose Option");
int n=s.nextInt();
int st_id=this.stid;
switch(n)
{
	case 1:
	
		
		//View All Books
		
		{
			System.out.println("-----------------------");
			System.out.println("\tBooks\t\t");
			System.out.println(" ");
			PreparedStatement s6=(PreparedStatement) con.getConnection().prepareStatement("Select * from books  ");
			ResultSet r6 = s6.executeQuery();
			while (r6.next())
			{
				System.out.println("-----------------------");
				System.out.println();
				System.out.println("Book ID:\t"+r6.getInt(1) + "\nBook name:\t" + r6.getString(2) + 
						"\nAuthor:\t" + r6.getString(3) +"\nEdition:\t"+ r6.getString(4)  );
				System.out.println();
				System.out.println("-----------------------");
			}
			
			
		}
		choice(stid);
	
	break;
	
	case 2:
		//Order Book by the student
	{

		System.out.println("Enter Book Id");
		int id=s.nextInt();
	Statement s5 = (Statement) con.getConnection().createStatement();
	ResultSet r1 = s5.executeQuery("Select * from books ");
	int flag=0;
	while (r1.next()) {
	if(r1.getInt(1)==id && r1.getInt(5)==0)
	{
		int book_id=r1.getInt(1);
		int status=r1.getInt(5);
		String book_name=r1.getString(2);
		String aut=r1.getString(3);
		String ed=r1.getString(4);
		if(status==0)
		{
			//ensure confirmation from the user
			System.out.println("Do you want to Issue any book?\nIf Yes enter 1");
			int res=s.nextInt();
			if(res==1)
			{
				int book_status=1;
				PreparedStatement st=(PreparedStatement) con.getConnection().prepareStatement("insert into  booking (student_id,book_id,status)values(?,?,?);");
				
				st.setInt(1,stid);
				st.setInt(2, id);
				st.setInt(3,book_status);
				st.executeUpdate();
				if(id==book_id)
				{
				PreparedStatement st1=(PreparedStatement) con.getConnection().prepareStatement("update   books set status=? where book_id=?");																					
				int p_status=1;
				st1.setInt(1, p_status);
				st1.setInt(2, id);
				st1.executeUpdate();
				}
			
				System.out.println("Order Placed Sucessfully");
				choice(stid);
			}
			else
			{
			//choice(stid);
			flag=1;
			}
	
		}
	}
	
	}
	//if the db contains nothing  
	if(r1.next()==false)
	{
		System.out.println("No books !!!");
	}
	if(flag==1)
	{
		System.out.println("Invalid Id / Book is not Avaliable to order!!");
		System.out.println();
		System.out.println();
		choice(stid);
	}
	}
	break;
	//choice();
	
	
	case 3:
	{
		//View ordered or taken books by the student
		int student_id=0;
		int booking_id=0;
		int book_id=0;
		PreparedStatement s31 = (PreparedStatement) con.getConnection().prepareStatement("Select * from booking where student_id=? ");    
		s31.setInt(1,stid);
		ResultSet r31 = s31.executeQuery();
		while (r31.next())
		{
			System.out.println("\nStudent  Id:\t" + r31.getInt(2));
			booking_id=r31.getInt(1);
			student_id= r31.getInt(2);
			book_id=r31.getInt(3);
		}
		
		PreparedStatement s32= (PreparedStatement) con.getConnection().prepareStatement("Select * from student where id=? ");    
		
		s32.setInt(1,student_id);
		ResultSet r32 = s32.executeQuery();	
		while(r32.next())
		{
			System.out.println("Student Name:\t"+r32.getString(2));
		}
		PreparedStatement s33 = (PreparedStatement) con.getConnection().prepareStatement("Select * from books where book_id=? ");    
				
				s33.setInt(1,book_id);
				ResultSet r33 = s33.executeQuery();	
				while(r33.next())
				{

					System.out.println("Book Name:\t"+r33.getString(2));
				}
				System.out.println();
		choice(stid);
	
		break;
	}
	case 4:
	{
		//Display all the available books in the LMS
		System.out.println("--- Available Books--");	
		Statement s4 = (Statement) con.getConnection().createStatement();
		ResultSet r4 = s4.executeQuery("Select * from books ");
		int flag=0;
		while (r4.next()) {
		
		if(r4.getInt(5)==0)
		{
			
		System.out.println("Book ID:\t"+r4.getInt(1) + "\nBook name:\t" + r4.getString(2) + "\nAuthor:\t" + r4.getString(3) +"\nEdition:\t"+ r4.getString(4)  );
		System.out.println("-----------");
		flag=1;
		}
		if(flag==0)
		{
			System.out.println("No books are available");
		}
		}
	}
	choice(stid);
		break;	
	case 5:
		LMS_Main lmm=new LMS_Main();
		lmm.choices();
	
}

}
}
