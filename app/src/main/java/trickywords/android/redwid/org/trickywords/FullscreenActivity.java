package trickywords.android.redwid.org.trickywords;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {

    private int mFailedCounter = 0;
    private int mCorrectCounter = 0;
    private int mIndex = 0;
    private final List<String> mWords = new ArrayList<>();
    private TextView mWordTextView;
    private TextView mFailStatsView;
    private TextView mCorrectStatsView;
    private Button mFailButton;
    private Button mCorrectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        mFailButton = (Button) findViewById(R.id.fail_button);
        mCorrectButton = (Button) findViewById(R.id.correct_button);

        mWordTextView = (TextView) findViewById(R.id.word);
        mFailStatsView = (TextView)findViewById(R.id.fail_stats);
        mCorrectStatsView = (TextView)findViewById(R.id.correct_stats);

        final String array[] = getResources().getStringArray(R.array.words);
        for(int i = 0; i < array.length; i++) {
            mWords.add(array[i]);
        }
        Collections.shuffle(mWords);
        mWordTextView.setText(mWords.get(mIndex));
    }

    public void onFailButtonClick(final View v) {
        mFailedCounter++;
        mFailStatsView.setText(Integer.toString(mFailedCounter));
        nextWord();
    }

    public void onCorrectButtonClick(final View v) {
        mCorrectCounter++;
        mCorrectStatsView.setText(Integer.toString(mCorrectCounter));
        nextWord();
    }

    private void nextWord() {
        mIndex++;
        if(mIndex > mWords.size() - 1) {
            mFailButton.setVisibility(View.GONE);
            mCorrectButton.setVisibility(View.GONE);
        }
        else {
            mWordTextView.setText(mWords.get(mIndex));
        }
    }
}
