import Header from "./Header.jsx";
import Media from "../media/Media.jsx";
import Pagination from "../media/pagination/Pagination.jsx";
import React, { useState, useEffect, useRef } from "react";
import queryString from "query-string";
import MediaFiltersForm from "../media/MediaFiltersForm.jsx";
import BookForm from "../media/form/BookForm.jsx";

export default function HomeAdmin() {
  const [isMenuVisible, setMenuVisible] = useState(false);

  const handleMenuVisible = () => {
    setMenuVisible(!isMenuVisible);
  };
  const redirectTo = (url) => {
    window.location.href = url;
  };

  const [media, setMedia] = useState([]);
  const [pagination, setPagination] = useState({
    page: 0,
    size: 10,
    total: 1,
  });

  const [filter, setFilter] = useState({
    page: 0,
    size: 10,
  });

  function handlePageChange(newPage) {
    console.log("New page: ", newPage);
    setFilter({
      ...filter,
      page: newPage,
    });
  }

  useEffect(() => {
    async function fetchMedia() {
      try {
        const paramsString = queryString.stringify(filter);
        const requestUrl = `http://127.0.0.1:8080/api/v1/media?${paramsString}`;
        const response = await fetch(requestUrl);
        const responseJSON = await response.json();
        console.log({ responseJSON });

        const data = responseJSON.result.mediaPage.content;
        const page = responseJSON.result.mediaPage.pageable.pageNumber;
        const size = responseJSON.result.mediaPage.pageable.pageSize;
        const totalElements = responseJSON.result.mediaPage.totalElements;
        const pagination = {
          page: page,
          size: size,
          total: totalElements,
        };

        setMedia(data);
        setPagination(pagination);
      } catch (error) {
        console.log("Failed to fetch media: ", error.message);
      }
    }

    console.log("Media list effect");
    fetchMedia();
  }, [filter]);

  function handleFiltersChange(newFilters) {
    console.log("New filters: ", newFilters);
    setFilter({
      ...filter,
      page: 0,
      title: newFilters.searchTerm,
    });
  }
  return (
    <>
      <Header />
      <div
        style={{
          display: "flex",
          flexDirection: "column",
          justifyContent: "center",
          alignItems: "center",
        }}
      >
        <div
          style={{
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            padding: "20px 20px 20px 20px",
          }}
        >
          <MediaFiltersForm onSubmit={handleFiltersChange} />
          {/* <button
            style={{ "margin-left": "30px" }}
            onClick={() => {
              window.location.href = "/aims-ecommerce/admin/create-media";
            }}
          >
            Create Media
          </button> */}
          <div style={{ position: "relative", display: "inline-block" }}>
            <button
              style={{ marginLeft: "30px", borderRadius: "5px" }}
              onClick={handleMenuVisible}
            >
              Create Media
            </button>

            {isMenuVisible && (
              <div
                id="mediaMenu"
                style={{
                  display: "block",
                  position: "absolute",
                  top: "100%",
                  left: 0,
                  backgroundColor: "#fff",
                  boxShadow: "0 2px 4px rgba(0, 0, 0, 0.1)",
                  padding: "10px",
                  zIndex: 1,
                }}
              >
                <p
                  onClick={() =>
                    redirectTo("/aims-ecommerce/admin/create-book")
                  }
                >
                  Book
                </p>
                <p
                  onClick={() => redirectTo("/aims-ecommerce/admin/create-cd")}
                >
                  CD
                </p>
                <p
                  onClick={() => redirectTo("/aims-ecommerce/admin/create-dvd")}
                >
                  DVD
                </p>
                <p
                  onClick={() => redirectTo("/aims-ecommerce/admin/create-lp")}
                >
                  LP
                </p>
              </div>
            )}
          </div>
        </div>
        <div className="container" style={{ height: "50%" }}>
          {media.map((mediaItem) => (
            <Media
              key={mediaItem.id}
              props={{
                title: mediaItem.title,
                price: mediaItem.price,
                quantity: mediaItem.quantityInStock,
                photo: mediaItem.imageUrl,
                category: mediaItem.category,
              }}
            />
          ))}
        </div>
        <div
          style={{
            marginTop: "auto",
          }}
        >
          <Pagination pagination={pagination} onPageChange={handlePageChange} />
        </div>
      </div>
    </>
  );
}
