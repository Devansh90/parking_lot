package com.devansh.personal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FourthApp {

    //    1
//    3 1
//    P T P
//    T P T
//    T T P
    public static void main(String args[]) throws Exception {

        //Scanner
        Scanner s = new Scanner(System.in);
        Integer testCases = s.nextInt();

        for (int i = 0; i < testCases; i++) {
            String[][] grid = getGrid(s);
        }

    }

    private static String[][] getGrid(final Scanner s) {
        int gridSize = s.nextInt();
        int maxDis = s.nextInt();
//        System.out.println("Grid size:" + gridSize + " maxDis:" + maxDis);
        String[][] grid = new String[gridSize][gridSize];
        for (int k = 0; k < gridSize; k++) {
            for (int j = 0; j < gridSize; j++) {
                grid[k][j] = s.next();
//                System.out.println("Saved:" + grid[k][j]);
            }
        }

        int finalcount = 0;
        for (int i = 0; i < gridSize; i++) {
            String[] row = grid[i];
            List<Integer> usedPolice = new ArrayList<>();
            for (int j = 0; j < gridSize; j++) {
                if (grid[i][j].equals("T")) {
//                    System.out.println("Found t at:(" + i + "," + j + ")");
                    InnerClass innerClass = checkPoliceNearBy(row, j, maxDis, usedPolice);
                    if (innerClass != null && innerClass.getFound()) {
                        finalcount = finalcount + 1;
                        usedPolice = innerClass.usedPolice;
                    }
                }
            }
        }
        System.out.println(finalcount);

        return grid;
    }

    private static InnerClass checkPoliceNearBy(final String[] row, final int j, final int maxDis, List<Integer> usedPolice) {
        for (int i = 0; i < j; i++) {
            if (j - i <= maxDis) {
                if (row[i].equals("P") && !usedPolice.contains(i)) {
                    InnerClass innerClass = new InnerClass();
                    usedPolice.add(i);
                    innerClass.setUsedPolice(usedPolice);
                    innerClass.setFound(true);
                    return innerClass;
                }
            }
        }

        for (int i = j; i < row.length; i++) {
            if (i - j <= maxDis) {
                if (row[i].equals("P") && !usedPolice.contains(i)) {
                    InnerClass innerClass = new InnerClass();
                    usedPolice.add(i);
                    innerClass.setUsedPolice(usedPolice);
                    innerClass.setFound(true);
                    return innerClass;
                }
            }
        }
        return null;
    }

    private static class InnerClass {
        public Boolean getFound() {
            return found;
        }

        public void setFound(final Boolean found) {
            this.found = found;
        }

        public List<Integer> getUsedPolice() {
            return usedPolice;
        }

        public void setUsedPolice(final List<Integer> usedPolice) {
            this.usedPolice = usedPolice;
        }

        Boolean found;
        List<Integer> usedPolice;
    }
}

