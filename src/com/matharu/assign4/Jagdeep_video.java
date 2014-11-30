/*Jagdeep Matharu - 300710666 - Assignment 4*/
package com.matharu.assign4;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Video;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class Jagdeep_video extends Activity implements OnCompletionListener {
	Button bBack, bPause;
	int dataIdx = 0;
	TextView txtFilename;
	VideoView videoView = null;
	private List<String> v = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video_matharu);

		declaring();

		videoView = (VideoView) findViewById(R.id.videoview);

		try {
			videoView.setOnCompletionListener((OnCompletionListener) this);
			String projection[] = new String[] { Video.Media.DATA };

			String Path = Environment.getExternalStorageDirectory()
					+ "/Videos/";
			File f = new File(Path);
			if (f.isDirectory()) {
				String[] files = f.list();
				for (int i = 0; i < files.length; i++) {
					if (files[i].endsWith(".3gp")) {
						v.add(Environment.getExternalStorageDirectory()
								+ "/Videos/" + files[i]);
					}
				}
			}
			if (v.size() > 0) {
				bPause.setEnabled(true);
				// videoView.layout(l, t, r, b)
				File file = new File(v.get(dataIdx));
				txtFilename.setText(file.getName());

				videoView.setVideoPath(v.get(dataIdx++));
				videoView.setMediaController(new MediaController(this));
				videoView.requestFocus();
				videoView.start();
			} else {
				txtFilename.setText(R.string.txtVideoFile);
				bPause.setEnabled(false);
			}
		} catch (Exception e) {
			// TODO: handle exception
			String x = e.toString();
			String b = x;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.jagdeep_video, menu);
		return true;
	}

	private void declaring() {
		// TODO Auto-generated method stub
		TextView txt1 = (TextView) findViewById(R.id.studentDetail);
		txt1.setText(R.string.studentDetail);

		bBack = (Button) findViewById(R.id.bBack);
		bBack.setText(R.string.backButton);

		bPause = (Button) findViewById(R.id.bPause);
		bPause.setText(R.string.bPause);

		txtFilename = (TextView) findViewById(R.id.txtFileName);

	}

	public void back(View view) {
		Intent activityIntent = new Intent(Jagdeep_video.this,
				JagdeepActivity4.class);
		Jagdeep_video.this.startActivity(activityIntent);
	}

	public void pause(View view) {
		if (videoView.isPlaying()) {
			videoView.pause();
			bPause.setText(R.string.bPause);
		} else {
			// videoView.resume();
			videoView.start();
			bPause.setText(R.string.bPlay);
		}
	}

	@Override
	public void onCompletion(MediaPlayer arg0) {
		// TODO Auto-generated method stub
		playNextVideo();
	}

	private void playNextVideo() {
		// TODO Auto-generated method stub
		if (dataIdx < v.size()) {
			File file = new File(v.get(dataIdx));
			txtFilename.setText(file.getName());

			videoView.setVideoPath(v.get(dataIdx++));
			videoView.setMediaController(new MediaController(this));
			videoView.requestFocus();
			videoView.start();
		}
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
