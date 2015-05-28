package uklid.com.pipesurvey;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
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
public class PipeSurveysListLoader extends AsyncTaskLoader<List<PipeSurvey>> {
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
                PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_UTILIZATION,
                PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_SHEETNO,
                PipeSurveysContract.PipeSurveysColumns.PIPESURVEY_DATE,
                PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_ORDER,
                PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_TYPE,
                PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_NAME,
                PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_NO,
                PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_MOO,
                PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_SOI,
                PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_ROAD,
                PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_SUBDISTRICT,
                PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_DISTRICT,
                PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_PROVINCE,
                PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_PO,
                PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_TELEPHONE,
                PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_NROOMS,
                PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_NOCCUPANTS,
                PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_NSTOREY,
                PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_MATERIAL,
                PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_FOCCUPANTS,
                PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_CONSTRUCTION,
                PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_PICTURENAME
        };

        List<PipeSurvey> entries = new ArrayList<PipeSurvey>();

        mCursor = mContentResolver.query(PipeSurveysContract.URI_TABLE, projection, null, null, null);

        if (mCursor != null) {
            if (mCursor.moveToFirst()) {
                do {
                    int _id = mCursor.getInt(mCursor.getColumnIndex(BaseColumns._ID));
                    String pipecode = mCursor.getString(
                            mCursor.getColumnIndex(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_PIPECODE));
                    String code = mCursor.getString(
                            mCursor.getColumnIndex(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_CODE));
                    String utilization = mCursor.getString(
                            mCursor.getColumnIndex(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_UTILIZATION));
                    String sheetno = mCursor.getString(
                            mCursor.getColumnIndex(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_SHEETNO));
                    String date = mCursor.getString(
                            mCursor.getColumnIndex(PipeSurveysContract.PipeSurveysColumns.PIPESURVEY_DATE));
                    String order = mCursor.getString(
                            mCursor.getColumnIndex(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_ORDER));
                    String type = mCursor.getString(
                            mCursor.getColumnIndex(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_TYPE));
                    String name = mCursor.getString(
                            mCursor.getColumnIndex(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_NAME));
                    String no = mCursor.getString(
                            mCursor.getColumnIndex(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_NO));
                    String moo = mCursor.getString(
                            mCursor.getColumnIndex(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_MOO));
                    String soi = mCursor.getString(
                            mCursor.getColumnIndex(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_SOI));
                    String road = mCursor.getString(
                            mCursor.getColumnIndex(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_ROAD));
                    String subdistrict = mCursor.getString(
                            mCursor.getColumnIndex(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_SUBDISTRICT));
                    String district = mCursor.getString(
                            mCursor.getColumnIndex(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_DISTRICT));
                    String province = mCursor.getString(
                            mCursor.getColumnIndex(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_PROVINCE));
                    String po = mCursor.getString(
                            mCursor.getColumnIndex(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_PO));
                    String telephone = mCursor.getString(
                            mCursor.getColumnIndex(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_TELEPHONE));


                    String tempString =  mCursor.getString(mCursor.getColumnIndex(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_NROOMS));
                    int nRoom = 0;
                    if(!tempString.isEmpty()) {
                        nRoom = Integer.parseInt(mCursor.getString(
                                mCursor.getColumnIndex(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_NROOMS)));
                    }

                    tempString = mCursor.getString(mCursor.getColumnIndex(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_NOCCUPANTS));
                    int nOccupant = 0;
                    if(!tempString.isEmpty()) {
                        Integer.parseInt(mCursor.getString(
                                mCursor.getColumnIndex(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_NOCCUPANTS)));
                    }

                    tempString = mCursor.getString(mCursor.getColumnIndex(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_NSTOREY));
                    int nStorey = 0;
                    if(!tempString.isEmpty()) {
                        nStorey = Integer.parseInt(mCursor.getString(
                                mCursor.getColumnIndex(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_NSTOREY)));
                    }
                    String material = mCursor.getString(
                            mCursor.getColumnIndex(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_MATERIAL));
                    String fOccupant = mCursor.getString(
                            mCursor.getColumnIndex(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_FOCCUPANTS));
                    String construction = mCursor.getString(
                            mCursor.getColumnIndex(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_CONSTRUCTION));
                    String pictureName = mCursor.getString(
                            mCursor.getColumnIndex(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_PICTURENAME));


                    PipeSurvey pipeSurvey = new PipeSurvey(_id, pipecode, sheetno, date, code, order, utilization, type, name, no, moo, soi, road,
                            subdistrict, district, province, po, telephone, nOccupant, fOccupant, nStorey, nRoom, material, construction, pictureName);
                    entries.add(pipeSurvey);
                } while (mCursor.moveToNext());
            }
        }
        return entries;
    }

    @Override
    public void deliverResult(List<PipeSurvey> pipeSurveys) {
        if (isReset()) {
            if (pipeSurveys != null) {
                mCursor.close();
            }
        }

        List<PipeSurvey> oldPipeSurveysList = mPipeSurvey;
        if (mPipeSurvey == null || mPipeSurvey.size() == 0) {
            Log.d(LOG_TAG, "+++++++++ No Data Return");
        }

        mPipeSurvey = pipeSurveys;
        if (isStarted()) {
            super.deliverResult(pipeSurveys);
        }

        if (oldPipeSurveysList != null && oldPipeSurveysList != pipeSurveys) {
            mCursor.close();
        }
    }

    @Override
    protected void onStartLoading() {
        if (mPipeSurvey != null) {
            deliverResult(mPipeSurvey);
        }

        if (takeContentChanged() || mPipeSurvey == null) { //reload data again
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
        if (mCursor != null) {
            mCursor.close();
        }

        mPipeSurvey = null;
    }

    @Override
    public void onCanceled(List<PipeSurvey> pipeSurveys) {
        super.onCanceled(pipeSurveys);

        if (mCursor != null) {
            mCursor.close();
        }
    }

    @Override
    public void forceLoad() {
        super.forceLoad();
    }
}