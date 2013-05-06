package sang.learn.android.sqlite.first;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SongListFirstChar extends ListActivity {
	
	private ArrayAdapter<SongFirstChar> adapter;
	public static final String SONGBOOK_ID = "sang.learn.android.sqlite.first.SONGBOOKID";
	public static final String SONG_FIRST_CHAR = "sang.learn.android.sqlite.first.SONGFIRSTCHAR";
	public static final String SONGBOOK_NAME = "sang.learn.android.sqlite.first.SONGBOOKNAME";
	private static String songBookName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		int songBookId = intent.getIntExtra(TestDatabaseActivity.SONGBOOK_ID, 1);
		songBookName = intent.getStringExtra(TestDatabaseActivity.SONGBOOK_NAME);
		List<SongFirstChar> songFirstChars = TestDatabaseActivity.dataSource.getSongFirstChars(songBookId);
		setTitle(songBookName);
		
		adapter = new ArrayAdapter<SongFirstChar>(this, 
				android.R.layout.simple_list_item_1,
				songFirstChars);
		
		setListAdapter(adapter);		
	}

	@Override
	protected void onListItemClick(ListView lstView, View view, final int position, long id){
		SongFirstChar objSongFirstChar = adapter.getItem(position);						
		Intent intent = new Intent(this, SongList.class);
		intent.putExtra(SONGBOOK_ID, objSongFirstChar.getSongBookId());
		intent.putExtra(SONG_FIRST_CHAR, objSongFirstChar.getSongFirstChar());	
		intent.putExtra(SONGBOOK_NAME, songBookName);
		startActivity(intent);		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.song_list_first_char, menu);
		return true;
	}

}
