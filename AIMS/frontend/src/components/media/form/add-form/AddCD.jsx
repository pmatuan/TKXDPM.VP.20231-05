import React, { useState } from "react";
import { Form } from "react-final-form";
import MediaField from "../../media-field/MediaField.jsx";
import CDField from "../../media-field/CDField.jsx";

const validate = (values) => {
  const errors = {};

  if (!values.title) {
    errors.title = "Required";
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

export default function AddCD() {
  const [isLoading, setIsLoading] = useState("start");
  const [initialValues, setInitialValues] = useState({});
  function onSubmit(values) {
    console.log(JSON.stringify(values));
    const postData = async () => {
      try {
        setIsLoading(true);

        const response = await fetch(
          "http://127.0.0.1:8080/api/v1/media?type=cd",
          {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify(values),
          }
        );

        if (response.oke) {
          setInitialValues({ isAbleToRushDelivery: false });
        } else if (!response.ok) {
          throw new Error("Network response was not ok");
        }
      } catch (error) {
        console.error("Error sending POST request:", error);
      } finally {
        setIsLoading(false);
      }
    };
    postData();
  }
  return (
    <div
      className="container mt-5"
      style={{
        backgroundColor: "rgb(249, 250, 251)",
        padding: "20px",
        borderRadius: "10px",
      }}
    >
      <div>
        {isLoading == "start" ? (
          <p>Fill in the form</p>
        ) : isLoading ? (
          <p>Loading...</p>
        ) : (
          <pre style={{ color: "green" }}>Success!</pre>
        )}
      </div>
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
                <CDField />
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
}
