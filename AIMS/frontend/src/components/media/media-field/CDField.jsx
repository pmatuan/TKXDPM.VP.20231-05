import InputField from "../custom-field/customInputField.jsx";

export default function CDField() {
  return (
    <div>
      <InputField name="artists" type="text" label="Artist" />
      <InputField name="recordLabel" type="text" label="Record Label" />
      <InputField name="trackList" type="text" label="Track List" />
      <InputField name="genres" type="text" label="Genres" />
      <InputField name="releaseDate" type="date" label="Release Date" />
    </div>
  );
}
