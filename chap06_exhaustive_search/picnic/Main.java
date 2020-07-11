/**
 * @ProblemName : PICNIC(소풍)
 * @ProblemUrl  : https://www.algospot.com/judge/problem/read/PICNIC
 * @Author      : 조민욱 (https://github.com/jominuook)
 * @Date        : 2020.07.11
 * Copyright 2020. 조민욱 All Rights Reserved.
 */
package chap06_exhaustive_search.picnic;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static boolean[][] areFriends;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int C = Integer.parseInt(br.readLine());
        while(C-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            areFriends = new boolean[n][n];
            st = new StringTokenizer(br.readLine());

            for(int i=0; i<m; i++) {
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());
                areFriends[n1][n2] = areFriends[n2][n1] = true;
            }
            bw.write(countPairings(new boolean[n]) + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static int countPairings(boolean[] taken) {
        int firstFree = -1;
        for(int i=0; i<n; i++)
            if(!taken[i]) {
                firstFree = i;
                break;
            }
        if(firstFree == -1)
            return 1;
        int ret = 0;
        for(int pairWith=firstFree+1; pairWith<n; pairWith++)
            if(!taken[pairWith] && areFriends[firstFree][pairWith]) {
                taken[firstFree] = taken[pairWith] = true;
                ret += countPairings(taken);
                taken[firstFree] = taken[pairWith] = false;
            }
        return ret;
    }
}