package com.example.tpmapas;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class timerHelper {
    private  static Timer timer = new Timer();
    private static boolean estaCorriendo = false;
    private  static double time;
    private  static TimerTask task;

    public static void starStop(boolean reset){
        if(!estaCorriendo) {
            if(reset) reset();
            estaCorriendo = true;
            startTimer();
        }
        else{
            estaCorriendo = false;
            task.cancel();
        }

    }
    public static void starStop(){
        starStop(false);
    }

    public static void reset(){
        if(task != null){
            task.cancel();
            time = 0.0;
            estaCorriendo = false;
        }
    }
    public static void startTimer(){
        task = new TimerTask() {
            @Override
            public void run() {
                time++;
            }
        };
        timer.scheduleAtFixedRate(task,0,1000);
    }
    public static String getTimerText(){
        int rounded = (int) Math.round(time);
        int segundos = ((rounded % 86400) % 3600)% 60;
        int minutos = ((rounded % 86400)%3600) /60;
        return formatTime(segundos,minutos);
    }

    public static String getTimerTextWithHours(){
        int rounded = (int) Math.round(time);
        int segundos = ((rounded % 86400) % 3600)% 60;
        int minutos = ((rounded % 86400)%3600) /60;
        int horas = ((rounded % 86400)/3600);
        return formatTime(segundos,minutos,horas);
    }

    private static String formatTime(int segundos, int minutos) {
        return String.format(Locale.getDefault(),"%02d:%02d",minutos,segundos);
    }
    private static String formatTime(int segundos, int minutos, int horas) {
        return String.format(Locale.getDefault(),"%02d:%02d:%02d",horas,minutos,segundos);
    }
}
