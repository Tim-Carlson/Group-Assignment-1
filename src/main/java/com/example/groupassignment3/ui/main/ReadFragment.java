package com.example.groupassignment3.ui.main;
import com.example.groupassignment3.MainActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;


import com.example.groupassignment3.R;


public class ReadFragment extends Fragment {

    private MainActivity activity;
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final int INDEX = 1;
    private PageViewModel pageViewModel;

    private Button search;
    private TextView fileAddressRead;

    public static ReadFragment newInstance() {
        ReadFragment fragment = new ReadFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, INDEX);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = (MainActivity) getActivity();


        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;

        if (getArguments() != null ) {
                index = getArguments().getInt(ARG_SECTION_NUMBER);

            if (getArguments().containsKey("fileLocation")) {
                System.err.println("this happened!!");
            }
        }
        pageViewModel.setIndex(index);





    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_read, container, false);
        final TextView textView = v.findViewById(R.id.section_label);

        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        search = v.findViewById(R.id.search);
        fileAddressRead = v.findViewById(R.id.file_address_read);


        return v;
    }

    @Override
    public void onViewCreated(View v, @Nullable final Bundle savedInstanceState) {

        search = v.findViewById(R.id.search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               activity.performSearch();

            }
        });


    }





}