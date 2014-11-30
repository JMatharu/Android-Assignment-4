/*Jagdeep Matharu - 300710666 - Assignment 4*/

package com.matharu.assign4;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Jagdeep_download extends Activity implements
		OnItemSelectedListener {

	Button bBack;
	String filename1 = "File1.txt";
	String filename2 = "File2.txt";
	Spinner spinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.downoad_matharu4);

		declaring();
		generatingFile1();
		generatingFile2();
		getFileName();

	}

	private void getFileName() {
		// TODO Auto-generated method stub
		String[] fileNames = getApplicationContext().fileList();
		List<String> list = new ArrayList<String>();
		list.add("Jagdeep Matharu - 2 File");
		for (int i = 0; i < fileNames.length; i++) {
			// Log.d("filename : ", fileNames[i]);

			list.add(fileNames[i]);
		}
		ArrayAdapter<String> filenameAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		spinner.setAdapter(filenameAdapter);
		spinner.setOnItemSelectedListener(this);
	}

	private void generatingFile1() {
		// TODO Auto-generated method stub
		String content1 = getResources().getString(R.string.file1Element1);
		String content2 = getResources().getString(R.string.file1Element2);
		String content3 = getResources().getString(R.string.file1Element3);
		String content4 = getResources().getString(R.string.file1Element4);
		String content5 = getResources().getString(R.string.file1Element5);
		String content6 = getResources().getString(R.string.file1Element6);

		String finalContent = content1 + "\r\n" + content2 + "\r\n" + content3
				+ "\r\n" + content4 + "\r\n" + content5 + "\r\n" + content6;

		String content = finalContent.toString();

		FileOutputStream fos = null;

		try {
			fos = openFileOutput(filename1, MODE_PRIVATE);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {

			fos.write(content.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void generatingFile2() {
		// TODO Auto-generated method stub
		String content1 = getResources().getString(R.string.file2Element1);
		String content2 = getResources().getString(R.string.file2Element2);
		String content3 = getResources().getString(R.string.file2Element3);
		String content4 = getResources().getString(R.string.file2Element4);
		String content5 = getResources().getString(R.string.file2Element5);
		String content6 = getResources().getString(R.string.file2Element6);

		String finalContent = content1 + "\r\n" + content2 + "\r\n" + content3
				+ "\r\n" + content4 + "\r\n" + content5 + "\r\n" + content6;

		String content = finalContent.toString();

		FileOutputStream fos = null;

		try {
			fos = openFileOutput(filename2, MODE_PRIVATE);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {

			fos.write(content.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void declaring() {
		// TODO Auto-generated method stub
		TextView txt1 = (TextView) findViewById(R.id.studentDetail);
		txt1.setText(R.string.studentDetail);

		bBack = (Button) findViewById(R.id.bBack);
		bBack.setText(R.string.backButton);

		spinner = (Spinner) findViewById(R.id.spinner1);
	}

	public void back(View view) {
		Intent activityIntent = new Intent(Jagdeep_download.this,
				JagdeepActivity4.class);
		Jagdeep_download.this.startActivity(activityIntent);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		TextView txtDisplay = (TextView) findViewById(R.id.txtDisplay);
		String item = parent.getItemAtPosition(position).toString();

		if (item.equals("File1.txt")) {

			String content1 = getResources().getString(R.string.file1Element1);
			String content2 = getResources().getString(R.string.file1Element2);
			String content3 = getResources().getString(R.string.file1Element3);
			String content4 = getResources().getString(R.string.file1Element4);
			String content5 = getResources().getString(R.string.file1Element5);
			String content6 = getResources().getString(R.string.file1Element6);

			String finalContent = content1 + "\r\n" + content2 + "\r\n"
					+ content3 + "\r\n" + content4 + "\r\n" + content5 + "\r\n"
					+ content6;

			String content = finalContent.toString();
			txtDisplay.setText(content);
		} else if (item.equals("File2.txt")) {
			String content1 = getResources().getString(R.string.file2Element1);
			String content2 = getResources().getString(R.string.file2Element2);
			String content3 = getResources().getString(R.string.file2Element3);
			String content4 = getResources().getString(R.string.file2Element4);
			String content5 = getResources().getString(R.string.file2Element5);
			String content6 = getResources().getString(R.string.file2Element6);

			String finalContent = content1 + "\r\n" + content2 + "\r\n"
					+ content3 + "\r\n" + content4 + "\r\n" + content5 + "\r\n"
					+ content6;

			String content = finalContent.toString();
			txtDisplay.setText(content);
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

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
				.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
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
