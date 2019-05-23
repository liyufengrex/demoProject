package com.rex.myapplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Description: TODO
 * @Author: 李宇峰 on 2018-08-07 上午9:47.
 * @NO.: 146714
 * @Phone: 13756017116
 * @Email: liyufeng@syswin.com
 * @Leader：yupengfei <yupengfei@syswin.com>
 * @Charge: 李宇峰
 */
public class Sort {

    /**
     * 冒泡排序
     * @param
     * @return
     * @author liyufeng[13756017116]@2018-08-08 下午2:32
     */
    public static void maoPaoSort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                if (arr[j] >= arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 快速排序
     * @param
     * @return
     * @author liyufeng[13756017116]@2018-08-08 下午2:39
     */
    public static void quickSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int base = arr[end];
        int n = start;
        for (int i = start; i < end; i ++){
            if(arr[i] >= base){
                if(i != n){
                    exchange(arr,i,n);
                }
                n++;
            }
        }
        exchange(arr,end,n);
        quickSort(arr,start,n-1);
        quickSort(arr,n+1,end);
    }

    //并归排序
    public static void inMerge(int[] arr, int start, int end){
        if(start < end){
            int middle = (end + start)/2;
            inMerge(arr,start,middle);
            inMerge(arr,middle+1,end);
            merge(arr,start,end,middle);
        }
    }

    public static void merge(int[] arr, int start, int end, int middle) {
        int leftLen = middle - start;
        int rightLen = end - middle;
        int[] leftArr = new int[leftLen];
        int[] rightArr = new int[rightLen];
        for (int i = 0; i < leftLen; i++) {
            leftArr[i] = arr[i + start];
        }
        for (int j = 0; j < rightLen; j++) {
            rightArr[j] = arr[j + middle];
        }

        int i = 0;
        int j = 0;
        int k = start;
        while (i < leftLen && j < rightLen) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }
        if (i < leftLen) {
            arr[k++] = leftArr[i++];
        }
        if (j < rightLen) {
            arr[k++] = rightArr[j++];
        }
    }

    public static void exchange(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    /**
     * 前序遍历
     * @param
     * @return
     * @author liyufeng[13756017116]@2018-08-08 下午3:00
     */
    public static List<Integer> preOrder(TreeNode node){
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        if(node == null){
            return result;
        }
        stack.push(node);
        while (!stack.isEmpty()){
            TreeNode current = stack.pop();
            result.add(current.value);
            if(current.right != null){
                stack.push(current.right);
            }
            if(current.left != null){
                stack.push(current.left);
            }
        }
        return result;
    }

    /**
     * 中序遍历
     * @param
     * @return
     * @author liyufeng[13756017116]@2018-08-08 下午3:05
     */
    public static List<Integer> inOrder(TreeNode node){
        List<Integer> result = new ArrayList<>();
        TreeNode current = node;
        Stack<TreeNode> stack = new Stack<>();
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            result.add(current.value);
            current = current.right;
        }
        return result;
    }

    /**
     * 后序遍历
     * @param
     * @return
     * @author liyufeng[13756017116]@2018-08-08 下午3:06
     */
    public static List<Integer> posOrder(TreeNode node){
        List<Integer> result = new ArrayList<>();
        if(node == null){
            return result;
        }
        result.addAll(posOrder(node.left));
        result.addAll(posOrder(node.right));
        result.add(node.value);
        return result;
    }

    public static void a(){
        System.out.println("rex");
    }
    public static void b(){
        System.out.println("b");
        c();
    }
    public static void c(){
        return;
    }

    public static void main(String[] args){
        b();
        a();
    }

    public class TreeNode{
        public int value;
        public TreeNode left;
        public TreeNode right;
    }

}
