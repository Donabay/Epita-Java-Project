
package fr.epita.iam.launcher;

import java.sql.SQLException;
import java.util.Scanner;

import fr.epita.iam.service.CreateIdentity;
import fr.epita.iam.service.DeleteIdentity;
import fr.epita.iam.service.IdentityJDBCDAO;
import fr.epita.iam.service.SearchIdentity;
import fr.epita.iam.service.UpdateIdentity;
import fr.epita.logger.Logger;

//the code.
public class Main {
	
	private static final Logger LOGGER = new Logger(Main.class);


	private static final String EXCEPTION = "EXCEPTION";
	
	public static void main(String[] args) throws SQLException {
		

		IdentityJDBCDAO jdbcdao = new IdentityJDBCDAO();
		
		 boolean quit = false;
		 boolean isauthenticated = false;
		 boolean userExist=false;
		 
		 System.out.print("This is the beginning of Iam Core Program....." + "\n");
		 do { 
			 
			if(userExist && !isauthenticated) {
				System.out.println("User Exists and Authentication issue, please try entering the creds again.");
			}
			 
			Scanner input1 = new Scanner(System.in);
		    System.out.println("Enter admin Username : ");
		    String username = input1.next();

		    Scanner input2 = new Scanner(System.in);
		    System.out.println("Enter admin Password : ");
		    String password = input2.next();
		
		
		   
		try {
		
			if (jdbcdao.userexist(username)) {
				userExist=true;
			}
			else if (jdbcdao.createUser(username, password)) { //lets debug again
				System.out.println("User created");
			
			}

        if (jdbcdao.authenticateUser(username, password)) {
          
            isauthenticated = true;
        }
        
        	
		}
        catch (ClassNotFoundException e1) {
			
        	LOGGER.error(EXCEPTION, e1); 
		}
	} while (!isauthenticated);
		 

		    try {
				if (isauthenticated) {

					System.out.println("Access Granted! Welcome To!");

					Scanner scanner = new Scanner(System.in);
					
					
					do {
					
					
			        System.out.println("*****************************************");
			        System.out.println("|   IDENTITY MANAGER APPLICATION          |");
			        System.out.println("*****************************************");
			        System.out.println("| Select Options:                         |");
			        System.out.println("|        1. Create Identity    			  |");
			        System.out.println("|        2. Update Identity   			  |");
			        System.out.println("|        3. Delete Identity   			  |");
			        System.out.println("|        4. List/View Database 			  |");
			        System.out.println("|        5. Exit             		      |");
			        System.out.println("*****************************************");
					String choice = scanner.nextLine();

					switch (choice) {
					case "1":
						//create
						/**method to invoke the creation of a new identity**/
						CreateIdentity.makeIdentity(scanner);

						break;
					case "2":
						//modify
						/**Update identity**/

						UpdateIdentity.update(scanner);

						break;
					case "3":
						//delete
						DeleteIdentity.deletion(scanner);
						break;
						
					case "4":
						
						//search and print current list of identities found withing the database
						
						SearchIdentity.search(scanner);
						break;
						
					case "5":
						 quit = true;
						System.out.println("Goodbye  ");
						 System.exit(0); 
			              break;
						

						//Quit

					default:
						System.out.println("Your choice is not recognized");
					}

					 } while (!quit);
				}

				 
			} catch (Exception e) {
				LOGGER.error(EXCEPTION, e); 
				
				
			}

		    
		    
		    
		    
		}	
	

}


	

