import InputField from "../custom-field/customInputField.jsx";
import FileField from "../custom-field/customFileField.jsx";

export default function MediaField() {
  return (
    <>
      <InputField name="title" type="text" label="Tiêu đề" />
      <InputField name="value" type="number" label="Giá trị" />
      <InputField name="price" type="number" label="Giá bán" />
      <InputField
        name="quantityInStock"
        type="number"
        label="Số lượng trong kho"
      />
      <InputField
        name="isAbleToRushDelivery"
        type="checkbox"
        label="Có thể giao nhanh"
      />
      <InputField name="weight" label="Cân nặng" />
      <FileField name="imageUrl" label="Hình ảnh" />
      <InputField name="barcodeUrl" label="Mã code" />
      <InputField name="description" type="text" label="Mô tả" />
      <InputField name="importDate" type="date" label="Ngày nhập kho" />
    </>
  );
}
