package com.share.tencentShare;

import java.util.ArrayList;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;

import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

public class TencentShare extends CordovaPlugin {
  public CallbackContext callbackContext;

  private String title;
  private String summary;
  private String target_url;
  private String image_url;

  public static Tencent mTencent = null;

  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    try {
      this.callbackContext = callbackContext;
      JSONObject jsonObject = args.getJSONObject(0);
      String appid = jsonObject.getString("appid");
      if (mTencent == null) {
        mTencent = Tencent.createInstance(appid, this.cordova.getActivity());
      }
      title = jsonObject.getString("title");
      summary = jsonObject.getString("summary");
      target_url = jsonObject.getString("target_url");
      image_url = jsonObject.getString("image_url");

      JSONObject result = new JSONObject();
      result.put("result", true);

      if (action.equals("qqShare")) {
        sharetoQQ(title, summary, target_url, image_url);
        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, result));
        return true;
      } else if (action.equals("qzoneShare")) {
        sharetoQzone(title, summary, target_url, image_url);
        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, result));
        return true;
      } else {
        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR));
        return false;
      }
    } catch (JSONException e) {
      Log.e("Protonet", "JSON Exception Plugin... :(");
    }
    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR));
    return false;
  }

  public void sharetoQQ(String title, String summary, String target_url, String image_url) {
    Bundle bundle = new Bundle();
    bundle.clear();
    bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
    bundle.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE);
    bundle.putString(QQShare.SHARE_TO_QQ_TITLE, title);
    bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, summary);
    bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, target_url);
    bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, image_url);
    mTencent.shareToQQ(this.cordova.getActivity(), bundle, new BaseUiListener());
  }

  public void sharetoQzone(String title, String summary, String target_url, String image_url) {
    Bundle bundle = new Bundle();
    bundle.clear();
    bundle.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
    bundle.putInt(QzoneShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE);
    bundle.putString(QzoneShare.SHARE_TO_QQ_TITLE, title);
    bundle.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, summary);
    bundle.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, target_url);
    ArrayList<String> imageUrls = new ArrayList<String>();
    imageUrls.add(image_url);
    bundle.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imageUrls);
    mTencent.shareToQzone(this.cordova.getActivity(), bundle, new BaseUiListener());
  }

  public class BaseUiListener implements IUiListener {

    @Override
    public void onCancel() {
      System.out.println("onCancel");
    }

    @Override
    public void onComplete(Object arg0) {
      System.out.println("onComplete");

    }

    @Override
    public void onError(UiError arg0) {
      System.out.println("onError");
    }

  }

}
