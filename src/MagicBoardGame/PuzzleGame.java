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

        fileNewGrade = new File("����ħ����Ϸ���а�.txt");

        fileOneGrade = new File("����ħ����Ϸ���а�.txt");

        fileTwoGrade = new File("�߼�ħ����Ϸ���а�.txt");

        tool = getToolkit();

        bar = new JMenuBar();

        modeMenu = new JMenu("ѡ���淨");

        digitPlay = new JMenuItem("�����淨");

        imagePlay = new JMenuItem("ͼ���淨");

        modeMenu.add(digitPlay);

        modeMenu.add(imagePlay);

        gradeMenu = new JMenu("ѡ�񼶱�");

        newGrade = new JMenuItem("����");

        oneGrade = new JMenuItem("����");

        twoGrade = new JMenuItem("�߼�");
        gradeMenu.add(newGrade);

        gradeMenu.add(oneGrade);

        gradeMenu.add(twoGrade);

        choiceImage = new JMenu("ѡ��ͼ��");

        newImage = new JMenuItem("ѡ��һ����ͼ��");

        defaultImage = new JMenuItem("ʹ��Ĭ��ͼ��");

        choiceImage.add(newImage);

        choiceImage.add(defaultImage);

        resultMenu = new JMenu("�鿴Ӣ�۰�");/////////////

        newGradeResult = new JMenuItem("����Ӣ�۰�");

        oneGradeResult = new JMenuItem("����Ӣ�۰�"); /////////////

        twoGradeResult = new JMenuItem("�߼�Ӣ�۰�");////////////////
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

        startButton = new JButton("ˢ������");

        startButton.addActionListener(this);

        refreshButton = new JButton("ˢ��ͼƬ");

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

        } // ��������Ӣ�۰�txt�ĵ�/////

    }

    public static void main(String args[]) {

        new PuzzleGame();

    }

}
