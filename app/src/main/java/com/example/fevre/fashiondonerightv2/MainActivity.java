package com.example.fevre.fashiondonerightv2;

import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x; // int screenWidth = display.getWidth(); on API < 13
        int screenHeight = size.y;

        FloatingActionButton hatButton = createFab(Math.round(0.5F * screenWidth), 0);

        final MainActivity mainActivity = this;

        TextView brandSearch = (TextView) findViewById(R.id.brand_search);
        brandSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getSupportFragmentManager();
                SearchDialogFragment searchDialogFragment = new SearchDialogFragment();
                searchDialogFragment.show(manager, "searchdialog");
            }
        });

        hatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FrameLayout selectorMenu = (FrameLayout) findViewById(R.id.selector_menu);

                ResizeWidthAnimation anim = new ResizeWidthAnimation(selectorMenu, 900);
                anim.setDuration(500);
                anim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Spinner spinner = (Spinner) findViewById(R.id.type_spinner);
                        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mainActivity,
                                R.array.type_array, android.R.layout.simple_spinner_item);

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        spinner.setAdapter(adapter);


                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                selectorMenu.startAnimation(anim);

            }
        });











        RelativeLayout buttonMap = (RelativeLayout) findViewById(R.id.buttonMap);
        buttonMap.addView(hatButton);
        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                .setAction("Action", null).show();
        //    }
        //});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private FloatingActionButton createFab(int x,int y){
        FloatingActionButton floatingActionButton = new FloatingActionButton(this);
        floatingActionButton.setX(x);
        floatingActionButton.setY(y);

        return floatingActionButton;
    }

    public class ResizeWidthAnimation extends Animation
    {
        private int mWidth;
        private int mStartWidth;
        private View mView;

        public ResizeWidthAnimation(View view, int width)
        {
            mView = view;
            mWidth = width;
            mStartWidth = view.getWidth();
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t)
        {
            int newWidth = mStartWidth + (int) ((mWidth - mStartWidth) * interpolatedTime);

            mView.getLayoutParams().width = newWidth;
            mView.requestLayout();
        }

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight)
        {
            super.initialize(width, height, parentWidth, parentHeight);
        }

        @Override
        public boolean willChangeBounds()
        {
            return true;
        }
    }

    public class SearchDialogFragment extends DialogFragment {
        private SearchDialogFragment dialog = this;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.searchdialog, container,
                    false);
            getDialog().setTitle("DialogFragment Tutorial");



            EditText searchEditText = (EditText) rootView.findViewById(R.id.search_editText);
            searchEditText.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    if(keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                        Log.d("JRJ", "Start search");

                        ListView listView = (ListView) rootView.findViewById(R.id.search_result_list);
                        BrandSearchTask bst = new BrandSearchTask();
                        bst.prepare(((EditText) view).getText().toString(),
                                listView, dialog);
                        bst.execute();
                    }
                    return true;
                }
            });
            // Do something else
            return rootView;
        }
    }

    class BrandSearchTask extends AsyncTask<String, String, String> {
        private String searchText;
        private ListView listView;
        private SearchDialogFragment sdf;

        public void prepare(String searchText, ListView listView, SearchDialogFragment sdf){
            this.searchText = searchText;
            this.listView = listView;
            this.sdf = sdf;
        }
        @Override
        protected String doInBackground(String... params) {
            String str = null;
            URL url = null;
            try {
                url = new URL("https://mrjakob.dk/test/brandSearch.php?s=" + searchText);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection urlConnection = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                str = getResult(in);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }
            return str;

        }

        @Override
        protected void onPostExecute(String result) {
            try {

                Log.d("JRJ",result);
                JSONArray json = new JSONArray(result);

                ArrayList<String> items = new ArrayList<String>();
                for(int i=0; i < json.length() ; i++) {
                    JSONObject json_data = json.getJSONObject(i);
                    //int id=json_data.getInt("id");
                    String name=json_data.getString("name");
                    items.add(name);
                    Log.d(name,"Output");
                }
                final ArrayAdapter adapter = new ArrayAdapter<String>(getBaseContext(),
                        android.R.layout.simple_list_item_1, android.R.id.text1, items);

                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        ((TextView) findViewById(R.id.brand_search)).setText((String) adapterView.getItemAtPosition(i));
                        sdf.dismiss();
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        private String getResult(InputStream in) {
            String str = convertStreamToString(in);
            return str;
        }

        private String convertStreamToString(InputStream is) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            String line ="";
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return sb.toString();
        }
    }
}
