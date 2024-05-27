package edu.fpt.assignment.utils;

import android.annotation.SuppressLint;

import androidx.recyclerview.widget.RecyclerView;

import java.util.function.UnaryOperator;

public class RecycleViewUtils {
    @SuppressLint("NotifyDataSetChanged")
    public static void setup(RecyclerView.Adapter<?> adapter, RecyclerView recyclerView, UnaryOperator<RecyclerView.Adapter<?>> operator) {
        adapter = operator.apply(adapter);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
