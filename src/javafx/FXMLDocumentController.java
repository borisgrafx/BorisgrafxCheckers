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
    public int id = 0;
    public int previd = -1;
    public boolean blackMove = true;
    public Button[] buttons;
    public Checker[] checkers = new Checker[32];
    public int pluser = 0;
    public boolean killStreak = false;
    public boolean transBig = false;

    //event.getSource возвращает строку Button[id=button57, styleClass=button]'' для нажатой кнопки

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
            for (int i = 0; i < 32; i++) {
                checkers[i].setColor('g');
                buttons[i].setStyle("-fx-background-color: #006400;");
            }
            killStreak = false;
            if (Math.abs(id - previd) >= 7 && !transBig) {
                if (blackMove && checkers[id].getWhChk() == 'b') {
                    buttons[id].setStyle("-fx-background-color: #FF0000;");
                    checkers[id].setColor('r');
                    bigMover(id, 'm');
                }
                if (!blackMove && checkers[id].getWhChk() == 'w') {
                    buttons[id].setStyle("-fx-background-color: #FF0000;");
                    checkers[id].setColor('r');
                    bigMover(id, 'm');
                }
                if (blackMove && checkers[id].getWhChk() == 'c') {
                    buttons[id].setStyle("-fx-background-color: #FF0000;");
                    checkers[id].setColor('r');
                    bigMover(id, 'm');
                }
                if (!blackMove && checkers[id].getWhChk() == 'x') {
                    buttons[id].setStyle("-fx-background-color: #FF0000;");
                    checkers[id].setColor('r');
                    bigMover(id, 'm');
                }
                for (int i = 0; i < 32; i++) {
                    if (checkers[i].getColor() == 'b') {
                        killStreak = true;
                        break;
                    }
                }
            }
            if (!killStreak) {
                checkers[id].setColor('g');
                buttons[id].setStyle("-fx-background-color: #006400;");
                id = 0;
                previd = -1;
                blackMove = !blackMove;
            }

        } else if (!killStreak){
            if (previd != id)
                for (int i = 0; i < 32; i++) {
                    checkers[i].setColor('g');
                    buttons[i].setStyle("-fx-background-color: #006400;");
                }
            if (blackMove && checkers[id].getWhChk() == 'b') {
                buttons[id].setStyle("-fx-background-color: #FF0000;");
                checkers[id].setColor('r');
                bigMover(id, 's');
            }
            if (!blackMove && checkers[id].getWhChk() == 'w') {
                buttons[id].setStyle("-fx-background-color: #FF0000;");
                checkers[id].setColor('r');
                bigMover(id, 's');
            }
            if (blackMove && checkers[id].getWhChk() == 'c') {
                buttons[id].setStyle("-fx-background-color: #FF0000;");
                checkers[id].setColor('r');
                bigMover(id, 's');
            }
            if (!blackMove && checkers[id].getWhChk() == 'x') {
                buttons[id].setStyle("-fx-background-color: #FF0000;");
                checkers[id].setColor('r');
                bigMover(id, 's');
            }
        }
        previd = id;
        //Жёлтый #FFFF00
        //Красный #FF0000
        //Синий #2E9AFE
        //Зелёный дефолт #006400
        //Замена одной шашки на другую
        /*InputStream input = getClass().getResourceAsStream("b.png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        button29.setGraphic(imageView);*/


    }

    //shouldEat: 'n' = no; 's' = should; 'm' = must
    //'n' - только ходит; 'm' - только ест; 's' - ходит и, если может, ест
    void bigMover(int id, char shouldEat) {
        boolean idsorter;
        if (shouldEat != 'm') {
            if (blackMove || checkers[id].getWhChk() == 'x' || checkers[id].getWhChk() == 'c') {
                if (id == 4 || id == 12 || id == 20 || id == 3 || id == 11 || id == 19 || id == 27) {
                    if (checkers[id + 4].getWhChk() == 'e') {
                        buttons[id + 4].setStyle("-fx-background-color: #FFFF00;");
                        checkers[id + 4].setColor('y');
                    }
                } else if ((id >= 0 && id <= 2) || (id >= 8 && id <= 10) || (id >= 16 && id <= 18) || (id >= 24 && id <= 26)) {
                    if (checkers[id + 5].getWhChk() == 'e') {
                        buttons[id + 5].setStyle("-fx-background-color: #FFFF00;");
                        checkers[id + 5].setColor('y');
                    }
                    if (checkers[id + 4].getWhChk() == 'e') {
                        buttons[id + 4].setStyle("-fx-background-color: #FFFF00;");
                        checkers[id + 4].setColor('y');
                    }
                } else if ((id >= 5 && id <= 7) || (id >= 13 && id <= 15) || (id >= 21 && id <= 23)) {
                    if (checkers[id + 3].getWhChk() == 'e') {
                        buttons[id + 3].setStyle("-fx-background-color: #FFFF00;");
                        checkers[id + 3].setColor('y');
                    }
                    if (checkers[id + 4].getWhChk() == 'e') {
                        buttons[id + 4].setStyle("-fx-background-color: #FFFF00;");
                        checkers[id + 4].setColor('y');
                    }
                }
            }
            if (!blackMove || checkers[id].getWhChk() == 'x' || checkers[id].getWhChk() == 'c') {
                if (id == 4 || id == 12 || id == 20 || id == 28 || id == 11 || id == 19 || id == 27) {
                    if (checkers[id - 4].getWhChk() == 'e') {
                        buttons[id - 4].setStyle("-fx-background-color: #FFFF00;");
                        checkers[id - 4].setColor('y');
                    }
                } else if ((id >= 8 && id <= 10) || (id >= 16 && id <= 18) || (id >= 24 && id <= 26)) {
                    if (checkers[id - 3].getWhChk() == 'e') {
                        buttons[id - 3].setStyle("-fx-background-color: #FFFF00;");
                        checkers[id - 3].setColor('y');
                    }
                    if (checkers[id - 4].getWhChk() == 'e') {
                        buttons[id - 4].setStyle("-fx-background-color: #FFFF00;");
                        checkers[id - 4].setColor('y');
                    }
                } else if ((id >= 5 && id <= 7) || (id >= 13 && id <= 15) || (id >= 21 && id <= 23) || (id >= 29 && id <= 31)) {
                    if (checkers[id - 5].getWhChk() == 'e') {
                        buttons[id - 5].setStyle("-fx-background-color: #FFFF00;");
                        checkers[id - 5].setColor('y');
                    }
                    if (checkers[id - 4].getWhChk() == 'e') {
                        buttons[id - 4].setStyle("-fx-background-color: #FFFF00;");
                        checkers[id - 4].setColor('y');
                    }
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
            if (blackMove || checkers[id].getWhChk() == 'x' || checkers[id].getWhChk() == 'c') {
                idsorter = (id >= 0 && id <= 3) || (id >= 8 && id <= 11) || (id >= 16 && id <= 19);
                if (idsorter)
                    pluser = 5;
                else pluser = 4;
                if (id != 3 && id != 7 && id != 11 && id != 15 && id != 19 && id < 23 && checkers[id + 9].getWhChk() == 'e' && (checkers[id + pluser].getWhChk() == c1 || checkers[id + pluser].getWhChk() == c2)) {
                    buttons[id + pluser].setStyle("-fx-background-color: #2E9AFE;");
                    checkers[id + pluser].setColor('b');
                    buttons[id + 9].setStyle("-fx-background-color: #FFFF00;");
                    checkers[id + 9].setColor('y');
                }
                if (idsorter)
                    pluser = 4;
                else pluser = 3;
                if (id != 0 && id != 4 && id != 8 && id != 12 && id != 16 && id != 20 && id < 24 && checkers[id + 7].getWhChk() == 'e' && (checkers[id + pluser].getWhChk() == c1 || checkers[id + pluser].getWhChk() == c2)) {
                    buttons[id + pluser].setStyle("-fx-background-color: #2E9AFE;");
                    checkers[id + pluser].setColor('b');
                    buttons[id + 7].setStyle("-fx-background-color: #FFFF00;");
                    checkers[id + 7].setColor('y');
                }
            }
            if (!blackMove || checkers[id].getWhChk() == 'x' || checkers[id].getWhChk() == 'c') {
                idsorter = (id >= 24 && id <= 27) || (id >= 8 && id <= 11) || (id >= 16 && id <= 19);
                if (idsorter)
                    pluser = 4;
                else pluser = 5;
                if (id > 8 && id != 12 && id != 16 && id != 20 && id != 24 && id != 28 && checkers[id - 9].getWhChk() == 'e' && (checkers[id - pluser].getWhChk() == c1 || checkers[id - pluser].getWhChk() == c2)) {
                    buttons[id - pluser].setStyle("-fx-background-color: #2E9AFE;");
                    checkers[id - pluser].setColor('b');
                    buttons[id - 9].setStyle("-fx-background-color: #FFFF00;");
                    checkers[id - 9].setColor('y');
                }
                if (idsorter)
                    pluser = 3;
                else pluser = 4;
                if (id > 7 && id != 11 && id != 15 && id != 19 && id != 23 && id != 27 && id != 31 && checkers[id - 7].getWhChk() == 'e' && (checkers[id - pluser].getWhChk() == c1 || checkers[id - pluser].getWhChk() == c2)) {
                    buttons[id - pluser].setStyle("-fx-background-color: #2E9AFE;");
                    checkers[id - pluser].setColor('b');
                    buttons[id - 7].setStyle("-fx-background-color: #FFFF00;");
                    checkers[id - 7].setColor('y');
                }
            }
        }
    }

    void move(int id, int previd) {
        if (blackMove || checkers[previd].getWhChk() == 'c' || checkers[previd].getWhChk() == 'x') {
            if (id - previd == 9) {
                if (checkers[id - 4].getColor() == 'b') {
                    buttons[id - 4].setGraphic(new ImageView());
                    checkers[id - 4].setWhChk('e');
                } else if (checkers[id - 5].getColor() == 'b') {
                    buttons[id - 5].setGraphic(new ImageView());
                    checkers[id - 5].setWhChk('e');
                }
            } else if (id - previd == 7) {
                if (checkers[id - 4].getColor() == 'b') {
                    buttons[id - 4].setGraphic(new ImageView());
                    checkers[id - 4].setWhChk('e');
                } else if (checkers[id - 3].getColor() == 'b') {
                    buttons[id - 3].setGraphic(new ImageView());
                    checkers[id - 3].setWhChk('e');
                }
            }
        }
        if (!blackMove || checkers[previd].getWhChk() == 'c' || checkers[previd].getWhChk() == 'x') {
            if (previd - id == 9) {
                if (checkers[id + 4].getColor() == 'b') {
                    buttons[id + 4].setGraphic(new ImageView());
                    checkers[id + 4].setWhChk('e');
                } else if (checkers[id + 5].getColor() == 'b') {
                    buttons[id + 5].setGraphic(new ImageView());
                    checkers[id + 5].setWhChk('e');
                }
            } else if (previd - id == 7) {
                if (checkers[id + 4].getColor() == 'b') {
                    buttons[id + 4].setGraphic(new ImageView());
                    checkers[id + 4].setWhChk('e');
                } else if (checkers[id + 3].getColor() == 'b') {
                    buttons[id + 3].setGraphic(new ImageView());
                    checkers[id + 3].setWhChk('e');
                }
            }
        }
        char name = checkers[previd].getWhChk();
        if (name == 'b' && id >= 28 && id <= 31) {
            name = 'c';
            transBig = true;
        }
        else if (name == 'w' && id <= 3) {
            name = 'x';
            transBig = true;
        } else transBig = false;
        checkers[id].setWhChk(name);
        checkers[previd].setWhChk('e');
        InputStream input = getClass().getResourceAsStream(name + ".png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        buttons[id].setGraphic(imageView);
        buttons[previd].setGraphic(new ImageView());
    }

    public void checkBugs(){
        /*blackMove  = false;
        for (int i = 0; i < 32; i++) {
            buttons[i].setGraphic(new ImageView());
            checkers[i].setWhChk('e');
        }
        checkers[25].setWhChk('w');
        InputStream input = getClass().getResourceAsStream("w.png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        buttons[25].setGraphic(imageView);

        checkers[21].setWhChk('b');
        input = getClass().getResourceAsStream("b.png");
        image = new Image(input);
        imageView = new ImageView(image);
        buttons[21].setGraphic(imageView);
        checkers[22].setWhChk('b');
        input = getClass().getResourceAsStream("b.png");
        image = new Image(input);
        imageView = new ImageView(image);
        buttons[22].setGraphic(imageView);*/
        /*for (int i = 0; i < 32; i++) {
            buttons[i].setGraphic(new ImageView());
            checkers[i].setWhChk('e');
        }
        checkers[6].setWhChk('b');
        InputStream input = getClass().getResourceAsStream("b.png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        buttons[6].setGraphic(imageView);

        checkers[9].setWhChk('w');
        input = getClass().getResourceAsStream("w.png");
        image = new Image(input);
        imageView = new ImageView(image);
        buttons[9].setGraphic(imageView);
        checkers[10].setWhChk('w');
        input = getClass().getResourceAsStream("w.png");
        image = new Image(input);
        imageView = new ImageView(image);
        buttons[10].setGraphic(imageView);*/

        /*for (int i = 0; i < 32; i++) {
            buttons[i].setGraphic(new ImageView());
            checkers[i].setWhChk('e');
        }
        checkers[1].setWhChk('b');
        InputStream input = getClass().getResourceAsStream("b.png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        buttons[1].setGraphic(imageView);

        checkers[5].setWhChk('w');
        input = getClass().getResourceAsStream("w.png");
        image = new Image(input);
         imageView = new ImageView(image);
        buttons[5].setGraphic(imageView);
        checkers[6].setWhChk('w');
        input = getClass().getResourceAsStream("w.png");
        image = new Image(input);
         imageView = new ImageView(image);
        buttons[6].setGraphic(imageView);
        checkers[12].setWhChk('w');
        input = getClass().getResourceAsStream("w.png");
        image = new Image(input);
        imageView = new ImageView(image);
        buttons[12].setGraphic(imageView);
        checkers[13].setWhChk('w');
        input = getClass().getResourceAsStream("w.png");
        image = new Image(input);
        imageView = new ImageView(image);
        buttons[13].setGraphic(imageView);
        checkers[14].setWhChk('w');
        input = getClass().getResourceAsStream("w.png");
        image = new Image(input);
        imageView = new ImageView(image);
        buttons[14].setGraphic(imageView);
        checkers[15].setWhChk('w');
        input = getClass().getResourceAsStream("w.png");
        image = new Image(input);
        imageView = new ImageView(image);
        buttons[15].setGraphic(imageView);*/

        /*blackMove  = false;
        for (int i = 0; i < 32; i++) {
            buttons[i].setGraphic(new ImageView());
            checkers[i].setWhChk('e');
        }
        checkers[30].setWhChk('w');
        InputStream input = getClass().getResourceAsStream("w.png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        buttons[30].setGraphic(imageView);

        checkers[25].setWhChk('b');
         input = getClass().getResourceAsStream("b.png");
         image = new Image(input);
        imageView = new ImageView(image);
        buttons[25].setGraphic(imageView);
        checkers[26].setWhChk('b');
        input = getClass().getResourceAsStream("b.png");
        image = new Image(input);
        imageView = new ImageView(image);
        buttons[26].setGraphic(imageView);
        checkers[17].setWhChk('b');
        input = getClass().getResourceAsStream("b.png");
        image = new Image(input);
        imageView = new ImageView(image);
        buttons[17].setGraphic(imageView);
        checkers[16].setWhChk('b');
        input = getClass().getResourceAsStream("b.png");
        image = new Image(input);
        imageView = new ImageView(image);
        buttons[16].setGraphic(imageView);
        checkers[18].setWhChk('b');
        input = getClass().getResourceAsStream("b.png");
        image = new Image(input);
        imageView = new ImageView(image);
        buttons[18].setGraphic(imageView);
        checkers[9].setWhChk('b');
        input = getClass().getResourceAsStream("b.png");
        image = new Image(input);
        imageView = new ImageView(image);
        buttons[9].setGraphic(imageView);
        checkers[10].setWhChk('b');
        input = getClass().getResourceAsStream("b.png");
        image = new Image(input);
        imageView = new ImageView(image);
        buttons[10].setGraphic(imageView);*/
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttons = new Button[]{button1, button2, button3, button4, button5, button6, button7, button8,
                button9, button10, button11, button12, button13, button14, button15, button16, button17, button18,
                button19, button20, button21, button22, button23, button24, button25, button26, button27, button28,
                button29, button30, button31, button32};

        for (int i = 0; i < 12; i++) {
            checkers[i] = new Checker('b', 'g');
        }
        for (int i = 12; i < 20; i++) {
            checkers[i] = new Checker('e', 'g');
        }
        for (int i = 20; i < 32; i++) {
            checkers[i] = new Checker('w', 'g');
        }
        id = 0;
        for (int i = 0; i < 12; i++) {
            InputStream input = getClass().getResourceAsStream("b.png");
            Image image = new Image(input);
            ImageView imageView = new ImageView(image);
            buttons[i].setGraphic(imageView);
        }

        for (int i = 20; i < 32; i++) {
            InputStream input = getClass().getResourceAsStream("w.png");
            Image image = new Image(input);
            ImageView imageView = new ImageView(image);
            buttons[i].setGraphic(imageView);
        }
        blackMove = true;
        //checkBugs();
    }
}
