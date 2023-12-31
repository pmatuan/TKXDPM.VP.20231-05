import RadioButtonField from "../custom-field/customRadioButtonField.jsx";
import InputField from "../custom-field/customInputField.jsx";

const coverTypes = [
  { value: "UNKNOWN", label: "Chưa rõ" },
  { value: "PAPERBACK", label: "Bìa mềm" },
  { value: "HARDCOVER", label: "Bìa cứng" },
];

export default function BookField() {
  return (
    <div>
      <InputField name="authors" type="text" label="Tác giả" />
      <RadioButtonField
        name="coverType"
        options={coverTypes}
        label="Loại bìa"
      />
      <InputField name="publisher" type="text" label="Nhà sản xuất" />
      <InputField name="publicationDate" type="date" label="Ngày xuất bản" />
      <InputField name="pages" type="number" label="Số trang" />
      <InputField name="language" type="text" label="Ngôn ngữ" />
      <InputField name="type" type="text" label="Loại sách" />
    </div>
  );
}
