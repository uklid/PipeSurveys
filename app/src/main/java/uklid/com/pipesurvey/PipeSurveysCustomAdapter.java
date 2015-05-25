package uklid.com.pipesurvey;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Uklid on 5/25/2015.
 */
public class PipeSurveysCustomAdapter extends ArrayAdapter<PipeSurvey>{
    private LayoutInflater mLayoutInflater;
    private static FragmentManager sFragmentManager;

    public PipeSurveysCustomAdapter(Context context, FragmentManager fragmentManager) {
        super(context, android.R.layout.simple_list_item_2);
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        sFragmentManager = fragmentManager;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view;
        if(convertView == null) {
            view = mLayoutInflater.inflate(R.layout.custom_pipesurveys,parent,false);
        } else {
            view = convertView;
        }
        final PipeSurvey pipeSurvey = getItem(position);
        final int _id = pipeSurvey.getId();
        final String pipecode = pipeSurvey.getPipecode();
        final String code = pipeSurvey.getCode();
        final String utilization = pipeSurvey.getUtilization();

        ((TextView) view.findViewById(R.id.pipesurvey_pipecode)).setText(pipecode);
        ((TextView) view.findViewById(R.id.pipesurvey_code)).setText(code);
        ((TextView) view.findViewById(R.id.pipesurvey_utilization)).setText(utilization);

        Button editButton = (Button) view.findViewById(R.id.edit);
       /* editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditActivity.class);
                intent.putExtra(FriendsContract.FriendsColumns.FRIENDS_ID, String.valueOf(_id));
                intent.putExtra(FriendsContract.FriendsColumns.FRIENDS_NAME, String.valueOf(name));
                intent.putExtra(FriendsContract.FriendsColumns.FRIENDS_EMAIL, String.valueOf(email));
                intent.putExtra(FriendsContract.FriendsColumns.FRIENDS_PHONE, String.valueOf(phone));
                getContext().startActivity(intent);
            }
        });*/

        Button deleteButton = (Button) view.findViewById(R.id.delete);
        /*deleteButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                FriendsDialog dialog = new FriendsDialog();
                                                Bundle args = new Bundle();
                                                args.putString(FriendsDialog.DIALOG_TYPE, FriendsDialog.DELETE_RECORD);
                                                args.putInt(FriendsContract.FriendsColumns.FRIENDS_ID, _id);
                                                args.putString(FriendsContract.FriendsColumns.FRIENDS_NAME, name);
                                                dialog.setArguments(args);
                                                dialog.show(sFragmentManager, "delete-record");
                                            }
                                        }
        );*/
        return view;
    }
    public void setData(List<PipeSurvey> pipesurveys) {
        clear();
        if(pipesurveys != null) {
            for(PipeSurvey pipesurvey : pipesurveys) {
                add(pipesurvey);
            }
        }
    }
}
