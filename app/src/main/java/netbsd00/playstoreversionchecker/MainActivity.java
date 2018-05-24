package netbsd00.playstoreversionchecker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvVersion = findViewById(R.id.tv_version);

        findViewById(R.id.btn_get_version).setOnClickListener(v -> {
            String pkgName = (String) v.getTag();
            Runnable getVersionRunnable = () -> {
                String ver = StoreVersionChecker.getStoreVersion(pkgName);
                if (!TextUtils.isEmpty(ver)) {
                    tvVersion.post(() -> tvVersion.setText(ver));
                }
            };
            new Thread(getVersionRunnable).start();
        });
    }
}
