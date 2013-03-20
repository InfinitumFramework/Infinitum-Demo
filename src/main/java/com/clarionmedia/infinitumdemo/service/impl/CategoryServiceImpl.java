package com.clarionmedia.infinitumdemo.service.impl;

import com.clarionmedia.infinitum.di.annotation.Autowired;
import com.clarionmedia.infinitum.di.annotation.Bean;
import com.clarionmedia.infinitumdemo.dao.CategoryDao;
import com.clarionmedia.infinitumdemo.domain.Category;
import com.clarionmedia.infinitumdemo.service.CategoryService;

@Bean
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao mCategoryDao;

    @Override
    public Category getByName(String name) {
        return mCategoryDao.getByName(name);
    }

}
