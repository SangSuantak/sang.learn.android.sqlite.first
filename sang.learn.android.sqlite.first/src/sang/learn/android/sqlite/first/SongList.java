package sang.learn.android.sqlite.first;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SongList extends ListActivity {
	
	//private CommentsDataSource dataSource;
	private List<Song> songs;
	private ArrayAdapter<Song> adapter;
	public static final String SONG_ID = "sang.learn.android.sqlite.first.SONGID";
	private static int songBookId = 0;
	private static String songBookName = "";
	private static String songFirstChar = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		Intent intent = getIntent();
		songBookId = intent.getIntExtra(SongListFirstChar.SONGBOOK_ID, 1);
		songBookName = intent.getStringExtra(SongListFirstChar.SONGBOOK_NAME);
		songFirstChar = intent.getStringExtra(SongListFirstChar.SONG_FIRST_CHAR);
		songs = TestDatabaseActivity.dataSource.getSongs(songBookId, songFirstChar);
		bindSongList(songs);		
	}
	
	public void bindSongList(List<Song> songs){		
		setTitle(songBookName);		
		adapter = new ArrayAdapter<Song>(this,
				android.R.layout.simple_list_item_1,
				songs);		
		setListAdapter(adapter);
	}

	/*
	
	@SuppressLint("NewApi")
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.song_list, menu);
		getMenuInflater().inflate(R.menu.options_menu, menu);
		
		//Associate Searchable configuration with the SearchView
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
			SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
			searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
		}
		return true;
	}
	
	*/
	
	@Override
	public void onListItemClick(ListView lstView, View view, int position, long id){
		Song song = adapter.getItem(position);
		showSong(song.getId());
	}
	
	public void showSong(int songId){
		Intent intent = new Intent(this, SongDisplay.class);
		intent.putExtra(SONG_ID, songId);		
		startActivity(intent);
	}
		
}
