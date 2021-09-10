package com.skilldistillery.filmquery.app;

import java.util.Scanner;

public class scratchpad {

	
	
	
	///////////////////////
	

	public class FilmQueryApp {

		DatabaseAccessor db = new DatabaseAccessorObject();

		public static void main(String[] args) throws SQLException {
			FilmQueryApp app = new FilmQueryApp();
//			app.test();
			app.launch();
		}

		private void test() throws SQLException {
//			Film film = db.findFilmById(1);
//			List<Actor> listOfActors = db.findActorsByFilmId(1);
//			System.out.println(film);
//			System.out.println(listOfActors);
		}

		private void launch() throws SQLException {
			Scanner input = new Scanner(System.in);

			while (!startUserInterface(input))
				;

			input.close();
		}

		private boolean startUserInterface(Scanner input) throws SQLException {
			DatabaseAccessorObject dao = new DatabaseAccessorObject();
			boolean exit = false;

			System.out.println("+-----------------------------------------+");
			System.out.println("|  1. Look up a film by its id.           |");
			System.out.println("|  2. Look up a film by a search keyword. |");
			System.out.println("|  3. Exit the application.               |");
			System.out.println("+-----------------------------------------+ \n\n");

			System.out.print("Enter your menu choice:  ");
			String choice = input.nextLine();

			switch (choice) {
			case "1":
				System.out.print("\nEnter the id for the film you're looking for: ");
				int c1;
				try {
					c1 = input.nextInt();
					Film film = db.findFilmById(c1);

					if (film == null) {
						System.out.println("The film was not found\n");

					} else {
						System.out.println(film);
					}

				} catch (InputMismatchException inputWrong) {
					System.out.println("\nEnter a number for the film id.... Press Enter to go back to main menu.\n");
					input.nextLine();

				} finally {
					input.nextLine();
				}
				break;
			case "2":
				System.out.print("Enter a keyword to find your film: ");

				String c2 = input.nextLine();

				List<Film> film = db.findFilmByKeyword(c2);

				if (film.size() == 0) {
					System.out.println("\nThe film was not found");

				} else {
					for (int i = 0; i < film.size(); i++) {
						System.out.println(film.get(i).toString());
					}

				}
				break;
			case "3":
				System.out.println("Have a good day!");
				exit = true;

				break;

			default:
				System.out.println("Please enter a 1 , 2, or 3: \n");

				break;

			}

			return exit;
		}

	
	
	
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