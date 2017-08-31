package com.ivianuu.rxfastadapter.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.ivianuu.rxfastadapter.click.ClickEvent;
import com.ivianuu.rxfastadapter.clickeventhook.ClickEventHookEvent;
import com.ivianuu.rxfastadapter.eventhookcallback.IdEventHookCallback;
import com.ivianuu.rxfastadapter.longclick.LongClickEvent;
import com.ivianuu.rxfastadapter.RxFastAdapter;
import com.ivianuu.rxfastadapter.longclickeventhook.LongClickEventHookEvent;
import com.ivianuu.rxfastadapter.selection.SelectionEvent;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    private FastItemAdapter<SampleItem> adapter;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));

        adapter = new FastItemAdapter<>();
        adapter.withSelectable(true);
        adapter.withSelectOnLongClick(true);

        for (int i = 0; i < 100; i++) {
            SampleItem sampleItem = new SampleItem("Title " + i);
            adapter.add(sampleItem);
        }

        Disposable clicks = RxFastAdapter.clicks(adapter)
                .subscribe(new Consumer<ClickEvent<SampleItem>>() {
                    @Override
                    public void accept(ClickEvent<SampleItem> sampleItemClickEvent) throws Exception {
                        Toast.makeText(MainActivity.this,
                                sampleItemClickEvent.position() + " Clicked!", Toast.LENGTH_SHORT).show();
                    }
                });
        compositeDisposable.add(clicks);

        Disposable longClicks = RxFastAdapter.longClicks(adapter)
                .subscribe(new Consumer<LongClickEvent<SampleItem>>() {
                    @Override
                    public void accept(LongClickEvent<SampleItem> sampleItemLongClickEvent) throws Exception {
                        Toast.makeText(MainActivity.this,
                                sampleItemLongClickEvent.position() + " Long clicked!", Toast.LENGTH_SHORT).show();
                    }
                });
        compositeDisposable.add(longClicks);

        Disposable selections = RxFastAdapter.selections(adapter)
                .subscribe(new Consumer<SelectionEvent<SampleItem>>() {
                    @Override
                    public void accept(SelectionEvent<SampleItem> sampleItemSelectionEvent) throws Exception {
                        Toast.makeText(MainActivity.this, sampleItemSelectionEvent.item().getTitle()
                                + " " + sampleItemSelectionEvent.selected(), Toast.LENGTH_SHORT).show();
                    }
                });
        compositeDisposable.add(selections);

        Disposable launcherIconLongClicks
                = RxFastAdapter.longClickEventHooks(adapter, new SampleItem.LauncherIconEventCallback())
                .subscribe(new Consumer<LongClickEventHookEvent<SampleItem>>() {
                    @Override
                    public void accept(LongClickEventHookEvent<SampleItem> sampleItemLongClickEventHookEvent) throws Exception {
                        Toast.makeText(MainActivity.this, sampleItemLongClickEventHookEvent.position() + " Icon Long clicked", Toast.LENGTH_SHORT).show();
                    }
                });
        compositeDisposable.add(launcherIconLongClicks);

        Disposable test =
                RxFastAdapter.clickEventHooks(adapter, IdEventHookCallback.with(R.id.launcher_icon))
                .subscribe(new Consumer<ClickEventHookEvent<SampleItem>>() {
                    @Override
                    public void accept(ClickEventHookEvent<SampleItem> sampleItemClickEventHookEvent) throws Exception {
                        Toast.makeText(MainActivity.this, sampleItemClickEventHookEvent.position() + " Icon clicked", Toast.LENGTH_SHORT).show();
                    }
                });
        compositeDisposable.add(test);

        list.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
