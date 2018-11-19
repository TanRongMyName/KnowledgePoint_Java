package intentcompany.tanrong.com.knowledgepointset;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import intentcompany.tanrong.com.knowledgepointset.MVPPage.LowUser.MVPLowerActivity;
import intentcompany.tanrong.com.knowledgepointset.MVPPage.NormalUse.MVPNormalUseActivity;
import intentcompany.tanrong.com.knowledgepointset.Utils.LogUtils;

public class MVPModelACtivity extends AppCompatActivity implements View.OnClickListener{
    Button lowuse,normaluse,superuse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvpmodel);
        LogUtils.v("MVPModelACtivity-----------");
        lowuse=(Button)findViewById(R.id.lowuse);
        normaluse=findViewById(R.id.nomaluse);
        superuse=findViewById(R.id.superuse);

        lowuse.setOnClickListener(this);
        normaluse.setOnClickListener(this);
        superuse.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
          Intent intent=null;
          switch(view.getId()){
              case R.id.lowuse:
                  intent=new Intent(this, MVPLowerActivity.class);
                  break;
              case R.id.nomaluse:
                  intent=new Intent(this, MVPNormalUseActivity.class);
                  break;
              case R.id.superuse:
                  break;
              default:
                  break;
          }
          if(intent!=null){
              startActivity(intent);
          }
    }
}
