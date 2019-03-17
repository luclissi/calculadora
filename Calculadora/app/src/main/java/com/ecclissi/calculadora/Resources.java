package com.ecclissi.calculadora;

public class Resources {

    private Integer[] imageResources = new Integer[]{
            R.drawable.nove,
            R.drawable.oito,
            R.drawable.sete,
            R.drawable.seis,
            R.drawable.cinco,
            R.drawable.quatro,
            R.drawable.tres,
            R.drawable.dois,
            R.drawable.um,
            R.drawable.zero
    };

    private Integer[] audioResources = new Integer[]{
            R.raw.nove,
            R.raw.oito,
            R.raw.sete,
            R.raw.seis,
            R.raw.cinco,
            R.raw.quatro,
            R.raw.tres,
            R.raw.dois,
            R.raw.um,
            R.raw.zero
    };

    private Integer[] imageResourcesOpera = new Integer[]{
            R.drawable.mais,
            R.drawable.menos
            // R.drawable.dividido,
            //  R.drawable.vezes
    };

    private Integer[] audioResourcesOpera = new Integer[]{
            R.raw.mais,
            R.raw.menos,
            R.raw.dividido,
            R.raw.vezes
    };

    private int imageResourceIndex = 0,imageResourceOperaIndex = 0 ;

    public int getIdResource(int index){
        if(index==9)return R.drawable.zero;
        else if(index==8)return R.drawable.um;
        else if(index==7)return R.drawable.dois;
        else if(index==6)return R.drawable.tres;
        else if(index==5)return R.drawable.quatro;
        else if(index==4)return R.drawable.cinco;
        else if(index==3)return R.drawable.seis;
        else if(index==2)return R.drawable.sete;
        else if(index==1)return R.drawable.oito;
        else return R.drawable.nove;
    }

    public int getImageResource() {
        if (imageResourceIndex >= imageResources.length) imageResourceIndex = 0;
        return imageResources[imageResourceIndex++];
    }

    public int getImageResourceOpera() {
        if (imageResourceOperaIndex >= imageResourcesOpera.length) imageResourceOperaIndex = 0;
        return imageResourcesOpera[imageResourceOperaIndex++];
    }
    public int getIdResourceOpera(int index){
        if(index==0)return R.drawable.mais;
        else if(index==1)return R.drawable.menos;
        else if(index==2)return R.drawable.dividido;
        else return R.drawable.vezes;
    }

    public int getIdAudioByChar(char c){
        return  (c=='0')?R.raw.zero:
                (c=='1')?R.raw.um:
                (c=='2')?R.raw.dois:
                (c=='3')?R.raw.tres:
                (c=='4')?R.raw.quatro:
                (c=='5')?R.raw.cinco:
                (c=='6')?R.raw.seis:
                (c=='7')?R.raw.sete:
                (c=='8')?R.raw.oito:
                (c=='9')?R.raw.nove:
                (c=='+')?R.raw.mais:
                (c=='-')?R.raw.menos:
                (c=='*')?R.raw.vezes:R.raw.dividido;
    }

    public int getIdImageByChar(char c){
        return  (c=='0')?R.drawable.zero:
                (c=='1')?R.drawable.um:
                (c=='2')?R.drawable.dois:
                (c=='3')?R.drawable.tres:
                (c=='4')?R.drawable.quatro:
                (c=='5')?R.drawable.cinco:
                (c=='6')?R.drawable.seis:
                (c=='7')?R.drawable.sete:
                (c=='8')?R.drawable.oito:
                (c=='9')?R.drawable.nove:
                (c=='+')?R.drawable.mais:
                (c=='-')?R.drawable.menos:
                (c=='*')?R.drawable.vezes:R.drawable.dividido;
    }
    public int getIdByOpera(Integer i){
        return audioResourcesOpera[i];
    }

    public  int getIdByNumber(Integer i){
        return audioResources[i];
    }

    public int getNumber(int i){
        return
            (i==9)?0:
                i==8 ?1:
                i==7 ?2:
                i==6 ?3:
                i==5 ?4:
                i==4 ?5:
                i==3 ?6:
                i==2 ?7:
                i==1 ?8 :9;
    }
}
