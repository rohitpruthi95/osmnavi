package khushboo.rohit.osmnavi;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by rohit on 19/1/17.
 */

public class AddButton extends Activity {

    TextView myDescription;
    boolean[] tags = {false, false, false, false};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_view);
        myDescription = (TextView)findViewById(R.id.editText);
    }

    public void addTags(View view) {
        Intent i = new Intent(getBaseContext(), AddTags.class);
        startActivityForResult(i, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2) {
            if(resultCode == Activity.RESULT_OK){
                tags = data.getBooleanArrayExtra("tags");
            }
        }
        else if (requestCode == 1) {
            if (resultCode == RESULT_OK && null != data) {

                ArrayList<String> result = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                myDescription.setText(result.get(0));
            }
        }
    }

    public void promptSpeechInputAdd(View view) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, 1);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void sendMessage(View view) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",myDescription.getText().toString());
        returnIntent.putExtra("tags", tags);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}
