package uklid.com.pipesurvey;

import android.app.ProgressDialog;
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
import android.os.AsyncTask;
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

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Provider;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

    //JSON
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    private static final String LOGIN_URL = "http://http://uklidyeesarapat.com/addSurvey.php";
    private String fileName;
    //ENDJSON





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
        if(mCursor != null && mCursor.getCount() !=0) {
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

                    fileName = mPipeCode.getText().toString() + "-" + mSheetNo.getText().toString() + "-" + mCode.getText().toString()+".jpg";
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

                    //start parser




                    //finish parser

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



    class AddPipeSurvaeysToServer extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        boolean failure = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(AddActivity.this);
            pDialog.setMessage("Upload Data....");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            // Check for success tag
            int success;
            String pipecode = mPipeCode.getText().toString();
            String sheetno = mSheetNo.getText().toString();
            String date = mDate.getText().toString();
            String code = mCode.getText().toString();
            String order = mOrder.getText().toString();
            String utilization = mUtilization.getText().toString();
            String type = mType.getText().toString();
            String name = mName.getText().toString();
            String no = mNo.getText().toString();
            String moo = mMoo.getText().toString();
            String soi = mSoi.getText().toString();
            String road = mRoad.getText().toString();
            String subdistrict = mSubDistrict.getText().toString();
            String district = mDistrict.getText().toString();
            String province = mProvince.getText().toString();
            String po = mPo.getText().toString();
            String telephone = mTelephone.getText().toString();
            String noccupants = mNOccupant.getText().toString();
            String foccupants = mFOccupant.getText().toString();
            String nstorey = mNStorey.getText().toString();
            String nrooms = mNRoom.getText().toString();
            String material = mMaterial.getSelectedItem().toString();
            String construction = mConstruction.getText().toString();
            String picturename = fileName;

            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();


                params.add(new BasicNameValuePair("pipecode", pipecode));
                params.add(new BasicNameValuePair("sheetno", sheetno));
                params.add(new BasicNameValuePair("date", date));
                params.add(new BasicNameValuePair("code", code));
                params.add(new BasicNameValuePair("order", order));
                params.add(new BasicNameValuePair("utilization", utilization));
                params.add(new BasicNameValuePair("type", type));
                params.add(new BasicNameValuePair("name", name));
                params.add(new BasicNameValuePair("no", no));
                params.add(new BasicNameValuePair("moo", moo));
                params.add(new BasicNameValuePair("soi", soi));
                params.add(new BasicNameValuePair("road", road));
                params.add(new BasicNameValuePair("subdistrict", subdistrict));
                params.add(new BasicNameValuePair("district", district));
                params.add(new BasicNameValuePair("province", province));
                params.add(new BasicNameValuePair("po", po));
                params.add(new BasicNameValuePair("telephone", telephone));
                params.add(new BasicNameValuePair("nrooms", nrooms));
                params.add(new BasicNameValuePair("noccupants", noccupants));
                params.add(new BasicNameValuePair("nstorey", nstorey));
                params.add(new BasicNameValuePair("material", material));
                params.add(new BasicNameValuePair("construction", construction));
                params.add(new BasicNameValuePair("picturename", picturename));
                params.add(new BasicNameValuePair("foccupants", foccupants));



                Log.d("request!", "starting");

                //Posting user data to script
                JSONObject json = jsonParser.makeHttpRequest(
                        LOGIN_URL, "POST", params);

                // full json response
                Log.d("Login attempt", json.toString());

                // json success element
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    Log.d("User Created!", json.toString());
                    finish();
                    return json.getString(TAG_MESSAGE);
                }else{
                    Log.d("Login Failure!", json.getString(TAG_MESSAGE));
                    return json.getString(TAG_MESSAGE);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            if (file_url != null){
                Toast.makeText(AddActivity.this, file_url, Toast.LENGTH_LONG).show();
            }

        }

    }



}
