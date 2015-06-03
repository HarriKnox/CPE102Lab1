import static java.lang.Math.sqrt;
import java.util.List;
import java.util.ArrayList;
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
   
   private int winAnimationTimer = 0;
   private boolean won = false;
   private static final double A = -0.46627416997969523;
   private static final double B = 7.725483399593904;
   
   private int rows = 15;
   private int cols = 20;
   
   private static final int SPRITE_SIZE = 32;
   
   private final int BGND_COLOR = color(220, 230, 245);
   private static final int ANIMATION_TIME = 100;
   
   private static final int EMPTY = 0;
   private static final int OBSTACLE = 1;
   private static final int GOAL = 2;
   
   public void setup()
   {
      this.size(640,480);
      this.background(BGND_COLOR);
      
      this.world = new int[cols][rows];
      
      for (int y = 0; y < 15; y++)
         this.world[0][y] = OBSTACLE;
      for (int x = 4; x < 13; x++)
         this.world[x][3] = OBSTACLE;
      for (int y = 0; y < 8; y++)
         this.world[16][y] = OBSTACLE;
      for (int y = 4; y < 8; y++)
         this.world[4][y] = OBSTACLE;
      for (int x = 1; x < 4; x++)
         this.world[x][7] = OBSTACLE;
      for (int x = 4; x < 12; x++)
         this.world[x][11] = OBSTACLE;
      for (int y = 7; y < 11; y++)
         this.world[8][y] = OBSTACLE;
      for (int y = 7; y < 15; y++)
         this.world[12][y] = OBSTACLE;
      for (int x = 16; x < 20; x++)
         this.world[x][11] = OBSTACLE;
      
      this.world[10][13] = GOAL;
      
      this.imgs = new ArrayList<PImage>();
      this.imgs.add(loadImage("wyvern1.png"));
      this.imgs.add(loadImage("wyvern2.png"));
      this.imgs.add(loadImage("wyvern3.png"));
      
      this.wall = loadImage("wall.png");
      this.goal = loadImage("star.png");
      
      this.current_image = 0;
      this.next_time = System.currentTimeMillis() + this.ANIMATION_TIME;
   }
   
   private void next_image()
   {
      this.current_image = (this.current_image + 1) % this.imgs.size();
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
      
         this.background(BGND_COLOR);
         
         for (int x = 0; x < this.cols; x++)
         {
            for (int y = 0; y < this.rows; y++)
            {
               if (this.world[x][y] == OBSTACLE)
               {
                  this.image(this.wall, x * SPRITE_SIZE, y * SPRITE_SIZE);
               }
               else if (this.world[x][y] == GOAL)
               {
                  this.image(this.goal, x * SPRITE_SIZE, y * SPRITE_SIZE);
               }
            }
         }
         
         int wyvX = this.wyvernLocation.getX();
         int wyvY = this.wyvernLocation.getY();
         if (!this.won)
         {
            this.image(this.imgs.get(this.current_image), wyvX * SPRITE_SIZE, wyvY * SPRITE_SIZE);
         }
         else
         {
            int imageSize = (int)((A * this.winAnimationTimer * this.winAnimationTimer) + (B * this.winAnimationTimer) + 32);
            if (imageSize > 0)
            {
               
               imageMode(CENTER);
               this.image(this.imgs.get(this.current_image), wyvX * SPRITE_SIZE + (SPRITE_SIZE / 2), wyvY * SPRITE_SIZE + (SPRITE_SIZE / 2), imageSize, imageSize);
               this.winAnimationTimer++;
            }
            else
            {
               this.textSize(50);
               this.textAlign(CENTER);
               this.fill(0, 0, 0);
               this.text("A WINNER IS YOU!", this.width / 2, this.height / 2);
            }
         }
      }
   }
   
   public void keyPressed()
   {
      if (!this.won)
      {
         int x = this.wyvernLocation.getX();
         int y = this.wyvernLocation.getY();
         
         switch (this.key)
         {
            case 'w':
               if (y > 0 && this.world[x][y - 1] != OBSTACLE)
               {
                  y--;
               }
               break;
            case 's':
               if (y < (this.rows - 1) && this.world[x][y + 1] != OBSTACLE)
               {
                  y++;
               }
               break;
            case 'a':
               if (x > 0 && this.world[x - 1][y] != OBSTACLE)
               {
                  x--;
               }
               break;
            case 'd':
               if (x < (this.cols - 1) && this.world[x + 1][y] != OBSTACLE)
               {
                  x++;
               }
               break;
            case 'h':
               this.won = true;
               break;
         }
         if (x != this.wyvernLocation.getX() || y != this.wyvernLocation.getY())
         {
            this.wyvernLocation = new Point(x, y);
         }
         if (this.world[x][y] == GOAL)
         {
            this.won = true;
         }
      }
   }
   
   public static void main(String[] args)
   {
      PApplet.main("ProcessingIntro");
   }
}
