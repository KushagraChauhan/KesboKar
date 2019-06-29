package com.kesbokar.kesbokar;

import android.content.AsyncTaskLoader;
import android.content.Context;

public class LoaderLogin extends AsyncTaskLoader<LoginInfo> {
    private String QueryId;
    private String QueryPass;
    private String BaseUrl;
    public LoaderLogin(Context context, String QueryId, String QueryPass, String url){
        super(context);
        this.QueryId = QueryId;
        this.QueryPass = QueryPass;
        BaseUrl = url;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public LoginInfo loadInBackground() {
        LoginInfo loginInfos = new LoginInfo();
        String data = (new SetHttpPost()).sendPostLogin(QueryId,QueryPass,BaseUrl);
        //call jsonParser only if the data is not null
        if(data != null){
            try {
                JsonParser json_login_info = new JsonParser();
                loginInfos = json_login_info.getLoginInfo(data);
            }catch (Throwable t){
                t.printStackTrace();
            }
            return loginInfos;
        }
        return null;
    }
}
