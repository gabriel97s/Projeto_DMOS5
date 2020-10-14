package br.edu.ifsp.projeto_dmos5.ui.recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.edu.ifsp.projeto_dmos5.R;
import br.edu.ifsp.projeto_dmos5.model.Elephant;
import de.hdodenhof.circleimageview.CircleImageView;

public class ListElephantAdapter extends RecyclerView.Adapter<ListElephantAdapter.ElephantViewHolder> {

    private static ClickListener clickListener;

    private final List<Elephant> elephants;
    private final Context context;

    public void setClickListener(ClickListener clickListener) {
        ListElephantAdapter.clickListener = clickListener;
    }

    public ListElephantAdapter(Context context, List<Elephant> elephants) {
        this.context = context;
        this.elephants = elephants;
    }

    @Override
    public ListElephantAdapter.ElephantViewHolder onCreateViewHolder(
            ViewGroup parent
            , int viewType
    ) {
        View viewCriada = LayoutInflater.from(context)
                .inflate(R.layout.item_elephant, parent, false);
        return new ElephantViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(ListElephantAdapter.ElephantViewHolder holder, int position) {
        Elephant elephant = elephants.get(position);
        holder.fill(elephant);
    }

    @Override
    public int getItemCount() {
        return elephants.size();
    }

    static class ElephantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView textTitle;
        private final TextView textNote;
        private final CircleImageView imageOwnerPicture;

        public ElephantViewHolder(View itemView) {
            super(itemView);
            textNote = itemView.findViewById(R.id.item_elephant_note);
            textTitle = itemView.findViewById(R.id.item_elephant_title);
            imageOwnerPicture = itemView.findViewById(R.id.item_elephant_picture);

            itemView.setOnClickListener(this);
        }


        public void fill(Elephant elephant) {
            textNote.setText(elephant.getNote().substring(0, 20).concat("..."));
            textTitle.setText(elephant.getName());
            Picasso.get().load(elephant.getImage()).into(imageOwnerPicture);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onClick(getAdapterPosition());
            }
        }
    }

    public void change(List<Elephant> elephants) {
        this.elephants.clear();
        this.elephants.addAll(elephants);
        notifyDataSetChanged();
    }

    public void add(Elephant elephant) {
        elephants.add(elephant);
        notifyDataSetChanged();
    }

    public Elephant getElephant(int position) {
        return elephants.get(position);
    }
}
