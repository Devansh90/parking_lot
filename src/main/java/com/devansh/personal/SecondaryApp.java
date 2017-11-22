package com.devansh.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SecondaryApp {
    public static void main(String args[]) {
        int[] a = {1, 2, 4, 4};
        int[] rotate = {0, 2};
//        getMaxElementIndices(a, rotate);
        String word1 = "w:/a\\b";
        String word2 = "w:/a\\bc::/12\\xyz";
        System.out.println("Count : " + getAdorableCount(word2));
    }

    static String[] getMovieTitles(String substr) {
        return null;
    }

    static int[] getMaxElementIndices(int[] a, int[] rotate) {
        int sizeOfA = a.length;
        int SizeofRotate = rotate.length;

        int[] finalArray = new int[rotate.length];

        //Get max index of a
        int maxElementIndex = getMax(a);

        for (int element = 0; element < rotate.length; element++) {
            //For each rotate element subtract element % sizeofA
            int rotateBy = rotate[element] % sizeOfA;
            System.out.println("Rotate by:" + rotateBy);
            //If the above value is more than index of max, subtract THAT value from a.length
            int finalIndex = maxElementIndex - rotateBy;
            System.out.println("Final index (1):" + finalIndex);
            if (finalIndex < 0) {
                finalIndex = sizeOfA + finalIndex;

            }
            System.out.println("Final index (2):" + finalIndex);
            //Save new index in an array
            finalArray[element] = finalIndex;
            System.out.println("Saving index:" + finalIndex);
        }

        return null;
    }

    private static int getMax(final int[] a) {
        int maxValue = a[0];
        int maxIndex = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] > maxValue) {
                maxValue = a[i];
                maxIndex = i;
            }
        }
        System.out.println("Max at index:" + maxIndex);
        return maxIndex;
    }

    static int[] adorableCount(String[] words) {

        int counter = 0;
        int[] finalArray = new int[words.length];
        for (String currentWord : words) {
            finalArray[counter] = getAdorableCount(currentWord);
            counter = counter + 1;
        }
        return finalArray;
    }

    private static int getAdorableCount(final String currentWord) {
        if (currentWord == null) return 0;
        if (currentWord.length() < 5) return 0;
        if (getMatch4(currentWord.substring(0, 1))) {
            int indexOfForwardSlash = currentWord.indexOf("/");
            if (indexOfForwardSlash != -1) {
                String sub1 = currentWord.substring(1, indexOfForwardSlash);
                if (getMatch1(sub1)) {
                    int indexOfBackwardSlash = sub1.indexOf("\\");
                    if (indexOfBackwardSlash != -1) {
                        String sub2 = currentWord.substring(indexOfForwardSlash + 1, indexOfBackwardSlash);
                        if (getMatch2(sub2)) {
                            String sub3 = currentWord.substring(indexOfBackwardSlash + 1);
                            if (getMatch3(sub3)) {
                                System.out.println("Found 1 in :" + currentWord);
                                return (1 + getAdorableCount(currentWord.substring(1)));
                            } else {
                                System.out.println("Not found 5, lower in :" + sub3);
                                return getAdorableCount(currentWord.substring(indexOfBackwardSlash));
                            }
                        } else {
                            System.out.println("Not found 4, lower-digit in :" + sub2);
                            return getAdorableCount(currentWord.substring(indexOfBackwardSlash));
                        }
                    } else {
                        System.out.println("Not found 3,backward slash");
                        return getAdorableCount(currentWord.substring(indexOfForwardSlash));
                    }
                } else {
                    System.out.println("Not found 2, lower-digit-colon in :" + sub1);
                    return getAdorableCount(currentWord.substring(indexOfForwardSlash));
                }
            } else {
                System.out.println("Not found 1, forward slash");
                return getAdorableCount(currentWord.substring(1));
            }
        } else {
            System.out.println("Not found 1 in :" + currentWord);
            return getAdorableCount(currentWord.substring(1));
        }
    }

    private static boolean getMatch4(final String sub3) {
        if (sub3.matches("[a-z]")) return true;
        return false;
    }

    private static boolean getMatch3(final String sub3) {
        if (sub3.matches("[a-z]*")) return true;
        return false;
    }

    private static boolean getMatch2(final String sub2) {
        if (sub2.matches("[a-z]*[0-9]*")) return true;
        if (sub2.matches("[0-9]*[a-z]*")) return true;
        return false;
    }

    private static boolean getMatch1(final String sub1) {
        if (sub1.matches("[a-z]*[0-9]*[:]*")) return true;
        if (sub1.matches("[a-z]*[:]*[0-9]*")) return true;
        if (sub1.matches("[0-9]*[:]*[a-z]*")) return true;
        if (sub1.matches("[0-9]*[a-z]*[:]*")) return true;
        if (sub1.matches("[:]*[a-z]*[0-9]*")) return true;
        if (sub1.matches("[:]*[0-9]*[a-z]*")) return true;
        return false;
    }

    static int minMoves(int[][] maze, int x, int y) {
        int count = 0;
        int i = 0, j = 0;
        while (i < maze.length && j < maze.length) {
            if (i == x && j == y) return count;

            if (maze[i + 1][j] == 1) {
                //Wall below
                //Try Right
                if (maze[i][j + 1] == 1) {
                    //Wall on Right, backtrack
                    continue;
                } else {
                    //Right free
                    j = j + 1;
                }
            } else {
                //Below free
                i = i + 1;
            }
        }
        return count;
    }

    static void callHttp(int pageNumber) throws IOException {
        URL url = new URL("http://example.com" + pageNumber);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
    }
}
