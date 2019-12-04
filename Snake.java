// snakeX: {5, 6, 0, 0, 0, 0 ...}
// snakeY: {5, 5, 0, 0, 0, 0 ...}
int[] snakeX = new int[1000];
int[] snakeY = new int[1000];
int appleX = 10;
int appleY = 10;
int snakeSize = 1;
int blockSize;
int widthInBlocks;
int heightInBlocks;
boolean isExtended = false;
boolean isGameOver = false;

void setup() {
  size(1000, 1000);
  blockSize = width / 30;
  widthInBlocks = width / blockSize;
  heightInBlocks = height / blockSize;
  snakeX[0] = 5;
  snakeY[0] = 5;
}

void draw() {
  if (!isGameOver) {
    background(255, 255, 255);
    drawSnake();
    drawApple();

    if (snakeX[0] == appleX && snakeY[0] == appleY) {
        appleX = (int) random(0, widthInBlocks);
        appleY = (int) random(0, heightInBlocks);

        extendSnake();
    }

    for (int i = 1; i < snakeSize; ++i) {
        if (snakeX[i] == snakeX[0] && snakeY[i] == snakeY[0] && !isExtended) {
            isGameOver = true;
        }
    }
  }
  else {
    background(255);
    textSize(30);
    textAlign(CENTER);
    fill(255, 0, 0);
    text("Game Over. Press space to restart.", width / 2, height / 2);

    if (keyPressed) {
        if (key == ' ') {
            isGameOver = false;
            snakeSize = 1;
            snakeX[0] = (int) random(0, widthInBlocks);
            snakeY[0] = (int) random(0, heightInBlocks);
        }
    }
  }
}

void keyPressed() {
    if (key == 'w') moveSnake(0, -1);
    if (key == 'a') moveSnake(-1, 0);
    if (key == 's') moveSnake(0, 1);
    if (key == 'd') moveSnake(1, 0);

    if (key == CODED) {
      if (keyCode == UP) moveSnake(0, -1);
      if (keyCode == LEFT) moveSnake(-1, 0);
      if (keyCode == DOWN) moveSnake(0, 1);
      if (keyCode == RIGHT) moveSnake(1, 0);
    }
}

void drawSnake() {
    fill(0, 255, 0);
    for (int i = 0; i < snakeSize; ++i) {
        rect(snakeX[i] * blockSize, snakeY[i] * blockSize, blockSize, blockSize);
    }
}

void drawApple() {
    fill(255, 0, 0);
    rect(appleX * blockSize, appleY * blockSize, blockSize, blockSize);
}

void moveSnake(int directionX, int directionY) {
    if (isExtended) {
        snakeX[0] += directionX;
        snakeY[0] += directionY;
        restrictPosition();
        isExtended = false;
        return;
    }
    for (int i = snakeSize - 1; i >= 1; --i) {
        snakeX[i] = snakeX[i - 1];
        snakeY[i] = snakeY[i - 1];
    }
    snakeX[0] += directionX;
    snakeY[0] += directionY;

    restrictPosition();
}

void restrictPosition() {
    if (snakeX[0] >= widthInBlocks) {
        snakeX[0] = 0;
    }
    else if (snakeX[0] < 0) {
        snakeX[0] = widthInBlocks - 1;
    }
    else if (snakeY[0] >= heightInBlocks) {
        snakeY[0] = 0;
    }
    else if (snakeY[0] < 0) {
        snakeY[0] = heightInBlocks - 1;
    }
}
void extendSnake() {
    snakeSize++;
    System.out.println(snakeSize);
    for (int i = snakeSize - 1; i > 0; --i) {
        snakeX[i] = snakeX[i - 1];
        snakeY[i] = snakeY[i - 1];
    }
    isExtended = true;
}
