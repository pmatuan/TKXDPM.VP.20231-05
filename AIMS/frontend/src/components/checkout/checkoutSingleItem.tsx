interface Props {
  imageUrl: string;
  title: string;
  color: string;
  size: string;
  price: number;
}

export default function CheckoutSingleItem({
  imageUrl,
  title,
  color,
  size,
  price,
}: Props) {
  return (
    <>
      <div className="d-flex mb-4">
        <img
          className="w-20 rounded-3"
          src={`${import.meta.env.BASE_URL}${imageUrl}`}
        />
        <div className="w-60 w-md-70 pt-2 ps-3 ps-md-4">
          <h6 className="text-lg text-white mb-1">{title}</h6>
          <p className="mb-0 text-white opacity-8">{color}</p>
          <p className="mb-0 text-sm text-white opacity-8">{size}</p>
        </div>

        <div className="w-10 text-end">
          <p className="text-white mb-0 ">${price.toLocaleString()}</p>
        </div>
      </div>
    </>
  );
}
