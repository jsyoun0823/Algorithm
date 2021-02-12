package Hash;

import java.util.*;

public class 프로그래머스_베스트앨범 {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> index = new HashMap<>();  
        Map<Integer, Integer> genreCount = new HashMap<>();
        Map<Integer, List<int[]>> genrePlays = new HashMap<>();
        int idx = 0;

        int len = genres.length;

        for (int i = 0; i < len; i++) {
            int gIndex = index.getOrDefault(genres[i], idx);

            if (!index.containsKey(genres[i])) {
                index.put(genres[i], idx);
                idx++;
            }
            genreCount.put(gIndex, genreCount.getOrDefault(gIndex, 0) + plays[i]);

            List<int[]> list = genrePlays.getOrDefault(gIndex, new ArrayList<>());
            list.add(new int[]{i, plays[i]});
            genrePlays.put(gIndex, list);
        }

        int genreSize = genreCount.size();

        int[][] genreCounts = new int[genreSize][2];

        for (int i = 0; i < genreSize; i++) {
            genreCounts[i][0] = i;
            genreCounts[i][1] = genreCount.get(i);
        }

        Arrays.sort(genreCounts, (a, b) -> b[1] - a[1]);

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < genreSize; i++) {
            if (genreCounts[i][1] == 0) continue;
            int gIndex = genreCounts[i][0];
            
            List<int[]> list = genrePlays.getOrDefault(gIndex, new ArrayList<>());
            Collections.sort(list, (a, b) -> b[1] - a[1]);

            for (int j = 0; j < list.size() && j < 2; j++) {
                ans.add(list.get(j)[0]);
            }
        }

        int[] answer = new int[ans.size()];

        for (int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i);
        }

        return answer;
    }
}