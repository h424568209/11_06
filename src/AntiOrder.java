import java.util.*;

public class AntiOrder {
    public static int count(int[] A, int n) {
        // write code here
        return mergeSort(A, 0, n - 1);
    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int[] A = new int[n];
            for (int i = 0; i < n; i++) {
                A[i] = scanner.nextInt();
            }
            System.out.println(count(A, n));
        }
    }

    public static int merge(int[] A, int low, int mid, int high) {
        int[] B = new int[high - low + 1];
        int i = low;
        int j = mid + 1;
        int index = 0;
        int reverseNum = 0;
        while (i <= mid && j <= high) {
            if (A[i] <= A[j]) {
                B[index++] = A[i];
                i++;
            } else {
                B[index++] = A[j];
                j++;
                reverseNum += mid - i + 1;
            }
        }
        while (i <= mid) {
            B[index++] = A[i++];
        }
        while (j <= high) {
            B[index++] = A[j++];
        }
        for (int k = 0; k < B.length; k++) {
            A[low + k] = B[k];
        }
        return reverseNum;
    }

    public static int mergeSort(int[] A, int start, int high) {
        if (start >= high) {
            return 0;
        }
        int mid = (start + high) / 2;
        int count1 = mergeSort(A, start, mid);
        int count2 = mergeSort(A, mid + 1, high);
        return count1 + count2 + merge(A, start, mid, high);
    }
}

