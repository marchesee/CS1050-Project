import java.util.Random;

public class Room {

    private int width, height;       // room size
    private char[][] grid;           // layout
    private int playerX, playerY;    // player pos
    private int enemyX, enemyY;      // enemy pos
    private int doorX, doorY;        // door pos
    private Enemy enemy;             // enemy object
    private boolean enemyAlive;      // track if alive
    private Random random = new Random();

    public Room() {
        width = random.nextInt(4) + 6;   //room size 6–9
        height = random.nextInt(4) + 6;
        grid = new char[height][width];
        generateRoom();
    }

    private void generateRoom() {
        // walls and floor
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x == 0 || y == 0 || x == width-1 || y == height-1) grid[y][x] = '#';
                else grid[y][x] = '.';
            }
        }

        playerX = 1; playerY = 1;

        // enemy somewhere inside, not on player
        do {
            enemyX = random.nextInt(width-2) + 1;
            enemyY = random.nextInt(height-2) + 1;
        } while (enemyX == playerX && enemyY == playerY);

        int enemyType = random.nextInt(3);

        switch (enemyType) {
            case 0:
                enemy = new Enemy("Goblin", 40, 10, 2, 15, "sprites/goblin");
                break;
            case 1:
                enemy = new Skeleton("Skeleton", 30, 15, 1, 20, "sprites/skeleton"); 
                break;
            case 2:
                enemy = new Golem("Golem", 80, 5, 8, 40, "sprites/golem"); 
                break;
        }
        
        enemyAlive = true;
    }

    // draw everything
    public void drawRoom() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
            	if (x == playerX && y == playerY) System.out.print('P');
            	else if (x == enemyX && y == enemyY && enemyAlive) System.out.print('E');  // only show if alive
            	else System.out.print(grid[y][x]);
            }
            System.out.println();
        }
    }

    // move player, stop at walls
    public void movePlayer(int dx, int dy) {
        int newX = playerX + dx;
        int newY = playerY + dy;
        if (grid[newY][newX] != '#') { playerX = newX; playerY = newY; }
    }

    // check if player next to enemy
    public boolean isNearEnemy() {
        if (!enemyAlive) return false;
        int dx = Math.abs(playerX - enemyX);
        int dy = Math.abs(playerY - enemyY);
        return dx + dy == 1;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
        this.enemyAlive = true;
    }
    
    public Enemy getEnemy() { return enemy; }

    // called after combat ends
    public void enemyDefeated() {
        enemyAlive = false;
        grid[enemyY][enemyX] = '.';   // remove enemy
        generateDoor();               // spawn exit
        System.out.println("A doorway appears!");
    }

    // randomly generate door
    private void generateDoor() {
        int side = random.nextInt(4);
        switch(side) {
            case 0: doorX = random.nextInt(width-2)+1; doorY=0; break;
            case 1: doorX = random.nextInt(width-2)+1; doorY=height-1; break;
            case 2: doorX = 0; doorY=random.nextInt(height-2)+1; break;
            case 3: doorX = width-1; doorY=random.nextInt(height-2)+1; break;
        }
        grid[doorY][doorX] = 'D';
    }

    public boolean reachedDoor() { return playerX==doorX && playerY==doorY; }
    
    
}