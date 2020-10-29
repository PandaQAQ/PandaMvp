package com.pandaq.app_jetpack.app;

import android.os.AsyncTask;

/**
 * Created by huxinyu on 2020/10/29.
 * Email : panda.h@foxmail.com
 * Description :
 */
public class TestTask extends AsyncTask<String,Integer,String> {


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}
