const Media = ({ props }) => {
  const { title, price, quantity, photo, category } = props;

  return (
    <div
      className="media"
      style={{
        border: "1px solid #ccc",
        padding: "10px",
        margin: "10px",
        display: "inline-block",
        borderWidth: "3px",
        borderRadius: "10px",
      }}
    >
      <img
        src={photo}
        alt={title}
        className="mr-3"
        style={{ width: "100px", height: "100px", objectFit: "cover" }}
      />
      <div className="media-body">
        <h6>Title: {title}</h6>
        <div>Price: ${price}</div>
        <div>Quantity: {quantity}</div>
        <div>Category: {category}</div>
        <button
          className="btn btn-primary"
          style={{ backgroundColor: "#D3E0EA", color: "black" }}
        >
          View Detail
        </button>
        <button
          className="btn btn-danger ml-2"
          style={{ backgroundColor: "#FFECDB", color: "black" }}
        >
          Delete
        </button>
      </div>
    </div>
  );
};

export default Media;
