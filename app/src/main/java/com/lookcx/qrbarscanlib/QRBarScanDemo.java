package com.lookcx.qrbarscanlib;

import android.Manifest;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lookcx.qrbarscanlib.util.RxPermissionUtil;
import com.zbar.lib.CaptureContextActivity;

/**
 * Created by lookchoxun on 2018/11/7
 * <p>
 * 扫描示例代码
 */
public class QRBarScanDemo extends CaptureContextActivity {

    private SurfaceView mSurfaceView;
    private ImageView mScanLine;
    private RelativeLayout mContainter;
    private LinearLayout mCapturelayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RxPermissionUtil.handlePermission(this, Manifest.permission.CAMERA);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrbardemo);
        mContainter = findViewById(R.id.capture_containter);
        mSurfaceView = findViewById(R.id.capture_preview);
        mScanLine = findViewById(R.id.capture_scan_line);
        mCapturelayout = findViewById(R.id.capture_crop_layout);

        findViewById(R.id.restart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reStartScan();
            }
        });
        findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopScan();
            }
        });
        findViewById(R.id.openlight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                light();
            }
        });
    }

    @Override
    public SurfaceView getSurfaceView() {
        return mSurfaceView;
    }

    @Override
    protected ImageView getQrLineView() {
        return mScanLine;
    }

    @Override
    protected RelativeLayout getContainerView() {
        return mContainter;
    }

    @Override
    protected LinearLayout getCropView() {
        return mCapturelayout;
    }

    @Override
    public void handleDecodeResult(String result) {
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
    }
}
