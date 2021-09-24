package lesson2;

public class HomeWorkApp2 {
    public static void main(String[] args) {
        int a=12, b=-2;
        String s="I want to know Java!";
        System.out.println(CheckSum(a,b));
        CheckSign(a);
        System.out.println(NegativeNumber(b));
        OutputOfLines(s, a);
        int Year=400;
        System.out.println(IsLeapYear(Year));
    }
    private static boolean CheckSum(int a, int b) {
        return (a+b)>=10 && (a+b)<=20;
    }
    private static void CheckSign(int a) {
        String Sign = a>=0 ? "the number is positive" : "the number is negative"; //'0' is positive number
        System.out.println(Sign);
    }
    private static boolean NegativeNumber(int a) {
        return a<0; //'0' is positive number
    }
    private static void OutputOfLines(String s, int a) {
        for(int i=0; i<a; i++) {
            System.out.println(s);
        }
    }
    private static boolean IsLeapYear(int Year) {
        boolean LY;
        if(Year%4==0 && Year%100!=0 || Year%400==0) LY=true;
        else LY=false;
        return LY;
    }
}
