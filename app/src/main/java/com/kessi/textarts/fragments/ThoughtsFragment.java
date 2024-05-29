package com.kessi.textarts.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.kessi.textarts.R;
import com.kessi.textarts.adapter.ThoughtsAdapter;
import com.kessi.textarts.interfaces.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class ThoughtsFragment extends Fragment {
    ImageView btnBackThoughts;
    List<Integer> listThought;
    RecyclerView recyclerThoughts;
    ThoughtsAdapter thoughtsAdapter;
    ThoughtsFragmentListener thoughtsFragmentListener;

    public interface ThoughtsFragmentListener {
        void onThought(int i);
    }

    public void setThoughtListener(ThoughtsFragmentListener thoughtsFragmentListener2) {
        this.thoughtsFragmentListener = thoughtsFragmentListener2;
    }

    @Override 
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_more_thoughts, viewGroup, false);
        this.btnBackThoughts = (ImageView) inflate.findViewById(R.id.btnBackThoughts);
        recyclerThoughts = (RecyclerView) inflate.findViewById(R.id.recyclerThoughts);
        recyclerThoughts.setHasFixedSize(true);
        this.recyclerThoughts.setLayoutManager(new LinearLayoutManager(getActivity(), 1, false));
        this.listThought = genThoughts();
        thoughtsAdapter = new ThoughtsAdapter(this.listThought, getActivity(), new ItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                int intValue = listThought.get(i).intValue();
                if (thoughtsFragmentListener != null) {
                    thoughtsFragmentListener.onThought(intValue);
                    requireActivity().getSupportFragmentManager().popBackStack();
                }
            }
        });
        this.recyclerThoughts.setAdapter(thoughtsAdapter);
        this.btnBackThoughts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().popBackStack();

            }
        });
        return inflate;
    }

    private List<Integer> genThoughts() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf((int) R.string.thought1));
        arrayList.add(Integer.valueOf((int) R.string.thought2));
        arrayList.add(Integer.valueOf((int) R.string.thought3));
        arrayList.add(Integer.valueOf((int) R.string.thought4));
        arrayList.add(Integer.valueOf((int) R.string.thought5));
        arrayList.add(Integer.valueOf((int) R.string.thought6));
        arrayList.add(Integer.valueOf((int) R.string.thought7));
        arrayList.add(Integer.valueOf((int) R.string.thought8));
        arrayList.add(Integer.valueOf((int) R.string.thought9));
        arrayList.add(Integer.valueOf((int) R.string.thought10));
        arrayList.add(Integer.valueOf((int) R.string.thought11));
        arrayList.add(Integer.valueOf((int) R.string.thought12));
        arrayList.add(Integer.valueOf((int) R.string.thought13));
        arrayList.add(Integer.valueOf((int) R.string.thought14));
        arrayList.add(Integer.valueOf((int) R.string.thought15));
        arrayList.add(Integer.valueOf((int) R.string.thought16));
        arrayList.add(Integer.valueOf((int) R.string.thought17));
        arrayList.add(Integer.valueOf((int) R.string.thought18));
        arrayList.add(Integer.valueOf((int) R.string.thought19));
        arrayList.add(Integer.valueOf((int) R.string.thought20));
        arrayList.add(Integer.valueOf((int) R.string.thought21));
        arrayList.add(Integer.valueOf((int) R.string.thought22));
        arrayList.add(Integer.valueOf((int) R.string.thought23));
        arrayList.add(Integer.valueOf((int) R.string.thought24));
        arrayList.add(Integer.valueOf((int) R.string.thought25));
        arrayList.add(Integer.valueOf((int) R.string.thought26));
        arrayList.add(Integer.valueOf((int) R.string.thought27));
        arrayList.add(Integer.valueOf((int) R.string.thought28));
        arrayList.add(Integer.valueOf((int) R.string.thought29));
        arrayList.add(Integer.valueOf((int) R.string.thought30));
        arrayList.add(Integer.valueOf((int) R.string.thought31));
        arrayList.add(Integer.valueOf((int) R.string.thought32));
        arrayList.add(Integer.valueOf((int) R.string.thought33));
        arrayList.add(Integer.valueOf((int) R.string.thought34));
        arrayList.add(Integer.valueOf((int) R.string.thought35));
        arrayList.add(Integer.valueOf((int) R.string.thought36));
        arrayList.add(Integer.valueOf((int) R.string.thought37));
        arrayList.add(Integer.valueOf((int) R.string.thought38));
        arrayList.add(Integer.valueOf((int) R.string.thought39));
        arrayList.add(Integer.valueOf((int) R.string.thought40));
        arrayList.add(Integer.valueOf((int) R.string.thought41));
        arrayList.add(Integer.valueOf((int) R.string.thought42));
        arrayList.add(Integer.valueOf((int) R.string.thought43));
        arrayList.add(Integer.valueOf((int) R.string.thought44));
        arrayList.add(Integer.valueOf((int) R.string.thought45));
        arrayList.add(Integer.valueOf((int) R.string.thought46));
        arrayList.add(Integer.valueOf((int) R.string.thought47));
        arrayList.add(Integer.valueOf((int) R.string.thought48));
        arrayList.add(Integer.valueOf((int) R.string.thought49));
        arrayList.add(Integer.valueOf((int) R.string.thought50));
        arrayList.add(Integer.valueOf((int) R.string.thought51));
        arrayList.add(Integer.valueOf((int) R.string.thought52));
        arrayList.add(Integer.valueOf((int) R.string.thought53));
        arrayList.add(Integer.valueOf((int) R.string.thought54));
        arrayList.add(Integer.valueOf((int) R.string.thought55));
        arrayList.add(Integer.valueOf((int) R.string.thought56));
        arrayList.add(Integer.valueOf((int) R.string.thought57));
        arrayList.add(Integer.valueOf((int) R.string.thought58));
        arrayList.add(Integer.valueOf((int) R.string.thought59));
        arrayList.add(Integer.valueOf((int) R.string.thought60));
        arrayList.add(Integer.valueOf((int) R.string.thought61));
        arrayList.add(Integer.valueOf((int) R.string.thought62));
        arrayList.add(Integer.valueOf((int) R.string.thought63));
        arrayList.add(Integer.valueOf((int) R.string.thought64));
        arrayList.add(Integer.valueOf((int) R.string.thought65));
        arrayList.add(Integer.valueOf((int) R.string.thought66));
        arrayList.add(Integer.valueOf((int) R.string.thought67));
        arrayList.add(Integer.valueOf((int) R.string.thought68));
        arrayList.add(Integer.valueOf((int) R.string.thought69));
        arrayList.add(Integer.valueOf((int) R.string.thought70));
        arrayList.add(Integer.valueOf((int) R.string.thought71));
        arrayList.add(Integer.valueOf((int) R.string.thought72));
        arrayList.add(Integer.valueOf((int) R.string.thought73));
        arrayList.add(Integer.valueOf((int) R.string.thought74));
        arrayList.add(Integer.valueOf((int) R.string.thought75));
        arrayList.add(Integer.valueOf((int) R.string.thought76));
        arrayList.add(Integer.valueOf((int) R.string.thought77));
        arrayList.add(Integer.valueOf((int) R.string.thought78));
        arrayList.add(Integer.valueOf((int) R.string.thought79));
        arrayList.add(Integer.valueOf((int) R.string.thought80));
        arrayList.add(Integer.valueOf((int) R.string.thought81));
        arrayList.add(Integer.valueOf((int) R.string.thought82));
        arrayList.add(Integer.valueOf((int) R.string.thought83));
        arrayList.add(Integer.valueOf((int) R.string.thought84));
        arrayList.add(Integer.valueOf((int) R.string.thought85));
        arrayList.add(Integer.valueOf((int) R.string.thought86));
        arrayList.add(Integer.valueOf((int) R.string.thought87));
        arrayList.add(Integer.valueOf((int) R.string.thought88));
        arrayList.add(Integer.valueOf((int) R.string.thought89));
        arrayList.add(Integer.valueOf((int) R.string.thought90));
        return arrayList;
    }
}
