package com.baibian.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baibian.R;
import com.baibian.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;

/**
 * Created by XZY on 2017/4/13.
 * ��������Ȩ���츳/�˸�
 * ������Ȩ���츳����ȻȨ����Natural%20Right��������ı��������Թ�ϣ��˹���ѧ�ɣ�ǿ����������Ȼ״̬��
 * ���еģ���ʥ�����ַ��Ͱ����Ȩ������ˣ������Ȩ��������Ȼ������һ����Ϊ�Ĺ��ҡ���Ȩ����ᡢ����
 * �����򣬶����뾭���˵�Ȩ����%20������һЩ������Ϊ������Ȼ״̬�£�ֻ������ǿʳ���ﾺ����ʤ��Ϊ��
 * ��û����Ȩ����û��ƽ�ȡ����ˣ�������Ȼ�Ŀ����У��������������У�������
 * �������ٽ���ߵ����壬����ν�ġ�Ȩ������������Լ���%20��ˣ������е���ȻȨ�������������ۣ�Ȩ
 * �������ǵ������죬�������������Լ����������ڴ��ⳡ�������������ǵ�������
 */

public class DetailsActivity extends BaseActivity {
    ViewPager pager = null;
    PagerTabStrip tabStrip = null;
    ArrayList<View> viewContainter = new ArrayList<View>();
    ArrayList<String> titleContainer = new ArrayList<String>();
    public String TAG = "tag";
    Button backBt = null;
    private TagContainerLayout mTagContainerLayout1, mTagContainerLayout2, mTagContainerLayout3, mTagContainerLayout4;
    private Button addTag1;
    private EditText editTag;
    private ViewPager viewPager;
    List<Fragment> viewPagerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.forums_details);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        initData();

        initView();
        initViewPager();
//        viewpager��������
//        viewPagerList=new ArrayList<Fragment>();
//        viewPagerList.add(new overviewFragment());
//        viewPagerList.add(new subdivideFragment());
//        viewPager =(ViewPager) findViewById(R.id.pager);
//        fgViewPagerAdapter adapter=new fgViewPagerAdapter(getSupportFragmentManager());
//        viewPager.setAdapter(adapter);


//      ���ð�ť���
//        addTag1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                editTag.setText(addTag1.getText());
//            }
//        });

    }

    private void initData() {


        List<String> list1 = new ArrayList<String>();
        list1.add("Java");
        list1.add("C++");
        list1.add("Python");
        list1.add("Swift");
        list1.add("��ã�����һ��TAG����ã�����һ��TAG����ã�����һ��TAG����ã�����һ��TAG��");
        list1.add("PHP");
        list1.add("JavaScript");
        list1.add("Html");
        list1.add("Welcome to use AndroidTagView!");

        List<String> list2 = new ArrayList<String>();
        list2.add("China");
        list2.add("USA");
        list2.add("Austria");
        list2.add("Japan");
        list2.add("Sudan");
        list2.add("Spain");
        list2.add("UK");
        list2.add("Germany");
        list2.add("Niger");
        list2.add("Poland");
        list2.add("Norway");
        list2.add("Uruguay");
        list2.add("Brazil");

        String[] list3 = new String[]{"Persian", "��˹��", "?????", "Hello", "���", "????"};
        String[] list4 = new String[]{"Adele", "Whitney Houston"};

        mTagContainerLayout1 = (TagContainerLayout) findViewById(R.id.tagcontainerLayout1);
        mTagContainerLayout2 = (TagContainerLayout) findViewById(R.id.tagcontainerLayout2);
        mTagContainerLayout3 = (TagContainerLayout) findViewById(R.id.tagcontainerLayout3);
        mTagContainerLayout4 = (TagContainerLayout) findViewById(R.id.tagcontainerLayout4);

        // Set custom click listener
        mTagContainerLayout1.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {
                Toast.makeText(DetailsActivity.this, "click-position:" + position + ", text:" + text,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTagLongClick(final int position, String text) {
                AlertDialog dialog = new AlertDialog.Builder(DetailsActivity.this)
                        .setTitle("long click")
                        .setMessage("You will delete this tag!")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mTagContainerLayout1.removeTag(position);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();
                dialog.show();
            }

            @Override
            public void onTagCrossClick(int position) {
//                mTagContainerLayout1.removeTag(position);
                Toast.makeText(DetailsActivity.this, "Click TagView cross! position = " + position,
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Custom settings
//        mTagContainerLayout1.setTagMaxLength(4);

        // Set the custom theme
//        mTagContainerLayout1.setTheme(ColorFactory.PURE_CYAN);

        // If you want to use your colors for TagView, remember set the theme with ColorFactory.NONE
//        mTagContainerLayout1.setTheme(ColorFactory.NONE);
//        mTagContainerLayout1.setTagBackgroundColor(Color.TRANSPARENT);
//        mTagContainerLayout1.setTagTextDirection(View.TEXT_DIRECTION_RTL);

        // support typeface
//        Typeface typeface = Typeface.createFromAsset(getAssets(), "iran_sans.ttf");
//        mTagContainerLayout.setTagTypeface(typeface);

        // adjust distance baseline and descent
//        mTagContainerLayout.setTagBdDistance(4.6f);

        // After you set your own attributes for TagView, then set tag(s) or add tag(s)
        mTagContainerLayout1.setTags(list1);
        mTagContainerLayout2.setTags(list2);
        mTagContainerLayout3.setTags(list3);
        mTagContainerLayout4.setTags(list4);

        final EditText text = (EditText) findViewById(R.id.text_tag);
        Button btnAddTag = (Button) findViewById(R.id.btn_add_tag);
        btnAddTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTagContainerLayout1.addTag(text.getText().toString());
                // Add tag in the specified position
//                mTagContainerLayout1.addTag(text.getText().toString(), 4);
            }
        });

//        mTagContainerLayout1.setMaxLines(1);


        // test in RecyclerView
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        recyclerView.setVisibility(View.VISIBLE);
//        TagRecyclerViewAdapter adapter = new TagRecyclerViewAdapter(this, list3);
//        adapter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Click on TagContainerLayout", Toast.LENGTH_SHORT).show();
//            }
//        });
//        recyclerView.setAdapter(adapter);
    }


    private void initViewPager() {
        viewPager.setAdapter(new PagerAdapter() {

            //viewpager�е��������
            @Override
            public int getCount() {
                return viewContainter.size();
            }

            //�����л���ʱ�����ٵ�ǰ�����
            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                ((ViewPager) container).removeView(viewContainter.get(position));
            }

            //ÿ�λ�����ʱ�����ɵ����
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ((ViewPager) container).addView(viewContainter.get(position));
                return viewContainter.get(position);
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getItemPosition(Object object) {
                return super.getItemPosition(object);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                SpannableStringBuilder ssb = new SpannableStringBuilder(" "
                        + titleContainer.get(position)); // space added before text for
                ForegroundColorSpan fcs = new ForegroundColorSpan(getResources().getColor(R.color.baibian_back_color));//������ɫ����Ϊ��ɫ
                ssb.setSpan(fcs, 1, ssb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);//����������ɫ
                ssb.setSpan(new RelativeSizeSpan(1.2f), 1, ssb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                return ssb;
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int arg0) {
                Log.e(TAG, "--------changed:" + arg0);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                Log.e(TAG, "-------scrolled arg0:" + arg0);
                Log.e(TAG, "-------scrolled arg1:" + arg1);
                Log.e(TAG, "-------scrolled arg2:" + arg2);
            }

            @Override
            public void onPageSelected(int arg0) {
                Log.e(TAG, "------selected:" + arg0);
            }
        });
    }

    private void initView() {
        viewPager = (ViewPager) this.findViewById(R.id.viewpager);
        tabStrip = (PagerTabStrip) this.findViewById(R.id.tab_strip);
        backBt = (Button) this.findViewById(R.id.forums_details_back);
        addTag1 = (Button) this.findViewById(R.id.button4);
        editTag = (EditText) this.findViewById(R.id.tag_edit);
        List<String> tags = new ArrayList<String>();
        tags.add("123");
        tags.add("134455");
//        TagContainerLayout  mTagContainerLayout = (TagContainerLayout) findViewById(R.id.tagcontainerLayout);
//        mTagContainerLayout.addTag(tags.get(0));
        //ȡ��tab����ĳ�����
        tabStrip.setDrawFullUnderline(false);
        //����tab�ı���ɫ
        tabStrip.setBackgroundColor(this.getResources().getColor(R.color.white));
        //���õ�ǰtabҳǩ���»�����ɫ
        tabStrip.setTabIndicatorColor(this.getResources().getColor(R.color.baibian_back_color));
        tabStrip.setTextSpacing(200);

        View view1 = LayoutInflater.from(this).inflate(R.layout.forums_details_overview, null);
        View view2 = LayoutInflater.from(this).inflate(R.layout.forums_details_subdivide, null);
        //viewpager��ʼ���view
        viewContainter.add(view1);
        viewContainter.add(view2);
        //ҳǩ��
        titleContainer.add("�Ƕ�ϸ��");
        titleContainer.add("�������");

        backBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
        //���һ���þͳ�������һ�¡�
//        mTagContainerLayout.setTags(tags);


    }


    public class TagRecyclerViewAdapter
            extends RecyclerView.Adapter<TagRecyclerViewAdapter.TagViewHolder> {

        private Context mContext;
        private String[] mData;
        private View.OnClickListener mOnClickListener;

        public TagRecyclerViewAdapter(Context context, String[] data) {
            this.mContext = context;
            this.mData = data;
        }

        @Override
        public int getItemCount() {
            return 10;
        }

        @Override
        public TagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new TagViewHolder(LayoutInflater.from(mContext)
                    .inflate(R.layout.view_recyclerview_item, parent, false), mOnClickListener);
        }

        @Override
        public void onBindViewHolder(TagViewHolder holder, int position) {
            holder.tagContainerLayout.setTags(mData);
            holder.button.setOnClickListener(mOnClickListener);
        }

        public void setOnClickListener(View.OnClickListener listener) {
            this.mOnClickListener = listener;
        }

        class TagViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TagContainerLayout tagContainerLayout;
            View.OnClickListener clickListener;
            Button button;

            public TagViewHolder(View v, View.OnClickListener listener) {
                super(v);
                this.clickListener = listener;
//                tagContainerLayout = (TagContainerLayout) v.findViewById(R.id.tagcontainerLayout);
                button = (Button) v.findViewById(R.id.button);
//                v.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onClick(v);
                }
            }
        }
    }

    //����Fragment��ViewPager��������
    private class fgViewPagerAdapter extends FragmentPagerAdapter {

        public fgViewPagerAdapter(FragmentManager fm) {
            super(fm);
            // TODO Auto-generated constructor stub
        }

        @Override
        public Fragment getItem(int arg0) {
            // TODO Auto-generated method stub
            return viewPagerList.get(arg0);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return viewPagerList.size();
        }

    }
}