import React from "react";
import PropTypes from "prop-types";

Pagination.propTypes = {
  pagination: PropTypes.object.isRequired,
  onPageChange: PropTypes.func,
};

Pagination.defaultProps = {
  onPageChange: null,
};

export default function Pagination(props) {
  const { pagination, onPageChange } = props;
  const { page, size, total } = pagination;
  const totalPages = Math.ceil(total / size);

  const handlePageChange = (newPage) => {
    if (onPageChange) {
      onPageChange(newPage);
    }
  };

  return (
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <button
        className="btn btn-dark"
        style={{ borderRadius: "5px" }}
        disabled={page <= 0}
        onClick={() => handlePageChange(page - 1)}
      >
        Prev
      </button>

      <button className="btn btn-close-white mx-1" style={{ borderRadius: "5px" }}>{page}</button>


      
      <button
        className="btn btn-dark"
        style={{ borderRadius: "5px" }}
        disabled={page >= totalPages - 1}
        onClick={() => handlePageChange(page + 1)}
      >
        Next
      </button>
    </div>
  );
}
