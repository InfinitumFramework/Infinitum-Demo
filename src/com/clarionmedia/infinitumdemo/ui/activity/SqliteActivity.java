package com.clarionmedia.infinitumdemo.ui.activity;

import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

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
		populateNotes();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_sqlite, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_add:
			createNewNoteDialog().show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	private void populateNotes() {
		InfinitumOrmContext orm = getInfinitumContext().getChildContext(InfinitumOrmContext.class);
		Session session = orm.getSession(SessionType.SQLITE);
		session.open();
		List<Note> notes = session.createCriteria(Note.class).list();
		setListAdapter(new NoteAdapter(this, R.id.note_name, notes));
		session.close();
	}

	private Dialog createNewNoteDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		LayoutInflater inflater = getLayoutInflater();
		View layout = inflater.inflate(R.layout.dialog_create_note, null);
		final EditText noteName = (EditText) layout.findViewById(R.id.field_note_name);
		Spinner courseName = (Spinner) layout.findViewById(R.id.field_course_name);
		final EditText noteContents = (EditText) layout.findViewById(R.id.field_note_contents);
		builder.setView(layout).setPositiveButton(R.string.save_note, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				String name = noteName.getText().toString().trim();
				if (TextUtils.isEmpty(name))
					return;
				Note note = new Note();
				note.setName(name);
				note.setContents(noteContents.getText().toString());
				note.setCourse(null); // TODO
				Session session = getInfinitumContext().getChildContext(InfinitumOrmContext.class).getSession(SessionType.SQLITE);
				session.open();
				session.save(note);
				session.close();
				populateNotes();
				dialog.dismiss();
			}
		}).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});
		return builder.create();
	}

}
