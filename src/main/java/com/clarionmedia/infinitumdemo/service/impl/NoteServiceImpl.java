package com.clarionmedia.infinitumdemo.service.impl;

import com.clarionmedia.infinitum.di.annotation.Autowired;
import com.clarionmedia.infinitum.di.annotation.Bean;
import com.clarionmedia.infinitumdemo.dao.NoteDao;
import com.clarionmedia.infinitumdemo.domain.Note;
import com.clarionmedia.infinitumdemo.service.NoteService;

import java.util.List;

@Bean
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteDao mNoteDao;

    @Override
    public List<Note> getAllNotes() {
        return mNoteDao.getAllNotes();
    }

    @Override
    public void saveNote(Note note) {
        mNoteDao.saveNote(note);
    }

    @Override
    public void updateNote(Note note) {
        mNoteDao.updateNote(note);
    }

    @Override
    public Note getById(long id) {
        return mNoteDao.getById(id);
    }

    @Override
    public void deleteNote(Note note) {
        mNoteDao.deleteNote(note);
    }

}
