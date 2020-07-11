/**
 * @ProblemName : CLOCKSYNC(Synchronizing Clocks)
 * @ProblemUrl  : https://www.algospot.com/judge/problem/read/CLOCKSYNC
 * @Author      : 조민욱 (https://github.com/jominuook)
 * @Date        : 2020.07.11
 * Copyright 2020. 조민욱 All Rights Reserved.
 */
package chap06_exhaustive_search.clocksync;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 9999, SWITCHES = 10, CLOCKS = 16;
    static int[][] linked = {
            {0, 1, 2},
            {3, 7, 9, 11},
            {4, 10, 14, 15},
            {0, 4, 5, 6, 7},
            {6, 7, 8, 10, 12},
            {0, 2, 14, 15},
            {3, 14, 15},
            {4, 5, 7, 14, 15},
            {1, 2, 3, 4, 5},
            {3, 4, 5, 9, 13}
    };
    static int[] clocks = new int[CLOCKS];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int C = Integer.parseInt(br.readLine());
        while(C-- > 0) {
            st = new StringTokenizer(br.readLine());
            for(int clock=0; clock<CLOCKS; clock++)
                clocks[clock] = Integer.parseInt(st.nextToken());
            int ret = solve(0);
            bw.write((ret != INF ? ret : -1) + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static boolean areAligned() {
        for(int clock : clocks)
            if(clock != 12)
                return false;
        return true;
    }

    private static void push(int swtch) {
        for(int clock : linked[swtch]) {
            clocks[clock] += 3;
            if(clocks[clock] == 15)
                clocks[clock] = 3;
        }
    }

    private static int solve(int swtch) {
        if(swtch == SWITCHES)
            return areAligned() ? 0 : INF;
        int ret = INF;
        for(int cnt=0; cnt<4; cnt++) {
            ret = Math.min(ret, cnt+solve(swtch+1));
            push(swtch);
        }
        return ret;
    }
}