Project to construct a reinforcement learning balance robot on Arduino.

The basic idea is that an Arduino Uno microcontroller with an accelerometer will sit on a chassis with two wheels. The goal is for the microcontroller to provide torque to the servos so that the robot stays balanced for as long as possible. This can be thought of as a reinforcement learning problem. The state space is the acceleration in the x, y, and z directions, and the actions are to apply a PWM pulse to the servos to move one, both, or neither wheels in the counter- or clockwise direction. The reward function is 0 at a vertical (or near vertical) position, and -1 otherwise. The robot will use temporal difference methods based on the Bellman equation to learn how to apply force in order to stay balanced as long as possible.