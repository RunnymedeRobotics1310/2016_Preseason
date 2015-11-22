package robot;

public enum F310_Joystick{
  A(1, 2), 
  B(2, 3),
  X(3, 1),
  Y(4, 4), 
  LB(5, 5),
  RB(6, 6),
  LT(7, 7),
  RT(8, 8),
  BACK(9, 9),
  START(10, 10),
  LEFTSTICK_BUTTON(11, 11),
  RIGHTSTICK_BUTTON(12, 12),
  LS_XAXIS (0,0),
  LS_YAXIS (1,1),
  LS_ZAXIS (-1,2),
  RS_XAXIS (2,4),
  RS_YAXIS (3,5),
  RS_ZAXIS (-1,3);

  int dButtonNumber;
  int xButtonNumber;
  F310_Joystick(int dButtonNumber, int xButtonNumber) {
      this.dButtonNumber = dButtonNumber;
      this.xButtonNumber = xButtonNumber;
    }
}
