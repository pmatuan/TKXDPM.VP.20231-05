interface Props {
  image_url: string;
  title: string;
  category: string;
  price: number;
  stock: boolean;
  quantity: number;
  onRemove: () => void;
  onChangeQuantity: (quantity: number) => void;
}

export default function CartItem({
  image_url,
  title,
  category,
  price,
  stock,
  quantity,
  onRemove,
  onChangeQuantity,
}: Props) {
  return (
    <>
      <div className="d-block d-md-flex">
        <img
            className="w-50 w-md-25 rounded-3"
            src={image_url.includes("http") ? `${image_url}` : `${import.meta.env.BASE_URL}${image_url}`}
        />

        <div className="w-100 w-md-35 ps-md-4">
          <h6 className="text-lg mb-1">{title}</h6>
          <div className="d-flex">
            <p className="pe-3 mb-0">{category}</p>
          </div>
          <div className="d-flex align-items-center mt-6">
            {stock ? (
              <>
                <i className="fas fa-check text-lg text-success"></i>
                <p className="mb-0 ms-2 text-sm">Còn hàng</p>
              </>
            ) : (
              <>
                <i className="fas fa-minus-circle text-lg"></i>
                <p className="mb-0 ms-2 text-sm">Hết hàng</p>
              </>
            )}
          </div>
        </div>
        <div className="w-20 w-md-10 mt-4 mt-md-0">
          <input
            type="number"
            min={1}
            className="form-control"
            placeholder={String(quantity)}
            aria-label="amount"
            onChange={(e) => onChangeQuantity(Number(e.target.value))}
          />
        </div>
        <h4 className="ms-4 text-base">{price.toLocaleString()} đồng</h4>

        <div className="w-10 text-end">
          <a onClick={onRemove}>
            <i className="fas fa-times ms-3 cursor-pointer"></i>
          </a>
        </div>
      </div>
    </>
  );
}
