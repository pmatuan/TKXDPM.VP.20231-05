import React, { useState } from "react";
import { AiOutlineCloseSquare } from "react-icons/ai";
const ViewDetailMedia = ({ setViewDetail, mediaView }) => {
  return (
    <div
      onClick={() => setViewDetail(false)}
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
        zIndex: 1000,
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
          <h3 style={{ margin: 0 }}>Xem chi tiết</h3>
          <div
            onClick={() => setViewDetail(false)}
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
        <div>
          {mediaView.category == "book" && (
            <div style={{ display: "flex" }}>
              <div style={{ flex: 1 }}>
                <img
                  src={`${mediaView.imageUrl}`}
                  alt={mediaView.title}
                  className="w-100"
                />
              </div>

              <div style={{ flex: 1, marginLeft: "20px" }}>
                <p>Tên sách: {mediaView.title}</p>
                <p>Tác giả: {mediaView.authors}</p>
                <p>Loại bìa: {mediaView.coverType}</p>
                <p>Loại sách: {mediaView.type}</p>
                <p>Loại hình: {mediaView.category}</p>
                <p>Nhà xuất bản: {mediaView.publisher}</p>
                <p>Ngày xuất bản: {mediaView.publicationDate}</p>
                <p>Ngôn ngữ: {mediaView.language}</p>
                <p>Số trang: {mediaView.pages}</p>
                <p>
                  Hỗ trợ giao hàng nhanh:{" "}
                  {mediaView.isAbleToRushDelivery ? "Có" : "Không"}
                </p>
                <p>Giá: {mediaView.price} đ</p>
              </div>
            </div>
          )}

          {mediaView.category == "cd" && (
            <div style={{ display: "flex" }}>
              <div style={{ flex: 1 }}>
                <img
                  src={`http://127.0.0.1:8080/api/v1/media/images/${mediaView.imageUrl}`}
                  alt={mediaView.title}
                  className="w-100"
                />
              </div>

              <div style={{ flex: 1, marginLeft: "20px" }}>
                <p>Tên CD: {mediaView.title}</p>
                <p>Nghệ sĩ: {mediaView.artists}</p>
                <p>Hãng thu âm: {mediaView.recordLabel}</p>
                <p>Loại hình: {mediaView.category}</p>
                <p>Tracklist: {mediaView.trackList}</p>
                <p>Ngày ra mắt: {mediaView.releaseDate}</p>
                <p>Thể loại: {mediaView.genres}</p>

                <p>
                  Hỗ trợ giao hàng nhanh:{" "}
                  {mediaView.isAbleToRushDelivery ? "Có" : "Không"}
                </p>
                <p>Giá: {mediaView.price} đ</p>
              </div>
            </div>
          )}

          {mediaView.category == "dvd" && (
            <div style={{ display: "flex" }}>
              <div style={{ flex: 1 }}>
                <img
                  src={`http://127.0.0.1:8080/api/v1/media/images/${mediaView.imageUrl}`}
                  alt={mediaView.title}
                  className="w-100"
                />
              </div>

              <div style={{ flex: 1, marginLeft: "20px" }}>
                <p>Tên DVD: {mediaView.title}</p>
                <p>Đạo diễn: {mediaView.director}</p>
                <p>Loại DVD: {mediaView.discFormat}</p>
                <p>Loại hình: {mediaView.category}</p>
                <p>Runtime: {mediaView.runTime}</p>
                <p>Ngày xuất bản: {mediaView.publicationDate}</p>
                <p>Ngôn ngữ: {mediaView.language}</p>
                <p>Hãng: {mediaView.studio}</p>
                <p>Phụ đề: {mediaView.subtitles}</p>
                <p>Thể loại: {mediaView.genres}</p>
                <p>Ngày ra mắt: {mediaView.releaseDate}</p>
                <p>
                  Hỗ trợ giao hàng nhanh:{" "}
                  {mediaView.isAbleToRushDelivery ? "Có" : "Không"}
                </p>
                <p>Giá: {mediaView.price}đ</p>
              </div>
            </div>
          )}

          {mediaView.category == "lp" && (
            <div style={{ display: "flex" }}>
              <div style={{ flex: 1 }}>
                <img
                  src={`http://127.0.0.1:8080/api/v1/media/images/${mediaView.imageUrl}`}
                  alt={mediaView.title}
                  className="w-100"
                />
              </div>

              <div style={{ flex: 1, marginLeft: "20px" }}>
                <p>Tên LP: {mediaView.title}</p>
                <p>Nghệ sĩ: {mediaView.artists}</p>
                <p>Hãng thu âm: {mediaView.recordLabel}</p>
                <p>Loại hình: {mediaView.category}</p>
                <p>Tracklist: {mediaView.trackList}</p>
                <p>Ngày ra mắt: {mediaView.releaseDate}</p>
                <p>Thể loại: {mediaView.genres}</p>

                <p>
                  Hỗ trợ giao hàng nhanh:{" "}
                  {mediaView.isAbleToRushDelivery ? "Có" : "Không"}
                </p>
                <p>Giá: {mediaView.price}đ</p>
              </div>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default ViewDetailMedia;
