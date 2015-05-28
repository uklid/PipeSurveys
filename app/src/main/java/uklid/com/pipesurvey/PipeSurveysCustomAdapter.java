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
public class PipeSurveysCustomAdapter extends ArrayAdapter<PipeSurvey> {
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
        if (convertView == null) {
            view = mLayoutInflater.inflate(R.layout.custom_pipesurveys, parent, false);
        } else {
            view = convertView;
        }
        final PipeSurvey pipeSurvey = getItem(position);
        final int _id = pipeSurvey.getId();
        final String pipecode = pipeSurvey.getPipecode();
        final String code = pipeSurvey.getCode();
        final String utilization = pipeSurvey.getUtilization();

        final String sheetno = pipeSurvey.getSheetno();
        final String date = pipeSurvey.getDate();
        final String order = pipeSurvey.getOrder();
        final String type = pipeSurvey.getType();
        final String name = pipeSurvey.getName();
        final String no = pipeSurvey.getNo();
        final String moo = pipeSurvey.getMoo();
        final String soi = pipeSurvey.getSoi();
        final String road = pipeSurvey.getRoad();
        final String subdistrict = pipeSurvey.getSubdistrict();
        final String district = pipeSurvey.getDistrict();
        final String province = pipeSurvey.getProvince();
        final String po = pipeSurvey.getPo();
        final String telephone = pipeSurvey.getTelephone();
        final int noccupants = pipeSurvey.getNoccupants();
        final String foccupants = pipeSurvey.getFoccupants();
        final int nstorey = pipeSurvey.getNstorey();
        final int nroom = pipeSurvey.getNroom();
        final String material = pipeSurvey.getMaterial();
        final String construction = pipeSurvey.getConstruction();
        final String picturename = pipeSurvey.getPicturename();


        ((TextView) view.findViewById(R.id.pipesurvey_pipecode)).setText( pipecode);
        ((TextView) view.findViewById(R.id.pipesurvey_code)).setText(code);
        ((TextView) view.findViewById(R.id.pipesurvey_utilization)).setText( utilization);

        Button editButton = (Button) view.findViewById(R.id.edit);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditActivity.class);


                intent.putExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_ID, String.valueOf(_id));
                intent.putExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_PIPECODE, String.valueOf(pipecode));
                intent.putExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_SHEETNO, String.valueOf(sheetno));
                intent.putExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEY_DATE, String.valueOf(date));
                intent.putExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_CODE, String.valueOf(code));
                intent.putExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_ORDER, String.valueOf(order));
                intent.putExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_UTILIZATION, String.valueOf(utilization));
                intent.putExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_TYPE, String.valueOf(type));
                intent.putExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_NAME, String.valueOf(name));
                intent.putExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_NO, String.valueOf(no));
                intent.putExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_SOI, String.valueOf(soi));
                intent.putExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_ROAD, String.valueOf(road));
                intent.putExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_MOO, String.valueOf(moo));
                intent.putExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_SUBDISTRICT, String.valueOf(subdistrict));
                intent.putExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_DISTRICT, String.valueOf(district));
                intent.putExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_PROVINCE, String.valueOf(province));
                intent.putExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_PO, String.valueOf(po));
                intent.putExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_TELEPHONE, String.valueOf(telephone));
                intent.putExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_NROOMS, String.valueOf(nroom));
                intent.putExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_NOCCUPANTS, String.valueOf(noccupants));
                intent.putExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_NSTOREY, String.valueOf(nstorey))
                ;
                intent.putExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_MATERIAL, String.valueOf(material));
                intent.putExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_FOCCUPANTS, String.valueOf(foccupants));
                intent.putExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_CONSTRUCTION, String.valueOf(construction));
                intent.putExtra(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_PICTURENAME, String.valueOf(picturename));


                getContext().startActivity(intent);
            }
        });

        Button deleteButton = (Button) view.findViewById(R.id.delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                PipeSurveysDialog dialog = new PipeSurveysDialog();
                                                Bundle args = new Bundle();
                                                args.putString(PipeSurveysDialog.DIALOG_TYPE, PipeSurveysDialog.DELETE_RECORD);
                                                args.putInt(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_ID, _id);
                                                args.putString(PipeSurveysContract.PipeSurveysColumns.PIPESURVEYS_CODE, code);
                                                dialog.setArguments(args);
                                                dialog.show(sFragmentManager, "delete-record");
                                            }
                                        }
        );
        return view;
    }

    public void setData(List<PipeSurvey> pipesurveys) {
        clear();
        if (pipesurveys != null) {
            for (PipeSurvey pipesurvey : pipesurveys) {
                add(pipesurvey);
            }
        }
    }
}
