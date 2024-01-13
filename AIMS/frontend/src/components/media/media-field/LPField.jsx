import InputField from "../custom-field/customInputField.jsx";

export default function LPField() {
  return (
    <div>
      <InputField name="artists" type="text" label="Nghệ sĩ" />
      <InputField name="recordLabel" type="text" label="Hãng thu âm" />
      <InputField name="trackList" type="text" label="Track List" />
      <InputField name="genres" type="text" label="Loại" />
      <InputField name="releaseDate" type="date" label="Ngày ra mắt" />
    </div>
  );
}
