package com.ivianuu.rxfastadapter.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.ivianuu.rxfastadapter.RxFastAdapter;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

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
                .subscribe(event -> Toast.makeText(MainActivity.this,
                        event.position() + " Clicked!", Toast.LENGTH_SHORT).show());
        compositeDisposable.add(clicks);

        Disposable longClicks = RxFastAdapter.longClicks(adapter)
                .subscribe(event -> Toast.makeText(MainActivity.this,
                        event.position() + " Long clicked!", Toast.LENGTH_SHORT).show());
        compositeDisposable.add(longClicks);

        Disposable selections = RxFastAdapter.selections(adapter)
                .subscribe(event -> Toast.makeText(MainActivity.this, event.item().getTitle()
                        + " " + event.selected(), Toast.LENGTH_SHORT).show());
        compositeDisposable.add(selections);

        Disposable launcherIconLongClicks
                = RxFastAdapter.longClicks(adapter, R.id.launcher_icon)
                .subscribe(event -> Toast.makeText(
                        MainActivity.this, event.position() + " Icon Long clicked", Toast.LENGTH_SHORT).show());
        compositeDisposable.add(launcherIconLongClicks);

        Disposable test =
                RxFastAdapter.clicks(adapter, R.id.launcher_icon)
                .subscribe(event -> Toast.makeText(
                        MainActivity.this, event.position() + " Icon clicked", Toast.LENGTH_SHORT).show());
        compositeDisposable.add(test);

        list.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
