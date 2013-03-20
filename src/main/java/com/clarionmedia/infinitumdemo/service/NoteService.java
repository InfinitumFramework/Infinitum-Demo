package com.clarionmedia.infinitumdemo.service;

import com.clarionmedia.infinitumdemo.domain.Note;

import java.util.List;

public interface NoteService {

    List<Note> getAllNotes();

    void saveNote(Note note);

    void updateNote(Note note);

    Note getById(long id);

    void deleteNote(Note note);

}
