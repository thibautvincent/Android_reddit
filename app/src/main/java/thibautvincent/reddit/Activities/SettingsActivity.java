package thibautvincent.reddit.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import thibautvincent.reddit.R;

public class SettingsActivity extends AppCompatActivity {

    @Bind(R.id.btn_settings_save)
    public Button btnSave;

    @Bind(R.id.etMaxListingsLoading)
    public EditText etNumberOfPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ButterKnife.bind(this);

        SharedPreferences sp = getSharedPreferences("MY_PREFS", Activity.MODE_PRIVATE);
        int max = sp.getInt("POSTS_LOAD", 20);
        etNumberOfPosts.setText(String.valueOf(max));
    }

    @OnClick(R.id.btn_settings_save)
    public void save() {
        SharedPreferences mySharedPreferences = getSharedPreferences("MY_PREFS", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putInt("POSTS_LOAD", Integer.parseInt(etNumberOfPosts.getText().toString()));
        editor.apply();

        finish();
    }
}
