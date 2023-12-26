import React from "react";
import { Field } from "react-final-form";

const RadioButtonField = ({ name, options, label }) => (
  <div className="mb-3">
    <label
      className="form-label"
      style={{
        fontSize: "13px",
        fontWeight: "600",
        lineHeight: "20.8px",
        marginBottom: "6px",
        marginLeft: "2px",
      }}
    >
      {label}
    </label>
    <div className="form-check">
      {options.map((option, index) => (
        <div key={option.value} className="form-check form-check-inline">
          <Field
            name={name}
            component="input"
            type="radio"
            value={option.value}
            id={`${name}-${index}`}
            className="form-check-input"
          />
          <label
            htmlFor={`${name}-${index}`}
            className="form-check-label"
            style={{ marginLeft: "10px" }}
          >
            {option.label}
          </label>
        </div>
      ))}
    </div>

    <div style={{ color: "red" }}>
      <Field name={name} subscription={{ touched: true, error: true }}>
        {({ meta: { touched, error } }) =>
          touched && error ? <span>{error}</span> : null
        }
      </Field>
    </div>
  </div>
);

export default RadioButtonField;
