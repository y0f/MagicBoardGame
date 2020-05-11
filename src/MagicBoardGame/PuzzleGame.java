package MagicBoardGame;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

import java.io.*;

import javax.swing.filechooser.*;

public class PuzzleGame extends JFrame implements ActionListener {

    PuzzlePad puzzlePad;

    JMenuBar bar;

    JMenu gradeMenu, choiceImage, resultMenu, modeMenu;///////////

    JMenuItem newGrade, oneGrade, twoGrade, newImage, defaultImage;

    JMenuItem newGradeResult, oneGradeResult, twoGradeResult;////////

    File fileNewGrade, fileOneGrade, fileTwoGrade, gradeFile;//////////

    JMenuItem digitPlay, imagePlay;

    ButtonGroup group = null;

    // Record record=null;

    ShowRecordDialog showDiolag = null;///////////

    JButton startButton;

    JButton refreshButton;

    Image image;

    Toolkit tool;

    public PuzzleGame() {

        fileNewGrade = new File("新手魔板游戏排行榜.txt");

        fileOneGrade = new File("初级魔板游戏排行榜.txt");

        fileTwoGrade = new File("高级魔板游戏排行榜.txt");

        tool = getToolkit();

        bar = new JMenuBar();

        modeMenu = new JMenu("选择玩法");

        digitPlay = new JMenuItem("数字玩法");

        imagePlay = new JMenuItem("图像玩法");

        modeMenu.add(digitPlay);

        modeMenu.add(imagePlay);

        gradeMenu = new JMenu("选择级别");

        newGrade = new JMenuItem("新手");

        oneGrade = new JMenuItem("初级");

        twoGrade = new JMenuItem("高级");
        gradeMenu.add(newGrade);

        gradeMenu.add(oneGrade);

        gradeMenu.add(twoGrade);

        choiceImage = new JMenu("选择图像");

        newImage = new JMenuItem("选择一幅新图像");

        defaultImage = new JMenuItem("使用默认图像");

        choiceImage.add(newImage);

        choiceImage.add(defaultImage);

        resultMenu = new JMenu("查看英雄榜");/////////////

        newGradeResult = new JMenuItem("新手英雄榜");

        oneGradeResult = new JMenuItem("初级英雄榜"); /////////////

        twoGradeResult = new JMenuItem("高级英雄榜");////////////////
        resultMenu.add(newGradeResult);

        resultMenu.add(oneGradeResult);///////////

        resultMenu.add(twoGradeResult);//////////////

        bar.add(modeMenu);

        bar.add(gradeMenu);

        bar.add(choiceImage);

        bar.add(resultMenu);

        /////////

        setJMenuBar(bar);

        newGrade.addActionListener(this);

        oneGrade.addActionListener(this);///////////

        twoGrade.addActionListener(this);//////////

        newImage.addActionListener(this);

        defaultImage.addActionListener(this);

        newGradeResult.addActionListener(this);

        oneGradeResult.addActionListener(this);/////

        twoGradeResult.addActionListener(this);/////

        digitPlay.addActionListener(this);

        imagePlay.addActionListener(this);

        startButton = new JButton("刷新数字");

        startButton.addActionListener(this);

        refreshButton = new JButton("刷新图片");

        refreshButton.addActionListener(this);

        puzzlePad = new PuzzlePad();

        add(puzzlePad, BorderLayout.CENTER);

        JPanel pNorth = new JPanel();

        pNorth.add(startButton);

        pNorth.add(refreshButton);

        add(pNorth, BorderLayout.NORTH);

        add(puzzlePad.getHandleMove(), BorderLayout.SOUTH);

        validate();

        setVisible(true);

        setBounds(250, 50, 650, 650);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {

            image = tool.createImage(new File("dog.jpg").toURI().toURL());

            puzzlePad.setImage(image);

        }

        catch (Exception exp) {
        }

        // imageName=new String[imageNumber];

        // for(int i=0;i<imageName.length;i++){

        // imageName[i]=new String("car"+i+".jpg");

        // }

        // m=5;

        // n=6;

        // gradeFile=fileOneGrade;

        // memoryArea.initBlock(m,n,imageName,gradeFile);

        // add(memoryArea,BorderLayout.CENTER);

        // record=new Record();

        // showRead=new ShowRecord();

        if (!fileNewGrade.exists()) { /////

            try {
                fileNewGrade.createNewFile();/////

            } /////

            catch (IOException exp) {
            } /////

        }

        if (!fileOneGrade.exists()) { /////

            try {
                fileOneGrade.createNewFile();/////

            } /////

            catch (IOException exp) {
            } /////

        }

        if (!fileTwoGrade.exists()) { ////

            try {
                fileTwoGrade.createNewFile();////

            }

            catch (IOException exp) {
            } /////

        }

        showDiolag = new ShowRecordDialog();/////

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == startButton) {

            puzzlePad.setIsDigitPlay();

        }

        else if (e.getSource() == refreshButton) {

            puzzlePad.setImage(image);

            puzzlePad.setIsImagePlay();

        }

        else if (e.getSource() == digitPlay) {

            puzzlePad.setIsDigitPlay();

        }

        else if (e.getSource() == imagePlay) {

            puzzlePad.setImage(image);

            puzzlePad.setIsImagePlay();

        }

        else if (e.getSource() == newGrade) {

            puzzlePad.setGrade(0);

        }

        else if (e.getSource() == oneGrade) {

            puzzlePad.setGrade(1);

        }

        else if (e.getSource() == twoGrade) {

            puzzlePad.setGrade(2);

        }

        else if (e.getSource() == newImage) {

            FileNameExtensionFilter filter = new FileNameExtensionFilter(

                    "JPG & GIF Images", "jpg", "gif");

            JFileChooser chooser = new JFileChooser();

            chooser.setFileFilter(filter);

            int state = chooser.showOpenDialog(null);

            File file = chooser.getSelectedFile();

            if (file != null && state == JFileChooser.APPROVE_OPTION) {

                try {

                    image = tool.createImage(file.toURI().toURL());

                    puzzlePad.setImage(image);

                }

                catch (Exception exp) {
                }

            }

        }

        else if (e.getSource() == defaultImage) {

            try {

                image = tool.createImage(new File("dog.jpg").toURI().toURL());

                puzzlePad.setImage(image);

            }

            catch (Exception exp) {
            }

        }

        if (e.getSource() == newGradeResult) {////

            showDiolag.setGradeFile(fileNewGrade);////

            showDiolag.showRecord();////

            showDiolag.setVisible(true);////

        } ////

        if (e.getSource() == oneGradeResult) {////

            showDiolag.setGradeFile(fileOneGrade);////

            showDiolag.showRecord();////

            showDiolag.setVisible(true);////

        } ////

        if (e.getSource() == twoGradeResult) {/////

            showDiolag.setGradeFile(fileTwoGrade);/////

            showDiolag.showRecord();/////

            showDiolag.setVisible(true);////

        } // 用来生成英雄榜txt文档/////

    }

    public static void main(String args[]) {

        new PuzzleGame();

    }

}
