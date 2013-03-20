package com.clarionmedia.infinitumdemo.dao;

import com.clarionmedia.infinitumdemo.domain.Category;

public interface CategoryDao {

    Category getByName(String name);

}
