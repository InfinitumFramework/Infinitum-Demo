package com.clarionmedia.infinitumdemo.ui.activity;

import java.util.List;

import android.os.Bundle;
import android.view.Menu;

import com.clarionmedia.infinitum.activity.InfinitumListActivity;
import com.clarionmedia.infinitum.activity.annotation.InjectLayout;
import com.clarionmedia.infinitum.orm.Session;
import com.clarionmedia.infinitum.orm.context.InfinitumOrmContext;
import com.clarionmedia.infinitum.orm.context.InfinitumOrmContext.SessionType;
import com.clarionmedia.infinitumdemo.R;
import com.clarionmedia.infinitumdemo.domain.Note;
import com.clarionmedia.infinitumdemo.ui.widget.NoteAdapter;

@InjectLayout(R.layout.activity_sqlite)
public class SqliteActivity extends InfinitumListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		InfinitumOrmContext orm = getInfinitumContext().getChildContext(InfinitumOrmContext.class);
		Session session = orm.getSession(SessionType.SQLITE);
		session.open();
		List<Note> notes = session.createCriteria(Note.class).list();
		setListAdapter(new NoteAdapter(this, R.id.note_name, notes));
		session.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_sqlite, menu);
		return true;
	}

}
