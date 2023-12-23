interface Props {
  imageUrl: string;
  title: string;
  price: number;
  quantity: number;
  onRemove: () => void;
}

export default function CheckoutSingleItem({
  imageUrl,
  title,
  price,
  quantity,
  onRemove,
}: Props) {
  return (
    <>
      <div className="card card-product card-plain d-flex mb-4">
        <div className="row">
          <div className="col-4 col-md-2">
            <img
              className="w-100 max-height-100 rounded-3"
              src={`${import.meta.env.BASE_URL}${imageUrl}`}
            />
          </div>
          <div className="col-5 col-md-6">
            <h5 className="text-base mb-1">{title}</h5>
            <h6 className="text-sm font-weight-bold mb-0">
              {price.toLocaleString()} đồng
            </h6>
          </div>
          <div className="col-2">
            <input
              type="number"
              min={1}
              className="form-control"
              placeholder={String(quantity)}
              aria-label="amount"
              onChange={(e) => onChangeQuantity(Number(e.target.value))}
            />
          </div>
          <div className="col-2">
            <a onClick={onRemove}>
              <i className="fas fa-times ms-3 cursor-pointer"></i>
            </a>
          </div>
        </div>
      </div>
    </>
  );
}
