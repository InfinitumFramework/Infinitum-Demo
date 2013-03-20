package com.clarionmedia.infinitumdemo.dao.impl;

import com.clarionmedia.infinitum.di.annotation.Autowired;
import com.clarionmedia.infinitum.di.annotation.Bean;
import com.clarionmedia.infinitum.orm.Session;
import com.clarionmedia.infinitum.orm.context.InfinitumOrmContext;
import com.clarionmedia.infinitum.orm.criteria.criterion.Conditions;
import com.clarionmedia.infinitumdemo.dao.CategoryDao;
import com.clarionmedia.infinitumdemo.domain.Category;

@Bean
public class CategoryDaoImpl implements CategoryDao {

    private Session mSession;

    @Autowired
    public CategoryDaoImpl(InfinitumOrmContext context) {
        mSession = context.getSession(InfinitumOrmContext.SessionType.SQLITE);
    }

    @Override
    public Category getByName(String name) {
        return mSession.createCriteria(Category.class).add(Conditions.eq("mName", name)).unique();
    }

}
