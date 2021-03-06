package com.jp.app.readFolder;


import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * @author Dimit(Xansa)
 * 
 *         Created on Dec 30, 2004
 * 
 * 
 */

public class ReadFolder extends JFrame {

	private static final long serialVersionUID = 1L;
	public String fileType[] = new String[10];
	public JTextField txtDirSel;
	public File resultDir;
	public File selectedDir;

	public static JFileChooser dirChooser;
	public static Container c;
	public static int w = 500;
	public static int h = 300;
	public static String strFrame;

	public static JButton butInitial;
	public static JButton butGenReport;

	public static JLabel lblName;
	public static JLabel lblFooter;

	/**
	 * 
	 */
	public ReadFolder() {
		setTitle("LOC Generating Utility");
		setSize(400, 300);
		setVisible(true);
		c = getContentPane();
		GridBagLayout gbl = new GridBagLayout();
		c.setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.fill = GridBagConstraints.CENTER;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.weightx = 0;
		gbc.weighty = 0;

		lblName = new JLabel("Utility");
		add(lblName, gbc, 0, 1, 1, 1);

		txtDirSel = new JTextField(20);
		add(txtDirSel, gbc, 0, 4, 1, 1);

		butInitial = new JButton("Folder-Location");
		add(butInitial, gbc, 1, 4, 1, 1);

		butGenReport = new JButton("Generate-Report");
		add(butGenReport, gbc, 1, 8, 1, 1);
		butGenReport.setEnabled(true);

		lblFooter = new JLabel("Utility Developed by Dimit Chadha");
		add(lblFooter, gbc, 0, 10, 1, 1);

		butInitial.addActionListener(new addEvent());
		butGenReport.addActionListener(new addEvent());

	}

	public class addEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object evt = e.getActionCommand();
			try {
				if (evt == "Folder-Location") {
					dirChooser = new JFileChooser();
					dirChooser.setCurrentDirectory(null);
					dirChooser
							.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					dirChooser.showOpenDialog(c);
					selectedDir = dirChooser.getSelectedFile();
					if (selectedDir == null) {
						int sel = JOptionPane.showConfirmDialog(ReadFolder.c,
								"Pls Select the Folder !", "Select Folder",
								JOptionPane.CLOSED_OPTION,
								JOptionPane.PLAIN_MESSAGE);
						System.out.println(sel);
					} else {
						txtDirSel.setText(selectedDir.toString());
						txtDirSel.setEditable(false);
					}
				} else if (evt == "Generate-Report" && (selectedDir != null)) {
					dirChooser = new JFileChooser();
					dirChooser.setCurrentDirectory(null);
					dirChooser
							.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					dirChooser.showOpenDialog(c);
					resultDir = dirChooser.getSelectedFile();
					if (resultDir == null) {
						int sel = JOptionPane.showConfirmDialog(ReadFolder.c,
								"Pls Select the Folder !", "Select Folder",
								JOptionPane.CLOSED_OPTION,
								JOptionPane.PLAIN_MESSAGE);
						System.out.println(sel);
					} else {
						GenerateReport();
					}
				}
			} catch (FileNotFoundException ioe) {
			} catch (IOException e1) {
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReadFolder readFolder = new ReadFolder();
		readFolder.accessProperties();
		readFolder.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		readFolder.setVisible(true);
	}

	/**
	 * @param butFinal
	 * @param gbc
	 * @param i
	 * @param j
	 * @param k
	 * @param l
	 */

	/**
	 * 
	 */
	private void accessProperties() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("readFolder");
		String fileTypes = resourceBundle.getString("FILETYPE");
		StringTokenizer stringTokenizer = new StringTokenizer(fileTypes, ",");
		for (int i = 0; stringTokenizer.hasMoreTokens(); i++) {
			fileType[i] = (String) stringTokenizer.nextElement();
		}
	}

	public void add(Component comp, GridBagConstraints gbc, int x, int y,
			int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		getContentPane().add(comp, gbc);
	}

	/**
	 * 
	 */
	public void GenerateReport() throws IOException {
		try {
			Map<String, String> fileList = getFileListing(selectedDir);
			String reportFilePath = WriteToFile(fileList);
			int sel = JOptionPane.showConfirmDialog(ReadFolder.c,
					"Report Generated!! File Stored in " + reportFilePath,
					"Report Generated", JOptionPane.CLOSED_OPTION,
					JOptionPane.PLAIN_MESSAGE);
			System.out.println(sel);
		} catch (FileNotFoundException ioe) {
			ioe.getMessage();
		}
	}

	/**
	 * @param tempDir
	 */
	private String WriteToFile(Map<String, String> fileList) throws IOException {
		PrintWriter printRecs = null;
		String reportFilePath = "";
		try {
			Date today = new Date();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
			reportFilePath = resultDir.toString() + "\\" + "Result("
					+ dateFormat.format(today).toString() + ")" + ".xls";
			printRecs = new PrintWriter(
					new FileWriter(new File(reportFilePath)));
		} catch (IOException ioe) {
		}
		Iterator<?> files = fileList.keySet().iterator();
		while (files.hasNext()) {
			String fileName = files.next().toString();
			String path = fileList.get(fileName).toString();
			printRecs.println(fileName + "\t" + path);
			System.out.print("lib/" + fileName + ";");
			printRecs.flush();
		}
		printRecs.close();
		return reportFilePath;
	}

	/**
	 * @param tempDir
	 * @return result Modify by SumitK on dated 02.12.2005 for giving option to
	 *         retrieve FileTypes from loc.peoperties
	 */
	public Map<String, String> getFileListing(File selectedDir)
			throws FileNotFoundException {

		try {
			validateDirectory(selectedDir);
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
			System.exit(0);
		}

		File[] filesAndDirs = selectedDir.listFiles();
		List<File> filesDirs = Arrays.asList(filesAndDirs);
		Iterator<File> filesDirIter = filesDirs.iterator();
		boolean flag;
		File file = null;
		Map<String, String> result = new HashMap<String, String>();
		while (filesDirIter.hasNext()) {
			flag = false;
			file = (File) filesDirIter.next();
			if (file.isFile()) { // file
				int fileTypesLen = fileType.length;
				for (int i = 0; (fileTypesLen > 0) && (null != fileType[i]); i++) {
					if (!(file.getName().toString().endsWith(fileType[i]
							.toString()))) {
						continue;
					} else {
						flag = true;
					}
				}
				if (flag) {
					String Cpath = file.getAbsolutePath();
					String fileName = file.getName();
					String path = Cpath.substring(0, Cpath.indexOf(fileName));
					result.put(fileName, path);
					flag = false;
				}
			} else { // dir
				// must be a directory recursive call! add no size//
				Map<String, String> deeperList = getFileListing(file);
				if (deeperList.size() > 0) {
					// result.addAll(deeperList);
					result.putAll(deeperList);
				} else {
					for (int i = 0; (fileType.length > 0)
							&& (null != fileType[i]); i++) {
						if ((null != fileType[i])
								&& !(file.getName().toString()
										.endsWith(fileType[i].toString()))) {
							continue;
						} else {
							flag = true;
						}
					}
					if (flag) {
						String Cpath = file.getAbsolutePath();
						String fileName = file.getName();
						String path = Cpath.substring(0,
								Cpath.indexOf(fileName));
						result.put(fileName, path);
						flag = false;
					}
				}
			}
		}
		return result;
	}

	/**
	 * @param tempDir1
	 */
	public static void validateDirectory(File selectedDir)
			throws FileNotFoundException {

		if (selectedDir == null) {
			throw new IllegalArgumentException("Directory should not be null.");
		}
		if (!selectedDir.exists()) {
			throw new FileNotFoundException("Directory does not exist: "
					+ selectedDir);
		}
		if (!selectedDir.isDirectory()) {
			throw new IllegalArgumentException("Is not a directory: "
					+ selectedDir);
		}
		if (!selectedDir.canRead()) {
			throw new IllegalArgumentException("Directory cannot be read: "
					+ selectedDir);
		}
	}

}