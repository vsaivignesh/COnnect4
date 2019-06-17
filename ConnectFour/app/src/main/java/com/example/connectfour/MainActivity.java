package com.example.connectfour;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Integer i=0;
    Integer j=0;
    Integer h=0;
    Integer turn=0;
    Point size = new Point();
    Paint text=new Paint();
    Paint paint=new Paint();
    Paint paintBlue=new Paint();
    Paint paintRed=new Paint();
    int[][] connect4= new int[7][6];
    int rVal,cVal,dVal1,dVal2;
    int[][] moves=new int[42][2];
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(new myview(this));
    }
    private class myview extends View {
        public myview(Context context) {
            super(context);
        }
        @Override
        protected void onDraw(Canvas canvas){
            Display display = getWindowManager(). getDefaultDisplay();
            display.getSize(size);
            super.onDraw(canvas);
            paint.setColor(Color.rgb(200,200,200));
            paintBlue.setColor(Color.rgb(0,0,200));
            paintRed.setColor(Color.rgb(200,0,0));
            text.setTextSize(100);
            for (i=0;i<7;i++){for(h=0;h<6;h++){
                if(connect4[i][h]==0){
                    canvas.drawCircle((size.x/15)+2*i*size.x/15,(size.y/13)+h*(size.y/13+size.x/15), size.x/30,paint);
                }
            else if(connect4[i][h]==1){
                    canvas.drawCircle((size.x/15)+2*i*size.x/15,(size.y/13)+h*(size.y/13+size.x/15), size.x/30,paintBlue);
            }
            else{canvas.drawCircle((size.x/15)+2*i*size.x/15,(size.y/13)+h*(size.y/13+size.x/15), size.x/30,paintRed);}
            }
            }
            canvas.drawText("UNDO",2*size.x/5,8*size.y/13+size.x*6/15,text);
            



    }
            @Override
        public boolean onTouchEvent(MotionEvent event){
                int k;
                rVal=-1;
                cVal=-1;
                dVal1=-1;
                dVal2=-1;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    int locationTouch=checkTouch(event.getX(),event.getY());
                    if(locationTouch!=-1){
                    for (j = 5; j >= 0; j--) {
                        if (connect4[locationTouch][j] == 0) {
                            if (turn == 0) {
                                turn = 1;
                                connect4[locationTouch][j] = 1;
                                break;
                            } else {
                                turn = 0;
                                connect4[locationTouch][j] = 2;
                                break;
                            }
                        }
                    }
                    if(j!=-1){
                        moves[count][0]=locationTouch;
                        moves[count][1]=j;
                        count++;


                    for(k=locationTouch;k>=0;k--){
                        if(connect4[locationTouch][j]==connect4[k][j]){
                            rVal++;
                        }
                        else{break;}}
                    for(k=locationTouch;k<7;k++){
                        if(connect4[locationTouch][j]==connect4[k][j]){
                            rVal++;
                        }
                        else{break;}}
                    for(k=j;k<6;k++){
                        if(connect4[locationTouch][j]==connect4[locationTouch][k]){
                            cVal++;
                        }
                        else{break;}}
                    for(k=j;k>=0;k--){
                        if(connect4[locationTouch][j]==connect4[locationTouch][k]){
                            cVal++;
                        }
                        else{break;}}
                    k=locationTouch;
                    int k2=j;
                    while(k<7 && k2<6){
                        if(connect4[locationTouch][j]==connect4[k][k2]){
                            dVal1++;
                        }
                        else{break;}
                        k++;k2++;
                    }
                    k=locationTouch;
                    k2=j;
                    while(k>=0 && k2>=0){
                        if(connect4[locationTouch][j]==connect4[k][k2]){
                            dVal1++;
                        }
                        else{break;}
                        k--;k2--;
                    }
                    k=locationTouch;
                    k2=j;
                    while(k<7 && k2>=0){
                        if(connect4[locationTouch][j]==connect4[k][k2]){
                            dVal2++;
                        }
                        else{break;}
                        k++;k2--;
                    }
                    k=locationTouch;
                    k2=j;
                    while(k>=0 && k2<6){
                        if(connect4[locationTouch][j]==connect4[k][k2]){
                            dVal2++;
                        }
                        else{break;}
                        k--;k2++;
                    }}
                    if(rVal>=4 || cVal>=4 || dVal2>=4 || dVal1>=4){count=0;
                    if(turn==0){
                        Toast.makeText(getApplicationContext(), "Game over. Red wins", Toast.LENGTH_SHORT).show();}
                    else{
                        Toast.makeText(getApplicationContext(), "Game over. Blue wins", Toast.LENGTH_SHORT).show();
                    }
                        reset(connect4);
                            }
                    else if(count==42){count=0;
                        reset(connect4);
                    Toast.makeText(getApplicationContext(),"Draw",Toast.LENGTH_SHORT).show();}}
                    else{
                    if(count!=0){count--;}
                    connect4[moves[count][0]][moves[count][1]]=0;
                    if(turn==0){turn=1;}else{turn=0;}
                    }
                }invalidate();
            return true;
        }

        private void reset(int[][] connect4) {
            for(i=0;i<7;i++){
                for(j=0;j<6;j++) {
                    connect4[i][j] = 0;
        }}}

        private int checkTouch(float x,float y) {
            if(y<7*(size.y/13)+6*(size.x/15)){
            for(i=0;i<7;i++){if(x<2*(i+1)*size.x/15){break;}}
            if(i==7){i=6;}
        }
        else{i=-1;}
        return i;

    }
    }}