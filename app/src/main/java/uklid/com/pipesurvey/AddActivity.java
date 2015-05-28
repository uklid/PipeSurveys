package uklid.com.pipesurvey;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Provider;
import java.util.Calendar;

/**
 * Created by Uklid on 5/28/2015.
 */
public class AddActivity extends FragmentActivity{
    private final String LOG_TAG = AddActivity.class.getSimpleName();
    private TextView mPipeCode, mSheetNo, mDate, mCode, mOrder, mUtilization,
    mType,mName,mNo,mMoo,mSoi,mRoad,mSubDistrict,mDistrict,mProvince,mPo,mTelephone,mNRoom,mNOccupant,mNStorey,mFOccupant,
    mConstruction;
    private Spinner mMaterial;
    private Button mButton,mCameraCaptureButton;
    private ImageView mImage;
    private ContentResolver mContentResolver;

    private static final int CAMERA_REQUEST = 1888;

    private Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit);
        getActionBar().setDisplayHomeAsUpEnabled(true);





        mPipeCode = (TextView) findViewById(R.id.pipeCode);
        mSheetNo = (TextView) findViewById(R.id.sheetNo);
        mDate = (TextView) findViewById(R.id.date);
        mCode = (TextView) findViewById(R.id.code);
        mOrder = (TextView) findViewById(R.id.order);
        mUtilization = (TextView) findViewById(R.id.utilization);
        mType = (TextView) findViewById(R.id.type);
        mName = (TextView) findViewById(R.id.name);
        mNo = (TextView) findViewById(R.id.no);
        mMoo = (TextView) findViewById(R.id.moo);
        mSoi = (TextView) findViewById(R.id.soi);
        mRoad = (TextView) findViewById(R.id.road);
        mSubDistrict = (TextView) findViewById(R.id.subdistrict);
        mDistrict = (TextView) findViewById(R.id.district);
        mProvince = (TextView) findViewById(R.id.province);
        mPo = (TextView) findViewById(R.id.po);
        mTelephone = (TextView) findViewById(R.id.telephone);
        mNRoom = (TextView) findViewById(R.id.nroom);
        mNOccupant = (TextView) findViewById(R.id.noccupant);
        mNStorey = (TextView) findViewById(R.id.nstorey);
        mFOccupant = (TextView) findViewById(R.id.foccupant);
        mConstruction = (TextView) findViewById(R.id.construction);
        mContentResolver = AddActivity.this.getContentResolver();

        mCursor = mContentResolver.query(PipeSurveysContract.URI_TABLE,null,null,null,PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_ID + " DESC");
        if(mCursor != null) {
            mCursor.moveToFirst();
            int order = Integer.parseInt(mCursor.getString(
                    mCursor.getColumnIndex(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_ORDER)));

            mOrder.setText(String.valueOf(order+1));

            mPipeCode.setText(mCursor.getString(
                    mCursor.getColumnIndex(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_PIPECODE)));
            mSheetNo.setText(mCursor.getString(
                    mCursor.getColumnIndex(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_SHEETNO)));

        }



        mMaterial = (Spinner) findViewById(R.id.material);

        mImage = (ImageView) findViewById(R.id.image);

        mCameraCaptureButton = (Button)findViewById(R.id.cameraCaptureButton);
        mCameraCaptureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        ///Retrieve Latest Record












        final Calendar c = Calendar.getInstance();
        int yy = c.get(Calendar.YEAR);
        int mm = c.get(Calendar.MONTH);
        int dd = c.get(Calendar.DAY_OF_MONTH);

        mDate.setText(new StringBuilder()
        .append(dd).append("/")
        .append(mm+1).append("/")
        .append(yy));





        mButton = (Button) findViewById(R.id.savedButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    ContentValues values = new ContentValues();
                    values.put(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_PIPECODE, mPipeCode.getText().toString());
                    values.put(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_SHEETNO, mSheetNo.getText().toString());
                    values.put(PipeSurveysContract.PipeSurveysColumns.PIPESURVEY_DATE, mDate.getText().toString());
                    values.put(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_CODE, mCode.getText().toString());
                    values.put(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_ORDER, mOrder.getText().toString());
                    values.put(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_UTILIZATION, mUtilization.getText().toString());
                    values.put(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_TYPE, mType.getText().toString());
                    values.put(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_NAME, mName.getText().toString());
                    values.put(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_NO, mNo.getText().toString());
                    values.put(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_SOI, mSoi.getText().toString());
                    values.put(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_ROAD, mRoad.getText().toString());
                    values.put(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_MOO, mMoo.getText().toString());
                    values.put(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_SUBDISTRICT, mSubDistrict.getText().toString());
                    values.put(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_DISTRICT, mDistrict.getText().toString());
                    values.put(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_PROVINCE, mProvince.getText().toString());
                    values.put(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_PO, mPo.getText().toString());
                    values.put(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_TELEPHONE, mTelephone.getText().toString());
                    values.put(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_NROOMS, mNRoom.getText().toString());
                    values.put(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_NOCCUPANTS, mNOccupant.getText().toString());
                    values.put(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_NSTOREY, mNStorey.getText().toString());
                    values.put(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_MATERIAL, mMaterial.getSelectedItem().toString());
                    values.put(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_FOCCUPANTS, mFOccupant.getText().toString());
                    values.put(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_CONSTRUCTION, mConstruction.getText().toString());

                    String fileName = mPipeCode.getText().toString() + "-" + mSheetNo.getText().toString() + "-" + mCode.getText().toString()+".jpg";
                    values.put(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_PICTURENAME, fileName);


                    Bitmap bitmapImage = ((BitmapDrawable)mImage.getDrawable()).getBitmap();
                    ContextWrapper cw = new ContextWrapper(getApplicationContext());
                    File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
                    File myPath = new File(directory,fileName);
                    FileOutputStream fos = null;
                    try{
                        fos = new FileOutputStream(myPath);
                        bitmapImage.compress(Bitmap.CompressFormat.PNG,100,fos);
                        fos.close();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }



                    mContentResolver = AddActivity.this.getContentResolver();

                    Uri returned = mContentResolver.insert(PipeSurveysContract.URI_TABLE, values);


                    Log.d(LOG_TAG, "record id returned is " + returned.toString());
                    Intent intent = new Intent(AddActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();




                } else {
                    Toast.makeText(getApplicationContext(), "Please ensure you have entered some valid data.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            mImage.setImageBitmap(photo);
        }
    }

    private boolean isValid() {
        if (mPipeCode.getText().toString().length() == 0 ||
                mSheetNo.getText().toString().length() == 0 ||
                mDate.getText().toString().length() == 0 ||
        mCode.getText().toString().length() == 0||
                mOrder.getText().toString().length() == 0 ||
                mUtilization.getText().toString().length() == 0||
        mNRoom.getText().toString().length() == 0||
                mNOccupant.getText().toString().length()==0||
                mNStorey.getText().toString().length()==0)
        {
            return false;
        } else {
            return true;
        }
    }


    private boolean someDataEntered() {
        if (mPipeCode.getText().toString().length() > 0 ||
                mDate.getText().toString().length() > 0 ||
                mSheetNo.getText().toString().length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        if(someDataEntered()) {
            PipeSurveysDialog dialog = new PipeSurveysDialog();
            Bundle args = new Bundle();
            args.putString(PipeSurveysDialog.DIALOG_TYPE, PipeSurveysDialog.CONFIRM_EXIT);
            dialog.setArguments(args);
            dialog.show(getSupportFragmentManager(), "confirm-exit");
        }else {
            super.onBackPressed();
        }
    }
}
