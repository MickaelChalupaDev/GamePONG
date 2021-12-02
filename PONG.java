/***********************************
*DOWNLOAD PROCESSING LIB           *
*                                  *
*AND ADD BUILDPATH TO USE THIS GAME*
*                                  *
************************************/

import processing.core.*;

public class PONG extends PApplet {

    double PlayerPos=360;
    double PlayerSpeed=360;
    int PlayerPoint=0;
    double OpponentPos=360;
    double OpponentSpeed=0;
    int OpponentPoint=0;

    PVector ball=new PVector(640,360);
    PVector ballSpeed=new PVector(0,0);

    int c=0;

    public void setup(){


        background(0);
        float a=random(-PI/3,PI/3);
        ballSpeed.x=cos(a)*10;
        if(random(2)<1)ballSpeed.x*=-1;
        ballSpeed.y=sin(a)*10;
        textAlign(CENTER,CENTER);
        textFont(createFont("Arial",40));


    }

    public void settings() {
        size(1280,720);
    }

    public void draw(){

        noStroke();
        colorMode(RGB);
        fill(0,500);
        rect(0,0,width,height);
        colorMode(HSB,1000);
        fill(c,1000,1000);
        rect(30,(int)(PlayerPos-70),20,140);
        rect(1230,(int)(OpponentPos-70),20,140);
        ellipse(ball.x,ball.y,30,30);
        stroke(c,1000,1000);
        strokeWeight(3);
        line(640,20,640,700);
        text(PlayerPoint,600,50);
        text(OpponentPoint,680,50);
        colorMode(RGB);
        noFill();
        rect(20,20,width-40,height-40);
        c++;
        if(c>1000)c=0;

        boolean k=false;

        if(dist((float)OpponentPos,0,ball.y,0)>50){
            if(ball.y<OpponentPos){
                k=true;
                OpponentSpeed-=0.6;
            }
            if(ball.y>OpponentPos){
                k=true;
                OpponentSpeed+=0.6;
            }
            OpponentSpeed=(double)constrain((float)OpponentSpeed,-12,12);
        }
        if(!k)OpponentSpeed*=0.95;
        OpponentPos+=OpponentSpeed;
        if(OpponentPos>620){
            OpponentPos=620;
            OpponentSpeed=-OpponentSpeed*0.6;
        }
        if(OpponentPos<100){
            OpponentPos=100;
            OpponentSpeed=-OpponentSpeed*0.6;
        }
        k=false;

        if(keyPressed){
            if(keyCode==UP){
                k=true;
                PlayerSpeed-=0.6;
            }
            if(keyCode==DOWN){
                k=true;
                PlayerSpeed+=0.6;
            }
            PlayerSpeed=(double)constrain((float)PlayerSpeed,-12,12);
        }
        if(!k)PlayerSpeed*=0.95;
        PlayerPos+=PlayerSpeed;
        if(PlayerPos>620){
            PlayerPos=620;
            PlayerSpeed=-PlayerSpeed*0.6;
        }
        if(PlayerPos<100){
            PlayerPos=100;
            PlayerSpeed=-PlayerSpeed*0.6;
        }
        ball.add(ballSpeed);
        if(ball.y>675){
            ball.y=675;
            ballSpeed.y*=-1;
        }
        if(ball.y< 35){
            ball.y=35;
            ballSpeed.y*=-1;
        }
        if(ball.x<65&&ball.x>35&&ball.y>PlayerPos-85&&ball.y<PlayerPos+85){
            ball.x=65;
            float a=map((float)ball.y,(float)(PlayerPos-85),(float)(PlayerPos+85),-PI/3,PI/3);
            ballSpeed.x=cos(a)*10;
            ballSpeed.y=sin(a)*10;
        }
        if(ball.x>1215&&ball.x>1230&&ball.y>OpponentPos-85&&ball.y<OpponentPos+85){
            ball.x=1215;
            float a=map((float)ball.y,(float)(OpponentPos-85),(float)(OpponentPos+85),-PI/3,PI/3);
            ballSpeed.x=-cos(a)*10;
            ballSpeed.y=sin(a)*10;
        }
        if(ball.x< -15){
            OpponentPoint++;
            ball=new PVector(640,360);
            float a=random(-PI/3,PI/3);
            ballSpeed.x=cos(a)*10;
            if(random(2)<1)ballSpeed.x=-1;
            ballSpeed.y=sin(a)*10;
        }

        if(ball.x>1295){
            PlayerPoint++;
            ball=new PVector(640,360);
            float a=random(-PI/3,PI/3);
            ballSpeed.x=cos(a)*10;
            if(random(2)<1)ballSpeed.x=-1;
            ballSpeed.y=sin(a)*10;
        }
    }

     public static void main(String[] args) {
        PApplet.main("PONG");
    }
}

