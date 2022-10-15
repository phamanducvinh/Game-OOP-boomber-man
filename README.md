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

## Game detail
![TreeExtends](Game-OOP-bomber-man.png)
- Xây dựng cây kế thừa
- Phát triển thêm hệ thống âm thanh, hiệu ứng bomb nổ, giết quái, nhạc nền ... có thể bật tắt âm thanh tùy ý.
- Phát triển thuật toán tìm đường cho các enemy - xử dụng thuật toán [BFS](https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/)
- Phát trển thêm 4 loại quái vật
  + ![Doll](src/main/resources/sprites/doll_right1.png) Doll, quái vật này có thể tìm được người chơi di chuyển lúc nhanh lúc chậm
  + ![Kondoria](src/main/resources/sprites/kondoria_right1.png) Kondoria, quái vật này có thể tim được người chơi, có thể bò được qua tường
  + ![Ghost](src/main/resources/sprites/ghost.png) Ghost, quái vật này có thể tìm được người chơi, có thể đi xuyên tường và đi qua bomb và có thể tàng hình
  + ![Minvo](src/main/resources/sprites/minvo_right1.png) Minvo, quái vật này có thể tìm được người chơi, né được đường mà bom sẽ nổ, và phun lửa
- Phát triển thêm 3 loại Item mới với sức mạnh lớn
  + ![](src/main/resources/sprites/powerup_bombpass.png) giúp người chơi có thể đi xuyên qua bomb khi chưa nổ
  + ![](src/main/resources/sprites/powerup_flamepass.png) giúp người chơi không bị lửa từ bomb nổ thiêu đốt
  + ![](src/main/resources/sprites/powerup_wallpass.png) giúp người chơi đi xuyên qua được các khối gạch ![Brick](src/main/resources/sprites/brick.png)
