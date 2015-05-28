package uklid.com.pipesurvey;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Uklid on 5/28/2015.
 */
public class EditActivity extends FragmentActivity{
    private final String LOG_TAG = EditActivity.class.getSimpleName();
    private TextView mPipeCode, mSheetNo, mDate, mCode, mOrder, mUtilization,
            mType,mName,mNo,mMoo,mSoi,mRoad,mSubDistrict,mDistrict,mProvince,mPo,mTelephone,mNRoom,mNOccupant,mNStorey,mFOccupant,
            mConstruction;
    private Spinner mMaterial;
    private Button mButton,mCameraCaptureButton;
    private ImageView mImageView;



    private static final int CAMERA_REQUEST = 1888;
    private ContentResolver mContentResolver;

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

        mMaterial = (Spinner) findViewById(R.id.material);

        mImageView = (ImageView) findViewById(R.id.image);

        mContentResolver = EditActivity.this.getContentResolver();

        Intent intent = getIntent();
        final String _id = intent.getStringExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_ID);
        String pipeCode = intent.getStringExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_PIPECODE);
        String sheetNo = intent.getStringExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_SHEETNO);
        String date = intent.getStringExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEY_DATE);
        String code = intent.getStringExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_CODE);
        String order = intent.getStringExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_ORDER);
        String utilization = intent.getStringExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_UTILIZATION);
        String type = intent.getStringExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_TYPE);
        String name = intent.getStringExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_NAME);
        String no = intent.getStringExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_NO);
        String moo = intent.getStringExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_MOO);
        String soi = intent.getStringExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_SOI);
        String road = intent.getStringExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_ROAD);
        String subdistrict = intent.getStringExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_SUBDISTRICT);
        String district = intent.getStringExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_DISTRICT);
        String province = intent.getStringExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_PROVINCE);
        String po = intent.getStringExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_PO);
        String telephone = intent.getStringExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_TELEPHONE);
        String nRoom = intent.getStringExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_NROOMS);
        String nOccupant = intent.getStringExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_NOCCUPANTS);
        String nStorey = intent.getStringExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_NSTOREY);
        String material = intent.getStringExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_MATERIAL);
        String fOccupant = intent.getStringExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_FOCCUPANTS);
        String construction = intent.getStringExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_CONSTRUCTION);
        String pictureName = intent.getStringExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_PICTURENAME);

        mCameraCaptureButton = (Button)findViewById(R.id.cameraCaptureButton);
        mCameraCaptureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });





        mPipeCode.setText(pipeCode);
        mSheetNo.setText(sheetNo);
        mDate.setText(date);
        mCode.setText(code);
        mOrder.setText(order);
        mUtilization.setText(utilization);
        mType.setText(type);
        mName.setText(name);
        mNo.setText(no);
        mMoo.setText(moo);
        mSoi.setText(soi);
        mRoad.setText(road);
        mSubDistrict.setText(subdistrict);
        mDistrict.setText(district);
        mProvince.setText(province);
        mPo.setText(po);
        mTelephone.setText(telephone);
        mNRoom.setText(nRoom);
        mNOccupant.setText(nOccupant);
        mNStorey.setText(nStorey);
        mFOccupant.setText(fOccupant);
        mConstruction.setText(construction);

        mMaterial.setSelection(getSelectionIndex(mMaterial, material));

        try
        {
            ContextWrapper cw = new ContextWrapper(getApplicationContext());
            File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
            File f=new File(directory,pictureName);
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            mImageView = (ImageView)findViewById(R.id.image);
            mImageView.setImageBitmap(b);
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }


        mButton = (Button) findViewById(R.id.savedButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid()) {
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


                    Bitmap bitmapImage = ((BitmapDrawable)mImageView.getDrawable()).getBitmap();
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




                    Uri uri = PipeSurveysContract.PipeSurveys.buildPipeSurveyUri(_id);

                    int recordsUpdated = mContentResolver.update(uri, values, null, null);
                    Log.d(LOG_TAG, "number of record update = " + recordsUpdated);
                    Intent intent = new Intent(EditActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Please ensure you have entered some valid data.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                break;
        }
        return true;
    }

    private int getSelectionIndex(Spinner spinner, String myString) {
        int index = 0;
        for(int i=0;i<spinner.getCount();i++) {
            if(spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                index = i;
                break;
            }
        }
        return index;
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            mImageView.setImageBitmap(photo);
        }
    }
}
