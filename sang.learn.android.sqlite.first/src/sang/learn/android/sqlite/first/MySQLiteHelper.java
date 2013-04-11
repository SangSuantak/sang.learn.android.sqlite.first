package sang.learn.android.sqlite.first;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
	
	public static final String TABLE_COMMENTS = "comments";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_COMMENT = "comment";
	public static final String DATABASE_NAME = "comments.db";
	public static final int DATABASE_VERSION = 1;
	
	public static final String DATABASE_CREATE = "create table "
			+ TABLE_COMMENTS + "(" 
			+ COLUMN_ID + " integer primary key autoincrement, " 
			+ COLUMN_COMMENT + " text not null)";
	
	
	public MySQLiteHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}	
	
	@Override
	public void onCreate(SQLiteDatabase database){
		database.execSQL(DATABASE_CREATE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
		Log.w(MySQLiteHelper.class.getName(),
				"Upgrading database version from " + oldVersion + " to "
				+ newVersion+ ", which will destroy all old data");
		
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
		onCreate(database);		
	}
	
}