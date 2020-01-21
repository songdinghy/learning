package com.sd.learning;

/**
 * @author SONGDING
 * @Description 朴素模式匹配算法
 * @date 2019/4/17 8:53
 */
public class C5_SimpleStringMatch {

    /**
     * 从parent的pos位置开始匹配，若匹配上了返回下标。若未匹配上，返回-1
     * @param parent
     * @param sub
     * @param pos
     * @return
     */
    public static int index(char[] parent,char[] sub,int pos){
        int i =pos;
        int retuIndex = -1;
        // 从pos的位置开始匹配
        for (; i < parent.length; i++) {
            for (int j = 0; j <sub.length; j++) {
                if(i+j== parent.length || parent[i+j] != sub[j]){
                    break;
                }
                if(j == sub.length-1){
                    retuIndex = i;
                }
            }
        }
        return retuIndex;
    }
    public static void main(String[] args) {
        char[] parent = "0000000000000000000000000000000000000000000000000001".toCharArray();
        char[] sub = "00000000000001".toCharArray();
        System.out.println("index = [" + index(parent,sub,0) + "]");

    }
}
