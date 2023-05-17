import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class SyncListTest extends TestCase {

    public void testAddList(){

        SyncList syncList = new SyncList();

        syncList.add(1);
        syncList.add(2);
        syncList.add(3);
        syncList.add(Integer.MIN_VALUE);
        syncList.add(3);
        System.out.println(syncList.contain(5));
        System.out.println(syncList.contain(2));
        syncList.remove(3);



    }
    public void testRandomSeq(){
        RandomSeq randomSeq = new RandomSeq(2, 80000);
        for(int i = 0; i <10; i++){
            System.out.println(randomSeq.next() + " ");
        }
    }

    int randLen = 20000;
    public void init(SortList list){
        RandomSeq randomSeq = new RandomSeq(0, 80000);
        ContainThread th = new ContainThread(randomSeq, randLen/3, list);
        th.init();
        RemoveThread th1 = new RemoveThread(randomSeq, randLen/3, list);
        th1.init();
    }

    //int core =
    public void testHelp(SortList list, String label){
        RandomSeq randomSeq = new RandomSeq(0, 80000);
        List<Thread> addThreads = new ArrayList<>();
        List<Thread> containThreads = new ArrayList<>();
        List<Thread> removeThreads = new ArrayList<>();
        //runtimegetprocessor

        for(int i = 0; i < 6; i++){
            AddThread addThread = new AddThread(randomSeq, randLen/6, list);
            ContainThread containThread = new ContainThread(randomSeq, randLen/6, list);
            RemoveThread removeThread = new RemoveThread(randomSeq, randLen/6, list);
            Thread threada = new Thread(addThread);
            addThreads.add(threada);
            Thread threadc = new Thread(containThread);
            containThreads.add(threadc);
            Thread threadr = new Thread(removeThread);
            removeThreads.add(threadr);
        }
        System.out.println(Runtime.getRuntime().availableProcessors());
        long start = System.currentTimeMillis();
        addThreads.stream().forEach(e -> e.start());
        int totalSuccessCount = 0;
        addThreads.stream().forEach(e->{
            try{
                e.join();
            }
            catch (InterruptedException ex){
                throw new RuntimeException(ex);
            }
        });
        long end = System.currentTimeMillis() - start;
        if(!list.checkSort()){
            System.out.println("Error the list is not sorted");
        }
        else{
            System.out.println("The list is sorted successfully");
        }
        System.out.println("ADD "+ label + " executation task " + end);
        int listLengthAfterAdds = list.length();
        System.out.println("List length after add is " + listLengthAfterAdds);

        long startc = System.currentTimeMillis();
        containThreads.stream().forEach(e -> e.start());
        containThreads.stream().forEach(e->{
            try{
                e.join();
            }
            catch (InterruptedException ex){
                throw new RuntimeException(ex);
            }
        });
        long endc = System.currentTimeMillis() - startc;

        System.out.println("Contain " + label + " executation task " + endc);
        ContainThread th = new ContainThread(randomSeq, randLen/3, list);
        System.out.println("Total number of successes found " + th.getContainSuccessCount() + ", failures found:" + th.getContainFailCount() );

        long startr = System.currentTimeMillis();
        removeThreads.stream().forEach(e -> e.start());
        removeThreads.stream().forEach(e->{
            try{
                e.join();
            }
            catch (InterruptedException ex){
                throw new RuntimeException(ex);
            }
        });
        long endr = System.currentTimeMillis() - startr;
        System.out.println("Remove " + label + " executation task " + endr);
        if(!list.checkSort()){
            System.out.println("Error the list is not sorted");
        }
        else{
            System.out.println("The list is sorted successfully");
        }
        int listLengthAfterRemove = list.length();
        System.out.println("List length after remove is " + listLengthAfterRemove);
        RemoveThread th1 = new RemoveThread(randomSeq, randLen/3, list);
        System.out.println("Total number of successes removed " + th1.getRemoveSuccessCount() + ", failures removed:" + th1.getRemoveFailCount() );

    }

    public void testRun(){
        SyncList syncList = new SyncList();
        testHelp(syncList, "Synchronized");

        init(syncList);

        System.out.println("++++++++++++++++++++");
        RWLockList rwLockList = new RWLockList();
        testHelp(rwLockList, "RWLock");

        init(rwLockList);
        System.out.println("++++++++++++++++++++");
        LockList lockList = new LockList();
        testHelp(lockList, "LockList");
    }
}
