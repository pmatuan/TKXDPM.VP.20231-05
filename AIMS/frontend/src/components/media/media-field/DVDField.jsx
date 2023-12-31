import InputField from "../custom-field/customInputField";
import RadioButtonField from "../custom-field/customRadioButtonField";

const disFormats = [
  { value: "UNKNOWN", label: "Chưa rõ" },
  { value: "BLURAY", label: "BLURAY" },
  { value: "HDDVD", label: "HDDVD" },
];
export default function DVDField() {
  return (
    <div>
      <RadioButtonField
        name="disFormat"
        options={disFormats}
        label="Loại đĩa"
      />
      <InputField name="directors" type="text" label="Đạo diễn" />
      <InputField name="runTime" type="number" label="Thời lượng" />
      <InputField name="studio" type="text" label="Hãng sản xuất" />
      <InputField name="languague" type="text" label="Ngôn ngữ" />
      <InputField name="subtitiles" type="text" label="Phụ đề" />
      <InputField name="genres" type="text" label="Loại" />
      <InputField name="releaseDate" type="date" label="Ngày ra mắt" />
    </div>
  );
}
