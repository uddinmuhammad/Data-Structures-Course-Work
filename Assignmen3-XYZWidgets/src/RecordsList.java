public class RecordsList {
    class Node {
        RecieptRecord recieptRecord;
        Node next;

        public Node() {
        }

        public Node(RecieptRecord recieptRecord, Node next) {
            this.recieptRecord = recieptRecord;
            this.next = next;
        }

        public Node(RecieptRecord recieptRecord) {
            this(recieptRecord, null);
        }
    }

    public Node first;
    public Node last;

    public RecordsList() {
        first = null;
        last = null;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public int size()
    {
        int count = 0;
        Node p = first;

        while(p != null){
            count++;
            p = p.next;
        }
        return count;
    }

    public void add(RecieptRecord recieptRecord)
    {
        if(isEmpty()){
            first = new Node(recieptRecord);
            last = first;
        }
        else{
            last.next = new Node(recieptRecord);
            last = last.next;
        }
    }


    public RecieptRecord remove(int index){
        RecieptRecord element;
        if(index == 0) {
            element = first.recieptRecord;
            first = first.next;

            if(first == null)
                last = null;
        }
        else{
            Node pred = first;

            for(int i = 1; i < index; i++)
                pred = pred.next;

            element = pred.next.recieptRecord;

            pred.next = pred.next.next;

            if(pred.next == null)
                last = pred;
        }
        return element;
    }

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        Node p = first;
        while(p != null){
            stringBuilder.append(p.recieptRecord+"\n");
            p = p.next;
        }
        return stringBuilder.toString();
    }
}
