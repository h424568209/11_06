import java.util.Scanner;
public class SS{
    static  int nums2 = 0;
    public static void main(String[] args) {
        int []nums1 = new int[10];
        int count = 0;
        int temp ;
        while(count<10){
            Scanner scanner = new Scanner(System.in);
            temp = scanner.nextInt();
            if(temp == 0)
                break;
            nums1[count++] = temp;
        }
        for(int i = 0 ; i <count;i++){
            nums2 = 0;
            int n = 0;
            while(nums1[i]>=2){
                if(nums1[i]==2){
                    nums2++;
                    nums1[i]=0;
                }else if(n==3){
                    nums2++;
                    nums1[i] = nums1[i]-3+1;
                    n= 0;
                }
                n++;
            }
            System.out.println(nums2);
        }

    }


}