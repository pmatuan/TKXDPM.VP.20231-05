import InputField from "../custom-field/customInputField.jsx";
import FileField from "../custom-field/customFileField.jsx";

export default function MediaField() {
  return (
    <>
      <InputField name="title" type="text" label="Title" />
      <InputField name="category" type="text" label="Category" />
      <InputField name="value" type="number" label="Value" />
      <InputField name="price" type="number" label="Price" />
      <InputField
        name="quantityInStock"
        type="number"
        label="Quantity In Stock"
      />
      <InputField
        name="isAbleToRushDelivery"
        type="checkbox"
        label="Is Able To Rush Delivery"
      />
      <InputField name="weight" label="Weight" />
      <FileField name="imageFile" label="Image " />
      <FileField name="barCodeFile" label="BarCode " />
      <InputField name="description" type="text" label="Description" />
      <InputField name="importDate" type="date" label="Import Date" />
    </>
  );
}
