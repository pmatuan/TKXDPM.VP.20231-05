import React from "react";
import { Form } from "react-final-form";
import MediaField from "../media-field/MediaField.jsx";
import DVDField from "../media-field/DVDField.jsx";

const validate = (values) => {
  const errors = {};

  if (!values.title) {
    errors.title = "Required";
  }

  if (!values.disFormat) {
    errors.disFormat = "Required";
  }

  if (!values.value) {
    errors.value = "Required";
  } else if (isNaN(values.value) || values.value <= 0) {
    errors.value = "Invalid";
  }

  if (!values.price) {
    errors.price = "Required";
  } else if (isNaN(values.price) || values.price <= 0) {
    errors.price = "Invalid";
  }

  if (!values.quantityInStock) {
    errors.quantityInStock = "Required";
  } else if (
    Number.isInteger(values.quantityInStock) ||
    values.quantityInStock < 0
  ) {
    errors.quantityInStock = "Invalid";
  }
  return errors;
};

const sleep = (ms) => new Promise((resolve) => setTimeout(resolve, ms));

const onSubmit = async (values) => {
  await sleep(300);
  window.alert(JSON.stringify(values, 0, 2));
};

const DVDForm = () => {
  return (
    <div
      className="container mt-5"
      style={{
        backgroundColor: "rgb(249, 250, 251)",
        padding: "20px",
        borderRadius: "10px",
      }}
    >
      <Form
        onSubmit={onSubmit}
        validate={validate}
        initialValues={{ isAbleToRushDelivery: false }}
        render={({ handleSubmit, form, submitting, pristine, values }) => (
          <form onSubmit={handleSubmit}>
            <div className="row">
              <div className="col-md-6" style={{ "padding-left": "0px" }}>
                <MediaField />
              </div>
              <div className="col-md-6">
                <DVDField />
              </div>
            </div>

            <div className="text-center mt-3">
              <button
                type="submit"
                className="btn btn-primary"
                disabled={submitting || pristine}
              >
                Submit
              </button>
              <button
                type="button"
                className="btn btn-secondary ms-2"
                onClick={form.reset}
                disabled={submitting || pristine}
              >
                Reset
              </button>
            </div>
            <pre>{JSON.stringify(values, 0, 2)}</pre>
          </form>
        )}
      />
    </div>
  );
};

export default DVDForm;
