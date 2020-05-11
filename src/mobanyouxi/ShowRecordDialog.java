package mobanyouxi;

import java.io.*;
import java.util.*;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class ShowRecordDialog extends JDialog implements ActionListener {
	File gradeFile;
	JButton clear;
	JTextArea showArea = null;
	TreeSet<People> treeSet;
	private long fileLength;

	public ShowRecordDialog() {
		treeSet = new TreeSet<People>();
		showArea = new JTextArea("input some", 6, 4);
		showArea.setFont(new Font("楷体", Font.BOLD, 20));
		showArea.setEditable(false);
		clear = new JButton("清空排行榜");
		clear.addActionListener(this);
		add(showArea, BorderLayout.NORTH);
		add(clear, BorderLayout.SOUTH);
		setBounds(100, 50, 550, 380);
		setModal(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
	}

	public void setGradeFile(File f) {
		gradeFile = f;
		setTitle(f.getName());

	}

	public void showRecord() {
		showArea.setText(null);
		treeSet.clear();
		try {
			RandomAccessFile in = new RandomAccessFile(gradeFile, "rw");
			long readPosition = 0;
			fileLength = in.length();
			while (readPosition < fileLength) {
				String name = in.readUTF();
				int time = in.readInt();
				readPosition = in.getFilePointer();
				// People people = new People(name, time);
				showArea.append("姓名:" + name + ",成绩:" + time + "秒");
				showArea.append("\n");
				// treeSet.add(people);
			}
			in.close();
		} catch (IOException exp) {
			System.out.print(exp);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == clear) {
			try {
				File f = gradeFile.getAbsoluteFile();
				gradeFile.delete();
				f.createNewFile();
				showArea.setText("排行榜被清空");
			} catch (Exception ee) {
			}
		}
	}

}
