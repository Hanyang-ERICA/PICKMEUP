package info.androidhive.customlistviewvolley.adater;

import info.androidhive.customlistviewvolley.R;
import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Member;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class CustomListAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<Member> memberItems;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public CustomListAdapter(Activity activity, List<Member> memberItems) {
		this.activity = activity;
		this.memberItems = memberItems;
	}

	@Override
	public int getCount() {
		return memberItems.size();
	}

	@Override
	public Object getItem(int location) {
		return memberItems.get(location);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (inflater == null)
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.list_row, null);

		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();
		NetworkImageView image = (NetworkImageView) convertView
				.findViewById(R.id.image);
		TextView catchphrase = (TextView) convertView.findViewById(R.id.title);
		TextView name = (TextView) convertView.findViewById(R.id.rating);
		TextView company = (TextView) convertView.findViewById(R.id.genre);
		TextView age = (TextView) convertView.findViewById(R.id.releaseYear);

		// getting movie data for the row
		Member m = memberItems.get(position);

		// thumbnail image
		image.setImageUrl(m.getImgurl(), imageLoader);

		// title
		catchphrase.setText(m.getCatchphrase());
		name.setText(m.getName());
		company.setText(m.getCompany());
		age.setText(m.getAge());

		return convertView;
	}

}
