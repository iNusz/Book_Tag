package com.seunghoshin.android.booktag_final;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.seunghoshin.android.booktag_final.domain.BookInfo;

import java.util.List;

/**
 * Created by SeungHoShin on 2017. 7. 3..
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.Holder> {


    private List<BookInfo> data;
    private LayoutInflater inflater;

    // 이렇게 context를 받아야지 성능상 이점이 있다  , todo 이렇게 하면 한번만 만들기 때문에 여기있는걸 참조해서 쓸수 있다 . Recyclerview를 쓸때는 항상 해줘야한다 .
    public ListAdapter(Context context, List<BookInfo> data) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
    }


    public void setData(List<BookInfo> data) {
        this.data = data;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(ListAdapter.Holder holder, int position) {
        BookInfo bbs = data.get(position);
        holder.txtTitle.setText(bbs.title);
        holder.txtAuthor.setText(bbs.author);
        holder.txtDate.setText(bbs.date+"");
        holder.position = position;
    }



    class Holder extends RecyclerView.ViewHolder {
        private int position;
        private TextView txtTitle;
        private TextView txtAuthor;
        private TextView txtDate;

        public Holder(View v) {
            super(v);
            txtTitle = (TextView) v.findViewById(R.id.txtTitle);
            txtAuthor = (TextView) v.findViewById(R.id.txtAuthor);
            txtDate = (TextView) v.findViewById(R.id.txtDate);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ReadActivity.class);
                    intent.putExtra("LIST_POSITION",position);
                    v.getContext().startActivity(intent);
                }
            });

        }


        public void setPosition(int position){
            this.position = position;
        }
        // todo 채우기
        public void setTxtTitle(String title){

        }
        public void setTxtDate(long date){

        }


    }

}
