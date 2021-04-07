
package com.example.android.materialme;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.lang.annotation.Target;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/***
 * Main Activity for the Material Me app, a mock sports news application
 * with poor design choices.
 */
public class MainActivity extends AppCompatActivity {

    private ArrayList<Sport> mSportsData;
    private SportsAdapter mAdapter;

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int grid_collumcount = getResources().getInteger(R.integer.grid_collum_count);
        // Initialize the RecyclerView.
        // Member variables.
        final RecyclerView mRecyclerView = findViewById(R.id.recyclerView);

        // Set the Layout Manager.
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, grid_collumcount));

        // Initialize the ArrayList that will contain the data.
        mSportsData = new ArrayList<>();

        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = new SportsAdapter(this, mSportsData);
        mRecyclerView.setAdapter(mAdapter);

        // Get the data.
        initializeData();
        final ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolderfrom, @NonNull RecyclerView.ViewHolder viewHolderto) {
                int from = viewHolderfrom.getAdapterPosition();
                int to = viewHolderto.getAdapterPosition();
                Collections.swap(mSportsData, from, to);
                mAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                mSportsData.remove(viewHolder.getAdapterPosition());
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        helper.attachToRecyclerView(mRecyclerView);
    }

    /**
     * Initialize the sports data from resources.
     */
    private void initializeData() {
        // Get the resources from the XML file.

        mSportsData.clear();
        String[] sportstitles = getResources()
                .getStringArray(R.array.sports_titles);
        String[] sportsInfo = getResources()
                .getStringArray(R.array.sports_info);

        TypedArray sportsimgesresource = getResources().obtainTypedArray(R.array.sportimages);

        // Clear the existing data (to avoid duplication).
        // Create the ArrayList of Sports objects with titles and
        // information about each sport.
        for (int i = 0; i < sportstitles.length; i++) {
            mSportsData.add(new Sport(sportstitles[i], sportsInfo[i],
                    sportsimgesresource.getResourceId(i, 0)));
        }
        sportsimgesresource.recycle();
        // Notify the adapter of the change.
        mAdapter.notifyDataSetChanged();
    }

    public void resetSportsitems(View view) {
        initializeData();
    }
}
