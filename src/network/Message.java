
import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    private int type;
    private int sender = -1;
    private Object obj;
    public static final int JOIN = 0;
    public static final int NEWPLAYER = 1;

    public Message(int type, int sender, Object obj) {
        this.type = type;
        this.sender = sender;
        this.obj = obj;
    }

    public Message(int type, int sender) {
        this.type = type;
        this.sender = sender;
    }

    public int getType() {
        return this.type;
    }

    public int getSender() { return this.sender; }

    public void setSender(int sender) { this.sender= sender; }

    public Object getObj() { return this.obj; }

    public void setObj(Object newObj) { this.obj = newObj; }

}
