package weather.android.example.com.weather_application;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.xml.sax.ErrorHandler;

import butterknife.BindView;
import butterknife.ButterKnife;
import weather.android.example.com.weather_application.Fragments.Weather_view;

import weather.android.example.com.weather_application.Services.NetworkTest;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.zipcode) EditText zipCode;
    @BindView(R.id.btn_submit) Button submitButton;

    private Snackbar mySnackbar;

    public static final String THE_ZIPCODE = "The_ZipCode";
    private boolean snackBarIsShow = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setListeners();

        checkConnection();

    }

    // Check connection before submitting zip code.
    public void checkConnection() {
        if (NetworkTest.isNetworkAvailable(getApplicationContext())) {
            if (zipCode.getText().toString().length() > 4) {
                submitButton.setEnabled(true);
            }
        }
        else {
            mySnackbar = Snackbar.make(findViewById(R.id.search_layout),
                    R.string.sbar_noconnect, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.sbar_tryagain, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            checkConnection();
                        }
                    });

            mySnackbar.show();

            snackBarIsShow = true;
        }
    }

    // zipCode.addTextChangedListener -- Verify if user has entered 5 numbers for zip code.
    // submitButton.setOnClickListener -- When enabled, submit zip code to the next screen.
    public void setListeners() {

        zipCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (ValidateZip.isAValidZipCode(zipCode.getText().toString()) &&
                        NetworkTest.isNetworkAvailable(getApplicationContext())) {
                    submitButton.setEnabled(true);
                    if (snackBarIsShow) {
                        mySnackbar.dismiss();

                        snackBarIsShow = false;
                    }
                }
                else {
                    if (ValidateZip.isAValidZipCode(zipCode.getText().toString()) && !snackBarIsShow) {
                        checkConnection();
                    }

                    submitButton.setEnabled(false);
                }
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkConnection();

               if (NetworkTest.isNetworkAvailable(getApplicationContext())) {
                    InputMethodManager inputManager =
                            (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow((null == getCurrentFocus())
                                    ? null : getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);

                    android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    Fragment fragment;
                    Bundle bundle = new Bundle();
                    bundle.putString("ZIPCODE", String.valueOf(zipCode.getText()));

                    fragment = new Weather_view();
                    fragment.setArguments(bundle);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.frame, fragment);
                    fragmentTransaction.commit();
                }
                else {

                   submitButton.setEnabled(false);
                }
            }
        });
    }


}