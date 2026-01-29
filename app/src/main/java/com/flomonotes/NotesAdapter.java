package com.flomonotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {
    private List<Note> notesList;
    private OnNoteDeleteListener deleteListener;
    private OnNoteEditListener editListener;

    public interface OnNoteDeleteListener {
        void onDelete(int position);
    }

    public interface OnNoteEditListener {
        void onEdit(int position);
    }

    public NotesAdapter(List<Note> notesList, OnNoteDeleteListener deleteListener, OnNoteEditListener editListener) {
        this.notesList = notesList;
        this.deleteListener = deleteListener;
        this.editListener = editListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notesList.get(position);

        holder.textContent.setText(note.getContent());
        holder.textTags.setText(note.getTags().isEmpty() ? "" : "#" + note.getTags().replace(" ", " #"));
        holder.textDate.setText(note.getFormattedDate());

        if (note.getTags().isEmpty()) {
            holder.textTags.setVisibility(View.GONE);
        } else {
            holder.textTags.setVisibility(View.VISIBLE);
        }

        holder.cardView.setOnClickListener(v -> editListener.onEdit(position));
        holder.cardView.setOnLongClickListener(v -> {
            deleteListener.onDelete(position);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public void updateList(List<Note> filteredList) {
        notesList = filteredList;
        notifyDataSetChanged();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView textContent;
        TextView textTags;
        TextView textDate;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            textContent = itemView.findViewById(R.id.textContent);
            textTags = itemView.findViewById(R.id.textTags);
            textDate = itemView.findViewById(R.id.textDate);
        }
    }
}