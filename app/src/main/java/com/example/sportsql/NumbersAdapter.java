package com.example.sportsql;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class NumbersAdapter extends RecyclerView.Adapter<NumbersAdapter.NumberViewHolder> {

    private int numberItems;
    private final Context context;
    private List<Match> matches;

    public NumbersAdapter(int numberItems, Context context, List<Match> matches) {
        this.numberItems = numberItems;
        this.context = context;
        this.matches = matches;
    }

    public void setNumberItems(int numberItems) {
        this.numberItems = numberItems;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    @Override
    public int getItemCount() {
        return numberItems;
    }


    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutIdForListItem = R.layout.number_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new NumberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {

        Log.e("MyLog", "on bind");
        holder.bind(position);
    }



    class NumberViewHolder extends RecyclerView.ViewHolder {

        TextView tvTeam1, tvTeam2, tvScore, tvData;

        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTeam1 = itemView.findViewById(R.id.tv_team1);
            tvTeam2 = itemView.findViewById(R.id.tv_team2);
            tvScore = itemView.findViewById(R.id.tv_score);
            tvData = itemView.findViewById(R.id.tv_date);


            // слушатель на нажатие, только ЛОГ
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("MyLog", "click");
                }
            });
        }

        void bind(int item) {
            Log.e("MyLog", "bind");
            Match match = matches.get(item);
            tvTeam1.setText("Команда 1: " + match.team1);
            tvTeam2.setText("Команда 2: " + match.team2);
            tvScore.setText("Счёт: " + match.score);
            tvData.setText("Дата матча: " + match.date);
            Log.e("MyLog", "bind1");
        }
    }
}
