public class RemoveThread extends ThreadTest implements Runnable{

    public RemoveThread(RandomSeq seq, int seqPart, SortList setList) {
        super(seq, seqPart, setList);
    }

    private static int removeSuccessCount = 0;
    private static int removeFailCount = 0;
    public static int getRemoveSuccessCount(){
        return removeSuccessCount;
    }
    public static int getRemoveFailCount(){
        return removeFailCount;
    }
    public void init(){
        removeSuccessCount = 0;
        removeFailCount = 0;
    }
    @Override
    public void run() {
        for(int i = 0; i < nums.length; i++){
            if(list.remove(nums[i])){
                removeSuccessCount++;
            }
            else{
                removeFailCount++;
            }
        }
    }
}
