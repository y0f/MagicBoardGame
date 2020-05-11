package mobanyouxi;

import java.awt.event.*;

import java.awt.*;

import javax.swing.*;

import java.io.*;

import java.applet.*;

import java.net.*;

public class HandleMove extends JPanel implements MouseListener, ActionListener {

    Point[][] point;

    int spendTime = 0;

    int grade = 1;///

    javax.swing.Timer recordTime;

    File fileNewGrade, fileOneGrade, fileTwoGrade;////

    ///////////////

    File musicFile = new File("hello.wav");//

    URL url;//

    AudioClip clip;

    ///////////////

    JTextField showTime;

    VerifySuccess verify;

    Record record;////

    HandleMove() {

        try

        {

            url = musicFile.toURL();

            clip = Applet.newAudioClip(url);

        }

        catch (Exception e)

        {
        }

        recordTime = new javax.swing.Timer(1000, this);

        showTime = new JTextField(16);

        showTime.setEditable(false);

        showTime.setHorizontalAlignment(JTextField.CENTER);

        showTime.setFont(new Font("楷体_GB2312", Font.BOLD, 16));

        JLabel hitMess = new JLabel("用鼠标单击方块", JLabel.CENTER);

        hitMess.setFont(new Font("楷体_GB2312", Font.BOLD, 18));

        add(hitMess);

        add(showTime);

        setBackground(Color.cyan);

        fileNewGrade = new File("新手魔板游戏排行榜.txt");

        fileOneGrade = new File("初级魔板游戏排行榜.txt");///

        fileTwoGrade = new File("高级魔板游戏排行榜.txt");///

    }

    public void setPoint(Point[][] p) {

        point = p;

    }

    public void initSpendTime() {

        recordTime.stop();

        spendTime = 0;

        showTime.setText(null);

        /////////////////////////////////////////

        /*
         * File musicFile=new File("hello.wav");////
         * 
         * URI uri=musicFile.toURI();/////////
         * 
         * URL url=uri.toURL("E:\chaper6");
         * 
         * AudioClip clip=Applet.newAudioClip(url);
         */

        /////////////////////////////////////////

    }

    public void setVerifySuccess(VerifySuccess verify) {

        this.verify = verify;

    }

    public void mousePressed(MouseEvent e) {

        recordTime.start();

        clip.play();/////

        Block block = null;

        block = (Block) e.getSource();

        int w = block.getBounds().width;

        int h = block.getBounds().height;

        int startI = -1, startJ = -1, endI = -1, endJ = -1;

        Point startPoint = block.getAtPoint();

        Point endPoint = null;

        for (int i = 0; i < point.length; i++) {

            for (int j = 0; j < point[0].length; j++)

                if (point[i][j].equals(startPoint)) {

                    startI = i;

                    startJ = j;

                    break;

                }

        }

        for (int i = 0; i < point.length; i++) {

            for (int j = 0; j < point[0].length; j++)

                if (point[i][j].isHaveBlock() == false) {

                    endI = i;

                    endJ = j;

                    endPoint = point[i][j];

                    break;

                }

        }

        boolean moveCondition1 = Math.abs(endI - startI) == 1 && endJ == startJ;

        boolean moveCondition2 = Math.abs(endJ - startJ) == 1 && endI == startI;

        if (moveCondition1 || moveCondition2) {

            block.setLocation(endPoint.getX(), endPoint.getY());

            block.setAtPoint(endPoint);

            endPoint.setBlock(block);

            endPoint.setHaveBlock(true);

            startPoint.setHaveBlock(false);

        }

    }

    public void actionPerformed(ActionEvent e) {

        spendTime++;

        showTime.setText("您的用时:" + spendTime + "秒");

    }

    public void mouseReleased(MouseEvent e) {

        if (verify.isSuccess()) {
            // if(true) {

            recordTime.stop();

            record = new Record();////

            switch (grade) {///

                case 0:
                    record.setGradeFile(fileNewGrade);///

                    break;///

                case 1:
                    record.setGradeFile(fileOneGrade);///

                    break;///

                case 2:
                    record.setGradeFile(fileTwoGrade);///

                    break;///

            }///

            record.setTime(spendTime);///

            record.setVisible(true);////

            JOptionPane.showMessageDialog(this, "您成功了!", "消息框",

                    JOptionPane.INFORMATION_MESSAGE);

            /*
             * record.setTime(spendTime);
             * 
             * record.setGradeFile(gradeFile);
             * 
             * record.setVisible(true);//记录英雄榜
             */

        }

    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {

        /*
         * record.setTime(spendTime);
         * 
         * record.setGradeFile(gradeFile);
         * 
         * record.setVisible(true);//记录英雄榜
         */

    }

}
