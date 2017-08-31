package com.ivianuu.rxfastadapter.sample;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

/**
 * Sample item
 */
class SampleItem extends AbstractItem<SampleItem, SampleItem.Holder> {

    private final String title;

    SampleItem(@NonNull String title) {
        this.title = title;
    }

    /**
     * Returns the title
     */
    @NonNull
    String getTitle() {
        return title;
    }

    @Override
    public void bindView(Holder holder, List<Object> payloads) {
        super.bindView(holder, payloads);
        holder.title.setText(title);
    }

    @Override
    public Holder getViewHolder(View v) {
        return new Holder(v);
    }

    @SuppressLint("ResourceType")
    @Override
    public int getType() {
        return 1;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_sample;
    }

    static class Holder extends RecyclerView.ViewHolder {

        private final ImageButton launcherIcon;
        private final TextView title;

        Holder(View itemView) {
            super(itemView);
            this.launcherIcon = itemView.findViewById(R.id.launcher_icon);
            this.title = itemView.findViewById(R.id.title);
        }
    }
}
