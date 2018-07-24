package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import java.util.Observable;
import javafx.stage.FileChooser;

public class FileModel extends Observable{


	public ArrayList<String> openFile()
	{
		String line;
		ArrayList<String> str = new ArrayList<String>();
		FileChooser fc = new FileChooser();
		fc.setTitle("Open Maze file");
		int k=0;
		fc.setInitialDirectory(new File("./resources/"));
		File  choosen = fc.showOpenDialog(null);
		FileReader fr=null;
		try {
			fr = new FileReader(choosen);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader b = new BufferedReader(fr);
		try {
			while (!((line=b.readLine()).equals("done")))
			{
				str.add(line);
				System.out.println(str.get(k));
				k++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("yes");
		return str;
	}

	public BufferedReader sol(char[][] maze)
	{
		InputStream inputStream = null;
		OutputStream outputStream=null;
		BufferedReader reader;
		PrintWriter write;
		String line;
		try {
			Socket s = new Socket("127.0.0.1",5700);
			inputStream = s.getInputStream();
			outputStream =s.getOutputStream();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Connected");
		 write = new PrintWriter(new OutputStreamWriter(outputStream));
		 for (char[] cs : maze) {
			System.out.println(cs);
		}
		 for (char[] cs : maze) {
			write.println(cs);
			write.flush();
		}
		 write.println("done");
			write.flush();



		 reader = new BufferedReader(new InputStreamReader(inputStream));
		 return reader;
	}





}
