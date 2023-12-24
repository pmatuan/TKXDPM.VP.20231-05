interface Props {
  imageUrl: string;
  title: string;
  category: string;
  price: number;
  quantityInStock: number;
  quantity: number;
  onChangeQuantity: (quantity: number) => void;
}

export default function CartItem({
  imageUrl,
  title,
  category,
  price,
  quantityInStock,
  quantity,
  onChangeQuantity,
}: Props) {
  return (
    <>
      <div className="d-block d-md-flex">
        <img
          className="w-50 w-md-25 rounded-3"
          src={
            imageUrl.includes("http")
              ? `${imageUrl}`
              : `${import.meta.env.BASE_URL}${imageUrl}`
          }
        />

        <div className="w-100 w-md-35 ps-md-4">
          <h6 className="text-lg mb-1">{title}</h6>
          <div className="d-flex">
            <p className="pe-3 mb-0">{category}</p>
          </div>
          <div className="d-flex align-items-center mt-6">
            {quantityInStock >= quantity ? (
              <>
                <i className="fas fa-check text-lg text-success"></i>
                <p className="mb-0 ms-2 text-sm">Còn hàng</p>
              </>
            ) : (
              <>
                <i className="fas fa-minus-circle text-lg"></i>
                <p className="mb-0 ms-2 text-sm">Thiếu {quantity - quantityInStock} sản phẩm</p>
              </>
            )}
          </div>
        </div>
        <div className="w-20 w-md-10 mt-4 mt-md-0">
          <input
            type="number"
            min={1}
            className="form-control"
            value={String(quantity)}
            aria-label="amount"
            onChange={(e) => onChangeQuantity(Number(e.target.value))}
          />
        </div>
        <h4 className="ms-4 text-base">{price.toLocaleString()} đồng</h4>

        <div className="w-10 text-end">
          <a onClick={() => onChangeQuantity(0)}>
            <i className="fas fa-times ms-3 cursor-pointer"></i>
          </a>
        </div>
      </div>
    </>
  );
}
