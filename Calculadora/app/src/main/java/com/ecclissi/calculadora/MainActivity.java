package com.ecclissi.calculadora;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.nex3z.flowlayout.FlowLayout;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Util;

import java.util.Locale;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class MainActivity extends Activity {


    private MediaPlayer resource, sound;
    private String calculo = "";
    private TextToSpeech ttsResult;
    private Resources r = new Resources();
    private boolean isEqual = false, isMute = false;
    private ImageButton btnSong;
    private TapTargetSequence ttsInfo;
    //private FlowLayout flowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //flowLayout = (FlowLayout) findViewById(R.id.flow_layout);

        initialOperations();
        initialNumbers();
        initialTargetSequence();

        btnSong = (ImageButton) findViewById(R.id.btnSong);

        ttsResult = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR){
                    Locale myLocal = new Locale("pt","BR");
                    ttsResult.setLanguage(myLocal);
                }
            }
        });

        sound = MediaPlayer.create(MainActivity.this, R.raw.sound);
        sound.setLooping(true);
        sound.setVolume(50.0f,50.0f);
        sound.start();
    }

    private void initialTargetSequence() {
        ttsInfo = new TapTargetSequence(this)
                .targets(
                        TapTarget.forView(findViewById(R.id.bmbNumbers),"Números")
                                .dimColor(android.R.color.white)
                                .outerCircleColor(R.color.default_bmb_shadow_color)
                                .targetCircleColor(android.R.color.transparent)
                                .textColor(android.R.color.white),
                        TapTarget.forView(findViewById(R.id.bmbOperation),"Operações")
                                .dimColor(android.R.color.white)
                                .outerCircleColor(R.color.default_bmb_shadow_color)
                                .targetCircleColor(android.R.color.transparent)
                                .textColor(android.R.color.white),
                        TapTarget.forView(findViewById(R.id.btnEqual),"Igualdade")
                                .dimColor(android.R.color.white)
                                .outerCircleColor(R.color.default_bmb_shadow_color)
                                .targetCircleColor(android.R.color.transparent)
                                .textColor(android.R.color.white),
                        TapTarget.forView(findViewById(R.id.btnSong),"Som")
                                .dimColor(android.R.color.white)
                                .outerCircleColor(android.R.color.holo_blue_light)
                                .targetCircleColor(android.R.color.transparent)
                                .textColor(android.R.color.white))
                .listener(new TapTargetSequence.Listener() {
                    @Override
                    public void onSequenceFinish() {

                    }

                    @Override
                    public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {

                    }

                    @Override
                    public void onSequenceCanceled(TapTarget lastTarget) {

                    }
                });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        sound.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sound.stop();
    }

    @Override
    protected void onStop() {
        if(sound != null)sound.pause();
        ttsResult.stop();
        ttsResult.shutdown();
        super.onStop();
    }

    private void initialOperations(){
        BoomMenuButton bmb = (BoomMenuButton) findViewById(R.id.bmbOperation);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++)
            bmb.addBuilder(getSimpleCircleButtonBuilder(true));
    }

    private void initialNumbers(){
        BoomMenuButton bmb1 = (BoomMenuButton) findViewById(R.id.bmbNumbers);
        for (int i = 0; i < 10; i++)
            bmb1.addBuilder(getSimpleCircleButtonBuilder(false));

        float w = Util.dp2px(80);
        float h = Util.dp2px(96);
        float h_0_5 = h / 2;
        float h_1_5 = h * 1.5f;

        float hm = bmb1.getButtonHorizontalMargin();
        float vm = bmb1.getButtonVerticalMargin();
        float vm_0_5 = vm / 2;
        float vm_1_5 = vm * 1.5f;

        bmb1.getCustomPiecePlacePositions().add(new PointF(Util.dp2px(+9), Util.dp2px(-9)));
        bmb1.getCustomPiecePlacePositions().add(new PointF(Util.dp2px(0), Util.dp2px(-9)));
        bmb1.getCustomPiecePlacePositions().add(new PointF(Util.dp2px(-9), Util.dp2px(-9)));

        bmb1.getCustomPiecePlacePositions().add(new PointF(Util.dp2px(-9), Util.dp2px(0)));
        bmb1.getCustomPiecePlacePositions().add(new PointF(0, 0));
        bmb1.getCustomPiecePlacePositions().add(new PointF(Util.dp2px(+9), Util.dp2px(0)));

        bmb1.getCustomPiecePlacePositions().add(new PointF(Util.dp2px(-9), Util.dp2px(+9)));
        bmb1.getCustomPiecePlacePositions().add(new PointF(Util.dp2px(0), Util.dp2px(+9)));
        bmb1.getCustomPiecePlacePositions().add(new PointF(Util.dp2px(+9), Util.dp2px(+9)));

        bmb1.getCustomPiecePlacePositions().add(new PointF(Util.dp2px(0), Util.dp2px(+18l)));


        bmb1.getCustomButtonPlacePositions().add(new PointF(-w - hm, -h_1_5 - vm_1_5));
        bmb1.getCustomButtonPlacePositions().add(new PointF(      0, -h_1_5 - vm_1_5));
        bmb1.getCustomButtonPlacePositions().add(new PointF(+w + hm, -h_1_5 - vm_1_5));
        bmb1.getCustomButtonPlacePositions().add(new PointF(-w - hm, -h_0_5 - vm_0_5));
        bmb1.getCustomButtonPlacePositions().add(new PointF(      0, -h_0_5 - vm_0_5));
        bmb1.getCustomButtonPlacePositions().add(new PointF(+w + hm, -h_0_5 - vm_0_5));
        bmb1.getCustomButtonPlacePositions().add(new PointF(-w - hm, +h_0_5 + vm_0_5));
        bmb1.getCustomButtonPlacePositions().add(new PointF(      0, +h_0_5 + vm_0_5));
        bmb1.getCustomButtonPlacePositions().add(new PointF(+w + hm, +h_0_5 + vm_0_5));
        bmb1.getCustomButtonPlacePositions().add(new PointF(      0, +h_1_5 + vm_1_5));
    }



    private SimpleCircleButton.Builder getSimpleCircleButtonBuilder(final boolean isOpera) {
        return new SimpleCircleButton.Builder()
            .normalImageRes((!isOpera)?r.getImageResource():r.getImageResourceOpera())
            .listener(new OnBMClickListener() {
                @Override
                public void onBoomButtonClick(int index) {
                    if(isOpera)songsOpera(index);
                    else {
                        songsNumbers(index);
                        addImage((char)(r.getNumber(index)+'0'));
                    }
                }
            });
    }

    private void playsound(Integer id){
        resource = MediaPlayer.create(MainActivity.this, id);
        if(!isMute)resource.start();
    }

   private void  songsNumbers(Integer i){
        playsound(r.getIdByNumber(i));
        calculo += r.getNumber(i);
   }

    private void  songsOpera(Integer i){
        playsound(r.getIdByOpera(i));
        char opera = (i==0)?'+':
                    (i==1)?'-':
                    (i==2)?'/':'*';
        addImage(opera);
        calculo += opera;
    }


    private void addImage(char id){
        FlowLayout linearLayout1 = (FlowLayout) findViewById(R.id.flow_layout);
        linearLayout1.addView(getImageView(id,200));
    }

    private View getImageView(char c, int size) {
        ImageView imageView = new ImageView(getApplicationContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 10, 0);
        lp.width = size;
        lp.height = size;
        imageView.setLayoutParams(lp);
        imageView.setImageResource(r.getIdImageByChar(c));
        imageView.setTag(c);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playsound(r.getIdAudioByChar((char) view.getTag()));

            }
        });
        return imageView;
    }

    public void showInfo(View v){
        initialTargetSequence();
        ttsInfo.start();
    }

    public void  songAct(View v){
        if(sound.isPlaying()){
            btnSong.setBackgroundResource(R.drawable.song_off);
            sound.pause();
            isMute = true;
        }else{
            btnSong.setBackgroundResource(R.drawable.song_on);
            sound.start();
            isMute = false;
        }
    }

    public void  playsoungEqual(View v){
        playsound(R.raw.igual);
        parseToImage();
        isEqual = true;
        Calculae c = new Calculae();
        calculo = c.calc(calculo);
        FlowLayout linearLayout1 = (FlowLayout) findViewById(R.id.flow_layout);
        linearLayout1.removeAllViews();

        for(int i=0;i<calculo.length();i++){
            addImage(calculo.charAt(i));
        }
        resource.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(isEqual)ttsResult.speak(calculo,TextToSpeech.QUEUE_FLUSH,null);
                isEqual = false;
            }
        });

    }

    private void parseToImage(){
        FlowLayout fl = (FlowLayout) findViewById(R.id.flResult);
        fl.removeAllViews();
        for(int i=0;i<calculo.length();i++){
            fl.addView(getImageView(calculo.charAt(i),100));
        }

    }




}
