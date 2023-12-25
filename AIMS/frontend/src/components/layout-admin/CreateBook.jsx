import BookForm from "../media/form/BookForm.jsx";
import { useState, useEffect } from "react";

export default function CreateBook() {
  const [values, setValues] = useState({});
  const [submit, setSubmit] = useState(false);
  const [isLoading, setIsLoading] = useState("");

  const initialValues = { isAbleToRushDelivery: false };

  if (submit === true) {
    const postData = async () => {
      try {
        setIsLoading(true);

        // Replace 'YOUR_API_URL' with the actual URL where you want to send the POST request
        const response = await fetch(
          "http://127.0.0.1:8080/api/v1/media?type=book",
          {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify({
              values,
            }),
          }
        );

        if (!response.ok) {
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
    <div>
      <div>
        {isLoading == "" ? (
          <p>Fill out the information </p>
        ) : isLoading ? (
          <p>Loading...</p>
        ) : (
          <pre>Success!</pre>
        )}
      </div>
      <BookForm
        initialValues={initialValues}
        setValues={setValues}
        setSubmit={setSubmit}
      />
    </div>
  );
}
