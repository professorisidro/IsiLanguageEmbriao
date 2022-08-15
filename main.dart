import 'dart:io';

void main() {
  int a;
  int b;
  String t;
  a = 1;
  b = 1;
  if (a < b) {
    a = 0;
  } else {
    b = 0;
  }

  switch (a) {
    case 1:
      {
        b = 0;
        break;
      }
    case 2:
      {
        a = 0;
        break;
      }
    default:
      a = 1;
      break;
  }
}
