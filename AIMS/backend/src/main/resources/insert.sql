drop database aims;

use aims;

show tables;

select * from cart;
select * from cart_media;
select * from media;
select * from `order`;
select * from order_media;
select * from rush_order;
select * from delivery_info;

INSERT INTO media(title, category, value, price, quantity_in_stock, is_able_to_rush_delivery, weight)
VALUES
    ("book1", "book", 120000, 69600.0, 17, False, 1.5),
    ("lp2", "lp", 116000, 117160.0, 25, False, 0.5),
    ("dvd3", "dvd", 261000, 242730.0, 20, False, 2.5),
    ("book4", "book", 35000, 23800.0, 4, True, 1.5),
    ("cd5", "cd", 72000, 31680.0, 18, False, 1.0),
    ("dvd6", "dvd", 97000, 42680.0, 3, False, 1.4),
    ("dvd7", "dvd", 257000, 172190.0, 9, True, 1.2),
    ("book8", "book", 99000, 65340.0, 24, True, 2.5),
    ("cd9", "cd", 249000, 171810.0, 5, False, 2.1),
    ("dvd10", "dvd", 149000, 62580.0, 11, False, 1.4),
    ("book11", "book", 153000, 185130.0, 3, True, 1.5),
    ("cd12", "cd", 158000, 105860.0, 6, False, 2.7),
    ("cd13", "cd", 85000, 98600.0, 2, False, 2.3),
    ("cd14", "cd", 30000, 22800.0, 16, True, 2.5),
    ("cd15", "cd", 41000, 33210.0, 3, False, 1.7),
    ("lp16", "lp", 129000, 95460.0, 21, False, 2.8),
    ("dvd17", "dvd", 50000, 43500.0, 6, False, 1.1),
    ("cd18", "cd", 235000, 239700.0, 8, True, 2.9),
    ("lp19", "lp", 273000, 390390.0, 6, False, 1.7),
    ("cd20", "cd", 269000, 139880.0, 6, False, 1.7),
    ("book21", "book", 260000, 218400.0, 1, False, 2.1),
    ("book22", "book", 198000, 295020.0, 26, False, 0.9),
    ("cd23", "cd", 286000, 348920.0, 16, True, 1.0),
    ("cd24", "cd", 58000, 34800.0, 16, True, 1.8),
    ("cd25", "cd", 216000, 213840.0, 18, False, 0.7),
    ("book26", "book", 51000, 57120.0, 1, True, 2.5),
    ("book27", "book", 264000, 176880.0, 0, True, 2.4),
    ("dvd28", "dvd", 78000, 94380.0, 8, False, 1.0),
    ("book29", "book", 224000, 199360.0, 23, False, 2.2),
    ("lp30", "lp", 38000, 42940.0, 5, False, 0.6),
    ("lp31", "lp", 129000, 39990.0, 4, True, 2.4),
    ("dvd32", "dvd", 208000, 201760.0, 12, False, 1.0),
    ("dvd33", "dvd", 194000, 283240.0, 0, True, 1.9),
    ("cd34", "cd", 235000, 256150.0, 13, False, 1.6),
    ("book35", "book", 117000, 169650.0, 11, True, 1.4),
    ("dvd36", "dvd", 93000, 88350.0, 25, True, 1.2),
    ("book37", "book", 197000, 263980.0, 27, True, 2.7),
    ("dvd38", "dvd", 38000, 20900.0, 15, True, 0.6),
    ("cd39", "cd", 193000, 204580.0, 19, True, 0.9),
    ("lp40", "lp", 33000, 38280.0, 13, True, 0.6),
    ("book41", "book", 89000, 126380.0, 28, True, 2.4),
    ("lp42", "lp", 174000, 151380.0, 12, False, 2.9),
    ("book43", "book", 271000, 181570.0, 24, True, 1.8),
    ("cd44", "cd", 125000, 51250.0, 28, True, 1.5),
    ("cd45", "cd", 146000, 156220.0, 11, True, 1.9),
    ("lp46", "lp", 146000, 68620.0, 16, False, 2.0),
    ("book47", "book", 88000, 26400.0, 23, False, 0.7),
    ("cd48", "cd", 97000, 106700.0, 3, False, 1.2),
    ("cd49", "cd", 86000, 67940.0, 26, False, 1.3),
    ("cd50", "cd", 162000, 153900.0, 16, False, 0.7),
    ("dvd51", "dvd", 90000, 104400.0, 26, False, 0.5),
    ("book52", "book", 217000, 232190.0, 15, True, 1.7),
    ("cd53", "cd", 49000, 16660.0, 19, True, 2.8),
    ("cd54", "cd", 98000, 136220.0, 1, True, 1.1),
    ("dvd55", "dvd", 123000, 87330.0, 17, True, 2.2),
    ("book56", "book", 182000, 87360.0, 7, True, 1.8),
    ("book57", "book", 126000, 54180.0, 13, False, 1.4),
    ("lp58", "lp", 295000, 424800.0, 1, False, 1.2),
    ("lp59", "lp", 250000, 220000.0, 3, True, 2.5),
    ("dvd60", "dvd", 50000, 67000.0, 3, True, 1.4),
    ("book61", "book", 89000, 132610.0, 19, False, 0.6),
    ("dvd62", "dvd", 184000, 233680.0, 15, False, 1.9),
    ("dvd63", "dvd", 94000, 81780.0, 7, True, 2.8),
    ("lp64", "lp", 290000, 275500.0, 19, True, 2.4),
    ("lp65", "lp", 79000, 97960.0, 4, False, 2.6),
    ("lp66", "lp", 71000, 57510.0, 14, False, 1.6),
    ("lp67", "lp", 198000, 223740.0, 21, True, 1.4),
    ("cd68", "cd", 244000, 309880.0, 5, True, 2.4),
    ("dvd69", "dvd", 134000, 116580.0, 12, False, 0.6),
    ("book70", "book", 75000, 51750.0, 23, True, 1.4),
    ("cd71", "cd", 133000, 146300.0, 20, True, 0.9),
    ("lp72", "lp", 272000, 111520.0, 9, False, 0.8),
    ("lp73", "lp", 296000, 124320.0, 22, True, 1.2),
    ("cd74", "cd", 69000, 58650.0, 23, True, 0.9),
    ("lp75", "lp", 133000, 75810.0, 10, True, 1.9),
    ("book76", "book", 200000, 174000.0, 18, True, 1.5),
    ("dvd77", "dvd", 72000, 88560.0, 25, False, 1.8),
    ("cd78", "cd", 65000, 84500.0, 0, False, 2.1),
    ("lp79", "lp", 153000, 133110.0, 10, True, 0.8),
    ("cd80", "cd", 290000, 353800.0, 23, False, 1.6),
    ("book81", "book", 259000, 352240.0, 27, True, 2.7),
    ("cd82", "cd", 233000, 342510.0, 29, True, 2.1),
    ("lp83", "lp", 251000, 128010.0, 3, True, 1.0),
    ("lp84", "lp", 247000, 86450.0, 5, False, 1.2),
    ("lp85", "lp", 176000, 260480.0, 4, True, 1.6),
    ("dvd86", "dvd", 280000, 288400.0, 9, True, 2.0),
    ("book87", "book", 103000, 131840.0, 18, False, 0.9),
    ("cd88", "cd", 280000, 411600.0, 21, False, 1.8),
    ("lp89", "lp", 129000, 64500.0, 10, False, 1.3),
    ("book90", "book", 186000, 93000.0, 14, True, 1.1),
    ("cd91", "cd", 158000, 74260.0, 26, True, 1.9),
    ("cd92", "cd", 170000, 249900.0, 16, True, 1.1),
    ("lp93", "lp", 82000, 86920.0, 18, True, 1.2),
    ("dvd94", "dvd", 187000, 134640.0, 17, False, 1.2),
    ("dvd95", "dvd", 121000, 141570.0, 17, False, 0.6),
    ("cd96", "cd", 196000, 88200.0, 2, False, 2.3),
    ("lp97", "lp", 193000, 210370.0, 27, False, 1.2),
    ("dvd98", "dvd", 292000, 268640.0, 26, False, 1.7),
    ("lp99", "lp", 152000, 177840.0, 7, False, 1.4),
    ("cd100", "cd", 82000, 68060.0, 20, True, 0.8);

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
VALUES ('Test', '<!DOCTYPE html>
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
    <title th:text="Tài khoản của bạn đã ${blockedState}"></title>
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
        Để biết thêm chi tiết về hành động này, vui lòng liên hệ với chúng tôi bằng cách trả lời email này.,
    </p>
    <p>Cảm ơn bạn đã sử dụng nền tảng bán hàng của chúng tôi!</p>
    <div class="footer">
        <p>Trân trọng,<br />AIMS</p>
    </div>
</div>
</body>
</html>
', '["blockedState", "action"]');