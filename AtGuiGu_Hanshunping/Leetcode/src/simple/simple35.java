package simple;

public class simple35 {
    public static void main(String[] args) {

    }
    /*public int searchInsert(int[] nums, int target) {
        for (int i=0;i<nums.length;i++){
            if (target<=nums[i]){
                return i;
            }
        }
        return nums.length;
    }*/
    public int searchInsert(int[] nums, int target) {
        int left=0;
        int right=nums.length-1;
        while (left<right){
            int mid=(left+right)/2;
            if (nums[mid]==target){
                return mid;
            }else if (nums[mid]<target){
                left=mid+1;
            }else {
                right=mid-1;
            }
        }
        return right +1;
    }

}
