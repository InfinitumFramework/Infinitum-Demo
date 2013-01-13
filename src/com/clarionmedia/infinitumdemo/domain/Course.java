package com.clarionmedia.infinitumdemo.domain;

import java.util.ArrayList;
import java.util.List;

import com.clarionmedia.infinitum.orm.annotation.OneToMany;

public class Course {

	private long mId;

	private String mName;

	@OneToMany(className = "com.clarionmedia.infinitumdemo.domain.Note", column = "course", name = "courseNotes")
	private List<Note> mNotes;

	public long getmId() {
		return mId;
	}

	public void setId(long id) {
		mId = id;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}

	public List<Note> getNotes() {
		return mNotes;
	}

	public void setNotes(List<Note> notes) {
		mNotes = notes;
	}

	public Course() {
		mNotes = new ArrayList<Note>();
	}

}
