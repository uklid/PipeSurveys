package uklid.com.pipesurvey;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Uklid on 5/27/2015.
 */
public class PipeSurveysDialog extends DialogFragment{
    private LayoutInflater mLayoutInfalter;

    public static final String LOG_TAG = PipeSurveysDialog.class.getSimpleName();
    public static final String DIALOG_TYPE = "command";
    public static final String DELETE_RECORD = "deleteRecord";

    public static final String DELETE_DATABASE = "deleteDatabase";

    public static final String CONFIRM_EXIT = "confirmExit";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        mLayoutInfalter = getActivity().getLayoutInflater();
        final View view = mLayoutInfalter.inflate(R.layout.pipesurvey_layout, null);
        String command = getArguments().getString(DIALOG_TYPE);
        if (command.equals(DELETE_RECORD)) {
            final int _id = getArguments().getInt(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_ID);
            String code = getArguments().getString(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_CODE);
            TextView popupMessage = (TextView) view.findViewById(R.id.popup_message);
            Toast.makeText(getActivity(), "Deleting record: " + _id, Toast.LENGTH_LONG).show();
            popupMessage.setText("Are you sure you want to delete " + code + " from your friends list?");
            builder.setView(view).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ContentResolver contentResolver = getActivity().getContentResolver();
                    Uri uri = PipeSurveysContract.PipeSurveys.buildPipeSurveyUri(String.valueOf(_id));
                    contentResolver.delete(uri, null, null);
                    Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
        } else if (command.equals(DELETE_DATABASE)) {

            TextView popupMessage = (TextView) view.findViewById(R.id.popup_message);
            popupMessage.setText("Are you sure you want to delete entire database?");
            builder.setView(view).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ContentResolver contentResolver = getActivity().getContentResolver();
                    Uri uri = PipeSurveysContract.URI_TABLE;
                    contentResolver.delete(uri, null, null);






                    Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
        } else if (command.equals(CONFIRM_EXIT)) {
            /// backpressed
            TextView popupMessage = (TextView) view.findViewById(R.id.popup_message);
            popupMessage.setText("exit?");
            builder.setView(view).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    getActivity().finish();

                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
        } else {
            Log.d(LOG_TAG, "Invalid command passed as parameter");
        }
        return builder.create();
    }
}
