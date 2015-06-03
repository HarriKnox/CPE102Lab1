import static java.lang.Math.sqrt;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import processing.core.*;

public class ProcessingIntro extends PApplet
{
	private List<PImage> imgs;
	private PImage wall;
	private PImage goal;
	private int current_image;
	private long next_time;
	private int[][] world;
	
	private Point wyvernLocation = new Point(10, 1);
	
	private List<Point> path = new LinkedList<Point>();
	private List<Point> visited = new LinkedList<Point>();
	
	private int rows = 15;
	private int cols = 20;
	
	private static final int SPRITE_SIZE = 32;
	
	private final int BGND_COLOR = color(220, 230, 245);
	private static final int ANIMATION_TIME = 100;
	
	private static final int EMPTY = 0;
	private static final int OBSTACLE = 1;
	private static final int GOAL = 2;
	
	private static final int LEFT = 0b00;
	private static final int RIGHT = 0b01;
	private static final int UP = 0b10;
	private static final int DOWN = 0b11;
	
	private static final String[] dirStrings = new String[]{"LEFT", "RIGHT", "UP", "DOWN"};
	
	private int[][] directions;
	private int dir = 0;
	
	public void setup()
	{
		this.size(this.cols * SPRITE_SIZE, this.rows * SPRITE_SIZE);
		this.background(BGND_COLOR);
		
		this.world = new int[cols][rows];
		
		for (int y = 0; y < 15; y++) this.world[0][y] = OBSTACLE;
		for (int x = 4; x < 13; x++) this.world[x][3] = OBSTACLE;
		for (int y = 0; y < 8; y++) this.world[16][y] = OBSTACLE;
		for (int y = 4; y < 8; y++) this.world[4][y] = OBSTACLE;
		for (int x = 1; x < 4; x++) this.world[x][7] = OBSTACLE;
		for (int x = 4; x < 12; x++) this.world[x][11] = OBSTACLE;
		for (int y = 7; y < 11; y++) this.world[8][y] = OBSTACLE;
		for (int y = 7; y < 15; y++) this.world[12][y] = OBSTACLE;
		for (int x = 16; x < 20; x++) this.world[x][11] = OBSTACLE;
		
		this.world[10][13] = GOAL;
		
		this.imgs = new ArrayList<PImage>();
		for (int i = 1; i <= 8; i++)
		{
			PImage mask = loadImage("wyvern" + i + ".bmp");
			mask.loadPixels();
			for (int p = 0; p < mask.pixels.length; p++)
			{
				mask.pixels[p] = color(mask.pixels[p] == color(255, 255, 255) ? 0 : 255);
			}
			mask.updatePixels();
			
			PImage sprite = loadImage("wyvern" + i + ".bmp");
			sprite.mask(mask);
			
			this.imgs.add(sprite);
		}
		/*this.imgs.add(loadImage("wyvern1.png"));
		this.imgs.add(loadImage("wyvern2.png"));
		this.imgs.add(loadImage("wyvern3.png"));*/
		
		this.wall = loadImage("wall.png");
		this.goal = loadImage("star.png");
		
		this.current_image = 0;
		this.next_time = System.currentTimeMillis() + this.ANIMATION_TIME;
		
		this.directions = new int[24][4];
		int index = 0;
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				if (j != i)
					for (int k = 0; k < 4; k++)
						if ((k != i) && (k != j))
							for (int l = 0; l < 4; l++)
								if ((l != i) && (l != j) && (l != k))
									this.directions[index++] = new int[]{i, j, k, l};
	}
	
	private void next_image()
	{
		this.current_image = (this.current_image + 1) % this.imgs.size();
	}
	
	private void drawFrame()
	{
		this.background(BGND_COLOR);
		
		for (int x = 0; x < this.cols; x++)
		{
			for (int y = 0; y < this.rows; y++)
			{
				Point p = new Point(x, y);
				if (this.visited.contains(p))
				{
					this.fill(127);
					this.rectMode(CENTER);
					this.rect(x * SPRITE_SIZE + (SPRITE_SIZE / 2), y * SPRITE_SIZE + (SPRITE_SIZE / 2), SPRITE_SIZE * 3 / 4, SPRITE_SIZE * 3 / 4);
				}
				
				if (this.world[x][y] == OBSTACLE)
				{
					this.image(this.wall, x * SPRITE_SIZE, y * SPRITE_SIZE);
				}
				else if (this.world[x][y] == GOAL)
				{
					this.image(this.goal, x * SPRITE_SIZE, y * SPRITE_SIZE);
				}
				
				if (this.path.contains(p))
				{
					this.fill(200, 0, 0);
					this.ellipseMode(CENTER);
					this.ellipse(x * SPRITE_SIZE + (SPRITE_SIZE / 2), y * SPRITE_SIZE + (SPRITE_SIZE / 2), SPRITE_SIZE / 2, SPRITE_SIZE / 2);
					/*
					this.fill(255);
					this.textSize(15);
					this.textAlign(CENTER, CENTER);
					this.text(Integer.toString(this.path.indexOf(p)), x * SPRITE_SIZE + (SPRITE_SIZE / 2), y * SPRITE_SIZE + (SPRITE_SIZE / 2)); /* */
				}
			}
		}
		
		int wyvX = this.wyvernLocation.getX();
		int wyvY = this.wyvernLocation.getY();
		this.image(this.imgs.get(this.current_image), wyvX * SPRITE_SIZE, wyvY * SPRITE_SIZE);
		
		if (!this.path.isEmpty())
		{
			int[] d = this.directions[this.dir];
			this.fill(255);
			this.textSize(20);
			this.textAlign(RIGHT, TOP);
			this.text(this.dirStrings[d[0]] + ", " + this.dirStrings[d[1]] + ", " + this.dirStrings[d[2]] + ", " + this.dirStrings[d[3]], SPRITE_SIZE * 5, SPRITE_SIZE * 3);
		}
	}
	
	public void draw()
	{
		this.imageMode(CORNER);
		// A simplified action scheduling handler
		long time = System.currentTimeMillis();
		if (time >= this.next_time)
		{
			this.next_image();
			this.next_time = time + this.ANIMATION_TIME;
			
			this.drawFrame();
		}
	}
	
	public void keyPressed()
	{
		int x = this.wyvernLocation.getX();
		int y = this.wyvernLocation.getY();
		
		switch (this.key)
		{
		case 'w':
			if (y > 0 && this.world[x][y - 1] != OBSTACLE) y--;
			break;
		case 's':
			if (y < (this.rows - 1) && this.world[x][y + 1] != OBSTACLE) y++;
			break;
		case 'a':
			if (x > 0 && this.world[x - 1][y] != OBSTACLE) x--;
			break;
		case 'd':
			if (x < (this.cols - 1) && this.world[x + 1][y] != OBSTACLE) x++;
			break;
		case 'c': case 'j': case 'k': case 'l':
			this.path.clear();
			this.visited.clear();
			if (this.key != 'c')
			{
				if (this.key == 'j') dir--;
				if (this.key == 'l') dir++;
				if (dir < 0) dir = 23;
				if (dir > 23) dir = 0;
				this.search(this.wyvernLocation, this.directions[dir]);
			}
			break;
		}
		
		if (x != this.wyvernLocation.getX() || y != this.wyvernLocation.getY())
		{
			this.wyvernLocation = new Point(x, y);
		}
	}
	
	private boolean search(Point p, int[] order)
	{
		int x = p.getX();
		int y = p.getY();
		
		if (this.visited.contains(p) || (x < 0 || x >= this.cols || y < 0 || y >= this.rows) || this.world[x][y] == OBSTACLE)
			return false;
		
		this.visited.add(p);
		
		if (this.world[x][y] == GOAL)
		{
			this.path.add(0, p);
			return true;
		}
		
		boolean found = search(move(p, order[0]), order) || search(move(p, order[1]), order) || search(move(p, order[2]), order) || search(move(p, order[3]), order);
		
		if (found)
		{
			this.path.add(0, p);
		}
		return found;
	}
	
	private static Point move(Point start, int dir)
	{
		int x = start.getX();
		int y = start.getY();
		
		x += (1 - ((dir & 2) >> 1)) * (((dir & 1) * 2) - 1);
		y += ((dir & 2) >> 1) * (((dir & 1) * 2) - 1);
		
		return new Point(x, y);
	}
	
	public static void main(String[] args)
	{
		PApplet.main("ProcessingIntro");
	}
}
