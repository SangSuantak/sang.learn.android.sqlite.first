package sang.learn.android.sqlite.first;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;

public class SongList extends ListActivity {
	
	//private CommentsDataSource dataSource;
	private List<Song> songs;
	private ArrayAdapter<Song> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		int songBookId = intent.getIntExtra(TestDatabaseActivity.SONGBOOK_ID, 1);
				
		
		//TestDatabaseActivity.dataSource = new CommentsDataSource(this);
		songs = TestDatabaseActivity.dataSource.getSongs(songBookId);
		
		adapter = new ArrayAdapter<Song>(this,
				android.R.layout.simple_expandable_list_item_1,
				songs);
		
		setListAdapter(adapter);
		
		//setContentView(R.layout.activity_song_list);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.song_list, menu);
		return true;
	}
	
	@Override
	public void onBackPressed(){
		super.onBackPressed();
		//overridePendingTransition(R.anim.anim_out, R.anim.anim_in);
	}
	

}
