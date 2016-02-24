package ext;

import java.util.Scanner;

/**
 * spring-engine 2016/2/24 20:44
 * fuquanemail@gmail.com
 */
public class Calc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.err.println("上个月人数：");
        int preNum = scanner.nextInt();
        int num = 6;
        if(preNum > 6){
            num = (int) (preNum * 0.8);
        }

        System.err.println("这个月人数：");
        int currentNum = scanner.nextInt();
        int current = currentNum - num;

        int money = 0;
        String desc = "";
        if(current >= 5){
            money+= 500+ 600+700 +800 + 900;
            desc = "500+ 600+700 +800 + 900";
        }
        else if(current >= 4){
            money+= 500+ 600+700 +800;
            desc = "500+ 600+700 +800 ";
        }
        else if(current >= 3){
            money+= 500+ 600+700;
            desc = "500+ 600+700";
        }
        else if(current >= 2){
            money+= 500+ 600;
            desc = "500+ 600";
        }
        else if(current >= 1){
            money+= 500;
            desc = "500";
        }
        int n = 0;
        if(current>5){
            money += (current - 5 ) * 1000;
            n = current - 5;
        }
        System.err.println("上个月人数："+ preNum+", 乘以基数之后："+ current +" 金额:"+ money);
        System.err.println("计算方式:" + desc +"+" + n +" x 1000 = "+ money );
    }
}
