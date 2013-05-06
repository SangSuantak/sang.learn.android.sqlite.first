package sang.learn.android.sqlite.first;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.widget.TextView;

public class SongDisplay extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_song_display);
		
		Intent intent = getIntent();
		int songId = intent.getIntExtra(SongList.SONG_ID, 1);
		
		Song song = TestDatabaseActivity.dataSource.getSong(songId);
		
		//TextView titleText = (TextView) findViewById(R.id.songtitle);
		TextView lyricText = (TextView) findViewById(R.id.songlyric);
		
		//titleText.setText(song.getName());
		//lyricText.setText(song.getLyric());		
		
		lyricText.setText(Html.fromHtml(song.getLyric()));
		//lyricText.setTextSize(18);
		
		setTitle(Integer.toString(song.getSongnumber()) + "." + song.getName());
		
		//setContentView(R.layout.activity_song_display);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.song_display, menu);
		return true;
	}

}
