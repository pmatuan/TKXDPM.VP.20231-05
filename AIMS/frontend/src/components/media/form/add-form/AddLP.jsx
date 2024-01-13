import React, { useState } from "react";
import { Form } from "react-final-form";
import MediaField from "../../media-field/MediaField.jsx";
import LPField from "../../media-field/LPField.jsx";

const validate = (values) => {
  const errors = {};

  if (!values.title) {
    errors.title = "Bắt buộc";
  }
  if (!values.imageUrl) {
    errors.imageUrl = "Bắt buộc";
  }
  if (!values.value) {
    errors.value = "Bắt buộc";
  } else if (isNaN(values.value) || values.value <= 0) {
    errors.value = "Không hợp lệ";
  }

  if (!values.price) {
    errors.price = "Bắt buộc";
  } else if (isNaN(values.price) || values.price <= 0) {
    errors.price = "Không hợp lệ";
  }

  if (!values.quantityInStock) {
    errors.quantityInStock = "Bắt buộc";
  } else {
    const quantityAsNumber = parseInt(values.quantityInStock, 10);

    if (
      isNaN(quantityAsNumber) ||
      !Number.isInteger(quantityAsNumber) ||
      quantityAsNumber < 0
    ) {
      errors.quantityInStock = "Không hợp lệ";
    }
  }
  if (!values.releaseDate) {
    errors.releaseDate = "Bắt buộc";
  }
  if (!values.importDate) {
    errors.importDate = "Bắt buộc";
  }
  return errors;
};

export default function AddLP() {
  const [isLoading, setIsLoading] = useState("Điền thông tin vào biểu mẫu");

  function onSubmit(values, form) {
    const postData = async () => {
      try {
        setIsLoading(true);
        const image = values.imageUrl[0];

        delete values.imageUrl;

        const formData = new FormData();
        Object.keys(values).forEach((key) => formData.append(key, values[key]));
        formData.append("file", image);

        const response = await fetch(
          "http://127.0.0.1:8080/api/v1/media?mediaType=lp",
          {
            method: "POST",
            body: formData,
          }
        );

        if (response.ok) {
          form.reset();
          setIsLoading("Thêm thành công");
        } else if (!response.ok) {
          throw new Error("Network response was not ok");
        }
      } catch (error) {
        console.error("Error sending POST request:", error);
      } finally {
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
        <pre style={{ color: "green" }}>{isLoading}</pre>
      </div>
      <Form
        onSubmit={onSubmit}
        validate={validate}
        initialValues={{ isAbleToRushDelivery: false, category: "lp" }}
        render={({ handleSubmit, form, submitting, pristine, values }) => (
          <form onSubmit={handleSubmit}>
            <div className="row">
              <div className="col-md-6" style={{ paddingLeft: "0px" }}>
                <MediaField />
              </div>
              <div className="col-md-6">
                <LPField />
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
          </form>
        )}
      />
    </div>
  );
}
