package edu.nyu.cs.CompanyTest;

import java.io.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * there are many drugs, from 1 to N
 * each drug has a important level which is 1 to 25
 * 
 * two drug may cannot be taken together
 * 
 * calculate how many drugs can be taken in order to 
 * make their sum of important levels is max
 * 
 * print out the max
 * 
 * 
 * 
 * eg: input:
 * 5 1
 * 18 12 5 18 16
 * 1 4
 * output:
 * 51
 * 
 * 
 * 3 1
 * 1 4 9
 * 1 2
 * output:
 * 13
 * @author xiaonanwang
 *
 */
public class Solution {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        
        Scanner stdin = new Scanner(System.in);
        int drugNo=0;
        int interNo =0;
        
        HashMap<Integer,ArrayList<Integer>> map = new HashMap<Integer,ArrayList<Integer>>();
        
        if (stdin.hasNextLine()){
            String line = stdin.nextLine();
            String[] tokens = line.split(" ");
            drugNo = Integer.parseInt(tokens[0]);
            interNo = Integer.parseInt(tokens[1]);
        }
        int[] levels = new int[drugNo];
        if (stdin.hasNextLine()) {
            String line = stdin.nextLine();
            String[] tokens = line.split(" ");
            for (int i = 0 ; i < tokens.length;i++){
                levels[i] = Integer.parseInt(tokens[i]);
            }
        }
        
        if (stdin.hasNextLine() ) {
            int i = 0 ;
            while (i < interNo) {
                int first = stdin.nextInt();
                int second = stdin.nextInt();
                
                if (map.containsKey(first)) {
                    ArrayList<Integer> array = map.get(first);
                    array.add(second);
                    map.put(first,array);
                } else {
                    ArrayList<Integer> array = new ArrayList<Integer>();
                    array.add(second);
                    map.put (first,array);
                }
                
                if (map.containsKey(second)) {
                    ArrayList<Integer> array = map.get(second);
                    array.add(first);
                    map.put(second,array);
                } else {
                    ArrayList<Integer> array = new ArrayList<Integer>();
                    array.add(first);
                    map.put (second,array);
                }
                i++;
            }
        }
        
        int max = 0;
        ArrayList<Integer> n = new ArrayList<Integer>();
        result.add(n);
        
        for (int i = 0 ; i < drugNo; i++) {
          int size = result.size();
          for (int j = 0 ; j < size; j++) {
            ArrayList<Integer> tmp = new ArrayList<Integer>(result.get(j));
            if (map.containsKey(i+1)) {
              boolean mark = false;
              for (int mm = 0; mm < tmp.size() ; mm++) {
                for (int nn = 0; nn < map.get(i+1).size();nn++){
                  if (tmp.get(mm) == map.get(i+1).get(nn)){
                    mark = true;
                  }
                  if (mark == true){
                    break;
                  }
                }
                if (mark == true) {
                  break;
                }
              }
              
              if (mark == false) {
                tmp.add(i+1);
                result.add(tmp);
              }
            } else {
              tmp.add(i+1);
              result.add(tmp);
            }
          }
        }
        for (int i = 0 ; i < result.size();i++) {
          int com = 0;
          ArrayList<Integer> r = result.get(i);
          for (int j = 0 ; j < r.size();j++) {
            com += levels[r.get(j)-1];
          }
          if (max < com) {
            max = com;
          }
        }
        System.out.println(max);
    }
}