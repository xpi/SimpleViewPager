package com.example.viewpagertest;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends FragmentActivity {
	/**
	 * The number of pages (wizard steps) to show in this demo.
	 */
	private static final int NUM_PAGES = 3;

	/**
	 * The pager widget, which handles animation and allows swiping horizontally
	 * to access previous and next wizard steps.
	 */
	private ViewPager mPager;

	/**
	 * The pager adapter, which provides the pages to the view pager widget.
	 */
	private PagerAdapter mPagerAdapter;
	Button btn1, btn2, btn3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Instantiate a ViewPager and a PagerAdapter.
		btn1 = (Button) findViewById(R.id.button1);
		btn2 = (Button) findViewById(R.id.button2);
		btn3 = (Button) findViewById(R.id.button3);
		btn1.setOnClickListener(new BtnListener());
		btn2.setOnClickListener(new BtnListener());
		btn3.setOnClickListener(new BtnListener());
		btn1.setBackgroundResource(R.drawable.btn_current1);

		mPager = (ViewPager) findViewById(R.id.pager);
		mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
		mPager.setAdapter(mPagerAdapter);

		mPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int pagenum) {
				switch (pagenum) {
				case 0:
					btn1.setBackgroundResource(R.drawable.btn_current1);
					btn2.setBackgroundResource(R.drawable.btn_out);
					btn3.setBackgroundResource(R.drawable.btn_out);
					break;
				case 1:
					btn2.setBackgroundResource(R.drawable.btn_current2);
					btn1.setBackgroundResource(R.drawable.btn_out);
					btn3.setBackgroundResource(R.drawable.btn_out);
					break;

				case 2:
					btn3.setBackgroundResource(R.drawable.btn_current3);
					btn1.setBackgroundResource(R.drawable.btn_out);
					btn2.setBackgroundResource(R.drawable.btn_out);
					break;

				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				//btn1.setText(arg0+" "+arg1+" "+arg2);
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
		public ScreenSlidePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				return new Fragment1();

			case 1:
				return new Fragment2();
			case 2:
				return new Fragment3();
			default:
				return new Fragment3();
			}
		}

		@Override
		public int getCount() {
			return NUM_PAGES;
		}
	}

	class BtnListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			int bid = v.getId();
			switch (bid) {
			case R.id.button1:
				mPager.setCurrentItem(0);
				break;
			case R.id.button2:
				mPager.setCurrentItem(1);
				break;

			case R.id.button3:
				mPager.setCurrentItem(2);
				break;

			}
		}

	}

}
