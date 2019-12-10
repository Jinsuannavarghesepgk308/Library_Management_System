package LMS;
import java.util.Scanner;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;
import com.mysql.jdbc.PreparedStatement;

public class LMS_Main {
		Scanner s=new Scanner(System.in);
		public static void main(String[] args) throws ClassNotFoundException,SQLException, IOException
		{
			// TODO Auto-generated method stub
			
		//creating the object of Main class and calling the method choice()	
		LMS_Main sp=new LMS_Main();
		sp.choices();
		}


		public void choices() throws ClassNotFoundException,SQLException, IOException
		{
		//Create choices 
		System.out.println("Enter the Choice:");
		System.out.println("1.Admin Login\n2.Student Login\n3.Exit");
		int op=s.nextInt();
		switch(op)
		{
		case 1:
			//create object of admin class and calling the method of admin class
			LMS_Admin ad=new LMS_Admin();
			ad.adminlogin();
			
			
		break;
		case 2:
			//create object of student class and calling the method of student class
			LMS_Student agent=new LMS_Student();
			agent.studentLogin();
			 
		break;
		case 3:
			System.out.println("Process Completed");
			System.exit(0);
			break;
		
		}
		}
	}


