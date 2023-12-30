import React from "react";
import { Button, ButtonGroup } from "react-bootstrap";
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
    <div className="d-flex justify-content-center mt-4">
      <ButtonGroup
        aria-label="Basic example"
        className="align-items-center"
        style={{ marginTop: 16 }}
      >
        <Button
          disabled={page <= 0}
          onClick={() => handlePageChange(page - 1)}
          className="mb-0"
          variant="primary"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="16"
            height="16"
            fill="currentColor"
            className="bi bi-arrow-left"
            viewBox="0 0 16 16"
          >
            <path
              fill-rule="evenodd"
              d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8"
            />
          </svg>
        </Button>
        <div style={{ width: 40, textAlign: "center" }}>{page}</div>
        <Button
          className="mb-0"
          variant="primary"
          disabled={page >= totalPages - 1}
          onClick={() => handlePageChange(page + 1)}
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="16"
            height="16"
            fill="currentColor"
            className="bi bi-arrow-right"
            viewBox="0 0 16 16"
          >
            <path
              fill-rule="evenodd"
              d="M1 8a.5.5 0 0 1 .5-.5h11.793l-3.147-3.146a.5.5 0 0 1 .708-.708l4 4a.5.5 0 0 1 0 .708l-4 4a.5.5 0 0 1-.708-.708L13.293 8.5H1.5A.5.5 0 0 1 1 8"
            />
          </svg>
        </Button>
      </ButtonGroup>
    </div>
  );
}
