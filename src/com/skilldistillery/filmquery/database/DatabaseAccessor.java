package com.skilldistillery.filmquery.database;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;
import java.util.*;

public interface DatabaseAccessor {
  public Film findFilmById(int filmId);
//  public Actor findActorById(int actorId);
  public Actor findActorById(int actorId);
//  public List<Actor> findActorsByFilmId(int filmId);
  public List<Actor> findActorsByFilmId(int filmId);
}
