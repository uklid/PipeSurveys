package uklid.com.pipesurvey;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Uklid on 5/25/2015.
 */
public class PipeSurveysContract {
    interface PipeSurveysColumns {
        String PIPESURVEYS_ID = "_id";
        String PIPESURVEYS_PIPECODE = "pipesurveys_pipecode";
        String PIPESURVEYS_SHEETNO = "pipesurveys_sheetno";
        String PIPESURVEY_DATE = "pipesurveys_date";
        String PIPESURVEYS_CODE = "pipesurveys_code";
        String PIPESURVEYS_ORDER = "pipesurveys_order";
        String PIPESURVEYS_UTILIZATION = "pipesurveys_utilization";
        String PIPESURVEYS_TYPE = "pipesurveys_type";
        String PIPESURVEYS_NAME = "pipesurveys_name";
        String PIPESURVEYS_NO = "pipesurveys_no";
        String PIPESURVEYS_MOO = "pipesurveys_moo";
        String PIPESURVEYS_SOI = "pipesurveys_soi";
        String PIPESURVEYS_ROAD = "pipesurveys_road";
        String PIPESURVEYS_SUBDISTRICT = "pipesurveys_subdistrict";
        String PIPESURVEYS_DISTRICT = "pipesurveys_district";
        String PIPESURVEYS_PROVINCE = "pipesurveys_province";
        String PIPESURVEYS_PO = "pipesurveys_po";
        String PIPESURVEYS_TELEPHONE = "pipesurveys_telephone";
        String PIPESURVEYS_NROOMS = "pipesurveys_nrooms";
        String PIPESURVEYS_NOCCUPANTS = "pipesurveys_noccupants";
        String PIPESURVEYS_FOCCUPANTS = "pipesurveys_foccupants";
        String PIPESURVEYS_NSTOREY = "pipesurveys_nstorey";
        String PIPESURVEYS_MATERIAL = "pipesurveys_material";
        String PIPESURVEYS_CONSTRUCTION = "pipesurveys_construction";
        String PIPESURVEYS_PICTURENAME = "pipesurveys_picturename";
    }

    public static final String CONTENT_AUTHORITY = "uklid.com.pipesurveys.provider"; //high level provider
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    private static final String PATH_PIPESURVEYS = "pipesurveys";

    public static final Uri URI_TABLE = Uri.parse(BASE_CONTENT_URI.toString() + "/" + PATH_PIPESURVEYS);


    public static final String[] TOP_LEVEL_PATHS = {
            PATH_PIPESURVEYS
    };

    public static class PipeSurveys implements PipeSurveysColumns, BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendEncodedPath(PATH_PIPESURVEYS).build(); //access to the contract provider
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd" + CONTENT_AUTHORITY + ".pipesurveys"; //access morethan 1 data
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd" + CONTENT_AUTHORITY + ".pipesurveys";

        public static Uri buildPipeSurveyUri(String pipeSurveyId) {
            return CONTENT_URI.buildUpon().appendEncodedPath(pipeSurveyId).build();
        }

        public static String getPipeSurveyId(Uri uri) {
            return uri.getPathSegments().get(1);
        }
    }
}
