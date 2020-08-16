package javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLDocumentController implements Initializable {
    public Button button1;
    public Button button2;
    public Button button3;
    public Button button4;
    public Button button5;
    public Button button6;
    public Button button7;
    public Button button8;
    public Button button9;
    public Button button10;
    public Button button11;
    public Button button12;
    public Button button13;
    public Button button14;
    public Button button15;
    public Button button16;
    public Button button17;
    public Button button18;
    public Button button19;
    public Button button20;
    public Button button21;
    public Button button22;
    public Button button23;
    public Button button24;
    public Button button25;
    public Button button26;
    public Button button27;
    public Button button28;
    public Button button29;
    public Button button30;
    public Button button31;
    public Button button32;

    private int id;
    private int previd;
    private boolean blackMove = true;
    public Button[] buttons;
    public Checker[] checkers;
    private boolean killStreak = false;
    private boolean transBig = false;

    //event.getSource возвращает строку Button[id=button57, styleClass=button]'' для нажатой кнопки
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttons = new Button[]{button1, button2, button3, button4, button5, button6, button7, button8,
                button9, button10, button11, button12, button13, button14, button15, button16, button17, button18,
                button19, button20, button21, button22, button23, button24, button25, button26, button27, button28,
                button29, button30, button31, button32};
        checkers = new Checker[32];
        newGame();
    }

    public void newGame() {
        for (int i = 0; i < 12; i++) {
            checkers[i] = new Checker('b', 'g');
        }
        for (int i = 12; i < 20; i++) {
            checkers[i] = new Checker('e', 'g');
        }
        for (int i = 20; i < 32; i++) {
            checkers[i] = new Checker('w', 'g');
        }
        clearColors();
        id = 0;
        previd = 1;
        for (int i = 0; i < 12; i++)
            imageChanger(i, 'b');
        for (int i = 12; i < 20; i++)
            buttons[i].setGraphic(new ImageView());
        for (int i = 20; i < 32; i++)
            imageChanger(i, 'w');
        blackMove = true;
    }

    @FXML
    private void buttonAction(ActionEvent event) {
        String source = event.getSource().toString();
        id = 0;
        for (int i = 0; i < source.length(); i++) {
            if (Character.isDigit(source.charAt(i)))
                id = id * 10 + Character.getNumericValue(source.charAt(i));
        }
        id--;
        if (killStreak && checkers[id].getColor() != 'y')
            id = previd;
        if (checkers[id].getColor() == 'y') {
            move(id, previd);
            clearColors();
            killStreak = false;
            if (Math.abs(id - previd) >= 7 && !transBig) {
                if ((blackMove && (checkers[id].getWhChk() == 'b' || checkers[id].getWhChk() == 'c'))
                        || (!blackMove && (checkers[id].getWhChk() == 'w' || checkers[id].getWhChk() == 'x'))) {
                    recoloriser(id, 'r');
                    chooser('m');
                }
                for (int i = 0; i < 32; i++) {
                    if (checkers[i].getColor() == 'b') {
                        killStreak = true;
                        break;
                    }
                }
            }
            if (!killStreak) {
                recoloriser(id, 'g');
                id = 0;
                previd = -1;
                blackMove = !blackMove;
            }

        } else if (!killStreak) {
            if (previd != id)
                clearColors();
            if ((blackMove && (checkers[id].getWhChk() == 'b' || checkers[id].getWhChk() == 'c'))
                    || (!blackMove && (checkers[id].getWhChk() == 'w' || checkers[id].getWhChk() == 'x'))) {
                recoloriser(id, 'r');
                chooser('s');
            }
        }
        previd = id;
        //Жёлтый #FFFF00
        //Красный #FF0000
        //Синий #2E9AFE
        //Зелёный дефолт #006400
    }

    //shouldEat: 'n' = no; 's' = should; 'm' = must
    //'n' - только ходит; 'm' - только ест; 's' - ходит и, если может, ест
    public void chooser(char shouldEat) {
        boolean idsorter;
        if (shouldEat != 'm') {
            if (blackMove || checkers[id].getWhChk() == 'x' || checkers[id].getWhChk() == 'c') {
                if (id == 4 || id == 12 || id == 20 || id == 3 || id == 11 || id == 19 || id == 27) {
                    if (checkers[id + 4].getWhChk() == 'e')
                        recoloriser(id + 4, 'y');
                } else if ((id >= 0 && id <= 2) || (id >= 8 && id <= 10) || (id >= 16 && id <= 18)
                        || (id >= 24 && id <= 26)) {
                    if (checkers[id + 5].getWhChk() == 'e')
                        recoloriser(id + 5, 'y');
                    if (checkers[id + 4].getWhChk() == 'e')
                        recoloriser(id + 4, 'y');
                } else if ((id >= 5 && id <= 7) || (id >= 13 && id <= 15) || (id >= 21 && id <= 23)) {
                    if (checkers[id + 3].getWhChk() == 'e')
                        recoloriser(id + 3, 'y');
                    if (checkers[id + 4].getWhChk() == 'e')
                        recoloriser(id + 4, 'y');
                }
            }
            if (!blackMove || checkers[id].getWhChk() == 'x' || checkers[id].getWhChk() == 'c') {
                if (id == 4 || id == 12 || id == 20 || id == 28 || id == 11 || id == 19 || id == 27) {
                    if (checkers[id - 4].getWhChk() == 'e')
                        recoloriser(id - 4, 'y');
                } else if ((id >= 8 && id <= 10) || (id >= 16 && id <= 18) || (id >= 24 && id <= 26)) {
                    if (checkers[id - 3].getWhChk() == 'e')
                        recoloriser(id - 3, 'y');
                    if (checkers[id - 4].getWhChk() == 'e')
                        recoloriser(id - 4, 'y');
                } else if ((id >= 5 && id <= 7) || (id >= 13 && id <= 15) || (id >= 21 && id <= 23)
                        || (id >= 29 && id <= 31)) {
                    if (checkers[id - 5].getWhChk() == 'e')
                        recoloriser(id - 5, 'y');
                    if (checkers[id - 4].getWhChk() == 'e')
                        recoloriser(id - 4, 'y');
                }
            }
        }
        if (shouldEat != 'n') {
            char c1;
            char c2;
            if (blackMove) {
                c1 = 'w';
                c2 = 'x';
            } else {
                c1 = 'b';
                c2 = 'c';
            }
            int pluser;
            if (blackMove || checkers[id].getWhChk() == 'x' || checkers[id].getWhChk() == 'c') {
                idsorter = (id >= 0 && id <= 3) || (id >= 8 && id <= 11) || (id >= 16 && id <= 19);
                if (idsorter)
                    pluser = 5;
                else pluser = 4;
                if (id != 3 && id != 7 && id != 11 && id != 15 && id != 19 && id < 23
                        && checkers[id + 9].getWhChk() == 'e' && (checkers[id + pluser].getWhChk() == c1
                        || checkers[id + pluser].getWhChk() == c2)) {
                    recoloriser(id + pluser, 'b');
                    recoloriser(id + 9, 'y');
                }
                if (idsorter)
                    pluser = 4;
                else pluser = 3;
                if (id != 0 && id != 4 && id != 8 && id != 12 && id != 16 && id != 20 && id < 24
                        && checkers[id + 7].getWhChk() == 'e' && (checkers[id + pluser].getWhChk() == c1
                        || checkers[id + pluser].getWhChk() == c2)) {
                    recoloriser(id + pluser, 'b');
                    recoloriser(id + 7, 'y');
                }
            }
            if (!blackMove || checkers[id].getWhChk() == 'x' || checkers[id].getWhChk() == 'c') {
                idsorter = (id >= 24 && id <= 27) || (id >= 8 && id <= 11) || (id >= 16 && id <= 19);
                if (idsorter)
                    pluser = 4;
                else pluser = 5;
                if (id > 8 && id != 12 && id != 16 && id != 20 && id != 24 && id != 28
                        && checkers[id - 9].getWhChk() == 'e' && (checkers[id - pluser].getWhChk() == c1
                        || checkers[id - pluser].getWhChk() == c2)) {
                    recoloriser(id - pluser, 'b');
                    recoloriser(id - 9, 'y');
                }
                if (idsorter)
                    pluser = 3;
                else pluser = 4;
                if (id > 7 && id != 11 && id != 15 && id != 19 && id != 23 && id != 27 && id != 31
                        && checkers[id - 7].getWhChk() == 'e' && (checkers[id - pluser].getWhChk() == c1
                        || checkers[id - pluser].getWhChk() == c2)) {
                    recoloriser(id - pluser, 'b');
                    recoloriser(id - 7, 'y');
                }
            }
        }
    }

    public void move(int id, int previd) {
        if (blackMove || checkers[previd].getWhChk() == 'c' || checkers[previd].getWhChk() == 'x') {
            if (id - previd == 9) {
                if (checkers[id - 4].getColor() == 'b')
                    imageChanger(id - 4, 'n');
                else if (checkers[id - 5].getColor() == 'b')
                    imageChanger(id - 5, 'n');
            } else if (id - previd == 7) {
                if (checkers[id - 4].getColor() == 'b')
                    imageChanger(id - 4, 'n');
                else if (checkers[id - 3].getColor() == 'b')
                    imageChanger(id - 3, 'n');
            }
        }
        if (!blackMove || checkers[previd].getWhChk() == 'c' || checkers[previd].getWhChk() == 'x') {
            if (previd - id == 9) {
                if (checkers[id + 4].getColor() == 'b')
                    imageChanger(id + 4, 'n');
                else if (checkers[id + 5].getColor() == 'b')
                    imageChanger(id + 5, 'n');
            } else if (previd - id == 7) {
                if (checkers[id + 4].getColor() == 'b')
                    imageChanger(id + 4, 'n');
                else if (checkers[id + 3].getColor() == 'b')
                    imageChanger(id + 3, 'n');
            }
        }
        char name = checkers[previd].getWhChk();
        if (name == 'b' && id >= 28 && id <= 31) {
            name = 'c';
            transBig = true;
        } else if (name == 'w' && id <= 3) {
            name = 'x';
            transBig = true;
        } else transBig = false;
        imageChanger(id, name);
        imageChanger(previd, 'n');
    }

    void clearColors() {
        for (int i = 0; i < 32; i++) {
            recoloriser(i, 'g');
        }
    }

    void recoloriser(int checkerNum, char newColor) {
        checkers[checkerNum].setColor(newColor);
        switch (newColor) {
            case 'b':
                buttons[checkerNum].setStyle("-fx-background-color: #2E9AFE;");
                break;
            case 'y':
                buttons[checkerNum].setStyle("-fx-background-color: #FFFF00;");
                break;
            case 'r':
                buttons[checkerNum].setStyle("-fx-background-color: #FF0000;");
                break;
            default:
                buttons[checkerNum].setStyle("-fx-background-color: #006400;");
                break;
        }
    }

    void imageChanger(int checkerNum, char name) {
        if (name != 'n') {
            InputStream input = getClass().getResourceAsStream(name + ".png");
            Image image = new Image(input);
            ImageView imageView = new ImageView(image);
            buttons[checkerNum].setGraphic(imageView);
            checkers[checkerNum].setWhChk(name);
        } else {
            checkers[checkerNum].setWhChk('e');
            buttons[checkerNum].setGraphic(new ImageView());
        }

    }

    //Далее идут методы, связанные с тестированием

    public void myWorld(char[] checko) {
        Button button1 = new Button();
        Button button2 = new Button();
        Button button3 = new Button();
        Button button4 = new Button();
        Button button5 = new Button();
        Button button6 = new Button();
        Button button7 = new Button();
        Button button8 = new Button();
        Button button9 = new Button();
        Button button10 = new Button();
        Button button11 = new Button();
        Button button12 = new Button();
        Button button13 = new Button();
        Button button14 = new Button();
        Button button15 = new Button();
        Button button16 = new Button();
        Button button17 = new Button();
        Button button18 = new Button();
        Button button19 = new Button();
        Button button20 = new Button();
        Button button21 = new Button();
        Button button22 = new Button();
        Button button23 = new Button();
        Button button24 = new Button();
        Button button25 = new Button();
        Button button26 = new Button();
        Button button27 = new Button();
        Button button28 = new Button();
        Button button29 = new Button();
        Button button30 = new Button();
        Button button31 = new Button();
        Button button32 = new Button();
        buttons = new Button[]{button1, button2, button3, button4, button5, button6, button7, button8,
                button9, button10, button11, button12, button13, button14, button15, button16, button17, button18,
                button19, button20, button21, button22, button23, button24, button25, button26, button27, button28,
                button29, button30, button31, button32};
        checkers = new Checker[32];
        newGame();
        for (int i = 0; i < checko.length; i++) {
            checkers[i] = new Checker(checko[i], 'g');
        }
        for (int i = checko.length; i < 32; i++) {
            checkers[i] = new Checker('e', 'g');
        }
        for (int i = 0; i < checko.length; i++) {
            checkers[i].setWhChk(checko[i]);
        }
    }

    public char[] getCheckers() {
        char[] whoIsWho = new char[32];
        for (int i = 0; i < 32; i++)
            whoIsWho[i] = checkers[i].getWhChk();
        return whoIsWho;
    }

    void setId(int setCur, int setPrev, boolean whoseMove) {
        id = setCur;
        previd = setPrev;
        blackMove = whoseMove;
    }
}
