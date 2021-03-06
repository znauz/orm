
import java.awt.Color;
import java.util.ArrayList;

public class Snake {

    public boolean Up = false;
    public boolean Right = true;
    public boolean Left = false;
    public boolean Down = false;

    public int nBalls;
    public ArrayList<Integer> xBalls = new ArrayList<Integer>();
    public ArrayList<Integer> yBalls = new ArrayList<Integer>();

    public int moveSize = 20;
    public int ballHeight = 20;
    public int ballWidth = 20;

    public int worldX = 800;
    public int worldY = 600;

    public char name;
    private String playerName;
    private boolean dead = false;

    Color color;

    public Snake(char c, String p, int startPosX, int startPosY) {
        this.name = c;
        this.playerName = p;

        xBalls.add(0, startPosX);
        yBalls.add(0, startPosY);

        xBalls.add(0, startPosX);
        yBalls.add(0, startPosY + 20);

        xBalls.add(0, startPosX);
        yBalls.add(0, startPosY + 40);

        xBalls.add(0, startPosX);
        yBalls.add(0, startPosY + 60);

        nBalls = 4;
    }

    public Snake(char c, String p, String startPosX, String startPosY) {
        this.name = c;
        this.playerName = p;
        int startPoX = Integer.parseInt(startPosX);
        int startPoY = Integer.parseInt(startPosY);
        xBalls.add(0, startPoX);
        yBalls.add(0, startPoY);

        xBalls.add(0, startPoX);
        yBalls.add(0, startPoY + 20);

        xBalls.add(0, startPoX);
        yBalls.add(0, startPoY + 40);

        xBalls.add(0, startPoX);
        yBalls.add(0, startPoY + 60);

        nBalls = 4;
    }

    public void setDirection(char c) {
        if (c == 'U' && !Down) {

            Up = true;
            Down = false;
            Right = false;
            Left = false;
        }
        if (c == 'D' && !Up) {

            Up = false;
            Down = true;
            Right = false;
            Left = false;
        }
        if (c == 'R' && !Left) {
            Up = false;
            Down = false;
            Right = true;
            Left = false;
        }
        if (c == 'L' && !Right) {
            Up = false;
            Down = false;
            Right = false;
            Left = true;
        }
    }

    public char[] getDirection() {
        char[] info = new char[2];
        info[0] = this.name;
        if (Up) {
            info[1] = 'U';
        }
        if (Down) {
            info[1] = 'D';
        }
        if (Right) {
            info[1] = 'R';
        }
        if (Left) {
            info[1] = 'L';
        }
        return info;
    }

    public void checkCollision() {

        int x = xBalls.get(nBalls - 1);
        int y = yBalls.get(nBalls - 1);
        for (int i = 0; i < nBalls - 1; i++) {

            if ((xBalls.get(nBalls - 1).equals(xBalls.get(i))) && (yBalls.get(nBalls - 1).equals(yBalls.get(i)))) {
                System.out.println("Game over");
                this.dead = true;
            }
        }
        if (x >= worldX - ballWidth) {
            
            this.dead = true;
        }
        if (x < 0) {
            
            this.dead = true;
        }
        if (y > worldY - ballHeight) {
            
            this.dead = true;
        }
        if (y < 0) {
            
            this.dead = true;
        }
    }

    public void checkOtherCollision(ArrayList<Snake> snakes){
	ArrayList<Integer> BallsX;
	ArrayList<Integer> BallsY;
	
	for(int j = 0; j < snakes.size(); j++){
	    BallsX = snakes.get(j).getSnakeX();
	    BallsY = snakes.get(j).getSnakeY();
	    for (int i = 0; i < BallsX.size()-1; i++) {
		if ((this.xBalls.get(nBalls - 1).equals(BallsX.get(i))) && (this.yBalls.get(nBalls - 1).equals(BallsY.get(i)))) {
		    System.out.println("Game over, collided with: " + snakes.get(j).getName());
		    this.dead = true;
		}
	    }
	}
    }

    public void move() {
        int x;
        int y;

        if (Up) {

            x = this.xBalls.get(nBalls - 1);
            y = this.yBalls.get(nBalls - 1);
            y = y - moveSize;

            this.xBalls.add(x);
            this.yBalls.add(y);

            this.xBalls.remove(0);
            this.yBalls.remove(0);

        } else if (Down) {

            x = this.xBalls.get(nBalls - 1);
            y = this.yBalls.get(nBalls - 1);
            y = y + moveSize;

            this.xBalls.add(x);
            this.yBalls.add(y);

            this.xBalls.remove(0);
            this.yBalls.remove(0);

        } else if (Left) {
            x = this.xBalls.get(nBalls - 1);
            y = this.yBalls.get(nBalls - 1);
            x = x - moveSize;

            this.xBalls.add(x);
            this.yBalls.add(y);

            this.xBalls.remove(0);
            this.yBalls.remove(0);

        } else if (Right) {
            x = this.xBalls.get(nBalls - 1);
            y = this.yBalls.get(nBalls - 1);
            x = x + moveSize;

            this.xBalls.add(x);
            this.yBalls.add(y);

            this.xBalls.remove(0);
            this.yBalls.remove(0);

        }
    }

    public void grow() {
        this.nBalls = this.nBalls + 1;

        int x = xBalls.get(0);
        int y = yBalls.get(0);

        xBalls.add(0, x);
        yBalls.add(0, y);
    }

    public ArrayList<Integer> getSnakeX() {
        return xBalls;
    }

    public ArrayList<Integer> getSnakeY() {
        return yBalls;
    }

    public void updatePos(ArrayList<Integer> xBalls, ArrayList<Integer> yBalls) {
        this.xBalls = xBalls;
        this.yBalls = yBalls;
    }

    public void updateHead(String hX, String hY) {
        int headX = Integer.parseInt(hX);
        int headY = Integer.parseInt(hY);
        xBalls.add(xBalls.size(), headX);
        xBalls.remove(0);
        yBalls.add(yBalls.size(), headY);
        yBalls.remove(0);
    }

    public String getHead() {
        return xBalls.get(xBalls.size() - 1) + ";" + yBalls.get(yBalls.size() - 1) + ";";
    }

    public int getSize() {
        return this.nBalls;
    }

    public Color getColor() {
        return color;
    }

    public int getHeight() {
        return ballHeight;
    }

    public int getWidth() {
        return ballWidth;
    }

    public char getName() {
        return this.name;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public boolean isDead() {
        return this.dead;
    }

    public void collideSnake(Snake s) {
        int sSize = s.getSize();
        int x = this.xBalls.get(this.nBalls - 1);
        int y = this.yBalls.get(this.nBalls - 1);
        for (int i = 0; i < sSize; i++) {
            if (x == s.getSnakeX().get(i) && y == s.getSnakeY().get(i)) {
                this.dead = true;
                break;
            }
        }
    }
    
    public void setColor(Color color){
        this.color = color;
    }
}
