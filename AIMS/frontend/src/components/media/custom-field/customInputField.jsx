import React from "react";
import { Field } from "react-final-form";

const InputField = ({ name, type, label }) => (
  <div className="mb-3 row">
    <label
      htmlFor={name}
      className="form-label col-6"
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
    <div className="col-6">
      {type === "checkbox" ? (
        <Field
          name={name}
          component="input"
          type="checkbox"
          className="form-check-input"
        />
      ) : (
        <>
          <Field
            name={name}
            component="input"
            type={type}
            className="form-control"
          />
          <div style={{ color: "red" }}>
            <Field name={name} subscription={{ touched: true, error: true }}>
              {({ meta: { touched, error } }) =>
                touched && error ? <span>{error}</span> : null
              }
            </Field>
          </div>
        </>
      )}
    </div>
  </div>
);

export default InputField;
