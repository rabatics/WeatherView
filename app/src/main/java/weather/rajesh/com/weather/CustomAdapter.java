
package weather.rajesh.com.weather;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class CustomAdapter extends ArrayAdapter {


    private String[] objects;



    public CustomAdapter(Context context, int resource, String[] objects) {
        super(context, resource, objects);
        this.objects = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

       // RecyclerView.ViewHolder viewHolder = null;
        String listViewItem = objects[position];
    //    int listViewItemType = getItemViewType(position);
        List<String> l= Arrays.asList(listViewItem.split(";"));
    ViewHolder vh;
        for(int i=0;i<l.size();i++){
            Log.v("condnlist",":"+l.get(i));
        }
     //   String user=((NavDrawer) getContext()).getUsername();
        if (convertView == null) {
            vh=new ViewHolder();
            String condition=l.get(2);
            Log.v("condn",":"+condition);
         //   System.out.println(user + ":" + l.get(0));
            if ( condition.contains("clear")) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.sunny, null);
                vh.mText= (TextView)convertView.findViewById(R.id.textsun);

            } else if(condition.contains("partlycloudy")) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.partlycloudy, null);
                vh.mText= (TextView)convertView.findViewById(R.id.textpc);
            }
            else if(condition.contains("mostlycloudy")) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.mostlycloudy, null);
                vh.mText= (TextView)convertView.findViewById(R.id.textmc);
            }
            else if(condition.contains("storm")) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.thunderstorm, null);
                vh.mText= (TextView)convertView.findViewById(R.id.textstorm);
            }
            else if(condition.contains("snow")) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.snow, null);
                vh.mText= (TextView)convertView.findViewById(R.id.textsnow);
            }
            else{
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.partlycloudy, null);
                vh.mText= (TextView)convertView.findViewById(R.id.textpc);
            }

            convertView.setTag(vh);

        }
        else{
            vh = (ViewHolder) convertView.getTag();
            String condition=l.get(2);
            //   System.out.println(user + ":" + l.get(0));
            if ( condition.contains("clear")) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.sunny, null);
                vh.mText= (TextView)convertView.findViewById(R.id.textsun);

            } else if(condition.contains("partlycloudy")) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.partlycloudy, null);
                vh.mText= (TextView)convertView.findViewById(R.id.textpc);
            }
            else if(condition.contains("mostlycloudy")) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.mostlycloudy, null);
                vh.mText= (TextView)convertView.findViewById(R.id.textmc);
            }
            else if(condition.contains("storm")) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.thunderstorm, null);
                vh.mText= (TextView)convertView.findViewById(R.id.textstorm);
            }
            else if(condition.contains("snow")) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.snow, null);
                vh.mText= (TextView)convertView.findViewById(R.id.textsnow);
            }
            else{
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.partlycloudy, null);
                vh.mText= (TextView)convertView.findViewById(R.id.textpc);
            }
            convertView.setTag(vh);
           // convertView.setTag(vh);
        }


        vh.mText.setText(l.get(0)+"  "+l.get(1));




        return convertView;
    }



    class ViewHolder{
        TextView mText;
        ImageView wimg;

    }
}
