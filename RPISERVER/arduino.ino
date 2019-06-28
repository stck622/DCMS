#include <Servo.h>
Servo servo1; 
Servo servo2; 
String income="";
void setup() {
  Serial.begin(9600); //Baudrate을 9600으로 시리얼 통신.
  servo1.attach(8); 
  servo2.attach(9); 
}
 
void loop() {
  while(Serial.available()){    //시리얼에 읽을 값이 있으면
    income += (char)Serial.read();  //income안에 해당 내용 저장
    delay(20);
  }
  if(income != 0){  //income에 내용이 있으면
    if(income[0]=='1'){
      Serial.println(atoi(income.c_str())-1000);
       servo1.write(atoi(income.c_str())-1000);
    }
    if(income[0]=='2'){
      Serial.println(atoi(income.c_str())-2000);
       servo2.write(atoi(income.c_str())-2000);
    }
    income = "";  //전송한 income내용 초기화
  }
}
