package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class MazeDisplayer extends Canvas{

	char[][] maze;
	private StringProperty img1;
	int steps = 0;
	int row=0,col=0;
	int time;

	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getSteps() {
		return steps;
	}
	public void setSteps(int steps) {
		this.steps = steps;
	}
	public MazeDisplayer(){
		img1 = new SimpleStringProperty();
	}
	public String getImg1() {
		return img1.get();
	}
	public void setImg1(String img) {
		this.img1.set(img);
	}

	public char[][] getMaze() {
		return maze;
	}
	public char getTile(int i, int j) {
		return this.maze[i][j];
	}

	public void setMaze(char[][] maze) {
		this.maze = maze;
		this.steps=0;
		redrow();
	}
	public void setTile(int i, int j, char ch) {
		this.steps++;
		this.maze[i][j]=ch;
		redrowTile(i,j);
	}
	public MazeDisplayer(ArrayList<String> str) {
		this.row = str.size();
		this.col = findrow(str);
		this.maze = new char[row][col];

		for (int i = 0; i < row; i++) {
			char[] temp = str.get(i).toCharArray();
			for (int j = 0; j < col; j++) {
				this.setTile(i, j, temp[j]);

			}
		}
		this.steps=0;
		this.setMaze(this.maze);
		}
	public void redrow()
	{
		double W =getWidth();
		double H =getHeight();
		double w = W/maze[0].length;
		double h = H/maze.length;

		GraphicsContext gc = getGraphicsContext2D();
		Image img = null;



		for(int i=0;i<maze[0].length;i++)
		{
			for(int j=0;j<maze.length;j++)
			{
				char x = this.getTile(i, j);
				if((x!='d') || (x!='o')||(x!='n') || (x!='e'))
				{
				if(x=='|')
				{
					try {
						img= new Image(new FileInputStream("./resources/I.png"));
						gc.drawImage(img,j*w,i*h, w, h);

					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				else
				{
					try {
						img= new Image(new FileInputStream("./resources/"+x+".png"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				if(img!=null)
					gc.drawImage(img,j*w,i*h, w, h);
				else
					gc.fillRect(j*w,i*h, w, h);
				}
			}
			}

		}

	}
	public int findrow(ArrayList<String> str)
	{
		char[] temp = str.get(0).toCharArray();
		return temp.length;
	}
	public void drowTile(String s)
	{
		if(s.length()>4)
		{
		int i = Character.getNumericValue(s.charAt(0));
		int j = Character.getNumericValue(s.charAt(2));
		int k = Character.getNumericValue(s.charAt(4));
		rotate(i,j,k);
		}

	}
	public void rotate(int i,int j,int k)
	{
		while(k!=0)
		{
		char s = this.getTile(i, j);
		switch(s)
		{
		case '|':
			this.setTile(i, j, '-');
			break;
		case '-':
			this.setTile(i, j, '|');
			break;
		case '7':
			this.setTile(i, j, 'J');
			break;
		case 'J':
			this.setTile(i, j, 'L');
			break;
		case 'L':
			this.setTile(i, j, 'F');
			break;
		case 'F':
			this.setTile(i, j, '7');
			break;

			default:
				break;
		}
		k--;
		}
	}
	public int[] place(double x,double y)
	{
		int[] xy = new int[2];
		double W =getWidth();
		double H =getHeight();
		double w = W/maze[0].length;
		double h = H/maze.length;
		int x1=0,y1=0;
		for(int i=0;i<maze[0].length;i++)
		{
			if(x>w*(i+1))
				x1++;
		}
		for(int j=0;j<maze.length;j++)
		{
			if(y>h*(j+1))
				y1++;
		}

		xy[0] =x1;
		xy[1] =y1;
		return xy;


	}
	public void redrowTile(int i, int j)
	{
		double W =getWidth();
		double H =getHeight();
		double w = W/maze[0].length;
		double h = H/maze.length;

		GraphicsContext gc = getGraphicsContext2D();
		Image img = null;



		if(this.getTile(i, j) == 's')
				{
					try {
						img= new Image(new FileInputStream("./resources/S.png"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					gc.drawImage(img,j*w,i*h, w, h);
				}
				else if (this.getTile(i, j) == 'g')
				{
					try {
						img= new Image(new FileInputStream("./resources/S.png"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					gc.drawImage(img,j*w,i*h, w, h);
				}
				else if(this.getTile(i, j) == '7')
				{
					try {
						img= new Image(new FileInputStream("./resources/7.png"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					gc.drawImage(img,j*w,i*h, w, h);
				}
				else if(this.getTile(i, j) == 'F')
				{
					try {
						img= new Image(new FileInputStream("./resources/F.png"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					gc.drawImage(img,j*w,i*h, w, h);
				}
				else if(this.getTile(i, j) == 'L')
				{
					try {
						img= new Image(new FileInputStream("./resources/L.png"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					gc.drawImage(img,j*w,i*h, w, h);
				}
				else if(this.getTile(i, j) == 'J')
				{
					try {
						img= new Image(new FileInputStream("./resources/J.png"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					gc.drawImage(img,j*w,i*h, w, h);
				}
				else if(this.getTile(i, j) == '-')
				{
					try {
						img= new Image(new FileInputStream("./resources/-.png"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					gc.drawImage(img,j*w,i*h, w, h);
				}
				else if(this.getTile(i, j) == '|')
				{
					try {
						img= new Image(new FileInputStream("./resources/I.png"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					gc.drawImage(img,j*w,i*h, w, h);
				}
				else
					gc.fillRect(j*w,i*h, w, h);
			}





}
