package onem2m.android.mobiusproject;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyService extends Service {
    private boolean flag;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MYTAG", "onCreate가 호출되나?");
        flag = true;
        foregroundService();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MYTAG", "Destroy가 호출되나?");
        flag = false;
        stopForeground(true);
        this.stopSelf();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

//        Log.d("MYTAG", "서비스에서... " + MainActivity.sensorDataList.get(0).getDEVICE_SCODE());
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                while(flag) {
//                    Log.d("MYTAG", "온도 : " + MainActivity.sensorDataList.get(0).getDEVICE_FIELD01());
                    requestData();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                return null;
            }
        }.execute();
        return Service.START_REDELIVER_INTENT;
    }

    public void show_noti() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "intelli_T");
        builder.setContentTitle("히터 끄세요");
        builder.setContentText("실내 온도 20도 이상");
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);

        builder.setAutoCancel(true);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1000, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pendingIntent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(new NotificationChannel("intelli_T_notification", "intelli_T_notification", NotificationManager.IMPORTANCE_LOW));
        }

        manager.notify(1, builder.build());
    }

    public void requestData() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request newRequest  = chain.request().newBuilder()
                                .addHeader("Authorization", MainActivity.authToken)
                                .build();
                        return chain.proceed(newRequest);
                    }
                }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(API.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);
        api.getAllData().enqueue(new Callback<Sensor>() {
            @Override
            public void onResponse(Call<Sensor> call, retrofit2.Response<Sensor> response) {
                Log.d("MYTAG", response.body().getCode());
                Log.d("MYTAG", response.body().getMessage());
                Log.d("MYTAG", response.body().getTotal());
                List<Result> resultList = response.body().getResult();
                Result ourData = resultList.get(0);

                if(ourData.getDEVICE_FIELD01() > 20) {
                    show_noti();
                }
            }

            @Override
            public void onFailure(Call<Sensor> call, Throwable t) {
                Log.d("MYTAG", t.getMessage());
            }
        });
    }

    public void foregroundService() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "intelli_T"); //오레오부터는 채널id가 필요함
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);

        builder.setContentTitle("센서데이터 받아오는 중");
        builder.setContentText("3초마다 받아옴");

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 2000, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.createNotificationChannel(new NotificationChannel("intelli_T", "intelli_T", NotificationManager.IMPORTANCE_LOW ));
        }

        startForeground(2, builder.build());
    }
}
