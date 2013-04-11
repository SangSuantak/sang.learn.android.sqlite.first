package sang.learn.android.sqlite.first;

import java.util.List;
import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TestDatabaseActivity extends ListActivity {

	public static CommentsDataSource dataSource;
	private List<SongBook> songBooks;
	private ArrayAdapter<SongBook> adapter;
	
	public static final String SONGBOOK_ID = "sang.learn.android.sqlite.first.SONGBOOKID";
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		dataSource = new CommentsDataSource(this);
		
		songBooks = dataSource.getSongBooks();
		
		/*
		ListAdapter adapter = new SimpleCursorAdapter(this, 
				R.layout.activity_test_database, 
				songBooks, 
				new String[] {"name"}, 
				null);
		
		*/
		
		adapter = new ArrayAdapter<SongBook>(
				this, 
				android.R.layout.simple_list_item_1,
				songBooks);
				
		
		setListAdapter(adapter);
			
		/*
		ListView myListView = (ListView)findViewById(android.R.id.list);
				
		myListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id){
				//Log.d(Long.toString(id));
				//System.out.println(id);
				SongBook songBook = adapter.getItem(position);
				Toast.makeText(getBaseContext(), Integer.toString(songBook.getId()), Toast.LENGTH_SHORT).show();
			}
		});
		*/
		
		//getListView().setAdapter(adapter);
		//myListView.setAdapter(adapter);
		
	}
	
	protected void OnDestroy(){
		super.onDestroy();		
		dataSource.close();
	}
		
	@Override
	protected void onListItemClick(ListView lstView, View view, final int position, long id){
		SongBook songBook = adapter.getItem(position);
						
		Intent intent = new Intent(this, SongList.class);
		intent.putExtra(SONGBOOK_ID, songBook.getId());
		
		/*
		ActivityOptions options = ActivityOptions.makeScaleUpAnimation(
				lstView, 
				0, 
				0, 
				lstView.getWidth(), 
				lstView.getHeight());
		*/
						
		startActivity(intent);
		overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
		//Toast.makeText(getBaseContext(), Integer.toString(songBook.getId()), Toast.LENGTH_SHORT).show();
	}
	
	/*
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_database);
		
		dataSource = new CommentsDataSource(this);
		dataSource.open();
		
		List<Comment> comments = dataSource.getAllComments();
		
		//Use the SimpleCursorAdapter to show the elements in a ListView
		ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this, android.R.layout.simple_list_item_1, comments);
		setListAdapter(adapter);		
	}
	
	public void onClick(View view){
		@SuppressWarnings("unchecked")
		ArrayAdapter<Comment> adapter = (ArrayAdapter<Comment>)getListAdapter();
		Comment comment = null;
		switch(view.getId()){
		case R.id.add:
			String[] comments = new String[]{ "Cool", "Very Nice", "Hate it" };
			int nextInt = new Random().nextInt(3);
			comment = dataSource.createComment(comments[nextInt]);
			adapter.add(comment);
			break;
		case R.id.delete:
			if(getListAdapter().getCount() > 0) {
				comment = (Comment)getListAdapter().getItem(0);
				dataSource.deleteComment(comment);
				adapter.remove(comment);
			}
			break;
		}
		adapter.notifyDataSetChanged();
	}
	
	@Override
	public void onResume(){
		dataSource.open();
		super.onResume();
	}
	
	@Override
	public void onPause(){
		dataSource.close();
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test_database, menu);
		return true;
	}
	*/
}
