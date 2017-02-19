package group9.hsr.ch.businesstravelagent.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import ch.schoeb.opendatatransport.model.Connection;
import ch.schoeb.opendatatransport.model.ConnectionList;
import group9.hsr.ch.businesstravelagent.R;

public class TransportAdapter extends BaseAdapter {

    private Context context;
    private ConnectionList connections;

    public  TransportAdapter(Context context, ConnectionList connections){
        this.context = context;
        this.connections = connections;
    }

    @Override
    public int getCount() {
        return connections.getConnections().size();
    }

    @Override
    public Object getItem(int i) {
        return connections.getConnections().get(i);
    }

    @Override
    public long getItemId(int i) {
        return connections.getConnections().get(i).hashCode();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_transport_item, parent, false);
        }

        Connection connection = (Connection)getItem(position);
        TextView startTimeText = (TextView)view.findViewById(R.id.startTimeText);
        startTimeText.setText(connection.getDuration());

        return view;
    }
}
