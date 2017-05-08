package com.baibian.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.baibian.R;
import com.baibian.activity.login.Login4Activity;
import com.baibian.adapter.NewsFragmentPagerAdapter;
import com.baibian.bean.ChannelItem;
import com.baibian.fragment.main.FindFragment;
import com.baibian.fragment.main.ForumsFragment;
import com.baibian.fragment.main.HomepageFragment;
import com.baibian.fragment.main.PeriodicalsFragment;
import com.baibian.tool.BaseTools;
import com.baibian.view.ColumnHorizontalScrollView;
import com.baibian.view.SlidingDrawerView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;

/**
 *  ģ�⻹ԭ����ͷ�� --�����Ķ���
 * author:XZY && RA
 */
public class MainActivity extends FragmentActivity implements OnClickListener{

    /**
     * �ĸ���Ƭ���������ı���
     */
    private HomepageFragment homepageFragment;//��һ����Ƭ
    private ForumsFragment forumsFragment;//�ڶ�����Ƭ
    private FindFragment findFragment;//��������Ƭ
    private PeriodicalsFragment periodicalsFragment;//���ĸ���Ƭ
    private View fragmentLayout1;
    private View fragmentLayout2;
    private View fragmentLayout3;
    private View fragmentLayout4;
    private ImageView fragmentImage1;
    private ImageView fragmentImage2;
    private ImageView fragmentImage3;
    private ImageView fragmentImage4;
    private FragmentManager fragmentManager;
    /**
     * �Զ���HorizontalScrollView
     */
    private ColumnHorizontalScrollView mColumnHorizontalScrollView;
    LinearLayout mRadioGroup_content;
    LinearLayout ll_more_columns;
    RelativeLayout rl_column;
    private ViewPager mViewPager;
    private ImageView button_more_columns;
    /**
     * �û�ѡ������ŷ����б�
     */
    private ArrayList<ChannelItem> userChannelList = new ArrayList<ChannelItem>();
    /**
     * ��ǰѡ�е���Ŀ
     */
    private int columnSelectIndex = 0;
    /**
     * ����Ӱ����
     */
    public ImageView shade_left;
    /**
     * ����Ӱ����
     */
    public ImageView shade_right;
    /**
     * ��Ļ���
     */
    private int mScreenWidth = 0;
    /**
     * Item���
     */
    private int mItemWidth = 0;
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    public SlidingMenu side_drawer;
    /**
     * head ͷ�� ���м��loading
     */
    private ProgressBar top_progress;
    /**
     * head ͷ�� �м��ˢ�°�ť
     */
    private ImageView top_refresh;
    /**
     * head ͷ�� �����˵� ��ť
     */
    private ImageView top_head;
    /**
     * head ͷ�� ���Ҳ�˵� ��ť
     */
    private ImageView top_more;
    /**
     * ����CODE
     */
    public final static int CHANNELREQUEST = 1;
    /**
     * �������ص�RESULTCODE
     */
    public final static int CHANNELRESULT = 10;
//    private static final int STATE_REFRESHING = 3;

    private NewsFragmentPagerAdapter mAdapter;
    private int position1 = 0;

    /**
     * ʵ�ֵ�½�����UIΪԲ��ͷ��
     */
    private ImageView baibian_btn;//ͨ���ٱ��˺ŵ�¼
    private final int LOGIN4_REQUEST=11;//����login4activity��������
    private LinearLayout logout_layout_not_login;//δ��¼����
    private LinearLayout login_layout;//����Բ��ͷ��Ĳ��֣���������ԭ���Ĳ���

    /**
     * ���������������
     */
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;//�ж��Ƿ��ǵ�һ�ε�½ʹ��
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        StrictMode.setThreadPolicy(new
//                StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
//        StrictMode.setVmPolicy(
//                new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
        setContentView(R.layout.main);
        mScreenWidth = BaseTools.getWindowsWidth(this);
        mItemWidth = mScreenWidth / 4;// ???Item?????????1/4

        initSlidingMenu();

//        init_guide();//��������ĳ�ʼ��

        initViews();
        fragmentManager = getFragmentManager();
        // ��һ������ʱѡ�е�0��tab
        setTabSelection(0);




    }

    /**
     * �������ȡ��ÿ����Ҫ�õ��Ŀؼ���ʵ���������������úñ�Ҫ�ĵ���¼���
     */
    private void initViews() {
        fragmentLayout1 = findViewById(R.id.fragment1_layout);
        fragmentLayout2 = findViewById(R.id.fragment2_layout);
        fragmentLayout3 = findViewById(R.id.fragment3_layout);
        fragmentLayout4 = findViewById(R.id.fragment4_layout);
        fragmentImage1 = (ImageView) findViewById(R.id.fragment1_img);
        fragmentImage2 = (ImageView) findViewById(R.id.fragment2_img);
        fragmentImage3 = (ImageView) findViewById(R.id.fragment3_img);
        fragmentImage4 = (ImageView) findViewById(R.id.fragment4_img);
        fragmentLayout1.setOnClickListener(this);
        fragmentLayout2.setOnClickListener(this);
        fragmentLayout3.setOnClickListener(this);
        fragmentLayout4.setOnClickListener(this);


        top_head = (ImageView) findViewById(R.id.top_head);
        top_more = (ImageView) findViewById(R.id.top_more);
        top_refresh = (ImageView) findViewById(R.id.top_refresh);
       top_progress = (ProgressBar) findViewById(R.id.top_progress);
        top_head.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (side_drawer.isMenuShowing()) {
                    side_drawer.showContent();
                } else {
                    side_drawer.showMenu();
                }
            }
        });
        top_more.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                /**
                 * if (side_drawer.isSecondaryMenuShowing()) {
                 side_drawer.showContent();
                 } else {
                 side_drawer.showSecondaryMenu();
                 }
                 */
                // TODO Auto-generated method stub

            }
        });
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment1_layout:
                // ���������Ϣtabʱ��ѡ�е�1��tab
                InputMethodManager imm = (InputMethodManager) this.getSystemService(this.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(fragmentLayout1.getWindowToken(), 0);//���д������������
                setTabSelection(0);
                break;
            case R.id.fragment2_layout:
                // ���������ϵ��tabʱ��ѡ�е�2��tab
                InputMethodManager imm2 = (InputMethodManager) this.getSystemService(this.INPUT_METHOD_SERVICE);
                imm2.hideSoftInputFromWindow(fragmentLayout1.getWindowToken(), 0);//���д������������
                setTabSelection(1);
                break;
            case R.id.fragment3_layout:
                // ������˶�̬tabʱ��ѡ�е�3��tab
                InputMethodManager imm3 = (InputMethodManager) this.getSystemService(this.INPUT_METHOD_SERVICE);
                imm3.hideSoftInputFromWindow(fragmentLayout1.getWindowToken(), 0);//���д������������
                setTabSelection(2);
                break;
            case R.id.fragment4_layout:
                // ������˶�̬tabʱ��ѡ�е�3��tab
                InputMethodManager imm4 = (InputMethodManager) this.getSystemService(this.INPUT_METHOD_SERVICE);
                imm4.hideSoftInputFromWindow(fragmentLayout1.getWindowToken(), 0);//���д������������
                setTabSelection(3);
                break;
            default:
                break;
        }
    }

    /**
     * ���ݴ����index����������ѡ�е�tabҳ��
     *
     */
    private void setTabSelection(int index) {
        // ÿ��ѡ��֮ǰ��������ϴε�ѡ��״̬
        clearSelection();
        // ����һ��Fragment����
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // �����ص����е�Fragment���Է�ֹ�ж��Fragment��ʾ�ڽ����ϵ����
        hideFragments(transaction);
        switch (index) {
            case 0:
                fragmentImage1.setImageResource(R.drawable.homefragment_selected);
                fragmentLayout1.setBackgroundColor(getResources().getColor(R.color.main_layout_selected));
                if (homepageFragment == null) {
                    homepageFragment = new HomepageFragment();
                    transaction.add(R.id.content, homepageFragment);
                } else {
                    transaction.show(homepageFragment);
                }
                break;
            case 1:

                fragmentImage2.setImageResource(R.drawable.forumsfragment_selected);
                fragmentLayout2.setBackgroundColor(getResources().getColor(R.color.main_layout_selected));
                if (forumsFragment == null) {
                    forumsFragment = new ForumsFragment();
                    transaction.add(R.id.content, forumsFragment);
                } else {
                    transaction.show(forumsFragment);
                }
                break;
            case 2:
                fragmentImage3.setImageResource(R.drawable.findfragment_selected);
                fragmentLayout3.setBackgroundColor(getResources().getColor(R.color.main_layout_selected));
                if (findFragment == null) {
                    findFragment = new FindFragment();
                    transaction.add(R.id.content, findFragment);
                } else {
                    transaction.show(findFragment);
                }
                break;
            case 3:
                fragmentImage4.setImageResource(R.drawable.periodicalsfragment_selected);
                fragmentLayout4.setBackgroundColor(getResources().getColor(R.color.main_layout_selected));
                if (periodicalsFragment == null) {
                    // ���NewsFragmentΪ�գ��򴴽�һ������ӵ�������
                    periodicalsFragment = new PeriodicalsFragment();
                    transaction.add(R.id.content, periodicalsFragment);
                } else {
                    // ���NewsFragment��Ϊ�գ���ֱ�ӽ�����ʾ����
                    transaction.show(periodicalsFragment);
                }
                break;
        }


        transaction.commit();
    }

    /**
     * ��������е�ѡ��״̬��
     */
    private void clearSelection() {
        fragmentImage1.setImageResource(R.drawable.homefragment);
        fragmentLayout1.setBackgroundColor(getResources().getColor(R.color.main_layout1_unselected));
     //   fragmentLayout1.setBackgroundColor(getResources().getColor(
      //          R.color.mainFragment_background
      //  ));
        fragmentImage2.setImageResource(R.drawable.forumsfragment);
        fragmentLayout2.setBackgroundColor(getResources().getColor(R.color.main_layout1_unselected));
        fragmentImage3.setImageResource(R.drawable.findfragment);
        fragmentLayout3.setBackgroundColor(getResources().getColor(R.color.main_layout1_unselected));
        fragmentImage4.setImageResource(R.drawable.periodicalsfragment);
        fragmentLayout4.setBackgroundColor(getResources().getColor(R.color.main_layout1_unselected));
    }

    /**
     * �����е�Fragment����Ϊ����״̬��
     *
     *            ���ڶ�Fragmentִ�в���������
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (homepageFragment != null) {
            transaction.hide( homepageFragment);
        }
        if (forumsFragment != null) {
            transaction.hide(forumsFragment);
        }
        if (findFragment != null) {
            transaction.hide(findFragment);
        }
        if (periodicalsFragment != null) {
            transaction.hide(periodicalsFragment);
        }
    }
//
//
//
//    /**
//     * ���������ʼ������
//     */
//    private void init_guide(){
//        preferences = getSharedPreferences("phone", Context.MODE_PRIVATE);
//        if (preferences.getBoolean("firststart", true)) {
//            editor = preferences.edit();
//            //����¼��־λ����Ϊfalse���´ε�¼ʱ������ʾ�״ε�¼����
//            editor.putBoolean("firststart", false);
//            editor.commit();
//            Intent intent = new Intent(MainActivity.this, GuideActivity.class);
//            startActivity(intent);
//            finish();
//        }
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
//�Ժ�����
    protected void initSlidingMenu() {
        side_drawer = new SlidingDrawerView(this).initSlidingMenu();

        /**
         * ��¼�л���������
         */
       login_layout=(LinearLayout) side_drawer.findViewById(R.id.login_layout);
        baibian_btn=(ImageView) side_drawer.findViewById(R.id.baibian_btn);//�ٱ��¼��ť
        logout_layout_not_login=(LinearLayout) side_drawer.findViewById(R.id.logout_layout_not_login);//δ��¼����

        baibian_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent_baibian_btn=new Intent(MainActivity.this,Login4Activity.class);
                startActivityForResult(intent_baibian_btn,LOGIN4_REQUEST);
            }
        });
        login_layout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,UsersImformationActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });
    }

    private long mExitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (side_drawer.isMenuShowing() || side_drawer.isSecondaryMenuShowing()) {
                side_drawer.showContent();
            } else {
                if ((System.currentTimeMillis() - mExitTime) > 2000) {
                    Toast.makeText(this, R.string.Press_again_to_exit,
                            Toast.LENGTH_SHORT).show();
                    mExitTime = System.currentTimeMillis();
                } else {
                    finish();
                }
            }
            return true;
        }
        //����MENU��ť�¼����������κβ���
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        switch (requestCode) {

            case LOGIN4_REQUEST:
                if(resultCode==LOGIN4_REQUEST){
                    /**
                     * ��¼�л����ֲ���
                     */
                    logout_layout_not_login.setVisibility(View.GONE);//�ɲ�����ʧ
                    login_layout.setVisibility(View.VISIBLE);//�²��ֳ���
                }
                break;

            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
    * ?????????Scrollview?????????????????????   public boolean onTouchEvent(MotionEvent event){
     switch (event.getAction()){
     case MotionEvent.ACTION_DOWN:
     //????
     break;
     case MotionEvent.ACTION_MOVE:
     //???
     break;
     case MotionEvent.ACTION_UP:
     //???
     int y=(int) event.getY();
     if (y>=30d){
     imformation_viewPager.setVisibility(View.GONE);
     }
     if(y<=30d&&(imformation_viewPager.getVisibility()==View.VISIBLE)){
     imformation_viewPager.setVisibility(View.VISIBLE);
     }
     break;
     }
     return true;
     }
    */

}
