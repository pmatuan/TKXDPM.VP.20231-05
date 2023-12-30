import { set } from "date-fns";
import React, { useState } from "react";

const Media = ({
  props,
  onDelete,
  setUpdateMedia,
  setMediaUpdateId,
  setDeleteIds,
  selectStatus,
}) => {
  const { id, title, price, quantity, photo, category } = props;
  const [isChecked, setIsChecked] = useState(false);

  function handleOnClickDelete() {
    async function deleteMediaData(ids) {
      try {
        const apiUrl = "http://127.0.0.1:8080/api/v1/media";

        const requestOptions = {
          method: "DELETE",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({ ids }),
        };

        const response = await fetch(apiUrl, requestOptions);

        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const responseData = await response.json();
        console.log("Delete response:", responseData);

        onDelete(id);

        return responseData;
      } catch (error) {
        console.error("Error deleting media:", error.message);
      }
    }

    const ids = [id];
    deleteMediaData(ids);
  }

  return (
    <div
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
        src={`http://127.0.0.1:8080/api/v1/media/images/${photo}`}
        alt={title}
        // className="mr-3"
        style={{
          width: "100px",
          height: "100px",
          objectFit: "cover",
          display: "block",
          margin: "auto",
        }}
      />
      <div className="media-body">
        <h5 style={{ textAlign: "center", lineHeight: "2.5" }}>{title}</h5>
        <p style={{ paddingLeft: "20px" }}>
          <div>Giá: {price}đ</div>
          <div>Số lượng: {quantity}</div>
          <div>Loại: {category}</div>
        </p>

        <div style={{ display: "flex", gap: "5px", alignItems: "center" }}>
          <button
            className="btn btn-primary"
            style={{
              backgroundColor: "#D3E0EA",
              color: "black",
            }}
            onClick={() => {
              setUpdateMedia(true);
              setMediaUpdateId(id);
            }}
          >
            Cập nhật
          </button>
          <button
            className="btn btn-danger ml-2"
            style={{ backgroundColor: "#FFECDB", color: "black" }}
            onClick={() => {
              handleOnClickDelete();
            }}
          >
            Xóa
          </button>
          {selectStatus && (
            <input
              type="checkbox"
              checked={isChecked}
              onChange={() => {
                setIsChecked(!isChecked);
                if (isChecked) {
                  setDeleteIds((prevIds) =>
                    prevIds.filter((prevId) => prevId !== id)
                  );
                } else {
                  setDeleteIds((prevIds) => [...prevIds, id]);
                }
              }}
              style={{
                verticalAlign: "middle",

                width: "20px", // Độ rộng của checkbox
                height: "20px", // Độ cao của checkbox
              }}
            />
          )}
        </div>
      </div>
    </div>
  );
};

export default Media;
