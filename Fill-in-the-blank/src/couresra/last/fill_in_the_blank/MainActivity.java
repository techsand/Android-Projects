package couresra.last.fill_in_the_blank;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements TextWatcher {

	private static final String TAG = "MainActivity";

	private EditText name;
	private EditText place;
	private EditText hobbies;
	private EditText istory;
	private EditText SMSphone;
	private EditText comS;  //the complete story

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		name=(EditText) findViewById(R.id.name);
		place=(EditText) findViewById(R.id.place);
		hobbies=(EditText) findViewById(R.id.hobbies);
		istory=(EditText) findViewById(R.id.story);
		comS=(EditText) findViewById(R.id.story);
		SMSphone=(EditText) findViewById(R.id.phone);
		istory.addTextChangedListener(this);
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {

	}
 
	@Override
	public void afterTextChanged(Editable str) {
		
		String myStory = str.toString();
		

		
		boolean valid = myStory.length() > 0;

		View view = findViewById(R.id.submitButton);

		boolean isVisible = view.getVisibility() == View.VISIBLE;
		if (isVisible == valid) {
			return;
		}

		Animation anim;

		if (valid) {
			view.setVisibility(View.VISIBLE);
			anim = AnimationUtils.makeInAnimation(this, true);
		} else {
			//creating new object
			anim = AnimationUtils.makeOutAnimation(this, true);
			view.setVisibility(View.INVISIBLE);
		}
		// start animating
		view.startAnimation(anim);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void ShowStory(View duck) {
		//simpleExample();
		sendSMS();
	}

	public void sendSMS() {
		String cStory = "There was a man named " + name.getText().toString() + ". He lived in" + place.getText().toString() + ". He liked " + hobbies.getText().toString() + ". Some amazing incident happened with him. " +  istory.getText().toString();
		String phone = SMSphone.getText().toString();

		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.fromParts("sms", phone, null));
		intent.putExtra("sms_body", cStory);

		try {
			startActivity(intent);
			
		} catch (Exception ex) {
			Log.e(TAG, "Could not send message", ex);
		}
	}
}
