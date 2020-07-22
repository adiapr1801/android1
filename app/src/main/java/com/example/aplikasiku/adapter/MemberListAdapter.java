package com.example.aplikasiku.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.aplikasiku.R;
import com.example.aplikasiku.fragment.HomeFragment;
import com.example.aplikasiku.service.AppService;

import java.util.ArrayList;
import java.util.List;

public class MemberListAdapter extends RecyclerView.Adapter<MemberListAdapter.MemberViewHolder> {
    private List<BookAdapter> bookAdapterList;
    private Context mcontext;
    private HomeFragment fragment;


    public MemberListAdapter(Context mcontext, HomeFragment fragment) {
        bookAdapterList = new    ArrayList<>();
        this.mcontext = mcontext;
        this.fragment = fragment;
    }

    private void add(BookAdapter item) {
        bookAdapterList.add(item);
        notifyItemInserted(bookAdapterList.size() - 1);
    }

    public void addAll(List<BookAdapter> bookAdapterList) {
        for (BookAdapter bookAdapter : bookAdapterList) {
            add(bookAdapter);
        }
    }

    @Override
    public MemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book, parent, false);
        MemberViewHolder memberViewHolder = new MemberViewHolder(view);
        return memberViewHolder;
    }

    @Override
    public void onBindViewHolder(MemberViewHolder holder, final int position) {
        final BookAdapter bookAdapter = bookAdapterList.get(position);


        Bitmap bitmap = getBitmap(bookAdapter.getThumb());

        holder.bookThumb.setImageBitmap(bitmap);
        holder.judul.setText(bookAdapter.getJudul());
        holder.penulis.setText(bookAdapter.getPenulis());

        final int bukuId = bookAdapterList.get(position).getId();

        holder.bookThumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("TAG", "onClick: " + bookAdapterList.get(position).getId() );
                fragment.openIsiFragment(bukuId);
                AppService.setIdBuku(bukuId);
            }
        });

        holder.judul.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Log.e("TAG", "long clik listener: ");
                return false;
            }
        });

        holder.bookThumb.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Log.e("TAG", "long click listener: ");
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookAdapterList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }

    static class MemberViewHolder extends RecyclerView.ViewHolder {
        ImageView bookThumb;
        TextView judul;
        TextView penulis;
        int id;

        public MemberViewHolder(View itemView) {
            super(itemView);

            bookThumb = itemView.findViewById(R.id.thumb);
            judul = itemView.findViewById(R.id.judul);
            penulis = itemView.findViewById(R.id.penulis);
        }
    }

    private Bitmap getBitmap(String base64String) {
        byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        return decodedByte;
    }
}