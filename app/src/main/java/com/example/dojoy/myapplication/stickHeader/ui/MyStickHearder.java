package com.example.dojoy.myapplication.stickHeader.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dojoy.myapplication.R;
import com.example.dojoy.myapplication.stickHeader.SectioningAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyStickHearder extends AppCompatActivity {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    MyTestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_stick_hearder);
        ButterKnife.bind(this);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new MyTestAdapter();
        adapter.setBigNameDatas(getDatas());
        recycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private List<BigName> getDatas() {
        ArrayList<BigName> bigNames = new ArrayList<>();
                bigNames.add(BigName.builder().first("A").last("Apple").build());
                bigNames.add(BigName.builder().first("C").last("Apple").build());
                bigNames.add(BigName.builder().first("C").last("Apple").build());
                bigNames.add(BigName.builder().first("T").last("Apple").build());
                bigNames.add(BigName.builder().first("T").last("Apple").build());
                bigNames.add(BigName.builder().first("X").last("Apple").build());
                bigNames.add(BigName.builder().first("X").last("Apple").build());
                bigNames.add(BigName.builder().first("X").last("Apple").build());
                bigNames.add(BigName.builder().first("X").last("Apple").build());
                bigNames.add(BigName.builder().first("X").last("Apple").build());
                bigNames.add(BigName.builder().first("Y").last("Apple").build());
                bigNames.add(BigName.builder().first("Z").last("Apple").build());
                bigNames.add(BigName.builder().first("Z").last("Apple").build());
                bigNames.add(BigName.builder().first("Z").last("Apple").build());
        return bigNames;
    }


    class MyTestAdapter extends SectioningAdapter {
        public MyTestAdapter() {
        }

        List<BigName> bigNameDatas;
        ArrayList<Section> sections = new ArrayList<>();
        static final boolean USE_DEBUG_APPEARANCE = false;

        private class Section {
            String alpha;
            ArrayList<BigName> bigNames = new ArrayList<>();
        }

        public class ItemViewHolder extends SectioningAdapter.ItemViewHolder {
            TextView nameTextView;

            public ItemViewHolder(View itemView) {
                super(itemView);
                nameTextView = (TextView) itemView.findViewById(R.id.personNameTextView);
            }
        }

        public class HeaderViewHolder extends SectioningAdapter.HeaderViewHolder {
            TextView firstTextView;

            public HeaderViewHolder(View itemView) {
                super(itemView);
                firstTextView = (TextView) itemView.findViewById(R.id.titleTextView);
            }
        }

        public List<BigName> getBigNameDatas() {
            return bigNameDatas;
        }

        public void setBigNameDatas(List<BigName> bigNameDatas) {
            this.bigNameDatas = bigNameDatas;
            sections.clear();

            // sort people into buckets by the first letter of last name
            char alpha = 0;
            Section currentSection = null;
            for (BigName bigName : this.bigNameDatas) {
                if (bigName.first.charAt(0) != alpha) {
                    if (currentSection != null) {
                        sections.add(currentSection);
                    }
                    currentSection = new Section();
                    alpha = bigName.first.charAt(0);
                    currentSection.alpha = String.valueOf(alpha);
                }

                if (currentSection != null) {
                    currentSection.bigNames.add(bigName);
                }

            }
            if (currentSection != null) {
                sections.add(currentSection);
            }
            notifyAllSectionsDataSetChanged();
        }

        @Override
        public int getNumberOfSections() {
            return sections.size();
        }

        @Override
        public int getNumberOfItemsInSection(int sectionIndex) {
            return sections.get(sectionIndex).bigNames.size();
        }

        @Override
        public boolean doesSectionHaveHeader(int sectionIndex) {
            return true;
        }

        @Override
        public boolean doesSectionHaveFooter(int sectionIndex) {
            return false;
        }

        @Override
        public ItemViewHolder onCreateItemViewHolder(ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View v = inflater.inflate(R.layout.list_item_addressbook_person, parent, false);
            return new ItemViewHolder(v);
        }

        @Override
        public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View v = inflater.inflate(R.layout.list_item_addressbook_header, parent, false);
            return new HeaderViewHolder(v);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindItemViewHolder(SectioningAdapter.ItemViewHolder viewHolder, int sectionIndex, int itemIndex) {
            Section s = sections.get(sectionIndex);
            ItemViewHolder ivh = (ItemViewHolder) viewHolder;
            BigName bigName = s.bigNames.get(itemIndex);
            ivh.nameTextView.setText(bigName.first + bigName.last);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindHeaderViewHolder(SectioningAdapter.HeaderViewHolder viewHolder, int sectionIndex) {
            Section s = sections.get(sectionIndex);
            HeaderViewHolder hvh = (HeaderViewHolder) viewHolder;
            if (USE_DEBUG_APPEARANCE) {
                hvh.itemView.setBackgroundColor(0x55ffffff);
                hvh.firstTextView.setText(pad(sectionIndex * 2) + s.alpha);
            } else {
                hvh.firstTextView.setText(s.alpha);
            }
        }

    }

    private String pad(int spaces) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < spaces; i++) {
            b.append(' ');
        }
        return b.toString();
    }
}
