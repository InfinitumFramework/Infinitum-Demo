package com.clarionmedia.infinitumdemo.domain;

import java.util.ArrayList;
import java.util.List;

import com.clarionmedia.infinitum.orm.annotation.OneToMany;

public class Course {

	private long mId;

	private String mName;

	@OneToMany(className = "com.clarionmedia.infinitumdemo.domain.Note", column = "course", name = "courseNotes")
	private List<Note> mNotes;

	public Course() {
		mNotes = new ArrayList<Note>();
	}

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

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int hash = 7;
		hash = hash * PRIME + Long.valueOf(mId).hashCode();
		hash = hash * PRIME + mName.hashCode();
		for (Note note : mNotes)
			hash = hash * PRIME + note.hashCode();
		return hash;
	}

	@Override
	public boolean equals(Object other) {
		if (!getClass().isInstance(other))
			return false;
		Course otherCourse = (Course) other;
		return mId == otherCourse.mId && mName.equals(otherCourse.mName) && mNotes.equals(otherCourse.mNotes);
	}

}
