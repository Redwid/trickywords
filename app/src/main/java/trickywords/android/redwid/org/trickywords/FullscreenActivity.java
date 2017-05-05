package trickywords.android.redwid.org.trickywords;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private final List<String> mFailedWords = new ArrayList<>();
    private TextView mWordTextView;
    private TextView mFailStatsView;
    private TextView mCorrectStatsView;
    private TextView mAllStatsView;
    private Button mFailButton;
    private Button mCorrectButton;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mFailButton = (Button) findViewById(R.id.fail_button);
        mCorrectButton = (Button) findViewById(R.id.correct_button);

        mWordTextView = (TextView) findViewById(R.id.word);
        mFailStatsView = (TextView)findViewById(R.id.fail_stats);
        mCorrectStatsView = (TextView)findViewById(R.id.correct_stats);
        mAllStatsView = (TextView)findViewById(R.id.all_stats);

        final String array[] = getResources().getStringArray(R.array.words);
        for(int i = 0; i < array.length; i++) {
            mWords.add(array[i]);
        }
        Collections.shuffle(mWords);
        mAllStatsView.setText(Integer.toString(mWords.size()));
        mWordTextView.setText(mWords.get(mIndex));
    }

    public void onFailButtonClick(final View v) {
        mFailedCounter++;
        mFailStatsView.setText(Integer.toString(mFailedCounter));
        mFailedWords.add(mWordTextView.getText().toString());
        doNextWord();
    }

    public void onCorrectButtonClick(final View v) {
        mCorrectCounter++;
        mCorrectStatsView.setText(Integer.toString(mCorrectCounter));
        doNextWord();
    }

    private void doNextWord() {
        mIndex++;
        if(mIndex > mWords.size() - 1) {
            doFinish();
        }
        else {
            mWordTextView.setText(mWords.get(mIndex));
        }
    }

    private void doFinish() {
        if(mFailedWords.size() != 0) {
            mWordTextView.setVisibility(View.GONE);
            mRecyclerView.setAdapter(new FailedWordsAdapter(mFailedWords));
        }

        mFailButton.setVisibility(View.GONE);
        mCorrectButton.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    protected class FailedWordsAdapter extends RecyclerView.Adapter<FailedWordsAdapter.ViewHolder> {

        protected class ViewHolder extends RecyclerView.ViewHolder {

            protected TextView mTextView;
            public ViewHolder(View view) {
                super(view);
                mTextView = (TextView) view.findViewById(R.id.title);
            }
        }

        private final List<String> mData;

        public FailedWordsAdapter(List<String> data) {
            mData = data;
        }

        @Override
        public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
            final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            final ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.mTextView.setText(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }
}
