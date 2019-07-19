package com.codelabss.anphoqua.lab2b;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class VolumeOfCube extends AsyncTask<Void, Void, Void> {
    Context context;
    String edge, result;
    TextView tv_result;
    ProgressDialog progressDialog;

    public VolumeOfCube(Context context, String edge, TextView tv_result) {
        this.context = context;
        this.edge = edge;
        this.tv_result = tv_result;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL(CubeActivity.SERVER_NAME);
            String param = "edge=" + URLEncoder.encode(edge,"utf-8");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setFixedLengthStreamingMode(param.getBytes().length);
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded" );

            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            printWriter.print(param);
            printWriter.close();

            String line="";
            BufferedReader bufferedReader =  new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            while ((line = bufferedReader.readLine()) != null){
                stringBuffer.append(line);
            }
            result = stringBuffer.toString();

            httpURLConnection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Vui long cho trong giay lat...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        tv_result.setText(result);
    }
}
