import acm.graphics.*;
import acm.program.GraphicsProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

/*
* @Author:  Brian Limaye
* Date:  06/12/2018
* Version - 0.1
*/
public class Breakout extends GraphicsProgram {

    //width of game display
    private static final int WIDTH = 400;

    //height of game display
    private static final int HEIGHT = 600;

    //width of paddle
    private static final int PADDLE_WIDTH = 60;

    //height of paddle
    private static final int PADDLE_HEIGHT = 10;

    //offset of paddle up from the bottom
    private static final int PADDLE_Y_OFFSET = 30;

    //number of bricks per row
    private static final int NBRICKS_PER_ROW = 10;

    //number of rows of bricks
    private static final int NBRICK_ROWS = 10;

    //separation between bricks
    private static final int BRICK_SEP = 4;

    //width of each brick (based on the dimensions of the game display)
    private static final int BRICK_WIDTH = WIDTH / NBRICKS_PER_ROW - BRICK_SEP;

    //height of brick
    private static final int BRICK_HEIGHT = 8;

    //radius of ball in pixels
    private static final int BALL_RADIUS = 2;

    //offset of the top brick row from top
    private static final int BRICK_Y_OFFSET = 70;

    //number of turns
    private static final int NTURNS = 3;

    //number of total bricks
    private static final int TOTALBRICKS = NBRICK_ROWS * NBRICKS_PER_ROW;

    //the paddle
    private GRect paddle;

    //the ball
    private GOval ball;

    // the scoreboard
    private GLabel scoreLabel;

    //the highscore
    private GLabel scoreLabel1;

    //the Game Over
    private GLabel scoreLabel2;

    //ball velocity in both directions (x-direction, and y-direction)
    private double vx, vy;

    //records the last x position of the mouse (see mouseMoved method)
    private double lastX;

    //used for mouse events (only moves the paddle every 5th mouse move)
    private int toggle = 0;

    private int highscore = 0;
    private int score = 0;

    //main method -- called when the program is run
    public static void main(String[] args) {
        String[] sizeArgs = {"width=" + WIDTH, "height=" + HEIGHT};
        new Breakout().start(sizeArgs);
    }

    //run method -- called indirectly from the main method
    public void run() {
        for (int b = 0; b < NTURNS; b++) {
            removeAll();
            setup();
            play();
            if (TOTALBRICKS == 0) {
                ball.setVisible(false);
                scoreLabel2.setLabel("Winner");
                break;
            }
        }
    }

    //setup method -- called from the run method
    public void setup() {
        createBackground();
        createScoreboard();
        createHighScore();
        createBricks();
        createPaddle();
        createBall();
        addMouseListeners();
        addGameOver();
    }

    //createBricks method -- called from the setup method
    public void createBricks() {
        //make the bricks
        for (int x = 0; x < NBRICK_ROWS; x++) {
            for (int y = 0; y < NBRICKS_PER_ROW; y++) {
                GRect brick = new GRect((y * BRICK_WIDTH) + BRICK_SEP * y + BRICK_SEP / 2,
                        BRICK_Y_OFFSET + (BRICK_HEIGHT * x) + BRICK_SEP * x,
                        BRICK_WIDTH,
                        BRICK_HEIGHT);
                brick.setFilled(true);


                add(brick);

                if (x == 0) {
                    brick.setColor(Color.red);
                }
                if (x == 1) {
                    brick.setColor(Color.orange);
                }
                if (x == 2) {
                    brick.setColor(Color.yellow);
                }
                if (x == 3) {
                    brick.setColor(Color.green);
                }
                if (x == 4) {
                    brick.setColor(Color.blue);
                }
                if (x == 5) {
                    brick.setColor(new Color(0, 51, 153));
                }
                if (x == 6) {
                    brick.setColor(new Color(102, 102, 255));
                }
                if (x == 7) {
                    brick.setColor(new Color(102, 255, 255));
                }
                if (x == 8) {
                    brick.setColor(new Color(255, 102, 255));
                }
                if (x == 9) {
                    brick.setColor(new Color(255, 153, 102));
                }


            }
        }
    }

    //createPaddle method -- called from the setup method
    public void createPaddle() {
        double x = getWidth() / 2 - PADDLE_WIDTH / 2;
        double y = getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
        paddle = new GRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle.setFilled(true);
        add(paddle);
    }

    public void createScoreboard() {
        scoreLabel = new GLabel("Score: ", getWidth() - 150, 20);
        scoreLabel.setColor(Color.RED);
        Font font = new Font("Monospaced", Font.BOLD | Font.ITALIC, 20);
        scoreLabel.setFont(font);
        add(scoreLabel);
    }

    public void createHighScore() {
        scoreLabel1 = new GLabel("Highscore: " + highscore, getWidth() - 400, 20);
        scoreLabel1.setColor(Color.BLUE);
        Font font = new Font("Monospaced", Font.BOLD | Font.ITALIC, 20);
        scoreLabel1.setFont(font);
        add(scoreLabel1);
    }

    public void addGameOver() {
        scoreLabel2 = new GLabel("GAME OVER!", getWidth() / 2, getHeight() / 2);
        scoreLabel2.move(-scoreLabel2.getWidth(), -scoreLabel2.getHeight());
        scoreLabel2.setColor(Color.BLACK);
        Font font = new Font("Monospaced", Font.BOLD | Font.ITALIC, 20);
        scoreLabel2.setVisible(false);


        scoreLabel2.setFont(font);
        add(scoreLabel2);
    }

    public void createBackground() {

        setBackground(Color.cyan);
        //add(Background);
    }


    //createBall method -- called from the setup method
    public void createBall() {

        ball = new GOval(155, 300, 10, 10);
        ball.setFilled(true);
        ball.setColor(Color.black);
        ball.setVisible(false);
        add(ball);
    }

    //play method -- called from the run method after setup
    public void play() {
        scoreLabel2.setVisible(true);

        for(int i=5; i>0; i--)
        {
            scoreLabel2.setLabel("Countdown: "+i);
            pause(1000);
        }
        //waitForClick();
        scoreLabel2.setVisible(false);
        ball.setVisible(true);

        startTheBall();
        playBall();
    }

    //startTheBall method -- called from the play method
    public void startTheBall() {
        vx = (double) (Math.random() * 2.99 + 1.0);
        int flip = (int) (Math.random() * 2 + 1);
        if (flip == 1) {
            vx = -vx;
        }
        vy = 1.0;

    }

    //playBall method -- called from the play method
    public void playBall() {

        //continuous loop
        while (true) {
            //move the ball
            ball.move(vx, vy);
            //pause
            pause(10);

            //check for walls
            if ((ball.getX() - vx <= 0 && vx < 0) || (ball.getX() + vx >= (WIDTH - BALL_RADIUS * 2) && vx > 0)) {
                vx = -vx;
            }
            //We don't need to check for the bottom wall, since the ball can fall through the wall at that point
            if ((ball.getY() - vy <= 0 && vy < 0)) {
                vy = -vy;
            }

            //check for collisions with bricks or paddle
            GObject collider = getCollidingObject();

            //if the ball collided with the paddle
            if (collider == paddle) {
                vy = -vy;
            }
            //otherwise if the ball collided with a brick

            else if (collider instanceof GRect) {

                //reverse y velocity
                Toolkit.getDefaultToolkit().beep();
                vy = -vy;
                //remove the brick
                remove(collider);
                int offset = 0;

                GRect grect = (GRect) collider;
                Color color = grect.getColor();
                if (color == Color.red) {
                    offset = 200;
                } else if (color == Color.orange) {
                    offset = 140;
                } else if (color == Color.yellow) {
                    offset = 110;
                } else if (color == Color.green) {
                    offset = 90;
                } else if (color == Color.blue) {
                    offset = 70;
                } else {
                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();

                    if ((red == 0) && (green == 51) && (blue == 153)) {
                        offset = 50;
                    }

                    if ((red == 102) && (green == 102) && (blue == 255)) {
                        offset = 40;
                    }

                    if ((red == 102) && (green == 255) && (blue == 255)) {
                        offset = 30;
                    }

                    if ((red == 255) && (green == 102) && (blue == 255)) {
                        offset = 20;
                    }

                    if ((red == 255) && (green == 153) && (blue == 102)) {
                        offset = 10;
                    }
                }

                score = score + offset;
                scoreLabel.setLabel("Score: " + score);

                if (score >= highscore) {
                    highscore = score;
                    scoreLabel1.setLabel("Highscore: " + highscore);
                }
            }

            else
            {
               double ballY = ball.getY();
               double paddleY = paddle.getY();

               if (ballY > paddleY)
               {
                   scoreLabel2.setVisible(true);
                   scoreLabel2.setLabel("GAME OVER!");
                   pause(2000);
                   return;
               }
            }
        }
    }


    //getCollidingObject -- called from the playBall method
    //discovers and returns the object that the ball collided with
    private GObject getCollidingObject() {
        if (getElementAt(ball.getX(), ball.getY()) != null)
            return getElementAt(ball.getX(), ball.getY());
        else if (getElementAt(ball.getX() + BALL_RADIUS * 2, ball.getY()) != null)
            return getElementAt(ball.getX() + BALL_RADIUS * 2, ball.getY());
        else if (getElementAt(ball.getX() + BALL_RADIUS * 2, ball.getY() + BALL_RADIUS * 2) != null)
            return getElementAt(ball.getX() + BALL_RADIUS * 2, ball.getY() + BALL_RADIUS * 2);
        else if (getElementAt(ball.getX(), ball.getY() + BALL_RADIUS * 2) != null)
            return getElementAt(ball.getX(), ball.getY() + BALL_RADIUS * 2);
        else
            return null;
    }

    //mouseMoved method -- called by the mouseListener when the mouse is moved
    //anywhere within the boundaries of the run window

    public void mouseMoved(MouseEvent e) {
        // start brian limaye
        if ((e.getX() < getWidth() - PADDLE_WIDTH / 2) && (e.getX() > PADDLE_WIDTH / 2)) {
            paddle.setLocation(e.getX() - PADDLE_WIDTH / 2, getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
            return;
        }
        // end brian limaye

        //only move the paddle every 5th mouse event
        //otherwise the play slows every time the mouse moves
        if (toggle == 5) {
            //get the x-coordinate of the mouse
            double eX = e.getX();

            //if the mouse moved to the right
            if (eX - lastX > 0) {
                //if paddle is not already at the right wall
                if (paddle.getX() < WIDTH - PADDLE_WIDTH) {
                    //move to the right
                    paddle.move(eX - lastX, 0);
                }
            } else //(if the mouse moved to the left)
            {
                //if paddle is not already at the left wall
                if (paddle.getX() > 0) {
                    //move to the left
                    paddle.move(eX - lastX, 0);
                }
            }

            //record this mouse x position for next mouse event
            GPoint last = new GPoint(e.getPoint());
            lastX = last.getX();

            //reset toggle to 1
            toggle = 1;
        } else {
            //increment toggle by 1
            //(when toggle gets to 5 the code will move the paddle
            // and reset toggle back to 1)
            toggle++;
        }
    }
}









