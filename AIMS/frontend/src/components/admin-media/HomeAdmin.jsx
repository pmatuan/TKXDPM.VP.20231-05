// HomeAdmin.jsx
import Header from "./Header.jsx";
import AdminNavbar from "../../components/admin/adminNavbar.tsx"
import Media from "../media/Media.jsx";
import Pagination from "../media/pagination/Pagination.jsx";
import React, { useState, useEffect } from "react";
import queryString from "query-string";
import MediaFiltersForm from "../media/MediaFiltersForm.jsx";
import UpdateMedia from "../media/form/update-form/UpdateMedia.jsx";

export default function HomeAdmin() {
  const [isMenuVisible, setMenuVisible] = useState(false);
  const [messageDelete, setMessageDelete] = useState("");
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

  const [updateMedia, setUpdateMedia] = useState(false);
  const [mediaUpdateId, setMediaUpdateId] = useState("");
  const [deletedIds, setDeletedIds] = useState([]);
  const [selectStatus, setSelectStatus] = useState(false);

  ///

  function handleDeleteMedia() {
    if (deletedIds.length > 0) {
      const deleteMedia = async () => {
        try {
          const requestUrl = "http://127.0.0.1:8080/api/v1/media";
          const requestOptions = {
            method: "DELETE",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify({ ids: deletedIds }),
          };

          const response = await fetch(requestUrl, requestOptions);

          if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
          }

          const responseData = await response.json();
          console.log("Delete response:", responseData);

          setMessageDelete("Deleted");
        } catch (error) {
          console.error("Error deleting media:", error.message);
        } finally {
          setDeletedIds([]);
          setMessageDelete("Deleted");
        }
      };

      deleteMedia();
    } else setMessageDelete("No media selected");
  }

  function handleSelectMedias() {
    setSelectStatus(!selectStatus);
    if (!selectStatus) {
      setDeletedIds([]);
    }
  }
  useEffect(() => {
    if (messageDelete) {
      const timerId = setTimeout(() => {
        setMessageDelete("");
      }, 1000);
      return () => clearTimeout(timerId);
    }
  }, [messageDelete]);

  const handleMenuVisible = () => {
    setMenuVisible(!isMenuVisible);
  };

  const redirectTo = (url) => {
    window.location.href = url;
  };

  const handlePageChange = (newPage) => {
    setFilter({
      ...filter,
      page: newPage,
    });
  };

  const handleMediaDelete = (id) => {
    setMessageDelete("Deleted");
    // setMedia((prevMedia) =>
    //   prevMedia.filter((mediaItem) => mediaItem.id !== id)
    // );
  };

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
  }, [filter, messageDelete, updateMedia]);

  function handleFiltersChange(newFilters) {
    if (newFilters) {
      setFilter({
        ...filter,
        page: 0,
        title: newFilters.searchTerm,
      });
    } else
      setFilter((prev) => {
        delete prev.title;
        return prev;
      });
  }

  return (
    <>
      {/* <Header /> */}
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
          <div style={{ position: "relative", display: "inline-block" }}>
            <button
              className="btn btn-dark mt-3"
              style={{ marginLeft: "30px", borderRadius: "5px" }}
              onClick={handleMenuVisible}
            >
              Tạo Media
            </button>
            <button
              className="btn btn-dark mt-3"
              style={{ marginLeft: "30px", borderRadius: "5px" }}
              onClick={() => {
                handleSelectMedias();
              }}
            >
              Chọn nhiều sản phẩm
            </button>
            <button
              className="btn btn-dark mt-3"
              style={{ marginLeft: "30px", borderRadius: "5px" }}
              onClick={() => {
                handleDeleteMedia();
              }}
            >
              Xóa
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
                    redirectTo("/aims-ecommerce/management/media/create-book")
                  }
                >
                  Book
                </p>
                <p
                  onClick={() =>
                    redirectTo("/aims-ecommerce/management/media/create-cd")
                  }
                >
                  CD
                </p>
                <p
                  onClick={() =>
                    redirectTo("/aims-ecommerce/management/media/create-dvd")
                  }
                >
                  DVD
                </p>
                <p
                  onClick={() =>
                    redirectTo("/aims-ecommerce/management/media/create-lp")
                  }
                >
                  LP
                </p>
              </div>
            )}
          </div>
        </div>
        <p style={{ color: "green" }}>{messageDelete}</p>
        <div className="container" style={{ height: "50%" }}>
          {media.map((mediaItem) => (
            <>
              <Media
                key={mediaItem.id}
                props={{
                  id: mediaItem.id,
                  title: mediaItem.title,
                  price: mediaItem.price,
                  quantity: mediaItem.quantityInStock,
                  photo: mediaItem.imageUrl,
                  category: mediaItem.category,
                }}
                onDelete={handleMediaDelete}
                setUpdateMedia={setUpdateMedia}
                setMediaUpdateId={setMediaUpdateId}
                setDeleteIds={setDeletedIds}
                selectStatus={selectStatus}
              />

              {updateMedia && mediaUpdateId == mediaItem.id && (
                <UpdateMedia
                  setUpdateMedia={setUpdateMedia}
                  mediaItem={mediaItem}
                />
              )}
            </>
          ))}
        </div>
        <div
          style={{
            marginTop: "auto",
          }}
        >
          --------------------------------------
          <Pagination pagination={pagination} onPageChange={handlePageChange} />
        </div>
      </div>
    </>
  );
}
