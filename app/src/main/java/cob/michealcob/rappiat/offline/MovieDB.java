package cob.michealcob.rappiat.offline;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import cob.michealcob.rappiat.model.Movie;

public class MovieDB extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "RAPPIAT";

    public static final String TABLE_MOVIE = "movie_table";
    public static final String TABLE_TR = "movie_tr";
    public static final String TABLE_UPCOMING = "movie_upcoming";


    private static final String KEY_ID = "id";
    private static final String KEY_MOVIEID = "createdAt";
    private static final String KEY_TITLE= "updateAt";

    private static final String KEY_OVERVIEW = "name";
    private static final String KEY_RATING = "phone";
    private static final String KEY_POSTERPATH = "company";
    private static final String KEY_BACKDROP = "aim";


    public MovieDB(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_MOVIE =
                "CREATE TABLE " + TABLE_MOVIE + "("
                        + KEY_ID + " INTEGER PRIMARY KEY, "
                        + KEY_MOVIEID + " TEXT,"
                        + KEY_TITLE+ " TEXT,"
                        + KEY_RATING + " TEXT,"
                        + KEY_OVERVIEW + " TEXT,"
                        + KEY_POSTERPATH + " TEXT,"
                        + KEY_BACKDROP + " TEXT"
                        + ")";
        String CREATE_TR =
                "CREATE TABLE " + TABLE_TR + "("
                        + KEY_ID + " INTEGER PRIMARY KEY, "
                        + KEY_MOVIEID + " TEXT,"
                        + KEY_TITLE+ " TEXT,"
                        + KEY_RATING + " TEXT,"
                        + KEY_OVERVIEW + " TEXT,"
                        + KEY_POSTERPATH + " TEXT,"
                        + KEY_BACKDROP + " TEXT"
                        + ")";
        String CREATE_UPCOMING =
                "CREATE TABLE " + TABLE_UPCOMING + "("
                        + KEY_ID + " INTEGER PRIMARY KEY, "
                        + KEY_MOVIEID + " TEXT,"
                        + KEY_TITLE+ " TEXT,"
                        + KEY_RATING + " TEXT,"
                        + KEY_OVERVIEW + " TEXT,"
                        + KEY_POSTERPATH + " TEXT,"
                        + KEY_BACKDROP + " TEXT"
                        + ")";
        db.execSQL(CREATE_MOVIE);
        db.execSQL(CREATE_TR);
        db.execSQL(CREATE_UPCOMING);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UPCOMING);

        onCreate(db);
    }

    public void createMovie(Movie movie){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MOVIEID, movie.getMovieid());
        values.put(KEY_TITLE, movie.getTitle());
        values.put(KEY_RATING, movie.getRating());
        values.put(KEY_OVERVIEW, movie.getOverview());
        values.put(KEY_POSTERPATH, movie.getPosterpath());
        values.put(KEY_BACKDROP, movie.getBackdrop());
        //inserting Rows
        db.insert(TABLE_MOVIE,null, values);
        db.close();
    }

    public List<Movie> getAllMovies(){
        List<Movie> moviesList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_MOVIE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (cursor.moveToFirst()){
            do {
                Movie movie = new Movie();
                movie.setId(Integer.parseInt(cursor.getString(0)));
                movie.setMovieid(cursor.getString(1));
                movie.setTitle(cursor.getString(2));
                movie.setRating(cursor.getString(3));
                movie.setOverview(cursor.getString(4));
                movie.setPosterpath(cursor.getString(5));
                movie.setBackdrop(cursor.getString(6));
                moviesList.add(movie);
            }while (cursor.moveToNext());
        }
        return moviesList;
    }

    public void createMovieTR(Movie movie){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MOVIEID, movie.getMovieid());
        values.put(KEY_TITLE, movie.getTitle());
        values.put(KEY_RATING, movie.getRating());
        values.put(KEY_OVERVIEW, movie.getOverview());
        values.put(KEY_POSTERPATH, movie.getPosterpath());
        values.put(KEY_BACKDROP, movie.getBackdrop());
        //inserting Rows
        db.insert(TABLE_TR,null, values);
        db.close();
    }

    public List<Movie> getAllMoviesTR(){
        List<Movie> moviesList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_TR;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (cursor.moveToFirst()){
            do {
                Movie movie = new Movie();
                movie.setId(Integer.parseInt(cursor.getString(0)));
                movie.setMovieid(cursor.getString(1));
                movie.setTitle(cursor.getString(2));
                movie.setRating(cursor.getString(3));
                movie.setOverview(cursor.getString(4));
                movie.setPosterpath(cursor.getString(5));
                movie.setBackdrop(cursor.getString(6));
                moviesList.add(movie);
            }while (cursor.moveToNext());
        }
        return moviesList;
    }
    public void createMovieUpcoming(Movie movie){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MOVIEID, movie.getMovieid());
        values.put(KEY_TITLE, movie.getTitle());
        values.put(KEY_RATING, movie.getRating());
        values.put(KEY_OVERVIEW, movie.getOverview());
        values.put(KEY_POSTERPATH, movie.getPosterpath());
        values.put(KEY_BACKDROP, movie.getBackdrop());
        //inserting Rows
        db.insert(TABLE_UPCOMING,null, values);
        db.close();
    }

    public List<Movie> getAllMoviesUpcoming(){
        List<Movie> moviesList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_UPCOMING;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (cursor.moveToFirst()){
            do {
                Movie movie = new Movie();
                movie.setId(Integer.parseInt(cursor.getString(0)));
                movie.setMovieid(cursor.getString(1));
                movie.setTitle(cursor.getString(2));
                movie.setRating(cursor.getString(3));
                movie.setOverview(cursor.getString(4));
                movie.setPosterpath(cursor.getString(5));
                movie.setBackdrop(cursor.getString(6));
                moviesList.add(movie);
            }while (cursor.moveToNext());
        }
        return moviesList;
    }
}
