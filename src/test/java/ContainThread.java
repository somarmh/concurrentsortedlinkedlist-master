public class ContainThread extends ThreadTest implements Runnable{

    public ContainThread(RandomSeq seq, int seqPart, SortList setList) {
        super(seq, seqPart, setList);
    }
    private static int containSuccessCount = 0;
    private static int containFailCount = 0;
    public static int getContainSuccessCount(){
        return containSuccessCount;
    }
    public void init(){
        containSuccessCount = 0;
        containFailCount = 0;
    }
    public static int getContainFailCount() {
        return containFailCount;
    }

    @Override
    public void run() {
        for(int i = 0; i < nums.length; i++){
            if(list.contain(nums[i])){
                containSuccessCount++;
            }
            else{
                containFailCount++;
            }
        }
    }
}
