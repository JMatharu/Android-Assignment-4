/*Jagdeep Matharu - 300710666 - Assignment 4*/

package com.matharu.assign4;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Jagdeep_sms extends Activity {
	Button bBack, bSendMessage;
	EditText etPhoneNumber, etMessage;
	TextView txtStatus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sms_matharu4);

		declaring();
	}

	private void declaring() {
		// TODO Auto-generated method stub

		etPhoneNumber = (EditText) findViewById(R.id.eTPhoneNumber);
		etPhoneNumber
				.setFilters(new InputFilter[] { new InputFilter.LengthFilter(10) });

		etMessage = (EditText) findViewById(R.id.eTMessage);
		etMessage.setFilters(new InputFilter[] { new InputFilter.LengthFilter(
				240) });

		TextView txt1 = (TextView) findViewById(R.id.studentDetail);
		txt1.setText(R.string.studentDetail);

		TextView txtPhone = (TextView) findViewById(R.id.tvPhone);
		txtPhone.setText(R.string.enter_ph);

		TextView txtMsg = (TextView) findViewById(R.id.tvMsg);
		txtMsg.setText(R.string.enter_msg);

		bBack = (Button) findViewById(R.id.bBack);
		bBack.setText(R.string.backButton);

		bSendMessage = (Button) findViewById(R.id.bSendMessage);
		bSendMessage.setText(R.string.send_msg);

	}

	public void back(View view) {
		Intent activityIntent = new Intent(Jagdeep_sms.this,
				JagdeepActivity4.class);
		Jagdeep_sms.this.startActivity(activityIntent);
	}

	public void send(View view) {
		etPhoneNumber = (EditText) findViewById(R.id.eTPhoneNumber);
		etMessage = (EditText) findViewById(R.id.eTMessage);

		String phoneNo = etPhoneNumber.getText().toString();
		String message = etMessage.getText().toString();

		if (phoneNo.length() > 0 && message.length() > 0) {
			sendSMS(phoneNo, message);
		} else if (phoneNo.length() > 0 && message.length() == 0) {
			Toast.makeText(this, R.string.error1_msg, Toast.LENGTH_LONG).show();
		} else if (phoneNo.length() == 0 && message.length() > 0) {
			Toast.makeText(this, R.string.error2_msg, Toast.LENGTH_LONG).show();
		} else if (phoneNo.length() == 0 && message.length() == 0) {
			Toast.makeText(this, R.string.error3_msg, Toast.LENGTH_LONG).show();
		}
	}

	private void sendSMS(String phoneNo, String message) {
		// TODO Auto-generated method stub
		String SENT = "SMS_SENT";
		String DELIVERED = "SMS_DELIVERED";
		txtStatus = (TextView) findViewById(R.id.tvStatus);

		PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(
				SENT), 0);

		PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
				new Intent(DELIVERED), 0);

		// ---when the SMS has been sent---
		registerReceiver(new BroadcastReceiver() {
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					txtStatus.setText(R.string.notiication1);
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
					txtStatus.setText(R.string.notiication2);
					break;
				case SmsManager.RESULT_ERROR_NO_SERVICE:
					txtStatus.setText(R.string.notiication3);
					break;
				case SmsManager.RESULT_ERROR_NULL_PDU:
					txtStatus.setText(R.string.notiication4);
					break;
				case SmsManager.RESULT_ERROR_RADIO_OFF:
					txtStatus.setText(R.string.notiication5);
					break;
				}
			}
		}, new IntentFilter(SENT));

		// ---when the SMS has been delivered---
		registerReceiver(new BroadcastReceiver() {
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					txtStatus.setText(R.string.diliverd);
					break;
				case Activity.RESULT_CANCELED:
					txtStatus.setText(R.string.notdiliverd);
					break;
				}
			}
		}, new IntentFilter(DELIVERED));

		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(phoneNo, null, message, sentPI, deliveredPI);
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
