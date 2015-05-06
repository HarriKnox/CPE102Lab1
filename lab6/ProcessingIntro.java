import java.util.List;
import java.util.ArrayList;
import processing.core.*;

public class ProcessingIntro extends PApplet
{
   private List<PImage> imgs;
   private int current_image;
   private long next_time;

   private final int BGND_COLOR = color(220, 230, 245);
   private static final int ANIMATION_TIME = 100;

   public void setup()
   {
      size(640,480);
      background(BGND_COLOR);

      imgs = new ArrayList<PImage>();
      imgs.add(loadImage("wyvern1.png"));
      imgs.add(loadImage("wyvern2.png"));
      imgs.add(loadImage("wyvern3.png"));

      current_image = 0;
      next_time = System.currentTimeMillis() + ANIMATION_TIME;
  }

   private void next_image()
   {
      current_image = (current_image + 1) % imgs.size();
   }

   public void draw()
   {
      // A simplified action scheduling handler
      long time = System.currentTimeMillis();
      if (time >= next_time)
      {
         next_image();
         next_time = time + ANIMATION_TIME;
      }

      background(BGND_COLOR);
      image(imgs.get(current_image), 0, 0);
   }

   public static void main(String[] args)
   {
      PApplet.main("ProcessingIntro");
   }
}
