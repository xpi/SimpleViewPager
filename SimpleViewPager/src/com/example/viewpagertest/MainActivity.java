package com.example.viewpagertest;

import android.graphics.Color;
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

/**
 * @title: MainActivity.java
 * @description: 一个类似微信的viewpager
 * @copyright: Copyright (c) 2014
 * @company: HanZhiSoft
 * @author HuangXiaoPeng
 * @date 2014-12-19
 * @version 1.0
 */
public class MainActivity extends FragmentActivity {
	private Fragment f1 = new Fragment1();
	private Fragment f2 = new Fragment2();
	private Fragment f3 = new Fragment3();
	private Fragment[] fras = { f1, f2, f3 };

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
	int current = 0;
	int next = 0;
	int state = 0;

	Button[] btns = new Button[3];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initUI();
		// viewpager使用方法类似ListView

		mPager = (ViewPager) findViewById(R.id.pager);
		mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
		mPager.setAdapter(mPagerAdapter);
		mPager.setCurrentItem(0);
		mPager.setOnPageChangeListener(new OnPageChangeListener() {

	
			@Override
			public void onPageSelected(int pagenum) {
				current = pagenum;

				for (int i = 0; i < btns.length; i++) {
					if (i == current) {

						btns[i].getBackground().setAlpha(255);
						btns[i].setTextColor(Color.argb(255, 0, 0, 0));
					} else {
						btns[i].getBackground().setAlpha(0);
						btns[i].setTextColor(Color.argb(127, 0, 0, 0));

					}

				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				int orien;
				if (state == 1 && arg1 != 0) {
					if (current == arg0) {
						orien = 1;
						next = arg0 + 1;
						changeBtnStyle(arg1, current, next, orien);
						return;
					}
					orien = -1;
					next = arg0;
					changeBtnStyle(arg1, current, next, orien);
				}

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

				state = arg0;
				if (state == 2) {
					btns[current].getBackground().setAlpha(255);
					btns[current].setTextColor(Color.argb(255, 0, 0, 0));
					btns[next].getBackground().setAlpha(0);
					btns[next].setTextColor(Color.argb(127, 0, 0, 0));
				}

			}

			// 渐变底部bar样式
			private void changeBtnStyle(float size, int current, int next,
					int orien) {
				float fadeIn = (float) (Math.round(size * 100)) / 100;
				float fadeOut = (float) (Math.round((1 - size) * 100)) / 100;
				if (orien == 1) {

					btns[current].getBackground().setAlpha(
							(int) (255 * fadeOut));
					btns[current].setTextColor(Color.argb(
							(int) (127 * fadeOut) + 127, 0, 0, 0));
					btns[next].getBackground().setAlpha((int) (255 * fadeIn));
					btns[next].setTextColor(Color.argb(
							(int) (127 * fadeIn) + 127, 0, 0, 0));
					// btns[next].setText(fadeIn + "");
					// btns[current].setText(fadeOut + "");

				}
				if (orien == -1) {
					btns[current].getBackground()
							.setAlpha((int) (255 * fadeIn));
					btns[current].setTextColor(Color.argb(
							(int) (127 * fadeIn) + 127, 0, 0, 0));
					btns[next].getBackground().setAlpha((int) (255 * fadeOut));
					btns[next].setTextColor(Color.argb(
							(int) (127 * fadeOut) + 127, 0, 0, 0));
					// btns[next].setText(fadeOut + "");
					// btns[current].setText(fadeIn + "");

				}

			}

		});

	}

	private void initUI() {
		btn1 = (Button) findViewById(R.id.button1);
		btn2 = (Button) findViewById(R.id.button2);
		btn3 = (Button) findViewById(R.id.button3);
		btn1.getBackground().setAlpha(255);
		btn2.getBackground().setAlpha(0);
		btn3.getBackground().setAlpha(0);

		btns[0] = btn1;
		btns[1] = btn2;
		btns[2] = btn3;

		btn1.setOnClickListener(new BtnListener());
		btn2.setOnClickListener(new BtnListener());
		btn3.setOnClickListener(new BtnListener());
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	// viewpager scroll listener
	private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
		public ScreenSlidePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				return fras[0];
			case 1:
				return fras[1];
			case 2:
				return fras[2];
			}
			return fras[0];
		}

		@Override
		public int getCount() {
			return NUM_PAGES;
		}
	}

	// bottom bar lisetner
	class BtnListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			int bid = v.getId();
			switch (bid) {
			case R.id.button1:
				next=0;
				mPager.setCurrentItem(0);
				break;
			case R.id.button2:
				next=1;
				mPager.setCurrentItem(1);
				break;
			case R.id.button3:
				next=2;
				mPager.setCurrentItem(2);
				break;

			}
		}
	}

}
