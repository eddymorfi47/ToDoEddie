package com.eddiemartnez.app.todoeddie.Subclase;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.eddiemartnez.app.todoeddie.R;

import org.sufficientlysecure.htmltextview.HtmlTextView;

/**
 * Created by Eddie Martínez on 26/2/2018.
 */

public class ToDoViewHolder extends RecyclerView.ViewHolder {

    public HtmlTextView html;

    public ToDoViewHolder(View itemView){
        super(itemView);
        html=itemView.findViewById(R.id.html_text);


    }
}
