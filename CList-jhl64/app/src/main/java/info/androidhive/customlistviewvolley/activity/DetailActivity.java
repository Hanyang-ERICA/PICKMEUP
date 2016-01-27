package info.androidhive.customlistviewvolley.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import info.androidhive.customlistviewvolley.R;
import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.util.CounterUtil;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.LikeView;
import com.facebook.share.widget.ShareButton;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.MutableData;
import com.firebase.client.Transaction;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by jaehalee on 2016. 1. 23..
 */
public class DetailActivity extends FragmentActivity {

    /*
     * PagerAdapter을 생성합니다.
	 * PagerAdapter는 android.support.v4.app.FragmentPagerAdapter
	 * 를 상속받습니다. FragmentPagerAdapter는 v4 libs에 추가된 내용입니다.
	 */
    SectionsPagerAdapter mSectionsPagerAdapter;

    //android.support.v4.view.ViewPager의 Custom Layout
    ViewPager mViewPager;

    private String mUrl = null;
    String mname = null;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        Intent intent = getIntent();
        mUrl = intent.getStringExtra("image");
        mname = intent.getStringExtra("name");
//        if (imageLoader == null)
//            imageLoader = AppController.getInstance().getImageLoader();
//        NetworkImageView thumbNail = (NetworkImageView) findViewById(R.id.thumbnail);
//        thumbNail.setImageUrl(mUrl,imageLoader);
//
//        TextView nameView = (TextView) findViewById(R.id.name);
//        nameView.setText(mname);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Detail Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://info.androidhive.customlistviewvolley.activity/http/host/path")
        );
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Detail Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://info.androidhive.customlistviewvolley.activity/http/host/path")
        );
        client.disconnect();
    }

    //PagerAdapter는 Fragment를 통해 각각의 페이지의 이름과 Fragment를 정의
    //이 페이지는 xml의 android.support.v4.view.ViewPager에 보여지게 된다.
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        //각 position의 값에 따라서 각 화면을 정의한다.
        //여기서는 Bundle를 이용하여 position + 1한 값을 전송
        //Fragment의 View에서 1,2,3을 표시하게 된다.
        @Override
        public Fragment getItem(int position) {
            if (position == 0)
                return new GreenFragment();
            else
                return new RedFragment();
        }

        //Fragment에 사용될 전체 페이지 수를 정의한다. 여기서는 3페이지
        @Override
        public int getCount() {
            return 2;
        }

        /*
         * getPageTitle을 정의한다. 이 부분은 XML의
         * android.support.v4.view.PagerTitleStrip에 뿌려진다.
         * 제목은 모두 대문자로 변형되어 출력되고, String에 저장되어 있다.
         */
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.title_section1);
                case 1:
                    return getString(R.string.title_section2);
            }
            return null;
        }
    }

    //각각의 Fragment를 만들게 된다.
    public static class DummySectionFragment extends Fragment {
        public static final String ARG_SECTION_NUMBER = "section_number";

        public DummySectionFragment() {
        }

        /* onCreateView를 통해 화면에 보여질 내용을 정리하게 된다.
         * XML을 통해서 직접 작성할 수도 있다.
         * 그리고 각각 페이지의 내용이 다르다면 각각의 Fragment와 별도의 XML을 가지면 된다.
         */

    }


    public class RedFragment extends Fragment {


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
// Inflate the layout for this fragment
            FacebookSdk.sdkInitialize(getApplicationContext());
            View view = inflater.inflate(R.layout.fragment_red, container, false);
            TextView wcatchphrase;
            TextView wname;
            TextView wcompany;
            TextView wage;
            TextView wheight;
            TextView wweight;
            TextView wperiod;
            TextView whobby;
            TextView wtalent;
            TextView wcomment;

            Intent intent = getIntent();
            String catchphrase = intent.getStringExtra("catchphrase");
            String name = intent.getStringExtra("name");
            String company = intent.getStringExtra("company");
            String age = intent.getStringExtra("age");
            String height = intent.getStringExtra("height");
            String weight = intent.getStringExtra("weight");
            String period = intent.getStringExtra("period");
            String hobby = intent.getStringExtra("hobby");
            String talent = intent.getStringExtra("talent");
            String comment = intent.getStringExtra("comment");

            wcatchphrase = (TextView) view.findViewById(R.id.catchphrase);
            wcatchphrase.setText(catchphrase);
            wname = (TextView) view.findViewById(R.id.name);
            wname.setText("이름 : "+name+" 나이 : "+age);
            wcompany = (TextView) view.findViewById(R.id.company);
            wcompany.setText("소속사 : "+company);
            wheight = (TextView) view.findViewById(R.id.height);
            wheight.setText("신장 : "+height+" 체중 : "+weight);
            wperiod = (TextView) view.findViewById(R.id.period);
            wperiod.setText("연습기간 : "+period);
            whobby = (TextView) view.findViewById(R.id.hobby);
            whobby.setText("취미 : "+hobby);
            wtalent = (TextView) view.findViewById(R.id.talent);
            wtalent.setText("특기 : "+talent);
            wcomment = (TextView) view.findViewById(R.id.comment);
            wcomment.setText(comment);


            return view;
        }

    }

    public class GreenFragment extends Fragment {
        String name;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            FacebookSdk.sdkInitialize(getApplicationContext());

            Firebase.setAndroidContext(getContext());
            Intent intent = getIntent();
            String imageurl = intent.getStringExtra("imgurl");
            name = intent.getStringExtra("name");
// Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_green, container, false);
            ShareLinkContent content = new ShareLinkContent.Builder()
                    .setContentTitle("Produce 101")
                    .setContentDescription("프로듀스 101 :: " + name + "을 응원합니다!")
                    .setContentUrl(Uri.parse(imageurl))
                    .build();

            ShareButton shbtn = (ShareButton) view.findViewById(R.id.share_btn);
            NetworkImageView image = (NetworkImageView) view.findViewById(R.id.bigimage);
            image.setImageUrl(imageurl, imageLoader);
            shbtn.setShareContent(content);

            Button hate = (Button) view.findViewById(R.id.the_button);
            hate.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    onButtonHateClick(view);
                }
            });
            Button like = (Button) view.findViewById(R.id.the_button2);
            like.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    onButtonLikeClick(view);
                }
            });

            return view;
        }

        public void onButtonHateClick(View view) {
            String message = "싫어요!";
            CounterUtil.updateCounter("hatecounter", name);
            Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
            toast.show();
        }

        public void onButtonLikeClick(View view) {
            String message = "좋아요!";
            CounterUtil.updateCounter("likecounter", name);
            Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}


