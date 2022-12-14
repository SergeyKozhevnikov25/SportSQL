package com.example.sportsql;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NumbersAdapter numbersAdapter;
    private EditText etTeam1, etTeam2, etScore, etDate;
    private Dialog dialog;
    //for room
    private MatchDatabase matchDatabase;
    private MatchDao matchDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        // for room
        matchDatabase = Room.databaseBuilder(getApplicationContext(),
                MatchDatabase.class, "database").allowMainThreadQueries().build();
        matchDao = matchDatabase.matchDao();

        numbersAdapter = new NumbersAdapter(matchDao.getAll().size(), this, matchDao.getAll());
        recyclerView.setAdapter(numbersAdapter);

        setDialog();
    }

    private void setDialog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        etTeam1 = dialog.findViewById(R.id.et_team1);
        etTeam2 = dialog.findViewById(R.id.et_team2);
        etScore = dialog.findViewById(R.id.et_score);
        etDate = dialog.findViewById(R.id.et_date);
    }

    public void onClickGson(View view) throws JSONException {
        Intent intent = new Intent(MainActivity.this, GSonActivity.class);
            JSONObject jResult = new JSONObject();
            JSONArray jArray = new JSONArray();
            JSONObject jOuter = new JSONObject();
            for (int i = 0; i < matchDao.getAll().size(); i++) {
                JSONObject jGroup = new JSONObject();
                jGroup.put("command1", matchDao.getAll().get(i).team1);
                jGroup.put("command2", matchDao.getAll().get(i).team2);
                jGroup.put("score", matchDao.getAll().get(i).score);
                jGroup.put("date", matchDao.getAll().get(i).date);
                jOuter.put("X", jGroup);
                jArray.put(jGroup);
                jResult.put("matches", jArray);
            }
    String s = jResult.toString();
intent.putExtra("json", s);
        startActivity(intent);
    }

    public void onClickAdd(View view) {
        dialog.show();
    }

    public void onClickSave(View view) {
        Match match = new Match();
        match.team1 = etTeam1.getText().toString();
        match.date = etDate.getText().toString();
        match.team2 = etTeam2.getText().toString();
        match.score = etScore.getText().toString();
        matchDao.insert(match);
        Log.e("MyLog", "added");
        Log.e("MyLog", "matchDao.getAll()   " + matchDao.getAll().size());
        numbersAdapter.setMatches(matchDao.getAll());
        numbersAdapter.setNumberItems(matchDao.getAll().size());
        numbersAdapter.notifyDataSetChanged();
        dialog.dismiss();
    }

    public void onClickCancel(View view) {
        dialog.dismiss();
    }
}