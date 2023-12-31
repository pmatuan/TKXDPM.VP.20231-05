import React, { useState } from "react";
import { AiOutlineCloseSquare } from "react-icons/ai";
import UpdateBook from "./UpdateBook";
import UpdateCD from "./UpdateCD";
import UpdateDVD from "./UpdateDVD";
import UpdateLP from "./UpdateLP";
const UpdateMedia = ({ setUpdateMedia, mediaItem }) => {
  const [updateStatus, setUpdateStatus] = useState("Change the information");
  return (
    <div
      onClick={() => setUpdateMedia(false)}
      style={{
        position: "fixed",
        background: "rgba(255, 255, 255, 0.5)",
        top: 0,
        left: 0,
        right: 0,
        bottom: 0,
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
      }}
    >
      {/* Content */}
      <div
        onClick={(e) => e.stopPropagation()}
        style={{
          position: "relative",
          background: "white",
          borderRadius: "8px",
          width: "60%", // Set the width to 60% of the viewport width
          height: "80%", // Set the height to 80% of the viewport height
          padding: "20px 10px",
          animation: "dropTop .3s linear",
          overflowY: "auto",
          maxWidth: "800px", // Set a maximum width
          minWidth: "400px", // Set a minimum width
          maxHeight: "80vh", // Set a maximum height
          minHeight: "200px", // Set a minimum height
        }}
      >
        {/* Header */}
        <div
          style={{ borderBottom: "1px solid lightgray", paddingBottom: "10px" }}
        >
          <h1 style={{ margin: 0 }}>Update</h1>
          <div
            onClick={() => setUpdateMedia(false)}
            style={{
              cursor: "pointer",
              position: "absolute",
              top: 10,
              right: 10,
            }}
          >
            <AiOutlineCloseSquare />
          </div>
        </div>
        {/* Body */}
        <div>{updateStatus}</div>
        <div>
          {mediaItem.category === "book" && (
            <UpdateBook
              initialValuesProps={mediaItem}
              setUpdateStatus={setUpdateStatus}
            />
          )}
          {mediaItem.category == "cd" && (
            <UpdateCD
              initialValuesProps={mediaItem}
              setUpdateStatus={setUpdateStatus}
            />
          )}
          {mediaItem.category == "dvd" && (
            <UpdateDVD
              initialValuesProps={mediaItem}
              setUpdateStatus={setUpdateStatus}
            />
          )}
          {mediaItem.category == "lp" && (
            <UpdateLP
              initialValuesProps={mediaItem}
              setUpdateStatus={setUpdateStatus}
            />
          )}
          <p>{/* Long content here */}</p>
        </div>
      </div>
    </div>
  );
};

export default UpdateMedia;
