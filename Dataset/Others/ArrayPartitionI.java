
public class ArrayPartitionI {
    public static void main (String[] args) {
        System.out.println(arrayPairSum(new int[]{6,2,6,5,1,2}));
    }

    public static int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0 , len = nums.length; i < len; i += 2)
            ans += nums[i];
        return ans;
    }
}
