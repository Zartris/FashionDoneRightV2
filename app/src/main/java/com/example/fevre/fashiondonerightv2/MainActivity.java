package com.example.fevre.fashiondonerightv2;

import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

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

    public class SearchDialogFragment extends DialogFragment implements TextView.OnEditorActionListener {

        private EditText mEditText;


        // Empty constructor required for DialogFragment
        public SearchDialogFragment() {}

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.searchdialog, container);
            mEditText = (EditText) view.findViewById(R.id.search_editText);

            // set this instance as callback for editor action
            mEditText.setOnEditorActionListener(this);
            mEditText.requestFocus();
            getDialog().getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            getDialog().setTitle("Please enter username");

            return view;
        }

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            // Return input text to activity
            /*UserNameListener activity = (UserNameListener) getActivity();
            activity.onFinishUserDialog(mEditText.getText().toString());
            this.dismiss();
            return true;*/
            return true;
        }
    }
}
