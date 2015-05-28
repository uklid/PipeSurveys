package uklid.com.pipesurvey;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Uklid on 5/25/2015.
 */
public class PipeSurveysDatabase extends SQLiteOpenHelper {
    private static final String TAG = PipeSurveysDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "pipe_surveys_database.db";
    private static final int DATABASE_VERSION = 5;
    private final Context mContext;

    interface Tables {
        String PIPESURVEYS = "pipesurveys";
    }

    public PipeSurveysDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Tables.PIPESURVEYS + " ("
                        + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_PIPECODE + " TEXT NOT NULL,"
                        + PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_SHEETNO + " TEXT NOT NULL,"
                        + PipeSurveysContract.PipeSurveysColumns.PIPESURVEY_DATE + " TEXT NOT NULL,"
                        + PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_CODE + " TEXT NOT NULL,"
                        + PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_ORDER + " INT NOT NULL,"
                        + PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_UTILIZATION + " TEXT NOT NULL,"
                        + PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_TYPE + " TEXT,"
                        + PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_NAME + " TEXT,"
                        + PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_NO + " TEXT,"
                        + PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_MOO + " TEXT,"
                        + PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_SOI + " TEXT,"
                        + PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_ROAD + " TEXT,"
                        + PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_SUBDISTRICT + " TEXT,"
                        + PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_DISTRICT + " TEXT,"
                        + PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_PROVINCE + " TEXT,"
                        + PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_PO + " TEXT,"
                        + PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_TELEPHONE + " TEXT,"
                        + PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_NROOMS + " INT NOT NULL,"
                        + PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_NOCCUPANTS + " INT NOT NULL,"
                        + PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_NSTOREY + " INT NOT NULL,"
                        + PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_MATERIAL + " TEXT NOT NULL,"
                        + PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_FOCCUPANTS + " TEXT,"
                        + PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_CONSTRUCTION + " TEXT,"

                        + PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_PICTURENAME + " TEXT)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        int version = oldVersion;

        try{
            db.execSQL("DROP TABLE IF EXISTS "+ Tables.PIPESURVEYS);
            onCreate(db);
        }catch (SQLiteException e) {}



        if (version == 1) {
            // Add some extra fields to the database w/o deleting existing data
            version = 2;
        }
        if (version != DATABASE_VERSION) {
            db.execSQL("DROP TABLE IF EXISTS " + Tables.PIPESURVEYS);
            onCreate(db);
        }
    }

    public static void deleteDatabase(Context context)
    {
        context.deleteDatabase(DATABASE_NAME);
    }
}
