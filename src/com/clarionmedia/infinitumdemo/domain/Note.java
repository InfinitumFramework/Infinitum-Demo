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

}
