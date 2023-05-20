package com.ict370.project_aust;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myadapter extends FirebaseRecyclerAdapter<uploadinfo,myadapter.myviewholder> {
    private onNoteListener mOnNoteListener;
    public myadapter(@NonNull FirebaseRecyclerOptions<uploadinfo> options, onNoteListener onNoteListener) {
        super(options);
        this.mOnNoteListener=onNoteListener;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder myviewholder, int i, @NonNull uploadinfo uploadinfo) {
        myviewholder.name.setText(uploadinfo.getImageName());
        myviewholder.desc.setText(uploadinfo.getImageDesc());
        myviewholder.date.setText(uploadinfo.getUploadDate());
        Glide.with(myviewholder.img.getContext()).load(uploadinfo.getImageURL()).into(myviewholder.img);
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view,mOnNoteListener);
    }



    class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView img;
        TextView name,desc,date;
        onNoteListener onNoteListener;
        public myviewholder(@NonNull View itemView,onNoteListener onNoteListener) {
            super(itemView);
            img=(ImageView) itemView.findViewById(R.id.img1);
            name=(TextView) itemView.findViewById(R.id.rName);
            desc=(TextView) itemView.findViewById(R.id.rDesc);
            date=(TextView) itemView.findViewById(R.id.rDate);
            this.onNoteListener =onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }
    public interface onNoteListener{
        void onNoteClick(int position);
    }
}

