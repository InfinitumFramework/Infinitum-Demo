package com.clarionmedia.infinitumdemo.util;

import com.clarionmedia.infinitumdemo.domain.Category;
import com.clarionmedia.infinitumdemo.domain.Note;

public interface NoteGenerator {
	
	Note generate(Category category);

}
