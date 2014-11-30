/*Jagdeep Matharu - 300710666 - Assignment 4*/
package com.matharu.assign4;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Jagdeep_gallery extends Activity {
	Button bBack;
	GridView gridView;
	private Cursor cursor;
	private int columnIndex;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gallery_matharu4);

		declaring();

		ToggleButton toggle = (ToggleButton) findViewById(R.id.toggleButton1);
		toggle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (((ToggleButton) v).isChecked()) {
				} else {

				}
			}
		});
		try {
			String[] projection = { MediaStore.Images.Thumbnails._ID };
			cursor = managedQuery(
					MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI,
					projection, null, null,
					MediaStore.Images.Thumbnails.IMAGE_ID);

			columnIndex = cursor
					.getColumnIndexOrThrow(MediaStore.Images.Thumbnails._ID);

			GridView sdcardImages = (GridView) findViewById(R.id.gridView1);
			sdcardImages.setAdapter(new ImageAdapter(this));
			sdcardImages.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View v,
						int position, long id) {
					String[] projection = { MediaStore.Images.Media.DATA };
					cursor = managedQuery(
							MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
							projection, null, null, null);
					columnIndex = cursor
							.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
					cursor.moveToPosition(position);
				}
			});
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private class ImageAdapter extends BaseAdapter {

		private Context context;

		public ImageAdapter(Jagdeep_gallery jagdeep_gallery) {
			// TODO Auto-generated constructor stub
			context = jagdeep_gallery;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return cursor.getCount();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ImageView picturesView;
			if (convertView == null) {

				picturesView = new ImageView(context);
				cursor.moveToPosition(position);
				int imageID = cursor.getInt(columnIndex);
				picturesView.setImageURI(Uri.withAppendedPath(
						MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, ""
								+ imageID));
				if (position % 3 == 0) {
					picturesView.setScaleType(ImageView.ScaleType.CENTER_CROP);
					picturesView.setPadding(0, 0, 0, 0);
					picturesView.setLayoutParams(new GridView.LayoutParams(200,
							200));
				} else {
					picturesView.setScaleType(ImageView.ScaleType.CENTER_CROP);
					picturesView.setPadding(0, 0, 0, 0);
					picturesView.setLayoutParams(new GridView.LayoutParams(200,
							200));
				}
			} else {
				picturesView = (ImageView) convertView;
			}
			return picturesView;
		}
	}

	private void declaring() {
		// TODO Auto-generated method stub
		TextView txt1 = (TextView) findViewById(R.id.studentDetail);
		txt1.setText(R.string.studentDetail);

		bBack = (Button) findViewById(R.id.bBack);
		bBack.setText(R.string.backButton);

		TextView txtToggle = (TextView) findViewById(R.id.txtToggle);
		txtToggle.setText(R.string.txtToggle);
	}

	public void back(View view) {
		Intent activityIntent = new Intent(Jagdeep_gallery.this,
				JagdeepActivity4.class);
		Jagdeep_gallery.this.startActivity(activityIntent);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		AlertDialog diaBox = AskOption();
		diaBox.show();
	}

	private AlertDialog AskOption() {
		// TODO Auto-generated method stub
		AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
				.setTitle(R.string.exit)
				.setMessage(R.string.notification)
				.setPositiveButton(R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								finish();
							}
						})
				.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						}).create();
		return myQuittingDialogBox;
	}
}
