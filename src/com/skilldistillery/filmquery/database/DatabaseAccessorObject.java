package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private String user = "student";
	private String pass = "student";
	
	 public DatabaseAccessorObject()  {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.err.println("Error loading database driver: ");
				System.err.println(e);
				e.printStackTrace();				

			}
	}

  @Override
  public Film findFilmById(int filmId) {
	  //JDBC code to retrieve film, create film object
	  Film film = null;
	  
	  try {
		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sql = ""
				+ "SELECT id, title, description, release_year,"
				+ " language_id, rental_duration, rental_rate,"
				+ "length, replacement_cost, rating, special_features"
				+ " FROM film WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			film = new Film();
			film.setId(rs.getInt("id"));
			film.setTitle(rs.getString("title"));
			film.setDescription(rs.getString("description"));
			film.setReleaseYear(rs.getInt("release_year"));
			//film.setLanguageName(rs.getString("language.name"));
			film.setLanguageId(rs.getInt("language_id"));
			film.setRentalDuration(rs.getDouble("rental_duration"));
			film.setRentalRate(rs.getInt("rental_rate"));
			film.setLength(rs.getInt("length"));
			film.setReplacementCost(rs.getInt("replacement_cost"));
			film.setRating(rs.getString("rating"));
			film.setSpecialFeatures(rs.getString("special_features"));
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.err.println("Database error");
		System.err.println(e);
		e.printStackTrace();
	}
	  
	  
	  
    return film;
  }

@Override
public Actor findActorById(int actorId) {

	String sql = "SELECT actor.id, actor.first_name, actor.last_name FROM actor WHERE actor.id = ?";
	try (Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement pst = conn.prepareStatement(sql);)

	{
		pst.setInt(1, actorId); 
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			Actor actor = new Actor(rs.getInt("actor.id"), rs.getString("actor.first_name"),
					rs.getString("actor.last_name"));
			return actor;
		}
		rs.close();
	} catch (SQLException e) {
		System.err.println("The application has encountered a SQL Exception.");
		e.printStackTrace();
	}

	return null;
}

@Override
public List<Actor> findActorsByFilmId(int filmId) {
	List<Actor> actorList = new ArrayList<>();

String sql = "SELECT * FROM actor JOIN film_actor ON actor.id = film_actor.actor_id WHERE film_actor.film_id = ?";
try (Connection conn = DriverManager.getConnection(URL, user, pass);
		PreparedStatement pst = conn.prepareStatement(sql);)

{
	pst.setInt(1, filmId);
	ResultSet rs = pst.executeQuery();
	actorList = new ArrayList<>();
	while (rs.next()) {
		Actor actor = new Actor(rs.getInt("actor.id"), rs.getString("actor.first_name"),
				rs.getString("actor.last_name"));
		actorList.add(actor);
	}
	rs.close();
} catch (SQLException e) {
	System.err.println("The application has encountered a SQL Exception.");
	e.printStackTrace();
}
return actorList;
}


public ArrayList<Integer> searchFilm(String searchTerm) {
	String sql = "SELECT film.id FROM film WHERE (film.title LIKE ? OR film.description LIKE ?)";
	try (Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement pst = conn.prepareStatement(sql);) {
		pst.setString(1, "%" + searchTerm + "%");
		pst.setString(2, "%" + searchTerm + "%");
		ResultSet rs = pst.executeQuery();

		ArrayList<Integer> filmIdArr = new ArrayList<>();
		while (rs.next()) {
			filmIdArr.add(rs.getInt("film.id"));
		}
		rs.close();
		return filmIdArr;
	} catch (SQLException e) {
		System.err.println("The application has encountered a SQL Exception.");
		e.printStackTrace();
	}
	return null;

}

}//end DatabaseAccessorObject
  
  


