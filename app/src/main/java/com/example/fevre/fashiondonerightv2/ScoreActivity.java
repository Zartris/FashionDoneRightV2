package com.example.fevre.fashiondonerightv2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        SmartFab score = (SmartFab) findViewById(R.id.total_score);
        double totalScore = 0;
        int countScore = 0;
        for(ClothItem item: MainActivity.clothList.getValues()){
            countScore++;
            switch (item.getBrand().getScore()) {
                case "a": totalScore+=5;
                    break;
                case "b": totalScore+=4;
                    break;
                case "c": totalScore+=3;
                    break;
                case "d": totalScore+=2;
                    break;
                case "e": totalScore+=1;
                    break;
            }
        }

        switch ((int) Math.round(totalScore/countScore)) {
            case 5: score.setImageResource(R.drawable.ic_score_a);
                break;
            case 4: score.setImageResource(R.drawable.ic_score_b);
                break;
            case 3: score.setImageResource(R.drawable.ic_score_c);
                break;
            case 2: score.setImageResource(R.drawable.ic_score_d);
                break;
            case 1: score.setImageResource(R.drawable.ic_score_e);
                break;
        }


        ListView listview = (ListView) findViewById(R.id.score_listView);
        listview.setAdapter(new ScoreAdapter(this, MainActivity.clothList.getValues()));

    }

    static class ScoreAdapter extends BaseAdapter {

        Context context;
        ArrayList<ClothItem> data;
        private static LayoutInflater inflater = null;

        public ScoreAdapter(Context context, ArrayList<ClothItem> data) {
            this.context = context;
            this.data = data;
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View vi = convertView;
            if (vi == null)
                vi = inflater.inflate(R.layout.score_list_item, null);
            TextView itemTitle = (TextView) vi.findViewById(R.id.item_title);
            itemTitle.setText(data.get(position).getType().toString());
            TextView itemBrand = (TextView) vi.findViewById(R.id.item_brand);
            itemBrand.setText(data.get(position).getBrand().getName());
            TextView itemMaterial = (TextView) vi.findViewById(R.id.item_material);
            itemMaterial.setText(data.get(position).getMaterial().toString());
            TextView itemUsage = (TextView) vi.findViewById(R.id.item_usage);
            itemUsage.setText(data.get(position).getMaterial().toString());
            SmartFab itemScore = (SmartFab) vi.findViewById(R.id.item_score);
            switch (data.get(position).getBrand().getScore()) {
                case "a": itemScore.setImageResource(R.drawable.ic_score_a);
                    break;
                case "b": itemScore.setImageResource(R.drawable.ic_score_b);
                    break;
                case "c": itemScore.setImageResource(R.drawable.ic_score_c);
                    break;
                case "d": itemScore.setImageResource(R.drawable.ic_score_d);
                    break;
                case "e": itemScore.setImageResource(R.drawable.ic_score_e);
                    break;
            }
            return vi;
        }
    }
}
