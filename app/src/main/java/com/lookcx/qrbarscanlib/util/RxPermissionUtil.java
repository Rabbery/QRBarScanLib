package com.lookcx.qrbarscanlib.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;

import com.lookcx.qrbarscanlib.R;
import com.tbruyelle.rxpermissions.RxPermissions;

import rx.Observer;

/**
 * 描述: 对RxPermission进一步简化封装，提供制定权限的处理流程
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-03-28 13:44
 */

public class RxPermissionUtil {

    /**
     * 处理权限的检查申请等流程
     * @param activity
     * @param permissionName    权限名称
     */
    public static void handlePermission(final Activity activity, String permissionName) {
        if(checkPermission(activity, permissionName)) {// 具备权限
            return;
        }
        requestPermission(activity, permissionName);
//        if(PrefrenceUtil.getPermissionIsReject(permissionName)) {// 之前手动拒绝权限，提示去设置页面打开
//            showMissingPermissionDialog(activity);
//            LogUtil.logD("手动禁用 " + permissionName + " 权限");
//        } else {// 未手动拒绝权限，则打开权限
//            LogUtil.logD("手动申请 " + permissionName + " 权限");
//            requestPermission(activity, permissionName);
//        }
    }

    /**
     * 获取权限
     * @param activity
     * @param permissionName        权限名称
     */
    private static void requestPermission(final Activity activity, final String permissionName) {
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions
                .request(permissionName)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Boolean granted) {
//                        if (granted) {// 授权成功
//                            PrefrenceUtil.setPermissionIsReject(permissionName, false);// 记录用户允许权限的操作
//                        } else {// 授权失败
//                            PrefrenceUtil.setPermissionIsReject(permissionName, true);// 记录用户拒绝权限的操作
//                            ToastUtil.showDoFailToast(activity.getResources().getString(R.string.granted_fail));
//                        }
                    }
                });
    }

    /**
     * 显示提示信息
     *
     * @since 2.5.0
     *
     */
    private static void showMissingPermissionDialog(final Activity activity) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//        builder.setTitle(R.string.notifyTitle);
//        builder.setMessage(R.string.notifyMsg);
//
//        // 拒绝, 退出应用
//        builder.setNegativeButton(R.string.cancel,
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                });
//
//        builder.setPositiveButton(R.string.setting,
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        startAppSettings(activity);
//                    }
//                });
//
//        builder.setCancelable(false);
//
//        builder.show();
    }

    /**
     * 检查是否具有指定权限
     * @param activity
     * @param permissionName        权限名
     * @return
     */
    private static boolean checkPermission(Activity activity, String permissionName) {
        if(Build.VERSION.SDK_INT< Build.VERSION_CODES.M) {
            return true;
        }
        if(ContextCompat.checkSelfPermission(activity, permissionName)
                != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

    /**
     *  启动应用的设置
     *
     * @since 2.5.0
     *
     */
    private static void startAppSettings(final Activity activity) {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + activity.getPackageName()));
        activity.startActivity(intent);
    }
}
