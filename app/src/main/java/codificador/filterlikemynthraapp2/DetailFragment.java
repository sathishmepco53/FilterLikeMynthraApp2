package codificador.filterlikemynthraapp2;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Seng on 8/16/2017.
 */

public class DetailFragment extends ListFragment implements AdapterView.OnItemClickListener{

    CustomAdapter adapter = null;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment, container, false);
        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        Toast.makeText(getActivity(), ""+i, Toast.LENGTH_SHORT).show();
        adapter.setChecked(i);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getListView().setOnItemClickListener(this);
    }

    public void setAdapter(String[] array){
        adapter= new CustomAdapter(getActivity(),array);
        setListAdapter(adapter);
    }

    public ArrayList<Integer> getSelectedList(){
        if(adapter == null)
            return new ArrayList<>();
        return adapter.getSelectedList();
    }

    public void setSelectedList(ArrayList<Integer> list){
        adapter.setSelectedList(list);
        adapter.notifyDataSetChanged();
    }
}

class CustomAdapter extends BaseAdapter{

    String array[];
    boolean selectedList[];
    Context context;
    LayoutInflater layoutInflater;


    public CustomAdapter(Context context,String[] array){
        this.context = context;
        this.array = array;
        layoutInflater = LayoutInflater.from(context);
        selectedList = new boolean[array.length];
    }

    public void setChecked(int pos){
        selectedList[pos] = !selectedList[pos];
    }

    public ArrayList<Integer> getSelectedList(){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<selectedList.length;i++)
            if(selectedList[i] == true)
                list.add(i);
        return list;
    }

    @Override
    public int getCount() {
        return array.length;
    }

    @Override
    public Object getItem(int i) {
        return array[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            view = layoutInflater.inflate(R.layout.detail_list_item_row,viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.checkBox = view.findViewById(R.id.checkbox);
            view.setTag(viewHolder);
            viewHolder.checkBox.setTag(i);
            viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = (int) view.getTag();
                    selectedList[pos] = !selectedList[pos];
                }
            });
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.checkBox.setText(array[i]);;
        viewHolder.checkBox.setChecked(selectedList[i]);
        return view;
    }

    class ViewHolder{
        CheckBox checkBox;
    }

    public void setSelectedList(ArrayList<Integer> list){
        for(int i=0;i<list.size();i++)
            selectedList[list.get(i)] = true;
    }
}