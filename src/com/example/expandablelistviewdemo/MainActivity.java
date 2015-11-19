package com.example.expandablelistviewdemo;

import org.w3c.dom.Text;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ExpandableListView list;

	int[] logos = new int[] { R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher };
	// 设置组视图的显示文字
	String[] category = new String[] { "僵尸  ", "魔法植物", "远程植物" };
	// 子视图显示文字
	String[][] subcategory = new String[][] { { "旗帜僵尸", "铠甲僵尸", "书生见识", "铁桶僵尸", "尸娃僵尸", "舞蹈僵尸" },
			{ "黄金蘑菇", "贪睡蘑菇", "大头蘑菇", "诱惑植物", "多嘴蘑菇", "七彩蘑菇" }, { "满天星", "风车植物", "带刺植物", "贪睡植物", "双子植物", "胆怯蘑菇" }

	};
	// 子视图图片
	int[][] sublogos = new int[][] {
			{ R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
					R.drawable.ic_launcher, R.drawable.ic_launcher },
			{ R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
					R.drawable.ic_launcher, R.drawable.ic_launcher },
			{ R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
					R.drawable.ic_launcher, R.drawable.ic_launcher } };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		list = (ExpandableListView) findViewById(R.id.list);
		// 设置组视图的图片
//		list.setGroupIndicator(null);
//		list.setDivider(null);
//		list.setSelector(new ColorDrawable(Color.TRANSPARENT));
		list.setAdapter(adapter);
		list.setOnChildClickListener(new OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition,
					long id) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "你单击了：" + adapter.getChild(groupPosition, childPosition),
						Toast.LENGTH_LONG).show();
				Intent intent = new Intent(MainActivity.this,ExampleActivity.class);
				startActivity(intent);
				
				return true;
			}
		});

	}

	// 定义一个显示文字信息的方法
	private TextView getTextView() {
		AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 64);
		TextView textView = new TextView(MainActivity.this);
		// 设置 textView控件的布局
		textView.setLayoutParams(lp);
		// 设置该textView中的内容相对于textView的位置
		textView.setGravity(Gravity.CENTER_VERTICAL);
		// 设置txtView的内边距
		textView.setPadding(36, 0, 0, 0);
		// 设置文本颜色
		textView.setTextColor(Color.BLACK);
		return textView;

	}

	final ExpandableListAdapter adapter = new BaseExpandableListAdapter() {

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return true;
		}

		// 取得用于显示给定分组的试图,这个方法返回分组的视图对象
		@Override
		public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			LinearLayout ll = new LinearLayout(MainActivity.this);
			ll.setOrientation(LinearLayout.HORIZONTAL);
			ImageView logo = new ImageView(MainActivity.this);
			logo.setPadding(50, 0, 0, 0);
			AbsListView.LayoutParams lparParams = new AbsListView.LayoutParams(96, 46);
			logo.setLayoutParams(lparParams);
			logo.setImageResource(logos[groupPosition]);
			ll.addView(logo);
			TextView textView = getTextView();
			textView.setTextSize(20);
			textView.setText(category[groupPosition]);
			ll.addView(textView);
			return ll;
		}

		@Override
		public long getGroupId(int groupPosition) {
			// TODO Auto-generated method stub
			return groupPosition;
		}

		@Override
		public int getGroupCount() {
			// TODO Auto-generated method stub
			return category.length;
		}

		@Override
		public Object getGroup(int groupPosition) {
			// TODO Auto-generated method stub
			return category[groupPosition];
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			// TODO Auto-generated method stub
			return subcategory[groupPosition].length;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
				ViewGroup parent) {
			// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			// 定义一个LinearLayout用于存放ImageView、TextView
			LinearLayout ll = new LinearLayout(MainActivity.this);
			// 设置子控件的显示方式为水平
			ll.setOrientation(0);
			// 定义一个ImageView用于显示列表图片
			ImageView logo = new ImageView(MainActivity.this);
			logo.setPadding(70, 0, 0, 0);
			// 设置logo的大小
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(110, 40);
			logo.setLayoutParams(lp);
			logo.setImageResource(sublogos[groupPosition][childPosition]);
			ll.addView(logo);
			TextView textView = getTextView();
			textView.setText(subcategory[groupPosition][childPosition]);
			ll.addView(textView);
			return ll;
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return childPosition;
		}

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return subcategory[groupPosition][childPosition];
		}
	};



}
