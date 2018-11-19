package intentcompany.tanrong.com.knowledgepointset;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ScreenAdapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_adapter);
    }
    public void onBackUp(View view){
        finish();
    }
}
