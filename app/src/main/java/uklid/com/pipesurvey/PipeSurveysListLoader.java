package uklid.com.pipesurvey;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Uklid on 5/27/2015.
 */
public class PipeSurveysListLoader  extends AsyncTaskLoader<List<PipeSurvey>>  {
    private static final String LOG_TAG = PipeSurveysListLoader.class.getSimpleName();
    private List<PipeSurvey> mPipeSurvey;
    private ContentResolver mContentResolver;
    private Cursor mCursor;

    public PipeSurveysListLoader(Context context, Uri uri, ContentResolver contentResolver) {
        super(context);
        mContentResolver = contentResolver;
    }

    @Override
    public List<PipeSurvey> loadInBackground() {
        String[] projection = {BaseColumns._ID,
                PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_PIPECODE,
                PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_CODE,
                PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_UTILIZATION
        };

        List<PipeSurvey> entries = new ArrayList<PipeSurvey>();

        mCursor = mContentResolver.query(PipeSurveysContract.URI_TABLE, projection,null,null,null);
        if(mCursor != null) {
            if(mCursor.moveToFirst()) {
                do {
                    int _id = mCursor.getInt(mCursor.getColumnIndex(BaseColumns._ID));
                    String pipecode = mCursor.getString(
                            mCursor.getColumnIndex(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_PIPECODE));
                    String code = mCursor.getString(
                            mCursor.getColumnIndex(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_CODE));
                    String utilization = mCursor.getString(
                            mCursor.getColumnIndex(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_UTILIZATION));

                    PipeSurvey pipeSurvey = new PipeSurvey(_id, pipecode,"sheetno","date",code,"order",utilization,"type","name","no","moo","soi","road",
                            "subdistrict","district","province","po","telephone",1/*noccupant*/,"foccupant",1/*nstorey*/,1/*nroom*/,"material","construction","picturename");
                    entries.add(pipeSurvey);
                } while(mCursor.moveToNext());
            }
        }
        return entries;
    }

    @Override
    public void deliverResult(List<PipeSurvey> pipeSurveys) {
        if(isReset()) {
            if (pipeSurveys != null) {
                mCursor.close();
            }
        }

        List<PipeSurvey> oldPipeSurveysList = mPipeSurvey;
        if(mPipeSurvey == null || mPipeSurvey.size() == 0) {
            Log.d(LOG_TAG, "+++++++++ No Data Return");
        }

        mPipeSurvey = pipeSurveys;
        if(isStarted()) {
            super.deliverResult(pipeSurveys);
        }

        if(oldPipeSurveysList != null && oldPipeSurveysList != pipeSurveys) {
            mCursor.close();
        }
    }

    @Override
    protected void onStartLoading() {
        if(mPipeSurvey!=null) {
            deliverResult(mPipeSurvey);
        }

        if(takeContentChanged() || mPipeSurvey == null) { //reload data again
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        onStopLoading();
        if(mCursor != null) {
            mCursor.close();
        }

        mPipeSurvey = null;
    }

    @Override
    public void onCanceled(List<PipeSurvey> pipeSurveys) {
        super.onCanceled(pipeSurveys);

        if(mCursor != null) {
            mCursor.close();
        }
    }

    @Override
    public void forceLoad() {
        super.forceLoad();
    }
}