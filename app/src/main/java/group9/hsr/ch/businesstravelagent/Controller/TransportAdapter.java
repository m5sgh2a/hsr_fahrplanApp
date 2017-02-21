package group9.hsr.ch.businesstravelagent.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ch.schoeb.opendatatransport.model.Connection;
import ch.schoeb.opendatatransport.model.ConnectionList;
import group9.hsr.ch.businesstravelagent.Model.HelperDate;
import group9.hsr.ch.businesstravelagent.R;

public class TransportAdapter extends BaseAdapter {

    private Context context;
    private ConnectionList connections;
    private HelperDate helperDate = new HelperDate();

    public TransportAdapter(Context context, ConnectionList connections) {
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

        Connection connection = (Connection) getItem(position);

        SetConnectionIdText(position, view);
        SetStartTimeText(connection, view);
        SetStartLocationText(connection, view);
        SetEndLocationText(connection, view);
        SetProductText(connection, view);

        return view;
    }

    private void SetConnectionIdText(int position, View view) {
        TextView connectionId = (TextView) view.findViewById(R.id.connectionId);
        connectionId.setText((position + 1) + "");
    }

    private void SetStartTimeText(Connection connection, View view) {
        TextView startTime = (TextView) view.findViewById(R.id.startTimeText);
        startTime.setText(connection.getFrom().getDeparture());
    }

    private void SetStartLocationText(Connection connection, View view) {
        TextView startLocation = (TextView) view.findViewById(R.id.startLocationText);
        startLocation.setText(connection.getFrom().getLocation().getName().toString());
    }

    private void SetEndLocationText(Connection connection, View view) {
        TextView endLocation = (TextView) view.findViewById(R.id.endLocationText);
        endLocation.setText(connection.getTo().getLocation().getName().toString());
    }

    private void SetProductText(Connection connection, View view) {
        TextView productClass = (TextView) view.findViewById(R.id.productText);
        productClass.setText(ConcatAllProducts(connection.getProducts()));
    }

    private String ConcatAllProducts(List products) {
        String result = "";

        for (int i = 0; i < products.size(); i++) {

            if (i != 0) {
                result += ", ";
            }

            result += products.get(i).toString();
        }

        return result;
    }
}
