
public class State {
  
    private byte middle;
    private byte up;
    private byte right;
    private byte down;
    private byte left;

    public State(byte middle, byte up, byte right, byte down, byte left) {
        this.middle = middle;
        this.up = up;
        this.right = right;
        this.down = down;
        this.left = left;
    }

    public byte getMiddle() {
        return middle;
    }

    public void setMiddle(byte middle) {
        this.middle = middle;
    }

    public byte getUp() {
        return up;
    }

    public void setUp(byte up) {
        this.up = up;
    }

    public byte getRight() {
        return right;
    }

    public void setRight(byte right) {
        this.right = right;
    }

    public byte getDown() {
        return down;
    }

    public void setDown(byte down) {
        this.down = down;
    }

    public byte getLeft() {
        return left;
    }

    public void setLeft(byte left) {
        this.left = left;
    }


}
