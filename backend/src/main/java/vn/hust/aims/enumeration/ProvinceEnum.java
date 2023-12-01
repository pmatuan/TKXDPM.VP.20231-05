package vn.hust.aims.enumeration;

public enum ProvinceEnum {
  HANOI(1, "Hà Nội"),
  HOCHIMINH(2, "Hồ Chí Minh"),
  BACGIANG(3, "Bắc Giang"),
  BACKAN(4, "Bắc Kạn"),
  CAOBANG(5, "Cao Bằng"),
  HAGIANG(6, "Hà Giang"),
  LANGSON(7, "Lạng Sơn"),
  PHUTHO(8, "Phú Thọ"),
  QUANGNINH(9, "Quảng Ninh"),
  THAINGUYEN(10, "Thái Nguyên"),
  TUYENQUANG(11, "Tuyên Quang"),
  YENBAI(12, "Yên Bái"),
  DIENBIEN(13, "Điện Biên"),
  HOABINH(14, "Hòa Bình"),
  LAICHAU(15, "Lai Châu"),
  SONLA(16, "Sơn La"),
  BACNINH(17, "Bắc Ninh"),
  HANAM(18, "Hà Nam"),
  HAIDUONG(19, "Hải Dương"),
  HUNGYEN(20, "Hưng Yên"),
  NAMDINH(21, "Nam Định"),
  NINHBINH(22, "Ninh Bình"),
  THAIBINH(23, "Thái Bình"),
  VINHPHUC(24, "Vĩnh Phúc"),
  HAIPHONG(25, "Hải Phòng"),
  HATINH(26, "Hà Tĩnh"),
  NGHEAN(27, "Nghệ An"),
  QUANGBINH(28, "Quảng Bình"),
  QUANGTRI(29, "Quảng Trị"),
  THANHHOA(30, "Thanh Hóa"),
  THUATHIENHUE(31, "Thừa Thiên-Huế"),
  DAKLAK(32, "Đắk Lắk"),
  DAKNONG(33, "Đắk Nông"),
  GIALAI(34, "Gia Lai"),
  KONTUM(35, "Kon Tum"),
  LAMDONG(36, "Lâm Đồng"),
  BINHDINH(37, "Bình Định"),
  BINHTHUAN(38, "Bình Thuận"),
  KHANHHOA(39, "Khánh Hòa"),
  NINHTHUAN(40, "Ninh Thuận"),
  PHUYEN(41, "Phú Yên"),
  QUANGNAM(42, "Quảng Nam"),
  QUANGNGAI(43, "Quảng Ngãi"),
  DANANG(44, "Đà Nẵng"),
  BARIAVUNGTAU(45, "Bà Rịa-Vũng Tàu"),
  BINHDUONG(46, "Bình Dương"),
  BINHPHUOC(47, "Bình Phước"),
  DONGNAI(48, "Đồng Nai"),
  TAYNINH(49, "Tây Ninh"),
  ANGIANG(50, "An Giang"),
  BACLIEU(51, "Bạc Liêu"),
  BENTRE(52, "Bến Tre"),
  CAMAU(53, "Cà Mau"),
  DONGTHAP(54, "Đồng Tháp"),
  HAUGIANG(55, "Hậu Giang"),
  KIENGIANG(56, "Kiên Giang"),
  LONGAN(57, "Long An"),
  SOCTRANG(58, "Sóc Trăng"),
  TIENGIANG(59, "Tiền Giang"),
  TRAVINH(60, "Trà Vinh"),
  VINHLONG(61, "Vĩnh Long"),
  CANTHO(62, "Cần Thơ"),
  UNKNOWN(0, "UNKNOWN");

  private final int intValue;
  private final String stringValue;

  ProvinceEnum(int intValue, String stringValue) {
    this.intValue = intValue;
    this.stringValue = stringValue;
  }

  public static ProvinceEnum fromInt(int value) {
    for (ProvinceEnum province : ProvinceEnum.values()) {
      if (province.intValue == value) {
        return province;
      }
    }
    return UNKNOWN;
  }

  public static ProvinceEnum fromString(String value) {
    for (ProvinceEnum province : ProvinceEnum.values()) {
      if (province.stringValue.equals(value)) {
        return province;
      }
    }
    return UNKNOWN;
  }

  public int getIntValue() {
    return intValue;
  }

  public String getStringValue() {
    return stringValue;
  }
}
