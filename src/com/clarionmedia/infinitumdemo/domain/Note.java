package com.clarionmedia.infinitumdemo.domain;

import java.util.Date;

import com.clarionmedia.infinitum.orm.annotation.ManyToOne;

public class Note {

	private long mId;

	private Date mCreated;

	private String mName;

	private String mContents;

	@ManyToOne(className = "com.clarionmedia.infinitumdemo.domain.Course", column = "course", name = "noteCourse")
	private Course mCourse;
	
	public Note() {
		mCreated = new Date();
	}
	
	public long getId() {
		return mId;
	}

	public void setmId(long id) {
		mId = id;
	}

	public Date getCreated() {
		return mCreated;
	}

	public void setmCreated(Date created) {
		mCreated = created;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}

	public String getContents() {
		return mContents;
	}

	public void setContents(String contents) {
		mContents = contents;
	}

	public Course getCourse() {
		return mCourse;
	}

	public void setCourse(Course course) {
		mCourse = course;
	}
	
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int hash = 7;
		hash = hash * PRIME + Long.valueOf(mId).hashCode();
		hash = hash * PRIME + mCreated.hashCode();
		hash = hash * PRIME + mName.hashCode();
		hash = hash * PRIME + mContents.hashCode();
		if (mCourse != null)
			hash = hash * PRIME + mCourse.hashCode();
		return hash;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!getClass().isInstance(other))
			return false;
		Note otherNote = (Note) other;
		if (mId != otherNote.mId)
			return false;
		if (!mCreated.equals(otherNote.mCreated))
			return false;
		if (!mName.equals(otherNote.mName))
			return false;
		if (!mContents.equals(otherNote.mContents))
			return false;
		if (mCourse == null && otherNote.mCourse != null)
			return false;
		if (mCourse == null && otherNote.mCourse == null)
			return true;
		return mCourse.equals(otherNote.mCourse);
	}

}
