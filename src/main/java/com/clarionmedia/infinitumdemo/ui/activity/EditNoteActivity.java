package com.clarionmedia.infinitumdemo.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.clarionmedia.infinitum.activity.InfinitumActivity;
import com.clarionmedia.infinitum.activity.annotation.Bind;
import com.clarionmedia.infinitum.activity.annotation.InjectLayout;
import com.clarionmedia.infinitum.activity.annotation.InjectView;
import com.clarionmedia.infinitum.di.annotation.Autowired;
import com.clarionmedia.infinitumdemo.R;
import com.clarionmedia.infinitumdemo.domain.Category;
import com.clarionmedia.infinitumdemo.domain.Note;
import com.clarionmedia.infinitumdemo.service.CategoryService;
import com.clarionmedia.infinitumdemo.service.NoteService;

@InjectLayout(R.layout.activity_edit_note)
public class EditNoteActivity extends InfinitumActivity {

	public static final String EXTRA_NOTE_ID = "com.clarionmedia.infinitumdemo.NoteId";

	@InjectView(R.id.field_note_name)
	private EditText mNoteName;

	@InjectView(R.id.field_category_name)
	private EditText mCategoryName;

	@InjectView(R.id.field_note_contents)
	private EditText mNoteContents;

	@InjectView(R.id.cancel_edit)
	@Bind("cancel")
	private Button mCancel;

	@InjectView(R.id.confirm_edit)
	@Bind("editNote")
	private Button mConfirm;

    @Autowired
    private NoteService mNoteService;

    @Autowired
    private CategoryService mCategoryService;

	private Note mNote;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		long noteId = getIntent().getLongExtra(EXTRA_NOTE_ID, -1);
		mNote = mNoteService.getById(noteId);
		mNoteName.setText(mNote.getName());
		mCategoryName.setText(mNote.getCategory().getName());
		mNoteContents.setText(mNote.getContents());
	}

	protected void editNote(View v) {
		String name = mNoteName.getText().toString().trim();
		String categoryName = mCategoryName.getText().toString().trim();
		if (TextUtils.isEmpty(name)) {
			Toast.makeText(this, "Note name is required", Toast.LENGTH_LONG).show();
			return;
		}
		if (TextUtils.isEmpty(categoryName)) {
			Toast.makeText(this, "Category name is required", Toast.LENGTH_LONG).show();
			return;
		}
		mNote.setName(name);
		mNote.setContents(mNoteContents.getText().toString());
		Category category = mCategoryService.getByName(categoryName);
		if (category == null) {
			category = new Category();
			category.setName(categoryName);
		}
		mNote.setCategory(category);
		mNoteService.updateNote(mNote);
		setResult(RESULT_OK);
		finish();
	}

	protected void cancel(View v) {
		setResult(RESULT_CANCELED);
		finish();
	}

}
