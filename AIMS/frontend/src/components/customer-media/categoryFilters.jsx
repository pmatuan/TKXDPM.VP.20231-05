import { useEffect, useState } from "react";
import Pagination from "./pagination";
import data from "../../data.json";
import Product from "./product";
import queryString from "query-string";
import { set } from "date-fns";
import ViewDetailMedia from "./viewDetailMedia";

export default function ProductOverview({ title, cartId }) {
  const [titleSearch, setTitleSearch] = useState("");
  const [media, setMedia] = useState([]);
  const [selectedOption, setSelectedOption] = useState("");
  const [pagination, setPagination] = useState({
    page: 0,
    size: 20,
    total: 1,
  });

  const [filter, setFilter] = useState({ page: 0, size: 20 });
  const [sortType, setSortType] = useState(false);
  const [mediaView, setMediaView] = useState();
  const [viewDetail, setViewDetail] = useState(false);
  const [resetFilter, setResetFilter] = useState(false);
  function handlePageChange(newPage) {
    setFilter({ ...filter, page: newPage });
  }
  const handleOptionChange = (e) => {
    setSelectedOption(e.target.value);
    if (e.target.value === "none") {
      setFilter((prev) => {
        const newFilter = { ...prev };
        delete newFilter.category;
        return newFilter;
      });
    } else setFilter((prev) => ({ ...prev, category: e.target.value }));
  };
  const handleSubmitSearch = (e) => {
    e.preventDefault();
    if (titleSearch === "") {
      setFilter((prev) => {
        const newFilter = { ...prev };
        delete newFilter.title;
        return newFilter;
      });
    } else {
      setFilter((prev) => ({ ...prev, title: titleSearch }));
      setTitleSearch("");
    }
  };

  useEffect(() => {
    async function fetchMedia() {
      try {
        const paramsString = queryString.stringify(filter);
        const requestUrl = `http://127.0.0.1:8080/api/v1/media?${paramsString}`;
        console.log("requestUrl", requestUrl);
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

  return (
    <>
      <div className="card card-product card-plain">
        <div className="d-flex border-bottom pb-3">
          {title.length != 0 && <h2 className="mb-3">{title}</h2>}
          <div className="d-flex ms-auto align-items-center">
            <div className="dropdown">
              <button
                className="btn btn-link text-dark mb-0 dropdown-toggle"
                type="button"
                id="sortButton"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                Sắp xếp
              </button>
              <ul className="dropdown-menu" aria-labelledby="sortButton">
                <li>
                  <a
                    className="dropdown-item"
                    onClick={() => setSortType("asc")}
                  >
                    Giá: Thấp đến cao
                  </a>
                </li>
                <li>
                  <a
                    className="dropdown-item"
                    onClick={() => setSortType("des")}
                  >
                    Giá: Cao đến thấp
                  </a>
                </li>
                <li>
                  <a
                    className="dropdown-item"
                    onClick={() => setSortType(false)}
                  >
                    Không sắp xếp
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </div>
        <div className="row mt-5">
          <div className="col-12 col-md-2">
            <form onSubmit={handleSubmitSearch}>
              <div className="input-group mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Tìm kiếm"
                  aria-label="Tìm kiếm"
                  aria-describedby="basic-addon2"
                  value={titleSearch}
                  onChange={(e) => setTitleSearch(e.target.value)}
                />
                <div className="input-group-append">
                  <button className="input-group-text" id="basic-addon2">
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      width="16"
                      height="16"
                      fill="currentColor"
                      className="bi bi-search"
                      viewBox="0 0 16 16"
                    >
                      <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0" />
                    </svg>
                  </button>
                </div>
              </div>
            </form>

            <div className="mt-5">
              <p>
                <label className="d-flex gap-2">
                  <input
                    id="1"
                    type="radio"
                    value="book"
                    name="mediaType"
                    checked={selectedOption === "book"}
                    onChange={handleOptionChange}
                  />
                  Sách
                </label>
              </p>
              <p>
                <label className="d-flex gap-2">
                  <input
                    id="2"
                    type="radio"
                    value="cd"
                    name="mediaType"
                    checked={selectedOption === "cd"}
                    onChange={handleOptionChange}
                  />
                  CD
                </label>
              </p>

              <p>
                <label className="d-flex gap-2">
                  <input
                    id="3"
                    type="radio"
                    value="dvd"
                    name="mediaType"
                    checked={selectedOption === "dvd"}
                    onChange={handleOptionChange}
                  />
                  DVD
                </label>
              </p>
              <p>
                <label className="d-flex gap-2">
                  <input
                    id="4"
                    type="radio"
                    value="lp"
                    name="mediaType"
                    checked={selectedOption === "lp"}
                    onChange={handleOptionChange}
                  />
                  LP
                </label>
              </p>
              <p>
                <label className="d-flex gap-2">
                  <input
                    id="5"
                    type="radio"
                    value="none"
                    name="mediaType"
                    checked={selectedOption === "none"}
                    onChange={handleOptionChange}
                  />
                  Xóa bộ lọc
                </label>
              </p>
            </div>
          </div>
          <div className="col-12 col-md-10 pb-6">
            {!sortType && (
              <div className="row row-gap-7">
                {media.map((product) => (
                  <div className="col-md-10 col-lg-4">
                    <Product
                      id={product.id}
                      imageUrl={product.imageUrl}
                      title={product.title}
                      description={product.description}
                      price={product.price}
                      quantityInStock={product.quantityInStock}
                      cartId={cartId}
                      product={product}
                      position="center"
                      setMediaView={setMediaView}
                      setViewDetail={setViewDetail}
                    />
                  </div>
                ))}
              </div>
            )}
            {sortType === "asc" && (
              <div className="row row-gap-7">
                {media
                  .slice() // Create a copy of the array to avoid mutating the original data
                  .sort((a, b) => a.price - b.price) // Sort products based on price (ascending order)
                  .map((product) => (
                    <div className="col-md-10 col-lg-4" key={product.id}>
                      <Product
                        id={product.id}
                        imageUrl={product.imageUrl}
                        title={product.title}
                        description={product.description}
                        price={product.price}
                        quantityInStock={product.quantityInStock}
                        cartId={cartId}
                        product={product}
                        position="center"
                        setMediaView={setMediaView}
                        setViewDetail={setViewDetail}
                      />
                    </div>
                  ))}
              </div>
            )}
            {sortType === "des" && (
              <div className="row row-gap-7">
                {media
                  .slice() // Create a copy of the array to avoid mutating the original data
                  .sort((a, b) => b.price - a.price) // Sort products based on price (descending order)
                  .map((product) => (
                    <div className="col-md-10 col-lg-4" key={product.id}>
                      <Product
                        id={product.id}
                        imageUrl={product.imageUrl}
                        title={product.title}
                        description={product.description}
                        price={product.price}
                        quantityInStock={product.quantityInStock}
                        cartId={cartId}
                        product={product}
                        position="center"
                        setMediaView={setMediaView}
                        setViewDetail={setViewDetail}
                      />
                    </div>
                  ))}
              </div>
            )}
          </div>
          {viewDetail && (
            <ViewDetailMedia
              mediaView={mediaView}
              setViewDetail={setViewDetail}
            />
          )}
          <Pagination pagination={pagination} onPageChange={handlePageChange} />
        </div>
      </div>
    </>
  );
}
