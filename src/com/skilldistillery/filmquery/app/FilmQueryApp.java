package com.skilldistillery.filmquery.app;

import java.util.ArrayList;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
  
  DatabaseAccessor db = new DatabaseAccessorObject();

  public static void main(String[] args) {
    FilmQueryApp app = new FilmQueryApp();
    app.launch();
//    app.test();
  }

//  private void test() {
//    Film film = db.findFilmById(1);
//    System.out.println(film);
//  }

  private void launch() {
    Scanner input = new Scanner(System.in);
    
    startUserInterface(input);
    
    input.close();
  }

  private void startUserInterface(Scanner input) {
	 
			Scanner scanner = new Scanner(System.in);
			int uInput;
			String uI;

			while(true)
			{
				System.out.println(
					"Main Menu\n\n" +
					"1.  Look up film by id\n" +
					"2.  Look up film by search word\n" +
					"3.  Exit the application");
				uInput = scanner.nextInt();
				if(uInput == 1) {
					System.out.println("What is the film id? ");
					uInput = scanner.nextInt();
					System.out.println("Here are your results: ");
					Film film = db.findFilmById(uInput);
				    System.out.println(film);
					
				} else if (uInput == 2) {
					System.out.println("What is your search keyword: ");
					uI = scanner.nextLine();
					System.out.println("Here are your results: ");	
					ArrayList<Integer> film = db.searchFilm(uI);
				    System.out.println(film);
				} else if (uInput == 3) {
					System.out.println("You have chosen to exit.");
					System.exit(0);
				}
				
			}//end while loop
		}
  

}
