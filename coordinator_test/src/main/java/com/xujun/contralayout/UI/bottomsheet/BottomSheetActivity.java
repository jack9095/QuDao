package com.xujun.contralayout.UI.bottomsheet;

import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;

import com.xujun.contralayout.R;
import com.xujun.contralayout.base.BaseMVPActivity;
import com.xujun.contralayout.base.mvp.IBasePresenter;

/**
 * Created by Q.Jay on 2016/4/30 15:27
 *
 * @version 1.0.0
 */
public class BottomSheetActivity extends BaseMVPActivity implements View.OnClickListener {
    private static final String TAG = "BottomSheetActivity";
    private BottomSheetBehavior<View> behavior;



    @Override
    protected IBasePresenter setPresenter() {
        return null;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.bottom_sheet_activity;
    }

    @Override
    protected void initView() {
        setOnClickListener(this, R.id.btnBehavior, R.id.btnDialog,R.id.btn_baidumap);

        View bottomSheet = findViewById(R.id.bottom_sheet);
        if (bottomSheet != null) {
            behavior = BottomSheetBehavior.from(bottomSheet);
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBehavior:
                int state = behavior.getState();
                if (state == BottomSheetBehavior.STATE_EXPANDED) {
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                } else if(state == BottomSheetBehavior.STATE_COLLAPSED){
                    behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }else if(state == BottomSheetBehavior.STATE_HIDDEN){
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
                break;
            case R.id.btnDialog:
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
                bottomSheetDialog.setContentView(R.layout.include_bottom_sheet_layout);
                bottomSheetDialog.show();
                break;
            case R.id.btn_baidumap:
                readyGo(BaiduMapSample.class);
                break;
        }
    }
}
