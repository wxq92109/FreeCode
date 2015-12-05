/**
 * @author wuxiaoquan
 */
package cn.edu.gzu;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
 


import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
 
public class RemoveMainFrame extends JFrame {
    private JMenuItem menuOpen;
    private JMenuItem menuSave;
    private JMenuItem menuSaveAs;
    private JMenuItem menuClose;
 
    private JMenu editMenu;
    private JMenuItem menuCut;
    private JMenuItem menuCopy;
    private JMenuItem menuPaste;
 
    private JMenu noteMenu;
    private JMenuItem menuRMcn;
    private JMenuItem menuRMen;
    
    private JMenuItem menuAbout;
   
    private JTextArea textArea;
    private JLabel stateBar;
    private JFileChooser fileChooser;
   
    private JPopupMenu popUpMenu;
 
    public RemoveMainFrame() {
        super("�½��ı��ļ�");
        setUpUIComponent();
        super.setSize(800, 700);
        setUpEventListener();
        setFrameCenterToScreenCenter();
        setVisible(true);
    }
    private void setFrameCenterToScreenCenter(){ 
    	Point pointSreenCenter = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint(); 
    	setLocation(pointSreenCenter.x-getSize().width/2, pointSreenCenter.y-getSize().height/2); 
    	}
    private void setUpUIComponent() {
        setSize(360, 640);
       
        // �˵���
        JMenuBar menuBar = new JMenuBar();
       
        // ���á��ļ����˵�
        JMenu fileMenu = new JMenu("�ļ�");
        menuOpen = new JMenuItem("��");
        // ��ݼ�����
        menuOpen.setAccelerator(
                    KeyStroke.getKeyStroke(
                            KeyEvent.VK_O,
                            InputEvent.CTRL_MASK));
        menuSave = new JMenuItem("����");
        menuSave.setAccelerator(
                    KeyStroke.getKeyStroke(
                            KeyEvent.VK_S,
                            InputEvent.CTRL_MASK));
        menuSaveAs = new JMenuItem("���Ϊ");
 
        menuClose = new JMenuItem("�ر�");
        menuClose.setAccelerator(
                    KeyStroke.getKeyStroke(
                            KeyEvent.VK_Q,
                            InputEvent.CTRL_MASK));
       
        fileMenu.add(menuOpen);
        fileMenu.addSeparator(); // �ָ���
        fileMenu.add(menuSave);
        fileMenu.add(menuSaveAs);       
        fileMenu.addSeparator(); // �ָ���
        fileMenu.add(menuClose);
       
        // ���á��༭���˵�       
        JMenu editMenu = new JMenu("�༭");
        menuCut = new JMenuItem("����");
        menuCut.setAccelerator(
                    KeyStroke.getKeyStroke(KeyEvent.VK_X,
                            InputEvent.CTRL_MASK));
        menuCopy = new JMenuItem("����");
        menuCopy.setAccelerator(
                    KeyStroke.getKeyStroke(KeyEvent.VK_C,
                            InputEvent.CTRL_MASK));
        menuPaste = new JMenuItem("ճ��");
        menuPaste.setAccelerator(
                    KeyStroke.getKeyStroke(KeyEvent.VK_V,
                            InputEvent.CTRL_MASK));
        editMenu.add(menuCut);
        editMenu.add(menuCopy);
        editMenu.add(menuPaste);
       
        //���á�ע�͡���ť
        JMenu noteMenu = new JMenu("ע��");
        menuRMcn = new JMenuItem("ȥ������ע��");
        menuRMen = new JMenuItem("ȥ��Ӣ��ע��");
        noteMenu.add(menuRMcn);
        noteMenu.add(menuRMen);
        
        // ���á����ڡ��˵�       
        JMenu aboutMenu = new JMenu("����");
        menuAbout = new JMenuItem("����");
        aboutMenu.add(menuAbout);
       
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(noteMenu);
        menuBar.add(aboutMenu);
 
       
        setJMenuBar(menuBar);
       
        // ���ֱ༭����
        textArea = new JTextArea();
        textArea.setFont(new Font("����", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        JScrollPane panel = new JScrollPane(textArea,
          ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
          ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
       
        Container contentPane = getContentPane();
        contentPane.add(panel, BorderLayout.CENTER); 
       
        // ״̬��
        stateBar = new JLabel("δ�޸�");
        stateBar.setHorizontalAlignment(SwingConstants.LEFT);
        stateBar.setBorder(
                BorderFactory.createEtchedBorder());
        contentPane.add(stateBar, BorderLayout.SOUTH);      
       
        popUpMenu = editMenu.getPopupMenu();
        fileChooser = new JFileChooser();
    }
   
   
    private void setUpEventListener() {
        // ���´��ڹر�ť�¼�����
        addWindowListener(
            new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    closeFile();
                }
            }
        );
       
        // �˵� - ��
        menuOpen.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    openFile();
                }
            }
        );
 
        // �˵� - ����
        menuSave.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    saveFile();
                }
            }
        );
 
        // �˵� - ���Ϊ
        menuSaveAs.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    saveFileAs();
                }
            }
        );
 
 
        // �˵� - �ر��ļ�
        menuClose.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    closeFile();
                }
            }
        );
 
        // �˵� - ����
        menuCut.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cut();
                }
            }
        );
 
        // �˵� - ����
        menuCopy.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    copy();
                }
            }
        );
 
        // �˵� - ճ��
        menuPaste.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    paste();
                }
            }
        );
       
        // �˵� - ����
        menuAbout.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // ��ʾ�Ի���
                    JOptionPane.showOptionDialog(null,
                        "����:\n \t����ע��ȥ������ \n" +
                        "���:\n \tһ���򵥵����ĳ���ȥ�����༭\n�����ڳ���������ȥ�����󣬽��й���\n" +
                        " \n",
                        "����RNote",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null, null, null);
                }
            }
        );      
       menuRMcn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				textArea.setText("");
				Reader in = getFile(getTitle());
				removeNote(in);
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	});
       menuRMen.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("Can not be implement");
		}
	});
        // �༭�������¼�
        textArea.addKeyListener(
            new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    processTextArea();
                }
            }
        );
 
        // �༭������¼�
        textArea.addMouseListener(
            new MouseAdapter() {
                public void mouseReleased(MouseEvent e) {
                    if(e.getButton() == MouseEvent.BUTTON3)
                        popUpMenu.show(editMenu, e.getX(), e.getY());
                }
               
                public void mouseClicked(MouseEvent e) {
                    if(e.getButton() == MouseEvent.BUTTON1)
                        popUpMenu.setVisible(false);
                }
            }
        );       
    }
 
    private void openFile() {
    	 open();
    }
    private boolean isCurrentFileSaved() {
        if(stateBar.getText().equals("δ�޸�")) {
            return true;
        }
        else {
            return false;
        }
    }
    private void open() {
        // fileChooser �� JFileChooser ��ʵ��
        // ��ʾ�ļ�ѡȡ�ĶԻ���
        int option = fileChooser.showDialog(null, null);
           
        // ʹ���߰���ȷ�ϼ�
        if(option == JFileChooser.APPROVE_OPTION) {
            try {
                // ����ѡȡ���ļ�
                BufferedReader buf =
                    new BufferedReader(
                       new FileReader(
                         fileChooser.getSelectedFile()));
 
                // �趨�ļ�����
                setTitle(fileChooser.getSelectedFile().toString());
                // ���ǰһ���ļ�
                textArea.setText("");
                // �趨״̬��
                stateBar.setText("δ�޸�");
                // ȡ��ϵͳ�����Ļ����ַ�
                String lineSeparator = System.getProperty("line.separator");
                // ��ȡ�ļ������������ֱ༭��
                String text;
                while((text = buf.readLine()) != null) {
                    textArea.append(text);
                    textArea.append(lineSeparator);
                }
 
                buf.close();
            }  
            catch(IOException e) {
                JOptionPane.showMessageDialog(null, e.toString(),
                    "�����ļ�ʧ��", JOptionPane.ERROR_MESSAGE);
            }
        }       
    }
    private void saveFile() {
        // �ӱ�����ȡ���ļ�����
        File file = new File(getTitle());
        // ��ָ�����ļ�������
        if(!file.exists()) {
            // ִ�����Ϊ
            saveFileAs();
        }
        else {
            try {
                // ����ָ�����ļ�
                BufferedWriter buf =
                    new BufferedWriter(
                            new FileWriter(file));
                // �����ֱ༭��������д���ļ�
                buf.write(textArea.getText());
                buf.close();
                // �趨״̬��Ϊδ�޸�
                stateBar.setText("δ�޸�");
            }
            catch(IOException e) {
                JOptionPane.showMessageDialog(null, e.toString(),
                  "д���ļ�ʧ��", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
 
    private void saveFileAs() {
        // ��ʾ�ļ��Ի���
        int option = fileChooser.showSaveDialog(null);
 
        // ���ȷ��ѡȡ�ļ�
        if(option == JFileChooser.APPROVE_OPTION) {
            // ȡ��ѡ����ļ�
            File file = fileChooser.getSelectedFile();
           
            // �ڱ��������趨�ļ�����
            setTitle(file.toString());
               
            try {
                // �����ļ�
                file.createNewFile();
                // �����ļ�����
                    saveFile();
            }
            catch(IOException e) {
            JOptionPane.showMessageDialog(null, e.toString(),
                   "�޷��������ļ�", JOptionPane.ERROR_MESSAGE);
            }
        }  
    }
 
    private void closeFile() {
        // �Ƿ��ѱ����ļ�
        if(isCurrentFileSaved()) {
            // �ͷŴ�����Դ������رճ���
        	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        else {
            int option = JOptionPane.showConfirmDialog(
                    null, "�ļ����޸ģ��Ƿ񱣴棿",
                    "�����ļ���", JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE, null);
 
            switch(option) {
                case JOptionPane.YES_OPTION:
                    saveFile();
                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    break;
                case JOptionPane.NO_OPTION:
                	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        }
    }
 
    private void cut() {
        textArea.cut();
        stateBar.setText("���޸�");
        popUpMenu.setVisible(false);
    }
 
    private void copy() {
        textArea.copy();
        popUpMenu.setVisible(false);   
    }
 
    private void paste() {
        textArea.paste();
        stateBar.setText("���޸�");
        popUpMenu.setVisible(false);
    }
 
    private void processTextArea() {
            stateBar.setText("���޸�");
    }
    public Reader getFile(String filename) throws FileNotFoundException {
		
		Reader in = new BufferedReader(new FileReader(filename));
		return in;
	}

	public void removeNote(final Reader in) throws IOException {
		int n;
		String text = "";
		 while ((n = in.read()) != -1) {
				char c = (char)n;
				int flag = regxChinese(c+"");
				if (flag ==0)
					text += c;
			}
		 textArea.setText(text);
	}
//	public void matchTest(Reader in) throws IOException
//	{
//		int n;
//		String text = "";
//		 while ((n = in.read()) != -1) {
//				char c = (char)n;
//				int flag = regxChinese(c+"");
//				if (flag ==0)
//					text += c;
//			}
//		 textArea.setText(text);
//	}
	 public int regxChinese(String s){
		 String reg_charset ="[\u4E00-\u9FA5]";
		 String str_char="[\u3002\uff1b\uff0c\uff1a\u201c\u201d\uff08\uff09\u3001\uff1f\u300a\u300b]";
		 Pattern p = Pattern.compile(reg_charset);  
         Matcher m = p.matcher(s);  
         while (m.find()) {  
           return 1;
         }
         Pattern p2 = Pattern.compile(str_char);  
         Matcher m2 = p2.matcher(s);  
         while (m2.find()) {  
        	 return 1;
         }
         
         return 0;
	 }
 
   
    public static void main(String[] args) {
        new RemoveMainFrame();
    }   
   }