/*Jagdeep Matharu - 300710666 - Assignment 4*/

package com.matharu.assign4;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class JagdeepActivity4 extends Activity {
	AlertDialog.Builder dialogBuilder;
	RadioButton rButton0, rButton1, rButton2, rButton3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_matharu4);

		declaring();

	}

	private void declaring() {
		// TODO Auto-generated method stub
		TextView txt1 = (TextView) findViewById(R.id.studentDetail);
		txt1.setText(R.string.studentDetail);

		rButton0 = (RadioButton) findViewById(R.id.radio0);
		rButton0.setText(R.string.download_activity);

		rButton1 = (RadioButton) findViewById(R.id.radio1);
		rButton1.setText(R.string.video_activity);

		rButton2 = (RadioButton) findViewById(R.id.radio2);
		rButton2.setText(R.string.sms_activity);

		rButton3 = (RadioButton) findViewById(R.id.radio3);
		rButton3.setText(R.string.gallary_activity);

	}

	public void imgButt(View view) {

		try {
			if (rButton0.isChecked() || rButton1.isChecked()
					|| rButton2.isChecked() || rButton3.isChecked()) {

			} else {
				AlertDialog diaBox = SelectOption();
				diaBox.show();
			}

			if (rButton0.isChecked()) {
				Intent activityIntent = new Intent(JagdeepActivity4.this,
						Jagdeep_download.class);
				JagdeepActivity4.this.startActivity(activityIntent);
			} else if (rButton1.isChecked()) {
				Intent activityIntent = new Intent(JagdeepActivity4.this,
						Jagdeep_video.class);
				JagdeepActivity4.this.startActivity(activityIntent);
			} else if (rButton2.isChecked()) {
				Intent activityIntent = new Intent(JagdeepActivity4.this,
						Jagdeep_sms.class);
				JagdeepActivity4.this.startActivity(activityIntent);
			} else if (rButton3.isChecked()) {
				Intent activityIntent = new Intent(JagdeepActivity4.this,
						Jagdeep_gallery.class);
				JagdeepActivity4.this.startActivity(activityIntent);
			}
		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(this, R.string.error, Toast.LENGTH_LONG).show();
		}

	}

	private AlertDialog SelectOption() {
		// TODO Auto-generated method stub
		AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
				.setTitle(R.string.selectOne)
				.setMessage(R.string.dialogMessage)

				.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.dismiss();
					}
				}).create();
		return myQuittingDialogBox;
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

	//Not implementing complete method As said by professor in class (Home Key method)
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		Log.d("debug", "Screen inches : " + event.getKeyCode());
		if (keyCode == KeyEvent.KEYCODE_HOME) {

			Toast.makeText(this, "Address cannot be left blank",
					Toast.LENGTH_LONG).show();
		}
		return super.onKeyDown(keyCode, event);
	}

}
