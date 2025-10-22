// This solution uses a bucket sort approach. We calculate distances for all the workers and bike and store them in map in ascending order of indices
// From min to max distance we try to assign bikes and also use visited arrays to make sure not to reassign bikes
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        Map<Integer, List<int[]>> map = new HashMap();
        int min = 0, max = 0;
        for(int i=0;i<workers.length;i++) {
            for(int j=0;j<bikes.length;j++) {
                int distance = getDistance(workers[i], bikes[j]);
                map.putIfAbsent(distance, new ArrayList());
                map.get(distance).add(new int[]{i, j});
                min=Math.min(min, distance);
                max=Math.max(max, distance);
            }
        }

        int[] response = new int[workers.length];
        boolean[] workerFlag = new boolean[workers.length];
        boolean[] bikeFlag = new boolean[bikes.length];
        for(int i=min;i<=max;i++) {
            if(!map.containsKey(i)) continue;
            for(int[] temp:map.get(i)) {
                if(workerFlag[temp[0]] || bikeFlag[temp[1]]) continue;
                response[temp[0]] = temp[1];
                workerFlag[temp[0]] = true;
                bikeFlag[temp[1]] = true;
            }
        }

        return response;
    }

    private int getDistance(int[] worker, int[] bike) {
        return Math.abs(worker[0]-bike[0])+Math.abs(worker[1]-bike[1]);
    }
}
