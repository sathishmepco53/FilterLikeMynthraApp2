package codificador.filterlikemynthraapp2;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Seng on 8/16/2017.
 */

public class TitlesFragment extends ListFragment implements AdapterView.OnItemClickListener{

    HashMap<Integer,ArrayList<Integer>> map;
    int selectedIndex = 0;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        map = new HashMap<>();
        View view = inflater.inflate(R.layout.titles_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.mainList, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);

        if(getListView().getAdapter().getCount()>0){
//            DetailFragment fragment = (DetailFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.detailFragment);
//            fragment.setText(getListView().getAdapter().getItem(0).toString());
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
//        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
//        DetailFragment fragment = (DetailFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.detailFragment);
//        fragment.setText(parent.getAdapter().getItem(position).toString());

        DetailFragment detailFragment = (DetailFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.detailFragment);
        map.put(selectedIndex,detailFragment.getSelectedList());
        detailFragment.setAdapter(getStringArray(position));
        selectedIndex = position;
        if(map.containsKey(position))
            detailFragment.setSelectedList(map.get(position));
    }

    private String[] getStringArray(int position){
        switch (position){
            case 0:
                return getResources().getStringArray(R.array.Planets);
            case 1:
                return getResources().getStringArray(R.array.countries);
            case 2:
                return getResources().getStringArray(R.array.states);
            case 3:
                return getResources().getStringArray(R.array.cities);
            default:
                return new String[0];
        }
    }
}