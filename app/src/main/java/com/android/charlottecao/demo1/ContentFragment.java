package com.android.charlottecao.demo1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Charlottecao on 2017/5/11.
 */

public class ContentFragment extends Fragment {

    private TextView mTitleTextView;

    private TextView mDetailTextView;

    public static ContentFragment newInstance(String title, int detail) {
        Bundle args = new Bundle();
        ContentFragment fragment = new ContentFragment();
        args.putInt("detail", detail);
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_content, container, false);

        mTitleTextView = (TextView) view.findViewById(R.id.title);
        mTitleTextView.setText(getArguments().getString("title"));

        mDetailTextView = (TextView) view.findViewById(R.id.detail);
        mDetailTextView.setText(getArguments().getInt("detail"));

        return view;
    }

}
