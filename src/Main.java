import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.next();
        String s2 = scanner.next();
        int count = 0;
        for(int i = 0 ; i<s1.length() ;i++){
            //  res.append(s1.substring(0,i)).append(s2).append(s1.substring(i,s1.length()));
            StringBuilder res = new StringBuilder(s1);
            res.insert(i,s2);
           if(FindConunt(res)){
               count++;
           }
        }
        System.out.println(count);
    }
    private static boolean FindConunt(StringBuilder res) {
        int left = 0;
        int right = res.length()-1;
        while(left<=right){
            if(res.charAt(left) != res.charAt(right)){
                return false;
            }
            left++;
            right --;
        }
        return true;
    }
}
