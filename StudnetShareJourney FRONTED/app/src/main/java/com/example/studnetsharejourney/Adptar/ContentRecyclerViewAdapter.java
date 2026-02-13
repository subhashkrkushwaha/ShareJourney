package com.example.studnetsharejourney.Adptar;

/*import androidx.recyclerview.widget.RecyclerView;

public class ContentRecycerView  {
}*/

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studnetsharejourney.Entity.JournalResponse;
import com.example.studnetsharejourney.R;

import java.util.List;

public class ContentRecyclerViewAdapter extends RecyclerView.Adapter<ContentRecyclerViewAdapter.ViewHolder> {

    private List<JournalResponse> itemList;
    private Context context;

    public ContentRecyclerViewAdapter(List<JournalResponse> itemList,Context context) {
        this.itemList = itemList;
        this.context =context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contentshow, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        JournalResponse item = itemList.get(position);
        holder.title.setText(item.getTitle());
        holder.content.setText(item.getContent());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.Content);
        }
    }
}

