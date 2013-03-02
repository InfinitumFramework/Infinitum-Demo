package com.clarionmedia.infinitumdemo.domain;

import java.util.ArrayList;
import java.util.List;

import com.clarionmedia.infinitum.orm.annotation.OneToMany;

public class Category {

	private long mId;

	private String mName;

	@OneToMany(className = "com.clarionmedia.infinitumdemo.domain.Note", column = "category", name = "categoryNotes")
	private List<Note> mNotes;

	public Category() {
		mNotes = new ArrayList<Note>();
	}

	public long getId() {
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
		return hash;
	}

	@Override
	public boolean equals(Object other) {
		if (!getClass().isInstance(other))
			return false;
		Category otherCategory = (Category) other;
		return mId == otherCategory.mId && mName.equals(otherCategory.mName);
	}

}
