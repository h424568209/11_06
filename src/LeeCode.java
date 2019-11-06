import java.util.*;


public class LeeCode {
    /**
     * 解数独
     * @param board 二维数组
     */
    public void solveSudoku(char[][] board) {
        boolean[][] row = new boolean[9][10];
        /**
         * 记录某列，某位数字是否已经被摆放
         */
        boolean[][] col = new boolean[9][10];
        /**
         * 记录某 3x3 宫格内，某位数字是否已经被摆放
         */
        boolean[][] block = new boolean[9][10];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    row[i][num] = true;
                    col[j][num] = true;
                    // blockIndex = i / 3 * 3 + j / 3，取整
                    block[i / 3 * 3 + j / 3][num] = true;
                }
            }
        }
        dfs(board, row, col, block, 0, 0);
    }

    private boolean dfs(char[][] board, boolean[][] row, boolean[][] col, boolean[][] block, int i, int j) {
        // 找寻空位置
        while (board[i][j] != '.') {
            if (++j >= 9) {
                i++;
                j = 0;
            }
            if (i >= 9) {
                return true;
            }
        }
        for (int num = 1; num <= 9; num++) {
            int blockIndex = i / 3 * 3 + j / 3;
            if (!row[i][num] && !col[j][num] && !block[blockIndex][num]) {
                // 递归
                board[i][j] = (char) ('0' + num);
                row[i][num] = true;
                col[j][num] = true;
                block[blockIndex][num] = true;
                if (dfs(board, row, col, block, i, j)) {
                    return true;
                } else {
                    // 回溯
                    row[i][num] = false;
                    col[j][num] = false;
                    block[blockIndex][num] = false;
                    board[i][j] = '.';
                }
            }
        }
        return false;
    }
    private void printBoard(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     *  有效的数独
     *  使用value->count哈希映射来跟踪所有已经遇到过的值
     *  遍历这个二维数组，检查看到每个单元的数字是否在当前的行/列/子数独中出现过
     *      如果出现过 则他不是有效的数独
     *       如果没有出现过，则进一步的进行跟踪
     * @param board  用二维数组表示的一组数独
     * @return  是否为有效的数独
     */
    public boolean isValidSudoku(char[][] board) {
        HashMap[] rows = new HashMap[9];
        HashMap[] colums = new HashMap[9];
        HashMap[] box = new HashMap[9];
        for(int i = 0 ; i <9;i++){
            rows[i]=new HashMap<Integer, Integer>();
            colums[i] = new HashMap<Integer, Integer>();
            box[i] = new HashMap<Integer, Integer>();
        }
        for(int i = 0 ; i<9 ; i++){
            for(int j = 0 ; j <9 ;j++){
                char num = board[i][j];
                if(num!='.'){
                    int n = (int)num;
                    int box_index = (i/3)*3+j/3;
                     rows[i].put(n,(int)rows[i].getOrDefault(n,0)+1);
                     colums[j].put(n,(int)colums[j].getOrDefault(n,0)+1);
                     box[box_index].put(n,(int)box[box_index].getOrDefault(n,0)+1);
                     if((int)rows[i].get(n)>1||(int)colums[j].get(n)>1||(int)box[box_index].get(n)>1){
                         return false;
                     }
                }

            }
        }
        return true;
    }
    /**
     *  逆置一个数字 可以为正、可以为负
     * @param x 数字
     * @return 返回它的逆置的数
     */
    public int reverse(int x) {
        int num = 0;
        while(x>0){
          int carry = x%10;
            x /= 10;
            num  = carry*10;
        }
        return num;
    }
    /**
     *  子域名访问计数  计算出每个域名包括子域名出现的次数
     *  先将数组中的一组域名进行解析，解析成数字+域名的形式，将这一组数据放到map中进行存储
     *  最后进行遍历这个map  将的数据放到list中
     * @param cpdomains 访问次数和域名组合的数组  访问次数在前面，域名组合在后面
     * @return 每个域名的出现次数  使用链表进行存储
     */
    private List<String> subdomainVisits(String[] cpdomains) {
        List<String> list = new ArrayList<>();
        Map<String,Integer> map = new HashMap<>();
        //遍历整个数组 将每个元素都进行解析
        for(String domain : cpdomains){
            String [] sp = domain.split(" ");
            //次数
            int times = Integer.valueOf(sp[0]);
            //域名
            String[]subdomain = sp[1].split("\\.");
            //先将最后一个域名放到map中 因为它无法进行解析
            String name = subdomain[subdomain.length-1];
            map.put(name,map.getOrDefault(name,0)+times);
            //循环从倒数第二个域名开始，将他们放到map中
            for(int index = subdomain.length-2 ; index>=0 ; index--){
                name = subdomain[index]+"."+name;
                map.put(name,map.getOrDefault(name,0)+times);
            }
        }
        //遍历map集合 将元素转换成合适的形式放到list中
        for(String s : map.keySet()){
            list.add(map.get(s)+" "+s);
        }
        return list;

    }



    /**
     *  实现String中的 toLowerCase方法
     * @param str 字符串
     * @return 小写的字符串
     */
    private String toLowerCase(String str) {
            StringBuilder s = new StringBuilder();
            for(int i = 0 ; i<str.length() ;i++){
                char c = str.charAt(i);
                if(c>=65&&c<=90){
                    c+=32;
                }
                s.append(c);
            }
            return s.toString();
        }

    /**
     * 使用二分查找才是最优解
     * @param nums 数组
     * @return 数组中最小的元素
     */
    private static int findMin(int[] nums) {
        int left = 0 ;
        int right = nums.length-1;
        while(left <right){
            int mid = left +((right - left)>>1);
            if(nums[mid]>nums[right]){
                left  = mid+1;
            }else{
                right = mid;
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        int []nums = {4,5,6,7,0,1,2};
        System.out.println(LeeCode.findMin(nums));
        String []s = {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
        LeeCode l = new LeeCode();
        System.out.println(l.toLowerCase("ADADADA"));
        System.out.println(l.subdomainVisits(s));
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        l.printBoard(board);
        l.solveSudoku(board);
        l.printBoard(board);
    }
}


