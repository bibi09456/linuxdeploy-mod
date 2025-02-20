package app.linuxdeploy.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import app.linuxdeploy.PrefStore;
import app.linuxdeploy.R;
import app.linuxdeploy.fragment.SettingsFragment;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PrefStore.setLocale(this);
        setContentView(R.layout.activity_preference);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, new SettingsFragment())
                .commit();

        // Restore from conf file
        PrefStore.restoreSettings(this);
    }

    @Override
    public void setTheme(int resId) {
        super.setTheme(PrefStore.getTheme(this));
    }

    @Override
    public void onResume() {
        super.onResume();

        setTitle(R.string.title_activity_settings);
    }

    @Override
    public void onPause() {
        super.onPause();

        // update configuration file
        PrefStore.dumpSettings(this);
    }
}
