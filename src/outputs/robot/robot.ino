#include <Servo.h>
Servo ojoDerX; //8
Servo ojoIzqX; //9
Servo cabeza; //10
Servo ojoDerY; //11
Servo boca; //12
Servo ojoIzqY; //4
void setup(){
Serial.begin(9600);
ojoDerX.attach(7);
ojoDerX.write(90);
ojoDerY.attach(2);
ojoDerY.write(60);
ojoIzqX.attach(3);
ojoIzqX.write(90);
ojoIzqY.attach(6);
ojoIzqY.write(90);
boca.attach(5);
boca.write(105);
cabeza.attach(4);
cabeza.write(90);}
void loop(){
