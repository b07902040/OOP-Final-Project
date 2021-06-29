package heart.model;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;
public class StateMachine implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Integer> stack = new ArrayList<Integer>();
    
    public int peek(){
        int top = this.stack.size() - 1;
        if(top >= 0)   
            return this.stack.get(top);
        return -1;
    }

    public int pop(){
        int top = this.stack.size() - 1;
        int v = this.peek();
        if(v != -1){
            this.stack.remove(top);
            return v;
        }
        return -1;
    }

    public void push(int v){
        this.stack.add(v);
    }

    public void clear(){
        this.stack = new ArrayList<Integer>();
    }
    
}