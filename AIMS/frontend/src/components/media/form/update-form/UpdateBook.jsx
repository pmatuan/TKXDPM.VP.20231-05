import { Form } from "react-final-form";
import MediaField from "../../media-field/MediaField.jsx";
import BookField from "../../media-field/BookField.jsx";

const validate = (values) => {
  const errors = {};

  if (!values.authors) {
    errors.authors = "Required";
  }

  if (!values.coverType) {
    errors.coverType = "Required";
  }

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
  } else {
    const quantityAsNumber = parseInt(values.quantityInStock, 10);
    if (
      isNaN(quantityAsNumber) ||
      !Number.isInteger(quantityAsNumber) ||
      quantityAsNumber < 0
    ) {
      errors.quantityInStock = "Invalid";
    }
  }

  if (!values.publicationDate) {
    errors.publicationDate = "Required";
  }
  if (!values.importDate) {
    errors.importDate = "Required";
  }
  return errors;
};

export default function UpdateBook({ initialValuesProps, setUpdateStatus }) {
  console.log(initialValuesProps);
  function onSubmit(values) {
    const patchData = async () => {
      try {
        setUpdateStatus("Updating...");
        const formData = new FormData();
        if (!values.imageUrl) {
          values.imageUrl = initialValuesProps.imageUrl;
          Object.keys(values).forEach((key) =>
            formData.append(key, values[key])
          );
        } else {
          const image = values.imageUrl[0];
          formData.append("file", image);
          Object.keys(values).forEach((key) =>
            formData.append(key, values[key])
          );
        }

        const response = await fetch(
          `http://127.0.0.1:8080/api/v1/media/${values.id}`,
          {
            method: "PATCH",

            body: formData,
          }
        );
        if (response.ok) {
          setUpdateStatus("Updated Success!");
        } else if (!response.ok) {
          throw new Error("Network response was not ok");
        }
      } catch (error) {
        console.error("Error sending PACTH request:", error);
        setUpdateStatus("Update Failed. Please try again.");
      } finally {
        setUpdateStatus("Update Complete");
      }
    };
    patchData();
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
      <Form
        onSubmit={onSubmit}
        validate={validate}
        initialValues={initialValuesProps}
        render={({ handleSubmit, form, submitting, pristine, values }) => (
          <form onSubmit={handleSubmit}>
            <div className="row">
              <div className="col-md-6" style={{ paddingLeft: "0px" }}>
                <MediaField />
              </div>
              <div className="col-md-6">
                <BookField />
              </div>
            </div>

            <div className="text-center mt-3">
              <button
                type="submit"
                className="btn btn-primary"
                disabled={submitting || pristine}
              >
                Update
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
