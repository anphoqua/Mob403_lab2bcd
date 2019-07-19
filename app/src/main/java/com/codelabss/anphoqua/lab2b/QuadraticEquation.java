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

public class QuadraticEquation extends AsyncTask<Void, Void, Void> {
    Context context;
    String unknown_a, unknown_b, unknown_c, result;
    TextView tv_result;
    ProgressDialog progressDialog;

    public QuadraticEquation(Context context, String unknown_a, String unknown_b, String unknown_c, TextView tv_result) {
        this.context = context;
        this.unknown_a = unknown_a;
        this.unknown_b = unknown_b;
        this.unknown_c = unknown_c;
        this.tv_result = tv_result;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL(QuadraticActivity.SERVER_NAME);
            String param = "a=" + URLEncoder.encode(unknown_a, "utf-8") +
            "&b=" + URLEncoder.encode(unknown_b,"utf-8") +
            "&c=" + URLEncoder.encode(unknown_c, "utf-8");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setFixedLengthStreamingMode(param.getBytes().length);
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded" );

            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            printWriter.print(param);
            printWriter.close();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String line ="";
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
