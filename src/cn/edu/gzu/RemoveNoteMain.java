/**
 * @author wuxiaoquan
 *
 */
package cn.edu.gzu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class RemoveNoteMain {

		public static void main(String[] args) throws FileNotFoundException,
				IOException {
			RemoveNoteMain c = new RemoveNoteMain();
			Reader in = c.getFile();
			try {
				c.remove(in);
			} finally {
				in.close();
			}
		}

		public Reader getFile() throws FileNotFoundException {
			Scanner scanner = new Scanner(System.in);
			System.out.println("请输入文件名字和路径:");
			String fileName = scanner.next();
			Reader in = new BufferedReader(new FileReader(fileName));
			return in;
		}

		public void remove(Reader in) throws IOException {
			//StringBuilder s = new StringBuilder();
			int n;
			while ((n = in.read()) != -1) {
				char c = (char)n;
				int flag = regxChinese(c+"");
				if (flag ==0)
					System.out.print(c);
			}
			//String ss = s.toString();
			//System.out.println(ss);
		}
		 public int regxChinese(String s){
			 String reg_charset ="[\u4E00-\u9FA5]";
			 Pattern p = Pattern.compile(reg_charset);  
	         Matcher m = p.matcher(s);  
	         while (m.find()) {  
	           return 1;
	         }
	         return 0;
		 }
}
