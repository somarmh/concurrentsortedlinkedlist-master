public abstract class SortList {

    public Entry head;

    public SortList() {
        this.head = new Entry(Integer.MIN_VALUE);
       this.head.next =new Entry(Integer.MAX_VALUE);
    }

    public  abstract  boolean add(Integer obj);
    public  abstract  boolean remove(Integer obj);
    public  abstract  boolean contain(Integer obj);

    public void printList(){
        Entry curr = this.head;
        while (curr != null){
            System.out.println(curr.object);
            curr = curr.next;

        }
    }

    public boolean checkSort(){
        boolean sorted = true;
        Entry curr = this.head;
        int prev = curr.object;
        curr = curr.next;
        while(curr != null){
            if(curr.object < prev){
                sorted = false;
            }
            curr = curr.next;
        }
        return sorted;
    }

    public int length(){
        Entry curr = this.head;
        int length = 0;
        while(curr != null){
            length++;
            curr = curr.next;
        }
        return length;
    }
}
