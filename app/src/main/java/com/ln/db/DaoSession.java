package com.ln.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.ln.db.RecordPiaDB;

import com.ln.db.RecordPiaDBDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig recordPiaDBDaoConfig;

    private final RecordPiaDBDao recordPiaDBDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        recordPiaDBDaoConfig = daoConfigMap.get(RecordPiaDBDao.class).clone();
        recordPiaDBDaoConfig.initIdentityScope(type);

        recordPiaDBDao = new RecordPiaDBDao(recordPiaDBDaoConfig, this);

        registerDao(RecordPiaDB.class, recordPiaDBDao);
    }
    
    public void clear() {
        recordPiaDBDaoConfig.clearIdentityScope();
    }

    public RecordPiaDBDao getRecordPiaDBDao() {
        return recordPiaDBDao;
    }

}