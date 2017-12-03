package com.aib.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xxx.design.R;

/**
 * Created by HLP on 2017/12/3.
 */

public class TeacherQuizAdapter extends RecyclerView.Adapter<TeacherQuizAdapter.ViewHolder> {

    private Context ctx;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ctx = parent.getContext();
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_teacher_quiz, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
