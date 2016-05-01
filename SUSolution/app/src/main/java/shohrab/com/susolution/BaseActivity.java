package shohrab.com.susolution;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Shohrab on 4/8/2016.
 */
public abstract class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent();
    }

    protected abstract void setupActivityComponent();

}
