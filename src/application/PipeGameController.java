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
import java.io.Reader;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

public class PipeGameController implements Initializable,Observer{



	public FileModel fm;
	public MazeDisplayer ma;

	public PipeGameController(){}
	public PipeGameController(FileModel fm, MazeDisplayer ma) {
		this.fm = fm;
		this.ma = ma;
	}
	char[][] maze ={ {'s','-','L'},
			{'-','-','|'},
	{'-','-','g'},



	};
	int secondpas;


	@FXML
	MazeDisplayer mazedata;
	@FXML
	Label stepss,timee;
	@FXML
	Button b;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*
		secondpas=0;
		Timer t = new Timer();
		TimerTask task = new TimerTask()
				{
					@Override
					public void run() {
						secondpas++;

						}};
			t.scheduleAtFixedRate(task, 1000, 1000);

*/
		fm = new FileModel();
		mazedata.setMaze(maze);

		mazedata.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				int[] xy = mazedata.place(event.getSceneX(), event.getSceneY());
				mazedata.rotate(xy[1],xy[0],1);
				stepss.setText(" "+mazedata.getSteps());
			}


		});

		b.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				SolveServer();
			}
		});

	}
	public void SolveServer()
	{
		BufferedReader reader = fm.sol(mazedata.getMaze());
		String line;
		 try {
			line =reader.readLine();
			while(line!=null)
			{
			mazedata.drowTile(line);
			line =reader.readLine();
		 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}
	public void openFile()
	{
		ArrayList<String> s =fm.openFile();
		MazeDisplayer m =new MazeDisplayer(s);
		mazedata.setMaze(m.getMaze());

	}


	@Override
	public void update(Observable o, Object arg) {

	}










}
