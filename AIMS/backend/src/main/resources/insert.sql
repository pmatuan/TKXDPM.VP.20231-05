INSERT INTO media (id, created_at, updated_at, barcode_url, category, `description`, image_url, import_date, is_able_to_rush_delivery, price, quantity_in_stock, title, `value`, `weight`)
VALUES
    (1, NOW(), NOW(), 'barcode_url_1', 'book', 'Interesting novel about life', 'http://localhost:8080/api/v1/media/images/image_url_1.jpg', '2023-01-01', 1, 19990, 50, 'The Journey of a Lifetime', 29990, 1.5),
    (2, NOW(), NOW(), 'barcode_url_2', 'book', 'Chart-topping music album', 'http://localhost:8080/api/v1/media/images/image_url_2.jpg', '2023-02-15', 0, 12990, 100, 'Harmony Melodies', 18990, 0.8),
    (3, NOW(), NOW(), 'barcode_url_3', 'book', 'Award-winning documentary', 'http://localhost:8080/api/v1/media/images/image_url_3.jpg', '2023-03-20', 1, 24990, 30, 'Discovering Nature', 34990, 2.0),
    (4, NOW(), NOW(), 'barcode_url_4', 'book', 'Classic vinyl record collection', 'http://localhost:8080/api/v1/media/images/image_url_4.jpg', '2023-04-10', 0, 39990, 75, 'Vintage Vibes', 49990, 2.5),
    (5, NOW(), NOW(), 'barcode_url_5', 'book', 'Suspenseful thriller', 'http://localhost:8080/api/v1/media/images/image_url_5.jpg', '2023-05-05', 1, 21990, 40, 'Whispers in the Shadows', 31990, 1.2),
    (6, NOW(), NOW(), 'barcode_url_6', 'book', 'Soulful jazz compilation', 'http://localhost:8080/api/v1/media/images/image_url_6.jpg', '2023-06-12', 0, 15990, 90, 'Smooth Jazz Express', 25990, 0.9),
    (7, NOW(), NOW(), 'barcode_url_7', 'book', 'Educational documentary series', 'http://localhost:8080/api/v1/media/images/image_url_7.jpg', '2023-07-18', 1, 29990, 20, 'The World Unveiled', 39990, 1.8),
    (8, NOW(), NOW(), 'barcode_url_8', 'book', 'Rock and roll classics', 'http://localhost:8080/api/v1/media/images/image_url_8.jpg', '2023-08-22', 0, 44990, 60, 'Rock Revolution', 54990, 2.7),
    (9, NOW(), NOW(), 'barcode_url_9', 'book', 'Heartwarming love story', 'http://localhost:8080/api/v1/media/images/image_url_9.jpg', '2023-09-30', 1, 17990, 55, 'Love Beyond Words', 27990, 1.0),
    (10, NOW(), NOW(), 'barcode_url_10', 'book', 'Energetic pop hits', 'http://localhost:8080/api/v1/media/images/image_url_10.jpg', '2023-10-15', 0, 14990, 80, 'Pop Sensation', 24990, 0.7),
    (11, NOW(), NOW(), 'barcode_url_11', 'book', 'Historical drama series', 'http://localhost:8080/api/v1/media/images/image_url_11.jpg', '2023-11-25', 1, 26990, 25, 'Epic Chronicles', 36990, 1.5),
    (12, NOW(), NOW(), 'barcode_url_12', 'book', 'Blues and soul compilation', 'http://localhost:8080/api/v1/media/images/image_url_12.jpg', '2023-12-05', 0, 49990, 70, 'Soulful Blues Journey', 59990, 2.8),
    (13, NOW(), NOW(), 'barcode_url_13', 'book', 'Inspirational self-help book', 'http://localhost:8080/api/v1/media/images/image_url_13.jpg', '2024-01-01', 1, 23990, 45, 'The Power of Positivity', 33990, 1.3),
    (14, NOW(), NOW(), 'barcode_url_14', 'book', 'Top 40 hits', 'http://localhost:8080/api/v1/media/images/image_url_14.jpg', '2024-02-15', 0, 16990, 95, 'Top 40 Countdown', 26990, 0.6),
    (15, NOW(), NOW(), 'barcode_url_15', 'book', 'Award-winning documentary', 'http://localhost:8080/api/v1/media/images/image_url_15.jpg', '2024-03-20', 1, 24990, 30, 'Discovering Nature', 34990, 1.8),
    (16, NOW(), NOW(), 'barcode_url_16', 'book', 'Classic vinyl record collection', 'http://localhost:8080/api/v1/media/images/image_url_16.jpg', '2024-04-10', 0, 39990, 75, 'Vintage Vibes', 49990, 2.5),
    (17, NOW(), NOW(), 'barcode_url_17', 'book', 'Suspenseful thriller', 'http://localhost:8080/api/v1/media/images/image_url_17.jpg', '2024-05-05', 1, 21990, 40, 'Whispers in the Shadows', 31990, 1.2),
    (18, NOW(), NOW(), 'barcode_url_18', 'book', 'Soulful jazz compilation', 'http://localhost:8080/api/v1/media/images/image_url_18.jpg', '2024-06-12', 0, 15990, 90, 'Smooth Jazz Express', 25990, 0.9),
    (19, NOW(), NOW(), 'barcode_url_19', 'book', 'Educational documentary series', 'http://localhost:8080/api/v1/media/images/image_url_19.jpg', '2024-07-18', 1, 29990, 20, 'The World Unveiled', 39990, 1.8),
    (20, NOW(), NOW(), 'barcode_url_20', 'book', 'Rock and roll classics', 'http://localhost:8080/api/v1/media/images/image_url_20.jpg', '2024-08-22', 0, 44990, 60, 'Rock Revolution', 54990, 2.7),
    (21, NOW(), NOW(), 'barcode_url_21', 'book', 'Heartwarming love story', 'http://localhost:8080/api/v1/media/images/image_url_21.jpg', '2024-09-30', 1, 17990, 55, 'Love Beyond Words', 27990, 1.0),
    (22, NOW(), NOW(), 'barcode_url_22', 'cd', 'Energetic pop hits', 'http://localhost:8080/api/v1/media/images/image_url_22.jpg', '2024-10-15', 0, 14990, 80, 'Pop Sensation', 24990, 0.7),
    (23, NOW(), NOW(), 'barcode_url_23', 'cd', 'Historical drama series', 'http://localhost:8080/api/v1/media/images/image_url_23.jpg', '2024-11-25', 1, 26990, 25, 'Epic Chronicles', 36990, 1.5),
    (24, NOW(), NOW(), 'barcode_url_24', 'cd', 'Blues and soul compilation', 'http://localhost:8080/api/v1/media/images/image_url_24.jpg', '2024-12-05', 0, 49990, 70, 'Soulful Blues Journey', 59990, 2.8),
    (25, NOW(), NOW(), 'barcode_url_25', 'cd', 'Inspirational self-help book', 'http://localhost:8080/api/v1/media/images/image_url_25.jpg', '2025-01-01', 1, 23990, 45, 'The Power of Positivity', 33990, 1.3),
    (26, NOW(), NOW(), 'barcode_url_26', 'cd', 'Top 40 hits', 'http://localhost:8080/api/v1/media/images/image_url_26.jpg', '2025-02-15', 0, 16990, 95, 'Top 40 Countdown', 26990, 0.6),
    (27, NOW(), NOW(), 'barcode_url_27', 'cd', 'Award-winning documentary', 'http://localhost:8080/api/v1/media/images/image_url_27.jpg', '2025-03-20', 1, 24990, 30, 'Discovering Nature', 34990, 1.8),
    (28, NOW(), NOW(), 'barcode_url_28', 'cd', 'Classic vinyl record collection', 'http://localhost:8080/api/v1/media/images/image_url_28.jpg', '2025-04-10', 0, 39990, 75, 'Vintage Vibes', 49990, 2.5),
    (29, NOW(), NOW(), 'barcode_url_29', 'cd', 'Suspenseful thriller', 'http://localhost:8080/api/v1/media/images/image_url_29.jpg', '2025-05-05', 1, 21990, 40, 'Whispers in the Shadows', 31990, 1.2),
    (30, NOW(), NOW(), 'barcode_url_30', 'cd', 'Soulful jazz compilation', 'http://localhost:8080/api/v1/media/images/image_url_30.jpg', '2025-06-12', 0, 15990, 90, 'Smooth Jazz Express', 25990, 0.9),
    (31, NOW(), NOW(), 'barcode_url_31', 'dvd', 'Educational documentary series', 'http://localhost:8080/api/v1/media/images/image_url_31.jpg', '2025-07-18', 1, 29990, 20, 'The World Unveiled', 39990, 1.8),
    (32, NOW(), NOW(), 'barcode_url_32', 'dvd', 'Rock and roll classics', 'http://localhost:8080/api/v1/media/images/image_url_32.jpg', '2025-08-22', 0, 44990, 60, 'Rock Revolution', 54990, 2.7),
    (33, NOW(), NOW(), 'barcode_url_33', 'dvd', 'Heartwarming love story', 'http://localhost:8080/api/v1/media/images/image_url_33.jpg', '2025-09-30', 1, 17990, 55, 'Love Beyond Words', 27990, 1.0),
    (34, NOW(), NOW(), 'barcode_url_34', 'dvd', 'Energetic pop hits', 'http://localhost:8080/api/v1/media/images/image_url_34.jpg', '2025-10-15', 0, 14990, 80, 'Pop Sensation', 24990, 0.7),
    (35, NOW(), NOW(), 'barcode_url_35', 'dvd', 'Historical drama series', 'http://localhost:8080/api/v1/media/images/image_url_35.jpg', '2025-11-25', 1, 26990, 25, 'Epic Chronicles', 36990, 1.5),
    (36, NOW(), NOW(), 'barcode_url_36', 'dvd', 'Blues and soul compilation', 'http://localhost:8080/api/v1/media/images/image_url_36.jpg', '2025-12-05', 0, 49990, 70, 'Soulful Blues Journey', 59990, 2.8),
    (37, NOW(), NOW(), 'barcode_url_37', 'dvd', 'Inspirational self-help book', 'http://localhost:8080/api/v1/media/images/image_url_37.jpg', '2026-01-01', 1, 23990, 45, 'The Power of Positivity', 33990, 1.3),
    (38, NOW(), NOW(), 'barcode_url_38', 'dvd', 'Top 40 hits', 'http://localhost:8080/api/v1/media/images/image_url_38.jpg', '2026-02-15', 0, 16990, 95, 'Top 40 Countdown', 26990, 0.6),
    (39, NOW(), NOW(), 'barcode_url_39', 'dvd', 'Award-winning documentary', 'http://localhost:8080/api/v1/media/images/image_url_39.jpg', '2026-03-20', 1, 24990, 30, 'Discovering Nature', 34990, 1.8),
    (40, NOW(), NOW(), 'barcode_url_40', 'dvd', 'Classic vinyl record collection', 'http://localhost:8080/api/v1/media/images/image_url_40.jpg', '2026-04-10', 0, 39990, 75, 'Vintage Vibes', 49990, 2.5),
    (41, NOW(), NOW(), 'barcode_url_41', 'dvd', 'Suspenseful thriller', 'http://localhost:8080/api/v1/media/images/image_url_41.jpg', '2026-05-05', 1, 21990, 40, 'Whispers in the Shadows', 31990, 1.2),
    (42, NOW(), NOW(), 'barcode_url_42', 'dvd', 'Soulful jazz compilation', 'http://localhost:8080/api/v1/media/images/image_url_42.jpg', '2026-06-12', 0, 15990, 90, 'Smooth Jazz Express', 25990, 0.9),
    (43, NOW(), NOW(), 'barcode_url_43', 'dvd', 'Educational documentary series', 'http://localhost:8080/api/v1/media/images/image_url_43.jpg', '2026-07-18', 1, 29990, 20, 'The World Unveiled', 39990, 1.8),
    (44, NOW(), NOW(), 'barcode_url_44', 'dvd', 'Rock and roll classics', 'http://localhost:8080/api/v1/media/images/image_url_44.jpg', '2026-08-22', 0, 44990, 60, 'Rock Revolution', 54990, 2.7),
    (45, NOW(), NOW(), 'barcode_url_45', 'dvd', 'Heartwarming love story', 'http://localhost:8080/api/v1/media/images/image_url_45.jpg', '2026-09-30', 1, 17990, 55, 'Love Beyond Words', 27990, 1.0),
    (46, NOW(), NOW(), 'barcode_url_46', 'lp', 'Energetic pop hits', 'http://localhost:8080/api/v1/media/images/image_url_46.jpg', '2026-10-15', 0, 14990, 80, 'Pop Sensation', 24990, 0.7),
    (47, NOW(), NOW(), 'barcode_url_47', 'lp', 'Historical drama series', 'http://localhost:8080/api/v1/media/images/image_url_47.jpg', '2026-11-25', 1, 26990, 25, 'Epic Chronicles', 36990, 1.5),
    (48, NOW(), NOW(), 'barcode_url_48', 'lp', 'Blues and soul compilation', 'http://localhost:8080/api/v1/media/images/image_url_48.jpg', '2026-12-05', 0, 49990, 70, 'Soulful Blues Journey', 59990, 2.8),
    (49, NOW(), NOW(), 'barcode_url_49', 'lp', 'Inspirational self-help book', 'http://localhost:8080/api/v1/media/images/image_url_49.jpg', '2027-01-01', 1, 23990, 45, 'The Power of Positivity', 33990, 1.3),
    (50, NOW(), NOW(), 'barcode_url_50', 'lp', 'Top 40 hits', 'http://localhost:8080/api/v1/media/images/image_url_50.jpg', '2027-02-15', 0, 16990, 95, 'Top 40 Countdown', 26990, 0.6);






INSERT INTO book (authors, cover_type, `language`, pages, publication_date, publisher, `type`, id)
VALUES
    ('John Doe', 'UNKNOWN', 'English', 23, '2020-01-01', 'Kim Dong', 'Tieu thuyet', 1),
    ('Alice Smith', 'PAPERBACK', 'French', 250, '2021-03-15', 'Librairie Française', 'Roman', 2),
    ('Bob Writer', 'UNKNOWN', 'Spanish', 180, '2019-12-10', 'Casa de Libros', 'Novela', 3),
    ('Eva Editor', 'HARDCOVER', 'German', 400, '2022-06-20', 'Buch Verlag', 'Roman', 4),
    ('Carlos Contributor', 'PAPERBACK', 'Italian', 350, '2023-04-05', 'Casa Editrice', 'Romanzo', 5),
    ('Mia Author', 'UNKNOWN', 'Portuguese', 280, '2024-01-10', 'Editora Livro', 'Romance', 6),
    ('John Doe', 'UNKNOWN', 'English', 23, '2020-01-01', 'Kim Dong', 'Tieu thuyet', 7),
    ('Alice Smith', 'PAPERBACK', 'French', 250, '2021-03-15', 'Librairie Française', 'Roman', 8),
    ('Bob Writer', 'UNKNOWN', 'Spanish', 180, '2019-12-10', 'Casa de Libros', 'Novela', 9),
    ('Eva Editor', 'HARDCOVER', 'German', 400, '2022-06-20', 'Buch Verlag', 'Roman', 10),
    ('Carlos Contributor', 'PAPERBACK', 'Italian', 350, '2023-04-05', 'Casa Editrice', 'Romanzo', 11),
    ('Mia Author', 'UNKNOWN', 'Portuguese', 280, '2024-01-10', 'Editora Livro', 'Romance', 12),
    ('John Doe', 'UNKNOWN', 'English', 23, '2020-01-01', 'Kim Dong', 'Tieu thuyet', 13),
    ('Alice Smith', 'PAPERBACK', 'French', 250, '2021-03-15', 'Librairie Française', 'Roman', 14),
    ('Bob Writer', 'UNKNOWN', 'Spanish', 180, '2019-12-10', 'Casa de Libros', 'Novela', 15),
    ('Eva Editor', 'HARDCOVER', 'German', 400, '2022-06-20', 'Buch Verlag', 'Roman', 16),
    ('Carlos Contributor', 'PAPERBACK', 'Italian', 350, '2023-04-05', 'Casa Editrice', 'Romanzo', 17),
    ('Mia Author', 'UNKNOWN', 'Portuguese', 280, '2024-01-10', 'Editora Livro', 'Romance', 18),
    ('John Doe', 'UNKNOWN', 'English', 23, '2020-01-01', 'Kim Dong', 'Tieu thuyet', 19),
    ('Alice Smith', 'PAPERBACK', 'French', 250, '2021-03-01','Kim DOng', 'Truyen', 20);



INSERT INTO cd (artists, genres, record_label, release_date, track_list, id)
VALUES
    ('Adele', 'Pop', 'Music Records', '2023-01-15', 'Hello, Someone Like You, Rolling in the Deep', 21),
    ('Ed Sheeran', 'Pop', 'Atlantic Records', '2023-03-20', 'Shape of You, Thinking Out Loud', 22),
    ('John Coltrane', 'Jazz', 'Blue Note', '2023-05-25', 'Giant Steps, My Favorite Things', 23),
    ('Calvin Harris', 'Electronic', 'Columbia Records', '2023-07-10', 'Summer, Feel So Close', 24),
    ('Johnny Cash', 'Country', 'Sun Records', '2023-09-15', 'Ring of Fire, I Walk the Line', 25),
    ('Lady Gaga', 'Pop', 'Interscope Records', '2023-11-20', 'Bad Romance, Poker Face', 26),
    ('The Beatles', 'Rock', 'Apple Records', '2024-01-10', 'Hey Jude, Let It Be', 27),
    ('Miles Davis', 'Jazz', 'Columbia Records', '2024-03-15', 'So What, Kind of Blue', 28),
    ('Avicii', 'Electronic', 'PRMD', '2024-05-20', 'Wake Me Up, Levels', 29),
    ('Shania Twain', 'Country', 'Mercury Nashville', '2024-07-25', 'Man! I Feel Like a Woman!, Youre Still the One', 30);


INSERT INTO dvd (director, disc_format, genres, `language`, release_date, run_time, studio, subtitles, id)
VALUES
    ('Christopher Nolan', 'BLURAY', 'Action', 'English', '2023-01-15', 150.5, 'Warner Bros.', 'English', 31),
    ('Quentin Tarantino', 'UNKNOWN', 'Crime', 'English', '2023-03-20', 180.0, 'Miramax Films', 'English', 32),
    ('Steven Spielberg', 'BLURAY', 'Adventure', 'English', '2023-05-25', 130.75, 'Amblin Entertainment', 'English', 33),
    ('Martin Scorsese', 'UNKNOWN', 'Drama', 'English', '2023-07-10', 160.25, 'Paramount Pictures', 'English', 34),
    ('Hayao Miyazaki', 'BLURAY', 'Animation', 'Japanese', '2023-09-15', 120.0, 'Studio Ghibli', 'English, Japanese', 35),
    ('Ridley Scott', 'UNKNOWN', 'Sci-Fi', 'English', '2023-11-20', 145.5, '20th Century Studios', 'English', 36),
    ('Alfred Hitchcock', 'BLURAY', 'Thriller', 'English', '2024-01-10', 110.75, 'Paramount Pictures', 'English', 37),
    ('Stanley Kubrick', 'UNKNOWN', 'Mystery', 'English', '2024-03-15', 155.0, 'Warner Bros.', 'English', 38),
    ('Spike Lee', 'BLURAY', 'Comedy', 'English', '2024-05-20', 140.5, '40 Acres and a Mule Filmworks', 'English', 39),
    ('Tim Burton', 'UNKNOWN', 'Fantasy', 'English', '2024-07-25', 125.25, 'Walt Disney Pictures', 'English', 40),
    ('Ang Lee', 'BLURAY', 'Romance', 'English', '2024-09-30', 170.75, 'Focus Features', 'English', 41),
    ('David Fincher', 'UNKNOWN', 'Mystery', 'English', '2024-12-05', 150.0, '20th Century Studios', 'English', 42),
    ('James Cameron', 'BLURAY', 'Sci-Fi', 'English', '2025-02-10', 180.25, 'Lightstorm Entertainment', 'English', 43),
    ('George Lucas', 'UNKNOWN', 'Adventure', 'English', '2025-04-15', 165.5, 'Lucasfilm', 'English', 44),
    ('Greta Gerwig', 'BLURAY', 'Drama', 'English', '2025-06-20', 120.75, 'Columbia Pictures', 'English', 45);

INSERT INTO lp (artists, genres, record_label, release_date, track_list, id)
VALUES
    ('Elvis Presley', 'Rock and Roll', 'RCA Records', '1956-03-23', 'Blue Suede Shoes, Heartbreak Hotel', 46),
    ('Miles Davis', 'Jazz', 'Columbia Records', '1959-08-17', 'So What, Freddie Freeloader', 47),
    ('The Beatles', 'Rock', 'Parlophone', '1967-05-26', 'Lucy in the Sky with Diamonds, A Day in the Life', 48),
    ('Michael Jackson', 'Pop', 'Epic Records', '1982-11-30', 'Billie Jean, Beat It', 49),
    ('Bob Marley', 'Reggae', 'Island Records', '1977-06-03', 'Three Little Birds, No Woman, No Cry', 50);

INSERT INTO sender (config, provider)
VALUES ('{"mail": {"host": "smtp.gmail.com", "port": "587", "password": "gwpp vehb oqnr mkvk", "username": "chamsockhachhangaims@gmail.com", "properties": {"mail": {"mime": {"charset": "UTF"}, "smtp": {"auth": true, "starttls": {"enable": true, "required": true}}}}, "default-encoding": "UTF-8"}}', 'GMAIL')

    INSERT INTO template (title, content, required_parameters)
VALUES ('Xác nhận đơn hàng', '<!DOCTYPE html>
<html lang="vi" xmlns:th="http://www.thymeleaf.org">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Xác nhận đơn hàng</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      line-height: 1.6;
      margin: 0;
      padding: 20px;
      background-color: #f4f4f4;
    }

    .container {
      max-width: 600px;
      margin: 0 auto;
      background-color: #ffffff;
      padding: 20px;
      border-radius: 5px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    h1 {
      color: #333;
    }

    p {
      color: #555;
    }

    .footer {
      margin-top: 20px;
      padding-top: 10px;
      border-top: 1px solid #ddd;
      color: #888;
      font-size: 12px;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>Đặt hàng thành công</h1>
  <p>
    Cảm ơn bạn đã đặt hàng từ chúng tôi.
  </p>
  <p>Thông tin đơn hàng của bạn:</p>
  <ul>
    <li><strong>Mã đơn hàng:</strong> <span th:text="${orderId}"></span></li>
  </ul>
  <p>
    Để theo dõi tình trạng đơn hàng của bạn, vui lòng theo dõi <a th:href="${trace_order_link}">tại đây</a>
  </p>
  <p>Cảm ơn bạn đã mua sắm tại cửa hàng chúng tôi!</p>
  <div class="footer">
    <p>Trân trọng,<br />AIMS</p>
  </div>
</div>
</body>
</html>
', '["orderId", "trace_order_link"]');

INSERT INTO template (title, content, required_parameters)
VALUES ('Xác nhận hủy đơn hàng', '<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Xác nhận huỷ đơn hàng</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      line-height: 1.6;
      margin: 0;
      padding: 20px;
      background-color: #f4f4f4;
    }

    .container {
      max-width: 600px;
      margin: 0 auto;
      background-color: #ffffff;
      padding: 20px;
      border-radius: 5px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    h1 {
      color: #333;
    }

    p {
      color: #555;
    }

    .footer {
      margin-top: 20px;
      padding-top: 10px;
      border-top: 1px solid #ddd;
      color: #888;
      font-size: 12px;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>Xác nhận hủy đơn hàng</h1>
  <p>
    Chúng tôi nhận được yêu cầu huỷ đơn hàng từ bạn.
  </p>
  <p>Thông tin đơn hàng của bạn:</p>
  <ul>
    <li><strong>Mã đơn hàng:</strong> <span th:text="${orderId}"></span></li>
  </ul>
  <p>Vui lòng xác nhận huỷ đơn hàng <a th:href="${confirm_cancel_order_link}">tại đây</a>
  <p>
    Nếu có bất kỳ câu hỏi hoặc cần thêm hỗ trợ, vui lòng liên hệ với chúng
    tôi qua địa chỉ email: chamsockhachhangaims@gmail.com
  </p>
  <p>Xin cảm ơn bạn đã chọn sản phẩm của chúng tôi!</p>
  <div class="footer">
    <p>Trân trọng,<br />AIMS</p>
  </div>
</div>
</body>
</html>', '["orderId", "confirm_cancel_order_link"]');

INSERT INTO template (title, content, required_parameters)
VALUES ('Hủy đơn hàng thành công', '<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Hủy đơn hàng thành công</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      line-height: 1.6;
      margin: 0;
      padding: 20px;
      background-color: #f4f4f4;
    }

    .container {
      max-width: 600px;
      margin: 0 auto;
      background-color: #ffffff;
      padding: 20px;
      border-radius: 5px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    h1 {
      color: #333;
    }

    p {
      color: #555;
    }

    .footer {
      margin-top: 20px;
      padding-top: 10px;
      border-top: 1px solid #ddd;
      color: #888;
      font-size: 12px;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>Hủy đơn hàng thành công</h1>
  <p>
    Chúng tôi xin thông báo rằng đơn hàng của bạn đã được hủy thành công.
  </p>
  <p>Tiền sẽ được hoàn trả vào tài khoản của bạn chậm nhất sau 3 ngày làm việc.</p>
  <p>Thông tin đơn hàng đã hủy:</p>
  <ul>
    <li><strong>Mã đơn hàng:</strong> <span th:text="${orderId}"></span></li>
  </ul>
  <p>
    Nếu có bất kỳ câu hỏi hoặc cần thêm hỗ trợ, vui lòng liên hệ với chúng
    tôi qua địa chỉ email: chamsockhachhangaims@gmail.com
  </p>
  <p>Xin cảm ơn bạn đã chọn sản phẩm của chúng tôi!</p>
  <div class="footer">
    <p>Trân trọng,<br />AIMS</p>
  </div>
</div>
</body>
</html>', '["orderId"]');

INSERT INTO template (title, content, required_parameters)
VALUES ('Đơn hàng đã được duyệt', '<!DOCTYPE html>
<html lang="vi" xmlns:th="http://www.thymeleaf.org">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Đơn hàng đã được duyệt</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      line-height: 1.6;
      margin: 0;
      padding: 20px;
      background-color: #f4f4f4;
    }

    .container {
      max-width: 600px;
      margin: 0 auto;
      background-color: #ffffff;
      padding: 20px;
      border-radius: 5px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    h1 {
      color: #333;
    }

    p {
      color: #555;
    }

    .footer {
      margin-top: 20px;
      padding-top: 10px;
      border-top: 1px solid #ddd;
      color: #888;
      font-size: 12px;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>Đơn hàng đã được duyệt</h1>
  <p>
    Cảm ơn bạn đã đặt hàng từ chúng tôi.
  </p>
  <p>Thông tin đơn hàng của bạn:</p>
  <ul>
    <li><strong>Mã đơn hàng:</strong> <span th:text="${orderId}"></span></li>
  </ul>
  <p>
    Để theo dõi tình trạng đơn hàng của bạn, vui lòng theo dõi <a th:href="${trace_order_link}">tại đây</a>
  </p>
  <p>Cảm ơn bạn đã mua sắm tại cửa hàng chúng tôi!</p>
  <div class="footer">
    <p>Trân trọng,<br />AIMS</p>
  </div>
</div>
</body>
</html>
', '["orderId", "trace_order_link"]');

INSERT INTO template (title, content, required_parameters)
VALUES ('Đơn hàng đã bị từ chối', '<!DOCTYPE html>
<html lang="vi" xmlns:th="http://www.thymeleaf.org">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Đơn hàng đã bị từ chối</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      line-height: 1.6;
      margin: 0;
      padding: 20px;
      background-color: #f4f4f4;
    }

    .container {
      max-width: 600px;
      margin: 0 auto;
      background-color: #ffffff;
      padding: 20px;
      border-radius: 5px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    h1 {
      color: #333;
    }

    p {
      color: #555;
    }

    .footer {
      margin-top: 20px;
      padding-top: 10px;
      border-top: 1px solid #ddd;
      color: #888;
      font-size: 12px;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>Đơn hàng bị từ chối</h1>
  <p>
    Rất tiếc nhưng đơn hàng của bạn đã bị từ chối.
  </p>
  <p>Cảm ơn bạn đã mua sắm tại cửa hàng chúng tôi!</p>
  <div class="footer">
    <p>Trân trọng,<br />AIMS</p>
  </div>
</div>
</body>
</html>
', '[""]');

INSERT INTO template (title, content, required_parameters)
VALUES ('Trạng thái tài khoản', '<!DOCTYPE html>
<html lang="vi" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title th:text="${blockedState}"></title>
    <style>
        body {
          font-family: Arial, sans-serif;
          line-height: 1.6;
          margin: 0;
          padding: 20px;
          background-color: #f4f4f4;
        }

        .container {
          max-width: 600px;
          margin: 0 auto;
          background-color: #ffffff;
          padding: 20px;
          border-radius: 5px;
          box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
          color: #333;
        }

        p {
          color: #555;
        }

        .footer {
          margin-top: 20px;
          padding-top: 10px;
          border-top: 1px solid #ddd;
          color: #888;
          font-size: 12px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Tài khoản của bạn đã <span th:text="${blockedState}"></span></h1>
    <p>
        Một quản trị viên đã <span th:text="${action}"></span> tài khoản của bạn.
    </p>
    <p>
        Để biết thêm chi tiết về hành động này, vui lòng liên hệ với chúng tôi bằng cách trả lời email này.
    </p>
    <p>Cảm ơn bạn đã sử dụng nền tảng bán hàng của chúng tôi!</p>
    <div class="footer">
        <p>Trân trọng,<br />AIMS</p>
    </div>
</div>
</body>
</html>
', '["blockedState", "action"]');

INSERT INTO template (title, content, required_parameters)
VALUES ('Tạo người dùng', '<!DOCTYPE html>
<html lang="vi" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Tài khoản aims đã được tạo</title>
    <style>
        body {
          font-family: Arial, sans-serif;
          line-height: 1.6;
          margin: 0;
          padding: 20px;
          background-color: #f4f4f4;
        }

        .container {
          max-width: 600px;
          margin: 0 auto;
          background-color: #ffffff;
          padding: 20px;
          border-radius: 5px;
          box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
          color: #333;
        }

        p {
          color: #555;
        }

        .footer {
          margin-top: 20px;
          padding-top: 10px;
          border-top: 1px solid #ddd;
          color: #888;
          font-size: 12px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Bạn đã được cấp tài khoản sử dụng hệ thống AIMS</h1>
    <p>Bạn có thể đăng nhập sử dụng địa chỉ email này và mật khẩu sau đây:</p>
    <p>Mật khẩu mới: <span th:text="${newPassword}"></span></p>
    <p>Nếu bạn có thắc mắc về sự kiện này, vui lòng liên hệ với chúng tôi bằng cách trả lời email này.</p>
    <p>Cảm ơn bạn đã sử dụng nền tảng bán hàng của chúng tôi!</p>
    <div class="footer">
        <p>Trân trọng,<br />AIMS</p>
    </div>
</div>
</body>
</html>
', '["newPassword"]');

INSERT INTO template (title, content, required_parameters)
VALUES ('Đổi mật khẩu', '<!DOCTYPE html>
<html lang="vi" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Mật khẩu của bạn đã được thay đổi</title>
    <style>
        body {
          font-family: Arial, sans-serif;
          line-height: 1.6;
          margin: 0;
          padding: 20px;
          background-color: #f4f4f4;
        }

        .container {
          max-width: 600px;
          margin: 0 auto;
          background-color: #ffffff;
          padding: 20px;
          border-radius: 5px;
          box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
          color: #333;
        }

        p {
          color: #555;
        }

        .footer {
          margin-top: 20px;
          padding-top: 10px;
          border-top: 1px solid #ddd;
          color: #888;
          font-size: 12px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Mật khẩu của bạn đã được thay đổi</h1>
    <p>Một quản trị viên đã thay đổi mật khẩu tài khoản của bạn.</p>
    <p>Mật khẩu mới: <span th:text="${newPassword}"></span></p>
    <p>Nếu bạn có thắc mắc về sự thay đổi này, vui lòng liên hệ với chúng tôi bằng cách trả lời email này.</p>
    <p>Cảm ơn bạn đã sử dụng nền tảng bán hàng của chúng tôi!</p>
    <div class="footer">
        <p>Trân trọng,<br />AIMS</p>
    </div>
</div>
</body>
</html>
', '["newPassword"]');

INSERT INTO template (title, content, required_parameters)
VALUES ('Cập nhật thông tin', '<!DOCTYPE html>
<html lang="vi" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Thông tin người dùng đã được thay đổi</title>
    <style>
        body {
          font-family: Arial, sans-serif;
          line-height: 1.6;
          margin: 0;
          padding: 20px;
          background-color: #f4f4f4;
        }

        .container {
          max-width: 600px;
          margin: 0 auto;
          background-color: #ffffff;
          padding: 20px;
          border-radius: 5px;
          box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
          color: #333;
        }

        p {
          color: #555;
        }

        .footer {
          margin-top: 20px;
          padding-top: 10px;
          border-top: 1px solid #ddd;
          color: #888;
          font-size: 12px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Thông tin người dùng đã được thay đổi</h1>
    <p>Một quản trị viên đã thay đổi thông tin tài khoản của bạn.</p>
    <p>Nếu bạn có thắc mắc về sự thay đổi này, vui lòng liên hệ với chúng tôi bằng cách trả lời email này.</p>
    <p>Cảm ơn bạn đã sử dụng nền tảng bán hàng của chúng tôi!</p>
    <div class="footer">
        <p>Trân trọng,<br />AIMS</p>
    </div>
</div>
</body>
</html>
', '[""]');