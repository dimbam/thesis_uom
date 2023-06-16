
public class CourseSchedule {
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        //do top sort, use numCourses to prefill inDegree

        if(prerequisites == null || prerequisites.length == 0) return true;

        Map<Integer, Integer> inDegree = new HashMap<>();
        Map<Integer, List<Integer>> adj = new HashMap<>();

        for(int course = 0; course < numCourses; course++) inDegree.put(course, 0);

        //now fill adj list and inDegrees

        for(int[] coursePair : prerequisites){

            int from = coursePair[1];
            int to = coursePair[0];

            if(!adj.containsKey(from))
                adj.put(from, new ArrayList<>());

            adj.get(from).add(to);

            inDegree.compute(to , (k , v)-> v == null ? 1 : v + 1);
        }

        //find the 0 inDegree nodes

        Queue<Integer> toVisit = new LinkedList<>();

        for(int course = 0; course < numCourses; course++){

            if(inDegree.get(course) > 0) continue;

            toVisit.offer(course);
        }

        if(toVisit.isEmpty()) return false;

        //process all vertices in the toVisit q, and decrees the edges
        int coursesTaken = 0;

        while(!toVisit.isEmpty()){
            int vertex = toVisit.poll();
            coursesTaken++;
            List<Integer> edges = adj.get(vertex);

            if(edges == null) continue;
            for(Integer courseToTake : edges){
                inDegree.compute(courseToTake , (k , v)-> v == null ? 0 : v -1);
                if(inDegree.get(courseToTake) == 0)
                    toVisit.offer(courseToTake);
            }
        }
        return coursesTaken == numCourses;
    }

    public static void main (String[] args) {
        System.out.println(canFinish(2 , new int[][]{{1 , 0} , {0 , 1}}));
    }
}
