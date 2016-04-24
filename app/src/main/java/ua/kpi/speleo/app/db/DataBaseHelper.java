package ua.kpi.speleo.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cooper on 23.04.16.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "cavedb";
    private static final int DATABASE_VERSION = 1;

    public static final String CAVES_TABLE = "caves";
    public static final String DATA_TABLE = "data";

    public static final String ID_COLUMN = "id";
    public static final String CAVES_NAME = "name";
    public static final String DATA_FROM = "from1";
    public static final String DATA_TO = "to1";
    public static final String DATA_DISTANCE = "distance";
    public static final String DATA_AZIMUTH = "azimuth";
    public static final String DATA_INCLINATION = "inclination";
    public static final String DATA_ROLL_ANGLE = "roll_angle";
    public static final String DATA_ID_CAVE = "id_cave";

    public static final String CREATE_DATA_TABLE = "CREATE TABLE IF NOT EXISTS "
            + DATA_TABLE + "(" + ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DATA_FROM + " INTEGER , "
            + DATA_TO + " INTEGER , "
            + DATA_DISTANCE + " REAL , "
            + DATA_AZIMUTH + " REAL ,"
            + DATA_INCLINATION + " REAL ,"
            + DATA_ROLL_ANGLE + " REAL ,"
            + DATA_ID_CAVE + " INTEGER ,"
            + "FOREIGN KEY( " + DATA_ID_CAVE + " ) REFERENCES "
            + CAVES_TABLE + "( id ) " + ")";

    public static final String CREATE_CAVES_TABLE = "CREATE TABLE IF NOT EXISTS "
            + CAVES_TABLE + "( " + ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + CAVES_NAME + " TEXT)";

    private static DataBaseHelper instance;

    public static synchronized DataBaseHelper getInstance (Context context) {
        if( instance == null)
            instance = new DataBaseHelper(context);
        return instance;
    }

    private DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CAVES_TABLE);
        db.execSQL(CREATE_DATA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
