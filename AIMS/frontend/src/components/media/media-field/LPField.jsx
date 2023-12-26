import InputField from "../custom-field/customInputField.jsx";

export default function LPField() {
  return (
    <div>
      <InputField name="artists" type="text" label="Artist" />
      <InputField name="recordLabel" type="text" label="Record Label" />
      <InputField name="trackList" type="date" label="Track List" />
      <InputField name="genres" type="number" label="Genres" />
      <InputField name="releaseDate" type="date" label="Release Date" />
    </div>
  );
}
