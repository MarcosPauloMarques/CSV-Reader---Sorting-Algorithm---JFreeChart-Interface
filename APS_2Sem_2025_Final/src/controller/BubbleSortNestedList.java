package controller;
import java.util.List;

public class BubbleSortNestedList {

    public static List<List<String>> bubbleSortYear(List<List<String>> list, boolean ascending) {

        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - 1 - i; j++) {

                Analytics.totalComps++;

                if (ascending){
                    if ((list.get(j)).get(0).compareTo((list.get(j + 1)).get(0)) > 0) {
                        List<String> temp = list.get(j);
                        list.set(j, list.get(j + 1));
                        list.set(j + 1, temp);
                        Analytics.totalSubs++;
                    }
                } else {
                    if ((list.get(j)).get(0).compareTo((list.get(j + 1)).get(0)) < 0) {
                        List<String> temp = list.get(j);
                        list.set(j, list.get(j + 1));
                        list.set(j + 1, temp);
                        Analytics.totalSubs++;
                    }
                }
            }
        }

        return list;
    }

    public static List<List<String>> bubbleSortArea(List<List<String>> list, boolean ascending) {

        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - 1 - i; j++) {

                Analytics.totalComps++;

                try {
                    int a = Integer.parseInt(list.get(j).get(1));
                    int b = Integer.parseInt(list.get(j + 1).get(1));

                    if (ascending){
                        if (a > b) {
                            List<String> temp = list.get(j);
                            list.set(j, list.get(j + 1));
                            list.set(j + 1, temp);
                            Analytics.totalSubs++;
                        }
                    } else {
                        if (a < b) {
                            List<String> temp = list.get(j);
                            list.set(j, list.get(j + 1));
                            list.set(j + 1, temp);
                            Analytics.totalSubs++;
                        }
                    }
                } catch (NumberFormatException e) {
                    if (ascending) {
                        if ((list.get(j)).get(1).compareTo((list.get(j + 1)).get(1)) > 0) {
                            List<String> temp = list.get(j);
                            list.set(j, list.get(j + 1));
                            list.set(j + 1, temp);
                            Analytics.totalSubs++;
                        }
                    } else {
                        if ((list.get(j)).get(1).compareTo((list.get(j + 1)).get(1)) < 0) {
                            List<String> temp = list.get(j);
                            list.set(j, list.get(j + 1));
                            list.set(j + 1, temp);
                            Analytics.totalSubs++;
                        }
                    }
                }
            }
        }

        return list;
    }

    public static List<List<String>> bubbleSortState(List<List<String>> list, boolean ascending) {

        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - 1 - i; j++) {

                Analytics.totalComps++;

                if (ascending){
                    if ((list.get(j)).get(2).compareTo((list.get(j + 1)).get(2)) > 0) {
                        List<String> temp = list.get(j);
                        list.set(j, list.get(j + 1));
                        list.set(j + 1, temp);
                        Analytics.totalSubs++;
                    }
                } else {
                    if ((list.get(j)).get(2).compareTo((list.get(j + 1)).get(2)) < 0) {
                        List<String> temp = list.get(j);
                        list.set(j, list.get(j + 1));
                        list.set(j + 1, temp);
                        Analytics.totalSubs++;
                    }
                }
            }
        }

        return list;
    }

}
