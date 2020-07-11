/**
 * @ProblemName : BOARDCOVER(게임판 덮기)
 * @ProblemUrl  : https://www.algospot.com/judge/problem/read/BOARDCOVER
 * @Author      : 조민욱 (https://github.com/jominuook)
 * @Date        : 2020.07.11
 * Copyright 2020. 조민욱 All Rights Reserved.
 */
package chap06_exhaustive_search.boardcover;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static final int[][][] COVER_TYPE = {
        {{0, 0}, {1, 0}, {0,  1}},
        {{0, 0}, {0, 1}, {1,  1}},
        {{0, 0}, {1, 0}, {1,  1}},
        {{0, 0}, {1, 0}, {1, -1}}
    };
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int C = Integer.parseInt(br.readLine());
        while(C-- > 0) {
            st = new StringTokenizer(br.readLine());
            board = new int[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())];
            for(int i=0; i<board.length; i++) {
                String row = br.readLine();
                for(int j=0; j<board[i].length; j++)
                    board[i][j] = row.charAt(j) == '#' ? 1 : 0;
            }
            bw.write(cover() + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static boolean set(int y, int x, int type, int delta) {
        boolean ok = true;
        for(int i=0; i<COVER_TYPE[type].length; i++) {
            int ny = y + COVER_TYPE[type][i][0];
            int nx = x + COVER_TYPE[type][i][1];
            if(ny<0 || ny>=board.length || nx<0 || nx>=board[0].length)
                ok = false;
            else if((board[ny][nx] += delta) > 1)
                ok = false;
        }
        return ok;
    }

    private static int cover() {
        int y = -1, x = -1;
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length; j++)
                if(board[i][j] == 0) {
                    y = i;
                    x = j;
                    break;
                }
            if(y != -1)
                break;
        }
        if(y == -1)
            return 1;
        int ret = 0;
        for(int type=0; type<COVER_TYPE.length; type++) {
            if(set(y, x, type, 1))
                ret += cover();
            set(y, x, type, -1);
        }
        return ret;
    }
}