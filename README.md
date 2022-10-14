<h1 align="center"><project-name>Bomberman</h1>

<p align="center"><project-description>Bài tập lớn môn lập trình hướng đối tượng</p>

## Author
- [Phạm An Đức Vinh - 21020097](https://www.facebook.com/phamanducvinhuet/)
- [Nguyễn Đức Thuận - 21020037](https://www.facebook.com/floweronstone)

## Built With
- [Java18](https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html)

## Game engine
- [JavaFx](https://openjfx.io/openjfx-docs/)

## Tools build menu game
- [SceneBuilder](https://gluonhq.com/products/scene-builder/)

## Demo game
![img.png](img.png) 
- Load map
- Di chuyển ![Bomber](src/main/resources/sprites/player_down.png) Bomber theo sự điều kiện của người chơi
- Tự động di chuyển các Enemy (![Balloon](src/main/resources/sprites/balloom_right1.png) Balloon,
![Oneal](src/main/resources/sprites/oneal_right1.png ) Oneal)
- Xỷ lý va chạm cho các đối tượng ![Bomber](src/main/resources/sprites/player_down.png) Bomber, 
Enemy,
![Wall](src/main/resources/sprites/wall.png) Wall, 
![Brick](src/main/resources/sprites/brick.png) Brick, 
![Bomb](src/main/resources/sprites/bomb.png) Bomb
- Xỷ lý bomb nổ ![](src/main/resources/sprites/bomb.png)
- Xử lý Khi bomber sử dụng các Item và khi di vào vài vị trí Portal
    + ![Portal](src/main/resources/sprites/portal.png) Cổng dịch chuyển sang màn mới
    + ![SpeedItem](src/main/resources/sprites/powerup_speed.png) Chạy nhanh hơn
    + ![FlameItem](src/main/resources/sprites/powerup_flames.png) Bomb nổ to hơn
    + ![BombItem](src/main/resources/sprites/powerup_bombs.png) Đặt được nhiều bomb cùng lúc chưa nổ nhiều hơn
- [Video demo game](https://drive.google.com/file/d/1ZdyyQUwcMcBzrQsOe-bswLOe13SIa8M-/view?usp=sharing)