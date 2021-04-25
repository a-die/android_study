package com.hnucm.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;


public class MessageFragment extends Fragment {
    RecyclerView recyclerView;
    List<Chat> chatList = new ArrayList<>();
    MyAdapter myAdapter;
    SmartRefreshLayout smartRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = getActivity().findViewById(R.id.recyclerview);
//        无下拉功能
//        上拉刷新，数据到底  重新加载新数据
//        开源
        for (int i = 0; i < 10; i++) {
            Chat chat = new Chat();
            chat.setTime("下午4：00");
            chat.setContent("content" + i);
            chat.setName("name" + i);
            chat.setImg(R.drawable.a1);
            chatList.add(chat);
        }
        myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        smartRefreshLayout=getActivity().findViewById(R.id.SmartRefreshLayout);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
//                todo 清除数据
                chatList.clear();
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                for (int i = 0; i < 10; i++) {
                    Chat chat = new Chat();
                    chat.setTime("下午4：00  new");
                    chat.setContent("content  new" + i);
                    chat.setName("name  new" + i);
                    chat.setImg(R.drawable.a1);
                    chatList.add(chat);
                }
                myAdapter.notifyDataSetChanged();
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
//                todo 添加数据
//                重新添加数据
                for (int i = 10; i < 20; i++) {
                    Chat chat = new Chat();
                    chat.setTime("下午4：00");
                    chat.setContent("content" + i);
                    chat.setName("name" + i);
                    chat.setImg(R.drawable.a1);
                    chatList.add(chat);
                }
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                myAdapter.notifyDataSetChanged();
            }
        });
    }


    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_chat, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.nameTv.setText(chatList.get(position).getName());
            holder.contentTv.setText(chatList.get(position).getContent());
            holder.timeTv.setText(chatList.get(position).getTime());
            holder.imageView.setImageResource(chatList.get(position).getImg());
//          getActivity       如果当前类继承了Activity，就用当前类.this
            //Glide.with(getActivity()).load("https://p3.ssl.qhimgs1.com/sdr/400__/t0188e5449327de5e33.jpg").into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return chatList.size();
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameTv;
        TextView contentTv;
        TextView timeTv;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.nametextview);
            contentTv = itemView.findViewById(R.id.contenttextview);
            timeTv = itemView.findViewById(R.id.timetextview);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

}