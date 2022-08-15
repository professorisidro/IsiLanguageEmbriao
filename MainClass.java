import java.util.Scanner;
public class MainClass{ 
  public static void main(String args[]){
       Scanner _key = new Scanner(System.in);
int a;
int b;
String  t;
a = 1;
b = 1;
if (a<b) {
a = 0;}else {
b = 0;}

switch (a) {
case 1:
b = 0;case 2:
a = 0;default:
a = 1;

}

_key.close();  }}