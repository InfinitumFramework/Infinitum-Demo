package com.clarionmedia.infinitumdemo.ui.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.clarionmedia.infinitum.activity.InfinitumListActivity;
import com.clarionmedia.infinitum.activity.annotation.InjectLayout;
import com.clarionmedia.infinitum.di.annotation.Autowired;
import com.clarionmedia.infinitum.orm.Session;
import com.clarionmedia.infinitum.orm.context.InfinitumOrmContext;
import com.clarionmedia.infinitum.orm.context.InfinitumOrmContext.SessionType;
import com.clarionmedia.infinitum.orm.criteria.criterion.Conditions;
import com.clarionmedia.infinitum.ui.widget.impl.DataBoundArrayAdapter;
import com.clarionmedia.infinitumdemo.R;
import com.clarionmedia.infinitumdemo.domain.Category;
import com.clarionmedia.infinitumdemo.domain.Note;
import com.clarionmedia.infinitumdemo.util.NoteGenerator;

@InjectLayout(R.layout.activity_note_list)
public class NoteListActivity extends InfinitumListActivity {

	private Session mSession;

	@Autowired
	private NoteGenerator mNoteGenerator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		InfinitumOrmContext orm = getInfinitumContext().getChildContext(InfinitumOrmContext.class);
		mSession = orm.getSession(SessionType.SQLITE).open();
		populateNotes();
	}

	@Override
	public void onDestroy() {
		mSession.close();
		super.onDestroy();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Note note = (Note) getListAdapter().getItem(position);
		Intent intent = new Intent(this, ViewNoteActivity.class);
		intent.putExtra(ViewNoteActivity.EXTRA_NOTE_ID, note.getId());
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_note_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_add:
			createNewNoteDialog().show();
			return true;
		case R.id.menu_random:
			mSession.open();
			Category random = mSession.createCriteria(Category.class).add(Conditions.eq("mName", "Random")).unique();
			if (random == null) {
				random = new Category();
				random.setName("Random");
			}
			Note note = mNoteGenerator.generate(random);
			mSession.save(note);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void populateNotes() {
		DataBoundArrayAdapter<Note> adapter = new DataBoundArrayAdapter<Note>(getInfinitumContext(), this, R.layout.layout_note_row,
				R.id.note_name, mSession.createCriteria(Note.class)) {
			public View getView(int position, View convertView, ViewGroup parent) {
				LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View rowView = inflater.inflate(R.layout.layout_note_row, parent, false);
				Note note = getItem(position);
				TextView textView = (TextView) rowView.findViewById(R.id.note_name);
				textView.setText(note.getName() + " (" + note.getCategory().getName() + ")");
				return rowView;
			}
		};
		adapter.bind();
		setListAdapter(adapter);
	}

	private Dialog createNewNoteDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		LayoutInflater inflater = getLayoutInflater();
		View layout = inflater.inflate(R.layout.dialog_create_note, null);
		final EditText noteName = (EditText) layout.findViewById(R.id.field_note_name);
		final EditText categoryName = (EditText) layout.findViewById(R.id.field_category_name);
		final EditText noteContents = (EditText) layout.findViewById(R.id.field_note_contents);
		builder.setView(layout).setPositiveButton(R.string.save_note, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				String name = noteName.getText().toString().trim();
				String category = categoryName.getText().toString().trim();
				if (TextUtils.isEmpty(name)) {
					Toast.makeText(NoteListActivity.this, "Note name is required", Toast.LENGTH_LONG).show();
					return;
				}
				if (TextUtils.isEmpty(category)) {
					Toast.makeText(NoteListActivity.this, "Category name is required", Toast.LENGTH_LONG).show();
					return;
				}
				createNote(name, category, noteContents.getText().toString());
				dialog.dismiss();
			}
		}).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});
		return builder.create();
	}

	private void createNote(String name, String categoryName, String contents) {
		Session session = getInfinitumContext().getChildContext(InfinitumOrmContext.class).getSession(SessionType.SQLITE);
		session.open();
		Note note = new Note();
		note.setName(name);
		note.setContents(contents);
		Category category = session.createCriteria(Category.class).add(Conditions.eq("mName", categoryName)).unique();
		if (category == null) {
			category = new Category();
			category.setName(categoryName);
		}
		note.setCategory(category);
		session.save(note);
	}

}
