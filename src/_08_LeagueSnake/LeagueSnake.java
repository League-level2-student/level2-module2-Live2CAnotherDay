package _08_LeagueSnake;

import java.util.ArrayList;

import processing.core.PApplet;

public class LeagueSnake extends PApplet {
    static final int WIDTH = 800;
    static final int HEIGHT = 800;
    
    /*
     * Game variables
     * 
     * Put all the game variables here.
     */
    	Segment head;
    	int foodX;
    	int foodY;
    	int snakeDirection = UP;
    	int score = 0;
    	ArrayList<Segment> tail = new ArrayList<Segment>();

    
    /*
     * Setup methods
     * 
     * These methods are called at the start of the game.
     */
    @Override
    public void settings() {
       size(500, 500);
    }

    @Override
    public void setup() {
        head = new Segment(260, 260);
        head.x = 400;
        head.y = 400;
    	frameRate(20);
    	dropFood();
    	frameRate(25);
    }

    void dropFood() {
        // Set the food in a new random location
        foodX = ((int)random(25)*20);
        foodY = ((int)random(25)*20);
    }

    /*
     * Draw Methods
     * 
     * These methods are used to draw the snake and its food
     */

    @Override
    public void draw() {
        background(50, 50, 50);
        drawFood();
        move();
        drawSnake();
        eat();
    }

    void drawFood() {
        // Draw the food
    	fill(200, 0, 0);
    	rect(foodX, foodY, 20, 20);
        
        
    }

    void drawSnake() {
        // Draw the head of the snake followed by its tail
    	fill(0, 200, 0);
    	rect(head.x, head.y, 20, 20);
    	
    	
    }

    void drawTail() {
        // Draw each segment of the tail
        fill(0,200,0);
        rect(head.x+10, head.y+10, 10, 10);
        manageTail();
    }

    /*
     * Tail Management methods
     * 
     * These methods make sure the tail is the correct length.
     */

    void manageTail() {
    	checkTailCollision();
    	drawTail();
    	tail.add(new Segment(head.x, head.y));
    	tail.remove(0);
    	
        // After drawing the tail, add a new segment at the "start" of the tail and
        // remove the one at the "end"
        // This produces the illusion of the snake tail moving.

    }

    void checkTailCollision() {
        // If the snake crosses its own tail, shrink the tail back to one segment
        for(int i = 0; i < tail.size();i ++) {
        	if (head.x == tail.get(i).x || head.y == tail.get(i).y) {
            	tail.clear();
            	score = 0;
            	tail.add(new Segment(head.x, head.y));
            }
        }
    	
    }

    /*
     * Control methods
     * 
     * These methods are used to change what is happening to the snake
     */

    @Override
    public void keyPressed() {
        // Set the direction of the snake according to the arrow keys pressed
    	if (keyCode == UP && snakeDirection != DOWN) {
    		snakeDirection = UP;
    	}else if(keyCode == DOWN && snakeDirection != UP) {
    		snakeDirection = DOWN;
    	}else if(keyCode == LEFT && snakeDirection != RIGHT) {
    		snakeDirection = LEFT;
    	}else if(keyCode == RIGHT && snakeDirection != LEFT) {
    		snakeDirection = RIGHT;
    	}
    	
    	
    }

    void move() {
        // Change the location of the Snake head based on the direction it is moving.

        
        if (snakeDirection == UP) {
            // Move head up
            head.y -= 20;
        } else if (snakeDirection == DOWN) {
            // Move head down
        	head.y += 20;
        } else if (snakeDirection == LEFT) {
        	head.x -= 20;
        } else if (snakeDirection == RIGHT) {
        	head.x += 20;
        }
        checkBoundaries();
        
    }

    void checkBoundaries() {
        // If the snake leaves the frame, make it reappear on the other side
    
    	
        if(head.x < 0) {
        	head.x = 480;
        }
        if(head.x >= 500) {
        	head.x = 0;
        }
        if(head.y >= 500) {
        	head.y = 0;
        }
        if(head.y < 0) {
        	head.y = 480;
        }
    }

    void eat() {
        // When the snake eats the food, its tail should grow and more
        // food appear
    	if (head.x == foodX && head.y == foodY) {
    		score ++;
    		dropFood();
    		tail.add(new Segment(head.x, head.y));
    	}
        
    }

    static public void main(String[] passedArgs) {
        PApplet.main(LeagueSnake.class.getName());
    }
}
