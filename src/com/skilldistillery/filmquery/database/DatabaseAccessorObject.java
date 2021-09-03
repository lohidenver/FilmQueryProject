package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = ""
	private String user = "student";
	private String pass = "student";
	
	public public DatabaseAccessorObject() {
		// TODO Auto-generated constructor stub
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
			film.setId(rs.getInt(1));
			film.setTitle(rs.getString(2));
			film.setDescription(rs.getString(3));
			film.setReleaseYear(rs.getInt(4));
			film.setLanguageId(5);
			film.setRentalDuration(6);
			film.setRentalRate(rs.getInt("rental_rate"));
			film.setLength(rs.getInt("length"));
			film.setReplacementCost(rs.getInt("replacement_cost"));
			film.setRating(rs.getString("rating"));
			film.setSpecialFeatures(rs.getString("special_features"));
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.err.println("Database error");
		e.printStackTrace();
	}
	  
	  
	  
    return film;
  }
  
  

}
