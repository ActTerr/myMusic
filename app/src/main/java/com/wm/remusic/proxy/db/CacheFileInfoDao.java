package com.wm.remusic.proxy.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.wm.remusic.MainApplication;

import java.util.Collection;
import java.util.Map;

public class CacheFileInfoDao extends SQLiteOpenHelper {

    static final int DB_VERSION = 1;
    static final String DB_NAME = "cachefileinfo.db";
    static final String TABLE_NAME = "CacheFileInfo";

    private static CacheFileInfoDao audioDao;

    synchronized public static CacheFileInfoDao getInstance() {
        if (audioDao == null) {
            audioDao = new CacheFileInfoDao();
        }
        return audioDao;
    }

    private CacheFileInfoDao() {
        super(MainApplication.context, DB_NAME, null, DB_VERSION);
    }

    public void insertOrUpdate(String fileName, int fileSize) {
        CacheFileInfo cacheFileInfo = new CacheFileInfo(fileName, fileSize);
        if (getFileSize(cacheFileInfo.getFileName()) == -1) {
            insert(cacheFileInfo);
        } else {
            update(cacheFileInfo);
        }
    }

    private void insert(CacheFileInfo cacheFileInfo) {
        //取得可以写入的数据库
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        //开始执行事务
        sqLiteDatabase.beginTransaction();
        try {
            //将数据赋值给ContentValues
            ContentValues cv = packData(cacheFileInfo);
            //执行插入方法(表名，字段，数据）
            sqLiteDatabase.insert(TABLE_NAME, null, cv);
            //设置事务执行成功
            sqLiteDatabase.setTransactionSuccessful();
        } finally {
            //关闭事务
            sqLiteDatabase.endTransaction();
        }
    }

    public void delete(String fileName) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            sqLiteDatabase.delete(TABLE_NAME, "FileName=?", new String[]{fileName});
            sqLiteDatabase.setTransactionSuccessful();
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    private void update(CacheFileInfo cacheFileSize) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            ContentValues cv = packData(cacheFileSize);
            sqLiteDatabase.update(TABLE_NAME, cv, "FileName=?", new String[]{cacheFileSize.getFileName()});
            sqLiteDatabase.setTransactionSuccessful();
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    public int getFileSize(String fileName) {
        //通过文件名查询，用结果集来接收
        Cursor cursor = rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE FileName=?", new String[]{fileName});
        CacheFileInfo t = null;

        if (cursor != null) {
            //当指针移动到第一个位置时
            if (cursor.moveToFirst()) {
                //调用extractData方法来取得实体类
                t = extractData(cursor);
            }
            //关闭结果集
            cursor.close();
            cursor = null;
        }
        if (t == null) {
            return -1;
        } else {
            return t.getFileSize();
        }
    }

    public CacheFileInfo extractData(Cursor cursor) {
        if (null == cursor) {
            return null;
        }
        //新建一个实体类
        CacheFileInfo album = new CacheFileInfo();
        //从结果集中取到对应字段的值
        album.setFileName(cursor.getString(cursor.getColumnIndex("FileName")));
        album.setFileSize(cursor.getInt(cursor.getColumnIndex("FileSize")));
        return album;
    }

    public ContentValues packData(CacheFileInfo cacheFileSize) {
        ContentValues cv = new ContentValues();
        cv.put("FileName", cacheFileSize.getFileName());
        cv.put("FileSize", cacheFileSize.getFileSize());
        return cv;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("DB", "CreateTable " + TABLE_NAME);
        db.execSQL("create table " + TABLE_NAME + "(FileName STRING PRIMARY KEY,FileSize INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor rawQuery(String sql, String[] selectionArgs) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, selectionArgs);
    }

    public static boolean isNull(Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof Collection<?>) {
            return ((Collection<?>) object).size() == 0;
        }
        if (object instanceof Map<?, ?>) {
            return ((Map<?, ?>) object).size() == 0;
        }
        return false;
    }

    class CacheFileInfo {
        private String fileName;
        private int fileSize;

        public CacheFileInfo(String fileName, int fileSize) {
            this.fileName = fileName;
            this.fileSize = fileSize;
        }

        public CacheFileInfo() {
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public int getFileSize() {
            return fileSize;
        }

        public void setFileSize(int fileSize) {
            this.fileSize = fileSize;
        }
    }
}
