package com.flomonotes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private RecyclerView notesRecyclerView;
    private NotesAdapter notesAdapter;
    private List<Note> notesList;
    private FloatingActionButton fabAdd;
    private ImageView searchIcon;
    private EditText searchEditText;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupRecyclerView();
        loadNotes();
        setupClickListeners();
    }

    private void initViews() {
        notesRecyclerView = findViewById(R.id.recyclerViewNotes);
        fabAdd = findViewById(R.id.fabAdd);
        searchIcon = findViewById(R.id.searchIcon);
        searchEditText = findViewById(R.id.searchEditText);

        sharedPreferences = getSharedPreferences("FlomoNotes", Context.MODE_PRIVATE);
        notesList = new ArrayList<>();
    }

    private void setupRecyclerView() {
        notesAdapter = new NotesAdapter(notesList, this::deleteNote, this::editNote);
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        notesRecyclerView.setAdapter(notesAdapter);
    }

    private void setupClickListeners() {
        fabAdd.setOnClickListener(v -> showAddNoteDialog());

        searchIcon.setOnClickListener(v -> {
            if (searchEditText.getVisibility() == View.GONE) {
                searchEditText.setVisibility(View.VISIBLE);
                searchEditText.requestFocus();
            } else {
                searchEditText.setVisibility(View.GONE);
                searchEditText.setText("");
                loadNotes();
            }
        });

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterNotes(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void showAddNoteDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_note, null);
        EditText editTextContent = dialogView.findViewById(R.id.editTextContent);
        EditText editTextTags = dialogView.findViewById(R.id.editTextTags);

        new AlertDialog.Builder(this)
            .setTitle("添加新笔记")
            .setView(dialogView)
            .setPositiveButton("保存", (dialog, which) -> {
                String content = editTextContent.getText().toString().trim();
                String tags = editTextTags.getText().toString().trim();

                if (!content.isEmpty()) {
                    Note note = new Note(content, tags, System.currentTimeMillis());
                    addNote(note);
                } else {
                    Toast.makeText(this, "内容不能为空", Toast.LENGTH_SHORT).show();
                }
            })
            .setNegativeButton("取消", null)
            .show();
    }

    private void addNote(Note note) {
        notesList.add(0, note);
        notesAdapter.notifyItemInserted(0);
        saveNotes();
        Toast.makeText(this, "笔记已保存", Toast.LENGTH_SHORT).show();
    }

    private void editNote(int position) {
        Note note = notesList.get(position);

        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_note, null);
        EditText editTextContent = dialogView.findViewById(R.id.editTextContent);
        EditText editTextTags = dialogView.findViewById(R.id.editTextTags);

        editTextContent.setText(note.getContent());
        editTextTags.setText(note.getTags());

        new AlertDialog.Builder(this)
            .setTitle("编辑笔记")
            .setView(dialogView)
            .setPositiveButton("保存", (dialog, which) -> {
                String content = editTextContent.getText().toString().trim();
                String tags = editTextTags.getText().toString().trim();

                if (!content.isEmpty()) {
                    note.setContent(content);
                    note.setTags(tags);
                    note.setTimestamp(System.currentTimeMillis());
                    notesAdapter.notifyItemChanged(position);
                    saveNotes();
                    Toast.makeText(this, "笔记已更新", Toast.LENGTH_SHORT).show();
                }
            })
            .setNegativeButton("取消", null)
            .show();
    }

    private void deleteNote(int position) {
        new AlertDialog.Builder(this)
            .setTitle("删除笔记")
            .setMessage("确定要删除这条笔记吗？")
            .setPositiveButton("删除", (dialog, which) -> {
                notesList.remove(position);
                notesAdapter.notifyItemRemoved(position);
                saveNotes();
                Toast.makeText(this, "笔记已删除", Toast.LENGTH_SHORT).show();
            })
            .setNegativeButton("取消", null)
            .show();
    }

    private void filterNotes(String query) {
        List<Note> filteredList = new ArrayList<>();

        if (query.isEmpty()) {
            filteredList.addAll(notesList);
        } else {
            for (Note note : notesList) {
                if (note.getContent().toLowerCase().contains(query.toLowerCase()) ||
                    note.getTags().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(note);
                }
            }
        }

        notesAdapter.updateList(filteredList);
    }

    private void saveNotes() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> notesSet = new HashSet<>();

        for (Note note : notesList) {
            String noteString = note.getContent() + "|" + note.getTags() + "|" + note.getTimestamp();
            notesSet.add(noteString);
        }

        editor.putStringSet("notes", notesSet);
        editor.apply();
    }

    private void loadNotes() {
        Set<String> notesSet = sharedPreferences.getStringSet("notes", new HashSet<>());
        notesList.clear();

        for (String noteString : notesSet) {
            String[] parts = noteString.split("\\|");
            if (parts.length >= 3) {
                Note note = new Note(parts[0], parts[1], Long.parseLong(parts[2]));
                notesList.add(note);
            }
        }

        Collections.sort(notesList, (n1, n2) -> Long.compare(n2.getTimestamp(), n1.getTimestamp()));
        notesAdapter.notifyDataSetChanged();
    }
}