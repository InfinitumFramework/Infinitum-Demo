package com.clarionmedia.infinitumdemo.ui.widget;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.clarionmedia.infinitumdemo.R;
import com.clarionmedia.infinitumdemo.domain.Note;

public class NoteAdapter extends ArrayAdapter<Note> {

	private Context mContext;

	public NoteAdapter(Context context, int textViewResourceId, List<Note> notes) {
		super(context, textViewResourceId, notes);
		mContext = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.layout_note_row, parent, false);
		Note note = getItem(position);
		TextView textView = (TextView) rowView.findViewById(R.id.note_name);
		textView.setText(note.getName());
		return rowView;
	}

}
