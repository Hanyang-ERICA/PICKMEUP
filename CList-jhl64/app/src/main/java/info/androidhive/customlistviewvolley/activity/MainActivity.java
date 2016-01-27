package info.androidhive.customlistviewvolley.activity;

import info.androidhive.customlistviewvolley.R;
import info.androidhive.customlistviewvolley.adater.CustomListAdapter;
import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Movie;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

public class MainActivity extends Activity {
	// Log tag
	private static final String TAG = MainActivity.class.getSimpleName();

	// Movies json url
	private static final String url = "http://52.192.111.80/data.php";
	private ProgressDialog pDialog;
	private List<Movie> movieList = new ArrayList<Movie>();
	private ListView listView;
	private CustomListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.list);
		adapter = new CustomListAdapter(this, movieList);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Movie m = movieList.get(position);
				Intent intent = new Intent(MainActivity.this, DetailActivity.class);
				intent.putExtra("catchphrase", m.getCatchphrase());
				intent.putExtra("imgurl", m.getImgurl());
				intent.putExtra("name", m.getName());
				intent.putExtra("nameurl", m.getNameurl());
				intent.putExtra("company", m.getCompany());
				intent.putExtra("age", m.getAge());
				intent.putExtra("height", m.getHeight());
				intent.putExtra("weight", m.getWeight());
				intent.putExtra("period", m.getPeriod());
				intent.putExtra("hobby", m.getHobby());
				intent.putExtra("talent", m.getTalent());
				intent.putExtra("comment", m.getComment());

				startActivity(intent);
			}
		});

		pDialog = new ProgressDialog(this);
		// Showing progress dialog before making http request
		pDialog.setMessage("Loading...");
		pDialog.show();

		// changing action bar color
		getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#1b1b1b")));

		// Creating volley request obj
		JsonArrayRequest movieReq = new JsonArrayRequest(url,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						Log.d(TAG, response.toString());
						hidePDialog();

						// Parsing json
						Log.d(TAG, "Members:"+response.length());
						for (int i = 0; i < response.length(); i++) {
							try {

								JSONObject obj = response.getJSONObject(i);
								Movie movie = new Movie();
								movie.setCatchphrase(obj.getString("catchphrase"));
								movie.setImgurl(obj.getString("imgurl"));
								movie.setName(obj.getString("name"));
								movie.setNameurl(obj.getString("nameurl"));
								movie.setCompany(obj.getString("company"));
								movie.setAge(obj.getString("age"));
								movie.setHeight(obj.getString("height"));
								movie.setWeight(obj.getString("weight"));
								movie.setPeriod(obj.getString("period"));
								movie.setHobby(obj.getString("hobby"));
								movie.setTalent(obj.getString("talent"));
								movie.setComment(obj.getString("comment"));

								// adding movie to movies array
								movieList.add(movie);

							} catch (JSONException e) {
								e.printStackTrace();
							}

						}

						// notifying list adapter about data changes
						// so that it renders the list view with updated data
						adapter.notifyDataSetChanged();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.d(TAG, error.getMessage());
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						hidePDialog();

					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(movieReq);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		hidePDialog();
	}

	private void hidePDialog() {
		if (pDialog != null) {
			pDialog.dismiss();
			pDialog = null;
		}
	}


}
