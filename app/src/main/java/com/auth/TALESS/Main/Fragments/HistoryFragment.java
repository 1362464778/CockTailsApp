package com.auth.TALESS.Main.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auth.TALESS.DataClasses.CocktailRecipe;
import com.auth.TALESS.Database.SenpaiDB;
import com.auth.TALESS.Main.Activities.MainActivity;
import com.auth.TALESS.Main.Adapters.GeneralAdapter;
import com.auth.TALESS.R;

import java.util.ArrayList;

/**
 * This fragment sets a background if history is empty
 * and if it is not it takes all history recipes from SenpaiDB
 * and shows them in a RecyclerView
 */
public class HistoryFragment extends Fragment {
    private SenpaiDB db;
    private ArrayList<CocktailRecipe> recipes;
    private ConstraintLayout background;
    // The toolbar is not used in the original code, so it can be removed if you wish.
    private androidx.appcompat.widget.Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 1. Inflate the new history layout file
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = SenpaiDB.getInstance(requireContext());

        // 2. Call the new database method for history
        recipes = db.getHistoryRecipes(MainActivity.currentUserId);

        // 3. Find the new layout ID
        background = view.findViewById(R.id.HistoryConstraintLayout);

        if (!recipes.isEmpty())
            background.setBackground(null);

        // 4. Find the new RecyclerView ID
        RecyclerView rv = view.findViewById(R.id.historyRecyclerView);

        rv.setLayoutManager(new LinearLayoutManager(requireActivity()));
        // We can reuse GeneralAdapter as it's suitable for both lists
        rv.setAdapter(new GeneralAdapter(requireContext(), recipes));
    }

    @Override
    public void onResume() {
        super.onResume();
        // You can add logic here to refresh the list if needed,
        // but for now, it's fine to leave it empty.
    }
}