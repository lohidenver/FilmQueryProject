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

	@Override
	public Film findFilmById(int filmId) throws SQLException {
		Film film = null;
		String user = "student";
		String pass = "student";

		
		String sql = "SELECT  f.title, f.description, f.release_year, f.rating, l.name FROM film f JOIN language l ON f.language_id = l.id WHERE f.id = ?";
		Connection conn = DriverManager.getConnection(URL, user, pass);
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet filmResult = stmt.executeQuery();
		while (filmResult.next()) {
			film = new Film();
			film.setTitle(filmResult.getString(1));
			film.setDescription(filmResult.getString(2));
			film.setReleaseYear(filmResult.getString(3));
			film.setRating(filmResult.getString(4));
			film.setLanguage(filmResult.getString(5));
			film.setActor(findActorsByFilmId(6));
		}
			filmResult.close();
			stmt.close();
			conn.close();


		return film;
	}

	public Actor findActorById(int actorId) throws SQLException {
		Actor actor = null;
		String user = "student";
		String pass = "student";

		String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
		Connection conn = DriverManager.getConnection(URL, user, pass);
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, actorId);
		ResultSet actorResult = stmt.executeQuery();
		if (actorResult.next()) {
			actor = new Actor(actorId); // Create the object
			// Here is our mapping of query columns to our object fields:
			actor.setId(actorResult.getInt(1));
			actor.setFirstName(actorResult.getString(2));
			actor.setLastName(actorResult.getString(3));

			actorResult.close();
			stmt.close();
			conn.close();
		}

		return actor;
	}

	public List<Actor> findActorsByFilmId(int filmId) throws SQLException {

		List<Actor> actorList = new ArrayList<>();
		String user = "student";
		String pass = "student";
		
		String sql = "SELECT actor.id, actor.first_name, actor.last_name FROM actor JOIN film_actor ON actor.id = film_actor.actor_id  WHERE film_actor.film_id = ?";

		Connection conn = DriverManager.getConnection(URL, user, pass);
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet rs = stmt.executeQuery();
		actorList = new ArrayList<>();
		while (rs.next()) {
			Actor actor = new Actor(rs.getInt("actor.id"), rs.getString("actor.first_name"),
					rs.getString("actor.last_name"));
			actorList.add(actor);
		}

		rs.close();
		stmt.close();
		conn.close();

		return actorList;
	}

	
	public List<Film> findFilmBySearch(String keyword) throws SQLException {
		Film film = null;
		List<Film> filmList = new ArrayList<>();
		String user = "student";
		String pass = "student";
		

		String sql = "SELECT  f.title, f.description, f.release_year, f.rating, l.name, f.id FROM film f JOIN language l ON f.language_id = l.id WHERE title LIKE ? OR description LIKE ?";
		Connection conn = DriverManager.getConnection(URL, user, pass);
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%" + keyword + "%");
		stmt.setString(2, "%" + keyword + "%");
		ResultSet filmResult = stmt.executeQuery();
		filmList = new ArrayList<>();
		while (filmResult.next()) {
			film = new Film();
			film.setTitle(filmResult.getString(1));
			film.setDescription(filmResult.getString(2));
			film.setReleaseYear(filmResult.getString(3));
			film.setRating(filmResult.getString(4));
			film.setActor(findActorsByFilmId(filmResult.getInt(6)));
			film.setLanguage(filmResult.getString(5));

			filmList.add(film);
		}
			filmResult.close();
			stmt.close();
			conn.close();


		return filmList;
	}

	@Override
	public List<Film> searchFilm(String searchTerm) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
