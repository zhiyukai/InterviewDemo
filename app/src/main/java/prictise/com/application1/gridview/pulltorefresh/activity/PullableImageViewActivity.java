package prictise.com.application1.gridview.pulltorefresh.activity;

import android.app.Activity;
import android.os.Bundle;

import prictise.com.application1.R;
import prictise.com.application1.gridview.pulltorefresh.MyListener;
import prictise.com.application1.gridview.pulltorefresh.PullToRefreshLayout;

public class PullableImageViewActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_imageview);
		((PullToRefreshLayout) findViewById(R.id.refresh_view))
				.setOnRefreshListener(new MyListener());
	}
}
