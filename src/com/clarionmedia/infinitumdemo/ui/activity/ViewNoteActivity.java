package com.clarionmedia.infinitumdemo.ui.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.clarionmedia.infinitum.activity.InfinitumActivity;
import com.clarionmedia.infinitum.activity.annotation.InjectLayout;
import com.clarionmedia.infinitum.activity.annotation.InjectView;
import com.clarionmedia.infinitum.orm.Session;
import com.clarionmedia.infinitum.orm.context.InfinitumOrmContext;
import com.clarionmedia.infinitum.orm.context.InfinitumOrmContext.SessionType;
import com.clarionmedia.infinitumdemo.R;
import com.clarionmedia.infinitumdemo.domain.Note;

@InjectLayout(R.layout.activity_view_note)
public class ViewNoteActivity extends InfinitumActivity {

	public static final String EXTRA_NOTE_ID = "com.clarionmedia.infinitumdemo.NoteId";

	@InjectView(R.id.note_name)
	private TextView mNoteName;
	
	@InjectView(R.id.course_name)
	private TextView mCourseName;

	@InjectView(R.id.note_contents)
	private TextView mNoteContents;

	private Note mNote;
	private Session mSession;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		long noteId = getIntent().getLongExtra(EXTRA_NOTE_ID, -1);
		InfinitumOrmContext orm = getInfinitumContext().getChildContext(InfinitumOrmContext.class);
		mSession = orm.getSession(SessionType.SQLITE).open();
		mNote = mSession.load(Note.class, noteId);
		mNoteName.setText(mNote.getName());
		mCourseName.setText(mNote.getCourse().getName());
		mNoteContents.setText(mNote.getContents());
	}
	
	@Override
	public void onDestroy() {
		mSession.close();
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_view_note, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_delete:
			createConfirmDeleteDialog().show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private Dialog createConfirmDeleteDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Are you sure you want to delete this note?")
				.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						mSession.delete(mNote);
						dialog.dismiss();
						finish();
					}
				}).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		return builder.create();
	}

}
