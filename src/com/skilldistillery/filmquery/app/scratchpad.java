package com.skilldistillery.filmquery.app;

import java.util.Scanner;

public class scratchpad {

//	User Stories
//	User Story 1
//	The user is presented with a menu in which they can choose to:

//	Look up a film by its id.
//	Look up a film by a search keyword.
//	Exit the application.

//	User Story 2
//	If the user looks up a film by id, they are prompted to enter the film id. If the film is not found, they see a message saying so. If the film is found, its title, year, rating, and description are displayed.

//	User Story 3
//	If the user looks up a film by search keyword, they are prompted to enter it. If no matching films are found, they see a message saying so. Otherwise, they see a list of films for which the search term was found anywhere in the title or description, with each film displayed exactly as it is for User Story 2.

//	User Story 4
//	When a film is displayed, its language (English,Japanese, etc.) is also displayed.

//	User Story 5
//	When a film is displayed, the list of actors in its cast is displayed along with the title, year, rating, and description.

//	Stretch Goals
//	Goal 1
//	When a film is displayed, the user can choose from a submenu whether to:

//	Return to the main menu.
//	View all film details.
//	If they choose to view all details, they will see all column values of the film record they chose.

//	Goal 2
//	When viewing film details, the user will see a list of all the film's categories (Family, Comedy, etc.) for the film.

//	Goal 3
//	When viewing film details, the user will see a list of all copies of the film in inventory, with their condition.

//	Goal 4
//	Write JUnit tests for DatabaseAccessorObject's methods.

//	Grading
//	The application pushed to Github must satisfy all of the user stories without throwing any exceptions.
//	



public void menuMain () {
	Scanner scanner = new Scanner(System.in);
	String uInput;

	while(true)
	{
		System.out.println(
			"Main Menu\n\n" +
			"1.  Look up film by id\n" +
			"2.  Look up film by search word" +
			"3.  Exit the application");
		uInput = scanner.nextLine();
		if(uInput.equals("1")) {
			System.out.println("Here are your results: ");
			//method for id
		} else if ((uInput.equals("2"))) {
			System.out.println("Here are your results: ");	
			//method for search word
		} else if ((uInput.equals("3"))) {
			System.out.println("You have chosen to exit.");
			System.exit(0);
		}
		
	}//end while loop
}//end menuMain

}//end scratchpad	