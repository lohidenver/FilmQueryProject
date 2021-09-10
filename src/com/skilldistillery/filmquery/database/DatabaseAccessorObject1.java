package com.skilldistillery.filmquery.database;
//FilmQuery
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject1 implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private String user = "student";
	private String pass = "student";
	
	 public DatabaseAccessorObject1()  {
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
	  String user = "student";
	  String pass = "student";
	  try {
		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sql = "SELECT  f.title, f.description, f.release_year, f.rating, l.name FROM film f JOIN language l ON f.language_id = l.id WHERE f.id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			film = new Film();
			film.setTitle(rs.getString(1));
			film.setDescription(rs.getString(2));
			film.setReleaseYear(rs.getString(3));
			film.setRating(rs.getString(4));
			film.setLanguage(rs.getString(5));
			//film.setActor(findActorsByFilmId(6));
	
			rs.close();
			stmt.close();
			conn.close();
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
public Actor findActorById(int actorId) throws SQLException {
	Actor actor = null;
	String user = "student";
	String pass = "pass";
	
	String sql = "SELECT id, first_name, last_name FROM actor WHERE actor.id = ?";
	try (Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);)
	{
		stmt.setInt(1, actorId); 
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			  actor.setId(rs.getInt(1));
			    actor.setFirstName(rs.getString(2));
			    actor.setLastName(rs.getString(3));
			    actor.setFilms(findFilmsByActorId(actorId));
			return actor;
		}
		rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		System.err.println("The application has encountered a SQL Exception.");
		e.printStackTrace();
	}

	return actor;
}

public List<Film> findFilmsByActorId(int actorId) {
	  List<Film> films = new ArrayList<>();
	  try {
	    Connection conn = DriverManager.getConnection(URL, user, pass);
	    String sql = "SELECT id, title, description, release_year, language_id, rental_duration, ";
	                sql += " rental_rate, length, replacement_cost, rating, special_features "
	               +  " FROM film JOIN film_actor ON film.id = film_actor.film_id "
	               + " WHERE actor_id = ?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setInt(1, actorId);
	    ResultSet rs = stmt.executeQuery();
	    while (rs.next()) {
	      int filmId = rs.getInt(1);
	      String title = rs.getString(2);
	      String desc = rs.getString(3);
	      short releaseYear = rs.getShort(4);
	      int langId = rs.getInt(5);
	      int rentDur = rs.getInt(6);
	      double rate = rs.getDouble(7);
	      int length = rs.getInt(8);
	      double repCost = rs.getDouble(9);
	      String rating = rs.getString(10);
	      String features = rs.getString(11);
	      Film film = new Film();
	      films.add(film);
	    }
	    rs.close();
	    stmt.close();
	    conn.close();
	  } catch (SQLException e) {
	    e.printStackTrace();
	  }
	  return films;
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


public List<Film> searchFilm(String searchTerm) {
	Film film = null;
	List<Film> filmIdArr = new ArrayList<>();
	String user = "student";
	String pass = "student";
	
	
	String sql = "SELECT film.id FROM film WHERE (film.title LIKE ? OR film.description LIKE ?)";
	try (Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement pst = conn.prepareStatement(sql);) {
		pst.setString(1, "%" + searchTerm + "%");
		pst.setString(2, "%" + searchTerm + "%");
		ResultSet rs = pst.executeQuery();
		filmIdArr = new ArrayList<>();
		while (rs.next()) {
			film = new Film();
			film.setId(rs.getInt("id"));
			film.setTitle(rs.getString("title"));
			film.setReleaseYear(rs.getInt("release_year"));
			film.setDescription(rs.getString("description"));
			film.setRating(rs.getString("rating"));
			film.setLanguageId(rs.getInt("language_id"));
			film.setLanguage(rs.getString("language.name"));
			film.setActor(findActorsByFilmId(rs.getInt(film.getId())));
		}
		rs.close();
		pst.close();
		conn.close();
		
		return filmIdArr;
	} catch (SQLException e) {
		System.err.println("The application has encountered a SQL Exception.");
		e.printStackTrace();
	}
	return null;

}

}//end DatabaseAccessorObject
  
  


