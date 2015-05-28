package uklid.com.pipesurvey;

import android.content.ContentResolver;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import java.util.List;

/**
 * Created by Uklid on 5/27/2015.
 */
public class PipeSurveysListFragment extends ListFragment
implements LoaderManager.LoaderCallbacks<List<PipeSurvey>>
{

    private static final String LOG_TAG = PipeSurveysListFragment.class.getSimpleName();
    private PipeSurveysCustomAdapter mAdapter;
    private static final int LOADER_ID = 1;
    private ContentResolver mContentResolver;
    private List<PipeSurvey> mPipeSurveys;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        mContentResolver = getActivity().getContentResolver();
        mAdapter = new PipeSurveysCustomAdapter(getActivity(), getChildFragmentManager());
        setEmptyText("No Surveys found");
        setListAdapter(mAdapter);
        setListShown(false);

        getLoaderManager().initLoader(LOADER_ID,null,this);
    }

    @Override
    public Loader<List<PipeSurvey>> onCreateLoader(int id, Bundle args) {
        mContentResolver = getActivity().getContentResolver();
        return new PipeSurveysListLoader(getActivity(), PipeSurveysContract.URI_TABLE,mContentResolver);
    }

    @Override
    public void onLoadFinished(Loader<List<PipeSurvey>> loader, List<PipeSurvey> pipeSurveys) {
        mAdapter.setData(pipeSurveys);
        mPipeSurveys = pipeSurveys;

        if(isResumed()){
            setListShown(true);
        }else{
            setListShownNoAnimation(true);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<PipeSurvey>> loader) {
        mAdapter.setData(null);
    }
}
