/*
键盘输入三个整数，使用if-else,按照从小到大进行排序
*
* */
import java.util.Scanner;
public class IfScannerSortTest {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in) ;
        System.out.println("请输入第一个整数:");
        int num1=scanner.nextInt();
        System.out.println("请输入第二个整数:");
        int num2=scanner.nextInt();
        System.out.println("请输入第三个整数:");
        int num3=scanner.nextInt();
        if(num1>num2){
            if(num3>num1){
                System.out.println(num2+","+num1+","+num3);
            }else if(num2>num3){
                System.out.println(num3+","+num2+","+num1);
            }else{
                System.out.println(num2+","+num3+","+num1);
            }
        }else{
            if(num3>num2){
                System.out.println(num1+","+num2+","+num3);
            }else if(num1>num3){
                System.out.println(num3+","+num1+","+num2);
            }else{
                System.out.println(num1+","+num3+","+num2);

            }
        }



    }

}
