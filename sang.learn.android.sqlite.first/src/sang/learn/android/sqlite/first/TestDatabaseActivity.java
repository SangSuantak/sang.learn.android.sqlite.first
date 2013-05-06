package sang.learn.android.sqlite.first;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TestDatabaseActivity extends ListActivity {

	public static CommentsDataSource dataSource;
	private List<SongBook> songBooks;
	private ArrayAdapter<SongBook> adapter;
	
	public static final String SONGBOOK_ID = "sang.learn.android.sqlite.first.SONGBOOKID";
	public static final String SONGBOOK_NAME = "sang.learn.android.sqlite.first.SONGBOOKNAME";
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		dataSource = new CommentsDataSource(this);
		
		songBooks = dataSource.getSongBooks();
				
		adapter = new ArrayAdapter<SongBook>(
				this, 
				android.R.layout.simple_list_item_1,
				songBooks);
				
		
		setListAdapter(adapter);
				
	}
	
	protected void OnDestroy(){
		super.onDestroy();		
		dataSource.close();
	}
		
	@Override
	protected void onListItemClick(ListView lstView, View view, final int position, long id){
		SongBook songBook = adapter.getItem(position);
						
		Intent intent = new Intent(this, SongListFirstChar.class);
		intent.putExtra(SONGBOOK_ID, songBook.getId());
		intent.putExtra(SONGBOOK_NAME, ((TextView)view).getText());			
		startActivity(intent);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.labu_menu, menu);
		return true;
	}
	
}
