package javafx;

public class Checker {
    private char whChk;
    private char color;

    public Checker(char whChk, char color) {
        this.whChk = whChk;
        this.color = color;
    }

    public char getWhChk() {
        return whChk;
    }

    public void setWhChk(char whChk) {
        this.whChk = whChk;
    }

    public char getColor() {
        return color;
    }

    public void setColor(char color) {
        this.color = color;
    }
}
