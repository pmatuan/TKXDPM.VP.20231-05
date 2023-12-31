import React from "react";
import PropTypes from "prop-types";
import { useState, useRef } from "react";

MediaFiltersForm.propTypes = {
  onSubmit: PropTypes.func,
};

MediaFiltersForm.defaultProps = {
  onSubmit: null,
};

export default function MediaFiltersForm(props) {
  const { onSubmit } = props;
  const [searchTerm, setSearchTerm] = useState("");
  const typingTimeoutRef = useRef(null);

  function handleSearchTermChange(e) {
    const value = e.target.value;
    setSearchTerm(e.target.value);

    if (!onSubmit) return;

    if (typingTimeoutRef.current) {
      clearTimeout(typingTimeoutRef.current);
    }

    typingTimeoutRef.current = setTimeout(() => {
      const formValues = {
        searchTerm: value,
      };
      onSubmit(formValues);
    }, 300);
  }

  return (
    <div>
      <form>
        <input
          type="text"
          value={searchTerm}
          onChange={handleSearchTermChange}
          className="form-control"
          placeholder="Nhập vào tiêu đề ..."
          style={{ borderWidth: "2px" }}
        />
      </form>
    </div>
  );
}
