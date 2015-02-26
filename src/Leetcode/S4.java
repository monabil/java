package Leetcode;

/**
 * Created by juzhou on 2/25/2015.
 */
public class S4 {
    public static void main(String[] args){
        Solution s = new Solution();
        int[] A = {1,3,26,39,98,228,9087,76654,111111,222222,8888888};
        int[] B = {2,4,5,18,22,123,456,789,1111,3333,5555,7777,9999};
        System.out.println(s.f2(A, B, 0, A.length - 1, 0, B.length - 1, 6));
        // s.findMedianSortedArrays(A,B);
    }

    public static class Solution {
        public int f2(int a[], int b[],int a1, int a2, int b1, int b2, int index ){
            int i = 0;
            int m = Math.min(a[a1], b[b1]);
            boolean c = true;

            while (i < index){
                if (a[a1] > b[b1]){

                    m = b[b1];
                    b1++;
                }
                else{
                    System.out.println(" " + a1 + " " + b1);
                    m = a[a1];
                    a1++;
                }
                System.out.println(" " + m);
                i++;
                if (b1 > b2 || a1>a2)
                    break;
            }
            if (i == index) {
                System.out.println("reutnr");
                return m;
            }

            if (a2 > a1){
                return a[a1 + i];
            }
            else{
                return b[b1 + i];
            }

        }

        public int f(int a[], int b[], int a1, int a2, int b1, int b2, int index){
            if(a1 > a2){
                return b[b1 + index];
            }
            if (b1 > b2){
                return a[a1 + index];
            }

            System.out.println(" " + a1 + " " + a2 + " " + b1 + " " + b2 + " " + index);

            if (((a2-a1) < 8 ) && ((b2-b1) < 8))
                return f2(a,b,a1,a2,b1,b2,index);

            if (a2 + b2 - a1 - b1 + 2 < index) {
                System.out.println(" not enough" + index);
                throw new RuntimeException();
                // return -1;
            }

            int half = index / 2;
            int sizea = a2 - a1;
            int sizeb = b2 -b1;
            if(sizea > sizeb)
            {
                System.out.println(" a2 - a1 > half ");
                int m = a[a1 + half];
                int n = getPosition(b, b1, b2, m);
                System.out.println(" getPosition " + n);

                if ((n - b1) == half) {
                    System.out.println(" return m " + m);
                    return m;
                }
                if (n - b1 > half){
                    return f(a, b, a1, Math.max(a1, a1 + half - 1), b1, n, index);
                }
                else {
                    return f(a, b, Math.min(a1+half+1, a2), a2, n, b2, index - half - 1);
                }
            }
            else if (b2 - b1 >= half){
                System.out.println(" b2 - b1  > half ");
                int m = b[b1 + half];
                int n = getPosition(a, a1, a2, m);
                if((n - a1) == half) {
                    System.out.println(" return m " + m);
                    return m;
                }
                if (n - a1 > half){
                    return f(a, b, a1, n, b1, Math.min(b2, b1 + half-1), index);
                }
                else {
                    return f(a, b, n, a2, Math.max(b1, b1 + half+1), b2, index - half - 1);
                }
            }

            return -1;
        }

        public int getPosition(int a[], int l, int r, int m){
            System.out.println(" " + l + " " + r + " " + m);
            if (m < a[l])
                return l;
            if ( m > a[r])
                return r;
            if (r == l)
                return l;
            int am = a[(r + l) / 2];

            if (m > am){
                return getPosition(a, (r + l) / 2, r, m);
            }
            else {
                return getPosition(a, l, (r + l) / 2, m);
            }

        }
        public double findMedianSortedArrays(int A[], int B[]) {

            return f(A, B, 0, A.length - 1, 0, B.length -1, (A.length + B.length + 1) / 2 );
        }
    }


}
