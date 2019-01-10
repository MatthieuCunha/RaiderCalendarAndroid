package raidercalendar.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class playerAdaptater  extends ArrayAdapter<EventStatus> {

    int resource;
    String response;
    Context context;
    //Initialize adapter
    public playerAdaptater(Context context, int resource, List<EventStatus> items) {
        super(context, resource, items);
        this.resource=resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LinearLayout playerView;
        //current eventStatus item
        EventStatus eventStatus = getItem(position);

        //Inflate the view
        if(convertView==null)
        {
            playerView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi;
            vi = (LayoutInflater)getContext().getSystemService(inflater);
            vi.inflate(resource, playerView, true);
        }
        else
        {
            playerView = (LinearLayout) convertView;
        }
        //textBox
        TextView playerName=(TextView)playerView.findViewById(R.id.playerName);
        TextView playerRole =(TextView)playerView.findViewById(R.id.playerRole);


        Long playerId=eventStatus.getPlayerID();
        User user = User.findById(User.class,playerId);
        String name = user.getName();

        //Assign value name and role
        playerName.setText(name);
        playerRole.setText(eventStatus.getRole());

        return playerView;
    }

}

