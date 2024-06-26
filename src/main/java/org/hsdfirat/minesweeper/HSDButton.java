package org.hsdfirat.minesweeper;

import javax.swing.*;
import java.awt.*;

public class HSDButton extends JButton {

    int row, column,point;
    boolean mine, flag;

    public HSDButton(int row, int column) {
        this.row = row;
        this.column = column;
        this.mine = false;
        this.flag = false;
        setBackground(Color.pink);
    }

    public HSDButton() {
    }

    public boolean isMine(){
        return mine;
    }
    }

