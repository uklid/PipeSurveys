package uklid.com.pipesurvey;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by Uklid on 5/25/2015.
 */
public class PipeSurveysProvider extends ContentProvider {
    private PipeSurveysDatabase mOpenHelper;

    private static String TAG = PipeSurveysProvider.class.getSimpleName();
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private static final int PIPESURVEYS = 100;
    private static final int PIPESURVEYS_ID = 101;

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = PipeSurveysContract.CONTENT_AUTHORITY;
        matcher.addURI(authority, "pipesurveys",PIPESURVEYS);
        matcher.addURI(authority,"pipesurveys/*",PIPESURVEYS_ID);
        return  matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new PipeSurveysDatabase(getContext());
        return true;
    }


    private void deleteDatabase() {
        mOpenHelper.close();
        PipeSurveysDatabase.deleteDatabase(getContext());

        mOpenHelper = new PipeSurveysDatabase(getContext());
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch(match) {
            case PIPESURVEYS:
                return PipeSurveysContract.PipeSurveys.CONTENT_TYPE;
            case PIPESURVEYS_ID:
                return PipeSurveysContract.PipeSurveys.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri);
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        final int match = sUriMatcher.match(uri);

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(PipeSurveysDatabase.Tables.PIPESURVEYS);

        switch (match) {
            case PIPESURVEYS:
                //do nothing
                break;
            case PIPESURVEYS_ID:
                String id = PipeSurveysContract.PipeSurveys.getPipeSurveyId(uri);
                queryBuilder.appendWhere(BaseColumns._ID + "=" + id);
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri);
        }

        Cursor cursor = queryBuilder.query(db ,projection,selection,selectionArgs,null,null,sortOrder);
        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.v(TAG, "insert(uri=" + uri + ", values=" + values.toString());

        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        switch(match) {
            case PIPESURVEYS:
                long recordId = db.insertOrThrow(PipeSurveysDatabase.Tables.PIPESURVEYS,null,values);
                return PipeSurveysContract.PipeSurveys.buildPipeSurveyUri(String.valueOf(recordId));
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri);
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.v(TAG, "delete(uri=" + uri);

        if(uri.equals(PipeSurveysContract.URI_TABLE))
        {
            deleteDatabase();
            return 0;
        }

        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);

        switch(match) {
            case PIPESURVEYS_ID:
                String id = PipeSurveysContract.PipeSurveys.getPipeSurveyId(uri);
                String selectionCriteria = BaseColumns._ID + "=" + id
                        +(!TextUtils.isEmpty(selection) ? " AND (" + selection + ")" : "");
                return  db.delete(PipeSurveysDatabase.Tables.PIPESURVEYS,selectionCriteria,selectionArgs);
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri);
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Log.v(TAG, "update(uri=" + uri + ", values=" + values.toString());

        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);

        String selectionCriteria = selection;
        switch(match) {
            case PIPESURVEYS:
                //do nothing
                break;
            case PIPESURVEYS_ID:
                String id = PipeSurveysContract.PipeSurveys.getPipeSurveyId(uri);
                selectionCriteria = BaseColumns._ID + "=" + id
                        +(!TextUtils.isEmpty(selection) ? " AND (" + selection + ")" : "");
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri);
        }

        return db.update(PipeSurveysDatabase.Tables.PIPESURVEYS,values, selectionCriteria,selectionArgs);
    }
}
