package com.zedpine.ui;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.baasbox.android.BaasDocument;
import com.zedpine.leaguechat.R;

/**
 * Created by angelo on 6/19/15.
 */
public class AddNewPostFragment extends Fragment {

    private LayoutInflater inflater;
    private EditText mTitle;
    private EditText mContent;
    private Button button1;

    public AddNewPostFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        int i = getArguments().getInt("SettingsItem");
        Log.d("LEAGUE CHAT", "SettingsItem:" + i);
        View v = loadView(inflater, null);
        getActivity().setTitle("New Post");

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnAddNote){
            mListener = (OnAddNote)activity;
        } else {
            mListener = NOOP;
        }
    }

    private View loadView(LayoutInflater inflater, ViewGroup container) {
        View v = inflater.inflate(R.layout.add_new_post, container, false);
        mTitle = (EditText)v.findViewById(R.id.in_title);
        mContent = (EditText)v.findViewById(R.id.in_content);
        button1 = (Button)v.findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchAddNote();
                mTitle.setText("");
                mContent.setText("");
            }
        });
        return v;
    }

    public interface OnAddNote {
        public void onAddNote(BaasDocument values);
    }

    private OnAddNote mListener;

    private final static OnAddNote NOOP = new OnAddNote() {
        @Override
        public void onAddNote(BaasDocument values) {
        }
    };

    private void dispatchAddNote(){
        mListener.onAddNote(getData());
    }


    public BaasDocument getData() {
        String title = mTitle.getText().toString();
        String content  = mContent.getText().toString();
        BaasDocument doc = new BaasDocument("memos");
        doc.put("title",title);
        doc.put("content",content);
        return doc;
    }
}
