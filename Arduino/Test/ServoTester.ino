#include <Servo.h>
Servo servo1;
int servo1pin = 13;
int position = 0;
void setup() {
  // put your setup code here, to run once:
  servo1.attach(servo1pin);
}

void loop() {
  //BotBrainServoTest();
}


void HiTechServoTest() {
  servo1.write(abs(180-position));
  delay(50);
  position = (position + 5) % 360;
}

void BotBrainServoTest() {
  servo1.write(180);
  delay(500);
  //servo1.write(0);
  delay(500);
  servo1.write(92);
  delay(500);
}

