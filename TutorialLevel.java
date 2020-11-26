import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;

/**
 * Write a description of class TutorialLevel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TutorialLevel extends World
{
    TutorialPlatformMap map = new TutorialPlatformMap(); // the image the mpa will be drawn from
    GreenfootImage mapImg = map.getImage(); // get the image
    final int mapImgWidth = mapImg.getWidth(); // width of the map
    final int mapImgHeight = mapImg.getHeight(); // height of the map
    Platform platformTemplate = new Platform(0,0); //a template 
    GreenfootImage platformImg = platformTemplate.getImage(); //how tall nad wide the platform is
    final int platformHeight = platformImg.getHeight(); // the height of the platform
    final int platformWidth = platformImg.getWidth(); // the width of the platform
    final int mapWidth = mapImgWidth * platformWidth;
    final int mapHeight = mapImgHeight * platformHeight;

    private List<Platform> thePlatforms = new ArrayList<Platform>();

    int leftBound = 0; //left-side of screen
    int bottomBound = mapHeight; //bottom of screen
    int topBound = mapHeight - getHeight(); //top of screen
    int rightBound = getWidth(); //right of screen

    /**
     * Constructor for objects of class TutorialLevel.
     * 
     */
    public TutorialLevel()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1920, 1080, 1); 
        makeMap();
        update();
    }

    public void makeMap()
    {
        for(int y=0;y<mapImgHeight;y++)
        {
            for(int x=0;x<mapImgWidth;x++)
            {
                if(Color.BLACK.equals(mapImg.getColorAt(x,y)))
                {
                    int mapX = x * platformWidth + platformWidth / 2;
                    int mapY = y * platformHeight + platformHeight / 2;
                    thePlatforms.add(new Platform(mapX,mapY));
                }
            }
        }

    }

    public void update()
    {
        Platform thisPlatform;
        int thisPlatformX;
        int thisPlatformY;
        int screenX;
        int screenY;
        for(int i=0; i<thePlatforms.size(); i++)
        {
            thisPlatform = thePlatforms.get(i);
            thisPlatformX = thisPlatform.mapX;
            thisPlatformY = thisPlatform.mapY;

            if(thisPlatformX >= leftBound && thisPlatformX <= rightBound && thisPlatformY >= topBound && thisPlatformY <= bottomBound)
            {
                screenX = thisPlatformX - leftBound;
                screenY = thisPlatformY - topBound;
                if(thisPlatform.getWorld() == null)
                {
                    addObject(thisPlatform, screenX, screenY);
                } 
            }   
        }
    }
}