package com.clarionmedia.infinitumdemo.util.impl;

import java.util.Date;
import java.util.Random;

import com.clarionmedia.infinitum.di.annotation.Bean;
import com.clarionmedia.infinitumdemo.domain.Category;
import com.clarionmedia.infinitumdemo.domain.Note;
import com.clarionmedia.infinitumdemo.util.NoteGenerator;

@Bean("noteGenerator")
public class NoteGeneratorImpl implements NoteGenerator {
	
	private Random mRandom;
	
	public NoteGeneratorImpl() {
		mRandom = new Random();
	}
	
	@Override
	public Note generate(Category category) {
		int n = mRandom.nextInt(100);
		String name = "Note " + n;
		Note note = new Note();
		note.setName(name);
		note.setCategory(category);
		note.setContents("This note was generated on " + new Date() + ".");
		return note;
	}

}
