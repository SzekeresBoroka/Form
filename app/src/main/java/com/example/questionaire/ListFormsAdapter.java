package com.example.questionaire;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListFormsAdapter extends RecyclerView.Adapter<ListFormsAdapter.ListFormsViewHolder>{
    private ArrayList<User>  mDataset;
    private Context context;

    public ListFormsAdapter(ArrayList<User> myDataset, Context context) {
        this.context = context;
        mDataset = myDataset;
    }

    @Override
    public ListFormsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_forms_item, parent, false);
        return new ListFormsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListFormsViewHolder holder, int position) {
        final User user = mDataset.get(position);
        String name = user.getLastname() + " " + user.getFirstname();
        holder.btn_view_form.setText(name);
        holder.itemView.setClickable(true);

        holder.btn_view_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FormFragment listFormsFragment = new FormFragment();
                //set argument for fragment
                Bundle bundle = new Bundle();
                bundle.putSerializable("user", user);
                listFormsFragment.setArguments(bundle);

                FragmentTransaction frag_trans =((MainActivity) context).getSupportFragmentManager().beginTransaction();
                frag_trans.replace(R.id.fragment_container, listFormsFragment);
                frag_trans.addToBackStack("Questionaire");
                frag_trans.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ListFormsViewHolder extends RecyclerView.ViewHolder {
        public Button btn_view_form;
        public ListFormsViewHolder(View itemView) {
            super(itemView);
            btn_view_form = itemView.findViewById(R.id.btn_view_form);
        }
    }
}

