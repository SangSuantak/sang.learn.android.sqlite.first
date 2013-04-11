package sang.learn.android.sqlite.first;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class CommentsDataSource extends SQLiteAssetHelper {
	private static final String DATABASE_NAME = "vaipheilabu";
	private static final int DATABASE_VERSION = 1;
	
	public CommentsDataSource(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public List<SongBook> getSongBooks(){
		SQLiteDatabase db = getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		String[] sqlSelect = {"_id", "name", "description"};
		String sqlTables = "songbooks";
		
		qb.setTables(sqlTables);
		Cursor cursor = qb.query(db, sqlSelect, null, null, null, null, null);
		cursor.moveToFirst();
		
		List<SongBook> songBooks = new ArrayList<SongBook>();
		while(!cursor.isAfterLast()){
			SongBook songBook = cursorToSongBook(cursor);
			songBooks.add(songBook);
			cursor.moveToNext();
		}
		cursor.close();		
		return songBooks;
	}	
	
	public List<Song> getSongs(int songbookid){
		SQLiteDatabase db = getReadableDatabase();
		
		String query = "SELECT _id, name "
				+ "FROM songs "
				+ "WHERE songbookid = " + songbookid;
		
		//SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		//String[] sqlSelect = {"_id", "name"};
		//String sqlTables = "songs";
		
		//qb.setTables(sqlTables);
		//Cursor cursor = qb.query(db, sqlSelect, null, null, null, null, null);
		Cursor cursor = db.rawQuery(query, null);
		cursor.moveToFirst();
		
		List<Song> songs = new ArrayList<Song>();
		while(!cursor.isAfterLast()){
			Song song = cursorToSong(cursor);
			songs.add(song);
			cursor.moveToNext();
		}
		cursor.close();		
		return songs;
	}
	
	public SongBook cursorToSongBook(Cursor cursor){
		SongBook songBook = new SongBook();
		songBook.setId(cursor.getInt(0));
		songBook.setName(cursor.getString(1));
		return songBook;
	}
	
	public Song cursorToSong(Cursor cursor){
		Song song = new Song();
		song.setId(cursor.getInt(0));
		song.setName(cursor.getString(1));
		return song;
	}
	
}

/*

public class CommentsDataSource {

	//Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = {
			MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.COLUMN_COMMENT
	};
	
	public CommentsDataSource(Context context){
		dbHelper = new MySQLiteHelper(context);
	}
	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	
	public void close(){
		dbHelper.close();
	}
	
	public Comment createComment(String comment) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_COMMENT, comment);
		long insertId = database.insert(MySQLiteHelper.TABLE_COMMENTS, null, values);
		Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS, allColumns, 
				MySQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
		cursor.moveToFirst();
		Comment newComment = cursorToComment(cursor);
		cursor.close();
		return newComment;
	}
	
	public void deleteComment(Comment comment){
		long id = comment.getId();
		System.out.println("Comment with id " + id + " deleted");
		database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID + " = " + id, null);
	}
	
	public List<Comment> getAllComments(){
		List<Comment> comments = new ArrayList<Comment>();
		Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS, allColumns, null, null, null, null, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			Comment comment = cursorToComment(cursor);
			comments.add(comment);
			cursor.moveToNext();
		}
		cursor.close();
		return comments;
	}
	
	public Comment cursorToComment(Cursor cursor){
		Comment comment = new Comment();
		comment.setId(cursor.getLong(0));
		comment.setComment(cursor.getString(1));
		return comment;
	}
	
}

*/
