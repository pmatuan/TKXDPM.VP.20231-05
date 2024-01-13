import { useState } from "react";

interface Props {
  setOrderId: (value: string) => void;
}

export default function OrderSearchBox({ setOrderId }: Props) {
  const [inputValue, setInputValue] = useState<string>("");

  const handleSearch = () => {
    // Gọi hàm setOrderId với giá trị của input
    setOrderId(inputValue);
  };

  return (
      <div className="row">
        <div className="col-10">
          <div className="form-group">
            <input
                type="text"
                className="form-control"
                placeholder="Nhập mã đơn hàng của bạn"
                value={inputValue}
                onChange={(e) => setInputValue(e.target.value)}
            />
          </div>
        </div>
        <div className="col-2">
          <button className="btn btn-dark" onClick={handleSearch}>
            Tìm kiếm
          </button>
        </div>
      </div>
  );
}
