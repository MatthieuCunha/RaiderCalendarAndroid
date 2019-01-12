package raidercalendar.android;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class spinnerAdapter extends ArrayAdapter<Groupe> {

    int resource;
    String response;
    Context context;
    //Initialize adapter
    public spinnerAdapter(Context context, int resource, List<Groupe> items) {
        super(context, resource, items);
        this.resource=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LinearLayout groupView;
        //current eventStatus item
        Groupe groupItem = getItem(position);

        //Inflate the view
        if(convertView==null)
        {
            groupView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi;
            vi = (LayoutInflater)getContext().getSystemService(inflater);
            vi.inflate(resource, groupView, true);
        }
        else
        {
            groupView = (LinearLayout) convertView;
        }
        //textBox
        TextView groupName=(TextView)groupView.findViewById(R.id.spinnerGroupeName);


        //Assign value name and role
        groupName.setText(groupItem.getName());

        return groupView;
    }



}
