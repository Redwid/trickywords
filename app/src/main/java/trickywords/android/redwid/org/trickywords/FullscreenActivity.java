package trickywords.android.redwid.org.trickywords;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {

    private int mFailedCounter = 0;
    private int mCorrectCounter = 0;
    private int mIndex = 0;
    private String mWords[];
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

        mWords = getResources().getStringArray(R.array.words);
        mWordTextView.setText(mWords[mIndex]);
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
        if(mIndex > mWords.length - 1) {
            mFailButton.setVisibility(View.GONE);
            mCorrectButton.setVisibility(View.GONE);
        }
        else {
            mWordTextView.setText(mWords[mIndex]);
        }
    }
}
