package com.baibian.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baibian.R;
import com.baibian.tool.LinearLayout_Inflaterable;
import com.baibian.tool.UI_Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * �༭������Ϣ��activity
 */

public class Edit_Information_Activity extends Activity implements View.OnClickListener{
    private ImageView edit_information_back;//������Ϣ���ؼ�
    private LinearLayout edit_information_all_layout;//ȫ����
    private List<String> data;//��������Դ
    private EditText user_name_edittext;
    private int i ;
    private LinearLayout house_LinearLayout;
    private LinearLayout advantage_LinearLayout;
    private View add_house_layout;
    private View add_advantage_layout;
    LinearLayout_Inflaterable house_linearlayout_inflaterable;
    LinearLayout_Inflaterable advantage_linearlayout_inflaterable;
    private TextView complete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.edit_informationlayout);
        initview();

        data = new ArrayList<String>();
//        for (int i = 0; i < 5; i++) {
//            int index = i + 1;
//            data.add("����" + index);
//        }

        house_linearlayout_inflaterable=new LinearLayout_Inflaterable(this,house_LinearLayout,add_house_layout,data,10
        );
        house_linearlayout_inflaterable.initlayout();
        advantage_linearlayout_inflaterable=new LinearLayout_Inflaterable(this,advantage_LinearLayout,add_advantage_layout,data,10);
        advantage_linearlayout_inflaterable.initlayout();
        UI_Tools ui_tools = new UI_Tools();
        ui_tools.CancelFocusOne(this, edit_information_all_layout, user_name_edittext);
        edit_information_back.setOnClickListener(this);
        complete.setOnClickListener(this);

    }

    private void initview(){
        edit_information_back=(ImageView) findViewById(R.id.edit_information_back);
        edit_information_all_layout=(LinearLayout) findViewById(R.id.edit_information_all_layout);
        user_name_edittext=(EditText) findViewById(R.id.user_name_edittext);
        house_LinearLayout=(LinearLayout) findViewById(R.id.house_LinearLayout);
        add_house_layout=(View) findViewById(R.id.add_house_layout);
        add_advantage_layout=(View)findViewById(R.id.add_advantage_layout);
        advantage_LinearLayout=(LinearLayout) findViewById(R.id.advantage_LinearLayout);
        complete=(TextView) findViewById(R.id.complete);//���textview
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_information_back://?????????
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
            case R.id.complete:
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
            default:
                break;
        }
    }

}
