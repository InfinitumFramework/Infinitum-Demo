package com.clarionmedia.infinitumdemo.dao.impl;

import com.clarionmedia.infinitum.di.annotation.Autowired;
import com.clarionmedia.infinitum.di.annotation.Bean;
import com.clarionmedia.infinitum.orm.Session;
import com.clarionmedia.infinitum.orm.context.InfinitumOrmContext;
import com.clarionmedia.infinitumdemo.dao.NoteDao;
import com.clarionmedia.infinitumdemo.domain.Note;

import java.util.List;

@Bean
public class NoteDaoImpl implements NoteDao {

    private Session mSession;

    @Autowired
    public NoteDaoImpl(InfinitumOrmContext context) {
        mSession = context.getSession(InfinitumOrmContext.SessionType.SQLITE);
    }

    @Override
    public List<Note> getAllNotes() {
        return mSession.createCriteria(Note.class).list();
    }

    @Override
    public void saveNote(Note note) {
        mSession.save(note);
    }

    @Override
    public void updateNote(Note note) {
        mSession.update(note);
    }

    @Override
    public Note getById(long id) {
        return mSession.load(Note.class, id);
    }

    @Override
    public void deleteNote(Note note) {
        mSession.delete(note);
    }

}
