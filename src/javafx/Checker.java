package javafx;

public class Checker {
    //'b' - black, чёрная шашка; 'w' - white, белая шашка; 'c' - [b + 1], чёрная дамка; 'x' - [w + 1], белая дамка
    private char whChk;
    //'g' - green; 'y' - yellow; 'r' - red; 'b' - blue
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
