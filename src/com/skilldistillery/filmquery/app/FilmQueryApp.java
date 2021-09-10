package com.skilldistillery.filmquery.app;

	import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
	import java.util.List;
	import java.util.Scanner;

	import com.skilldistillery.filmquery.database.DatabaseAccessor;
	import com.skilldistillery.filmquery.database.DatabaseAccessorObject1;
	import com.skilldistillery.filmquery.entities.Film;


public class FilmQueryApp {
  
	DatabaseAccessor db = new DatabaseAccessorObject1();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
//		app.test();
		app.launch();
	}

	private void test() throws SQLException {
//		Film film = db.findFilmById(1);
//		List<Actor> listOfActors = db.findActorsByFilmId(1);
//		System.out.println(film);
//		System.out.println(listOfActors);
	}

	private void launch() throws SQLException {
		Scanner input = new Scanner(System.in);

		while (!startUserInterface(input))
			;

		input.close();
	}

	private boolean startUserInterface(Scanner input) throws SQLException {
		DatabaseAccessorObject1 dao = new DatabaseAccessorObject1();
		boolean exit = false;


		System.out.println("              SD30db                     ");
		System.out.println("                                         ");
		System.out.println("  1. Find film by ID                     ");
		System.out.println("  2. Find film by a search keyword.      ");
		System.out.println("  3. Exit the application.               ");
		System.out.println("                                         ");
		System.out.println("\n\n");

		System.out.print("Enter your choice:  ");
		String choice = input.nextLine();

		switch (choice) {
		case "1":
			System.out.print("\nEnter the film id: ");
			int c1;
			try {
				c1 = input.nextInt();
				Film film = db.findFilmById(c1);

				if (film == null) {
					System.out.println("The film was not found\n");

				} else {
					System.out.println(film);
				}

			} catch (InputMismatchException inputError) {
				System.out.println("\nEnter a number for the film id.... Press Enter to go back to main menu.\n");
				input.nextLine();

			} finally {
				input.nextLine();
			}
			break;
		case "2":
			System.out.print("Enter a keyword to find your film: ");

			String c2 = input.nextLine();

			List<Film> film = db.searchFilm(c2);

			if (film.size() == 0) {
				System.out.println("\nThe film was not found");

			} else {
				for (int i = 0; i < film.size(); i++) {
					System.out.println(film.get(i).toString());
				}

			}
			break;
		case "3":
			System.out.println("Enjoy your movie!");
			exit = true;

			break;

		default:
			System.out.println("Please enter a number: \n");

			break;

		}

		return exit;
	}

}
