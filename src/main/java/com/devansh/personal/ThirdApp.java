package com.devansh.personal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ThirdApp {

    private static String houseMap;
    private static int houseMapSize;

    public static void main(String args[]) throws Exception {

        // Read Input
        List<CostClass> testArray = readInput();

        houseMapSize = houseMap.length();

        if(testArray != null && testArray.size() == 0)
            System.out.println("0");
        for (CostClass testCase : testArray) {
            System.out.println(fetchMinArrangement(houseMap, houseMapSize, testCase));
        }
    }

    private static int fetchMinArrangement(String houseMap, final int houseMapSize, final CostClass testCase) {
        int numberOfFoodTile = calcFoodTile(houseMap, houseMapSize);

        int cost = 0;
        if (numberOfFoodTile < 2) return 0;

        int firstIndex = houseMap.indexOf("F");
        int lastIndex = houseMap.lastIndexOf("F");

        cost = calcDirtyTile(houseMap.substring(firstIndex, lastIndex+1), houseMapSize) * testCase.getClearCost() +
                (lastIndex - firstIndex - numberOfFoodTile + 1) * testCase.getMoveCost();

//        System.out.println("Cost is :" + cost);
//        System.out.println("Final str is :" + houseMap);
        return cost;
    }

    private static int calcFoodTile(final String houseMap, final int houseMapSize) {
        int count = 0;
        for (int i = 0; i < houseMapSize; i++) {
//            System.out.println("Checking :" + i);
            if ('F' == houseMap.charAt(i)) {
                count = count + 1;
//                System.out.println("Found at :" + i);
            }
        }
//        System.out.println("Food Tile count is :" + count);
        return count;
    }

    private static int calcDirtyTile(final String houseMap, final int houseMapSize) {
        int count = 0;
        for (int i = 0; i < houseMap.length(); i++) {
            if ('D' == houseMap.charAt(i)) {
                count = count + 1;
            }
        }
//        System.out.println("Dirty tile count is :" + count);
        return count;
    }

    private static List<CostClass> readInput() {
        //Scanner
        Scanner s = new Scanner(System.in);
        int houseMapSize = Integer.parseInt(s.next());
        int numberOfTestCases = Integer.parseInt(s.next());
        houseMap = s.next();
        if(houseMapSize != houseMap.length()) {
            System.out.println("0");
            return null;
        }
        List<CostClass> testArray = new ArrayList<>();
        for (int i = 0; i < numberOfTestCases; i++) {
            CostClass costClass = new CostClass();
            costClass.setMoveCost(Integer.valueOf(s.next()));
            costClass.setClearCost(Integer.valueOf(s.next()));
            testArray.add(costClass);
        }
        return testArray;
    }

    public static class CostClass
    {
        Integer MoveCost;
        Integer ClearCost;

        public Integer getMoveCost() {
            return MoveCost;
        }

        public void setMoveCost(final Integer moveCost) {
            MoveCost = moveCost;
        }

        public Integer getClearCost() {
            return ClearCost;
        }

        public void setClearCost(final Integer clearCost) {
            ClearCost = clearCost;
        }
    }
}
