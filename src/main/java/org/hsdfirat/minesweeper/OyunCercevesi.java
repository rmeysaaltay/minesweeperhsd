package org.hsdfirat.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class OyunCercevesi extends JFrame {
    public static final int GENISLIK = 10;
    public static final int YUKSEKLIK = 10;
    public static final int MAYIN_SAYISI = 10;
    HSDButton[][] hsDbutons = new HSDButton[YUKSEKLIK][GENISLIK];

    public OyunCercevesi() {
        setSize(800, 800);
        setTitle("Mayın Oyunu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(10, 10));

        for (int i = 0; i < YUKSEKLIK; i++) {
            for (int j = 0; j < GENISLIK; j++) {
                hsDbutons[i][j] = new HSDButton(i, j);
                hsDbutons[i][j].addActionListener(new ButtonClickListener());

                add(hsDbutons[i][j]);
            }
        }
        mayinYerlestir();
     puanlariEkle();
        //herSeyiGoster();
    }

    void mayinYerlestir() {

        Random rastgele = new Random();
        Set<Point> mineLocations = new HashSet<>();
        while (mineLocations.size() < MAYIN_SAYISI) {
            int x = rastgele.nextInt(GENISLIK);
            int y = rastgele.nextInt(YUKSEKLIK);
            mineLocations.add(new Point(x, y));
        }
        for (Point point : mineLocations) {
            hsDbutons[point.y][point.x].mine = true;
        }
    } /* for (int i = 0; i < MAYIN_SAYISI; i++) {
            Random rastgele = new Random();
            int x = rastgele.nextInt(10);
            int y = rastgele.nextInt(10);
            hsDbutons[x][y].mine = true;
        }
    }*/

    void puanlariEkle() {
        for (int i = 0; i < YUKSEKLIK; i++) {
            for (int j = 0; j < GENISLIK; j++) {
                int puan = cevreKontrol(hsDbutons[i][j], i, j);
                hsDbutons[i][j].point = puan;
            }
        }
    }

    int cevreKontrol(HSDButton merkezButon, int x, int y) {
        int baslangic_x = Math.max(x - 1, 0);
        int baslangic_y = Math.max(y - 1, 0);
        int bitis_x = Math.min(x+1, GENISLIK-1);
        int bitis_y = Math.min(y+1, YUKSEKLIK-1);

        int puan = 0;
        for (int i = baslangic_x; i<=bitis_x; i++) {
            for (int j = baslangic_y; j<=bitis_y; j++) {
                if (i == x && j == y) continue;
                if (hsDbutons[i][j].mine) {
                    puan++;
                }
            }
        }
        return puan;

    }

    void herSeyiGoster() {
        for (int i = 0; i < YUKSEKLIK; i++) {
            for (int j = 0; j < GENISLIK; j++) {
                if (hsDbutons[i][j].mine) hsDbutons[i][j].setText("M");

                else hsDbutons[i][j].setText(hsDbutons[i][j].point + "");
            }
        }
    }
    void oyunBitti() {
       herSeyiGoster();
        JOptionPane.showMessageDialog(this, "Oyun Bitti!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

    class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            HSDButton button = (HSDButton) e.getSource();
            if (button.isMine()) {
                oyunBitti();
            } else {
                button.setText(button.point + "");
                button.setEnabled(false);
            }
        }
    }
}


